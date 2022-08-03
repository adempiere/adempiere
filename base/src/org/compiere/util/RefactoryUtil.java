/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2015 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2015 Victor Pérez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.compiere.util;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.api.I_HR_Concept;
import org.adempiere.core.api.I_HR_Employee;
import org.adempiere.core.api.I_HR_Movement;
import org.adempiere.core.api.I_HR_Payroll;
import org.adempiere.core.api.I_HR_Process;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MTable;
import org.compiere.model.PO;

/**
 * A util class for keep worked functionality
 */
public class RefactoryUtil {
	public static final int		DD_Order_Table_ID = 53037;
	public static final int		DD_OrderLine_Table_ID = 53038;
	public static final String	DD_Order_Table_Name = "DD_Order";
	public static final String	DD_OrderLine_Table_Name = "DD_OrderLine";
	public static final int		A_Asset_Addition_Table_ID = 53137;
	public static final String	A_Asset_Addition_Table_Name = "A_Asset_Addition";
	public static final int		A_Depreciation_Entry_Table_ID = 53121;
	public static final String	A_Depreciation_Entry_Table_Name = "A_Depreciation_Entry";
	public static final int		A_Asset_Disposed_Table_ID = 53127;
	public static final String	A_Asset_Disposed_Table_Name = "A_Asset_Disposed";
	public static final int		A_Asset_Table_ID = 539;
	public static final String	A_Asset_Table_Name = "A_Asset";
	public static final int		HR_Process_Table_ID = 53092;
	public static final String	HR_Process_Table_Name = "HR_Process";
	public static final int		HR_Payroll_Table_ID = 53093;
	public static final String	HR_Payroll_Table_Name = "HR_Payroll";
	public static final int		HR_Movement_Table_ID = 53102;
	public static final String	HR_Movement_Table_Name = "HR_Movement";
	public static final int		HR_Concept_Table_ID = 53090;
	public static final String	HR_Concept_Table_Name = "HR_Concept";
	public static final int		HR_PaySelectionCheck_Table_ID = 53250;
	public static final String	HR_PaySelectionCheck_Table_Name = "HR_PaySelectionCheck";
	public static final int		HR_Employee_Table_ID = 53086;
	public static final String	HR_Employee_Table_Name = "HR_Employee";
	
	/** AccountSign AD_Reference_ID=118 */
	public static final int HR_Concept_ACCOUNTSIGN_AD_Reference_ID=118;
	/** Natural = N */
	public static final String HR_Concept_ACCOUNTSIGN_Natural = "N";
	/** Debit = D */
	public static final String HR_Concept_ACCOUNTSIGN_Debit = "D";
	/** Credit = C */
	public static final String HR_Concept_ACCOUNTSIGN_Credit = "C";
	/** ColumnType AD_Reference_ID=53243 */
	public static final int HR_Concept_COLUMNTYPE_AD_Reference_ID=53243;
	/** Amount = A */
	public static final String HR_Concept_COLUMNTYPE_Amount = "A";
	/** Date = D */
	public static final String HR_Concept_COLUMNTYPE_Date = "D";
	/** Quantity = Q */
	public static final String HR_Concept_COLUMNTYPE_Quantity = "Q";
	/** Text = T */
	public static final String HR_Concept_COLUMNTYPE_Text = "T";
	
	/**
	 * Get Payroll Movement from Id
	 * @param context
	 * @param payrollMovementId
	 * @param transactionName
	 * @return
	 */
	public static I_HR_Movement getPayrollMovement(Properties context, int payrollMovementId, String transactionName) {
		return (I_HR_Movement) MTable.get(context, HR_Movement_Table_Name).getPO(payrollMovementId, transactionName);
	}
	
	/**
	 * Get Payroll Process from Id
	 * @param context
	 * @param payrollProcessId
	 * @param transactionName
	 * @return
	 */
	public static I_HR_Process getPayrollProcess(Properties context, int payrollProcessId, String transactionName) {
		return (I_HR_Process) MTable.get(context, HR_Process_Table_Name).getPO(payrollProcessId, transactionName);
	}
	
	/**
	 * Get Payroll definition from Id
	 * @param context
	 * @param payrollDefinitionId
	 * @param transactionName
	 * @return
	 */
	public static I_HR_Payroll getPayrollDefinition(Properties context, int payrollDefinitionId, String transactionName) {
		return (I_HR_Payroll) MTable.get(context, HR_Payroll_Table_Name).getPO(payrollDefinitionId, transactionName);
	}
	
	
	/**
	 * Get Payroll concept from Id
	 * @param context
	 * @param payrollConceptId
	 * @param transactionName
	 * @return
	 */
	public static I_HR_Concept getPayrollConcept(Properties context, int payrollConceptId, String transactionName) {
		return (I_HR_Concept) MTable.get(context, HR_Concept_Table_Name).getPO(payrollConceptId, transactionName);
	}
	
