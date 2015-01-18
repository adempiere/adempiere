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
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.ProductCost;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

/**
 *  Asset Acct Model
 *	@author	Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MAssetAcct extends X_A_Asset_Acct
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4779953750434068382L;

	/**
	 * DO NOT USE DIRECTLY
	 */
	public MAssetAcct (Properties ctx, int X_A_Asset_Acct_ID, String trxName)
	{
		super (ctx,X_A_Asset_Acct_ID, trxName);
		if (X_A_Asset_Acct_ID == 0)
		{
			setA_Salvage_Value(Env.ZERO);
		}
	}
	
	public MAssetAcct (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	/**		Static Cache: A_Asset_Acct_ID -> MAssetAcct					*/
	private static CCache<Integer,MAssetAcct> s_cache = new CCache<Integer,MAssetAcct>(Table_Name, 5);
	/**		Static Cache: Asset,PostingType,DateAcct -> MAssetAcct				*/
	private static CCache<MultiKey,MAssetAcct> s_cacheAsset = new CCache<MultiKey,MAssetAcct>(Table_Name+"_Asset", 5);
	
	/**
	 * Get Asset Accounting (from cache)
	 * @param ctx context
	 * @param A_Asset_Acct_ID asset accounting id
	 * @return asset accounting or null if not found
	 */
	public static MAssetAcct get (Properties ctx, int A_Asset_Acct_ID)
	{
		MAssetAcct acct = s_cache.get(A_Asset_Acct_ID);
		if (acct != null)
		{
			return acct;
		}
		acct = new MAssetAcct(ctx, A_Asset_Acct_ID, null);
		if (acct.get_ID() > 0)
		{
			addToCache(acct, null);
		}
		else
		{
			acct = null;
		}
		return acct;
	}
	
	/**
	 * Get asset accounting.
	 * @param ctx context
	 * @param A_Asset_ID asset
	 * @param postingType Posting type
	 * @param dateAcct check ValidFrom
	 * @return asset accounting for the given asset
	 */
	public static MAssetAcct forA_Asset_ID (Properties ctx, int A_Asset_ID, String postingType, Timestamp dateAcct, String trxName)
	{
		MultiKey key = new MultiKey(A_Asset_ID, postingType, dateAcct);
		MAssetAcct acct = null;
		if (trxName == null)
		{
			// do not use cache
			//acct = s_cacheAsset.get(key);
		}
		if (acct != null)
		{
			return acct;
		}
		//
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer(COLUMNNAME_A_Asset_ID+"=? AND "+COLUMNNAME_PostingType+"=?");
		params.add(A_Asset_ID);
		params.add(postingType);
		if (dateAcct != null)
		{
			whereClause.append(" AND " + COLUMNNAME_ValidFrom).append("<=?");
			params.add(dateAcct);
		}
		acct = new Query(ctx, Table_Name, whereClause.toString(), trxName)
								.setParameters(params)
								.setOrderBy(COLUMNNAME_ValidFrom+" DESC NULLS LAST")
								.first();
		if (trxName == null)
		{
			addToCache(acct, key);
		}
		return acct;
	}
	
	private static void addToCache(MAssetAcct acct, MultiKey keyAsset)
	{
		if (acct == null || acct.get_ID() <= 0)
		{
			return;
		}
		s_cache.put(acct.get_ID(), acct);
		if (keyAsset != null)
		{
			s_cacheAsset.put(keyAsset, acct);
		}
	}
	
	/**
	 * Create new asset accounting from asset group accounting
	 * @param asset asset
	 * @param assetgrpacct asset group accounting
	 */
	public MAssetAcct(MAsset asset, MAssetGroupAcct assetgrpacct)
	{
		this(assetgrpacct.getCtx(), 0, asset.get_TrxName());
		
		SetGetUtil.copyValues(this, assetgrpacct, null, null);
		setA_Asset_ID(asset.getA_Asset_ID());
		if (asset.getA_Depreciation_ID() > 0)
		{
			setA_Depreciation_ID(asset.getA_Depreciation_ID());
		}
		if (asset.getA_Depreciation_F_ID() > 0)
		{
			setA_Depreciation_F_ID(asset.getA_Depreciation_F_ID());
		}
		setA_Period_Start(1);
		setA_Period_End(asset.getUseLifeMonths());
		//~ setProcessing(false);
		dump();
	}
	
	/**
	 *
	 */
	public BigDecimal getA_Depreciation_Variable_Perc(boolean fiscal)
	{
		return fiscal ? getA_Depreciation_Variable_Perc_F() : getA_Depreciation_Variable_Perc();
	}
	
	
	public MAcctSchema getC_AcctSchema()
	{
		return MAcctSchema.get(getCtx(), getC_AcctSchema_ID());
	}
	
	public MAccount getP_Asset_Acct(int M_Product_ID)
	{
		MAcctSchema as = getC_AcctSchema();
		ProductCost pc = new ProductCost(getCtx(), M_Product_ID, 0, null);
		return pc.getAccount(ProductCost.ACCTTYPE_P_Asset, as);
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (getValidFrom() == null && newRecord)
		{
			setValidFrom(TimeUtil.getDay(1970, 01, 01)); // FIXME
		}
		return true;
	}
	
	
}	//	class MAssetAcct
