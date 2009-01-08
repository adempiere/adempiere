-- Dec 31, 2008 11:32:42 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53721,0,'P_WIP_Acct',TO_TIMESTAMP('2008-12-31 11:32:42','YYYY-MM-DD HH24:MI:SS'),0,'The Work in Process account is the account used Manufacturing Order','EE01','Y','Work in Process','Account for Work in Progress',TO_TIMESTAMP('2008-12-31 11:32:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 11:32:42 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53721 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 11:33:49 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56537,53721,0,25,315,'P_WIP_Acct',TO_TIMESTAMP('2008-12-31 11:33:49','YYYY-MM-DD HH24:MI:SS'),0,'The Work in Process account is the account used Manufacturing Order','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Work in Process',0,TO_TIMESTAMP('2008-12-31 11:33:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 11:33:49 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56537 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 11:34:02 AM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_WIP_Acct NUMERIC(10)
;

-- Dec 31, 2008 11:34:06 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 11:34:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56537
;

-- Dec 31, 2008 11:54:52 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53722,0,'P_MethodChangeVariance_Acct',TO_TIMESTAMP('2008-12-31 11:54:52','YYYY-MM-DD HH24:MI:SS'),0,'The Method Change Variance account is the account used Manufacturing Order','EE01','The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing define in BOM or Workflow Manufacturig then this variance is generate.','Y','Method Change Variance','Account for Method Change Variance',TO_TIMESTAMP('2008-12-31 11:54:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 11:54:52 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53722 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 11:55:23 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56538,53722,0,25,315,'P_MethodChangeVariance_Acct',TO_TIMESTAMP('2008-12-31 11:55:23','YYYY-MM-DD HH24:MI:SS'),0,'The Method Change Variance account is the account used Manufacturing Order','EE01',22,'The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing define in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Method Change Variance',0,TO_TIMESTAMP('2008-12-31 11:55:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 11:55:23 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56538 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 11:55:25 AM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_MethodChangeVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 11:55:30 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 11:55:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56538
;

-- Dec 31, 2008 11:56:00 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Element SET Help='The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.',Updated=TO_TIMESTAMP('2008-12-31 11:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53722
;

-- Dec 31, 2008 11:56:00 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53722
;

-- Dec 31, 2008 11:56:00 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET ColumnName='P_MethodChangeVariance_Acct', Name='Method Change Variance', Description='The Method Change Variance account is the account used Manufacturing Order', Help='The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.' WHERE AD_Element_ID=53722
;

-- Dec 31, 2008 11:56:00 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='P_MethodChangeVariance_Acct', Name='Method Change Variance', Description='The Method Change Variance account is the account used Manufacturing Order', Help='The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.', AD_Element_ID=53722 WHERE UPPER(ColumnName)='P_METHODCHANGEVARIANCE_ACCT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 31, 2008 11:56:00 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='P_MethodChangeVariance_Acct', Name='Method Change Variance', Description='The Method Change Variance account is the account used Manufacturing Order', Help='The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.' WHERE AD_Element_ID=53722 AND IsCentrallyMaintained='Y'
;

-- Dec 31, 2008 11:56:00 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET Name='Method Change Variance', Description='The Method Change Variance account is the account used Manufacturing Order', Help='The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53722) AND IsCentrallyMaintained='Y'
;

-- Dec 31, 2008 12:01:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53723,0,'P_UsageVariance_Acct',TO_TIMESTAMP('2008-12-31 12:01:25','YYYY-MM-DD HH24:MI:SS'),0,'The Usage Variance account is the account used Manufacturing Order','EE01','The Usage Variance is used in Standard Costing. It reflects the difference between the  Quantities of Standard BOM  or Time Standard Manufacturing Workflow and Quantities of Manufacturing BOM or Time Manufacturing Workflow of Manufacturing Order.

If you change the Quantities or Time  defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Usage Variance','Account for Usage Variance',TO_TIMESTAMP('2008-12-31 12:01:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 12:01:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53723 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 12:01:49 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56539,53723,0,25,315,'P_UsageVariance_Acct',TO_TIMESTAMP('2008-12-31 12:01:49','YYYY-MM-DD HH24:MI:SS'),0,'The Usage Variance account is the account used Manufacturing Order','EE01',22,'The Usage Variance is used in Standard Costing. It reflects the difference between the  Quantities of Standard BOM  or Time Standard Manufacturing Workflow and Quantities of Manufacturing BOM or Time Manufacturing Workflow of Manufacturing Order.

If you change the Quantities or Time  defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Usage Variance',0,TO_TIMESTAMP('2008-12-31 12:01:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 12:01:49 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56539 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 12:01:52 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_UsageVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 12:01:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 12:01:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56539
;

-- Dec 31, 2008 1:25:36 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53724,0,'P_RateVariance_Acct',TO_TIMESTAMP('2008-12-31 13:25:36','YYYY-MM-DD HH24:MI:SS'),0,'The Rate Variance account is the account used Manufacturing Order','EE01','The Rate Variance is used in Standard Costing. It reflects the difference between the Standard Cost Rates and  The Cost Rates of Manufacturing Order.

If you change the Standard Rates then this variance is generate.','Y','Rate Variance','Account for Rate Variance',TO_TIMESTAMP('2008-12-31 13:25:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 1:25:36 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53724 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 1:25:59 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56540,53724,0,25,315,'P_RateVariance_Acct',TO_TIMESTAMP('2008-12-31 13:25:59','YYYY-MM-DD HH24:MI:SS'),0,'The Rate Variance account is the account used Manufacturing Order','EE01',22,'The Rate Variance is used in Standard Costing. It reflects the difference between the Standard Cost Rates and  The Cost Rates of Manufacturing Order.

If you change the Standard Rates then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Rate Variance',0,TO_TIMESTAMP('2008-12-31 13:25:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 1:25:59 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56540 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 1:26:02 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_RateVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 1:26:08 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 13:26:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56540
;

-- Dec 31, 2008 2:28:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53725,0,'P_MixVariance_Acct',TO_TIMESTAMP('2008-12-31 14:28:30','YYYY-MM-DD HH24:MI:SS'),0,'The Mix Variance account is the account used Manufacturing Order','EE01','The Mix Variance is used when a co-product  received in Inventory  is different the quantity  expected
','Y','Mix Variance','Account for Mix Variance',TO_TIMESTAMP('2008-12-31 14:28:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 2:28:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53725 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 2:29:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56541,53725,0,25,315,'P_MixVariance_Acct',TO_TIMESTAMP('2008-12-31 14:29:30','YYYY-MM-DD HH24:MI:SS'),0,'The Mix Variance account is the account used Manufacturing Order','EE01',22,'The Mix Variance is used when a co-product  received in Inventory  is different the quantity  expected
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Mix Variance',0,TO_TIMESTAMP('2008-12-31 14:29:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 2:29:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56541 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 2:29:35 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_MixVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 2:30:03 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 14:30:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56541
;

-- Dec 31, 2008 3:14:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53726,0,'P_FloorStock_Acct',TO_TIMESTAMP('2008-12-31 15:14:39','YYYY-MM-DD HH24:MI:SS'),0,'The Floor Stock account is the account used Manufacturing Order','EE01','The Floor Stock is used for accounting the component with Issue Policy is set in No into Product Planning Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ','Y','Floor Stock','Account for Floor Stock',TO_TIMESTAMP('2008-12-31 15:14:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 3:14:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53726 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 3:16:43 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56542,53726,0,25,315,'P_FloorStock_Acct',TO_TIMESTAMP('2008-12-31 15:16:43','YYYY-MM-DD HH24:MI:SS'),0,'The Floor Stock account is the account used Manufacturing Order','EE01',22,'The Floor Stock is used for accounting the component with Issue Policy is set in No into Product Planning Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Floor Stock',0,TO_TIMESTAMP('2008-12-31 15:16:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 3:16:43 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56542 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 3:16:46 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_FloorStock_Acct NUMERIC(10)
;

-- Dec 31, 2008 3:16:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 15:16:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56542
;

-- Dec 31, 2008 3:44:12 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53727,0,'P_CostOfProduction_Acct',TO_TIMESTAMP('2008-12-31 15:44:12','YYYY-MM-DD HH24:MI:SS'),0,'The Cost Of Production account is the account used Manufacturing Order','EE01','The Cost Of Production is used for accounting Non productive Labor
','Y','Cost Of Production','Account for Cost Of Production',TO_TIMESTAMP('2008-12-31 15:44:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 3:44:12 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53727 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 3:44:32 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56543,53727,0,25,315,'P_CostOfProduction_Acct',TO_TIMESTAMP('2008-12-31 15:44:32','YYYY-MM-DD HH24:MI:SS'),0,'The Cost Of Production account is the account used Manufacturing Order','EE01',22,'The Cost Of Production is used for accounting Non productive Labor
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Of Production',0,TO_TIMESTAMP('2008-12-31 15:44:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 3:44:32 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56543 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 3:44:35 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_CostOfProduction_Acct NUMERIC(10)
;

-- Dec 31, 2008 3:44:39 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 15:44:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56543
;

-- Dec 31, 2008 3:46:26 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53728,0,'P_Labor_Acct',TO_TIMESTAMP('2008-12-31 15:46:26','YYYY-MM-DD HH24:MI:SS'),0,'The Labor account is the account used Manufacturing Order','EE01','The Labor is used for accounting the productive Labor
','Y','P_Labor_Acct','Account for Labor',TO_TIMESTAMP('2008-12-31 15:46:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 3:46:26 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53728 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 3:46:47 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56544,53728,0,25,315,'P_Labor_Acct',TO_TIMESTAMP('2008-12-31 15:46:47','YYYY-MM-DD HH24:MI:SS'),0,'The Labor account is the account used Manufacturing Order','EE01',22,'The Labor is used for accounting the productive Labor
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','P_Labor_Acct',0,TO_TIMESTAMP('2008-12-31 15:46:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 3:46:47 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56544 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 3:46:51 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_Labor_Acct NUMERIC(10)
;

-- Dec 31, 2008 3:46:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 15:46:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56544
;

-- Dec 31, 2008 3:47:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Element SET Name='Labor',Updated=TO_TIMESTAMP('2008-12-31 15:47:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53728
;

-- Dec 31, 2008 3:47:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53728
;

-- Dec 31, 2008 3:47:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET ColumnName='P_Labor_Acct', Name='Labor', Description='The Labor account is the account used Manufacturing Order', Help='The Labor is used for accounting the productive Labor
' WHERE AD_Element_ID=53728
;

-- Dec 31, 2008 3:47:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='P_Labor_Acct', Name='Labor', Description='The Labor account is the account used Manufacturing Order', Help='The Labor is used for accounting the productive Labor
', AD_Element_ID=53728 WHERE UPPER(ColumnName)='P_LABOR_ACCT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 31, 2008 3:47:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='P_Labor_Acct', Name='Labor', Description='The Labor account is the account used Manufacturing Order', Help='The Labor is used for accounting the productive Labor
' WHERE AD_Element_ID=53728 AND IsCentrallyMaintained='Y'
;

-- Dec 31, 2008 3:47:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET Name='Labor', Description='The Labor account is the account used Manufacturing Order', Help='The Labor is used for accounting the productive Labor
' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53728) AND IsCentrallyMaintained='Y'
;

-- Dec 31, 2008 3:47:51 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_PrintFormatItem SET PrintName='Account for Labor', Name='Labor' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53728)
;

-- Dec 31, 2008 5:42:11 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53729,0,'P_Burden_Acct',TO_TIMESTAMP('2008-12-31 17:42:11','YYYY-MM-DD HH24:MI:SS'),0,'The Burden account is the account used Manufacturing Order','EE01','The Burden is used for accounting the Burden','Y','Burden','Account for Burden',TO_TIMESTAMP('2008-12-31 17:42:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:42:11 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53729 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 5:43:02 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56545,53729,0,25,315,'P_Burden_Acct',TO_TIMESTAMP('2008-12-31 17:43:01','YYYY-MM-DD HH24:MI:SS'),0,'The Burden account is the account used Manufacturing Order','EE01',22,'The Burden is used for accounting the Burden','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Burden',0,TO_TIMESTAMP('2008-12-31 17:43:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 5:43:02 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56545 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:43:06 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_Burden_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:43:10 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:43:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56545
;

-- Dec 31, 2008 5:45:20 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53730,0,'P_OutsideProcessing_Acct',TO_TIMESTAMP('2008-12-31 17:45:20','YYYY-MM-DD HH24:MI:SS'),0,'The Outside Processing Account is the account used in Manufacturing Order','EE01','The Outside Processing Account is used for accounting the Outside Processing','Y','Outside Processing','Account for Burden',TO_TIMESTAMP('2008-12-31 17:45:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:45:20 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53730 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 5:46:13 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56546,53730,0,25,315,'P_OutsideProcessing_Acct',TO_TIMESTAMP('2008-12-31 17:46:13','YYYY-MM-DD HH24:MI:SS'),0,'The Outside Processing Account is the account used in Manufacturing Order','EE01',22,'The Outside Processing Account is used for accounting the Outside Processing','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Outside Processing',0,TO_TIMESTAMP('2008-12-31 17:46:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 5:46:13 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56546 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:46:16 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_OutsideProcessing_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:46:21 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:46:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56546
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56545,56520,0,252,TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0,'The Burden account is the account used Manufacturing Order',22,'EE01','The Burden is used for accounting the Burden','Y','Y','Y','N','N','N','N','N','Burden',TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56520 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56543,56521,0,252,TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0,'The Cost Of Production account is the account used Manufacturing Order',22,'EE01','The Cost Of Production is used for accounting Non productive Labor
','Y','Y','Y','N','N','N','N','N','Cost Of Production',TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56521 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56542,56522,0,252,TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0,'The Floor Stock account is the account used Manufacturing Order',22,'EE01','The Floor Stock is used for accounting the component with Issue Policy is set in No into Product Planning Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ','Y','Y','Y','N','N','N','N','N','Floor Stock',TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56522 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56544,56523,0,252,TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0,'The Labor account is the account used Manufacturing Order',22,'EE01','The Labor is used for accounting the productive Labor
','Y','Y','Y','N','N','N','N','N','Labor',TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56523 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56538,56524,0,252,TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0,'The Method Change Variance account is the account used Manufacturing Order',22,'EE01','The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','Y','N','N','N','N','N','Method Change Variance',TO_TIMESTAMP('2008-12-31 17:47:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56524 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56541,56525,0,252,TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0,'The Mix Variance account is the account used Manufacturing Order',22,'EE01','The Mix Variance is used when a co-product  received in Inventory  is different the quantity  expected
','Y','Y','Y','N','N','N','N','N','Mix Variance',TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56525 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

--  Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56546,56526,0,252,TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0,'The Outside Processing Account is the account used in Manufacturing Order',22,'EE01','The Outside Processing Account is used for accounting the Outside Processing','Y','Y','Y','N','N','N','N','N','Outside Processing',TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56526 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56540,56527,0,252,TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0,'The Rate Variance account is the account used Manufacturing Order',22,'EE01','The Rate Variance is used in Standard Costing. It reflects the difference between the Standard Cost Rates and  The Cost Rates of Manufacturing Order.

If you change the Standard Rates then this variance is generate.','Y','Y','Y','N','N','N','N','N','Rate Variance',TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56527 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56539,56528,0,252,TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0,'The Usage Variance account is the account used Manufacturing Order',22,'EE01','The Usage Variance is used in Standard Costing. It reflects the difference between the  Quantities of Standard BOM  or Time Standard Manufacturing Workflow and Quantities of Manufacturing BOM or Time Manufacturing Workflow of Manufacturing Order.

If you change the Quantities or Time  defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','Y','N','N','N','N','N','Usage Variance',TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56528 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56537,56529,0,252,TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0,'The Work in Process account is the account used Manufacturing Order',22,'EE01','Y','Y','Y','N','N','N','N','N','Work in Process',TO_TIMESTAMP('2008-12-31 17:47:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:47:30 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56529 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 5:49:01 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_FieldGroup (AD_Client_ID,AD_FieldGroup_ID,AD_Org_ID,Created,CreatedBy,EntityType,FieldGroupType,IsActive,IsCollapsedByDefault,Name,Updated,UpdatedBy) VALUES (0,50010,0,TO_TIMESTAMP('2008-12-31 17:49:01','YYYY-MM-DD HH24:MI:SS'),0,'EE01','C','Y','N','Manufacturing',TO_TIMESTAMP('2008-12-31 17:49:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 5:49:01 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_FieldGroup_Trl (AD_Language,AD_FieldGroup_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_FieldGroup_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_FieldGroup t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_FieldGroup_ID=50010 AND EXISTS (SELECT * FROM AD_FieldGroup_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_FieldGroup_ID!=t.AD_FieldGroup_ID)
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56522
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56529
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56524
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56528
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=56527
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=56525
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=56523
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=56520
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=56521
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=56526
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=3842
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=3841
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=3846
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=5133
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=5132
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Field_ID=3843
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Field_ID=3845
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=530,IsDisplayed='Y' WHERE AD_Field_ID=3844
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=540,IsDisplayed='Y' WHERE AD_Field_ID=3849
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Field_ID=3850
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Field_ID=5138
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Field_ID=3847
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=580,IsDisplayed='Y' WHERE AD_Field_ID=3839
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=590,IsDisplayed='Y' WHERE AD_Field_ID=3837
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=600,IsDisplayed='Y' WHERE AD_Field_ID=3840
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=610,IsDisplayed='Y' WHERE AD_Field_ID=3838
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=620,IsDisplayed='Y' WHERE AD_Field_ID=3836
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=630,IsDisplayed='Y' WHERE AD_Field_ID=3851
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=640,IsDisplayed='Y' WHERE AD_Field_ID=3852
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=650,IsDisplayed='Y' WHERE AD_Field_ID=3830
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=660,IsDisplayed='Y' WHERE AD_Field_ID=3831
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=670,IsDisplayed='Y' WHERE AD_Field_ID=3832
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=680,IsDisplayed='Y' WHERE AD_Field_ID=3833
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=690,IsDisplayed='Y' WHERE AD_Field_ID=4092
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=700,IsDisplayed='Y' WHERE AD_Field_ID=4093
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=710,IsDisplayed='Y' WHERE AD_Field_ID=5134
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=720,IsDisplayed='Y' WHERE AD_Field_ID=4094
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=730,IsDisplayed='Y' WHERE AD_Field_ID=4095
;

-- Dec 31, 2008 5:50:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=740,IsDisplayed='Y' WHERE AD_Field_ID=3823
;

-- Dec 31, 2008 5:51:19 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 17:51:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56522
;

-- Dec 31, 2008 5:51:26 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 17:51:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56529
;

-- Dec 31, 2008 5:51:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 17:51:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56524
;

-- Dec 31, 2008 5:51:37 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 17:51:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56528
;

-- Dec 31, 2008 5:52:00 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 17:52:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56527
;

-- Dec 31, 2008 5:52:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 17:52:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56525
;

-- Dec 31, 2008 5:52:51 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 17:52:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56525
;

-- Dec 31, 2008 5:53:01 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 17:53:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56523
;

-- Dec 31, 2008 5:53:29 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 17:53:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56520
;

-- Dec 31, 2008 5:53:39 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 17:53:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56521
;

-- Dec 31, 2008 5:53:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 17:53:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56526
;

-- Dec 31, 2008 5:55:57 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56547,53726,0,25,401,'P_FloorStock_Acct',TO_TIMESTAMP('2008-12-31 17:55:56','YYYY-MM-DD HH24:MI:SS'),0,'The Floor Stock account is the account used Manufacturing Order','EE01',22,'The Floor Stock is used for accounting the component with Issue Policy is set in No into Product Planning Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Floor Stock',TO_TIMESTAMP('2008-12-31 17:55:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 5:55:57 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56547 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:56:01 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_FloorStock_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:56:04 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:56:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56547
;

-- Dec 31, 2008 5:56:34 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56548,53721,0,25,401,'P_WIP_Acct',TO_TIMESTAMP('2008-12-31 17:56:34','YYYY-MM-DD HH24:MI:SS'),0,'The Work in Process account is the account used Manufacturing Order','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Work in Process',TO_TIMESTAMP('2008-12-31 17:56:34','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 5:56:34 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56548 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:56:40 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_WIP_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:56:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:56:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56548
;

-- Dec 31, 2008 5:56:57 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-12-31 17:56:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56547
;

-- Dec 31, 2008 5:56:58 PM ECT
-- Accounting Manufacturing Management
insert into t_alter_column values('m_product_category_acct','P_FloorStock_Acct','NUMERIC(10)',null,'NULL')
;

-- Dec 31, 2008 5:57:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:57:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56547
;

-- Dec 31, 2008 5:57:07 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2008-12-31 17:57:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56548
;

-- Dec 31, 2008 5:57:09 PM ECT
-- Accounting Manufacturing Management
insert into t_alter_column values('m_product_category_acct','P_WIP_Acct','NUMERIC(10)',null,'NULL')
;

-- Dec 31, 2008 5:57:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:57:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56548
;

-- Dec 31, 2008 5:57:36 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56549,53722,0,25,401,'P_MethodChangeVariance_Acct',TO_TIMESTAMP('2008-12-31 17:57:36','YYYY-MM-DD HH24:MI:SS'),0,'The Method Change Variance account is the account used Manufacturing Order','EE01',22,'The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Method Change Variance',TO_TIMESTAMP('2008-12-31 17:57:36','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 5:57:36 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56549 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:57:39 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_MethodChangeVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:57:43 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:57:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56549
;

-- Dec 31, 2008 5:58:02 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56550,53723,0,25,401,'P_UsageVariance_Acct',TO_TIMESTAMP('2008-12-31 17:58:02','YYYY-MM-DD HH24:MI:SS'),0,'The Usage Variance account is the account used Manufacturing Order','EE01',22,'The Usage Variance is used in Standard Costing. It reflects the difference between the  Quantities of Standard BOM  or Time Standard Manufacturing Workflow and Quantities of Manufacturing BOM or Time Manufacturing Workflow of Manufacturing Order.

If you change the Quantities or Time  defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Usage Variance',TO_TIMESTAMP('2008-12-31 17:58:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 5:58:03 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56550 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:58:06 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_UsageVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:58:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:58:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56550
;

-- Dec 31, 2008 5:58:32 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56551,53724,0,25,401,'P_RateVariance_Acct',TO_TIMESTAMP('2008-12-31 17:58:32','YYYY-MM-DD HH24:MI:SS'),0,'The Rate Variance account is the account used Manufacturing Order','EE01',22,'The Rate Variance is used in Standard Costing. It reflects the difference between the Standard Cost Rates and  The Cost Rates of Manufacturing Order.

If you change the Standard Rates then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Rate Variance',TO_TIMESTAMP('2008-12-31 17:58:32','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 5:58:32 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56551 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:58:34 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_RateVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:58:39 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:58:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56551
;

-- Dec 31, 2008 5:59:16 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56552,53725,0,25,401,'P_MixVariance_Acct',TO_TIMESTAMP('2008-12-31 17:59:16','YYYY-MM-DD HH24:MI:SS'),0,'The Mix Variance account is the account used Manufacturing Order','EE01',22,'The Mix Variance is used when a co-product  received in Inventory  is different the quantity  expected
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Mix Variance',TO_TIMESTAMP('2008-12-31 17:59:16','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 5:59:16 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56552 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:59:18 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_MixVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:59:23 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:59:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56552
;

-- Dec 31, 2008 5:59:47 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56553,53727,0,25,401,'P_CostOfProduction_Acct',TO_TIMESTAMP('2008-12-31 17:59:47','YYYY-MM-DD HH24:MI:SS'),0,'The Cost Of Production account is the account used Manufacturing Order','EE01',22,'The Cost Of Production is used for accounting Non productive Labor
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Of Production',TO_TIMESTAMP('2008-12-31 17:59:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 5:59:47 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56553 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 5:59:49 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_CostOfProduction_Acct NUMERIC(10)
;

-- Dec 31, 2008 5:59:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 17:59:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56553
;

-- Dec 31, 2008 6:00:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56554,53728,0,25,401,'P_Labor_Acct',TO_TIMESTAMP('2008-12-31 18:00:25','YYYY-MM-DD HH24:MI:SS'),0,'The Labor account is the account used Manufacturing Order','EE01',10,'The Labor is used for accounting the productive Labor
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Labor',TO_TIMESTAMP('2008-12-31 18:00:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 6:00:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56554 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:00:28 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_Labor_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:00:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:00:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56554
;

-- Dec 31, 2008 6:01:01 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56555,53729,0,25,401,'P_Burden_Acct',TO_TIMESTAMP('2008-12-31 18:01:01','YYYY-MM-DD HH24:MI:SS'),0,'The Burden account is the account used Manufacturing Order','EE01',10,'The Burden is used for accounting the Burden','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Burden',TO_TIMESTAMP('2008-12-31 18:01:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 6:01:01 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56555 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:01:04 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_Burden_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:01:08 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:01:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56555
;

-- Dec 31, 2008 6:01:49 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56556,53730,0,25,401,'P_OutsideProcessing_Acct',TO_TIMESTAMP('2008-12-31 18:01:49','YYYY-MM-DD HH24:MI:SS'),0,'The Outside Processing Account is the account used in Manufacturing Order','EE01',10,'The Outside Processing Account is used for accounting the Outside Processing','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Outside Processing',TO_TIMESTAMP('2008-12-31 18:01:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 31, 2008 6:01:49 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56556 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:01:52 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_OutsideProcessing_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:01:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:01:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56556
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56555,56530,0,324,TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0,'The Burden account is the account used Manufacturing Order',10,'EE01','The Burden is used for accounting the Burden','Y','Y','Y','N','N','N','N','N','Burden',TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56530 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56553,56531,0,324,TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0,'The Cost Of Production account is the account used Manufacturing Order',22,'EE01','The Cost Of Production is used for accounting Non productive Labor
','Y','Y','Y','N','N','N','N','N','Cost Of Production',TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56531 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56547,56532,0,324,TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0,'The Floor Stock account is the account used Manufacturing Order',22,'EE01','The Floor Stock is used for accounting the component with Issue Policy is set in No into Product Planning Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ','Y','Y','Y','N','N','N','N','N','Floor Stock',TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56532 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56554,56533,0,324,TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0,'The Labor account is the account used Manufacturing Order',10,'EE01','The Labor is used for accounting the productive Labor
','Y','Y','Y','N','N','N','N','N','Labor',TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56533 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56549,56534,0,324,TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0,'The Method Change Variance account is the account used Manufacturing Order',22,'EE01','The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','Y','N','N','N','N','N','Method Change Variance',TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56534 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56552,56535,0,324,TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0,'The Mix Variance account is the account used Manufacturing Order',22,'EE01','The Mix Variance is used when a co-product  received in Inventory  is different the quantity  expected
','Y','Y','Y','N','N','N','N','N','Mix Variance',TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56535 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:28 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56556,56536,0,324,TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0,'The Outside Processing Account is the account used in Manufacturing Order',10,'EE01','The Outside Processing Account is used for accounting the Outside Processing','Y','Y','Y','N','N','N','N','N','Outside Processing',TO_TIMESTAMP('2008-12-31 18:08:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:28 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56536 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:28 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56551,56537,0,324,TO_TIMESTAMP('2008-12-31 18:08:28','YYYY-MM-DD HH24:MI:SS'),0,'The Rate Variance account is the account used Manufacturing Order',22,'EE01','The Rate Variance is used in Standard Costing. It reflects the difference between the Standard Cost Rates and  The Cost Rates of Manufacturing Order.

If you change the Standard Rates then this variance is generate.','Y','Y','Y','N','N','N','N','N','Rate Variance',TO_TIMESTAMP('2008-12-31 18:08:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:28 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56537 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:28 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56550,56538,0,324,TO_TIMESTAMP('2008-12-31 18:08:28','YYYY-MM-DD HH24:MI:SS'),0,'The Usage Variance account is the account used Manufacturing Order',22,'EE01','The Usage Variance is used in Standard Costing. It reflects the difference between the  Quantities of Standard BOM  or Time Standard Manufacturing Workflow and Quantities of Manufacturing BOM or Time Manufacturing Workflow of Manufacturing Order.

If you change the Quantities or Time  defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','Y','N','N','N','N','N','Usage Variance',TO_TIMESTAMP('2008-12-31 18:08:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:28 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56538 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:08:28 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56548,56539,0,324,TO_TIMESTAMP('2008-12-31 18:08:28','YYYY-MM-DD HH24:MI:SS'),0,'The Work in Process account is the account used Manufacturing Order',22,'EE01','Y','Y','Y','N','N','N','N','N','Work in Process',TO_TIMESTAMP('2008-12-31 18:08:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:08:28 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56539 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56532
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56539
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56534
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56538
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56537
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56535
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56533
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56530
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56531
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56536
;

-- Dec 31, 2008 6:10:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=3945
;

-- Dec 31, 2008 6:11:18 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 18:11:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56532
;

-- Dec 31, 2008 6:11:24 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:11:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56539
;

-- Dec 31, 2008 6:11:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 18:11:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56534
;

-- Dec 31, 2008 6:11:55 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:11:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56538
;

-- Dec 31, 2008 6:12:03 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 18:12:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56537
;

-- Dec 31, 2008 6:12:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:12:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56535
;

-- Dec 31, 2008 6:12:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 18:12:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56533
;

-- Dec 31, 2008 6:12:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:12:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56530
;

-- Dec 31, 2008 6:12:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 18:12:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56531
;

-- Dec 31, 2008 6:12:32 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:12:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56536
;

-- Dec 31, 2008 6:14:24 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56557,53721,0,25,273,'P_WIP_Acct',TO_TIMESTAMP('2008-12-31 18:14:24','YYYY-MM-DD HH24:MI:SS'),0,'The Work in Process account is the account used Manufacturing Order','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Work in Process',TO_TIMESTAMP('2008-12-31 18:14:24','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:14:24 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56557 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:14:26 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_WIP_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:14:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:14:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56557
;

-- Dec 31, 2008 6:14:57 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56558,53722,0,25,273,'P_MethodChangeVariance_Acct',TO_TIMESTAMP('2008-12-31 18:14:57','YYYY-MM-DD HH24:MI:SS'),0,'The Method Change Variance account is the account used Manufacturing Order','EE01',22,'The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Method Change Variance',TO_TIMESTAMP('2008-12-31 18:14:57','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:14:57 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56558 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:15:00 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_MethodChangeVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:15:05 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:15:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56558
;

-- Dec 31, 2008 6:15:31 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56559,53723,0,25,273,'P_UsageVariance_Acct',TO_TIMESTAMP('2008-12-31 18:15:31','YYYY-MM-DD HH24:MI:SS'),0,'The Usage Variance account is the account used Manufacturing Order','EE01',22,'The Usage Variance is used in Standard Costing. It reflects the difference between the  Quantities of Standard BOM  or Time Standard Manufacturing Workflow and Quantities of Manufacturing BOM or Time Manufacturing Workflow of Manufacturing Order.

If you change the Quantities or Time  defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Usage Variance',TO_TIMESTAMP('2008-12-31 18:15:31','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:15:31 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56559 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:15:35 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_UsageVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:15:38 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:15:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56559
;

-- Dec 31, 2008 6:16:34 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56560,53724,0,25,273,'P_RateVariance_Acct',TO_TIMESTAMP('2008-12-31 18:16:34','YYYY-MM-DD HH24:MI:SS'),0,'The Rate Variance account is the account used Manufacturing Order','EE01',22,'The Rate Variance is used in Standard Costing. It reflects the difference between the Standard Cost Rates and  The Cost Rates of Manufacturing Order.

If you change the Standard Rates then this variance is generate.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Rate Variance',TO_TIMESTAMP('2008-12-31 18:16:34','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:16:34 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56560 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:16:37 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_RateVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:16:51 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:16:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56560
;

-- Dec 31, 2008 6:17:37 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56561,53725,0,25,273,'P_MixVariance_Acct',TO_TIMESTAMP('2008-12-31 18:17:37','YYYY-MM-DD HH24:MI:SS'),0,'The Mix Variance account is the account used Manufacturing Order','EE01',22,'The Mix Variance is used when a co-product  received in Inventory  is different the quantity  expected
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Mix Variance',TO_TIMESTAMP('2008-12-31 18:17:37','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:17:37 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56561 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:17:40 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_MixVariance_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:17:43 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:17:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56561
;

-- Dec 31, 2008 6:18:22 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56562,53726,0,25,273,'P_FloorStock_Acct',TO_TIMESTAMP('2008-12-31 18:18:22','YYYY-MM-DD HH24:MI:SS'),0,'The Floor Stock account is the account used Manufacturing Order','EE01',22,'The Floor Stock is used for accounting the component with Issue Policy is set in No into Product Planning Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Floor Stock',TO_TIMESTAMP('2008-12-31 18:18:22','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:18:22 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56562 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:18:25 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_FloorStock_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:18:32 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:18:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56562
;

-- Dec 31, 2008 6:19:07 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56563,53727,0,25,273,'P_CostOfProduction_Acct',TO_TIMESTAMP('2008-12-31 18:19:07','YYYY-MM-DD HH24:MI:SS'),0,'The Cost Of Production account is the account used Manufacturing Order','EE01',22,'The Cost Of Production is used for accounting Non productive Labor
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Of Production',TO_TIMESTAMP('2008-12-31 18:19:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:19:07 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56563 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:19:10 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_CostOfProduction_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:19:13 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:19:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56563
;

-- Dec 31, 2008 6:19:42 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56564,53728,0,25,273,'P_Labor_Acct',TO_TIMESTAMP('2008-12-31 18:19:42','YYYY-MM-DD HH24:MI:SS'),0,'The Labor account is the account used Manufacturing Order','EE01',22,'The Labor is used for accounting the productive Labor
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Labor',TO_TIMESTAMP('2008-12-31 18:19:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:19:42 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56564 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:19:44 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_Labor_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:19:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:19:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56564
;

-- Dec 31, 2008 6:20:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56565,53729,0,25,273,'P_Burden_Acct',TO_TIMESTAMP('2008-12-31 18:20:39','YYYY-MM-DD HH24:MI:SS'),0,'The Burden account is the account used Manufacturing Order','EE01',22,'The Burden is used for accounting the Burden','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Burden',TO_TIMESTAMP('2008-12-31 18:20:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:20:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56565 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:20:42 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_Burden_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:20:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:20:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56565
;

-- Dec 31, 2008 6:21:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56566,53730,0,25,273,'P_OutsideProcessing_Acct',TO_TIMESTAMP('2008-12-31 18:21:29','YYYY-MM-DD HH24:MI:SS'),0,'The Outside Processing Account is the account used in Manufacturing Order','EE01',22,'The Outside Processing Account is used for accounting the Outside Processing','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Outside Processing',TO_TIMESTAMP('2008-12-31 18:21:29','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 6:21:29 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56566 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 6:21:32 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_OutsideProcessing_Acct NUMERIC(10)
;

-- Dec 31, 2008 6:21:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 18:21:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56566
;

-- Dec 31, 2008 6:22:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56565,56540,0,210,TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0,'The Burden account is the account used Manufacturing Order',22,'EE01','The Burden is used for accounting the Burden','Y','Y','Y','N','N','N','N','N','Burden',TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56540 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56563,56541,0,210,TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0,'The Cost Of Production account is the account used Manufacturing Order',22,'EE01','The Cost Of Production is used for accounting Non productive Labor
','Y','Y','Y','N','N','N','N','N','Cost Of Production',TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56541 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56562,56542,0,210,TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0,'The Floor Stock account is the account used Manufacturing Order',22,'EE01','The Floor Stock is used for accounting the component with Issue Policy is set in No into Product Planning Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ','Y','Y','Y','N','N','N','N','N','Floor Stock',TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56542 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56564,56543,0,210,TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0,'The Labor account is the account used Manufacturing Order',22,'EE01','The Labor is used for accounting the productive Labor
','Y','Y','Y','N','N','N','N','N','Labor',TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56543 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56558,56544,0,210,TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0,'The Method Change Variance account is the account used Manufacturing Order',22,'EE01','The Method Change Variance is used in Standard Costing. It reflects the difference between the Standard BOM , Standard Manufacturing Workflow and Manufacturing BOM Manufacturing Workflow.

If you change the method the manufacturing defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','Y','N','N','N','N','N','Method Change Variance',TO_TIMESTAMP('2008-12-31 18:22:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56544 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56561,56545,0,210,TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0,'The Mix Variance account is the account used Manufacturing Order',22,'EE01','The Mix Variance is used when a co-product  received in Inventory  is different the quantity  expected
','Y','Y','Y','N','N','N','N','N','Mix Variance',TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56545 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56566,56546,0,210,TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0,'The Outside Processing Account is the account used in Manufacturing Order',22,'EE01','The Outside Processing Account is used for accounting the Outside Processing','Y','Y','Y','N','N','N','N','N','Outside Processing',TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56546 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56560,56547,0,210,TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0,'The Rate Variance account is the account used Manufacturing Order',22,'EE01','The Rate Variance is used in Standard Costing. It reflects the difference between the Standard Cost Rates and  The Cost Rates of Manufacturing Order.

If you change the Standard Rates then this variance is generate.','Y','Y','Y','N','N','N','N','N','Rate Variance',TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56547 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56559,56548,0,210,TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0,'The Usage Variance account is the account used Manufacturing Order',22,'EE01','The Usage Variance is used in Standard Costing. It reflects the difference between the  Quantities of Standard BOM  or Time Standard Manufacturing Workflow and Quantities of Manufacturing BOM or Time Manufacturing Workflow of Manufacturing Order.

If you change the Quantities or Time  defined in BOM or Workflow Manufacturig then this variance is generate.','Y','Y','Y','N','N','N','N','N','Usage Variance',TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56548 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56557,56549,0,210,TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0,'The Work in Process account is the account used Manufacturing Order',22,'EE01','Y','Y','Y','N','N','N','N','N','Work in Process',TO_TIMESTAMP('2008-12-31 18:22:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 6:22:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56549 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56542
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56549
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56544
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56548
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56547
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56545
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56543
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56540
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56541
;

-- Dec 31, 2008 6:23:11 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56546
;

-- Dec 31, 2008 6:24:14 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''',Updated=TO_TIMESTAMP('2008-12-31 18:24:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56542
;

-- Dec 31, 2008 6:24:23 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''', IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:24:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56549
;

-- Dec 31, 2008 6:24:29 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 18:24:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56544
;

-- Dec 31, 2008 6:24:38 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''', IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:24:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56548
;

-- Dec 31, 2008 6:24:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@IsBOM@=''Y''',Updated=TO_TIMESTAMP('2008-12-31 18:24:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56544
;

-- Dec 31, 2008 6:25:17 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''', IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56547
;

-- Dec 31, 2008 6:25:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2008-12-31 18:25:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56547
;

-- Dec 31, 2008 6:25:32 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''', IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:25:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56545
;

-- Dec 31, 2008 6:25:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''',Updated=TO_TIMESTAMP('2008-12-31 18:25:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56543
;

-- Dec 31, 2008 6:25:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''', IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:25:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56540
;

-- Dec 31, 2008 6:26:04 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''',Updated=TO_TIMESTAMP('2008-12-31 18:26:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56541
;

-- Dec 31, 2008 6:26:14 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, DisplayLogic='@IsBOM@=''Y''', IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:26:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56546
;

-- Dec 31, 2008 6:26:53 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56549
;

-- Dec 31, 2008 6:26:53 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56542
;

-- Dec 31, 2008 6:27:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2008-12-31 18:27:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56549
;

-- Dec 31, 2008 6:27:05 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:27:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56542
;

-- Dec 31, 2008 6:27:29 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56539
;

-- Dec 31, 2008 6:27:29 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56532
;

-- Dec 31, 2008 6:27:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2008-12-31 18:27:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56539
;

-- Dec 31, 2008 6:27:47 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:27:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56532
;

-- Dec 31, 2008 6:28:21 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56529
;

-- Dec 31, 2008 6:28:21 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56522
;

-- Dec 31, 2008 6:29:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Element SET Name='Work In Process',Updated=TO_TIMESTAMP('2008-12-31 18:29:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53721
;

-- Dec 31, 2008 6:29:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53721
;

-- Dec 31, 2008 6:29:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET ColumnName='P_WIP_Acct', Name='Work In Process', Description='The Work in Process account is the account used Manufacturing Order', Help=NULL WHERE AD_Element_ID=53721
;

-- Dec 31, 2008 6:29:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='P_WIP_Acct', Name='Work In Process', Description='The Work in Process account is the account used Manufacturing Order', Help=NULL, AD_Element_ID=53721 WHERE UPPER(ColumnName)='P_WIP_ACCT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 31, 2008 6:29:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='P_WIP_Acct', Name='Work In Process', Description='The Work in Process account is the account used Manufacturing Order', Help=NULL WHERE AD_Element_ID=53721 AND IsCentrallyMaintained='Y'
;

-- Dec 31, 2008 6:29:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET Name='Work In Process', Description='The Work in Process account is the account used Manufacturing Order', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53721) AND IsCentrallyMaintained='Y'
;

-- Dec 31, 2008 6:29:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_PrintFormatItem SET PrintName='Account for Work in Progress', Name='Work In Process' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53721)
;

-- Dec 31, 2008 6:29:51 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2008-12-31 18:29:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56529
;

-- Dec 31, 2008 6:29:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 18:29:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56522
;

-- Dec 31, 2008 10:11:49 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53731,0,'P_Overhead_Acct',TO_TIMESTAMP('2008-12-31 22:11:46','YYYY-MM-DD HH24:MI:SS'),0,'The Overhead account is the account used  in Manufacturing Order ','EE01',NULL,'Y','Overhead','Account for Overhead',TO_TIMESTAMP('2008-12-31 22:11:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 10:11:49 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53731 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 10:12:12 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56567,53731,0,25,315,'P_Overhead_Acct',TO_TIMESTAMP('2008-12-31 22:12:11','YYYY-MM-DD HH24:MI:SS'),0,'The Overhead account is the account used  in Manufacturing Order ','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Overhead',TO_TIMESTAMP('2008-12-31 22:12:11','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 10:12:12 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56567 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 10:12:15 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_Overhead_Acct NUMERIC(10)
;

-- Dec 31, 2008 10:12:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 22:12:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56567
;

-- Dec 31, 2008 10:14:22 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53732,0,'P_Scrap_Acct',TO_TIMESTAMP('2008-12-31 22:14:19','YYYY-MM-DD HH24:MI:SS'),0,'The Scrap account is the account used  in Manufacturing Order ','EE01','Y','Scrap','Account for Scrap',TO_TIMESTAMP('2008-12-31 22:14:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 10:14:22 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53732 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 31, 2008 10:14:45 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56568,53732,0,25,315,'P_Scrap_Acct',TO_TIMESTAMP('2008-12-31 22:14:41','YYYY-MM-DD HH24:MI:SS'),0,'The Scrap account is the account used  in Manufacturing Order ','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Scrap',TO_TIMESTAMP('2008-12-31 22:14:41','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 10:14:45 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56568 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 10:14:50 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_Scrap_Acct NUMERIC(10)
;

-- Dec 31, 2008 10:15:05 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 22:15:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56568
;

-- Dec 31, 2008 10:15:38 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56569,53731,0,25,401,'P_Overhead_Acct',TO_TIMESTAMP('2008-12-31 22:15:37','YYYY-MM-DD HH24:MI:SS'),0,'The Overhead account is the account used  in Manufacturing Order ','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Overhead',0,TO_TIMESTAMP('2008-12-31 22:15:37','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 10:15:38 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56569 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 10:15:41 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_Overhead_Acct NUMERIC(10)
;

-- Dec 31, 2008 10:15:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 22:15:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56569
;

-- Dec 31, 2008 10:16:08 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56570,53732,0,25,401,'P_Scrap_Acct',TO_TIMESTAMP('2008-12-31 22:16:04','YYYY-MM-DD HH24:MI:SS'),0,'The Scrap account is the account used  in Manufacturing Order ','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Scrap',0,TO_TIMESTAMP('2008-12-31 22:16:04','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 10:16:08 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56570 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 10:16:10 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_Scrap_Acct NUMERIC(10)
;

-- Dec 31, 2008 10:16:16 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 22:16:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56570
;

-- Dec 31, 2008 10:16:49 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56571,53731,0,25,273,'P_Overhead_Acct',TO_TIMESTAMP('2008-12-31 22:16:48','YYYY-MM-DD HH24:MI:SS'),0,'The Overhead account is the account used  in Manufacturing Order ','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Overhead',TO_TIMESTAMP('2008-12-31 22:16:48','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 10:16:49 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56571 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 10:16:52 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_Overhead_Acct NUMERIC(10)
;

-- Dec 31, 2008 10:17:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56572,53732,0,25,273,'P_Scrap_Acct',TO_TIMESTAMP('2008-12-31 22:17:24','YYYY-MM-DD HH24:MI:SS'),0,'The Scrap account is the account used  in Manufacturing Order ','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Scrap',TO_TIMESTAMP('2008-12-31 22:17:24','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 31, 2008 10:17:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56572 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 31, 2008 10:17:27 PM ECT
-- Accounting Manufacturing Management
ALTER TABLE M_Product_Acct ADD COLUMN P_Scrap_Acct NUMERIC(10)
;

-- Dec 31, 2008 10:17:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 22:17:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56572
;

-- Dec 31, 2008 10:17:39 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-31 22:17:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56571
;

-- Dec 31, 2008 10:18:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56567,56550,0,252,TO_TIMESTAMP('2008-12-31 22:18:05','YYYY-MM-DD HH24:MI:SS'),0,'The Overhead account is the account used  in Manufacturing Order ',22,'EE01','Y','Y','Y','N','N','N','N','N','Overhead',TO_TIMESTAMP('2008-12-31 22:18:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 10:18:06 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56550 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 10:18:08 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56568,56551,0,252,TO_TIMESTAMP('2008-12-31 22:18:06','YYYY-MM-DD HH24:MI:SS'),0,'The Scrap account is the account used  in Manufacturing Order ',22,'EE01','Y','Y','Y','N','N','N','N','N','Scrap',TO_TIMESTAMP('2008-12-31 22:18:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 10:18:08 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56551 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=56550
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=56551
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=3842
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=3841
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=3846
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Field_ID=5133
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Field_ID=5132
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=530,IsDisplayed='Y' WHERE AD_Field_ID=3843
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=540,IsDisplayed='Y' WHERE AD_Field_ID=3845
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Field_ID=3844
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Field_ID=3849
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Field_ID=3850
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=580,IsDisplayed='Y' WHERE AD_Field_ID=5138
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=590,IsDisplayed='Y' WHERE AD_Field_ID=3847
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=600,IsDisplayed='Y' WHERE AD_Field_ID=3839
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=610,IsDisplayed='Y' WHERE AD_Field_ID=3837
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=620,IsDisplayed='Y' WHERE AD_Field_ID=3840
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=630,IsDisplayed='Y' WHERE AD_Field_ID=3838
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=640,IsDisplayed='Y' WHERE AD_Field_ID=3836
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=650,IsDisplayed='Y' WHERE AD_Field_ID=3851
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=660,IsDisplayed='Y' WHERE AD_Field_ID=3852
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=670,IsDisplayed='Y' WHERE AD_Field_ID=3830
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=680,IsDisplayed='Y' WHERE AD_Field_ID=3831
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=690,IsDisplayed='Y' WHERE AD_Field_ID=3832
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=700,IsDisplayed='Y' WHERE AD_Field_ID=3833
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=710,IsDisplayed='Y' WHERE AD_Field_ID=4092
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=720,IsDisplayed='Y' WHERE AD_Field_ID=4093
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=730,IsDisplayed='Y' WHERE AD_Field_ID=5134
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=740,IsDisplayed='Y' WHERE AD_Field_ID=4094
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=750,IsDisplayed='Y' WHERE AD_Field_ID=4095
;

-- Dec 31, 2008 10:18:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=760,IsDisplayed='Y' WHERE AD_Field_ID=3823
;

-- Dec 31, 2008 10:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 22:19:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56550
;

-- Dec 31, 2008 10:19:17 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 22:19:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56551
;

-- Dec 31, 2008 10:19:38 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56569,56552,0,324,TO_TIMESTAMP('2008-12-31 22:19:37','YYYY-MM-DD HH24:MI:SS'),0,'The Overhead account is the account used  in Manufacturing Order ',22,'EE01','Y','Y','Y','N','N','N','N','N','Overhead',TO_TIMESTAMP('2008-12-31 22:19:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 10:19:38 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56552 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 10:19:40 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56570,56553,0,324,TO_TIMESTAMP('2008-12-31 22:19:38','YYYY-MM-DD HH24:MI:SS'),0,'The Scrap account is the account used  in Manufacturing Order ',22,'EE01','Y','Y','Y','N','N','N','N','N','Scrap',TO_TIMESTAMP('2008-12-31 22:19:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 10:19:40 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56553 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 10:19:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56552
;

-- Dec 31, 2008 10:19:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56553
;

-- Dec 31, 2008 10:19:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=3945
;

-- Dec 31, 2008 10:20:00 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 22:20:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56552
;

-- Dec 31, 2008 10:20:05 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 22:20:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56553
;

-- Dec 31, 2008 10:20:33 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56571,56554,0,210,TO_TIMESTAMP('2008-12-31 22:20:32','YYYY-MM-DD HH24:MI:SS'),0,'The Overhead account is the account used  in Manufacturing Order ',22,'EE01','Y','Y','Y','N','N','N','N','N','Overhead',TO_TIMESTAMP('2008-12-31 22:20:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 10:20:33 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56554 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 10:20:35 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56572,56555,0,210,TO_TIMESTAMP('2008-12-31 22:20:33','YYYY-MM-DD HH24:MI:SS'),0,'The Scrap account is the account used  in Manufacturing Order ',22,'EE01','Y','Y','Y','N','N','N','N','N','Scrap',TO_TIMESTAMP('2008-12-31 22:20:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 31, 2008 10:20:35 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56555 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 31, 2008 10:20:53 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010,Updated=TO_TIMESTAMP('2008-12-31 22:20:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56554
;

-- Dec 31, 2008 10:20:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET AD_FieldGroup_ID=50010, IsSameLine='Y',Updated=TO_TIMESTAMP('2008-12-31 22:20:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56555
;

-- Dec 31, 2008 10:21:26 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@ProductType@=''R''',Updated=TO_TIMESTAMP('2008-12-31 22:21:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56554
;

-- Dec 31, 2008 10:21:29 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@ProductType@=''R''',Updated=TO_TIMESTAMP('2008-12-31 22:21:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56555
;

-- Jan 1, 2009 1:14:30 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@ProductType@=''R''',Updated=TO_TIMESTAMP('2009-01-01 01:14:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56546
;

-- Jan 1, 2009 1:14:35 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@ProductType@=''R''',Updated=TO_TIMESTAMP('2009-01-01 01:14:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56541
;

-- Jan 1, 2009 1:14:40 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@ProductType@=''R''',Updated=TO_TIMESTAMP('2009-01-01 01:14:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56540
;

-- Jan 1, 2009 1:14:44 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@ProductType@=''R''',Updated=TO_TIMESTAMP('2009-01-01 01:14:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56543
;

-- Jan 1, 2009 1:16:11 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56554
;

-- Jan 1, 2009 1:16:11 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56555
;

