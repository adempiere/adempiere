-- Jul 22, 2008 3:46:17 PM COT
-- [ 2019115 ] Don't allow UoM change if product already used
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53043,0,TO_TIMESTAMP('2008-07-22 15:46:13','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','UOM can''t be changed if the product has movements or costs','E',TO_TIMESTAMP('2008-07-22 15:46:13','YYYY-MM-DD HH24:MI:SS'),100,'SaveUomError')
;

-- Jul 22, 2008 3:46:17 PM COT
-- [ 2019115 ] Don't allow UoM change if product already used
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53043 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- Jul 22, 2008 3:47:05 PM COT
-- [ 2019115 ] Don't allow UoM change if product already used
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='UOM no puede ser modificado si el producto tiene movimientos o costos',Updated=TO_TIMESTAMP('2008-07-22 15:47:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53043 AND AD_Language like 'es_%'
;

