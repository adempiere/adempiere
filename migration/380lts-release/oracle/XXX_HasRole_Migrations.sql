-- Oct 23, 2013 2:53:07 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56541,0,17,'HasRole',TO_DATE('2013-10-23 14:53:06','YYYY-MM-DD HH24:MI:SS'),0,'Has Role Y/N','D',1,'Y','HasRole','Has Role Y/N',TO_DATE('2013-10-23 14:53:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:53:07 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56541 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 23, 2013 2:54:04 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsRange,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69212,56541,0,17,114,'HasRole','(CASE WHEN (SELECT count(*) FROM AD_User_Roles ur WHERE ur.AD_User_ID=AD_User.AD_User_ID AND ur.IsActive=''Y'') > 0 THEN ''Y'' ELSE ''N'' END)',TO_DATE('2013-10-23 14:54:03','YYYY-MM-DD HH24:MI:SS'),0,'Has Role Y/N','U',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','N','HasRole',0,TO_DATE('2013-10-23 14:54:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 23, 2013 2:54:04 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69212 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 23, 2013 2:54:26 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62125,70064,0,118,TO_DATE('2013-10-23 14:54:25','YYYY-MM-DD HH24:MI:SS'),0,'Location or Address',10,'D','The Location / Address field defines the location of an entity.','Y','Y','Y','N','N','N','N','N','Address',TO_DATE('2013-10-23 14:54:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:26 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70064 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:26 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62477,70065,0,118,TO_DATE('2013-10-23 14:54:26','YYYY-MM-DD HH24:MI:SS'),0,'Address of the Business Partner',10,'D','Y','Y','Y','N','N','N','N','N','BP Address',TO_DATE('2013-10-23 14:54:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:26 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70065 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:27 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62476,70066,0,118,TO_DATE('2013-10-23 14:54:26','YYYY-MM-DD HH24:MI:SS'),0,60,'D','Y','Y','Y','N','N','N','N','N','BP Name',TO_DATE('2013-10-23 14:54:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:27 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70066 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:27 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62130,70067,0,118,TO_DATE('2013-10-23 14:54:27','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign',10,'D','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','N','N','N','N','N','Campaign',TO_DATE('2013-10-23 14:54:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:27 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70067 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:28 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69212,70068,0,118,TO_DATE('2013-10-23 14:54:27','YYYY-MM-DD HH24:MI:SS'),0,'Has Role Y/N',1,'U','Y','Y','Y','N','N','N','N','N','HasRole',TO_DATE('2013-10-23 14:54:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:28 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70068 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:28 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56294,70069,0,118,TO_DATE('2013-10-23 14:54:28','YYYY-MM-DD HH24:MI:SS'),0,'Defined if any User Contact will be used for Calculate Payroll',1,'EE02','Y','Y','Y','N','N','N','N','N','Is In Payroll',TO_DATE('2013-10-23 14:54:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:28 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70069 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:28 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62126,70070,0,118,TO_DATE('2013-10-23 14:54:28','YYYY-MM-DD HH24:MI:SS'),0,'The source of this lead/opportunity',10,'D','Y','Y','Y','N','N','N','N','N','Lead Source',TO_DATE('2013-10-23 14:54:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:28 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70070 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:29 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62128,70071,0,118,TO_DATE('2013-10-23 14:54:28','YYYY-MM-DD HH24:MI:SS'),0,'Additional information on the source of this lead/opportunity',255,'D','Y','Y','Y','N','N','N','N','N','Lead Source Description',TO_DATE('2013-10-23 14:54:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:29 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70071 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:29 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62127,70072,0,118,TO_DATE('2013-10-23 14:54:29','YYYY-MM-DD HH24:MI:SS'),0,'The status of this lead/opportunity in the sales cycle',10,'D','Y','Y','Y','N','N','N','N','N','Lead Status',TO_DATE('2013-10-23 14:54:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:29 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70072 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:30 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62129,70073,0,118,TO_DATE('2013-10-23 14:54:29','YYYY-MM-DD HH24:MI:SS'),0,'Additional information on the status of this lead/opportunity',255,'D','Y','Y','Y','N','N','N','N','N','Lead Status Description',TO_DATE('2013-10-23 14:54:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:30 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70073 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:30 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62124,70074,0,118,TO_DATE('2013-10-23 14:54:30','YYYY-MM-DD HH24:MI:SS'),0,'This contact is a sales lead',1,'D','Sales leads can be converted into full contacts with Business Partners.','Y','Y','Y','N','N','N','N','N','Sales Lead',TO_DATE('2013-10-23 14:54:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:30 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70074 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:31 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,62131,70075,0,118,TO_DATE('2013-10-23 14:54:30','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent',10,'D','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','Y','N','N','N','N','N','Sales Representative',TO_DATE('2013-10-23 14:54:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:31 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70075 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:54:31 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61756,70076,0,118,TO_DATE('2013-10-23 14:54:31','YYYY-MM-DD HH24:MI:SS'),0,'Random data added to improve password hash effectiveness',16,'D','Y','Y','Y','N','N','N','N','N','Salt',TO_DATE('2013-10-23 14:54:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 23, 2013 2:54:31 PM IST
-- Added a new column "Has Role" in User Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70076 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70064
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70065
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70066
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70067
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70069
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70070
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70071
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70072
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70073
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70074
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70075
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70076
;

-- Oct 23, 2013 2:56:28 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=320,Updated=TO_DATE('2013-10-23 14:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70068
;

-- Oct 23, 2013 3:12:36 PM IST
-- Added a new column "Has Role" in User Window
UPDATE AD_Column SET AD_Reference_ID=10,Updated=TO_DATE('2013-10-23 15:12:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69212
;

-- Oct 25, 2013 4:13:55 PM IST
-- A Small fix in HasRole. Changing the name of the field from 'HasRole' to 'Has Role'
UPDATE AD_Field SET Name='Has Role',Updated=TO_DATE('2013-10-25 16:13:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70068
;

-- Oct 25, 2013 4:13:55 PM IST
-- A Small fix in HasRole. Changing the name of the field from 'HasRole' to 'Has Role'
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70068
;

-- Oct 25, 2013 4:14:40 PM IST
-- A Small fix in HasRole. Changing the name of the field from 'HasRole' to 'Has Role'
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2013-10-25 16:14:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69212
;

-- Oct 25, 2013 4:14:54 PM IST
-- A Small fix in HasRole. Changing the name of the field from 'HasRole' to 'Has Role'
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2013-10-25 16:14:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70068
;