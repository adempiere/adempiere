-- Nov 9, 2010 12:02:24 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54360,0,'IsOpenByDefault',TO_DATE('2010-11-09 12:02:21','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Open By Default','Open By Default',TO_DATE('2010-11-09 12:02:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 9, 2010 12:02:24 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54360 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Nov 9, 2010 12:03:08 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60079,54360,0,20,50010,'IsOpenByDefault',TO_DATE('2010-11-09 12:03:00','YYYY-MM-DD HH24:MI:SS'),100,'Y','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Open By Default',0,TO_DATE('2010-11-09 12:03:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 9, 2010 12:03:08 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60079 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 9, 2010 12:03:13 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
ALTER TABLE PA_DashboardContent ADD IsOpenByDefault CHAR(1) DEFAULT 'Y' CHECK (IsOpenByDefault IN ('Y','N'))
;

-- Nov 9, 2010 12:04:04 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60079,60822,0,50010,TO_DATE('2010-11-09 12:03:57','YYYY-MM-DD HH24:MI:SS'),100,1,'D','N','Y','Y','Y','N','N','N','N','N','Open By Default',0,130,0,TO_DATE('2010-11-09 12:03:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 9, 2010 12:04:04 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60822 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Nov 9, 2010 12:05:03 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56504
;

-- Nov 9, 2010 12:05:03 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=60822
;

-- Nov 9, 2010 12:05:11 PM CET
-- FR [3106704] - ZK Closed by Default Dashlet
-- https://sourceforge.net/tracker/?func=detail&aid=3106704&group_id=176962&atid=883808
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-11-09 12:05:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=60822
;

