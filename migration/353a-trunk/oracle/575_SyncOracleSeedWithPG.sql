update c_currency set emurate = 0 where emurate is null;

update c_invoiceline set taxamt = 0 where taxamt is null;

update c_invoiceline set linetotalamt = 0 where linetotalamt is null;

update m_product_po set order_min = 0 where order_min is null;

update m_product_po set order_pack = 0 where order_pack is null;

update m_product_po set costperorder = 0 where costperorder is null;

update m_product_po set royaltyamt = 0 where royaltyamt is null;

alter table ad_form_access drop constraint ad_form_access_key;

alter table ad_form_access add (constraint ad_form_access_key primary key (ad_form_id, ad_role_id));

alter table ad_process_access drop constraint ad_process_access_key;

alter table ad_process_access add (constraint ad_process_access_key primary key (ad_process_id, ad_role_id));

alter table ad_task_access drop constraint ad_task_access_key;

alter table ad_task_access add (constraint ad_task_access_key primary key (ad_task_id, ad_role_id));

alter table ad_user_roles drop constraint ad_user_role_key;

alter table ad_user_roles add (constraint ad_user_roles_key primary key (ad_role_id, ad_user_id));

alter table ad_wf_node_trl drop constraint ad_wf_node_trl_key;

alter table ad_wf_node_trl add (constraint ad_wf_node_trl_key primary key (ad_language, ad_wf_node_id));

alter table ad_window_access drop constraint ad_window_access_key;

alter table ad_window_access add (constraint ad_window_access_key primary key (ad_window_id, ad_role_id));

alter table ad_workflow_access drop constraint ad_workflow_access_key;

alter table ad_workflow_access add (constraint ad_workflow_access_key primary key (ad_workflow_id, ad_role_id));

alter table asp_field modify ( ad_field_id number(10,0) not null );

alter table asp_process_para modify ( ad_process_para_id number(10,0) not null );

alter table a_asset_acct modify ( a_asset_id number(10,0) not null );

alter table c_dunninglevel_trl drop constraint c_dunninglevel_trl_key;

alter table c_dunninglevel_trl add (constraint c_dunninglevel_trl_key primary key (ad_language, c_dunninglevel_id));

alter table c_greeting_trl drop constraint c_greeting_trl_key;

alter table c_greeting_trl add (constraint c_greeting_trl_key primary key (ad_language, c_greeting_id));

alter table c_order add (constraint mfreightcategory_order foreign key(m_freightcategory_id) references m_freightcategory(m_freightcategory_id));

drop view m_product_substituterelate_v;

drop view pp_product_bom_header_v;

drop view pp_product_bom_header_vt;

CREATE OR REPLACE VIEW C_INVOICE_V
(C_INVOICE_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED,
 CREATEDBY, UPDATED, UPDATEDBY, ISSOTRX, DOCUMENTNO,
 DOCSTATUS, DOCACTION, PROCESSING, PROCESSED, C_DOCTYPE_ID,
 C_DOCTYPETARGET_ID, C_ORDER_ID, DESCRIPTION, ISAPPROVED, ISTRANSFERRED,
 SALESREP_ID, DATEINVOICED, DATEPRINTED, DATEACCT, C_BPARTNER_ID,
 C_BPARTNER_LOCATION_ID, AD_USER_ID, POREFERENCE, DATEORDERED, C_CURRENCY_ID,
 C_CONVERSIONTYPE_ID, PAYMENTRULE, C_PAYMENTTERM_ID, C_CHARGE_ID, M_PRICELIST_ID,
 C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, ISPRINTED, ISDISCOUNTPRINTED,
 ISPAID, ISINDISPUTE, ISPAYSCHEDULEVALID, C_INVOICEPAYSCHEDULE_ID, INVOICECOLLECTIONTYPE,DUNNINGGRACE,
 CHARGEAMT, TOTALLINES, GRANDTOTAL, MULTIPLIER, MULTIPLIERAP,
 DOCBASETYPE, DUEDATE
 )
AS
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID,
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred,
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID,
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule,
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID,
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, cast(null as number) AS C_InvoicePaySchedule_ID, i.InvoiceCollectionType,i.DunningGrace,
    (CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END)  AS ChargeAmt,
    (CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END) AS TotalLines,
    (CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END) AS GrandTotal,
    (CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END) AS Multiplier,
    (CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END) AS MultiplierAP,
    d.DocBaseType
    , paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) as DueDate
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
    , ips.DueDate
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE i.IsPayScheduleValid='Y'
    AND ips.IsValid='Y';


