-- 04-jun-2008 23:12:26 CDT
-- Fix Libero

-- added first to fix [ 1985198 ] 196_FB1983672_refactory script failing
INSERT INTO AD_COLUMN
            (AD_COLUMN_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE,
             CREATED,
             UPDATED, CREATEDBY,
             UPDATEDBY, NAME, DESCRIPTION, VERSION,
             ENTITYTYPE, COLUMNNAME, AD_TABLE_ID, AD_REFERENCE_ID,
             FIELDLENGTH, ISKEY, ISPARENT, ISMANDATORY, ISUPDATEABLE,
             READONLYLOGIC, ISIDENTIFIER, ISTRANSLATED, ISENCRYPTED,
             ISSELECTIONCOLUMN, AD_ELEMENT_ID, ISSYNCDATABASE,
             ISALWAYSUPDATEABLE
            )
     VALUES (56076, 0, 0, 'Y',
             TO_DATE('2008-06-04 23:11:54','YYYY-MM-DD HH24:MI:SS'),
             TO_DATE('2008-06-04 23:11:54','YYYY-MM-DD HH24:MI:SS'), 100,
             100, 'Current Cost Price Low Level', 'The low level cost is using to accumulation the cost for low level in a bill of material or formula.', 0,
             'EE01', 'CurrentCostPriceLL', 771, 37,
             22, 'N', 'N', 'Y', 'Y',
             '@CostingMethod@!x & @CostingMethod@!S', 'N', 'N', 'N',
             'N', 53296, 'N',
             'N'
            );


-- 04-jun-2008 23:12:26 CDT
-- Fix Libero
INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,Updated,UpdatedBy) VALUES (0,56076,56266,0,701,TO_DATE('2008-06-04 23:11:54','YYYY-MM-DD HH24:MI:SS'),0,'The low level cost is using to accumulation the cost for low level in a bill of material or formula.',22,'EE01','The low level cost is using to accumulation the cost for low level in a bill of material or formula.','Y','Y','Y','N','N','N','N','N','Current Cost Price Low Level',TO_DATE('2008-06-04 23:11:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 04-jun-2008 23:12:26 CDT
-- Fix Libero
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56266 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_FIELD SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56266
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_FIELD SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=11352
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_FIELD SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=12318
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_FIELD SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12175
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_FIELD SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12176
;

-- 04-jun-2008 23:14:30 CDT
-- Fix Libero
UPDATE AD_FIELD SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=12319
;

-- 04-jun-2008 23:14:39 CDT
-- Fix Libero
UPDATE AD_FIELD SET IsSameLine='Y',Updated=TO_DATE('2008-06-04 23:14:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=11352
;

-- Add CurrentCostPriceLL column to database
ALTER TABLE M_Cost ADD CurrentCostPriceLL NUMBER DEFAULT 0;
