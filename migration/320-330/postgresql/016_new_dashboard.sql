-- create new table

CREATE TABLE PA_DASHBOARDCONTENT
(
  pa_dashboardcontent_id  NUMERIC(10)            NOT NULL,
  ad_client_id            NUMERIC(10)            NOT NULL,
  ad_org_id               NUMERIC(10)            NOT NULL,
  created                 TIMESTAMP                  NOT NULL,
  createdby               NUMERIC(10)            NOT NULL,
  updated                 TIMESTAMP                  NOT NULL,
  updatedby               NUMERIC(10)            NOT NULL,
  isactive                CHAR(1)          NOT NULL,
  NAME                    VARCHAR(120)        NOT NULL,
  ad_window_id            NUMERIC(10),
  description             VARCHAR(255),
  html                    TEXT,
  line                    NUMERIC,
  pa_goal_id              NUMERIC(10),
  CHECK (isactive IN ('Y','N')),
  CONSTRAINT pa_dashboardcontent_key
 PRIMARY KEY
 (pa_dashboardcontent_id)
);

-- dictionary additions

-- new table

INSERT INTO AD_TABLE
            (ad_table_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, tablename, isview, accesslevel, entitytype,
             issecurityenabled, isdeleteable, ishighvolume, importtable,
             ischangelog, replicationtype
            )
     VALUES (50010, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:22:49', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:22:49', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Dashboard Content', 'PA_DashboardContent', 'N', '1', 'D',
             'N', 'Y', 'N', 'N',
             'N', 'L'
            );

-- new sequence

INSERT INTO AD_SEQUENCE
            (ad_sequence_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, isautosequence, incrementno,
             startno, currentnext, currentnextsys, isaudited, istableid,
             startnewyear
            )
     VALUES (50015, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:22:49', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:22:49', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'PA_DashboardContent', 'Table PA_DashboardContent', 'Y', 1,
             1000000, 1000000, 50000, 'N', 'Y',
             'N'
            );

-- new elements

INSERT INTO AD_ELEMENT
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME, printname
            )
     VALUES (51005, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'HTML', 'D', 'HTML', 'HTML'
            );

INSERT INTO AD_ELEMENT
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (51006, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:07', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:07', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'PA_DashboardContent_ID', 'D', 'PA_DashboardContent_ID',
             'PA_DashboardContent_ID'
            );

