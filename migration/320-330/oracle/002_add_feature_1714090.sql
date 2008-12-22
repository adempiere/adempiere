-- Feature Request
-- juddm - add the ability to specific a shipment date (instead of current date) to the shipment generation process
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1714090&group_id=176962&atid=879335

INSERT INTO AD_PROCESS_PARA
            (ad_process_para_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, NAME,
             description,
             HELP,
             ad_process_id, seqno, ad_reference_id, ad_reference_value_id,
             ad_val_rule_id, columnname, iscentrallymaintained, fieldlength,
             ismandatory, isrange, ad_element_id, entitytype
            )
     VALUES (50019, 0, 0, 'Y', TO_DATE ('2007-03-03', 'YYYY-MM-DD'),
             100, TO_DATE ('2007-03-03', 'YYYY-MM-DD'), 100, 'Shipment Date',
             'Date printed on shipment',
             'The Shipment Date indicates the date printed on the shipment.',
             118, 15, 15, NULL,
             NULL, 'MovementDate', 'N', 0,
             'Y', 'N', 1037, 'D'
            );

COMMIT ;

UPDATE AD_SEQUENCE
   SET currentnextsys = (SELECT MAX (ad_process_para_id) + 1
                           FROM AD_PROCESS_PARA
                          WHERE ad_process_para_id < 1000000)
 WHERE NAME = 'AD_Process_Para';

COMMIT ;
