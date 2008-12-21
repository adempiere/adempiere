-- Sep 17, 2008 5:24:31 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56347,1308,0,20,635,'Posted',TO_DATE('2008-09-17 17:24:29','YYYY-MM-DD HH24:MI:SS'),100,'Posting status','U',1,'The Posted field indicates the status of the Generation of General Ledger Accounting Lines ','Y','N','N','N','N','N','N','N','N','N','Posted',TO_DATE('2008-09-17 17:24:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2008 5:24:31 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56347 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 17, 2008 5:24:31 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56348,524,0,20,635,'Processing',TO_DATE('2008-09-17 17:24:31','YYYY-MM-DD HH24:MI:SS'),100,'U',1,'Y','N','N','N','N','N','N','N','N','N','Process Now',TO_DATE('2008-09-17 17:24:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2008 5:24:31 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56348 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 17, 2008 5:24:32 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56349,1047,0,20,635,'Processed',TO_DATE('2008-09-17 17:24:31','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed','U',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','N','N','N','N','N','Processed',TO_DATE('2008-09-17 17:24:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2008 5:24:32 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56349 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 17, 2008 5:24:32 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56350,289,0,14,635,'DocStatus',TO_DATE('2008-09-17 17:24:32','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document','U',2147483647,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','N','N','Document Status',TO_DATE('2008-09-17 17:24:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 17, 2008 5:24:32 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56350 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 17, 2008 5:24:46 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-17 17:24:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56347
;

-- Sep 17, 2008 5:24:54 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-17 17:24:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56349
;

-- Sep 17, 2008 5:24:58 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-17 17:24:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56348
;

-- Sep 17, 2008 5:25:04 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-17 17:25:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56350
;

-- Sep 17, 2008 5:25:31 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56350,56369,0,662,TO_DATE('2008-09-17 17:25:30','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document',2147483647,'D','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','Y','N','N','N','N','N','Document Status',TO_DATE('2008-09-17 17:25:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2008 5:25:31 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56369 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 17, 2008 5:25:31 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56347,56370,0,662,TO_DATE('2008-09-17 17:25:31','YYYY-MM-DD HH24:MI:SS'),100,'Posting status',1,'D','The Posted field indicates the status of the Generation of General Ledger Accounting Lines ','Y','Y','Y','N','N','N','N','N','Posted',TO_DATE('2008-09-17 17:25:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2008 5:25:31 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56370 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 17, 2008 5:25:32 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56349,56371,0,662,TO_DATE('2008-09-17 17:25:31','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',1,'D','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','N','N','N','Processed',TO_DATE('2008-09-17 17:25:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2008 5:25:32 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56371 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 17, 2008 5:25:33 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56348,56372,0,662,TO_DATE('2008-09-17 17:25:32','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Process Now',TO_DATE('2008-09-17 17:25:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 17, 2008 5:25:33 PM COT
-- Improvements on RV_Unposted
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56372 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 17, 2008 5:26:13 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-09-17 17:26:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56372
;

-- Sep 17, 2008 5:26:19 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-09-17 17:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56369
;

-- Sep 17, 2008 5:29:05 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=131, FieldLength=2,Updated=TO_DATE('2008-09-17 17:29:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56350
;

-- Sep 17, 2008 5:29:35 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2008-09-17 17:29:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56370
;

-- Sep 17, 2008 5:29:36 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2008-09-17 17:29:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56372
;

-- Sep 17, 2008 5:29:38 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2008-09-17 17:29:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56371
;

-- Sep 17, 2008 5:29:40 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2008-09-17 17:29:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56369
;

-- Sep 17, 2008 5:30:52 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56370
;

-- Sep 17, 2008 5:30:52 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56372
;

-- Sep 17, 2008 5:30:52 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56369
;

-- Sep 17, 2008 5:30:52 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56371
;

-- Sep 17, 2008 5:31:01 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET DisplayLength=1,Updated=TO_DATE('2008-09-17 17:31:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56369
;

drop view rv_unposted;

create or replace view rv_unposted
as
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateDoc, DateAcct, 224 AS AD_Table_ID,
          GL_Journal_ID AS Record_ID, 'N' AS IsSOTrx, posted, processing,
          processed, docstatus
     from GL_JOURNAL
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT pi.AD_Client_ID, pi.AD_Org_ID, pi.Created, pi.CreatedBy, pi.Updated,
          pi.UpdatedBy, pi.IsActive, p.NAME || '_' || pi.Line,
          pi.MovementDate, pi.MovementDate, 623, pi.C_ProjectIssue_ID, 'N',
          posted, pi.processing, pi.processed, 'CO' as DocStatus
     from C_PROJECTISSUE pi INNER JOIN C_PROJECT p
          ON (pi.C_Project_ID = p.C_Project_ID)
    WHERE Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateInvoiced, DateAcct, 318, C_Invoice_ID,
          IsSOTrx, posted, processing, processed, docstatus
     from C_INVOICE
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, DateAcct, 319, M_InOut_ID,
          IsSOTrx, posted, processing, processed, docstatus
     from M_INOUT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, MovementDate, 321,
          M_Inventory_ID, 'N', posted, processing, processed, docstatus
     from M_INVENTORY
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, MovementDate, 323,
          M_Movement_ID, 'N', posted, processing, processed, docstatus
     from M_MOVEMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, MovementDate, MovementDate, 325, M_Production_ID,
          'N', posted, processing, processed, 'CO' as docstatus
     from M_PRODUCTION
    WHERE Posted <> 'Y'                                 -- AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, StatementDate, DateAcct, 407, C_Cash_ID, 'N',
          posted, processing, processed, docstatus
     from C_CASH
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateTrx, 335, C_Payment_ID, 'N',
          posted, processing, processed, docstatus
     from C_PAYMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateTrx, 735, C_AllocationHdr_ID,
          'N', posted, processing, processed, docstatus
     from C_ALLOCATIONHDR
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, StatementDate, StatementDate, 392,
          C_BankStatement_ID, 'N', posted, processing, processed, docstatus
     from C_BANKSTATEMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateTrx, 472, M_MatchInv_ID, 'N',
          posted, processing, processed, 'CO' as docstatus
     from M_MATCHINV
    WHERE Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateTrx, 473, M_MatchPO_ID, 'N',
          posted, processing, processed, 'CO' as docstatus
     from M_MATCHPO
    WHERE Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateOrdered, DateAcct, 259, C_Order_ID,
          IsSOTrx, posted, processing, processed, docstatus
     from C_ORDER
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateRequired, DateRequired, 702,
          M_Requisition_ID, 'N', posted, processing, processed, docstatus
     from M_REQUISITION
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
;

-- Sep 17, 2008 5:35:27 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-09-17 17:35:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56369
;

-- Sep 17, 2008 5:35:29 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-09-17 17:35:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56371
;

-- Sep 17, 2008 5:39:38 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=234,Updated=TO_DATE('2008-09-17 17:39:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56347
;

-- Sep 17, 2008 5:40:00 PM COT
-- Improvements on RV_Unposted
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56370
;

