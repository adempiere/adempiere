-- [ 1789836 ] Add fields to Import Invoice - allow import charges
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1789836&group_id=176962&atid=879335

ALTER TABLE I_INVOICE ADD COLUMN projectvalue VARCHAR(40) NULL;

ALTER TABLE I_INVOICE ADD COLUMN activityvalue VARCHAR(40) NULL;

ALTER TABLE I_INVOICE ADD COLUMN chargename VARCHAR(60) NULL;

ALTER TABLE I_INVOICE ADD COLUMN c_charge_id NUMERIC(10,0) NULL;

INSERT INTO AD_ELEMENT
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME, printname
            )
     VALUES (53222, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'ActivityValue', 'U', 'ActivityValue', 'ActivityValue'
            );

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, VERSION, entitytype,
             columnname, ad_table_id, ad_reference_id, fieldlength, iskey,
             isparent, ismandatory, isupdateable, isidentifier, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (53241, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'),
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Project Key', 'Key of the Project', 0, 'U',
             'ProjectValue', 598, 10, 40, 'N',
             'N', 'N', 'Y', 'N', 'N',
             'N', 'N', 2118, 'Y',
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
     VALUES (53242, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'),
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Charge', 'Additional document charges',
             'The Charge indicates a type of Charge (Handling, Shipping, Restocking)',
             0, 'U', 'C_Charge_ID', 598, 19,
             10, 'N', 'N', 'N', 'Y',
             'N', 'N', 'N', 'N',
             968, 'Y', 'N'
            );

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (53243, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'),
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'ActivityValue', 0, 'U', 'ActivityValue', 598,
             10, 40, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 53222, 'Y',
             'N'
            );

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, VERSION, entitytype, columnname,
             ad_table_id, ad_reference_id, fieldlength, iskey, isparent,
             ismandatory, isupdateable, isidentifier, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (53244, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:46', 'MM/DD/YYYY HH24:MI:SS'),
             TO_TIMESTAMP ('09/04/2007 22:54:46', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Charge Name', 'Name of the Charge', 0, 'U', 'ChargeName',
             598, 10, 60, 'N', 'N',
             'N', 'Y', 'N', 'N',
             'N', 'N', 2096, 'Y',
             'N'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             isdisplayed, displaylength, isreadonly, seqno, sortno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (53251, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'ActivityValue', 'Y', 510, 53243,
             'Y', 40, 'N', 498, 0,
             'N', 'N', 'N', 'N', 'U'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, iscentrallymaintained, ad_tab_id,
             ad_column_id, ad_fieldgroup_id, isdisplayed, displaylength,
             isreadonly, seqno, sortno, issameline, isheading, isfieldonly,
             isencrypted, entitytype
            )
     VALUES (53252, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Project Key', 'Key of the Project', 'Y', 510,
             53241, 104, 'Y', 40,
             'N', 478, 0, 'N', 'N', 'N',
             'N', 'U'
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
     VALUES (53253, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Charge', 'Additional document charges',
             'The Charge indicates a type of Charge (Handling, Shipping, Restocking)',
             'Y', 510, 53242, 'Y',
             10, 'N', 392, 0, 'N', 'N',
             'N', 'N', 'U'
            );

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, iscentrallymaintained, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly, seqno,
             sortno, issameline, isheading, isfieldonly, isencrypted,
             entitytype
            )
     VALUES (53254, 0, 0, 'Y',
             TO_TIMESTAMP ('09/04/2007 22:54:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_TIMESTAMP ('09/04/2007 22:57:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Charge Name', 'Name of the Charge', 'Y', 510,
             53244, 'Y', 40, 'N', 394,
             0, 'Y', 'N', 'N', 'N',
             'U'
            );

