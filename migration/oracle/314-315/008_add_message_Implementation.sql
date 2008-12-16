INSERT INTO ad_message
        (ad_message_id, ad_client_id, ad_org_id, isactive, created,
         createdby, updated, updatedby, VALUE,
         msgtext, msgtip, msgtype, entitytype
        )
    values (
        50008,0,0,'Y',to_date('26-02-2007','DD-MM-RRRR'),
        0,to_date('26-02-2007','DD-MM-RRRR'),0,'ImplementationVendor',
        'Implementation Vendor',null,'I','D'
    );

INSERT INTO ad_message
        (ad_message_id, ad_client_id, ad_org_id, isactive, created,
         createdby, updated, updatedby, VALUE,
         msgtext, msgtip, msgtype, entitytype
        )
    values (
        50009,0,0,'Y',to_date('26-02-2007','DD-MM-RRRR'),
        0,to_date('26-02-2007','DD-MM-RRRR'),0,'ImplementationVersion',
        'Implementation Version',null,'I','D'
    );

INSERT INTO ad_message_trl
            (ad_message_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, msgtext, msgtip,
             istranslated)
   SELECT m.ad_message_id, lang.ad_language, m.ad_client_id, m.ad_org_id, 'Y',
          m.created, m.createdby, m.updated, m.updatedby, m.msgtext, m.msgtip,
          'N'
     FROM ad_message m, ad_language lang
    WHERE m.ad_message_id in (50008, 50009)
      AND lang.issystemlanguage = 'Y'
      AND lang.isbaselanguage = 'N'
      AND NOT EXISTS (
             SELECT *
               FROM ad_message_trl m2
              WHERE m2.ad_message_id = m.ad_message_id
                AND m2.ad_language = lang.ad_language);

COMMIT ;

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_message_id) + 1
                           FROM ad_message
                          WHERE ad_message_id < 1000000)
 WHERE NAME = 'AD_Message';

COMMIT ;
