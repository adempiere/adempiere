
-- Jul 29, 2008 11:00:46 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53675,0,'CreateDO',TO_DATE('2008-07-29 11:00:37','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Create Distribution Order','Create Distribution Order',TO_DATE('2008-07-29 11:00:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 29, 2008 11:00:46 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53675 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;


-- Jul 28, 2008 8:27:38 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53150,'1','org.eevolution.process.DistributionRunOrders',TO_DATE('2008-07-28 20:27:26','YYYY-MM-DD HH24:MI:SS'),0,'Create Distribution Run Orders based on Distribution List and redistribute the quantity into Distribution Plan line items','EE01','Please note that due to rounding, the total quantity of the order(s) is likely to be higher then the quantity entered.','Y','N','N','N','Distribution Run Orders','Y',0,0,TO_DATE('2008-07-28 20:27:26','YYYY-MM-DD HH24:MI:SS'),0,'M_DistributionRun Orders',NULL)
;

-- Jul 28, 2008 8:27:38 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53150 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Jul 28, 2008 8:27:39 PM CDT
-- Distribution Run Orders
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_DATE('2008-07-28 20:27:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=15
;

-- Jul 28, 2008 8:27:39 PM CDT
-- Distribution Run Orders
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Jul 28, 2008 8:27:41 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,269,0,53150,53218,15,'DatePromised',TO_DATE('2008-07-28 20:27:40','YYYY-MM-DD HH24:MI:SS'),0,'Date Promised','EE01',8,'Date Promised','Y','Y','N','N','Date Promised',25,TO_DATE('2008-07-28 20:27:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 8:27:41 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53218 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 8:27:41 PM CDT
-- Distribution Run Orders
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2008-07-28 20:27:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Jul 28, 2008 8:27:41 PM CDT
-- Distribution Run Orders
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Jul 28, 2008 8:27:42 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53675,0,53150,53219,20,'CreateDO',TO_DATE('2008-07-28 20:27:41','YYYY-MM-DD HH24:MI:SS'),0,'Create Distribution Order','EE01',1,'Create Distribution Order','Y','Y','N','N','Create Distribution Order',50,TO_DATE('2008-07-28 20:27:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 8:27:42 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53219 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 8:27:43 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2153,0,53150,53220,20,'IsTest',TO_DATE('2008-07-28 20:27:42','YYYY-MM-DD HH24:MI:SS'),0,'Test','EE01',1,'Execute in Test Mode','Y','Y','N','N','Test',40,TO_DATE('2008-07-28 20:27:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 8:27:43 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53220 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 8:27:43 PM CDT
-- Distribution Run Orders
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2008-07-28 20:27:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Jul 28, 2008 8:27:43 PM CDT
-- Distribution Run Orders
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Jul 28, 2008 8:27:44 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2408,0,53150,53221,19,'M_DistributionList_ID',TO_DATE('2008-07-28 20:27:43','YYYY-MM-DD HH24:MI:SS'),0,'Distribution List','EE01',10,'Distribution Lists allow to distribute products to a selected list of partners','Y','N','Y','N','Distribution List',30,TO_DATE('2008-07-28 20:27:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 8:27:44 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53221 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 8:27:45 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,459,0,53150,53222,19,'M_Warehouse_ID',TO_DATE('2008-07-28 20:27:44','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','Warehouse',20,TO_DATE('2008-07-28 20:27:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 8:27:45 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53222 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 8:27:46 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53150,53223,19,'AD_Org_ID',TO_DATE('2008-07-28 20:27:45','YYYY-MM-DD HH24:MI:SS'),0,'Organization','EE01',10,'Organizational entity within client','Y','Y','Y','N','Organization',10,TO_DATE('2008-07-28 20:27:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 8:27:46 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53223 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 8:27:48 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53184,0,53150,'P',TO_DATE('2008-07-28 20:27:47','YYYY-MM-DD HH24:MI:SS'),0,'Create Distribution Run Orders based on Distribution List and redistribute the quantity into Distribution Plan line items','EE01','Y','N','N','N','Distribution Run Orders',TO_DATE('2008-07-28 20:27:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 8:27:48 PM CDT
-- Distribution Run Orders
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53184 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Jul 28, 2008 8:27:48 PM CDT
-- Distribution Run Orders
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53066,7, 10, 53184)
;

-- Jul 28, 2008 8:59:40 PM CDT
-- Distribution Run Orders
UPDATE AD_Val_Rule SET Code='C_DocType.DocBaseType IN (''SOO'', ''POO'',''DOO'')',Updated=TO_DATE('2008-07-28 20:59:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=172
;



