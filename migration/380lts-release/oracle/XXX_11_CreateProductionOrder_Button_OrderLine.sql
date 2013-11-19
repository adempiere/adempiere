
AlTER TABLE C_OrderLine ADD createfrom CHAR(1) CHECK ( createfrom IN ('Y','N') );

-- Oct 17, 2013 5:53:21 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68954,1490,0,20,260,'CreateFrom',TO_DATE('2013-10-17 17:53:20','YYYY-MM-DD HH24:MI:SS'),0,'Process which will generate a new document lines based on an existing document','D',1,'The Create From process will create a new document based on information in an existing document selected by the user.','Y','N','N','N','N','N','N','N','N','Y','Create lines from',TO_DATE('2013-10-17 17:53:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 17, 2013 5:53:21 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68954 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 17, 2013 5:53:52 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
UPDATE AD_Column SET AD_Process_ID=53477, AD_Reference_ID=28,Updated=TO_DATE('2013-10-17 17:53:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=68954
;

-- Oct 17, 2013 5:54:09 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68954,69922,0,187,TO_DATE('2013-10-17 17:54:09','YYYY-MM-DD HH24:MI:SS'),0,'Process which will generate a new document lines based on an existing document',1,'D','The Create From process will create a new document based on information in an existing document selected by the user.','Y','Y','Y','N','N','N','N','N','Create lines from',TO_DATE('2013-10-17 17:54:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 17, 2013 5:54:09 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69922 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 17, 2013 5:55:11 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
UPDATE AD_Field SET DisplayLogic='@Processed@=Y', Name='Create Production Order',Updated=TO_DATE('2013-10-17 17:55:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69922
;

-- Oct 17, 2013 5:55:11 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69922
;

-- Oct 17, 2013 5:56:15 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=330,Updated=TO_DATE('2013-10-17 17:56:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69922
;

-- Oct 17, 2013 6:05:58 PM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
UPDATE AD_Column SET IsAlwaysUpdateable='Y',Updated=TO_DATE('2013-10-17 18:05:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=68954
;