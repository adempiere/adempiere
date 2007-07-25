INSERT INTO ad_message
	(ad_message_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             value, msgtext, msgtype
            )
     VALUES (50017, 0, 0, 'Y',
             TO_TIMESTAMP ('06/12/2007 18:00:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_TIMESTAMP ('06/12/2007 18:00:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'PrintPreview','Print preview','I'
            );     

COMMIT ;            
 
 UPDATE ad_sequence
    SET currentnextsys = (SELECT MAX (ad_message_id) + 1
                            FROM ad_message
                           WHERE ad_message_id < 1000000)
 WHERE NAME = 'AD_Message';

COMMIT ;
