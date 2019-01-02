-- View: RV_HR_ProcessDetail
-- DROP VIEW RV_HR_ProcessDetail;
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************
 ***
 * Title:	Payroll Process Report Source
 * Description:
 *	Show movement from payroll process report
 *
 * Test:
 * 	SELECT * FROM RV_HR_ProcessDetail WHERE HR_ProcessReport_ID = 1000000;
 ************************************************************************/
CREATE OR REPLACE VIEW RV_HR_ProcessDetail AS
 SELECT mop.AD_Client_ID,
    mop.AD_Org_ID,
    mop.Created,
    mop.CreatedBy,
    mop.Updated,
    mop.UpdatedBy,
    mop.ValidFrom,
    mop.ValidTo,
    mop.HR_movement_ID,
    mop.HR_Department_ID,
    mop.HR_Job_ID,
    mop.HR_Concept_Category_ID,
    mop.HR_Concept_ID,
    mop.ServiceDate,
    mop.Description,
    mop.Qty,
    mop.Amount,
    mop.HR_movement_ID AS RV_HR_ProcessDetail_ID, 
    mop.UUID, 
    (CASE
        WHEN c.ColumnType = 'Q' THEN mop.Qty
        WHEN c.ColumnType = 'A' THEN mop.Amount
        ELSE NULL
    END) AS ConceptAmt,
    CASE
        WHEN c.Type = 'R' THEN COALESCE(alt.Name, al.Name)
        ELSE mop.TextMsg
    END AS TextMsg,
    c.HR_Concept_Type_ID,
    c.ColumnType,
    c.Value ConceptValue,
    c.AccountSign,
    CASE WHEN c.AccountSign = 'C' THEN -1 ELSE 1 END Multiplier,
    (CASE
        WHEN c.ColumnType = 'Q' THEN mop.Qty
        WHEN c.ColumnType = 'A' THEN mop.Amount
        ELSE NULL
    END) * (CASE WHEN c.AccountSign = 'C' THEN -1 ELSE 1 END) AS ConvertedAmt,
    ProcessReportSource(mop.HR_Process_ID, mop.C_BPartner_ID, prl.HR_ProcessReportLine_ID, mop.ValidFrom, mop.ValidTo) SourceDescription,
    cc.Value AS CategoryValue,
    cc.Name AS CategoryName,
    prl.IsActive,
    prl.IsAveraged, 
    prl.IsSummarized,
    COALESCE(prl.PrintName, c.Name) PrintName,
    prl.Description AS LineDescription,
    prl.SeqNo,
    prl.HR_ProcessReportLine_ID, 
    prc.HR_process_ID,
    prc.DateAcct,
    prc.DocumentNo,
    prc.HR_Payroll_ID,
    prc.DocStatus,
    prc.HR_Period_ID,
    bp.C_BPartner_ID,
    bp.Value AS BPValue,
    bp.TaxID AS BPTaxID,
    bp.Name,
    bp.Name2,
    e.EmployeeStatus,
    COALESCE(esrt.Name, esr.Name) EmployeeStatusName, 
    e.HR_Employee_ID,  
    e.StartDate, 
    e.EndDate, 
    bp.Name || COALESCE(' ' || bp.Name2, N'') AS BPname,
    pyr.HR_Contract_ID,
    pyr.Value AS PayrollValue,
    pyr.Name AS PayrollName,
    pr.Name AS ProcessReport,
    COALESCE(pr.TextMsg, pr.Description) AS DocumentNote,
    pr.PrintName AS HeaderPrintName,
    pr.ReceiptFooterMsg,
    pr.HR_ProcessReport_ID
   FROM HR_Process prc
   	 INNER JOIN AD_Client cl ON(cl.AD_Client_ID = prc.AD_Client_ID)
     INNER JOIN HR_Payroll pyr ON(pyr.HR_Payroll_ID = prc.HR_Payroll_ID)
     INNER JOIN HR_Movement mop ON(mop.HR_Process_ID = prc.HR_Process_ID)
     INNER JOIN HR_ProcessReportLine prl ON(prl.HR_Concept_ID = mop.HR_Concept_ID)
     INNER JOIN HR_ProcessReport pr ON(pr.HR_ProcessReport_ID = prl.HR_ProcessReport_ID)
     INNER JOIN C_BPartner bp ON(bp.C_BPartner_ID = mop.C_BPartner_ID)
     INNER JOIN HR_Concept_Category cc ON(cc.HR_Concept_Category_ID = mop.HR_Concept_Category_ID)
     INNER JOIN HR_Concept c ON(c.HR_Concept_ID = mop.HR_Concept_ID)
     LEFT JOIN (SELECT e.C_BPartner_ID, e.HR_Employee_ID, e.EmployeeStatus, e.StartDate, e.EndDate
		FROM HR_Employee e
		WHERE e.IsActive = 'Y'
        AND ROWNUM <= 1
		ORDER BY e.StartDate DESC) e ON(e.C_BPartner_ID = bp.C_BPartner_ID)
     LEFT JOIN AD_Ref_List al ON(c.AD_Reference_ID = al.AD_Reference_ID 
                                 AND mop.Textmsg = al.Value)
	 LEFT JOIN AD_Ref_List_Trl alt ON(alt.AD_Ref_List_ID = al.AD_Ref_List_ID 
                                 AND alt.AD_Language = cl.AD_Language)
	 LEFT JOIN AD_Ref_List esr ON(esr.AD_Reference_ID = 53617 
                                 AND esr.Value = e.EmployeeStatus)
	 LEFT JOIN AD_Ref_List_Trl esrt ON(esrt.AD_Ref_List_ID = esr.AD_Ref_List_ID 
                                 AND esrt.AD_Language = cl.AD_Language)
  WHERE prl.IsActive = 'Y'
  AND (EXISTS(SELECT 1 FROM HR_ProcessReportPayroll pp
             WHERE pp.HR_ProcessReport_ID = pr.HR_ProcessReport_ID
             AND pp.HR_Payroll_ID = pyr.HR_Payroll_ID)
      OR NOT EXISTS(SELECT 1 FROM HR_ProcessReportPayroll pp
             WHERE pp.HR_ProcessReport_ID = pr.HR_ProcessReport_ID))
/
