
-- 08/07/2011 2:31:39 PM
-- --
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsCentrallyMaintained,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('3',0,0,53325,123,'N',TO_TIMESTAMP('2011-07-08 14:31:36','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner specific pricing that will override the price list','D','N','Y','Y','N','Y','N','N','N',0,'Business Partner Price','L','M_BP_Price',TO_TIMESTAMP('2011-07-08 14:31:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 08/07/2011 2:31:39 PM
-- --
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53325 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- 08/07/2011 2:31:41 PM
-- --
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53436,TO_TIMESTAMP('2011-07-08 14:31:39','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table M_BP_Price',1,'Y','N','Y','Y','M_BP_Price','N',1000000,TO_TIMESTAMP('2011-07-08 14:31:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 08/07/2011 2:31:53 PM
-- --
INSERT INTO AD_Column (DefaultValue,AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,AD_Val_Rule_ID,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES ('@AD_Client_ID@',61824,53325,'D',1,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:31:51','YYYY-MM-DD HH24:MI:SS'),'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','N',0,0,'Y',22,'N',19,'N',TO_TIMESTAMP('2011-07-08 14:31:51','YYYY-MM-DD HH24:MI:SS'),129,'N',102,100,'N','N','N','AD_Client_ID','Client/Tenant for this installation.','Client')
;

-- 08/07/2011 2:31:53 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61824 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:31:54 PM
-- --
INSERT INTO AD_Column (DefaultValue,AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,AD_Val_Rule_ID,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES ('@AD_Org_ID@',61825,53325,'D',1,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:31:53','YYYY-MM-DD HH24:MI:SS'),'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','N',0,0,'Y',22,'N',19,'N',TO_TIMESTAMP('2011-07-08 14:31:53','YYYY-MM-DD HH24:MI:SS'),104,'N',113,100,'N','N','N','AD_Org_ID','Organizational entity within client','Organization')
;

-- 08/07/2011 2:31:54 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61825 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:31:56 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61826,53325,'D',0,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:31:54','YYYY-MM-DD HH24:MI:SS'),'Starting Quantity or Amount Value for break level','N',0,0,'Y',22,'N',22,'N',TO_TIMESTAMP('2011-07-08 14:31:54','YYYY-MM-DD HH24:MI:SS'),'N',1708,100,'N','N','N','BreakValue','Low Value of trade discount break level','Break Value')
;

-- 08/07/2011 2:31:56 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61826 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:31:58 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,AD_Reference_Value_ID,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61827,53325,'D',0,138,'Y','N','Y',0,100,TO_TIMESTAMP('2011-07-08 14:31:56','YYYY-MM-DD HH24:MI:SS'),'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','N',0,0,'Y',10,'N',30,'N',TO_TIMESTAMP('2011-07-08 14:31:56','YYYY-MM-DD HH24:MI:SS'),'N',187,100,'N','N','N','C_BPartner_ID','Identifies a Business Partner','Business Partner ')
;

-- 08/07/2011 2:31:58 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61827 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:31:59 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61828,53325,'D',0,'N','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:31:58','YYYY-MM-DD HH24:MI:SS'),'The Comments field allows for free form entry of additional information.','N',0,0,'Y',2000,'N',14,'N',TO_TIMESTAMP('2011-07-08 14:31:58','YYYY-MM-DD HH24:MI:SS'),'N',230,100,'N','Y','N','Comments','Comments or additional information','Comments')
;

-- 08/07/2011 2:31:59 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61828 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:01 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61829,53325,'D',1,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:31:59','YYYY-MM-DD HH24:MI:SS'),'The Created field indicates the date that this record was created.','N',0,0,'Y',7,'N',16,'N',TO_TIMESTAMP('2011-07-08 14:31:59','YYYY-MM-DD HH24:MI:SS'),'N',245,100,'N','N','N','Created','Date this record was created','Created')
;

-- 08/07/2011 2:32:01 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61829 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:02 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,AD_Reference_Value_ID,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61830,53325,'D',1,110,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:01','YYYY-MM-DD HH24:MI:SS'),'The Created By field indicates the user who created this record.','N',0,0,'Y',22,'N',18,'N',TO_TIMESTAMP('2011-07-08 14:32:01','YYYY-MM-DD HH24:MI:SS'),'N',246,100,'N','N','N','CreatedBy','User who created this records','Created By')
;

-- 08/07/2011 2:32:02 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61830 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:04 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61831,53325,'D',0,'N','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:02','YYYY-MM-DD HH24:MI:SS'),'The Discount indicates the discount applied or taken as a percentage.','N',0,0,'Y',10,'N',12,'N',TO_TIMESTAMP('2011-07-08 14:32:02','YYYY-MM-DD HH24:MI:SS'),'N',280,100,'N','Y','N','Discount','Discount in percent','Discount %')
;

-- 08/07/2011 2:32:04 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61831 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:05 PM
-- --
INSERT INTO AD_Column (DefaultValue,AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES ('Y',61832,53325,'D',1,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:04','YYYY-MM-DD HH24:MI:SS'),'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','N',0,0,'Y',1,'N',20,'N',TO_TIMESTAMP('2011-07-08 14:32:04','YYYY-MM-DD HH24:MI:SS'),'N',348,100,'N','Y','N','IsActive','The record is active in the system','Active')
;

-- 08/07/2011 2:32:05 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61832 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:07 PM
-- --
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55222,0,'M_BP_Price_ID',TO_TIMESTAMP('2011-07-08 14:32:06','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Business Partner Price','Business Partner Price',TO_TIMESTAMP('2011-07-08 14:32:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 08/07/2011 2:32:07 PM
-- --
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55222 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 08/07/2011 2:32:08 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Name) VALUES (61833,53325,'D',0,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:05','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'Y',22,'N',13,'N',TO_TIMESTAMP('2011-07-08 14:32:05','YYYY-MM-DD HH24:MI:SS'),'Y',55222,100,'N','N','N','M_BP_Price_ID','Business Partner Price')
;

-- 08/07/2011 2:32:08 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61833 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:10 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,AD_Val_Rule_ID,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61834,53325,'D',1,'Y','N','Y',1,100,TO_TIMESTAMP('2011-07-08 14:32:08','YYYY-MM-DD HH24:MI:SS'),'Identifies an item which is either purchased or sold in this organization.','N',0,0,'Y',22,'N',30,'N',TO_TIMESTAMP('2011-07-08 14:32:08','YYYY-MM-DD HH24:MI:SS'),231,'N',454,100,'N','N','N','M_Product_ID','Product, Service, Item','Product')
;

-- 08/07/2011 2:32:10 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61834 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:11 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61835,53325,'D',1,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:10','YYYY-MM-DD HH24:MI:SS'),'The Price Limit indicates the lowest price for a product stated in the Price List Currency.','N',0,0,'Y',22,'N',37,'N',TO_TIMESTAMP('2011-07-08 14:32:10','YYYY-MM-DD HH24:MI:SS'),'N',955,100,'N','Y','N','PriceLimit','Lowest price for a product','Limit Price')
;

-- 08/07/2011 2:32:11 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61835 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:13 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61836,53325,'D',1,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:11','YYYY-MM-DD HH24:MI:SS'),'The List Price is the official List Price in the document currency.','N',0,0,'Y',22,'N',37,'N',TO_TIMESTAMP('2011-07-08 14:32:11','YYYY-MM-DD HH24:MI:SS'),'N',520,100,'N','Y','N','PriceList','List Price','List Price')
;

-- 08/07/2011 2:32:13 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61836 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:15 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61837,53325,'D',1,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:13','YYYY-MM-DD HH24:MI:SS'),'The Standard Price indicates the standard or normal price for a product on this price list','N',0,0,'Y',22,'N',37,'N',TO_TIMESTAMP('2011-07-08 14:32:13','YYYY-MM-DD HH24:MI:SS'),'N',957,100,'N','Y','N','PriceStd','Standard Price','Standard Price')
;

-- 08/07/2011 2:32:15 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61837 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:16 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61838,53325,'D',1,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:15','YYYY-MM-DD HH24:MI:SS'),'The Updated field indicates the date that this record was updated.','N',0,0,'Y',7,'N',16,'N',TO_TIMESTAMP('2011-07-08 14:32:15','YYYY-MM-DD HH24:MI:SS'),'N',607,100,'N','N','N','Updated','Date this record was updated','Updated')
;

-- 08/07/2011 2:32:16 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61838 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:18 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,AD_Reference_Value_ID,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61839,53325,'D',1,110,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:16','YYYY-MM-DD HH24:MI:SS'),'The Updated By field indicates the user who updated this record.','N',0,0,'Y',22,'N',18,'N',TO_TIMESTAMP('2011-07-08 14:32:16','YYYY-MM-DD HH24:MI:SS'),'N',608,100,'N','N','N','UpdatedBy','User who updated this records','Updated By')
;

-- 08/07/2011 2:32:18 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61839 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:19 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61840,53325,'D',0,'N','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:18','YYYY-MM-DD HH24:MI:SS'),'The Valid From date indicates the first day of a date range','N',0,0,'Y',7,'N',15,'N',TO_TIMESTAMP('2011-07-08 14:32:18','YYYY-MM-DD HH24:MI:SS'),'N',617,100,'N','Y','N','ValidFrom','Valid from including this date (first day)','Valid from')
;

-- 08/07/2011 2:32:19 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61840 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:32:21 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61841,53325,'D',0,'N','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:32:19','YYYY-MM-DD HH24:MI:SS'),'The Valid To date indicates the last day of a date range','N',0,0,'Y',7,'N',15,'N',TO_TIMESTAMP('2011-07-08 14:32:19','YYYY-MM-DD HH24:MI:SS'),'N',618,100,'N','Y','N','ValidTo','Valid to including this date (last day)','Valid to')
;

-- 08/07/2011 2:32:21 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61841 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 2:42:48 PM
-- --
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55223,0,'PriceOverrideType',TO_TIMESTAMP('2011-07-08 14:42:46','YYYY-MM-DD HH24:MI:SS'),100,'Type of price override, fixed price or discount off list','D','Y','Price Override Type','Price Override Type',TO_TIMESTAMP('2011-07-08 14:42:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 08/07/2011 2:42:48 PM
-- --
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55223 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 08/07/2011 2:43:43 PM
-- --
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53410,TO_TIMESTAMP('2011-07-08 14:43:41','YYYY-MM-DD HH24:MI:SS'),100,'Price Override Type','D','Fixed price or discount off list','Y','N','PriceOverrideType',TO_TIMESTAMP('2011-07-08 14:43:41','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- 08/07/2011 2:43:43 PM
-- --
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53410 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 08/07/2011 2:44:28 PM
-- --
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53410,53703,TO_TIMESTAMP('2011-07-08 14:44:27','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Fixed Price',TO_TIMESTAMP('2011-07-08 14:44:27','YYYY-MM-DD HH24:MI:SS'),100,'P')
;

-- 08/07/2011 2:44:28 PM
-- --
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53703 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- 08/07/2011 2:44:44 PM
-- --
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53410,53704,TO_TIMESTAMP('2011-07-08 14:44:43','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Discount',TO_TIMESTAMP('2011-07-08 14:44:43','YYYY-MM-DD HH24:MI:SS'),100,'D')
;

-- 08/07/2011 2:44:44 PM
-- --
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53704 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- 08/07/2011 2:45:22 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,AD_Reference_Value_ID,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsAutocomplete,IsAllowLogging,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61842,53325,'D',0,53410,'N','N','N',0,100,TO_TIMESTAMP('2011-07-08 14:45:20','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'Y',1,'N',17,'N',TO_TIMESTAMP('2011-07-08 14:45:20','YYYY-MM-DD HH24:MI:SS'),'N',55223,100,'N','Y','N','Y','N','PriceOverrideType','Type of price override, fixed price or discount off list','Price Override Type')
;

-- 08/07/2011 2:45:22 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61842 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 3:58:11 PM
-- --
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55224,0,'IsNetPrice',TO_TIMESTAMP('2011-07-08 15:58:09','YYYY-MM-DD HH24:MI:SS'),100,'Net Price including all discounts','D','If price is set as "Net Price" no further discounts will be applied.','Y','Net Price','Net Price',TO_TIMESTAMP('2011-07-08 15:58:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 08/07/2011 3:58:11 PM
-- --
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55224 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 08/07/2011 3:58:35 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsAutocomplete,IsAllowLogging,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61843,53325,'D',0,'N','N','N',0,100,TO_TIMESTAMP('2011-07-08 15:58:33','YYYY-MM-DD HH24:MI:SS'),'If price is set as "Net Price" no further discounts will be applied.','N',0,0,'Y',1,'N',20,'N',TO_TIMESTAMP('2011-07-08 15:58:33','YYYY-MM-DD HH24:MI:SS'),'N',55224,100,'N','Y','N','Y','N','IsNetPrice','Net Price including all discounts','Net Price')
;

-- 08/07/2011 3:58:35 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61843 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 08/07/2011 4:09:07 PM
-- --
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsAutocomplete,IsAllowLogging,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES (61844,53325,'D',0,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-08 16:09:05','YYYY-MM-DD HH24:MI:SS'),'Indicates the Currency to be used when processing or reporting on this record','N',0,0,'Y',22,'N',19,'N',TO_TIMESTAMP('2011-07-08 16:09:05','YYYY-MM-DD HH24:MI:SS'),'N',193,100,'N','Y','N','Y','N','C_Currency_ID','The Currency for this record','Currency')
;

-- 08/07/2011 4:09:07 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61844 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 11/07/2011 2:48:11 PM
-- --
INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,61827,0,53415,53325,123,TO_TIMESTAMP('2011-07-11 14:48:07','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner specific prices','D','N','Prices and discounts specified here will override the selected price list price and discount schema discount.','N','Y','N','N','Y','N','N','N','N','Price','N',120,1,TO_TIMESTAMP('2011-07-11 14:48:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:11 PM
-- --
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53415 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- 11/07/2011 2:48:15 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61832,61882,0,53415,TO_TIMESTAMP('2011-07-11 14:48:14','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_TIMESTAMP('2011-07-11 14:48:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:15 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61882 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:17 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61826,61883,0,53415,TO_TIMESTAMP('2011-07-11 14:48:15','YYYY-MM-DD HH24:MI:SS'),100,'Low Value of trade discount break level',22,'D','Starting Quantity or Amount Value for break level','Y','Y','Y','N','N','N','N','N','Break Value',TO_TIMESTAMP('2011-07-11 14:48:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:17 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61883 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:18 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61827,61884,0,53415,TO_TIMESTAMP('2011-07-11 14:48:17','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',10,'D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','N','Business Partner ',TO_TIMESTAMP('2011-07-11 14:48:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:18 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61884 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:20 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61833,61885,0,53415,TO_TIMESTAMP('2011-07-11 14:48:18','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','N','Business Partner Price',TO_TIMESTAMP('2011-07-11 14:48:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:20 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61885 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:21 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61824,61886,0,53415,TO_TIMESTAMP('2011-07-11 14:48:20','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2011-07-11 14:48:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:21 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61886 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:23 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61828,61887,0,53415,TO_TIMESTAMP('2011-07-11 14:48:21','YYYY-MM-DD HH24:MI:SS'),100,'Comments or additional information',2000,'D','The Comments field allows for free form entry of additional information.','Y','Y','Y','N','N','N','N','N','Comments',TO_TIMESTAMP('2011-07-11 14:48:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:23 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61887 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:25 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61831,61888,0,53415,TO_TIMESTAMP('2011-07-11 14:48:23','YYYY-MM-DD HH24:MI:SS'),100,'Discount in percent',10,'D','The Discount indicates the discount applied or taken as a percentage.','Y','Y','Y','N','N','N','N','N','Discount %',TO_TIMESTAMP('2011-07-11 14:48:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:25 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61888 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:26 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61835,61889,0,53415,TO_TIMESTAMP('2011-07-11 14:48:25','YYYY-MM-DD HH24:MI:SS'),100,'Lowest price for a product',22,'D','The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','Y','Y','N','N','N','N','N','Limit Price',TO_TIMESTAMP('2011-07-11 14:48:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:26 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61889 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:28 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61836,61890,0,53415,TO_TIMESTAMP('2011-07-11 14:48:26','YYYY-MM-DD HH24:MI:SS'),100,'List Price',22,'D','The List Price is the official List Price in the document currency.','Y','Y','Y','N','N','N','N','N','List Price',TO_TIMESTAMP('2011-07-11 14:48:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:28 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61890 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:29 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61825,61891,0,53415,TO_TIMESTAMP('2011-07-11 14:48:28','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2011-07-11 14:48:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:29 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61891 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:31 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61842,61892,0,53415,TO_TIMESTAMP('2011-07-11 14:48:29','YYYY-MM-DD HH24:MI:SS'),100,'Type of price override, fixed price or discount off list',1,'D','Y','Y','Y','N','N','N','N','N','Price Override Type',TO_TIMESTAMP('2011-07-11 14:48:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:31 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61892 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:32 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61834,61893,0,53415,TO_TIMESTAMP('2011-07-11 14:48:31','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',22,'D','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','N','Product',TO_TIMESTAMP('2011-07-11 14:48:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:32 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61893 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:34 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61837,61894,0,53415,TO_TIMESTAMP('2011-07-11 14:48:32','YYYY-MM-DD HH24:MI:SS'),100,'Standard Price',22,'D','The Standard Price indicates the standard or normal price for a product on this price list','Y','Y','Y','N','N','N','N','N','Standard Price',TO_TIMESTAMP('2011-07-11 14:48:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:34 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61894 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:35 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61840,61895,0,53415,TO_TIMESTAMP('2011-07-11 14:48:34','YYYY-MM-DD HH24:MI:SS'),100,'Valid from including this date (first day)',7,'D','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','N','N','Valid from',TO_TIMESTAMP('2011-07-11 14:48:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:35 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61895 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:48:36 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61841,61896,0,53415,TO_TIMESTAMP('2011-07-11 14:48:35','YYYY-MM-DD HH24:MI:SS'),100,'Valid to including this date (last day)',7,'D','The Valid To date indicates the last day of a date range','Y','Y','Y','N','N','N','N','N','Valid to',TO_TIMESTAMP('2011-07-11 14:48:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 2:48:36 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61896 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=61886
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=61891
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=61884
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=61893
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=61882
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=61895
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=61896
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=61887
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=61892
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=61883
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=61888
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=61890
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=61894
;

-- 11/07/2011 2:50:24 PM
-- --
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=61889
;

-- 11/07/2011 2:50:32 PM
-- --
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2011-07-11 14:50:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61886
;

-- 11/07/2011 2:50:51 PM
-- --
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2011-07-11 14:50:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61891
;

-- 11/07/2011 2:51:02 PM
-- --
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2011-07-11 14:51:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61893
;

-- 11/07/2011 2:51:10 PM
-- --
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2011-07-11 14:51:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61896
;

-- 11/07/2011 2:51:31 PM
-- --
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2011-07-11 14:51:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61883
;

-- 11/07/2011 2:51:57 PM
-- --
UPDATE AD_Field SET DisplayLogic='@PriceOverrideType@=''D''',Updated=TO_TIMESTAMP('2011-07-11 14:51:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61888
;

-- 11/07/2011 2:52:04 PM
-- --
UPDATE AD_Field SET DisplayLogic='@PriceOverrideType@=''D''',Updated=TO_TIMESTAMP('2011-07-11 14:52:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61890
;

-- 11/07/2011 2:52:15 PM
-- --
UPDATE AD_Field SET DisplayLogic='@PriceOverrideType@=''P''',Updated=TO_TIMESTAMP('2011-07-11 14:52:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61890
;

-- 11/07/2011 2:52:22 PM
-- --
UPDATE AD_Field SET DisplayLogic='@PriceOverrideType@=''P''',Updated=TO_TIMESTAMP('2011-07-11 14:52:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61894
;

-- 11/07/2011 2:52:29 PM
-- --
UPDATE AD_Field SET DisplayLogic='@PriceOverrideType@=''P''',Updated=TO_TIMESTAMP('2011-07-11 14:52:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61889
;

-- 11/07/2011 3:05:58 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61844,61897,0,53415,TO_TIMESTAMP('2011-07-11 15:05:57','YYYY-MM-DD HH24:MI:SS'),100,'The Currency for this record',22,'D','Indicates the Currency to be used when processing or reporting on this record','Y','Y','Y','N','N','N','N','N','Currency',TO_TIMESTAMP('2011-07-11 15:05:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 3:05:58 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61897 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 3:06:24 PM
-- --
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=61897
;

-- 11/07/2011 3:06:24 PM
-- --
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=61890
;

-- 11/07/2011 3:06:24 PM
-- --
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=61894
;

-- 11/07/2011 3:06:24 PM
-- --
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=61889
;

-- 11/07/2011 3:06:44 PM
-- --
UPDATE AD_Field SET DisplayLogic='@PriceOverrideType@=''P''',Updated=TO_TIMESTAMP('2011-07-11 15:06:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61897
;

-- 11/07/2011 3:06:47 PM
-- --
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2011-07-11 15:06:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61890
;

-- 11/07/2011 3:06:51 PM
-- --
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2011-07-11 15:06:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61889
;

-- 11/07/2011 3:08:05 PM
-- --
INSERT INTO AD_Column (DefaultValue,AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,IsSyncDatabase,Created,IsKey,AD_Element_ID,UpdatedBy,IsAutocomplete,IsAllowLogging,IsEncrypted,IsUpdateable,IsAlwaysUpdateable,ColumnName,Description,Name) VALUES ('N',61846,255,'D',0,'Y','N','N',0,100,TO_TIMESTAMP('2011-07-11 15:08:03','YYYY-MM-DD HH24:MI:SS'),'If price is set as "Net Price" no further discounts will be applied.','N',0,0,'Y',1,'N',20,'N',TO_TIMESTAMP('2011-07-11 15:08:03','YYYY-MM-DD HH24:MI:SS'),'N',55224,100,'N','Y','N','Y','N','IsNetPrice','Net Price including all discounts','Net Price')
;

-- 11/07/2011 3:08:05 PM
-- --
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61846 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 11/07/2011 3:08:15 PM
-- --
ALTER TABLE M_PriceList ADD COLUMN IsNetPrice CHAR(1) DEFAULT 'N' CHECK (IsNetPrice IN ('Y','N')) NOT NULL
;

-- 11/07/2011 3:08:50 PM
-- --
CREATE TABLE M_BP_Price (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, BreakValue NUMERIC NOT NULL, C_BPartner_ID NUMERIC(10) NOT NULL, C_Currency_ID NUMERIC(10) NOT NULL, Comments VARCHAR(2000) DEFAULT NULL , Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Discount NUMERIC DEFAULT NULL , IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, M_BP_Price_ID NUMERIC(10) NOT NULL, M_Product_ID NUMERIC(10) NOT NULL, PriceLimit NUMERIC NOT NULL, PriceList NUMERIC NOT NULL, PriceOverrideType CHAR(1) DEFAULT NULL , PriceStd NUMERIC NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, ValidFrom TIMESTAMP DEFAULT NULL , ValidTo TIMESTAMP DEFAULT NULL , CONSTRAINT M_BP_Price_Key PRIMARY KEY (M_BP_Price_ID))
;

-- 11/07/2011 3:29:41 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,52071,61898,0,191,TO_TIMESTAMP('2011-07-11 15:29:39','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','isPresentForProduct',TO_TIMESTAMP('2011-07-11 15:29:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 3:29:41 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61898 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 3:29:42 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,52072,61899,0,191,TO_TIMESTAMP('2011-07-11 15:29:41','YYYY-MM-DD HH24:MI:SS'),100,'Data entry is required in this column',1,'D','The field must have a value for the record to be saved to the database.','Y','Y','Y','N','N','N','N','N','Mandatory',TO_TIMESTAMP('2011-07-11 15:29:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 3:29:42 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61899 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 3:29:44 PM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61846,61900,0,191,TO_TIMESTAMP('2011-07-11 15:29:42','YYYY-MM-DD HH24:MI:SS'),100,'Net Price including all discounts',1,'D','If price is set as "Net Price" no further discounts will be applied.','Y','Y','Y','N','N','N','N','N','Net Price',TO_TIMESTAMP('2011-07-11 15:29:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 11/07/2011 3:29:44 PM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61900 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 11/07/2011 3:30:17 PM
-- --
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=61899
;

-- 11/07/2011 3:30:17 PM
-- --
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=61898
;

-- 11/07/2011 3:30:17 PM
-- --
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=61900
;

-- 12/07/2011 11:05:45 AM
-- --
ALTER TABLE M_BP_Price ADD COLUMN IsNetPrice CHAR(1) DEFAULT NULL CHECK (IsNetPrice IN ('Y','N'))
;

-- 12/07/2011 11:06:15 AM
-- --
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,61843,61901,0,53415,TO_TIMESTAMP('2011-07-12 11:06:12','YYYY-MM-DD HH24:MI:SS'),100,'Net Price including all discounts',1,'D','If price is set as "Net Price" no further discounts will be applied.','Y','Y','Y','N','N','N','N','N','Net Price',TO_TIMESTAMP('2011-07-12 11:06:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 12/07/2011 11:06:15 AM
-- --
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61901 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 12/07/2011 11:06:35 AM
-- --
UPDATE AD_Field SET DisplayLogic='@PriceOverrideType@=''P''',Updated=TO_TIMESTAMP('2011-07-12 11:06:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=61901
;
