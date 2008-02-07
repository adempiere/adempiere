-- 05.02.2008 16:30:44 CET
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,1000001,'DecimalPattern',TO_DATE('2008-02-05 16:30:43','YYYY-MM-DD HH24:MI:SS'),100,'Java Decimal Pattern','D','Option Decimal pattern in Java notation. Example: pattern 0000 will format the sequence number 23 to 0023','Y','Decimal Pattern','Decimal Pattern',TO_DATE('2008-02-05 16:30:43','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 05.02.2008 16:30:44 CET
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=1000001 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 05.02.2008 16:37:47 CET
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,AD_Client_ID,Version,AD_Column_ID) VALUES (0,1000001,10,115,'DecimalPattern',TO_DATE('2008-02-05 16:37:47','YYYY-MM-DD HH24:MI:SS'),100,'Java Decimal Pattern','D',40,'Option Decimal pattern in Java notation. Example: pattern 0000 will format the sequence number 23 to 0023','Y','N','N','N','N','N','N','N','N','N','Y','Decimal Pattern',0,TO_DATE('2008-02-05 16:37:47','YYYY-MM-DD HH24:MI:SS'),100,0,0,1000000)
;

-- 05.02.2008 16:37:47 CET
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=1000000 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 05.02.2008 16:38:09 CET
-- [ 1883270 ] Enhance Document No Formatting
ALTER TABLE AD_Sequence ADD DecimalPattern NVARCHAR2(40)
;

-- 05.02.2008 16:42:30 CET
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,AD_Client_ID,UpdatedBy) VALUES (1000000,0,1000000,146,TO_DATE('2008-02-05 16:42:30','YYYY-MM-DD HH24:MI:SS'),100,'Java Decimal Pattern',40,'D','Option Decimal pattern in Java notation. Example: pattern 0000 will format the sequence number 23 to 0023','Y','Y','Y','N','N','N','N','Y','Decimal Pattern',170,0,TO_DATE('2008-02-05 16:42:30','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- 05.02.2008 16:42:30 CET
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=1000000 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 05.02.2008 16:43:43 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=1000000
;

-- 05.02.2008 16:43:43 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=333
;

-- 05.02.2008 16:43:43 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=334
;

-- 05.02.2008 16:49:59 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='decimal Pattern',Updated=TO_DATE('2008-02-05 16:49:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1000000
;

-- 05.02.2008 16:49:59 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=1000000
;

-- 05.02.2008 16:50:10 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Decimal Pattern',Updated=TO_DATE('2008-02-05 16:50:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1000000
;

-- 05.02.2008 16:50:11 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=1000000
;

-- 05.02.2008 16:51:44 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Pattern',Updated=TO_DATE('2008-02-05 16:51:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1000000
;

-- 05.02.2008 16:51:44 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=1000000
;

-- 05.02.2008 16:52:26 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET DisplayLength=14,Updated=TO_DATE('2008-02-05 16:52:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1000000
;

-- 07.02.2008 13:25:39 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-02-07 13:25:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1000000
;

-- 07.02.2008 13:25:56 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=1000000
;

-- 07.02.2008 13:25:56 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=335
;

-- 07.02.2008 13:25:56 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=1555
;

-- 07.02.2008 13:25:57 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=54357
;

-- 07.02.2008 13:25:57 CET
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=332
;

