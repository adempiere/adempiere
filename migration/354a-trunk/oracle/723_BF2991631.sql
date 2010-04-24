-- 23-abr-2010 22:55:57 CDT
-- fix default value
UPDATE AD_Column SET DefaultValue=NULL, ValueMax=NULL,Updated=TO_DATE('2010-04-23 22:55:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53954
;

-- 23-abr-2010 22:56:00 CDT
-- fix default value
ALTER TABLE DD_OrderLine MODIFY QtyEntered NUMBER DEFAULT NULL 
;

-- 23-abr-2010 22:56:19 CDT
-- fix default value
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_DATE('2010-04-23 22:56:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53927
;

-- 23-abr-2010 22:56:23 CDT
-- fix default value
ALTER TABLE DD_OrderLine MODIFY QtyInTransit NUMBER DEFAULT NULL 
;

-- 23-abr-2010 22:56:34 CDT
-- fix default value
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_DATE('2010-04-23 22:56:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53928
;

-- 23-abr-2010 22:56:37 CDT
-- fix default value
ALTER TABLE DD_OrderLine MODIFY QtyOrdered NUMBER DEFAULT NULL 
;


