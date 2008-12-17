UPDATE AD_SYSTEM
   SET releaseno = '330',
       VERSION = '2007-07-13'
 WHERE ad_system_id = 0 AND ad_client_id = 0;

COMMIT ;
