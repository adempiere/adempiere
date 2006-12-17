CREATE OR REPLACE VIEW RV_UNPOSTED
(AD_CLIENT_ID, AD_ORG_ID, CREATED, CREATEDBY, UPDATED, 
 UPDATEDBY, ISACTIVE, DOCUMENTNO, DATEDOC, DATEACCT, 
 AD_TABLE_ID, RECORD_ID, ISSOTRX)
AS 
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, DateDoc, DateAcct, 224 AS AD_Table_ID, GL_Journal_ID AS Record_ID, 'N' AS IsSOTrx
FROM GL_Journal  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT pi.AD_Client_ID, pi.AD_Org_ID, pi.Created, pi.CreatedBy, pi.Updated, pi.UpdatedBy, pi. IsActive,
    p.Name || '_' || pi.Line, pi.MovementDate, pi.MovementDate, 623, pi.C_ProjectIssue_ID, 'N' 
FROM C_ProjectIssue pi INNER JOIN C_Project p ON (pi.C_Project_ID=p.C_Project_ID)  
WHERE Posted<>'Y' --AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, DateInvoiced, DateAcct, 318, C_Invoice_ID, IsSOTrx
FROM C_Invoice  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, DateAcct, 319, M_InOut_ID, IsSOTrx 
FROM M_InOut  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, MovementDate, 321, M_Inventory_ID, 'N' 
FROM M_Inventory  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, MovementDate, 323, M_Movement_ID, 'N' 
FROM M_Movement  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    Name, MovementDate, MovementDate, 325, M_Production_ID, 'N'
FROM M_Production  WHERE Posted<>'Y' -- AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    Name, StatementDate, DateAcct, 407, C_Cash_ID, 'N' 
FROM C_Cash  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 335, C_Payment_ID, 'N'
FROM C_Payment  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 735, C_AllocationHdr_ID, 'N' 
FROM C_AllocationHdr  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    Name, StatementDate, StatementDate, 392, C_BankStatement_ID, 'N' 
FROM C_BankStatement  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 472, M_MatchInv_ID, 'N' 
FROM M_MatchInv  WHERE Posted<>'Y' --AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 473, M_MatchPO_ID, 'N'
FROM M_MatchPO  WHERE Posted<>'Y' --AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateOrdered, DateAcct, 259, C_Order_ID, IsSOTrx
FROM C_Order  WHERE Posted<>'Y' AND DocStatus<>'VO'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateRequired, DateRequired, 702, M_Requisition_ID, 'N'
FROM M_Requisition  WHERE Posted<>'Y' AND DocStatus<>'VO';



