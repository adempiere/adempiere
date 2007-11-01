SET DEFINE OFF;

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


  CREATE OR REPLACE FORCE VIEW C_INVOICE_V AS 
  SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID, 
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred, 
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID, 
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule, 
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, 
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, null AS C_InvoicePaySchedule_ID, i.InvoiceCollectionType,
    i.DunningGrace,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
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
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID, i.InvoiceCollectionType,
    i.DunningGrace,
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

COMMIT ;