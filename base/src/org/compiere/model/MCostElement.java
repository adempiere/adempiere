/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_M_Product_Category_Acct;
import org.adempiere.core.domains.models.X_M_CostElement;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * 	Cost Element Model
 *  @author Jorg Janke
 *  @version $Id: MCostElement.java,v 1.2 2006/07/30 00:58:04 jjanke Exp $
 *  
 *  @author Teo Sarca, www.arhipac.ro
 *  		<li>BF [ 2664529 ] More then one Labor/Burden//Overhead is not allowed
 *  		<li>BF [ 2667470 ] MCostElement.getMaterialCostElement should check only material
 *  @author red1
 *  		<li>FR: [ 2214883 ] Remove SQL code and Replace for Query -- JUnit tested
 */
public class MCostElement extends X_M_CostElement
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3196322266971717530L;
	
	/**
	 * get or create Cost Element 
	 * @param po Persistence Object
	 * @return get Cost Element
	 */
	public static List<MCostElement> getDefaultElements (PO po)
	{
		//
		final String whereClause = "IsDefault=?";
		List<MCostElement> elements = new Query(po.getCtx(), Table_Name, whereClause, po.get_TrxName())
			.setParameters(true)
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.setOrderBy("AD_Org_ID DESC")
			.list();
		
		if (elements != null && elements.size() > 0)
			return elements;	
		
		MCostElement costElement = MCostElement.getByMaterialCostElementType(po);
		
		if (costElement != null)
		{	
			if (!costElement.isActive())
			{	
				costElement.setIsActive(true);
				costElement.saveEx();
			}		
		}
		else
		{
		//	Create New
			costElement = new MCostElement (po.getCtx(), 0, po.get_TrxName());
			costElement.setClientOrg(po.getAD_Client_ID(), 0);
			costElement.setName("Material");
			costElement.setIsDefault(true);
			costElement.setIsActive(true);
			costElement.setCostElementType(COSTELEMENTTYPE_Material);
			costElement.saveEx();
		}	
		
		elements = new ArrayList();
		elements.add(costElement);
		//
		return elements;
    }	//	getMaterialCostElement  
		
    /**
     * Get Cost Element    
     * @param po Persistence Object
     * @return MCostElement Cost Element
     */
	public static MCostElement getByMaterialCostElementType (PO po)
	{
		final String whereClause = "CostElementType=?";
		MCostElement retValue = new Query(po.getCtx(), Table_Name, whereClause, po.get_TrxName())
			.setParameters(COSTELEMENTTYPE_Material)
			.setClient_ID()
			.setOrderBy("M_CostElement_ID ,IsDefault, AD_Org_ID DESC")
			.first();
		//
		return retValue;
    }	//	getMaterialCostElement  
	
	/**
	 * Get All Cost Elements for current AD_Client_ID
	 * @param ctx context
	 * @param trxName transaction
	 * @return List with cost elements
	 */
	public static List<MCostElement> getCostElement(Properties ctx, String trxName)		
	{
		return new Query(ctx, Table_Name, null, trxName)
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setOrderBy(COLUMNNAME_Created)
					.list();
	}

    /**
     * Get Material Cost Element or create it
     * @param po
     * @return cost element entity
     */
    public static MCostElement getMaterialCostElement (PO po)
    {

        MCostElement costElement = MCostElement.getByMaterialCostElementType(po);
        if (costElement != null)
            return costElement;

        //	Create New
        costElement = new MCostElement (po.getCtx(), 0, po.get_TrxName());
        costElement.setClientOrg(po.getAD_Client_ID(), 0);
        String name = MRefList.getListName(po.getCtx(), COSTELEMENTTYPE_AD_Reference_ID, COSTELEMENTTYPE_Material);
        if (name == null || name.length() == 0)
            name = COSTELEMENTTYPE_Material;
        costElement.setName(name);
        costElement.setCostElementType(COSTELEMENTTYPE_Material);
        costElement.saveEx();
        return costElement;
    }

	/**
	 * 	Get Cost Element from Cache
	 *	@param ctx context
	 *	@param M_CostElement_ID id
	 *	@return Cost Element
	 */
	public static MCostElement get (Properties ctx, int M_CostElement_ID)
	{
		Integer key = Integer.valueOf(M_CostElement_ID);
		MCostElement retValue = (MCostElement) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MCostElement (ctx, M_CostElement_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get
	
	
	/**
	 * Get All Cost Elements for current AD_Client_ID
	 * @param ctx context
	 * @param trxName transaction
	 * @return array cost elements
	 */
	@Deprecated
	public static MCostElement[] getElements (Properties ctx, String trxName)
	{
		int AD_Org_ID = 0; // Org is always ZERO - see beforeSave
		
		final String whereClause = "AD_Client_ID = ? AND AD_Org_ID=?";
		List<MCostElement> list = new Query(ctx, Table_Name, whereClause, trxName)
					.setParameters(Env.getAD_Client_ID(ctx),AD_Org_ID)
					.list();
		MCostElement[] retValue = new MCostElement[list.size()];
		list.toArray(retValue);
		return retValue;	
	}

	/**	Cache						*/
	private static CCache<Integer,MCostElement>	s_cache	= new CCache<Integer,MCostElement>("M_CostElement", 20);
	
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MCostElement.class);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_CostElement_ID id
	 *	@param trxName trx
	 */
	public MCostElement (Properties ctx, int M_CostElement_ID, String trxName)
	{
		super (ctx, M_CostElement_ID, trxName);
		if (M_CostElement_ID == 0)
		{
		//	setName (null);
			setCostElementType (COSTELEMENTTYPE_Material);
			setIsCalculated (false);
		}
	}	//	MCostElement

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MCostElement (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MCostElement
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Check Unique Costing Method
		if (
			(  COSTELEMENTTYPE_Material.equals(getCostElementType())
//			|| COSTELEMENTTYPE_Resource.equals(getCostElementType())
//			|| COSTELEMENTTYPE_BurdenMOverhead.equals(getCostElementType())
//			|| COSTELEMENTTYPE_Overhead.equals(getCostElementType())
			|| COSTELEMENTTYPE_OutsideProcessing.equals(getCostElementType())
			)
			&& (newRecord || is_ValueChanged(COLUMNNAME_CostingMethod)))
		{
			String sql = "SELECT  COALESCE(MAX(M_CostElement_ID),0) FROM M_CostElement "
				+ "WHERE AD_Client_ID=? AND CostingMethod=? AND CostElementType=?";
			int id = DB.getSQLValue(get_TrxName(), sql, getAD_Client_ID(), getCostingMethod() , getCostElementType());
			if (id > 0 && id != get_ID())
			{
				log.saveError("AlreadyExists", Msg.getElement(getCtx(), "CostingMethod"));
				return false;
			}
		}

		//	Maintain Calculated
		/*
		if (COSTELEMENTTYPE_Material.equals(getCostElementType()))
		{
			String cm = getCostingMethod();
			if (cm == null || cm.length() == 0
				|| COSTINGMETHOD_StandardCosting.equals(cm))
				setIsCalculated(false);
			else
				setIsCalculated(true);
		}
		else
		{
			if (isCalculated())
				setIsCalculated(false);
			if (getCostingMethod() != null)
				setCostingMethod(null);
		}*/
		
		if (getAD_Org_ID() != 0)
			setAD_Org_ID(0);
		return true;
	}	//	beforeSave
	
	/**
	 * 	Before Delete
	 *	@return true if can be deleted
	 */
	protected boolean beforeDelete ()
	{
		String cm = getCostingMethod();
		if (cm == null
			|| !COSTELEMENTTYPE_Material.equals(getCostElementType()))
			return true;
		
		//	Costing Methods on AS level
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
		for (int i = 0; i < ass.length; i++)
		{
			if (ass[i].getCostingMethod().equals(getCostingMethod()))
			{
				log.saveError("CannotDeleteUsed", Msg.getElement(getCtx(), "C_AcctSchema_ID") 
					+ " - " + ass[i].getName());
				return false;
			}
		}
		
		//	Costing Methods on PC level
		int M_Product_Category_ID = 0;
		final String whereClause ="CostingMethod=?";
		MProductCategoryAcct retValue = new Query(getCtx(), I_M_Product_Category_Acct.Table_Name, whereClause, null)
		.setParameters(getCostingMethod())
		.setClient_ID()
		.first();
		if (retValue != null)
			M_Product_Category_ID = retValue.getM_Product_Category_ID();
		if (M_Product_Category_ID != 0)
		{
			log.saveError("CannotDeleteUsed", Msg.getElement(getCtx(), "M_Product_Category_ID") 
				+ " (ID=" + M_Product_Category_ID + ")");
			return false;
		}
		return true;
	}	//	beforeDelete
	
	/**
	 * 	Is this a Costing Method
	 *	@return true if not Material cost or no costing method.
	 */
	public boolean isCostingMethod()
	{
		return COSTELEMENTTYPE_Material.equals(getCostElementType())
			&& getCostingMethod() != null;
	}	//	isCostingMethod
	
	/**
	 * 	Is Avg Invoice Costing Method
	 *	@return true if AverageInvoice
	 *	@deprecated
	 */
	public boolean isAverageInvoice()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_AverageInvoice)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isAverageInvoice
	
	/**
	 * 	Is Avg PO Costing Method
	 *	@return true if AveragePO
	 *	@deprecated
	 */
	public boolean isAveragePO()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_AveragePO)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isAveragePO
	/**
	 * 	Is FiFo Costing Method
	 *	@return true if Fifo
	 *	@deprecated
	 */
	public boolean isFifo()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_Fifo)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isFifo
	/**
	 * 	Is Last Invoice Costing Method
	 *	@return true if LastInvoice
	 *	@deprecated
	 */
	public boolean isLastInvoice()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_LastInvoice)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isLastInvoice
	/**
	 * 	Is Last PO Costing Method
	 *	@return true if LastPOPrice
	 *	@deprecated
	 */
	public boolean isLastPOPrice()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_LastPOPrice)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isLastPOPrice
	/**
	 * 	Is LiFo Costing Method
	 *	@return true if Lifo
	 *	@deprecated
	 */
	public boolean isLifo()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_Lifo)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isLiFo
	/**
	 * 	Is Std Costing Method
	 *	@return true if StandardCosting
	 *	@deprecated
	 */
	public boolean isStandardCosting()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_StandardCosting)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isStandardCosting
	/**
	 * 	Is User Costing Method
	 *	@return true if User Defined
	 *	@deprecated
	 */
	public boolean isUserDefined()
	{
		String cm = getCostingMethod();
		return cm != null 
			&& cm.equals(COSTINGMETHOD_UserDefined)
			&& COSTELEMENTTYPE_Material.equals(getCostElementType());
	}	//	isAveragePO
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MCostElement[");
		sb.append (get_ID ())
			.append ("-").append (getName())
			.append(",Type=").append(getCostElementType())
			.append(",Method=").append(getCostingMethod())
			.append ("]");
		return sb.toString ();
	} //	toString
	
}	//	MCostElement
