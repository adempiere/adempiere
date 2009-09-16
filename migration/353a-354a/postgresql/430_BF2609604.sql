-- Feb 18, 2009 2:47:26 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
INSERT INTO AD_Column (Help,Created,CreatedBy,Updated,Version,IsActive,AD_Reference_ID,IsMandatory,IsIdentifier,SeqNo,IsAutocomplete,ColumnName,AD_Column_ID,IsParent,AD_Table_ID,FieldLength,Description,IsKey,IsTranslated,AD_Client_ID,AD_Org_ID,AD_Element_ID,IsSelectionColumn,IsUpdateable,IsSyncDatabase,Name,EntityType,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,IsAllowLogging) VALUES ('A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson',TO_TIMESTAMP('2009-02-18 14:47:24','YYYY-MM-DD HH24:MI:SS'),0,TO_TIMESTAMP('2009-02-18 14:47:24','YYYY-MM-DD HH24:MI:SS'),0,'Y',30,'N','N',0,'N','C_BPartner_ID',56816,'N',703,10,'Identifies a Business Partner','N','N',0,0,187,'N','Y','N','Business Partner ','U',0,'N','N','Y')
;

-- Feb 18, 2009 2:47:26 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56816 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 2:47:27 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
INSERT INTO AD_Field (UpdatedBy,IsSameLine,IsHeading,CreatedBy,Updated,Description,DisplayLength,Help,IsFieldOnly,AD_Field_ID,AD_Tab_ID,AD_Column_ID,IsActive,Created,IsDisplayed,AD_Client_ID,AD_Org_ID,IsEncrypted,Name,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES (0,'N','N',0,TO_TIMESTAMP('2009-02-18 14:47:26','YYYY-MM-DD HH24:MI:SS'),'Identifies a Business Partner',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','N',56715,642,56816,'Y',TO_TIMESTAMP('2009-02-18 14:47:26','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'N','Business Partner ','N','Y','U')
;

-- Feb 18, 2009 2:47:27 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56715 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 2:47:27 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
INSERT INTO AD_Field (UpdatedBy,IsSameLine,IsHeading,CreatedBy,Updated,Description,DisplayLength,Help,IsFieldOnly,AD_Field_ID,AD_Tab_ID,AD_Column_ID,IsActive,Created,IsDisplayed,AD_Client_ID,AD_Org_ID,IsEncrypted,Name,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES (0,'N','N',0,TO_TIMESTAMP('2009-02-18 14:47:27','YYYY-MM-DD HH24:MI:SS'),'Identifies a Business Partner',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','N',56716,768,56816,'Y',TO_TIMESTAMP('2009-02-18 14:47:27','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'N','Business Partner ','N','Y','U')
;

-- Feb 18, 2009 2:47:28 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56716 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 2:47:30 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
ALTER TABLE M_RequisitionLine ADD COLUMN C_BPartner_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 18, 2009 3:10:50 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56715
;

-- Feb 18, 2009 3:10:50 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=10043
;

-- Feb 18, 2009 3:10:50 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=13014
;

-- Feb 18, 2009 3:10:50 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=10037
;

-- Feb 18, 2009 3:10:50 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=10034
;

-- Feb 18, 2009 3:10:50 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=10036
;

-- Feb 18, 2009 3:10:50 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=10035
;

-- Feb 18, 2009 3:10:50 PM EET
-- [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=10196
;

