-- Dec 1, 2009 4:57:27 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Table (IsSecurityEnabled,TableName,AccessLevel,LoadSeq,AD_Client_ID,IsActive,AD_Org_ID,Name,IsDeleteable,CreatedBy,Updated,UpdatedBy,AD_Table_ID,Created,IsHighVolume,ImportTable,IsView,IsChangeLog,ReplicationType,CopyColumnsFromTable,EntityType) VALUES ('N','RV_Storage_Per_Product','3',0,0,'Y',0,'Storage per Product','Y',100,TO_TIMESTAMP('2009-12-01 16:57:26','YYYY-MM-DD HH24:MI:SS'),100,53247,TO_TIMESTAMP('2009-12-01 16:57:26','YYYY-MM-DD HH24:MI:SS'),'N','N','N','N','L','N','D')
;

-- Dec 1, 2009 4:57:27 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53247 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Dec 1, 2009 4:57:28 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53357,TO_TIMESTAMP('2009-12-01 16:57:27','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table RV_Storage_Per_Product',1,'Y','N','Y','Y','RV_Storage_Per_Product','N',1000000,TO_TIMESTAMP('2009-12-01 16:57:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 1, 2009 4:57:36 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58671,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:35','YYYY-MM-DD HH24:MI:SS'),'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','N',0,0,'Client/Tenant for this installation.','Client','Y',10,'N',19,TO_TIMESTAMP('2009-12-01 16:57:35','YYYY-MM-DD HH24:MI:SS'),'N','N',102,100,'N','AD_Client_ID','N')
;

-- Dec 1, 2009 4:57:36 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58671 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:36 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58672,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:36','YYYY-MM-DD HH24:MI:SS'),'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','N',0,0,'Organizational entity within client','Organization','Y',10,'N',19,TO_TIMESTAMP('2009-12-01 16:57:36','YYYY-MM-DD HH24:MI:SS'),'N','N',113,100,'N','AD_Org_ID','N')
;

-- Dec 1, 2009 4:57:36 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58672 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:37 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58673,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:36','YYYY-MM-DD HH24:MI:SS'),'Identifies an item which is either purchased or sold in this organization.','N',0,0,'Product, Service, Item','Product','Y',10,'N',19,TO_TIMESTAMP('2009-12-01 16:57:36','YYYY-MM-DD HH24:MI:SS'),'Y','N',454,100,'N','M_Product_ID','N')
;

-- Dec 1, 2009 4:57:37 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58673 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:38 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58674,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:37','YYYY-MM-DD HH24:MI:SS'),'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','N',0,0,'Search key for the record in the format required - must be unique','Search Key','Y',40,'N',10,TO_TIMESTAMP('2009-12-01 16:57:37','YYYY-MM-DD HH24:MI:SS'),'Y','N',620,100,'N','Value','N')
;

-- Dec 1, 2009 4:57:38 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58674 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:38 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58675,53247,'D',0,'N','N','Y',1,100,TO_TIMESTAMP('2009-12-01 16:57:38','YYYY-MM-DD HH24:MI:SS'),'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','N',0,0,'Alphanumeric identifier of the entity','Name','Y',255,'N',10,TO_TIMESTAMP('2009-12-01 16:57:38','YYYY-MM-DD HH24:MI:SS'),'Y','N',469,100,'N','Name','N')
;

-- Dec 1, 2009 4:57:38 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58675 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:39 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58676,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:38','YYYY-MM-DD HH24:MI:SS'),'A description is limited to 255 characters.','N',0,0,'Optional short description of the record','Description','Y',255,'N',10,TO_TIMESTAMP('2009-12-01 16:57:38','YYYY-MM-DD HH24:MI:SS'),'Y','N',275,100,'N','Description','N')
;

-- Dec 1, 2009 4:57:39 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58676 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:39 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58677,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:39','YYYY-MM-DD HH24:MI:SS'),'Use this field to enter the bar code for the product in any of the bar code symbologies (Codabar, Code 25, Code 39, Code 93, Code 128, UPC (A), UPC (E), EAN-13, EAN-8, ITF, ITF-14, ISBN, ISSN, JAN-13, JAN-8, POSTNET and FIM, MSI/Plessey, and Pharmacode) ','N',0,0,'Bar Code (Universal Product Code or its superset European Article Number)','UPC/EAN','Y',30,'N',10,TO_TIMESTAMP('2009-12-01 16:57:39','YYYY-MM-DD HH24:MI:SS'),'Y','N',603,100,'N','UPC','N')
;

