-- 23.12.2008 12:20:26 EET
-- FR [ 2457781 ] Introduce NoVendorForProductException
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53050,0,TO_DATE('2008-12-23 12:19:53','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','No Vendor for Product : ','E',TO_DATE('2008-12-23 12:19:53','YYYY-MM-DD HH24:MI:SS'),0,'NoVendorForProduct')
;

-- 23.12.2008 12:20:26 EET
-- FR [ 2457781 ] Introduce NoVendorForProductException
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53050 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 23.12.2008 12:20:48 EET
-- FR [ 2457781 ] Introduce NoVendorForProductException
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Nu exista furnizor pentru produsul : ',Updated=TO_DATE('2008-12-23 12:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53050 AND AD_Language='ro_RO'
;

