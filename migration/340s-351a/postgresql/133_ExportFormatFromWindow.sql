-- Mar 24, 2008 11:18:28 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53085,'6',TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','N','EXP_Format Generator','Y',0,0,TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0,'EXP_Format Generator')
;

-- Mar 24, 2008 11:18:28 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53085 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Mar 24, 2008 11:18:28 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53085,0,TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 11:18:28 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53085,102,TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 11:18:28 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53085,103,TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 11:18:28 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53085,50001,TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-24 11:18:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 11:19:27 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,143,0,53085,53141,19,'AD_Window_ID',TO_TIMESTAMP('2008-03-24 11:19:27','YYYY-MM-DD HH24:MI:SS'),0,'EE05',22,'Y','Y','N','N','Window',10,TO_TIMESTAMP('2008-03-24 11:19:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 11:19:27 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53141 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 24, 2008 11:19:41 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55059,524,0,53085,28,53072,'Processing',TO_TIMESTAMP('2008-03-24 11:19:40','YYYY-MM-DD HH24:MI:SS'),0,'EE05',1,'Y','N','N','N','N','N','N','N','N','N','Y','Process Now',0,TO_TIMESTAMP('2008-03-24 11:19:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 24, 2008 11:19:41 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55059 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 24, 2008 11:20:04 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55059,55187,0,53085,TO_TIMESTAMP('2008-03-24 11:20:04','YYYY-MM-DD HH24:MI:SS'),0,1,'EE05','Y','Y','Y','N','N','N','N','N','Process Now',TO_TIMESTAMP('2008-03-24 11:20:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 11:20:04 AM CST
-- Generate a Export Format from a Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55187 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 24, 2008 11:20:32 AM CST
-- Generate a Export Format from a Window
UPDATE AD_Field SET IsCentrallyMaintained='N', IsSameLine='Y', Name='Create from a Window',Updated=TO_TIMESTAMP('2008-03-24 11:20:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=55187
;

-- Mar 24, 2008 11:20:32 AM CST
-- Generate a Export Format from a Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=55187
;

UPDATE AD_Process SET Classname='org.eevolution.process.ExportFormatGenerator',Updated=TO_TIMESTAMP('2008-03-24 11:42:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53085
;

ALTER TABLE EXP_Format ADD COLUMN Processing CHAR(1)
;

