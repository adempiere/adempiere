SET DEFINE OFF
SET SQLBLANKLINES ON

-- Sep 4, 2009 12:16:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,
IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) 
VALUES (0,58110,145,0,17,5,53224,'AccessLevel',TO_DATE('2009-09-04 00:16:30','YYYY-MM-DD HH24:MI:SS'),0,'Access Level required','EE07',1,'Indicates the access level required for this record or process.',
'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Data Access Level',0,TO_DATE('2009-09-04 00:16:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 4, 2009 12:16:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) 
SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t 
WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58110 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 12:16:40 AM ECT
-- Warehouse Management
ALTER TABLE AD_Browse ADD AccessLevel CHAR(1) DEFAULT NULL 
;

-- Sep 4, 2009 12:17:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,
IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58110,57530,0,53248,TO_DATE('2009-09-04 00:17:26','YYYY-MM-DD HH24:MI:SS'),0,'Access Level required',1,'EE07',
'Indicates the access level required for this record or process.','Y','Y','Y','N','N','N','N','N','Data Access Level',TO_DATE('2009-09-04 00:17:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2009 12:17:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',
t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57530 AND 
EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

