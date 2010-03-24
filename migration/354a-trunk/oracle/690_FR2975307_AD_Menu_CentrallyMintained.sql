-- Mar 24, 2010 10:59:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59134,362,0,20,116,'IsCentrallyMaintained',TO_DATE('2010-03-24 10:59:24','YYYY-MM-DD HH24:MI:SS'),100,'Information maintained in System Element table','D',1,'The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Centrally maintained',0,TO_DATE('2010-03-24 10:59:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 24, 2010 10:59:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59134 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 24, 2010 10:59:45 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
ALTER TABLE AD_Menu ADD IsCentrallyMaintained CHAR(1) DEFAULT 'Y'  CHECK (IsCentrallyMaintained IN ('Y','N'))
;

-- Mar 24, 2010 11:00:05 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2010-03-24 11:00:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59134
;

-- Mar 24, 2010 11:00:08 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
ALTER TABLE AD_Menu MODIFY IsCentrallyMaintained CHAR(1) DEFAULT 'Y'
;

-- Mar 24, 2010 11:01:40 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59134,58843,0,110,TO_DATE('2010-03-24 11:01:38','YYYY-MM-DD HH24:MI:SS'),100,'Information maintained in System Element table',0,'D','The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.','Y','Y','Y','N','N','N','N','N','Centrally maintained',180,0,TO_DATE('2010-03-24 11:01:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 11:01:40 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58843 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 24, 2010 11:03:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=202
;

-- Mar 24, 2010 11:03:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=284
;

-- Mar 24, 2010 11:03:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=1993
;

-- Mar 24, 2010 11:03:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=201
;

-- Mar 24, 2010 11:03:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=203
;

-- Mar 24, 2010 11:03:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=283
;

-- Mar 24, 2010 11:03:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=5827
;

-- Mar 24, 2010 11:03:30 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=58843
;

-- Mar 24, 2010 11:03:55 AM CET
-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-03-24 11:03:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58843
;

UPDATE AD_Menu
SET IsCentrallyMaintained = 'Y'
WHERE IsCentrallyMaintained is NULL;

