UPDATE AD_SYSTEM
   SET releaseno = '340s',
       VERSION = '2008-03-26'
 WHERE ad_system_id = 0 AND ad_client_id = 0;

COMMIT ;
