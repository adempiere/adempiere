-- Jun 23, 2008 9:58:30 AM EST
-- RV_PP_Order_BOMLine dictionary columns
UPDATE AD_Table SET IsView='Y',Updated=TO_TIMESTAMP('2008-06-23 09:58:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53028
;

-- Jun 23, 2008 9:59:10 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56089,102,0,19,53028,'AD_Client_ID',TO_TIMESTAMP('2008-06-23 09:59:02','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','Client',TO_TIMESTAMP('2008-06-23 09:59:02','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 9:59:10 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56089 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 9:59:11 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56090,113,0,19,53028,'AD_Org_ID',TO_TIMESTAMP('2008-06-23 09:59:10','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','Organization',TO_TIMESTAMP('2008-06-23 09:59:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 9:59:11 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56090 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 9:59:11 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56091,246,0,18,110,53028,'CreatedBy',TO_TIMESTAMP('2008-06-23 09:59:11','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','Created By',TO_TIMESTAMP('2008-06-23 09:59:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 9:59:11 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56091 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:31 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56092,608,0,18,110,53028,'UpdatedBy',TO_TIMESTAMP('2008-06-23 11:04:30','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','Updated By',TO_TIMESTAMP('2008-06-23 11:04:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:31 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56092 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:31 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56093,607,0,16,53028,'Updated',TO_TIMESTAMP('2008-06-23 11:04:31','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','Updated',TO_TIMESTAMP('2008-06-23 11:04:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:31 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56093 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:32 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56094,245,0,16,53028,'Created',TO_TIMESTAMP('2008-06-23 11:04:32','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','Created',TO_TIMESTAMP('2008-06-23 11:04:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:32 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56094 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:33 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56095,348,0,20,53028,'IsActive',TO_TIMESTAMP('2008-06-23 11:04:32','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Active',TO_TIMESTAMP('2008-06-23 11:04:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:33 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56095 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:34 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56096,53298,0,19,53028,'PP_Order_BOM_ID',TO_TIMESTAMP('2008-06-23 11:04:33','YYYY-MM-DD HH24:MI:SS'),100,'EE01',10,'Y','N','N','N','N','Y','N','N','N','N','PP_Order_BOM_ID',TO_TIMESTAMP('2008-06-23 11:04:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:34 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56096 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:35 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56097,53275,0,19,53028,'PP_Order_BOMLine_ID',TO_TIMESTAMP('2008-06-23 11:04:34','YYYY-MM-DD HH24:MI:SS'),100,'EE01',10,'Y','N','N','N','N','Y','N','N','N','N','PP_Order_BOMLine_ID',TO_TIMESTAMP('2008-06-23 11:04:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:35 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56097 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:36 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56098,53276,0,19,53028,'PP_Order_ID',TO_TIMESTAMP('2008-06-23 11:04:35','YYYY-MM-DD HH24:MI:SS'),100,'EE01',10,'Y','N','N','N','N','Y','N','N','N','N','PP_Order_ID',TO_TIMESTAMP('2008-06-23 11:04:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:36 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56098 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:37 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56099,53251,0,20,53028,'IsCritical',TO_TIMESTAMP('2008-06-23 11:04:36','YYYY-MM-DD HH24:MI:SS'),100,'Indicate that a Manufacturing Order can not begin without have this component','EE01',1,'Indicate that a Manufacturing Order can not begin without have this component','Y','N','N','N','N','Y','N','N','N','N','Is Critical Component',TO_TIMESTAMP('2008-06-23 11:04:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:37 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56099 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:38 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56100,53249,0,17,53028,'ComponentType',TO_TIMESTAMP('2008-06-23 11:04:37','YYYY-MM-DD HH24:MI:SS'),100,'Component Type for a Bill of Material or Formula','EE01',2,'The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
','Y','N','N','N','N','N','N','N','N','N','Component Type',TO_TIMESTAMP('2008-06-23 11:04:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:38 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56100 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:39 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56101,454,0,19,53028,'M_Product_ID',TO_TIMESTAMP('2008-06-23 11:04:38','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','Y','N','N','N','N','Product',TO_TIMESTAMP('2008-06-23 11:04:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:39 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56101 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:40 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56102,215,0,19,53028,'C_UOM_ID',TO_TIMESTAMP('2008-06-23 11:04:39','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','Y','N','N','N','N','UOM',TO_TIMESTAMP('2008-06-23 11:04:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:40 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56102 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:41 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56103,53288,0,29,53028,'QtyRequiered',TO_TIMESTAMP('2008-06-23 11:04:40','YYYY-MM-DD HH24:MI:SS'),100,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','QtyRequiered',TO_TIMESTAMP('2008-06-23 11:04:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:41 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56103 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:42 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56104,532,0,29,53028,'QtyReserved',TO_TIMESTAMP('2008-06-23 11:04:41','YYYY-MM-DD HH24:MI:SS'),100,'Reserved Quantity','EE01',22,'The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','N','N','N','N','N','N','N','N','N','Reserved Quantity',TO_TIMESTAMP('2008-06-23 11:04:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:42 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56104 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:43 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56105,2238,0,29,53028,'QtyAvailable',TO_TIMESTAMP('2008-06-23 11:04:42','YYYY-MM-DD HH24:MI:SS'),100,'Available Quantity (On Hand - Reserved)','EE01',22,'Quantity available to promise = On Hand minus Reserved Quantity','Y','N','N','N','N','N','N','N','N','N','Available Quantity',TO_TIMESTAMP('2008-06-23 11:04:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:43 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56105 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:44 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56106,530,0,29,53028,'QtyOnHand',TO_TIMESTAMP('2008-06-23 11:04:43','YYYY-MM-DD HH24:MI:SS'),100,'On Hand Quantity','EE01',22,'The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.','Y','N','N','N','N','N','N','N','N','N','On Hand Quantity',TO_TIMESTAMP('2008-06-23 11:04:43','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:44 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56106 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:45 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56107,459,0,19,53028,'M_Warehouse_ID',TO_TIMESTAMP('2008-06-23 11:04:44','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','Y','N','N','N','N','Warehouse',TO_TIMESTAMP('2008-06-23 11:04:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:45 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56107 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:45 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56108,53255,0,29,53028,'QtyBOM',TO_TIMESTAMP('2008-06-23 11:04:45','YYYY-MM-DD HH24:MI:SS'),100,'Indicate the Quantity  use in this BOM','EE01',22,'Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','N','N','N','N','N','N','N','N','N','Quantity',TO_TIMESTAMP('2008-06-23 11:04:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:45 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56108 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:46 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56109,53252,0,29,53028,'IsQtyPercentage',TO_TIMESTAMP('2008-06-23 11:04:45','YYYY-MM-DD HH24:MI:SS'),100,'Indicate that this component is based in % Quantity','EE01',1,'Indicate that this component is based in % Quantity','Y','N','N','N','N','N','N','N','N','N','Is Qty Percentage',TO_TIMESTAMP('2008-06-23 11:04:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:46 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56109 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:47 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56110,53256,0,29,53028,'QtyBatch',TO_TIMESTAMP('2008-06-23 11:04:46','YYYY-MM-DD HH24:MI:SS'),100,'Indicate the Quantity % use in this Formula','EE01',22,'Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','N','N','N','N','N','N','N','N','N','Quantity %',TO_TIMESTAMP('2008-06-23 11:04:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:47 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56110 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:04:48 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56111,53243,0,29,53028,'QtyBatchSize',TO_TIMESTAMP('2008-06-23 11:04:47','YYYY-MM-DD HH24:MI:SS'),100,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','QtyBatchSize',TO_TIMESTAMP('2008-06-23 11:04:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 23, 2008 11:04:48 AM EST
-- RV_PP_Order_BOMLine dictionary columns
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56111 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 23, 2008 11:15:46 AM EST
-- RV_PP_Order_BOMLine dictionary columns
UPDATE AD_Element SET PrintName='QtyRequired',Updated=TO_TIMESTAMP('2008-06-23 11:15:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53288
;

-- Jun 23, 2008 11:15:47 AM EST
-- RV_PP_Order_BOMLine dictionary columns
UPDATE AD_PrintFormatItem SET PrintName='QtyRequired', Name='QtyRequiered' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53288)
;
