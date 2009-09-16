-- Jan 22, 2009 10:59:03 PM ECT
-- Calendar
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56678,190,0,19,228,'C_Calendar_ID',TO_TIMESTAMP('2009-01-22 22:58:59','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Calendar Name','D',22,'The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Calendar',0,TO_TIMESTAMP('2009-01-22 22:58:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jan 22, 2009 10:59:03 PM ECT
-- Calendar
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56678 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 22, 2009 10:59:08 PM ECT
-- Calendar
ALTER TABLE AD_OrgInfo ADD COLUMN C_Calendar_ID NUMERIC(10)
;

-- Jan 22, 2009 10:59:38 PM ECT
-- Calendar
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,52075,56621,0,170,TO_TIMESTAMP('2009-01-22 22:59:35','YYYY-MM-DD HH24:MI:SS'),100,'Bank account depending on currency will be used from this bank for doing transfers',22,'D','Y','Y','Y','N','N','N','N','N','Bank for transfers',TO_TIMESTAMP('2009-01-22 22:59:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 22, 2009 10:59:38 PM ECT
-- Calendar
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56621 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 22, 2009 10:59:39 PM ECT
-- Calendar
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56678,56622,0,170,TO_TIMESTAMP('2009-01-22 22:59:38','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Calendar Name',22,'D','The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.','Y','Y','Y','N','N','N','N','N','Calendar',TO_TIMESTAMP('2009-01-22 22:59:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 22, 2009 10:59:39 PM ECT
-- Calendar
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56622 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 22, 2009 10:59:41 PM ECT
-- Calendar
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,52076,56623,0,170,TO_TIMESTAMP('2009-01-22 22:59:39','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','N','CashBook for transfers',TO_TIMESTAMP('2009-01-22 22:59:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 22, 2009 10:59:41 PM ECT
-- Calendar
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56623 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 22, 2009 10:59:44 PM ECT
-- Calendar
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,52112,56624,0,170,TO_TIMESTAMP('2009-01-22 22:59:41','YYYY-MM-DD HH24:MI:SS'),100,'This message will be displayed at the bottom of a receipt when doing a sales or purchase',1024,'D','Y','Y','Y','N','N','N','N','N','Receipt Footer Msg',TO_TIMESTAMP('2009-01-22 22:59:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 22, 2009 10:59:44 PM ECT
-- Calendar
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56624 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 22, 2009 11:00:10 PM ECT
-- Calendar
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56621
;

-- Jan 22, 2009 11:00:10 PM ECT
-- Calendar
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56623
;

-- Jan 22, 2009 11:00:10 PM ECT
-- Calendar
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56624
;

-- Jan 22, 2009 11:00:10 PM ECT
-- Calendar
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=55416
;

-- Jan 22, 2009 11:00:10 PM ECT
-- Calendar
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=1314
;

-- Jan 22, 2009 11:00:10 PM ECT
-- Calendar
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=1315
;

-- Jan 22, 2009 11:00:10 PM ECT
-- Calendar
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56622
;

