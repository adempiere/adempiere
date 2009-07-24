CREATE OR REPLACE VIEW RV_UNPROCESSED
(
   AD_CLIENT_ID,
   AD_ORG_ID,
   CREATED,
   CREATEDBY,
   UPDATED,
   UPDATEDBY,
   ISACTIVE,
   DOCUMENTNO,
   DATEDOC,
   DATEACCT,
   AD_TABLE_ID,
   RECORD_ID,
   ISSOTRX,
   POSTED,
   PROCESSING,
   PROCESSED,
   DOCSTATUS
)
AS
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            DateDoc,
            DateAcct,
            224 AS AD_Table_ID,
            GL_Journal_ID AS Record_ID,
            'N' AS IsSOTrx,
            posted,
            processing,
            processed,
            docstatus
     FROM   GL_JOURNAL
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   /*SELECT   pi.AD_Client_ID,
            pi.AD_Org_ID,
            pi.Created,
            pi.CreatedBy,
            pi.Updated,
            pi.UpdatedBy,
            pi.IsActive,
            p.NAME || '_' || pi.Line,
            pi.MovementDate,
            pi.MovementDate,
            623,
            pi.C_ProjectIssue_ID,
            'N',
            posted,
            pi.processing,
            pi.processed,
            'CO' AS DocStatus
     FROM      C_PROJECTISSUE pi
            INNER JOIN
               C_PROJECT p
            ON (pi.C_Project_ID = p.C_Project_ID)
    WHERE   Posted <> 'Y'                                --AND DocStatus<>'VO'
   UNION*/
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            DateInvoiced,
            DateAcct,
            318,
            C_Invoice_ID,
            IsSOTrx,
            posted,
            processing,
            processed,
            docstatus
     FROM   C_INVOICE
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            MovementDate,
            DateAcct,
            319,
            M_InOut_ID,
            IsSOTrx,
            posted,
            processing,
            processed,
            docstatus
     FROM   M_INOUT
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            MovementDate,
            MovementDate,
            321,
            M_Inventory_ID,
            'N',
            posted,
            processing,
            processed,
            docstatus
     FROM   M_INVENTORY
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            MovementDate,
            MovementDate,
            323,
            M_Movement_ID,
            'N',
            posted,
            processing,
            processed,
            docstatus
     FROM   M_MOVEMENT
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   /*SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            NAME,
            MovementDate,
            MovementDate,
            325,
            M_Production_ID,
            'N',
            posted,
            processing,
            processed,
            'CO' AS docstatus
     FROM   M_PRODUCTION
    WHERE   Posted <> 'Y'                               -- AND DocStatus<>'VO'
   UNION*/
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            NAME,
            StatementDate,
            DateAcct,
            407,
            C_Cash_ID,
            'N',
            posted,
            processing,
            processed,
            docstatus
     FROM   C_CASH
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            DateTrx,
            DateTrx,
            335,
            C_Payment_ID,
            'N',
            posted,
            processing,
            processed,
            docstatus
     FROM   C_PAYMENT
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            DateTrx,
            DateTrx,
            735,
            C_AllocationHdr_ID,
            'N',
            posted,
            processing,
            processed,
            docstatus
     FROM   C_ALLOCATIONHDR
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            NAME,
            StatementDate,
            StatementDate,
            392,
            C_BankStatement_ID,
            'N',
            posted,
            processing,
            processed,
            docstatus
     FROM   C_BANKSTATEMENT
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   /*SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            DateTrx,
            DateTrx,
            472,
            M_MatchInv_ID,
            'N',
            posted,
            processing,
            processed,
            'CO' AS docstatus
     FROM   M_MATCHINV
    WHERE   Posted <> 'Y'                                --AND DocStatus<>'VO'
   UNION*/
   /*SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            DateTrx,
            DateTrx,
            473,
            M_MatchPO_ID,
            'N',
            posted,
            processing,
            processed,
            'CO' AS docstatus
     FROM   M_MATCHPO
    WHERE   Posted <> 'Y'                                --AND DocStatus<>'VO'
   UNION*/
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            DateOrdered,
            DateAcct,
            259,
            C_Order_ID,
            IsSOTrx,
            posted,
            processing,
            processed,
            docstatus
     FROM   C_ORDER
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE')
   UNION
   SELECT   AD_Client_ID,
            AD_Org_ID,
            Created,
            CreatedBy,
            Updated,
            UpdatedBy,
            IsActive,
            DocumentNo,
            DateRequired,
            DateRequired,
            702,
            M_Requisition_ID,
            'N',
            posted,
            processing,
            processed,
            docstatus
     FROM   M_REQUISITION
    WHERE   DocStatus NOT IN ('CO','CL','VO','RE');
