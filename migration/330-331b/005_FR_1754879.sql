INSERT INTO AD_MESSAGE
            (ad_message_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             VALUE, msgtext, msgtype, entitytype
            )
     VALUES (50018, 0, 0, 'Y',
             TO_DATE ('08/02/2007 00:15:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('08/02/2007 00:15:12', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'CC', 'CC', 'M', 'D'
            );

COMMIT ;
