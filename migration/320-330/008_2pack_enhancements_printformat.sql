ALTER TABLE AD_PACKAGE_EXP_DETAIL ADD ad_printformat_id NUMERIC(10,0);

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP, VERSION, entitytype, columnname,
             ad_table_id, ad_reference_id, fieldlength, iskey, isparent,
             ismandatory, isupdateable, isidentifier, seqno, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50217, 0, 0, 'Y',
             TO_DATE ('05/25/2007 19:48:41', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('05/25/2007 19:48:41', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Print Format', 'Data Print Format',
             'The print format determines how data is rendered for print.', 0, 'D', 'AD_PrintFormat_ID',
             50006, 19, 22, 'N', 'N',
             'N', 'Y', 'N', 0, 'N',
             'N', 'N', 1790, 'N',
             'N'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylogic, displaylength, isreadonly, seqno, issameline,
             isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50187, 0, 0, 'Y',
             TO_DATE ('05/25/2007 19:51:35', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('05/25/2007 19:52:28', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'PrintFormat', 'Print Format', 'Print Format',
             'Y', 50006, 50217, 'Y',
             '@Type@=''PFT''', 22, 'N', 246, 'N',
             'N', 'N', 'N', 'D'
            );

INSERT INTO AD_REF_LIST
            (ad_ref_list_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             VALUE, NAME, ad_reference_id, entitytype
            )
     VALUES (50044, 0, 0, 'Y',
             TO_DATE ('05/14/2007 19:54:20', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('05/14/2007 19:54:20', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'PFT', 'PrintFormat', 50004, 'D'
            );

COMMIT ;

UPDATE AD_SEQUENCE
   SET currentnextsys = (SELECT MAX (ad_column_id) + 1
                           FROM AD_COLUMN
                          WHERE ad_column_id < 1000000)
 WHERE NAME = 'AD_Column';

UPDATE AD_SEQUENCE
   SET currentnextsys = (SELECT MAX (ad_field_id) + 1
                           FROM AD_FIELD
                          WHERE ad_field_id < 1000000)
 WHERE NAME = 'AD_Field';

UPDATE AD_SEQUENCE
   SET currentnextsys = (SELECT MAX (ad_ref_list_id) + 1
                           FROM AD_REF_LIST
                          WHERE ad_ref_list_id < 1000000)
 WHERE NAME = 'AD_Ref_List';

COMMIT ;
