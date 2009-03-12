-- 12-mar-2009 11:18:22 COT
-- FR [2685367] GL Distribution delete line instead reverse
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PO_Name,PrintName,Updated,UpdatedBy) VALUES (0,53795,0,'IsCreateReversal',TO_DATE('2009-03-12 11:18:21','YYYY-MM-DD HH24:MI:SS'),100,'Indicates that reversal movement will be created, if disabled the original movement will be deleted.','D','Y','Create Reversal',NULL,'Create Reversal',TO_DATE('2009-03-12 11:18:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 12-mar-2009 11:18:22 COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53795 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 12-mar-2009 11:18:58 COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56912,53795,0,20,708,'IsCreateReversal',TO_DATE('2009-03-12 11:18:58','YYYY-MM-DD HH24:MI:SS'),100,'Y','Indicates that reversal movement will be created, if disabled the original movement will be deleted.','D',1,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Create Reversal',0,TO_DATE('2009-03-12 11:18:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 12-mar-2009 11:18:58 COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56912 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 12-mar-2009 11:19:06 COT
ALTER TABLE GL_Distribution ADD IsCreateReversal CHAR(1) DEFAULT 'Y' CHECK (IsCreateReversal IN ('Y','N')) NOT NULL
;

-- 12-mar-2009 11:20:46 COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,56912,56748,0,646,TO_DATE('2009-03-12 11:20:45','YYYY-MM-DD HH24:MI:SS'),100,'Indicates that reversal movement will be created, if disabled the original movement will be deleted.',1,'D','Y','Y','Y','N','N','N','N','N','Create Reversal',390,0,TO_DATE('2009-03-12 11:20:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 12-mar-2009 11:20:46 COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56748 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56748
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=10875
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=10862
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=10866
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=10149
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=10860
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=10145
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=10868
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=10141
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=10869
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=10142
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=10864
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=10133
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=10873
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=10150
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=10865
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=10143
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=10871
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=10154
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=10872
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=10138
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=10867
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=10156
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=10861
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=10148
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=10874
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=10137
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=10863
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=10155
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=10870
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=10134
;

-- 12-mar-2009 11:21:13 COT
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=10140
;

-- 12-mar-2009 11:21:34 COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-12 11:21:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56748
;

