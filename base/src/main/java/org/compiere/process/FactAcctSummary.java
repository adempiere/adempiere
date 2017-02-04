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

package org.compiere.process;

import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MReportCube;
import org.compiere.model.Query;

/*
 * Populate Fact_Acct_Summary table with pre-calculated totals of 
 * accounting facts, grouped by the dimensions selected in active report cubes.
 * @author Paul Bowden
 */
public class FactAcctSummary extends SvrProcess {
	

	private boolean p_reset = false;
	private int p_Cube_ID = 0;
	private boolean p_force = false;

	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] params = getParameter();
		for (ProcessInfoParameter p : params)
		{
			if ( p.getParameterName().equals("Reset") )
				p_reset = p.getParameterAsBoolean();
			else if ( p.getParameterName().equals("PA_ReportCube_ID"))
				p_Cube_ID = p.getParameterAsInt();
			else if ( p.getParameterName().equals("Force"))
				p_force = p.getParameterAsBoolean();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + p.getParameterName());
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		String where = "";
		if ( p_Cube_ID > 0)
			where = "PA_ReportCube_ID = " + p_Cube_ID;
		
		List<MReportCube> cubes = new Query(getCtx(), MReportCube.Table_Name, where, get_TrxName())
		.setOnlyActiveRecords(true).setClient_ID()
		.list();
		
		for ( MReportCube cube : cubes )
		{
			addLog( cube.update( p_reset, p_force ) );
		}  // for each cube
		
		
		return "@OK@";
	}




}
