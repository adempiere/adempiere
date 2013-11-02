package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

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
	//private int p_M_Locator_ID=0;
	
	
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if ("Recreate".equals(name))
				recreate = "Y".equals(para[i].getParameter());
			else if ("ProductionQty".equals(name))
				newQty  = (BigDecimal) para[i].getParameter();
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
		String sql = "SELECT ABS(((cc.currentcostprice-(SELECT SUM(c.currentcostprice*bom.bomqty)"
            + " FROM m_cost c"
            + " INNER JOIN m_product_bom bom ON (c.m_product_id=bom.m_productbom_id)"
            + " WHERE bom.m_product_id = pp.m_product_id)"
            + " )/cc.currentcostprice))"
            + " FROM m_product pp"
            + " INNER JOIN m_cost cc on (cc.m_product_id=pp.m_product_id)"
            + " INNER JOIN m_costelement ce ON (cc.m_costelement_id=ce.m_costelement_id)"
            + " WHERE cc.currentcostprice > 0 AND pp.M_Product_ID = ?"
            + " AND ce.costingmethod='S'";
		
		BigDecimal costPercentageDiff = DB.getSQLValueBD(get_TrxName(), sql, M_Product_ID);
		
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
		
		if (!costsOK(m_production.getM_Product_ID()))
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
		int materials = DB.getSQLValue(get_TrxName(), "SELECT count(M_Product_BOM_ID) FROM M_Product_BOM WHERE M_Product_ID = ?", M_Product_ID);
		if (materials == 0)
		{
			throw new AdempiereUserError ("Attempt to create product line for Bill Of Materials with no BOM Products");
		}
	}
}
