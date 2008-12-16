INSERT INTO ad_message
            (ad_message_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, VALUE,
             msgtext, msgtip, msgtype, entitytype
            )
     VALUES (50007, 0, 0, 'Y', TO_DATE ('12-02-2007', 'DD-MM-RRRR'),
             0, TO_DATE ('12-02-2007', 'DD-MM-RRRR'), 0, 'Charset',
             'Charset', 'Charset used for import / export', 'I', 'D'
            );

COMMIT ;

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_message_id) + 1
                           FROM ad_message
                          WHERE ad_message_id < 1000000)
 WHERE NAME = 'AD_Message';

COMMIT ;
