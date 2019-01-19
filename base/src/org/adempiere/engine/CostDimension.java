package org.adempiere.engine;

import java.util.ArrayList;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_Client;
import org.compiere.model.I_AD_Org;
import org.compiere.model.I_C_AcctSchema;
import org.compiere.model.I_M_AttributeInstance;
import org.compiere.model.I_M_CostElement;
import org.compiere.model.I_M_CostType;
import org.compiere.model.I_M_Product;
import org.compiere.model.I_M_Warehouse;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostType;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MTable;
import org.compiere.model.MTransaction;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Util;


/**
 * Immutable Cost Dimension
 * @author Teo Sarca
 */
public class CostDimension
{
	public static final int ANY = -10;

	private int AD_Client_ID;
	private int AD_Org_ID;
	private int M_Warehouse_ID;
	private int M_Product_ID;
	private int S_Resource_ID;
	private int M_AttributeSetInstance_ID;
	private int M_CostType_ID;
	private int C_AcctSchema_ID;
	private int M_CostElement_ID;

	public CostDimension(MProduct product, MAcctSchema as, int M_CostType_ID, int AD_Org_ID, int M_Warehouse_ID, int M_ASI_ID, int M_CostElement_ID)
	{
		this.AD_Client_ID = as.getAD_Client_ID();
		this.AD_Org_ID = AD_Org_ID;
		this.M_Warehouse_ID = M_Warehouse_ID;
		this.M_Product_ID = product != null ? product.get_ID() : ANY;
		this.M_AttributeSetInstance_ID = M_ASI_ID;
		this.M_CostType_ID = M_CostType_ID;
		this.C_AcctSchema_ID = as.get_ID();
		this.M_CostElement_ID = M_CostElement_ID;
		updateForProduct(product, as);
	}

	public CostDimension(int client_ID, int org_ID, int warehouse_ID,  int product_ID, int attributeSetInstance_ID, int costType_ID, int acctSchema_ID, int costElement_ID)
	{
		this.AD_Client_ID = client_ID;
		this.AD_Org_ID = org_ID;
		this.M_Warehouse_ID  = warehouse_ID;
		this.M_Product_ID = product_ID;
		this.M_AttributeSetInstance_ID = attributeSetInstance_ID;
		this.M_CostType_ID = costType_ID;
		this.C_AcctSchema_ID = acctSchema_ID;
		this.M_CostElement_ID = costElement_ID;
		//
		updateForProduct(null, null);
	}

	/**
	 * Copy Constructor
	 *
	 * @param costDimension a <code>CostDimension</code> object
	 */
	public CostDimension(CostDimension costDimension) 
	{
		this.AD_Client_ID = costDimension.AD_Client_ID;
		this.AD_Org_ID = costDimension.AD_Org_ID;
		this.M_Warehouse_ID = costDimension.M_Warehouse_ID;
		this.M_Product_ID = costDimension.M_Product_ID;
		this.M_AttributeSetInstance_ID = costDimension.M_AttributeSetInstance_ID;
		this.M_CostType_ID = costDimension.M_CostType_ID;
		this.C_AcctSchema_ID = costDimension.C_AcctSchema_ID;
		this.M_CostElement_ID = costDimension.M_CostElement_ID;
	}

	private Properties getCtx()
	{
		return Env.getCtx(); // TODO
	}

