/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *																			  *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com					  *
 * Contributor(s): Low Heng Sin hengsin@avantz.com							  *
 *                 Victor Perez  victor.perez@e-evoluton.com				  *
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.model.I_AD_Browse_Access;
import org.adempiere.model.X_AD_Browse_Access;
import org.adempiere.pipo.PackOut;
import org.compiere.model.I_AD_Column_Access;
import org.compiere.model.I_AD_Document_Action_Access;
import org.compiere.model.I_AD_Form_Access;
import org.compiere.model.I_AD_Process_Access;
import org.compiere.model.I_AD_Record_Access;
import org.compiere.model.I_AD_Role;
import org.compiere.model.I_AD_Role_Included;
import org.compiere.model.I_AD_Role_OrgAccess;
import org.compiere.model.I_AD_Table_Access;
import org.compiere.model.I_AD_Task_Access;
import org.compiere.model.I_AD_User_Roles;
import org.compiere.model.I_AD_Window_Access;
import org.compiere.model.I_AD_Workflow_Access;
import org.compiere.model.MColumnAccess;
import org.compiere.model.MFormAccess;
import org.compiere.model.MProcessAccess;
import org.compiere.model.MRecordAccess;
import org.compiere.model.MRole;
import org.compiere.model.MRoleOrgAccess;
import org.compiere.model.MTableAccess;
import org.compiere.model.MUserRoles;
import org.compiere.model.MWindowAccess;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Document_Action_Access;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.model.X_AD_Role_Included;
import org.compiere.model.X_AD_Task_Access;
import org.compiere.util.Env;
import org.compiere.wf.MWorkflowAccess;
import org.spin.model.I_AD_Dashboard_Access;
import org.spin.model.X_AD_Dashboard_Access;
import org.xml.sax.SAXException;

/**
 * Add support to generic PO Handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class RoleElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		int roleId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Role_ID);
		packOut.createGenericPO(document, I_AD_Role.Table_ID, roleId, true, null);
		//	Org Access
		List<MRoleOrgAccess> orgAccessList = new Query(ctx, I_AD_Role_OrgAccess.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<MRoleOrgAccess>list();
		for(MRoleOrgAccess access : orgAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	User Access
		List<MUserRoles> userAccessList = new Query(ctx, I_AD_User_Roles.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<MUserRoles>list();
		for(MUserRoles access : userAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Process Access
		List<MProcessAccess> processAccessList = new Query(ctx, I_AD_Process_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<MProcessAccess>list();
		for(MProcessAccess access : processAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Window Access
		List<MWindowAccess> windowAccessList = new Query(ctx, I_AD_Window_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<MWindowAccess>list();
		for(MWindowAccess access : windowAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Form Access
		List<MFormAccess> formAccessList = new Query(ctx, I_AD_Form_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<MFormAccess>list();
		for(MFormAccess access : formAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Browse Access
		List<X_AD_Browse_Access> browseAccessList = new Query(ctx, I_AD_Browse_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<X_AD_Browse_Access>list();
		for(X_AD_Browse_Access access : browseAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Task Access
		List<X_AD_Task_Access> taskAccessList = new Query(ctx, I_AD_Task_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<X_AD_Task_Access>list();
		for(X_AD_Task_Access access : taskAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Dashboard Access
		List<X_AD_Dashboard_Access> dashboardAccessList = new Query(ctx, I_AD_Dashboard_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<X_AD_Dashboard_Access>list();
		for(X_AD_Dashboard_Access access : dashboardAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Workflow Access
		List<MWorkflowAccess> workflowAccessList = new Query(ctx, I_AD_Workflow_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<MWorkflowAccess>list();
		for(MWorkflowAccess access : workflowAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Document Action Access
		List<X_AD_Document_Action_Access> documentActionAccessList = new Query(ctx, I_AD_Document_Action_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<X_AD_Document_Action_Access>list();
		for(X_AD_Document_Action_Access access : documentActionAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Include Role Access
		List<X_AD_Role_Included> includeRoleAccessList = new Query(ctx, I_AD_Role_Included.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<X_AD_Role_Included>list();
		for(X_AD_Role_Included access : includeRoleAccessList) {
			MRole includedRole = MRole.get(ctx, access.getIncluded_Role_ID());
			packOut.createGenericPO(document, includedRole, true, null);
			packOut.createGenericPO(document, access);
		}
		//	Table Access
		List<MTableAccess> tableAccessList = new Query(ctx, I_AD_Table_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.<MTableAccess>list();
		for(MTableAccess access : tableAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Column Access
		List<MColumnAccess> columnAccessList = new Query(Env.getCtx(), I_AD_Column_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.setOnlyActiveRecords(true)
			.<MColumnAccess>list();
		for(MColumnAccess access : columnAccessList) {
			packOut.createGenericPO(document, access);
		}
		//	Record Access
		List<MRecordAccess> recordAccessList = new Query(Env.getCtx(), I_AD_Record_Access.Table_Name, "AD_Role_ID = ?", null)
			.setParameters(roleId)
			.setOnlyActiveRecords(true)
			.<MRecordAccess>list();
		for(MRecordAccess access : recordAccessList) {
			packOut.createGenericPO(document, access);
		}
	}
}