-- new columns

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
     VALUES (51005, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Name', 'Alphanumeric identifier of the entity',
             'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',
             0, 'D', 'Name', 50010, 10,
             120, 'N', 'N', 'Y', 'Y',
             'Y', 1, 'N', 'N',
             'N', 469, 'Y',
             'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51006, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:22:53', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:22:53', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Organization', 'Organizational entity within client',
             'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',
             0, 'D', 'AD_Org_ID', 50010, 19,
             10, 'N', 'N', 'Y', 'N',
             'N', 'N', 'N', 'N',
             113, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, istranslated, isencrypted, isselectioncolumn,
             ad_element_id, issyncdatabase, isalwaysupdateable
            )
     VALUES (51007, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:22:54', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:22:54', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Window', 'Data entry or display window',
             'The Window field identifies a unique Window in the system.', 0,
             'D', 'AD_Window_ID', 50010, 19,
             22, 'N', 'N', 'N', 'Y',
             'N', 'N', 'N', 'N',
             143, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51008, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:22:55', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:22:55', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Created', 'Date this record was created',
             'The Created field indicates the date that this record was created.',
             0, 'D', 'Created', 50010, 16,
             7, 'N', 'N', 'Y', 'N',
             'N', 'N', 'N', 'N',
             245, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51009, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:22:57', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:22:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Created By', 'User who created this records',
             'The Created By field indicates the user who created this record.',
             0, 'D', 'CreatedBy', 50010, 18,
             110, 10, 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 'N', 246, 'Y',
             'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51010, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:22:59', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:22:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Description', 'Optional short description of the record',
             'A description is limited to 255 characters.', 0, 'D',
             'Description', 50010, 10, 255, 'N',
             'N', 'N', 'Y', 'N', 'N',
             'N', 'N', 275, 'Y',
             'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51011, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:23:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Updated By', 'User who updated this records',
             'The Updated By field indicates the user who updated this record.',
             0, 'D', 'UpdatedBy', 50010, 18,
             110, 10, 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 'N', 608, 'Y',
             'N'
            );

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, iskey, isparent, ismandatory, isupdateable,
             isidentifier, istranslated, isencrypted, isselectioncolumn,
             ad_element_id, issyncdatabase, isalwaysupdateable
            )
     VALUES (51012, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:01', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:23:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'HTML', 0, 'D', 'HTML', 50010,
             36, 'N', 'N', 'N', 'Y',
             'N', 'N', 'N', 'N',
             51005, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51013, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:03', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:23:03', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Active', 'The record is active in the system',
             'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.',
             0, 'D', 'IsActive', 50010, 20,
             1, 'N', 'N', 'Y', 'Y',
             'N', 'N', 'N', 'N',
             348, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51014, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:04', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:23:04', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Line No', 'Unique line for this document',
             'Indicates the unique line for a document.  It will also control the display order of the lines within a document.',
             0, 'D', 'Line', 50010, 22,
             22, 'N', 'N', 'N', 'Y',
             'N', 'N', 'N', 'N',
             439, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51015, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:05', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:23:05', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Goal', 'Performance Goal',
             'The Performance Goal indicates what this users performance will be measured against.',
             0, 'D', 'PA_Goal_ID', 50010, 19,
             22, 'N', 'N', 'N', 'Y',
             'N', 'N', 'N', 'N',
             1594, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51016, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:06', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:23:06', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Updated', 'Date this record was updated',
             'The Updated field indicates the date that this record was updated.',
             0, 'D', 'Updated', 50010, 16,
             7, 'N', 'N', 'Y', 'N',
             'N', 'N', 'N', 'N',
             607, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
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
     VALUES (51017, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:07', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:23:07', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Client', 'Client/Tenant for this installation.',
             'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',
             0, 'D', 'AD_Client_ID', 50010, 19,
             10, 'N', 'N', 'Y', 'N',
             'N', 'N', 'N', 'N',
             102, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname,
             ad_table_id, ad_reference_id, fieldlength, iskey, isparent,
             ismandatory, isupdateable, isidentifier, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (51018, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:07', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/09/2007 14:23:07', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'PA_DashboardContent_ID', 0, 'D', 'PA_DashboardContent_ID',
             50010, 13, 10, 'Y', 'N',
             'Y', 'N', 'N', 'N',
             'N', 'N', 51006, 'Y',
             'N'
            );

-- new window			

INSERT INTO AD_WINDOW
            (ad_window_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, windowtype, issotrx, entitytype, processing, isdefault,
             isbetafunctionality
            )
     VALUES (50007, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Dashboard Content Edit', 'M', 'Y', 'D', 'N', 'N',
             'N'
            );

-- new menu

INSERT INTO AD_MENU
            (ad_menu_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated,
             NAME, updatedby, issummary, issotrx, isreadonly, action,
             ad_window_id, entitytype
            )
     VALUES (50010, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:30', 'MM/DD/YYYY HH24:MI:SS'),
             'Dashboard Content Edit', 100, 'N', 'N', 'N', 'W',
             50007, 'D'
            );

-- new menu in tree

INSERT INTO AD_TREENODEMM
            (ad_tree_id, node_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             parent_id, seqno
            )
     VALUES (10, 50010, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:35', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('07/09/2007 14:23:35', 'MM/DD/YYYY HH24:MI:SS'), 0,
             175, 0
            );

-- access to new window

INSERT INTO AD_WINDOW_ACCESS
            (ad_window_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50007, 0, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO AD_WINDOW_ACCESS
            (ad_window_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50007, 102, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO AD_WINDOW_ACCESS
            (ad_window_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50007, 103, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO AD_WINDOW_ACCESS
            (ad_window_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50007, 50001, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

-- new tab

INSERT INTO AD_TAB
            (ad_tab_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, ad_table_id, ad_window_id, seqno, tablevel, issinglerow,
             isinfotab, istranslationtab, isreadonly, hastree, processing,
             issorttab, entitytype, isinsertrecord, isadvancedtab
            )
     VALUES (50010, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:08', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Edit', 50010, 50007, 10, 0, 'Y',
             'N', 'N', 'N', 'N', 'N',
             'N', 'D', 'Y', 'N'
            );

-- new fields

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, sortno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (51005, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Client', 'Client/Tenant for this installation.',
             'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',
             'Y', 50010, 51017, 'Y',
             10, 'N', 10, 0, 'N', 'N',
             'N', 'N', 'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, sortno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (51006, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:18', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Organization', 'Organizational entity within client',
             'An organization is a unit of your client or legal entity - examples are store department. You can share data between organizations.',
             'Y', 50010, 51006, 'Y',
             10, 'N', 20, 0, 'Y', 'N',
             'N', 'N', 'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, sortno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (51007, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:20', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Name', 'Alphanumeric identifier of the entity',
             'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',
             'Y', 50010, 51005, 'Y',
             120, 'N', 30, 0, 'N', 'N',
             'N', 'N', 'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, sortno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (51008, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:22', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Active', 'The record is active in the system',
             'There are two methods of making records unavailable in the system: One is to delete the record the other is to de-activate the record. A de-activated record is not available for selection but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g. you cannot DELETE a Business Partner IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.',
             'Y', 50010, 51013, 'Y',
             1, 'N', 40, 0, 'N', 'N',
             'N', 'N', 'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, sortno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (51009, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:10', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:23', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Line No', 'Unique line for this document',
             'Indicates the unique line for a document.  It will also control the display order of the lines within a document.',
             'Y', 50010, 51014, 'Y',
             22, 'N', 50, 0, 'Y', 'N',
             'N', 'N', 'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP, iscentrallymaintained, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly, seqno,
             sortno, issameline, isheading, isfieldonly, isencrypted,
             entitytype
            )
     VALUES (51010, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:24', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Description', 'Optional short description of the record',
             'A description is limited to 255 characters.', 'Y', 50010,
             51010, 'Y', 255, 'N', 60,
             0, 'N', 'N', 'N', 'N',
             'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             isdisplayed, displaylength, isreadonly, seqno, sortno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (51011, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:26', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'HTML', 'Y', 50010, 51012,
             'Y', 0, 'N', 70, 0,
             'N', 'N', 'N', 'N', 'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, sortno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (51012, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:27', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Goal', 'Performance Goal',
             'The Performance Goal indicates what this users performance will be measured against.',
             'Y', 50010, 51015, 'Y',
             22, 'N', 80, 0, 'N', 'N',
             'N', 'N', 'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, sortno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (51013, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Window', 'Data entry or display window',
             'The Window field identifies a unique Window in the system.',
             'Y', 50010, 51007, 'Y',
             22, 'N', 90, 0, 'Y', 'N',
             'N', 'N', 'D'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             isdisplayed, displaylength, isreadonly, seqno, sortno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (51014, 0, 0, 'Y',
             TO_DATE ('07/09/2007 14:23:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/09/2007 14:23:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Dashboard Content', 'Y', 50010, 51018,
             'N', 10, 'N', 0, 0,
             'N', 'N', 'N', 'N', 'D'
            );

-- assign window to new table

UPDATE AD_TABLE
   SET ad_window_id = 50007
 WHERE ad_table_id = 50010;


-- NOTE: Don't forget to run the three processes:
-- 1 - Add missing translations
-- 2 - Synchronize terminology
-- 3 - Check sequences
COMMIT ;
