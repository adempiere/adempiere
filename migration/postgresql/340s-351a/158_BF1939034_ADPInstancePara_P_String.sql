-- May 14, 2008 01:12:40 PM EST
-- Change P_String and P_String_To
UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2008-05-14 13:12:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2791
;

UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2008-05-14 13:12:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2792
;

insert into t_alter_column values('ad_pinstance_para','P_String','VARCHAR(255)',null,'NULL')
;

insert into t_alter_column values('ad_pinstance_para','P_String_To','VARCHAR(255)',null,'NULL')
;
