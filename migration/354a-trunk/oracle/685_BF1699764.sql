-- Mar 18, 2010 10:26:43 PM COT
-- BF1699764_Usability - Price List GUI falsely links to Discount Schemas
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53343,TO_DATE('2010-03-18 22:26:41','YYYY-MM-DD HH24:MI:SS'),100,'Price List Discount Schema','D','Y','N','M_DiscountSchema PL',TO_DATE('2010-03-18 22:26:41','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Mar 18, 2010 10:26:43 PM COT
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53343 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Mar 18, 2010 10:27:34 PM COT
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,WhereClause) VALUES (0,6589,6581,0,53343,475,337,TO_DATE('2010-03-18 22:27:34','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_DATE('2010-03-18 22:27:34','YYYY-MM-DD HH24:MI:SS'),100,'M_DiscountSchema.DiscountType=''P''')
;

-- Mar 18, 2010 10:28:27 PM COT
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53343, AD_Val_Rule_ID=NULL,Updated=TO_DATE('2010-03-18 22:28:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6645
;

-- Mar 18, 2010 10:29:21 PM COT
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53343, IsUpdateable='N',Updated=TO_DATE('2010-03-18 22:29:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6620
;

