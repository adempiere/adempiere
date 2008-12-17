-- Mar 6, 2008 9:59:53 PM COT
-- [ 1909248 ] Translation columns different from original
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-03-06 21:59:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14614
;

insert into t_alter_column values('r_mailtext_trl','MailHeader','VARCHAR(2000)',null,'NULL')
;

insert into t_alter_column values('r_mailtext_trl','MailHeader',null,'NULL',null)
;

UPDATE AD_Column SET FieldLength=0, IsMandatory='N',Updated=TO_TIMESTAMP('2008-03-06 22:16:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15268
;

insert into t_alter_column values('cm_container_element_trl','ContentHTML','TEXT',null,'NULL')
;

insert into t_alter_column values('cm_container_element_trl','ContentHTML',null,'NULL',null)
;

UPDATE AD_Column SET FieldLength=0, IsMandatory='N',Updated=TO_TIMESTAMP('2008-03-06 22:17:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15380
;

insert into t_alter_column values('cm_cstage_element_trl','ContentHTML','TEXT',null,'NULL')
;

insert into t_alter_column values('cm_cstage_element_trl','ContentHTML',null,'NULL',null)
;

UPDATE AD_Column SET FieldLength=510,Updated=TO_TIMESTAMP('2008-03-06 22:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=1410
;

UPDATE AD_Column SET FieldLength=510,Updated=TO_TIMESTAMP('2008-03-06 22:21:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3330
;

insert into t_alter_column values('m_product_trl','Name','VARCHAR(510)',null,'NULL')
;

UPDATE AD_Column SET FieldLength=60,Updated=TO_TIMESTAMP('2008-03-06 22:26:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=1410
;

UPDATE AD_Column SET FieldLength=60,Updated=TO_TIMESTAMP('2008-03-06 22:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3330
;

UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-03-06 22:26:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2596
;

insert into t_alter_column values('ad_element','AD_Org_ID','NUMERIC(10)',null,'NULL')
;

insert into t_alter_column values('ad_element','AD_Org_ID',null,'NOT NULL',null)
;

insert into t_alter_column values('ad_element','Created','TIMESTAMP',null,'NULL')
;

insert into t_alter_column values('ad_element','Created','TIMESTAMP',null,'NULL')
;

UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-03-06 22:28:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2599
;

insert into t_alter_column values('ad_element','CreatedBy','NUMERIC(10)',null,'NULL')
;

insert into t_alter_column values('ad_element','CreatedBy',null,'NOT NULL',null)
;

insert into t_alter_column values('ad_element','Name','VARCHAR(60)',null,'NULL')
;

insert into t_alter_column values('ad_element','Name',null,'NOT NULL',null)
;

UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-03-06 22:28:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2601
;

insert into t_alter_column values('ad_element','UpdatedBy','NUMERIC(10)',null,'NULL')
;

insert into t_alter_column values('ad_element','UpdatedBy',null,'NOT NULL',null)
;

UPDATE AD_Column SET FieldLength=60,Updated=TO_TIMESTAMP('2008-03-06 22:30:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14613
;

UPDATE AD_Column SET FieldLength=0,Updated=TO_TIMESTAMP('2008-03-06 22:30:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15380
;

UPDATE AD_Column SET FieldLength=0,Updated=TO_TIMESTAMP('2008-03-06 22:31:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15268
;

UPDATE AD_Column SET FieldLength=0,Updated=TO_TIMESTAMP('2008-03-06 22:31:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15268
;

UPDATE AD_Column SET FieldLength=0,Updated=TO_TIMESTAMP('2008-03-06 22:32:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15366
;

UPDATE AD_Column SET FieldLength=0,Updated=TO_TIMESTAMP('2008-03-06 22:32:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15257
;

UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-03-06 22:34:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15394
;

UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-03-06 22:34:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15588
;

UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-03-06 22:34:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15589
;

UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-03-06 22:35:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15307
;

UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-03-06 22:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15308
;