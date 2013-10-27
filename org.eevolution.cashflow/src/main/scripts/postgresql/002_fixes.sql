-- Sep 16, 2009 10:37:23 AM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET Callout='org.eevolution.model.CalloutCashFlow.DueDate',Updated=TO_TIMESTAMP('2009-09-16 10:37:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56992
;

-- Sep 16, 2009 10:37:57 AM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Process SET Value='RV_CashFlow',Updated=TO_TIMESTAMP('2009-09-16 10:37:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53165
;

-- Sep 16, 2009 11:10:31 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58383,558,0,30,631,'C_Order_ID',TO_TIMESTAMP('2009-09-16 11:10:05','YYYY-MM-DD HH24:MI:SS'),0,'Order','EE06',10,'The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Order',0,TO_TIMESTAMP('2009-09-16 11:10:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 16, 2009 11:10:31 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58383 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 16, 2009 11:10:42 AM EEST
-- [ 2859820 ] Fix CashFlow integration
ALTER TABLE T_Aging ADD COLUMN C_Order_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 16, 2009 11:11:32 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58384,53798,0,30,631,'C_CashFlow_ID',TO_TIMESTAMP('2009-09-16 11:11:16','YYYY-MM-DD HH24:MI:SS'),0,'EE06',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','C_CashFlow_ID',0,TO_TIMESTAMP('2009-09-16 11:11:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 16, 2009 11:11:32 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58384 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 16, 2009 11:11:38 AM EEST
-- [ 2859820 ] Fix CashFlow integration
ALTER TABLE T_Aging ADD COLUMN C_CashFlow_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 16, 2009 11:12:37 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=1,Updated=TO_TIMESTAMP('2009-09-16 11:12:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56999
;

-- Sep 16, 2009 11:15:33 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53928,0,'IsIncludeOrders',TO_TIMESTAMP('2009-09-16 11:15:32','YYYY-MM-DD HH24:MI:SS'),0,'Include Orders in reporting','EE06','Y','Include Orders','Orders',TO_TIMESTAMP('2009-09-16 11:15:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 16, 2009 11:15:33 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53928 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 16, 2009 11:16:06 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53929,0,'IsIncludeCashFlow',TO_TIMESTAMP('2009-09-16 11:16:05','YYYY-MM-DD HH24:MI:SS'),0,'Include Cash Flow records in reporting','EE06','Y','Include Cash Flow records','Cash Flow',TO_TIMESTAMP('2009-09-16 11:16:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 16, 2009 11:16:06 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53929 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 16, 2009 11:17:20 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET AD_Element_ID=53928, ColumnName='IsIncludeOrders', DefaultValue='N', Description='Include Orders in reporting', IsMandatory='Y', Name='Include Orders',Updated=TO_TIMESTAMP('2009-09-16 11:17:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53305
;

-- Sep 16, 2009 11:17:20 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53305
;

-- Sep 16, 2009 11:19:04 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53930,0,'IsIncludeInvoices',TO_TIMESTAMP('2009-09-16 11:19:03','YYYY-MM-DD HH24:MI:SS'),0,'Include Orders in reporting','U','Y','Include Orders','Orders',TO_TIMESTAMP('2009-09-16 11:19:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 16, 2009 11:19:04 AM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53930 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 16, 2009 11:19:27 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Element SET Description='Include Invoices (legal)  in reporting', EntityType='EE06', Name='Include Invoices', PrintName='Invoices',Updated=TO_TIMESTAMP('2009-09-16 11:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53930
;

-- Sep 16, 2009 11:19:27 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53930
;

-- Sep 16, 2009 11:19:27 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET ColumnName='IsIncludeInvoices', Name='Include Invoices', Description='Include Invoices (legal)  in reporting', Help=NULL WHERE AD_Element_ID=53930
;

-- Sep 16, 2009 11:19:27 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET ColumnName='IsIncludeInvoices', Name='Include Invoices', Description='Include Invoices (legal)  in reporting', Help=NULL, AD_Element_ID=53930 WHERE UPPER(ColumnName)='ISINCLUDEINVOICES' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 16, 2009 11:19:27 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET ColumnName='IsIncludeInvoices', Name='Include Invoices', Description='Include Invoices (legal)  in reporting', Help=NULL WHERE AD_Element_ID=53930 AND IsCentrallyMaintained='Y'
;

-- Sep 16, 2009 11:19:27 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Field SET Name='Include Invoices', Description='Include Invoices (legal)  in reporting', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53930) AND IsCentrallyMaintained='Y'
;

-- Sep 16, 2009 11:19:27 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_PrintFormatItem SET PrintName='Invoices', Name='Include Invoices' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53930)
;

-- Sep 16, 2009 11:20:06 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET AD_Element_ID=53930, ColumnName='IsIncludeInvoices', DefaultValue='Y', Description='Include Invoices (legal)  in reporting', IsMandatory='Y', Name='Include Invoices',Updated=TO_TIMESTAMP('2009-09-16 11:20:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53304
;

-- Sep 16, 2009 11:20:06 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53304
;

-- Sep 16, 2009 11:20:34 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET DefaultValue='@#Date@', IsMandatory='Y',Updated=TO_TIMESTAMP('2009-09-16 11:20:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53310
;

-- Sep 16, 2009 11:21:30 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Element SET ColumnName='IsIncludeCashFlows',Updated=TO_TIMESTAMP('2009-09-16 11:21:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53929
;

-- Sep 16, 2009 11:21:30 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow records', Description='Include Cash Flow records in reporting', Help=NULL WHERE AD_Element_ID=53929
;

-- Sep 16, 2009 11:21:30 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow records', Description='Include Cash Flow records in reporting', Help=NULL, AD_Element_ID=53929 WHERE UPPER(ColumnName)='ISINCLUDECASHFLOWS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 16, 2009 11:21:30 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow records', Description='Include Cash Flow records in reporting', Help=NULL WHERE AD_Element_ID=53929 AND IsCentrallyMaintained='Y'
;

-- Sep 16, 2009 11:21:36 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Element SET Description='Include Cash Flow (manual) records in reporting',Updated=TO_TIMESTAMP('2009-09-16 11:21:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53929
;

-- Sep 16, 2009 11:21:36 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53929
;

-- Sep 16, 2009 11:21:36 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow records', Description='Include Cash Flow (manual) records in reporting', Help=NULL WHERE AD_Element_ID=53929
;

-- Sep 16, 2009 11:21:36 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow records', Description='Include Cash Flow (manual) records in reporting', Help=NULL, AD_Element_ID=53929 WHERE UPPER(ColumnName)='ISINCLUDECASHFLOWS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 16, 2009 11:21:36 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow records', Description='Include Cash Flow (manual) records in reporting', Help=NULL WHERE AD_Element_ID=53929 AND IsCentrallyMaintained='Y'
;

-- Sep 16, 2009 11:21:36 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Field SET Name='Include Cash Flow records', Description='Include Cash Flow (manual) records in reporting', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53929) AND IsCentrallyMaintained='Y'
;

-- Sep 16, 2009 11:22:02 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Element SET Name='Include Cash Flow (manual) records',Updated=TO_TIMESTAMP('2009-09-16 11:22:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53929
;

-- Sep 16, 2009 11:22:02 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53929
;

-- Sep 16, 2009 11:22:02 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow (manual) records', Description='Include Cash Flow (manual) records in reporting', Help=NULL WHERE AD_Element_ID=53929
;

-- Sep 16, 2009 11:22:02 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow (manual) records', Description='Include Cash Flow (manual) records in reporting', Help=NULL, AD_Element_ID=53929 WHERE UPPER(ColumnName)='ISINCLUDECASHFLOWS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 16, 2009 11:22:02 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET ColumnName='IsIncludeCashFlows', Name='Include Cash Flow (manual) records', Description='Include Cash Flow (manual) records in reporting', Help=NULL WHERE AD_Element_ID=53929 AND IsCentrallyMaintained='Y'
;

-- Sep 16, 2009 11:22:02 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Field SET Name='Include Cash Flow (manual) records', Description='Include Cash Flow (manual) records in reporting', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53929) AND IsCentrallyMaintained='Y'
;

-- Sep 16, 2009 11:22:02 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_PrintFormatItem SET PrintName='Cash Flow', Name='Include Cash Flow (manual) records' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53929)
;

-- Sep 16, 2009 11:22:09 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET AD_Element_ID=53929, ColumnName='IsIncludeCashFlows', DefaultValue='Y', Description='Include Cash Flow (manual) records in reporting', IsMandatory='Y', Name='Include Cash Flow (manual) records',Updated=TO_TIMESTAMP('2009-09-16 11:22:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53306
;

-- Sep 16, 2009 11:22:09 AM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53306
;

alter table T_Aging drop constraint t_aging_pkey;

-- Sep 16, 2009 12:10:55 PM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2009-09-16 12:10:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58384
;

-- Sep 16, 2009 12:12:00 PM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2009-09-16 12:12:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58383
;

-- Sep 16, 2009 12:12:09 PM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2009-09-16 12:12:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=10142
;

-- Sep 16, 2009 12:12:11 PM EEST
-- [ 2859820 ] Fix CashFlow integration
insert into t_alter_column values('t_aging','C_Invoice_ID','NUMERIC(10)',null,'NULL')
;

-- Sep 16, 2009 12:12:11 PM EEST
-- [ 2859820 ] Fix CashFlow integration
insert into t_alter_column values('t_aging','C_Invoice_ID',null,'NULL',null)
;

-- Sep 16, 2009 12:12:27 PM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_TIMESTAMP('2009-09-16 12:12:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=10142
;

-- Sep 16, 2009 12:17:44 PM EEST
-- [ 2859820 ] Fix CashFlow integration
insert into t_alter_column values('t_aging','C_Order_ID','NUMERIC(10)',null,'NULL')
;

-- Sep 16, 2009 12:17:44 PM EEST
-- [ 2859820 ] Fix CashFlow integration
insert into t_alter_column values('t_aging','C_Order_ID',null,'NULL',null)
;

-- Sep 16, 2009 12:18:10 PM EEST
-- [ 2859820 ] Fix CashFlow integration
insert into t_alter_column values('t_aging','C_CashFlow_ID','NUMERIC(10)',null,'NULL')
;

-- Sep 16, 2009 12:18:10 PM EEST
-- [ 2859820 ] Fix CashFlow integration
insert into t_alter_column values('t_aging','C_CashFlow_ID',null,'NULL',null)
;

-- Sep 16, 2009 12:20:07 PM EEST
-- [ 2859820 ] Fix CashFlow integration
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53029,631,TO_TIMESTAMP('2009-09-16 12:20:06','YYYY-MM-DD HH24:MI:SS'),0,'EE06','Y','RV_CashFlow',TO_TIMESTAMP('2009-09-16 12:20:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 16, 2009 12:20:17 PM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process SET AD_ReportView_ID=53029,Updated=TO_TIMESTAMP('2009-09-16 12:20:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53165
;

-- Sep 16, 2009 12:41:39 PM EEST
-- [ 2859820 ] Fix CashFlow integration
UPDATE AD_Process_Para SET DefaultValue=NULL, IsMandatory='N',Updated=TO_TIMESTAMP('2009-09-16 12:41:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53310
;

-- 16.09.2009 15:29:56 EEST
-- Create confirmations force
UPDATE AD_Column SET DefaultValue='0', IsMandatory='Y', IsUpdateable='N',Updated=TO_TIMESTAMP('2009-09-16 15:29:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=10142
;

-- 16.09.2009 15:30:16 EEST
-- Create confirmations force
UPDATE AD_Column SET IsParent='Y', IsMandatory='Y', IsUpdateable='N',Updated=TO_TIMESTAMP('2009-09-16 15:30:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58383
;

-- 16.09.2009 15:30:39 EEST
-- Create confirmations force
UPDATE AD_Column SET DefaultValue='0', IsUpdateable='N',Updated=TO_TIMESTAMP('2009-09-16 15:30:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58383
;

-- 16.09.2009 15:30:55 EEST
-- Create confirmations force
insert into t_alter_column values('t_aging','C_Order_ID','NUMERIC(10)',null,'0')
;

-- 16.09.2009 15:30:56 EEST
-- Create confirmations force
insert into t_alter_column values('t_aging','C_Order_ID',null,'NOT NULL',null)
;

-- 16.09.2009 15:31:07 EEST
-- Create confirmations force
insert into t_alter_column values('t_aging','C_Invoice_ID','NUMERIC(10)',null,'0')
;

-- 16.09.2009 15:31:07 EEST
-- Create confirmations force
insert into t_alter_column values('t_aging','C_Invoice_ID',null,'NOT NULL',null)
;

-- 16.09.2009 15:31:37 EEST
-- Create confirmations force
UPDATE AD_Column SET IsParent='Y', DefaultValue='0', IsMandatory='Y', IsUpdateable='N',Updated=TO_TIMESTAMP('2009-09-16 15:31:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58384
;

-- 16.09.2009 15:31:50 EEST
-- Create confirmations force
insert into t_alter_column values('t_aging','C_CashFlow_ID','NUMERIC(10)',null,'0')
;

-- 16.09.2009 15:31:51 EEST
-- Create confirmations force
insert into t_alter_column values('t_aging','C_CashFlow_ID',null,'NOT NULL',null)
;

alter table T_Aging add constraint t_aging_key PRIMARY KEY (ad_pinstance_id, c_bpartner_id, c_currency_id, c_invoice_id, c_invoicepayschedule_id, c_order_id, c_cashflow_id)
;

