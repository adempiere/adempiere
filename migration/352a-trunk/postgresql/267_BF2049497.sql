-- 13.08.2008 15:37:55 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:37:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50198
;

-- 13.08.2008 15:37:58 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Account','CHAR(1)',null,'N')
;

-- 13.08.2008 15:37:58 EEST
-- 
UPDATE AD_Role SET Allow_Info_Account='N' WHERE Allow_Info_Account IS NULL
;

-- 13.08.2008 15:37:58 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Account',null,'NOT NULL',null)
;

-- 13.08.2008 15:38:17 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:38:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50199
;

-- 13.08.2008 15:38:19 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Asset','CHAR(1)',null,'N')
;

-- 13.08.2008 15:38:19 EEST
-- 
UPDATE AD_Role SET Allow_Info_Asset='N' WHERE Allow_Info_Asset IS NULL
;

-- 13.08.2008 15:38:19 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Asset',null,'NOT NULL',null)
;

-- 13.08.2008 15:38:23 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:38:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50200
;

-- 13.08.2008 15:38:25 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_BPartner','CHAR(1)',null,'N')
;

-- 13.08.2008 15:38:25 EEST
-- 
UPDATE AD_Role SET Allow_Info_BPartner='N' WHERE Allow_Info_BPartner IS NULL
;

-- 13.08.2008 15:38:25 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_BPartner',null,'NOT NULL',null)
;

-- 13.08.2008 15:38:28 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:38:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50201
;

-- 13.08.2008 15:38:30 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_CashJournal','CHAR(1)',null,'N')
;

-- 13.08.2008 15:38:30 EEST
-- 
UPDATE AD_Role SET Allow_Info_CashJournal='N' WHERE Allow_Info_CashJournal IS NULL
;

-- 13.08.2008 15:38:30 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_CashJournal',null,'NOT NULL',null)
;

-- 13.08.2008 15:38:38 EEST
-- 
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:38:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=55333
;

-- 13.08.2008 15:38:40 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_CRP','CHAR(1)',null,'Y')
;

-- 13.08.2008 15:38:40 EEST
-- 
UPDATE AD_Role SET Allow_Info_CRP='Y' WHERE Allow_Info_CRP IS NULL
;

-- 13.08.2008 15:38:40 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_CRP',null,'NOT NULL',null)
;

-- 13.08.2008 15:38:47 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:38:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50202
;

-- 13.08.2008 15:38:48 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_InOut','CHAR(1)',null,'N')
;

-- 13.08.2008 15:38:48 EEST
-- 
UPDATE AD_Role SET Allow_Info_InOut='N' WHERE Allow_Info_InOut IS NULL
;

-- 13.08.2008 15:38:48 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_InOut',null,'NOT NULL',null)
;

-- 13.08.2008 15:38:54 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:38:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50203
;

-- 13.08.2008 15:38:56 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Invoice','CHAR(1)',null,'N')
;

-- 13.08.2008 15:38:56 EEST
-- 
UPDATE AD_Role SET Allow_Info_Invoice='N' WHERE Allow_Info_Invoice IS NULL
;

-- 13.08.2008 15:38:56 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Invoice',null,'NOT NULL',null)
;

-- 13.08.2008 15:39:00 EEST
-- 
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:39:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=55332
;

-- 13.08.2008 15:39:02 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_MRP','CHAR(1)',null,'Y')
;

-- 13.08.2008 15:39:02 EEST
-- 
UPDATE AD_Role SET Allow_Info_MRP='Y' WHERE Allow_Info_MRP IS NULL
;

-- 13.08.2008 15:39:02 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_MRP',null,'NOT NULL',null)
;

-- 13.08.2008 15:39:08 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:39:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50204
;

-- 13.08.2008 15:39:09 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Order','CHAR(1)',null,'N')
;

-- 13.08.2008 15:39:09 EEST
-- 
UPDATE AD_Role SET Allow_Info_Order='N' WHERE Allow_Info_Order IS NULL
;

-- 13.08.2008 15:39:09 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Order',null,'NOT NULL',null)
;

-- 13.08.2008 15:39:14 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:39:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50205
;

-- 13.08.2008 15:39:15 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Payment','CHAR(1)',null,'N')
;

-- 13.08.2008 15:39:15 EEST
-- 
UPDATE AD_Role SET Allow_Info_Payment='N' WHERE Allow_Info_Payment IS NULL
;

-- 13.08.2008 15:39:15 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Payment',null,'NOT NULL',null)
;

-- 13.08.2008 15:39:21 EEST
-- 
UPDATE AD_Column SET DefaultValue='Y', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:39:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50206
;

-- 13.08.2008 15:39:22 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Product','CHAR(1)',null,'Y')
;

-- 13.08.2008 15:39:22 EEST
-- 
UPDATE AD_Role SET Allow_Info_Product='Y' WHERE Allow_Info_Product IS NULL
;

-- 13.08.2008 15:39:22 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Product',null,'NOT NULL',null)
;

-- 13.08.2008 15:39:27 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:39:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50207
;

-- 13.08.2008 15:39:29 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Resource','CHAR(1)',null,'N')
;

-- 13.08.2008 15:39:29 EEST
-- 
UPDATE AD_Role SET Allow_Info_Resource='N' WHERE Allow_Info_Resource IS NULL
;

-- 13.08.2008 15:39:29 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Resource',null,'NOT NULL',null)
;

-- 13.08.2008 15:39:34 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-13 15:39:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=50208
;

-- 13.08.2008 15:39:35 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Schedule','CHAR(1)',null,'N')
;

-- 13.08.2008 15:39:35 EEST
-- 
UPDATE AD_Role SET Allow_Info_Schedule='N' WHERE Allow_Info_Schedule IS NULL
;

-- 13.08.2008 15:39:35 EEST
-- 
insert into t_alter_column values('ad_role','Allow_Info_Schedule',null,'NOT NULL',null)
;