-- Dec 1, 2009 4:57:39 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58677 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:40 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58678,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:39','YYYY-MM-DD HH24:MI:SS'),'The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','N',0,0,'Stock Keeping Unit','SKU','Y',30,'N',10,TO_TIMESTAMP('2009-12-01 16:57:39','YYYY-MM-DD HH24:MI:SS'),'Y','N',549,100,'N','SKU','N')
;

-- Dec 1, 2009 4:57:40 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58678 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:40 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58679,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:40','YYYY-MM-DD HH24:MI:SS'),'The UOM defines a unique non monetary Unit of Measure','N',0,0,'Unit of Measure','UOM','Y',10,'N',19,TO_TIMESTAMP('2009-12-01 16:57:40','YYYY-MM-DD HH24:MI:SS'),'Y','N',215,100,'N','C_UOM_ID','N')
;

-- Dec 1, 2009 4:57:40 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58679 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:41 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58680,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:40','YYYY-MM-DD HH24:MI:SS'),'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','N',0,0,'Category of a Product','Product Category','Y',10,'N',19,TO_TIMESTAMP('2009-12-01 16:57:40','YYYY-MM-DD HH24:MI:SS'),'Y','N',453,100,'N','M_Product_Category_ID','N')
;

-- Dec 1, 2009 4:57:41 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58680 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:42 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58681,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:41','YYYY-MM-DD HH24:MI:SS'),'The Classification can be used to optionally group products.','N',0,0,'Classification for grouping','Classification','Y',12,'N',10,TO_TIMESTAMP('2009-12-01 16:57:41','YYYY-MM-DD HH24:MI:SS'),'Y','N',852,100,'N','Classification','N')
;

-- Dec 1, 2009 4:57:42 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58681 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:42 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58682,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:42','YYYY-MM-DD HH24:MI:SS'),'The Weight indicates the weight  of the product in the Weight UOM of the Client','N',0,0,'Weight of a product','Weight','Y',131089,'N',22,TO_TIMESTAMP('2009-12-01 16:57:42','YYYY-MM-DD HH24:MI:SS'),'Y','N',629,100,'N','Weight','N')
;

-- Dec 1, 2009 4:57:42 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58682 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:43 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58683,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:42','YYYY-MM-DD HH24:MI:SS'),'The Volume indicates the volume of the product in the Volume UOM of the Client','N',0,0,'Volume of a product','Volume','Y',131089,'N',22,TO_TIMESTAMP('2009-12-01 16:57:42','YYYY-MM-DD HH24:MI:SS'),'Y','N',627,100,'N','Volume','N')
;

-- Dec 1, 2009 4:57:43 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58683 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:44 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58684,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:43','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'Version Number','Version No','Y',20,'N',10,TO_TIMESTAMP('2009-12-01 16:57:43','YYYY-MM-DD HH24:MI:SS'),'Y','N',1949,100,'N','VersionNo','N')
;

-- Dec 1, 2009 4:57:44 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58684 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:44 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58685,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:44','YYYY-MM-DD HH24:MI:SS'),'If the value is 0, there is no limit to the availability or guarantee, otherwise the guarantee date is calculated by adding the days to the delivery date.','N',0,0,'Number of days the product is guaranteed or available','Guarantee Days','Y',10,'N',11,TO_TIMESTAMP('2009-12-01 16:57:44','YYYY-MM-DD HH24:MI:SS'),'Y','N',1937,100,'N','GuaranteeDays','N')
;

-- Dec 1, 2009 4:57:44 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58685 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:45 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58686,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:44','YYYY-MM-DD HH24:MI:SS'),'When selecting batch/products with a guarantee date, the minimum left guarantee days for automatic picking.  You can pick any batch/product manually. ','N',0,0,'Minumum number of guarantee days','Min Guarantee Days','Y',10,'N',11,TO_TIMESTAMP('2009-12-01 16:57:44','YYYY-MM-DD HH24:MI:SS'),'Y','N',2197,100,'N','GuaranteeDaysMin','N')
;

