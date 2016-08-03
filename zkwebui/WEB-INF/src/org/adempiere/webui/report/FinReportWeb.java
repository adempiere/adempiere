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
 * Contributor(s): Armen Rizal (goodwill.co.id) 							  *
 *****************************************************************************/
package org.adempiere.webui.report;

import org.adempiere.webui.component.SMJReportViewer;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.compiere.print.MPrintFormat;
import org.compiere.report.FinReport;
import org.compiere.util.Ini;

/**
 * Financial Report Engine
 * 
 *  @author armenrz (Goodwill.co.id)
 */
public class FinReportWeb extends FinReport 
{
	
	/**************************************************************************
	 *  Perform process.
	 *  @return Message to be translated
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		// Call the normal FinReport to fill the T_Report table
		String finReportMsg = super.doIt();
		
		//	Create Report
		if (!Ini.isClient())
		{
			MPrintFormat pf = getPrintFormat();
			Window viewer = new SMJReportViewer(getAD_PInstance_ID(), get_TrxName(), getRecord_ID(), p_C_Period_ID, pf.getAD_PrintFont_ID(), m_columns);
			viewer.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
			viewer.setAttribute(Window.INSERT_POSITION_KEY, Window.INSERT_NEXT);
			SessionManager.getAppDesktop().showWindow(viewer);
		}
		
		return finReportMsg;
	}	//	doIt

}	//	FinReportWeb
