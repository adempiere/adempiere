ALTER TABLE AD_PACKAGE_EXP_DETAIL ADD ad_val_rule_id NUMBER(10);

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50212, 0, 0, 'Y',
             TO_DATE ('04/26/2007 03:24:26', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('04/26/2007 03:24:26', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Dynamic Validation', 'Dynamic Validation Rule',
             'These rules define how an entry is determined to valid. You can use variables for dynamic (context sensitive) validation.',
             0, 'D', 'AD_Val_Rule_ID', 50006, 18,
             22, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 139, 'N',
             'N'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylogic, displaylength, isreadonly, seqno, issameline,
             isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50182, 0, 0, 'Y',
             TO_DATE ('04/26/2007 03:28:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('04/26/2007 03:30:25', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Dynamic Validation', 'Dynamic Validation Rule',
             'These rules define how an entry is determined to valid. You can use variables for dynamic (context sensitive) validation.',
             'Y', 50006, 50212, 'Y',
             '@Type@=''V''', 22, 'N', 245, 'N',
             'N', 'N', 'N', 'D'
            );

INSERT INTO AD_REF_LIST
            (ad_ref_list_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             VALUE, NAME, ad_reference_id, entitytype
            )
     VALUES (50041, 0, 0, 'Y',
             TO_DATE ('04/26/2007 03:33:05', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('04/26/2007 03:33:05', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'V', 'Dynamic Validation Rule', 50004, 'D'
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
