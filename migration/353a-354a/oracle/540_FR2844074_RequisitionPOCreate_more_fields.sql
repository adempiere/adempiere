-- 21.08.2009 12:43:06 EEST
-- Requisition PO Create - more selection fields
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,Updated,IsActive,Created,UpdatedBy,AD_Org_ID,CreatedBy,FieldLength,Name,IsCentrallyMaintained,IsRange,Description,Help,AD_Process_ID,EntityType,ColumnName,IsMandatory,SeqNo,AD_Reference_ID,AD_Element_ID) VALUES (53322,0,TO_DATE('2009-08-21 12:43:05','YYYY-MM-DD HH24:MI:SS'),'Y',TO_DATE('2009-08-21 12:43:05','YYYY-MM-DD HH24:MI:SS'),0,0,0,10,'Product Category','Y','N','Category of a Product','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.',337,'D','M_Product_Category_ID','N',90,19,453)
;

-- 21.08.2009 12:43:07 EEST
-- Requisition PO Create - more selection fields
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Name,Description,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Name,t.Description,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53322 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 21.08.2009 12:55:57 EEST
-- Requisition PO Create - more selection fields
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,Updated,IsActive,Created,UpdatedBy,AD_Org_ID,CreatedBy,FieldLength,Name,IsCentrallyMaintained,IsRange,Description,Help,AD_Process_ID,EntityType,ColumnName,IsMandatory,SeqNo,AD_Reference_ID,AD_Element_ID) VALUES (53323,0,TO_DATE('2009-08-21 12:55:55','YYYY-MM-DD HH24:MI:SS'),'Y',TO_DATE('2009-08-21 12:55:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,10,'Business Partner Group','Y','N','Business Partner Group','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.',337,'D','C_BP_Group_ID','N',100,30,1383)
;

-- 21.08.2009 12:55:57 EEST
-- Requisition PO Create - more selection fields
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Name,Description,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Name,t.Description,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53323 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 21.08.2009 12:56:22 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=10,IsActive='Y' WHERE AD_Process_Para_ID=697
;

-- 21.08.2009 12:56:22 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=20,IsActive='Y' WHERE AD_Process_Para_ID=689
;

-- 21.08.2009 12:56:22 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=30,IsActive='Y' WHERE AD_Process_Para_ID=690
;

-- 21.08.2009 12:56:23 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=40,IsActive='Y' WHERE AD_Process_Para_ID=688
;

-- 21.08.2009 12:56:23 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=50,IsActive='Y' WHERE AD_Process_Para_ID=691
;

-- 21.08.2009 12:56:23 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=60,IsActive='Y' WHERE AD_Process_Para_ID=692
;

-- 21.08.2009 12:56:23 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=70,IsActive='Y' WHERE AD_Process_Para_ID=693
;

-- 21.08.2009 12:56:23 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=80,IsActive='Y' WHERE AD_Process_Para_ID=695
;

-- 21.08.2009 12:56:23 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET SeqNo=110,IsActive='Y' WHERE AD_Process_Para_ID=694
;

-- 21.08.2009 12:56:37 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET DefaultValue='Y',Updated=TO_DATE('2009-08-21 12:56:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=694
;

-- 21.08.2009 12:56:44 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_DATE('2009-08-21 12:56:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=694
;

-- 21.08.2009 12:57:04 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET DisplayLogic='@M_Product_ID@=0',Updated=TO_DATE('2009-08-21 12:57:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53322
;

-- 21.08.2009 12:57:13 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET DisplayLogic='@M_Product_Category_ID@=0',Updated=TO_DATE('2009-08-21 12:57:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=695
;

-- 21.08.2009 12:57:50 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET DefaultValue='-1',Updated=TO_DATE('2009-08-21 12:57:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=695
;

-- 21.08.2009 12:57:53 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET DefaultValue='-1',Updated=TO_DATE('2009-08-21 12:57:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53322
;

-- 21.08.2009 12:57:59 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET DefaultValue='-1',Updated=TO_DATE('2009-08-21 12:57:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53323
;

-- 21.08.2009 12:58:16 EEST
-- Requisition PO Create - more selection fields
UPDATE AD_Process_Para SET AD_Reference_ID=19,Updated=TO_DATE('2009-08-21 12:58:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53323
;

