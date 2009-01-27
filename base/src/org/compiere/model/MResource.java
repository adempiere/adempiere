/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CCache;
import org.compiere.util.DB;


/**
 *	Resource Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MResource.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 				<li>FR [ 2051056 ] MResource[Type] should be cached
 * 				<li>BF [ 2227901 ] MRP (Calculate Material Plan) fails if resource is empty
 */
public class MResource extends X_S_Resource
{
	private static final long serialVersionUID = 1L;
	
	/** Cache */
	private static CCache<Integer, MResource> s_cache = new CCache<Integer, MResource>(Table_Name, 20);
	
	/**
	 * Get from Cache
	 * @param ctx
	 * @param S_Resource_ID
	 * @return MResource
	 */
	public static MResource get(Properties ctx, int S_Resource_ID)
	{
		if (S_Resource_ID <= 0)
			return null;
		MResource r = s_cache.get(S_Resource_ID);
		if (r == null) {
			r = new MResource(ctx, S_Resource_ID, null);
			if (r.get_ID() == S_Resource_ID) {
				s_cache.put(S_Resource_ID, r);
			}
		}
		return r;
	}

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param S_Resource_ID id
	 */
	public MResource (Properties ctx, int S_Resource_ID, String trxName)
	{
		super (ctx, S_Resource_ID, trxName);
	}	//	MResource

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MResource (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MResource
	
	
	/** Cached Resource Type	*/
	private MResourceType	m_resourceType = null;
	/** Cached Product			*/
	private MProduct		m_product = null;
	
	
	/**
	 * 	Get cached Resource Type
	 *	@return Resource Type
	 */
	public MResourceType getResourceType()
	{
		// Use cache if we are outside transaction:
		if (get_TrxName() == null && getS_ResourceType_ID() > 0)
			return MResourceType.get(getCtx(), getS_ResourceType_ID());
		//
		if (m_resourceType == null && getS_ResourceType_ID() != 0) {
			m_resourceType = new MResourceType (getCtx(), getS_ResourceType_ID(), get_TrxName());
		}
		return m_resourceType;
	}	//	getResourceType
	
	/**
	 * 	Get Product
	 *	@return product
	 */
	public MProduct getProduct()
	{
		if (m_product == null)
		{
			MProduct[] products = MProduct.get(getCtx(), "S_Resource_ID=" + getS_Resource_ID(), get_TrxName());
			if (products.length > 0)
				m_product = products[0];
		}
		return m_product;
	}	//	getProduct
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if it can be saved
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (newRecord)
		{
			if (getValue() == null || getValue().length() == 0)
				setValue(getName());
			m_product = new MProduct(this, getResourceType());
			m_product.saveEx(get_TrxName());
		}
		//
		// Validate Manufacturing Resource
		if (isManufacturingResource()
				&& MANUFACTURINGRESOURCETYPE_Plant.equals(getManufacturingResourceType())
				&& getPlanningHorizon() <= 0)
		{
			throw new AdempiereException("@"+COLUMNNAME_PlanningHorizon+"@ <= @0@ !");
		}
		return true;
	}	//	beforeSave

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
			
		MProduct prod = getProduct();
		if (prod.setResource(this))
			prod.saveEx(get_TrxName());
		
		return success;
	}	//	afterSave
	
	/**
	 *  Get Rate for this Resource
	 *  @param CostElementType Cost Element Type (Labor and Overhead.)
	 *  @param S_Resource_ID Resource
	 *  @param AD_Org_ID Organization
	 *  @return Rate for Resource
	 */
	public double getResouceRate(int C_AcctSchema_ID,int  M_CostType_ID, String CostElementType , int AD_Org_ID)
	{
				
		final String sql = "SELECT SUM(c."+MCost.COLUMNNAME_CurrentCostPrice+")"
							+" FROM M_Cost c, M_CostElement ce, M_Product p"
							+" WHERE c.AD_Client_ID=? AND c.AD_Org_ID=?"
							+" AND c."+MCost.COLUMNNAME_C_AcctSchema_ID+"=?"
							+" AND c."+MCost.COLUMNNAME_M_CostType_ID+"=?"
							// Cost Element Type
							+" AND ce."+MCostElement.COLUMNNAME_M_CostElement_ID+"=c."+MCost.COLUMNNAME_M_CostElement_ID
							+" AND ce."+MCostElement.COLUMNNAME_CostElementType+"=?"
							// Product / Resource
							+" AND p."+MProduct.COLUMNNAME_M_Product_ID+"=c."+MCost.COLUMNNAME_M_Product_ID
							+" AND p."+MProduct.COLUMNNAME_S_Resource_ID+"=?"
		;
		BigDecimal rate = DB.getSQLValueBDEx(get_TrxName(), sql, getAD_Client_ID(), AD_Org_ID,
											C_AcctSchema_ID, M_CostType_ID,
											CostElementType, getS_Resource_ID());
		return (rate != null ? rate.doubleValue() : 0);
	}     
	
	public String toString()
	{
	      StringBuffer sb = new StringBuffer ("MResource[")
	        .append(get_ID())
	        .append(", Value=").append(getValue())
	        .append(", Name=").append(getName())
	        .append("]");
	      return sb.toString();
	}
	
}	//	MResource
