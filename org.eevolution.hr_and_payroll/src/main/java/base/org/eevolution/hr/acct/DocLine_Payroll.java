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
package org.eevolution.hr.acct;

import java.math.BigDecimal;

import org.compiere.acct.DocLine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.hr.model.MHRConcept;
import org.eevolution.hr.model.MHRMovement;
import org.eevolution.hr.model.X_HR_Concept_Acct;

/**
 *  Payroll Line
 *
 *  @author Jorg Janke
 *  @version  $Id: DocLine_Payroll.java,v 1.4 2005/10/17 23:43:52 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1030">
 * 		@see FR [ 1030 ] Posting Error on payroll process</a>
 */
public class DocLine_Payroll extends DocLine
{
	/**
	 *  Constructor
	 *  @param movement Payroll line
	 *  @param doc header
	 */
	public DocLine_Payroll (MHRMovement movement, Doc_HRProcess doc) {
		super (movement, doc);
		int C_BPartner_ID = movement.getC_BPartner_ID();
		MHRConcept concept = MHRConcept.getById(Env.getCtx(), movement.getHR_Concept_ID() , getTrxName());
		//
		conceptId = concept.getHR_Concept_ID();
		processId = movement.getHR_Process_ID();
		accountSign = concept.getAccountSign();
		amount = movement.getAmount();
		//	Reference
		bPartnerId = C_BPartner_ID;
		departmentId = movement.getHR_Department_ID();
		payrollId = movement.getHR_Payroll_ID();
		bPGroupId = movement.getC_BP_Group_ID();
		if(bPGroupId == 0) {
			bPGroupId = DB.getSQLValue(doc.getTrxName(), 
					"SELECT C_BP_Group_ID "
					+ "FROM C_BPartner "
					+ "WHERE C_BPartner_ID=?", C_BPartner_ID);
		}
		orgId = movement.getAD_Org_ID();
		orgTrxId = movement.getAD_OrgTrx_ID();
		activityId = movement.getC_Activity_ID();
		projectId = movement.getC_Project_ID();
		projectPhaseId = movement.getC_ProjectPhase_ID();
		projectTaskId = movement.getC_ProjectTask_ID();
		user1Id = movement.getUser1_ID();
		user2Id = movement.getUser2_ID();
		user3Id = movement.getUser3_ID();
		user4Id = movement.getUser4_ID();
		//	
		setAmount(movement.getAmount());
	}   //  DocLine_Payroll

	//  References
	private int processId  = 0;
	private int conceptId  = 0;
	private int bPartnerId  = 0;
	private int orgId = 0;
	private int orgTrxId = 0;
	private int activityId  = 0;
	private int projectId  = 0;
	private int projectPhaseId  = 0;
	private int projectTaskId  = 0;
	private int campaignId  = 0;
	private int user1Id  = 0;
	private int user2Id  = 0;
	private int user3Id  = 0;
	private int user4Id  = 0;
	private String accountSign = "";
	private BigDecimal amount  = Env.ZERO;
	private int departmentId = 0;
	private int bPGroupId = 0;
	private int payrollId = 0;
	
	public int getHR_Process_ID(){
		return processId;
	}
	
	public int getHR_Concept_ID(){
		return conceptId;
	}
	
	public String getAccountSign(){
		return accountSign;
	}
	
	public int getC_BPartner_ID(){
		return bPartnerId;
	}
	
	public int getC_Activity_ID(){
		return activityId;
	}
	
	public int getC_Project_ID() {
		return projectId;
	}
	
	public int getC_ProjectPhase_ID() {
		return projectPhaseId;
	}

	public int getC_ProjectTask_ID() {
		return projectTaskId;
	}
	
	public int getC_Campaign_ID() {
		return campaignId;
	}
	
	public int getUser1_ID() {
		return user1Id;
	}
	
	public int getUser2_ID() {
		return user2Id;
	}
	
	public int getUser3_ID() {
		return user3Id;
	}
	
	public int getUser4_ID() {
		return user4Id;
	}
	
	public int getAD_Org_ID() {
		return orgId;
	}
	
	public int getAD_OrgTrx_ID() {
		return orgTrxId;
	}
	
	public BigDecimal getAmount()	{
		return amount;
	}

	public int getHR_Department_ID()	{
		return departmentId;
	}

	public int getC_BP_Group_ID()	{
		return bPGroupId;
	}

	public int getHR_Payroll_ID()	{
		return payrollId;
	}

	public Integer getAccountSchemaId()
	{
		return getC_AcctSchema_ID() > 0 ? getC_AcctSchema_ID() : null;
	}

	public Integer getPartnerGroupId ()
	{
		return getC_BP_Group_ID() > 0 ? getC_BP_Group_ID() : null;
	}

	public Integer getPayrollId() {
		return getHR_Payroll_ID() > 0 ? getHR_Payroll_ID() : null;
	}

	/**
	 * Get Concept Account
	 * @param acctSchemaId
	 * @return
	 * @deprecated
	 */
	public X_HR_Concept_Acct getConceptAcct(int acctSchemaId) {
		MHRConcept concept = MHRConcept.getById(Env.getCtx(), getHR_Concept_ID() , getTrxName());
		if(concept != null) {
			return concept.getConceptAcct(acctSchemaId);
		}
		//	Default null
		return null;
	}
}   //  DocLine_Payroll
