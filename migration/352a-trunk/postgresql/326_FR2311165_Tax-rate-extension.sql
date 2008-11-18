-- Nov 18, 2008 10:06:28 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56465,453,0,19,261,'M_Product_Category_ID',TO_TIMESTAMP('2008-11-18 22:06:25','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','U',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product Category',0,TO_TIMESTAMP('2008-11-18 22:06:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 18, 2008 10:06:29 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56465 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 18, 2008 10:06:32 PM EET
-- [ 2311165 ] Tax rate Extension
ALTER TABLE C_Tax ADD COLUMN M_Product_Category_ID NUMERIC(10)
;

-- Nov 18, 2008 10:20:40 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56466,454,0,19,261,'M_Product_ID',TO_TIMESTAMP('2008-11-18 22:20:39','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','U',10,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product',0,TO_TIMESTAMP('2008-11-18 22:20:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 18, 2008 10:20:40 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56466 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 18, 2008 10:20:44 PM EET
-- [ 2311165 ] Tax rate Extension
ALTER TABLE C_Tax ADD COLUMN M_Product_ID NUMERIC(10)
;

-- Nov 18, 2008 10:21:40 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56467,1383,0,19,261,'C_BP_Group_ID',TO_TIMESTAMP('2008-11-18 22:21:39','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Group','U',10,'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Business Partner Group',0,TO_TIMESTAMP('2008-11-18 22:21:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 18, 2008 10:21:40 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56467 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 18, 2008 10:21:47 PM EET
-- [ 2311165 ] Tax rate Extension
ALTER TABLE C_Tax ADD COLUMN C_BP_Group_ID NUMERIC(10)
;

-- Nov 18, 2008 10:22:42 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56468,187,0,19,261,'C_BPartner_ID',TO_TIMESTAMP('2008-11-18 22:22:40','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','U',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Business Partner ',0,TO_TIMESTAMP('2008-11-18 22:22:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 18, 2008 10:22:42 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56468 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 18, 2008 10:22:45 PM EET
-- [ 2311165 ] Tax rate Extension
ALTER TABLE C_Tax ADD COLUMN C_BPartner_ID NUMERIC(10)
;

-- Nov 18, 2008 10:27:10 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56468,56481,0,174,TO_TIMESTAMP('2008-11-18 22:27:09','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',10,'U','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','N','Business Partner ',TO_TIMESTAMP('2008-11-18 22:27:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 18, 2008 10:27:10 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56481 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 18, 2008 10:27:12 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56467,56482,0,174,TO_TIMESTAMP('2008-11-18 22:27:10','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Group',10,'U','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','Y','N','N','N','N','N','Business Partner Group',TO_TIMESTAMP('2008-11-18 22:27:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 18, 2008 10:27:12 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56482 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 18, 2008 10:27:12 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56466,56483,0,174,TO_TIMESTAMP('2008-11-18 22:27:12','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',10,'U','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','N','Product',TO_TIMESTAMP('2008-11-18 22:27:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 18, 2008 10:27:12 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56483 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 18, 2008 10:27:13 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56465,56484,0,174,TO_TIMESTAMP('2008-11-18 22:27:12','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product',10,'U','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','Y','N','N','N','N','N','Product Category',TO_TIMESTAMP('2008-11-18 22:27:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 18, 2008 10:27:13 PM EET
-- [ 2311165 ] Tax rate Extension
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56484 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 18, 2008 10:27:29 PM EET
-- [ 2311165 ] Tax rate Extension
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56482
;

-- Nov 18, 2008 10:27:29 PM EET
-- [ 2311165 ] Tax rate Extension
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56481
;

-- Nov 18, 2008 10:27:29 PM EET
-- [ 2311165 ] Tax rate Extension
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56484
;

-- Nov 18, 2008 10:27:29 PM EET
-- [ 2311165 ] Tax rate Extension
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56483
;

-- Nov 18, 2008 10:29:55 PM EET
-- [ 2311165 ] Tax rate Extension
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-11-18 22:29:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56481
;

-- Nov 18, 2008 10:30:00 PM EET
-- [ 2311165 ] Tax rate Extension
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-11-18 22:30:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56483
;

