-- Jan 21, 2008 1:38:27 AM EET
-- [ 1875981 ] Add support for EAN13 Barcode
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53288,377,TO_TIMESTAMP('2008-01-21 01:38:27','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','EAN 13',TO_TIMESTAMP('2008-01-21 01:38:27','YYYY-MM-DD HH24:MI:SS'),100,'E13')
;

-- Jan 21, 2008 1:38:28 AM EET
-- [ 1875981 ] Add support for EAN13 Barcode
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53288 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

