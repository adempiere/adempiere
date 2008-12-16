SET DEFINE OFF;

update AD_Column set IsAlwaysUpdateable='Y' where AD_Column_ID=12569;
update AD_Column set IsAlwaysUpdateable='Y' where AD_Column_ID=12566;

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (53224, 0, 0, 'Y',
             TO_DATE ('2007-09-28', 'YYYY-MM-DD'), 100,
             TO_DATE ('2007-09-28', 'YYYY-MM-DD'), 100,
             'PrintUnprocessedOnly', 'D', 'Print Unprocessed Entries Only',
             'Print Unprocessed Entries Only'
            );


INSERT INTO AD_Process_Para
(AD_Process_Para_ID, AD_Client_ID, AD_Org_ID, IsActive, Created,
CreatedBy, Updated, UpdatedBy, Name,
Description,
Help,
AD_Process_ID, SeqNo, AD_Reference_ID, AD_Reference_Value_ID,
AD_Val_Rule_ID, ColumnName, IsCentrallyMaintained, FieldLength,
IsMandatory, DefaultValue, IsRange, AD_Element_ID, EntityType
)
VALUES 
(53011 , 0, 0, 'Y', TO_DATE ('2007-09-28', 'YYYY-MM-DD'),
100, TO_DATE ('2007-09-28', 'YYYY-MM-DD'), 100, 'Print Unprocessed Entries Only',
'Print the unprocessed (unprinted) entries of the dunning run only.',
'Print the unprocessed (unprinted) entries of the dunning run only. This allows you to reprint only certain dunning entries.',
312, 50, 20, NULL,
NULL, 'PrintUnprocessedOnly', 'N', 1,
'Y', 'Y', 'N', 53224, 'D'
);

INSERT INTO AD_VAL_RULE(
AD_VAL_RULE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,
UPDATED,UPDATEDBY,
NAME,DESCRIPTION,
TYPE,CODE,ENTITYTYPE)
values(51003,0,0,'Y',to_date('2007-09-28','RRRR-MM-DD'),100,
to_date('2007-09-28','RRRR-MM-DD'),100,
'C_DunningRun Unprocessed','Unprocessed Dunning Runs',
'S','C_DunningRun.Processed=''N''','D');

UPDATE AD_Process_Para SET AD_Val_Rule_ID=51003 WHERE AD_Process_Para_ID=578;


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
     VALUES (53248, 0, 0, 'Y',
             TO_DATE ('2007-09-28','RRRR-MM-DD'),
             TO_DATE ('2007-09-28','RRRR-MM-DD'), 100,
             100, 'Dunning Level', 'Dunning Level', 
             'Dunning Level', 1,
             'D', 'C_DunningLevel_ID', 318, 19,
             22, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 1075, 'N',
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
     VALUES (53258 , 0, 0, 'Y',
             TO_DATE ('2007-09-28','RRRR-MM-DD'), 100,
             TO_DATE ('2007-09-28','RRRR-MM-DD'), 100,
             'Dunning Level', 'Dunning Level', 
             'Dunning Level',
             'Y', 420 ,263,
             53248, 'Y', 14, 'Y',
             'Y', 'N', 'N', 'N', 'D',
             '@Processed@=Y'
            );    

ALTER TABLE C_Invoice ADD C_DunningLevel_ID NUMBER(10,0) NULL;

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
     VALUES (53249, 0, 0, 'Y',
             TO_DATE ('2007-09-28','RRRR-MM-DD'),
             TO_DATE ('2007-09-28','RRRR-MM-DD'), 100,
             100, 'Invoice Payment Schedule', 'Invoice Payment Schedule', 
             'Invoice Payment Schedule', 1,
             'D', 'C_InvoicePaySchedule_ID', 524, 19,
             22, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 1995, 'N',
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
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (53259 , 0, 0, 'Y',
             TO_DATE ('2007-09-28','RRRR-MM-DD'), 100,
             TO_DATE ('2007-09-28','RRRR-MM-DD'), 100,
             'Invoice Payment Schedule', 'Invoice Payment Schedule', 
             'Invoice Payment Schedule',
             'Y', 65 ,635,
             53249, 'Y', 26, 'Y',
             'N', 'N', 'N', 'N', 'D'
            );    

ALTER TABLE C_DunningRunLine ADD C_InvoicePaySchedule_ID NUMBER(10,0) NULL;



COMMIT;


UPDATE AD_SEQUENCE
SET currentnextsys = (SELECT MAX (ad_process_para_id) + 1
FROM AD_Process_Para
WHERE AD_Process_Para_ID < 1000000)
WHERE NAME = 'AD_Process_Para';

UPDATE AD_SEQUENCE
SET currentnextsys = (SELECT MAX (AD_Val_Rule_id) + 1
FROM AD_Val_Rule
WHERE AD_Val_Rule_ID < 1000000)
WHERE NAME = 'AD_Val_Rule';

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

COMMIT;

