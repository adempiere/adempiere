/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Carlos Parada cparada@erpya.com                                       *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MStandardProjectLine extends X_C_StandardProjectLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MStandardProjectLine(Properties ctx, int C_StandardProjectLine_ID, String trxName) {
		super(ctx, C_StandardProjectLine_ID, trxName);
	}
	public MStandardProjectLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * get nodes from parent
	 * @param type
	 * @param Parent_ID
	 * @return
	 */
	public static List<MStandardProjectLine> getNodes(MProjectType type, int Parent_ID){
		
		List<MStandardProjectLine> nodes = new Query(type.getCtx(), MStandardProjectLine.Table_Name, "C_ProjectType_ID = ? AND (Parent_ID = ? "+ (Parent_ID == 0 ? " OR Parent_ID IS NULL " : "") +")", type.get_TrxName())
												.setParameters(type.getC_ProjectType_ID(),Parent_ID)
												.list();
		List<MStandardProjectLine> childs = new ArrayList<MStandardProjectLine>();
		
		nodes.stream()
				.forEach(ProjectLine -> {
					childs.addAll(getNodes(type, ProjectLine.getC_StandardProjectLine_ID()));
					
				});
		nodes.addAll(childs);
		return nodes;
	}
	
	/**
	 * has children?
	 * @return
	 */
	public boolean hasChilds() {
		return new Query(getCtx(), MStandardProjectLine.Table_Name, "Parent_ID = ?", get_TrxName())
					.setParameters(getC_StandardProjectLine_ID())
					.match();
	}
}
