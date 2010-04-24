-- 23-abr-2010 22:55:57 CDT
-- fix default value
UPDATE AD_Column SET DefaultValue=NULL, ValueMax=NULL,Updated=TO_TIMESTAMP('2010-04-23 22:55:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53954
;

-- 23-abr-2010 22:56:00 CDT
-- fix default value
INSERT INTO t_alter_column values('dd_orderline','QtyEntered','NUMERIC',null,'NULL')
;

-- 23-abr-2010 22:56:19 CDT
-- fix default value
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2010-04-23 22:56:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53927
;

-- 23-abr-2010 22:56:23 CDT
-- fix default value
INSERT INTO t_alter_column values('dd_orderline','QtyInTransit','NUMERIC',null,'NULL')
;

-- 23-abr-2010 22:56:34 CDT
-- fix default value
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2010-04-23 22:56:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53928
;

-- 23-abr-2010 22:56:37 CDT
-- fix default value
INSERT INTO t_alter_column values('dd_orderline','QtyOrdered','NUMERIC',null,'NULL')
;
