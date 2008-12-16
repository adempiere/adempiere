UPDATE AD_SYSTEM
   SET releaseno = '320',
       VERSION = '2007-05-01'
 WHERE ad_system_id = 0 AND ad_client_id = 0;

COMMIT ;
