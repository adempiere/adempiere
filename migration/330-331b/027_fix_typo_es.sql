UPDATE AD_WINDOW_TRL
   SET HELP =
          'La terminal de PDV define los datos por omisión y las funciones disponibles para las formas de PDV.'
 WHERE ad_window_id = 338
   AND HELP LIKE
          '%La terminal de PDV define los datos por homición y las funciones disponibles para las formas de PDV.%'
   AND AD_LANGUAGE LIKE 'es_%';

COMMIT ;
