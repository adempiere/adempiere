/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Carlos Ruiz - globalqss                               *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz - globalqss                                           *
*                                                                     *
* Sponsors:                                                           *
* - Company (http://www.globalqss.com)                                *
***********************************************************************/

package org.adempiere.process;

import java.util.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWorkflow;

/**
 * 	Generate ASP fields for a window
 *	
 *  @author Carlos Ruiz
 */
public class ASPGenerateFields extends SvrProcess
{
	private String  p_ASP_Status;
	private int p_ASP_Level_ID;
	
	private int noFields = 0;
	private int p_AD_Tab_ID;
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("ASP_Status"))
				p_ASP_Status = (String) para[i].getParameter();
			else if (name.equals("ASP_Level_ID"))
				p_ASP_Level_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_Tab_ID"))
				p_AD_Tab_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("ASP_Status=" + p_ASP_Status 
				+ ", ASP_Level_ID=" + p_ASP_Level_ID
				+ ", AD_Tab_ID=" + p_AD_Tab_ID
		);

		// tabs
		MTab tab = new MTab(getCtx(), p_AD_Tab_ID, get_TrxName());
		// fields
		MField[] fields = tab.getFields(true, get_TrxName());
		for (int  ifi = 0; ifi < fields.length; ifi++) {
			if (DB.getSQLValue(
					get_TrxName(),
					"SELECT COUNT(*) FROM ASP_Field WHERE ASP_Level_ID = ? AND AD_Field_ID = ?",
					p_ASP_Level_ID, fields[ifi].getAD_Field_ID()) < 1) {
				X_ASP_Field aspField = new X_ASP_Field(getCtx(), 0, get_TrxName());
				aspField.setASP_Level_ID(p_ASP_Level_ID);
				aspField.setAD_Tab_ID(fields[ifi].getAD_Tab_ID());
				aspField.setAD_Field_ID(fields[ifi].getAD_Field_ID());
				aspField.setASP_Status(p_ASP_Status);
				if (aspField.save())
					noFields++;
			}
		}

		if (noFields > 0)
			addLog("Field " + noFields);

		return "@OK@";
	}	//	doIt

}	//	ASPGenerateFields