	/**
	 * Get employee from Id
	 * @param context
	 * @param payrollEmployeeId
	 * @param transactionName
	 * @return
	 */
	public static I_HR_Employee getPayrollEmployee(Properties context, int payrollEmployeeId, String transactionName) {
		return (I_HR_Employee) MTable.get(context, HR_Concept_Table_Name).getPO(payrollEmployeeId, transactionName);
	}
	
	/**
	 * Get a instance of Distribution Order
	 * @param context
	 * @param distributionOrderId
	 * @param transactionName
	 * @return
	 */
	public static PO getDistributionOrder(Properties context, int distributionOrderId, String transactionName) {
		return MTable.get(context, DD_Order_Table_ID).getPO(distributionOrderId, transactionName);
	}
	
	/**
	 * Get Instance of Distribution Order Line
	 * @param context
	 * @param distributionOrderLineId
	 * @param transactionName
	 * @return
	 */
	public static PO getDistributionOrderLine(Properties context, int distributionOrderLineId, String transactionName) {
		return MTable.get(context, DD_OrderLine_Table_ID).getPO(distributionOrderLineId, transactionName);
	}
	
	/**
	 * Get Instance of Distribution Order Line from Distribution Order
	 * @param distributionOrder
	 * @return
	 */
	public static PO getDistributionOrderLineInstanceFromParent(PO distributionOrder) {
		PO distributionOrderLine = getDistributionOrderLine(distributionOrder.getCtx(), 0, distributionOrder.get_TrxName());
		distributionOrderLine.set_ValueOfColumn("DD_Order_ID", distributionOrder.get_ID());
		distributionOrderLine.setAD_Org_ID(distributionOrder.getAD_Org_ID());
		distributionOrderLine.set_ValueOfColumn("DateOrdered", distributionOrder.get_Value("DateOrdered"));
		distributionOrderLine.set_ValueOfColumn("DatePromised", distributionOrder.get_Value("DatePromised"));
		return distributionOrderLine;
	}
	
	/**
	 * Set Business Partner Reference
	 * @param referenceToSet
	 * @param bp
	 */
	public static void setBusinessPartner(PO referenceToSet, MBPartner bp) {
		if (bp == null)
			return;

		referenceToSet.set_ValueOfColumn("C_BPartner_ID", bp.getC_BPartner_ID());
		//	Defaults Payment Term
		int ii = 0;
		if (referenceToSet.get_ValueAsBoolean("IsSOTrx"))
			ii = bp.getC_PaymentTerm_ID();
		else
			ii = bp.getPO_PaymentTerm_ID();
		
		//	Default Price List
		if (referenceToSet.get_ValueAsBoolean("IsSOTrx"))
			ii = bp.getM_PriceList_ID();
		else
			ii = bp.getPO_PriceList_ID();
		//	Default Delivery/Via Rule
		String ss = bp.getDeliveryRule();
		if (ss != null)
			referenceToSet.set_ValueOfColumn("DeliveryRule", ss);
		ss = bp.getDeliveryViaRule();
		if (ss != null)
			referenceToSet.set_ValueOfColumn("DeliveryViaRule", ss);
		//	Default Invoice/Payment Rule
		ss = bp.getInvoiceRule();

		if (referenceToSet.get_ValueAsInt("SalesRep_ID") == 0)
		{
			ii = Env.getAD_User_ID(referenceToSet.getCtx());
			if (ii != 0)
				referenceToSet.set_ValueOfColumn("SalesRep_ID", ii);
		}

		List<MBPartnerLocation> partnerLocations = Arrays.asList(bp.getLocations(false));
		// search the Ship To Location
		MBPartnerLocation partnerLocation = partnerLocations.stream() 			// create steam
				.filter( pl -> pl.isShipTo()).reduce((first , last ) -> last) 	// get of last Ship to location
				.orElseGet(() -> partnerLocations.stream() 								// if not exist Ship to location else get first partner location
							.findFirst()										// if not exist partner location then throw an exception
							.orElseThrow(() -> new AdempiereException("@IsShipTo@ @NotFound@"))
				);

		referenceToSet.set_ValueOfColumn("C_BPartner_Location_ID", partnerLocation.getC_BPartner_Location_ID());
		//	
		Arrays.asList(bp.getContacts(false))
				.stream()
				.findFirst()
				.ifPresent(user -> referenceToSet.set_ValueOfColumn("AD_User_ID", user.getAD_User_ID()));
	}
	
}    //	MovementGenerate
