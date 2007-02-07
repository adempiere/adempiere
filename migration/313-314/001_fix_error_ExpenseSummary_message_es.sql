UPDATE ad_message_trl
   SET msgtext = '{0} Línea(s) - Total: {1,number,#,##0.00} {2}'
 WHERE ad_language = 'es_MX' AND ad_message_id = 623;

COMMIT ;
