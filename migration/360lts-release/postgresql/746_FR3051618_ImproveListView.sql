-- Aug 23, 2010 5:52:28 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54272,0,'HideInListView',TO_TIMESTAMP('2010-08-23 17:52:26','YYYY-MM-DD HH24:MI:SS'),100,'When checked this field will be hidden by default in list view','D','Y','Hide in list view','Hide in list view',TO_TIMESTAMP('2010-08-23 17:52:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 23, 2010 5:52:28 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54272 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 23, 2010 5:52:59 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54273,0,'PreferredWidth',TO_TIMESTAMP('2010-08-23 17:52:58','YYYY-MM-DD HH24:MI:SS'),100,'Preferred width in pixels','D','Y','Preferred Width','Preferred Width',TO_TIMESTAMP('2010-08-23 17:52:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 23, 2010 5:52:59 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54273 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 23, 2010 5:57:39 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59703,54272,0,20,107,'HideInListView',TO_TIMESTAMP('2010-08-23 17:57:36','YYYY-MM-DD HH24:MI:SS'),100,'N','When checked this field will be hidden by default in list view','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Hide in list view',0,TO_TIMESTAMP('2010-08-23 17:57:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 23, 2010 5:57:39 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59703 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 23, 2010 5:57:47 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
ALTER TABLE AD_Field ADD COLUMN HideInListView CHAR(1) DEFAULT 'N' CHECK (HideInListView IN ('Y','N'))
;

-- Aug 23, 2010 5:59:02 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59704,54273,0,11,107,'PreferredWidth',TO_TIMESTAMP('2010-08-23 17:59:01','YYYY-MM-DD HH24:MI:SS'),100,'Preferred width in pixels','D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Preferred Width',0,TO_TIMESTAMP('2010-08-23 17:59:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 23, 2010 5:59:02 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59704 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 23, 2010 5:59:12 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
ALTER TABLE AD_Field ADD COLUMN PreferredWidth NUMERIC(10) DEFAULT NULL 
;

-- Aug 23, 2010 6:00:33 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59703,59620,0,107,TO_TIMESTAMP('2010-08-23 18:00:31','YYYY-MM-DD HH24:MI:SS'),100,'When checked this field will be hidden by default in list view',1,'D','Y','Y','Y','N','N','N','N','N','Hide in list view',290,0,TO_TIMESTAMP('2010-08-23 18:00:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 23, 2010 6:00:33 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59620 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 23, 2010 6:01:13 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59704,59621,0,107,TO_TIMESTAMP('2010-08-23 18:01:12','YYYY-MM-DD HH24:MI:SS'),100,'Preferred width in pixels',10,'D','Y','Y','Y','N','N','N','N','Y','Preferred Width',300,0,TO_TIMESTAMP('2010-08-23 18:01:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 23, 2010 6:01:13 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59621 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 23, 2010 6:01:49 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=59620
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=59621
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=133
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=136
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=142
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=137
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=139
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=138
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=8343
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=140
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=141
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=13425
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=54401
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=54402
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=13424
;

-- Aug 23, 2010 6:01:50 PM CEST
-- FR 3051618 - Grid view improvements - Hide columns by default - preferred width
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=53280
;

