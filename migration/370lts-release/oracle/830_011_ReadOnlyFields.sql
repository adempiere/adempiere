-- Jul 9, 2012 12:55:19 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET SeqNo=2,Updated=TO_DATE('2012-07-09 12:55:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57981
;

-- Jul 9, 2012 12:55:43 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=1,Updated=TO_DATE('2012-07-09 12:55:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57971
;

-- Jul 9, 2012 12:56:36 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Table SET AD_Window_ID=53088,Updated=TO_DATE('2012-07-09 12:56:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53223
;

-- Jul 9, 2012 12:57:26 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Table SET AD_Window_ID=53089,Updated=TO_DATE('2012-07-09 12:57:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53232
;

-- Jul 9, 2012 12:57:39 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-07-09 12:57:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58105
;

-- Jul 9, 2012 12:57:59 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsSelectionColumn='Y', IsUpdateable='N',Updated=TO_DATE('2012-07-09 12:57:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58101
;

-- Jul 9, 2012 12:58:17 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-07-09 12:58:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58075
;

-- Jul 9, 2012 12:58:37 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-07-09 12:58:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58089
;

-- Jul 9, 2012 12:59:58 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63579,405,0,20,53223,'IsReadOnly',TO_DATE('2012-07-09 12:59:56','YYYY-MM-DD HH24:MI:SS'),100,'Field is read only','EE07',1,'The Read Only indicates that this field may only be Read.  It may not be updated.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Read Only',0,TO_DATE('2012-07-09 12:59:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 9, 2012 12:59:58 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63579 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 9, 2012 1:00:01 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_Browse_Field ADD IsReadOnly CHAR(1) DEFAULT NULL  CHECK (IsReadOnly IN ('Y','N'))
;

-- Jul 9, 2012 1:00:29 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2012-07-09 13:00:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63579
;

-- Jul 9, 2012 1:00:30 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_Browse_Field MODIFY IsReadOnly CHAR(1) DEFAULT 'Y'
;

-- Jul 9, 2012 1:00:44 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63579,64654,0,53247,TO_DATE('2012-07-09 13:00:43','YYYY-MM-DD HH24:MI:SS'),100,'Field is read only',1,'EE07','The Read Only indicates that this field may only be Read.  It may not be updated.','Y','Y','Y','N','N','N','N','N','Read Only',TO_DATE('2012-07-09 13:00:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 9, 2012 1:00:44 PM CDT
-- ADEMPIERE-10 Smart Browser
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64654 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 9, 2012 1:01:02 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=64654
;

-- Jul 9, 2012 1:01:02 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=57439
;

-- Jul 9, 2012 1:01:02 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=57440
;

-- Jul 9, 2012 1:01:02 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=64393
;

-- Jul 9, 2012 1:01:02 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=64394
;

-- Jul 9, 2012 1:01:02 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=57441
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53203
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=586
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=138
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=139
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=249
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=141
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=300
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=589
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=295
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=216
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53228
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53227
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=140
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=142
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53227
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53012
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=143
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=201
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=176
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53086
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=239
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=517
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=499
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53089
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53090
;

-- Jul 9, 2012 1:01:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=50001
;

-- Jul 9, 2012 1:03:04 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-07-09 13:03:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64654
;

