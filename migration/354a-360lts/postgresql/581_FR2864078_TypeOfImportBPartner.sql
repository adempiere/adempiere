-- Sep 23, 2009 10:00:58 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58549,364,0,20,533,'IsCustomer',TO_TIMESTAMP('2009-09-23 10:00:56','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this Business Partner is a Customer','D',1,'The Customer checkbox indicates if this Business Partner is a customer.  If it is select additional fields will display which further define this customer.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Customer',0,TO_TIMESTAMP('2009-09-23 10:00:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 23, 2009 10:00:58 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58549 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 23, 2009 10:02:03 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58550,373,0,20,533,'IsEmployee',TO_TIMESTAMP('2009-09-23 10:01:57','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if  this Business Partner is an employee','D',1,'The Employee checkbox indicates if this Business Partner is an Employee.  If it is selected, additional fields will display which further identify this employee.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Employee',0,TO_TIMESTAMP('2009-09-23 10:01:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 23, 2009 10:02:03 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58550 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 23, 2009 10:06:41 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58551,426,0,20,533,'IsVendor',TO_TIMESTAMP('2009-09-23 10:06:41','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this Business Partner is a Vendor','D',1,'The Vendor checkbox indicates if this Business Partner is a Vendor.  If it is selected, additional fields will display which further identify this vendor.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Vendor',0,TO_TIMESTAMP('2009-09-23 10:06:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 23, 2009 10:06:41 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58551 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 23, 2009 10:07:01 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
ALTER TABLE I_BPartner ADD COLUMN IsCustomer CHAR(1) DEFAULT NULL CHECK (IsCustomer IN ('Y','N'))
;

-- Sep 23, 2009 10:08:50 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
ALTER TABLE I_BPartner ADD COLUMN IsEmployee CHAR(1) DEFAULT NULL CHECK (IsEmployee IN ('Y','N'))
;

-- Sep 23, 2009 10:08:59 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
ALTER TABLE I_BPartner ADD COLUMN IsVendor CHAR(1) DEFAULT NULL CHECK (IsVendor IN ('Y','N'))
;

-- Sep 23, 2009 10:09:54 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58549,58038,0,441,TO_TIMESTAMP('2009-09-23 10:09:54','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this Business Partner is a Customer',1,'D','The Customer checkbox indicates if this Business Partner is a customer.  If it is select additional fields will display which further define this customer.','Y','Y','Y','N','N','N','N','N','Customer',TO_TIMESTAMP('2009-09-23 10:09:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2009 10:09:54 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58038 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 23, 2009 10:09:56 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58550,58039,0,441,TO_TIMESTAMP('2009-09-23 10:09:55','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if  this Business Partner is an employee',1,'D','The Employee checkbox indicates if this Business Partner is an Employee.  If it is selected, additional fields will display which further identify this employee.','Y','Y','Y','N','N','N','N','N','Employee',TO_TIMESTAMP('2009-09-23 10:09:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2009 10:09:56 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58039 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 23, 2009 10:09:56 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58551,58040,0,441,TO_TIMESTAMP('2009-09-23 10:09:56','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this Business Partner is a Vendor',1,'D','The Vendor checkbox indicates if this Business Partner is a Vendor.  If it is selected, additional fields will display which further identify this vendor.','Y','Y','Y','N','N','N','N','N','Vendor',TO_TIMESTAMP('2009-09-23 10:09:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2009 10:09:56 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58040 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 23, 2009 10:10:24 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=58038
;

-- Sep 23, 2009 10:10:24 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=58039
;

-- Sep 23, 2009 10:10:24 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=58040
;

-- Sep 23, 2009 10:10:24 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=5941
;

-- Sep 23, 2009 10:10:24 AM CEST
-- FR[2864078] TypeOfImportBPartner - https://sourceforge.net/tracker/?func=detail&atid=883808&aid=2864078&group_id=176962
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=5924
;

