-- Mar 15, 2013 12:37:46 PM CST
-- SeqNo Cost Detail
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64836,566,0,11,808,'SeqNo',TO_DATE('2013-03-15 12:37:45','YYYY-MM-DD HH24:MI:SS'),100,'Method of ordering records; lowest number comes first','D',22,'The Sequence indicates the order of records','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sequence',0,TO_DATE('2013-03-15 12:37:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 15, 2013 12:37:46 PM CST
-- SeqNo Cost Detail
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64836 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 15, 2013 12:37:50 PM CST
-- SeqNo Cost Detail
ALTER TABLE M_CostDetail ADD SeqNo NUMBER(10) DEFAULT NULL 
;

-- Mar 15, 2013 12:38:33 PM CST
-- SeqNo Cost Detail
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64836,66094,0,748,TO_DATE('2013-03-15 12:38:33','YYYY-MM-DD HH24:MI:SS'),100,'Method of ordering records; lowest number comes first',22,'D','The Sequence indicates the order of records','Y','Y','Y','N','N','N','Y','N','Sequence',TO_DATE('2013-03-15 12:38:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 15, 2013 12:38:33 PM CST
-- SeqNo Cost Detail
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66094 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 15, 2013 5:01:42 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=70,Updated=TO_DATE('2013-03-15 17:01:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66094
;

-- Mar 15, 2013 5:01:43 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=80,Updated=TO_DATE('2013-03-15 17:01:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12154
;

-- Mar 15, 2013 5:01:43 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_DATE('2013-03-15 17:01:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12148
;

-- Mar 15, 2013 5:01:43 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_DATE('2013-03-15 17:01:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12155
;

-- Mar 15, 2013 5:01:43 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_DATE('2013-03-15 17:01:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58847
;

-- Mar 15, 2013 5:01:43 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_DATE('2013-03-15 17:01:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12317
;

-- Mar 15, 2013 5:01:43 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_DATE('2013-03-15 17:01:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12147
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59666
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58846
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59523
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12341
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12340
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58849
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58848
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59671
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59622
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59669
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59533
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59668
;

-- Mar 15, 2013 5:01:44 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_DATE('2013-03-15 17:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59534
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59667
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12177
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12151
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12156
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=310,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12342
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=320,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12343
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=330,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12345
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=340,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12344
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59521
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59452
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58865
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12346
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12150
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=400,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58864
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=410,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12153
;

-- Mar 15, 2013 5:01:45 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=420,Updated=TO_DATE('2013-03-15 17:01:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59632
;

-- Mar 15, 2013 5:02:02 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2013-03-15 17:02:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66094
;

-- Mar 15, 2013 5:02:12 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_DATE('2013-03-15 17:02:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59452
;

-- Mar 15, 2013 5:02:12 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_DATE('2013-03-15 17:02:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59521
;

-- Mar 15, 2013 5:03:57 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2013-03-15 17:03:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58865
;

-- Mar 15, 2013 5:03:59 PM CST
-- SeqNo Cost Detail
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2013-03-15 17:03:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12346
;

