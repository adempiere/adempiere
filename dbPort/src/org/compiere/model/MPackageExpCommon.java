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
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.util.*;

/**
 *	Menu Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MMenu.java,v 1.5 2005/05/14 05:32:16 jjanke Exp $
 */
public class MPackageExpCommon extends X_AD_Package_Exp_Common
{	
	
	/**
	 * 	MPackageExpDetail
	 *	@param ctx
	 *	@param int
	 */
	public MPackageExpCommon (Properties ctx, int AD_Package_Exp_Common_ID, String trxName)
	{
		super(ctx, AD_Package_Exp_Common_ID, trxName);		
		
	}	//	MPackageExp

	/**
	 * 	MPackageExp
	 *	@param ctx
	 *	@param rs
	 */
	public MPackageExpCommon (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);		
		
	}	//	MPackageExp
	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		
		X_AD_Package_Exp_Common PackCommon =new X_AD_Package_Exp_Common(Env.getCtx(), getAD_Package_Exp_Common_ID(), null);
		String sql = "SELECT max(Line) FROM AD_Package_Exp_Common";
		int lineNo = DB.getSQLValue(null, sql);		
		
		if(PackCommon.getLine()==0){					
			PackCommon.setLine(lineNo+10);
			PackCommon.save();}			
		
		return true;
	}	//	afterSave
	
}	//	MMenu
