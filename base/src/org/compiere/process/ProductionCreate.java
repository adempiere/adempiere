package org.compiere.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MCostType;
import org.compiere.model.MProduction;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;


/**
 * 
 * Process to create production lines based on the plans
 * defined for a particular production header
 * @author Paul Bowden
 *
 */
public class ProductionCreate extends SvrProcess {

	private int p_M_Production_ID=0;
	private MProduction m_production = null;
	private boolean mustBeStocked = false;  //not used
	private boolean recreate = false;
	private BigDecimal newQty = null;
	private int p_M_CostType_ID =0;
	//private int p_M_Locator_ID=0;
	
	
	protected void prepare() {for (ProcessInfoParameter para : getParameter())
	{
		String name = para.getParameterName();
		if (para.getParameter() == null)
			;
			if ("Recreate".equals(name))
				recreate = "Y".equals(para.getParameter());
			else if ("ProductionQty".equals(name))
				newQty  = (BigDecimal) para.getParameter();
			else if (MCostType.COLUMNNAME_M_CostType_ID.equals(name))
				p_M_CostType_ID = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
		
		p_M_Production_ID = getRecord_ID();
		m_production = new MProduction(getCtx(), p_M_Production_ID, get_TrxName());

	}	//prepare

	@Override
	protected String doIt() throws Exception {

		if ( m_production.get_ID() == 0 )
			throw new AdempiereUserError("Could not load production header");

		if ( m_production.isProcessed() )
			return "Already processed";

		return createLines();

	}
	
	private boolean costsOK(int M_Product_ID) throws AdempiereUserError {
		// Warning will not work if non-standard costing is used
		// SHW Parameter p_M_Costtype 
		MCostType ct = new MCostType(getCtx(), p_M_CostType_ID, get_TrxName());
		if (!ct.getCostingMethod().equals(MCostType.COSTINGMETHOD_StandardCosting))
			return true;
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(M_Product_ID);
		params.add(p_M_CostType_ID);
		String sql = "SELECT ABS(((cc.currentcostprice-(SELECT SUM(c.currentcostprice*bom.qtybom)"
			+ " FROM m_cost c"
			+ " INNER JOIN pp_product_bomline bom ON (c.m_product_id=bom.m_product_id)"
			+ " JOIN pp_product_bom b ON (b.pp_product_bom_id = bom.pp_product_bom_id)"
			+ " WHERE b.m_product_id = pp.m_product_id)"
			+ " )/cc.currentcostprice))"
			+ " FROM m_product pp"
			+ " INNER JOIN m_cost cc on (cc.m_product_id=pp.m_product_id)"
			+ " WHERE cc.currentcostprice > 0 AND pp.M_Product_ID = ?"
			+ " AND cc.m_costtype_ID=?";
		
		BigDecimal costPercentageDiff = DB.getSQLValueBD(get_TrxName(), sql, params);
		
		if (costPercentageDiff == null)
		{
			throw new AdempiereUserError("Could not retrieve costs");
		}
		
		if ( (costPercentageDiff.compareTo(new BigDecimal("0.005")))< 0 )
			return true;
		
		return false;
	}

	protected String createLines() throws Exception {
		
		int created = 0;
		isBom(m_production.getM_Product_ID());
		MCostType ct = new MCostType(getCtx(), p_M_CostType_ID, get_TrxName());
		if (!costsOK(m_production.getM_Product_ID()) && ct.getCostingMethod().equals(MCostType.COSTINGMETHOD_StandardCosting))
			throw new AdempiereUserError("Excessive difference in standard costs");
		
		if (!recreate && "true".equalsIgnoreCase(m_production.get_ValueAsString("IsCreated")))
			throw new AdempiereUserError("Production already created.");
		
		if (newQty != null )
			m_production.setProductionQty(newQty);
		
		m_production.deleteLines(get_TrxName());
		created = m_production.createLines(mustBeStocked);
		if ( created == 0 ) 
		{return "Failed to create production lines"; }
		
		
		m_production.setIsCreated("Y");
		m_production.save(get_TrxName());
		return created + " production lines were created";
	}
	
	protected void isBom(int M_Product_ID) throws Exception
	{
		String bom = DB.getSQLValueString(get_TrxName(), "SELECT isbom FROM M_Product WHERE M_Product_ID = ?", M_Product_ID);
		if ("N".compareTo(bom) == 0)
		{
			throw new AdempiereUserError ("Attempt to create product line for Non Bill Of Materials");
		}
		int materials = DB.getSQLValue(get_TrxName(), "SELECT count(bl.PP_Product_BOMLine_ID) FROM PP_Product_BOMLine bl JOIN PP_Product_BOM b ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID WHERE b.M_Product_ID = ?", M_Product_ID );
		if (materials == 0)
		{
			throw new AdempiereUserError ("Attempt to create product line for Bill Of Materials with no BOM Products");
		}
	}
}
