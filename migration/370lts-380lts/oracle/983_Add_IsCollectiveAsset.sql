SET DEFINE OFF
SET SQLBLANKLINES ON

-- Apr 5, 2014 3:37:43 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET IsDisplayed='N', Updated=TO_DATE('2014-04-05 03:37:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59082
;

-- Apr 5, 2014 3:39:57 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Column SET AD_Val_Rule_ID=200035,Updated=TO_DATE('2014-04-05 03:39:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=26301
;

-- Apr 5, 2014 3:45:35 AM WIT
-- DICTIONARY_ID_COMMENTS
INSERT INTO AD_Element (PrintName,EntityType,ColumnName,Name,Description,AD_Element_ID,AD_Client_ID,Created,Updated,IsActive,UpdatedBy,CreatedBy,AD_Org_ID,Help) VALUES ('Collective Asset','D','IsCollectiveAsset','IsCollectiveAsset','Asset Quantity is Collective',26023,0,TO_DATE('2014-04-05 03:45:35','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2014-04-05 03:45:35','YYYY-MM-DD HH24:MI:SS'),'Y',0,0,0,'Asset Quantity is Collective')
;

-- Apr 5, 2014 3:45:35 AM WIT
-- DICTIONARY_ID_COMMENTS
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, PrintName,PO_Help,PO_Description,PO_Name,PO_PrintName,Name,Description,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.PrintName,t.PO_Help,t.PO_Description,t.PO_Name,t.PO_PrintName,t.Name,t.Description,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=26023 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 5, 2014 3:48:35 AM WIT
-- DICTIONARY_ID_COMMENTS
INSERT INTO AD_Column (AD_Table_ID,SeqNo,EntityType,Version,IsTranslated,IsMandatory,IsIdentifier,Help,IsParent,FieldLength,AD_Reference_ID,IsSelectionColumn,IsSyncDatabase,IsKey,AD_Element_ID,IsAllowLogging,IsAutocomplete,IsEncrypted,Name,ColumnName,AD_Column_ID,IsUpdateable,AD_Org_ID,Updated,CreatedBy,AD_Client_ID,IsActive,Created,UpdatedBy,IsAlwaysUpdateable,Description) VALUES (333,0,'D',0,'N','N','N','Asset Quantity is Collective','N',1,20,'N','N','N',26023,'Y','N','N','IsCollectiveAsset','IsCollectiveAsset',26303,'Y',0,TO_DATE('2014-04-05 03:48:34','YYYY-MM-DD HH24:MI:SS'),0,0,'Y',TO_DATE('2014-04-05 03:48:34','YYYY-MM-DD HH24:MI:SS'),0,'N','Asset Quantity is Collective')
;

-- Apr 5, 2014 3:48:35 AM WIT
-- DICTIONARY_ID_COMMENTS
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=26303 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 5, 2014 3:48:45 AM WIT
-- DICTIONARY_ID_COMMENTS
ALTER TABLE C_InvoiceLine ADD IsCollectiveAsset CHAR(1) DEFAULT 'N' CHECK (IsCollectiveAsset IN ('Y','N')) NOT NULL 
;

-- Apr 5, 2014 3:54:19 AM WIT
-- DICTIONARY_ID_COMMENTS
INSERT INTO AD_Field (SortNo,IsEncrypted,DisplayLength,AD_Column_ID,IsDisplayed,IsHeading,IsFieldOnly,AD_Tab_ID,SeqNo,IsCentrallyMaintained,IsReadOnly,Help,PreferredWidth,EntityType,Name,AD_Field_ID,AD_Org_ID,AD_Client_ID,IsActive,Created,CreatedBy,Updated,IsSameLine,Description,UpdatedBy) VALUES (0,'N',1,26303,'Y','N','N',291,340,'Y','N','Asset Quantity is Collective',0,'D','IsCollectiveAsset',26002,0,0,'Y',TO_DATE('2014-04-05 03:54:18','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2014-04-05 03:54:18','YYYY-MM-DD HH24:MI:SS'),'N','Asset Quantity is Collective',0)
;

-- Apr 5, 2014 3:54:19 AM WIT
-- DICTIONARY_ID_COMMENTS
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=26002 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=26002
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56253
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56254
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=5825
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=10823
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=3365
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=3374
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=10824
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=3373
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=3372
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=21300
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=3364
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=6430
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12747
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=13668
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=13669
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=13693
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=13674
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=13675
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=3370
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=8266
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=8244
;

-- Apr 5, 2014 3:55:08 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=8267
;

-- Apr 5, 2014 3:56:54 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET DisplayLogic='@A_CreateAsset@=''Y''&@A_CapvsExp@=''Cap''', IsSameLine='Y',Updated=TO_DATE('2014-04-05 03:56:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=26002
;

-- Apr 5, 2014 3:58:10 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Element SET Name='Collective Asset',Updated=TO_DATE('2014-04-05 03:58:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=26023
;

-- Apr 5, 2014 3:58:10 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=26023
;

-- Apr 5, 2014 3:58:10 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Column SET ColumnName='IsCollectiveAsset', Name='Collective Asset', Description='Asset Quantity is Collective', Help='Asset Quantity is Collective' WHERE AD_Element_ID=26023
;

-- Apr 5, 2014 3:58:10 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Process_Para SET ColumnName='IsCollectiveAsset', Name='Collective Asset', Description='Asset Quantity is Collective', Help='Asset Quantity is Collective', AD_Element_ID=26023 WHERE UPPER(ColumnName)='ISCOLLECTIVEASSET' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Apr 5, 2014 3:58:10 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Process_Para SET ColumnName='IsCollectiveAsset', Name='Collective Asset', Description='Asset Quantity is Collective', Help='Asset Quantity is Collective' WHERE AD_Element_ID=26023 AND IsCentrallyMaintained='Y'
;

-- Apr 5, 2014 3:58:10 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET Name='Collective Asset', Description='Asset Quantity is Collective', Help='Asset Quantity is Collective' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=26023) AND IsCentrallyMaintained='Y'
;

-- Apr 5, 2014 3:58:10 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_PrintFormatItem SET PrintName='Collective Asset', Name='Collective Asset' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=26023)
;

-- Apr 5, 2014 4:00:53 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field_Trl SET Name='Collective Asset',Updated=TO_DATE('2014-04-05 04:00:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=26002 AND AD_Language='in_ID'
;

-- Apr 5, 2014 4:04:22 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=59118
;

-- Apr 5, 2014 4:04:22 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=59367
;

-- Apr 5, 2014 4:04:22 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=26000
;

-- Apr 5, 2014 4:04:34 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2014-04-05 04:04:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=26000
;

-- Apr 7, 2014 9:40:32 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Column SET DefaultValue='''N''',Updated=TO_DATE('2014-04-07 09:40:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=26303
;

-- Index: a_asset
-- DROP INDEX a_asset_value;
CREATE UNIQUE INDEX a_asset_value ON a_asset(ad_client_id, value);