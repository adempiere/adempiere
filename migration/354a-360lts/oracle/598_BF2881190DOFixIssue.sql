-- Oct 10, 2009 10:35:31 PM CDT
-- Distribution Management
UPDATE AD_Column SET Callout='org.eevolution.model.CalloutDistributionOrder.bPartner',Updated=TO_DATE('2009-10-10 22:35:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53866
;

-- Oct 10, 2009 10:35:47 PM CDT
-- Distribution Management
UPDATE AD_Column SET Callout=NULL,Updated=TO_DATE('2009-10-10 22:35:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53906
;

-- Oct 10, 2009 10:36:06 PM CDT
-- Distribution Management
ALTER TABLE DD_Order MODIFY AD_User_ID NUMBER(10) DEFAULT NULL 
;

-- Oct 10, 2009 10:36:18 PM CDT
-- Distribution Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-10-10 22:36:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53870
;

-- Oct 10, 2009 10:36:21 PM CDT
-- Distribution Management
ALTER TABLE DD_Order MODIFY C_BPartner_Location_ID NUMBER(10)
;

-- Oct 10, 2009 10:36:21 PM CDT
-- Distribution Management
ALTER TABLE DD_Order MODIFY C_BPartner_Location_ID NOT NULL
;

-- Oct 11, 2009 1:17:47 AM CDT
-- Distribution Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-10-11 01:17:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53884
;

-- Oct 11, 2009 1:17:49 AM CDT
-- Distribution Management
ALTER TABLE DD_Order MODIFY DateOrdered DATE
;

-- Oct 11, 2009 1:17:49 AM CDT
-- Distribution Management
ALTER TABLE DD_Order MODIFY DateOrdered NOT NULL
;

-- Oct 11, 2009 1:18:03 AM CDT
-- Distribution Management
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-10-11 01:18:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53886
;

-- Oct 11, 2009 1:18:04 AM CDT
-- Distribution Management
ALTER TABLE DD_Order MODIFY DatePromised DATE
;

-- Oct 11, 2009 1:18:04 AM CDT
-- Distribution Management
ALTER TABLE DD_Order MODIFY DatePromised NOT NULL
;

-- Oct 11, 2009 1:29:28 AM CDT
-- Distribution Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=54031
;

-- Oct 11, 2009 1:29:28 AM CDT
-- Distribution Management
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54011
;

-- Oct 11, 2009 1:29:28 AM CDT
-- Distribution Management
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=54036
;

-- Oct 11, 2009 1:29:28 AM CDT
-- Distribution Management
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=54037
;

-- Oct 11, 2009 1:29:28 AM CDT
-- Distribution Management
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54038
;

-- Oct 11, 2009 1:29:28 AM CDT
-- Distribution Management
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=54039
;

-- Oct 11, 2009 1:39:00 AM CDT
-- Distribution Management
UPDATE AD_Column SET DefaultValue='@SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM DD_OrderLine WHERE DD_Order_ID=@DD_Order_ID@',Updated=TO_DATE('2009-10-11 01:39:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53926
;

-- Oct 11, 2009 1:40:56 AM CDT
-- Distribution Management
UPDATE AD_Tab SET Description='Distribution Order Line', Name='Distribution Order Line',Updated=TO_DATE('2009-10-11 01:40:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53050
;

-- Oct 11, 2009 1:40:56 AM CDT
-- Distribution Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53050
;

-- Oct 11, 2009 1:41:09 AM CDT
-- Distribution Management
UPDATE AD_Tab SET Name='Distribution Order',Updated=TO_DATE('2009-10-11 01:41:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53055
;

-- Oct 11, 2009 1:41:09 AM CDT
-- Distribution Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53055
;

-- Oct 11, 2009 1:42:08 AM CDT
-- Distribution Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-10-11 01:42:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54215
;

-- Oct 11, 2009 1:42:16 AM CDT
-- Distribution Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-10-11 01:42:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54216
;

-- Oct 11, 2009 2:05:22 AM CDT
-- Distribution Management
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53084,0,TO_DATE('2009-10-11 02:05:20','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Not exists a Business Partner linked for Organization','E',TO_DATE('2009-10-11 02:05:20','YYYY-MM-DD HH24:MI:SS'),0,'NotExistsBPLinkedforOrgError')
;

-- Oct 11, 2009 2:05:22 AM CDT
-- Distribution Management
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53084 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- Oct 11, 2009 2:11:23 AM CDT
-- Distribution Management
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53085,0,TO_DATE('2009-10-11 02:11:23','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Print Movements','I',TO_DATE('2009-10-11 02:11:23','YYYY-MM-DD HH24:MI:SS'),0,'PrintMovements')
;

-- Oct 11, 2009 2:11:23 AM CDT
-- Distribution Management
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53085 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- Oct 12, 2009 9:53:49 PM CDT
-- Distribution Management
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53086,0,TO_DATE('2009-10-12 21:53:44','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Select Distribution Orders to generate Inventory Moves','I',TO_DATE('2009-10-12 21:53:44','YYYY-MM-DD HH24:MI:SS'),0,'InventoryMoveGenerateSel')
;

-- Oct 12, 2009 9:53:49 PM CDT
-- Distribution Management
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53086 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Oct 12, 2009 9:55:42 PM CDT
-- Distribution Management
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53087,0,TO_DATE('2009-10-12 21:55:40','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Inventory Moves are generated depending on the "Delivery Rule" selection in the Distribution Order','I',TO_DATE('2009-10-12 21:55:40','YYYY-MM-DD HH24:MI:SS'),0,'InventoryMoveGenerateInfo')
;

-- Oct 12, 2009 9:55:42 PM CDT
-- Distribution Management
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53087 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 18.10.2009 klo 2.29.33
-- Distribution Management
UPDATE AD_Column SET AD_Reference_Value_ID=286,Updated=TO_DATE('2009-10-18 02:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53915
;



