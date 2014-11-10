SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

-- Jul 5, 2010 2:09:03 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-05 14:09:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59451
;

-- Jul 5, 2010 2:09:04 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-05 14:09:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12150
;

-- Jul 5, 2010 2:09:05 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-05 14:09:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12328
;

-- Jul 5, 2010 2:09:07 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-05 14:09:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59521
;

-- Jul 5, 2010 2:09:08 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-05 14:09:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12153
;

-- Jul 5, 2010 2:09:09 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-05 14:09:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58864
;

-- Jul 5, 2010 2:09:10 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-05 14:09:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58865
;

-- Jul 5, 2010 2:09:16 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-05 14:09:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58860
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=59451
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=58860
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=12163
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=59452
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=12153
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=58846
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Jul 5, 2010 2:09:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=58864
;

-- Jul 5, 2010 2:10:45 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-07-05 14:10:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58860
;

-- Jul 5, 2010 2:10:50 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-07-05 14:10:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12163
;

-- Jul 5, 2010 2:15:31 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59612,53296,0,37,808,'CurrentCostPriceLL',TO_DATE('2010-07-05 14:15:30','YYYY-MM-DD HH24:MI:SS'),100,'Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.','D',22,'Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Current Cost Price Lower Level',0,TO_DATE('2010-07-05 14:15:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 5, 2010 2:15:31 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59612 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 5, 2010 2:15:48 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD CurrentCostPriceLL NUMBER DEFAULT NULL 
;

-- Jul 5, 2010 2:18:12 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59612,59523,0,748,TO_DATE('2010-07-05 14:18:11','YYYY-MM-DD HH24:MI:SS'),100,'Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.',22,'D','Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
','Y','Y','Y','N','N','N','N','N','Current Cost Price Lower Level',TO_DATE('2010-07-05 14:18:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 5, 2010 2:18:12 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59523 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 5, 2010 2:18:29 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=59523
;

-- Jul 5, 2010 2:18:29 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 5, 2010 2:18:29 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Jul 5, 2010 2:18:29 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Jul 5, 2010 2:18:29 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=58864
;

-- Jul 5, 2010 2:18:38 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsReadOnly='Y', IsSameLine='Y',Updated=TO_DATE('2010-07-05 14:18:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59523
;

-- Jul 5, 2010 2:19:59 PM CDT
-- Cost Engine
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2010-07-05 14:19:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59609
;

-- Jul 5, 2010 2:20:03 PM CDT
-- Cost Engine
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2010-07-05 14:20:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59583
;

-- Jul 5, 2010 2:20:06 PM CDT
-- Cost Engine
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2010-07-05 14:20:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59153
;

-- Jul 5, 2010 2:20:14 PM CDT
-- Cost Engine
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2010-07-05 14:20:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59149
;

-- Jul 5, 2010 2:20:19 PM CDT
-- Cost Engine
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2010-07-05 14:20:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59154
;

-- Jul 5, 2010 2:20:54 PM CDT
-- Cost Engine
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2010-07-05 14:20:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59166
;

-- Jul 5, 2010 2:22:00 PM CDT
-- Cost Engine
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2010-07-05 14:22:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59451
;

-- Jul 5, 2010 2:22:05 PM CDT
-- Cost Engine
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2010-07-05 14:22:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59521
;

-- Jul 5, 2010 2:22:08 PM CDT
-- Cost Engine
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2010-07-05 14:22:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58865
;

-- Jul 5, 2010 2:22:11 PM CDT
-- Cost Engine
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2010-07-05 14:22:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58849
;

-- Jul 5, 2010 2:22:15 PM CDT
-- Cost Engine
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2010-07-05 14:22:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58864
;

-- Jul 5, 2010 2:22:22 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_DATE('2010-07-05 14:22:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59523
;

-- Jul 5, 2010 2:22:23 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsCentrallyMaintained='N',Updated=TO_DATE('2010-07-05 14:22:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12153
;

-- Jul 5, 2010 2:22:26 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_DATE('2010-07-05 14:22:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58864
;

-- Jul 5, 2010 2:22:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:22:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12177
;

-- Jul 5, 2010 2:22:41 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:22:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12151
;

-- Jul 5, 2010 2:22:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:22:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59521
;

-- Jul 5, 2010 2:22:46 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:22:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12156
;

-- Jul 5, 2010 2:22:48 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:22:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12342
;

-- Jul 5, 2010 2:22:51 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:22:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12343
;

-- Jul 5, 2010 2:22:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:22:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12345
;

-- Jul 5, 2010 2:22:57 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:22:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12344
;

-- Jul 5, 2010 2:23:00 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:23:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59452
;

-- Jul 5, 2010 2:23:02 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:23:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12346
;

-- Jul 5, 2010 2:23:05 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:23:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58865
;

-- Jul 5, 2010 2:23:10 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2010-07-05 14:23:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12153
;

-- Jul 5, 2010 2:23:13 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=102,Updated=TO_DATE('2010-07-05 14:23:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12155
;

-- Jul 5, 2010 2:23:16 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=102,Updated=TO_DATE('2010-07-05 14:23:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12147
;

-- Jul 5, 2010 2:23:19 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=102,Updated=TO_DATE('2010-07-05 14:23:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12341
;

-- Jul 5, 2010 2:23:25 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=102,Updated=TO_DATE('2010-07-05 14:23:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12340
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=12328
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=12177
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=12151
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=59521
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12156
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=12342
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=12343
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=12345
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=12344
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=59452
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12150
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=58846
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=59523
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=58864
;

-- Jul 5, 2010 2:23:54 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=12153
;

-- Jul 5, 2010 2:24:06 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_DATE('2010-07-05 14:24:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12150
;

-- Jul 5, 2010 2:24:12 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_DATE('2010-07-05 14:24:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12153
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=58846
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=59523
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=12317
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=12147
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=12155
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=12341
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12340
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=12177
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=12151
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=59521
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=12156
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=12342
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=12343
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12345
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=12344
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=59452
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Jul 5, 2010 2:26:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=12150
;

-- Jul 5, 2010 2:27:00 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=103,Updated=TO_DATE('2010-07-05 14:27:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58846
;

-- Jul 5, 2010 2:27:03 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=50001,Updated=TO_DATE('2010-07-05 14:27:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59523
;

-- Jul 5, 2010 2:27:06 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=50001,Updated=TO_DATE('2010-07-05 14:27:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12317
;

-- Jul 5, 2010 2:27:11 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=103,Updated=TO_DATE('2010-07-05 14:27:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12147
;

-- Jul 5, 2010 2:27:15 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=102,Updated=TO_DATE('2010-07-05 14:27:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58847
;

-- Jul 5, 2010 2:27:24 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=102,Updated=TO_DATE('2010-07-05 14:27:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58849
;

-- Jul 5, 2010 2:27:30 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=103,Updated=TO_DATE('2010-07-05 14:27:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58848
;

-- Jul 5, 2010 2:27:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Jul 5, 2010 2:27:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 5, 2010 2:27:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=12155
;

-- Jul 5, 2010 2:27:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12341
;

-- Jul 5, 2010 2:27:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12340
;

-- Jul 5, 2010 2:27:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Jul 5, 2010 2:28:21 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=103,Updated=TO_DATE('2010-07-05 14:28:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12317
;

-- Jul 5, 2010 2:28:24 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=103,Updated=TO_DATE('2010-07-05 14:28:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59523
;

-- Jul 5, 2010 2:28:53 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-07-05 14:28:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58848
;

-- Jul 5, 2010 2:29:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=12340
;

-- Jul 5, 2010 2:29:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Jul 5, 2010 2:29:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 5, 2010 2:29:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12155
;

-- Jul 5, 2010 2:29:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12341
;

-- Jul 5, 2010 2:30:27 PM CDT
-- Cost Engine
UPDATE AD_Field SET AD_FieldGroup_ID=103,Updated=TO_DATE('2010-07-05 14:30:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12340
;

-- Jul 5, 2010 2:31:07 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-07-05 14:31:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12147
;

-- Jul 5, 2010 2:31:38 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-07-05 14:31:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12340
;

-- Jul 5, 2010 2:32:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=12156
;

-- Jul 5, 2010 2:32:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=12342
;

-- Jul 5, 2010 2:32:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=12343
;

-- Jul 5, 2010 2:32:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=12345
;

-- Jul 5, 2010 2:32:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12344
;

-- Jul 5, 2010 2:32:39 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=59521
;

-- Jul 5, 2010 2:32:49 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-07-05 14:32:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59452
;

-- Jul 5, 2010 2:33:42 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Jul 5, 2010 2:33:42 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Jul 5, 2010 2:34:50 PM CDT
-- Cost Engine
UPDATE AD_Column SET IsAlwaysUpdateable='N',Updated=TO_DATE('2010-07-05 14:34:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14187
;

-- Jul 5, 2010 2:35:01 PM CDT
-- Cost Engine
UPDATE AD_Column SET IsAlwaysUpdateable='N',Updated=TO_DATE('2010-07-05 14:35:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=14186
;

