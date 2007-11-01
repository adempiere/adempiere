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

CREATE OR REPLACE VIEW c_invoice_v AS 
 SELECT i.c_invoice_id, i.ad_client_id, i.ad_org_id, i.isactive, i.created, i.createdby, i.updated, i.updatedby, i.issotrx, i.documentno, i.docstatus, i.docaction, i.processing, i.processed, i.c_doctype_id, i.c_doctypetarget_id, i.c_order_id, 
i.description, i.isapproved, i.istransferred, i.salesrep_id, i.dateinvoiced, i.dateprinted, i.dateacct, i.c_bpartner_id, i.c_bpartner_location_id, i.ad_user_id, i.poreference, i.dateordered, i.c_currency_id, i.c_conversiontype_id, i.paymentrule, 
i.c_paymentterm_id, i.c_charge_id, i.m_pricelist_id, i.c_campaign_id, i.c_project_id, i.c_activity_id, i.isprinted, i.isdiscountprinted, i.ispaid, i.isindispute, i.ispayschedulevalid, NULL::numeric AS c_invoicepayschedule_id, i.invoicecollectiontype, i.dunninggrace,
        CASE
            WHEN charat(d.docbasetype::character varying, 3)::text = 'C'::text THEN i.chargeamt * -1::numeric
            ELSE i.chargeamt
        END AS chargeamt, 
        CASE
            WHEN charat(d.docbasetype::character varying, 3)::text = 'C'::text THEN i.totallines * -1::numeric
            ELSE i.totallines
        END AS totallines, 
        CASE
            WHEN charat(d.docbasetype::character varying, 3)::text = 'C'::text THEN i.grandtotal * -1::numeric
            ELSE i.grandtotal
        END AS grandtotal, 
        CASE
            WHEN charat(d.docbasetype::character varying, 3)::text = 'C'::text THEN -1
            ELSE 1
        END::numeric AS multiplier, 
        CASE
            WHEN charat(d.docbasetype::character varying, 2)::text = 'P'::text THEN -1
            ELSE 1
        END::numeric AS multiplierap, d.docbasetype
   FROM c_invoice i
   JOIN c_doctype d ON i.c_doctype_id = d.c_doctype_id
  WHERE i.ispayschedulevalid <> 'Y'::bpchar
UNION 
 SELECT i.c_invoice_id, i.ad_client_id, i.ad_org_id, i.isactive, i.created, i.createdby, i.updated, i.updatedby, i.issotrx, i.documentno, i.docstatus, i.docaction, i.processing, i.processed, i.c_doctype_id, i.c_doctypetarget_id, i.c_order_id, 
i.description, i.isapproved, i.istransferred, i.salesrep_id, i.dateinvoiced, i.dateprinted, i.dateacct, i.c_bpartner_id, i.c_bpartner_location_id, i.ad_user_id, i.poreference, i.dateordered, i.c_currency_id, i.c_conversiontype_id, i.paymentrule, 
i.c_paymentterm_id, i.c_charge_id, i.m_pricelist_id, i.c_campaign_id, i.c_project_id, i.c_activity_id, i.isprinted, i.isdiscountprinted, i.ispaid, i.isindispute, i.ispayschedulevalid, ips.c_invoicepayschedule_id, i.invoicecollectiontype, i.dunninggrace,
NULL::"unknown" AS chargeamt, NULL::"unknown" AS totallines, 
        CASE
            WHEN charat(d.docbasetype::character varying, 3)::text = 'C'::text THEN ips.dueamt * -1::numeric
            ELSE ips.dueamt
        END AS grandtotal, 
        CASE
            WHEN charat(d.docbasetype::character varying, 3)::text = 'C'::text THEN -1
            ELSE 1
        END AS multiplier, 
        CASE
            WHEN charat(d.docbasetype::character varying, 2)::text = 'P'::text THEN -1
            ELSE 1
        END AS multiplierap, d.docbasetype
   FROM c_invoice i
   JOIN c_doctype d ON i.c_doctype_id = d.c_doctype_id
   JOIN c_invoicepayschedule ips ON i.c_invoice_id = ips.c_invoice_id
  WHERE i.ispayschedulevalid = 'Y'::bpchar AND ips.isvalid = 'Y'::bpchar;


CREATE OR REPLACE VIEW c_invoiceline_v AS 
 SELECT il.ad_client_id, il.ad_org_id, il.c_invoiceline_id, i.c_invoice_id, i.salesrep_id, i.c_bpartner_id, il.m_product_id, i.documentno, i.dateinvoiced, i.dateacct, i.issotrx, i.docstatus, round(i.multiplier * il.linenetamt, 2) AS linenetamt, round(i.multiplier * il.pricelist * il.qtyinvoiced, 2) AS linelistamt, 
        CASE
            WHEN COALESCE(il.pricelimit, 0::numeric) = 0::numeric THEN round(i.multiplier * il.linenetamt, 2)
            ELSE round(i.multiplier * il.pricelimit * il.qtyinvoiced, 2)
        END AS linelimitamt, round(i.multiplier * il.pricelist * il.qtyinvoiced - il.linenetamt, 2) AS linediscountamt, 
        CASE
            WHEN COALESCE(il.pricelimit, 0::numeric) = 0::numeric THEN 0::numeric
            ELSE round(i.multiplier * il.linenetamt - il.pricelimit * il.qtyinvoiced, 2)
        END AS lineoverlimitamt, il.qtyinvoiced, il.qtyentered, il.line, il.c_orderline_id, il.c_uom_id, il.c_campaign_id, il.c_project_id, il.c_activity_id, il.c_projectphase_id, il.c_projecttask_id
   FROM c_invoice_v i, c_invoiceline il
  WHERE i.c_invoice_id = il.c_invoice_id;


CREATE OR REPLACE VIEW rv_bpartneropen AS 
 SELECT i.ad_client_id, i.ad_org_id, i.isactive, i.created, i.createdby, i.updated, i.updatedby, i.c_bpartner_id, i.c_currency_id, i.grandtotal * i.multiplierap AS amt, invoiceopen(i.c_invoice_id, i.c_invoicepayschedule_id) * i.multiplierap AS openamt, i.dateinvoiced AS datedoc, COALESCE(daysbetween(getdate(), ips.duedate::timestamp with time zone), paymenttermduedays(i.c_paymentterm_id, i.dateinvoiced::timestamp with time zone, getdate())) AS daysdue, i.c_campaign_id, i.c_project_id, i.c_activity_id
   FROM c_invoice_v i
   LEFT JOIN c_invoicepayschedule ips ON i.c_invoicepayschedule_id = ips.c_invoicepayschedule_id
  WHERE i.ispaid = 'N'::bpchar AND (i.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar]))
UNION 
 SELECT p.ad_client_id, p.ad_org_id, p.isactive, p.created, p.createdby, p.updated, p.updatedby, p.c_bpartner_id, p.c_currency_id, p.payamt * p.multiplierap::numeric * -1::numeric AS amt, paymentavailable(p.c_payment_id) * p.multiplierap::numeric * -1::numeric AS openamt, p.datetrx AS datedoc, NULL::"unknown" AS daysdue, p.c_campaign_id, p.c_project_id, p.c_activity_id
   FROM c_payment_v p
  WHERE p.isallocated = 'N'::bpchar AND p.c_bpartner_id IS NOT NULL AND (p.docstatus = ANY (ARRAY['CO'::bpchar, 'CL'::bpchar]));


COMMIT ;
