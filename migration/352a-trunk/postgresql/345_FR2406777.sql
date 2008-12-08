-- 08.12.2008 18:04:07 EET
-- FR [ 2406777 ] Introduce MRP-130 No Current Vendor Selected
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53048,0,TO_TIMESTAMP('2008-12-08 18:04:06','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','No Current Vendor Selected','Indicates that the Product has no Current Vendor selected','I',TO_TIMESTAMP('2008-12-08 18:04:06','YYYY-MM-DD HH24:MI:SS'),0,'MRP-130')
;

-- 08.12.2008 18:04:07 EET
-- FR [ 2406777 ] Introduce MRP-130 No Current Vendor Selected
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53048 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 08.12.2008 18:06:55 EET
-- FR [ 2406777 ] Introduce MRP-130 No Current Vendor Selected
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Furnizorul curent nu este specificat',MsgTip='Produsul nu are furnizor curent specificat',Updated=TO_TIMESTAMP('2008-12-08 18:06:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53048 AND AD_Language='ro_RO'
;

