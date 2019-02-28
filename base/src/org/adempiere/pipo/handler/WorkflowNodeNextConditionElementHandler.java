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
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_WF_NextCondition;
import org.compiere.model.I_AD_WF_Node;
import org.compiere.model.I_AD_WF_NodeNext;
import org.compiere.model.I_AD_Workflow;
import org.compiere.model.X_AD_WF_NextCondition;
import org.compiere.util.Env;
import org.compiere.wf.MWFNextCondition;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class WorkflowNodeNextConditionElementHandler extends
		AbstractElementHandler {

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		Attributes atts = element.attributes;
		String uuid = getUUIDValue(atts, I_AD_WF_NextCondition.Table_Name);
		log.info(uuid);
		String entitytype = getStringValue(atts, I_AD_WF_NextCondition.COLUMNNAME_EntityType);
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
			//	
			String workflowNodeUuid = getUUIDValue(atts, I_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID);
			String workflowNodeNextUuid = getUUIDValue(atts, I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NodeNext_ID);

			int wfNodeId = getIdFromUUID(ctx, I_AD_WF_Node.Table_Name, workflowNodeUuid);
			if (wfNodeId <= 0) {
				element.unresolved = "AD_WF_Node=" + workflowNodeUuid;
				element.defer = true;
				return;
			}
			
			int wfNodeNextId = getIdFromUUID(ctx, I_AD_WF_NodeNext.Table_Name, workflowNodeNextUuid);
			if (wfNodeNextId <= 0) {
				element.unresolved = "AD_WF_Node=" + workflowNodeNextUuid;
				element.defer = true;
				return;
            }
			//	
			int id = getIdFromUUID(ctx, I_AD_WF_NextCondition.Table_Name, uuid);
			MWFNextCondition wFNodeNextCondition = new MWFNextCondition(ctx, id, getTrxName(ctx));
			int backupId = -1;
			String objectStatus = null;
			if (id <= 0 && getIntValue(atts, I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NextCondition_ID) > 0 && getIntValue(atts, I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NextCondition_ID) <= PackOut.MAX_OFFICIAL_ID) {
				wFNodeNextCondition.setAD_WF_NextCondition_ID(getIntValue(atts, I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NextCondition_ID));
				wFNodeNextCondition.setIsDirectLoad(true);
			}
			if (id > 0) {
				backupId = copyRecord(ctx, "AD_WF_NextCondition",
						wFNodeNextCondition);
				objectStatus = "Update";
			} else {
				objectStatus = "New";
				backupId = 0;
			}
			wFNodeNextCondition.setUUID(uuid);
			//	Standard Attributes
			wFNodeNextCondition.setAndOr(getStringValue(atts, I_AD_WF_NextCondition.COLUMNNAME_AndOr));
			wFNodeNextCondition.setEntityType(getStringValue(atts, I_AD_WF_NextCondition.COLUMNNAME_EntityType));
			wFNodeNextCondition.setIsActive(getBooleanValue(atts, I_AD_WF_NextCondition.COLUMNNAME_IsActive));
			wFNodeNextCondition.setOperation(getStringValue(atts, I_AD_WF_NextCondition.COLUMNNAME_Operation));
			wFNodeNextCondition.setSeqNo(getIntValue(atts, I_AD_WF_NextCondition.COLUMNNAME_SeqNo));
			wFNodeNextCondition.setValue(getStringValue(atts, I_AD_WF_NextCondition.COLUMNNAME_Value));
			wFNodeNextCondition.setValue2(getStringValue(atts, I_AD_WF_NextCondition.COLUMNNAME_Value2));
			//	Save
			try {
				wFNodeNextCondition.saveEx(getTrxName(ctx));
				log.info("m_WFNodeNextCondition save success");
				recordLog(
						ctx,
						1,
						wFNodeNextCondition.getUUID(),
						"WFNextCondition",
						wFNodeNextCondition.get_ID(),
						backupId,
						objectStatus,
						"AD_WF_NextCondition",
						get_IDWithColumn(ctx, "AD_Table",
								"TableName", "AD_WF_NextCondition"));
			} catch (Exception e) {
				log.info("m_WFNodeNextCondition save failure");
				recordLog(
						ctx,
						0,
						wFNodeNextCondition.getUUID(),
						"WFNextCondition",
						wFNodeNextCondition.get_ID(),
						backupId,
						objectStatus,
						"AD_WF_NextCondition",
						get_IDWithColumn(ctx, "AD_Table",
								"TableName", "AD_WF_NextCondition"));
				throw new POSaveFailedException(e);
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int nodenextconditionId = Env.getContextAsInt(ctx, X_AD_WF_NextCondition.COLUMNNAME_AD_WF_NextCondition_ID);
		X_AD_WF_NextCondition nodeNextCondition = new X_AD_WF_NextCondition(ctx, nodenextconditionId, null);
		AttributesImpl atts = new AttributesImpl();
		createWorkflowNodeNextConditionBinding(atts, nodeNextCondition);
		document.startElement("", "", "workflowNodeNextCondition", atts);
		document.endElement("", "", "workflowNodeNextCondition");
	}

	private AttributesImpl createWorkflowNodeNextConditionBinding(AttributesImpl atts, X_AD_WF_NextCondition nodeNextCondition) {
		atts.clear();
		AttributeFiller filler = new AttributeFiller(atts, nodeNextCondition);
		if (nodeNextCondition.getAD_WF_NextCondition_ID() <= PackOut.MAX_OFFICIAL_ID) {
			filler.add(I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NextCondition_ID);
		}
		filler.addUUID();
		//	Next Node
		if (nodeNextCondition.getAD_WF_NodeNext_ID() > 0) {
			filler.add(I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NodeNext_ID, true);
			filler.addUUID(I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NodeNext_ID, getUUIDFromId(nodeNextCondition.getCtx(), I_AD_WF_NodeNext.Table_Name, nodeNextCondition.getAD_WF_NodeNext_ID()));
			nodeNextCondition.getAD_WF_NodeNext().getAD_WF_Node().getUUID();
			//	Node
			filler.addUUID(I_AD_WF_Node.COLUMNNAME_AD_WF_Node_ID, nodeNextCondition.getAD_WF_NodeNext().getAD_WF_Node().getUUID());
			//	Workflow
			filler.addUUID(I_AD_Workflow.COLUMNNAME_AD_Workflow_ID, nodeNextCondition.getAD_WF_NodeNext().getAD_WF_Node().getAD_Workflow().getUUID());
		}
		//	Column
		if (nodeNextCondition.getAD_Column_ID() > 0) {
			filler.add(I_AD_WF_NextCondition.COLUMNNAME_AD_Column_ID, true);
			filler.addUUID(I_AD_WF_NextCondition.COLUMNNAME_AD_Column_ID, getUUIDFromId(nodeNextCondition.getCtx(), I_AD_Column.Table_Name, nodeNextCondition.getAD_Column_ID()));
		}
		//Add Standard Attributes
		filler.add(I_AD_WF_NextCondition.COLUMNNAME_AndOr);
		filler.add(I_AD_WF_NextCondition.COLUMNNAME_EntityType);
		filler.add(I_AD_WF_NextCondition.COLUMNNAME_IsActive);
		filler.add(I_AD_WF_NextCondition.COLUMNNAME_Operation);
		filler.add(I_AD_WF_NextCondition.COLUMNNAME_SeqNo);
		filler.add(I_AD_WF_NextCondition.COLUMNNAME_Value);
		filler.add(I_AD_WF_NextCondition.COLUMNNAME_Value2);
		return atts;
	}

}
