-- Jun 14, 2010 4:46:33 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2011-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=9328
;

UPDATE AD_SYSTEM
 SET releaseno = '3.7.0LTS', VERSION = '2011-09-01' 
  WHERE ad_system_id = 0 AND ad_client_id = 0;
