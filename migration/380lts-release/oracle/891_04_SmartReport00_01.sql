-- Oct 16, 2013 3:04:35 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=69735
;

-- Oct 16, 2013 3:04:35 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE FROM AD_Field WHERE AD_Field_ID=69735
;

-- Oct 16, 2013 3:05:00 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=68443
;

-- Oct 16, 2013 3:05:00 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE FROM AD_Column WHERE AD_Column_ID=68443
;

-- Oct 16, 2013 3:05:28 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE  FROM  AD_Element_Trl WHERE AD_Element_ID=1000011
;

-- Oct 16, 2013 3:05:28 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE FROM AD_Element WHERE AD_Element_ID=1000011
;

-- Oct 16, 2013 3:05:53 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=68442
;

-- Oct 16, 2013 3:05:53 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE FROM AD_Column WHERE AD_Column_ID=68442
;

-- Oct 16, 2013 3:06:20 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE  FROM  AD_Element_Trl WHERE AD_Element_ID=1000011
;

-- Oct 16, 2013 3:06:20 PM IST
-- Modifying the errors in SmartReport Migration Scripts
DELETE FROM AD_Element WHERE AD_Element_ID=1000011
;

-- Oct 16, 2013 3:07:42 PM IST
-- Modifying the errors in SmartReport Migration Scripts
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56491,0,'fixedpercentage',TO_DATE('2013-10-16 15:07:42','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','fixedpercentage','fixedpercentage',TO_DATE('2013-10-16 15:07:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 16, 2013 3:07:42 PM IST
-- Modifying the errors in SmartReport Migration Scripts
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56491 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 16, 2013 3:07:43 PM IST
-- Modifying the errors in SmartReport Migration Scripts
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68919,56491,0,22,448,'fixedpercentage',TO_DATE('2013-10-16 15:07:42','YYYY-MM-DD HH24:MI:SS'),0,'D',131089,'Y','N','N','N','N','N','N','N','N','Y','fixedpercentage',TO_DATE('2013-10-16 15:07:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 16, 2013 3:07:43 PM IST
-- Modifying the errors in SmartReport Migration Scripts
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68919 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 16, 2013 3:08:56 PM IST
-- Modifying the errors in SmartReport Migration Scripts
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68920,56491,0,22,544,'fixedpercentage',TO_DATE('2013-10-16 15:08:55','YYYY-MM-DD HH24:MI:SS'),0,'D',131089,'Y','N','N','N','N','N','N','N','N','Y','fixedpercentage',TO_DATE('2013-10-16 15:08:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 16, 2013 3:08:56 PM IST
-- Modifying the errors in SmartReport Migration Scripts
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68920 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 16, 2013 3:09:30 PM IST
-- Modifying the errors in SmartReport Migration Scripts
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68919,69917,0,376,TO_DATE('2013-10-16 15:09:29','YYYY-MM-DD HH24:MI:SS'),0,131089,'D','Y','Y','Y','N','N','N','N','N','fixedpercentage',TO_DATE('2013-10-16 15:09:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 16, 2013 3:09:30 PM IST
-- Modifying the errors in SmartReport Migration Scripts
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69917 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 16, 2013 3:11:18 PM IST
-- Modifying the errors in SmartReport Migration Scripts
UPDATE AD_Field SET DisplayLogic='@LineType@=C', Name='Fixed Percentage',Updated=TO_DATE('2013-10-16 15:11:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69917
;

-- Oct 16, 2013 3:11:18 PM IST
-- Modifying the errors in SmartReport Migration Scripts
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69917
;

-- Oct 16, 2013 3:11:29 PM IST
-- Modifying the errors in SmartReport Migration Scripts
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_DATE('2013-10-16 15:11:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69917
;

