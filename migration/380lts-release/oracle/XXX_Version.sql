
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2014-04-30 00:00:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=9328
;

UPDATE AD_SYSTEM
 SET releaseno = '3.8.0LTS', VERSION = '2014-04-30'
  WHERE ad_system_id = 0 AND ad_client_id = 0;
