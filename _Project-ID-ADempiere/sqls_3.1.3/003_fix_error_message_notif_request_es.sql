UPDATE ad_message_trl
   SET msgtext = 'Solicitud {0} fue transferida por {1} de {2} a {3}'
 WHERE ad_language = 'es_MX' AND ad_message_id = 483;

COMMIT ;
