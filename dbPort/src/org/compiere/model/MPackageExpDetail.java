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
public class MPackageExpDetail extends X_AD_Package_Exp_Detail
{	
	
	/**
	 * 	MPackageExpDetail
	 *	@param ctx
	 *	@param int
	 */
	public MPackageExpDetail (Properties ctx, int AD_Package_Exp_ID, String trxName)
	{
		super(ctx, AD_Package_Exp_ID, trxName);		
		
	}	//	MPackageExp

	/**
	 * 	MPackageExp
	 *	@param ctx
	 *	@param rs
	 */
	public MPackageExpDetail (Properties ctx, ResultSet rs, String trxName)
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
		
		X_AD_Package_Exp_Detail PackDetail =new X_AD_Package_Exp_Detail(Env.getCtx(), getAD_Package_Exp_Detail_ID(), null);
		String sql = "SELECT max(Line) FROM AD_Package_Exp_Detail WHERE AD_Package_Exp_ID = ?";
		int lineNo = DB.getSQLValue(null, sql,getAD_Package_Exp_ID());		
		
		if(PackDetail.getLine()==0){					
				PackDetail.setLine(lineNo+10);
				PackDetail.save();}			
		
		return true;
	}	//	afterSave
	
}	//	MMenu
