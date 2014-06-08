-- Aug 28, 2012 9:33:43 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53459,TO_TIMESTAMP('2012-08-28 09:33:41','YYYY-MM-DD HH24:MI:SS'),100,'Material Requirement Planning','EE01','Material Requirement Planning','Y','N','PP_MRP',TO_TIMESTAMP('2012-08-28 09:33:41','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Aug 28, 2012 9:33:43 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53459 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Aug 28, 2012 9:34:53 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,54023,54047,0,53459,53043,TO_TIMESTAMP('2012-08-28 09:34:53','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N',TO_TIMESTAMP('2012-08-28 09:34:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 9:36:26 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64224,208,0,19,53043,'C_Project_ID',TO_TIMESTAMP('2012-08-28 09:36:25','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',22,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_TIMESTAMP('2012-08-28 09:36:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2012 9:36:26 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64224 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2012 9:36:28 AM CDT
-- MFG-38 Muli-Level Pegging
ALTER TABLE PP_MRP ADD COLUMN C_Project_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 28, 2012 9:36:54 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64225,2073,0,19,53043,'C_ProjectPhase_ID',TO_TIMESTAMP('2012-08-28 09:36:53','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Phase',0,TO_TIMESTAMP('2012-08-28 09:36:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2012 9:36:54 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64225 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2012 9:36:58 AM CDT
-- MFG-38 Muli-Level Pegging
ALTER TABLE PP_MRP ADD COLUMN C_ProjectPhase_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 28, 2012 9:44:16 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64226,2074,0,19,53043,'C_ProjectTask_ID',TO_TIMESTAMP('2012-08-28 09:44:15','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01',22,'A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Task',0,TO_TIMESTAMP('2012-08-28 09:44:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2012 9:44:16 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64226 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2012 9:44:19 AM CDT
-- MFG-38 Muli-Level Pegging
ALTER TABLE PP_MRP ADD COLUMN C_ProjectTask_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 28, 2012 11:51:47 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64228,208,0,19,53021,'C_Project_ID',TO_TIMESTAMP('2012-08-28 11:51:44','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',22,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_TIMESTAMP('2012-08-28 11:51:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2012 11:51:47 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64228 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2012 11:51:57 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64229,2073,0,19,53021,'C_ProjectPhase_ID',TO_TIMESTAMP('2012-08-28 11:51:56','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Phase',0,TO_TIMESTAMP('2012-08-28 11:51:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2012 11:51:57 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64229 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2012 11:52:08 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64230,2074,0,19,53021,'C_ProjectTask_ID',TO_TIMESTAMP('2012-08-28 11:52:07','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01',22,'A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Task',0,TO_TIMESTAMP('2012-08-28 11:52:07','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2012 11:52:08 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64230 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;
-- Aug 28, 2012 12:36:24 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64177,65180,0,53180,TO_TIMESTAMP('2012-08-28 12:36:23','YYYY-MM-DD HH24:MI:SS'),100,'Is DRP Required',1,'EE01','If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Y','Y','N','N','N','N','N','Is DRP Required',TO_TIMESTAMP('2012-08-28 12:36:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 12:36:24 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65180 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2012 12:36:25 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64176,65181,0,53180,TO_TIMESTAMP('2012-08-28 12:36:24','YYYY-MM-DD HH24:MI:SS'),100,'Is MRP Required',1,'EE01','If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Y','Y','N','N','N','N','N','Is MRP Required',TO_TIMESTAMP('2012-08-28 12:36:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 12:36:25 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65181 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2012 12:36:27 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64229,65185,0,53180,TO_TIMESTAMP('2012-08-28 12:36:27','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project',22,'EE01','Y','Y','Y','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2012-08-28 12:36:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 12:36:27 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65185 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2012 12:36:28 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64230,65186,0,53180,TO_TIMESTAMP('2012-08-28 12:36:27','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase',22,'EE01','A Project Task in a Project Phase represents the actual work.','Y','Y','Y','N','N','N','N','N','Project Task',TO_TIMESTAMP('2012-08-28 12:36:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 12:36:28 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65186 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2012 12:49:42 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_Column_ID=62040, Description=NULL, Help=NULL, Name='Manufacturing Order BOM Line',Updated=TO_TIMESTAMP('2012-08-28 12:49:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56428
;

-- Aug 28, 2012 12:49:42 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56428
;

-- Aug 28, 2012 12:50:24 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_Column_ID=62041, Description=NULL, Help=NULL, Name='Distribution Order',Updated=TO_TIMESTAMP('2012-08-28 12:50:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56422
;

-- Aug 28, 2012 12:50:24 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56422
;

-- Aug 28, 2012 12:50:47 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_Column_ID=62042, Description=NULL, Help=NULL, Name='Distribution Order Line',Updated=TO_TIMESTAMP('2012-08-28 12:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56423
;

-- Aug 28, 2012 12:50:47 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56423
;

-- Aug 28, 2012 12:51:34 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_Column_ID=62043, Description='Company Agent for Planning', Help='The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.', Name='Planner',Updated=TO_TIMESTAMP('2012-08-28 12:51:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56434
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58573
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56435
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=65180
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=65181
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=65185
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=65186
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=65187
;

-- Aug 28, 2012 12:53:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=56411
;

-- Aug 28, 2012 12:53:41 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_Column_ID=62038, Description='Resource is available', Help='Resource is available for assignments', Name='Available',Updated=TO_TIMESTAMP('2012-08-28 12:53:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56411
;

-- Aug 28, 2012 12:54:06 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_Column_ID=62044, Description='Version of the table definition', Help='The Version indicates the version of this table definition.', Name='Version',Updated=TO_TIMESTAMP('2012-08-28 12:54:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56444
;

-- Aug 28, 2012 12:54:28 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_Column_ID=62039, Description='Optional short description of the record', Help='A description is limited to 255 characters.', Name='Description',Updated=TO_TIMESTAMP('2012-08-28 12:54:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56421
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56411
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=65181
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=65180
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56435
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=65185
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=65186
;

-- Aug 28, 2012 1:01:54 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=65187
;

-- Aug 28, 2012 1:02:02 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2012-08-28 13:02:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65180
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=65185
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=65186
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=65187
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56435
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Aug 28, 2012 1:02:55 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Aug 28, 2012 1:03:10 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2012-08-28 13:03:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65185
;

-- Aug 28, 2012 1:04:44 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2012-08-28 13:04:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65187
;

-- Aug 28, 2012 1:07:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_TIMESTAMP('2012-08-28 13:07:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58573
;

-- Aug 28, 2012 1:07:26 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_TIMESTAMP('2012-08-28 13:07:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65181
;

-- Aug 28, 2012 1:07:35 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_TIMESTAMP('2012-08-28 13:07:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65180
;

-- Aug 28, 2012 1:09:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 28, 2012 1:09:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 28, 2012 1:09:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=65187
;

-- Aug 28, 2012 1:09:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56435
;

-- Aug 28, 2012 1:09:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 28, 2012 1:09:18 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2012-08-28 13:09:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65187
;

-- Aug 28, 2012 1:09:31 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2012-08-28 13:09:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56414
;

-- Aug 28, 2012 1:10:05 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2012-08-28 13:10:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56437
;

-- Aug 28, 2012 1:10:10 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2012-08-28 13:10:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56414
;

-- Aug 28, 2012 1:10:15 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2012-08-28 13:10:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56419
;

-- Aug 28, 2012 1:10:21 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2012-08-28 13:10:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56415
;

-- Aug 28, 2012 1:10:26 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2012-08-28 13:10:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56420
;

-- Aug 28, 2012 1:10:31 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2012-08-28 13:10:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56417
;

-- Aug 28, 2012 1:10:36 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2012-08-28 13:10:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56416
;

-- Aug 28, 2012 1:10:44 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2012-08-28 13:10:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56418
;

-- Aug 28, 2012 1:11:31 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_TIMESTAMP('2012-08-28 13:11:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65181
;

-- Aug 28, 2012 1:11:37 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_TIMESTAMP('2012-08-28 13:11:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65180
;

-- Aug 28, 2012 1:14:23 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64231,2073,0,19,53027,'C_ProjectPhase_ID',TO_TIMESTAMP('2012-08-28 13:14:21','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Phase',0,TO_TIMESTAMP('2012-08-28 13:14:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2012 1:14:23 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64231 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2012 1:14:26 PM CDT
-- MFG-38 Muli-Level Pegging
ALTER TABLE PP_Order ADD COLUMN C_ProjectPhase_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 28, 2012 1:14:40 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64232,2074,0,19,53027,'C_ProjectTask_ID',TO_TIMESTAMP('2012-08-28 13:14:39','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01',22,'A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Task',0,TO_TIMESTAMP('2012-08-28 13:14:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2012 1:14:40 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64232 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2012 1:14:42 PM CDT
-- MFG-38 Muli-Level Pegging
ALTER TABLE PP_Order ADD COLUMN C_ProjectTask_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 28, 2012 1:15:32 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59052,65189,0,53054,TO_TIMESTAMP('2012-08-28 13:15:31','YYYY-MM-DD HH24:MI:SS'),100,'The date+time (expressed in decimal format) when the document has been processed',20,'EE01','The ProcessedOn Date+Time save the exact moment (nanoseconds precision if allowed by the DB) when a document has been processed.','Y','Y','Y','N','N','N','N','N','Processed On',TO_TIMESTAMP('2012-08-28 13:15:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 1:15:32 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65189 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2012 1:15:32 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64231,65190,0,53054,TO_TIMESTAMP('2012-08-28 13:15:32','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project',22,'EE01','Y','Y','Y','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2012-08-28 13:15:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 1:15:32 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65190 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2012 1:15:33 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64232,65191,0,53054,TO_TIMESTAMP('2012-08-28 13:15:32','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase',22,'EE01','A Project Task in a Project Phase represents the actual work.','Y','Y','Y','N','N','N','N','N','Project Task',TO_TIMESTAMP('2012-08-28 13:15:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 1:15:33 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65191 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2012 1:15:33 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53672,65192,0,53054,TO_TIMESTAMP('2012-08-28 13:15:33','YYYY-MM-DD HH24:MI:SS'),100,'Reserved Quantity',22,'EE01','The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','Y','Y','N','N','N','N','N','Reserved Quantity',TO_TIMESTAMP('2012-08-28 13:15:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2012 1:15:33 PM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65192 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=65189
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=65192
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=65190
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=65191
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=54159
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=54160
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=54161
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=54162
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=54163
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=54164
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=54165
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=54166
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=54167
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=54168
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=54169
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=54170
;

-- Aug 28, 2012 1:16:09 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=54171
;

-- Aug 28, 2012 1:16:26 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2012-08-28 13:16:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65190
;

-- Aug 31, 2012 1:28:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53435,'3',TO_TIMESTAMP('2012-08-31 13:28:55','YYYY-MM-DD HH24:MI:SS'),100,'Contains the supplies chronological relationships for each demand.','EE01','N','Y','N','Y','N','N','N','MRP Detail ID','L','PP_MRP_Detail',TO_TIMESTAMP('2012-08-31 13:28:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:28:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53435 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Aug 31, 2012 1:28:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64238,102,0,19,53435,129,'AD_Client_ID',TO_TIMESTAMP('2012-08-31 13:28:56','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2012-08-31 13:28:56','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:28:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64238 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:28:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64239,113,0,19,53435,104,'AD_Org_ID',TO_TIMESTAMP('2012-08-31 13:28:56','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2012-08-31 13:28:56','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:28:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64239 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:28:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64240,348,0,20,53435,'IsActive',TO_TIMESTAMP('2012-08-31 13:28:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2012-08-31 13:28:57','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:28:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64240 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:28:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64241,245,0,16,53435,'Created',TO_TIMESTAMP('2012-08-31 13:28:57','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2012-08-31 13:28:57','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:28:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64241 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:28:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64242,607,0,16,53435,'Updated',TO_TIMESTAMP('2012-08-31 13:28:58','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2012-08-31 13:28:58','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:28:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64242 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:28:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64243,246,0,19,110,53435,'CreatedBy',TO_TIMESTAMP('2012-08-31 13:28:58','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2012-08-31 13:28:58','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:28:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64243 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:28:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64244,608,0,19,110,53435,'UpdatedBy',TO_TIMESTAMP('2012-08-31 13:28:59','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2012-08-31 13:28:59','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:28:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64244 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:28:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55633,0,'PP_MRP_Detail_ID',TO_TIMESTAMP('2012-08-31 13:28:59','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','MRP Detail ID ID','MRP Detail ID ID',TO_TIMESTAMP('2012-08-31 13:28:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:28:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55633 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 31, 2012 1:29:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64245,55633,0,13,53435,'PP_MRP_Detail_ID',TO_TIMESTAMP('2012-08-31 13:28:59','YYYY-MM-DD HH24:MI:SS'),100,NULL,'EE01',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','MRP Detail ID ID',TO_TIMESTAMP('2012-08-31 13:28:59','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64245 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53584,TO_TIMESTAMP('2012-08-31 13:29:00','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table PP_MRP_Detail',1,'Y','N','Y','Y','PP_MRP_Detail','N',1000000,TO_TIMESTAMP('2012-08-31 13:29:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='PP_MRP_Detail_ID', Description='Contains the supplies chronological relationships for each demand.', EntityType='EE01', Help=NULL, IsActive='Y', Name='MRP Detail ID', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='MRP Detail ID',Updated=TO_TIMESTAMP('2012-08-31 13:29:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55633
;

-- Aug 31, 2012 1:29:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55633
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=13
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
CREATE TABLE PP_MRP_Detail (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, PP_MRP_Detail_ID NUMERIC(10) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT PP_MRP_Detail_Key PRIMARY KEY (PP_MRP_Detail_ID))
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2012-08-31 13:29:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=102
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=19
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_TIMESTAMP('2012-08-31 13:29:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=129
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2012-08-31 13:29:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=113
;

-- Aug 31, 2012 1:29:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2012-08-31 13:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=348
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=20
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2012-08-31 13:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=245
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=16
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2012-08-31 13:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=607
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2012-08-31 13:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=246
;

-- Aug 31, 2012 1:29:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Aug 31, 2012 1:29:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2012-08-31 13:29:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=110
;

-- Aug 31, 2012 1:29:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Aug 31, 2012 1:29:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Aug 31, 2012 1:29:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2012-08-31 13:29:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=608
;

-- Aug 31, 2012 1:29:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Aug 31, 2012 1:29:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55634,0,'MRP_Supply_ID',TO_TIMESTAMP('2012-08-31 13:29:03','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supply No','EE01','Y','Supply No','Supply No',TO_TIMESTAMP('2012-08-31 13:29:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55634 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 31, 2012 1:29:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=18
;

-- Aug 31, 2012 1:29:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Aug 31, 2012 1:29:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53460,TO_TIMESTAMP('2012-08-31 13:29:04','YYYY-MM-DD HH24:MI:SS'),100,'Material Requirement Planning','EE01','Material Requirement Planning','Y','PP_MRP Document No',TO_TIMESTAMP('2012-08-31 13:29:04','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Aug 31, 2012 1:29:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53460 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Aug 31, 2012 1:29:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Ref_Table(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, AD_Reference_ID, AD_Table_ID, AD_Display, AD_Key ,entityType, isValueDisplayed, OrderByClause, WhereClause )VALUES(0, 0, 0, 0, 53460, 53021, 58804, 58801, 'EE01', 'N', '', '')
;

-- Aug 31, 2012 1:29:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64246,55634,0,18,53460,53435,'MRP_Supply_ID',TO_TIMESTAMP('2012-08-31 13:29:04','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supply No','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Supply No',TO_TIMESTAMP('2012-08-31 13:29:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64246 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
ALTER TABLE PP_MRP_Detail ADD COLUMN MRP_Supply_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 31, 2012 1:29:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55635,0,'MRP_Demand_ID',TO_TIMESTAMP('2012-08-31 13:29:05','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demand No','EE01','Y','Demand No','Demand No',TO_TIMESTAMP('2012-08-31 13:29:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55635 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 31, 2012 1:29:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64247,55635,0,18,53460,53435,'MRP_Demand_ID',TO_TIMESTAMP('2012-08-31 13:29:05','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demand No','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Demand No',TO_TIMESTAMP('2012-08-31 13:29:05','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64247 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
ALTER TABLE PP_MRP_Detail ADD COLUMN MRP_Demand_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 31, 2012 1:29:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='Qty', Description='Quantity', EntityType='D', Help='The Quantity indicates the number of a specific product or item for this document.', IsActive='Y', Name='Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_TIMESTAMP('2012-08-31 13:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=526
;

-- Aug 31, 2012 1:29:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=526
;

-- Aug 31, 2012 1:29:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Number with 4 decimals', EntityType='D', Help=NULL, IsActive='Y', Name='Amount', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=12
;

-- Aug 31, 2012 1:29:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=12
;

-- Aug 31, 2012 1:29:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64248,526,0,12,53435,'Qty',TO_TIMESTAMP('2012-08-31 13:29:06','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','Y','Quantity',TO_TIMESTAMP('2012-08-31 13:29:06','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64248 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
ALTER TABLE PP_MRP_Detail ADD COLUMN Qty NUMERIC DEFAULT NULL 
;

-- Aug 31, 2012 1:29:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53436,'3',TO_TIMESTAMP('2012-08-31 13:29:07','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demand View','EE01','N','Y','N','N','N','N','Y','MRP Demand View','L','RV_PP_MRP_Demand',TO_TIMESTAMP('2012-08-31 13:29:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53436 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Aug 31, 2012 1:29:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64249,102,0,19,53436,129,'AD_Client_ID',TO_TIMESTAMP('2012-08-31 13:29:07','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2012-08-31 13:29:07','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64249 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64250,113,0,19,53436,104,'AD_Org_ID',TO_TIMESTAMP('2012-08-31 13:29:07','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2012-08-31 13:29:07','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64250 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64251,348,0,20,53436,'IsActive',TO_TIMESTAMP('2012-08-31 13:29:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2012-08-31 13:29:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64251 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64252,245,0,16,53436,'Created',TO_TIMESTAMP('2012-08-31 13:29:08','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2012-08-31 13:29:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64252 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64253,607,0,16,53436,'Updated',TO_TIMESTAMP('2012-08-31 13:29:09','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2012-08-31 13:29:09','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64253 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64254,246,0,19,110,53436,'CreatedBy',TO_TIMESTAMP('2012-08-31 13:29:09','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2012-08-31 13:29:09','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64254 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64255,608,0,19,110,53436,'UpdatedBy',TO_TIMESTAMP('2012-08-31 13:29:10','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2012-08-31 13:29:10','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64255 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53585,TO_TIMESTAMP('2012-08-31 13:29:10','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table RV_PP_MRP_Demand',1,'Y','N','Y','Y','RV_PP_MRP_Demand','N',1000000,TO_TIMESTAMP('2012-08-31 13:29:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Employee or SalesRep', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User - Internal', ValidationType='T',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=286
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=286
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'EXISTS (SELECT * FROM C_BPartner bp WHERE AD_User.C_BPartner_ID=bp.C_BPartner_ID AND (bp.IsEmployee=''Y'' OR bp.IsSalesRep=''Y''))' WHERE AD_Reference_ID = 286
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='PP_MRP_ID', Description='MRP ID', EntityType='EE01', Help=NULL, IsActive='Y', Name='Material Requirement Planning', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Material Requirement Planning',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53316
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53316
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=290
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=10
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='OrderType', Description='Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)', EntityType='D', Help=NULL, IsActive='Y', Name='Order Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Order Type',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=52020
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52020
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=17
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='_MRP Order Type', ValidationType='L',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53229
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53229
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=53229, Description=NULL, EntityType='EE01', IsActive='Y', Name='Distribution Order', Value='DOO',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53435
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53435
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=53229, Description=NULL, EntityType='EE01', IsActive='Y', Name='Forecast', Value='FCT',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53275
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53275
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=53229, Description=NULL, EntityType='EE01', IsActive='Y', Name='Manufacturing Order', Value='MOP',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53276
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53276
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=53229, Description=NULL, EntityType='EE01', IsActive='Y', Name='Purchase Order', Value='POO',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53277
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53277
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=53229, Description=NULL, EntityType='EE01', IsActive='Y', Name='Material Requisition', Value='POR',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53278
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53278
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=53229, Description=NULL, EntityType='EE01', IsActive='Y', Name='Sales Order', Value='SOO',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53279
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53279
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=53229, Description=NULL, EntityType='EE01', IsActive='Y', Name='Safety Stock', Value='STK',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53561
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53561
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='Planner_ID', Description='Company Agent for Planning', EntityType='EE01', Help='The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.', IsActive='Y', Name='Planner', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Planner',Updated=TO_TIMESTAMP('2012-08-31 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53269
;

-- Aug 31, 2012 1:29:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53269
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='S_Resource_ID', Description='Resource', EntityType='D', Help=NULL, IsActive='Y', Name='Resource', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Resource',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1777
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1777
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Manufacturing Resources', EntityType='EE01', Help=NULL, IsActive='Y', Name='S_Resource_Manufacturing', ValidationType='T',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53320
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53320
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_Table SET AD_Table_ID = 487, AD_Display = 6853, AD_Key = 6862, isValueDisplayed = 'N', OrderByClause = '', EntityType ='EE01', WhereClause = '' WHERE AD_Reference_ID = 53320
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Val_Rule SET Code='IsManufacturingResource=''Y'' AND ManufacturingResourceType=''PT''', Description=NULL, EntityType='EE01', IsActive='Y', Name='S_Resource Plant', Type='S',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52002
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='M_Warehouse_ID', Description='Storage Warehouse and Service Point', EntityType='D', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', Name='Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=459
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=459
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='DatePromised', Description='Date Order was promised', EntityType='D', Help='The Date Promised indicates the date, if any, that an Order was promised for.', IsActive='Y', Name='Date Promised', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Date Promised',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=269
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=269
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='Priority', Description='Indicates if this request is of a high, medium or low priority.', EntityType='D', Help='The Priority indicates the importance of this request.', IsActive='Y', Name='Priority', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Priority',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1514
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1514
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Priority scale list', EntityType='D', Help=NULL, IsActive='Y', Name='_PriorityRule', ValidationType='L',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=154
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=154
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=154, Description=NULL, EntityType='D', IsActive='Y', Name='Urgent', Value='1',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=759
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=759
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=154, Description=NULL, EntityType='D', IsActive='Y', Name='Minor', Value='9',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=760
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=760
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=454
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='SKU', Description='Stock Keeping Unit', EntityType='D', Help='The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.', IsActive='Y', Name='SKU', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='SKU',Updated=TO_TIMESTAMP('2012-08-31 13:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=549
;

-- Aug 31, 2012 1:29:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=549
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='C_UOM_ID', Description='Unit of Measure', EntityType='D', Help='The UOM defines a unique non monetary Unit of Measure', IsActive='Y', Name='UOM', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UOM',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=215
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=215
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='M_Product_Category_ID', Description='Category of a Product', EntityType='D', Help='Identifies the category which this product belongs to.  Product categories are used for pricing and selection.', IsActive='Y', Name='Product Category', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Category',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=453
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=453
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_Product Category ', ValidationType='T',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=163
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=163
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_Table SET AD_Table_ID = 209, AD_Display = 1776, AD_Key = 2020, isValueDisplayed = 'Y', OrderByClause = 'M_Product_Category.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 163
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='IsBOM', Description='Bill of Materials', EntityType='D', Help='The Bill of Materials check box indicates if this product consists of a bill of materials.', IsActive='Y', Name='Bill of Materials', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='BOM',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1326
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1326
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='IsPurchased', Description='Organization purchases this product', EntityType='D', Help='The Purchased check box indicates if this product is purchased by this organization.', IsActive='Y', Name='Purchased', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Purchased',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=403
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=403
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=29
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='DocStatus', Description='The current status of the document', EntityType='D', Help='The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field', IsActive='Y', Name='Document Status', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Doc Status',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=289
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=289
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Document Status list', EntityType='D', Help=NULL, IsActive='Y', Name='_Document Status', ValidationType='L',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=131
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=131
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Unknown', Value='??',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=190
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=190
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Approved', Value='AP',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=166
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=166
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Closed', Value='CL',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=177
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=177
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Completed', Value='CO',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=165
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=165
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Drafted', Value='DR',Updated=TO_TIMESTAMP('2012-08-31 13:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=164
;

-- Aug 31, 2012 1:29:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=164
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Invalid', Value='IN',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=173
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=173
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='In Progress', Value='IP',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=341
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=341
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Not Approved', Value='NA',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=168
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=168
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Reversed', Value='RE',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=176
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=176
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Voided', Value='VO',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=172
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=172
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Waiting Confirmation', Value='WC',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=670
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=670
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=131, Description=NULL, EntityType='D', IsActive='Y', Name='Waiting Payment', Value='WP',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=346
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=346
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='C_BPartner_ID', Description='Identifies a Business Partner', EntityType='D', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', Name='Business Partner ', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Business Partner ',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=187
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=187
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='DateOrdered', Description='Date of Order', EntityType='D', Help='Indicates the Date an item was ordered.', IsActive='Y', Name='Date Ordered', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Date Ordered',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=268
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=268
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='IsMPS', Description='Indicates if this product is part of the master production schedule', EntityType='EE01', Help='The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.', IsActive='Y', Name='Is MPS', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Is MPS',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53261
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53261
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='IsRequiredMRP', Description='Is MRP Required', EntityType='EE01', Help='If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.', IsActive='Y', Name='Is MRP Required', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Is MRP Required',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53262
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53262
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='IsRequiredDRP', Description='Is DRP Required', EntityType='EE01', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.', IsActive='Y', Name='Is DRP Required', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Is DRP Required',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53470
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53470
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element SET ColumnName='IsSold', Description='Organization sells this product', EntityType='D', Help='The Sold check box indicates if this product is sold by this organization.', IsActive='Y', Name='Sold', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Sold',Updated=TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=414
;

-- Aug 31, 2012 1:29:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=414
;

-- Aug 31, 2012 1:29:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53437,'3',TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supply View','EE01','N','Y','N','N','N','N','Y','MRP Supply View','L','RV_PP_MRP_Supply',TO_TIMESTAMP('2012-08-31 13:29:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53437 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Aug 31, 2012 1:29:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64256,102,0,19,53437,129,'AD_Client_ID',TO_TIMESTAMP('2012-08-31 13:29:15','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2012-08-31 13:29:15','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64256 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64257,113,0,19,53437,104,'AD_Org_ID',TO_TIMESTAMP('2012-08-31 13:29:15','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2012-08-31 13:29:15','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64257 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64258,348,0,20,53437,'IsActive',TO_TIMESTAMP('2012-08-31 13:29:16','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2012-08-31 13:29:16','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64258 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64259,245,0,16,53437,'Created',TO_TIMESTAMP('2012-08-31 13:29:16','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2012-08-31 13:29:16','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64259 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64260,607,0,16,53437,'Updated',TO_TIMESTAMP('2012-08-31 13:29:17','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2012-08-31 13:29:17','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64260 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64261,246,0,19,110,53437,'CreatedBy',TO_TIMESTAMP('2012-08-31 13:29:17','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2012-08-31 13:29:17','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64261 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64262,608,0,19,110,53437,'UpdatedBy',TO_TIMESTAMP('2012-08-31 13:29:17','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2012-08-31 13:29:17','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64262 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53586,TO_TIMESTAMP('2012-08-31 13:29:18','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table RV_PP_MRP_Supply',1,'Y','N','Y','Y','RV_PP_MRP_Supply','N',1000000,TO_TIMESTAMP('2012-08-31 13:29:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53438,'3',TO_TIMESTAMP('2012-08-31 13:29:18','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demand Detail View','EE01','N','Y','N','N','N','N','Y','MRP Demand Detail View','L','RV_PP_MRP_Detail_Demand',TO_TIMESTAMP('2012-08-31 13:29:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53438 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Aug 31, 2012 1:29:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64263,102,0,19,53438,129,'AD_Client_ID',TO_TIMESTAMP('2012-08-31 13:29:18','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2012-08-31 13:29:18','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64263 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64264,113,0,19,53438,104,'AD_Org_ID',TO_TIMESTAMP('2012-08-31 13:29:19','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2012-08-31 13:29:19','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64264 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64265,348,0,20,53438,'IsActive',TO_TIMESTAMP('2012-08-31 13:29:19','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2012-08-31 13:29:19','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64265 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64266,245,0,16,53438,'Created',TO_TIMESTAMP('2012-08-31 13:29:20','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2012-08-31 13:29:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64266 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:21 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64267,607,0,16,53438,'Updated',TO_TIMESTAMP('2012-08-31 13:29:20','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2012-08-31 13:29:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:21 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64267 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:21 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64268,246,0,19,110,53438,'CreatedBy',TO_TIMESTAMP('2012-08-31 13:29:21','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2012-08-31 13:29:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:21 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64268 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64269,608,0,19,110,53438,'UpdatedBy',TO_TIMESTAMP('2012-08-31 13:29:21','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2012-08-31 13:29:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64269 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53587,TO_TIMESTAMP('2012-08-31 13:29:22','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table RV_PP_MRP_Detail_Demand',1,'Y','N','Y','Y','RV_PP_MRP_Detail_Demand','N',1000000,TO_TIMESTAMP('2012-08-31 13:29:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64270,55635,0,18,53460,53438,'MRP_Demand_ID',TO_TIMESTAMP('2012-08-31 13:29:24','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demand No','EE01',10,'Y','N','N','Y','N','N','N','N','Y','N','N','Demand No',1,TO_TIMESTAMP('2012-08-31 13:29:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64270 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64271,55634,0,18,53460,53438,'MRP_Supply_ID',TO_TIMESTAMP('2012-08-31 13:29:24','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supply No','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Supply No',TO_TIMESTAMP('2012-08-31 13:29:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64271 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64272,526,0,29,53438,'Qty',TO_TIMESTAMP('2012-08-31 13:29:25','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','N','Quantity',TO_TIMESTAMP('2012-08-31 13:29:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64272 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64273,52020,0,17,53229,53438,'OrderType',TO_TIMESTAMP('2012-08-31 13:29:25','YYYY-MM-DD HH24:MI:SS'),100,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)','EE01',3,'Y','N','N','N','N','N','N','N','Y','N','N','Order Type',TO_TIMESTAMP('2012-08-31 13:29:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64273 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64274,289,0,17,131,53438,'DocStatus',TO_TIMESTAMP('2012-08-31 13:29:26','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','Y','N','N','Document Status',TO_TIMESTAMP('2012-08-31 13:29:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64274 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:29:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=15
;

-- Aug 31, 2012 1:29:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Aug 31, 2012 1:29:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64275,268,0,15,53438,'DateOrdered',TO_TIMESTAMP('2012-08-31 13:29:26','YYYY-MM-DD HH24:MI:SS'),100,'Date of Order','EE01',7,'Indicates the Date an item was ordered.','Y','N','N','N','N','N','N','N','Y','N','N','Date Ordered',TO_TIMESTAMP('2012-08-31 13:29:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64275 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:27 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64276,269,0,15,53438,'DatePromised',TO_TIMESTAMP('2012-08-31 13:29:26','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01',7,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','Y','N','N','N','N','Y','N','N','Date Promised',2,TO_TIMESTAMP('2012-08-31 13:29:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:27 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64276 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:27 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64277,1514,0,17,154,53438,'Priority',TO_TIMESTAMP('2012-08-31 13:29:27','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this request is of a high, medium or low priority.','EE01',1,'The Priority indicates the importance of this request.','Y','N','N','N','N','N','N','N','Y','N','N','Priority',TO_TIMESTAMP('2012-08-31 13:29:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:27 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64277 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64278,1777,0,18,53320,53438,52002,'S_Resource_ID',TO_TIMESTAMP('2012-08-31 13:29:27','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Resource',TO_TIMESTAMP('2012-08-31 13:29:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64278 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64279,459,0,19,53438,'M_Warehouse_ID',TO_TIMESTAMP('2012-08-31 13:29:28','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','Y','N','N','Warehouse',TO_TIMESTAMP('2012-08-31 13:29:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64279 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64280,187,0,19,53438,'C_BPartner_ID',TO_TIMESTAMP('2012-08-31 13:29:28','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE01',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','N','Business Partner ',TO_TIMESTAMP('2012-08-31 13:29:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64280 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64281,53269,0,18,286,53438,'Planner_ID',TO_TIMESTAMP('2012-08-31 13:29:29','YYYY-MM-DD HH24:MI:SS'),100,'Company Agent for Planning','EE01',22,'The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','N','Planner',TO_TIMESTAMP('2012-08-31 13:29:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64281 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53439,'3',TO_TIMESTAMP('2012-08-31 13:29:29','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supply Detail View','EE01','N','Y','N','N','N','N','Y','MRP Supply Detail View','L','RV_PP_MRP_Detail_Supply',TO_TIMESTAMP('2012-08-31 13:29:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53439 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Aug 31, 2012 1:29:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64282,102,0,19,53439,129,'AD_Client_ID',TO_TIMESTAMP('2012-08-31 13:29:29','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2012-08-31 13:29:29','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64282 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64283,113,0,19,53439,104,'AD_Org_ID',TO_TIMESTAMP('2012-08-31 13:29:30','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2012-08-31 13:29:30','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64283 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64284,348,0,20,53439,'IsActive',TO_TIMESTAMP('2012-08-31 13:29:30','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2012-08-31 13:29:30','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64284 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64285,245,0,16,53439,'Created',TO_TIMESTAMP('2012-08-31 13:29:31','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2012-08-31 13:29:31','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64285 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64286,607,0,16,53439,'Updated',TO_TIMESTAMP('2012-08-31 13:29:31','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2012-08-31 13:29:31','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64286 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64287,246,0,19,110,53439,'CreatedBy',TO_TIMESTAMP('2012-08-31 13:29:32','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2012-08-31 13:29:32','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64287 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64288,608,0,19,110,53439,'UpdatedBy',TO_TIMESTAMP('2012-08-31 13:29:32','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2012-08-31 13:29:32','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 31, 2012 1:29:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64288 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:33 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53588,TO_TIMESTAMP('2012-08-31 13:29:32','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table RV_PP_MRP_Detail_Supply',1,'Y','N','Y','Y','RV_PP_MRP_Detail_Supply','N',1000000,TO_TIMESTAMP('2012-08-31 13:29:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64289,55635,0,18,53460,53439,'MRP_Demand_ID',TO_TIMESTAMP('2012-08-31 13:29:34','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demand No','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Demand No',TO_TIMESTAMP('2012-08-31 13:29:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64289 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64290,55634,0,18,53460,53439,'MRP_Supply_ID',TO_TIMESTAMP('2012-08-31 13:29:35','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supply No','EE01',22,'Y','N','N','Y','N','N','N','N','Y','N','N','Supply No',1,TO_TIMESTAMP('2012-08-31 13:29:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64290 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64291,52020,0,17,53229,53439,'OrderType',TO_TIMESTAMP('2012-08-31 13:29:35','YYYY-MM-DD HH24:MI:SS'),100,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)','EE01',3,'Y','N','N','N','N','N','N','N','Y','N','N','Order Type',TO_TIMESTAMP('2012-08-31 13:29:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64291 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64292,289,0,17,131,53439,'DocStatus',TO_TIMESTAMP('2012-08-31 13:29:35','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','EE01',2147483647,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','Y','N','N','Document Status',TO_TIMESTAMP('2012-08-31 13:29:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64292 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64293,268,0,15,53439,'DateOrdered',TO_TIMESTAMP('2012-08-31 13:29:36','YYYY-MM-DD HH24:MI:SS'),100,'Date of Order','EE01',35,'Indicates the Date an item was ordered.','Y','N','N','N','N','N','N','N','Y','N','N','Date Ordered',TO_TIMESTAMP('2012-08-31 13:29:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64293 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64294,269,0,15,53439,'DatePromised',TO_TIMESTAMP('2012-08-31 13:29:36','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01',35,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','Y','N','N','N','N','Y','N','N','Date Promised',2,TO_TIMESTAMP('2012-08-31 13:29:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64294 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64295,1514,0,17,154,53439,'Priority',TO_TIMESTAMP('2012-08-31 13:29:37','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this request is of a high, medium or low priority.','EE01',2147483647,'The Priority indicates the importance of this request.','Y','N','N','N','N','N','N','N','Y','N','N','Priority',TO_TIMESTAMP('2012-08-31 13:29:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64295 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64296,1777,0,18,53320,53439,52002,'S_Resource_ID',TO_TIMESTAMP('2012-08-31 13:29:37','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Resource',TO_TIMESTAMP('2012-08-31 13:29:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64296 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64297,459,0,19,53439,'M_Warehouse_ID',TO_TIMESTAMP('2012-08-31 13:29:38','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','Y','N','N','Warehouse',TO_TIMESTAMP('2012-08-31 13:29:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64297 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64298,187,0,19,53439,'C_BPartner_ID',TO_TIMESTAMP('2012-08-31 13:29:38','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE01',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','N','Business Partner ',TO_TIMESTAMP('2012-08-31 13:29:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64298 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64299,53269,0,18,286,53439,'Planner_ID',TO_TIMESTAMP('2012-08-31 13:29:38','YYYY-MM-DD HH24:MI:SS'),100,'Company Agent for Planning','EE01',10,'The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','N','Planner',TO_TIMESTAMP('2012-08-31 13:29:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64299 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64300,526,0,29,53439,'Qty',TO_TIMESTAMP('2012-08-31 13:29:39','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','N','Quantity',TO_TIMESTAMP('2012-08-31 13:29:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:29:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64300 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:29:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WinHeight,WinWidth,WindowType) VALUES (0,0,53202,TO_TIMESTAMP('2012-08-31 13:29:39','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demands View','EE01','MRP Demands View allows to display a demands detail and their respective supplies.','Y','N','N','N','MRP Demands View','N',TO_TIMESTAMP('2012-08-31 13:29:39','YYYY-MM-DD HH24:MI:SS'),100,882,1351,'M')
;

-- Aug 31, 2012 1:29:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53202 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Aug 31, 2012 1:29:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53567,53436,53202,NULL,TO_TIMESTAMP('2012-08-31 13:29:40','YYYY-MM-DD HH24:MI:SS'),100,'Demand Detail','EE01','N','Detailed information of the demand','Y','N','N','N','Y','Y','N','N','Demand Detail','N',10,0,TO_TIMESTAMP('2012-08-31 13:29:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53567 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Aug 31, 2012 1:29:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64251,65204,0,53567,TO_TIMESTAMP('2012-08-31 13:29:40','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','N','Y','Y','N','N','N','N','N','Active',0,0,0,TO_TIMESTAMP('2012-08-31 13:29:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65204 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64249,65205,0,53567,TO_TIMESTAMP('2012-08-31 13:29:41','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','N','Y','Y','Y','N','N','N','N','Client',0,10,0,TO_TIMESTAMP('2012-08-31 13:29:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65205 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64250,65206,0,53567,TO_TIMESTAMP('2012-08-31 13:29:41','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','N','Y','Y','Y','N','N','N','Y','Organization',0,20,0,TO_TIMESTAMP('2012-08-31 13:29:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65206 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,OrderByClause,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,0,53568,53439,53202,NULL,TO_TIMESTAMP('2012-08-31 13:29:42','YYYY-MM-DD HH24:MI:SS'),100,'Supplies Detail','EE01','N','Detailed information of the supplies for this demand.','Y','N','N','N','Y','N','N','N','Supplies Detail','DatePromised','N',20,1,TO_TIMESTAMP('2012-08-31 13:29:42','YYYY-MM-DD HH24:MI:SS'),100,'MRP_Demand_ID=@PP_MRP_ID@')
;

-- Aug 31, 2012 1:29:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53568 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Aug 31, 2012 1:29:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64284,65207,0,53568,TO_TIMESTAMP('2012-08-31 13:29:42','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','N','Y','Y','N','N','N','N','N','Active',0,0,0,TO_TIMESTAMP('2012-08-31 13:29:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65207 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64282,65208,0,53568,TO_TIMESTAMP('2012-08-31 13:29:43','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','N','Y','Y','N','N','N','N','N','Client',0,0,0,TO_TIMESTAMP('2012-08-31 13:29:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65208 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:44 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64289,65209,0,53568,TO_TIMESTAMP('2012-08-31 13:29:43','YYYY-MM-DD HH24:MI:SS'),100,NULL,'MRP Demand No',10,'EE01','N','Y','Y','N','N','N','N','N','Demand No',0,0,0,TO_TIMESTAMP('2012-08-31 13:29:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:44 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65209 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:45 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64283,65210,0,53568,TO_TIMESTAMP('2012-08-31 13:29:44','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','N','Y','Y','N','N','N','N','N','Organization',0,0,0,TO_TIMESTAMP('2012-08-31 13:29:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:45 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65210 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:46 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64290,65211,0,53568,TO_TIMESTAMP('2012-08-31 13:29:45','YYYY-MM-DD HH24:MI:SS'),100,NULL,'MRP Supply No',22,'EE01','N','Y','Y','Y','N','N','N','N','Supply No',0,10,0,TO_TIMESTAMP('2012-08-31 13:29:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:46 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65211 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:46 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64295,65212,0,53568,TO_TIMESTAMP('2012-08-31 13:29:46','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Indicates if this request is of a high, medium or low priority.',10,'EE01','The Priority indicates the importance of this request.','N','Y','Y','Y','N','N','N','Y','Priority',0,20,0,TO_TIMESTAMP('2012-08-31 13:29:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:46 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65212 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64291,65213,0,53568,TO_TIMESTAMP('2012-08-31 13:29:46','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)',3,'EE01','N','Y','Y','Y','N','N','N','N','Order Type',0,30,0,TO_TIMESTAMP('2012-08-31 13:29:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65213 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64292,65214,0,53568,TO_TIMESTAMP('2012-08-31 13:29:47','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The current status of the document',22,'EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','N','Y','Y','Y','N','N','N','Y','Document Status',0,40,0,TO_TIMESTAMP('2012-08-31 13:29:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65214 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:48 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64293,65215,0,53568,TO_TIMESTAMP('2012-08-31 13:29:47','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date of Order',7,'EE01','Indicates the Date an item was ordered.','N','Y','Y','Y','N','N','N','N','Date Ordered',0,50,0,TO_TIMESTAMP('2012-08-31 13:29:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:48 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65215 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:48 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64294,65216,0,53568,TO_TIMESTAMP('2012-08-31 13:29:48','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date Order was promised',7,'EE01','The Date Promised indicates the date, if any, that an Order was promised for.','N','Y','Y','Y','N','N','N','Y','Date Promised',0,60,0,TO_TIMESTAMP('2012-08-31 13:29:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:48 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65216 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64298,65217,0,53568,TO_TIMESTAMP('2012-08-31 13:29:48','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Identifies a Business Partner',10,'EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','N','Y','Y','Y','N','N','N','N','Business Partner ',0,70,0,TO_TIMESTAMP('2012-08-31 13:29:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65217 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64299,65218,0,53568,TO_TIMESTAMP('2012-08-31 13:29:49','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Company Agent for Planning',10,'EE01','The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','N','Y','Y','Y','N','N','N','Y','Planner',0,80,0,TO_TIMESTAMP('2012-08-31 13:29:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65218 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64296,65219,0,53568,TO_TIMESTAMP('2012-08-31 13:29:49','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Resource',10,'EE01','N','Y','Y','Y','N','N','N','N','Resource',0,90,0,TO_TIMESTAMP('2012-08-31 13:29:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65219 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64297,65220,0,53568,TO_TIMESTAMP('2012-08-31 13:29:50','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Storage Warehouse and Service Point',10,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','N','Y','Y','Y','N','N','N','Y','Warehouse',0,100,0,TO_TIMESTAMP('2012-08-31 13:29:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65220 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64300,65221,0,53568,TO_TIMESTAMP('2012-08-31 13:29:50','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Quantity',22,'EE01','The Quantity indicates the number of a specific product or item for this document.','N','Y','Y','Y','N','N','N','N','Quantity',0,110,0,TO_TIMESTAMP('2012-08-31 13:29:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65221 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:51 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WinHeight,WinWidth,WindowType) VALUES (0,0,53203,TO_TIMESTAMP('2012-08-31 13:29:50','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supplies View','EE01','MRP Supplies View allows to display a supply detail and theirs respective demands.','Y','N','N','N','MRP Supplies View','N',TO_TIMESTAMP('2012-08-31 13:29:50','YYYY-MM-DD HH24:MI:SS'),100,905,1353,'M')
;

-- Aug 31, 2012 1:29:51 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53203 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Aug 31, 2012 1:29:51 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53569,53437,53203,NULL,TO_TIMESTAMP('2012-08-31 13:29:51','YYYY-MM-DD HH24:MI:SS'),100,'Supply Detail','EE01','N','Detailed information of the supply','Y','N','N','N','Y','Y','N','N','Supply Detail','N',10,0,TO_TIMESTAMP('2012-08-31 13:29:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:51 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53569 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Aug 31, 2012 1:29:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64258,65222,0,53569,TO_TIMESTAMP('2012-08-31 13:29:51','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','N','Y','Y','N','N','N','N','N','Active',0,0,0,TO_TIMESTAMP('2012-08-31 13:29:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65222 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64256,65223,0,53569,TO_TIMESTAMP('2012-08-31 13:29:52','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','N','Y','Y','Y','N','N','N','N','Client',0,10,0,TO_TIMESTAMP('2012-08-31 13:29:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65223 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:53 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64257,65224,0,53569,TO_TIMESTAMP('2012-08-31 13:29:52','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','N','Y','Y','Y','N','N','N','Y','Organization',0,20,0,TO_TIMESTAMP('2012-08-31 13:29:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:53 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65224 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:53 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,OrderByClause,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,0,53570,53438,53203,NULL,TO_TIMESTAMP('2012-08-31 13:29:53','YYYY-MM-DD HH24:MI:SS'),100,'Demands Detail','EE01','N','Detailed information of the demands for this supply.','Y','N','N','N','Y','N','N','N','Demands Detail','DatePromised','N',20,1,TO_TIMESTAMP('2012-08-31 13:29:53','YYYY-MM-DD HH24:MI:SS'),100,'MRP_Supply_ID=@PP_MRP_ID@')
;

-- Aug 31, 2012 1:29:53 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53570 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Aug 31, 2012 1:29:54 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64265,65225,0,53570,TO_TIMESTAMP('2012-08-31 13:29:53','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','N','Y','Y','N','N','N','N','N','Active',0,0,0,TO_TIMESTAMP('2012-08-31 13:29:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:54 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65225 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64271,65226,0,53570,TO_TIMESTAMP('2012-08-31 13:29:54','YYYY-MM-DD HH24:MI:SS'),100,NULL,'MRP Supply No',10,'EE01','N','Y','Y','N','N','N','N','N','Supply No',0,0,0,TO_TIMESTAMP('2012-08-31 13:29:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65226 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64263,65227,0,53570,TO_TIMESTAMP('2012-08-31 13:29:56','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','Y','N','N','N','N','Client',0,10,0,TO_TIMESTAMP('2012-08-31 13:29:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65227 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64264,65228,0,53570,TO_TIMESTAMP('2012-08-31 13:29:57','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','Y','N','N','N','Y','Organization',0,20,0,TO_TIMESTAMP('2012-08-31 13:29:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65228 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64270,65229,0,53570,TO_TIMESTAMP('2012-08-31 13:29:57','YYYY-MM-DD HH24:MI:SS'),100,NULL,'MRP Demand No',10,'EE01','N','Y','Y','Y','N','N','N','N','Demand No',0,30,0,TO_TIMESTAMP('2012-08-31 13:29:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65229 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:29:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64277,65230,0,53570,TO_TIMESTAMP('2012-08-31 13:29:58','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Indicates if this request is of a high, medium or low priority.',1,'EE01','The Priority indicates the importance of this request.','N','Y','Y','Y','N','N','N','Y','Priority',0,40,0,TO_TIMESTAMP('2012-08-31 13:29:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:29:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65230 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64273,65231,0,53570,TO_TIMESTAMP('2012-08-31 13:29:59','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)',3,'EE01','N','Y','Y','Y','N','N','N','N','Order Type',0,50,0,TO_TIMESTAMP('2012-08-31 13:29:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65231 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64274,65232,0,53570,TO_TIMESTAMP('2012-08-31 13:30:00','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The current status of the document',2,'EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','N','Y','Y','Y','N','N','N','Y','Document Status',0,60,0,TO_TIMESTAMP('2012-08-31 13:30:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65232 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64275,65233,0,53570,TO_TIMESTAMP('2012-08-31 13:30:00','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date of Order',7,'EE01','Indicates the Date an item was ordered.','N','Y','Y','Y','N','N','N','N','Date Ordered',0,70,0,TO_TIMESTAMP('2012-08-31 13:30:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65233 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64276,65234,0,53570,TO_TIMESTAMP('2012-08-31 13:30:01','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date Order was promised',7,'EE01','The Date Promised indicates the date, if any, that an Order was promised for.','N','Y','Y','Y','N','N','N','Y','Date Promised',0,80,0,TO_TIMESTAMP('2012-08-31 13:30:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65234 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64280,65235,0,53570,TO_TIMESTAMP('2012-08-31 13:30:02','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Identifies a Business Partner',22,'EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','N','Y','Y','Y','N','N','N','N','Business Partner ',0,90,0,TO_TIMESTAMP('2012-08-31 13:30:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65235 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64281,65236,0,53570,TO_TIMESTAMP('2012-08-31 13:30:02','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Company Agent for Planning',22,'EE01','The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','N','Y','Y','Y','N','N','N','Y','Planner',0,100,0,TO_TIMESTAMP('2012-08-31 13:30:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65236 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64278,65237,0,53570,TO_TIMESTAMP('2012-08-31 13:30:03','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Resource',10,'EE01','N','Y','Y','Y','N','N','N','N','Resource',0,110,0,TO_TIMESTAMP('2012-08-31 13:30:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65237 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64279,65238,0,53570,TO_TIMESTAMP('2012-08-31 13:30:03','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Storage Warehouse and Service Point',10,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','N','Y','Y','Y','N','N','N','Y','Warehouse',0,120,0,TO_TIMESTAMP('2012-08-31 13:30:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65238 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64272,65239,0,53570,TO_TIMESTAMP('2012-08-31 13:30:04','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Quantity',22,'EE01','The Quantity indicates the number of a specific product or item for this document.','N','Y','Y','Y','N','N','N','N','Quantity',0,130,0,TO_TIMESTAMP('2012-08-31 13:30:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65239 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View (AD_Client_ID,AD_Org_ID,AD_View_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50010,TO_TIMESTAMP('2012-08-31 13:30:04','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demands View','EE01','This view allows to display independent and dependent demand  for products, to be able to track the supply for each  requirement.','Y','MRP Demands View',TO_TIMESTAMP('2012-08-31 13:30:04','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demands View')
;

-- Aug 31, 2012 1:30:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Trl (AD_Language,AD_View_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_View_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_View t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_View_ID=50010 AND NOT EXISTS (SELECT * FROM AD_View_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_View_ID=t.AD_View_ID)
;

-- Aug 31, 2012 1:30:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,53436,50029,50010,TO_TIMESTAMP('2012-08-31 13:30:05','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',10,'demand',TO_TIMESTAMP('2012-08-31 13:30:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64249,0,51191,50029,50010,'DEMAND_AD_Client_ID','demand.AD_Client_ID',TO_TIMESTAMP('2012-08-31 13:30:05','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2012-08-31 13:30:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64250,0,51192,50029,50010,'DEMAND_AD_Org_ID','demand.AD_Org_ID',TO_TIMESTAMP('2012-08-31 13:30:06','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2012-08-31 13:30:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64252,0,51193,50029,50010,'DEMAND_Created','demand.Created',TO_TIMESTAMP('2012-08-31 13:30:06','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2012-08-31 13:30:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64254,0,51194,50029,50010,'DEMAND_CreatedBy','demand.CreatedBy',TO_TIMESTAMP('2012-08-31 13:30:06','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2012-08-31 13:30:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64251,0,51195,50029,50010,'DEMAND_IsActive','demand.IsActive',TO_TIMESTAMP('2012-08-31 13:30:07','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2012-08-31 13:30:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64253,0,51196,50029,50010,'DEMAND_Updated','demand.Updated',TO_TIMESTAMP('2012-08-31 13:30:07','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2012-08-31 13:30:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64255,0,51197,50029,50010,'DEMAND_UpdatedBy','demand.UpdatedBy',TO_TIMESTAMP('2012-08-31 13:30:08','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2012-08-31 13:30:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse (AD_Browse_ID,AD_Client_ID,AD_Org_ID,AD_View_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Processing,Updated,UpdatedBy,Value,WhereClause) VALUES (50011,0,0,50010,'3',TO_TIMESTAMP('2012-08-31 13:30:08','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demands Browse','C','This browse allows to display independent and dependent demand  for products, to be able to track the supply for each  requirement.

<p>
To know how the supplies satisfies each demand , it is possible to select and press the zoom icon, to  display the MRP Demands View with details of demand and their supplies.
','Y','MRP Demands Browse','N',TO_TIMESTAMP('2012-08-31 13:30:08','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demands Browse',NULL)
;

-- Aug 31, 2012 1:30:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Trl (AD_Language,AD_Browse_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_ID=50011 AND NOT EXISTS (SELECT * FROM AD_Browse_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_ID=t.AD_Browse_ID)
;

-- Aug 31, 2012 1:30:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51465,50011,0,607,0,16,51196,TO_TIMESTAMP('2012-08-31 13:30:09','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','N','N','N','Y','Updated',0,0,TO_TIMESTAMP('2012-08-31 13:30:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51465 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51466,50011,0,102,0,19,51191,TO_TIMESTAMP('2012-08-31 13:30:09','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','N','N','Y','Client',0,0,TO_TIMESTAMP('2012-08-31 13:30:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51466 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51467,50011,0,608,0,18,286,51197,TO_TIMESTAMP('2012-08-31 13:30:09','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','N','N','N','Y','Updated By',0,0,TO_TIMESTAMP('2012-08-31 13:30:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51467 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51468,50011,0,113,0,19,51192,TO_TIMESTAMP('2012-08-31 13:30:10','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','N','N','Y','Organization',0,0,TO_TIMESTAMP('2012-08-31 13:30:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51468 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51469,50011,0,245,0,16,51193,TO_TIMESTAMP('2012-08-31 13:30:10','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','N','N','N','Y','Created',0,0,TO_TIMESTAMP('2012-08-31 13:30:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51469 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51470,50011,0,246,0,18,286,51194,TO_TIMESTAMP('2012-08-31 13:30:11','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','N','N','N','Y','Created By',0,0,TO_TIMESTAMP('2012-08-31 13:30:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51470 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51471,50011,0,348,0,20,51195,TO_TIMESTAMP('2012-08-31 13:30:11','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','N','N','Y','Active',0,0,TO_TIMESTAMP('2012-08-31 13:30:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51471 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_TIMESTAMP('2012-08-31 13:30:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=30
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='_YesNo', ValidationType='L',Updated=TO_TIMESTAMP('2012-08-31 13:30:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=319
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=319
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=319, Description=NULL, EntityType='D', IsActive='Y', Name='No', Value='N',Updated=TO_TIMESTAMP('2012-08-31 13:30:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=668
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=668
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List SET AD_Reference_ID=319, Description=NULL, EntityType='D', IsActive='Y', Name='Yes', Value='Y',Updated=TO_TIMESTAMP('2012-08-31 13:30:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=667
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=667
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View (AD_Client_ID,AD_Org_ID,AD_View_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50011,TO_TIMESTAMP('2012-08-31 13:30:12','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supplies View','EE01','This view allows to display supplies, to be able to track each supply of every demand.','Y','MRP Supplies View',TO_TIMESTAMP('2012-08-31 13:30:12','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supplies View')
;

-- Aug 31, 2012 1:30:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Trl (AD_Language,AD_View_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_View_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_View t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_View_ID=50011 AND NOT EXISTS (SELECT * FROM AD_View_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_View_ID=t.AD_View_ID)
;

-- Aug 31, 2012 1:30:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,53437,50030,50011,TO_TIMESTAMP('2012-08-31 13:30:12','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',10,'supply',TO_TIMESTAMP('2012-08-31 13:30:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64256,0,51198,50030,50011,'SUPPLY_AD_Client_ID','supply.AD_Client_ID',TO_TIMESTAMP('2012-08-31 13:30:13','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2012-08-31 13:30:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64257,0,51199,50030,50011,'SUPPLY_AD_Org_ID','supply.AD_Org_ID',TO_TIMESTAMP('2012-08-31 13:30:13','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2012-08-31 13:30:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64259,0,51200,50030,50011,'SUPPLY_Created','supply.Created',TO_TIMESTAMP('2012-08-31 13:30:13','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2012-08-31 13:30:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64261,0,51201,50030,50011,'SUPPLY_CreatedBy','supply.CreatedBy',TO_TIMESTAMP('2012-08-31 13:30:14','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2012-08-31 13:30:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64258,0,51202,50030,50011,'SUPPLY_IsActive','supply.IsActive',TO_TIMESTAMP('2012-08-31 13:30:14','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2012-08-31 13:30:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64260,0,51203,50030,50011,'SUPPLY_Updated','supply.Updated',TO_TIMESTAMP('2012-08-31 13:30:15','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2012-08-31 13:30:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64262,0,51204,50030,50011,'SUPPLY_UpdatedBy','supply.UpdatedBy',TO_TIMESTAMP('2012-08-31 13:30:15','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2012-08-31 13:30:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse (AD_Browse_ID,AD_Client_ID,AD_Org_ID,AD_View_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Processing,Updated,UpdatedBy,Value,WhereClause) VALUES (50012,0,0,50011,'3',TO_TIMESTAMP('2012-08-31 13:30:15','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supplies Browse','EE01','This browse allows to display supplies, to be able to track each supply of every demand.

<p>To know how demands is satisfied , it is possible to select and press the zoom icon to display the MRP Supplies View, with supply details and their demands.','Y','MRP Supplies Browse','N',TO_TIMESTAMP('2012-08-31 13:30:15','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supplies Browse',NULL)
;

-- Aug 31, 2012 1:30:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Trl (AD_Language,AD_Browse_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_ID=50012 AND NOT EXISTS (SELECT * FROM AD_Browse_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_ID=t.AD_Browse_ID)
;

-- Aug 31, 2012 1:30:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51472,50012,0,102,0,19,51198,TO_TIMESTAMP('2012-08-31 13:30:16','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','N','N','Y','Client',0,0,TO_TIMESTAMP('2012-08-31 13:30:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51472 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51473,50012,0,113,0,19,51199,TO_TIMESTAMP('2012-08-31 13:30:16','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','N','N','Y','Organization',0,0,TO_TIMESTAMP('2012-08-31 13:30:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51473 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51474,50012,0,245,0,16,51200,TO_TIMESTAMP('2012-08-31 13:30:17','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','N','N','N','Y','Created',0,0,TO_TIMESTAMP('2012-08-31 13:30:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51474 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51475,50012,0,246,0,18,286,51201,TO_TIMESTAMP('2012-08-31 13:30:17','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','N','N','N','Y','Created By',0,0,TO_TIMESTAMP('2012-08-31 13:30:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51475 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51476,50012,0,348,0,20,51202,TO_TIMESTAMP('2012-08-31 13:30:17','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','N','N','Y','Active',0,0,TO_TIMESTAMP('2012-08-31 13:30:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51476 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51477,50012,0,607,0,16,51203,TO_TIMESTAMP('2012-08-31 13:30:18','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','N','N','N','Y','Updated',0,0,TO_TIMESTAMP('2012-08-31 13:30:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51477 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51478,50012,0,608,0,18,286,51204,TO_TIMESTAMP('2012-08-31 13:30:18','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','N','N','N','Y','Updated By',0,0,TO_TIMESTAMP('2012-08-31 13:30:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51478 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:30:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Table SET AD_Window_ID=53202, AccessLevel='3', Description='MRP Demand View', EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='Y', Name='MRP Demand View', ReplicationType='L', TableName='RV_PP_MRP_Demand',Updated=TO_TIMESTAMP('2012-08-31 13:30:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53436
;

-- Aug 31, 2012 1:30:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64301,53316,0,19,53436,'PP_MRP_ID',TO_TIMESTAMP('2012-08-31 13:30:21','YYYY-MM-DD HH24:MI:SS'),100,'MRP ID','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','N','Material Requirement Planning',TO_TIMESTAMP('2012-08-31 13:30:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64301 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64302,290,0,10,53436,'DocumentNo',TO_TIMESTAMP('2012-08-31 13:30:22','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01',40,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','Y','N','N','N','Y','Y','N','N','Document No',1,TO_TIMESTAMP('2012-08-31 13:30:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64302 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:23 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64303,52020,0,17,53229,53436,'OrderType',TO_TIMESTAMP('2012-08-31 13:30:22','YYYY-MM-DD HH24:MI:SS'),100,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)','EE01',1,'Y','N','N','N','N','N','N','N','Y','N','N','Order Type',TO_TIMESTAMP('2012-08-31 13:30:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:23 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64303 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:23 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64304,53269,0,18,286,53436,'Planner_ID',TO_TIMESTAMP('2012-08-31 13:30:23','YYYY-MM-DD HH24:MI:SS'),100,'Company Agent for Planning','EE01',22,'The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','N','Planner',TO_TIMESTAMP('2012-08-31 13:30:23','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:23 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64304 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64305,1777,0,18,53320,53436,52002,'S_Resource_ID',TO_TIMESTAMP('2012-08-31 13:30:23','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Resource',TO_TIMESTAMP('2012-08-31 13:30:23','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64305 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64306,459,0,19,53436,'M_Warehouse_ID',TO_TIMESTAMP('2012-08-31 13:30:24','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','Y','N','N','Warehouse',TO_TIMESTAMP('2012-08-31 13:30:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64306 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64307,269,0,16,53436,'DatePromised',TO_TIMESTAMP('2012-08-31 13:30:24','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01',35,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','Y','N','N','N','Y','Y','N','N','Date Promised',2,TO_TIMESTAMP('2012-08-31 13:30:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64307 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64308,1514,0,17,154,53436,'Priority',TO_TIMESTAMP('2012-08-31 13:30:24','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this request is of a high, medium or low priority.','EE01',1,'The Priority indicates the importance of this request.','Y','N','N','N','N','N','N','N','Y','N','N','Priority',TO_TIMESTAMP('2012-08-31 13:30:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64308 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64309,454,0,19,53436,'M_Product_ID',TO_TIMESTAMP('2012-08-31 13:30:25','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','Y','N','N','N','Y','Y','N','N','Product',3,TO_TIMESTAMP('2012-08-31 13:30:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64309 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64310,549,0,10,53436,'SKU',TO_TIMESTAMP('2012-08-31 13:30:25','YYYY-MM-DD HH24:MI:SS'),100,'Stock Keeping Unit','EE01',30,'The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','N','N','N','N','N','N','N','Y','N','N','SKU',TO_TIMESTAMP('2012-08-31 13:30:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64310 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64311,215,0,19,53436,'C_UOM_ID',TO_TIMESTAMP('2012-08-31 13:30:26','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','Y','N','N','UOM',TO_TIMESTAMP('2012-08-31 13:30:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64311 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64312,453,0,19,163,53436,'M_Product_Category_ID',TO_TIMESTAMP('2012-08-31 13:30:28','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','EE01',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','Y','N','N','Product Category',TO_TIMESTAMP('2012-08-31 13:30:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64312 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64313,1326,0,20,53436,'IsBOM',TO_TIMESTAMP('2012-08-31 13:30:28','YYYY-MM-DD HH24:MI:SS'),100,'Bill of Materials','EE01',1,'The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','N','N','N','N','N','N','N','Y','N','N','Bill of Materials',TO_TIMESTAMP('2012-08-31 13:30:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64313 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64314,403,0,20,53436,'IsPurchased',TO_TIMESTAMP('2012-08-31 13:30:28','YYYY-MM-DD HH24:MI:SS'),100,'Organization purchases this product','EE01',1,'The Purchased check box indicates if this product is purchased by this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Purchased',TO_TIMESTAMP('2012-08-31 13:30:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64314 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64315,526,0,29,53436,'Qty',TO_TIMESTAMP('2012-08-31 13:30:29','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','N','Quantity',TO_TIMESTAMP('2012-08-31 13:30:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64315 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64316,289,0,17,131,53436,'DocStatus',TO_TIMESTAMP('2012-08-31 13:30:29','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','Y','N','N','Document Status',TO_TIMESTAMP('2012-08-31 13:30:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64316 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64317,187,0,19,53436,'C_BPartner_ID',TO_TIMESTAMP('2012-08-31 13:30:30','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE01',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','N','Business Partner ',TO_TIMESTAMP('2012-08-31 13:30:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64317 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64318,268,0,16,53436,'DateOrdered',TO_TIMESTAMP('2012-08-31 13:30:30','YYYY-MM-DD HH24:MI:SS'),100,'Date of Order','EE01',35,'Indicates the Date an item was ordered.','Y','N','N','N','N','N','N','N','Y','N','N','Date Ordered',TO_TIMESTAMP('2012-08-31 13:30:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64318 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64319,53261,0,20,53436,'IsMPS',TO_TIMESTAMP('2012-08-31 13:30:31','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this product is part of the master production schedule','EE01',1,'The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.','Y','N','N','N','N','N','N','Y','Y','N','N','Is MPS',TO_TIMESTAMP('2012-08-31 13:30:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64319 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64320,53262,0,20,53436,'IsRequiredMRP',TO_TIMESTAMP('2012-08-31 13:30:31','YYYY-MM-DD HH24:MI:SS'),100,'Is MRP Required','EE01',1,'If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','N','N','N','N','N','N','N','Y','N','N','Is MRP Required',TO_TIMESTAMP('2012-08-31 13:30:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64320 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64321,53470,0,20,53436,'IsRequiredDRP',TO_TIMESTAMP('2012-08-31 13:30:31','YYYY-MM-DD HH24:MI:SS'),100,'Is DRP Required','EE01',1,'If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','N','N','N','N','N','N','N','Y','N','N','Is DRP Required',TO_TIMESTAMP('2012-08-31 13:30:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64321 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64322,414,0,20,53436,'IsSold',TO_TIMESTAMP('2012-08-31 13:30:32','YYYY-MM-DD HH24:MI:SS'),100,'Organization sells this product','EE01',1,'The Sold check box indicates if this product is sold by this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Sold',TO_TIMESTAMP('2012-08-31 13:30:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64322 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:33 PM CDT
-- MFG-38 MRP Multi-Level Pegging
UPDATE AD_Table SET AD_Window_ID=53203, AccessLevel='3', Description='MRP Supply View', EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='Y', Name='MRP Supply View', ReplicationType='L', TableName='RV_PP_MRP_Supply',Updated=TO_TIMESTAMP('2012-08-31 13:30:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53437
;

-- Aug 31, 2012 1:30:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64323,53316,0,19,53437,'PP_MRP_ID',TO_TIMESTAMP('2012-08-31 13:30:35','YYYY-MM-DD HH24:MI:SS'),100,'MRP ID','EE01',131089,'Y','N','N','N','N','N','N','N','Y','N','N','Material Requirement Planning',TO_TIMESTAMP('2012-08-31 13:30:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64323 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64324,290,0,10,53437,'DocumentNo',TO_TIMESTAMP('2012-08-31 13:30:36','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01',30,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','Y','Y','N','N','Document No',TO_TIMESTAMP('2012-08-31 13:30:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64324 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64325,52020,0,17,53229,53437,'OrderType',TO_TIMESTAMP('2012-08-31 13:30:36','YYYY-MM-DD HH24:MI:SS'),100,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)','EE01',3,'Y','N','N','N','N','N','N','N','Y','N','N','Order Type',TO_TIMESTAMP('2012-08-31 13:30:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64325 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64326,289,0,17,131,53437,'DocStatus',TO_TIMESTAMP('2012-08-31 13:30:36','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','Y','N','N','Document Status',TO_TIMESTAMP('2012-08-31 13:30:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64326 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64327,187,0,19,53437,'C_BPartner_ID',TO_TIMESTAMP('2012-08-31 13:30:37','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE01',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','N','Business Partner ',TO_TIMESTAMP('2012-08-31 13:30:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64327 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64328,53269,0,18,286,53437,'Planner_ID',TO_TIMESTAMP('2012-08-31 13:30:37','YYYY-MM-DD HH24:MI:SS'),100,'Company Agent for Planning','EE01',22,'The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','N','Planner',TO_TIMESTAMP('2012-08-31 13:30:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64328 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64329,1777,0,18,53320,53437,52002,'S_Resource_ID',TO_TIMESTAMP('2012-08-31 13:30:38','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Resource',TO_TIMESTAMP('2012-08-31 13:30:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64329 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64330,459,0,19,53437,'M_Warehouse_ID',TO_TIMESTAMP('2012-08-31 13:30:38','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','Y','N','N','Warehouse',TO_TIMESTAMP('2012-08-31 13:30:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64330 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64331,268,0,16,53437,'DateOrdered',TO_TIMESTAMP('2012-08-31 13:30:39','YYYY-MM-DD HH24:MI:SS'),100,'Date of Order','EE01',35,'Indicates the Date an item was ordered.','Y','N','N','N','N','N','N','N','Y','N','N','Date Ordered',TO_TIMESTAMP('2012-08-31 13:30:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64331 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64332,269,0,16,53437,'DatePromised',TO_TIMESTAMP('2012-08-31 13:30:39','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01',35,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','N','N','N','N','N','Y','N','N','Date Promised',TO_TIMESTAMP('2012-08-31 13:30:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64332 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64333,1514,0,17,154,53437,'Priority',TO_TIMESTAMP('2012-08-31 13:30:39','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this request is of a high, medium or low priority.','EE01',1,'The Priority indicates the importance of this request.','Y','N','N','N','N','N','N','N','Y','N','N','Priority',TO_TIMESTAMP('2012-08-31 13:30:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64333 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64334,454,0,19,53437,'M_Product_ID',TO_TIMESTAMP('2012-08-31 13:30:40','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Product',TO_TIMESTAMP('2012-08-31 13:30:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64334 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64335,549,0,10,53437,'SKU',TO_TIMESTAMP('2012-08-31 13:30:40','YYYY-MM-DD HH24:MI:SS'),100,'Stock Keeping Unit','EE01',30,'The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','N','N','N','N','N','N','N','Y','N','N','SKU',TO_TIMESTAMP('2012-08-31 13:30:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64335 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64336,215,0,19,53437,'C_UOM_ID',TO_TIMESTAMP('2012-08-31 13:30:41','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','Y','N','N','UOM',TO_TIMESTAMP('2012-08-31 13:30:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64336 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64337,414,0,20,53437,'IsSold',TO_TIMESTAMP('2012-08-31 13:30:41','YYYY-MM-DD HH24:MI:SS'),100,'Organization sells this product','EE01',1,'The Sold check box indicates if this product is sold by this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Sold',TO_TIMESTAMP('2012-08-31 13:30:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64337 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64338,453,0,19,163,53437,'M_Product_Category_ID',TO_TIMESTAMP('2012-08-31 13:30:42','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','EE01',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','Y','N','N','Product Category',TO_TIMESTAMP('2012-08-31 13:30:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64338 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64339,1326,0,20,53437,'IsBOM',TO_TIMESTAMP('2012-08-31 13:30:42','YYYY-MM-DD HH24:MI:SS'),100,'Bill of Materials','EE01',1,'The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','N','N','N','N','N','N','N','Y','N','N','Bill of Materials',TO_TIMESTAMP('2012-08-31 13:30:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64339 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64340,403,0,20,53437,'IsPurchased',TO_TIMESTAMP('2012-08-31 13:30:42','YYYY-MM-DD HH24:MI:SS'),100,'Organization purchases this product','EE01',1,'The Purchased check box indicates if this product is purchased by this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Purchased',TO_TIMESTAMP('2012-08-31 13:30:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64340 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64341,526,0,29,53437,'Qty',TO_TIMESTAMP('2012-08-31 13:30:43','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','N','Quantity',TO_TIMESTAMP('2012-08-31 13:30:43','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64341 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:44 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64342,53261,0,20,53437,'IsMPS',TO_TIMESTAMP('2012-08-31 13:30:43','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this product is part of the master production schedule','EE01',1,'The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.','Y','N','N','N','N','N','N','N','Y','N','N','Is MPS',TO_TIMESTAMP('2012-08-31 13:30:43','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:44 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64342 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:44 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64343,53262,0,20,53437,'IsRequiredMRP',TO_TIMESTAMP('2012-08-31 13:30:44','YYYY-MM-DD HH24:MI:SS'),100,'Is MRP Required','EE01',1,'If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','N','N','N','N','N','N','N','Y','N','N','Is MRP Required',TO_TIMESTAMP('2012-08-31 13:30:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:44 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64343 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:44 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64344,53470,0,20,53437,'IsRequiredDRP',TO_TIMESTAMP('2012-08-31 13:30:44','YYYY-MM-DD HH24:MI:SS'),100,'Is DRP Required','EE01',1,'If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','N','N','N','N','N','N','N','Y','N','N','Is DRP Required',TO_TIMESTAMP('2012-08-31 13:30:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 31, 2012 1:30:44 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64344 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 31, 2012 1:30:45 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64305,65240,0,53567,TO_TIMESTAMP('2012-08-31 13:30:44','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Resource',10,'EE01','N','Y','Y','Y','N','N','N','N','Resource',0,30,0,TO_TIMESTAMP('2012-08-31 13:30:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:45 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65240 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:45 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64306,65241,0,53567,TO_TIMESTAMP('2012-08-31 13:30:45','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Storage Warehouse and Service Point',10,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','N','Y','Y','Y','N','N','N','Y','Warehouse',0,40,0,TO_TIMESTAMP('2012-08-31 13:30:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:45 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65241 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:46 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64302,125,65242,0,53567,TO_TIMESTAMP('2012-08-31 13:30:45','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Document sequence number of the document',22,'EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','N','Y','Y','Y','N','N','N','N','Document No',0,50,0,TO_TIMESTAMP('2012-08-31 13:30:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:46 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65242 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:46 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64303,125,65243,0,53567,TO_TIMESTAMP('2012-08-31 13:30:46','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)',2147483647,'EE01','N','Y','Y','Y','N','N','N','Y','Order Type',0,60,0,TO_TIMESTAMP('2012-08-31 13:30:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:46 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65243 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64316,125,65244,0,53567,TO_TIMESTAMP('2012-08-31 13:30:46','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The current status of the document',2,'EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','N','Y','Y','Y','N','N','N','Y','Document Status',0,70,0,TO_TIMESTAMP('2012-08-31 13:30:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65244 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64308,125,65245,0,53567,TO_TIMESTAMP('2012-08-31 13:30:47','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Indicates if this request is of a high, medium or low priority.',22,'EE01','The Priority indicates the importance of this request.','N','Y','Y','Y','N','N','N','Y','Priority',0,80,0,TO_TIMESTAMP('2012-08-31 13:30:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65245 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64317,125,65246,0,53567,TO_TIMESTAMP('2012-08-31 13:30:47','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Identifies a Business Partner',22,'EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','N','Y','Y','Y','N','N','N','N','Business Partner ',0,90,0,TO_TIMESTAMP('2012-08-31 13:30:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:47 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65246 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:48 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64304,125,65247,0,53567,TO_TIMESTAMP('2012-08-31 13:30:47','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Company Agent for Planning',131089,'EE01','The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','N','Y','Y','Y','N','N','N','Y','Planner',0,100,0,TO_TIMESTAMP('2012-08-31 13:30:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:48 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65247 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:48 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64318,125,65248,0,53567,TO_TIMESTAMP('2012-08-31 13:30:48','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date of Order',7,'EE01','Indicates the Date an item was ordered.','N','Y','Y','Y','N','N','N','N','Date Ordered',0,110,0,TO_TIMESTAMP('2012-08-31 13:30:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:48 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65248 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64307,125,65249,0,53567,TO_TIMESTAMP('2012-08-31 13:30:48','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date Order was promised',7,'EE01','The Date Promised indicates the date, if any, that an Order was promised for.','N','Y','Y','Y','N','N','N','Y','Date Promised',0,120,0,TO_TIMESTAMP('2012-08-31 13:30:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65249 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64309,106,65250,0,53567,TO_TIMESTAMP('2012-08-31 13:30:49','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Product, Service, Item',10,'EE01','Identifies an item which is either purchased or sold in this organization.','N','Y','Y','Y','N','N','N','N','Product',0,130,0,TO_TIMESTAMP('2012-08-31 13:30:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65250 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:49 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64311,106,65251,0,53567,TO_TIMESTAMP('2012-08-31 13:30:49','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Unit of Measure',10,'EE01','The UOM defines a unique non monetary Unit of Measure','N','Y','Y','Y','N','N','N','Y','UOM',0,140,0,TO_TIMESTAMP('2012-08-31 13:30:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65251 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64312,106,65252,0,53567,TO_TIMESTAMP('2012-08-31 13:30:50','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Category of a Product',10,'EE01','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','N','Y','Y','Y','N','N','N','N','Product Category',0,150,0,TO_TIMESTAMP('2012-08-31 13:30:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65252 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64310,106,65253,0,53567,TO_TIMESTAMP('2012-08-31 13:30:50','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Stock Keeping Unit',30,'EE01','The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','N','Y','Y','Y','N','N','N','Y','SKU',0,160,0,TO_TIMESTAMP('2012-08-31 13:30:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:50 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65253 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:51 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64322,106,65254,0,53567,TO_TIMESTAMP('2012-08-31 13:30:50','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organization sells this product',1,'EE01','The Sold check box indicates if this product is sold by this organization.','N','Y','Y','Y','N','N','N','N','Sold',0,170,0,TO_TIMESTAMP('2012-08-31 13:30:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:51 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65254 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:51 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64314,106,65255,0,53567,TO_TIMESTAMP('2012-08-31 13:30:51','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organization purchases this product',1,'EE01','The Purchased check box indicates if this product is purchased by this organization.','N','Y','Y','Y','N','N','N','Y','Purchased',0,180,0,TO_TIMESTAMP('2012-08-31 13:30:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:51 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65255 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64319,106,65256,0,53567,TO_TIMESTAMP('2012-08-31 13:30:51','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Indicates if this product is part of the master production schedule',1,'EE01','The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.','N','Y','Y','Y','N','N','N','N','Is MPS',0,190,0,TO_TIMESTAMP('2012-08-31 13:30:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65256 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64313,106,65257,0,53567,TO_TIMESTAMP('2012-08-31 13:30:52','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Bill of Materials',1,'EE01','The Bill of Materials check box indicates if this product consists of a bill of materials.','N','Y','Y','Y','N','N','N','Y','Bill of Materials',0,200,0,TO_TIMESTAMP('2012-08-31 13:30:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65257 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64320,106,65258,0,53567,TO_TIMESTAMP('2012-08-31 13:30:52','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Is MRP Required',1,'EE01','If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','N','Y','Y','Y','N','N','N','N','Is MRP Required',0,210,0,TO_TIMESTAMP('2012-08-31 13:30:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:52 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65258 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:53 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64321,106,65259,0,53567,TO_TIMESTAMP('2012-08-31 13:30:52','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Is DRP Required',1,'EE01','If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','N','Y','Y','Y','N','N','N','Y','Is DRP Required',0,220,0,TO_TIMESTAMP('2012-08-31 13:30:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:53 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65259 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:53 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64315,106,65260,0,53567,TO_TIMESTAMP('2012-08-31 13:30:53','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Quantity',22,'EE01','The Quantity indicates the number of a specific product or item for this document.','N','Y','Y','Y','N','N','N','N','Quantity',0,230,0,TO_TIMESTAMP('2012-08-31 13:30:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:53 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65260 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:54 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,Included_Tab_ID,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64301,65261,0,53567,TO_TIMESTAMP('2012-08-31 13:30:53','YYYY-MM-DD HH24:MI:SS'),100,NULL,'MRP ID',22,'EE01','N',53568,'Y','Y','Y','N','N','N','N','Material Requirement Planning',0,240,0,TO_TIMESTAMP('2012-08-31 13:30:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:54 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65261 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:54 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64329,65262,0,53569,TO_TIMESTAMP('2012-08-31 13:30:54','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Resource',10,'EE01','N','Y','Y','Y','N','N','N','N','Resource',0,30,0,TO_TIMESTAMP('2012-08-31 13:30:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:54 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65262 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:55 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64330,65263,0,53569,TO_TIMESTAMP('2012-08-31 13:30:54','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Storage Warehouse and Service Point',10,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','N','Y','Y','Y','N','N','N','Y','Warehouse',0,40,0,TO_TIMESTAMP('2012-08-31 13:30:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:55 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65263 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:55 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64324,125,65264,0,53569,TO_TIMESTAMP('2012-08-31 13:30:55','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Document sequence number of the document',22,'EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','N','Y','Y','Y','N','N','N','N','Document No',0,50,0,TO_TIMESTAMP('2012-08-31 13:30:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:55 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65264 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:55 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64325,125,65265,0,53569,TO_TIMESTAMP('2012-08-31 13:30:55','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)',3,'EE01','N','Y','Y','Y','N','N','N','Y','Order Type',0,60,0,TO_TIMESTAMP('2012-08-31 13:30:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:55 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65265 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64326,125,65266,0,53569,TO_TIMESTAMP('2012-08-31 13:30:55','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The current status of the document',2,'EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','N','Y','Y','Y','N','N','N','N','Document Status',0,70,0,TO_TIMESTAMP('2012-08-31 13:30:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65266 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64333,125,65267,0,53569,TO_TIMESTAMP('2012-08-31 13:30:56','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Indicates if this request is of a high, medium or low priority.',1,'EE01','The Priority indicates the importance of this request.','N','Y','Y','Y','N','N','N','Y','Priority',0,80,0,TO_TIMESTAMP('2012-08-31 13:30:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:56 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65267 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64327,125,65268,0,53569,TO_TIMESTAMP('2012-08-31 13:30:56','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Identifies a Business Partner',22,'EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','N','Y','Y','Y','N','N','N','N','Business Partner ',0,90,0,TO_TIMESTAMP('2012-08-31 13:30:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65268 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64328,125,65269,0,53569,TO_TIMESTAMP('2012-08-31 13:30:57','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Company Agent for Planning',22,'EE01','The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','N','Y','Y','Y','N','N','N','Y','Planner',0,100,0,TO_TIMESTAMP('2012-08-31 13:30:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:57 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65269 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64331,125,65270,0,53569,TO_TIMESTAMP('2012-08-31 13:30:57','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date of Order',7,'EE01','Indicates the Date an item was ordered.','N','Y','Y','Y','N','N','N','N','Date Ordered',0,110,0,TO_TIMESTAMP('2012-08-31 13:30:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65270 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64332,125,65271,0,53569,TO_TIMESTAMP('2012-08-31 13:30:58','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date Order was promised',7,'EE01','The Date Promised indicates the date, if any, that an Order was promised for.','N','Y','Y','Y','N','N','N','Y','Date Promised',0,120,0,TO_TIMESTAMP('2012-08-31 13:30:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65271 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64334,106,65272,0,53569,TO_TIMESTAMP('2012-08-31 13:30:58','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Product, Service, Item',10,'EE01','Identifies an item which is either purchased or sold in this organization.','N','Y','Y','Y','N','N','N','N','Product',0,130,0,TO_TIMESTAMP('2012-08-31 13:30:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:58 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65272 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64336,106,65273,0,53569,TO_TIMESTAMP('2012-08-31 13:30:58','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Unit of Measure',10,'EE01','The UOM defines a unique non monetary Unit of Measure','N','Y','Y','Y','N','N','N','Y','UOM',0,140,0,TO_TIMESTAMP('2012-08-31 13:30:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65273 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:30:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64338,106,65274,0,53569,TO_TIMESTAMP('2012-08-31 13:30:59','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Category of a Product',10,'EE01','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','N','Y','Y','Y','N','N','N','N','Product Category',0,150,0,TO_TIMESTAMP('2012-08-31 13:30:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:30:59 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65274 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64335,106,65275,0,53569,TO_TIMESTAMP('2012-08-31 13:30:59','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Stock Keeping Unit',22,'EE01','The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','N','Y','Y','Y','N','N','N','Y','SKU',0,160,0,TO_TIMESTAMP('2012-08-31 13:30:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65275 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64337,106,65276,0,53569,TO_TIMESTAMP('2012-08-31 13:31:00','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organization sells this product',1,'EE01','The Sold check box indicates if this product is sold by this organization.','N','Y','Y','Y','N','N','N','N','Sold',0,170,0,TO_TIMESTAMP('2012-08-31 13:31:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:00 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65276 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64340,106,65277,0,53569,TO_TIMESTAMP('2012-08-31 13:31:00','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organization purchases this product',1,'EE01','The Purchased check box indicates if this product is purchased by this organization.','N','Y','Y','Y','N','N','N','Y','Purchased',0,180,0,TO_TIMESTAMP('2012-08-31 13:31:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65277 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64342,106,65278,0,53569,TO_TIMESTAMP('2012-08-31 13:31:01','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Indicates if this product is part of the master production schedule',1,'EE01','The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.','N','Y','Y','Y','N','N','N','N','Is MPS',0,190,0,TO_TIMESTAMP('2012-08-31 13:31:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65278 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64339,106,65279,0,53569,TO_TIMESTAMP('2012-08-31 13:31:01','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Bill of Materials',1,'EE01','The Bill of Materials check box indicates if this product consists of a bill of materials.','N','Y','Y','Y','N','N','N','Y','Bill of Materials',0,200,0,TO_TIMESTAMP('2012-08-31 13:31:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:01 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65279 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64343,106,65280,0,53569,TO_TIMESTAMP('2012-08-31 13:31:01','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Is MRP Required',1,'EE01','If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','N','Y','Y','Y','N','N','N','N','Is MRP Required',0,210,0,TO_TIMESTAMP('2012-08-31 13:31:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65280 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64344,125,65281,0,53569,TO_TIMESTAMP('2012-08-31 13:31:02','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Is DRP Required',1,'EE01','If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','N','Y','Y','Y','N','N','N','Y','Is DRP Required',0,220,0,TO_TIMESTAMP('2012-08-31 13:31:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:02 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65281 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64341,65282,0,53569,TO_TIMESTAMP('2012-08-31 13:31:02','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Quantity',22,'EE01','The Quantity indicates the number of a specific product or item for this document.','N','Y','Y','Y','N','N','N','N','Quantity',0,230,0,TO_TIMESTAMP('2012-08-31 13:31:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65282 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,Included_Tab_ID,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,64323,65283,0,53569,TO_TIMESTAMP('2012-08-31 13:31:03','YYYY-MM-DD HH24:MI:SS'),100,NULL,'MRP ID',131089,'EE01','N',53570,'Y','Y','Y','N','N','N','N','Material Requirement Planning',0,240,0,TO_TIMESTAMP('2012-08-31 13:31:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65283 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 31, 2012 1:31:03 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64317,0,51205,50029,50010,'DEMAND_C_BPartner_ID','demand.C_BPartner_ID',TO_TIMESTAMP('2012-08-31 13:31:03','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_TIMESTAMP('2012-08-31 13:31:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64311,0,51206,50029,50010,'DEMAND_C_UOM_ID','demand.C_UOM_ID',TO_TIMESTAMP('2012-08-31 13:31:04','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','EE01','The UOM defines a unique non monetary Unit of Measure','Y','UOM',TO_TIMESTAMP('2012-08-31 13:31:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:04 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64318,0,51207,50029,50010,'DEMAND_DateOrdered','demand.DateOrdered',TO_TIMESTAMP('2012-08-31 13:31:04','YYYY-MM-DD HH24:MI:SS'),100,'Date of Order','EE01','Indicates the Date an item was ordered.','Y','Date Ordered',TO_TIMESTAMP('2012-08-31 13:31:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64307,0,51208,50029,50010,'DEMAND_DatePromised','demand.DatePromised',TO_TIMESTAMP('2012-08-31 13:31:04','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Date Promised',TO_TIMESTAMP('2012-08-31 13:31:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:05 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64316,0,51209,50029,50010,'DEMAND_DocStatus','demand.DocStatus',TO_TIMESTAMP('2012-08-31 13:31:05','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Document Status',TO_TIMESTAMP('2012-08-31 13:31:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64302,0,51210,50029,50010,'DEMAND_DocumentNo','demand.DocumentNo',TO_TIMESTAMP('2012-08-31 13:31:05','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Document No',TO_TIMESTAMP('2012-08-31 13:31:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64313,0,51211,50029,50010,'DEMAND_IsBOM','demand.IsBOM',TO_TIMESTAMP('2012-08-31 13:31:06','YYYY-MM-DD HH24:MI:SS'),100,'Bill of Materials','EE01','The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','Bill of Materials',TO_TIMESTAMP('2012-08-31 13:31:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:06 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64319,0,51212,50029,50010,'DEMAND_IsMPS','demand.IsMPS',TO_TIMESTAMP('2012-08-31 13:31:06','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this product is part of the master production schedule','EE01','The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.','Y','Is MPS',TO_TIMESTAMP('2012-08-31 13:31:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64314,0,51213,50029,50010,'DEMAND_IsPurchased','demand.IsPurchased',TO_TIMESTAMP('2012-08-31 13:31:06','YYYY-MM-DD HH24:MI:SS'),100,'Organization purchases this product','EE01','The Purchased check box indicates if this product is purchased by this organization.','Y','Purchased',TO_TIMESTAMP('2012-08-31 13:31:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:07 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64321,0,51214,50029,50010,'DEMAND_IsRequiredDRP','demand.IsRequiredDRP',TO_TIMESTAMP('2012-08-31 13:31:07','YYYY-MM-DD HH24:MI:SS'),100,'Is DRP Required','EE01','If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Is DRP Required',TO_TIMESTAMP('2012-08-31 13:31:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64320,0,51215,50029,50010,'DEMAND_IsRequiredMRP','demand.IsRequiredMRP',TO_TIMESTAMP('2012-08-31 13:31:07','YYYY-MM-DD HH24:MI:SS'),100,'Is MRP Required','EE01','If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Is MRP Required',TO_TIMESTAMP('2012-08-31 13:31:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64322,0,51216,50029,50010,'DEMAND_IsSold','demand.IsSold',TO_TIMESTAMP('2012-08-31 13:31:08','YYYY-MM-DD HH24:MI:SS'),100,'Organization sells this product','EE01','The Sold check box indicates if this product is sold by this organization.','Y','Sold',TO_TIMESTAMP('2012-08-31 13:31:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:08 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64312,0,51217,50029,50010,'DEMAND_M_Product_Category_ID','demand.M_Product_Category_ID',TO_TIMESTAMP('2012-08-31 13:31:08','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','EE01','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Product Category',TO_TIMESTAMP('2012-08-31 13:31:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64309,0,51218,50029,50010,'DEMAND_M_Product_ID','demand.M_Product_ID',TO_TIMESTAMP('2012-08-31 13:31:08','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01','Identifies an item which is either purchased or sold in this organization.','Y','Product',TO_TIMESTAMP('2012-08-31 13:31:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:09 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64306,0,51219,50029,50010,'DEMAND_M_Warehouse_ID','demand.M_Warehouse_ID',TO_TIMESTAMP('2012-08-31 13:31:09','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_TIMESTAMP('2012-08-31 13:31:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64303,0,51220,50029,50010,'DEMAND_OrderType','demand.OrderType',TO_TIMESTAMP('2012-08-31 13:31:09','YYYY-MM-DD HH24:MI:SS'),100,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)','EE01','Y','Order Type',TO_TIMESTAMP('2012-08-31 13:31:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:10 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64301,0,51221,50029,50010,'DEMAND_PP_MRP_ID','demand.PP_MRP_ID',TO_TIMESTAMP('2012-08-31 13:31:10','YYYY-MM-DD HH24:MI:SS'),100,'MRP ID','EE01','Y','Material Requirement Planning',TO_TIMESTAMP('2012-08-31 13:31:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64304,0,51222,50029,50010,'DEMAND_Planner_ID','demand.Planner_ID',TO_TIMESTAMP('2012-08-31 13:31:10','YYYY-MM-DD HH24:MI:SS'),100,'Company Agent for Planning','EE01','The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','Y','Planner',TO_TIMESTAMP('2012-08-31 13:31:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64308,0,51223,50029,50010,'DEMAND_Priority','demand.Priority',TO_TIMESTAMP('2012-08-31 13:31:11','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this request is of a high, medium or low priority.','EE01','The Priority indicates the importance of this request.','Y','Priority',TO_TIMESTAMP('2012-08-31 13:31:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:11 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64315,0,51224,50029,50010,'DEMAND_Qty','demand.Qty',TO_TIMESTAMP('2012-08-31 13:31:11','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01','The Quantity indicates the number of a specific product or item for this document.','Y','Quantity',TO_TIMESTAMP('2012-08-31 13:31:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64310,0,51225,50029,50010,'DEMAND_SKU','demand.SKU',TO_TIMESTAMP('2012-08-31 13:31:11','YYYY-MM-DD HH24:MI:SS'),100,'Stock Keeping Unit','EE01','The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','SKU',TO_TIMESTAMP('2012-08-31 13:31:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:12 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64305,0,51226,50029,50010,'DEMAND_S_Resource_ID','demand.S_Resource_ID',TO_TIMESTAMP('2012-08-31 13:31:12','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01','Y','Resource',TO_TIMESTAMP('2012-08-31 13:31:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51479,50011,0,53269,0,18,286,51222,TO_TIMESTAMP('2012-08-31 13:31:12','YYYY-MM-DD HH24:MI:SS'),100,'Company Agent for Planning','EE01','The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','Y','Y','Y','N','N','N','N','Y','N','Y','Planner',50,0,TO_TIMESTAMP('2012-08-31 13:31:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51479 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51480,50011,0,549,0,10,51225,TO_TIMESTAMP('2012-08-31 13:31:13','YYYY-MM-DD HH24:MI:SS'),100,'Stock Keeping Unit','EE01','The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','Y','Y','N','N','N','N','N','N','Y','SKU',130,0,TO_TIMESTAMP('2012-08-31 13:31:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:13 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51480 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51481,50011,0,52020,0,17,53229,51220,TO_TIMESTAMP('2012-08-31 13:31:13','YYYY-MM-DD HH24:MI:SS'),100,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)','EE01','Y','Y','Y','N','N','N','N','Y','N','Y','Order Type',40,0,TO_TIMESTAMP('2012-08-31 13:31:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51481 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51482,50011,0,526,0,29,51224,TO_TIMESTAMP('2012-08-31 13:31:14','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01','The Quantity indicates the number of a specific product or item for this document.','Y','Y','Y','N','N','N','N','N','N','Y','Quantity',200,0,TO_TIMESTAMP('2012-08-31 13:31:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51482 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51483,50011,0,1777,0,18,53320,51226,TO_TIMESTAMP('2012-08-31 13:31:14','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01','Y','Y','Y','N','N','N','N','Y','N','Y','Resource',70,0,TO_TIMESTAMP('2012-08-31 13:31:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:14 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51483 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51484,50011,0,215,0,19,51206,TO_TIMESTAMP('2012-08-31 13:31:14','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','EE01','The UOM defines a unique non monetary Unit of Measure','Y','Y','Y','N','N','N','N','N','N','Y','UOM',150,0,TO_TIMESTAMP('2012-08-31 13:31:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51484 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51485,50011,0,269,0,16,51208,TO_TIMESTAMP('2012-08-31 13:31:15','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','Y','Y','N','N','N','Y','Y','Y','Date Promised',100,0,TO_TIMESTAMP('2012-08-31 13:31:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:15 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51485 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51486,50011,0,459,0,19,51219,TO_TIMESTAMP('2012-08-31 13:31:15','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','N','N','N','Y','N','Y','Warehouse',80,0,TO_TIMESTAMP('2012-08-31 13:31:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:16 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51486 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51487,50011,0,187,0,19,51205,TO_TIMESTAMP('2012-08-31 13:31:16','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','Y','N','Y','Business Partner ',60,0,TO_TIMESTAMP('2012-08-31 13:31:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51487 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51488,50011,0,403,0,20,51213,TO_TIMESTAMP('2012-08-31 13:31:17','YYYY-MM-DD HH24:MI:SS'),100,'Organization purchases this product','EE01','The Purchased check box indicates if this product is purchased by this organization.','Y','Y','Y','N','N','N','N','N','N','Y','Purchased',190,0,TO_TIMESTAMP('2012-08-31 13:31:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:17 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51488 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51489,50011,0,453,0,19,163,51217,TO_TIMESTAMP('2012-08-31 13:31:17','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','EE01','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','Y','N','N','N','N','N','N','Y','Product Category',140,0,TO_TIMESTAMP('2012-08-31 13:31:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:18 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51489 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51490,50011,0,53470,0,20,51214,TO_TIMESTAMP('2012-08-31 13:31:18','YYYY-MM-DD HH24:MI:SS'),100,'Is DRP Required','EE01','If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Y','N','N','N','N','N','N','N','Y','Is DRP Required',0,0,TO_TIMESTAMP('2012-08-31 13:31:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51490 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51491,50011,0,53262,0,20,51215,TO_TIMESTAMP('2012-08-31 13:31:19','YYYY-MM-DD HH24:MI:SS'),100,'Is MRP Required','EE01','If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Y','N','N','N','N','N','N','N','Y','Is MRP Required',0,0,TO_TIMESTAMP('2012-08-31 13:31:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:19 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51491 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51492,50011,0,454,0,30,51218,TO_TIMESTAMP('2012-08-31 13:31:19','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','Y','N','N','N','Y','N','Y','Product',120,0,TO_TIMESTAMP('2012-08-31 13:31:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51492 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51493,50011,0,268,0,16,51207,TO_TIMESTAMP('2012-08-31 13:31:20','YYYY-MM-DD HH24:MI:SS'),100,'Date of Order','EE01','Indicates the Date an item was ordered.','Y','Y','Y','N','N','N','N','N','N','Y','Date Ordered',90,0,TO_TIMESTAMP('2012-08-31 13:31:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51493 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51494,50011,0,289,0,17,131,51209,TO_TIMESTAMP('2012-08-31 13:31:20','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','Y','N','N','N','N','N','N','Y','Document Status',30,0,TO_TIMESTAMP('2012-08-31 13:31:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:20 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51494 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:21 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51495,50011,0,414,0,17,319,51216,TO_TIMESTAMP('2012-08-31 13:31:20','YYYY-MM-DD HH24:MI:SS'),100,'Organization sells this product','EE01','The Sold check box indicates if this product is sold by this organization.','Y','Y','Y','N','N','N','N','Y','N','Y','Sold',170,0,TO_TIMESTAMP('2012-08-31 13:31:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:21 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51495 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:21 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51496,50011,0,53261,0,17,319,51212,TO_TIMESTAMP('2012-08-31 13:31:21','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this product is part of the master production schedule','EE01','The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.','Y','Y','Y','N','N','N','N','Y','N','Y','Is MPS',160,0,TO_TIMESTAMP('2012-08-31 13:31:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:21 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51496 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51497,50011,0,1326,0,20,51211,TO_TIMESTAMP('2012-08-31 13:31:21','YYYY-MM-DD HH24:MI:SS'),100,'Bill of Materials','EE01','The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','Y','Y','N','N','N','N','N','N','Y','Bill of Materials',180,0,TO_TIMESTAMP('2012-08-31 13:31:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51497 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51498,50011,0,53316,0,19,51221,TO_TIMESTAMP('2012-08-31 13:31:22','YYYY-MM-DD HH24:MI:SS'),100,'MRP ID','EE01','Y','Y','Y','N','Y','N','N','N','N','Y','Material Requirement Planning',10,0,TO_TIMESTAMP('2012-08-31 13:31:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51498 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51499,50011,0,290,0,10,51210,TO_TIMESTAMP('2012-08-31 13:31:22','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','Y','N','N','N','Y','N','Y','Document No',20,0,TO_TIMESTAMP('2012-08-31 13:31:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:22 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51499 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:23 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51500,50011,0,1514,0,17,154,51223,TO_TIMESTAMP('2012-08-31 13:31:22','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this request is of a high, medium or low priority.','EE01','The Priority indicates the importance of this request.','Y','Y','Y','N','N','N','N','Y','N','Y','Priority',110,0,TO_TIMESTAMP('2012-08-31 13:31:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:23 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51500 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:23 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64327,0,51227,50030,50011,'SUPPLY_C_BPartner_ID','supply.C_BPartner_ID',TO_TIMESTAMP('2012-08-31 13:31:23','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_TIMESTAMP('2012-08-31 13:31:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64336,0,51228,50030,50011,'SUPPLY_C_UOM_ID','supply.C_UOM_ID',TO_TIMESTAMP('2012-08-31 13:31:23','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','EE01','The UOM defines a unique non monetary Unit of Measure','Y','UOM',TO_TIMESTAMP('2012-08-31 13:31:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:24 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64331,0,51229,50030,50011,'SUPPLY_DateOrdered','supply.DateOrdered',TO_TIMESTAMP('2012-08-31 13:31:24','YYYY-MM-DD HH24:MI:SS'),100,'Date of Order','EE01','Indicates the Date an item was ordered.','Y','Date Ordered',TO_TIMESTAMP('2012-08-31 13:31:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64332,0,51230,50030,50011,'SUPPLY_DatePromised','supply.DatePromised',TO_TIMESTAMP('2012-08-31 13:31:24','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Date Promised',TO_TIMESTAMP('2012-08-31 13:31:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64326,0,51231,50030,50011,'SUPPLY_DocStatus','supply.DocStatus',TO_TIMESTAMP('2012-08-31 13:31:25','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Document Status',TO_TIMESTAMP('2012-08-31 13:31:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:25 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64324,0,51232,50030,50011,'SUPPLY_DocumentNo','supply.DocumentNo',TO_TIMESTAMP('2012-08-31 13:31:25','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Document No',TO_TIMESTAMP('2012-08-31 13:31:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64339,0,51233,50030,50011,'SUPPLY_IsBOM','supply.IsBOM',TO_TIMESTAMP('2012-08-31 13:31:25','YYYY-MM-DD HH24:MI:SS'),100,'Bill of Materials','EE01','The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','Bill of Materials',TO_TIMESTAMP('2012-08-31 13:31:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:26 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64342,0,51234,50030,50011,'SUPPLY_IsMPS','supply.IsMPS',TO_TIMESTAMP('2012-08-31 13:31:26','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this product is part of the master production schedule','EE01','The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.','Y','Is MPS',TO_TIMESTAMP('2012-08-31 13:31:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:27 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64340,0,51235,50030,50011,'SUPPLY_IsPurchased','supply.IsPurchased',TO_TIMESTAMP('2012-08-31 13:31:26','YYYY-MM-DD HH24:MI:SS'),100,'Organization purchases this product','EE01','The Purchased check box indicates if this product is purchased by this organization.','Y','Purchased',TO_TIMESTAMP('2012-08-31 13:31:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:27 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64344,0,51236,50030,50011,'SUPPLY_IsRequiredDRP','supply.IsRequiredDRP',TO_TIMESTAMP('2012-08-31 13:31:27','YYYY-MM-DD HH24:MI:SS'),100,'Is DRP Required','EE01','If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Is DRP Required',TO_TIMESTAMP('2012-08-31 13:31:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:28 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64343,0,51237,50030,50011,'SUPPLY_IsRequiredMRP','supply.IsRequiredMRP',TO_TIMESTAMP('2012-08-31 13:31:27','YYYY-MM-DD HH24:MI:SS'),100,'Is MRP Required','EE01','If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Is MRP Required',TO_TIMESTAMP('2012-08-31 13:31:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64337,0,51238,50030,50011,'SUPPLY_IsSold','supply.IsSold',TO_TIMESTAMP('2012-08-31 13:31:28','YYYY-MM-DD HH24:MI:SS'),100,'Organization sells this product','EE01','The Sold check box indicates if this product is sold by this organization.','Y','Sold',TO_TIMESTAMP('2012-08-31 13:31:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64338,0,51239,50030,50011,'SUPPLY_M_Product_Category_ID','supply.M_Product_Category_ID',TO_TIMESTAMP('2012-08-31 13:31:29','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','EE01','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Product Category',TO_TIMESTAMP('2012-08-31 13:31:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:29 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64334,0,51240,50030,50011,'SUPPLY_M_Product_ID','supply.M_Product_ID',TO_TIMESTAMP('2012-08-31 13:31:29','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01','Identifies an item which is either purchased or sold in this organization.','Y','Product',TO_TIMESTAMP('2012-08-31 13:31:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64330,0,51241,50030,50011,'SUPPLY_M_Warehouse_ID','supply.M_Warehouse_ID',TO_TIMESTAMP('2012-08-31 13:31:29','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_TIMESTAMP('2012-08-31 13:31:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:30 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64325,0,51242,50030,50011,'SUPPLY_OrderType','supply.OrderType',TO_TIMESTAMP('2012-08-31 13:31:30','YYYY-MM-DD HH24:MI:SS'),100,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)','EE01','Y','Order Type',TO_TIMESTAMP('2012-08-31 13:31:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64323,0,51243,50030,50011,'SUPPLY_PP_MRP_ID','supply.PP_MRP_ID',TO_TIMESTAMP('2012-08-31 13:31:30','YYYY-MM-DD HH24:MI:SS'),100,'MRP ID','EE01','Y','Material Requirement Planning',TO_TIMESTAMP('2012-08-31 13:31:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64328,0,51244,50030,50011,'SUPPLY_Planner_ID','supply.Planner_ID',TO_TIMESTAMP('2012-08-31 13:31:31','YYYY-MM-DD HH24:MI:SS'),100,'Company Agent for Planning','EE01','The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','Y','Planner',TO_TIMESTAMP('2012-08-31 13:31:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:31 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64333,0,51245,50030,50011,'SUPPLY_Priority','supply.Priority',TO_TIMESTAMP('2012-08-31 13:31:31','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this request is of a high, medium or low priority.','EE01','The Priority indicates the importance of this request.','Y','Priority',TO_TIMESTAMP('2012-08-31 13:31:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64341,0,51246,50030,50011,'SUPPLY_Qty','supply.Qty',TO_TIMESTAMP('2012-08-31 13:31:31','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01','The Quantity indicates the number of a specific product or item for this document.','Y','Quantity',TO_TIMESTAMP('2012-08-31 13:31:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:32 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64335,0,51247,50030,50011,'SUPPLY_SKU','supply.SKU',TO_TIMESTAMP('2012-08-31 13:31:32','YYYY-MM-DD HH24:MI:SS'),100,'Stock Keeping Unit','EE01','The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','SKU',TO_TIMESTAMP('2012-08-31 13:31:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:33 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64329,0,51248,50030,50011,'SUPPLY_S_Resource_ID','supply.S_Resource_ID',TO_TIMESTAMP('2012-08-31 13:31:32','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01','Y','Resource',TO_TIMESTAMP('2012-08-31 13:31:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:33 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51501,50012,0,53470,0,20,51236,TO_TIMESTAMP('2012-08-31 13:31:33','YYYY-MM-DD HH24:MI:SS'),100,'Is DRP Required','EE01','If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Y','N','N','N','N','N','N','N','Y','Is DRP Required',0,0,TO_TIMESTAMP('2012-08-31 13:31:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:33 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51501 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:34 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51502,50012,0,53262,0,20,51237,TO_TIMESTAMP('2012-08-31 13:31:33','YYYY-MM-DD HH24:MI:SS'),100,'Is MRP Required','EE01','If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','Y','N','N','N','N','N','N','N','Y','Is MRP Required',0,0,TO_TIMESTAMP('2012-08-31 13:31:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:34 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51502 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:34 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51503,50012,0,289,0,17,131,51231,TO_TIMESTAMP('2012-08-31 13:31:34','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','Y','N','N','N','N','N','N','Y','Document Status',30,0,TO_TIMESTAMP('2012-08-31 13:31:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:34 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51503 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:34 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51504,50012,0,1326,0,17,319,51233,TO_TIMESTAMP('2012-08-31 13:31:34','YYYY-MM-DD HH24:MI:SS'),100,'Bill of Materials','EE01','The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','Y','Y','N','N','N','N','Y','N','Y','Bill of Materials',180,0,TO_TIMESTAMP('2012-08-31 13:31:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:34 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51504 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51505,50012,0,453,0,19,163,51239,TO_TIMESTAMP('2012-08-31 13:31:34','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','EE01','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','Y','N','N','N','N','N','N','Y','Product Category',140,0,TO_TIMESTAMP('2012-08-31 13:31:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51505 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51506,50012,0,215,0,19,51228,TO_TIMESTAMP('2012-08-31 13:31:35','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','EE01','The UOM defines a unique non monetary Unit of Measure','Y','Y','Y','N','N','N','N','N','N','Y','UOM',150,0,TO_TIMESTAMP('2012-08-31 13:31:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:35 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51506 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51507,50012,0,53261,0,20,51234,TO_TIMESTAMP('2012-08-31 13:31:35','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this product is part of the master production schedule','EE01','The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.','Y','Y','Y','N','N','N','N','N','N','Y','Is MPS',160,0,TO_TIMESTAMP('2012-08-31 13:31:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51507 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51508,50012,0,414,0,20,51238,TO_TIMESTAMP('2012-08-31 13:31:36','YYYY-MM-DD HH24:MI:SS'),100,'Organization sells this product','EE01','The Sold check box indicates if this product is sold by this organization.','Y','Y','Y','N','N','N','N','N','N','Y','Sold',170,0,TO_TIMESTAMP('2012-08-31 13:31:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51508 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51509,50012,0,403,0,17,319,51235,TO_TIMESTAMP('2012-08-31 13:31:36','YYYY-MM-DD HH24:MI:SS'),100,'Organization purchases this product','EE01','The Purchased check box indicates if this product is purchased by this organization.','Y','Y','Y','N','N','N','N','Y','N','Y','Purchased',190,0,TO_TIMESTAMP('2012-08-31 13:31:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:36 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51509 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51510,50012,0,187,0,19,51227,TO_TIMESTAMP('2012-08-31 13:31:36','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','Y','N','Y','Business Partner ',60,0,TO_TIMESTAMP('2012-08-31 13:31:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51510 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51511,50012,0,268,0,16,51229,TO_TIMESTAMP('2012-08-31 13:31:37','YYYY-MM-DD HH24:MI:SS'),100,'Date of Order','EE01','Indicates the Date an item was ordered.','Y','Y','Y','N','N','N','N','N','N','Y','Date Ordered',90,0,TO_TIMESTAMP('2012-08-31 13:31:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:37 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51511 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51512,50012,0,269,0,16,51230,TO_TIMESTAMP('2012-08-31 13:31:37','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','Y','N','N','N','N','Y','Y','Y','Date Promised',100,0,TO_TIMESTAMP('2012-08-31 13:31:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51512 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51513,50012,0,454,0,30,51240,TO_TIMESTAMP('2012-08-31 13:31:38','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','Y','N','Y','Product',120,0,TO_TIMESTAMP('2012-08-31 13:31:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51513 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51514,50012,0,290,0,10,51232,TO_TIMESTAMP('2012-08-31 13:31:38','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Y','N','Y','Document No',20,0,TO_TIMESTAMP('2012-08-31 13:31:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:38 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51514 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51515,50012,0,1777,0,18,53320,51248,TO_TIMESTAMP('2012-08-31 13:31:38','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01','Y','Y','Y','N','N','N','N','Y','N','Y','Resource',70,0,TO_TIMESTAMP('2012-08-31 13:31:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51515 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51516,50012,0,549,0,10,51247,TO_TIMESTAMP('2012-08-31 13:31:39','YYYY-MM-DD HH24:MI:SS'),100,'Stock Keeping Unit','EE01','The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','Y','Y','N','N','N','N','N','N','Y','SKU',130,0,TO_TIMESTAMP('2012-08-31 13:31:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:39 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51516 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51517,50012,0,526,0,29,51246,TO_TIMESTAMP('2012-08-31 13:31:39','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01','The Quantity indicates the number of a specific product or item for this document.','Y','Y','Y','N','N','N','N','N','N','Y','Quantity',200,0,TO_TIMESTAMP('2012-08-31 13:31:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51517 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51518,50012,0,53316,0,19,51243,TO_TIMESTAMP('2012-08-31 13:31:40','YYYY-MM-DD HH24:MI:SS'),100,'MRP ID','EE01','Y','Y','Y','N','Y','N','N','N','N','Y','Material Requirement Planning',10,0,TO_TIMESTAMP('2012-08-31 13:31:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:40 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51518 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51519,50012,0,52020,0,17,53229,51242,TO_TIMESTAMP('2012-08-31 13:31:40','YYYY-MM-DD HH24:MI:SS'),100,'Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)','EE01','Y','Y','Y','N','N','N','N','Y','N','Y','Order Type',40,0,TO_TIMESTAMP('2012-08-31 13:31:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51519 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51520,50012,0,53269,0,18,286,51244,TO_TIMESTAMP('2012-08-31 13:31:41','YYYY-MM-DD HH24:MI:SS'),100,'Company Agent for Planning','EE01','The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.','Y','Y','Y','N','N','N','N','Y','N','Y','Planner',50,0,TO_TIMESTAMP('2012-08-31 13:31:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51520 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51521,50012,0,459,0,19,51241,TO_TIMESTAMP('2012-08-31 13:31:41','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','N','N','N','Y','N','Y','Warehouse',80,0,TO_TIMESTAMP('2012-08-31 13:31:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:41 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51521 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsOrderBy,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (51522,50012,0,1514,0,17,154,51245,TO_TIMESTAMP('2012-08-31 13:31:41','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this request is of a high, medium or low priority.','EE01','The Priority indicates the importance of this request.','Y','Y','Y','N','N','N','N','Y','N','Y','Priority',110,0,TO_TIMESTAMP('2012-08-31 13:31:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51522 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Aug 31, 2012 1:31:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53456,0,53202,'W',TO_TIMESTAMP('2012-08-31 13:31:42','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demands View','EE01','Y','N','N','N','MRP Demands View',TO_TIMESTAMP('2012-08-31 13:31:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53456 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Aug 31, 2012 1:31:42 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53034,12, 10, 53456)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53457,0,53203,'W',TO_TIMESTAMP('2012-08-31 13:31:42','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supplies View','EE01','Y','N','N','N','MRP Supplies View',TO_TIMESTAMP('2012-08-31 13:31:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53457 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53034,14, 10, 53457)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Menu (AD_Browse_ID,AD_Client_ID,AD_Menu_ID,AD_Org_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (50011,0,53458,0,'S',TO_TIMESTAMP('2012-08-31 13:31:43','YYYY-MM-DD HH24:MI:SS'),100,'MRP Demands Browse','EE07','Y','N','N','N','MRP Demands Browse',TO_TIMESTAMP('2012-08-31 13:31:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53458 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53034,11, 10, 53458)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Menu (AD_Browse_ID,AD_Client_ID,AD_Menu_ID,AD_Org_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (50012,0,53459,0,'S',TO_TIMESTAMP('2012-08-31 13:31:43','YYYY-MM-DD HH24:MI:SS'),100,'MRP Supplies Browse','EE01','Y','N','N','N','MRP Supplies Browse',TO_TIMESTAMP('2012-08-31 13:31:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53459 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Aug 31, 2012 1:31:43 PM CDT
-- MFG-38 MRP Multi-Level Pegging
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53034,13, 10, 53459)
;
