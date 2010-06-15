-- Jun 14, 2010 4:46:33 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2010-06-14 16:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=9328
;

-- Jun 14, 2010 4:46:36 PM COT
-- Preparing release 3.6.0LTS
INSERT INTO t_alter_column values('ad_system','ReleaseNo','VARCHAR(10)',null,'NULL')
;

-- Jun 14, 2010 4:46:37 PM COT
-- Preparing release 3.6.0LTS
INSERT INTO t_alter_column values('ad_system','ReleaseNo',null,'NULL',null)
;

UPDATE AD_SYSTEM
 SET releaseno = '3.6.0LTS', VERSION = '2010-06-14' 
  WHERE ad_system_id = 0 AND ad_client_id = 0;
