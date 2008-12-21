-- 09.12.2008 19:27:42 EET
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Val_Rule_ID=189,Updated=TO_DATE('2008-12-09 19:27:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53824
;

-- 09.12.2008 19:27:47 EET
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE PP_Cost_Collector MODIFY M_Warehouse_ID NUMBER(10) DEFAULT  NULL 
;

-- 09.12.2008 19:54:39 EET
-- [ 2412212 ] Fix Activity Control Report Window
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52035,'PP_Order_Node.PP_Order_Workflow_ID=@PP_Order_Workflow_ID@',TO_DATE('2008-12-09 19:54:37','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','PP_Order_Node of PP_Order_Workflow','S',TO_DATE('2008-12-09 19:54:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 09.12.2008 19:55:25 EET
-- [ 2412212 ] Fix Activity Control Report Window
UPDATE AD_Column SET AD_Val_Rule_ID=52035,Updated=TO_DATE('2008-12-09 19:55:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53831
;

-- 09.12.2008 19:55:29 EET
-- [ 2412212 ] Fix Activity Control Report Window
ALTER TABLE PP_Cost_Collector MODIFY PP_Order_Node_ID NUMBER(10) DEFAULT  NULL 
;

-- 09.12.2008 20:11:54 EET
-- [ 2412212 ] Fix Activity Control Report Window
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52036,'PP_Order_Workflow.PP_Order_ID=@PP_Order_ID@',TO_DATE('2008-12-09 20:11:53','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','PP_Order_Workflow_ID of PP_Order_ID','S',TO_DATE('2008-12-09 20:11:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 09.12.2008 20:14:34 EET
-- [ 2412212 ] Fix Activity Control Report Window
UPDATE AD_Column SET AD_Val_Rule_ID=52036,Updated=TO_DATE('2008-12-09 20:14:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53832
;

update AD_Val_Rule set entitytype='EE01' where AD_Val_Rule_ID in (52035, 52036);

-- 09.12.2008 21:53:16 EET
-- [ 2412212 ] Fix Activity Control Report Window
UPDATE AD_Column SET AD_Val_Rule_ID=164,Updated=TO_DATE('2008-12-09 21:53:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53806
;
