-- Dec 8, 2008 9:45:04 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56519,2554,0,20,117,'IsBetaFunctionality',TO_DATE('2008-12-08 21:45:03','YYYY-MM-DD HH24:MI:SS'),100,'N','This functionality is considered Beta','D',1,'Beta functionality is not fully tested or completed.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Beta Functionality',0,TO_DATE('2008-12-08 21:45:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 8, 2008 9:45:04 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56519 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 8, 2008 9:46:03 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
ALTER TABLE AD_Workflow ADD IsBetaFunctionality CHAR(1) DEFAULT 'N' CHECK (IsBetaFunctionality IN ('Y','N')) NOT NULL
;

-- Dec 8, 2008 9:47:56 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,56519,56501,0,148,TO_DATE('2008-12-08 21:47:51','YYYY-MM-DD HH24:MI:SS'),100,'This functionality is considered Beta',1,'D','Beta functionality is not fully tested or completed.','Y','Y','Y','N','N','N','N','N','Beta Functionality',310,0,TO_DATE('2008-12-08 21:47:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 8, 2008 9:47:56 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56501 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56501
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=10084
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=3665
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=9495
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=8752
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=8745
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=8747
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=8750
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=8744
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=8753
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=10085
;

-- Dec 8, 2008 9:48:28 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=10919
;

-- Dec 8, 2008 9:48:43 PM COT
-- [ adempiere-Feature Requests-2408411 ] Add AD_Workflow.IsBetaFunctionality
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-08 21:48:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56501
;

