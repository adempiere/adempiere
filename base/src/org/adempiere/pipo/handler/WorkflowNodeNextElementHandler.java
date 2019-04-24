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

import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.I_AD_WF_Node;
import org.compiere.model.I_AD_WF_NodeNext;
import org.compiere.model.I_AD_Workflow;
import org.compiere.model.X_AD_WF_NodeNext;
import org.compiere.util.Env;
import org.compiere.wf.MWFNodeNext;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class WorkflowNodeNextElementHandler extends AbstractElementHandler {

	public void startElement(Properties ctx, Element element) throws SAXException {
		Attributes atts = element.attributes;
		String elementValue = element.getElementValue();
		String uuid = getUUIDValue(atts, I_AD_WF_NodeNext.Table_Name);
		log.info(elementValue + " " + uuid);
		String entitytype = getStringValue(atts, I_AD_WF_NodeNext.COLUMNNAME_EntityType);		
		if (isProcessElement(ctx, entitytype)) {
			if (element.parent != null && element.parent.skip) {
				element.skip = true;
				return;
			}

			String workflowUuid = getUUIDValue(atts, I_AD_Workflow.COLUMNNAME_AD_Workflow_ID);
			int workflowId = getIdFromUUID(ctx, I_AD_Workflow.Table_Name, workflowUuid);
			if (workflowId <= 0) {
				element.defer = true;
				element.unresolved = "AD_Workflow: " + workflowUuid;
				return;
			}

			String workflowNodeUuid = getUUIDValue(atts, I_AD_WF_NodeNext.COLUMNNAME_AD_WF_Node_ID);
			String workflowNodeNextUuid = getUUIDValue(atts, I_AD_WF_NodeNext.COLUMNNAME_AD_WF_Next_ID);
			
			int wfNodeId = getIdFromUUID(ctx, I_AD_WF_Node.Table_Name, workflowNodeUuid);
			if (wfNodeId <= 0) {
				element.defer = true;
				element.unresolved = "AD_WF_Node: " + workflowNodeUuid;
				return;
			}

			int wfNodeNextId = getIdFromUUID(ctx, I_AD_WF_Node.Table_Name, workflowNodeNextUuid);
			if (wfNodeNextId <= 0) {
				element.defer = true;
				element.unresolved = "AD_WF_Node: " + workflowNodeNextUuid;
				return;
			}
			int id = getIdFromUUID(ctx, I_AD_WF_NodeNext.Table_Name, uuid);
			MWFNodeNext nodeNext = new MWFNodeNext(ctx, id, getTrxName(ctx));
			int backupId = -1;
			String objectStatus = null;
			if (id <= 0 && getIntValue(atts, I_AD_WF_NodeNext.COLUMNNAME_AD_WF_NodeNext_ID) > 0 && getIntValue(atts, I_AD_WF_NodeNext.COLUMNNAME_AD_WF_NodeNext_ID) <= PackOut.MAX_OFFICIAL_ID) {
				nodeNext.setAD_WF_NodeNext_ID(getIntValue(atts, I_AD_WF_NodeNext.COLUMNNAME_AD_WF_NodeNext_ID));
				nodeNext.setIsDirectLoad(true);
			}
			if (id > 0){		
				backupId = copyRecord(ctx, "AD_WF_NodeNext",nodeNext);
				objectStatus = "Update";			
			}
			else{
				objectStatus = "New";
				backupId =0;
			}
			nodeNext.setUUID(uuid);
			nodeNext.setAD_WF_Node_ID(wfNodeId);
			nodeNext.setAD_WF_Next_ID(wfNodeNextId);
			//	Standard Attributes
			nodeNext.setDescription(getStringValue(atts, I_AD_WF_NodeNext.COLUMNNAME_Description));
			nodeNext.setEntityType(getStringValue(atts, I_AD_WF_NodeNext.COLUMNNAME_EntityType));
			nodeNext.setIsActive(getBooleanValue(atts, I_AD_WF_NodeNext.COLUMNNAME_IsActive));
			nodeNext.setIsStdUserWorkflow(getBooleanValue(atts, I_AD_WF_NodeNext.COLUMNNAME_IsStdUserWorkflow));
			nodeNext.setSeqNo(getIntValue(atts, I_AD_WF_NodeNext.COLUMNNAME_SeqNo));
			nodeNext.setTransitionCode(getStringValue(atts, I_AD_WF_NodeNext.COLUMNNAME_TransitionCode));
			//	Save
			try {
				nodeNext.saveEx(getTrxName(ctx));
				log.info("m_WFNodeNext save success");
				recordLog (ctx, 1, nodeNext.getUUID(),"WFNodeNext", nodeNext.get_ID(),backupId, objectStatus,"AD_WF_NodeNext",
							get_IDWithColumn(ctx, "AD_Table", "TableName", "AD_WF_NodeNext"));
			} catch (Exception e) {
				log.info("m_WFNodeNext save failure");
				recordLog (ctx, 0, nodeNext.getUUID(),"WFNodeNext", nodeNext.get_ID(),backupId, objectStatus,"AD_WF_NodeNext",
							get_IDWithColumn(ctx, "AD_Table", "TableName", "AD_WF_NodeNext"));
				throw new POSaveFailedException(e);
			}         
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}

	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int nodenextId = Env.getContextAsInt(ctx, X_AD_WF_NodeNext.COLUMNNAME_AD_WF_NodeNext_ID);
		X_AD_WF_NodeNext m_WF_NodeNext = new X_AD_WF_NodeNext(ctx, nodenextId, null);
		AttributesImpl atts = new AttributesImpl();
		createWorkflowNodeNextBinding(atts, m_WF_NodeNext);
		document.startElement("", "", "workflowNodeNext", atts);
		document.endElement("", "", "workflowNodeNext");
		
	}

	private AttributesImpl createWorkflowNodeNextBinding(AttributesImpl atts, X_AD_WF_NodeNext nodeNext) {
		atts.clear();
		AttributeFiller filler = new AttributeFiller(atts, nodeNext);
		if (nodeNext.getAD_WF_NodeNext_ID() <= PackOut.MAX_OFFICIAL_ID) {
			filler.add(I_AD_WF_NodeNext.COLUMNNAME_AD_WF_NodeNext_ID);
		}
		filler.addUUID();
		//	Node
		if (nodeNext.getAD_WF_Node_ID() > 0) {
			filler.add(I_AD_WF_NodeNext.COLUMNNAME_AD_WF_Node_ID, true);
			filler.addUUID(I_AD_WF_NodeNext.COLUMNNAME_AD_WF_Node_ID, getUUIDFromId(nodeNext.getCtx(), I_AD_WF_Node.Table_Name, nodeNext.getAD_WF_Node_ID()));
			//	Workflow
			filler.addUUID(I_AD_Workflow.COLUMNNAME_AD_Workflow_ID, nodeNext.getAD_WF_Node().getAD_Workflow().getUUID());
		}
		//	Next
		if (nodeNext.getAD_WF_Next_ID() > 0) {
			filler.add(I_AD_WF_NodeNext.COLUMNNAME_AD_WF_Next_ID, true);
			filler.addUUID(I_AD_WF_NodeNext.COLUMNNAME_AD_WF_Next_ID, getUUIDFromId(nodeNext.getCtx(), I_AD_WF_Node.Table_Name, nodeNext.getAD_WF_Next_ID()));
		}
		//	Standard Attributes
		filler.add(I_AD_WF_NodeNext.COLUMNNAME_Description);
		filler.add(I_AD_WF_NodeNext.COLUMNNAME_EntityType);
		filler.add(I_AD_WF_NodeNext.COLUMNNAME_IsActive);
		filler.add(I_AD_WF_NodeNext.COLUMNNAME_IsStdUserWorkflow);
		filler.add(I_AD_WF_NodeNext.COLUMNNAME_SeqNo);
		filler.add(I_AD_WF_NodeNext.COLUMNNAME_TransitionCode);
		//	
		return atts;
	}
}
