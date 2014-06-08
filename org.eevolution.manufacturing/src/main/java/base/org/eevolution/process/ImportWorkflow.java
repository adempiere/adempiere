/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;

import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MOrg;
import org.compiere.model.MResource;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFNodeNext;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.X_I_Product_BOM;
import org.eevolution.model.X_I_Workflow;

/**
 * @author Alberto Juarez C, alberto.juarez@e-evolution.com, www.e-evolution.com
 * @author victor.perez@e-evolution.com,www.e-evolution.com
 */

public class ImportWorkflow extends SvrProcess {

	private boolean m_DeleteOldImported = false;
	private boolean m_IsImportOnlyNoErrors = true;
	private int imported = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] parameters = getParameter();
		for (ProcessInfoParameter para : parameters) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("IsImportOnlyNoErrors"))
				m_IsImportOnlyNoErrors = "Y".equals(para.getParameter());
			else if (name.equals("DeleteOldImported"))
				m_DeleteOldImported = "Y".equals(para.getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		if (m_DeleteOldImported) {
			int no = 0;
			for (X_I_Workflow importWorkflow : getRecords(true, false)) {
				importWorkflow.deleteEx(true);
				no++;
			}
			log.fine("Delete Old Impored =" + no);
		}

		fillIDValues();
		importRecords();
		return "Imported: " + imported;
	}

	/**
	 * fill IDs values based on Search Key
	 */
	public void fillIDValues() {
		for (X_I_Workflow importWorkflow : getRecords(false, false)) {
			int AD_Org_ID = getID(MOrg.Table_Name, "Value = ?",
					new Object[] { importWorkflow.getOrgValue() });
			if (AD_Org_ID > 0)
				importWorkflow.setAD_Org_ID(AD_Org_ID);

			int S_Resource_ID = getID(MResource.Table_Name, "Value=?",
					new Object[] { importWorkflow.getResourceValue() });
			if (S_Resource_ID > 0)
				importWorkflow.setS_Resource_ID(S_Resource_ID);

			String errorMsg = "";

			if (MWorkflow.WORKFLOWTYPE_Manufacturing.equals(importWorkflow
					.getWorkflowType())) {
				if (importWorkflow.getS_Resource_ID() <= 0) {
					errorMsg += "@S_Resource_ID@ @NotFound@, ";
				}
			}

			if (importWorkflow.getDocumentNo() == null)
				errorMsg += "@DocumentNo@ @NotFound@, ";
			if (importWorkflow.getName() == null)
				errorMsg += "@Name@ @NotFound@, ";
			if (importWorkflow.getAccessLevel() == null)
				errorMsg += "@AccessLevel@ @NotFound@, ";
			if (importWorkflow.getAuthor() == null)
				errorMsg += "@Author@ @NotFound@, ";
			if (importWorkflow.getAuthor() == null)
				errorMsg += "@Author@ @NotFound@, ";
			if (importWorkflow.getEntityType() == null)
				errorMsg += "@EntityType@ @NotFound@, ";
			if (importWorkflow.getPublishStatus() == null)
				errorMsg += "@PublishStatus@ @NotFound@, ";
			if (importWorkflow.getValue() == null)
				errorMsg += "@Value@ @NotFound@, ";
			if (importWorkflow.getVersion() <= 0)
				errorMsg += "@Version@ @NotFound@, ";
			if (importWorkflow.getWorkflowType() == null)
				errorMsg += "@WorkflowType@ @NotFound@, ";
			if (importWorkflow.getDurationUnit() == null)
				errorMsg += "@DurationUnit@ @NotFound@, ";

			if (errorMsg != null && errorMsg.length() > 0) {
				importWorkflow.setI_ErrorMsg(Msg.parseTranslation(getCtx(),
						errorMsg));
			}
			importWorkflow.saveEx();
		}
	}

	/**
	 * import records from I_Workflow to AD_Workflow table
	 */
	public void importRecords() {
		List<X_I_Workflow> iworkflows = getRecords(false,
				m_IsImportOnlyNoErrors);
		for (X_I_Workflow importWorkflow : iworkflows) {
			MWFNode node = null;

			MWorkflow workflow = getWorkflow(importWorkflow);
			if (workflow != null) {
				node = getWorkflowNode(importWorkflow, workflow);
			}

			if (node != null) {
				if (workflow.getAD_WF_Node_ID() <= 0) {
					workflow.setAD_WF_Node_ID(node.get_ID());
					workflow.saveEx();
				}

				importWorkflow.setAD_WF_Node_ID(node.get_ID());
				importWorkflow.setAD_Workflow_ID(workflow.get_ID());
				imported++;
			}

			importWorkflow.setI_IsImported(true);
			importWorkflow.setProcessed(true);
			importWorkflow.saveEx();
		}

		// create the transition
		for (X_I_Workflow importWorkflow : iworkflows) {
			MWFNode node = (MWFNode) importWorkflow.getAD_WF_Node();
			if (node != null) {
				int node_id = getID(MWFNode.Table_Name,
						"AD_Workflow_ID= ? AND Value=?", new Object[] {
								importWorkflow.getAD_Workflow_ID(),
								importWorkflow.getNodeNextValue() });
				if (node_id > 0) {
					MWFNode next = new MWFNode(getCtx(), node_id, get_TrxName());
					createTransition(node, next);
				}

			}
		}
	}

	/**
	 * Search custom AD_Workflow, if it does not exist, create it
	 * @param iWf
	 * @return custom AD_Workflow
	 */
	public MWorkflow getWorkflow(X_I_Workflow iWf) {
		MWorkflow workflow = new Query(getCtx(), MWorkflow.Table_Name,
				"Value=? AND Name=?", get_TrxName()).setClient_ID()
				.setParameters(iWf.getValue(), iWf.getName()).firstOnly();

		if (workflow == null) {
			workflow = new MWorkflow(getCtx(), 0, get_TrxName());
			workflow.setValue(iWf.getValue());
			workflow.setName(iWf.getName());
		}
		workflow.setIsBetaFunctionality(iWf.isBetaFunctionality());
		workflow.setWorkflowType(iWf.getWorkflowType());
		workflow.setAccessLevel(iWf.getAccessLevel());
		workflow.setValidFrom(iWf.getValidFrom());
		workflow.setValidTo(iWf.getValidTo());
		workflow.setPublishStatus(iWf.getPublishStatus());
		workflow.setVersion(iWf.getVersion());
		workflow.setAuthor(iWf.getAuthor());
		workflow.setIsDefault(iWf.isDefault());
		workflow.setAD_WorkflowProcessor_ID(iWf.getAD_WorkflowProcessor_ID());
		workflow.setDurationUnit(iWf.getDurationUnit());
		workflow.setAD_Table_ID(iWf.getAD_Table_ID());
		workflow.setDocValueLogic(iWf.getDocValueLogic());
		workflow.setEntityType(iWf.getEntityType());
		// Workflow Manufacturing
		workflow.setProcessType(iWf.getProcessType());
		workflow.setS_Resource_ID(iWf.getS_Resource_ID());
		workflow.setDocumentNo(iWf.getDocumentNo());
		workflow.setMovingTime(iWf.getMovingTime());
		workflow.setUnitsCycles(iWf.getUnitsCycles());
		workflow.setQtyBatchSize(iWf.getQtyBatchSize());
		// workflow.setOverlapUnits(iWf.getOverlapUnits());
		workflow.setQueuingTime(iWf.getQueuingTime());
		workflow.setSetupTime(iWf.getSetupTime());
		workflow.setYield(iWf.getYield());
		workflow.saveEx();
		return workflow;

	}

	/**
	 * Search custom AD_WF_Node, if it does not exist, create it
	 * 
	 * @param iWf
	 *            , wf
	 * @return custom AD_WF_Node
	 */
	private MWFNode getWorkflowNode(X_I_Workflow importWorkflow,
			MWorkflow workflow) {
		final String whereClause = MWFNode.COLUMNNAME_AD_Workflow_ID
				+ "=? AND " + MWFNode.COLUMNNAME_Value + "=? AND "
				+ MWFNode.COLUMNNAME_Name + "=?";

		MWFNode node = new Query(getCtx(), MWFNode.Table_Name, whereClause,
				get_TrxName())
				.setClient_ID()
				.setParameters(workflow.get_ID(),
						importWorkflow.getNodeValue(),
						importWorkflow.getNodeName()).first();

		if (node == null) {
			node = new MWFNode(getCtx(), 0, get_TrxName());
			node.setValue(importWorkflow.getNodeValue());
			node.setName(importWorkflow.getNodeName());
		}

		node.setDescription(importWorkflow.getDescription());
		node.setIsCentrallyMaintained(importWorkflow.isCentrallyMaintained());
		node.setStartMode(importWorkflow.getStartMode());
		node.setFinishMode(importWorkflow.getFinishMode());
		node.setJoinElement(importWorkflow.getJoinElement());
		node.setSplitElement(importWorkflow.getSplitElement());
		node.setAction(importWorkflow.getAction());
		node.setAD_Image_ID(importWorkflow.getAD_Image_ID());
		node.setDocAction(importWorkflow.getDocAction());
		node.setDynPriorityUnit(importWorkflow.getDynPriorityUnit());
		node.setDynPriorityChange(importWorkflow.getDynPriorityChange());
		node.setAD_WF_Responsible_ID(importWorkflow.getAD_WF_Responsible_ID());
		node.setPriority(importWorkflow.getPriority());
		node.setLimit(importWorkflow.getLimit());
		node.setDuration(importWorkflow.getDuration());
		node.setCost(importWorkflow.getCost());
		node.setWorkingTime(importWorkflow.getWorkingTime());
		node.setWaitingTime(importWorkflow.getWaitingTime());
		node.setEntityType(importWorkflow.getEntityType());
		node.setAD_Column_ID(importWorkflow.getAD_Column_ID());
		node.setAttributeName(importWorkflow.getAttributeName());
		node.setAttributeValue(importWorkflow.getAttributeValue());
		node.setEMailRecipient(importWorkflow.getEMailRecipient());
		node.setEMail(importWorkflow.getEMail());
		node.setSubflowExecution(importWorkflow.getSubflowExecution());
		node.setAD_Process_ID(importWorkflow.getAD_Process_ID());
		node.setAD_Window_ID(importWorkflow.getAD_Window_ID());
		node.setAD_Form_ID(importWorkflow.getAD_Form_ID());
		node.setR_MailText_ID(importWorkflow.getR_MailText_ID());
		node.setAD_Task_ID(importWorkflow.getAD_Task_ID());
		node.setWaitTime(importWorkflow.getWaitTime());
		node.setAD_Workflow_ID(workflow.get_ID());
		// Manufacturing
		node.setIsMilestone(importWorkflow.isMilestone());
		node.setIsSubcontracting(importWorkflow.isSubcontracting());
		node.setMovingTime(importWorkflow.getMovingTime());
		node.setOverlapUnits(importWorkflow.getOverlapUnits());
		node.setQueuingTime(importWorkflow.getQueuingTime());
		node.setS_Resource_ID(importWorkflow.getS_Resource_ID());
		node.setSetupTime(importWorkflow.getSetupTime());
		node.setUnitsCycles(importWorkflow.getUnitsCycles());
		node.setValidFrom(importWorkflow.getValidFrom());
		node.setValidTo(importWorkflow.getValidTo());
		node.setYield(importWorkflow.getYield());
		node.saveEx();
		return node;
	}

	/**
	 * Search custom AD_WF_NodeNext, if it does not exist, create it
	 * @param first node, second node
	 * @return custom AD_WF_NodeNext
	 */
	private boolean createTransition(MWFNode source, MWFNode target) {
		if (source == null)
			return true;

		final String whereClause = MWFNodeNext.COLUMNNAME_AD_WF_Node_ID + "=?";

		MWFNodeNext transition = new Query(getCtx(), MWFNodeNext.Table_Name,
				whereClause, get_TrxName()).setClient_ID()
				.setParameters(target.get_ID()).first();

		if (transition == null) {
			transition = new MWFNodeNext(getCtx(), 0, get_TrxName());
		}
		transition.setAD_WF_Node_ID(source.get_ID());
		transition.setAD_WF_Next_ID(target.get_ID());
		transition.setEntityType(source.getEntityType());
		transition.saveEx();
		return true;
	}

	/**
	 * get a record's ID
	 * @param tableName String
	 * @param whereClause String
	 * @param values Object[]
	 * @return unique record's ID in the table
	 */
	private int getID(String tableName, String whereClause, Object[] values) {
		return new Query(getCtx(), tableName, whereClause, get_TrxName())
				.setClient_ID().setParameters(values).firstId();
	}

	/**
	 * get all records in X_I_ProductPlanning table
	 * @param imported boolean
	 * @param isWithError boolean
	 * @return List of X_I_ProductPlanning records
	 */
	private List<X_I_Workflow> getRecords(boolean imported, boolean isWithError) {
		final StringBuffer whereClause = new StringBuffer(
				X_I_Product_BOM.COLUMNNAME_I_IsImported).append("=?");

		if (isWithError) {
			whereClause.append(" AND ")
					.append(X_I_Product_BOM.COLUMNNAME_I_ErrorMsg)
					.append(" IS NULL");
		}
		return new Query(getCtx(), X_I_Workflow.Table_Name,
				whereClause.toString(), get_TrxName()).setClient_ID()
				.setParameters(imported).setOrderBy("Created").list();
	}
}
