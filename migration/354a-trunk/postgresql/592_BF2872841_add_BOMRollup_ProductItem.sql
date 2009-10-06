-- Oct 6, 2009 9:07:20 AM EEST
-- [ 2872841 ] RollupBillOfMaterial should check all BOM products
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DisplayLogic,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1899,0,53062,1000005,17,270,'ProductType',TO_TIMESTAMP('2009-10-06 09:07:20','YYYY-MM-DD HH24:MI:SS'),0,'@M_Product_ID@=0','EE01',0,'Y','Y','N','N','Product Type',80,TO_TIMESTAMP('2009-10-06 09:07:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 6, 2009 9:07:20 AM EEST
-- [ 2872841 ] RollupBillOfMaterial should check all BOM products
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=1000005 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Oct 6, 2009 9:07:26 AM EEST
-- [ 2872841 ] RollupBillOfMaterial should check all BOM products
UPDATE AD_Process_Para SET DisplayLogic='@M_Product_ID@=0',Updated=TO_TIMESTAMP('2009-10-06 09:07:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53112
;

-- Oct 6, 2009 9:07:58 AM EEST
-- [ 2872841 ] RollupBillOfMaterial should check all BOM products
UPDATE AD_Process_Para SET DisplayLogic='@M_Product_Category_ID@=0 & @ProductType@=0',Updated=TO_TIMESTAMP('2009-10-06 09:07:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53108
;

-- Oct 6, 2009 9:23:58 AM EEST
-- [ 2872841 ] RollupBillOfMaterial should check all BOM products
UPDATE AD_Process_Para SET DisplayLogic='@M_Product_Category_ID@=0',Updated=TO_TIMESTAMP('2009-10-06 09:23:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53108
;

-- Oct 6, 2009 9:24:18 AM EEST
-- [ 2872841 ] RollupBillOfMaterial should check all BOM products
UPDATE AD_Process_Para SET DefaultValue='-1', DisplayLogic='@M_Product_ID@=0',Updated=TO_TIMESTAMP('2009-10-06 09:24:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53112
;

-- Oct 6, 2009 9:24:22 AM EEST
-- [ 2872841 ] RollupBillOfMaterial should check all BOM products
UPDATE AD_Process_Para SET DisplayLogic='@M_Product_ID@=0',Updated=TO_TIMESTAMP('2009-10-06 09:24:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=1000005
;

