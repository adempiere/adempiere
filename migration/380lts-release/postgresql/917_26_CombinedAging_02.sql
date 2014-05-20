-- Nov 7, 2013 5:36:59 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69826,148,0,18,132,53323,'Account_ID','(select max(account_id) from fact_acct fa where fa.c_tax_id is null and ((fa.ad_table_id=318 and fa.record_id=t_combinedaging.c_invoice_id) or (fa.ad_table_id=335 and fa.record_id=t_combinedaging.c_payment_id and case when t_combinedaging.issotrx=''Y'' then fa.amtacctcr<>0 else fa.amtacctdr<>0 end)))',TO_TIMESTAMP('2013-11-07 17:36:55','YYYY-MM-DD HH24:MI:SS'),0,'Account used','D',22,'The (natural) account used','Y','Y','N','N','N','N','N','N','N','N','N','N','N','N','Account',0,TO_TIMESTAMP('2013-11-07 17:36:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 7, 2013 5:36:59 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69826 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 7, 2013 5:37:14 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Column SET EntityType='D',Updated=TO_TIMESTAMP('2013-11-07 17:37:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69826
;

-- Nov 7, 2013 5:38:26 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56594,0,'AmtAcctOpenPosted',TO_TIMESTAMP('2013-11-07 17:38:25','YYYY-MM-DD HH24:MI:SS'),0,'D',0,'Y','Open Posted Amount','Open Posted Amount',TO_TIMESTAMP('2013-11-07 17:38:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 7, 2013 5:38:26 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56594 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Nov 7, 2013 5:41:30 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69827,56594,0,12,53323,'AmtAcctOpenPosted','(select max((amtacctdr - amtacctcr) * CASE WHEN t_combinedaging.trxamt=0 THEN 0 ELSE (t_combinedaging.openamt/t_combinedaging.trxamt) END) * CASE WHEN t_combinedaging.issotrx=''N'' THEN -1 ELSE 1 END from fact_acct fa where fa.c_tax_id is null and ((fa.ad_table_id=318 and fa.record_id=t_combinedaging.c_invoice_id) or (fa.ad_table_id=335 and fa.record_id=t_combinedaging.c_payment_id and case when t_combinedaging.issotrx=''Y'' then fa.amtacctcr<>0 else fa.amtacctdr<>0 end)))',TO_TIMESTAMP('2013-11-07 17:41:29','YYYY-MM-DD HH24:MI:SS'),0,'D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','N','Open Posted Amount',0,TO_TIMESTAMP('2013-11-07 17:41:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 7, 2013 5:41:30 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69827 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 7, 2013 5:42:29 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56595,0,'AmtAcctOpenSource',TO_TIMESTAMP('2013-11-07 17:42:28','YYYY-MM-DD HH24:MI:SS'),0,'D',0,'Y','Open Source Amount','Open Source Amount',TO_TIMESTAMP('2013-11-07 17:42:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 7, 2013 5:42:29 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56595 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Nov 7, 2013 5:44:13 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69828,56595,0,12,53323,'AmtAcctOpenSource','(select max((amtsourcedr - amtsourcecr) * CASE WHEN t_combinedaging.trxamt=0 THEN 0 ELSE (t_combinedaging.openamt/t_combinedaging.trxamt) END) * CASE WHEN t_combinedaging.issotrx=''N'' THEN -1 ELSE 1 END from fact_acct fa where fa.c_tax_id is null and ((fa.ad_table_id=318 and fa.record_id=t_combinedaging.c_invoice_id) or (fa.ad_table_id=335 and fa.record_id=t_combinedaging.c_payment_id and case when t_combinedaging.issotrx=''Y'' then fa.amtacctcr<>0 else fa.amtacctdr<>0 end)))',TO_TIMESTAMP('2013-11-07 17:44:12','YYYY-MM-DD HH24:MI:SS'),0,'D',14,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','N','Open Source Amount',0,TO_TIMESTAMP('2013-11-07 17:44:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 7, 2013 5:44:13 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69828 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 7, 2013 5:45:04 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Column SET Description='The calculate open amount in document source currency ',Updated=TO_TIMESTAMP('2013-11-07 17:45:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69828
;

-- Nov 7, 2013 5:45:04 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Field SET Name='Open Source Amount', Description='The calculate open amount in document source currency ', Help=NULL WHERE AD_Column_ID=69828 AND IsCentrallyMaintained='Y'
;

-- Nov 7, 2013 5:46:12 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56596,0,'AmtRevalDiff',TO_TIMESTAMP('2013-11-07 17:46:11','YYYY-MM-DD HH24:MI:SS'),0,'Revaluation difference','D',0,'Y','Revalue Diff','Revalue Diff',TO_TIMESTAMP('2013-11-07 17:46:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 7, 2013 5:46:12 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56596 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Nov 7, 2013 5:47:45 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69829,56596,0,12,53323,'AmtRevalDiff','(select max(t_combinedaging.openamt-((amtacctdr - amtacctcr) * CASE WHEN t_combinedaging.trxamt=0 THEN 0 ELSE (t_combinedaging.openamt/t_combinedaging.trxamt) END) * CASE WHEN t_combinedaging.issotrx=''N'' THEN -1 ELSE 1 END) from fact_acct fa where fa.c_tax_id is null and ((fa.ad_table_id=318 and fa.record_id=t_combinedaging.c_invoice_id) or (fa.ad_table_id=335 and fa.record_id=t_combinedaging.c_payment_id and case when t_combinedaging.issotrx=''Y'' then fa.amtacctcr<>0 else fa.amtacctdr<>0 end)))',TO_TIMESTAMP('2013-11-07 17:47:44','YYYY-MM-DD HH24:MI:SS'),0,'Revaluation difference','D',14,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','N','Revalue Diff',0,TO_TIMESTAMP('2013-11-07 17:47:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 7, 2013 5:47:45 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69829 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 7, 2013 5:48:14 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2013-11-07 17:48:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69829
;

-- Nov 7, 2013 5:48:36 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2013-11-07 17:48:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69828
;

-- Nov 7, 2013 5:49:33 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Column SET Description='The calculated open amount of the posted account entry in the currency of Accouting Schema.',Updated=TO_TIMESTAMP('2013-11-07 17:49:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69827
;

-- Nov 7, 2013 5:49:33 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Field SET Name='Open Posted Amount', Description='The calculated open amount of the posted account entry in the currency of Accouting Schema.', Help=NULL WHERE AD_Column_ID=69827 AND IsCentrallyMaintained='Y'
;

-- Nov 7, 2013 5:50:23 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Column SET Description='The calculated open amount in document source currency ',Updated=TO_TIMESTAMP('2013-11-07 17:50:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69828
;

-- Nov 7, 2013 5:50:23 PM IST
-- Added the Virtual Columns in T_Combined Aging Table
UPDATE AD_Field SET Name='Open Source Amount', Description='The calculated open amount in document source currency ', Help=NULL WHERE AD_Column_ID=69828 AND IsCentrallyMaintained='Y'
;

