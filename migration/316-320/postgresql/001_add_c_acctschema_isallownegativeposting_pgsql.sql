-- ADD C_AcctSchema.IsAllowNegativePosting - postgres script

ALTER TABLE C_ACCTSCHEMA ADD COLUMN IsAllowNegativePosting CHAR(1) DEFAULT 'Y' CHECK (IsAllowNegativePosting IN ('Y','N'));

INSERT INTO AD_ELEMENT
            (ad_org_id, ad_element_id, columnname,
             created,
             createdby, description, entitytype, isactive,
             NAME, printname,
             updated,
             ad_client_id, updatedby
            )
     VALUES (0, 50065, 'IsAllowNegativePosting',
             TO_TIMESTAMP ('2007-04-03 18:17:38', 'YYYY-MM-DD HH24:MI:SS'),
             100, 'Allow to post negative accounting values', 'D', 'Y',
             'Allow Negative Posting', 'Allow Negative Posting',
             TO_TIMESTAMP ('2007-04-03 18:17:38', 'YYYY-MM-DD HH24:MI:SS'),
             0, 100
            );

INSERT INTO AD_COLUMN
            (ad_org_id, ad_element_id, ad_reference_id, ad_table_id,
             columnname,
             created,
             createdby, defaultvalue, description, entitytype, fieldlength,
             isactive, isalwaysupdateable, isencrypted, isidentifier, iskey,
             ismandatory, isparent, isselectioncolumn, issyncdatabase,
             istranslated, isupdateable, NAME, seqno,
             updated,
             updatedby, VERSION, ad_client_id, ad_column_id
            )
     VALUES (0, 50065, 20, 265,
             'IsAllowNegativePosting',
             TO_TIMESTAMP ('2007-04-03 18:20:00', 'YYYY-MM-DD HH24:MI:SS'),
             100, 'Y', 'Allow to post negative accounting values', 'D', 1,
             'Y', 'N', 'N', 'N', 'N',
             'N', 'N', 'N', 'N',
             'N', 'Y', 'Allow Negative Posting', 0,
             TO_TIMESTAMP ('2007-04-03 18:20:00', 'YYYY-MM-DD HH24:MI:SS'),
             100, 0, 0, 50210
            );

INSERT INTO AD_FIELD
            (ad_column_id, ad_org_id, ad_tab_id,
             created,
             createdby, description, displaylength, entitytype, isactive,
             iscentrallymaintained, isdisplayed, isencrypted, isfieldonly,
             isheading, isreadonly, issameline, NAME,
             updated,
             ad_client_id, updatedby, ad_field_id
            )
     VALUES (50210, 0, 199,
             TO_TIMESTAMP ('2007-04-03 18:25:16', 'YYYY-MM-DD HH24:MI:SS'),
             100, 'Allow to post negative accounting values', 1, 'D', 'Y',
             'Y', 'Y', 'N', 'N',
             'N', 'N', 'N', 'Allow Negative Posting',
             TO_TIMESTAMP ('2007-04-03 18:25:16', 'YYYY-MM-DD HH24:MI:SS'),
             0, 100, 50180
            );

UPDATE AD_FIELD
   SET seqno = 260,
       updated = TO_TIMESTAMP ('2007-04-03 18:26:28', 'YYYY-MM-DD HH24:MI:SS'),
       updatedby = 100
 WHERE ad_field_id = 12522;

UPDATE AD_FIELD
   SET issameline = 'Y',
       seqno = 250,
       updated = TO_TIMESTAMP ('2007-04-03 18:31:50', 'YYYY-MM-DD HH24:MI:SS'),
       updatedby = 100
 WHERE ad_field_id = 50180;

-- update sequences

UPDATE AD_SEQUENCE
   SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                           FROM AD_ELEMENT
                          WHERE ad_element_id < 1000000)
 WHERE NAME = 'AD_Element';

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

-- insert translation tables

INSERT INTO AD_ELEMENT_TRL
            (ad_element_id, AD_LANGUAGE, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, NAME, printname,
             description, HELP, po_name, po_printname, po_description,
             po_help, istranslated)
   SELECT e.ad_element_id, l.AD_LANGUAGE, e.ad_client_id, e.ad_org_id,
          e.isactive, e.created, e.createdby, e.updated, e.updatedby, e.NAME,
          e.printname, e.description, e.HELP, e.po_name, e.po_printname,
          e.po_description, e.po_help, 'N'
     FROM AD_ELEMENT e, AD_LANGUAGE l
    WHERE e.ad_element_id = 50065
      AND l.issystemlanguage = 'Y'
      AND l.isbaselanguage = 'N'
      AND NOT EXISTS (
             SELECT 1
               FROM AD_ELEMENT_TRL et
              WHERE et.ad_element_id = e.ad_element_id
                AND et.AD_LANGUAGE = l.AD_LANGUAGE);

INSERT INTO AD_COLUMN_TRL
            (ad_column_id, AD_LANGUAGE, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, NAME, istranslated)
   SELECT c.ad_column_id, l.AD_LANGUAGE, c.ad_client_id, c.ad_org_id,
          c.isactive, c.created, c.createdby, c.updated, c.updatedby, c.NAME,
          'N'
     FROM AD_COLUMN c, AD_LANGUAGE l
    WHERE c.ad_column_id = 50210
      AND l.issystemlanguage = 'Y'
      AND l.isbaselanguage = 'N'
      AND NOT EXISTS (
             SELECT 1
               FROM AD_COLUMN_TRL ct
              WHERE ct.ad_column_id = c.ad_column_id
                AND ct.AD_LANGUAGE = l.AD_LANGUAGE);

INSERT INTO AD_FIELD_TRL
            (ad_field_id, AD_LANGUAGE, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, NAME, description, HELP,
             istranslated)
   SELECT f.ad_field_id, l.AD_LANGUAGE, f.ad_client_id, f.ad_org_id,
          f.isactive, f.created, f.createdby, f.updated, f.updatedby, f.NAME,
          f.description, f.HELP, 'N'
     FROM AD_FIELD f, AD_LANGUAGE l
    WHERE f.ad_field_id = 50180
      AND l.issystemlanguage = 'Y'
      AND l.isbaselanguage = 'N'
      AND NOT EXISTS (
             SELECT 1
               FROM AD_FIELD_TRL ft
              WHERE ft.ad_field_id = f.ad_field_id
                AND ft.AD_LANGUAGE = l.AD_LANGUAGE);

COMMIT ;