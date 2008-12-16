-- Aug 4, 2008 7:48:17 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET IsMandatory='Y', IsRange='Y',Updated=TO_TIMESTAMP('2008-08-04 19:48:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53218
;

-- Aug 4, 2008 7:48:21 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-08-04 19:48:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53221
;

-- Aug 4, 2008 7:50:08 PM CDT
-- DRP Functionality
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2182,0,53150,53240,20,'ConsolidateDocument',TO_TIMESTAMP('2008-08-04 19:49:59','YYYY-MM-DD HH24:MI:SS'),0,'Consolidate Lines into one Document','EE01',1,'Y','Y','N','N','Consolidate to one Document',60,TO_TIMESTAMP('2008-08-04 19:49:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 4, 2008 7:50:08 PM CDT
-- DRP Functionality
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53240 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 4, 2008 7:51:49 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET IsActive='N',Updated=TO_TIMESTAMP('2008-08-04 19:51:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53219
;

-- Aug 4, 2008 8:09:12 PM CDT
-- DRP Functionality
UPDATE AD_Process SET Description='Create Distribution Run Orders based on Distribution List or The Quantity Demand the Distribution Order and redistribute the quantity into Distribution Plan line items', Help='Distribution List  do not selected then the  redistribute the quantity is based in % of Demand

Please note that due to rounding, the total quantity of the order(s) is likely to be higher then the quantity entered.

Consolidate to one Document let the distribution in current Documents with ranges dates promised, if do not is selected then a new Order is generate.',Updated=TO_TIMESTAMP('2008-08-04 20:09:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53150
;

-- Aug 4, 2008 8:09:12 PM CDT
-- DRP Functionality
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53150
;

-- Aug 4, 2008 8:09:12 PM CDT
-- DRP Functionality
UPDATE AD_Menu SET Description='Create Distribution Run Orders based on Distribution List or The Quantity Demand the Distribution Order and redistribute the quantity into Distribution Plan line items', IsActive='Y', Name='Distribution Run Orders',Updated=TO_TIMESTAMP('2008-08-04 20:09:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53184
;

-- Aug 4, 2008 8:09:12 PM CDT
-- DRP Functionality
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53184
;

