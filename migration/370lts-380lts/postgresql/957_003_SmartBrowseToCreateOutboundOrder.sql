-- Sep 2, 2009 1:30:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View (AD_Client_ID,AD_Org_ID,AD_View_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50000,TO_TIMESTAMP('2009-09-02 01:30:19','YYYY-MM-DD HH24:MI:SS'),0,'Allow select the Sales Order lines to create a Outbound Order','EE07','Y','Sales Order to Picking',TO_TIMESTAMP('2009-09-02 01:30:19','YYYY-MM-DD HH24:MI:SS'),0,'SalesOrderToPicking')
;

-- Sep 2, 2009 1:30:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Trl (AD_Language,AD_View_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_View_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_View t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_View_ID=50000 AND EXISTS (SELECT * FROM AD_View_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_View_ID!=t.AD_View_ID)
;

-- Sep 2, 2009 1:30:46 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,259,50000,50000,TO_TIMESTAMP('2009-09-02 01:30:45','YYYY-MM-DD HH24:MI:SS'),0,'Y','N',10,'o',TO_TIMESTAMP('2009-09-02 01:30:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:55 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2168,0,50000,50000,50000,'C_Order_UpdatedBy','o.UpdatedBy',TO_TIMESTAMP('2009-09-02 01:30:55','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2009-09-02 01:30:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:56 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2161,0,50001,50000,50000,'C_Order_C_Order_ID','o.C_Order_ID',TO_TIMESTAMP('2009-09-02 01:30:55','YYYY-MM-DD HH24:MI:SS'),0,'Order','D','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Order',TO_TIMESTAMP('2009-09-02 01:30:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:56 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3046,0,50002,50000,50000,'C_Order_C_Charge_ID','o.C_Charge_ID',TO_TIMESTAMP('2009-09-02 01:30:56','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','D','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Charge',TO_TIMESTAMP('2009-09-02 01:30:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:57 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2192,0,50003,50000,50000,'C_Order_InvoiceRule','o.InvoiceRule',TO_TIMESTAMP('2009-09-02 01:30:56','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','D','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Invoice Rule',TO_TIMESTAMP('2009-09-02 01:30:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:58 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2195,0,50004,50000,50000,'C_Order_FreightAmt','o.FreightAmt',TO_TIMESTAMP('2009-09-02 01:30:57','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','D','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Freight Amount',TO_TIMESTAMP('2009-09-02 01:30:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:58 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2196,0,50005,50000,50000,'C_Order_DeliveryViaRule','o.DeliveryViaRule',TO_TIMESTAMP('2009-09-02 01:30:58','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','D','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Delivery Via',TO_TIMESTAMP('2009-09-02 01:30:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:59 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2175,0,50006,50000,50000,'C_Order_IsApproved','o.IsApproved',TO_TIMESTAMP('2009-09-02 01:30:58','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','D','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Approved',TO_TIMESTAMP('2009-09-02 01:30:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:59 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2176,0,50007,50000,50000,'C_Order_IsCreditApproved','o.IsCreditApproved',TO_TIMESTAMP('2009-09-02 01:30:59','YYYY-MM-DD HH24:MI:SS'),0,'Credit  has been approved','D','Credit Approved indicates if the credit approval was successful for Orders','Y','Credit Approved',TO_TIMESTAMP('2009-09-02 01:30:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:30:59 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,2177,0,50008,50000,50000,'C_Order_IsDelivered','o.IsDelivered',TO_TIMESTAMP('2009-09-02 01:30:59','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Delivered',TO_TIMESTAMP('2009-09-02 01:30:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:00 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2178,0,50009,50000,50000,'C_Order_IsInvoiced','o.IsInvoiced',TO_TIMESTAMP('2009-09-02 01:30:59','YYYY-MM-DD HH24:MI:SS'),0,'Is this invoiced?','D','If selected, invoices are created','Y','Invoiced',TO_TIMESTAMP('2009-09-02 01:30:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:00 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2202,0,50010,50000,50000,'C_Order_M_Warehouse_ID','o.M_Warehouse_ID',TO_TIMESTAMP('2009-09-02 01:31:00','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','D','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_TIMESTAMP('2009-09-02 01:31:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:01 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2164,0,50011,50000,50000,'C_Order_IsActive','o.IsActive',TO_TIMESTAMP('2009-09-02 01:31:00','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2009-09-02 01:31:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:01 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2183,0,50012,50000,50000,'C_Order_DateAcct','o.DateAcct',TO_TIMESTAMP('2009-09-02 01:31:01','YYYY-MM-DD HH24:MI:SS'),0,'Accounting Date','D','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Account Date',TO_TIMESTAMP('2009-09-02 01:31:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:02 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2186,0,50013,50000,50000,'C_Order_SalesRep_ID','o.SalesRep_ID',TO_TIMESTAMP('2009-09-02 01:31:01','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','D','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Sales Representative',TO_TIMESTAMP('2009-09-02 01:31:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:02 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2165,0,50014,50000,50000,'C_Order_Created','o.Created',TO_TIMESTAMP('2009-09-02 01:31:02','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2009-09-02 01:31:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:03 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2454,0,50015,50000,50000,'C_Order_C_Campaign_ID','o.C_Campaign_ID',TO_TIMESTAMP('2009-09-02 01:31:02','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','D','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Campaign',TO_TIMESTAMP('2009-09-02 01:31:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:03 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2187,0,50016,50000,50000,'C_Order_C_PaymentTerm_ID','o.C_PaymentTerm_ID',TO_TIMESTAMP('2009-09-02 01:31:03','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','D','Payment Terms identify the method and timing of payment.','Y','Payment Term',TO_TIMESTAMP('2009-09-02 01:31:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:04 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2173,0,50017,50000,50000,'C_Order_C_DocTypeTarget_ID','o.C_DocTypeTarget_ID',TO_TIMESTAMP('2009-09-02 01:31:03','YYYY-MM-DD HH24:MI:SS'),0,'Target document type for conversing documents','D','You can convert document types (e.g. from Offer to Order or Invoice).  The conversion is then reflected in the current type.  This processing is initiated by selecting the appropriate Document Action.','Y','Target Document Type',TO_TIMESTAMP('2009-09-02 01:31:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:04 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2191,0,50018,50000,50000,'C_Order_C_Currency_ID','o.C_Currency_ID',TO_TIMESTAMP('2009-09-02 01:31:04','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','D','Indicates the Currency to be used when processing or reporting on this record','Y','Currency',TO_TIMESTAMP('2009-09-02 01:31:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:05 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2200,0,50019,50000,50000,'C_Order_TotalLines','o.TotalLines',TO_TIMESTAMP('2009-09-02 01:31:04','YYYY-MM-DD HH24:MI:SS'),0,'Total of all document lines','D','The Total amount displays the total of all lines in document currency','Y','Total Lines',TO_TIMESTAMP('2009-09-02 01:31:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:05 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2201,0,50020,50000,50000,'C_Order_GrandTotal','o.GrandTotal',TO_TIMESTAMP('2009-09-02 01:31:05','YYYY-MM-DD HH24:MI:SS'),0,'Total amount of document','D','The Grand Total displays the total amount including Tax and Freight in document currency','Y','Grand Total',TO_TIMESTAMP('2009-09-02 01:31:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:06 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2171,0,50021,50000,50000,'C_Order_DocAction','o.DocAction',TO_TIMESTAMP('2009-09-02 01:31:05','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','D','You find the current status in the Document Status field. The options are listed in a popup','Y','Document Action',TO_TIMESTAMP('2009-09-02 01:31:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:07 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2172,0,50022,50000,50000,'C_Order_C_DocType_ID','o.C_DocType_ID',TO_TIMESTAMP('2009-09-02 01:31:06','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','D','The Document Type determines document sequence and processing rules','Y','Document Type',TO_TIMESTAMP('2009-09-02 01:31:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:07 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2170,0,50023,50000,50000,'C_Order_DocStatus','o.DocStatus',TO_TIMESTAMP('2009-09-02 01:31:07','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','D','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Document Status',TO_TIMESTAMP('2009-09-02 01:31:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:07 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2163,0,50024,50000,50000,'C_Order_AD_Org_ID','o.AD_Org_ID',TO_TIMESTAMP('2009-09-02 01:31:07','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2009-09-02 01:31:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:08 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2169,0,50025,50000,50000,'C_Order_DocumentNo','o.DocumentNo',TO_TIMESTAMP('2009-09-02 01:31:07','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','D','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Document No',TO_TIMESTAMP('2009-09-02 01:31:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:08 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2179,0,50026,50000,50000,'C_Order_IsPrinted','o.IsPrinted',TO_TIMESTAMP('2009-09-02 01:31:08','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','D','The Printed checkbox indicates if this document or line will included when printing.','Y','Printed',TO_TIMESTAMP('2009-09-02 01:31:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:09 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2198,0,50027,50000,50000,'C_Order_PriorityRule','o.PriorityRule',TO_TIMESTAMP('2009-09-02 01:31:08','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','D','The Priority indicates the importance (high, medium, low) of this document','Y','Priority',TO_TIMESTAMP('2009-09-02 01:31:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:09 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9569,0,50028,50000,50000,'C_Order_User1_ID','o.User1_ID',TO_TIMESTAMP('2009-09-02 01:31:09','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','D','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 1',TO_TIMESTAMP('2009-09-02 01:31:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2763,0,50029,50000,50000,'C_Order_AD_User_ID','o.AD_User_ID',TO_TIMESTAMP('2009-09-02 01:31:09','YYYY-MM-DD HH24:MI:SS'),0,'User within the system - Internal or Business Partner Contact','D','The User identifies a unique user in the system. This could be an internal user or a business partner contact','Y','User/Contact',TO_TIMESTAMP('2009-09-02 01:31:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:11 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2762,0,50030,50000,50000,'C_Order_C_BPartner_ID','o.C_BPartner_ID',TO_TIMESTAMP('2009-09-02 01:31:10','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_TIMESTAMP('2009-09-02 01:31:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:11 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2162,0,50031,50000,50000,'C_Order_AD_Client_ID','o.AD_Client_ID',TO_TIMESTAMP('2009-09-02 01:31:11','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2009-09-02 01:31:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:12 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3402,0,50032,50000,50000,'C_Order_C_Project_ID','o.C_Project_ID',TO_TIMESTAMP('2009-09-02 01:31:11','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','D','A Project allows you to track and control internal or external activities.','Y','Project',TO_TIMESTAMP('2009-09-02 01:31:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:13 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3045,0,50033,50000,50000,'C_Order_POReference','o.POReference',TO_TIMESTAMP('2009-09-02 01:31:12','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','D','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Order Reference',TO_TIMESTAMP('2009-09-02 01:31:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:13 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2174,0,50034,50000,50000,'C_Order_Description','o.Description',TO_TIMESTAMP('2009-09-02 01:31:13','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Description',TO_TIMESTAMP('2009-09-02 01:31:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:14 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2197,0,50035,50000,50000,'C_Order_M_Shipper_ID','o.M_Shipper_ID',TO_TIMESTAMP('2009-09-02 01:31:13','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','D','The Shipper indicates the method of delivering product','Y','Shipper',TO_TIMESTAMP('2009-09-02 01:31:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:14 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3047,0,50036,50000,50000,'C_Order_ChargeAmt','o.ChargeAmt',TO_TIMESTAMP('2009-09-02 01:31:14','YYYY-MM-DD HH24:MI:SS'),0,'Charge Amount','D','The Charge Amount indicates the amount for an additional charge.','Y','Charge amount',TO_TIMESTAMP('2009-09-02 01:31:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2166,0,50037,50000,50000,'C_Order_CreatedBy','o.CreatedBy',TO_TIMESTAMP('2009-09-02 01:31:14','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2009-09-02 01:31:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2180,0,50038,50000,50000,'C_Order_IsTransferred','o.IsTransferred',TO_TIMESTAMP('2009-09-02 01:31:15','YYYY-MM-DD HH24:MI:SS'),0,'Transferred to General Ledger (i.e. accounted)','D','The transferred checkbox indicates if the transactions associated with this document should be transferred to the General Ledger.','Y','Transferred',TO_TIMESTAMP('2009-09-02 01:31:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2181,0,50039,50000,50000,'C_Order_DateOrdered','o.DateOrdered',TO_TIMESTAMP('2009-09-02 01:31:15','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','D','Indicates the Date an item was ordered.','Y','Date Ordered',TO_TIMESTAMP('2009-09-02 01:31:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4298,0,50040,50000,50000,'C_Order_IsDiscountPrinted','o.IsDiscountPrinted',TO_TIMESTAMP('2009-09-02 01:31:16','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','D','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Discount Printed',TO_TIMESTAMP('2009-09-02 01:31:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,2453,0,50041,50000,50000,'C_Order_Processing','o.Processing',TO_TIMESTAMP('2009-09-02 01:31:16','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Process Now',TO_TIMESTAMP('2009-09-02 01:31:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2167,0,50042,50000,50000,'C_Order_Updated','o.Updated',TO_TIMESTAMP('2009-09-02 01:31:16','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2009-09-02 01:31:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2182,0,50043,50000,50000,'C_Order_DatePromised','o.DatePromised',TO_TIMESTAMP('2009-09-02 01:31:17','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','D','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Date Promised',TO_TIMESTAMP('2009-09-02 01:31:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,4699,0,50044,50000,50000,'C_Order_IsSelected','o.IsSelected',TO_TIMESTAMP('2009-09-02 01:31:18','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Selected',TO_TIMESTAMP('2009-09-02 01:31:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3721,0,50045,50000,50000,'C_Order_DeliveryRule','o.DeliveryRule',TO_TIMESTAMP('2009-09-02 01:31:18','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','D','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Delivery Rule',TO_TIMESTAMP('2009-09-02 01:31:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3400,0,50046,50000,50000,'C_Order_C_BPartner_Location_ID','o.C_BPartner_Location_ID',TO_TIMESTAMP('2009-09-02 01:31:18','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','D','The Partner address indicates the location of a Business Partner','Y','Partner Location',TO_TIMESTAMP('2009-09-02 01:31:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4650,0,50047,50000,50000,'C_Order_Posted','o.Posted',TO_TIMESTAMP('2009-09-02 01:31:19','YYYY-MM-DD HH24:MI:SS'),0,'Posting status','D','The Posted field indicates the status of the Generation of General Ledger Accounting Lines ','Y','Posted',TO_TIMESTAMP('2009-09-02 01:31:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3719,0,50048,50000,50000,'C_Order_DatePrinted','o.DatePrinted',TO_TIMESTAMP('2009-09-02 01:31:19','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.','D','Indicates the Date that a document was printed.','Y','Date printed',TO_TIMESTAMP('2009-09-02 01:31:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3718,0,50049,50000,50000,'C_Order_IsSOTrx','o.IsSOTrx',TO_TIMESTAMP('2009-09-02 01:31:20','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction','D','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Sales Transaction',TO_TIMESTAMP('2009-09-02 01:31:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3403,0,50050,50000,50000,'C_Order_C_Activity_ID','o.C_Activity_ID',TO_TIMESTAMP('2009-09-02 01:31:20','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','D','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Activity',TO_TIMESTAMP('2009-09-02 01:31:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8166,0,50051,50000,50000,'C_Order_SendEMail','o.SendEMail',TO_TIMESTAMP('2009-09-02 01:31:21','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','D','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Send EMail',TO_TIMESTAMP('2009-09-02 01:31:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3722,0,50052,50000,50000,'C_Order_FreightCostRule','o.FreightCostRule',TO_TIMESTAMP('2009-09-02 01:31:21','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','D','The Freight Cost Rule indicates the method used when charging for freight.','Y','Freight Cost Rule',TO_TIMESTAMP('2009-09-02 01:31:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8764,0,50053,50000,50000,'C_Order_Bill_BPartner_ID','o.Bill_BPartner_ID',TO_TIMESTAMP('2009-09-02 01:31:22','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to be invoiced','D','If empty the shipment business partner will be invoiced','Y','Invoice Partner',TO_TIMESTAMP('2009-09-02 01:31:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,5348,0,50054,50000,50000,'C_Order_C_Payment_ID','o.C_Payment_ID',TO_TIMESTAMP('2009-09-02 01:31:22','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','D','The Payment is a unique identifier of this payment.','Y','Payment',TO_TIMESTAMP('2009-09-02 01:31:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,10925,0,50055,50000,50000,'C_Order_Pay_BPartner_ID','o.Pay_BPartner_ID',TO_TIMESTAMP('2009-09-02 01:31:22','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner responsible for the payment','D','Y','Payment BPartner',TO_TIMESTAMP('2009-09-02 01:31:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,5349,0,50056,50000,50000,'C_Order_C_CashLine_ID','o.C_CashLine_ID',TO_TIMESTAMP('2009-09-02 01:31:23','YYYY-MM-DD HH24:MI:SS'),0,'Cash Journal Line','D','The Cash Journal Line indicates a unique line in a cash journal.','Y','Cash Journal Line',TO_TIMESTAMP('2009-09-02 01:31:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,8763,0,50057,50000,50000,'C_Order_Bill_User_ID','o.Bill_User_ID',TO_TIMESTAMP('2009-09-02 01:31:23','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for invoicing','D','Y','Invoice Contact',TO_TIMESTAMP('2009-09-02 01:31:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8765,0,50058,50000,50000,'C_Order_CopyFrom','o.CopyFrom',TO_TIMESTAMP('2009-09-02 01:31:24','YYYY-MM-DD HH24:MI:SS'),0,'Copy From Record','D','Copy From Record','Y','Copy From',TO_TIMESTAMP('2009-09-02 01:31:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:25 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,8766,0,50059,50000,50000,'C_Order_Bill_Location_ID','o.Bill_Location_ID',TO_TIMESTAMP('2009-09-02 01:31:24','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for invoicing','D','Y','Invoice Location',TO_TIMESTAMP('2009-09-02 01:31:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:25 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3398,0,50060,50000,50000,'C_Order_Processed','o.Processed',TO_TIMESTAMP('2009-09-02 01:31:25','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','D','The Processed checkbox indicates that a document has been processed.','Y','Processed',TO_TIMESTAMP('2009-09-02 01:31:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:26 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4651,0,50061,50000,50000,'C_Order_IsTaxIncluded','o.IsTaxIncluded',TO_TIMESTAMP('2009-09-02 01:31:25','YYYY-MM-DD HH24:MI:SS'),0,'Tax is included in the price ','D','The Tax Included checkbox indicates if the prices include tax.  This is also known as the gross price.','Y','Price includes Tax',TO_TIMESTAMP('2009-09-02 01:31:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:26 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8832,0,50062,50000,50000,'C_Order_IsSelfService','o.IsSelfService',TO_TIMESTAMP('2009-09-02 01:31:26','YYYY-MM-DD HH24:MI:SS'),0,'This is a Self-Service entry or this entry can be changed via Self-Service','D','Self-Service allows users to enter data or update their data.  The flag indicates, that this record was entered or created via Self-Service or that the user can change it via the Self-Service functionality.','Y','Self-Service',TO_TIMESTAMP('2009-09-02 01:31:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:27 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9568,0,50063,50000,50000,'C_Order_User2_ID','o.User2_ID',TO_TIMESTAMP('2009-09-02 01:31:26','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','D','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 2',TO_TIMESTAMP('2009-09-02 01:31:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,10924,0,50064,50000,50000,'C_Order_Pay_Location_ID','o.Pay_Location_ID',TO_TIMESTAMP('2009-09-02 01:31:27','YYYY-MM-DD HH24:MI:SS'),0,'Location of the Business Partner responsible for the payment','D','Y','Payment Location',TO_TIMESTAMP('2009-09-02 01:31:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9331,0,50065,50000,50000,'C_Order_AD_OrgTrx_ID','o.AD_OrgTrx_ID',TO_TIMESTAMP('2009-09-02 01:31:28','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','D','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Trx Organization',TO_TIMESTAMP('2009-09-02 01:31:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10297,0,50066,50000,50000,'C_Order_C_ConversionType_ID','o.C_ConversionType_ID',TO_TIMESTAMP('2009-09-02 01:31:28','YYYY-MM-DD HH24:MI:SS'),0,'Currency Conversion Rate Type','D','The Currency Conversion Rate Type lets you define different type of rates, e.g. Spot, Corporate and/or Sell/Buy rates.','Y','Currency Type',TO_TIMESTAMP('2009-09-02 01:31:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10926,0,50067,50000,50000,'C_Order_Ref_Order_ID','o.Ref_Order_ID',TO_TIMESTAMP('2009-09-02 01:31:29','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','D','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Referenced Order',TO_TIMESTAMP('2009-09-02 01:31:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:30 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15899,0,50068,50000,50000,'C_Order_Volume','o.Volume',TO_TIMESTAMP('2009-09-02 01:31:29','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','D','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Volume',TO_TIMESTAMP('2009-09-02 01:31:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:30 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15900,0,50069,50000,50000,'C_Order_Weight','o.Weight',TO_TIMESTAMP('2009-09-02 01:31:30','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','D','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Weight',TO_TIMESTAMP('2009-09-02 01:31:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2204,0,50070,50000,50000,'C_Order_M_PriceList_ID','o.M_PriceList_ID',TO_TIMESTAMP('2009-09-02 01:31:30','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','D','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Price List',TO_TIMESTAMP('2009-09-02 01:31:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52063,0,50071,50000,50000,'C_Order_OrderType','o.OrderType',TO_TIMESTAMP('2009-09-02 01:31:31','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','OrderType',TO_TIMESTAMP('2009-09-02 01:31:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:32 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52064,0,50072,50000,50000,'C_Order_AmountTendered','o.AmountTendered',TO_TIMESTAMP('2009-09-02 01:31:31','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','AmountTendered',TO_TIMESTAMP('2009-09-02 01:31:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:32 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52065,0,50073,50000,50000,'C_Order_AmountRefunded','o.AmountRefunded',TO_TIMESTAMP('2009-09-02 01:31:32','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','AmountRefunded',TO_TIMESTAMP('2009-09-02 01:31:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:33 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,52070,0,50074,50000,50000,'C_Order_C_POS_ID','o.C_POS_ID',TO_TIMESTAMP('2009-09-02 01:31:32','YYYY-MM-DD HH24:MI:SS'),0,'Point of Sales Terminal','D','The POS Terminal defines the defaults and functions available for the POS Form','Y','POS Terminal',TO_TIMESTAMP('2009-09-02 01:31:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:33 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,55322,0,50075,50000,50000,'C_Order_Link_Order_ID','o.Link_Order_ID',TO_TIMESTAMP('2009-09-02 01:31:33','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order to the purchase order that is generated from it.','D','Y','Linked Order',TO_TIMESTAMP('2009-09-02 01:31:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4019,0,50076,50000,50000,'C_Order_PaymentRule','o.PaymentRule',TO_TIMESTAMP('2009-09-02 01:31:33','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','D','The Payment Rule indicates the method of invoice payment.','Y','Payment Rule',TO_TIMESTAMP('2009-09-02 01:31:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:35 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,56376,0,50077,50000,50000,'C_Order_M_FreightCategory_ID','o.M_FreightCategory_ID',TO_TIMESTAMP('2009-09-02 01:31:34','YYYY-MM-DD HH24:MI:SS'),0,'Category of the Freight','D','Freight Categories are used to calculate the Freight for the Shipper selected','Y','Freight Category',TO_TIMESTAMP('2009-09-02 01:31:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:35 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,55314,0,50078,50000,50000,'C_Order_DropShip_BPartner_ID','o.DropShip_BPartner_ID',TO_TIMESTAMP('2009-09-02 01:31:35','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to','D','If empty the business partner will be shipped to.','Y','Drop Shipment Partner',TO_TIMESTAMP('2009-09-02 01:31:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,55315,0,50079,50000,50000,'C_Order_DropShip_Location_ID','o.DropShip_Location_ID',TO_TIMESTAMP('2009-09-02 01:31:35','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to','D','Y','Drop Shipment Location',TO_TIMESTAMP('2009-09-02 01:31:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,55316,0,50080,50000,50000,'C_Order_DropShip_User_ID','o.DropShip_User_ID',TO_TIMESTAMP('2009-09-02 01:31:36','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment','D','Y','Drop Shipment Contact',TO_TIMESTAMP('2009-09-02 01:31:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:37 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,11580,0,50081,50000,50000,'C_Order_IsDropShip','o.IsDropShip',TO_TIMESTAMP('2009-09-02 01:31:36','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','D','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Drop Shipment',TO_TIMESTAMP('2009-09-02 01:31:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:31:37 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,57127,0,50082,50000,50000,'C_Order_PromotionCode','o.PromotionCode',TO_TIMESTAMP('2009-09-02 01:31:37','YYYY-MM-DD HH24:MI:SS'),0,'User entered promotion code at sales time','D','If present, user entered the promotion code at sales time to get this promotion','Y','Promotion Code',TO_TIMESTAMP('2009-09-02 01:31:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:32:55 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,260,50001,50000,TO_TIMESTAMP('2009-09-02 01:32:54','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN C_OrderLine ol ON (ol.C_Order_ID=o.C_Order_ID)','N',20,'ol',TO_TIMESTAMP('2009-09-02 01:32:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:32:59 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2206,0,50083,50001,50000,'C_OrderLine_AD_Client_ID','ol.AD_Client_ID',TO_TIMESTAMP('2009-09-02 01:32:58','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2009-09-02 01:32:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:32:59 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15861,0,50084,50001,50000,'C_OrderLine_AD_OrgTrx_ID','ol.AD_OrgTrx_ID',TO_TIMESTAMP('2009-09-02 01:32:59','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','D','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Trx Organization',TO_TIMESTAMP('2009-09-02 01:32:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:00 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2207,0,50085,50001,50000,'C_OrderLine_AD_Org_ID','ol.AD_Org_ID',TO_TIMESTAMP('2009-09-02 01:32:59','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2009-09-02 01:32:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:01 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15848,0,50086,50001,50000,'C_OrderLine_C_Activity_ID','ol.C_Activity_ID',TO_TIMESTAMP('2009-09-02 01:33:00','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','D','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Activity',TO_TIMESTAMP('2009-09-02 01:33:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:01 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2764,0,50087,50001,50000,'C_OrderLine_C_BPartner_ID','ol.C_BPartner_ID',TO_TIMESTAMP('2009-09-02 01:33:01','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_TIMESTAMP('2009-09-02 01:33:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:02 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3404,0,50088,50001,50000,'C_OrderLine_C_BPartner_Locatio','ol.C_BPartner_Location_ID',TO_TIMESTAMP('2009-09-02 01:33:01','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','D','The Partner address indicates the location of a Business Partner','Y','Partner Location',TO_TIMESTAMP('2009-09-02 01:33:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:04 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15847,0,50089,50001,50000,'C_OrderLine_C_Campaign_ID','ol.C_Campaign_ID',TO_TIMESTAMP('2009-09-02 01:33:02','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','D','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Campaign',TO_TIMESTAMP('2009-09-02 01:33:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:07 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3050,0,50090,50001,50000,'C_OrderLine_C_Charge_ID','ol.C_Charge_ID',TO_TIMESTAMP('2009-09-02 01:33:04','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','D','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Charge',TO_TIMESTAMP('2009-09-02 01:33:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:07 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2230,0,50091,50001,50000,'C_OrderLine_C_Currency_ID','ol.C_Currency_ID',TO_TIMESTAMP('2009-09-02 01:33:07','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','D','Indicates the Currency to be used when processing or reporting on this record','Y','Currency',TO_TIMESTAMP('2009-09-02 01:33:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:08 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2205,0,50092,50001,50000,'C_OrderLine_C_OrderLine_ID','ol.C_OrderLine_ID',TO_TIMESTAMP('2009-09-02 01:33:07','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','D','The Sales Order Line is a unique identifier for a line in an order.','Y','Sales Order Line',TO_TIMESTAMP('2009-09-02 01:33:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:08 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2213,0,50093,50001,50000,'C_OrderLine_C_Order_ID','ol.C_Order_ID',TO_TIMESTAMP('2009-09-02 01:33:08','YYYY-MM-DD HH24:MI:SS'),0,'Order','D','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Order',TO_TIMESTAMP('2009-09-02 01:33:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:09 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,15457,0,50094,50001,50000,'C_OrderLine_C_ProjectPhase_ID','ol.C_ProjectPhase_ID',TO_TIMESTAMP('2009-09-02 01:33:08','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','D','Y','Project Phase',TO_TIMESTAMP('2009-09-02 01:33:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:09 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15458,0,50095,50001,50000,'C_OrderLine_C_ProjectTask_ID','ol.C_ProjectTask_ID',TO_TIMESTAMP('2009-09-02 01:33:09','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','D','A Project Task in a Project Phase represents the actual work.','Y','Project Task',TO_TIMESTAMP('2009-09-02 01:33:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,14092,0,50096,50001,50000,'C_OrderLine_C_Project_ID','ol.C_Project_ID',TO_TIMESTAMP('2009-09-02 01:33:09','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','D','A Project allows you to track and control internal or external activities.','Y','Project',TO_TIMESTAMP('2009-09-02 01:33:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2235,0,50097,50001,50000,'C_OrderLine_C_Tax_ID','ol.C_Tax_ID',TO_TIMESTAMP('2009-09-02 01:33:10','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier','D','The Tax indicates the type of tax used in document line.','Y','Tax',TO_TIMESTAMP('2009-09-02 01:33:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2222,0,50098,50001,50000,'C_OrderLine_C_UOM_ID','ol.C_UOM_ID',TO_TIMESTAMP('2009-09-02 01:33:10','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','D','The UOM defines a unique non monetary Unit of Measure','Y','UOM',TO_TIMESTAMP('2009-09-02 01:33:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:11 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2209,0,50099,50001,50000,'C_OrderLine_Created','ol.Created',TO_TIMESTAMP('2009-09-02 01:33:10','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2009-09-02 01:33:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:11 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2210,0,50100,50001,50000,'C_OrderLine_CreatedBy','ol.CreatedBy',TO_TIMESTAMP('2009-09-02 01:33:11','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2009-09-02 01:33:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:12 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,2218,0,50101,50001,50000,'C_OrderLine_DateDelivered','ol.DateDelivered',TO_TIMESTAMP('2009-09-02 01:33:11','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','D','Y','Date Delivered',TO_TIMESTAMP('2009-09-02 01:33:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:12 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2219,0,50102,50001,50000,'C_OrderLine_DateInvoiced','ol.DateInvoiced',TO_TIMESTAMP('2009-09-02 01:33:12','YYYY-MM-DD HH24:MI:SS'),0,'Date printed on Invoice','D','The Date Invoice indicates the date printed on the invoice.','Y','Date Invoiced',TO_TIMESTAMP('2009-09-02 01:33:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:13 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2216,0,50103,50001,50000,'C_OrderLine_DateOrdered','ol.DateOrdered',TO_TIMESTAMP('2009-09-02 01:33:12','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','D','Indicates the Date an item was ordered.','Y','Date Ordered',TO_TIMESTAMP('2009-09-02 01:33:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:13 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2217,0,50104,50001,50000,'C_OrderLine_DatePromised','ol.DatePromised',TO_TIMESTAMP('2009-09-02 01:33:13','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','D','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Date Promised',TO_TIMESTAMP('2009-09-02 01:33:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:14 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2220,0,50105,50001,50000,'C_OrderLine_Description','ol.Description',TO_TIMESTAMP('2009-09-02 01:33:13','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Description',TO_TIMESTAMP('2009-09-02 01:33:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4031,0,50106,50001,50000,'C_OrderLine_Discount','ol.Discount',TO_TIMESTAMP('2009-09-02 01:33:14','YYYY-MM-DD HH24:MI:SS'),0,'Discount in percent','D','The Discount indicates the discount applied or taken as a percentage.','Y','Discount %',TO_TIMESTAMP('2009-09-02 01:33:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3049,0,50107,50001,50000,'C_OrderLine_FreightAmt','ol.FreightAmt',TO_TIMESTAMP('2009-09-02 01:33:15','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','D','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Freight Amount',TO_TIMESTAMP('2009-09-02 01:33:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2208,0,50108,50001,50000,'C_OrderLine_IsActive','ol.IsActive',TO_TIMESTAMP('2009-09-02 01:33:15','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2009-09-02 01:33:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9868,0,50109,50001,50000,'C_OrderLine_IsDescription','ol.IsDescription',TO_TIMESTAMP('2009-09-02 01:33:15','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction','D','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Description Only',TO_TIMESTAMP('2009-09-02 01:33:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2214,0,50110,50001,50000,'C_OrderLine_Line','ol.Line',TO_TIMESTAMP('2009-09-02 01:33:16','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','D','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Line No',TO_TIMESTAMP('2009-09-02 01:33:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3723,0,50111,50001,50000,'C_OrderLine_LineNetAmt','ol.LineNetAmt',TO_TIMESTAMP('2009-09-02 01:33:16','YYYY-MM-DD HH24:MI:SS'),0,'Line Extended Amount (Quantity * Actual Price) without Freight and Charges','D','Indicates the extended line amount based on the quantity and the actual price.  Any additional charges or freight are not included.  The Amount may or may not include tax.  If the price list is inclusive tax, the line amount is the same as the line total.','Y','Line Amount',TO_TIMESTAMP('2009-09-02 01:33:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,55323,0,50112,50001,50000,'C_OrderLine_Link_OrderLine_ID','ol.Link_OrderLine_ID',TO_TIMESTAMP('2009-09-02 01:33:17','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order line to the purchase order line that is generated from it.','D','Y','Linked Order Line',TO_TIMESTAMP('2009-09-02 01:33:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8767,0,50113,50001,50000,'C_OrderLine_M_AttributeSetInst','ol.M_AttributeSetInstance_ID',TO_TIMESTAMP('2009-09-02 01:33:17','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','D','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Attribute Set Instance',TO_TIMESTAMP('2009-09-02 01:33:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2221,0,50114,50001,50000,'C_OrderLine_M_Product_ID','ol.M_Product_ID',TO_TIMESTAMP('2009-09-02 01:33:17','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','D','Identifies an item which is either purchased or sold in this organization.','Y','Product',TO_TIMESTAMP('2009-09-02 01:33:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,57128,0,50115,50001,50000,'C_OrderLine_M_Promotion_ID','ol.M_Promotion_ID',TO_TIMESTAMP('2009-09-02 01:33:18','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Promotion',TO_TIMESTAMP('2009-09-02 01:33:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2229,0,50116,50001,50000,'C_OrderLine_M_Shipper_ID','ol.M_Shipper_ID',TO_TIMESTAMP('2009-09-02 01:33:18','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','D','The Shipper indicates the method of delivering product','Y','Shipper',TO_TIMESTAMP('2009-09-02 01:33:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2223,0,50117,50001,50000,'C_OrderLine_M_Warehouse_ID','ol.M_Warehouse_ID',TO_TIMESTAMP('2009-09-02 01:33:19','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','D','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_TIMESTAMP('2009-09-02 01:33:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,56532,0,50118,50001,50000,'C_OrderLine_PP_Cost_Collector_','ol.PP_Cost_Collector_ID',TO_TIMESTAMP('2009-09-02 01:33:19','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Manufacturing Cost Collector',TO_TIMESTAMP('2009-09-02 01:33:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2232,0,50119,50001,50000,'C_OrderLine_PriceActual','ol.PriceActual',TO_TIMESTAMP('2009-09-02 01:33:20','YYYY-MM-DD HH24:MI:SS'),0,'Actual Price ','D','The Actual or Unit Price indicates the Price for a product in source currency.','Y','Unit Price',TO_TIMESTAMP('2009-09-02 01:33:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,14200,0,50120,50001,50000,'C_OrderLine_PriceCost','ol.PriceCost',TO_TIMESTAMP('2009-09-02 01:33:20','YYYY-MM-DD HH24:MI:SS'),0,'Price per Unit of Measure including all indirect costs (Freight, etc.)','D','Optional Purchase Order Line cost price.','Y','Cost Price',TO_TIMESTAMP('2009-09-02 01:33:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12875,0,50121,50001,50000,'C_OrderLine_PriceEntered','ol.PriceEntered',TO_TIMESTAMP('2009-09-02 01:33:21','YYYY-MM-DD HH24:MI:SS'),0,'Price Entered - the price based on the selected/base UoM','D','The price entered is converted to the actual price based on the UoM conversion','Y','Price',TO_TIMESTAMP('2009-09-02 01:33:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4022,0,50122,50001,50000,'C_OrderLine_PriceLimit','ol.PriceLimit',TO_TIMESTAMP('2009-09-02 01:33:22','YYYY-MM-DD HH24:MI:SS'),0,'Lowest price for a product','D','The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','Limit Price',TO_TIMESTAMP('2009-09-02 01:33:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2231,0,50123,50001,50000,'C_OrderLine_PriceList','ol.PriceList',TO_TIMESTAMP('2009-09-02 01:33:23','YYYY-MM-DD HH24:MI:SS'),0,'List Price','D','The List Price is the official List Price in the document currency.','Y','List Price',TO_TIMESTAMP('2009-09-02 01:33:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12069,0,50124,50001,50000,'C_OrderLine_Processed','ol.Processed',TO_TIMESTAMP('2009-09-02 01:33:23','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','D','The Processed checkbox indicates that a document has been processed.','Y','Processed',TO_TIMESTAMP('2009-09-02 01:33:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:25 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2226,0,50125,50001,50000,'C_OrderLine_QtyDelivered','ol.QtyDelivered',TO_TIMESTAMP('2009-09-02 01:33:24','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','D','The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','Delivered Quantity',TO_TIMESTAMP('2009-09-02 01:33:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:26 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12876,0,50126,50001,50000,'C_OrderLine_QtyEntered','ol.QtyEntered',TO_TIMESTAMP('2009-09-02 01:33:25','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','D','The Quantity Entered is converted to base product UoM quantity','Y','Quantity',TO_TIMESTAMP('2009-09-02 01:33:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:27 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2227,0,50127,50001,50000,'C_OrderLine_QtyInvoiced','ol.QtyInvoiced',TO_TIMESTAMP('2009-09-02 01:33:26','YYYY-MM-DD HH24:MI:SS'),0,'Invoiced Quantity','D','The Invoiced Quantity indicates the quantity of a product that have been invoiced.','Y','Quantity Invoiced',TO_TIMESTAMP('2009-09-02 01:33:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:27 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,14206,0,50128,50001,50000,'C_OrderLine_QtyLostSales','ol.QtyLostSales',TO_TIMESTAMP('2009-09-02 01:33:27','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of potential sales','D','When an order is closed and there is a difference between the ordered quantity and the delivered (invoiced) quantity is the Lost Sales Quantity.  Note that the Lost Sales Quantity is 0 if you void an order, so close the order if you want to track lost opportunities.  [Void = data entry error - Close = the order is finished]','Y','Lost Sales Qty',TO_TIMESTAMP('2009-09-02 01:33:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2224,0,50129,50001,50000,'C_OrderLine_QtyOrdered','ol.QtyOrdered',TO_TIMESTAMP('2009-09-02 01:33:27','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','D','The Ordered Quantity indicates the quantity of a product that was ordered.','Y','Ordered Quantity',TO_TIMESTAMP('2009-09-02 01:33:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2225,0,50130,50001,50000,'C_OrderLine_QtyReserved','ol.QtyReserved',TO_TIMESTAMP('2009-09-02 01:33:28','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','D','The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','Reserved Quantity',TO_TIMESTAMP('2009-09-02 01:33:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15460,0,50131,50001,50000,'C_OrderLine_RRAmt','ol.RRAmt',TO_TIMESTAMP('2009-09-02 01:33:28','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Amount','D','The amount for revenue recognition calculation.  If empty, the complete invoice amount is used.  The difference between Revenue Recognition Amount and Invoice Line Net Amount is immediately recognized as revenue.','Y','Revenue Recognition Amt',TO_TIMESTAMP('2009-09-02 01:33:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15459,0,50132,50001,50000,'C_OrderLine_RRStartDate','ol.RRStartDate',TO_TIMESTAMP('2009-09-02 01:33:28','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Start Date','D','The date the revenue reconition starts.','Y','Revenue Recognition Start',TO_TIMESTAMP('2009-09-02 01:33:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,7812,0,50133,50001,50000,'C_OrderLine_Ref_OrderLine_ID','ol.Ref_OrderLine_ID',TO_TIMESTAMP('2009-09-02 01:33:29','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','D','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Referenced Order Line',TO_TIMESTAMP('2009-09-02 01:33:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:30 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,6775,0,50134,50001,50000,'C_OrderLine_S_ResourceAssignme','ol.S_ResourceAssignment_ID',TO_TIMESTAMP('2009-09-02 01:33:29','YYYY-MM-DD HH24:MI:SS'),0,'Resource Assignment','D','Y','Resource Assignment',TO_TIMESTAMP('2009-09-02 01:33:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2211,0,50135,50001,50000,'C_OrderLine_Updated','ol.Updated',TO_TIMESTAMP('2009-09-02 01:33:30','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2009-09-02 01:33:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2212,0,50136,50001,50000,'C_OrderLine_UpdatedBy','ol.UpdatedBy',TO_TIMESTAMP('2009-09-02 01:33:31','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2009-09-02 01:33:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15849,0,50137,50001,50000,'C_OrderLine_User1_ID','ol.User1_ID',TO_TIMESTAMP('2009-09-02 01:33:31','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','D','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 1',TO_TIMESTAMP('2009-09-02 01:33:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:33:32 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,15850,0,50138,50001,50000,'C_OrderLine_User2_ID','ol.User2_ID',TO_TIMESTAMP('2009-09-02 01:33:31','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','D','The user defined element displays the optional elements that have been defined for this account combination.','Y','User List 2',TO_TIMESTAMP('2009-09-02 01:33:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:09 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,291,50002,50000,TO_TIMESTAMP('2009-09-02 01:36:08','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN C_BPartner bp ON (bp.C_BPartner_ID=o.C_BPartner_ID)','N',30,'bp',TO_TIMESTAMP('2009-09-02 01:36:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:13 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2894,0,50139,50002,50000,'C_BPartner_AD_Client_ID','bp.AD_Client_ID',TO_TIMESTAMP('2009-09-02 01:36:12','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2009-09-02 01:36:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:14 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2914,0,50140,50002,50000,'C_BPartner_AD_Language','bp.AD_Language',TO_TIMESTAMP('2009-09-02 01:36:13','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','D','The Language identifies the language to use for display and formatting','Y','Language',TO_TIMESTAMP('2009-09-02 01:36:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:14 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10927,0,50141,50002,50000,'C_BPartner_AD_OrgBP_ID','bp.AD_OrgBP_ID',TO_TIMESTAMP('2009-09-02 01:36:14','YYYY-MM-DD HH24:MI:SS'),0,'The Business Partner is another Organization for explicit Inter-Org transactions','D','The business partner is another organization in the system. So when performing transactions, the counter-document is created automatically. Example: You have BPartnerA linked to OrgA and BPartnerB linked to OrgB.  If you create a sales order for BPartnerB in OrgA a purchase order is created for BPartnerA in OrgB.  This allows to have explicit documents for Inter-Org transactions.','Y','Linked Organization',TO_TIMESTAMP('2009-09-02 01:36:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2895,0,50142,50002,50000,'C_BPartner_AD_Org_ID','bp.AD_Org_ID',TO_TIMESTAMP('2009-09-02 01:36:14','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2009-09-02 01:36:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2922,0,50143,50002,50000,'C_BPartner_AcqusitionCost','bp.AcqusitionCost',TO_TIMESTAMP('2009-09-02 01:36:15','YYYY-MM-DD HH24:MI:SS'),0,'The cost of gaining the prospect as a customer','D','The Acquisition Cost identifies the cost associated with making this prospect a customer.','Y','Acquisition Cost',TO_TIMESTAMP('2009-09-02 01:36:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2925,0,50144,50002,50000,'C_BPartner_ActualLifeTimeValue','bp.ActualLifeTimeValue',TO_TIMESTAMP('2009-09-02 01:36:15','YYYY-MM-DD HH24:MI:SS'),0,'Actual Life Time Revenue','D','The Actual Life Time Value is the recorded revenue in primary accounting currency generated by the Business Partner.','Y','Actual Life Time Value',TO_TIMESTAMP('2009-09-02 01:36:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8768,0,50145,50002,50000,'C_BPartner_BPartner_Parent_ID','bp.BPartner_Parent_ID',TO_TIMESTAMP('2009-09-02 01:36:16','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Parent','D','The parent (organization) of the Business Partner for reporting purposes.','Y','Partner Parent',TO_TIMESTAMP('2009-09-02 01:36:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4940,0,50146,50002,50000,'C_BPartner_C_BP_Group_ID','bp.C_BP_Group_ID',TO_TIMESTAMP('2009-09-02 01:36:16','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','D','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Business Partner Group',TO_TIMESTAMP('2009-09-02 01:36:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2893,0,50147,50002,50000,'C_BPartner_C_BPartner_ID','bp.C_BPartner_ID',TO_TIMESTAMP('2009-09-02 01:36:17','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_TIMESTAMP('2009-09-02 01:36:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3085,0,50148,50002,50000,'C_BPartner_C_Dunning_ID','bp.C_Dunning_ID',TO_TIMESTAMP('2009-09-02 01:36:17','YYYY-MM-DD HH24:MI:SS'),0,'Dunning Rules for overdue invoices','D','The Dunning indicates the rules and method of dunning for past due payments.','Y','Dunning',TO_TIMESTAMP('2009-09-02 01:36:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4291,0,50149,50002,50000,'C_BPartner_C_Greeting_ID','bp.C_Greeting_ID',TO_TIMESTAMP('2009-09-02 01:36:18','YYYY-MM-DD HH24:MI:SS'),0,'Greeting to print on correspondence','D','The Greeting identifies the greeting to print on correspondence.','Y','Greeting',TO_TIMESTAMP('2009-09-02 01:36:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2917,0,50150,50002,50000,'C_BPartner_C_InvoiceSchedule_I','bp.C_InvoiceSchedule_ID',TO_TIMESTAMP('2009-09-02 01:36:18','YYYY-MM-DD HH24:MI:SS'),0,'Schedule for generating Invoices','D','The Invoice Schedule identifies the frequency used when generating invoices.','Y','Invoice Schedule',TO_TIMESTAMP('2009-09-02 01:36:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2924,0,50151,50002,50000,'C_BPartner_C_PaymentTerm_ID','bp.C_PaymentTerm_ID',TO_TIMESTAMP('2009-09-02 01:36:18','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','D','Payment Terms identify the method and timing of payment.','Y','Payment Term',TO_TIMESTAMP('2009-09-02 01:36:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,54463,0,50152,50002,50000,'C_BPartner_C_TaxGroup_ID','bp.C_TaxGroup_ID',TO_TIMESTAMP('2009-09-02 01:36:19','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','Tax Group',TO_TIMESTAMP('2009-09-02 01:36:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2897,0,50153,50002,50000,'C_BPartner_Created','bp.Created',TO_TIMESTAMP('2009-09-02 01:36:19','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2009-09-02 01:36:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2898,0,50154,50002,50000,'C_BPartner_CreatedBy','bp.CreatedBy',TO_TIMESTAMP('2009-09-02 01:36:20','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2009-09-02 01:36:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2906,0,50155,50002,50000,'C_BPartner_DUNS','bp.DUNS',TO_TIMESTAMP('2009-09-02 01:36:20','YYYY-MM-DD HH24:MI:SS'),0,'Dun & Bradstreet Number','D','Used for EDI - For details see   www.dnb.com/dunsno/list.htm','Y','D-U-N-S',TO_TIMESTAMP('2009-09-02 01:36:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4430,0,50156,50002,50000,'C_BPartner_DeliveryRule','bp.DeliveryRule',TO_TIMESTAMP('2009-09-02 01:36:20','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','D','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Delivery Rule',TO_TIMESTAMP('2009-09-02 01:36:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4433,0,50157,50002,50000,'C_BPartner_DeliveryViaRule','bp.DeliveryViaRule',TO_TIMESTAMP('2009-09-02 01:36:21','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','D','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Delivery Via',TO_TIMESTAMP('2009-09-02 01:36:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2903,0,50158,50002,50000,'C_BPartner_Description','bp.Description',TO_TIMESTAMP('2009-09-02 01:36:21','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Description',TO_TIMESTAMP('2009-09-02 01:36:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3086,0,50159,50002,50000,'C_BPartner_DocumentCopies','bp.DocumentCopies',TO_TIMESTAMP('2009-09-02 01:36:22','YYYY-MM-DD HH24:MI:SS'),0,'Number of copies to be printed','D','The Document Copies indicates the number of copies of each document that will be generated.','Y','Document Copies',TO_TIMESTAMP('2009-09-02 01:36:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,53246,0,50160,50002,50000,'C_BPartner_DunningGrace','bp.DunningGrace',TO_TIMESTAMP('2009-09-02 01:36:22','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Dunning Grace',TO_TIMESTAMP('2009-09-02 01:36:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2919,0,50161,50002,50000,'C_BPartner_FirstSale','bp.FirstSale',TO_TIMESTAMP('2009-09-02 01:36:23','YYYY-MM-DD HH24:MI:SS'),0,'Date of First Sale','D','The First Sale Date identifies the date of the first sale to this Business Partner','Y','First Sale',TO_TIMESTAMP('2009-09-02 01:36:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,12406,0,50162,50002,50000,'C_BPartner_FlatDiscount','bp.FlatDiscount',TO_TIMESTAMP('2009-09-02 01:36:23','YYYY-MM-DD HH24:MI:SS'),0,'Flat discount percentage ','D','Y','Flat Discount %',TO_TIMESTAMP('2009-09-02 01:36:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4432,0,50163,50002,50000,'C_BPartner_FreightCostRule','bp.FreightCostRule',TO_TIMESTAMP('2009-09-02 01:36:23','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','D','The Freight Cost Rule indicates the method used when charging for freight.','Y','Freight Cost Rule',TO_TIMESTAMP('2009-09-02 01:36:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4429,0,50164,50002,50000,'C_BPartner_InvoiceRule','bp.InvoiceRule',TO_TIMESTAMP('2009-09-02 01:36:24','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','D','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Invoice Rule',TO_TIMESTAMP('2009-09-02 01:36:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:25 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9332,0,50165,50002,50000,'C_BPartner_Invoice_PrintFormat','bp.Invoice_PrintFormat_ID',TO_TIMESTAMP('2009-09-02 01:36:24','YYYY-MM-DD HH24:MI:SS'),0,'Print Format for printing Invoices','D','You need to define a Print Format to print the document.','Y','Invoice Print Format',TO_TIMESTAMP('2009-09-02 01:36:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:26 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2896,0,50166,50002,50000,'C_BPartner_IsActive','bp.IsActive',TO_TIMESTAMP('2009-09-02 01:36:25','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2009-09-02 01:36:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:26 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2916,0,50167,50002,50000,'C_BPartner_IsCustomer','bp.IsCustomer',TO_TIMESTAMP('2009-09-02 01:36:26','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Customer','D','The Customer checkbox indicates if this Business Partner is a customer.  If it is select additional fields will display which further define this customer.','Y','Customer',TO_TIMESTAMP('2009-09-02 01:36:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:27 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4301,0,50168,50002,50000,'C_BPartner_IsDiscountPrinted','bp.IsDiscountPrinted',TO_TIMESTAMP('2009-09-02 01:36:26','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','D','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Discount Printed',TO_TIMESTAMP('2009-09-02 01:36:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:27 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2927,0,50169,50002,50000,'C_BPartner_IsEmployee','bp.IsEmployee',TO_TIMESTAMP('2009-09-02 01:36:27','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  this Business Partner is an employee','D','The Employee checkbox indicates if this Business Partner is an Employee.  If it is selected, additional fields will display which further identify this employee.','Y','Employee',TO_TIMESTAMP('2009-09-02 01:36:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,3080,0,50170,50002,50000,'C_BPartner_IsOneTime','bp.IsOneTime',TO_TIMESTAMP('2009-09-02 01:36:27','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','One time transaction',TO_TIMESTAMP('2009-09-02 01:36:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2918,0,50171,50002,50000,'C_BPartner_IsProspect','bp.IsProspect',TO_TIMESTAMP('2009-09-02 01:36:28','YYYY-MM-DD HH24:MI:SS'),0,'Indicates this is a Prospect','D','The Prospect checkbox indicates an entity that is an active prospect.','Y','Prospect',TO_TIMESTAMP('2009-09-02 01:36:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2929,0,50172,50002,50000,'C_BPartner_IsSalesRep','bp.IsSalesRep',TO_TIMESTAMP('2009-09-02 01:36:28','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  the business partner is a sales representative or company agent','D','The Sales Rep checkbox indicates if this business partner is a sales representative. A sales representative may also be an emplyee, but does not need to be.','Y','Sales Representative',TO_TIMESTAMP('2009-09-02 01:36:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2911,0,50173,50002,50000,'C_BPartner_IsSummary','bp.IsSummary',TO_TIMESTAMP('2009-09-02 01:36:28','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity','D','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Summary Level',TO_TIMESTAMP('2009-09-02 01:36:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3082,0,50174,50002,50000,'C_BPartner_IsTaxExempt','bp.IsTaxExempt',TO_TIMESTAMP('2009-09-02 01:36:29','YYYY-MM-DD HH24:MI:SS'),0,'Business partner is exempt from tax','D','If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Tax exempt',TO_TIMESTAMP('2009-09-02 01:36:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:30 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2915,0,50175,50002,50000,'C_BPartner_IsVendor','bp.IsVendor',TO_TIMESTAMP('2009-09-02 01:36:29','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Vendor','D','The Vendor checkbox indicates if this Business Partner is a Vendor.  If it is selected, additional fields will display which further identify this vendor.','Y','Vendor',TO_TIMESTAMP('2009-09-02 01:36:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:30 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,6579,0,50176,50002,50000,'C_BPartner_M_DiscountSchema_ID','bp.M_DiscountSchema_ID',TO_TIMESTAMP('2009-09-02 01:36:30','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the trade discount percentage','D','After calculation of the (standard) price, the trade discount percentage is calculated and applied resulting in the final price.','Y','Discount Schema',TO_TIMESTAMP('2009-09-02 01:36:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2930,0,50177,50002,50000,'C_BPartner_M_PriceList_ID','bp.M_PriceList_ID',TO_TIMESTAMP('2009-09-02 01:36:30','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','D','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Price List',TO_TIMESTAMP('2009-09-02 01:36:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2910,0,50178,50002,50000,'C_BPartner_NAICS','bp.NAICS',TO_TIMESTAMP('2009-09-02 01:36:31','YYYY-MM-DD HH24:MI:SS'),0,'Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html','D','The NAICS/SIC identifies either of these codes that may be applicable to this Business Partner.','Y','NAICS/SIC',TO_TIMESTAMP('2009-09-02 01:36:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2902,0,50179,50002,50000,'C_BPartner_Name','bp.Name',TO_TIMESTAMP('2009-09-02 01:36:31','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Name',TO_TIMESTAMP('2009-09-02 01:36:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:32 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,4216,0,50180,50002,50000,'C_BPartner_Name2','bp.Name2',TO_TIMESTAMP('2009-09-02 01:36:31','YYYY-MM-DD HH24:MI:SS'),0,'Additional Name','D','Y','Name 2',TO_TIMESTAMP('2009-09-02 01:36:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:32 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2907,0,50181,50002,50000,'C_BPartner_NumberEmployees','bp.NumberEmployees',TO_TIMESTAMP('2009-09-02 01:36:32','YYYY-MM-DD HH24:MI:SS'),0,'Number of employees','D','Indicates the number of employees for this Business Partner.  This field displays only for Prospects.','Y','Employees',TO_TIMESTAMP('2009-09-02 01:36:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:33 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4215,0,50182,50002,50000,'C_BPartner_POReference','bp.POReference',TO_TIMESTAMP('2009-09-02 01:36:32','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','D','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Order Reference',TO_TIMESTAMP('2009-09-02 01:36:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:33 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,6580,0,50183,50002,50000,'C_BPartner_PO_DiscountSchema_I','bp.PO_DiscountSchema_ID',TO_TIMESTAMP('2009-09-02 01:36:33','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the purchase trade discount percentage','D','Y','PO Discount Schema',TO_TIMESTAMP('2009-09-02 01:36:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,5826,0,50184,50002,50000,'C_BPartner_PO_PaymentTerm_ID','bp.PO_PaymentTerm_ID',TO_TIMESTAMP('2009-09-02 01:36:33','YYYY-MM-DD HH24:MI:SS'),0,'Payment rules for a purchase order','D','The PO Payment Term indicates the payment term that will be used when this purchase order becomes an invoice.','Y','PO Payment Term',TO_TIMESTAMP('2009-09-02 01:36:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2931,0,50185,50002,50000,'C_BPartner_PO_PriceList_ID','bp.PO_PriceList_ID',TO_TIMESTAMP('2009-09-02 01:36:34','YYYY-MM-DD HH24:MI:SS'),0,'Price List used by this Business Partner','D','Identifies the price list used by a Vendor for products purchased by this organization.','Y','Purchase Pricelist',TO_TIMESTAMP('2009-09-02 01:36:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3084,0,50186,50002,50000,'C_BPartner_PaymentRule','bp.PaymentRule',TO_TIMESTAMP('2009-09-02 01:36:34','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','D','The Payment Rule indicates the method of invoice payment.','Y','Payment Rule',TO_TIMESTAMP('2009-09-02 01:36:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:35 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4021,0,50187,50002,50000,'C_BPartner_PaymentRulePO','bp.PaymentRulePO',TO_TIMESTAMP('2009-09-02 01:36:34','YYYY-MM-DD HH24:MI:SS'),0,'Purchase payment option','D','The Payment Rule indicates the method of purchase payment.','Y','Payment Rule',TO_TIMESTAMP('2009-09-02 01:36:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:35 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2923,0,50188,50002,50000,'C_BPartner_PotentialLifeTimeVa','bp.PotentialLifeTimeValue',TO_TIMESTAMP('2009-09-02 01:36:35','YYYY-MM-DD HH24:MI:SS'),0,'Total Revenue expected','D','The Potential Life Time Value is the anticipated revenue in primary accounting currency to be generated by the Business Partner.','Y','Potential Life Time Value',TO_TIMESTAMP('2009-09-02 01:36:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3083,0,50189,50002,50000,'C_BPartner_Rating','bp.Rating',TO_TIMESTAMP('2009-09-02 01:36:35','YYYY-MM-DD HH24:MI:SS'),0,'Classification or Importance','D','The Rating is used to differentiate the importance','Y','Rating',TO_TIMESTAMP('2009-09-02 01:36:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2905,0,50190,50002,50000,'C_BPartner_ReferenceNo','bp.ReferenceNo',TO_TIMESTAMP('2009-09-02 01:36:36','YYYY-MM-DD HH24:MI:SS'),0,'Your customer or vendor number at the Business Partner''s site','D','The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.','Y','Reference No',TO_TIMESTAMP('2009-09-02 01:36:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:37 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9862,0,50191,50002,50000,'C_BPartner_SOCreditStatus','bp.SOCreditStatus',TO_TIMESTAMP('2009-09-02 01:36:36','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Credit Status','D','Credit Management is inactive if Credit Status is No Credit Check, Credit Stop or if the Credit Limit is 0.
If active, the status is set automatically set to Credit Hold, if the Total Open Balance (including Vendor activities)  is higher then the Credit Limit. It is set to Credit Watch, if above 90% of the Credit Limit and Credit OK otherwise.','Y','Credit Status',TO_TIMESTAMP('2009-09-02 01:36:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:38 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2920,0,50192,50002,50000,'C_BPartner_SO_CreditLimit','bp.SO_CreditLimit',TO_TIMESTAMP('2009-09-02 01:36:37','YYYY-MM-DD HH24:MI:SS'),0,'Total outstanding invoice amounts allowed','D','The Credit Limit indicates the total amount allowed ''on account'' in primary accounting currency.  If the Credit Limit is 0, no ckeck is performed.  Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Credit Limit',TO_TIMESTAMP('2009-09-02 01:36:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:38 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2921,0,50193,50002,50000,'C_BPartner_SO_CreditUsed','bp.SO_CreditUsed',TO_TIMESTAMP('2009-09-02 01:36:38','YYYY-MM-DD HH24:MI:SS'),0,'Current open balance','D','The Credit Used indicates the total amount of open or unpaid invoices in primary accounting currency for the Business Partner. Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Credit Used',TO_TIMESTAMP('2009-09-02 01:36:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:41 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4302,0,50194,50002,50000,'C_BPartner_SO_Description','bp.SO_Description',TO_TIMESTAMP('2009-09-02 01:36:38','YYYY-MM-DD HH24:MI:SS'),0,'Description to be used on orders','D','The Order Description identifies the standard description to use on orders for this Customer.','Y','Order Description',TO_TIMESTAMP('2009-09-02 01:36:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:41 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4431,0,50195,50002,50000,'C_BPartner_SalesRep_ID','bp.SalesRep_ID',TO_TIMESTAMP('2009-09-02 01:36:41','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','D','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Sales Representative',TO_TIMESTAMP('2009-09-02 01:36:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:42 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2904,0,50196,50002,50000,'C_BPartner_SalesVolume','bp.SalesVolume',TO_TIMESTAMP('2009-09-02 01:36:41','YYYY-MM-DD HH24:MI:SS'),0,'Total Volume of Sales in Thousands of Currency','D','The Sales Volume indicates the total volume of sales for a Business Partner.','Y','Sales Volume in 1.000',TO_TIMESTAMP('2009-09-02 01:36:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:42 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8167,0,50197,50002,50000,'C_BPartner_SendEMail','bp.SendEMail',TO_TIMESTAMP('2009-09-02 01:36:42','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','D','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Send EMail',TO_TIMESTAMP('2009-09-02 01:36:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:43 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2926,0,50198,50002,50000,'C_BPartner_ShareOfCustomer','bp.ShareOfCustomer',TO_TIMESTAMP('2009-09-02 01:36:42','YYYY-MM-DD HH24:MI:SS'),0,'Share of Customer''s business as a percentage','D','The Share indicates the percentage of this Business Partner''s volume of the products supplied.','Y','Share',TO_TIMESTAMP('2009-09-02 01:36:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:43 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10122,0,50199,50002,50000,'C_BPartner_ShelfLifeMinPct','bp.ShelfLifeMinPct',TO_TIMESTAMP('2009-09-02 01:36:43','YYYY-MM-DD HH24:MI:SS'),0,'Minimum Shelf Life in percent based on Product Instance Guarantee Date','D','Miminum Shelf Life of products with Guarantee Date instance. If > 0 you cannot select products with a shelf life ((Guarantee Date-Today) / Guarantee Days) less than the minum shelf life, unless you select "Show All"','Y','Min Shelf Life %',TO_TIMESTAMP('2009-09-02 01:36:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:44 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2909,0,50200,50002,50000,'C_BPartner_TaxID','bp.TaxID',TO_TIMESTAMP('2009-09-02 01:36:43','YYYY-MM-DD HH24:MI:SS'),0,'Tax Identification','D','The Tax ID field identifies the legal Identification number of this Entity.','Y','Tax ID',TO_TIMESTAMP('2009-09-02 01:36:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:44 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12533,0,50201,50002,50000,'C_BPartner_TotalOpenBalance','bp.TotalOpenBalance',TO_TIMESTAMP('2009-09-02 01:36:44','YYYY-MM-DD HH24:MI:SS'),0,'Total Open Balance Amount in primary Accounting Currency','D','The Total Open Balance Amount is the calculated open item amount for Customer and Vendor activity.  If the Balance is below zero, we owe the Business Partner.  The amout is used for Credit Management.
Invoices and Payment Allocations determine the Open Balance (i.e. not Orders or Payments).','Y','Open Balance',TO_TIMESTAMP('2009-09-02 01:36:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:45 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3081,0,50202,50002,50000,'C_BPartner_URL','bp.URL',TO_TIMESTAMP('2009-09-02 01:36:44','YYYY-MM-DD HH24:MI:SS'),0,'Full URL address - e.g. http://www.adempiere.org','D','The URL defines an fully qualified web address like http://www.adempiere.org','Y','URL',TO_TIMESTAMP('2009-09-02 01:36:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:45 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2899,0,50203,50002,50000,'C_BPartner_Updated','bp.Updated',TO_TIMESTAMP('2009-09-02 01:36:45','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2009-09-02 01:36:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:46 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2900,0,50204,50002,50000,'C_BPartner_UpdatedBy','bp.UpdatedBy',TO_TIMESTAMP('2009-09-02 01:36:45','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2009-09-02 01:36:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:36:47 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2901,0,50205,50002,50000,'C_BPartner_Value','bp.Value',TO_TIMESTAMP('2009-09-02 01:36:46','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Search Key',TO_TIMESTAMP('2009-09-02 01:36:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:11 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,293,50003,50000,TO_TIMESTAMP('2009-09-02 01:39:11','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN C_BPartner_Location bpl ON (bpl.C_BPartner_Location_ID=o.C_BPartner_Location_ID)','N',40,'bpl',TO_TIMESTAMP('2009-09-02 01:39:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2951,0,50206,50003,50000,'C_BPartner_Location_AD_Client_','bpl.AD_Client_ID',TO_TIMESTAMP('2009-09-02 01:39:14','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2009-09-02 01:39:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2952,0,50207,50003,50000,'C_BPartner_Location_AD_Org_ID','bpl.AD_Org_ID',TO_TIMESTAMP('2009-09-02 01:39:15','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2009-09-02 01:39:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2958,0,50208,50003,50000,'C_BPartner_Location_C_BPartner','bpl.C_BPartner_ID',TO_TIMESTAMP('2009-09-02 01:39:15','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Business Partner ',TO_TIMESTAMP('2009-09-02 01:39:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3434,0,50209,50003,50000,'C_BPartner_Location_C_BPartner','bpl.C_BPartner_Location_ID',TO_TIMESTAMP('2009-09-02 01:39:16','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','D','The Partner address indicates the location of a Business Partner','Y','Partner Location',TO_TIMESTAMP('2009-09-02 01:39:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2959,0,50210,50003,50000,'C_BPartner_Location_C_Location','bpl.C_Location_ID',TO_TIMESTAMP('2009-09-02 01:39:17','YYYY-MM-DD HH24:MI:SS'),0,'Location or Address','D','The Location / Address field defines the location of an entity.','Y','Address',TO_TIMESTAMP('2009-09-02 01:39:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2968,0,50211,50003,50000,'C_BPartner_Location_C_SalesReg','bpl.C_SalesRegion_ID',TO_TIMESTAMP('2009-09-02 01:39:17','YYYY-MM-DD HH24:MI:SS'),0,'Sales coverage region','D','The Sales Region indicates a specific area of sales coverage.','Y','Sales Region',TO_TIMESTAMP('2009-09-02 01:39:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2954,0,50212,50003,50000,'C_BPartner_Location_Created','bpl.Created',TO_TIMESTAMP('2009-09-02 01:39:18','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2009-09-02 01:39:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2955,0,50213,50003,50000,'C_BPartner_Location_CreatedBy','bpl.CreatedBy',TO_TIMESTAMP('2009-09-02 01:39:19','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2009-09-02 01:39:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2966,0,50214,50003,50000,'C_BPartner_Location_Fax','bpl.Fax',TO_TIMESTAMP('2009-09-02 01:39:19','YYYY-MM-DD HH24:MI:SS'),0,'Facsimile number','D','The Fax identifies a facsimile number for this Business Partner or  Location','Y','Fax',TO_TIMESTAMP('2009-09-02 01:39:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2967,0,50215,50003,50000,'C_BPartner_Location_ISDN','bpl.ISDN',TO_TIMESTAMP('2009-09-02 01:39:20','YYYY-MM-DD HH24:MI:SS'),0,'ISDN or modem line','D','The ISDN identifies a ISDN or Modem line number.','Y','ISDN',TO_TIMESTAMP('2009-09-02 01:39:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2953,0,50216,50003,50000,'C_BPartner_Location_IsActive','bpl.IsActive',TO_TIMESTAMP('2009-09-02 01:39:21','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2009-09-02 01:39:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3090,0,50217,50003,50000,'C_BPartner_Location_IsBillTo','bpl.IsBillTo',TO_TIMESTAMP('2009-09-02 01:39:21','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Invoice/Bill Address','D','If the Invoice Address is selected, the location is used to send invoices to a customer or receive invoices from a vendor.','Y','Invoice Address',TO_TIMESTAMP('2009-09-02 01:39:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3092,0,50218,50003,50000,'C_BPartner_Location_IsPayFrom','bpl.IsPayFrom',TO_TIMESTAMP('2009-09-02 01:39:22','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner pays from that address and we''ll send dunning letters there','D','If the Pay-From Address is selected, this location is the address the Business Partner pays from and where dunning letters will be sent to.','Y','Pay-From Address',TO_TIMESTAMP('2009-09-02 01:39:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3093,0,50219,50003,50000,'C_BPartner_Location_IsRemitTo','bpl.IsRemitTo',TO_TIMESTAMP('2009-09-02 01:39:22','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner payment address','D','If the Remit-To Address is selected, the location is used to send payments to the vendor.','Y','Remit-To Address',TO_TIMESTAMP('2009-09-02 01:39:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3091,0,50220,50003,50000,'C_BPartner_Location_IsShipTo','bpl.IsShipTo',TO_TIMESTAMP('2009-09-02 01:39:22','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Shipment Address','D','If the Ship Address is selected, the location is used to ship goods to a customer or receive goods from a vendor.','Y','Ship Address',TO_TIMESTAMP('2009-09-02 01:39:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2960,0,50221,50003,50000,'C_BPartner_Location_Name','bpl.Name',TO_TIMESTAMP('2009-09-02 01:39:23','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Name',TO_TIMESTAMP('2009-09-02 01:39:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2964,0,50222,50003,50000,'C_BPartner_Location_Phone','bpl.Phone',TO_TIMESTAMP('2009-09-02 01:39:23','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a telephone number','D','The Phone field identifies a telephone number','Y','Phone',TO_TIMESTAMP('2009-09-02 01:39:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2965,0,50223,50003,50000,'C_BPartner_Location_Phone2','bpl.Phone2',TO_TIMESTAMP('2009-09-02 01:39:24','YYYY-MM-DD HH24:MI:SS'),0,'Identifies an alternate telephone number.','D','The 2nd Phone field identifies an alternate telephone number.','Y','2nd Phone',TO_TIMESTAMP('2009-09-02 01:39:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2956,0,50224,50003,50000,'C_BPartner_Location_Updated','bpl.Updated',TO_TIMESTAMP('2009-09-02 01:39:24','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2009-09-02 01:39:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:39:25 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2957,0,50225,50003,50000,'C_BPartner_Location_UpdatedBy','bpl.UpdatedBy',TO_TIMESTAMP('2009-09-02 01:39:24','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2009-09-02 01:39:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,162,50004,50000,TO_TIMESTAMP('2009-09-02 01:41:18','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN C_Location l ON (l.C_Location_ID=bpl.C_Location_ID)','N',50,'l',TO_TIMESTAMP('2009-09-02 01:41:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,809,0,50226,50004,50000,'C_Location_AD_Client_ID','l.AD_Client_ID',TO_TIMESTAMP('2009-09-02 01:41:20','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2009-09-02 01:41:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,810,0,50227,50004,50000,'C_Location_AD_Org_ID','l.AD_Org_ID',TO_TIMESTAMP('2009-09-02 01:41:21','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2009-09-02 01:41:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,817,0,50228,50004,50000,'C_Location_Address1','l.Address1',TO_TIMESTAMP('2009-09-02 01:41:21','YYYY-MM-DD HH24:MI:SS'),0,'Address line 1 for this location','D','The Address 1 identifies the address for an entity''s location','Y','Address 1',TO_TIMESTAMP('2009-09-02 01:41:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,818,0,50229,50004,50000,'C_Location_Address2','l.Address2',TO_TIMESTAMP('2009-09-02 01:41:22','YYYY-MM-DD HH24:MI:SS'),0,'Address line 2 for this location','D','The Address 2 provides additional address information for an entity.  It can be used for building location, apartment number or similar information.','Y','Address 2',TO_TIMESTAMP('2009-09-02 01:41:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12530,0,50230,50004,50000,'C_Location_Address3','l.Address3',TO_TIMESTAMP('2009-09-02 01:41:24','YYYY-MM-DD HH24:MI:SS'),0,'Address Line 3 for the location','D','The Address 2 provides additional address information for an entity.  It can be used for building location, apartment number or similar information.','Y','Address 3',TO_TIMESTAMP('2009-09-02 01:41:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12531,0,50231,50004,50000,'C_Location_Address4','l.Address4',TO_TIMESTAMP('2009-09-02 01:41:28','YYYY-MM-DD HH24:MI:SS'),0,'Address Line 4 for the location','D','The Address 4 provides additional address information for an entity.  It can be used for building location, apartment number or similar information.','Y','Address 4',TO_TIMESTAMP('2009-09-02 01:41:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:30 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,7048,0,50232,50004,50000,'C_Location_C_City_ID','l.C_City_ID',TO_TIMESTAMP('2009-09-02 01:41:29','YYYY-MM-DD HH24:MI:SS'),0,'City','D','City in a country','Y','City',TO_TIMESTAMP('2009-09-02 01:41:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:33 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,820,0,50233,50004,50000,'C_Location_C_Country_ID','l.C_Country_ID',TO_TIMESTAMP('2009-09-02 01:41:30','YYYY-MM-DD HH24:MI:SS'),0,'Country ','D','The Country defines a Country.  Each Country must be defined before it can be used in any document.','Y','Country',TO_TIMESTAMP('2009-09-02 01:41:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,808,0,50234,50004,50000,'C_Location_C_Location_ID','l.C_Location_ID',TO_TIMESTAMP('2009-09-02 01:41:33','YYYY-MM-DD HH24:MI:SS'),0,'Location or Address','D','The Location / Address field defines the location of an entity.','Y','Address',TO_TIMESTAMP('2009-09-02 01:41:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,821,0,50235,50004,50000,'C_Location_C_Region_ID','l.C_Region_ID',TO_TIMESTAMP('2009-09-02 01:41:34','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a geographical Region','D','The Region identifies a unique Region for this Country.','Y','Region',TO_TIMESTAMP('2009-09-02 01:41:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:35 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,819,0,50236,50004,50000,'C_Location_City','l.City',TO_TIMESTAMP('2009-09-02 01:41:34','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a City','D','The City identifies a unique City for this Country or Region.','Y','City',TO_TIMESTAMP('2009-09-02 01:41:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,812,0,50237,50004,50000,'C_Location_Created','l.Created',TO_TIMESTAMP('2009-09-02 01:41:35','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2009-09-02 01:41:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,813,0,50238,50004,50000,'C_Location_CreatedBy','l.CreatedBy',TO_TIMESTAMP('2009-09-02 01:41:36','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2009-09-02 01:41:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,811,0,50239,50004,50000,'C_Location_IsActive','l.IsActive',TO_TIMESTAMP('2009-09-02 01:41:36','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2009-09-02 01:41:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:37 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,822,0,50240,50004,50000,'C_Location_Postal','l.Postal',TO_TIMESTAMP('2009-09-02 01:41:36','YYYY-MM-DD HH24:MI:SS'),0,'Postal code','D','The Postal Code or ZIP identifies the postal code for this entity''s address.','Y','ZIP',TO_TIMESTAMP('2009-09-02 01:41:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:37 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,823,0,50241,50004,50000,'C_Location_Postal_Add','l.Postal_Add',TO_TIMESTAMP('2009-09-02 01:41:37','YYYY-MM-DD HH24:MI:SS'),0,'Additional ZIP or Postal code','D','The Additional ZIP or Postal Code identifies, if appropriate, any additional Postal Code information.','Y','-',TO_TIMESTAMP('2009-09-02 01:41:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:38 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8214,0,50242,50004,50000,'C_Location_RegionName','l.RegionName',TO_TIMESTAMP('2009-09-02 01:41:37','YYYY-MM-DD HH24:MI:SS'),0,'Name of the Region','D','The Region Name defines the name that will print when this region is used in a document.','Y','Region',TO_TIMESTAMP('2009-09-02 01:41:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:38 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,814,0,50243,50004,50000,'C_Location_Updated','l.Updated',TO_TIMESTAMP('2009-09-02 01:41:38','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2009-09-02 01:41:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:41:39 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,815,0,50244,50004,50000,'C_Location_UpdatedBy','l.UpdatedBy',TO_TIMESTAMP('2009-09-02 01:41:38','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2009-09-02 01:41:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:06 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,208,50005,50000,TO_TIMESTAMP('2009-09-02 01:43:06','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN M_Product p ON (p.M_Product_ID=ol.M_Product_ID)','N',60,'p',TO_TIMESTAMP('2009-09-02 01:43:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1403,0,50245,50005,50000,'M_Product_AD_Client_ID','p.AD_Client_ID',TO_TIMESTAMP('2009-09-02 01:43:09','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2009-09-02 01:43:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1404,0,50246,50005,50000,'M_Product_AD_Org_ID','p.AD_Org_ID',TO_TIMESTAMP('2009-09-02 01:43:10','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2009-09-02 01:43:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:11 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3909,0,50247,50005,50000,'M_Product_C_RevenueRecognition','p.C_RevenueRecognition_ID',TO_TIMESTAMP('2009-09-02 01:43:10','YYYY-MM-DD HH24:MI:SS'),0,'Method for recording revenue','D','The Revenue Recognition indicates how revenue will be recognized for this product','Y','Revenue Recognition',TO_TIMESTAMP('2009-09-02 01:43:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:11 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10919,0,50248,50005,50000,'M_Product_C_SubscriptionType_I','p.C_SubscriptionType_ID',TO_TIMESTAMP('2009-09-02 01:43:11','YYYY-MM-DD HH24:MI:SS'),0,'Type of subscription','D','Subscription type and renewal frequency','Y','Subscription Type',TO_TIMESTAMP('2009-09-02 01:43:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:12 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2019,0,50249,50005,50000,'M_Product_C_TaxCategory_ID','p.C_TaxCategory_ID',TO_TIMESTAMP('2009-09-02 01:43:11','YYYY-MM-DD HH24:MI:SS'),0,'Tax Category','D','The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.','Y','Tax Category',TO_TIMESTAMP('2009-09-02 01:43:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:12 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1760,0,50250,50005,50000,'M_Product_C_UOM_ID','p.C_UOM_ID',TO_TIMESTAMP('2009-09-02 01:43:12','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','D','The UOM defines a unique non monetary Unit of Measure','Y','UOM',TO_TIMESTAMP('2009-09-02 01:43:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:12 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3016,0,50251,50005,50000,'M_Product_Classification','p.Classification',TO_TIMESTAMP('2009-09-02 01:43:12','YYYY-MM-DD HH24:MI:SS'),0,'Classification for grouping','D','The Classification can be used to optionally group products.','Y','Classification',TO_TIMESTAMP('2009-09-02 01:43:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:13 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1406,0,50252,50005,50000,'M_Product_Created','p.Created',TO_TIMESTAMP('2009-09-02 01:43:12','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2009-09-02 01:43:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:14 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1407,0,50253,50005,50000,'M_Product_CreatedBy','p.CreatedBy',TO_TIMESTAMP('2009-09-02 01:43:13','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2009-09-02 01:43:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:14 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1411,0,50254,50005,50000,'M_Product_Description','p.Description',TO_TIMESTAMP('2009-09-02 01:43:14','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Description',TO_TIMESTAMP('2009-09-02 01:43:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,7963,0,50255,50005,50000,'M_Product_DescriptionURL','p.DescriptionURL',TO_TIMESTAMP('2009-09-02 01:43:14','YYYY-MM-DD HH24:MI:SS'),0,'URL for the description','D','Y','Description URL',TO_TIMESTAMP('2009-09-02 01:43:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2703,0,50256,50005,50000,'M_Product_Discontinued','p.Discontinued',TO_TIMESTAMP('2009-09-02 01:43:15','YYYY-MM-DD HH24:MI:SS'),0,'This product is no longer available','D','The Discontinued check box indicates a product that has been discontinued.','Y','Discontinued',TO_TIMESTAMP('2009-09-02 01:43:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:15 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2704,0,50257,50005,50000,'M_Product_DiscontinuedBy','p.DiscontinuedBy',TO_TIMESTAMP('2009-09-02 01:43:15','YYYY-MM-DD HH24:MI:SS'),0,'Discontinued By','D','The Discontinued By indicates the individual who discontinued this product','Y','Discontinued by',TO_TIMESTAMP('2009-09-02 01:43:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3014,0,50258,50005,50000,'M_Product_DocumentNote','p.DocumentNote',TO_TIMESTAMP('2009-09-02 01:43:15','YYYY-MM-DD HH24:MI:SS'),0,'Additional information for a Document','D','The Document Note is used for recording any additional information regarding this product.','Y','Document Note',TO_TIMESTAMP('2009-09-02 01:43:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:16 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52061,0,50259,50005,50000,'M_Product_Group1','p.Group1',TO_TIMESTAMP('2009-09-02 01:43:16','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Group1',TO_TIMESTAMP('2009-09-02 01:43:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52062,0,50260,50005,50000,'M_Product_Group2','p.Group2',TO_TIMESTAMP('2009-09-02 01:43:16','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Group2',TO_TIMESTAMP('2009-09-02 01:43:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:17 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,7974,0,50261,50005,50000,'M_Product_GuaranteeDays','p.GuaranteeDays',TO_TIMESTAMP('2009-09-02 01:43:17','YYYY-MM-DD HH24:MI:SS'),0,'Number of days the product is guaranteed or available','D','If the value is 0, there is no limit to the availability or guarantee, otherwise the guarantee date is calculated by adding the days to the delivery date.','Y','Guarantee Days',TO_TIMESTAMP('2009-09-02 01:43:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9889,0,50262,50005,50000,'M_Product_GuaranteeDaysMin','p.GuaranteeDaysMin',TO_TIMESTAMP('2009-09-02 01:43:17','YYYY-MM-DD HH24:MI:SS'),0,'Minumum number of guarantee days','D','When selecting batch/products with a guarantee date, the minimum left guarantee days for automatic picking.  You can pick any batch/product manually. ','Y','Min Guarantee Days',TO_TIMESTAMP('2009-09-02 01:43:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3015,0,50263,50005,50000,'M_Product_Help','p.Help',TO_TIMESTAMP('2009-09-02 01:43:18','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','D','The Help field contains a hint, comment or help about the use of this item.','Y','Comment/Help',TO_TIMESTAMP('2009-09-02 01:43:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:18 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,7962,0,50264,50005,50000,'M_Product_ImageURL','p.ImageURL',TO_TIMESTAMP('2009-09-02 01:43:18','YYYY-MM-DD HH24:MI:SS'),0,'URL of  image','D','URL of image; The image is not stored in the database, but retrieved at runtime. The image can be a gif, jpeg or png.','Y','Image URL',TO_TIMESTAMP('2009-09-02 01:43:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:19 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1405,0,50265,50005,50000,'M_Product_IsActive','p.IsActive',TO_TIMESTAMP('2009-09-02 01:43:18','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2009-09-02 01:43:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4708,0,50266,50005,50000,'M_Product_IsBOM','p.IsBOM',TO_TIMESTAMP('2009-09-02 01:43:19','YYYY-MM-DD HH24:MI:SS'),0,'Bill of Materials','D','The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','Bill of Materials',TO_TIMESTAMP('2009-09-02 01:43:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:20 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,12147,0,50267,50005,50000,'M_Product_IsDropShip','p.IsDropShip',TO_TIMESTAMP('2009-09-02 01:43:20','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','D','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Drop Shipment',TO_TIMESTAMP('2009-09-02 01:43:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,14505,0,50268,50005,50000,'M_Product_IsExcludeAutoDeliver','p.IsExcludeAutoDelivery',TO_TIMESTAMP('2009-09-02 01:43:20','YYYY-MM-DD HH24:MI:SS'),0,'Exclude from automatic Delivery','D','The product is excluded from generating Shipments.  This allows manual creation of shipments for high demand items. If selected, you need to create the shipment manually.
But, the item is always included, when the delivery rule of the Order is Force (e.g. for POS). 
This allows finer granularity of the Delivery Rule Manual.','Y','Exclude Auto Delivery',TO_TIMESTAMP('2009-09-02 01:43:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4709,0,50269,50005,50000,'M_Product_IsInvoicePrintDetail','p.IsInvoicePrintDetails',TO_TIMESTAMP('2009-09-02 01:43:21','YYYY-MM-DD HH24:MI:SS'),0,'Print detail BOM elements on the invoice','D','The Print Details on Invoice indicates that the BOM element products will print on the Invoice as opposed to this product.','Y','Print detail records on invoice ',TO_TIMESTAMP('2009-09-02 01:43:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:21 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4710,0,50270,50005,50000,'M_Product_IsPickListPrintDetai','p.IsPickListPrintDetails',TO_TIMESTAMP('2009-09-02 01:43:21','YYYY-MM-DD HH24:MI:SS'),0,'Print detail BOM elements on the pick list','D','The Print Details on Pick List indicates that the BOM element products will print on the Pick List as opposed to this product.','Y','Print detail records on pick list',TO_TIMESTAMP('2009-09-02 01:43:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1762,0,50271,50005,50000,'M_Product_IsPurchased','p.IsPurchased',TO_TIMESTAMP('2009-09-02 01:43:21','YYYY-MM-DD HH24:MI:SS'),0,'Organization purchases this product','D','The Purchased check box indicates if this product is purchased by this organization.','Y','Purchased',TO_TIMESTAMP('2009-09-02 01:43:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:22 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10261,0,50272,50005,50000,'M_Product_IsSelfService','p.IsSelfService',TO_TIMESTAMP('2009-09-02 01:43:22','YYYY-MM-DD HH24:MI:SS'),0,'This is a Self-Service entry or this entry can be changed via Self-Service','D','Self-Service allows users to enter data or update their data.  The flag indicates, that this record was entered or created via Self-Service or that the user can change it via the Self-Service functionality.','Y','Self-Service',TO_TIMESTAMP('2009-09-02 01:43:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1763,0,50273,50005,50000,'M_Product_IsSold','p.IsSold',TO_TIMESTAMP('2009-09-02 01:43:22','YYYY-MM-DD HH24:MI:SS'),0,'Organization sells this product','D','The Sold check box indicates if this product is sold by this organization.','Y','Sold',TO_TIMESTAMP('2009-09-02 01:43:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:23 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1761,0,50274,50005,50000,'M_Product_IsStocked','p.IsStocked',TO_TIMESTAMP('2009-09-02 01:43:23','YYYY-MM-DD HH24:MI:SS'),0,'Organization stocks this product','D','The Stocked check box indicates if this product is stocked by this Organization.','Y','Stocked',TO_TIMESTAMP('2009-09-02 01:43:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1413,0,50275,50005,50000,'M_Product_IsSummary','p.IsSummary',TO_TIMESTAMP('2009-09-02 01:43:23','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity','D','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Summary Level',TO_TIMESTAMP('2009-09-02 01:43:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:24 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4711,0,50276,50005,50000,'M_Product_IsVerified','p.IsVerified',TO_TIMESTAMP('2009-09-02 01:43:24','YYYY-MM-DD HH24:MI:SS'),0,'The BOM configuration has been verified','D','The Verified check box indicates if the configuration of this product has been verified.  This is used for products that consist of a bill of materials','Y','Verified',TO_TIMESTAMP('2009-09-02 01:43:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:25 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10260,0,50277,50005,50000,'M_Product_IsWebStoreFeatured','p.IsWebStoreFeatured',TO_TIMESTAMP('2009-09-02 01:43:24','YYYY-MM-DD HH24:MI:SS'),0,'If selected, the product is displayed in the inital or any empy search','D','In the display of products in the Web Store, the product is displayed in the inital view or if no search criteria are entered. To be displayed, the product must be in the price list used.','Y','Featured in Web Store',TO_TIMESTAMP('2009-09-02 01:43:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:25 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,53408,0,50278,50005,50000,'M_Product_LowLevel','p.LowLevel',TO_TIMESTAMP('2009-09-02 01:43:25','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Low Level',TO_TIMESTAMP('2009-09-02 01:43:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:25 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8418,0,50279,50005,50000,'M_Product_M_AttributeSetInstan','p.M_AttributeSetInstance_ID',TO_TIMESTAMP('2009-09-02 01:43:25','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','D','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Attribute Set Instance',TO_TIMESTAMP('2009-09-02 01:43:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:26 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,8417,0,50280,50005,50000,'M_Product_M_AttributeSet_ID','p.M_AttributeSet_ID',TO_TIMESTAMP('2009-09-02 01:43:25','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set','D','Define Product Attribute Sets to add additional attributes and values to the product. You need to define a Attribute Set if you want to enable Serial and Lot Number tracking.','Y','Attribute Set',TO_TIMESTAMP('2009-09-02 01:43:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:26 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9329,0,50281,50005,50000,'M_Product_M_FreightCategory_ID','p.M_FreightCategory_ID',TO_TIMESTAMP('2009-09-02 01:43:26','YYYY-MM-DD HH24:MI:SS'),0,'Category of the Freight','D','Freight Categories are used to calculate the Freight for the Shipper selected','Y','Freight Category',TO_TIMESTAMP('2009-09-02 01:43:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:27 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,9420,0,50282,50005,50000,'M_Product_M_Locator_ID','p.M_Locator_ID',TO_TIMESTAMP('2009-09-02 01:43:26','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator','D','The Locator indicates where in a Warehouse a product is located.','Y','Locator',TO_TIMESTAMP('2009-09-02 01:43:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:27 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2012,0,50283,50005,50000,'M_Product_M_Product_Category_I','p.M_Product_Category_ID',TO_TIMESTAMP('2009-09-02 01:43:27','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','D','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Product Category',TO_TIMESTAMP('2009-09-02 01:43:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1402,0,50284,50005,50000,'M_Product_M_Product_ID','p.M_Product_ID',TO_TIMESTAMP('2009-09-02 01:43:27','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','D','Identifies an item which is either purchased or sold in this organization.','Y','Product',TO_TIMESTAMP('2009-09-02 01:43:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:28 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1410,0,50285,50005,50000,'M_Product_Name','p.Name',TO_TIMESTAMP('2009-09-02 01:43:28','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Name',TO_TIMESTAMP('2009-09-02 01:43:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,4712,0,50286,50005,50000,'M_Product_Processing','p.Processing',TO_TIMESTAMP('2009-09-02 01:43:28','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Process Now',TO_TIMESTAMP('2009-09-02 01:43:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,7795,0,50287,50005,50000,'M_Product_ProductType','p.ProductType',TO_TIMESTAMP('2009-09-02 01:43:29','YYYY-MM-DD HH24:MI:SS'),0,'Type of product','D','The type of product also determines accounting consequences.','Y','Product Type',TO_TIMESTAMP('2009-09-02 01:43:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:29 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,7972,0,50288,50005,50000,'M_Product_R_MailText_ID','p.R_MailText_ID',TO_TIMESTAMP('2009-09-02 01:43:29','YYYY-MM-DD HH24:MI:SS'),0,'Text templates for mailings','D','The Mail Template indicates the mail template for return messages. Mail text can include variables.  The priority of parsing is User/Contact, Business Partner and then the underlying business object (like Request, Dunning, Workflow object).<br>
So, @Name@ would resolve into the User name (if user is defined defined), then Business Partner name (if business partner is defined) and then the Name of the business object if it has a Name.<br>
For Multi-Lingual systems, the template is translated based on the Business Partner''s language selection.','Y','Mail Template',TO_TIMESTAMP('2009-09-02 01:43:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:30 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2305,0,50289,50005,50000,'M_Product_SKU','p.SKU',TO_TIMESTAMP('2009-09-02 01:43:29','YYYY-MM-DD HH24:MI:SS'),0,'Stock Keeping Unit','D','The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','SKU',TO_TIMESTAMP('2009-09-02 01:43:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,6771,0,50290,50005,50000,'M_Product_S_ExpenseType_ID','p.S_ExpenseType_ID',TO_TIMESTAMP('2009-09-02 01:43:30','YYYY-MM-DD HH24:MI:SS'),0,'Expense report type','D','Y','Expense Type',TO_TIMESTAMP('2009-09-02 01:43:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:31 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,6773,0,50291,50005,50000,'M_Product_S_Resource_ID','p.S_Resource_ID',TO_TIMESTAMP('2009-09-02 01:43:31','YYYY-MM-DD HH24:MI:SS'),0,'Resource','D','Y','Resource',TO_TIMESTAMP('2009-09-02 01:43:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:32 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3391,0,50292,50005,50000,'M_Product_SalesRep_ID','p.SalesRep_ID',TO_TIMESTAMP('2009-09-02 01:43:31','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','D','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Sales Representative',TO_TIMESTAMP('2009-09-02 01:43:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:32 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2309,0,50293,50005,50000,'M_Product_ShelfDepth','p.ShelfDepth',TO_TIMESTAMP('2009-09-02 01:43:32','YYYY-MM-DD HH24:MI:SS'),0,'Shelf depth required','D','The Shelf Depth indicates the depth dimension required on a shelf for a product ','Y','Shelf Depth',TO_TIMESTAMP('2009-09-02 01:43:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:33 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2308,0,50294,50005,50000,'M_Product_ShelfHeight','p.ShelfHeight',TO_TIMESTAMP('2009-09-02 01:43:32','YYYY-MM-DD HH24:MI:SS'),0,'Shelf height required','D','The Shelf Height indicates the height dimension required on a shelf for a product','Y','Shelf Height',TO_TIMESTAMP('2009-09-02 01:43:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:33 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2307,0,50295,50005,50000,'M_Product_ShelfWidth','p.ShelfWidth',TO_TIMESTAMP('2009-09-02 01:43:33','YYYY-MM-DD HH24:MI:SS'),0,'Shelf width required','D','The Shelf Width indicates the width dimension required on a shelf for a product','Y','Shelf Width',TO_TIMESTAMP('2009-09-02 01:43:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2304,0,50296,50005,50000,'M_Product_UPC','p.UPC',TO_TIMESTAMP('2009-09-02 01:43:33','YYYY-MM-DD HH24:MI:SS'),0,'Bar Code (Universal Product Code or its superset European Article Number)','D','Use this field to enter the bar code for the product in any of the bar code symbologies (Codabar, Code 25, Code 39, Code 93, Code 128, UPC (A), UPC (E), EAN-13, EAN-8, ITF, ITF-14, ISBN, ISSN, JAN-13, JAN-8, POSTNET and FIM, MSI/Plessey, and Pharmacode) ','Y','UPC/EAN',TO_TIMESTAMP('2009-09-02 01:43:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,52116,0,50297,50005,50000,'M_Product_UnitsPerPack','p.UnitsPerPack',TO_TIMESTAMP('2009-09-02 01:43:34','YYYY-MM-DD HH24:MI:SS'),0,'The Units Per Pack indicates the no of units of a product packed together.','D','Y','UnitsPerPack',TO_TIMESTAMP('2009-09-02 01:43:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:34 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2310,0,50298,50005,50000,'M_Product_UnitsPerPallet','p.UnitsPerPallet',TO_TIMESTAMP('2009-09-02 01:43:34','YYYY-MM-DD HH24:MI:SS'),0,'Units Per Pallet','D','The Units per Pallet indicates the number of units of this product which fit on a pallet.','Y','Units Per Pallet',TO_TIMESTAMP('2009-09-02 01:43:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:35 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1408,0,50299,50005,50000,'M_Product_Updated','p.Updated',TO_TIMESTAMP('2009-09-02 01:43:34','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2009-09-02 01:43:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:35 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1409,0,50300,50005,50000,'M_Product_UpdatedBy','p.UpdatedBy',TO_TIMESTAMP('2009-09-02 01:43:35','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2009-09-02 01:43:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2011,0,50301,50005,50000,'M_Product_Value','p.Value',TO_TIMESTAMP('2009-09-02 01:43:35','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Search Key',TO_TIMESTAMP('2009-09-02 01:43:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:36 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,7973,0,50302,50005,50000,'M_Product_VersionNo','p.VersionNo',TO_TIMESTAMP('2009-09-02 01:43:36','YYYY-MM-DD HH24:MI:SS'),0,'Version Number','D','Y','Version No',TO_TIMESTAMP('2009-09-02 01:43:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:37 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1766,0,50303,50005,50000,'M_Product_Volume','p.Volume',TO_TIMESTAMP('2009-09-02 01:43:36','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','D','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Volume',TO_TIMESTAMP('2009-09-02 01:43:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:43:37 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1767,0,50304,50005,50000,'M_Product_Weight','p.Weight',TO_TIMESTAMP('2009-09-02 01:43:37','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','D','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Weight',TO_TIMESTAMP('2009-09-02 01:43:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:00 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,209,50006,50000,TO_TIMESTAMP('2009-09-02 01:44:59','YYYY-MM-DD HH24:MI:SS'),0,'Y','INNER JOIN M_Product_Category pc ON (pc.M_Product_Category_ID=p.M_Product_Category_ID)','N',70,'pc',TO_TIMESTAMP('2009-09-02 01:44:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:03 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1769,0,50305,50006,50000,'M_Product_Category_AD_Client_I','pc.AD_Client_ID',TO_TIMESTAMP('2009-09-02 01:45:02','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2009-09-02 01:45:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:03 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1770,0,50306,50006,50000,'M_Product_Category_AD_Org_ID','pc.AD_Org_ID',TO_TIMESTAMP('2009-09-02 01:45:03','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2009-09-02 01:45:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:04 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,11889,0,50307,50006,50000,'M_Product_Category_AD_PrintCol','pc.AD_PrintColor_ID',TO_TIMESTAMP('2009-09-02 01:45:03','YYYY-MM-DD HH24:MI:SS'),0,'Color used for printing and display','D','Colors used for printing and display','Y','Print Color',TO_TIMESTAMP('2009-09-02 01:45:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:04 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,7975,0,50308,50006,50000,'M_Product_Category_A_Asset_Gro','pc.A_Asset_Group_ID',TO_TIMESTAMP('2009-09-02 01:45:04','YYYY-MM-DD HH24:MI:SS'),0,'Group of Assets','D','The group of assets determines default accounts.  If an asset group is selected in the product category, assets are created when delivering the asset.','Y','Asset Group',TO_TIMESTAMP('2009-09-02 01:45:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:04 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1772,0,50309,50006,50000,'M_Product_Category_Created','pc.Created',TO_TIMESTAMP('2009-09-02 01:45:04','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2009-09-02 01:45:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:05 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1773,0,50310,50006,50000,'M_Product_Category_CreatedBy','pc.CreatedBy',TO_TIMESTAMP('2009-09-02 01:45:04','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2009-09-02 01:45:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:05 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1777,0,50311,50006,50000,'M_Product_Category_Description','pc.Description',TO_TIMESTAMP('2009-09-02 01:45:05','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Description',TO_TIMESTAMP('2009-09-02 01:45:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:06 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1771,0,50312,50006,50000,'M_Product_Category_IsActive','pc.IsActive',TO_TIMESTAMP('2009-09-02 01:45:05','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2009-09-02 01:45:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:07 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,4725,0,50313,50006,50000,'M_Product_Category_IsDefault','pc.IsDefault',TO_TIMESTAMP('2009-09-02 01:45:06','YYYY-MM-DD HH24:MI:SS'),0,'Default value','D','The Default Checkbox indicates if this record will be used as a default value.','Y','Default',TO_TIMESTAMP('2009-09-02 01:45:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:07 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,10262,0,50314,50006,50000,'M_Product_Category_IsSelfServi','pc.IsSelfService',TO_TIMESTAMP('2009-09-02 01:45:07','YYYY-MM-DD HH24:MI:SS'),0,'This is a Self-Service entry or this entry can be changed via Self-Service','D','Self-Service allows users to enter data or update their data.  The flag indicates, that this record was entered or created via Self-Service or that the user can change it via the Self-Service functionality.','Y','Self-Service',TO_TIMESTAMP('2009-09-02 01:45:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:07 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,13244,0,50315,50006,50000,'M_Product_Category_MMPolicy','pc.MMPolicy',TO_TIMESTAMP('2009-09-02 01:45:07','YYYY-MM-DD HH24:MI:SS'),0,'Material Movement Policy','D','The Material Movement Policy determines how the stock is flowing (FiFo or LiFo) if a specific Product Instance was not selected.  The policy can not contradict the costing method (e.g. FiFo movement policy and LiFo costing method).','Y','Material Policy',TO_TIMESTAMP('2009-09-02 01:45:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:08 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,2020,0,50316,50006,50000,'M_Product_Category_M_Product_C','pc.M_Product_Category_ID',TO_TIMESTAMP('2009-09-02 01:45:07','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','D','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Product Category',TO_TIMESTAMP('2009-09-02 01:45:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:08 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,50211,0,50317,50006,50000,'M_Product_Category_M_Product_C','pc.M_Product_Category_Parent_ID',TO_TIMESTAMP('2009-09-02 01:45:08','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Parent Product Category',TO_TIMESTAMP('2009-09-02 01:45:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:09 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1776,0,50318,50006,50000,'M_Product_Category_Name','pc.Name',TO_TIMESTAMP('2009-09-02 01:45:08','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Name',TO_TIMESTAMP('2009-09-02 01:45:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:09 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,5788,0,50319,50006,50000,'M_Product_Category_PlannedMarg','pc.PlannedMargin',TO_TIMESTAMP('2009-09-02 01:45:09','YYYY-MM-DD HH24:MI:SS'),0,'Project''s planned margin as a percentage','D','The Planned Margin Percentage indicates the anticipated margin percentage for this project or project line','Y','Planned Margin %',TO_TIMESTAMP('2009-09-02 01:45:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1774,0,50320,50006,50000,'M_Product_Category_Updated','pc.Updated',TO_TIMESTAMP('2009-09-02 01:45:09','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2009-09-02 01:45:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,1775,0,50321,50006,50000,'M_Product_Category_UpdatedBy','pc.UpdatedBy',TO_TIMESTAMP('2009-09-02 01:45:10','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2009-09-02 01:45:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:45:10 AM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,3017,0,50322,50006,50000,'M_Product_Category_Value','pc.Value',TO_TIMESTAMP('2009-09-02 01:45:10','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Search Key',TO_TIMESTAMP('2009-09-02 01:45:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse (AD_Browse_ID,AD_Client_ID,AD_Org_ID,AD_View_ID,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,Name,Processing,Updated,UpdatedBy,Value,WhereClause,accesslevel) VALUES (50000,0,0,50000,TO_TIMESTAMP('2009-09-02 01:49:39','YYYY-MM-DD HH24:MI:SS'),0,'This Smatt Browse allow select the Sales order to generate an Outbound Order.','EE07','Y','Y','Salas Order to Picking','N',TO_TIMESTAMP('2009-09-02 01:49:39','YYYY-MM-DD HH24:MI:SS'),0,'SalesOrderToPicking','IsSOTrx=''Y'' AND DocStatus=''CO''','1')
;

-- Sep 2, 2009 1:49:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Trl (AD_Language,AD_Browse_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_ID=50000 AND EXISTS (SELECT * FROM AD_Browse_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_ID!=t.AD_Browse_ID)
;

-- Sep 2, 2009 1:49:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50000,50000,0,608,0,18,50000,TO_TIMESTAMP('2009-09-02 01:49:45','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE07','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_Order_UpdatedBy',10,TO_TIMESTAMP('2009-09-02 01:49:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50000 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50001,50000,0,558,0,13,50001,TO_TIMESTAMP('2009-09-02 01:49:45','YYYY-MM-DD HH24:MI:SS'),0,'Order','EE07','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Y','N','N','N','N','C_Order_C_Order_ID',11,TO_TIMESTAMP('2009-09-02 01:49:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50001 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50002,50000,0,968,0,18,50002,TO_TIMESTAMP('2009-09-02 01:49:46','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE07','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','C_Order_C_Charge_ID',12,TO_TIMESTAMP('2009-09-02 01:49:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50002 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50003,50000,0,559,0,17,50003,TO_TIMESTAMP('2009-09-02 01:49:46','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','EE07','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Y','N','N','N','N','C_Order_InvoiceRule',13,TO_TIMESTAMP('2009-09-02 01:49:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50003 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50004,50000,0,306,0,12,50004,TO_TIMESTAMP('2009-09-02 01:49:48','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE07','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Y','N','N','N','N','C_Order_FreightAmt',14,TO_TIMESTAMP('2009-09-02 01:49:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50004 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50005,50000,0,274,0,17,50005,TO_TIMESTAMP('2009-09-02 01:49:48','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE07','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','N','N','N','N','C_Order_DeliveryViaRule',15,TO_TIMESTAMP('2009-09-02 01:49:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50005 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50006,50000,0,351,0,20,50006,TO_TIMESTAMP('2009-09-02 01:49:49','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','EE07','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Y','N','N','N','N','C_Order_IsApproved',16,TO_TIMESTAMP('2009-09-02 01:49:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50006 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50007,50000,0,363,0,20,50007,TO_TIMESTAMP('2009-09-02 01:49:49','YYYY-MM-DD HH24:MI:SS'),0,'Credit  has been approved','EE07','Credit Approved indicates if the credit approval was successful for Orders','Y','Y','N','N','N','N','C_Order_IsCreditApproved',17,TO_TIMESTAMP('2009-09-02 01:49:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50007 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50008,50000,0,367,0,20,50008,TO_TIMESTAMP('2009-09-02 01:49:50','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_Order_IsDelivered',18,TO_TIMESTAMP('2009-09-02 01:49:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50008 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50009,50000,0,387,0,20,50009,TO_TIMESTAMP('2009-09-02 01:49:50','YYYY-MM-DD HH24:MI:SS'),0,'Is this invoiced?','EE07','If selected, invoices are created','Y','Y','N','N','N','N','C_Order_IsInvoiced',19,TO_TIMESTAMP('2009-09-02 01:49:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50009 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50010,50000,0,459,0,19,50010,TO_TIMESTAMP('2009-09-02 01:49:51','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE07','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','N','N','C_Order_M_Warehouse_ID',20,TO_TIMESTAMP('2009-09-02 01:49:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50010 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50011,50000,0,348,0,20,50011,TO_TIMESTAMP('2009-09-02 01:49:51','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_Order_IsActive',21,TO_TIMESTAMP('2009-09-02 01:49:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50011 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50012,50000,0,263,0,15,50012,TO_TIMESTAMP('2009-09-02 01:49:52','YYYY-MM-DD HH24:MI:SS'),0,'Accounting Date','EE07','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Y','N','N','N','N','C_Order_DateAcct',22,TO_TIMESTAMP('2009-09-02 01:49:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50012 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50013,50000,0,1063,0,18,50013,TO_TIMESTAMP('2009-09-02 01:49:52','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE07','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','C_Order_SalesRep_ID',23,TO_TIMESTAMP('2009-09-02 01:49:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50013 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50014,50000,0,245,0,16,50014,TO_TIMESTAMP('2009-09-02 01:49:53','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE07','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_Order_Created',24,TO_TIMESTAMP('2009-09-02 01:49:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50014 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50015,50000,0,550,0,19,50015,TO_TIMESTAMP('2009-09-02 01:49:53','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE07','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','C_Order_C_Campaign_ID',25,TO_TIMESTAMP('2009-09-02 01:49:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50015 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50016,50000,0,204,0,19,50016,TO_TIMESTAMP('2009-09-02 01:49:54','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','EE07','Payment Terms identify the method and timing of payment.','Y','Y','N','N','N','N','C_Order_C_PaymentTerm_ID',26,TO_TIMESTAMP('2009-09-02 01:49:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50016 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50017,50000,0,197,0,18,50017,TO_TIMESTAMP('2009-09-02 01:49:55','YYYY-MM-DD HH24:MI:SS'),0,'Target document type for conversing documents','EE07','You can convert document types (e.g. from Offer to Order or Invoice).  The conversion is then reflected in the current type.  This processing is initiated by selecting the appropriate Document Action.','Y','Y','N','N','N','N','C_Order_C_DocTypeTarget_ID',27,TO_TIMESTAMP('2009-09-02 01:49:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50017 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50018,50000,0,193,0,19,50018,TO_TIMESTAMP('2009-09-02 01:49:55','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','EE07','Indicates the Currency to be used when processing or reporting on this record','Y','Y','N','N','N','N','C_Order_C_Currency_ID',28,TO_TIMESTAMP('2009-09-02 01:49:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50018 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50019,50000,0,598,0,12,50019,TO_TIMESTAMP('2009-09-02 01:49:56','YYYY-MM-DD HH24:MI:SS'),0,'Total of all document lines','EE07','The Total amount displays the total of all lines in document currency','Y','Y','N','N','N','N','C_Order_TotalLines',29,TO_TIMESTAMP('2009-09-02 01:49:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50019 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50020,50000,0,316,0,12,50020,TO_TIMESTAMP('2009-09-02 01:49:56','YYYY-MM-DD HH24:MI:SS'),0,'Total amount of document','EE07','The Grand Total displays the total amount including Tax and Freight in document currency','Y','Y','N','N','N','N','C_Order_GrandTotal',30,TO_TIMESTAMP('2009-09-02 01:49:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50020 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50021,50000,0,287,0,28,50021,TO_TIMESTAMP('2009-09-02 01:49:57','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE07','You find the current status in the Document Status field. The options are listed in a popup','Y','Y','N','N','N','N','C_Order_DocAction',31,TO_TIMESTAMP('2009-09-02 01:49:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50021 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50022,50000,0,196,0,19,50022,TO_TIMESTAMP('2009-09-02 01:49:57','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE07','The Document Type determines document sequence and processing rules','Y','Y','N','N','N','N','C_Order_C_DocType_ID',32,TO_TIMESTAMP('2009-09-02 01:49:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50022 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50023,50000,0,289,0,17,50023,TO_TIMESTAMP('2009-09-02 01:49:58','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE07','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','N','N','N','N','C_Order_DocStatus',33,TO_TIMESTAMP('2009-09-02 01:49:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50023 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50024,50000,0,113,0,19,50024,TO_TIMESTAMP('2009-09-02 01:49:58','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_Order_AD_Org_ID',34,TO_TIMESTAMP('2009-09-02 01:49:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50024 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50025,50000,0,2039,0,18,50053,TO_TIMESTAMP('2009-09-02 01:49:58','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to be invoiced','EE07','If empty the shipment business partner will be invoiced','Y','Y','N','N','N','N','C_Order_Bill_BPartner_ID',35,TO_TIMESTAMP('2009-09-02 01:49:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50025 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:49:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50026,50000,0,1384,0,30,50054,TO_TIMESTAMP('2009-09-02 01:49:59','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','EE07','The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','C_Order_C_Payment_ID',36,TO_TIMESTAMP('2009-09-02 01:49:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:49:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50026 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50027,50000,0,2420,0,13,50055,TO_TIMESTAMP('2009-09-02 01:49:59','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner responsible for the payment','EE07','Y','Y','N','N','N','N','C_Order_Pay_BPartner_ID',37,TO_TIMESTAMP('2009-09-02 01:49:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50027 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50028,50000,0,290,0,10,50025,TO_TIMESTAMP('2009-09-02 01:50:00','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE07','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','C_Order_DocumentNo',38,TO_TIMESTAMP('2009-09-02 01:50:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50028 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50029,50000,0,399,0,20,50026,TO_TIMESTAMP('2009-09-02 01:50:00','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','EE07','The Printed checkbox indicates if this document or line will included when printing.','Y','Y','N','N','N','N','C_Order_IsPrinted',39,TO_TIMESTAMP('2009-09-02 01:50:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50029 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50030,50000,0,522,0,17,50027,TO_TIMESTAMP('2009-09-02 01:50:01','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE07','The Priority indicates the importance (high, medium, low) of this document','Y','Y','N','N','N','N','C_Order_PriorityRule',40,TO_TIMESTAMP('2009-09-02 01:50:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50030 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50031,50000,0,613,0,18,50028,TO_TIMESTAMP('2009-09-02 01:50:01','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE07','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_Order_User1_ID',41,TO_TIMESTAMP('2009-09-02 01:50:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50031 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50032,50000,0,138,0,19,50029,TO_TIMESTAMP('2009-09-02 01:50:01','YYYY-MM-DD HH24:MI:SS'),0,'User within the system - Internal or Business Partner Contact','EE07','The User identifies a unique user in the system. This could be an internal user or a business partner contact','Y','Y','N','N','N','N','C_Order_AD_User_ID',42,TO_TIMESTAMP('2009-09-02 01:50:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50032 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50033,50000,0,187,0,30,50030,TO_TIMESTAMP('2009-09-02 01:50:02','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE07','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_Order_C_BPartner_ID',43,TO_TIMESTAMP('2009-09-02 01:50:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50033 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50034,50000,0,102,0,19,50031,TO_TIMESTAMP('2009-09-02 01:50:02','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_Order_AD_Client_ID',44,TO_TIMESTAMP('2009-09-02 01:50:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50034 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50035,50000,0,208,0,19,50032,TO_TIMESTAMP('2009-09-02 01:50:03','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE07','A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','C_Order_C_Project_ID',45,TO_TIMESTAMP('2009-09-02 01:50:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50035 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50036,50000,0,952,0,10,50033,TO_TIMESTAMP('2009-09-02 01:50:03','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE07','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','N','N','N','N','C_Order_POReference',46,TO_TIMESTAMP('2009-09-02 01:50:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50036 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50037,50000,0,275,0,14,50034,TO_TIMESTAMP('2009-09-02 01:50:04','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07','A description is limited to 255 characters.','Y','Y','N','N','N','N','C_Order_Description',47,TO_TIMESTAMP('2009-09-02 01:50:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50037 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50038,50000,0,455,0,19,50035,TO_TIMESTAMP('2009-09-02 01:50:04','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE07','The Shipper indicates the method of delivering product','Y','Y','N','N','N','N','C_Order_M_Shipper_ID',48,TO_TIMESTAMP('2009-09-02 01:50:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50038 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50039,50000,0,849,0,12,50036,TO_TIMESTAMP('2009-09-02 01:50:05','YYYY-MM-DD HH24:MI:SS'),0,'Charge Amount','EE07','The Charge Amount indicates the amount for an additional charge.','Y','Y','N','N','N','N','C_Order_ChargeAmt',49,TO_TIMESTAMP('2009-09-02 01:50:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50039 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50040,50000,0,246,0,18,50037,TO_TIMESTAMP('2009-09-02 01:50:06','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE07','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_Order_CreatedBy',50,TO_TIMESTAMP('2009-09-02 01:50:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50040 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50041,50000,0,419,0,20,50038,TO_TIMESTAMP('2009-09-02 01:50:06','YYYY-MM-DD HH24:MI:SS'),0,'Transferred to General Ledger (i.e. accounted)','EE07','The transferred checkbox indicates if the transactions associated with this document should be transferred to the General Ledger.','Y','Y','N','N','N','N','C_Order_IsTransferred',51,TO_TIMESTAMP('2009-09-02 01:50:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50041 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50042,50000,0,268,0,15,50039,TO_TIMESTAMP('2009-09-02 01:50:06','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE07','Indicates the Date an item was ordered.','Y','Y','Y','N','N','N','C_Order_DateOrdered',52,TO_TIMESTAMP('2009-09-02 01:50:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50042 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50043,50000,0,1239,0,20,50040,TO_TIMESTAMP('2009-09-02 01:50:07','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','EE07','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Y','N','N','N','N','C_Order_IsDiscountPrinted',53,TO_TIMESTAMP('2009-09-02 01:50:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50043 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50044,50000,0,524,0,28,50041,TO_TIMESTAMP('2009-09-02 01:50:08','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_Order_Processing',54,TO_TIMESTAMP('2009-09-02 01:50:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50044 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50045,50000,0,607,0,16,50042,TO_TIMESTAMP('2009-09-02 01:50:08','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE07','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_Order_Updated',55,TO_TIMESTAMP('2009-09-02 01:50:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50045 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50046,50000,0,269,0,15,50043,TO_TIMESTAMP('2009-09-02 01:50:09','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE07','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','N','N','N','N','C_Order_DatePromised',56,TO_TIMESTAMP('2009-09-02 01:50:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50046 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50047,50000,0,1321,0,20,50044,TO_TIMESTAMP('2009-09-02 01:50:11','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_Order_IsSelected',57,TO_TIMESTAMP('2009-09-02 01:50:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50047 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50048,50000,0,555,0,17,50045,TO_TIMESTAMP('2009-09-02 01:50:11','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE07','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','C_Order_DeliveryRule',58,TO_TIMESTAMP('2009-09-02 01:50:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50048 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50049,50000,0,189,0,19,50046,TO_TIMESTAMP('2009-09-02 01:50:12','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','EE07','The Partner address indicates the location of a Business Partner','Y','Y','N','N','N','N','C_Order_C_BPartner_Location_ID',59,TO_TIMESTAMP('2009-09-02 01:50:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50049 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50050,50000,0,1308,0,28,50047,TO_TIMESTAMP('2009-09-02 01:50:12','YYYY-MM-DD HH24:MI:SS'),0,'Posting status','EE07','The Posted field indicates the status of the Generation of General Ledger Accounting Lines ','Y','Y','N','N','N','N','C_Order_Posted',60,TO_TIMESTAMP('2009-09-02 01:50:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50050 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50051,50000,0,1091,0,15,50048,TO_TIMESTAMP('2009-09-02 01:50:13','YYYY-MM-DD HH24:MI:SS'),0,'Date the document was printed.','EE07','Indicates the Date that a document was printed.','Y','Y','N','N','N','N','C_Order_DatePrinted',61,TO_TIMESTAMP('2009-09-02 01:50:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50051 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50052,50000,0,1106,0,20,50049,TO_TIMESTAMP('2009-09-02 01:50:16','YYYY-MM-DD HH24:MI:SS'),0,'This is a Sales Transaction','EE07','The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Y','N','N','N','N','C_Order_IsSOTrx',62,TO_TIMESTAMP('2009-09-02 01:50:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50052 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50053,50000,0,1005,0,19,50050,TO_TIMESTAMP('2009-09-02 01:50:16','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE07','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','C_Order_C_Activity_ID',63,TO_TIMESTAMP('2009-09-02 01:50:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50053 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50054,50000,0,1978,0,20,50051,TO_TIMESTAMP('2009-09-02 01:50:17','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE07','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','C_Order_SendEMail',64,TO_TIMESTAMP('2009-09-02 01:50:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50054 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50055,50000,0,1007,0,17,50052,TO_TIMESTAMP('2009-09-02 01:50:17','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE07','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','N','N','N','N','C_Order_FreightCostRule',65,TO_TIMESTAMP('2009-09-02 01:50:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50055 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50056,50000,0,1464,0,30,50056,TO_TIMESTAMP('2009-09-02 01:50:17','YYYY-MM-DD HH24:MI:SS'),0,'Cash Journal Line','EE07','The Cash Journal Line indicates a unique line in a cash journal.','Y','Y','N','N','N','N','C_Order_C_CashLine_ID',66,TO_TIMESTAMP('2009-09-02 01:50:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50056 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50057,50000,0,2041,0,18,50057,TO_TIMESTAMP('2009-09-02 01:50:18','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for invoicing','EE07','Y','Y','N','N','N','N','C_Order_Bill_User_ID',67,TO_TIMESTAMP('2009-09-02 01:50:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50057 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50058,50000,0,2037,0,28,50058,TO_TIMESTAMP('2009-09-02 01:50:18','YYYY-MM-DD HH24:MI:SS'),0,'Copy From Record','EE07','Copy From Record','Y','Y','N','N','N','N','C_Order_CopyFrom',68,TO_TIMESTAMP('2009-09-02 01:50:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50058 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50059,50000,0,2040,0,18,50059,TO_TIMESTAMP('2009-09-02 01:50:19','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for invoicing','EE07','Y','Y','N','N','N','N','C_Order_Bill_Location_ID',69,TO_TIMESTAMP('2009-09-02 01:50:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50059 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50060,50000,0,1047,0,20,50060,TO_TIMESTAMP('2009-09-02 01:50:19','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE07','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','C_Order_Processed',70,TO_TIMESTAMP('2009-09-02 01:50:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50060 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50061,50000,0,1065,0,20,50061,TO_TIMESTAMP('2009-09-02 01:50:20','YYYY-MM-DD HH24:MI:SS'),0,'Tax is included in the price ','EE07','The Tax Included checkbox indicates if the prices include tax.  This is also known as the gross price.','Y','Y','N','N','N','N','C_Order_IsTaxIncluded',71,TO_TIMESTAMP('2009-09-02 01:50:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50061 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50062,50000,0,2063,0,20,50062,TO_TIMESTAMP('2009-09-02 01:50:20','YYYY-MM-DD HH24:MI:SS'),0,'This is a Self-Service entry or this entry can be changed via Self-Service','EE07','Self-Service allows users to enter data or update their data.  The flag indicates, that this record was entered or created via Self-Service or that the user can change it via the Self-Service functionality.','Y','Y','N','N','N','N','C_Order_IsSelfService',72,TO_TIMESTAMP('2009-09-02 01:50:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50062 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50063,50000,0,614,0,18,50063,TO_TIMESTAMP('2009-09-02 01:50:20','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE07','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_Order_User2_ID',73,TO_TIMESTAMP('2009-09-02 01:50:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50063 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50064,50000,0,2421,0,13,50064,TO_TIMESTAMP('2009-09-02 01:50:21','YYYY-MM-DD HH24:MI:SS'),0,'Location of the Business Partner responsible for the payment','EE07','Y','Y','N','N','N','N','C_Order_Pay_Location_ID',74,TO_TIMESTAMP('2009-09-02 01:50:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50064 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50065,50000,0,112,0,18,50065,TO_TIMESTAMP('2009-09-02 01:50:22','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE07','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','C_Order_AD_OrgTrx_ID',75,TO_TIMESTAMP('2009-09-02 01:50:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50065 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50066,50000,0,2278,0,19,50066,TO_TIMESTAMP('2009-09-02 01:50:22','YYYY-MM-DD HH24:MI:SS'),0,'Currency Conversion Rate Type','EE07','The Currency Conversion Rate Type lets you define different type of rates, e.g. Spot, Corporate and/or Sell/Buy rates.','Y','Y','N','N','N','N','C_Order_C_ConversionType_ID',76,TO_TIMESTAMP('2009-09-02 01:50:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50066 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50067,50000,0,2431,0,30,50067,TO_TIMESTAMP('2009-09-02 01:50:23','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','EE07','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Y','N','N','N','N','C_Order_Ref_Order_ID',77,TO_TIMESTAMP('2009-09-02 01:50:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50067 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50068,50000,0,627,0,22,50068,TO_TIMESTAMP('2009-09-02 01:50:23','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','EE07','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Y','N','N','N','N','C_Order_Volume',78,TO_TIMESTAMP('2009-09-02 01:50:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50068 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50069,50000,0,629,0,22,50069,TO_TIMESTAMP('2009-09-02 01:50:23','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','EE07','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Y','N','N','N','N','C_Order_Weight',79,TO_TIMESTAMP('2009-09-02 01:50:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50069 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50070,50000,0,449,0,19,50070,TO_TIMESTAMP('2009-09-02 01:50:24','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','EE07','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Y','N','N','N','N','C_Order_M_PriceList_ID',80,TO_TIMESTAMP('2009-09-02 01:50:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50070 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50071,50000,0,52020,0,10,50071,TO_TIMESTAMP('2009-09-02 01:50:24','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_Order_OrderType',81,TO_TIMESTAMP('2009-09-02 01:50:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50071 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50072,50000,0,52021,0,22,50072,TO_TIMESTAMP('2009-09-02 01:50:25','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_Order_AmountTendered',82,TO_TIMESTAMP('2009-09-02 01:50:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50072 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50073,50000,0,52022,0,22,50073,TO_TIMESTAMP('2009-09-02 01:50:25','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_Order_AmountRefunded',83,TO_TIMESTAMP('2009-09-02 01:50:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50073 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50074,50000,0,2581,0,19,50074,TO_TIMESTAMP('2009-09-02 01:50:26','YYYY-MM-DD HH24:MI:SS'),0,'Point of Sales Terminal','EE07','The POS Terminal defines the defaults and functions available for the POS Form','Y','Y','N','N','N','N','C_Order_C_POS_ID',84,TO_TIMESTAMP('2009-09-02 01:50:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50074 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50075,50000,0,53462,0,18,50075,TO_TIMESTAMP('2009-09-02 01:50:26','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order to the purchase order that is generated from it.','EE07','Y','Y','N','N','N','N','C_Order_Link_Order_ID',85,TO_TIMESTAMP('2009-09-02 01:50:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50075 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50076,50000,0,1143,0,28,50076,TO_TIMESTAMP('2009-09-02 01:50:26','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','EE07','The Payment Rule indicates the method of invoice payment.','Y','Y','N','N','N','N','C_Order_PaymentRule',86,TO_TIMESTAMP('2009-09-02 01:50:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50076 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50077,50000,0,2111,0,19,50077,TO_TIMESTAMP('2009-09-02 01:50:27','YYYY-MM-DD HH24:MI:SS'),0,'Category of the Freight','EE07','Freight Categories are used to calculate the Freight for the Shipper selected','Y','Y','N','N','N','N','C_Order_M_FreightCategory_ID',87,TO_TIMESTAMP('2009-09-02 01:50:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50077 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50078,50000,0,53458,0,30,50078,TO_TIMESTAMP('2009-09-02 01:50:27','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner to ship to','EE07','If empty the business partner will be shipped to.','Y','Y','N','N','N','N','C_Order_DropShip_BPartner_ID',88,TO_TIMESTAMP('2009-09-02 01:50:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50078 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50079,50000,0,53459,0,18,50079,TO_TIMESTAMP('2009-09-02 01:50:28','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Location for shipping to','EE07','Y','Y','N','N','N','N','C_Order_DropShip_Location_ID',89,TO_TIMESTAMP('2009-09-02 01:50:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50079 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50080,50000,0,53460,0,18,50080,TO_TIMESTAMP('2009-09-02 01:50:28','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Contact for drop shipment','EE07','Y','Y','N','N','N','N','C_Order_DropShip_User_ID',90,TO_TIMESTAMP('2009-09-02 01:50:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50080 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50081,50000,0,2466,0,20,50081,TO_TIMESTAMP('2009-09-02 01:50:29','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','EE07','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','N','N','N','N','C_Order_IsDropShip',91,TO_TIMESTAMP('2009-09-02 01:50:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50081 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50082,50000,0,53809,0,10,50082,TO_TIMESTAMP('2009-09-02 01:50:30','YYYY-MM-DD HH24:MI:SS'),0,'User entered promotion code at sales time','EE07','If present, user entered the promotion code at sales time to get this promotion','Y','Y','N','N','N','N','C_Order_PromotionCode',92,TO_TIMESTAMP('2009-09-02 01:50:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50082 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50083,50000,0,102,0,19,50083,TO_TIMESTAMP('2009-09-02 01:50:30','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_OrderLine_AD_Client_ID',93,TO_TIMESTAMP('2009-09-02 01:50:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50083 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50084,50000,0,112,0,18,50084,TO_TIMESTAMP('2009-09-02 01:50:30','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE07','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','C_OrderLine_AD_OrgTrx_ID',94,TO_TIMESTAMP('2009-09-02 01:50:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50084 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:32 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50085,50000,0,113,0,19,50085,TO_TIMESTAMP('2009-09-02 01:50:31','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_OrderLine_AD_Org_ID',95,TO_TIMESTAMP('2009-09-02 01:50:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:32 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50085 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:32 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50086,50000,0,1005,0,19,50086,TO_TIMESTAMP('2009-09-02 01:50:32','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE07','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','C_OrderLine_C_Activity_ID',96,TO_TIMESTAMP('2009-09-02 01:50:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:32 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50086 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50087,50000,0,187,0,30,50087,TO_TIMESTAMP('2009-09-02 01:50:32','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE07','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_OrderLine_C_BPartner_ID',97,TO_TIMESTAMP('2009-09-02 01:50:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50087 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50088,50000,0,189,0,19,50088,TO_TIMESTAMP('2009-09-02 01:50:33','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','EE07','The Partner address indicates the location of a Business Partner','Y','Y','N','N','N','N','C_OrderLine_C_BPartner_Locatio',98,TO_TIMESTAMP('2009-09-02 01:50:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50088 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50089,50000,0,550,0,19,50089,TO_TIMESTAMP('2009-09-02 01:50:33','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE07','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','C_OrderLine_C_Campaign_ID',99,TO_TIMESTAMP('2009-09-02 01:50:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50089 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50090,50000,0,968,0,19,50090,TO_TIMESTAMP('2009-09-02 01:50:34','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','EE07','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','C_OrderLine_C_Charge_ID',100,TO_TIMESTAMP('2009-09-02 01:50:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50090 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50091,50000,0,193,0,19,50091,TO_TIMESTAMP('2009-09-02 01:50:34','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record','EE07','Indicates the Currency to be used when processing or reporting on this record','Y','Y','N','N','N','N','C_OrderLine_C_Currency_ID',101,TO_TIMESTAMP('2009-09-02 01:50:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50091 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50092,50000,0,561,0,13,50092,TO_TIMESTAMP('2009-09-02 01:50:35','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE07','The Sales Order Line is a unique identifier for a line in an order.','Y','Y','N','N','N','N','C_OrderLine_C_OrderLine_ID',102,TO_TIMESTAMP('2009-09-02 01:50:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50092 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50093,50000,0,558,0,30,50093,TO_TIMESTAMP('2009-09-02 01:50:35','YYYY-MM-DD HH24:MI:SS'),0,'Order','EE07','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Y','Y','N','N','N','C_OrderLine_C_Order_ID',103,TO_TIMESTAMP('2009-09-02 01:50:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50093 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50094,50000,0,2073,0,19,50094,TO_TIMESTAMP('2009-09-02 01:50:36','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','EE07','Y','Y','N','N','N','N','C_OrderLine_C_ProjectPhase_ID',104,TO_TIMESTAMP('2009-09-02 01:50:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50094 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:39 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50095,50000,0,2074,0,19,50095,TO_TIMESTAMP('2009-09-02 01:50:38','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','EE07','A Project Task in a Project Phase represents the actual work.','Y','Y','N','N','N','N','C_OrderLine_C_ProjectTask_ID',105,TO_TIMESTAMP('2009-09-02 01:50:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:39 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50095 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50096,50000,0,208,0,19,50096,TO_TIMESTAMP('2009-09-02 01:50:39','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE07','A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','C_OrderLine_C_Project_ID',106,TO_TIMESTAMP('2009-09-02 01:50:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50096 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50097,50000,0,213,0,19,50097,TO_TIMESTAMP('2009-09-02 01:50:40','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier','EE07','The Tax indicates the type of tax used in document line.','Y','Y','N','N','N','N','C_OrderLine_C_Tax_ID',107,TO_TIMESTAMP('2009-09-02 01:50:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50097 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50098,50000,0,215,0,19,50098,TO_TIMESTAMP('2009-09-02 01:50:40','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE07','The UOM defines a unique non monetary Unit of Measure','Y','Y','N','N','N','N','C_OrderLine_C_UOM_ID',108,TO_TIMESTAMP('2009-09-02 01:50:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50098 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:41 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50099,50000,0,245,0,16,50099,TO_TIMESTAMP('2009-09-02 01:50:40','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE07','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_OrderLine_Created',109,TO_TIMESTAMP('2009-09-02 01:50:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:41 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50099 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50100,50000,0,246,0,18,50100,TO_TIMESTAMP('2009-09-02 01:50:41','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE07','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_OrderLine_CreatedBy',110,TO_TIMESTAMP('2009-09-02 01:50:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50100 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50101,50000,0,264,0,15,50101,TO_TIMESTAMP('2009-09-02 01:50:42','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','EE07','Y','Y','N','N','N','N','C_OrderLine_DateDelivered',111,TO_TIMESTAMP('2009-09-02 01:50:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50101 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50102,50000,0,267,0,15,50102,TO_TIMESTAMP('2009-09-02 01:50:42','YYYY-MM-DD HH24:MI:SS'),0,'Date printed on Invoice','EE07','The Date Invoice indicates the date printed on the invoice.','Y','Y','N','N','N','N','C_OrderLine_DateInvoiced',112,TO_TIMESTAMP('2009-09-02 01:50:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50102 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50103,50000,0,268,0,15,50103,TO_TIMESTAMP('2009-09-02 01:50:42','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE07','Indicates the Date an item was ordered.','Y','Y','N','N','N','N','C_OrderLine_DateOrdered',113,TO_TIMESTAMP('2009-09-02 01:50:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50103 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50104,50000,0,269,0,15,50104,TO_TIMESTAMP('2009-09-02 01:50:43','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE07','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','N','N','N','N','C_OrderLine_DatePromised',114,TO_TIMESTAMP('2009-09-02 01:50:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50104 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50105,50000,0,275,0,14,50105,TO_TIMESTAMP('2009-09-02 01:50:43','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07','A description is limited to 255 characters.','Y','Y','N','N','N','N','C_OrderLine_Description',115,TO_TIMESTAMP('2009-09-02 01:50:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50105 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50106,50000,0,280,0,22,50106,TO_TIMESTAMP('2009-09-02 01:50:44','YYYY-MM-DD HH24:MI:SS'),0,'Discount in percent','EE07','The Discount indicates the discount applied or taken as a percentage.','Y','Y','N','N','N','N','C_OrderLine_Discount',116,TO_TIMESTAMP('2009-09-02 01:50:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50106 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50107,50000,0,306,0,12,50107,TO_TIMESTAMP('2009-09-02 01:50:44','YYYY-MM-DD HH24:MI:SS'),0,'Freight Amount ','EE07','The Freight Amount indicates the amount charged for Freight in the document currency.','Y','Y','N','N','N','N','C_OrderLine_FreightAmt',117,TO_TIMESTAMP('2009-09-02 01:50:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50107 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50108,50000,0,348,0,20,50108,TO_TIMESTAMP('2009-09-02 01:50:45','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_OrderLine_IsActive',118,TO_TIMESTAMP('2009-09-02 01:50:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50108 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50109,50000,0,2183,0,20,50109,TO_TIMESTAMP('2009-09-02 01:50:45','YYYY-MM-DD HH24:MI:SS'),0,'if true, the line is just description and no transaction','EE07','If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptional detail lines, e.g. for an Work Order.','Y','Y','N','N','N','N','C_OrderLine_IsDescription',119,TO_TIMESTAMP('2009-09-02 01:50:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50109 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50110,50000,0,439,0,11,50110,TO_TIMESTAMP('2009-09-02 01:50:46','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE07','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','N','N','N','C_OrderLine_Line',120,TO_TIMESTAMP('2009-09-02 01:50:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50110 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50111,50000,0,441,0,12,50111,TO_TIMESTAMP('2009-09-02 01:50:46','YYYY-MM-DD HH24:MI:SS'),0,'Line Extended Amount (Quantity * Actual Price) without Freight and Charges','EE07','Indicates the extended line amount based on the quantity and the actual price.  Any additional charges or freight are not included.  The Amount may or may not include tax.  If the price list is inclusive tax, the line amount is the same as the line total.','Y','Y','Y','N','N','N','C_OrderLine_LineNetAmt',121,TO_TIMESTAMP('2009-09-02 01:50:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50111 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50112,50000,0,53463,0,18,50112,TO_TIMESTAMP('2009-09-02 01:50:46','YYYY-MM-DD HH24:MI:SS'),0,'This field links a sales order line to the purchase order line that is generated from it.','EE07','Y','Y','N','N','N','N','C_OrderLine_Link_OrderLine_ID',122,TO_TIMESTAMP('2009-09-02 01:50:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50112 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50113,50000,0,1244,0,10,50194,TO_TIMESTAMP('2009-09-02 01:50:47','YYYY-MM-DD HH24:MI:SS'),0,'Description to be used on orders','EE07','The Order Description identifies the standard description to use on orders for this Customer.','Y','Y','N','N','N','N','C_BPartner_SO_Description',123,TO_TIMESTAMP('2009-09-02 01:50:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50113 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50114,50000,0,2019,0,35,50113,TO_TIMESTAMP('2009-09-02 01:50:49','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE07','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','N','N','N','N','C_OrderLine_M_AttributeSetInst',124,TO_TIMESTAMP('2009-09-02 01:50:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50114 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50115,50000,0,454,0,30,50114,TO_TIMESTAMP('2009-09-02 01:50:50','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE07','Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','N','N','C_OrderLine_M_Product_ID',125,TO_TIMESTAMP('2009-09-02 01:50:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50115 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50116,50000,0,53802,0,19,50115,TO_TIMESTAMP('2009-09-02 01:50:50','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_OrderLine_M_Promotion_ID',126,TO_TIMESTAMP('2009-09-02 01:50:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50116 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50117,50000,0,455,0,19,50116,TO_TIMESTAMP('2009-09-02 01:50:51','YYYY-MM-DD HH24:MI:SS'),0,'Method or manner of product delivery','EE07','The Shipper indicates the method of delivering product','Y','Y','N','N','N','N','C_OrderLine_M_Shipper_ID',127,TO_TIMESTAMP('2009-09-02 01:50:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50117 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50118,50000,0,459,0,18,50117,TO_TIMESTAMP('2009-09-02 01:50:51','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE07','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','N','N','C_OrderLine_M_Warehouse_ID',128,TO_TIMESTAMP('2009-09-02 01:50:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50118 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50119,50000,0,53310,0,19,50118,TO_TIMESTAMP('2009-09-02 01:50:52','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_OrderLine_PP_Cost_Collector_',129,TO_TIMESTAMP('2009-09-02 01:50:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50119 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50120,50000,0,519,0,37,50119,TO_TIMESTAMP('2009-09-02 01:50:52','YYYY-MM-DD HH24:MI:SS'),0,'Actual Price ','EE07','The Actual or Unit Price indicates the Price for a product in source currency.','Y','Y','N','N','N','N','C_OrderLine_PriceActual',130,TO_TIMESTAMP('2009-09-02 01:50:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50120 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50121,50000,0,2821,0,37,50120,TO_TIMESTAMP('2009-09-02 01:50:52','YYYY-MM-DD HH24:MI:SS'),0,'Price per Unit of Measure including all indirect costs (Freight, etc.)','EE07','Optional Purchase Order Line cost price.','Y','Y','N','N','N','N','C_OrderLine_PriceCost',131,TO_TIMESTAMP('2009-09-02 01:50:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50121 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50122,50000,0,2588,0,37,50121,TO_TIMESTAMP('2009-09-02 01:50:53','YYYY-MM-DD HH24:MI:SS'),0,'Price Entered - the price based on the selected/base UoM','EE07','The price entered is converted to the actual price based on the UoM conversion','Y','Y','N','N','N','N','C_OrderLine_PriceEntered',132,TO_TIMESTAMP('2009-09-02 01:50:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50122 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50123,50000,0,955,0,37,50122,TO_TIMESTAMP('2009-09-02 01:50:53','YYYY-MM-DD HH24:MI:SS'),0,'Lowest price for a product','EE07','The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','Y','N','N','N','N','C_OrderLine_PriceLimit',133,TO_TIMESTAMP('2009-09-02 01:50:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50123 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50124,50000,0,520,0,37,50123,TO_TIMESTAMP('2009-09-02 01:50:54','YYYY-MM-DD HH24:MI:SS'),0,'List Price','EE07','The List Price is the official List Price in the document currency.','Y','Y','N','N','N','N','C_OrderLine_PriceList',134,TO_TIMESTAMP('2009-09-02 01:50:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50124 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50125,50000,0,1047,0,20,50124,TO_TIMESTAMP('2009-09-02 01:50:54','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed','EE07','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','C_OrderLine_Processed',135,TO_TIMESTAMP('2009-09-02 01:50:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50125 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50126,50000,0,528,0,29,50125,TO_TIMESTAMP('2009-09-02 01:50:55','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE07','The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','Y','N','N','N','N','C_OrderLine_QtyDelivered',136,TO_TIMESTAMP('2009-09-02 01:50:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50126 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50127,50000,0,2589,0,29,50126,TO_TIMESTAMP('2009-09-02 01:50:55','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','EE07','The Quantity Entered is converted to base product UoM quantity','Y','Y','N','N','N','N','C_OrderLine_QtyEntered',137,TO_TIMESTAMP('2009-09-02 01:50:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50127 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50128,50000,0,529,0,29,50127,TO_TIMESTAMP('2009-09-02 01:50:56','YYYY-MM-DD HH24:MI:SS'),0,'Invoiced Quantity','EE07','The Invoiced Quantity indicates the quantity of a product that have been invoiced.','Y','Y','N','N','N','N','C_OrderLine_QtyInvoiced',138,TO_TIMESTAMP('2009-09-02 01:50:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50128 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50129,50000,0,2826,0,29,50128,TO_TIMESTAMP('2009-09-02 01:50:56','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of potential sales','EE07','When an order is closed and there is a difference between the ordered quantity and the delivered (invoiced) quantity is the Lost Sales Quantity.  Note that the Lost Sales Quantity is 0 if you void an order, so close the order if you want to track lost opportunities.  [Void = data entry error - Close = the order is finished]','Y','Y','N','N','N','N','C_OrderLine_QtyLostSales',139,TO_TIMESTAMP('2009-09-02 01:50:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50129 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50130,50000,0,531,0,29,50129,TO_TIMESTAMP('2009-09-02 01:50:57','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','EE07','The Ordered Quantity indicates the quantity of a product that was ordered.','Y','Y','N','N','N','N','C_OrderLine_QtyOrdered',140,TO_TIMESTAMP('2009-09-02 01:50:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50130 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50131,50000,0,532,0,29,50130,TO_TIMESTAMP('2009-09-02 01:50:57','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','EE07','The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','Y','N','N','N','N','C_OrderLine_QtyReserved',141,TO_TIMESTAMP('2009-09-02 01:50:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50131 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50132,50000,0,3033,0,12,50131,TO_TIMESTAMP('2009-09-02 01:50:58','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Amount','EE07','The amount for revenue recognition calculation.  If empty, the complete invoice amount is used.  The difference between Revenue Recognition Amount and Invoice Line Net Amount is immediately recognized as revenue.','Y','Y','N','N','N','N','C_OrderLine_RRAmt',142,TO_TIMESTAMP('2009-09-02 01:50:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50132 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50133,50000,0,3032,0,16,50132,TO_TIMESTAMP('2009-09-02 01:50:58','YYYY-MM-DD HH24:MI:SS'),0,'Revenue Recognition Start Date','EE07','The date the revenue reconition starts.','Y','Y','N','N','N','N','C_OrderLine_RRStartDate',143,TO_TIMESTAMP('2009-09-02 01:50:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50133 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50134,50000,0,1905,0,30,50133,TO_TIMESTAMP('2009-09-02 01:50:59','YYYY-MM-DD HH24:MI:SS'),0,'Reference to corresponding Sales/Purchase Order','EE07','Reference of the Sales Order Line to the corresponding Purchase Order Line or vice versa.','Y','Y','N','N','N','N','C_OrderLine_Ref_OrderLine_ID',144,TO_TIMESTAMP('2009-09-02 01:50:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50134 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:50:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50135,50000,0,1778,0,33,50134,TO_TIMESTAMP('2009-09-02 01:50:59','YYYY-MM-DD HH24:MI:SS'),0,'Resource Assignment','EE07','Y','Y','N','N','N','N','C_OrderLine_S_ResourceAssignme',145,TO_TIMESTAMP('2009-09-02 01:50:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:50:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50135 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50136,50000,0,607,0,16,50135,TO_TIMESTAMP('2009-09-02 01:50:59','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE07','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_OrderLine_Updated',146,TO_TIMESTAMP('2009-09-02 01:50:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50136 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50137,50000,0,608,0,18,50136,TO_TIMESTAMP('2009-09-02 01:51:00','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE07','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_OrderLine_UpdatedBy',147,TO_TIMESTAMP('2009-09-02 01:51:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50137 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50138,50000,0,613,0,18,50137,TO_TIMESTAMP('2009-09-02 01:51:00','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE07','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_OrderLine_User1_ID',148,TO_TIMESTAMP('2009-09-02 01:51:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50138 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50139,50000,0,614,0,18,50138,TO_TIMESTAMP('2009-09-02 01:51:01','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE07','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','C_OrderLine_User2_ID',149,TO_TIMESTAMP('2009-09-02 01:51:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50139 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50140,50000,0,102,0,19,50139,TO_TIMESTAMP('2009-09-02 01:51:02','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_BPartner_AD_Client_ID',150,TO_TIMESTAMP('2009-09-02 01:51:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50140 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50141,50000,0,109,0,18,50140,TO_TIMESTAMP('2009-09-02 01:51:02','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE07','The Language identifies the language to use for display and formatting','Y','Y','N','N','N','N','C_BPartner_AD_Language',151,TO_TIMESTAMP('2009-09-02 01:51:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50141 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50142,50000,0,364,0,20,50167,TO_TIMESTAMP('2009-09-02 01:51:02','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Customer','EE07','The Customer checkbox indicates if this Business Partner is a customer.  If it is select additional fields will display which further define this customer.','Y','Y','N','N','N','N','C_BPartner_IsCustomer',152,TO_TIMESTAMP('2009-09-02 01:51:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:03 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50142 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50143,50000,0,1949,0,10,50302,TO_TIMESTAMP('2009-09-02 01:51:03','YYYY-MM-DD HH24:MI:SS'),0,'Version Number','EE07','Y','Y','N','N','N','N','M_Product_VersionNo',153,TO_TIMESTAMP('2009-09-02 01:51:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50143 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50144,50000,0,2354,0,28,50141,TO_TIMESTAMP('2009-09-02 01:51:04','YYYY-MM-DD HH24:MI:SS'),0,'The Business Partner is another Organization for explicit Inter-Org transactions','EE07','The business partner is another organization in the system. So when performing transactions, the counter-document is created automatically. Example: You have BPartnerA linked to OrgA and BPartnerB linked to OrgB.  If you create a sales order for BPartnerB in OrgA a purchase order is created for BPartnerA in OrgB.  This allows to have explicit documents for Inter-Org transactions.','Y','Y','N','N','N','N','C_BPartner_AD_OrgBP_ID',154,TO_TIMESTAMP('2009-09-02 01:51:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50144 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50145,50000,0,113,0,19,50142,TO_TIMESTAMP('2009-09-02 01:51:04','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_BPartner_AD_Org_ID',155,TO_TIMESTAMP('2009-09-02 01:51:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50145 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50146,50000,0,151,0,37,50143,TO_TIMESTAMP('2009-09-02 01:51:05','YYYY-MM-DD HH24:MI:SS'),0,'The cost of gaining the prospect as a customer','EE07','The Acquisition Cost identifies the cost associated with making this prospect a customer.','Y','Y','N','N','N','N','C_BPartner_AcqusitionCost',156,TO_TIMESTAMP('2009-09-02 01:51:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50146 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50147,50000,0,153,0,12,50144,TO_TIMESTAMP('2009-09-02 01:51:06','YYYY-MM-DD HH24:MI:SS'),0,'Actual Life Time Revenue','EE07','The Actual Life Time Value is the recorded revenue in primary accounting currency generated by the Business Partner.','Y','Y','N','N','N','N','C_BPartner_ActualLifeTimeValue',157,TO_TIMESTAMP('2009-09-02 01:51:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50147 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50148,50000,0,2031,0,13,50145,TO_TIMESTAMP('2009-09-02 01:51:06','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Parent','EE07','The parent (organization) of the Business Partner for reporting purposes.','Y','Y','N','N','N','N','C_BPartner_BPartner_Parent_ID',158,TO_TIMESTAMP('2009-09-02 01:51:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50148 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50149,50000,0,1383,0,19,50146,TO_TIMESTAMP('2009-09-02 01:51:07','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','EE07','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','N','N','N','N','C_BPartner_C_BP_Group_ID',159,TO_TIMESTAMP('2009-09-02 01:51:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50149 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50150,50000,0,187,0,13,50147,TO_TIMESTAMP('2009-09-02 01:51:07','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE07','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_BPartner_C_BPartner_ID',160,TO_TIMESTAMP('2009-09-02 01:51:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50150 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50151,50000,0,838,0,19,50148,TO_TIMESTAMP('2009-09-02 01:51:08','YYYY-MM-DD HH24:MI:SS'),0,'Dunning Rules for overdue invoices','EE07','The Dunning indicates the rules and method of dunning for past due payments.','Y','Y','N','N','N','N','C_BPartner_C_Dunning_ID',161,TO_TIMESTAMP('2009-09-02 01:51:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50151 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50152,50000,0,1159,0,19,50149,TO_TIMESTAMP('2009-09-02 01:51:08','YYYY-MM-DD HH24:MI:SS'),0,'Greeting to print on correspondence','EE07','The Greeting identifies the greeting to print on correspondence.','Y','Y','N','N','N','N','C_BPartner_C_Greeting_ID',162,TO_TIMESTAMP('2009-09-02 01:51:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50152 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50153,50000,0,560,0,19,50150,TO_TIMESTAMP('2009-09-02 01:51:09','YYYY-MM-DD HH24:MI:SS'),0,'Schedule for generating Invoices','EE07','The Invoice Schedule identifies the frequency used when generating invoices.','Y','Y','N','N','N','N','C_BPartner_C_InvoiceSchedule_I',163,TO_TIMESTAMP('2009-09-02 01:51:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50153 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50154,50000,0,204,0,19,50151,TO_TIMESTAMP('2009-09-02 01:51:09','YYYY-MM-DD HH24:MI:SS'),0,'The terms of Payment (timing, discount)','EE07','Payment Terms identify the method and timing of payment.','Y','Y','N','N','N','N','C_BPartner_C_PaymentTerm_ID',164,TO_TIMESTAMP('2009-09-02 01:51:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50154 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50155,50000,0,53356,0,19,50152,TO_TIMESTAMP('2009-09-02 01:51:10','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_BPartner_C_TaxGroup_ID',165,TO_TIMESTAMP('2009-09-02 01:51:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50155 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50156,50000,0,245,0,16,50153,TO_TIMESTAMP('2009-09-02 01:51:10','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE07','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_BPartner_Created',166,TO_TIMESTAMP('2009-09-02 01:51:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50156 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50157,50000,0,246,0,18,50154,TO_TIMESTAMP('2009-09-02 01:51:11','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE07','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_BPartner_CreatedBy',167,TO_TIMESTAMP('2009-09-02 01:51:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50157 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50158,50000,0,260,0,10,50155,TO_TIMESTAMP('2009-09-02 01:51:11','YYYY-MM-DD HH24:MI:SS'),0,'Dun & Bradstreet Number','EE07','Used for EDI - For details see   www.dnb.com/dunsno/list.htm','Y','Y','N','N','N','N','C_BPartner_DUNS',168,TO_TIMESTAMP('2009-09-02 01:51:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50158 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50159,50000,0,555,0,17,50156,TO_TIMESTAMP('2009-09-02 01:51:12','YYYY-MM-DD HH24:MI:SS'),0,'Defines the timing of Delivery','EE07','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','C_BPartner_DeliveryRule',169,TO_TIMESTAMP('2009-09-02 01:51:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50159 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50160,50000,0,274,0,17,50157,TO_TIMESTAMP('2009-09-02 01:51:12','YYYY-MM-DD HH24:MI:SS'),0,'How the order will be delivered','EE07','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','N','N','N','N','C_BPartner_DeliveryViaRule',170,TO_TIMESTAMP('2009-09-02 01:51:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50160 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50161,50000,0,275,0,10,50158,TO_TIMESTAMP('2009-09-02 01:51:12','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07','A description is limited to 255 characters.','Y','Y','N','N','N','N','C_BPartner_Description',171,TO_TIMESTAMP('2009-09-02 01:51:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50161 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:14 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50162,50000,0,866,0,11,50159,TO_TIMESTAMP('2009-09-02 01:51:13','YYYY-MM-DD HH24:MI:SS'),0,'Number of copies to be printed','EE07','The Document Copies indicates the number of copies of each document that will be generated.','Y','Y','N','N','N','N','C_BPartner_DocumentCopies',172,TO_TIMESTAMP('2009-09-02 01:51:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:14 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50162 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50163,50000,0,53223,0,15,50160,TO_TIMESTAMP('2009-09-02 01:51:14','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_BPartner_DunningGrace',173,TO_TIMESTAMP('2009-09-02 01:51:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50163 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50164,50000,0,305,0,15,50161,TO_TIMESTAMP('2009-09-02 01:51:15','YYYY-MM-DD HH24:MI:SS'),0,'Date of First Sale','EE07','The First Sale Date identifies the date of the first sale to this Business Partner','Y','Y','N','N','N','N','C_BPartner_FirstSale',174,TO_TIMESTAMP('2009-09-02 01:51:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50164 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50165,50000,0,1712,0,22,50162,TO_TIMESTAMP('2009-09-02 01:51:15','YYYY-MM-DD HH24:MI:SS'),0,'Flat discount percentage ','EE07','Y','Y','N','N','N','N','C_BPartner_FlatDiscount',175,TO_TIMESTAMP('2009-09-02 01:51:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50165 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50166,50000,0,1007,0,17,50163,TO_TIMESTAMP('2009-09-02 01:51:15','YYYY-MM-DD HH24:MI:SS'),0,'Method for charging Freight','EE07','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','N','N','N','N','C_BPartner_FreightCostRule',176,TO_TIMESTAMP('2009-09-02 01:51:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50166 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50167,50000,0,559,0,17,50164,TO_TIMESTAMP('2009-09-02 01:51:16','YYYY-MM-DD HH24:MI:SS'),0,'Frequency and method of invoicing ','EE07','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Y','N','N','N','N','C_BPartner_InvoiceRule',177,TO_TIMESTAMP('2009-09-02 01:51:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50167 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50168,50000,0,1822,0,18,50165,TO_TIMESTAMP('2009-09-02 01:51:16','YYYY-MM-DD HH24:MI:SS'),0,'Print Format for printing Invoices','EE07','You need to define a Print Format to print the document.','Y','Y','N','N','N','N','C_BPartner_Invoice_PrintFormat',178,TO_TIMESTAMP('2009-09-02 01:51:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50168 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50169,50000,0,348,0,20,50166,TO_TIMESTAMP('2009-09-02 01:51:17','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_BPartner_IsActive',179,TO_TIMESTAMP('2009-09-02 01:51:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50169 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50170,50000,0,1239,0,20,50168,TO_TIMESTAMP('2009-09-02 01:51:17','YYYY-MM-DD HH24:MI:SS'),0,'Print Discount on Invoice and Order','EE07','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Y','N','N','N','N','C_BPartner_IsDiscountPrinted',180,TO_TIMESTAMP('2009-09-02 01:51:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50170 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50171,50000,0,373,0,20,50169,TO_TIMESTAMP('2009-09-02 01:51:18','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  this Business Partner is an employee','EE07','The Employee checkbox indicates if this Business Partner is an Employee.  If it is selected, additional fields will display which further identify this employee.','Y','Y','N','N','N','N','C_BPartner_IsEmployee',181,TO_TIMESTAMP('2009-09-02 01:51:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50171 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50172,50000,0,922,0,20,50170,TO_TIMESTAMP('2009-09-02 01:51:18','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','C_BPartner_IsOneTime',182,TO_TIMESTAMP('2009-09-02 01:51:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50172 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50173,50000,0,402,0,20,50171,TO_TIMESTAMP('2009-09-02 01:51:18','YYYY-MM-DD HH24:MI:SS'),0,'Indicates this is a Prospect','EE07','The Prospect checkbox indicates an entity that is an active prospect.','Y','Y','N','N','N','N','C_BPartner_IsProspect',183,TO_TIMESTAMP('2009-09-02 01:51:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50173 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50174,50000,0,409,0,20,50172,TO_TIMESTAMP('2009-09-02 01:51:19','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if  the business partner is a sales representative or company agent','EE07','The Sales Rep checkbox indicates if this business partner is a sales representative. A sales representative may also be an emplyee, but does not need to be.','Y','Y','N','N','N','N','C_BPartner_IsSalesRep',184,TO_TIMESTAMP('2009-09-02 01:51:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50174 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50175,50000,0,416,0,20,50173,TO_TIMESTAMP('2009-09-02 01:51:19','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity','EE07','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Y','N','N','N','N','C_BPartner_IsSummary',185,TO_TIMESTAMP('2009-09-02 01:51:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50175 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50176,50000,0,930,0,20,50174,TO_TIMESTAMP('2009-09-02 01:51:20','YYYY-MM-DD HH24:MI:SS'),0,'Business partner is exempt from tax','EE07','If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Y','N','N','N','N','C_BPartner_IsTaxExempt',186,TO_TIMESTAMP('2009-09-02 01:51:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50176 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50177,50000,0,426,0,20,50175,TO_TIMESTAMP('2009-09-02 01:51:20','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this Business Partner is a Vendor','EE07','The Vendor checkbox indicates if this Business Partner is a Vendor.  If it is selected, additional fields will display which further identify this vendor.','Y','Y','N','N','N','N','C_BPartner_IsVendor',187,TO_TIMESTAMP('2009-09-02 01:51:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50177 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50178,50000,0,1714,0,18,50176,TO_TIMESTAMP('2009-09-02 01:51:21','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the trade discount percentage','EE07','After calculation of the (standard) price, the trade discount percentage is calculated and applied resulting in the final price.','Y','Y','N','N','N','N','C_BPartner_M_DiscountSchema_ID',188,TO_TIMESTAMP('2009-09-02 01:51:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50178 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50179,50000,0,449,0,19,50177,TO_TIMESTAMP('2009-09-02 01:51:22','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','EE07','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Y','N','N','N','N','C_BPartner_M_PriceList_ID',189,TO_TIMESTAMP('2009-09-02 01:51:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50179 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50180,50000,0,468,0,10,50178,TO_TIMESTAMP('2009-09-02 01:51:22','YYYY-MM-DD HH24:MI:SS'),0,'Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html','EE07','The NAICS/SIC identifies either of these codes that may be applicable to this Business Partner.','Y','Y','N','N','N','N','C_BPartner_NAICS',190,TO_TIMESTAMP('2009-09-02 01:51:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50180 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50181,50000,0,469,0,10,50179,TO_TIMESTAMP('2009-09-02 01:51:23','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','C_BPartner_Name',191,TO_TIMESTAMP('2009-09-02 01:51:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50181 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50182,50000,0,1111,0,10,50180,TO_TIMESTAMP('2009-09-02 01:51:23','YYYY-MM-DD HH24:MI:SS'),0,'Additional Name','EE07','Y','Y','N','N','N','N','C_BPartner_Name2',192,TO_TIMESTAMP('2009-09-02 01:51:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50182 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50183,50000,0,473,0,11,50181,TO_TIMESTAMP('2009-09-02 01:51:24','YYYY-MM-DD HH24:MI:SS'),0,'Number of employees','EE07','Indicates the number of employees for this Business Partner.  This field displays only for Prospects.','Y','Y','N','N','N','N','C_BPartner_NumberEmployees',193,TO_TIMESTAMP('2009-09-02 01:51:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50183 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50184,50000,0,952,0,10,50182,TO_TIMESTAMP('2009-09-02 01:51:24','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE07','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','N','N','N','N','C_BPartner_POReference',194,TO_TIMESTAMP('2009-09-02 01:51:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50184 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50185,50000,0,1717,0,18,50183,TO_TIMESTAMP('2009-09-02 01:51:25','YYYY-MM-DD HH24:MI:SS'),0,'Schema to calculate the purchase trade discount percentage','EE07','Y','Y','N','N','N','N','C_BPartner_PO_DiscountSchema_I',195,TO_TIMESTAMP('2009-09-02 01:51:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50185 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50186,50000,0,1576,0,18,50184,TO_TIMESTAMP('2009-09-02 01:51:25','YYYY-MM-DD HH24:MI:SS'),0,'Payment rules for a purchase order','EE07','The PO Payment Term indicates the payment term that will be used when this purchase order becomes an invoice.','Y','Y','N','N','N','N','C_BPartner_PO_PaymentTerm_ID',196,TO_TIMESTAMP('2009-09-02 01:51:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50186 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50187,50000,0,480,0,18,50185,TO_TIMESTAMP('2009-09-02 01:51:25','YYYY-MM-DD HH24:MI:SS'),0,'Price List used by this Business Partner','EE07','Identifies the price list used by a Vendor for products purchased by this organization.','Y','Y','N','N','N','N','C_BPartner_PO_PriceList_ID',197,TO_TIMESTAMP('2009-09-02 01:51:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50187 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50188,50000,0,1143,0,17,50186,TO_TIMESTAMP('2009-09-02 01:51:26','YYYY-MM-DD HH24:MI:SS'),0,'How you pay the invoice','EE07','The Payment Rule indicates the method of invoice payment.','Y','Y','N','N','N','N','C_BPartner_PaymentRule',198,TO_TIMESTAMP('2009-09-02 01:51:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50188 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50189,50000,0,950,0,17,50187,TO_TIMESTAMP('2009-09-02 01:51:27','YYYY-MM-DD HH24:MI:SS'),0,'Purchase payment option','EE07','The Payment Rule indicates the method of purchase payment.','Y','Y','N','N','N','N','C_BPartner_PaymentRulePO',199,TO_TIMESTAMP('2009-09-02 01:51:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50189 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50190,50000,0,515,0,12,50188,TO_TIMESTAMP('2009-09-02 01:51:28','YYYY-MM-DD HH24:MI:SS'),0,'Total Revenue expected','EE07','The Potential Life Time Value is the anticipated revenue in primary accounting currency to be generated by the Business Partner.','Y','Y','N','N','N','N','C_BPartner_PotentialLifeTimeVa',200,TO_TIMESTAMP('2009-09-02 01:51:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50190 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50191,50000,0,962,0,10,50189,TO_TIMESTAMP('2009-09-02 01:51:29','YYYY-MM-DD HH24:MI:SS'),0,'Classification or Importance','EE07','The Rating is used to differentiate the importance','Y','Y','N','N','N','N','C_BPartner_Rating',201,TO_TIMESTAMP('2009-09-02 01:51:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50191 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50192,50000,0,540,0,10,50190,TO_TIMESTAMP('2009-09-02 01:51:29','YYYY-MM-DD HH24:MI:SS'),0,'Your customer or vendor number at the Business Partner''s site','EE07','The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.','Y','Y','N','N','N','N','C_BPartner_ReferenceNo',202,TO_TIMESTAMP('2009-09-02 01:51:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50192 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50193,50000,0,2181,0,17,50191,TO_TIMESTAMP('2009-09-02 01:51:30','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Credit Status','EE07','Credit Management is inactive if Credit Status is No Credit Check, Credit Stop or if the Credit Limit is 0.
If active, the status is set automatically set to Credit Hold, if the Total Open Balance (including Vendor activities)  is higher then the Credit Limit. It is set to Credit Watch, if above 90% of the Credit Limit and Credit OK otherwise.','Y','Y','N','N','N','N','C_BPartner_SOCreditStatus',203,TO_TIMESTAMP('2009-09-02 01:51:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50193 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50194,50000,0,553,0,12,50192,TO_TIMESTAMP('2009-09-02 01:51:30','YYYY-MM-DD HH24:MI:SS'),0,'Total outstanding invoice amounts allowed','EE07','The Credit Limit indicates the total amount allowed ''on account'' in primary accounting currency.  If the Credit Limit is 0, no ckeck is performed.  Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Y','N','N','N','N','C_BPartner_SO_CreditLimit',204,TO_TIMESTAMP('2009-09-02 01:51:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50194 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50195,50000,0,554,0,12,50193,TO_TIMESTAMP('2009-09-02 01:51:30','YYYY-MM-DD HH24:MI:SS'),0,'Current open balance','EE07','The Credit Used indicates the total amount of open or unpaid invoices in primary accounting currency for the Business Partner. Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Y','N','N','N','N','C_BPartner_SO_CreditUsed',205,TO_TIMESTAMP('2009-09-02 01:51:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50195 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50196,50000,0,1063,0,18,50195,TO_TIMESTAMP('2009-09-02 01:51:31','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE07','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','C_BPartner_SalesRep_ID',206,TO_TIMESTAMP('2009-09-02 01:51:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50196 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50197,50000,0,563,0,11,50196,TO_TIMESTAMP('2009-09-02 01:51:31','YYYY-MM-DD HH24:MI:SS'),0,'Total Volume of Sales in Thousands of Currency','EE07','The Sales Volume indicates the total volume of sales for a Business Partner.','Y','Y','N','N','N','N','C_BPartner_SalesVolume',207,TO_TIMESTAMP('2009-09-02 01:51:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50197 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50198,50000,0,1978,0,20,50197,TO_TIMESTAMP('2009-09-02 01:51:33','YYYY-MM-DD HH24:MI:SS'),0,'Enable sending Document EMail','EE07','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','C_BPartner_SendEMail',208,TO_TIMESTAMP('2009-09-02 01:51:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50198 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50199,50000,0,569,0,11,50198,TO_TIMESTAMP('2009-09-02 01:51:33','YYYY-MM-DD HH24:MI:SS'),0,'Share of Customer''s business as a percentage','EE07','The Share indicates the percentage of this Business Partner''s volume of the products supplied.','Y','Y','N','N','N','N','C_BPartner_ShareOfCustomer',209,TO_TIMESTAMP('2009-09-02 01:51:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50199 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50200,50000,0,2240,0,11,50199,TO_TIMESTAMP('2009-09-02 01:51:33','YYYY-MM-DD HH24:MI:SS'),0,'Minimum Shelf Life in percent based on Product Instance Guarantee Date','EE07','Miminum Shelf Life of products with Guarantee Date instance. If > 0 you cannot select products with a shelf life ((Guarantee Date-Today) / Guarantee Days) less than the minum shelf life, unless you select "Show All"','Y','Y','N','N','N','N','C_BPartner_ShelfLifeMinPct',210,TO_TIMESTAMP('2009-09-02 01:51:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50200 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50201,50000,0,590,0,10,50200,TO_TIMESTAMP('2009-09-02 01:51:34','YYYY-MM-DD HH24:MI:SS'),0,'Tax Identification','EE07','The Tax ID field identifies the legal Identification number of this Entity.','Y','Y','N','N','N','N','C_BPartner_TaxID',211,TO_TIMESTAMP('2009-09-02 01:51:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50201 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50202,50000,0,2562,0,12,50201,TO_TIMESTAMP('2009-09-02 01:51:34','YYYY-MM-DD HH24:MI:SS'),0,'Total Open Balance Amount in primary Accounting Currency','EE07','The Total Open Balance Amount is the calculated open item amount for Customer and Vendor activity.  If the Balance is below zero, we owe the Business Partner.  The amout is used for Credit Management.
Invoices and Payment Allocations determine the Open Balance (i.e. not Orders or Payments).','Y','Y','N','N','N','N','C_BPartner_TotalOpenBalance',212,TO_TIMESTAMP('2009-09-02 01:51:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50202 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50203,50000,0,983,0,40,50202,TO_TIMESTAMP('2009-09-02 01:51:35','YYYY-MM-DD HH24:MI:SS'),0,'Full URL address - e.g. http://www.adempiere.org','EE07','The URL defines an fully qualified web address like http://www.adempiere.org','Y','Y','N','N','N','N','C_BPartner_URL',213,TO_TIMESTAMP('2009-09-02 01:51:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:35 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50203 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50204,50000,0,607,0,16,50203,TO_TIMESTAMP('2009-09-02 01:51:35','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE07','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_BPartner_Updated',214,TO_TIMESTAMP('2009-09-02 01:51:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50204 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50205,50000,0,608,0,18,50204,TO_TIMESTAMP('2009-09-02 01:51:36','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE07','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_BPartner_UpdatedBy',215,TO_TIMESTAMP('2009-09-02 01:51:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:36 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50205 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:37 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50206,50000,0,620,0,10,50205,TO_TIMESTAMP('2009-09-02 01:51:36','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE07','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','N','N','N','N','C_BPartner_Value',216,TO_TIMESTAMP('2009-09-02 01:51:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:37 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50206 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:37 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50207,50000,0,102,0,19,50206,TO_TIMESTAMP('2009-09-02 01:51:37','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_BPartner_Location_AD_Client_',217,TO_TIMESTAMP('2009-09-02 01:51:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:37 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50207 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50208,50000,0,113,0,19,50207,TO_TIMESTAMP('2009-09-02 01:51:37','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_BPartner_Location_AD_Org_ID',218,TO_TIMESTAMP('2009-09-02 01:51:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50208 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50209,50000,0,187,0,30,50208,TO_TIMESTAMP('2009-09-02 01:51:38','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE07','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','C_BPartner_Location_C_BPartner',219,TO_TIMESTAMP('2009-09-02 01:51:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50209 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50210,50000,0,189,0,13,50209,TO_TIMESTAMP('2009-09-02 01:51:38','YYYY-MM-DD HH24:MI:SS'),0,'Identifies the (ship to) address for this Business Partner','EE07','The Partner address indicates the location of a Business Partner','Y','Y','N','N','N','N','C_BPartner_Location_C_BPartner',220,TO_TIMESTAMP('2009-09-02 01:51:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:38 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50210 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:39 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50211,50000,0,202,0,21,50210,TO_TIMESTAMP('2009-09-02 01:51:38','YYYY-MM-DD HH24:MI:SS'),0,'Location or Address','EE07','The Location / Address field defines the location of an entity.','Y','Y','N','N','N','N','C_BPartner_Location_C_Location',221,TO_TIMESTAMP('2009-09-02 01:51:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:39 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50211 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50212,50000,0,210,0,19,50211,TO_TIMESTAMP('2009-09-02 01:51:39','YYYY-MM-DD HH24:MI:SS'),0,'Sales coverage region','EE07','The Sales Region indicates a specific area of sales coverage.','Y','Y','N','N','N','N','C_BPartner_Location_C_SalesReg',222,TO_TIMESTAMP('2009-09-02 01:51:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50212 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50213,50000,0,245,0,16,50212,TO_TIMESTAMP('2009-09-02 01:51:40','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE07','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_BPartner_Location_Created',223,TO_TIMESTAMP('2009-09-02 01:51:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:40 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50213 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:41 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50214,50000,0,246,0,18,50213,TO_TIMESTAMP('2009-09-02 01:51:40','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE07','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_BPartner_Location_CreatedBy',224,TO_TIMESTAMP('2009-09-02 01:51:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:41 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50214 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:41 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50215,50000,0,301,0,10,50214,TO_TIMESTAMP('2009-09-02 01:51:41','YYYY-MM-DD HH24:MI:SS'),0,'Facsimile number','EE07','The Fax identifies a facsimile number for this Business Partner or  Location','Y','Y','N','N','N','N','C_BPartner_Location_Fax',225,TO_TIMESTAMP('2009-09-02 01:51:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:41 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50215 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50216,50000,0,327,0,10,50215,TO_TIMESTAMP('2009-09-02 01:51:41','YYYY-MM-DD HH24:MI:SS'),0,'ISDN or modem line','EE07','The ISDN identifies a ISDN or Modem line number.','Y','Y','N','N','N','N','C_BPartner_Location_ISDN',226,TO_TIMESTAMP('2009-09-02 01:51:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50216 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50217,50000,0,348,0,20,50216,TO_TIMESTAMP('2009-09-02 01:51:42','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_BPartner_Location_IsActive',227,TO_TIMESTAMP('2009-09-02 01:51:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:42 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50217 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50218,50000,0,916,0,20,50217,TO_TIMESTAMP('2009-09-02 01:51:42','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Invoice/Bill Address','EE07','If the Invoice Address is selected, the location is used to send invoices to a customer or receive invoices from a vendor.','Y','Y','N','N','N','N','C_BPartner_Location_IsBillTo',228,TO_TIMESTAMP('2009-09-02 01:51:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50218 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50219,50000,0,925,0,20,50218,TO_TIMESTAMP('2009-09-02 01:51:43','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner pays from that address and we''ll send dunning letters there','EE07','If the Pay-From Address is selected, this location is the address the Business Partner pays from and where dunning letters will be sent to.','Y','Y','N','N','N','N','C_BPartner_Location_IsPayFrom',229,TO_TIMESTAMP('2009-09-02 01:51:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:43 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50219 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50220,50000,0,927,0,20,50219,TO_TIMESTAMP('2009-09-02 01:51:43','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner payment address','EE07','If the Remit-To Address is selected, the location is used to send payments to the vendor.','Y','Y','N','N','N','N','C_BPartner_Location_IsRemitTo',230,TO_TIMESTAMP('2009-09-02 01:51:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:44 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50220 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50221,50000,0,929,0,20,50220,TO_TIMESTAMP('2009-09-02 01:51:44','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Shipment Address','EE07','If the Ship Address is selected, the location is used to ship goods to a customer or receive goods from a vendor.','Y','Y','N','N','N','N','C_BPartner_Location_IsShipTo',231,TO_TIMESTAMP('2009-09-02 01:51:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50221 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50222,50000,0,469,0,10,50221,TO_TIMESTAMP('2009-09-02 01:51:45','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','C_BPartner_Location_Name',232,TO_TIMESTAMP('2009-09-02 01:51:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:45 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50222 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50223,50000,0,505,0,10,50222,TO_TIMESTAMP('2009-09-02 01:51:45','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a telephone number','EE07','The Phone field identifies a telephone number','Y','Y','N','N','N','N','C_BPartner_Location_Phone',233,TO_TIMESTAMP('2009-09-02 01:51:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50223 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50224,50000,0,506,0,10,50223,TO_TIMESTAMP('2009-09-02 01:51:46','YYYY-MM-DD HH24:MI:SS'),0,'Identifies an alternate telephone number.','EE07','The 2nd Phone field identifies an alternate telephone number.','Y','Y','N','N','N','N','C_BPartner_Location_Phone2',234,TO_TIMESTAMP('2009-09-02 01:51:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50224 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50225,50000,0,607,0,16,50224,TO_TIMESTAMP('2009-09-02 01:51:46','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE07','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_BPartner_Location_Updated',235,TO_TIMESTAMP('2009-09-02 01:51:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:46 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50225 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50226,50000,0,608,0,18,50225,TO_TIMESTAMP('2009-09-02 01:51:46','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE07','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_BPartner_Location_UpdatedBy',236,TO_TIMESTAMP('2009-09-02 01:51:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:47 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50226 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50227,50000,0,102,0,19,50226,TO_TIMESTAMP('2009-09-02 01:51:47','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','C_Location_AD_Client_ID',237,TO_TIMESTAMP('2009-09-02 01:51:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50227 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50228,50000,0,113,0,19,50227,TO_TIMESTAMP('2009-09-02 01:51:48','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','C_Location_AD_Org_ID',238,TO_TIMESTAMP('2009-09-02 01:51:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50228 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50229,50000,0,156,0,10,50228,TO_TIMESTAMP('2009-09-02 01:51:48','YYYY-MM-DD HH24:MI:SS'),0,'Address line 1 for this location','EE07','The Address 1 identifies the address for an entity''s location','Y','Y','N','N','N','N','C_Location_Address1',239,TO_TIMESTAMP('2009-09-02 01:51:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:48 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50229 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50230,50000,0,157,0,10,50229,TO_TIMESTAMP('2009-09-02 01:51:48','YYYY-MM-DD HH24:MI:SS'),0,'Address line 2 for this location','EE07','The Address 2 provides additional address information for an entity.  It can be used for building location, apartment number or similar information.','Y','Y','N','N','N','N','C_Location_Address2',240,TO_TIMESTAMP('2009-09-02 01:51:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50230 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50231,50000,0,2555,0,10,50230,TO_TIMESTAMP('2009-09-02 01:51:49','YYYY-MM-DD HH24:MI:SS'),0,'Address Line 3 for the location','EE07','The Address 2 provides additional address information for an entity.  It can be used for building location, apartment number or similar information.','Y','Y','N','N','N','N','C_Location_Address3',241,TO_TIMESTAMP('2009-09-02 01:51:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:49 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50231 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50232,50000,0,2556,0,10,50231,TO_TIMESTAMP('2009-09-02 01:51:49','YYYY-MM-DD HH24:MI:SS'),0,'Address Line 4 for the location','EE07','The Address 4 provides additional address information for an entity.  It can be used for building location, apartment number or similar information.','Y','Y','N','N','N','N','C_Location_Address4',242,TO_TIMESTAMP('2009-09-02 01:51:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50232 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50233,50000,0,1830,0,30,50232,TO_TIMESTAMP('2009-09-02 01:51:50','YYYY-MM-DD HH24:MI:SS'),0,'City','EE07','City in a country','Y','Y','N','N','N','N','C_Location_C_City_ID',243,TO_TIMESTAMP('2009-09-02 01:51:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:50 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50233 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50234,50000,0,192,0,19,50233,TO_TIMESTAMP('2009-09-02 01:51:50','YYYY-MM-DD HH24:MI:SS'),0,'Country ','EE07','The Country defines a Country.  Each Country must be defined before it can be used in any document.','Y','Y','N','N','N','N','C_Location_C_Country_ID',244,TO_TIMESTAMP('2009-09-02 01:51:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50234 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50235,50000,0,202,0,13,50234,TO_TIMESTAMP('2009-09-02 01:51:51','YYYY-MM-DD HH24:MI:SS'),0,'Location or Address','EE07','The Location / Address field defines the location of an entity.','Y','Y','N','N','N','N','C_Location_C_Location_ID',245,TO_TIMESTAMP('2009-09-02 01:51:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:51 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50235 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50236,50000,0,209,0,19,50235,TO_TIMESTAMP('2009-09-02 01:51:51','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a geographical Region','EE07','The Region identifies a unique Region for this Country.','Y','Y','N','N','N','N','C_Location_C_Region_ID',246,TO_TIMESTAMP('2009-09-02 01:51:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50236 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50237,50000,0,225,0,10,50236,TO_TIMESTAMP('2009-09-02 01:51:52','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a City','EE07','The City identifies a unique City for this Country or Region.','Y','Y','Y','N','N','N','C_Location_City',247,TO_TIMESTAMP('2009-09-02 01:51:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:52 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50237 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50238,50000,0,245,0,16,50237,TO_TIMESTAMP('2009-09-02 01:51:52','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE07','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','C_Location_Created',248,TO_TIMESTAMP('2009-09-02 01:51:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50238 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50239,50000,0,246,0,18,50238,TO_TIMESTAMP('2009-09-02 01:51:53','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE07','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','C_Location_CreatedBy',249,TO_TIMESTAMP('2009-09-02 01:51:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:53 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50239 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50240,50000,0,348,0,20,50239,TO_TIMESTAMP('2009-09-02 01:51:53','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','C_Location_IsActive',250,TO_TIMESTAMP('2009-09-02 01:51:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50240 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50241,50000,0,512,0,10,50240,TO_TIMESTAMP('2009-09-02 01:51:54','YYYY-MM-DD HH24:MI:SS'),0,'Postal code','EE07','The Postal Code or ZIP identifies the postal code for this entity''s address.','Y','Y','N','N','N','N','C_Location_Postal',251,TO_TIMESTAMP('2009-09-02 01:51:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:54 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50241 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50242,50000,0,513,0,10,50241,TO_TIMESTAMP('2009-09-02 01:51:54','YYYY-MM-DD HH24:MI:SS'),0,'Additional ZIP or Postal code','EE07','The Additional ZIP or Postal Code identifies, if appropriate, any additional Postal Code information.','Y','Y','N','N','N','N','C_Location_Postal_Add',252,TO_TIMESTAMP('2009-09-02 01:51:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50242 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50243,50000,0,541,0,10,50242,TO_TIMESTAMP('2009-09-02 01:51:55','YYYY-MM-DD HH24:MI:SS'),0,'Name of the Region','EE07','The Region Name defines the name that will print when this region is used in a document.','Y','Y','N','N','N','N','C_Location_RegionName',253,TO_TIMESTAMP('2009-09-02 01:51:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50243 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50244,50000,0,607,0,16,50243,TO_TIMESTAMP('2009-09-02 01:51:55','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE07','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','C_Location_Updated',254,TO_TIMESTAMP('2009-09-02 01:51:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:55 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50244 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50245,50000,0,608,0,18,50244,TO_TIMESTAMP('2009-09-02 01:51:55','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE07','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','C_Location_UpdatedBy',255,TO_TIMESTAMP('2009-09-02 01:51:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50245 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50246,50000,0,102,0,19,50245,TO_TIMESTAMP('2009-09-02 01:51:56','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','M_Product_AD_Client_ID',256,TO_TIMESTAMP('2009-09-02 01:51:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:56 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50246 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50247,50000,0,113,0,19,50246,TO_TIMESTAMP('2009-09-02 01:51:56','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','M_Product_AD_Org_ID',257,TO_TIMESTAMP('2009-09-02 01:51:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:57 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50247 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50248,50000,0,1078,0,19,50247,TO_TIMESTAMP('2009-09-02 01:51:57','YYYY-MM-DD HH24:MI:SS'),0,'Method for recording revenue','EE07','The Revenue Recognition indicates how revenue will be recognized for this product','Y','Y','N','N','N','N','M_Product_C_RevenueRecognition',258,TO_TIMESTAMP('2009-09-02 01:51:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50248 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50249,50000,0,2385,0,19,50248,TO_TIMESTAMP('2009-09-02 01:51:58','YYYY-MM-DD HH24:MI:SS'),0,'Type of subscription','EE07','Subscription type and renewal frequency','Y','Y','N','N','N','N','M_Product_C_SubscriptionType_I',259,TO_TIMESTAMP('2009-09-02 01:51:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50249 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:58 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50250,50000,0,211,0,19,50249,TO_TIMESTAMP('2009-09-02 01:51:58','YYYY-MM-DD HH24:MI:SS'),0,'Tax Category','EE07','The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.','Y','Y','N','N','N','N','M_Product_C_TaxCategory_ID',260,TO_TIMESTAMP('2009-09-02 01:51:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50250 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50251,50000,0,215,0,19,50250,TO_TIMESTAMP('2009-09-02 01:51:59','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE07','The UOM defines a unique non monetary Unit of Measure','Y','Y','N','N','N','N','M_Product_C_UOM_ID',261,TO_TIMESTAMP('2009-09-02 01:51:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50251 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:51:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50252,50000,0,852,0,10,50251,TO_TIMESTAMP('2009-09-02 01:51:59','YYYY-MM-DD HH24:MI:SS'),0,'Classification for grouping','EE07','The Classification can be used to optionally group products.','Y','Y','N','N','N','N','M_Product_Classification',262,TO_TIMESTAMP('2009-09-02 01:51:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:51:59 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50252 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50253,50000,0,245,0,16,50252,TO_TIMESTAMP('2009-09-02 01:51:59','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE07','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','M_Product_Created',263,TO_TIMESTAMP('2009-09-02 01:51:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:00 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50253 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50254,50000,0,246,0,18,50253,TO_TIMESTAMP('2009-09-02 01:52:00','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE07','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','M_Product_CreatedBy',264,TO_TIMESTAMP('2009-09-02 01:52:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50254 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50255,50000,0,275,0,10,50254,TO_TIMESTAMP('2009-09-02 01:52:01','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07','A description is limited to 255 characters.','Y','Y','N','N','N','N','M_Product_Description',265,TO_TIMESTAMP('2009-09-02 01:52:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50255 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50256,50000,0,1920,0,40,50255,TO_TIMESTAMP('2009-09-02 01:52:01','YYYY-MM-DD HH24:MI:SS'),0,'URL for the description','EE07','Y','Y','N','N','N','N','M_Product_DescriptionURL',266,TO_TIMESTAMP('2009-09-02 01:52:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:01 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50256 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50257,50000,0,278,0,20,50256,TO_TIMESTAMP('2009-09-02 01:52:01','YYYY-MM-DD HH24:MI:SS'),0,'This product is no longer available','EE07','The Discontinued check box indicates a product that has been discontinued.','Y','Y','N','N','N','N','M_Product_Discontinued',267,TO_TIMESTAMP('2009-09-02 01:52:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50257 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50258,50000,0,279,0,15,50257,TO_TIMESTAMP('2009-09-02 01:52:02','YYYY-MM-DD HH24:MI:SS'),0,'Discontinued By','EE07','The Discontinued By indicates the individual who discontinued this product','Y','Y','N','N','N','N','M_Product_DiscontinuedBy',268,TO_TIMESTAMP('2009-09-02 01:52:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:02 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50258 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50259,50000,0,868,0,14,50258,TO_TIMESTAMP('2009-09-02 01:52:02','YYYY-MM-DD HH24:MI:SS'),0,'Additional information for a Document','EE07','The Document Note is used for recording any additional information regarding this product.','Y','Y','N','N','N','N','M_Product_DocumentNote',269,TO_TIMESTAMP('2009-09-02 01:52:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50259 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50260,50000,0,52018,0,10,50259,TO_TIMESTAMP('2009-09-02 01:52:04','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','M_Product_Group1',270,TO_TIMESTAMP('2009-09-02 01:52:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:04 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50260 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50261,50000,0,52019,0,10,50260,TO_TIMESTAMP('2009-09-02 01:52:04','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','M_Product_Group2',271,TO_TIMESTAMP('2009-09-02 01:52:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50261 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50262,50000,0,1937,0,11,50261,TO_TIMESTAMP('2009-09-02 01:52:05','YYYY-MM-DD HH24:MI:SS'),0,'Number of days the product is guaranteed or available','EE07','If the value is 0, there is no limit to the availability or guarantee, otherwise the guarantee date is calculated by adding the days to the delivery date.','Y','Y','N','N','N','N','M_Product_GuaranteeDays',272,TO_TIMESTAMP('2009-09-02 01:52:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:05 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50262 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50263,50000,0,2197,0,11,50262,TO_TIMESTAMP('2009-09-02 01:52:05','YYYY-MM-DD HH24:MI:SS'),0,'Minumum number of guarantee days','EE07','When selecting batch/products with a guarantee date, the minimum left guarantee days for automatic picking.  You can pick any batch/product manually. ','Y','Y','N','N','N','N','M_Product_GuaranteeDaysMin',273,TO_TIMESTAMP('2009-09-02 01:52:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50263 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50264,50000,0,326,0,14,50263,TO_TIMESTAMP('2009-09-02 01:52:06','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','N','N','N','N','M_Product_Help',274,TO_TIMESTAMP('2009-09-02 01:52:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:06 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50264 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50265,50000,0,1720,0,40,50264,TO_TIMESTAMP('2009-09-02 01:52:06','YYYY-MM-DD HH24:MI:SS'),0,'URL of  image','EE07','URL of image; The image is not stored in the database, but retrieved at runtime. The image can be a gif, jpeg or png.','Y','Y','N','N','N','N','M_Product_ImageURL',275,TO_TIMESTAMP('2009-09-02 01:52:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50265 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50266,50000,0,348,0,20,50265,TO_TIMESTAMP('2009-09-02 01:52:07','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','M_Product_IsActive',276,TO_TIMESTAMP('2009-09-02 01:52:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:07 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50266 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50267,50000,0,1326,0,20,50266,TO_TIMESTAMP('2009-09-02 01:52:07','YYYY-MM-DD HH24:MI:SS'),0,'Bill of Materials','EE07','The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','Y','N','N','N','N','M_Product_IsBOM',277,TO_TIMESTAMP('2009-09-02 01:52:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50267 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50268,50000,0,2466,0,20,50267,TO_TIMESTAMP('2009-09-02 01:52:08','YYYY-MM-DD HH24:MI:SS'),0,'Drop Shipments are sent from the Vendor directly to the Customer','EE07','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','N','N','N','N','M_Product_IsDropShip',278,TO_TIMESTAMP('2009-09-02 01:52:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50268 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50269,50000,0,2867,0,20,50268,TO_TIMESTAMP('2009-09-02 01:52:08','YYYY-MM-DD HH24:MI:SS'),0,'Exclude from automatic Delivery','EE07','The product is excluded from generating Shipments.  This allows manual creation of shipments for high demand items. If selected, you need to create the shipment manually.
But, the item is always included, when the delivery rule of the Order is Force (e.g. for POS). 
This allows finer granularity of the Delivery Rule Manual.','Y','Y','N','N','N','N','M_Product_IsExcludeAutoDeliver',279,TO_TIMESTAMP('2009-09-02 01:52:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:08 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50269 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50270,50000,0,1175,0,20,50269,TO_TIMESTAMP('2009-09-02 01:52:08','YYYY-MM-DD HH24:MI:SS'),0,'Print detail BOM elements on the invoice','EE07','The Print Details on Invoice indicates that the BOM element products will print on the Invoice as opposed to this product.','Y','Y','N','N','N','N','M_Product_IsInvoicePrintDetail',280,TO_TIMESTAMP('2009-09-02 01:52:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50270 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50271,50000,0,1176,0,20,50270,TO_TIMESTAMP('2009-09-02 01:52:09','YYYY-MM-DD HH24:MI:SS'),0,'Print detail BOM elements on the pick list','EE07','The Print Details on Pick List indicates that the BOM element products will print on the Pick List as opposed to this product.','Y','Y','N','N','N','N','M_Product_IsPickListPrintDetai',281,TO_TIMESTAMP('2009-09-02 01:52:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:09 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50271 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50272,50000,0,403,0,20,50271,TO_TIMESTAMP('2009-09-02 01:52:09','YYYY-MM-DD HH24:MI:SS'),0,'Organization purchases this product','EE07','The Purchased check box indicates if this product is purchased by this organization.','Y','Y','N','N','N','N','M_Product_IsPurchased',282,TO_TIMESTAMP('2009-09-02 01:52:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50272 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50273,50000,0,2063,0,20,50272,TO_TIMESTAMP('2009-09-02 01:52:10','YYYY-MM-DD HH24:MI:SS'),0,'This is a Self-Service entry or this entry can be changed via Self-Service','EE07','Self-Service allows users to enter data or update their data.  The flag indicates, that this record was entered or created via Self-Service or that the user can change it via the Self-Service functionality.','Y','Y','N','N','N','N','M_Product_IsSelfService',283,TO_TIMESTAMP('2009-09-02 01:52:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:10 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50273 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50274,50000,0,414,0,20,50273,TO_TIMESTAMP('2009-09-02 01:52:10','YYYY-MM-DD HH24:MI:SS'),0,'Organization sells this product','EE07','The Sold check box indicates if this product is sold by this organization.','Y','Y','N','N','N','N','M_Product_IsSold',284,TO_TIMESTAMP('2009-09-02 01:52:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50274 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50275,50000,0,415,0,20,50274,TO_TIMESTAMP('2009-09-02 01:52:11','YYYY-MM-DD HH24:MI:SS'),0,'Organization stocks this product','EE07','The Stocked check box indicates if this product is stocked by this Organization.','Y','Y','N','N','N','N','M_Product_IsStocked',285,TO_TIMESTAMP('2009-09-02 01:52:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50275 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:11 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50276,50000,0,416,0,20,50275,TO_TIMESTAMP('2009-09-02 01:52:11','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity','EE07','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Y','N','N','N','N','M_Product_IsSummary',286,TO_TIMESTAMP('2009-09-02 01:52:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50276 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50277,50000,0,1177,0,20,50276,TO_TIMESTAMP('2009-09-02 01:52:12','YYYY-MM-DD HH24:MI:SS'),0,'The BOM configuration has been verified','EE07','The Verified check box indicates if the configuration of this product has been verified.  This is used for products that consist of a bill of materials','Y','Y','N','N','N','N','M_Product_IsVerified',287,TO_TIMESTAMP('2009-09-02 01:52:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50277 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50278,50000,0,2277,0,20,50277,TO_TIMESTAMP('2009-09-02 01:52:12','YYYY-MM-DD HH24:MI:SS'),0,'If selected, the product is displayed in the inital or any empy search','EE07','In the display of products in the Web Store, the product is displayed in the inital view or if no search criteria are entered. To be displayed, the product must be in the price list used.','Y','Y','N','N','N','N','M_Product_IsWebStoreFeatured',288,TO_TIMESTAMP('2009-09-02 01:52:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:12 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50278 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50279,50000,0,53274,0,11,50278,TO_TIMESTAMP('2009-09-02 01:52:12','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','M_Product_LowLevel',289,TO_TIMESTAMP('2009-09-02 01:52:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50279 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50280,50000,0,2019,0,35,50279,TO_TIMESTAMP('2009-09-02 01:52:13','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE07','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','N','N','N','N','M_Product_M_AttributeSetInstan',290,TO_TIMESTAMP('2009-09-02 01:52:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:13 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50280 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:14 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50281,50000,0,2017,0,19,50280,TO_TIMESTAMP('2009-09-02 01:52:13','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set','EE07','Define Product Attribute Sets to add additional attributes and values to the product. You need to define a Attribute Set if you want to enable Serial and Lot Number tracking.','Y','Y','N','N','N','N','M_Product_M_AttributeSet_ID',291,TO_TIMESTAMP('2009-09-02 01:52:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:14 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50281 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50282,50000,0,2111,0,19,50281,TO_TIMESTAMP('2009-09-02 01:52:14','YYYY-MM-DD HH24:MI:SS'),0,'Category of the Freight','EE07','Freight Categories are used to calculate the Freight for the Shipper selected','Y','Y','N','N','N','N','M_Product_M_FreightCategory_ID',292,TO_TIMESTAMP('2009-09-02 01:52:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50282 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50283,50000,0,448,0,31,50282,TO_TIMESTAMP('2009-09-02 01:52:15','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator','EE07','The Locator indicates where in a Warehouse a product is located.','Y','Y','N','N','N','N','M_Product_M_Locator_ID',293,TO_TIMESTAMP('2009-09-02 01:52:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:15 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50283 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50284,50000,0,453,0,19,50283,TO_TIMESTAMP('2009-09-02 01:52:15','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','EE07','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','N','N','N','N','M_Product_M_Product_Category_I',294,TO_TIMESTAMP('2009-09-02 01:52:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50284 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50285,50000,0,454,0,13,50284,TO_TIMESTAMP('2009-09-02 01:52:16','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE07','Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','N','N','M_Product_M_Product_ID',295,TO_TIMESTAMP('2009-09-02 01:52:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:16 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50285 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50286,50000,0,469,0,10,50285,TO_TIMESTAMP('2009-09-02 01:52:16','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','M_Product_Name',296,TO_TIMESTAMP('2009-09-02 01:52:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50286 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50287,50000,0,524,0,28,50286,TO_TIMESTAMP('2009-09-02 01:52:17','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','M_Product_Processing',297,TO_TIMESTAMP('2009-09-02 01:52:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:17 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50287 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50288,50000,0,1899,0,17,50287,TO_TIMESTAMP('2009-09-02 01:52:17','YYYY-MM-DD HH24:MI:SS'),0,'Type of product','EE07','The type of product also determines accounting consequences.','Y','Y','N','N','N','N','M_Product_ProductType',298,TO_TIMESTAMP('2009-09-02 01:52:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50288 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50289,50000,0,1515,0,19,50288,TO_TIMESTAMP('2009-09-02 01:52:18','YYYY-MM-DD HH24:MI:SS'),0,'Text templates for mailings','EE07','The Mail Template indicates the mail template for return messages. Mail text can include variables.  The priority of parsing is User/Contact, Business Partner and then the underlying business object (like Request, Dunning, Workflow object).<br>
So, @Name@ would resolve into the User name (if user is defined defined), then Business Partner name (if business partner is defined) and then the Name of the business object if it has a Name.<br>
For Multi-Lingual systems, the template is translated based on the Business Partner''s language selection.','Y','Y','N','N','N','N','M_Product_R_MailText_ID',299,TO_TIMESTAMP('2009-09-02 01:52:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:18 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50289 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50290,50000,0,549,0,10,50289,TO_TIMESTAMP('2009-09-02 01:52:18','YYYY-MM-DD HH24:MI:SS'),0,'Stock Keeping Unit','EE07','The SKU indicates a user defined stock keeping unit.  It may be used for an additional bar code symbols or your own schema.','Y','Y','N','N','N','N','M_Product_SKU',300,TO_TIMESTAMP('2009-09-02 01:52:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50290 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50291,50000,0,1776,0,19,50290,TO_TIMESTAMP('2009-09-02 01:52:19','YYYY-MM-DD HH24:MI:SS'),0,'Expense report type','EE07','Y','Y','N','N','N','N','M_Product_S_ExpenseType_ID',301,TO_TIMESTAMP('2009-09-02 01:52:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:19 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50291 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50292,50000,0,1777,0,19,50291,TO_TIMESTAMP('2009-09-02 01:52:19','YYYY-MM-DD HH24:MI:SS'),0,'Resource','EE07','Y','Y','N','N','N','N','M_Product_S_Resource_ID',302,TO_TIMESTAMP('2009-09-02 01:52:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50292 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50293,50000,0,1063,0,18,50292,TO_TIMESTAMP('2009-09-02 01:52:20','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE07','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','M_Product_SalesRep_ID',303,TO_TIMESTAMP('2009-09-02 01:52:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50293 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50294,50000,0,570,0,11,50293,TO_TIMESTAMP('2009-09-02 01:52:20','YYYY-MM-DD HH24:MI:SS'),0,'Shelf depth required','EE07','The Shelf Depth indicates the depth dimension required on a shelf for a product ','Y','Y','N','N','N','N','M_Product_ShelfDepth',304,TO_TIMESTAMP('2009-09-02 01:52:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:20 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50294 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50295,50000,0,571,0,12,50294,TO_TIMESTAMP('2009-09-02 01:52:20','YYYY-MM-DD HH24:MI:SS'),0,'Shelf height required','EE07','The Shelf Height indicates the height dimension required on a shelf for a product','Y','Y','N','N','N','N','M_Product_ShelfHeight',305,TO_TIMESTAMP('2009-09-02 01:52:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50295 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50296,50000,0,572,0,11,50295,TO_TIMESTAMP('2009-09-02 01:52:21','YYYY-MM-DD HH24:MI:SS'),0,'Shelf width required','EE07','The Shelf Width indicates the width dimension required on a shelf for a product','Y','Y','N','N','N','N','M_Product_ShelfWidth',306,TO_TIMESTAMP('2009-09-02 01:52:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:21 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50296 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50297,50000,0,603,0,10,50296,TO_TIMESTAMP('2009-09-02 01:52:21','YYYY-MM-DD HH24:MI:SS'),0,'Bar Code (Universal Product Code or its superset European Article Number)','EE07','Use this field to enter the bar code for the product in any of the bar code symbologies (Codabar, Code 25, Code 39, Code 93, Code 128, UPC (A), UPC (E), EAN-13, EAN-8, ITF, ITF-14, ISBN, ISSN, JAN-13, JAN-8, POSTNET and FIM, MSI/Plessey, and Pharmacode) ','Y','Y','N','N','N','N','M_Product_UPC',307,TO_TIMESTAMP('2009-09-02 01:52:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50297 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50298,50000,0,52054,0,11,50297,TO_TIMESTAMP('2009-09-02 01:52:22','YYYY-MM-DD HH24:MI:SS'),0,'The Units Per Pack indicates the no of units of a product packed together.','EE07','Y','Y','N','N','N','N','M_Product_UnitsPerPack',308,TO_TIMESTAMP('2009-09-02 01:52:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:22 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50298 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50299,50000,0,604,0,37,50298,TO_TIMESTAMP('2009-09-02 01:52:22','YYYY-MM-DD HH24:MI:SS'),0,'Units Per Pallet','EE07','The Units per Pallet indicates the number of units of this product which fit on a pallet.','Y','Y','N','N','N','N','M_Product_UnitsPerPallet',309,TO_TIMESTAMP('2009-09-02 01:52:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50299 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50300,50000,0,607,0,16,50299,TO_TIMESTAMP('2009-09-02 01:52:23','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE07','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','M_Product_Updated',310,TO_TIMESTAMP('2009-09-02 01:52:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:23 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50300 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50301,50000,0,608,0,18,50300,TO_TIMESTAMP('2009-09-02 01:52:23','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE07','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','M_Product_UpdatedBy',311,TO_TIMESTAMP('2009-09-02 01:52:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50301 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50302,50000,0,620,0,10,50301,TO_TIMESTAMP('2009-09-02 01:52:24','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE07','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','M_Product_Value',312,TO_TIMESTAMP('2009-09-02 01:52:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:24 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50302 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50303,50000,0,627,0,12,50303,TO_TIMESTAMP('2009-09-02 01:52:24','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product','EE07','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Y','N','N','N','N','M_Product_Volume',313,TO_TIMESTAMP('2009-09-02 01:52:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50303 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50304,50000,0,629,0,12,50304,TO_TIMESTAMP('2009-09-02 01:52:25','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product','EE07','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Y','N','N','N','N','M_Product_Weight',314,TO_TIMESTAMP('2009-09-02 01:52:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:25 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50304 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50305,50000,0,102,0,19,50305,TO_TIMESTAMP('2009-09-02 01:52:25','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','M_Product_Category_AD_Client_I',315,TO_TIMESTAMP('2009-09-02 01:52:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50305 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50306,50000,0,113,0,19,50306,TO_TIMESTAMP('2009-09-02 01:52:26','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','M_Product_Category_AD_Org_ID',316,TO_TIMESTAMP('2009-09-02 01:52:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50306 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50307,50000,0,1788,0,19,50307,TO_TIMESTAMP('2009-09-02 01:52:26','YYYY-MM-DD HH24:MI:SS'),0,'Color used for printing and display','EE07','Colors used for printing and display','Y','Y','N','N','N','N','M_Product_Category_AD_PrintCol',317,TO_TIMESTAMP('2009-09-02 01:52:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:26 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50307 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50308,50000,0,1929,0,19,50308,TO_TIMESTAMP('2009-09-02 01:52:26','YYYY-MM-DD HH24:MI:SS'),0,'Group of Assets','EE07','The group of assets determines default accounts.  If an asset group is selected in the product category, assets are created when delivering the asset.','Y','Y','N','N','N','N','M_Product_Category_A_Asset_Gro',318,TO_TIMESTAMP('2009-09-02 01:52:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50308 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50309,50000,0,245,0,16,50309,TO_TIMESTAMP('2009-09-02 01:52:27','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE07','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','M_Product_Category_Created',319,TO_TIMESTAMP('2009-09-02 01:52:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:27 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50309 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50310,50000,0,246,0,18,50310,TO_TIMESTAMP('2009-09-02 01:52:27','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE07','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','M_Product_Category_CreatedBy',320,TO_TIMESTAMP('2009-09-02 01:52:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50310 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50311,50000,0,275,0,10,50311,TO_TIMESTAMP('2009-09-02 01:52:28','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07','A description is limited to 255 characters.','Y','Y','N','N','N','N','M_Product_Category_Description',321,TO_TIMESTAMP('2009-09-02 01:52:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:28 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50311 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50312,50000,0,348,0,20,50312,TO_TIMESTAMP('2009-09-02 01:52:28','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','M_Product_Category_IsActive',322,TO_TIMESTAMP('2009-09-02 01:52:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50312 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50313,50000,0,1103,0,20,50313,TO_TIMESTAMP('2009-09-02 01:52:29','YYYY-MM-DD HH24:MI:SS'),0,'Default value','EE07','The Default Checkbox indicates if this record will be used as a default value.','Y','Y','N','N','N','N','M_Product_Category_IsDefault',323,TO_TIMESTAMP('2009-09-02 01:52:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:29 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50313 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50314,50000,0,2063,0,20,50314,TO_TIMESTAMP('2009-09-02 01:52:29','YYYY-MM-DD HH24:MI:SS'),0,'This is a Self-Service entry or this entry can be changed via Self-Service','EE07','Self-Service allows users to enter data or update their data.  The flag indicates, that this record was entered or created via Self-Service or that the user can change it via the Self-Service functionality.','Y','Y','N','N','N','N','M_Product_Category_IsSelfServi',324,TO_TIMESTAMP('2009-09-02 01:52:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50314 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50315,50000,0,2685,0,17,50315,TO_TIMESTAMP('2009-09-02 01:52:30','YYYY-MM-DD HH24:MI:SS'),0,'Material Movement Policy','EE07','The Material Movement Policy determines how the stock is flowing (FiFo or LiFo) if a specific Product Instance was not selected.  The policy can not contradict the costing method (e.g. FiFo movement policy and LiFo costing method).','Y','Y','N','N','N','N','M_Product_Category_MMPolicy',325,TO_TIMESTAMP('2009-09-02 01:52:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:30 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50315 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50316,50000,0,453,0,13,50316,TO_TIMESTAMP('2009-09-02 01:52:30','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','EE07','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','N','N','N','N','M_Product_Category_M_Product_C',326,TO_TIMESTAMP('2009-09-02 01:52:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50316 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50317,50000,0,50070,0,18,50317,TO_TIMESTAMP('2009-09-02 01:52:31','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Y','N','N','N','N','M_Product_Category_M_Product_C',327,TO_TIMESTAMP('2009-09-02 01:52:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:31 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50317 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:32 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50318,50000,0,469,0,10,50318,TO_TIMESTAMP('2009-09-02 01:52:31','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','M_Product_Category_Name',328,TO_TIMESTAMP('2009-09-02 01:52:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:32 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50318 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:32 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50319,50000,0,1565,0,22,50319,TO_TIMESTAMP('2009-09-02 01:52:32','YYYY-MM-DD HH24:MI:SS'),0,'Project''s planned margin as a percentage','EE07','The Planned Margin Percentage indicates the anticipated margin percentage for this project or project line','Y','Y','N','N','N','N','M_Product_Category_PlannedMarg',329,TO_TIMESTAMP('2009-09-02 01:52:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:32 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50319 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50320,50000,0,607,0,16,50320,TO_TIMESTAMP('2009-09-02 01:52:32','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE07','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','M_Product_Category_Updated',330,TO_TIMESTAMP('2009-09-02 01:52:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50320 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50321,50000,0,608,0,18,50321,TO_TIMESTAMP('2009-09-02 01:52:33','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE07','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','M_Product_Category_UpdatedBy',331,TO_TIMESTAMP('2009-09-02 01:52:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:33 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50321 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 1:52:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50322,50000,0,620,0,10,50322,TO_TIMESTAMP('2009-09-02 01:52:33','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE07','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','N','N','N','N','M_Product_Category_Value',332,TO_TIMESTAMP('2009-09-02 01:52:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 2, 2009 1:52:34 AM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50322 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50140
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50141
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50144
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50145
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50146
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50147
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50148
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50149
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50150
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50151
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50152
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50153
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50154
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50155
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50156
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50157
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50158
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50159
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50160
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50161
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50162
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50163
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50164
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50165
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50166
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50167
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50168
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50169
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50142
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50170
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50171
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50172
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50173
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50174
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50175
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50176
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50177
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50207
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50208
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50210
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50209
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50211
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50212
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50213
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50214
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50215
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50216
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50217
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50218
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50219
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50220
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50221
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50222
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50223
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50224
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50225
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50226
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50178
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50179
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50180
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50182
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50183
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50184
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50185
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50186
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50187
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50188
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50189
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50190
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50191
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50192
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50193
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50195
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50113
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50196
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50197
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50198
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50199
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50200
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50201
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50202
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50203
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50204
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50205
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50206
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50227
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50228
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50229
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50230
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50231
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50232
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50233
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50235
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50236
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50237
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50238
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50239
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50240
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50242
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50243
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50244
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50245
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50083
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50084
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50085
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50086
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50087
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50088
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50089
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50090
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50091
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50092
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50093
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50094
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50095
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50096
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50097
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50098
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50099
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50100
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50101
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50102
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50103
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50104
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50105
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50106
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50107
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50108
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50109
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50111
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50112
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50114
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50116
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50117
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50118
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50119
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50120
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50121
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50122
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50123
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50124
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50125
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50126
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50127
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50128
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50129
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50130
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50131
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50132
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50133
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50134
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50135
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50136
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50137
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50138
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50139
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50034
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50065
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50024
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50032
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50073
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50072
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50025
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50059
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50057
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50053
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50049
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50015
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50056
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50002
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50066
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50018
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50017
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50074
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50016
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50026
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50035
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50039
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50058
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50014
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50040
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50012
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50051
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50048
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50005
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50037
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50021
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50023
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50078
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50079
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50080
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50004
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50055
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50020
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50003
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50011
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50006
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50007
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50008
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50043
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50081
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50009
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50029
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50052
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50047
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50062
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50061
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50041
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50075
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50077
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50070
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50071
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50036
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50027
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50064
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50076
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50050
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50060
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50044
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50082
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50067
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50013
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50054
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50019
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50045
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50000
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50031
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50063
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50068
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50246
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50247
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50248
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50249
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50250
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50251
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50305
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50306
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50307
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50308
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50309
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50310
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50311
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50312
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50313
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50314
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50315
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50317
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50316
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50319
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50320
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50321
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50322
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50252
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50253
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50254
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50255
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50256
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50257
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50258
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50259
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50260
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50261
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50262
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50263
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50264
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50265
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50266
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50267
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50268
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50269
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50270
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50271
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50272
;

-- Sep 2, 2009 2:01:11 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50273
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50274
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50275
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50276
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50277
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50278
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50279
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50280
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50281
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50282
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50283
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50285
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50287
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50288
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50289
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50290
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50291
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50292
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50293
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50294
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50295
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50296
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50297
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50298
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50299
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50300
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50301
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50302
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50143
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50303
;

-- Sep 2, 2009 2:01:12 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50304
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50010
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50001
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50022
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50046
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50030
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50042
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50033
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50234
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50241
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50115
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50284
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50038
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50028
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50069
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50181
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50194
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50286
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50318
;

-- Sep 2, 2009 2:01:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50110
;

-- Sep 2, 2009 2:01:44 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:01:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50001
;

-- Sep 2, 2009 2:01:52 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:01:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50010
;

-- Sep 2, 2009 2:02:04 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:02:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50022
;

-- Sep 2, 2009 2:02:16 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:02:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50030
;

-- Sep 2, 2009 2:02:21 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:02:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50033
;

-- Sep 2, 2009 2:02:26 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:02:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50038
;

-- Sep 2, 2009 2:02:32 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y', IsRange='Y',Updated=TO_TIMESTAMP('2009-09-02 02:02:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50042
;

-- Sep 2, 2009 2:02:39 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y', IsRange='Y',Updated=TO_TIMESTAMP('2009-09-02 02:02:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50046
;

-- Sep 2, 2009 2:02:51 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:02:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50115
;

-- Sep 2, 2009 2:03:00 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:03:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50234
;

-- Sep 2, 2009 2:03:05 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2009-09-02 02:03:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50241
;

-- Sep 2, 2009 2:03:15 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Name='M_Product_M_Product_Category_ID',Updated=TO_TIMESTAMP('2009-09-02 02:03:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50284
;

-- Sep 2, 2009 2:03:15 AM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50284
;

