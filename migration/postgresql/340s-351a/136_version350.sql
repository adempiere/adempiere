UPDATE AD_SYSTEM
   SET releaseno = '350',
       VERSION = '2008-03-26'
 WHERE ad_system_id = 0 AND ad_client_id = 0;

COMMIT ;
