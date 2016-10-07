package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionBatch;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Env;
import org.eevolution.service.dsl.ProcessBuilder;


/**
 * 
 * Process to create production lines based on the plans
 * defined for a particular production header
 * @author Paul Bowden
 *
 */
public class ProductionCreate extends SvrProcess {

	private int p_M_Production_ID					=0;
	private MProduction m_production 			= null;
	private boolean mustBeStocked 				= false;  //not used
	private boolean recreate 							= false;
	private BigDecimal newQty 						= null;
	private int S_Resource_ID 						= 0;
	private Boolean recalculate 						= false;
	private int C_AcctSchema_ID 					= 0;
	private MProduct  finishedProduct				= null;


	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			if ("Recreate".equals(name))
				recreate = para.getParameterAsBoolean();
			else if ("ProductionQty".equals(name))
				newQty = para.getParameterAsBigDecimal();
			else if ("S_Resource_ID".equals(name))
				S_Resource_ID = para.getParameterAsInt();
			else if ("recalculate".equals(name))
				recalculate = para.getParameterAsBoolean();
			else if (MAcctSchema.COLUMNNAME_C_AcctSchema_ID.equals(name))
				C_AcctSchema_ID = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);	
		}						
	p_M_Production_ID = getRecord_ID();
	m_production = new MProduction(getCtx(), p_M_Production_ID, get_TrxName());
	finishedProduct = (MProduct)m_production.getM_Product();
	}	//	prepare

	@Override
	protected String doIt() throws Exception {

		if ( m_production.get_ID() == 0 )
			throw new AdempiereUserError("Could not load production header");

		if ( m_production.isProcessed() )
			return "Already processed";
		if (recalculate)
			recalculate();
		return createLines();

	}
	

	protected String createLines() throws Exception {
		
		int created = 0;
		isBom(m_production.getM_Product_ID());
		
		if (!recreate && "true".equalsIgnoreCase(m_production.get_ValueAsString("IsCreated")))
			throw new AdempiereUserError("Production already created.");

		// Check batch having production planned Qty.
		BigDecimal cntQty = Env.ZERO;
		MProductionBatch pBatch = (MProductionBatch) m_production.getM_Production_Batch();
		for (MProduction p : pBatch.getHeaders(true))
		{
			if (p.getM_Production_ID() != m_production.getM_Production_ID())
				cntQty = cntQty.add(p.getProductionQty());
		}

		BigDecimal maxPlanQty = pBatch.getTargetQty().subtract(cntQty);
		if (newQty.compareTo(maxPlanQty) > 0)
			throw new AdempiereUserError("Production batch target qty is: " + pBatch.getTargetQty()
					+ " <BR/>Total production planned qty on current batch is: " + cntQty
					+ " <BR/>Maximum plan qty should be: " + maxPlanQty);

		if (newQty != null )
			m_production.setProductionQty(newQty);
		
		m_production.deleteLines(get_TrxName());
		m_production.createLines(mustBeStocked);
		created = m_production.getLines().length;
		if ( created == 0 ) 
		{return "Failed to create production lines"; }
		
		
		m_production.setIsCreated(true);
		m_production.save(get_TrxName());
		
		//jobrian - update Production Batch
		int M_Production_Batch_ID = m_production.get_ValueAsInt("M_Production_Batch_ID");
		MProductionBatch batch = new MProductionBatch(getCtx(), M_Production_Batch_ID, get_TrxName());
		batch.setQtyOrdered(m_production.getProductionQty());
		batch.save();
		
		return created + " production lines were created";
	}
	
	protected void isBom(int M_Product_ID) throws Exception
	{
		
		if (!finishedProduct.isBOM())
		{
			throw new AdempiereUserError ("Attempt to create product line for Non Bill Of Materials");
		}
	}
	
	private String recalculate ()
	{
		MAcctSchema as = MAcctSchema.get(getCtx(), C_AcctSchema_ID);
		MCostType ct 			= MCostType.getByMethodCosting(as, as.getCostingMethod());
		String costingLevel = finishedProduct.getCostingLevel(as);
		int AD_Org_ID 		= 	costingLevel.equals(MAcctSchema.COSTINGLEVEL_Organization)?m_production.getAD_Org_ID():0;
		int M_Warehouse_ID = costingLevel.equals(MAcctSchema.COSTINGLEVEL_Warehouse)?m_production.getM_Locator().getM_Warehouse_ID():0;
		ProcessInfo processInfo = ProcessBuilder.create(getCtx())
				.process(53062)
				.withRecordId(MProduction.Table_ID, getRecord_ID())
				.withParameter("C_AcctSchema_ID", C_AcctSchema_ID)
				.withParameter("S_Resource_ID", C_AcctSchema_ID)
				.withParameter("", as.getCostingMethod())
				.withParameter("M_CostType_ID", ct.getM_CostType_ID())
				.withParameter("ADOrg_ID", AD_Org_ID)
				.withParameter("M_Warehouse_ID", M_Warehouse_ID)
				.withParameter("CostingMethod", as.getCostingMethod())
				.withoutTransactionClose()
				.execute(get_TrxName());
		if (processInfo.isError())
			throw new AdempiereException(processInfo.getSummary());

		addLog(processInfo.getSummary());
		return "";
	}
}
