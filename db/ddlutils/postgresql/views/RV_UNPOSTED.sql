DROP VIEW RV_UnPosted;
CREATE OR REPLACE VIEW RV_UnPosted
AS
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateDoc, DateAcct, 224 AS AD_Table_ID,
          GL_Journal_ID AS Record_ID, 'N' AS IsSOTrx, posted, processing,
          processed, docstatus, processedon , ControlAmt AS Amount , NULL AS C_BPartner_ID, C_DocType_ID
     from GL_JOURNAL
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT pi.AD_Client_ID, pi.AD_Org_ID, pi.Created, pi.CreatedBy, pi.Updated,
          pi.UpdatedBy, pi.IsActive, p.NAME || '_' || pi.Line,
          pi.MovementDate, pi.MovementDate, 623, pi.C_ProjectIssue_ID, 'N',
          posted, pi.processing, pi.processed, 'CO' as DocStatus, processedon , 0.0 AS Amount ,  C_BPartner_ID , NULL AS C_DocType_ID
     from C_PROJECTISSUE pi INNER JOIN C_PROJECT p
          ON (pi.C_Project_ID = p.C_Project_ID)
    WHERE Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateInvoiced, DateAcct, 318, C_Invoice_ID,
          IsSOTrx, posted, processing, processed, docstatus, processedon,
          GrandTotal AS Amount , C_BPartner_ID , C_DocType_ID
     from C_INVOICE
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, DateAcct, 319, M_InOut_ID,
          IsSOTrx, posted, processing, processed, docstatus, processedon ,
          0.0 AS Amount , C_BPartner_ID , C_DocType_ID
     from M_INOUT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, MovementDate, 321,
          M_Inventory_ID, 'N', posted, processing, processed, docstatus, processedon ,
          0.0 AS Amount , NULL AS C_BPartner_ID , C_DocType_ID
     from M_INVENTORY
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, MovementDate, MovementDate, 323,
          M_Movement_ID, 'N', posted, processing, processed, docstatus, processedon ,
          0.0 AS Amount ,  C_BPartner_ID , C_DocType_ID
     from M_MOVEMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, MovementDate, MovementDate, 325, M_Production_ID,
          'N', posted, processing, processed, 'CO' as docstatus, processedon ,
          0.0 AS Amount , NULL AS C_BPartner_ID , NULL AS C_DocType_ID
     from M_PRODUCTION
    WHERE Posted <> 'Y'                                 -- AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, StatementDate, DateAcct, 407, C_Cash_ID, 'N',
          posted, processing, processed, docstatus, processedon ,
          BeginningBalance - EndingBalance  AS Amount , NULL AS C_BPartner_ID , NULL AS C_DocType_ID
     from C_CASH
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateAcct, 335, C_Payment_ID, 'N',
          posted, processing, processed, docstatus, processedon ,
          payamt AS Amount , C_BPartner_ID , C_DocType_ID
     from C_PAYMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateTrx, DateAcct, 735, C_AllocationHdr_ID,
          'N', posted, processing, processed, docstatus, processedon ,
          ApprovalAmt AS Amount , NULL AS C_BPartner_ID , NULL AS C_DocType_ID
     from C_ALLOCATIONHDR
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, NAME, StatementDate, StatementDate, 392,
          C_BankStatement_ID, 'N', posted, processing, processed, docstatus, processedon ,
          BeginningBalance - EndingBalance AS Amount , NULL AS C_BPartner_ID , NULL AS C_DocType_ID
     from C_BANKSTATEMENT
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT mi.AD_Client_ID, mi.AD_Org_ID, mi.Created, mi.CreatedBy, mi.Updated, mi.UpdatedBy,
          mi.IsActive, mi.DocumentNo, mi.DateTrx, mi.DateAcct, 472, mi.M_MatchInv_ID, 'N',
          mi.posted, mi.processing, mi.processed, 'CO' as docstatus, mi.processedon ,
          il.LineTotalAmt AS Amount , i.C_BPartner_ID, NULL AS C_DocType_ID
     FROM  M_MATCHINV mi
     LEFT JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID)
     LEFT JOIN C_Invoice i ON (il.C_Invoice_ID = i.C_Invoice_ID)
    WHERE mi.Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT po.AD_Client_ID, po.AD_Org_ID, po.Created, po.CreatedBy, po.Updated, po.UpdatedBy,
          po.IsActive, po.DocumentNo, po.DateTrx, po.DateAcct, 473, M_MatchPO_ID, 'N',
          po.posted, po.processing, po.processed, 'CO' as docstatus, po.processedon,
          0.0 AS Amount , NULL AS C_BPartner_ID , NULL AS C_DocType_ID
     FROM M_MATCHPO po
     LEFT JOIN C_OrderLine ol ON (po.C_OrderLine_ID=ol.C_OrderLine_ID)
     LEFT JOIN C_ORDER o ON (ol.C_Order_ID = o.C_Order_ID)
    WHERE po.Posted <> 'Y'                                  --AND DocStatus<>'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateOrdered, DateAcct, 259, C_Order_ID,
          IsSOTrx, posted, processing, processed, docstatus, processedon,
          GrandTotal AS Amount ,  C_BPartner_ID , C_DocType_ID
     from C_ORDER
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
   UNION
   SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
          IsActive, DocumentNo, DateRequired, DateRequired, 702,
          M_Requisition_ID, 'N' AS IsSOTrx, posted, processing, processed, docstatus, processedon ,
          TotalLines AS Amount, NULL AS C_BPartner_ID , C_DocType_ID
     from M_REQUISITION
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
  UNION
   SELECT AD_Client_ID , AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
      IsActive, DocumentNo,  DateAcct, DateAcct, 53092, HR_Process_ID ,'N' AS IsSOTrx, posted, Processing, Processed, CAST (DocStatus AS CHAR(2)) ,  processedon ,
      0.0 AS Amount , C_BPartner_ID , C_DocType_ID
    FROM HR_Process
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
 UNION
  SELECT AD_Client_ID , AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
      IsActive, DocumentNo,  DateAcct, DateAcct, 53137, A_Asset_Addition_ID ,'N' AS IsSOTrx, posted, Processing, Processed, CAST (DocStatus AS CHAR(2)),  processedon ,
      AssetValueAmt AS Amount , (SELECT C_BPartner_ID FROM C_Invoice i WHERE i.C_Invoice_ID = A_Asset_Addition.C_Invoice_ID) AS  C_BPartner_ID , C_DocType_ID
  FROM A_Asset_Addition
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
 UNION
  SELECT AD_Client_ID , AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
      IsActive, DocumentNo,  DateAcct, DateAcct, 53121, A_Depreciation_Entry_ID ,'N' AS IsSOTrx, posted, Processing, Processed, CAST (DocStatus AS CHAR(2)),  processedon ,
      NULL AS Amount , NULL AS  C_BPartner_ID, C_DocType_ID
  FROM A_Depreciation_Entry
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
  UNION
  SELECT AD_Client_ID , AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
      IsActive, DocumentNo,  DateAcct, DateAcct, 53127, A_Asset_Disposed_ID ,'N' AS IsSOTrx, posted, Processing, Processed, CAST (DocStatus AS CHAR(2)),  processedon ,
      A_Disposal_Amt AS Amount , (SELECT C_BPartner_ID FROM C_Invoice i WHERE i.C_Invoice_ID = A_Asset_Disposed.C_Invoice_ID) AS  C_BPartner_ID, C_DocType_ID
  FROM A_Asset_Disposed
    WHERE Posted <> 'Y' AND DocStatus <> 'VO'
  UNION
  SELECT AD_Client_ID , AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy,
      IsActive, DocumentNo,  DateAcct, DateAcct, 53035, PP_Cost_Collector_ID ,'N' AS IsSOTrx, posted, Processing, Processed, CAST (DocStatus AS CHAR(2)),  processedon ,
      Null AS Amount , NULL AS  C_BPartner_ID, C_DocType_ID
  FROM PP_Cost_Collector
    WHERE Posted <> 'Y' AND DocStatus <> 'VO' AND CostCollectorType <> '160';