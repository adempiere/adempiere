-- Jul 7, 2008 12:21:56 PM CDT
-- Improve MRP
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53656,0,'PlanningHorizon',TO_DATE('2008-07-07 12:21:51','YYYY-MM-DD HH24:MI:SS'),0,'The planning horizon is the amount of time an organisation will look into the future when preparing a strategic plan.','EE01','The planning horizon is the amount of time an organisation will look into the future when preparing a strategic plan.','Y','Planning Horizon','Planning Horizon',TO_DATE('2008-07-07 12:21:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 7, 2008 12:21:56 PM CDT
-- Improve MRP
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53656 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 7, 2008 12:23:05 PM CDT
-- Improve MRP
UPDATE AD_Element SET Description='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.', Help='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.',Updated=TO_DATE('2008-07-07 12:23:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53656
;

-- Jul 7, 2008 12:23:05 PM CDT
-- Improve MRP
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53656
;

-- Jul 7, 2008 12:23:05 PM CDT
-- Improve MRP
UPDATE AD_Column SET ColumnName='PlanningHorizon', Name='Planning Horizon', Description='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.', Help='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.' WHERE AD_Element_ID=53656
;

-- Jul 7, 2008 12:23:06 PM CDT
-- Improve MRP
UPDATE AD_Field SET Name='Planning Horizon', Description='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.', Help='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53656) AND IsCentrallyMaintained='Y'
;

-- Jul 7, 2008 12:23:06 PM CDT
-- Improve MRP
UPDATE AD_Process_Para SET ColumnName='PlanningHorizon', Name='Planning Horizon', Description='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.', Help='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.', AD_Element_ID=53656 WHERE UPPER(ColumnName)='PLANNINGHORIZON' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jul 7, 2008 12:23:06 PM CDT
-- Improve MRP
UPDATE AD_Process_Para SET ColumnName='PlanningHorizon', Name='Planning Horizon', Description='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.', Help='The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.' WHERE AD_Element_ID=53656 AND IsCentrallyMaintained='Y'
;

-- Jul 7, 2008 12:23:06 PM CDT
-- Improve MRP
UPDATE AD_PrintFormatItem pi SET PrintName='Planning Horizon', Name='Planning Horizon' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53656)
;

-- Jul 7, 2008 12:23:06 PM CDT
-- Improve MRP
UPDATE AD_PrintFormatItem pi SET PrintName='Planning Horizon', Name='Planning Horizon' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53656)
;

-- Jul 7, 2008 12:23:26 PM CDT
-- Improve MRP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56150,53656,0,11,487,'PlanningHorizon',TO_DATE('2008-07-07 12:23:24','YYYY-MM-DD HH24:MI:SS'),0,'0','The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.','EE01',22,'The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.','Y','N','N','N','N','N','N','N','N','N','Y','Planning Horizon',0,TO_DATE('2008-07-07 12:23:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 7, 2008 12:23:26 PM CDT
-- Improve MRP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56150 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 7, 2008 12:23:31 PM CDT
-- Improve MRP
ALTER TABLE S_Resource ADD PlanningHorizon NUMBER(10) DEFAULT 0
;

-- Jul 7, 2008 12:24:42 PM CDT
-- Improve MRP
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56150,56280,0,53015,TO_DATE('2008-07-07 12:24:21','YYYY-MM-DD HH24:MI:SS'),0,'The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.',22,'EE01','The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.','Y','Y','Y','N','N','N','N','N','Planning Horizon',TO_DATE('2008-07-07 12:24:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 7, 2008 12:24:42 PM CDT
-- Improve MRP
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56280 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 7, 2008 12:24:58 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53293
;

-- Jul 7, 2008 12:24:58 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53294
;

-- Jul 7, 2008 12:24:58 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56280
;

-- Jul 7, 2008 12:24:58 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53301
;

-- Jul 7, 2008 12:24:58 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53302
;

-- Jul 7, 2008 12:24:58 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53303
;

-- Jul 7, 2008 12:24:58 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53304
;


-- Jul 7, 2008 12:26:04 PM CDT
-- Improve MRP
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-07-07 12:26:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53301
;

-- Jul 7, 2008 12:26:24 PM CDT
-- Improve MRP
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-07-07 12:26:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53301
;

-- Jul 7, 2008 12:31:27 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=53296
;

-- Jul 7, 2008 12:31:27 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=53293
;

-- Jul 7, 2008 12:31:27 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53292
;

-- Jul 7, 2008 12:31:27 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53291
;

-- Jul 7, 2008 12:31:27 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53297
;

-- Jul 7, 2008 12:31:27 PM CDT
-- Improve MRP
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53294
;

-- Jul 7, 2008 12:35:03 PM CDT
-- Improve MRP
UPDATE AD_Field SET DisplayLogic='@IsManufacturingResource@=''Y'' & @ManufacturingResourceType@=''PT''',Updated=TO_DATE('2008-07-07 12:35:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56280
;

-- Jul 7, 2008 12:35:50 PM CDT
-- Improve MRP
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-07-07 12:35:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56280
;

