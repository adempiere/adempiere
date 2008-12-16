INSERT INTO ad_message_trl
            (ad_message_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, msgtext, msgtip,
             istranslated)
   SELECT m.ad_message_id, lang.ad_language, m.ad_client_id, m.ad_org_id, 'Y',
          m.created, m.createdby, m.updated, m.updatedby, m.msgtext, m.msgtip,
          'N'
     FROM ad_message m, ad_language lang
    WHERE m.ad_message_id = 50007
      AND lang.issystemlanguage = 'Y'
      AND lang.isbaselanguage = 'N'
      AND NOT EXISTS (
             SELECT *
               FROM ad_message_trl m2
              WHERE m2.ad_message_id = m.ad_message_id
                AND m2.ad_language = lang.ad_language);

COMMIT ;
