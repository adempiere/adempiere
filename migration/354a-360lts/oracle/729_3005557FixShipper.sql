-- May 21, 2010 6:20:05 PM CDT
-- Distribution Order
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59192,455,0,30,53038,'M_Shipper_ID',TO_DATE('2010-05-21 18:20:02','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE01',22,'The Shipper indicates the method of delivering product','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Shipper',0,TO_DATE('2010-05-21 18:20:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 21, 2010 6:20:05 PM CDT
-- Distribution Order
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59192 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 21, 2010 6:20:08 PM CDT
-- Distribution Order
ALTER TABLE DD_OrderLine ADD M_Shipper_ID NUMBER(10) DEFAULT NULL 
;

-- May 21, 2010 6:26:01 PM CDT
-- Distribution Order
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52084,'DD_OrderLine.DD_Order_ID=@DD_Order_ID@',TO_DATE('2010-05-21 18:26:00','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','DD_OrderLine of Order','S',TO_DATE('2010-05-21 18:26:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 21, 2010 6:26:12 PM CDT
-- Distribution Order
UPDATE AD_Column SET AD_Val_Rule_ID=52084,Updated=TO_DATE('2010-05-21 18:26:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53968
;

-- May 21, 2010 6:27:39 PM CDT
-- Distribution Order
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59192,58881,0,53050,TO_DATE('2010-05-21 18:27:36','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery',22,'EE01','The Shipper indicates the method of delivering product','Y','Y','Y','N','N','N','N','N','Shipper',TO_DATE('2010-05-21 18:27:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 21, 2010 6:27:39 PM CDT
-- Distribution Order
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58881 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 21, 2010 6:27:49 PM CDT
-- Distribution Order
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=58881
;

-- May 21, 2010 6:27:49 PM CDT
-- Distribution Order
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54036
;

-- May 21, 2010 6:27:49 PM CDT
-- Distribution Order
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=54037
;

-- May 21, 2010 6:27:49 PM CDT
-- Distribution Order
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=54038
;

-- May 21, 2010 6:27:49 PM CDT
-- Distribution Order
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=54039
;

-- May 21, 2010 6:28:11 PM CDT
-- Distribution Order
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-05-21 18:28:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58881
;