-- Dec 1, 2009 4:57:45 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58686 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:57:45 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54087,0,'sumqtyonhand',TO_TIMESTAMP('2009-12-01 16:57:45','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','sumqtyonhand','sumqtyonhand',TO_TIMESTAMP('2009-12-01 16:57:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 1, 2009 4:57:45 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54087 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 1, 2009 4:57:46 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,CreatedBy,Updated,IsParent,AD_Client_ID,AD_Org_ID,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsAlwaysUpdateable,ColumnName,IsEncrypted) VALUES (58687,53247,'D',0,'N','N','N',100,TO_TIMESTAMP('2009-12-01 16:57:45','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'sumqtyonhand','Y',131089,'N',29,TO_TIMESTAMP('2009-12-01 16:57:45','YYYY-MM-DD HH24:MI:SS'),'Y','N',54087,100,'N','sumqtyonhand','N')
;

-- Dec 1, 2009 4:57:46 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58687 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 1, 2009 4:59:05 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_Element SET ColumnName='SumQtyOnHand', Description='Summary of product on hand in all locators', Name='Sum Qty on Hand', PrintName='Sum Qty on Hand',Updated=TO_TIMESTAMP('2009-12-01 16:59:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54087
;

-- Dec 1, 2009 4:59:05 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54087
;

-- Dec 1, 2009 4:59:05 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_Column SET ColumnName='SumQtyOnHand', Name='Sum Qty on Hand', Description='Summary of product on hand in all locators', Help=NULL WHERE AD_Element_ID=54087
;

-- Dec 1, 2009 4:59:05 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_Process_Para SET ColumnName='SumQtyOnHand', Name='Sum Qty on Hand', Description='Summary of product on hand in all locators', Help=NULL, AD_Element_ID=54087 WHERE UPPER(ColumnName)='SUMQTYONHAND' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 1, 2009 4:59:05 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_Process_Para SET ColumnName='SumQtyOnHand', Name='Sum Qty on Hand', Description='Summary of product on hand in all locators', Help=NULL WHERE AD_Element_ID=54087 AND IsCentrallyMaintained='Y'
;

-- Dec 1, 2009 4:59:05 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_Field SET Name='Sum Qty on Hand', Description='Summary of product on hand in all locators', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54087) AND IsCentrallyMaintained='Y'
;

-- Dec 1, 2009 4:59:05 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_PrintFormatItem SET PrintName='Sum Qty on Hand', Name='Sum Qty on Hand' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=54087)
;

-- Dec 1, 2009 5:01:19 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53031,53247,TO_TIMESTAMP('2009-12-01 17:01:18','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','RV_Storage_Per_Product',TO_TIMESTAMP('2009-12-01 17:01:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 1, 2009 5:03:04 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_ReportView_ID,CopyFromProcess,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('3',0,0,53189,53031,'N',TO_TIMESTAMP('2009-12-01 17:02:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','Y','N','Storage per Product','Y',0,0,TO_TIMESTAMP('2009-12-01 17:02:59','YYYY-MM-DD HH24:MI:SS'),100,'RV_Storage_Per_Product')
;

-- Dec 1, 2009 5:03:04 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53189 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Dec 1, 2009 5:05:02 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,453,0,53189,53362,19,'M_Product_Category_ID',TO_TIMESTAMP('2009-12-01 17:05:01','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','Product Category',10,TO_TIMESTAMP('2009-12-01 17:05:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 1, 2009 5:05:02 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53362 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 1, 2009 5:05:40 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53189,53363,19,'M_Product_ID',TO_TIMESTAMP('2009-12-01 17:05:40','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','Product',20,TO_TIMESTAMP('2009-12-01 17:05:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 1, 2009 5:05:40 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53363 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 1, 2009 5:07:30 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('R',0,53253,0,53189,TO_TIMESTAMP('2009-12-01 17:07:29','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','Storage per Product',TO_TIMESTAMP('2009-12-01 17:07:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 1, 2009 5:07:30 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53253 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Dec 1, 2009 5:07:30 PM CET
-- RV_STORAGE_PER_PRODUCT
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53253, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53253)
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53242
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53253
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=167
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=357
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=229
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=412
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=256
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=197
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=477
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=179
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=503
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=196
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=228
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=479
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=482
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=481
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=411
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=537
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=311
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=292
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=19, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=504
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=20, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=515
;

-- Dec 1, 2009 5:07:37 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=21, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=545
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=167
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=357
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=229
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=412
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=256
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=197
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=477
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=179
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=503
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=196
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=228
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=479
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=482
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=481
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=411
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53253
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=537
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=311
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=292
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=19, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=504
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=20, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=515
;

-- Dec 1, 2009 5:07:57 PM CET
-- RV_STORAGE_PER_PRODUCT
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=21, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=545
;

