/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is             Compiere  ERP & CRM Smart Business Solution
 * The Initial Developer of the Original Code is Jorg Janke  and ComPiere, Inc.
 * Portions created by Jorg Janke are Copyright (C) 1999-2003 Jorg Janke, parts
 * created by ComPiere are Copyright (C) ComPiere, Inc.;   All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 *  Asset Addition Model
 *
 *
 */
public class MAssetGroupAcct extends X_A_Asset_Group_Acct
{
	/**
	 * 	Default ConstructorX_A_Asset_Group_Acct
	 *	@param ctx context
	 *	@param M_InventoryLine_ID line
	 */
	public MAssetGroupAcct (Properties ctx, int X_A_Asset_Group_Acct_ID, String trxName)
	{
		super (ctx,X_A_Asset_Group_Acct_ID, trxName);
		if (X_A_Asset_Group_Acct_ID == 0)
		{
		//
		}
	}	//	MAssetAddition
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MAssetGroupAcct (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs,  trxName);
	}	//	MInventoryLine

		


}	//	MAssetAddition
