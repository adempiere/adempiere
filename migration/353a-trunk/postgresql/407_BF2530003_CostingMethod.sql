-- Jan 27, 2009 10:45:13 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,241,0,53058,53270,17,122,'CostingMethod',TO_TIMESTAMP('2009-01-27 22:44:49','YYYY-MM-DD HH24:MI:SS'),0,'S','Indicates how Costs will be calculated','EE01',22,'The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FoFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).','Y','Y','Y','N','Costing Method',28,TO_TIMESTAMP('2009-01-27 22:44:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 27, 2009 10:45:13 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53270 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 27, 2009 10:46:44 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_TIMESTAMP('2009-01-27 22:46:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53103
;

-- Jan 27, 2009 10:46:46 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET FieldLength=109,Updated=TO_TIMESTAMP('2009-01-27 22:46:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53104
;

-- Jan 27, 2009 10:46:50 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_TIMESTAMP('2009-01-27 22:46:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53104
;

-- Jan 27, 2009 10:46:53 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_TIMESTAMP('2009-01-27 22:46:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53106
;

-- Jan 27, 2009 10:46:58 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_TIMESTAMP('2009-01-27 22:46:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53105
;

-- Jan 27, 2009 10:47:01 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_TIMESTAMP('2009-01-27 22:47:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53107
;

-- Jan 27, 2009 10:47:18 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_TIMESTAMP('2009-01-27 22:47:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53106
;

-- Jan 27, 2009 10:47:22 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_TIMESTAMP('2009-01-27 22:47:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53270
;

-- Jan 27, 2009 10:47:31 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_TIMESTAMP('2009-01-27 22:47:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53107
;

-- Jan 27, 2009 10:50:11 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,241,0,53062,53271,17,122,'CostingMethod',TO_TIMESTAMP('2009-01-27 22:50:09','YYYY-MM-DD HH24:MI:SS'),0,'S','Indicates how Costs will be calculated','EE01',22,'The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FoFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).','Y','Y','Y','N','Costing Method',40,TO_TIMESTAMP('2009-01-27 22:50:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 27, 2009 10:50:11 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53271 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 27, 2009 10:52:26 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2071,0,53159,53272,19,'M_CostType_ID',TO_TIMESTAMP('2009-01-27 22:52:25','YYYY-MM-DD HH24:MI:SS'),0,'Type of Cost (e.g. Current, Plan, Future)','EE01',10,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','Y','Y','N','Cost Type',20,TO_TIMESTAMP('2009-01-27 22:52:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 27, 2009 10:52:26 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53272 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 27, 2009 10:53:48 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,241,0,53159,53273,17,122,'CostingMethod',TO_TIMESTAMP('2009-01-27 22:53:47','YYYY-MM-DD HH24:MI:SS'),0,'S','Indicates how Costs will be calculated','EE01',10,'The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FoFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).','Y','Y','Y','N','Costing Method',20,TO_TIMESTAMP('2009-01-27 22:53:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 27, 2009 10:53:48 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53273 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 27, 2009 10:54:05 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_TIMESTAMP('2009-01-27 22:54:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53272
;

-- Jan 27, 2009 10:54:11 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_TIMESTAMP('2009-01-27 22:54:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53273
;

-- Jan 27, 2009 10:54:14 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET SeqNo=50,Updated=TO_TIMESTAMP('2009-01-27 22:54:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53267
;

