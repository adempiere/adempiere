INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50040, 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:31:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/13/2007 23:31:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'CopyColumnsFromTable', 'D', 'Copy Columns From Table',
             'Copy Columns From Table'
            );

INSERT INTO ad_process
            (ad_process_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             VALUE, NAME,
             description,
             accesslevel, entitytype, isreport, isdirectprint, classname,
             statistic_count, statistic_seconds, isbetafunctionality,
             isserverprocess, showhelp
            )
     VALUES (50011, 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:35:07', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/13/2007 23:35:48', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'AD_Table_CopyColumnsFromTable', 'Copy Columns from Table',
             'Create Dictionary Columns for a Table taking another as base',
             '4', 'D', 'N', 'N', 'org.compiere.process.CopyColumnsFromTable',
             2, 6, 'N',
             'N', 'Y'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             ad_element_id, entitytype
            )
     VALUES (50005, 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:39:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/13/2007 23:43:06', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Table', 'Database Table information',
             'The Database Table provides the information of the table definition',
             50011, 10, 19, 'AD_Table_ID',
             'Y', 0, 'Y', 'N',
             126, 'D'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50183, 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:46:03', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/13/2007 23:46:03', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Copy Columns from Table', 'Copy Columns from Table', 1,
             'D', 'CopyColumnsFromTable', 100, 28,
             1, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 50040, 50011, 'N',
             'N'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, iscentrallymaintained, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50157, 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:56:24', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/13/2007 23:56:24', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Copy Columns from Table', 'Copy Columns from Table', 'Y', 100,
             50183, 'Y', 1, 'N',
             'N', 'N', 'N', 'N', 'D'
            );

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                           FROM ad_element
                          WHERE ad_element_id < 1000000)
 WHERE NAME = 'AD_Element';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_process_id) + 1
                           FROM ad_process
                          WHERE ad_process_id < 1000000)
 WHERE NAME = 'AD_Process';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_process_para_id) + 1
                           FROM ad_process_para
                          WHERE ad_process_para_id < 1000000)
 WHERE NAME = 'AD_Process_Para';

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

COMMIT ;

ALTER TABLE ad_table ADD copycolumnsfromtable NVARCHAR2(1);
