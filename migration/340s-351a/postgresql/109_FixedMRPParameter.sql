-- Mar 2, 2008 10:27:20 PM CST
-- Fixed MRP
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=53514
;

-- Mar 2, 2008 10:27:20 PM CST
-- Fixed MRP
DELETE FROM AD_Field WHERE AD_Field_ID=53514
;

-- Mar 2, 2008 10:27:24 PM CST
-- Fixed MRP
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=53515
;


-- Mar 2, 2008 10:27:24 PM CST
-- Fixed MRP
DELETE FROM AD_Field WHERE AD_Field_ID=53515
;

-- Mar 2, 2008 10:27:58 PM CST
-- Fixed MRP
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=53383
;

-- Mar 2, 2008 10:27:58 PM CST
-- Fixed MRP
DELETE FROM AD_Column WHERE AD_Column_ID=53383
;

-- Mar 2, 2008 10:28:18 PM CST
-- Fixed MRP
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=53388
;

-- Mar 2, 2008 10:28:18 PM CST
-- Fixed MRP
DELETE FROM AD_Column WHERE AD_Column_ID=53388
;

-- Mar 2, 2008 10:32:01 PM CST
-- Fixed MRP
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1777,0,53016,53132,19,52002,'S_Resource_ID',TO_TIMESTAMP('2008-03-02 22:31:55','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','Y','N','S_Resource_ID',12,TO_TIMESTAMP('2008-03-02 22:31:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 2, 2008 10:32:01 PM CST
-- Fixed MRP
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53132 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 2, 2008 10:32:22 PM CST
-- Fixed MRP
UPDATE AD_Process_Para SET Name='Resource',Updated=TO_TIMESTAMP('2008-03-02 22:32:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53132
;

-- Mar 2, 2008 10:32:22 PM CST
-- Fixed MRP
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53132
;

-- Mar 2, 2008 10:34:50 PM CST
-- Fixed MRP
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,459,0,53016,53133,19,'M_Warehouse_ID',TO_TIMESTAMP('2008-03-02 22:34:50','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE01',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','Warehouse',40,TO_TIMESTAMP('2008-03-02 22:34:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 2, 2008 10:34:50 PM CST
-- Fixed MRP
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53133 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 2, 2008 10:35:29 PM CST
-- Fixed MRP
UPDATE AD_Process_Para SET AD_Val_Rule_ID=202,Updated=TO_TIMESTAMP('2008-03-02 22:35:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53054
;

-- Mar 2, 2008 10:35:37 PM CST
-- Fixed MRP
UPDATE AD_Process_Para SET SeqNo=20,Updated=TO_TIMESTAMP('2008-03-02 22:35:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53132
;

-- Mar 2, 2008 10:35:43 PM CST
-- Fixed MRP
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_TIMESTAMP('2008-03-02 22:35:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53133
;

-- Mar 2, 2008 10:35:49 PM CST
-- Fixed MRP
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_TIMESTAMP('2008-03-02 22:35:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53053
;

-- Mar 2, 2008 10:40:57 PM CST
-- Fixed MRP
DELETE FROM AD_Process_Para_Trl WHERE AD_Process_Para_ID=53040
;

-- Mar 2, 2008 10:40:57 PM CST
-- Fixed MRP
DELETE FROM AD_Process_Para WHERE AD_Process_Para_ID=53040
;

-- Mar 2, 2008 10:41:02 PM CST
-- Fixed MRP
DELETE FROM AD_Process_Para_Trl WHERE AD_Process_Para_ID=53041
;

-- Mar 2, 2008 10:41:02 PM CST
-- Fixed MRP
DELETE FROM AD_Process_Para WHERE AD_Process_Para_ID=53041
;

-- Mar 2, 2008 10:41:13 PM CST
-- Fixed MRP
DELETE FROM AD_Element_Trl WHERE AD_Element_ID=53259
;

-- Mar 2, 2008 10:41:13 PM CST
-- Fixed MRP
DELETE FROM AD_Element WHERE AD_Element_ID=53259
;

-- Mar 2, 2008 10:41:25 PM CST
-- Fixed MRP
DELETE FROM AD_Element_Trl WHERE AD_Element_ID=53263
;

-- Mar 2, 2008 10:41:25 PM CST
-- Fixed MRP
DELETE FROM AD_Element WHERE AD_Element_ID=53263
;

ALTER TABLE PP_Product_Planning DROP COLUMN IsSupply;
ALTER TABLE PP_Product_Planning DROP COLUMN IsDemand;