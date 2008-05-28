-- Feb 12, 2008 12:59:56 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53062,'3',TO_TIMESTAMP('2008-02-12 12:59:54','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','Y','N','N','N','Order BOM MA','L','PP_Order_BOMLineMA',TO_TIMESTAMP('2008-02-12 12:59:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 12, 2008 12:59:56 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53062 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Feb 12, 2008 12:59:57 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53079,TO_TIMESTAMP('2008-02-12 12:59:56','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Order_BOMLineMA',1,'Y','N','Y','Y','PP_Order_BOMLineMA','N',1000000,TO_TIMESTAMP('2008-02-12 12:59:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 12, 2008 12:59:58 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2008-02-12 12:59:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Feb 12, 2008 12:59:58 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Feb 12, 2008 12:59:59 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54311,102,0,19,53062,'AD_Client_ID',TO_TIMESTAMP('2008-02-12 12:59:58','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_TIMESTAMP('2008-02-12 12:59:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 12:59:59 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54311 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 12:59:59 PM CST
-- Include table PP_Order_BOMLineMA
CREATE TABLE PP_Order_BOMLineMA (AD_Client_ID NUMERIC(10) NOT NULL)
;

-- Feb 12, 2008 1:00:01 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54312,113,0,19,53062,'AD_Org_ID',TO_TIMESTAMP('2008-02-12 13:00:00','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_TIMESTAMP('2008-02-12 13:00:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:01 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54312 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:01 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN AD_Org_ID NUMERIC(10) NOT NULL
;

-- Feb 12, 2008 1:00:01 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2008-02-12 13:00:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Feb 12, 2008 1:00:01 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Feb 12, 2008 1:00:02 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54313,245,0,16,53062,'Created',TO_TIMESTAMP('2008-02-12 13:00:01','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_TIMESTAMP('2008-02-12 13:00:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:02 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54313 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:02 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN Created TIMESTAMP NOT NULL
;

-- Feb 12, 2008 1:00:02 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2008-02-12 13:00:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Feb 12, 2008 1:00:02 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Feb 12, 2008 1:00:03 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2008-02-12 13:00:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Feb 12, 2008 1:00:03 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Feb 12, 2008 1:00:03 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Feb 12, 2008 1:00:04 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54314,246,0,18,110,53062,'CreatedBy',TO_TIMESTAMP('2008-02-12 13:00:03','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_TIMESTAMP('2008-02-12 13:00:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:04 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54314 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:04 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN CreatedBy NUMERIC(10) NOT NULL
;

-- Feb 12, 2008 1:00:04 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2008-02-12 13:00:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Feb 12, 2008 1:00:04 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Feb 12, 2008 1:00:05 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54315,348,0,20,53062,'IsActive',TO_TIMESTAMP('2008-02-12 13:00:04','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_TIMESTAMP('2008-02-12 13:00:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:05 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54315 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:05 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Feb 12, 2008 1:00:05 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference SET Description='Product Attribute', EntityType='D', Help=NULL, IsActive='Y', Name='Product Attribute', ValidationType='D',Updated=TO_TIMESTAMP('2008-02-12 13:00:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=35
;

-- Feb 12, 2008 1:00:05 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=35
;

-- Feb 12, 2008 1:00:06 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54316,2019,0,35,53062,'M_AttributeSetInstance_ID',TO_TIMESTAMP('2008-02-12 13:00:05','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','Y','N','N','Y','N','Y','Attribute Set Instance',TO_TIMESTAMP('2008-02-12 13:00:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:06 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54316 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:06 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN M_AttributeSetInstance_ID NUMERIC(10) NOT NULL
;

-- Feb 12, 2008 1:00:06 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2008-02-12 13:00:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=29
;

-- Feb 12, 2008 1:00:06 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Feb 12, 2008 1:00:08 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54317,1038,0,29,53062,'MovementQty',TO_TIMESTAMP('2008-02-12 13:00:06','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of a product moved.','EE01',22,'The Movement Quantity indicates the quantity of a product that has been moved.','Y','N','N','N','N','Y','N','N','Y','N','Y','Movement Quantity',TO_TIMESTAMP('2008-02-12 13:00:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:08 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54317 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:08 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN MovementQty NUMERIC NOT NULL
;

-- Feb 12, 2008 1:00:08 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2008-02-12 13:00:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Feb 12, 2008 1:00:08 PM CST
-- Include table PP_Order_BOMLineMA
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Feb 12, 2008 1:00:09 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53343,0,'PP_Order_BOMLineMA_ID',TO_TIMESTAMP('2008-02-12 13:00:08','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','PP_Order_BOMLineMA_ID','PP_Order_BOMLineMA_ID',TO_TIMESTAMP('2008-02-12 13:00:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 12, 2008 1:00:09 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53343 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 12, 2008 1:00:10 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54318,53343,0,13,53062,'PP_Order_BOMLineMA_ID',TO_TIMESTAMP('2008-02-12 13:00:08','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','PP_Order_BOMLineMA_ID',TO_TIMESTAMP('2008-02-12 13:00:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:10 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54318 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:10 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN PP_Order_BOMLineMA_ID NUMERIC(10) NOT NULL
;

-- Feb 12, 2008 1:00:10 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD CONSTRAINT PP_Order_BOMLineMA_Key PRIMARY KEY (PP_Order_BOMLineMA_ID)
;

-- Feb 12, 2008 1:00:11 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54319,53275,0,19,53062,'PP_Order_BOMLine_ID',TO_TIMESTAMP('2008-02-12 13:00:10','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','Y','N','N','Y','N','Y','PP_Order_BOMLine_ID',TO_TIMESTAMP('2008-02-12 13:00:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:11 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54319 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:11 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN PP_Order_BOMLine_ID NUMERIC(10) NOT NULL
;

-- Feb 12, 2008 1:00:14 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54320,607,0,16,53062,'Updated',TO_TIMESTAMP('2008-02-12 13:00:11','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_TIMESTAMP('2008-02-12 13:00:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:14 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54320 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:14 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN Updated TIMESTAMP NOT NULL
;

-- Feb 12, 2008 1:00:14 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54321,608,0,18,110,53062,'UpdatedBy',TO_TIMESTAMP('2008-02-12 13:00:14','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_TIMESTAMP('2008-02-12 13:00:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:00:14 PM CST
-- Include table PP_Order_BOMLineMA
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54321 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:00:15 PM CST
-- Include table PP_Order_BOMLineMA
ALTER TABLE PP_Order_BOMLineMA ADD COLUMN UpdatedBy NUMERIC(10) NOT NULL
;

