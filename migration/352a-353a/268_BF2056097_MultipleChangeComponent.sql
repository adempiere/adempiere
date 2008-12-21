-- Aug 17, 2008 10:49:37 AM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53008,53242,19,'M_Product_ID',TO_DATE('2008-08-17 10:49:34','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','Product',50,TO_DATE('2008-08-17 10:49:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 17, 2008 10:49:38 AM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53242 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 17, 2008 10:52:26 AM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,617,0,53008,53243,15,'ValidFrom',TO_DATE('2008-08-17 10:52:25','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',10,'The Valid From date indicates the first day of a date range','Y','Y','N','N','Valid from',10,TO_DATE('2008-08-17 10:52:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 17, 2008 10:52:26 AM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53243 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 17, 2008 10:52:43 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET AD_Element_ID=618,Updated=TO_DATE('2008-08-17 10:52:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53031
;

-- Aug 17, 2008 10:54:17 AM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,526,0,53008,53244,29,'Qty',TO_DATE('2008-08-17 10:54:16','YYYY-MM-DD HH24:MI:SS'),0,'Quantity','EE01',10,'The Quantity indicates the number of a specific product or item for this document.','Y','Y','N','N','Quantity',60,TO_DATE('2008-08-17 10:54:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 17, 2008 10:54:17 AM CDT
-- Water Mark for Document Printed
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53244 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 17, 2008 10:54:41 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET IsCentrallyMaintained='N',Updated=TO_DATE('2008-08-17 10:54:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53243
;

-- Aug 17, 2008 10:55:07 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET SeqNo=10,Updated=TO_DATE('2008-08-17 10:55:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53242
;

-- Aug 17, 2008 10:55:14 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_DATE('2008-08-17 10:55:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53031
;

-- Aug 17, 2008 10:55:18 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET SeqNo=20,Updated=TO_DATE('2008-08-17 10:55:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53243
;

-- Aug 17, 2008 10:55:34 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-08-17 10:55:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53030
;

-- Aug 17, 2008 10:55:41 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-08-17 10:55:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53032
;

-- Aug 17, 2008 10:55:56 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-08-17 10:55:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53032
;

-- Aug 17, 2008 10:56:02 AM CDT
-- Water Mark for Document Printed
UPDATE AD_Process_Para SET SeqNo=50,Updated=TO_DATE('2008-08-17 10:56:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53030
;

-- Aug 17, 2008 10:57:03 AM CDT
-- Multi Change Component
UPDATE AD_Process_Para SET AD_Element_ID=454,Updated=TO_DATE('2008-08-17 10:57:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53030
;

-- Aug 17, 2008 11:00:31 AM CDT
-- Multi Change Component
UPDATE AD_Process_Para SET DisplayLogic=NULL,Updated=TO_DATE('2008-08-17 11:00:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53030
;

-- Aug 17, 2008 11:03:26 AM CDT
-- Multi Change Component
UPDATE AD_Process_Para SET DisplayLogic='@Action@=''A''  | @Action@=''R''  | @Action@=''RE'' ',Updated=TO_DATE('2008-08-17 11:03:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53030
;

-- Aug 17, 2008 12:04:00 PM CDT
-- Multi Change Component
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2783,0,53008,53245,19,'M_ChangeNotice_ID',TO_DATE('2008-08-17 12:03:59','YYYY-MM-DD HH24:MI:SS'),0,'Change Notice','EE01',10,'Bill of Materials (Engineering) Change Notice (Version)','Y','Y','N','N','Change Notice',70,TO_DATE('2008-08-17 12:03:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 17, 2008 12:04:00 PM CDT
-- Multi Change Component
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53245 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

