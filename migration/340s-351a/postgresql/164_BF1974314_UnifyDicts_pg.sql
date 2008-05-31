UPDATE AD_PROCESS_PARA
   SET defaultvalue = 'http://www.adempiere.org'
 WHERE ad_process_para_id = 53023;

UPDATE AD_PROCESS_PARA
   SET name = 'Purchase Price List Version', iscentrallymaintained='Y'
 WHERE ad_process_para_id = 53025;

UPDATE AD_PROCESS_PARA
   SET name = 'Generate Fields', iscentrallymaintained='Y'
 WHERE ad_process_para_id = 53127;

UPDATE AD_PROCESS_PARA
   SET name = 'Scripts Path', iscentrallymaintained='Y'
 WHERE ad_process_para_id = 53131;

UPDATE AD_FIELD 
   SET help = 'Either add missing accounts - or copy and overwrite all default accounts.  If you copy and overwrite the current default values, you may have to repeat previous updates (e.g. set the bank account asset accounts, ...).  If no Accounting Schema is selected all Accounting Schemas will be updated / inserted.'
 where ad_field_id = 3823;

update ad_ref_list
   set name = 'PO/SO Commitment & Reservation'
 where ad_ref_list_id = 53223;

update ad_ref_list
   set name = 'PO/SO Commitment'
 where ad_ref_list_id = 53225;

UPDATE ad_process
   SET help = 'Either add missing accounts - or copy and overwrite all default accounts.  If you copy and overwrite the current default values, you may have to repeat previous updates (e.g. set the bank account asset accounts, ...).  If no Accounting Schema is selected all Accounting Schemas will be updated / inserted.',
       classname = 'org.compiere.process.AcctSchemaDefaultCopy'
 where ad_process_id = 108;

