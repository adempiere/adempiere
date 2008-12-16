-- Jan 7, 2008 9:37:36 PM COT
-- 1866483 - Jasper on Financial Reports
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54091,50064,0,18,400,445,'JasperProcess_ID',TO_TIMESTAMP('2008-01-07 21:37:34','YYYY-MM-DD HH24:MI:SS'),100,'The Jasper Process used by the printengine if any process defined','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Jasper Process',0,TO_TIMESTAMP('2008-01-07 21:37:34','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54091 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE PA_Report ADD COLUMN JasperProcess_ID NUMERIC(10)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53323,0,'JasperProcessing',TO_TIMESTAMP('2008-01-07 21:40:17','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Jasper Process Now','Jasper Process Now',TO_TIMESTAMP('2008-01-07 21:40:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53323 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53063,'3','org.compiere.report.FinReportJasper',TO_TIMESTAMP('2008-01-07 21:41:49','YYYY-MM-DD HH24:MI:SS'),100,'Create Financial Report  (Jasper)','D','The default period is the current period. You can optionally enter other restrictions.  You can select an alternative Reporting Hierarchy.','Y','N','N','N','N','Create Report (Jasper)','Y',0,0,TO_TIMESTAMP('2008-01-07 21:41:49','YYYY-MM-DD HH24:MI:SS'),100,'FinReportJasper')
;

INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53063 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53063,0,TO_TIMESTAMP('2008-01-07 21:41:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-07 21:41:57','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53063,102,TO_TIMESTAMP('2008-01-07 21:41:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-07 21:41:57','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53063,103,TO_TIMESTAMP('2008-01-07 21:41:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-07 21:41:57','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53063,50001,TO_TIMESTAMP('2008-01-07 21:41:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-07 21:41:57','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54092,53323,0,53063,28,445,'JasperProcessing',TO_TIMESTAMP('2008-01-07 21:42:27','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','Jasper Process Now',0,TO_TIMESTAMP('2008-01-07 21:42:27','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54092 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-01-07 21:43:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54092
;

UPDATE AD_Field SET Name='Jasper Process Now', Description=NULL, Help=NULL WHERE AD_Column_ID=54092 AND IsCentrallyMaintained='Y'
;

ALTER TABLE PA_Report ADD COLUMN JasperProcessing CHAR(1)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,206,0,53063,53114,18,275,217,'C_Period_ID',TO_TIMESTAMP('2008-01-07 21:44:40','YYYY-MM-DD HH24:MI:SS'),100,'Period of the Calendar','D',0,'The Period indicates an exclusive range of dates for a calendar.','Y','Y','N','N','Period',10,TO_TIMESTAMP('2008-01-07 21:44:40','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53114 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,479,0,53063,53115,18,322,'Org_ID',TO_TIMESTAMP('2008-01-07 21:45:09','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','D',0,'An organization is a unit of your client or legal entity - examples are store, department.','Y','Y','N','N','Organization',20,TO_TIMESTAMP('2008-01-07 21:45:09','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53115 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,187,0,53063,53116,19,'C_BPartner_ID',TO_TIMESTAMP('2008-01-07 21:45:39','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','D',0,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','Business Partner ',30,TO_TIMESTAMP('2008-01-07 21:45:39','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53116 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53063,53117,19,'M_Product_ID',TO_TIMESTAMP('2008-01-07 21:46:05','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','D',0,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','Product',40,TO_TIMESTAMP('2008-01-07 21:46:05','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53117 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,208,0,53063,53118,19,'C_Project_ID',TO_TIMESTAMP('2008-01-07 21:47:04','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','D',0,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','Project',50,TO_TIMESTAMP('2008-01-07 21:47:04','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53118 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1005,0,53063,53119,19,'C_Activity_ID',TO_TIMESTAMP('2008-01-07 21:47:41','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity','D',0,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','Activity',60,TO_TIMESTAMP('2008-01-07 21:47:41','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53119 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,210,0,53063,53120,19,'C_SalesRegion_ID',TO_TIMESTAMP('2008-01-07 21:48:11','YYYY-MM-DD HH24:MI:SS'),100,'Sales coverage region','D',0,'The Sales Region indicates a specific area of sales coverage.','Y','Y','N','N','Sales Region',70,TO_TIMESTAMP('2008-01-07 21:48:11','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53120 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,550,0,53063,53121,19,'C_Campaign_ID',TO_TIMESTAMP('2008-01-07 21:48:35','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','D',0,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','Campaign',80,TO_TIMESTAMP('2008-01-07 21:48:35','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53121 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53063,53122,20,'DetailsSourceFirst',TO_TIMESTAMP('2008-01-07 21:49:04','YYYY-MM-DD HH24:MI:SS'),100,'Details and Sources are printed before the Line','D',0,'Y','Y','N','N','Details/Source First',90,TO_TIMESTAMP('2008-01-07 21:49:04','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53122 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2344,0,53063,53123,20,'UpdateBalances',TO_TIMESTAMP('2008-01-07 21:49:52','YYYY-MM-DD HH24:MI:SS'),100,'Y','Update Accounting Balances first (not required for subsequent runs)','D',0,'Y','Y','Y','N','Update Balances',100,TO_TIMESTAMP('2008-01-07 21:49:52','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53123 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2868,0,53063,53124,19,'PA_Hierarchy_ID',TO_TIMESTAMP('2008-01-07 21:50:22','YYYY-MM-DD HH24:MI:SS'),100,'Optional Reporting Hierarchy - If not selected the default hierarchy trees are used.','D',0,'Reporting Hierarchy allows you to select different Hierarchies/Trees for the report.
Accounting Segments like Organization, Account, Product may have several hierarchies to accomodate different views on the business.','Y','Y','N','N','Reporting Hierarchy',110,TO_TIMESTAMP('2008-01-07 21:50:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53124 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54091,54234,0,372,TO_TIMESTAMP('2008-01-07 21:51:00','YYYY-MM-DD HH24:MI:SS'),100,'The Jasper Process used by the printengine if any process defined',22,'D','Y','Y','Y','N','N','N','N','N','Jasper Process',TO_TIMESTAMP('2008-01-07 21:51:00','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54234 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54092,54235,0,372,TO_TIMESTAMP('2008-01-07 21:51:02','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Jasper Process Now',TO_TIMESTAMP('2008-01-07 21:51:02','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54235 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=4731
;

UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=4732
;

UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=4733
;

UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=6263
;

UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=6264
;

UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=4739
;

UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=4734
;

UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=4738
;

UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=4735
;

UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=4736
;

UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54234
;

UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=54235
;

UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=6265
;

UPDATE AD_Field SET DisplayLogic='@JasperProcess_ID@=0',Updated=TO_TIMESTAMP('2008-01-07 21:52:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4737
;

UPDATE AD_Field SET DisplayLogic='@JasperProcess_ID@>0',Updated=TO_TIMESTAMP('2008-01-07 21:53:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54235
;

UPDATE AD_Field SET DisplayLength=23,Updated=TO_TIMESTAMP('2008-01-07 21:55:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54235
;

UPDATE AD_Field SET Name='Create Report (Jasper)',Updated=TO_TIMESTAMP('2008-01-07 21:55:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54235
;

UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=54235
;

UPDATE AD_Field SET Description='Create Financial Report (Jasper)',Updated=TO_TIMESTAMP('2008-01-07 21:55:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54235
;

UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=54235
;

UPDATE AD_Field SET Help='The default period is the current period. You can optionally enter other restrictions.  You can select an alternative Reporting Hierarchy.',Updated=TO_TIMESTAMP('2008-01-07 21:55:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54235
;

UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=54235
;

UPDATE AD_Field SET AD_FieldGroup_ID=114,Updated=TO_TIMESTAMP('2008-01-07 21:55:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54235
;

