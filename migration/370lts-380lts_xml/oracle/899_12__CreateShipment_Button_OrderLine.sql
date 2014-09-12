ALTER TABLE C_OrderLine ADD CreateShipment char(1) check( CreateShipment IN ('Y', 'N'))
;

-- Oct 19, 2013 5:14:23 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('3',0,0,53501,'org.compiere.process.OrderLineCreateShipment','N',TO_DATE('2013-10-19 17:14:22','YYYY-MM-DD HH24:MI:SS'),0,'Create the shipment for Sales Order','D','Y','N','N','N','N','CreateShipment','Y',0,0,TO_DATE('2013-10-19 17:14:22','YYYY-MM-DD HH24:MI:SS'),0,'CreateShipment')
;

-- Oct 19, 2013 5:14:23 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53501 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Oct 19, 2013 5:15:21 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1037,0,53501,54176,15,'MovementDate',TO_DATE('2013-10-19 17:15:21','YYYY-MM-DD HH24:MI:SS'),0,'@Date@','MovementDate','D',7,'MovementDate','Y','Y','N','N','MovementDate',10,TO_DATE('2013-10-19 17:15:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 19, 2013 5:15:21 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54176 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Oct 19, 2013 5:38:07 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56518,0,'createshipment',TO_DATE('2013-10-19 17:38:07','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','createshipment','createshipment',TO_DATE('2013-10-19 17:38:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 19, 2013 5:38:07 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56518 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 19, 2013 5:38:08 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69035,56518,0,20,260,'createshipment',TO_DATE('2013-10-19 17:38:07','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','createshipment',TO_DATE('2013-10-19 17:38:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 19, 2013 5:38:08 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69035 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 19, 2013 5:38:57 PM IST
-- Create Shipment Process is Created
UPDATE AD_Column SET AD_Process_ID=53501, AD_Reference_ID=28, Name='Create Shipment',Updated=TO_DATE('2013-10-19 17:38:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69035
;

-- Oct 19, 2013 5:38:57 PM IST
-- Create Shipment Process is Created
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69035
;

-- Oct 19, 2013 5:38:57 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET Name='Create Shipment', Description=NULL, Help=NULL WHERE AD_Column_ID=69035 AND IsCentrallyMaintained='Y'
;

-- Oct 19, 2013 5:39:02 PM IST
-- Create Shipment Process is Created
UPDATE AD_Column SET IsAlwaysUpdateable='Y',Updated=TO_DATE('2013-10-19 17:39:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69035
;

-- Oct 19, 2013 5:39:47 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69035,69996,0,187,TO_DATE('2013-10-19 17:39:46','YYYY-MM-DD HH24:MI:SS'),0,1,'D','Y','Y','Y','N','N','N','N','N','createshipment',TO_DATE('2013-10-19 17:39:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 19, 2013 5:39:47 PM IST
-- Create Shipment Process is Created
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69996 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 19, 2013 5:40:23 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET DisplayLogic='@Processed@=Y', Name='Create Shipment',Updated=TO_DATE('2013-10-19 17:40:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69996
;

-- Oct 19, 2013 5:40:23 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69996
;

-- Oct 19, 2013 5:40:51 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=340,Updated=TO_DATE('2013-10-19 17:40:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69996
;

-- Oct 19, 2013 5:40:51 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_DATE('2013-10-19 17:40:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=13650
;

-- Oct 19, 2013 5:40:51 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_DATE('2013-10-19 17:40:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=13651
;

-- Oct 19, 2013 5:40:51 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_DATE('2013-10-19 17:40:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2880
;

-- Oct 19, 2013 5:40:51 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_DATE('2013-10-19 17:40:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12744
;

-- Oct 19, 2013 5:40:51 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_DATE('2013-10-19 17:40:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10332
;

-- Oct 19, 2013 5:47:52 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2013-10-19 17:47:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69996
;

-- Oct 19, 2013 5:48:07 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET Description='Process which will generate a new document lines based on an existing document',Updated=TO_DATE('2013-10-19 17:48:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69996
;

-- Oct 19, 2013 5:48:07 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69996
;

-- Oct 19, 2013 5:48:15 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field SET Help='The Create From process will create a new document based on information in an existing document selected by the user.',Updated=TO_DATE('2013-10-19 17:48:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69996
;

-- Oct 19, 2013 5:48:15 PM IST
-- Create Shipment Process is Created
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69996
;

-- Oct 19, 2013 7:41:03 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process SET Description='Create Production Order from Order Line', EntityType='D', Name='Create Production Order',Updated=TO_DATE('2013-10-19 19:41:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53477
;

-- Oct 19, 2013 7:41:04 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53477
;

-- Oct 19, 2013 7:41:23 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process_Para SET EntityType='D', Name='Movement Date',Updated=TO_DATE('2013-10-19 19:41:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=54106
;

-- Oct 19, 2013 7:41:23 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=54106
;

-- Oct 19, 2013 7:42:28 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process SET Name='Create Shipment',Updated=TO_DATE('2013-10-19 19:42:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53501
;

-- Oct 19, 2013 7:42:28 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53501
;

-- Oct 19, 2013 7:42:38 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process_Para SET Name='Movement Date',Updated=TO_DATE('2013-10-19 19:42:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=54176
;

-- Oct 19, 2013 7:42:38 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=54176
;

-- Oct 19, 2013 7:43:31 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process_Para SET Name='Ignore Prev Production ',Updated=TO_DATE('2013-10-19 19:43:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=54107
;

-- Oct 19, 2013 7:43:31 PM IST
-- Create Shipment Process is Created
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=54107
;

