-- Mar 17, 2009 11:16:40 PM COT
-- FR [2690930] - Importer for Price List
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53071,TO_DATE('2009-03-17 23:16:39','YYYY-MM-DD HH24:MI:SS'),100,'Import Price Lists','D','The Import Price List Window is an interim table which is used when importing external data into the system.  Selecting the ''Process'' button will either add or modify the appropriate records.','Y','N','N','N','Import Price List','N',TO_DATE('2009-03-17 23:16:39','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

-- Mar 17, 2009 11:16:40 PM COT
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53071 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 17, 2009 11:16:41 PM COT
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53071,TO_DATE('2009-03-17 23:16:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-03-17 23:16:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:16:41 PM COT
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53071,TO_DATE('2009-03-17 23:16:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-03-17 23:16:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:16:41 PM COT
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53071,TO_DATE('2009-03-17 23:16:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-03-17 23:16:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:16:41 PM COT
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53071,TO_DATE('2009-03-17 23:16:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-03-17 23:16:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:16:52 PM COT
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('2',0,0,53173,53071,TO_DATE('2009-03-17 23:16:52','YYYY-MM-DD HH24:MI:SS'),100,'Import Price List and Versions','D','Y','N','Y','N','N','N',145,'Import Price List','L','I_PriceList',TO_DATE('2009-03-17 23:16:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:16:52 PM COT
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53173 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 17, 2009 11:16:56 PM COT
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53274,TO_DATE('2009-03-17 23:16:52','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table I_PriceList',1,'Y','N','Y','Y','I_PriceList','N',1000000,TO_DATE('2009-03-17 23:16:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:17:21 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56931,102,0,19,53173,129,'AD_Client_ID',TO_DATE('2009-03-17 23:17:16','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','N','Client',0,TO_DATE('2009-03-17 23:17:16','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:17:21 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56931 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:17:27 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56932,113,0,19,53173,104,'AD_Org_ID',TO_DATE('2009-03-17 23:17:21','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','N','Organization',0,TO_DATE('2009-03-17 23:17:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:17:27 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56932 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:17:33 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56933,1906,0,10,53173,'BPartner_Value',TO_DATE('2009-03-17 23:17:27','YYYY-MM-DD HH24:MI:SS'),100,'The Key of the Business Partner','D',40,'Y','N','N','N','N','N','N','N','N','N','Y','Business Partner Key',0,TO_DATE('2009-03-17 23:17:27','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:17:33 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56933 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:17:38 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56934,187,0,30,53173,'C_BPartner_ID',TO_DATE('2009-03-17 23:17:33','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','D',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','N','N','Y','Business Partner ',0,TO_DATE('2009-03-17 23:17:33','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:17:38 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56934 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:17:43 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56935,193,0,19,53173,'C_Currency_ID',TO_DATE('2009-03-17 23:17:38','YYYY-MM-DD HH24:MI:SS'),100,'The Currency for this record','D',22,'Indicates the Currency to be used when processing or reporting on this record','Y','N','N','N','N','N','N','N','N','N','Y','Currency',0,TO_DATE('2009-03-17 23:17:38','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:17:43 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56935 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:17:48 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56936,852,0,10,53173,'Classification',TO_DATE('2009-03-17 23:17:43','YYYY-MM-DD HH24:MI:SS'),100,'Classification for grouping','D',1,'The Classification can be used to optionally group products.','Y','N','N','N','N','N','N','N','N','N','Y','Classification',0,TO_DATE('2009-03-17 23:17:43','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:17:48 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56936 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:17:53 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56937,1254,0,37,53173,'CostPerOrder',TO_DATE('2009-03-17 23:17:48','YYYY-MM-DD HH24:MI:SS'),100,'Fixed Cost Per Order','D',22,'The Cost Per Order indicates the fixed charge levied when an order for this product is placed.','Y','N','N','N','N','N','N','N','N','N','Y','Cost per Order',0,TO_DATE('2009-03-17 23:17:48','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:17:53 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56937 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:00 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56938,245,0,16,53173,'Created',TO_DATE('2009-03-17 23:17:53','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','N','Created',0,TO_DATE('2009-03-17 23:17:53','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:00 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56938 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:01 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56939,246,0,18,110,53173,'CreatedBy',TO_DATE('2009-03-17 23:18:00','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','N','Created By',0,TO_DATE('2009-03-17 23:18:00','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:01 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56939 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:05 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56940,215,0,19,53173,'C_UOM_ID',TO_DATE('2009-03-17 23:18:01','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure','D',22,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','N','N','Y','UOM',0,TO_DATE('2009-03-17 23:18:01','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:05 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56940 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:05 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56941,1256,0,11,53173,'DeliveryTime_Promised',TO_DATE('2009-03-17 23:18:05','YYYY-MM-DD HH24:MI:SS'),100,'Promised days between order and delivery','D',22,'The Promised Delivery Time indicates the number of days between the order date and the date that delivery was promised.','Y','N','N','N','N','N','N','N','N','N','Y','Promised Delivery Time',0,TO_DATE('2009-03-17 23:18:05','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:05 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56941 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:06 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56942,275,0,10,53173,'Description',TO_DATE('2009-03-17 23:18:05','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-03-17 23:18:05','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:06 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56942 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:07 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56943,1920,0,40,53173,'DescriptionURL',TO_DATE('2009-03-17 23:18:06','YYYY-MM-DD HH24:MI:SS'),100,'URL for the description','D',120,'Y','N','N','N','N','N','N','N','N','N','Y','Description URL',0,TO_DATE('2009-03-17 23:18:06','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:07 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56943 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:08 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56944,278,0,20,53173,'Discontinued',TO_DATE('2009-03-17 23:18:07','YYYY-MM-DD HH24:MI:SS'),100,'This product is no longer available','D',1,'The Discontinued check box indicates a product that has been discontinued.','Y','N','N','N','N','N','N','N','N','N','Y','Discontinued',0,TO_DATE('2009-03-17 23:18:07','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:08 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56944 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:08 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56945,279,0,15,53173,'DiscontinuedBy',TO_DATE('2009-03-17 23:18:08','YYYY-MM-DD HH24:MI:SS'),100,'Discontinued By','D',7,'The Discontinued By indicates the individual who discontinued this product','Y','N','N','N','N','N','N','N','N','N','Y','Discontinued by',0,TO_DATE('2009-03-17 23:18:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:08 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56945 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:09 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56946,868,0,14,53173,'DocumentNote',TO_DATE('2009-03-17 23:18:08','YYYY-MM-DD HH24:MI:SS'),100,'Additional information for a Document','D',2000,'The Document Note is used for recording any additional information regarding this product.','Y','N','N','N','N','N','N','N','N','N','Y','Document Note',0,TO_DATE('2009-03-17 23:18:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:09 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56946 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:09 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56947,326,0,14,53173,'Help',TO_DATE('2009-03-17 23:18:09','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint','D',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_DATE('2009-03-17 23:18:09','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:09 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56947 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:10 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56948,912,0,10,53173,'I_ErrorMsg',TO_DATE('2009-03-17 23:18:09','YYYY-MM-DD HH24:MI:SS'),100,'Messages generated from import process','D',2000,'The Import Error Message displays any error messages generated during the import process.','Y','N','N','N','N','N','N','N','N','N','Y','Import Error Message',0,TO_DATE('2009-03-17 23:18:09','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:10 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56948 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:11 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56949,913,0,20,53173,'I_IsImported',TO_DATE('2009-03-17 23:18:10','YYYY-MM-DD HH24:MI:SS'),100,'Has this import been processed','D',1,'The Imported check box indicates if this import has been processed.','Y','N','N','N','N','Y','N','N','N','N','Y','Imported',0,TO_DATE('2009-03-17 23:18:10','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:11 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56949 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:11 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56950,1720,0,40,53173,'ImageURL',TO_DATE('2009-03-17 23:18:11','YYYY-MM-DD HH24:MI:SS'),100,'URL of  image','D',120,'URL of image; The image is not stored in the database, but retrieved at runtime. The image can be a gif, jpeg or png.','Y','N','N','N','N','N','N','N','N','N','Y','Image URL',0,TO_DATE('2009-03-17 23:18:11','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:11 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56950 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:12 PM COT
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53797,0,'I_PriceList_ID',TO_DATE('2009-03-17 23:18:11','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Import Price List','Import Price List',TO_DATE('2009-03-17 23:18:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:18:12 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53797 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 17, 2009 11:18:13 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56951,53797,0,13,53173,'I_PriceList_ID',TO_DATE('2009-03-17 23:18:11','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','Y','Y','N','N','N','N','N','Import Price List',0,TO_DATE('2009-03-17 23:18:11','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:13 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56951 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:13 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56952,348,0,20,53173,'IsActive',TO_DATE('2009-03-17 23:18:13','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','N','Y','Active',0,TO_DATE('2009-03-17 23:18:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:13 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56952 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:14 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56953,328,0,10,53173,'ISO_Code',TO_DATE('2009-03-17 23:18:13','YYYY-MM-DD HH24:MI:SS'),100,'Three letter ISO 4217 Code of the Currency','D',3,'For details - http://www.unece.org/trade/rec/rec09en.htm','Y','N','N','N','N','N','N','N','N','N','Y','ISO Currency Code',0,TO_DATE('2009-03-17 23:18:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:14 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56953 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:14 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56954,1915,0,10,53173,'Manufacturer',TO_DATE('2009-03-17 23:18:14','YYYY-MM-DD HH24:MI:SS'),100,'Manufacturer of the Product','D',30,'The manufacturer of the Product (used if different from the Business Partner / Vendor)','Y','N','N','N','N','N','N','N','N','N','Y','Manufacturer',0,TO_DATE('2009-03-17 23:18:14','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:14 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56954 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:15 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56955,453,0,19,53173,'M_Product_Category_ID',TO_DATE('2009-03-17 23:18:14','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','D',22,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','N','N','Y','Product Category',0,TO_DATE('2009-03-17 23:18:14','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:15 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56955 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:15 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56956,454,0,19,53173,'M_Product_ID',TO_DATE('2009-03-17 23:18:15','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','D',22,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','N','N','Y','Product',0,TO_DATE('2009-03-17 23:18:15','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:15 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56956 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:16 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56957,469,0,10,53173,'Name',TO_DATE('2009-03-17 23:18:15','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','N','N','N','N','N','N','N','Y','Name',0,TO_DATE('2009-03-17 23:18:15','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:16 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56957 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:16 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56958,942,0,11,53173,'Order_Min',TO_DATE('2009-03-17 23:18:16','YYYY-MM-DD HH24:MI:SS'),100,'Minimum order quantity in UOM','D',22,'The Minimum Order Quantity indicates the smallest quantity of this product which can be ordered.','Y','N','N','N','N','N','N','N','N','N','Y','Minimum Order Qty',0,TO_DATE('2009-03-17 23:18:16','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:16 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56958 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:19 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56959,943,0,11,53173,'Order_Pack',TO_DATE('2009-03-17 23:18:16','YYYY-MM-DD HH24:MI:SS'),100,'Package order size in UOM (e.g. order set of 5 units)','D',22,'The Order Pack Quantity indicates the number of units in each pack of this product.','Y','N','N','N','N','N','N','N','N','N','Y','Order Pack Qty',0,TO_DATE('2009-03-17 23:18:16','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:19 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56959 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:19 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56960,1123,0,15,53173,'PriceEffective',TO_DATE('2009-03-17 23:18:19','YYYY-MM-DD HH24:MI:SS'),100,'Effective Date of Price','D',7,'The Price Effective indicates the date this price is for. This allows you to enter future prices for products which will become effective when appropriate.','Y','N','N','N','N','N','N','N','N','N','Y','Price effective',0,TO_DATE('2009-03-17 23:18:19','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:19 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56960 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:19 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56961,955,0,37,53173,'PriceLimit',TO_DATE('2009-03-17 23:18:19','YYYY-MM-DD HH24:MI:SS'),100,'Lowest price for a product','D',22,'The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','N','N','N','N','N','N','N','N','N','Y','Limit Price',0,TO_DATE('2009-03-17 23:18:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 17, 2009 11:18:20 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56961 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:22 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56962,520,0,37,53173,'PriceList',TO_DATE('2009-03-17 23:18:20','YYYY-MM-DD HH24:MI:SS'),100,'List Price','D',22,'The List Price is the official List Price in the document currency.','Y','N','N','N','N','N','N','N','N','N','Y','List Price',0,TO_DATE('2009-03-17 23:18:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:22 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56962 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:22 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56963,1124,0,37,53173,'PricePO',TO_DATE('2009-03-17 23:18:22','YYYY-MM-DD HH24:MI:SS'),100,'Price based on a purchase order','D',22,'The PO Price indicates the price for a product per the purchase order.','Y','N','N','N','N','N','N','N','N','N','Y','PO Price',0,TO_DATE('2009-03-17 23:18:22','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:22 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56963 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:23 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56964,957,0,37,53173,'PriceStd',TO_DATE('2009-03-17 23:18:22','YYYY-MM-DD HH24:MI:SS'),100,'Standard Price','D',22,'The Standard Price indicates the standard or normal price for a product on this price list','Y','N','N','N','N','N','N','N','N','N','Y','Standard Price',0,TO_DATE('2009-03-17 23:18:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 17, 2009 11:18:23 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56964 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:23 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56965,1047,0,20,53173,'Processed',TO_DATE('2009-03-17 23:18:23','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed','D',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','N','N','N','N','N','Y','Processed',0,TO_DATE('2009-03-17 23:18:23','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:23 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56965 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:24 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56966,524,0,196,28,53173,'Processing',TO_DATE('2009-03-17 23:18:23','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','N','N','N','N','N','N','N','N','N','Y','Process Now',0,TO_DATE('2009-03-17 23:18:23','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:24 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56966 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:25 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56967,1916,0,10,53173,'ProductCategory_Value',TO_DATE('2009-03-17 23:18:24','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','N','N','N','N','N','N','N','N','N','Y','Product Category Key',0,TO_DATE('2009-03-17 23:18:24','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:25 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56967 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:25 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56968,1899,0,17,270,53173,'ProductType',TO_DATE('2009-03-17 23:18:25','YYYY-MM-DD HH24:MI:SS'),100,'I','Type of product','D',1,'The type of product also determines accounting consequences.','Y','N','N','N','N','N','N','N','N','N','Y','Product Type',0,TO_DATE('2009-03-17 23:18:25','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:25 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56968 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:26 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56969,1918,0,12,53173,'RoyaltyAmt',TO_DATE('2009-03-17 23:18:25','YYYY-MM-DD HH24:MI:SS'),100,'(Included) Amount for copyright, etc.','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Royalty Amount',0,TO_DATE('2009-03-17 23:18:25','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:26 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56969 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:26 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56970,570,0,11,53173,'ShelfDepth',TO_DATE('2009-03-17 23:18:26','YYYY-MM-DD HH24:MI:SS'),100,'Shelf depth required','D',22,'The Shelf Depth indicates the depth dimension required on a shelf for a product ','Y','N','N','N','N','N','N','N','N','N','Y','Shelf Depth',0,TO_DATE('2009-03-17 23:18:26','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:27 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56970 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:27 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56971,571,0,11,53173,'ShelfHeight',TO_DATE('2009-03-17 23:18:27','YYYY-MM-DD HH24:MI:SS'),100,'Shelf height required','D',22,'The Shelf Height indicates the height dimension required on a shelf for a product','Y','N','N','N','N','N','N','N','N','N','Y','Shelf Height',0,TO_DATE('2009-03-17 23:18:27','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:27 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56971 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:27 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56972,572,0,11,53173,'ShelfWidth',TO_DATE('2009-03-17 23:18:27','YYYY-MM-DD HH24:MI:SS'),100,'Shelf width required','D',22,'The Shelf Width indicates the width dimension required on a shelf for a product','Y','N','N','N','N','N','N','N','N','N','Y','Shelf Width',0,TO_DATE('2009-03-17 23:18:27','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:27 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56972 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:28 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56973,549,0,10,53173,'SKU',TO_DATE('2009-03-17 23:18:27','YYYY-MM-DD HH24:MI:SS'),100,'Stock Keeping Unit','D',30,'The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','N','N','N','N','N','N','N','N','N','Y','SKU',0,TO_DATE('2009-03-17 23:18:27','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:28 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56973 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:29 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56974,604,0,11,53173,'UnitsPerPallet',TO_DATE('2009-03-17 23:18:28','YYYY-MM-DD HH24:MI:SS'),100,'Units Per Pallet','D',22,'The Units per Pallet indicates the number of units of this product which fit on a pallet.','Y','N','N','N','N','N','N','N','N','N','Y','Units Per Pallet',0,TO_DATE('2009-03-17 23:18:28','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:29 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56974 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:38 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56975,603,0,10,53173,'UPC',TO_DATE('2009-03-17 23:18:29','YYYY-MM-DD HH24:MI:SS'),100,'Bar Code (Universal Product Code or its superset European Article Number)','D',30,'Use this field to enter the bar code for the product in any of the bar code symbologies (Codabar, Code 25, Code 39, Code 93, Code 128, UPC (A), UPC (E), EAN-13, EAN-8, ITF, ITF-14, ISBN, ISSN, JAN-13, JAN-8, POSTNET and FIM, MSI/Plessey, and Pharmacode) ','Y','N','N','N','N','N','N','N','N','N','Y','UPC/EAN',0,TO_DATE('2009-03-17 23:18:29','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:38 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56975 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:47 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56976,607,0,16,53173,'Updated',TO_DATE('2009-03-17 23:18:38','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','N','Updated',0,TO_DATE('2009-03-17 23:18:38','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:47 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56976 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:18:55 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56977,608,0,18,110,53173,'UpdatedBy',TO_DATE('2009-03-17 23:18:47','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','N','Updated By',0,TO_DATE('2009-03-17 23:18:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:18:55 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56977 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:19:05 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56978,620,0,10,53173,'Value',TO_DATE('2009-03-17 23:18:55','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','D',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','Y','N','N','N','N','N','N','Y','Search Key',1,TO_DATE('2009-03-17 23:18:55','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:19:05 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56978 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:19:11 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56979,622,0,10,53173,'VendorCategory',TO_DATE('2009-03-17 23:19:05','YYYY-MM-DD HH24:MI:SS'),100,'Product Category of the Business Partner','D',30,'The Business Partner Category identifies the category used by the Business Partner for this product.','Y','N','N','N','N','N','N','N','N','N','Y','Partner Category',0,TO_DATE('2009-03-17 23:19:05','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:19:11 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56979 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:19:28 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56980,623,0,10,53173,'VendorProductNo',TO_DATE('2009-03-17 23:19:11','YYYY-MM-DD HH24:MI:SS'),100,'Product Key of the Business Partner','D',30,'The Business Partner Product Key identifies the number used by the Business Partner for this product. It can be printed on orders and invoices when you include the Product Key in the print format.','Y','N','N','N','N','N','N','N','N','N','Y','Partner Product Key',0,TO_DATE('2009-03-17 23:19:11','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:19:28 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56980 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:19:35 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56981,627,0,11,53173,'Volume',TO_DATE('2009-03-17 23:19:28','YYYY-MM-DD HH24:MI:SS'),100,'Volume of a product','D',22,'The Volume indicates the volume of the product in the Volume UOM of the Client','Y','N','N','N','N','N','N','N','N','N','Y','Volume',0,TO_DATE('2009-03-17 23:19:28','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:19:35 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56981 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:19:41 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56982,629,0,11,53173,'Weight',TO_DATE('2009-03-17 23:19:35','YYYY-MM-DD HH24:MI:SS'),100,'Weight of a product','D',22,'The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','N','N','N','N','N','N','N','N','N','Y','Weight',0,TO_DATE('2009-03-17 23:19:35','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:19:41 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56982 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:19:48 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56983,634,0,10,53173,'X12DE355',TO_DATE('2009-03-17 23:19:41','YYYY-MM-DD HH24:MI:SS'),100,'UOM EDI X12 Code','D',4,'The Unit of Measure Code indicates the EDI X12 Code Data Element 355 (Unit or Basis for Measurement)','Y','N','N','N','N','N','N','N','N','N','Y','UOM Code',0,TO_DATE('2009-03-17 23:19:41','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Mar 17, 2009 11:19:48 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56983 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:20:57 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56936
;

-- Mar 17, 2009 11:20:57 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56936
;

-- Mar 17, 2009 11:21:01 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56937
;

-- Mar 17, 2009 11:21:01 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56937
;

-- Mar 17, 2009 11:21:16 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56941
;

-- Mar 17, 2009 11:21:16 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56941
;

-- Mar 17, 2009 11:22:09 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56943
;

-- Mar 17, 2009 11:22:09 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56943
;

-- Mar 17, 2009 11:22:09 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56944
;

-- Mar 17, 2009 11:22:10 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56944
;

-- Mar 17, 2009 11:22:10 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56945
;

-- Mar 17, 2009 11:22:10 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56945
;

-- Mar 17, 2009 11:22:10 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56946
;

-- Mar 17, 2009 11:22:10 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56946
;

-- Mar 17, 2009 11:22:10 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56947
;

-- Mar 17, 2009 11:22:10 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56947
;

-- Mar 17, 2009 11:22:16 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56950
;

-- Mar 17, 2009 11:22:16 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56950
;

-- Mar 17, 2009 11:22:58 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56954
;

-- Mar 17, 2009 11:22:58 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56954
;

-- Mar 17, 2009 11:22:58 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56955
;

-- Mar 17, 2009 11:22:58 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56955
;

-- Mar 17, 2009 11:23:29 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56958
;

-- Mar 17, 2009 11:23:29 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56958
;

-- Mar 17, 2009 11:23:33 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56959
;

-- Mar 17, 2009 11:23:33 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56959
;

-- Mar 17, 2009 11:24:01 PM COT
UPDATE AD_Column SET AD_Element_ID=617, ColumnName='ValidFrom', Description='Valid from including this date (first day)', Help='The Valid From date indicates the first day of a date range', Name='Valid from',Updated=TO_DATE('2009-03-17 23:24:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56960
;

-- Mar 17, 2009 11:24:01 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56960
;

-- Mar 17, 2009 11:24:01 PM COT
UPDATE AD_Field SET Name='Valid from', Description='Valid from including this date (first day)', Help='The Valid From date indicates the first day of a date range' WHERE AD_Column_ID=56960 AND IsCentrallyMaintained='Y'
;

-- Mar 17, 2009 11:24:26 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56963
;

-- Mar 17, 2009 11:24:26 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56963
;

-- Mar 17, 2009 11:25:10 PM COT
UPDATE AD_Column SET AD_Element_ID=1675, ColumnName='ProductValue', Description='Key of the Product', Help=NULL, Name='Product Key',Updated=TO_DATE('2009-03-17 23:25:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56967
;

-- Mar 17, 2009 11:25:10 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56967
;

-- Mar 17, 2009 11:25:10 PM COT
UPDATE AD_Field SET Name='Product Key', Description='Key of the Product', Help=NULL WHERE AD_Column_ID=56967 AND IsCentrallyMaintained='Y'
;

-- Mar 17, 2009 11:26:20 PM COT
UPDATE AD_Column SET AD_Element_ID=1106, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, ColumnName='IsSOPriceList', DefaultValue=NULL, Description='This is a Sales Transaction', Help='The Sales Transaction checkbox indicates if this item is a Sales Transaction.', Name='Sales Transaction',Updated=TO_DATE('2009-03-17 23:26:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56968
;

-- Mar 17, 2009 11:26:20 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56968
;

-- Mar 17, 2009 11:26:20 PM COT
UPDATE AD_Field SET Name='Sales Transaction', Description='This is a Sales Transaction', Help='The Sales Transaction checkbox indicates if this item is a Sales Transaction.' WHERE AD_Column_ID=56968 AND IsCentrallyMaintained='Y'
;

-- Mar 17, 2009 11:27:15 PM COT
UPDATE AD_Column SET AD_Element_ID=1708, AD_Reference_ID=22, ColumnName='BreakValue', Description='Low Value of trade discount break level', Help='Starting Quantity or Amount Value for break level', Name='Break Value',Updated=TO_DATE('2009-03-17 23:27:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56969
;

-- Mar 17, 2009 11:27:15 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56969
;

-- Mar 17, 2009 11:27:15 PM COT
UPDATE AD_Field SET Name='Break Value', Description='Low Value of trade discount break level', Help='Starting Quantity or Amount Value for break level' WHERE AD_Column_ID=56969 AND IsCentrallyMaintained='Y'
;

-- Mar 17, 2009 11:29:22 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56970
;

-- Mar 17, 2009 11:29:22 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56970
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56971
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56971
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56972
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56972
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56973
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56973
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56974
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56974
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56975
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56975
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56982
;

-- Mar 17, 2009 11:29:23 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56982
;

-- Mar 17, 2009 11:29:24 PM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56978
;

-- Mar 17, 2009 11:29:24 PM COT
DELETE FROM AD_Column WHERE AD_Column_ID=56978
;

-- Mar 17, 2009 11:30:05 PM COT
UPDATE AD_Column SET AD_Element_ID=2665, AD_Reference_ID=22, ColumnName='PricePrecision', DefaultValue=NULL, Description='Precision (number of decimals) for the Price', FieldLength=22, Help='The prices of the price list are rounded to the precision entered.  This allows to have prices with below currency precision, e.g. $0.005. Enter the number of decimals or -1 for no rounding.', Name='Price Precision',Updated=TO_DATE('2009-03-17 23:30:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56979
;

-- Mar 17, 2009 11:30:05 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56979
;

-- Mar 17, 2009 11:30:05 PM COT
UPDATE AD_Field SET Name='Price Precision', Description='Precision (number of decimals) for the Price', Help='The prices of the price list are rounded to the precision entered.  This allows to have prices with below currency precision, e.g. $0.005. Enter the number of decimals or -1 for no rounding.' WHERE AD_Column_ID=56979 AND IsCentrallyMaintained='Y'
;

-- Mar 17, 2009 11:30:33 PM COT
UPDATE AD_Column SET AD_Element_ID=1065, AD_Reference_ID=20, ColumnName='IsTaxIncluded', Description='Tax is included in the price ', FieldLength=1, Help='The Tax Included checkbox indicates if the prices include tax.  This is also known as the gross price.', Name='Price includes Tax',Updated=TO_DATE('2009-03-17 23:30:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56980
;

-- Mar 17, 2009 11:30:33 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56980
;

-- Mar 17, 2009 11:30:33 PM COT
UPDATE AD_Field SET Name='Price includes Tax', Description='Tax is included in the price ', Help='The Tax Included checkbox indicates if the prices include tax.  This is also known as the gross price.' WHERE AD_Column_ID=56980 AND IsCentrallyMaintained='Y'
;

-- Mar 17, 2009 11:30:58 PM COT
UPDATE AD_Column SET AD_Element_ID=882, AD_Reference_ID=20, ColumnName='EnforcePriceLimit', Description='Do not allow prices below the limit price', FieldLength=1, Help='The Enforce Price Limit check box indicates that prices cannot be below the limit price in Orders and Invoices.  Ths can be overwritten, if the role allows this.', Name='Enforce price limit',Updated=TO_DATE('2009-03-17 23:30:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56981
;

-- Mar 17, 2009 11:30:58 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56981
;

-- Mar 17, 2009 11:30:58 PM COT
UPDATE AD_Field SET Name='Enforce price limit', Description='Do not allow prices below the limit price', Help='The Enforce Price Limit check box indicates that prices cannot be below the limit price in Orders and Invoices.  Ths can be overwritten, if the role allows this.' WHERE AD_Column_ID=56981 AND IsCentrallyMaintained='Y'
;

-- Mar 17, 2009 11:31:33 PM COT
CREATE TABLE I_PriceList (AD_Client_ID NUMBER(10) DEFAULT  NULL , AD_Org_ID NUMBER(10) DEFAULT  NULL , BPartner_Value NVARCHAR2(40), BreakValue NUMBER, C_BPartner_ID NUMBER(10), C_Currency_ID NUMBER(10), Created DATE, CreatedBy NUMBER(10), C_UOM_ID NUMBER(10), Description NVARCHAR2(255), EnforcePriceLimit CHAR(1) CHECK (EnforcePriceLimit IN ('Y','N')), I_ErrorMsg NVARCHAR2(2000), I_IsImported CHAR(1) NOT NULL, I_PriceList_ID NUMBER(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')), ISO_Code NVARCHAR2(3), IsSOPriceList CHAR(1) CHECK (IsSOPriceList IN ('Y','N')), IsTaxIncluded CHAR(1) CHECK (IsTaxIncluded IN ('Y','N')), M_Product_ID NUMBER(10), Name NVARCHAR2(60), PriceLimit NUMBER, PriceList NUMBER, PricePrecision NUMBER, PriceStd NUMBER, Processed CHAR(1) CHECK (Processed IN ('Y','N')), Processing CHAR(1), ProductValue NVARCHAR2(40), Updated DATE, UpdatedBy NUMBER(10), ValidFrom DATE, X12DE355 NVARCHAR2(4), CONSTRAINT I_PriceList_Key PRIMARY KEY (I_PriceList_ID))
;

-- Mar 17, 2009 11:33:33 PM COT
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53198,53173,53071,TO_DATE('2009-03-17 23:33:26','YYYY-MM-DD HH24:MI:SS'),100,'Import Price Lists','D','N',NULL,'N','Y','N','N','Y','N','Y','N','N','Import Price List','N',10,0,TO_DATE('2009-03-17 23:33:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:33:33 PM COT
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53198 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 17, 2009 11:33:44 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56952,56780,0,53198,TO_DATE('2009-03-17 23:33:38','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-03-17 23:33:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:33:44 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56780 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:33:53 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56969,56781,0,53198,TO_DATE('2009-03-17 23:33:44','YYYY-MM-DD HH24:MI:SS'),100,'Low Value of trade discount break level',22,'D','Starting Quantity or Amount Value for break level','Y','Y','Y','N','N','N','N','N','Break Value',TO_DATE('2009-03-17 23:33:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:33:53 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56781 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:00 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56934,56782,0,53198,TO_DATE('2009-03-17 23:33:53','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',22,'D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','N','Business Partner ',TO_DATE('2009-03-17 23:33:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:00 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56782 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:06 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56933,56783,0,53198,TO_DATE('2009-03-17 23:34:00','YYYY-MM-DD HH24:MI:SS'),100,'The Key of the Business Partner',40,'D','Y','Y','Y','N','N','N','N','N','Business Partner Key',TO_DATE('2009-03-17 23:34:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:06 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56783 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:12 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56931,56784,0,53198,TO_DATE('2009-03-17 23:34:06','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-03-17 23:34:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:12 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56784 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:17 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56935,56785,0,53198,TO_DATE('2009-03-17 23:34:12','YYYY-MM-DD HH24:MI:SS'),100,'The Currency for this record',22,'D','Indicates the Currency to be used when processing or reporting on this record','Y','Y','Y','N','N','N','N','N','Currency',TO_DATE('2009-03-17 23:34:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:17 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56785 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:24 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56942,56786,0,53198,TO_DATE('2009-03-17 23:34:18','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-03-17 23:34:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:24 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56786 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:30 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56981,56787,0,53198,TO_DATE('2009-03-17 23:34:24','YYYY-MM-DD HH24:MI:SS'),100,'Do not allow prices below the limit price',1,'D','The Enforce Price Limit check box indicates that prices cannot be below the limit price in Orders and Invoices.  Ths can be overwritten, if the role allows this.','Y','Y','Y','N','N','N','N','N','Enforce price limit',TO_DATE('2009-03-17 23:34:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:30 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56787 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:36 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56949,56788,0,53198,TO_DATE('2009-03-17 23:34:30','YYYY-MM-DD HH24:MI:SS'),100,'Has this import been processed',1,'D','The Imported check box indicates if this import has been processed.','Y','Y','Y','N','N','N','N','N','Imported',TO_DATE('2009-03-17 23:34:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:36 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56788 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:42 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56948,56789,0,53198,TO_DATE('2009-03-17 23:34:36','YYYY-MM-DD HH24:MI:SS'),100,'Messages generated from import process',2000,'D','The Import Error Message displays any error messages generated during the import process.','Y','Y','Y','N','N','N','N','N','Import Error Message',TO_DATE('2009-03-17 23:34:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:42 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56789 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:51 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56951,56790,0,53198,TO_DATE('2009-03-17 23:34:42','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','N','Import Price List',TO_DATE('2009-03-17 23:34:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:51 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56790 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:34:56 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56953,56791,0,53198,TO_DATE('2009-03-17 23:34:51','YYYY-MM-DD HH24:MI:SS'),100,'Three letter ISO 4217 Code of the Currency',3,'D','For details - http://www.unece.org/trade/rec/rec09en.htm','Y','Y','Y','N','N','N','N','N','ISO Currency Code',TO_DATE('2009-03-17 23:34:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:34:56 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56791 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:01 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56961,56792,0,53198,TO_DATE('2009-03-17 23:34:56','YYYY-MM-DD HH24:MI:SS'),100,'Lowest price for a product',22,'D','The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','Y','Y','N','N','N','N','N','Limit Price',TO_DATE('2009-03-17 23:34:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:01 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56792 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:06 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56962,56793,0,53198,TO_DATE('2009-03-17 23:35:01','YYYY-MM-DD HH24:MI:SS'),100,'List Price',22,'D','The List Price is the official List Price in the document currency.','Y','Y','Y','N','N','N','N','N','List Price',TO_DATE('2009-03-17 23:35:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:06 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56793 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:13 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56957,56794,0,53198,TO_DATE('2009-03-17 23:35:06','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2009-03-17 23:35:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:13 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56794 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:19 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56932,56795,0,53198,TO_DATE('2009-03-17 23:35:13','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-03-17 23:35:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:19 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56795 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:25 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56980,56796,0,53198,TO_DATE('2009-03-17 23:35:19','YYYY-MM-DD HH24:MI:SS'),100,'Tax is included in the price ',1,'D','The Tax Included checkbox indicates if the prices include tax.  This is also known as the gross price.','Y','Y','Y','N','N','N','N','N','Price includes Tax',TO_DATE('2009-03-17 23:35:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:25 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56796 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:31 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56979,56797,0,53198,TO_DATE('2009-03-17 23:35:25','YYYY-MM-DD HH24:MI:SS'),100,'Precision (number of decimals) for the Price',22,'D','The prices of the price list are rounded to the precision entered.  This allows to have prices with below currency precision, e.g. $0.005. Enter the number of decimals or -1 for no rounding.','Y','Y','Y','N','N','N','N','N','Price Precision',TO_DATE('2009-03-17 23:35:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:31 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56797 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:36 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56965,56798,0,53198,TO_DATE('2009-03-17 23:35:31','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',1,'D','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','N','N','N','Processed',TO_DATE('2009-03-17 23:35:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:36 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56798 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:43 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56966,56799,0,53198,TO_DATE('2009-03-17 23:35:36','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Process Now',TO_DATE('2009-03-17 23:35:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:43 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56799 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:44 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56956,56800,0,53198,TO_DATE('2009-03-17 23:35:43','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',22,'D','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','N','Product',TO_DATE('2009-03-17 23:35:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:44 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56800 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:44 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56967,56801,0,53198,TO_DATE('2009-03-17 23:35:44','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Product',40,'D','Y','Y','Y','N','N','N','N','N','Product Key',TO_DATE('2009-03-17 23:35:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:44 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56801 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:45 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56968,56802,0,53198,TO_DATE('2009-03-17 23:35:44','YYYY-MM-DD HH24:MI:SS'),100,'This is a Sales Transaction',1,'D','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Y','Y','N','N','N','N','N','Sales Transaction',TO_DATE('2009-03-17 23:35:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:45 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56802 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:46 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56964,56803,0,53198,TO_DATE('2009-03-17 23:35:45','YYYY-MM-DD HH24:MI:SS'),100,'Standard Price',22,'D','The Standard Price indicates the standard or normal price for a product on this price list','Y','Y','Y','N','N','N','N','N','Standard Price',TO_DATE('2009-03-17 23:35:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:46 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56803 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:47 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56940,56804,0,53198,TO_DATE('2009-03-17 23:35:46','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure',22,'D','The UOM defines a unique non monetary Unit of Measure','Y','Y','Y','N','N','N','N','N','UOM',TO_DATE('2009-03-17 23:35:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:47 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56804 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:47 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56983,56805,0,53198,TO_DATE('2009-03-17 23:35:47','YYYY-MM-DD HH24:MI:SS'),100,'UOM EDI X12 Code',4,'D','The Unit of Measure Code indicates the EDI X12 Code Data Element 355 (Unit or Basis for Measurement)','Y','Y','Y','N','N','N','N','N','UOM Code',TO_DATE('2009-03-17 23:35:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:47 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56805 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:35:48 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56960,56806,0,53198,TO_DATE('2009-03-17 23:35:47','YYYY-MM-DD HH24:MI:SS'),100,'Valid from including this date (first day)',7,'D','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','N','N','Valid from',TO_DATE('2009-03-17 23:35:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:35:48 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56806 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:38:43 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56984,449,0,19,53173,'M_PriceList_ID',TO_DATE('2009-03-17 23:38:36','YYYY-MM-DD HH24:MI:SS'),100,'Unique identifier of a Price List','D',22,'Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','N','N','N','N','N','N','N','N','N','Y','Price List',0,TO_DATE('2009-03-17 23:38:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 17, 2009 11:38:43 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56984 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:38:45 PM COT
ALTER TABLE I_PriceList ADD M_PriceList_ID NUMBER(10)
;

-- Mar 17, 2009 11:39:08 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56985,450,0,19,53173,'M_PriceList_Version_ID',TO_DATE('2009-03-17 23:39:03','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a unique instance of a Price List','D',22,'Each Price List can have multiple versions.  The most common use is to indicate the dates that a Price List is valid for.','Y','N','N','N','N','N','N','N','N','N','Y','Price List Version',0,TO_DATE('2009-03-17 23:39:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 17, 2009 11:39:08 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56985 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 17, 2009 11:39:10 PM COT
ALTER TABLE I_PriceList ADD M_PriceList_Version_ID NUMBER(10)
;

-- Mar 17, 2009 11:40:51 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56984,56807,0,53198,TO_DATE('2009-03-17 23:40:45','YYYY-MM-DD HH24:MI:SS'),100,'Unique identifier of a Price List',22,'D','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Y','Y','N','N','N','N','N','Price List',TO_DATE('2009-03-17 23:40:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:40:51 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56807 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:40:56 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56985,56808,0,53198,TO_DATE('2009-03-17 23:40:51','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a unique instance of a Price List',22,'D','Each Price List can have multiple versions.  The most common use is to indicate the dates that a Price List is valid for.','Y','Y','Y','N','N','N','N','N','Price List Version',TO_DATE('2009-03-17 23:40:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:40:56 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56808 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 17, 2009 11:45:31 PM COT
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56780
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56790
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56788
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56807
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56789
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56784
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56795
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56794
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56786
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56791
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56785
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56797
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56802
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56796
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56787
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56808
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56806
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56801
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56800
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56793
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56803
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56792
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56783
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56782
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56781
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56805
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56804
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56799
;

-- Mar 17, 2009 11:45:32 PM COT
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56798
;

-- Mar 17, 2009 11:45:50 PM COT
UPDATE AD_Field SET DisplayLogic='Y=N',Updated=TO_DATE('2009-03-17 23:45:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56790
;

-- Mar 17, 2009 11:46:12 PM COT
UPDATE AD_Column SET AD_Element_ID=408, ColumnName='IsSOPriceList', Description='This is a Sales Price List', Help='The Sales Price List check box indicates if this price list is used for sales transactions.', Name='Sales Price list',Updated=TO_DATE('2009-03-17 23:46:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56968
;

-- Mar 17, 2009 11:46:12 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56968
;

-- Mar 17, 2009 11:46:12 PM COT
UPDATE AD_Field SET Name='Sales Price list', Description='This is a Sales Price List', Help='The Sales Price List check box indicates if this price list is used for sales transactions.' WHERE AD_Column_ID=56968 AND IsCentrallyMaintained='Y'
;

-- Mar 17, 2009 11:47:53 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:47:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56807
;

-- Mar 17, 2009 11:48:01 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56795
;

-- Mar 17, 2009 11:48:16 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:48:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56785
;

-- Mar 17, 2009 11:48:40 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:48:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56802
;

-- Mar 17, 2009 11:48:43 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:48:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56787
;

-- Mar 17, 2009 11:49:07 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:49:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56800
;

-- Mar 17, 2009 11:49:13 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:49:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56782
;

-- Mar 17, 2009 11:49:17 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:49:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56804
;

-- Mar 17, 2009 11:50:09 PM COT
UPDATE AD_Field SET Description='Imports price lists from a file into the application', Name='Import Price Lists',Updated=TO_DATE('2009-03-17 23:50:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56799
;

-- Mar 17, 2009 11:50:09 PM COT
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56799
;

-- Mar 17, 2009 11:50:18 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-17 23:50:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56798
;

-- Mar 17, 2009 11:51:29 PM COT
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('3',0,0,53163,'org.adempiere.process.ImportPriceList',TO_DATE('2009-03-17 23:51:28','YYYY-MM-DD HH24:MI:SS'),100,'Imports price lists from a file into the application','D',NULL,'Y','N','N','N','N','Import Price Lists','Y',0,0,TO_DATE('2009-03-17 23:51:28','YYYY-MM-DD HH24:MI:SS'),100,'Import_Price_List')
;

-- Mar 17, 2009 11:51:29 PM COT
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53163 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Mar 17, 2009 11:51:29 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53163,0,TO_DATE('2009-03-17 23:51:29','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-03-17 23:51:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:51:29 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53163,102,TO_DATE('2009-03-17 23:51:29','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-03-17 23:51:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:51:29 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53163,103,TO_DATE('2009-03-17 23:51:29','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-03-17 23:51:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:51:29 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53163,50001,TO_DATE('2009-03-17 23:51:29','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-03-17 23:51:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:51:39 PM COT
UPDATE AD_Column SET AD_Process_ID=53163,Updated=TO_DATE('2009-03-17 23:51:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56966
;

-- Mar 17, 2009 11:53:20 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,102,0,53163,53302,19,'AD_Client_ID',TO_DATE('2009-03-17 23:53:19','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','D',0,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','Client',10,TO_DATE('2009-03-17 23:53:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:53:20 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53302 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 17, 2009 11:53:59 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1922,0,53163,53303,20,'DeleteOldImported',TO_DATE('2009-03-17 23:53:52','YYYY-MM-DD HH24:MI:SS'),100,'Before processing delete old imported records in the import table','D',0,'Y','Y','N','N','Delete old imported records',20,TO_DATE('2009-03-17 23:53:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 17, 2009 11:53:59 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53303 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 18, 2009 6:27:38 PM COT
UPDATE AD_Column SET DefaultValue='N',Updated=TO_DATE('2009-03-18 18:27:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56949
;

-- Mar 18, 2009 6:27:42 PM COT
ALTER TABLE I_PriceList MODIFY I_IsImported CHAR(1) DEFAULT 'N'
;

-- Mar 18, 2009 6:27:44 PM COT
UPDATE I_PriceList SET I_IsImported='N' WHERE I_IsImported IS NULL
;

-- Mar 18, 2009 6:27:59 PM COT
UPDATE AD_Column SET DefaultValue='N',Updated=TO_DATE('2009-03-18 18:27:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56965
;

-- Mar 18, 2009 6:28:01 PM COT
ALTER TABLE I_PriceList MODIFY Processed CHAR(1) DEFAULT 'N'
;

-- Mar 18, 2009 6:32:52 PM COT
UPDATE AD_Field SET DisplayLength=20,Updated=TO_DATE('2009-03-18 18:32:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56801
;

-- Mar 18, 2009 6:32:58 PM COT
UPDATE AD_Field SET DisplayLength=20,Updated=TO_DATE('2009-03-18 18:32:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56783
;

-- Mar 18, 2009 7:39:01 PM COT
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56774
;

-- Mar 18, 2009 7:39:01 PM COT
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56773
;

