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
 *                 Teo Sarca, teo.sarca@gmail.com
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.I_AD_Table;
import org.compiere.model.I_AD_WF_NextCondition;
import org.compiere.model.I_AD_WF_Node;
import org.compiere.model.I_AD_WF_NodeNext;
import org.compiere.model.I_AD_WF_Responsible;
import org.compiere.model.I_AD_Workflow;
import org.compiere.model.I_AD_WorkflowProcessor;
import org.compiere.model.I_S_Resource;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.model.X_AD_WF_NextCondition;
import org.compiere.model.X_AD_WF_Node;
import org.compiere.model.X_AD_WF_NodeNext;
import org.compiere.model.X_AD_Workflow;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.compiere.wf.MWFNextCondition;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFNodeNext;
import org.compiere.wf.MWorkflow;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class WorkflowElementHandler extends AbstractElementHandler {

	private WorkflowNodeElementHandler nodeHandler = new WorkflowNodeElementHandler();
	private WorkflowNodeNextElementHandler nodeNextHandler = new WorkflowNodeNextElementHandler();
	private WorkflowNodeNextConditionElementHandler nextConditionHandler = new WorkflowNodeNextConditionElementHandler();
	
	private List<Integer> workflows = new ArrayList<Integer>();

	public void startElement(Properties ctx, Element element) throws SAXException {
		Attributes atts = element.attributes;
		String uuid = getUUIDValue(atts, I_AD_Workflow.Table_Name);
		log.info(uuid);
		String entitytype = getStringValue(atts, I_AD_Workflow.COLUMNNAME_EntityType);
		if (isProcessElement(ctx, entitytype)) {
			int id = getIdFromUUID(ctx, I_AD_Workflow.Table_Name, uuid);
			if (id > 0 && workflows.contains(id)) {
				element.skip = true;
				return;
			}

			X_AD_Workflow workflow = new X_AD_Workflow(ctx, id, getTrxName(ctx));
			int backupId = -1;
			String objectStatus = null;
			if (id <= 0 && getIntValue(atts, I_AD_Workflow.COLUMNNAME_AD_Workflow_ID) > 0 && getIntValue(atts, I_AD_Workflow.COLUMNNAME_AD_Workflow_ID) <= PackOut.MAX_OFFICIAL_ID) {
				workflow.setAD_Workflow_ID(getIntValue(atts, I_AD_Workflow.COLUMNNAME_AD_Workflow_ID));
				workflow.setIsDirectLoad(true);
			}
			if (id > 0) {
				backupId = copyRecord(ctx, "AD_Workflow", workflow);
				objectStatus = "Update";
			} else {
				objectStatus = "New";
				backupId = 0;
			}
			workflow.setUUID(uuid);
			//	For Table
			uuid = getUUIDValue(atts, I_AD_Workflow.COLUMNNAME_AD_Table_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_Table.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				workflow.setAD_Table_ID(id);
			}
			//	For Responsible
			uuid = getUUIDValue(atts, I_AD_Workflow.COLUMNNAME_AD_WF_Responsible_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_WF_Responsible.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				workflow.setAD_WF_Responsible_ID(id);
			}
			//	For Processor
			uuid = getUUIDValue(atts, I_AD_Workflow.COLUMNNAME_AD_WorkflowProcessor_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_AD_WorkflowProcessor.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				workflow.setAD_WorkflowProcessor_ID(id);
			}
			//	For Resource
			uuid = getUUIDValue(atts, I_AD_Workflow.COLUMNNAME_S_Resource_ID);
			if (!Util.isEmpty(uuid)) {
				id = getIdFromUUID(ctx, I_S_Resource.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				workflow.setS_Resource_ID(id);
			}
			//	Standard Attributes
			workflow.setValue(getStringValue(atts, I_AD_Workflow.COLUMNNAME_Value));
			workflow.setName(getStringValue(atts, I_AD_Workflow.COLUMNNAME_Name));
			workflow.setDescription(getStringValue(atts, I_AD_Workflow.COLUMNNAME_Description));
			workflow.setHelp(getStringValue(atts, I_AD_Workflow.COLUMNNAME_Help));
			workflow.setAccessLevel(getStringValue(atts, I_AD_Workflow.COLUMNNAME_AccessLevel));
			workflow.setAuthor(getStringValue(atts, I_AD_Workflow.COLUMNNAME_Author));
			workflow.setCost(getBigDecimalValue(atts, I_AD_Workflow.COLUMNNAME_Cost));
			String documentNo = getStringValue(atts, I_AD_Workflow.COLUMNNAME_DocumentNo);
			workflow.setDocumentNo(documentNo == null? "": documentNo);
			workflow.setDocValueLogic(getStringValue(atts, I_AD_Workflow.COLUMNNAME_DocValueLogic));
			workflow.setDuration(getIntValue(atts, I_AD_Workflow.COLUMNNAME_Duration));
			workflow.setDurationUnit(getStringValue(atts, I_AD_Workflow.COLUMNNAME_DurationUnit));
			workflow.setEntityType(getStringValue(atts, I_AD_Workflow.COLUMNNAME_EntityType));
			workflow.setIsActive(getBooleanValue(atts, I_AD_Workflow.COLUMNNAME_IsActive));
			workflow.setIsBetaFunctionality(getBooleanValue(atts, I_AD_Workflow.COLUMNNAME_IsBetaFunctionality));
			workflow.setIsDefault(getBooleanValue(atts, I_AD_Workflow.COLUMNNAME_IsDefault));
			workflow.setIsValid(getBooleanValue(atts, I_AD_Workflow.COLUMNNAME_IsValid));
			workflow.setLimit(getIntValue(atts, I_AD_Workflow.COLUMNNAME_Limit));
			workflow.setMovingTime(getIntValue(atts, I_AD_Workflow.COLUMNNAME_MovingTime));
			workflow.setOverlapUnits(getBigDecimalValue(atts, I_AD_Workflow.COLUMNNAME_OverlapUnits));
			workflow.setPriority(getIntValue(atts, I_AD_Workflow.COLUMNNAME_Priority));
			workflow.setProcessType(getStringValue(atts, I_AD_Workflow.COLUMNNAME_ProcessType));
			workflow.setPublishStatus(getStringValue(atts, I_AD_Workflow.COLUMNNAME_PublishStatus));
			workflow.setQtyBatchSize(getBigDecimalValue(atts, I_AD_Workflow.COLUMNNAME_QtyBatchSize));
			workflow.setQueuingTime(getIntValue(atts, I_AD_Workflow.COLUMNNAME_QueuingTime));
			workflow.setSetupTime(getIntValue(atts, I_AD_Workflow.COLUMNNAME_SetupTime));
			workflow.setUnitsCycles(getBigDecimalValue(atts, I_AD_Workflow.COLUMNNAME_UnitsCycles));
			workflow.setValidFrom(getTimestampValue(atts, I_AD_Workflow.COLUMNNAME_ValidFrom));
			workflow.setValidTo(getTimestampValue(atts, I_AD_Workflow.COLUMNNAME_ValidTo));
			workflow.setVersion(getIntValue(atts, I_AD_Workflow.COLUMNNAME_Version));
			workflow.setWaitingTime(getIntValue(atts, I_AD_Workflow.COLUMNNAME_WaitingTime));
			workflow.setWorkflowType(getStringValue(atts, I_AD_Workflow.COLUMNNAME_WorkflowType));
			workflow.setWorkingTime(getIntValue(atts, I_AD_Workflow.COLUMNNAME_WorkingTime));
			workflow.setYield(getIntValue(atts, I_AD_Workflow.COLUMNNAME_Yield));
			//	
			workflow.setAD_WF_Node_ID(-1);
			//	Save
			try {
				workflow.saveEx(getTrxName(ctx));
				recordLog(ctx, 1, workflow.getUUID(), "Workflow", workflow
						.get_ID(), backupId, objectStatus, "AD_Workflow",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_Workflow"));
				workflows.add(workflow.getAD_Workflow_ID());
				element.recordId = workflow.getAD_Workflow_ID();
			} catch (Exception e) {
				recordLog(ctx, 0, workflow.getUUID(), "Workflow", workflow
						.get_ID(), backupId, objectStatus, "AD_Workflow",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_Workflow"));
				throw new POSaveFailedException(e);
			}
		} else {
			element.skip = true;
		}
	}

	/**
	 * @param ctx
	 * @param element 
	 */
	public void endElement(Properties ctx, Element element) throws SAXException {
		if (!element.defer && !element.skip && element.recordId > 0) {
			Attributes atts = element.attributes;
			//set start node
			String name = atts.getValue("ADWorkflowNodeNameID");
			if (name != null && name.trim().length() > 0) {
				MWorkflow m_Workflow = new MWorkflow(ctx, element.recordId, getTrxName(ctx));
				int id = get_IDWithMasterAndColumn(ctx, "AD_WF_Node", "Name", name, "AD_Workflow", m_Workflow.getAD_Workflow_ID()); 
				if (id <= 0) {
					log.warning("Failed to resolve start node reference for workflow element. Workflow=" 
							+ m_Workflow.getUUID() + " StartNode=" + name);
					return;
				}
				m_Workflow.setAD_WF_Node_ID(id);
				if (m_Workflow.save(getTrxName(ctx)) == true) {
					log.info("m_Workflow update success");
					recordLog(ctx, 1, m_Workflow.getUUID(), "Workflow", m_Workflow
							.get_ID(), 0, "Update", "AD_Workflow",
							get_IDWithColumn(ctx, "AD_Table", "TableName",
									"AD_Workflow"));
					workflows.add(m_Workflow.getAD_Workflow_ID());
					element.recordId = m_Workflow.getAD_Workflow_ID();
				} else {
					log.info("m_Workflow update fail");
					recordLog(ctx, 0, m_Workflow.getName(), "Workflow", m_Workflow
							.get_ID(), 0, "Update", "AD_Workflow",
							get_IDWithColumn(ctx, "AD_Table", "TableName",
									"AD_Workflow"));
					throw new POSaveFailedException("MWorkflow");
				}
			}			
		}
	}

	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int workflowId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Workflow_ID);
		if (workflows.contains(workflowId)) {
			return;
		}
		workflows.add(workflowId);
		//	
		AttributesImpl atts = new AttributesImpl();
		//	
		MWorkflow workflow = new MWorkflow(ctx, workflowId, null);
		//	
		createWorkflowBinding(atts, workflow);
		document.startElement("", "", "workflow", atts);
		//	Node as for
		MWFNode[] nodes = workflow.getNodes(true, Env.getAD_Client_ID(ctx));
		for(MWFNode node : nodes) {
			createNode(ctx, document, node.getAD_WF_Node_ID());
			//	for next nodes
			List<MWFNodeNext> nextNodeList =new Query(ctx, I_AD_WF_NodeNext.Table_Name, I_AD_WF_NodeNext.COLUMNNAME_AD_WF_Node_ID + " = ?", null)
				.setParameters(node.getAD_WF_Node_ID())
				.setClient_ID()
				.<MWFNodeNext>list();
			//	
			for(MWFNodeNext nextNode : nextNodeList) {
				createNodeNext(ctx, document, nextNode.getAD_WF_NodeNext_ID());
				//	for conditions
				List<MWFNextCondition> nextConditionList =new Query(ctx, I_AD_WF_NextCondition.Table_Name, I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NodeNext_ID + " = ?", null)
					.setParameters(nextNode.getAD_WF_NodeNext_ID())
					.setClient_ID()
					.<MWFNextCondition>list();
				//	
				for(MWFNextCondition nextCondition : nextConditionList) {
					createNodeNextCondition(ctx, document, nextCondition.getAD_WF_NextCondition_ID());
				}
			}
		}
		document.endElement("", "", "workflow");
	}

	private void createNodeNextCondition(Properties ctx, TransformerHandler document, int nodeNextConditionId) throws SAXException {
		Env.setContext(ctx, X_AD_WF_NextCondition.COLUMNNAME_AD_WF_NextCondition_ID, nodeNextConditionId);
		nextConditionHandler.create(ctx, document);
		ctx.remove(X_AD_WF_NextCondition.COLUMNNAME_AD_WF_NextCondition_ID);
	}

	private void createNodeNext(Properties ctx, TransformerHandler document, int nodeNextId) throws SAXException {
		Env.setContext(ctx, X_AD_WF_NodeNext.COLUMNNAME_AD_WF_NodeNext_ID, nodeNextId);
		nodeNextHandler.create(ctx, document);
		ctx.remove(X_AD_WF_NodeNext.COLUMNNAME_AD_WF_NodeNext_ID);
	}

	private void createNode(Properties ctx, TransformerHandler document, int nodeId) throws SAXException {
		Env.setContext(ctx, X_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID, nodeId);
		nodeHandler.create(ctx, document);
		ctx.remove(X_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID);
	}

	private AttributesImpl createWorkflowBinding(AttributesImpl atts, X_AD_Workflow workflow) {
		atts.clear();
		AttributeFiller filler = new AttributeFiller(atts, workflow);
		if (workflow.getAD_Workflow_ID() <= PackOut.MAX_OFFICIAL_ID) {
			filler.add(I_AD_Workflow.COLUMNNAME_AD_Workflow_ID);
		}
		filler.addUUID();
		//	Table
		if (workflow.getAD_Table_ID() > 0) {
			filler.add(I_AD_Workflow.COLUMNNAME_AD_Table_ID, true);
			filler.addUUID(I_AD_Workflow.COLUMNNAME_AD_Table_ID, getUUIDFromId(workflow.getCtx(), I_AD_Table.Table_Name, workflow.getAD_Table_ID()));
		}
		//	Default Node
		if (workflow.getAD_WF_Node_ID() > 0) {
			filler.add(I_AD_Workflow.COLUMNNAME_AD_WF_Node_ID, true);
			filler.addUUID(I_AD_Workflow.COLUMNNAME_AD_WF_Node_ID, getUUIDFromId(workflow.getCtx(), I_AD_WF_Node.Table_Name, workflow.getAD_WF_Node_ID()));
		}
		//	Responsible
		if (workflow.getAD_WF_Responsible_ID() > 0) {
			filler.add(I_AD_Workflow.COLUMNNAME_AD_WF_Responsible_ID, true);
			filler.addUUID(I_AD_Workflow.COLUMNNAME_AD_WF_Responsible_ID, getUUIDFromId(workflow.getCtx(), I_AD_WF_Responsible.Table_Name, workflow.getAD_WF_Responsible_ID()));
		}
		//	Workflow Processor
		if (workflow.getAD_WorkflowProcessor_ID() > 0) {
			filler.add(I_AD_Workflow.COLUMNNAME_AD_WorkflowProcessor_ID, true);
			filler.addUUID(I_AD_Workflow.COLUMNNAME_AD_WorkflowProcessor_ID, getUUIDFromId(workflow.getCtx(), I_AD_WorkflowProcessor.Table_Name, workflow.getAD_WorkflowProcessor_ID()));
		}
		//	Resource
		if (workflow.getS_Resource_ID() > 0) {
			filler.add(I_AD_Workflow.COLUMNNAME_S_Resource_ID, true);
			filler.addUUID(I_AD_Workflow.COLUMNNAME_S_Resource_ID, getUUIDFromId(workflow.getCtx(), I_S_Resource.Table_Name, workflow.getS_Resource_ID()));
		}
		//	Standard Attributes
		filler.add(I_AD_Workflow.COLUMNNAME_Value);
		filler.add(I_AD_Workflow.COLUMNNAME_Name);
		filler.add(I_AD_Workflow.COLUMNNAME_Description);
		filler.add(I_AD_Workflow.COLUMNNAME_Help);
		filler.add(I_AD_Workflow.COLUMNNAME_AccessLevel);
		filler.add(I_AD_Workflow.COLUMNNAME_Author);
		filler.add(I_AD_Workflow.COLUMNNAME_Cost);
		filler.add(I_AD_Workflow.COLUMNNAME_DocumentNo);
		filler.add(I_AD_Workflow.COLUMNNAME_DocValueLogic);
		filler.add(I_AD_Workflow.COLUMNNAME_Duration);
		filler.add(I_AD_Workflow.COLUMNNAME_DurationUnit);
		filler.add(I_AD_Workflow.COLUMNNAME_EntityType);
		filler.add(I_AD_Workflow.COLUMNNAME_IsActive);
		filler.add(I_AD_Workflow.COLUMNNAME_IsBetaFunctionality);
		filler.add(I_AD_Workflow.COLUMNNAME_IsDefault);
		filler.add(I_AD_Workflow.COLUMNNAME_IsValid);
		filler.add(I_AD_Workflow.COLUMNNAME_Limit);
		filler.add(I_AD_Workflow.COLUMNNAME_MovingTime);
		filler.add(I_AD_Workflow.COLUMNNAME_OverlapUnits);
		filler.add(I_AD_Workflow.COLUMNNAME_Priority);
		filler.add(I_AD_Workflow.COLUMNNAME_ProcessType);
		filler.add(I_AD_Workflow.COLUMNNAME_PublishStatus);
		filler.add(I_AD_Workflow.COLUMNNAME_QtyBatchSize);
		filler.add(I_AD_Workflow.COLUMNNAME_QueuingTime);
		filler.add(I_AD_Workflow.COLUMNNAME_SetupTime);
		filler.add(I_AD_Workflow.COLUMNNAME_UnitsCycles);
		filler.add(I_AD_Workflow.COLUMNNAME_ValidFrom);
		filler.add(I_AD_Workflow.COLUMNNAME_ValidTo);
		filler.add(I_AD_Workflow.COLUMNNAME_Version);
		filler.add(I_AD_Workflow.COLUMNNAME_WaitingTime);
		filler.add(I_AD_Workflow.COLUMNNAME_WorkflowType);
		filler.add(I_AD_Workflow.COLUMNNAME_WorkingTime);
		filler.add(I_AD_Workflow.COLUMNNAME_Yield);
		return atts;
	}
}
