SET DEFINE OFF;

-- Jul 28, 2008 10:50:09 PM CDT
-- Order Distribution
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53144,'3',TO_DATE('2008-07-28 22:49:59','YYYY-MM-DD HH24:MI:SS'),0,'Distribution Order & Line View','EE01','Y','N','N','N','N','Y',150,'Distribution Order Detail','L','RV_DD_OrderDetail',TO_DATE('2008-07-28 22:49:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 10:50:09 PM CDT
-- Order Distribution
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53144 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Jul 28, 2008 10:50:10 PM CDT
-- Order Distribution
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53165,TO_DATE('2008-07-28 22:50:10','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table RV_DD_OrderDetail',1,'Y','N','Y','Y','RV_DD_OrderDetail','N',1000000,TO_DATE('2008-07-28 22:50:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 10:50:39 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56249,102,0,19,53144,'AD_Client_ID',TO_DATE('2008-07-28 22:50:36','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',TO_DATE('2008-07-28 22:50:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:39 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56249 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:39 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56250,113,0,19,53144,'AD_Org_ID',TO_DATE('2008-07-28 22:50:39','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',TO_DATE('2008-07-28 22:50:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:39 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56250 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:41 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56251,348,0,20,53144,'IsActive',TO_DATE('2008-07-28 22:50:39','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','N','Active',TO_DATE('2008-07-28 22:50:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:41 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56251 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:42 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56252,245,0,16,53144,'Created',TO_DATE('2008-07-28 22:50:41','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',TO_DATE('2008-07-28 22:50:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:42 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56252 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:44 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56253,246,0,18,110,53144,'CreatedBy',TO_DATE('2008-07-28 22:50:42','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',TO_DATE('2008-07-28 22:50:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:44 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56253 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:45 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56254,607,0,16,53144,'Updated',TO_DATE('2008-07-28 22:50:44','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',TO_DATE('2008-07-28 22:50:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:45 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56254 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:46 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56255,608,0,18,110,53144,'UpdatedBy',TO_DATE('2008-07-28 22:50:45','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',TO_DATE('2008-07-28 22:50:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:46 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56255 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:47 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56256,558,0,19,53144,'C_Order_ID',TO_DATE('2008-07-28 22:50:46','YYYY-MM-DD HH24:MI:SS'),0,'Order','EE01',10,'The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','N','N','N','N','N','N','N','N','N','Order',TO_DATE('2008-07-28 22:50:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:47 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56256 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:48 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56257,289,0,17,53144,'DocStatus',TO_DATE('2008-07-28 22:50:47','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','N','N','Document Status',TO_DATE('2008-07-28 22:50:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:48 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56257 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:49 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56258,287,0,17,53144,'DocAction',TO_DATE('2008-07-28 22:50:48','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE01',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','N','N','N','N','N','N','N','N','N','Document Action',TO_DATE('2008-07-28 22:50:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:49 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56258 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:50 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56259,196,0,19,53144,'C_DocType_ID',TO_DATE('2008-07-28 22:50:49','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE01',10,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','N','N','N','N','N','Document Type',TO_DATE('2008-07-28 22:50:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:50 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56259 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:51 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56260,351,0,20,53144,'IsApproved',TO_DATE('2008-07-28 22:50:50','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','EE01',1,'The Approved checkbox indicates if this document requires approval before it can be processed.','Y','N','N','N','N','N','N','N','N','N','Approved',TO_DATE('2008-07-28 22:50:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:51 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56260 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:53 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56261,1063,0,18,190,53144,'SalesRep_ID',TO_DATE('2008-07-28 22:50:51','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE01',10,'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','N','N','N','N','N','N','N','N','N','Sales Representative',TO_DATE('2008-07-28 22:50:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:53 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56261 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:54 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56262,2466,0,20,53144,'IsDropShip',TO_DATE('2008-07-28 22:50:53','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','EE01',1,'Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','N','N','N','N','N','N','N','N','N','Drop Shipment',TO_DATE('2008-07-28 22:50:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:54 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56262 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:55 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56263,138,0,19,53144,'AD_User_ID',TO_DATE('2008-07-28 22:50:54','YYYY-MM-DD HH24:MI:SS'),0,'User within the system - Internal or Business Partner Contact','EE01',10,'The User identifies a unique user in the system. This could be an internal user or a business partner contact','Y','N','N','N','N','N','N','N','N','N','User/Contact',TO_DATE('2008-07-28 22:50:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:55 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56263 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:56 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56264,952,0,10,53144,'POReference',TO_DATE('2008-07-28 22:50:55','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE01',20,'The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','N','N','N','N','N','N','N','N','N','Order Reference',TO_DATE('2008-07-28 22:50:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:56 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56264 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:57 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56265,1106,0,20,53144,'IsSOTrx',TO_DATE('2008-07-28 22:50:56','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction','EE01',1,'The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','N','N','N','N','N','N','N','N','N','Sales Transaction',TO_DATE('2008-07-28 22:50:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:57 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56265 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:50:58 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56266,550,0,19,53144,'C_Campaign_ID',TO_DATE('2008-07-28 22:50:57','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE01',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','N','N','Campaign',TO_DATE('2008-07-28 22:50:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:50:58 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56266 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:00 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56267,208,0,19,53144,'C_Project_ID',TO_DATE('2008-07-28 22:50:58','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE01',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','N','Project',TO_DATE('2008-07-28 22:50:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:00 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56267 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:01 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56268,1005,0,19,53144,'C_Activity_ID',TO_DATE('2008-07-28 22:51:00','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE01',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','N','N','Activity',TO_DATE('2008-07-28 22:51:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:01 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56268 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:02 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56269,53313,0,19,53144,'DD_OrderLine_ID',TO_DATE('2008-07-28 22:51:01','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Distribution Order Line',TO_DATE('2008-07-28 22:51:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:02 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56269 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:03 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56270,268,0,16,53144,'DateOrdered',TO_DATE('2008-07-28 22:51:02','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE01',29,'Indicates the Date an item was ordered.','Y','N','N','N','N','N','N','N','N','N','Date Ordered',TO_DATE('2008-07-28 22:51:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:03 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56270 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:04 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56271,269,0,16,53144,'DatePromised',TO_DATE('2008-07-28 22:51:03','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE01',29,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','N','N','N','N','N','N','N','Date Promised',TO_DATE('2008-07-28 22:51:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:04 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56271 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:05 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56272,454,0,19,53144,'M_Product_ID',TO_DATE('2008-07-28 22:51:04','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','N','N','Product',TO_DATE('2008-07-28 22:51:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:05 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56272 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:06 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56273,448,0,19,53144,'M_Locator_ID',TO_DATE('2008-07-28 22:51:05','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator','EE01',10,'The Locator indicates where in a Warehouse a product is located.','Y','N','N','N','N','N','N','N','N','N','Locator',TO_DATE('2008-07-28 22:51:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:06 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56273 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:07 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56274,1029,0,19,53144,'M_LocatorTo_ID',TO_DATE('2008-07-28 22:51:06','YYYY-MM-DD HH24:MI:SS'),0,'Location inventory is moved to','EE01',10,'The Locator To indicates the location where the inventory is being moved to.','Y','N','N','N','N','N','N','N','N','N','Locator To',TO_DATE('2008-07-28 22:51:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:07 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56274 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:08 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56275,2019,0,35,53144,'M_AttributeSetInstance_ID',TO_DATE('2008-07-28 22:51:07','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','N','N','Attribute Set Instance',TO_DATE('2008-07-28 22:51:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:08 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56275 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:09 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56276,2679,0,14,53144,'ProductAttribute',TO_DATE('2008-07-28 22:51:08','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Instance Description','EE01',2147483647,'Y','N','N','N','N','N','N','N','N','N','Product Attribute',TO_DATE('2008-07-28 22:51:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:09 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56276 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:10 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56277,2799,0,19,53144,'M_AttributeSetInstanceTo_ID',TO_DATE('2008-07-28 22:51:09','YYYY-MM-DD HH24:MI:SS'),0,'Target Product Attribute Set Instance','EE01',10,'Y','N','N','N','N','N','N','N','N','N','Attribute Set Instance To',TO_DATE('2008-07-28 22:51:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:10 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56277 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:11 PM CDT
-- Order Distribution
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53674,0,'productattributeto',TO_DATE('2008-07-28 22:51:10','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','productattributeto','productattributeto',TO_DATE('2008-07-28 22:51:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 10:51:11 PM CDT
-- Order Distribution
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53674 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 28, 2008 10:51:12 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56278,53674,0,14,53144,'productattributeto',TO_DATE('2008-07-28 22:51:10','YYYY-MM-DD HH24:MI:SS'),0,'EE01',2147483647,'Y','N','N','N','N','N','N','N','N','N','productattributeto',TO_DATE('2008-07-28 22:51:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:12 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56278 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:13 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56279,2017,0,19,53144,'M_AttributeSet_ID',TO_DATE('2008-07-28 22:51:12','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set','EE01',10,'Define Product Attribute Sets to add additional attributes and values to the product. You need to define a Attribute Set if you want to enable Serial and Lot Number tracking.','Y','N','N','N','N','N','N','N','N','N','Attribute Set',TO_DATE('2008-07-28 22:51:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:13 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56279 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:14 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56280,2021,0,19,53144,'M_Lot_ID',TO_DATE('2008-07-28 22:51:13','YYYY-MM-DD HH24:MI:SS'),0,'Product Lot Definition','EE01',10,'The individual Lot of a Product','Y','N','N','N','N','N','N','N','N','N','Lot',TO_DATE('2008-07-28 22:51:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:14 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56280 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:15 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56281,1936,0,16,53144,'GuaranteeDate',TO_DATE('2008-07-28 22:51:14','YYYY-MM-DD HH24:MI:SS'),0,'Date when guarantee expires','EE01',29,'Date when the normal guarantee or availability expires','Y','N','N','N','N','N','N','N','N','N','Guarantee Date',TO_DATE('2008-07-28 22:51:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:15 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56281 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:17 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56282,446,0,10,53144,'Lot',TO_DATE('2008-07-28 22:51:15','YYYY-MM-DD HH24:MI:SS'),0,'Lot number (alphanumeric)','EE01',40,'The Lot Number indicates the specific lot that a product was part of.','Y','N','N','N','N','N','N','N','N','N','Lot No',TO_DATE('2008-07-28 22:51:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:17 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56282 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:17 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56283,568,0,10,53144,'SerNo',TO_DATE('2008-07-28 22:51:17','YYYY-MM-DD HH24:MI:SS'),0,'Product Serial Number ','EE01',40,'The Serial Number identifies a tracked, warranted product.  It can only be used when the quantity is 1.','Y','N','N','N','N','N','N','N','N','N','Serial No',TO_DATE('2008-07-28 22:51:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:17 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56283 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:18 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56284,215,0,19,53144,'C_UOM_ID',TO_DATE('2008-07-28 22:51:17','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','N','N','UOM',TO_DATE('2008-07-28 22:51:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:18 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56284 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:20 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56285,2589,0,29,53144,'QtyEntered',TO_DATE('2008-07-28 22:51:18','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','EE01',131089,'The Quantity Entered is converted to base product UoM quantity','Y','N','N','N','N','N','N','N','N','N','Quantity',TO_DATE('2008-07-28 22:51:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:20 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56285 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:20 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56286,531,0,29,53144,'QtyOrdered',TO_DATE('2008-07-28 22:51:20','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','EE01',131089,'The Ordered Quantity indicates the quantity of a product that was ordered.','Y','N','N','N','N','N','N','N','N','N','Ordered Quantity',TO_DATE('2008-07-28 22:51:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:20 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56286 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:21 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56287,532,0,29,53144,'QtyReserved',TO_DATE('2008-07-28 22:51:20','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','EE01',131089,'The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','N','N','N','N','N','N','N','N','N','Reserved Quantity',TO_DATE('2008-07-28 22:51:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:21 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56287 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:22 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56288,528,0,29,53144,'QtyDelivered',TO_DATE('2008-07-28 22:51:21','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE01',131089,'The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','N','N','N','N','N','N','N','N','N','Delivered Quantity',TO_DATE('2008-07-28 22:51:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:22 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56288 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:23 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56289,2386,0,29,53144,'ConfirmedQty',TO_DATE('2008-07-28 22:51:22','YYYY-MM-DD HH24:MI:SS'),0,'Confirmation of a received quantity','EE01',131089,'Confirmation of a received quantity','Y','N','N','N','N','N','N','N','N','N','Confirmed Quantity',TO_DATE('2008-07-28 22:51:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:23 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56289 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:24 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56290,53312,0,29,53144,'QtyInTransit',TO_DATE('2008-07-28 22:51:23','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','QtyInTransit',TO_DATE('2008-07-28 22:51:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:24 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56290 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:25 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56291,2436,0,29,53144,'TargetQty',TO_DATE('2008-07-28 22:51:24','YYYY-MM-DD HH24:MI:SS'),0,'Target Movement Quantity','EE01',131089,'The Quantity which should have been received','Y','N','N','N','N','N','N','N','N','N','Target Quantity',TO_DATE('2008-07-28 22:51:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:25 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56291 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:27 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56292,1250,0,29,53144,'QtyToDeliver',TO_DATE('2008-07-28 22:51:25','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Qty to deliver',TO_DATE('2008-07-28 22:51:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:51:27 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56292 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:51:49 PM CDT
-- Order Distribution
UPDATE AD_Column SET AD_Val_Rule_ID=129,Updated=TO_DATE('2008-07-28 22:51:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56249
;

-- Jul 28, 2008 10:52:05 PM CDT
-- Order Distribution
UPDATE AD_Column SET AD_Val_Rule_ID=130,Updated=TO_DATE('2008-07-28 22:52:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56250
;

-- Jul 28, 2008 10:53:50 PM CDT
-- Order Distribution
UPDATE AD_Column SET AD_Reference_Value_ID=135,Updated=TO_DATE('2008-07-28 22:53:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56258
;

-- Jul 28, 2008 10:54:01 PM CDT
-- Order Distribution
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56293,53311,0,19,53144,'DD_Order_ID',TO_DATE('2008-07-28 22:53:59','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Distribution Order',TO_DATE('2008-07-28 22:53:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 28, 2008 10:54:01 PM CDT
-- Order Distribution
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56293 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 28, 2008 10:54:35 PM CDT
-- Order Distribution
UPDATE AD_Column SET AD_Reference_Value_ID=131,Updated=TO_DATE('2008-07-28 22:54:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56257
;

-- Jul 28, 2008 10:56:13 PM CDT
-- Order Distribution
UPDATE AD_Column SET AD_Reference_ID=35,Updated=TO_DATE('2008-07-28 22:56:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56277
;

-- Jul 28, 2008 10:56:32 PM CDT
-- Order Distribution
UPDATE AD_Column SET AD_Reference_ID=31,Updated=TO_DATE('2008-07-28 22:56:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56274
;

-- Jul 28, 2008 10:56:38 PM CDT
-- Order Distribution
UPDATE AD_Column SET AD_Reference_ID=31,Updated=TO_DATE('2008-07-28 22:56:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56273
;

-- Jul 28, 2008 10:59:02 PM CDT
-- Order Distribution
UPDATE AD_Element SET ColumnName='ProductAttributeTo', Name='Product Attribute To', PrintName='Product Attribute To',Updated=TO_DATE('2008-07-28 22:59:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53674
;

-- Jul 28, 2008 10:59:02 PM CDT
-- Order Distribution
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53674
;

-- Jul 28, 2008 10:59:02 PM CDT
-- Order Distribution
UPDATE AD_Column SET ColumnName='ProductAttributeTo', Name='Product Attribute To', Description=NULL, Help=NULL WHERE AD_Element_ID=53674
;

-- Jul 28, 2008 10:59:02 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET ColumnName='ProductAttributeTo', Name='Product Attribute To', Description=NULL, Help=NULL, AD_Element_ID=53674 WHERE UPPER(ColumnName)='PRODUCTATTRIBUTETO' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jul 28, 2008 10:59:02 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET ColumnName='ProductAttributeTo', Name='Product Attribute To', Description=NULL, Help=NULL WHERE AD_Element_ID=53674 AND IsCentrallyMaintained='Y'
;

-- Jul 28, 2008 10:59:02 PM CDT
-- Order Distribution
UPDATE AD_Field SET Name='Product Attribute To', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53674) AND IsCentrallyMaintained='Y'
;

-- Jul 28, 2008 10:59:03 PM CDT
-- Order Distribution
UPDATE AD_PrintFormatItem pi SET PrintName='Product Attribute To', Name='Product Attribute To' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53674)
;

-- Jul 28, 2008 10:59:25 PM CDT
-- Order Distribution
UPDATE AD_Element SET Description='Product Attribute Instance Description',Updated=TO_DATE('2008-07-28 22:59:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53674
;

-- Jul 28, 2008 10:59:25 PM CDT
-- Order Distribution
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53674
;

-- Jul 28, 2008 10:59:25 PM CDT
-- Order Distribution
UPDATE AD_Column SET ColumnName='ProductAttributeTo', Name='Product Attribute To', Description='Product Attribute Instance Description', Help=NULL WHERE AD_Element_ID=53674
;

-- Jul 28, 2008 10:59:25 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET ColumnName='ProductAttributeTo', Name='Product Attribute To', Description='Product Attribute Instance Description', Help=NULL, AD_Element_ID=53674 WHERE UPPER(ColumnName)='PRODUCTATTRIBUTETO' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jul 28, 2008 10:59:25 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET ColumnName='ProductAttributeTo', Name='Product Attribute To', Description='Product Attribute Instance Description', Help=NULL WHERE AD_Element_ID=53674 AND IsCentrallyMaintained='Y'
;

-- Jul 28, 2008 10:59:25 PM CDT
-- Order Distribution
UPDATE AD_Field SET Name='Product Attribute To', Description='Product Attribute Instance Description', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53674) AND IsCentrallyMaintained='Y'
;

-- Jul 28, 2008 11:00:07 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:00:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56278
;

-- Jul 28, 2008 11:00:20 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:00:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56291
;

-- Jul 28, 2008 11:00:27 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:00:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56292
;

-- Jul 28, 2008 11:00:34 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:00:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56287
;

-- Jul 28, 2008 11:00:40 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:00:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56286
;

-- Jul 28, 2008 11:00:43 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:00:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56290
;

-- Jul 28, 2008 11:00:49 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:00:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56285
;

-- Jul 28, 2008 11:00:54 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:00:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56288
;

-- Jul 28, 2008 11:01:00 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:01:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56276
;

-- Jul 28, 2008 11:01:06 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:01:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56264
;

-- Jul 28, 2008 11:01:23 PM CDT
-- Order Distribution
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:01:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56289
;

-- Jul 28, 2008 11:02:49 PM CDT
-- Order Distribution
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53023,53144,TO_DATE('2008-07-28 23:02:48','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','RV_DD_OrderDetail',TO_DATE('2008-07-28 23:02:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:04:34 PM CDT
-- Order Distribution
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_ReportView_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53151,53023,'3',TO_DATE('2008-07-28 23:04:33','YYYY-MM-DD HH24:MI:SS'),0,'Distribution Order Detail Report','EE01','The report includes order detail information Distribution Orders.','Y','N','N','Y','N','Distribution Order Detail','Y',0,0,TO_DATE('2008-07-28 23:04:33','YYYY-MM-DD HH24:MI:SS'),0,'RV_DD_OrderDetail')
;

-- Jul 28, 2008 11:04:34 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53151 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Jul 28, 2008 11:04:34 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53151,0,TO_DATE('2008-07-28 23:04:34','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-07-28 23:04:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:04:34 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53151,103,TO_DATE('2008-07-28 23:04:34','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-07-28 23:04:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:04:34 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53151,50001,TO_DATE('2008-07-28 23:04:34','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-07-28 23:04:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:04:35 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53151,102,TO_DATE('2008-07-28 23:04:35','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-07-28 23:04:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:04:35 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53151,50002,TO_DATE('2008-07-28 23:04:35','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-07-28 23:04:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:06:31 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,196,0,53151,53224,19,52004,'C_DocType_ID',TO_DATE('2008-07-28 23:06:30','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE01',0,'The Document Type determines document sequence and processing rules','Y','Y','N','N','Document Type',10,TO_DATE('2008-07-28 23:06:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:06:31 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53224 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 11:07:32 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,187,0,53151,53225,30,'C_BPartner_ID',TO_DATE('2008-07-28 23:07:21','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE01',0,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','Business Partner ',20,TO_DATE('2008-07-28 23:07:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:07:32 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53225 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 11:08:31 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53151,53226,30,'M_Product_ID',TO_DATE('2008-07-28 23:08:19','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',0,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','Product',30,TO_DATE('2008-07-28 23:08:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:08:31 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53226 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 11:09:31 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,268,0,53151,53227,15,'DateOrdered',TO_DATE('2008-07-28 23:09:30','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE01',0,'Indicates the Date an item was ordered.','Y','Y','N','N','Date Ordered',40,TO_DATE('2008-07-28 23:09:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:09:31 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53227 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 11:10:29 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,269,0,53151,53228,15,'DatePromised',TO_DATE('2008-07-28 23:10:25','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE01',0,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','N','Y','Date Promised',40,TO_DATE('2008-07-28 23:10:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:10:29 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53228 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 11:10:34 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET IsRange='Y',Updated=TO_DATE('2008-07-28 23:10:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53227
;

-- Jul 28, 2008 11:11:59 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,289,0,53151,53229,17,131,'DocStatus',TO_DATE('2008-07-28 23:11:54','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE01',0,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','N','N','Document Status',50,TO_DATE('2008-07-28 23:11:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:11:59 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53229 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 11:13:57 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,448,0,53151,53230,19,'M_Locator_ID',TO_DATE('2008-07-28 23:13:56','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator','EE01',10,'The Locator indicates where in a Warehouse a product is located.','Y','Y','N','N','Locator',60,TO_DATE('2008-07-28 23:13:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:13:57 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53230 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 11:15:02 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1029,0,53151,53231,18,191,'M_LocatorTo_ID',TO_DATE('2008-07-28 23:15:00','YYYY-MM-DD HH24:MI:SS'),0,'Location inventory is moved to','EE01',10,'The Locator To indicates the location where the inventory is being moved to.','Y','Y','N','N','Locator To',60,TO_DATE('2008-07-28 23:15:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:15:02 PM CDT
-- Order Distribution
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53231 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 28, 2008 11:15:18 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:15:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53224
;

-- Jul 28, 2008 11:15:23 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:15:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53225
;

-- Jul 28, 2008 11:15:28 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:15:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53226
;

-- Jul 28, 2008 11:15:37 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:15:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53227
;

-- Jul 28, 2008 11:15:51 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET SeqNo=50,Updated=TO_DATE('2008-07-28 23:15:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53228
;

-- Jul 28, 2008 11:16:01 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:16:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53228
;

-- Jul 28, 2008 11:16:14 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-07-28 23:16:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53229
;

-- Jul 28, 2008 11:16:32 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET SeqNo=70,Updated=TO_DATE('2008-07-28 23:16:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53231
;

-- Jul 28, 2008 11:16:50 PM CDT
-- Order Distribution
UPDATE AD_Process_Para SET SeqNo=80,Updated=TO_DATE('2008-07-28 23:16:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53229
;

-- Jul 28, 2008 11:18:10 PM CDT
-- Order Distribution
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=54027
;

-- Jul 28, 2008 11:18:10 PM CDT
-- Order Distribution
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54031
;

-- Jul 28, 2008 11:19:21 PM CDT
-- Order Distribution
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53185,0,53151,'R',TO_DATE('2008-07-28 23:19:20','YYYY-MM-DD HH24:MI:SS'),0,'Distribution Order Detail Report','EE01','Y','N','N','N','Distribution Order Detail',TO_DATE('2008-07-28 23:19:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 28, 2008 11:19:21 PM CDT
-- Order Distribution
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53185 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Jul 28, 2008 11:19:21 PM CDT
-- Order Distribution
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53185, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53185)
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53105
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=1000007
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=1000009
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53067
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=381
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53088
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=128
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53068
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53185
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=445
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=472
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=1000041
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53069
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53070
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=181
;

-- Jul 28, 2008 11:19:43 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=484
;

--DROP VIEW RV_DD_ORDERDETAIL;
CREATE OR REPLACE VIEW RV_DD_ORDERDETAIL AS
SELECT l.AD_Client_ID, l.AD_Org_ID, 
	l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,
	o.C_Order_ID, o.DocStatus, o.DocAction, o.C_DocType_ID, o.IsApproved, --o.IsCreditApproved,
	o.SalesRep_ID, 
	o.IsDropShip, 
	o.AD_User_ID,
	o.POReference, 
	o.IsSOTrx,
	l.C_Campaign_ID, l.C_Project_ID, l.C_Activity_ID, 
	--l.C_ProjectPhase_ID, l.C_ProjectTask_ID,
	l.DD_OrderLine_ID, l.DateOrdered, l.DatePromised, l.M_Product_ID, l.M_Locator_ID,l.M_LocatorTo_ID,
	l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
	l.M_AttributeSetInstanceTo_ID, productAttribute(l.M_AttributeSetInstanceTo_ID) AS ProductAttributeTo,
	pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	l.C_UOM_ID, l.QtyEntered, l.QtyOrdered, l.QtyReserved, l.QtyDelivered, l.Confirmedqty,  l.Qtyintransit, l.TargetQty,
	l.QtyOrdered-l.QtyDelivered AS QtyToDeliver
FROM DD_Order o
  INNER JOIN DD_OrderLine l ON (o.DD_Order_ID=l.DD_Order_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasito ON (l.M_AttributeSetInstanceTo_ID=pasito.M_AttributeSetInstance_ID);

