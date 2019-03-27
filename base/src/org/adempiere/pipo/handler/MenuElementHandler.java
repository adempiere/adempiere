/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com
 * Contributor(s): Low Heng Sin hengsin@avantz.com
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.model.I_AD_Browse;
import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.I_AD_Form;
import org.compiere.model.I_AD_Menu;
import org.compiere.model.I_AD_Process;
import org.compiere.model.I_AD_Task;
import org.compiere.model.I_AD_TreeNodeMM;
import org.compiere.model.I_AD_Window;
import org.compiere.model.I_AD_Workbench;
import org.compiere.model.I_AD_Workflow;
import org.compiere.model.MMenu;
import org.compiere.model.MTree;
import org.compiere.model.MTree_NodeMM;
import org.compiere.model.X_AD_Menu;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class MenuElementHandler extends AbstractElementHandler {

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		String elementValue = element.getElementValue();
		int backupId = -1;
		String Object_Status = null;
		Attributes atts = element.attributes;
		String uuid = getUUIDValue(atts, I_AD_Menu.Table_Name);
		log.info(elementValue + " " + uuid);
		int menuId = getIdFromUUID(ctx, I_AD_Menu.Table_Name, uuid);
		X_AD_Menu menu = new X_AD_Menu(ctx, menuId, getTrxName(ctx));
		if (menuId <= 0 && getIntValue(atts, I_AD_Menu.COLUMNNAME_AD_Menu_ID) > 0 && getIntValue(atts, I_AD_Menu.COLUMNNAME_AD_Menu_ID) <= PackOut.MAX_OFFICIAL_ID) {
			menu.setAD_Menu_ID(getIntValue(atts, I_AD_Menu.COLUMNNAME_AD_Menu_ID));
		}
		if (menuId > 0) {
			backupId = copyRecord(ctx, I_AD_Menu.Table_Name, menu);
			Object_Status = "Update";
		} else {
			Object_Status = "New";
			backupId = 0;
		}
		menu.setIsDirectLoad(true);
		menu.setUUID(uuid);
		//	For window
		menu.setName(atts.getValue(I_AD_Menu.COLUMNNAME_Name));
		uuid = getUUIDValue(atts, I_AD_Menu.COLUMNNAME_AD_Window_ID);
		if (!Util.isEmpty(uuid)) {
			int id = getIdFromUUID(ctx, I_AD_Window.Table_Name, uuid);
			if (id <= 0) {
				element.defer = true;
				return;
			}
			menu.setAD_Window_ID(id);
		}
		//	for Process
		uuid = getUUIDValue(atts, I_AD_Menu.COLUMNNAME_AD_Process_ID);
		if (!Util.isEmpty(uuid)) {
			int id = getIdFromUUID(ctx, I_AD_Process.Table_Name, uuid);
			if (id <= 0) {
				element.defer = true;
				return;
			}
			menu.setAD_Process_ID(id);
		}
		//	For Form
		uuid = getUUIDValue(atts, I_AD_Menu.COLUMNNAME_AD_Form_ID);
		if (!Util.isEmpty(uuid)) {
			int id = getIdFromUUID(ctx, I_AD_Form.Table_Name, uuid);
			if (id <= 0) {
				element.defer = true;
				return;
			}
			menu.setAD_Form_ID(id);
		}
		//	Browse
		uuid = getUUIDValue(atts, I_AD_Menu.COLUMNNAME_AD_Browse_ID);
		if (!Util.isEmpty(uuid)) {
			int id = getIdFromUUID(ctx, I_AD_Browse.Table_Name, uuid);
			if (id <= 0) {
				element.defer = true;
				return;
			}
			menu.setAD_Browse_ID(id);
		}
		//	Task
		uuid = getUUIDValue(atts, I_AD_Menu.COLUMNNAME_AD_Task_ID);
		if (!Util.isEmpty(uuid)) {
			int id = getIdFromUUID(ctx, I_AD_Task.Table_Name, uuid);
			if (id <= 0) {
				element.defer = true;
				return;
			}
			menu.setAD_Task_ID(id);
		}
		//	Workbench
		uuid = getUUIDValue(atts, I_AD_Menu.COLUMNNAME_AD_Workbench_ID);
		if (!Util.isEmpty(uuid)) {
			int id = getIdFromUUID(ctx, I_AD_Workbench.Table_Name, uuid);
			if (id <= 0) {
				element.defer = true;
				return;
			}
			menu.setAD_Workbench_ID(id);
		}
		//	Workflow
		uuid = getUUIDValue(atts, I_AD_Menu.COLUMNNAME_AD_Workflow_ID);
		if (!Util.isEmpty(uuid)) {
			int id = getIdFromUUID(ctx, I_AD_Workflow.Table_Name, uuid);
			if (id <= 0) {
				element.defer = true;
				return;
			}
			menu.setAD_Workflow_ID(id);
		}
		
		String action = (atts.getValue(I_AD_Menu.COLUMNNAME_Action) != null ? atts.getValue(I_AD_Menu.COLUMNNAME_Action) : " ");
		if (action.compareTo(" ") > -1) {
			menu.setAction(action);
		}
		menu.setDescription(getStringValue(atts, I_AD_Menu.COLUMNNAME_Description));
		menu.setEntityType(getStringValue(atts, I_AD_Menu.COLUMNNAME_EntityType));
		
		menu.setIsReadOnly(getBooleanValue(atts, I_AD_Menu.COLUMNNAME_IsReadOnly));
		menu.setIsSOTrx(getBooleanValue(atts, I_AD_Menu.COLUMNNAME_IsSOTrx));
		menu.setIsSummary(getBooleanValue(atts, I_AD_Menu.COLUMNNAME_IsSummary));
		menu.setIsActive(getBooleanValue(atts, I_AD_Menu.COLUMNNAME_IsActive));
		int detailId;
		try {
			menu.saveEx(getTrxName(ctx));
			detailId = recordLog(ctx, 1, menu.getUUID(), "Menu", menu
					.get_ID(), backupId, Object_Status, "AD_Menu",
					get_IDWithColumn(ctx, "AD_Table", "TableName", "AD_Menu"));
		} catch (Exception e) {
			detailId = recordLog(ctx, 0, menu.getUUID(), "Menu", menu
					.get_ID(), backupId, Object_Status, "AD_Menu",
					get_IDWithColumn(ctx, "AD_Table", "TableName",
							"AD_Menu"));
			throw new POSaveFailedException(e);
		}
		//	Parent Menu UUID
		uuid = getUUIDValue(atts, I_AD_TreeNodeMM.COLUMNNAME_Parent_ID);
		int parentId = getIdFromUUID(ctx, I_AD_Menu.Table_Name, uuid);
		//	Default tree ID
		int defaultTreeId = MTree.getDefaultTreeIdFromTableId(menu.getAD_Client_ID(), I_AD_Menu.Table_ID);
		String sqlCounter = "SELECT count(Parent_ID) FROM AD_TREENODEMM WHERE AD_Tree_ID = " + defaultTreeId
				+ " AND Node_ID = " + menuId;
		int countRecords = DB.getSQLValue(getTrxName(ctx), sqlCounter);
		if (countRecords > 0) {
			StringBuffer sqlColumn = new StringBuffer(
					"select * from AD_TREENODEMM where AD_Tree_ID = 10 and "
							+ " Node_ID =?");
			try {
				PreparedStatement pstmt1 = DB.prepareStatement(sqlColumn.toString(),
						getTrxName(ctx));
				pstmt1.setInt(1, menuId);
				ResultSet rs1 = pstmt1.executeQuery();
				if (rs1.next()) {

					String colValue = null;
					ResultSetMetaData meta = rs1.getMetaData();
					int columns = meta.getColumnCount();
					int tableId = get_IDWithColumn(ctx, "AD_Table",
							"TableName", "AD_TreeNodeMM");

					for (int q = 1; q <= columns; q++) {

						String col_Name = meta.getColumnName(q);
						StringBuffer sql = new StringBuffer(
								"SELECT AD_Column_ID FROM AD_column WHERE Upper(ColumnName) = '"
										+ col_Name + "' AND AD_Table_ID = ?");
						int columnId = DB.getSQLValue(getTrxName(ctx), sql
								.toString(), tableId);
						sql = new StringBuffer(
								"SELECT AD_Reference_ID FROM AD_COLUMN WHERE AD_Column_ID = "
										+ (columnId == -1 ? "null" : columnId));
						int referenceId = DB.getSQLValue(getTrxName(ctx), sql
								.toString());
						int idBackup = DB.getNextID(Env
								.getAD_Client_ID(ctx), "AD_Package_Imp_Backup",
								getTrxName(ctx));
						if (referenceId == 20 || referenceId == 28)
							if (rs1.getObject(q).equals("Y"))
								colValue = "true";
							else
								colValue = "false";
						else
							colValue = rs1.getObject(q).toString();

						StringBuffer sqlD = new StringBuffer(
								"INSERT INTO AD_Package_Imp_Backup"
										+ "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, "
										+ "AD_PACKAGE_IMP_BACKUP_ID, AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID,"
										+ " AD_TABLE_ID, AD_COLUMN_ID, AD_REFERENCE_ID, COLVALUE)"
										+ "VALUES(" + " "
										+ Env.getAD_Client_ID(ctx)
										+ ", "
										+ Env.getAD_Org_ID(ctx)
										+ ", "
										+ Env.getAD_User_ID(ctx)
										+ ", "
										+ Env.getAD_User_ID(ctx)
										+ ", "
										+ idBackup
										+ ", "
										+ detailId
										+ ", "
										+ getPackageImpId(ctx)
										+ ", "
										+ tableId
										+ ", "
										+ (columnId == -1 ? "null" : columnId)
										+ ", "
										+ (referenceId == -1 ? "null" : referenceId)
										+ ", '" + colValue + "')");
						int no = DB.executeUpdate(sqlD.toString(),
								getTrxName(ctx));
						if (no == -1)
							log.info("Insert to import backup failed");
					}

				}
				rs1.close();
				pstmt1.close();
				pstmt1 = null;

			} catch (Exception e) {
				log.info("get_IDWithMasterID:" + e);
			}
		}
		//	Tree
		MTree tree = MTree.get(ctx, defaultTreeId, getTrxName(ctx));
		MTree_NodeMM treeNode = MTree_NodeMM.get(tree, menu.getAD_Menu_ID());
		if(treeNode == null) {
			treeNode = new MTree_NodeMM(tree, menu.getAD_Menu_ID());
		}
		treeNode.setSeqNo(Integer.valueOf(atts.getValue(I_AD_TreeNodeMM.COLUMNNAME_SeqNo)));
		treeNode.setParent_ID(parentId);
		treeNode.saveEx();
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int menuId = Env.getContextAsInt(ctx, "AD_Menu_ID");
		createApplication(ctx, document, menuId);
	}

	private AttributesImpl createMenuBinding(AttributesImpl atts, X_AD_Menu menu) {
		atts.clear();
		AttributeFiller filler = new AttributeFiller(atts, menu);
		filler.add(I_AD_Menu.COLUMNNAME_AD_Menu_ID);
		filler.addUUID();
		if (menu.getAD_Window_ID() > 0) {
			filler.add(I_AD_Menu.COLUMNNAME_AD_Window_ID, true);
			filler.addUUID(I_AD_Menu.COLUMNNAME_AD_Window_ID, getUUIDFromId(menu.getCtx(), I_AD_Window.Table_Name, menu.getAD_Window_ID()));
		}
		if (menu.getAD_Process_ID() > 0) {
			filler.add(I_AD_Menu.COLUMNNAME_AD_Process_ID, true);
			filler.addUUID(I_AD_Menu.COLUMNNAME_AD_Process_ID, getUUIDFromId(menu.getCtx(), I_AD_Process.Table_Name, menu.getAD_Process_ID()));
		}
		if (menu.getAD_Form_ID() > 0) {
			filler.add(I_AD_Menu.COLUMNNAME_AD_Form_ID, true);
			filler.addUUID(I_AD_Menu.COLUMNNAME_AD_Form_ID, getUUIDFromId(menu.getCtx(), I_AD_Form.Table_Name, menu.getAD_Form_ID()));
		}
		if (menu.getAD_Browse_ID() > 0) {
			filler.add(I_AD_Menu.COLUMNNAME_AD_Browse_ID, true);
			filler.addUUID(I_AD_Menu.COLUMNNAME_AD_Browse_ID, getUUIDFromId(menu.getCtx(), I_AD_Browse.Table_Name, menu.getAD_Browse_ID()));
		}
		if (menu.getAD_Task_ID() > 0) {
			filler.add(I_AD_Menu.COLUMNNAME_AD_Task_ID, true);
			filler.addUUID(I_AD_Menu.COLUMNNAME_AD_Task_ID, getUUIDFromId(menu.getCtx(), I_AD_Task.Table_Name, menu.getAD_Task_ID()));
		}
		if (menu.getAD_Workbench_ID() > 0) {
			filler.add(I_AD_Menu.COLUMNNAME_AD_Workbench_ID, true);
			filler.addUUID(I_AD_Menu.COLUMNNAME_AD_Workbench_ID, getUUIDFromId(menu.getCtx(), I_AD_Workbench.Table_Name, menu.getAD_Workbench_ID()));
		}
		if (menu.getAD_Workflow_ID() > 0) {
			filler.add(I_AD_Menu.COLUMNNAME_AD_Workflow_ID, true);
			filler.addUUID(I_AD_Menu.COLUMNNAME_AD_Workflow_ID, getUUIDFromId(menu.getCtx(), I_AD_Workflow.Table_Name, menu.getAD_Workflow_ID()));
		}
		//	
		filler.add(I_AD_Menu.COLUMNNAME_Name);
		filler.add(I_AD_Menu.COLUMNNAME_Action);
		filler.add(I_AD_Menu.COLUMNNAME_Description);
		filler.add(I_AD_Menu.COLUMNNAME_EntityType);
		filler.add(I_AD_Menu.COLUMNNAME_IsActive);
		filler.add(I_AD_Menu.COLUMNNAME_IsReadOnly);
		filler.add(I_AD_Menu.COLUMNNAME_IsSOTrx);
		filler.add(I_AD_Menu.COLUMNNAME_IsSummary);
		//	Parent Menu UUID
		int defaultTreeId = MTree.getDefaultTreeIdFromTableId(menu.getAD_Client_ID(), I_AD_Menu.Table_ID);
		MTree_NodeMM node = MTree_NodeMM.get(MTree.get(Env.getCtx(), defaultTreeId, null), menu.getAD_Menu_ID());
		if(node.getParent_ID() > 0) {
			MMenu parent = MMenu.getFromId(Env.getCtx(), node.getParent_ID());
			//	Set Parent UUID
			filler.addUUID(I_AD_TreeNodeMM.COLUMNNAME_Parent_ID, parent.getUUID());
		}
		filler.addInt(I_AD_TreeNodeMM.COLUMNNAME_SeqNo, node.getSeqNo());
		return atts;
	}

	/**
	 * Create Application
	 * @param ctx
	 * @param document
	 * @param menuId
	 * @throws SAXException
	 */
	private void createApplication(Properties ctx, TransformerHandler document, int menuId) throws SAXException {
		PackOut packOut = (PackOut)ctx.get("PackOutProcess");
		AttributesImpl atts = new AttributesImpl();
		X_AD_Menu menu = new X_AD_Menu(ctx, menuId, null);
		atts = createMenuBinding(atts, menu);
		document.startElement("", "", "menu", atts);
		if(menu.isSummary()) {
			int defaultTreeId = MTree.getDefaultTreeIdFromTableId(menu.getAD_Client_ID(), I_AD_Menu.Table_ID);
			String childSQL = "SELECT m.AD_Menu_ID "
					+ "FROM AD_Menu m "
					+ "WHERE EXISTS(SELECT 1 FROM AD_TreeNodeMM tnm "
					+ "			WHERE tnm.Node_ID = m.AD_Menu_ID "
					+ "			AND tnm.AD_Tree_ID = " + defaultTreeId + " "
					+ "			AND tnm.Parent_ID = ?)";
			int [] ids = DB.getIDsEx(null, childSQL, menu.getAD_Menu_ID());
			for(int id : ids) {
				//	Recursive call
				createApplication(ctx, document, id);
			}
		} else if (menu.getAD_Window_ID() > 0
				|| menu.getAD_Workflow_ID() > 0
				|| menu.getAD_Task_ID() > 0
				|| menu.getAD_Process_ID() > 0
				|| menu.getAD_Form_ID() > 0
				|| menu.getAD_Browse_ID() > 0
				|| menu.getAD_Workbench_ID() > 0) {
			// Call CreateWindow.
			if (menu.getAD_Window_ID() > 0) {
				packOut.createWindow(menu.getAD_Window_ID(), document);
			}
			// Call CreateProcess.
			else if (menu.getAD_Process_ID() > 0) {
				packOut.createProcess(menu.getAD_Process_ID(), document);
			}
			// Call CreateTask.
			else if (menu.getAD_Task_ID() > 0) {
				packOut.createTask(menu.getAD_Task_ID(), document);
			}
			// Call CreateForm.
			else if (menu.getAD_Form_ID() > 0) {
				packOut.createForm(menu.getAD_Form_ID(), document);
			}
			// Call CreateBrowse.
			else if (menu.getAD_Browse_ID() > 0) {
				packOut.createBrowse(menu.getAD_Browse_ID(), document);
			}
			// Call CreateWorkflow
			else if (menu.getAD_Workflow_ID() > 0) {
				packOut.createWorkflow(menu.getAD_Workflow_ID(), 
						document);
			}
			// Call CreateModule because entry is a summary menu
		}
		document.endElement("", "", "menu");
	}
}
