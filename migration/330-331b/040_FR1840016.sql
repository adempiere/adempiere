-- Nov 27, 2007 11:00:14 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53227,0,'IsPostIfClearingEqual',TO_DATE('2007-11-27 23:00:10','YYYY-MM-DD HH24:MI:SS'),100,'This flag controls if Adempiere must post when clearing (transit) and final accounts are the same','D',NULL,'Y','Post if Clearing Equal','Post if Clearing Equal',TO_DATE('2007-11-27 23:00:10','YYYY-MM-DD HH24:MI:SS'),100)
/

-- Nov 27, 2007 11:00:16 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53227 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
/

-- Nov 27, 2007 11:00:45 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
UPDATE AD_Column SET ColumnName='IsPostIfClearingEqual', Name='Post if Clearing Equal', Description='This flag controls if Adempiere must post when clearing (transit) and final accounts are the same', Help=NULL WHERE AD_Element_ID=53227
/

-- Nov 27, 2007 11:00:45 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
UPDATE AD_Field SET Name='Post if Clearing Equal', Description='This flag controls if Adempiere must post when clearing (transit) and final accounts are the same', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53227) AND IsCentrallyMaintained='Y'
/

-- Nov 27, 2007 11:00:45 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
UPDATE AD_Process_Para SET ColumnName='IsPostIfClearingEqual', Name='Post if Clearing Equal', Description='This flag controls if Adempiere must post when clearing (transit) and final accounts are the same', Help=NULL, AD_Element_ID=53227 WHERE UPPER(ColumnName)='ISPOSTIFCLEARINGEQUAL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
/

-- Nov 27, 2007 11:00:45 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
UPDATE AD_Process_Para SET ColumnName='IsPostIfClearingEqual', Name='Post if Clearing Equal', Description='This flag controls if Adempiere must post when clearing (transit) and final accounts are the same', Help=NULL WHERE AD_Element_ID=53227 AND IsCentrallyMaintained='Y'
/

-- Nov 27, 2007 11:00:45 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
UPDATE AD_PrintFormatItem pi SET PrintName='Post if Clearing Equal', Name='Post if Clearing Equal' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53227)
/

-- Nov 27, 2007 11:00:46 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
UPDATE AD_PrintFormatItem pi SET PrintName='Post if Clearing Equal', Name='Post if Clearing Equal' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53227)
/

-- Nov 27, 2007 11:01:30 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,53266,53227,0,20,265,'IsPostIfClearingEqual',TO_DATE('2007-11-27 23:01:29','YYYY-MM-DD HH24:MI:SS'),100,'Y','This flag controls if Adempiere must post when clearing (transit) and final accounts are the same','D',1,'Y','N','N','N','N','N','N','N','N','N','Y','Post if Clearing Equal',0,TO_DATE('2007-11-27 23:01:29','YYYY-MM-DD HH24:MI:SS'),100,0)
/

-- Nov 27, 2007 11:01:30 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=53266 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
/

-- Nov 27, 2007 11:01:45 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
ALTER TABLE C_AcctSchema ADD IsPostIfClearingEqual CHAR(1) DEFAULT 'Y' CHECK (IsPostIfClearingEqual IN ('Y','N'))
/

-- Nov 27, 2007 11:07:24 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53266,53281,0,199,TO_DATE('2007-11-27 23:07:22','YYYY-MM-DD HH24:MI:SS'),100,'This flag controls if Adempiere must post when clearing (transit) and final accounts are the same',1,'D','Y','Y','Y','N','N','N','N','Y','Post if Clearing Equal',270,TO_DATE('2007-11-27 23:07:22','YYYY-MM-DD HH24:MI:SS'),100)
/

-- Nov 27, 2007 11:07:24 PM COT
-- FR 1840016 - Avoid usage of clearing accounts
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=53281 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
/

