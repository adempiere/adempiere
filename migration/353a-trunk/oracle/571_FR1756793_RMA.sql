-- Sep 12, 2009 6:04:41 PM COT
-- FR1756793 RMA
UPDATE AD_Field SET Description='Creates a replacement sales order based on this RMA Document. The RMA should be correct and completed.', Help='Generate replacemente sales order from RMA will create an order based on this RMA document.', Name='Create Replacement Sales Order',Updated=TO_DATE('2009-09-12 18:04:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=52000
;

-- Sep 12, 2009 6:04:41 PM COT
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=52000
;

-- Sep 12, 2009 6:05:58 PM COT
UPDATE AD_Field SET Description='Creates a replacement purchase order based on this RMA Document. The RMA should be correct and completed.', Help='Generate replacement purchase order from RMA will create an order based on this RMA document.', Name='Create Replacement Purchase Order',Updated=TO_DATE('2009-09-12 18:05:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57965
;

-- Sep 12, 2009 6:05:58 PM COT
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=57965
;

-- Sep 12, 2009 6:06:05 PM COT
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=57965
;

-- Sep 12, 2009 6:06:26 PM COT
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=57964
;

-- Sep 12, 2009 6:40:22 PM COT
-- FR1756793 - RMA Feature
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53082,0,TO_DATE('2009-09-12 18:40:21','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Customer RMA','I',TO_DATE('2009-09-12 18:40:21','YYYY-MM-DD HH24:MI:SS'),100,'CustomerRMA')
;

-- Sep 12, 2009 6:40:22 PM COT
-- FR1756793 - RMA Feature
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53082 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

