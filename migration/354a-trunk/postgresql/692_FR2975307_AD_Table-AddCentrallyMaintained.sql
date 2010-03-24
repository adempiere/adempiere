-- Mar 24, 2010 3:14:09 PM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
-- ADD IsCentrallyMaintained to AD_Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59135,362,0,20,100,'IsCentrallyMaintained',TO_TIMESTAMP('2010-03-24 15:14:07','YYYY-MM-DD HH24:MI:SS'),100,'Y','Information maintained in System Element table','D',1,'The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Centrally maintained',0,TO_TIMESTAMP('2010-03-24 15:14:07','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 24, 2010 3:14:09 PM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
-- ADD IsCentrallyMaintained to AD_Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59135 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 24, 2010 3:14:19 PM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
-- ADD IsCentrallyMaintained to AD_Table
ALTER TABLE AD_Table ADD COLUMN IsCentrallyMaintained CHAR(1) DEFAULT 'Y' CHECK (IsCentrallyMaintained IN ('Y','N'))
;

-- Mar 24, 2010 3:15:47 PM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
-- ADD IsCentrallyMaintained to AD_Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59135,58844,0,100,TO_TIMESTAMP('2010-03-24 15:15:46','YYYY-MM-DD HH24:MI:SS'),100,'Information maintained in System Element table',0,'D','The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.','Y','Y','Y','N','N','N','N','Y','Centrally maintained',180,0,TO_TIMESTAMP('2010-03-24 15:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 3:15:47 PM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
-- ADD IsCentrallyMaintained to AD_Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58844 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 24, 2010 3:16:46 PM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
-- ADD IsCentrallyMaintained to AD_Table
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=50157
;

-- Mar 24, 2010 3:16:46 PM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
-- ADD IsCentrallyMaintained to AD_Table
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=58844
;

