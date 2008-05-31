-- May 14, 2008 11:24:14 AM EST
-- BF1939035 AD_Rule.Script
UPDATE AD_Column SET FieldLength=2000,Updated=TO_TIMESTAMP('2008-05-14 11:24:14','YYYY-MM-DD HH24:MI:ss'),UpdatedBy=100 WHERE AD_Column_ID=54257;

INSERT INTO t_alter_column values('ad_rule','Script','VARCHAR(2000)',null,'NULL');
