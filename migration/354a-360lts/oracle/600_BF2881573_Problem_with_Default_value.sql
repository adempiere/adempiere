-- 17.10.2009 15:16:29 EEST
-- libero: create missing fields
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_DATE('2009-10-17 15:16:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53390
;

-- 17.10.2009 15:16:58 EEST
-- libero: create missing fields
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_DATE('2009-10-17 15:16:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53400
;

-- 17.10.2009 15:19:39 EEST
-- libero: create missing fields
ALTER TABLE PP_Product_Planning MODIFY S_Resource_ID NUMBER(10) DEFAULT NULL 
;

-- 17.10.2009 15:20:08 EEST
-- libero: create missing fields
ALTER TABLE PP_Product_Planning MODIFY M_Warehouse_ID NUMBER(10) DEFAULT NULL 
;

