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

import org.adempiere.core.domains.models.X_C_DocBaseType;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * 
 * @author Carlos Parada
 * Model Class for Document Base Type
 *
 */
public class MDocBaseType extends X_C_DocBaseType{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Cache						*/
	private static CCache<String,MDocBaseType>	acct_cache	= new CCache<String,MDocBaseType>(MDocBaseType.Table_Name, 20);
	private static CCache<Integer,MDocBaseType>	doc_cache	= new CCache<Integer,MDocBaseType>(MDocBaseType.Table_Name, 20);
	private static CCache<String,MDocBaseType>	cache	= new CCache<String,MDocBaseType>(MDocBaseType.Table_Name, 20);
	
	/**
	 * Constructor
	 * @param ctx
	 * @param C_DocBaseType_ID
	 * @param trxName
	 */
	public MDocBaseType(Properties ctx, int C_DocBaseType_ID, String trxName) {
		super(ctx, C_DocBaseType_ID, trxName);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MDocBaseType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get Document Base Type by Table and Document Type
	 * @param C_DocType_ID
	 * @param AD_Table_ID
	 * @return
	 */
	public static MDocBaseType get(int C_DocType_ID, int AD_Table_ID) {
		String key = C_DocType_ID + "_" + AD_Table_ID;
		MDocBaseType docBaseType = acct_cache.get(key);
		List<Object> parameters = new ArrayList<>();
		
		if (docBaseType == null) {
			String whereClause = "AD_Table_ID = ? ";
			parameters.add(AD_Table_ID);
			
			if (C_DocType_ID > 0) {
				whereClause += "AND EXISTS (SELECT 1 "
										+ "FROM C_DocType dt "
										+ "WHERE ((C_DocBaseType.C_DocBaseType_ID = dt.C_DocBaseType_ID) "
												+ "OR (dt.C_DocBaseType_ID IS NULL AND C_DocBaseType.DocBaseType = dt.DocBaseType))"
										+ "AND dt.C_DocType_ID = ?)";
				parameters.add(C_DocType_ID);
			}
			docBaseType = new Query(Env.getCtx(), MDocBaseType.Table_Name, whereClause, null)
							.setParameters(parameters)
							.setOrderBy("IsDefault DESC")
							.first();
			acct_cache.put(key, docBaseType);
		}
		return docBaseType;
	}
	
	/**
	 * Get Document Base Type by Document Base Type Identifier
	 * @param C_DocBaseType_ID
	 * @return
	 */
	public static MDocBaseType get(int C_DocBaseType_ID) {
		return get(C_DocBaseType_ID, null);
	}
	
	/**
	 * Get Document Base Type by Document Base Type Identifier
	 * @param C_DocBaseType_ID
	 * @param trxName
	 * @return
	 */
	public static MDocBaseType get(int C_DocBaseType_ID, String trxName) {
		MDocBaseType docBaseType = doc_cache.get(C_DocBaseType_ID);
		
		if (docBaseType == null) {
			String whereClause = "C_DocBaseType_ID = ? ";
			docBaseType = new Query(Env.getCtx(), MDocBaseType.Table_Name, whereClause, trxName)
							.setParameters(C_DocBaseType_ID)
							.first();
			doc_cache.put(C_DocBaseType_ID, docBaseType);
		}
		return docBaseType;
	}
	
	/**
	 * Get Document Base by Document Base Type Static 
	 * @param DocBaseType
	 * @return
	 */
	public static MDocBaseType get(String DocBaseType) {
		return get(DocBaseType, null);
	}
	
	/**
	 * Get Document Base by Document Base Type Static 
	 * @param DocBaseType
	 * @param trxName
	 * @return
	 */
	public static MDocBaseType get(String DocBaseType, String trxName) {
		MDocBaseType docBaseType = cache.get(DocBaseType);
		
		if (docBaseType == null) {
			String whereClause = "DocBaseType = ? ";
			docBaseType = new Query(Env.getCtx(), MDocBaseType.Table_Name, whereClause, trxName)
							.setParameters(DocBaseType)
							.setOrderBy("IsDefault DESC")
							.first();
			cache.put(DocBaseType, docBaseType);
		}
		return docBaseType;
	}
	
}//MDocBaseType
