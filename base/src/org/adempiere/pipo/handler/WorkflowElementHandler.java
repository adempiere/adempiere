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
 *                                                                            *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com                     *
 * Contributor(s): Low Heng Sin hengsin@avantz.com                            *
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.core.domains.models.I_AD_WF_NextCondition;
import org.adempiere.core.domains.models.I_AD_WF_Node;
import org.adempiere.core.domains.models.I_AD_WF_NodeNext;
import org.adempiere.core.domains.models.X_AD_Package_Exp_Detail;
import org.adempiere.pipo.PackOut;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.wf.MWFNextCondition;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFNodeNext;
import org.compiere.wf.MWorkflow;
import org.xml.sax.SAXException;

/**
 * Change to Generic PO handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class WorkflowElementHandler extends GenericPOHandler {
	@Override
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int workflowId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Workflow_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		List<String> tableList = new ArrayList<String>();
		tableList.add(I_AD_WF_Node.Table_Name);
		//	Get
		MWorkflow workflow = MWorkflow.get(ctx, workflowId);
		//	Workflow
		packOut.createGenericPO(document, workflow, true, tableList);
		//	
		MWFNode[] nodes = workflow.getNodes(true, Env.getAD_Client_ID(ctx));
		for(MWFNode node : nodes) {
			//	Create Process
			if(node.getAD_Process_ID() > 0) {
				packOut.createProcess(node.getAD_Process_ID(), document);
			}
			//	Create Window
			if(node.getAD_Window_ID() > 0) {
				packOut.createWindow(node.getAD_Window_ID(), document);
			}
			//	Create Browser
			if(node.getAD_Browse_ID() > 0) {
				packOut.createBrowse(node.getAD_Browse_ID(), document);
			}
			//	Create From
			if(node.getAD_Form_ID() > 0) {
				packOut.createForm(node.getAD_Form_ID(), document);
			}
			//	Task
			if(node.getAD_Task_ID() > 0) {
				packOut.createTask(node.getAD_Task_ID(), document);
			}
			//	Create View
			if(node.getAD_View_ID() > 0) {
				packOut.createView(node.getAD_View_ID(), document);
			}
			packOut.createGenericPO(document, node);
		}
		//	Export conditions
		for(MWFNode node : nodes) {
			//	for next nodes
			List<MWFNodeNext> nextNodeList = new Query(ctx, I_AD_WF_NodeNext.Table_Name, I_AD_WF_NodeNext.COLUMNNAME_AD_WF_Node_ID + " = ?", null)
				.setParameters(node.getAD_WF_Node_ID())
				.setClient_ID()
				.<MWFNodeNext>list();
			//	
			for(MWFNodeNext nextNode : nextNodeList) {
				packOut.createGenericPO(document, nextNode);
				//	for conditions
				List<MWFNextCondition> nextConditionList = new Query(ctx, I_AD_WF_NextCondition.Table_Name, I_AD_WF_NextCondition.COLUMNNAME_AD_WF_NodeNext_ID + " = ?", null)
					.setParameters(nextNode.getAD_WF_NodeNext_ID())
					.setClient_ID()
					.<MWFNextCondition>list();
				//	
				for(MWFNextCondition nextCondition : nextConditionList) {
					packOut.createGenericPO(document, nextCondition);
				}
			}
		}
	}
	
	@Override
	protected void beforeSave(PO entity) {
		entity.set_ValueOfColumn("AD_WF_Node_ID", null);
	}
}
