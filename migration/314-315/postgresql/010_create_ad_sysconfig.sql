
CREATE TABLE ad_sysconfig
(
  ad_sysconfig_id  NUMERIC(10)                   NOT NULL,
  ad_client_id     NUMERIC(10)                   NOT NULL,
  ad_org_id        NUMERIC(10)                   NOT NULL,
  created          TIMESTAMP                         NOT NULL,
  updated          TIMESTAMP                         NOT NULL,
  createdby        NUMERIC(10)                   NOT NULL,
  updatedby        NUMERIC(10)                   NOT NULL,
  isactive         CHAR(1)                 DEFAULT 'Y'                   NOT NULL,
  NAME             VARCHAR(50)                NOT NULL,
  VALUE            VARCHAR(255)               NOT NULL,
  description      VARCHAR(255)
);

ALTER TABLE ad_sysconfig ADD 
  CHECK (isactive IN ('Y','N'));

ALTER TABLE ad_sysconfig ADD 
  CONSTRAINT ad_sysconfig_key PRIMARY KEY (ad_sysconfig_id);

INSERT INTO ad_window
            (ad_window_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, windowtype, issotrx, entitytype, processing, isdefault,
             winheight, winwidth, isbetafunctionality
            )
     VALUES (50006, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'System Configurator', 'M', 'Y', 'D', 'N', 'N',
             0, 0, 'N'
            );

