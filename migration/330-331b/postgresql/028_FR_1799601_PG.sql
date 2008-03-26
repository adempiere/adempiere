INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (53223, 0, 0, 'Y',
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'DunningGrace', 'D', 'Dunning Grace',
             'Dunning Grace'
            );


INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, 
             help, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id,  issyncdatabase,
             isalwaysupdateable
            )
     VALUES (53246, 0, 0, 'Y',
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Dunning Grace', 'Delay/block the dunning until this date is reached.', 
             'Delay/block the dunning until this date is reached.', 1,
             'D', 'DunningGrace', 291, 15,
             7, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 53223, 'N',
             'N'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, 
             help,
             iscentrallymaintained, seqno, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly,
             issameline, isheading, isfieldonly, isencrypted, entitytype,
             displaylogic
            )
     VALUES (53256, 0, 0, 'Y',
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Dunning Grace', 'Delay/block the dunning until this date is reached.', 
             'Delay/block the dunning until this date is reached.',
             'Y', 260 ,223,
             53246, 'Y', 14, 'N',
             'N', 'N', 'N', 'N', 'D',
             '@IsCustomer@=Y'
            );            
            
            
INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, 
             help, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id,  issyncdatabase,
             isalwaysupdateable
            )
     VALUES (53247, 0, 0, 'Y',
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Dunning Grace', 'Delay/block the dunning until this date is reached.', 
             'Delay/block the dunning until this date is reached.', 1,
             'D', 'DunningGrace', 318, 15,
             7, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 53223, 'N',
             'Y'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, 
             help,
             iscentrallymaintained, seqno, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (53257, 0, 0, 'Y',
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('09/21/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Dunning Grace', 'Delay/block the dunning until this date is reached.', 
             'Delay/block the dunning until this date is reached.',
             'Y', 410 ,263,
             53247, 'Y', 14, 'N',
             'N', 'N', 'N', 'N', 'D'
            );            
                        
         

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                           FROM ad_element
                          WHERE ad_element_id < 1000000)
 WHERE NAME = 'AD_Element';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_column_id) + 1
                           FROM ad_column
                          WHERE ad_column_id < 1000000)
 WHERE NAME = 'AD_Column';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_field_id) + 1
                           FROM ad_field
                          WHERE ad_field_id < 1000000)
 WHERE NAME = 'AD_Field';
 


ALTER TABLE C_BPartner ADD DunningGrace date NULL;
ALTER TABLE C_Invoice ADD DunningGrace date NULL;

DROP VIEW c_invoiceline_v;
DROP VIEW rv_bpartneropen;
DROP VIEW c_invoice_v;

CREATE OR REPLACE VIEW C_INVOICE_V
AS 
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID,
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred,
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID,
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule,
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID,
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, cast(null as numeric) AS C_InvoicePaySchedule_ID, i.InvoiceCollectionType,i.DunningGrace,
    cast(CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END as numeric) AS ChargeAmt,
    cast(CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END as numeric) AS TotalLines,
    cast(CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END as numeric) AS GrandTotal,
    cast(CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END as numeric) AS Multiplier,
    cast(CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS numeric) as MultiplierAP,
    d.DocBaseType
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
WHERE i.IsPayScheduleValid<>'Y'
UNION
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID,
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred,
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID,
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule,
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID,
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID, i.InvoiceCollectionType, i.DunningGrace,
    null AS ChargeAmt,
    null AS TotalLines,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN ips.DueAmt*-1 ELSE ips.DueAmt END AS GrandTotal,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
    d.DocBaseType
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE i.IsPayScheduleValid='Y'
    AND ips.IsValid='Y';

CREATE OR REPLACE VIEW C_INVOICELINE_V AS
SELECT il.AD_Client_ID, il.AD_Org_ID, 
	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID, 
	i.C_BPartner_ID, il.M_Product_ID,  
	i.DocumentNo, i.DateInvoiced, i.DateAcct,
	i.IsSOTrx, i.DocStatus,
	ROUND(i.Multiplier*LineNetAmt, 2) AS LineNetAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced, 2) AS LineListAmt,
	CASE WHEN COALESCE(il.PriceLimit, 0)=0 THEN ROUND(i.Multiplier*LineNetAmt,2) ELSE ROUND(i.Multiplier*il.PriceLimit*il.QtyInvoiced,2) END AS LineLimitAmt,
	ROUND(i.Multiplier*il.PriceList*il.QtyInvoiced-il.LineNetAmt,2) AS LineDiscountAmt,
	CASE WHEN COALESCE(il.PriceLimit,0)=0 THEN 0 ELSE ROUND(i.Multiplier*il.LineNetAmt-il.PriceLimit*il.QtyInvoiced,2) END AS LineOverLimitAmt,
	il.QtyInvoiced, il.QtyEntered,
	il.Line, il.C_OrderLine_ID, il.C_UOM_ID,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_Invoice_v i, C_InvoiceLine il
WHERE i.C_Invoice_ID=il.C_Invoice_ID;

--COMMENT ON TABLE C_INVOICELINE_V IS 'Invoice Line Summary for Reporting Views - Corrected for Credit Memos';


CREATE OR REPLACE VIEW RV_BPARTNEROPEN
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_BPARTNER_ID, C_CURRENCY_ID, AMT, 
 OPENAMT, DATEDOC, DAYSDUE, C_CAMPAIGN_ID, C_PROJECT_ID, 
 C_ACTIVITY_ID)
AS 
SELECT i.AD_Client_ID,i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
    i.C_BPartner_ID, i.C_Currency_ID,
    i.GrandTotal*i.MultiplierAP AS Amt,
    invoiceOpen (i.C_Invoice_ID, i.C_InvoicePaySchedule_ID)*i.MultiplierAP AS OpenAmt,
    i.DateInvoiced AS DateDoc, 
    COALESCE(daysBetween(getdate(),ips.DueDate), paymentTermDueDays(C_PaymentTerm_ID,DateInvoiced,getdate())) AS DaysDue,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM C_Invoice_v i 
  LEFT OUTER JOIN C_InvoicePaySchedule ips ON (i.C_InvoicePaySchedule_ID=ips.C_InvoicePaySchedule_ID)
WHERE i.IsPaid='N'
 AND i.DocStatus IN ('CO','CL')
UNION
SELECT p.AD_Client_ID,p.AD_Org_ID, p.IsActive, p.Created,p.CreatedBy,p.Updated,p.UpdatedBy,
    p.C_BPartner_ID, p.C_Currency_ID,
    p.PayAmt*MultiplierAP*-1 AS Amt,
    paymentAvailable(p.C_Payment_ID)*p.MultiplierAP*-1 AS OpenAmt,
    p.DateTrx AS DateDoc,
    null,
    p.C_Campaign_ID, p.C_Project_ID, p.C_Activity_ID
FROM C_Payment_v p 
WHERE p.IsAllocated='N' AND p.C_BPartner_ID IS NOT NULL;
