/**
 * Example View DDL SQL
 *
 * @author 	Jorg Janke
 * @version	$Id: XX_View.sql,v 1.1 2006/04/21 17:55:19 jjanke Exp $
 */

--	Inspect data
SELECT C_Invoice_ID,
  AD_Client_ID,AD_Org_ID, IsActive, Created,CreatedBy, Updated,UpdatedBy,
  IsSOTrx, DocumentNo, DocStatus, DocAction, Processed,
  SalesRep_ID, DateInvoiced, DatePrinted, DateAcct,
  C_BPartner_ID, C_BPartner_Location_ID, AD_User_ID,
  C_Currency_ID,
  TotalLines, GrandTotal,
  Multiplier, MultiplierAP, DocBaseType
FROM C_Invoice_V
/

--	The view
CREATE OR REPLACE VIEW XX_BPartner_Total
AS
SELECT AD_Client_ID,AD_Org_ID, 
  'Y' AS IsActive, SysDate AS Created, 0 AS CreatedBy, SysDate AS Updated, 0 AS UpdatedBy,
  C_BPartner_ID, C_BPartner_Location_ID, AD_User_ID,
  C_Currency_ID,
  SUM(InvoiceOpen (C_Invoice_ID, 0) * MultiplierAP) AS OpenAmount
FROM C_Invoice_V
WHERE Processed='Y' 
  AND IsPaid='N' 
GROUP BY AD_Client_ID,AD_Org_ID, 
  C_BPartner_ID, C_BPartner_Location_ID, AD_User_ID,
  C_Currency_ID
/
