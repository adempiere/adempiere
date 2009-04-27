/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package test.functional;

import org.compiere.model.MRequisition;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.wf.MWFActivity;
import org.compiere.wf.MWFEventAudit;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFNodeNext;
import org.compiere.wf.MWFNodePara;
import org.compiere.wf.MWFProcess;
import org.compiere.wf.MWorkflow;
import org.compiere.wf.MWorkflowProcessor;

import test.AdempiereTestCase;

/**
 * Test Workflow related classes
 * @author Teo Sarca, www.arhipac.ro
 */
public class WorkflowTest extends AdempiereTestCase
{
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	public void testQuery() throws Exception
	{
		//
		// Check MWFActivity
		int AD_Table_ID = MRequisition.Table_ID;
		int Record_ID = 1; // dummy;
		MWFActivity.get(getCtx(), AD_Table_ID, Record_ID, false);
		MWFActivity.get(getCtx(), AD_Table_ID, Record_ID, true);
		//
		// Check MWFEventAudit
		int AD_WF_Process_ID = 1; // dummy
		int AD_WF_Node_ID = 1; // dummy
		MWFEventAudit.get(getCtx(), AD_WF_Process_ID, AD_WF_Node_ID, getTrxName());
		MWFEventAudit.get(getCtx(), AD_WF_Process_ID, getTrxName());
		//
		// Check MWFProcess 
		MWFProcess proc = new Query(getCtx(), MWFProcess.Table_Name, null, getTrxName())
							.setClient_ID()
							.setOrderBy(MWFProcess.COLUMNNAME_AD_WF_Process_ID)
							.first();
		if (proc != null)
		{
			proc.getActivities(true, false, getTrxName());
			proc.getActivities(true, true, getTrxName());
		}
		else
		{
			// TODO: check MWFProcess - need better test
		}
		//
		// Check MWorkflow, MWFNode, MWFNodeNext etc
		int AD_Client_ID = getAD_Client_ID();
		int AD_Workflow_ID = 115; // Process_Requisition
		MWorkflow wf = MWorkflow.get(getCtx(), AD_Workflow_ID);
		for (MWFNode node : wf.getNodes(false, AD_Client_ID))
		{
			MWFNodePara.getParameters(node.getCtx(), node.getAD_WF_Node_ID());
			
			for (MWFNodeNext next : node.getTransitions(AD_Client_ID))
			{
				next.getConditions(true);
				next.getConditions(false);
			}
		}
		//
		// Check MWorkflowProcessor
		for (MWorkflowProcessor processor : MWorkflowProcessor.getActive(getCtx()))
		{
			processor.getLogs();
		}
		//
		//
	}
}