	private void updateForProduct(MProduct product, MAcctSchema as)
	{
		if (product == null)
		{
			product = MProduct.get(getCtx(), this.M_Product_ID);
		}
		if (product == null)
		{
			// incomplete specified dimension [SKIP]
			return;
		}
		if (as == null)
		{
			as = MAcctSchema.get(getCtx(), this.C_AcctSchema_ID);
		}
		String CostingLevel = product.getCostingLevel(as, AD_Org_ID);
		//
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
		{
			AD_Org_ID = 0;
			M_AttributeSetInstance_ID = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
		{
			M_AttributeSetInstance_ID = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Warehouse.equals(CostingLevel))
		{
			M_AttributeSetInstance_ID = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
		{
			AD_Org_ID = 0;
		}
		//
		this.S_Resource_ID = product.getS_Resource_ID();
	}

	/**
	 * @return the aD_Client_ID
	 */
	public int getAD_Client_ID()
	{
		return AD_Client_ID;
	}

	/**
	 * @return the aD_Org_ID
	 */
	public int getAD_Org_ID()
	{
		return AD_Org_ID;
	}

	/**
	 * @return the aD_Org_ID
	 */
	public int getM_Warehouse_ID()
	{
		return M_Warehouse_ID;
	}

	
	/**
	 * @return the m_Product_ID
	 */
	public int getM_Product_ID()
	{
		return M_Product_ID;
	}

	public int getS_Resource_ID()
	{
		return S_Resource_ID;
	}

	public CostDimension setM_Product_ID(int M_Product_ID)
	{
		CostDimension d = new CostDimension(this);
		d.M_Product_ID = M_Product_ID;
		d.updateForProduct(null, null);
		//
		return d;
	}

	public CostDimension setM_Product(MProduct product)
	{
		CostDimension d = new CostDimension(this);
		d.M_Product_ID = product.get_ID();
		d.updateForProduct(product, null);
		return d;
	}

	/**
	 * @return the M_AttributeSetInstance_ID
	 */
	public int getM_AttributeSetInstance_ID()
	{
		return M_AttributeSetInstance_ID;
	}

	/**
	 * @return the m_CostType_ID
	 */
	public int getM_CostType_ID()
	{
		return M_CostType_ID;
	}

	/**
	 * @return the c_AcctSchema_ID
	 */
	public int getC_AcctSchema_ID()
	{
		return C_AcctSchema_ID;
	}

	/**
	 * @return the m_CostElement_ID
	 */
	public int getM_CostElement_ID()
	{
		return M_CostElement_ID;
	}


	public Query toQuery(Class<?> clazz, String trxName)
	{
		return toQuery(clazz, null, null, trxName);
	}
	public Query toQuery(Class<?> clazz, String whereClause, Object[] params, String trxName)
	{
		String tableName;
		// Get Table_Name by Class
		// TODO: refactor
		try
		{
			tableName = (String)clazz.getField("Table_Name").get(null);
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		//
		Properties ctx = Env.getCtx();
		MTable table = MTable.get(ctx, tableName);
		ArrayList<Object> finalParams = new ArrayList<Object>();
		StringBuffer finalWhereClause = new StringBuffer();

		finalWhereClause.append(I_AD_Client.COLUMNNAME_AD_Client_ID+ "=? ");
		finalParams.add(this.AD_Client_ID);
		finalWhereClause.append(" AND "+I_C_AcctSchema.COLUMNNAME_C_AcctSchema_ID+"=?");
		finalParams.add(this.C_AcctSchema_ID);
		finalWhereClause.append(" AND "+I_AD_Org.COLUMNNAME_AD_Org_ID+"=?");
		finalParams.add(this.AD_Org_ID);
        finalWhereClause.append(" AND (").append(I_M_Warehouse.COLUMNNAME_M_Warehouse_ID).append(" IS NULL OR ").append(I_M_Warehouse.COLUMNNAME_M_Warehouse_ID+"=? )");
		finalParams.add(this.M_Warehouse_ID);
		finalWhereClause.append(" AND "+I_M_Product.COLUMNNAME_M_Product_ID+"=?");
		finalParams.add(this.M_Product_ID);
		finalWhereClause.append(" AND " + I_M_AttributeInstance.COLUMNNAME_M_AttributeSetInstance_ID + "=?");
		finalParams.add(this.M_AttributeSetInstance_ID);
		if (this.M_CostElement_ID != ANY)
		{
			finalWhereClause.append(" AND "+I_M_CostElement.COLUMNNAME_M_CostElement_ID+"=?");
			finalParams.add(this.M_CostElement_ID);
		}
		if (this.M_CostType_ID != ANY && table.getColumn(I_M_CostType.COLUMNNAME_M_CostType_ID) != null)
		{
			finalWhereClause.append(" AND "+I_M_CostType.COLUMNNAME_M_CostType_ID+"=?");
			finalParams.add(this.M_CostType_ID);
		}
		if (!Util.isEmpty(whereClause, true))
		{
			finalWhereClause.append(" AND (").append(whereClause).append(")");
			if (params != null && params.length > 0)
			{
				for (Object p : params)
				{
					finalParams.add(p);
				}
			}
		}

		return new Query(ctx, tableName, finalWhereClause.toString(), trxName)
		.setParameters(finalParams);
	}

	@Override
	protected Object clone()
	{
		return new CostDimension(this);
	}

	@Override
	public String toString()
	{
		final String TAB = ";";

		String retValue = "";

		retValue = "CostDimension{"
			+ "AD_Client_ID = " + this.AD_Client_ID + TAB
			+ "AD_Org_ID = " + this.AD_Org_ID + TAB
			+ "M_Warehouse_ID = " + this.M_Warehouse_ID + TAB
			+ "M_Product_ID = " + this.M_Product_ID + TAB
			+ "M_AttributeSetInstance_ID = " + this.M_AttributeSetInstance_ID + TAB
			+ "M_CostType_ID = " + this.M_CostType_ID + TAB
			+ "C_AcctSchema_ID = " + this.C_AcctSchema_ID + TAB
			+ "M_CostElement_ID = " + this.M_CostElement_ID + TAB
			+ "}";

		return retValue;
	}

	public static boolean isSameCostDimension(MAcctSchema as, MTransaction trxFrom, MTransaction trxTo)
	{
		if (trxFrom.getM_Product_ID() != trxTo.getM_Product_ID())
		{
			throw new AdempiereException("Same product is needed - "+trxFrom+", "+trxTo);
		}
		MProduct product = MProduct.get(trxFrom.getCtx(), trxFrom.getM_Product_ID());
		String CostingLevel = product.getCostingLevel(as, trxFrom.getAD_Org_ID());
		MLocator locatorFrom = MLocator.get(trxFrom.getCtx(), trxFrom.getM_Locator_ID());
		MLocator locatorTo = MLocator.get(trxTo.getCtx(), trxTo.getM_Locator_ID());
		int Org_ID = locatorFrom.getAD_Org_ID();
		int Org_ID_To = locatorTo.getAD_Org_ID();
		int ASI_ID = trxFrom.getM_AttributeSetInstance_ID();
		int ASI_ID_To = trxTo.getM_AttributeSetInstance_ID();
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
		{
			Org_ID = 0;
			Org_ID_To = 0;
			ASI_ID = 0;
			ASI_ID_To = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
		{
			ASI_ID = 0;
			ASI_ID_To = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Warehouse.equals(CostingLevel))
		{
			ASI_ID = 0;
			ASI_ID_To = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
		{
			Org_ID = 0;
			Org_ID_To = 0;
		}
		//
		return Org_ID == Org_ID_To && ASI_ID == ASI_ID_To;
	}
	public static boolean isSameCostDimension(MAcctSchema as, IDocumentLine model)
	{
		MProduct product = MProduct.get(model.getCtx(), model.getM_Product_ID());
		MLocator locator = MLocator.get(model.getCtx(), model.getM_LocatorTo_ID());
		String CostingLevel = product.getCostingLevel(as, model.getAD_Org_ID());
		int Org_ID = model.getAD_Org_ID();
		int Org_ID_To = locator.getAD_Org_ID();
		int ASI_ID = model.getM_AttributeSetInstance_ID();
		int ASI_ID_To = model.getM_AttributeSetInstance_ID();
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
		{
			Org_ID = 0;
			Org_ID_To = 0;
			ASI_ID = 0;
			ASI_ID_To = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
		{
			ASI_ID = 0;
			ASI_ID_To = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Warehouse.equals(CostingLevel))
		{
			ASI_ID = 0;
			ASI_ID_To = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
		{
			Org_ID = 0;
			Org_ID_To = 0;
		}
		//
		return Org_ID == Org_ID_To && ASI_ID == ASI_ID_To;
	}
	
	public String getCostingMethod()
	{
		MCostType ct = new MCostType(Env.getCtx(), getM_CostType_ID(), null);
		return ct.getCostingMethod();
	}
}
