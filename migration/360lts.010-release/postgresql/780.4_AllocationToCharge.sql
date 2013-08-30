-- 05/07/2011 11:37:32 AM
-- --
UPDATE AD_Column SET AD_Val_Rule_ID=52032,Updated=TO_TIMESTAMP('2011-07-05 11:35:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3050
;

-- 05/07/2011 11:37:32 AM
-- --
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52103,'(C_Charge.C_Charge_ID IN (
SELECT c.C_Charge_ID
FROM C_Charge c
JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
JOIN C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID AND dt.AD_Client_ID= ctd.AD_Client_ID AND dt.DocBaseType= ''CMA'')
) OR
(SELECT COUNT(*) FROM C_ChargeType_DocType WHERE AD_Client_ID=@AD_Client_ID@) = 0)',TO_TIMESTAMP('2011-07-05 11:37:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge_ID by DocType Allocation','S',TO_TIMESTAMP('2011-07-05 11:37:30','YYYY-MM-DD HH24:MI:SS'),100)
;
-- 05/07/2011 11:38:53 AM
-- --
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61804,968,0,19,390,52103,'C_Charge_ID',TO_TIMESTAMP('2011-07-05 11:38:51','YYYY-MM-DD HH24:MI:SS'),100,'Additional document charges','D',22,'The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','N','N','N','N','N','N','N','Charge',0,TO_TIMESTAMP('2011-07-05 11:38:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;
-- 05/07/2011 11:38:53 AM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61804 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;
-- 05/07/2011 11:39:37 AM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61804,61876,0,349,TO_TIMESTAMP('2011-07-05 11:39:36','YYYY-MM-DD HH24:MI:SS'),100,'Additional document charges',22,'D','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','Y','N','N','N','N','N','Charge',TO_TIMESTAMP('2011-07-05 11:39:36','YYYY-MM-DD HH24:MI:SS'),100)
;
-- 05/07/2011 11:39:37 AM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61876 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;
-- 05/07/2011 11:39:56 AM
-- --
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=61876
;
-- 05/07/2011 11:39:56 AM
-- --
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=4368
;
-- 05/07/2011 11:39:56 AM
-- --
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=4387
;
-- 05/07/2011 11:39:56 AM
-- --
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=4375
;
-- 05/07/2011 11:39:56 AM
-- --
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=4380
;
-- 05/07/2011 11:39:56 AM
-- --
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=8931
;
-- 05/07/2011 11:40:14 AM
-- --
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2011-07-05 11:40:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61876
;
-- 05/07/2011 11:41:30 AM
-- --
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53137,0,TO_TIMESTAMP('2011-07-05 11:41:29','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Charge to apply difference amount to.','I',TO_TIMESTAMP('2011-07-05 11:41:29','YYYY-MM-DD HH24:MI:SS'),100,'ChargeDifference')
;
-- 05/07/2011 11:41:30 AM
-- --
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53137 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;
-- 05/07/2011 11:54:19 AM
-- --
ALTER TABLE C_AllocationLine ADD COLUMN C_Charge_ID NUMERIC(10) DEFAULT NULL
; 