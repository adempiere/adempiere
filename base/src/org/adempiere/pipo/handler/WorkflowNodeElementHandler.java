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
 *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com
 * Contributor(s): Low Heng Sin hengsin@avantz.com
 * Contributor(s): Victor Perez. victor.perez@e-evolution.com [Bugs-1789058 ]
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.model.I_AD_Browse;
import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Form;
import org.compiere.model.I_AD_Image;
import org.compiere.model.I_AD_Process;
import org.compiere.model.I_AD_Task;
import org.compiere.model.I_AD_WF_Block;
import org.compiere.model.I_AD_WF_Node;
import org.compiere.model.I_AD_WF_Process;
import org.compiere.model.I_AD_WF_Responsible;
import org.compiere.model.I_AD_Window;
import org.compiere.model.I_AD_Workflow;
import org.compiere.model.X_AD_WF_Node;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class WorkflowNodeElementHandler extends AbstractElementHandler {

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		Attributes atts = element.attributes;
		String elementValue = element.getElementValue();
		String uuid = getUUIDValue(atts, I_AD_WF_Node.Table_Name);
		String workflowUuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Window_ID);
		log.info(elementValue + " " + uuid);
		String entitytype = getStringValue(atts, I_AD_WF_Node.COLUMNNAME_EntityType);
		if (isProcessElement(ctx, entitytype)) {
			if (element.parent != null && element.parent.skip) {
				element.skip = true;
				return;
			}
			if (element.parent != null && element.parent.getElementValue().equals("workflow")
				&& element.parent.defer) {
				element.unresolved = "Parent element mark as defer: " + workflowUuid;
				element.defer = true;
				return;
			}
			
			int workflowId = 0;
			if (element.parent != null && element.parent.getElementValue().equals("workflow")
				&& element.parent.recordId > 0)
				workflowId = element.parent.recordId;
			else {
				workflowId = getIdFromUUID(ctx, I_AD_WF_Node.Table_Name, workflowUuid);
				if (workflowId <= 0) {
					element.defer = true;
					element.unresolved = "AD_Workflow: " + workflowUuid;
					return;
				}
				else if (element.parent != null && element.parent.getElementValue().equals("workflow"))
					element.parent.recordId = workflowId;
			}
			//	
			int id = getIdFromUUID(ctx, I_AD_WF_Node.Table_Name, uuid);
			X_AD_WF_Node wFNode = new X_AD_WF_Node(ctx, id, getTrxName(ctx));
			int backupId = -1;
			String objectStatus = null;
			if (id <= 0 && getIntValue(atts, I_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID) > 0 && getIntValue(atts, I_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID) <= PackOut.MAX_OFFICIAL_ID) {
				wFNode.setAD_WF_Node_ID(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID));
				wFNode.setIsDirectLoad(true);
			}
			if (id > 0) {
				backupId = copyRecord(ctx, "AD_WF_Node", wFNode);
				objectStatus = "Update";
			} else {
				objectStatus = "New";
				backupId = 0;
			}
			wFNode.setUUID(uuid);
			//	Workflow
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Workflow_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_Workflow.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_Workflow_ID(id);
			}
			//	For Window
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Window_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_Window.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_Window_ID(id);
			}
			//	For Task
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Task_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_Task.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_Task_ID(id);
			}
			//	For Process
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Process_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_WF_Process.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_Process_ID(id);
			}
			//	For Form
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Form_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_Form.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_Form_ID(id);
			}
			//	For Browse
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Browse_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_Browse.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_Browse_ID(id);
			}
			//	For Block
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_WF_Block_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_WF_Block.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_WF_Block_ID(id);
			}
			//	For Responsible
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_WF_Responsible_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_WF_Responsible.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_WF_Responsible_ID(id);
			}
			//	For Image
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Image_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_Image.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_Image_ID(id);
			}
			//	For Column
			uuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_Column_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_Column.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				wFNode.setAD_Column_ID(id);
			}
			//	Standard Attributes
			wFNode.setValue(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_Value));
			wFNode.setName(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_Name));
			wFNode.setDescription(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_Description));
			wFNode.setHelp(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_Help));
			wFNode.setAction(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_Action));
			wFNode.setAttributeName(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_AttributeName));
			wFNode.setAttributeValue(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_AttributeValue));
			wFNode.setCost(getBigDecimalValue(atts, I_AD_WF_Node.COLUMNNAME_Cost));
			wFNode.setDocAction(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_DocAction));
			wFNode.setDuration(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_Duration));
			wFNode.setDynPriorityChange(getBigDecimalValue(atts, I_AD_WF_Node.COLUMNNAME_DynPriorityChange));
			wFNode.setDynPriorityUnit(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_DynPriorityUnit));
			wFNode.setEMail(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_EMail));
			wFNode.setEMailRecipient(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_EMailRecipient));
			wFNode.setEntityType(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_EntityType));
			wFNode.setFinishMode(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_FinishMode));
			wFNode.setIsActive(getBooleanValue(atts, I_AD_WF_Node.COLUMNNAME_IsActive));
			wFNode.setIsCentrallyMaintained(getBooleanValue(atts, I_AD_WF_Node.COLUMNNAME_IsCentrallyMaintained));
			wFNode.setIsMilestone(getBooleanValue(atts, I_AD_WF_Node.COLUMNNAME_IsMilestone));
			wFNode.setIsSubcontracting(getBooleanValue(atts, I_AD_WF_Node.COLUMNNAME_IsSubcontracting));
			wFNode.setJoinElement(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_JoinElement));
			wFNode.setLimit(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_Limit));
			wFNode.setMovingTime(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_MovingTime));
			wFNode.setOverlapUnits(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_OverlapUnits));
			wFNode.setPriority(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_Priority));
			wFNode.setQueuingTime(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_QueuingTime));
			wFNode.setSetupTime(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_SetupTime));
			wFNode.setSplitElement(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_SplitElement));
			wFNode.setStartMode(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_StartMode));
			wFNode.setSubflowExecution(getStringValue(atts, I_AD_WF_Node.COLUMNNAME_SubflowExecution));
			wFNode.setUnitsCycles(getBigDecimalValue(atts, I_AD_WF_Node.COLUMNNAME_UnitsCycles));
			wFNode.setValidFrom(getTimestampValue(atts, I_AD_WF_Node.COLUMNNAME_ValidFrom));
			wFNode.setValidTo(getTimestampValue(atts, I_AD_WF_Node.COLUMNNAME_ValidTo));
			wFNode.setWaitingTime(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_WaitingTime));
			wFNode.setWaitTime(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_WaitTime));
			wFNode.setWorkingTime(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_WorkingTime));
			wFNode.setXPosition(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_XPosition));
			wFNode.setYield(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_Yield));
			wFNode.setYPosition(getIntValue(atts, I_AD_WF_Node.COLUMNNAME_YPosition));
			//	Save
			try {
				wFNode.saveEx(getTrxName(ctx));
				log.info("m_WFNode save success");
				recordLog(ctx, 1, wFNode.getName(), "WFNode", wFNode
						.get_ID(), backupId, objectStatus, "AD_WF_Node",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_WF_Node"));
			} catch (Exception e) {
				log.info("m_WFNode save failure");
				recordLog(ctx, 0, wFNode.getName(), "WFNode", wFNode
						.get_ID(), backupId, objectStatus, "AD_WF_Node",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_WF_Node"));
				throw new POSaveFailedException(e);
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}

	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int nodeId = Env.getContextAsInt(ctx, X_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID);
		AttributesImpl atts = new AttributesImpl();
		X_AD_WF_Node m_WF_Node = new X_AD_WF_Node(ctx, nodeId, getTrxName(ctx));
		createWorkflowNodeBinding(atts, m_WF_Node);
		document.startElement("", "", "workflowNode", atts);
		document.endElement("", "", "workflowNode");
	}

	private AttributesImpl createWorkflowNodeBinding(AttributesImpl atts, X_AD_WF_Node node) {
		atts.clear();
		AttributeFiller filler = new AttributeFiller(atts, node);
		if (node.getAD_WF_Node_ID() <= PackOut.MAX_OFFICIAL_ID) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID);
		}
		filler.addUUID();
		//	Workflow
		if (node.getAD_Workflow_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_Workflow_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_Workflow_ID, getUUIDFromId(node.getCtx(), I_AD_Workflow.Table_Name, node.getAD_Workflow_ID()));
		}
		//	Window
		if (node.getAD_Window_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_Window_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_Window_ID, getUUIDFromId(node.getCtx(), I_AD_Window.Table_Name, node.getAD_Window_ID()));
		}
		//	Task
		if (node.getAD_Task_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_Task_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_Task_ID, getUUIDFromId(node.getCtx(), I_AD_Task.Table_Name, node.getAD_Task_ID()));
		}
		//	Process
		if (node.getAD_Process_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_Process_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_Process_ID, getUUIDFromId(node.getCtx(), I_AD_Process.Table_Name, node.getAD_Process_ID()));
		}
		//	Form
		if (node.getAD_Form_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_Form_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_Form_ID, getUUIDFromId(node.getCtx(), I_AD_Form.Table_Name, node.getAD_Form_ID()));
		}
		//	Browse
		if (node.getAD_Browse_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_Browse_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_Browse_ID, getUUIDFromId(node.getCtx(), I_AD_Browse.Table_Name, node.getAD_Browse_ID()));
		}
		//	Block
		if (node.getAD_WF_Block_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_WF_Block_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_WF_Block_ID, getUUIDFromId(node.getCtx(), I_AD_WF_Block.Table_Name, node.getAD_WF_Block_ID()));
		}
		//	Responsible
		if (node.getAD_WF_Responsible_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_WF_Responsible_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_WF_Responsible_ID, getUUIDFromId(node.getCtx(), I_AD_WF_Responsible.Table_Name, node.getAD_WF_Responsible_ID()));
		}
		//	Image
		if (node.getAD_Image_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_Image_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_Image_ID, getUUIDFromId(node.getCtx(), I_AD_Image.Table_Name, node.getAD_Image_ID()));
		}
		//[Bugs-1789058 ]
		if (node.getWorkflow_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_Workflow_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_Workflow_ID, getUUIDFromId(node.getCtx(), I_AD_Workflow.Table_Name, node.getWorkflow_ID()));
		}
		//	Column
		if (node.getAD_Column_ID() > 0) {
			filler.add(I_AD_WF_Node.COLUMNNAME_AD_Column_ID, true);
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_Column_ID, getUUIDFromId(node.getCtx(), I_AD_Column.Table_Name, node.getAD_Column_ID()));
		}
		//	Standard Attributes
		filler.add(I_AD_WF_Node.COLUMNNAME_Value);
		filler.add(I_AD_WF_Node.COLUMNNAME_Name);
		filler.add(I_AD_WF_Node.COLUMNNAME_Description);
		filler.add(I_AD_WF_Node.COLUMNNAME_Help);
		filler.add(I_AD_WF_Node.COLUMNNAME_Action);
		filler.add(I_AD_WF_Node.COLUMNNAME_AttributeName);
		filler.add(I_AD_WF_Node.COLUMNNAME_AttributeValue);
		filler.add(I_AD_WF_Node.COLUMNNAME_Cost);
		filler.add(I_AD_WF_Node.COLUMNNAME_DocAction);
		filler.add(I_AD_WF_Node.COLUMNNAME_Duration);
		filler.add(I_AD_WF_Node.COLUMNNAME_DynPriorityChange);
		filler.add(I_AD_WF_Node.COLUMNNAME_DynPriorityUnit);
		filler.add(I_AD_WF_Node.COLUMNNAME_EMail);
		filler.add(I_AD_WF_Node.COLUMNNAME_EMailRecipient);
		filler.add(I_AD_WF_Node.COLUMNNAME_EntityType);
		filler.add(I_AD_WF_Node.COLUMNNAME_FinishMode);
		filler.add(I_AD_WF_Node.COLUMNNAME_IsActive);
		filler.add(I_AD_WF_Node.COLUMNNAME_IsCentrallyMaintained);
		filler.add(I_AD_WF_Node.COLUMNNAME_IsMilestone);
		filler.add(I_AD_WF_Node.COLUMNNAME_IsSubcontracting);
		filler.add(I_AD_WF_Node.COLUMNNAME_JoinElement);
		filler.add(I_AD_WF_Node.COLUMNNAME_Limit);
		filler.add(I_AD_WF_Node.COLUMNNAME_MovingTime);
		filler.add(I_AD_WF_Node.COLUMNNAME_OverlapUnits);
		filler.add(I_AD_WF_Node.COLUMNNAME_Priority);
		filler.add(I_AD_WF_Node.COLUMNNAME_QueuingTime);
		filler.add(I_AD_WF_Node.COLUMNNAME_SetupTime);
		filler.add(I_AD_WF_Node.COLUMNNAME_SplitElement);
		filler.add(I_AD_WF_Node.COLUMNNAME_StartMode);
		filler.add(I_AD_WF_Node.COLUMNNAME_SubflowExecution);
		filler.add(I_AD_WF_Node.COLUMNNAME_UnitsCycles);
		filler.add(I_AD_WF_Node.COLUMNNAME_ValidFrom);
		filler.add(I_AD_WF_Node.COLUMNNAME_ValidTo);
		filler.add(I_AD_WF_Node.COLUMNNAME_WaitingTime);
		filler.add(I_AD_WF_Node.COLUMNNAME_WaitTime);
		filler.add(I_AD_WF_Node.COLUMNNAME_WorkingTime);
		filler.add(I_AD_WF_Node.COLUMNNAME_XPosition);
		filler.add(I_AD_WF_Node.COLUMNNAME_Yield);
		filler.add(I_AD_WF_Node.COLUMNNAME_YPosition);
		return atts;
	}
}
