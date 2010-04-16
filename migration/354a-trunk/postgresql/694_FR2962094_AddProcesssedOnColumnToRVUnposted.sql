-- Mar 23, 2010 12:44:24 PM COT
-- FR_2962094_Finish implementation of weighted average costing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59133,54128,0,22,635,'ProcessedOn',TO_TIMESTAMP('2010-03-23 12:44:23','YYYY-MM-DD HH24:MI:SS'),100,'The date+time (expressed in decimal format) when the document has been processed','D',20,'The ProcessedOn Date+Time save the exact moment (nanoseconds precision if allowed by the DB) when a document has been processed.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Processed On',0,TO_TIMESTAMP('2010-03-23 12:44:23','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 23, 2010 12:44:24 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59133 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 23, 2010 12:45:03 PM COT
UPDATE AD_Tab SET OrderByClause='ProcessedOn',Updated=TO_TIMESTAMP('2010-03-23 12:45:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=662
;

-- Mar 23, 2010 12:45:41 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59133,58842,0,662,TO_TIMESTAMP('2010-03-23 12:45:39','YYYY-MM-DD HH24:MI:SS'),100,'The date+time (expressed in decimal format) when the document has been processed',0,'D','The ProcessedOn Date+Time save the exact moment (nanoseconds precision if allowed by the DB) when a document has been processed.','Y','Y','Y','N','N','N','Y','N','Processed On',120,0,TO_TIMESTAMP('2010-03-23 12:45:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 23, 2010 12:45:41 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58842 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

drop view rv_unposted;

create or replace view rv_unposted
as
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateDoc, DateAcct, 224 AS AD_Table_ID,
          GL_Journal_ID AS Record_ID, 'N' AS IsSOTrx, posted, processing,
          processed, docstatus, processedon
     from GL_JOURNAL
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT pi.AD_Client_ID, pi.AD_Org_ID, pi.Created, pi.CreatedBy, pi.Updated,
          pi.UpdatedBy, pi.IsActive, p.NAME || '_' || pi.Line,
          pi.MovementDate, pi.MovementDate, 623, pi.C_ProjectIssue_ID, 'N',
          posted, pi.processing, pi.processed, 'CO' as DocStatus, processedon
     from C_PROJECTISSUE pi INNER JOIN C_PROJECT p
          ON (pi.C_Project_ID = p.C_Project_ID)
    WHERE Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateInvoiced, DateAcct, 318, C_Invoice_ID,
          IsSOTrx, posted, processing, processed, docstatus, processedon
     from C_INVOICE
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, DateAcct, 319, M_InOut_ID,
          IsSOTrx, posted, processing, processed, docstatus, processedon
     from M_INOUT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, MovementDate, 321,
          M_Inventory_ID, 'N', posted, processing, processed, docstatus, processedon
     from M_INVENTORY
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, MovementDate, 323,
          M_Movement_ID, 'N', posted, processing, processed, docstatus, processedon
     from M_MOVEMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, MovementDate, MovementDate, 325, M_Production_ID,
          'N', posted, processing, processed, 'CO' as docstatus, processedon
     from M_PRODUCTION
    WHERE Posted <> 'Y'                                 -- AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, StatementDate, DateAcct, 407, C_Cash_ID, 'N',
          posted, processing, processed, docstatus, processedon
     from C_CASH
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateTrx, 335, C_Payment_ID, 'N',
          posted, processing, processed, docstatus, processedon
     from C_PAYMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateTrx, 735, C_AllocationHdr_ID,
          'N', posted, processing, processed, docstatus, processedon
     from C_ALLOCATIONHDR
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, StatementDate, StatementDate, 392,
          C_BankStatement_ID, 'N', posted, processing, processed, docstatus, processedon
     from C_BANKSTATEMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateTrx, 472, M_MatchInv_ID, 'N',
          posted, processing, processed, 'CO' as docstatus, processedon
     from M_MATCHINV
    WHERE Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateTrx, 473, M_MatchPO_ID, 'N',
          posted, processing, processed, 'CO' as docstatus, processedon
     from M_MATCHPO
    WHERE Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateOrdered, DateAcct, 259, C_Order_ID,
          IsSOTrx, posted, processing, processed, docstatus, processedon
     from C_ORDER
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateRequired, DateRequired, 702,
          M_Requisition_ID, 'N', posted, processing, processed, docstatus, processedon
     from M_REQUISITION
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
;
