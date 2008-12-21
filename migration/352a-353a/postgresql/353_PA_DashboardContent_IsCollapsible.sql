-- Dec 10, 2008 3:05:19 PM SGT
-- Allow user to control the dashboard panel to be static or collapsible
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53715,0,'IsCollapsible',TO_TIMESTAMP('2008-12-10 15:05:17','YYYY-MM-DD HH24:MI:SS'),100,'Flag to indicate the state of the dashboard panel','D','Flag to indicate the state of the dashboard panel (i.e. collapsible or static)','Y','Collapsible','Collapsible',TO_TIMESTAMP('2008-12-10 15:05:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 10, 2008 3:05:19 PM SGT
-- Allow user to control the dashboard panel to be static or collapsible
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53715 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 10, 2008 3:05:44 PM SGT
-- Allow user to control the dashboard panel to be static or collapsible
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56522,53715,0,20,50010,'IsCollapsible',TO_TIMESTAMP('2008-12-10 15:05:40','YYYY-MM-DD HH24:MI:SS'),100,'Y','Flag to indicate the state of the dashboard panel','D',1,'Flag to indicate the state of the dashboard panel (i.e. collapsible or static)','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Collapsible',0,TO_TIMESTAMP('2008-12-10 15:05:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 10, 2008 3:05:44 PM SGT
-- Allow user to control the dashboard panel to be static or collapsible
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56522 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 10, 2008 3:05:47 PM SGT
-- Allow user to control the dashboard panel to be static or collapsible
ALTER TABLE PA_DashboardContent ADD COLUMN IsCollapsible CHAR(1) DEFAULT 'Y' CHECK (IsCollapsible IN ('Y','N')) NOT NULL
;

-- Dec 10, 2008 3:06:04 PM SGT
-- Allow user to control the dashboard panel to be static or collapsible
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56522,56504,0,50010,TO_TIMESTAMP('2008-12-10 15:06:00','YYYY-MM-DD HH24:MI:SS'),100,'Flag to indicate the state of the dashboard panel',1,'D','Flag to indicate the state of the dashboard panel (i.e. collapsible or static)','Y','Y','Y','N','N','N','N','N','Collapsible',TO_TIMESTAMP('2008-12-10 15:06:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 10, 2008 3:06:04 PM SGT
-- Allow user to control the dashboard panel to be static or collapsible
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56504 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

