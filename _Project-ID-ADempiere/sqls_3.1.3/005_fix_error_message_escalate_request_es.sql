UPDATE ad_message_trl
   SET msgtext = 'Escalada Solicitud {0} a {1}'
 WHERE ad_language = 'es_MX' AND ad_message_id = 485;

COMMIT ;
