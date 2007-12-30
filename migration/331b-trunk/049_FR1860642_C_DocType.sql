-- Dec 29, 2007 5:52:00 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53320,'IsOverwriteSeqOnComplete',TO_DATE('2007-12-29 17:51:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Overwrite Sequence on Complete','Overwrite Sequence on Complete',TO_DATE('2007-12-29 17:51:59','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- Dec 29, 2007 5:52:00 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53320 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

UPDATE AD_Element_Trl SET istranslated='Y', name = 'Sobreescribir Secuencia al Completar', printname = 'Sobreescribir Secuencia al Completar'
WHERE AD_Element_ID=53320 and ad_language like 'es_%'
;

-- Dec 29, 2007 5:53:26 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53321,'DefiniteSequence_ID',TO_DATE('2007-12-29 17:53:26','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Definite Sequence','Definite Sequence',TO_DATE('2007-12-29 17:53:26','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- Dec 29, 2007 5:53:26 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53321 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

UPDATE AD_Element_Trl SET istranslated='Y', name = 'Secuencia Definitiva', printname = 'Secuencia Definitiva'
WHERE AD_Element_ID=53321 and ad_language like 'es_%'
;

-- Dec 29, 2007 5:54:09 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53322,'IsOverwriteDateOnComplete',TO_DATE('2007-12-29 17:54:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Overwrite Date on Complete','Overwrite Date on Complete',TO_DATE('2007-12-29 17:54:09','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- Dec 29, 2007 5:54:09 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53322 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

UPDATE AD_Element_Trl SET istranslated='Y', name = 'Sobreescribir Fecha al Completar', printname = 'Sobreescribir Fecha al Completar'
WHERE AD_Element_ID=53322 and ad_language like 'es_%'
;

-- Dec 29, 2007 5:55:55 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,53320,20,217,'IsOverwriteSeqOnComplete',TO_DATE('2007-12-29 17:55:55','YYYY-MM-DD HH24:MI:SS'),100,'N','D',1,'Y','N','N','N','N','N','N','N','N','N','Y','Overwrite Sequence on Complete',0,TO_DATE('2007-12-29 17:55:55','YYYY-MM-DD HH24:MI:SS'),100,1.00,0,54087)
;

-- Dec 29, 2007 5:55:56 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54087 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 29, 2007 5:56:02 PM COT
-- 1860642 - Enhance document numbering
ALTER TABLE C_DocType ADD IsOverwriteSeqOnComplete CHAR(1) DEFAULT 'N' CHECK (IsOverwriteSeqOnComplete IN ('Y','N'))
;

-- Dec 29, 2007 5:57:54 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Column (AD_Org_ID,AD_Reference_Value_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,128,53321,18,217,'DefiniteSequence_ID',TO_DATE('2007-12-29 17:57:54','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Definite Sequence',TO_DATE('2007-12-29 17:57:54','YYYY-MM-DD HH24:MI:SS'),100,1,0,54088)
;

-- Dec 29, 2007 5:57:54 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54088 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 29, 2007 5:58:04 PM COT
-- 1860642 - Enhance document numbering
ALTER TABLE C_DocType ADD DefiniteSequence_ID NUMBER(10)
;

-- Dec 29, 2007 5:58:27 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID) VALUES (0,53322,20,217,'IsOverwriteDateOnComplete',TO_DATE('2007-12-29 17:58:26','YYYY-MM-DD HH24:MI:SS'),100,'N','D',1,'Y','N','N','N','N','N','N','N','N','N','Y','Overwrite Date on Complete',0,TO_DATE('2007-12-29 17:58:26','YYYY-MM-DD HH24:MI:SS'),100,1.00,0,54089)
;

-- Dec 29, 2007 5:58:27 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54089 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 29, 2007 5:58:37 PM COT
-- 1860642 - Enhance document numbering
ALTER TABLE C_DocType ADD IsOverwriteDateOnComplete CHAR(1) DEFAULT 'N' CHECK (IsOverwriteDateOnComplete IN ('Y','N'))
;

-- Dec 29, 2007 6:00:14 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (54088,0,167,TO_DATE('2007-12-29 18:00:13','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','N','Definite Sequence',TO_DATE('2007-12-29 18:00:13','YYYY-MM-DD HH24:MI:SS'),0,100,54230)
;

-- Dec 29, 2007 6:00:14 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54230 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 29, 2007 6:00:15 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (54089,0,167,TO_DATE('2007-12-29 18:00:15','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Overwrite Date on Complete',TO_DATE('2007-12-29 18:00:15','YYYY-MM-DD HH24:MI:SS'),0,100,54232)
;

-- Dec 29, 2007 6:00:15 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54232 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 29, 2007 6:00:16 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,AD_Field_ID) VALUES (54087,0,167,TO_DATE('2007-12-29 18:00:15','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Overwrite Sequence on Complete',TO_DATE('2007-12-29 18:00:15','YYYY-MM-DD HH24:MI:SS'),0,100,54233)
;

-- Dec 29, 2007 6:00:16 PM COT
-- 1860642 - Enhance document numbering
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54233 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 29, 2007 6:00:52 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=290,Updated=TO_DATE('2007-12-29 18:00:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54233
;

-- Dec 29, 2007 6:00:57 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET IsSameLine='Y', SeqNo=300,Updated=TO_DATE('2007-12-29 18:00:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54230
;

-- Dec 29, 2007 6:01:04 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=310,Updated=TO_DATE('2007-12-29 18:01:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54232
;

-- Dec 29, 2007 6:01:31 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET DisplayLogic='@IsOverwriteSeqOnComplete@=''Y''',Updated=TO_DATE('2007-12-29 18:01:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54230
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=54233
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=54230
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=54232
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=10345
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=10346
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=10481
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=10480
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=10371
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=10528
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=10340
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=6567
;

-- Dec 29, 2007 8:21:16 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=3125
;

-- Dec 29, 2007 8:21:57 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET DisplayLogic='@IsDocNoControlled@=''Y''',Updated=TO_DATE('2007-12-29 20:21:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54233
;

-- Dec 29, 2007 8:22:06 PM COT
-- 1860642 - Enhance document numbering
UPDATE AD_Field SET DisplayLogic='@IsDocNoControlled@=''Y'' & @IsOverwriteSeqOnComplete@=''Y''',Updated=TO_DATE('2007-12-29 20:22:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54230
;

