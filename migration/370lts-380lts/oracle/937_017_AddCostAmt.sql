SET SQLBLANKLINES ON
SET DEFINE OFF

-- Aug 28, 2010 5:47:45 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59706,2962,0,37,808,'CostAmt',TO_DATE('2010-08-28 17:47:45','YYYY-MM-DD HH24:MI:SS'),0,'Value with Cost','D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Value',0,TO_DATE('2010-08-28 17:47:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Aug 28, 2010 5:47:45 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59706 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2010 5:47:57 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD CostAmt NUMBER DEFAULT NULL 
;

-- Aug 28, 2010 5:49:10 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59706,59622,0,748,TO_DATE('2010-08-28 17:49:09','YYYY-MM-DD HH24:MI:SS'),0,'Value with Cost',22,'D','Y','Y','Y','N','N','N','N','N','Cost Value',TO_DATE('2010-08-28 17:49:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 28, 2010 5:49:10 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59622 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=59622
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=59533
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=59534
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=12177
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=12151
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=12156
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12342
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=12343
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=12345
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=12344
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=59521
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=59452
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=12150
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=58864
;

-- Aug 28, 2010 5:49:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=12153
;

