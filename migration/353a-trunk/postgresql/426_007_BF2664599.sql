-- 05.03.2009 12:04:46 EET
-- Implementing Subcontract in Manufacturing Management
DELETE FROM AD_Process_Para_Trl WHERE AD_Process_Para_ID=53088
;

-- 05.03.2009 12:04:46 EET
-- Implementing Subcontract in Manufacturing Management
DELETE FROM AD_Process_Para WHERE AD_Process_Para_ID=53088
;

-- 05.03.2009 12:05:01 EET
-- Implementing Subcontract in Manufacturing Management
DELETE FROM AD_Process_Para_Trl WHERE AD_Process_Para_ID=53091
;

-- 05.03.2009 12:05:01 EET
-- Implementing Subcontract in Manufacturing Management
DELETE FROM AD_Process_Para WHERE AD_Process_Para_ID=53091
;

-- 05.03.2009 12:05:25 EET
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET SeqNo=30,IsActive='Y' WHERE AD_Process_Para_ID=53093
;

-- 05.03.2009 12:05:25 EET
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET SeqNo=40,IsActive='Y' WHERE AD_Process_Para_ID=53092
;

-- 05.03.2009 12:05:25 EET
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET SeqNo=50,IsActive='Y' WHERE AD_Process_Para_ID=53266
;

-- Mar 5, 2009 12:11:23 PM EET
-- [ 2664599 ] Fix CreateCostElement process parameters
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,Created,CreatedBy,UpdatedBy,IsActive,AD_Org_ID,Updated,Name,IsRange,Description,Help,AD_Process_ID,EntityType,FieldLength,IsCentrallyMaintained,ColumnName,SeqNo,AD_Reference_ID,IsMandatory,AD_Element_ID) VALUES (53298,0,TO_TIMESTAMP('2009-03-05 12:11:21','YYYY-MM-DD HH24:MI:SS'),0,0,'Y',0,TO_TIMESTAMP('2009-03-05 12:11:21','YYYY-MM-DD HH24:MI:SS'),'Product Category','N','Category of a Product','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.',53052,'D',10,'Y','M_Product_Category_ID',60,30,'N',453)
;

-- Mar 5, 2009 12:11:23 PM EET
-- [ 2664599 ] Fix CreateCostElement process parameters
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Name,Description,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Name,t.Description,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53298 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 5, 2009 12:11:42 PM EET
-- [ 2664599 ] Fix CreateCostElement process parameters
UPDATE AD_Process_Para SET SeqNo=40,IsActive='Y' WHERE AD_Process_Para_ID=53298
;

-- Mar 5, 2009 12:11:42 PM EET
-- [ 2664599 ] Fix CreateCostElement process parameters
UPDATE AD_Process_Para SET SeqNo=50,IsActive='Y' WHERE AD_Process_Para_ID=53092
;

-- Mar 5, 2009 12:11:42 PM EET
-- [ 2664599 ] Fix CreateCostElement process parameters
UPDATE AD_Process_Para SET SeqNo=60,IsActive='Y' WHERE AD_Process_Para_ID=53266
;


-- Mar 5, 2009 12:15:02 PM EET
-- [ 2664599 ] Fix CreateCostElement process parameters
UPDATE AD_Process_Para SET AD_Reference_ID=19,Updated=TO_TIMESTAMP('2009-03-05 12:15:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53298
;

