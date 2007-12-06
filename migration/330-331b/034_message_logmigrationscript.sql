-- Nov 11, 2007 1:54:50 AM COT
-- FR 1829798 - Easy generation of migration scripts
INSERT INTO AD_MESSAGE
            (ad_client_id, ad_message_id, ad_org_id,
             created, createdby,
             entitytype, isactive, msgtext,
             msgtip,
             msgtype, updated,
             updatedby, VALUE
            )
     VALUES (0, 53007, 0,
             TO_DATE ('2007-11-11 01:54:49', 'YYYY-MM-DD HH24:MI:SS'), 100,
             'D', 'Y', 'Log Migration Script',
             'Log Migration Script - Save migration scripts file in %TEMP%/migration_script_*.sql',
             'I', TO_DATE ('2007-11-11 01:54:49', 'YYYY-MM-DD HH24:MI:SS'),
             100, 'LogMigrationScript'
            )
/
-- Nov 11, 2007 1:54:50 AM COT
-- FR 1829798 - Easy generation of migration scripts
INSERT INTO AD_MESSAGE_TRL
            (AD_LANGUAGE, ad_message_id, msgtext, msgtip, istranslated,
             ad_client_id, ad_org_id, created, createdby, updated, updatedby)
   SELECT l.AD_LANGUAGE, t.ad_message_id, t.msgtext, t.msgtip, 'N',
          t.ad_client_id, t.ad_org_id, t.created, t.createdby, t.updated,
          t.updatedby
     FROM AD_LANGUAGE l, AD_MESSAGE t
    WHERE l.isactive = 'Y'
      AND l.issystemlanguage = 'Y'
      AND l.isbaselanguage = 'N'
      AND t.ad_message_id = 53007
      AND EXISTS (
             SELECT *
               FROM AD_MESSAGE_TRL tt
              WHERE tt.AD_LANGUAGE != l.AD_LANGUAGE
                 OR tt.ad_message_id != t.ad_message_id)
/