INSERT INTO ad_window_access
            (ad_window_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50006, 102, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO ad_window_access
            (ad_window_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50006, 50001, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO ad_window_access
            (ad_window_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50006, 0, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO ad_window_access
            (ad_window_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50006, 103, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:39:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO ad_table
            (ad_table_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, tablename, isview, accesslevel, entitytype, ad_window_id,
             loadseq, issecurityenabled, isdeleteable, ishighvolume,
             importtable, ischangelog, replicationtype, copycolumnsfromtable
            )
     VALUES (50009, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:41:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'System Configurator', 'AD_SysConfig', 'N', '6', 'D', 50006,
             0, 'N', 'Y', 'Y',
             'N', 'N', 'L', 'N'
            );

INSERT INTO ad_sequence
            (ad_sequence_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, isautosequence, incrementno, startno,
             currentnext, currentnextsys, isaudited, istableid, startnewyear
            )
     VALUES (50009, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:41:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'AD_SysConfig', 'Table AD_SysConfig', 'Y', 1, 1000000,
             1000000, 50000, 'N', 'Y', 'N'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50044, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:28', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:41:28', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'AD_SysConfig_ID', 'D', 'System Configurator',
             'System Configurator'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50187, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:27', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:06', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'System Configurator', 0, 'D', 'AD_SysConfig_ID', 50009,
             13, 10, 'Y', 'N', 'Y',
             'N', 'N', 'N', 'N',
             'N', 50044, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, istranslated, isencrypted, isselectioncolumn,
             ad_element_id, issyncdatabase, isalwaysupdateable
            )
     VALUES (50188, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:28', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:42:05', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Client', 'Client/Tenant for this installation.',
             'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',
             0, 'D', 'AD_Client_ID', 50009, 19,
             10, 'N', 'N', 'Y', 'N',
             'N', 'N', 'N', 'N',
             102, 'N', 'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, istranslated, isencrypted, isselectioncolumn,
             ad_element_id, issyncdatabase, isalwaysupdateable
            )
     VALUES (50189, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:28', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:42:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Organization', 'Organizational entity within client',
             'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',
             0, 'D', 'AD_Org_ID', 50009, 19,
             10, 'N', 'N', 'Y', 'N',
             'N', 'N', 'N', 'N',
             113, 'N', 'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, istranslated, isencrypted, isselectioncolumn,
             ad_element_id, issyncdatabase, isalwaysupdateable
            )
     VALUES (50190, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:28', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:21', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Created', 'Date this record was created',
             'The Created field indicates the date that this record was created.',
             0, 'D', 'Created', 50009, 16,
             7, 'N', 'N', 'Y', 'N',
             'N', 'N', 'N', 'N',
             245, 'N', 'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, istranslated, isencrypted, isselectioncolumn,
             ad_element_id, issyncdatabase, isalwaysupdateable
            )
     VALUES (50191, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:29', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:40', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Updated', 'Date this record was updated',
             'The Updated field indicates the date that this record was updated.',
             0, 'D', 'Updated', 50009, 16,
             7, 'N', 'N', 'Y', 'N',
             'N', 'N', 'N', 'N',
             607, 'N', 'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             ad_reference_value_id, fieldlength, iskey, isparent,
             ismandatory, isupdateable, isidentifier, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50192, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:29', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:25', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Created By', 'User who created this records',
             'The Created By field indicates the user who created this record.',
             0, 'D', 'CreatedBy', 50009, 18,
             110, 10, 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 'N', 246, 'N',
             'N'
            );

INSERT INTO ad_column
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
     VALUES (50195, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:29', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:38', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Name', 'Alphanumeric identifier of the entity',
             'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',
             0, 'D', 'Name', 50009, 10,
             100, 'N', 'N', 'Y', 'Y',
             'Y', 1, 'N', 'N',
             'N', 469, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             ad_reference_value_id, fieldlength, iskey, isparent,
             ismandatory, isupdateable, isidentifier, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50193, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:29', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:41', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Updated By', 'User who updated this records',
             'The Updated By field indicates the user who updated this record.',
             0, 'D', 'UpdatedBy', 50009, 18,
             110, 10, 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 'N', 608, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, istranslated, isencrypted, isselectioncolumn,
             ad_element_id, issyncdatabase, isalwaysupdateable
            )
     VALUES (50194, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:29', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:36', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Active', 'The record is active in the system',
             'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',
             0, 'D', 'IsActive', 50009, 20,
             1, 'N', 'N', 'Y', 'Y',
             'N', 'N', 'N', 'N',
             348, 'N', 'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME,
             description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, istranslated, isencrypted, isselectioncolumn,
             ad_element_id, issyncdatabase, isalwaysupdateable
            )
     VALUES (50196, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:30', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:43', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Search Key',
             'Search key for the record in the format required - must be unique',
             'A search key allows you a fast method of finding a particular record.

If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).',
             0, 'D', 'Value', 50009, 10,
             255, 'N', 'N', 'Y', 'Y',
             'N', 'N', 'N', 'N',
             620, 'N', 'N'
            );
INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP, VERSION, entitytype,
             columnname, ad_table_id, ad_reference_id, fieldlength, iskey,
             isparent, ismandatory, isupdateable, isidentifier, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50197, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:41:30', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 01:43:33', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Description', 'Optional short description of the record',
             'A description is limited to 255 characters.', 0, 'D',
             'Description', 50009, 10, 255, 'N',
             'N', 'N', 'Y', 'N', 'N',
             'N', 'N', 275, 'N',
             'N'
            );

INSERT INTO ad_tab
            (ad_tab_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, ad_table_id, ad_window_id, seqno, tablevel, issinglerow,
             isinfotab, istranslationtab, isreadonly, hastree, processing,
             importfields, issorttab, entitytype, isinsertrecord,
             isadvancedtab
            )
     VALUES (50009, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:45:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:45:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'System Configurator', 50009, 50006, 10, 0, 'N',
             'N', 'N', 'N', 'N', 'N',
             'N', 'N', 'D', 'Y',
             'N'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (50162, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:46:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Client', 'Client/Tenant for this installation.',
             'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',
             'Y', 50009, 50188, 'Y',
             10, 'N', 10, 'N', 'N',
             'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (50161, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:46:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Active', 'The record is active in the system',
             'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',
             'Y', 50009, 50194, 'Y',
             1, 'N', 60, 'N', 'N',
             'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (50166, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Search Key',
             'Search key for the record in the format required - must be unique',
             'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).',
             'Y', 50009, 50196, 'Y',
             255, 'N', 30, 'N', 'N',
             'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP, iscentrallymaintained, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50163, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Description', 'Optional short description of the record',
             'A description is limited to 255 characters.', 'Y', 50009,
             50197, 'Y', 255, 'N', 50,
             'N', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (50165, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:46:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Organization', 'Organizational entity within client',
             'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',
             'Y', 50009, 50189, 'Y',
             10, 'N', 20, 'Y', 'N',
             'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (50164, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Name', 'Alphanumeric identifier of the entity',
             'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',
             'Y', 50009, 50195, 'Y',
             100, 'N', 40, 'N', 'N',
             'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             isdisplayed, displaylength, isreadonly, seqno, issameline,
             isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50167, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:46:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'System Configurator', 'Y', 50009, 50187,
             'N', 10, 'N', 0, 'N',
             'N', 'N', 'N', 'D'
            );

INSERT INTO ad_menu
            (ad_menu_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated,
             NAME, updatedby, issummary, issotrx, isreadonly, action,
             ad_window_id, entitytype
            )
     VALUES (50008, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:54', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 01:46:54', 'MM/DD/YYYY HH24:MI:SS'),
             'System Configurator', 100, 'N', 'N', 'N', 'W',
             50006, 'D'
            );

INSERT INTO ad_treenodemm
            (ad_tree_id, node_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             parent_id, seqno
            )
     VALUES (10, 50008, 0, 0, 'Y',
             TO_DATE ('02/28/2007 01:46:53', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('02/28/2007 01:47:50', 'MM/DD/YYYY HH24:MI:SS'), 0,
             161, 17
            );

COMMIT ;

