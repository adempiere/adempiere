 /* upgrading database from compiere 253a to 253b 
 Written by Karsten Thiemann, kt@schaeffer-ag.de
 Feel free to change everything you want, but you are using it at your own risk!
 
 Creates new 253b views, recreates views with differences between a and b.
 If you changed any of these views, make a backup (via toad or sqldeveloper) and reapply your changes later.
 */


  CREATE OR REPLACE FORCE VIEW "T_INVOICEGL_V" ("AD_CLIENT_ID", "AD_ORG_ID", "ISACTIVE", "CREATED", "CREATEDBY", "UPDATED", "UPDATEDBY", "C_INVOICE_ID", "ISSOTRX", "DOCUMENTNO", "DOCSTATUS", "C_DOCTYPE_ID", "C_ORDER_ID", "DESCRIPTION", "SALESREP_ID", "DATEINVOICED", "DATEACCT", "C_PAYMENTTERM_ID", "C_BPARTNER_ID", "C_BPARTNER_LOCATION_ID", "AD_USER_ID", "ISSELFSERVICE", "C_CURRENCY_ID", "C_CONVERSIONTYPE_ID", "GRANDTOTAL", "ISTAXINCLUDED", "C_CAMPAIGN_ID", "C_PROJECT_ID", "C_ACTIVITY_ID", "AD_ORGTRX_ID", "USER1_ID", "USER2_ID", "C_LOCFROM_ID", "C_LOCTO_ID", "C_SALESREGION_ID", "FACT_ACCT_ID", "C_ACCTSCHEMA_ID", "ACCOUNT_ID", "C_PERIOD_ID", "GL_CATEGORY_ID", "GL_BUDGET_ID", "C_TAX_ID", "M_LOCATOR_ID", "POSTINGTYPE", "AMTSOURCEDR", "AMTSOURCECR", "AMTACCTDR", "AMTACCTCR", "C_UOM_ID", "QTY", "AD_PINSTANCE_ID", "APAR", "OPENAMT", "PERCENT", "AMTREVALDR", "AMTREVALCR", "DATEREVAL", "C_CONVERSIONTYPEREVAL_ID", "AMTSOURCEBALANCE", "AMTACCTBALANCE", "C_DOCTYPEREVAL_ID", "AMTREVALDRDIFF", "AMTREVALCRDIFF", "ISALLCURRENCIES") AS 
  SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy, i.Updated,i.UpdatedBy,
    i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus, i.C_DocType_ID, i.C_Order_ID,
    i.Description, i.SalesRep_ID, i.DateInvoiced, i.DateAcct, i.C_PaymentTerm_ID,
    i.C_BPartner_ID, i.C_BPartner_Location_ID, i.AD_User_ID, i.IsSelfService,
    i.C_Currency_ID, i.C_ConversionType_ID, i.GrandTotal, i.IsTaxIncluded,
--  References
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID, 
    i.AD_OrgTrx_ID, i.User1_ID, i.User2_ID,
    fa.C_LocFrom_ID, fa.C_LocTo_ID, fa.C_SalesRegion_ID,
--  Accounting
    fa.Fact_Acct_ID, fa.C_AcctSchema_ID, fa.Account_ID, fa.C_Period_ID, fa.GL_Category_ID, fa.GL_Budget_ID,
    fa.C_Tax_ID, fa.M_Locator_ID,
    fa.PostingType, fa.AmtSourceDr, fa.AmtSourceCr, fa.AmtAcctDr, fa.AmtAcctCr,
    fa.C_UOM_ID, fa.Qty,
--  GL
    gl.AD_PInstance_ID, gl.APAR, gl.OpenAmt, gl.Percent,
    gl.AmtRevalDr, gl.AmtRevalCr, gl.DateReval, gl.C_ConversionTypeReval_ID,
    gl.AmtSourceBalance, gl.AmtAcctBalance, gl.C_DocTypeReval_ID,
    gl.AmtRevalDrDiff, gl.AmtRevalCrDiff, gl.IsAllCurrencies
FROM T_InvoiceGL gl
  INNER JOIN C_Invoice i ON (gl.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN Fact_Acct fa ON (gl.Fact_Acct_ID=fa.Fact_Acct_ID)
;

  CREATE OR REPLACE FORCE VIEW "T_INVOICEGL_VT" ("AD_CLIENT_ID", "AD_ORG_ID", "ISACTIVE", "CREATED", "CREATEDBY", "UPDATED", "UPDATEDBY", "C_INVOICE_ID", "ISSOTRX", "DOCUMENTNO", "DOCSTATUS", "C_DOCTYPE_ID", "C_ORDER_ID", "DESCRIPTION", "SALESREP_ID", "DATEINVOICED", "DATEACCT", "C_PAYMENTTERM_ID", "C_BPARTNER_ID", "C_BPARTNER_LOCATION_ID", "AD_USER_ID", "ISSELFSERVICE", "C_CURRENCY_ID", "C_CONVERSIONTYPE_ID", "GRANDTOTAL", "ISTAXINCLUDED", "C_CAMPAIGN_ID", "C_PROJECT_ID", "C_ACTIVITY_ID", "AD_ORGTRX_ID", "USER1_ID", "USER2_ID", "C_LOCFROM_ID", "C_LOCTO_ID", "C_SALESREGION_ID", "FACT_ACCT_ID", "C_ACCTSCHEMA_ID", "ACCOUNT_ID", "C_PERIOD_ID", "GL_CATEGORY_ID", "GL_BUDGET_ID", "C_TAX_ID", "M_LOCATOR_ID", "POSTINGTYPE", "AMTSOURCEDR", "AMTSOURCECR", "AMTACCTDR", "AMTACCTCR", "C_UOM_ID", "QTY", "AD_PINSTANCE_ID", "APAR", "OPENAMT", "PERCENT", "AMTREVALDR", "AMTREVALCR", "DATEREVAL", "C_CONVERSIONTYPEREVAL_ID", "AMTSOURCEBALANCE", "AMTACCTBALANCE", "C_DOCTYPEREVAL_ID", "AMTREVALDRDIFF", "AMTREVALCRDIFF", "ISALLCURRENCIES") AS 
  SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy, i.Updated,i.UpdatedBy,
    i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus, i.C_DocType_ID, i.C_Order_ID,
    i.Description, i.SalesRep_ID, i.DateInvoiced, i.DateAcct, i.C_PaymentTerm_ID,
    i.C_BPartner_ID, i.C_BPartner_Location_ID, i.AD_User_ID, i.IsSelfService,
    i.C_Currency_ID, i.C_ConversionType_ID, i.GrandTotal, i.IsTaxIncluded,
--  References
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID, 
    i.AD_OrgTrx_ID, i.User1_ID, i.User2_ID,
    fa.C_LocFrom_ID, fa.C_LocTo_ID, fa.C_SalesRegion_ID,
--  Accounting
    fa.Fact_Acct_ID, fa.C_AcctSchema_ID, fa.Account_ID, fa.C_Period_ID, fa.GL_Category_ID, fa.GL_Budget_ID,
    fa.C_Tax_ID, fa.M_Locator_ID,
    fa.PostingType, fa.AmtSourceDr, fa.AmtSourceCr, fa.AmtAcctDr, fa.AmtAcctCr,
    fa.C_UOM_ID, fa.Qty, 
--  GL
    gl.AD_PInstance_ID, gl.APAR, gl.OpenAmt, gl.Percent,
    gl.AmtRevalDr, gl.AmtRevalCr, gl.DateReval, gl.C_ConversionTypeReval_ID,
    gl.AmtSourceBalance, gl.AmtAcctBalance, gl.C_DocTypeReval_ID,
    gl.AmtRevalDrDiff, gl.AmtRevalCrDiff, gl.IsAllCurrencies
FROM T_InvoiceGL gl
  INNER JOIN C_Invoice i ON (gl.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN Fact_Acct fa ON (gl.Fact_Acct_ID=fa.Fact_Acct_ID)
;

  CREATE OR REPLACE FORCE VIEW "M_INOUTLINEMA_VT" ("AD_CLIENT_ID", "AD_ORG_ID", "ISACTIVE", "CREATED", "CREATEDBY", "UPDATED", "UPDATEDBY", "M_INOUT_ID", "M_INOUTLINE_ID", "LINE", "M_PRODUCT_ID", "M_ATTRIBUTESETINSTANCE_ID", "MOVEMENTQTY", "M_LOCATOR_ID") AS 
  SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_InOut_ID, m.M_InOutLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID
FROM M_InOutLineMA m INNER JOIN M_InOutLine l ON (m.M_InOutLine_ID=l.M_InOutLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_InOut_ID, M_InOutLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID
FROM M_InOutLine
 ;
 
   CREATE OR REPLACE FORCE VIEW "M_MOVEMENTLINEMA_VT" ("AD_CLIENT_ID", "AD_ORG_ID", "ISACTIVE", "CREATED", "CREATEDBY", "UPDATED", "UPDATEDBY", "M_MOVEMENT_ID", "M_MOVEMENTLINE_ID", "LINE", "M_PRODUCT_ID", "M_ATTRIBUTESETINSTANCE_ID", "MOVEMENTQTY", "M_LOCATOR_ID", "M_LOCATORTO_ID") AS 
   SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
     l.M_Movement_ID, m.M_MovementLine_ID, l.Line, l.M_Product_ID, 
     m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID, l.M_LocatorTo_ID
 FROM M_MovementLineMA m INNER JOIN M_MovementLine l ON (m.M_MovementLine_ID=l.M_MovementLine_ID)
 UNION
 SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
     M_Movement_ID, M_MovementLine_ID, Line, M_Product_ID, 
     M_AttributeSetInstance_ID, MovementQty, M_Locator_ID, M_LocatorTo_ID
 FROM M_MovementLine
  ;
 
   CREATE OR REPLACE FORCE VIEW "RV_C_INVOICE_PRODUCTMONTH" ("AD_CLIENT_ID", "AD_ORG_ID", "M_PRODUCT_ID", "DATEINVOICED", "LINENETAMT", "LINELISTAMT", "LINELIMITAMT", "LINEDISCOUNTAMT", "LINEDISCOUNT", "LINEOVERLIMITAMT", "LINEOVERLIMIT", "QTYINVOICED", "ISSOTRX") AS 
   SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
 	firstOf(il.DateInvoiced, 'MM') AS DateInvoiced,
 	SUM(il.LineNetAmt) AS LineNetAmt,
 	SUM(il.LineListAmt) AS LineListAmt,
 	SUM(il.LineLimitAmt) AS LineLimitAmt,
 	SUM(il.LineDiscountAmt) AS LineDiscountAmt,
 	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
 	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
 	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
 	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
 	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
 	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
 FROM RV_C_InvoiceLine il
 GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
 	firstOf(il.DateInvoiced, 'MM'), IsSOTrx
 ;
 
   CREATE OR REPLACE FORCE VIEW "RV_C_INVOICE_PRODUCTQTR" ("AD_CLIENT_ID", "AD_ORG_ID", "M_PRODUCT_ID", "DATEINVOICED", "LINENETAMT", "LINELISTAMT", "LINELIMITAMT", "LINEDISCOUNTAMT", "LINEDISCOUNT", "LINEOVERLIMITAMT", "LINEOVERLIMIT", "QTYINVOICED", "ISSOTRX") AS 
   SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
 	firstOf(il.DateInvoiced, 'Q') AS DateInvoiced,
 	SUM(il.LineNetAmt) AS LineNetAmt,
 	SUM(il.LineListAmt) AS LineListAmt,
 	SUM(il.LineLimitAmt) AS LineLimitAmt,
 	SUM(il.LineDiscountAmt) AS LineDiscountAmt,
 	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
 	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
 	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
 	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
 	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
 	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
 FROM RV_C_InvoiceLine il
 GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
 	firstOf(il.DateInvoiced, 'Q'), IsSOTrx
 ;
 
 
   CREATE OR REPLACE FORCE VIEW "R_REQUEST_V" ("R_REQUEST_ID", "AD_CLIENT_ID", "AD_ORG_ID", "ISACTIVE", "CREATED", "CREATEDBY", "UPDATED", "UPDATEDBY", "DOCUMENTNO", "R_REQUESTTYPE_ID", "R_GROUP_ID", "R_CATEGORY_ID", "R_STATUS_ID", "R_RESOLUTION_ID", "R_REQUESTRELATED_ID", "PRIORITY", "PRIORITYUSER", "DUETYPE", "SUMMARY", "CONFIDENTIALTYPE", "ISESCALATED", "ISSELFSERVICE", "SALESREP_ID", "AD_ROLE_ID", "DATELASTACTION", "DATELASTALERT", "LASTRESULT", "PROCESSED", "ISINVOICED", "C_BPARTNER_ID", "AD_USER_ID", "C_CAMPAIGN_ID", "C_ORDER_ID", "C_INVOICE_ID", "C_PAYMENT_ID", "M_PRODUCT_ID", "C_PROJECT_ID", "A_ASSET_ID", "M_INOUT_ID", "M_RMA_ID", "AD_TABLE_ID", "RECORD_ID", "REQUESTAMT", "R_MAILTEXT_ID", "RESULT", "CONFIDENTIALTYPEENTRY", "R_STANDARDRESPONSE_ID", "NEXTACTION", "DATENEXTACTION", "STARTTIME", "ENDTIME", "QTYSPENT", "QTYINVOICED", "M_PRODUCTSPENT_ID", "C_ACTIVITY_ID", "STARTDATE", "CLOSEDATE", "C_INVOICEREQUEST_ID", "M_CHANGEREQUEST_ID", "TASKSTATUS", "QTYPLAN", "DATECOMPLETEPLAN", "DATESTARTPLAN", "M_FIXCHANGENOTICE_ID") AS 
   SELECT "R_REQUEST_ID","AD_CLIENT_ID","AD_ORG_ID","ISACTIVE","CREATED","CREATEDBY","UPDATED","UPDATEDBY","DOCUMENTNO","R_REQUESTTYPE_ID","R_GROUP_ID","R_CATEGORY_ID","R_STATUS_ID","R_RESOLUTION_ID","R_REQUESTRELATED_ID","PRIORITY","PRIORITYUSER","DUETYPE","SUMMARY","CONFIDENTIALTYPE","ISESCALATED","ISSELFSERVICE","SALESREP_ID","AD_ROLE_ID","DATELASTACTION","DATELASTALERT","LASTRESULT","PROCESSED","ISINVOICED","C_BPARTNER_ID","AD_USER_ID","C_CAMPAIGN_ID","C_ORDER_ID","C_INVOICE_ID","C_PAYMENT_ID","M_PRODUCT_ID","C_PROJECT_ID","A_ASSET_ID","M_INOUT_ID","M_RMA_ID","AD_TABLE_ID","RECORD_ID","REQUESTAMT","R_MAILTEXT_ID","RESULT","CONFIDENTIALTYPEENTRY","R_STANDARDRESPONSE_ID","NEXTACTION","DATENEXTACTION","STARTTIME","ENDTIME","QTYSPENT","QTYINVOICED","M_PRODUCTSPENT_ID","C_ACTIVITY_ID","STARTDATE","CLOSEDATE","C_INVOICEREQUEST_ID","M_CHANGEREQUEST_ID","TASKSTATUS","QTYPLAN","DATECOMPLETEPLAN","DATESTARTPLAN","M_FIXCHANGENOTICE_ID"
 FROM R_Request
 WHERE IsActive='Y' AND Processed='N'
   AND getdate() > DateNextAction
 ;
 
 
   CREATE OR REPLACE FORCE VIEW "RV_C_INVOICELINE" ("AD_CLIENT_ID", "AD_ORG_ID", "ISACTIVE", "CREATED", "CREATEDBY", "UPDATED", "UPDATEDBY", "C_INVOICELINE_ID", "C_INVOICE_ID", "SALESREP_ID", "C_BPARTNER_ID", "C_BP_GROUP_ID", "M_PRODUCT_ID", "M_PRODUCT_CATEGORY_ID", "DATEINVOICED", "DATEACCT", "ISSOTRX", "C_DOCTYPE_ID", "DOCSTATUS", "ISPAID", "QTYINVOICED", "QTYENTERED", "M_ATTRIBUTESETINSTANCE_ID", "PRODUCTATTRIBUTE", "M_ATTRIBUTESET_ID", "M_LOT_ID", "GUARANTEEDATE", "LOT", "SERNO", "PRICELIST", "PRICEACTUAL", "PRICELIMIT", "PRICEENTERED", "DISCOUNT", "MARGIN", "MARGINAMT", "LINENETAMT", "LINELISTAMT", "LINELIMITAMT", "LINEDISCOUNTAMT", "LINEOVERLIMITAMT") AS 
   SELECT 
 	il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
 	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID,
 	i.C_BPartner_ID, i.C_BP_Group_ID,
 	il.M_Product_ID, p.M_Product_Category_ID,
 	i.DateInvoiced, i.DateAcct, i.IsSOTrx, i.C_DocType_ID, i.DocStatus, i.IsPaid,
 	--	Qty
 	il.QtyInvoiced*i.Multiplier AS QtyInvoiced,
 	il.QtyEntered*i.Multiplier AS QtyEntered,
     -- Attributes
     il.M_AttributeSetInstance_ID, productAttribute(il.M_AttributeSetInstance_ID) AS ProductAttribute,
     pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
 	--	Item Amounts
 	il.PriceList, il.PriceActual, il.PriceLimit, il.PriceEntered,
 	CASE WHEN PriceList=0 THEN 0 ELSE
 	  ROUND((PriceList-PriceActual)/PriceList*100,2) END AS Discount,
 	CASE WHEN PriceLimit=0 THEN 0 ELSE
 	  ROUND((PriceActual-PriceLimit)/PriceLimit*100,2) END AS Margin,
 	CASE WHEN PriceLimit=0 THEN 0 ELSE
 	  (PriceActual-PriceLimit)*QtyInvoiced END AS MarginAmt,
 	--	Line Amounts
 	ROUND(i.Multiplier*LineNetAmt, 2) AS LineNetAmt,
 	ROUND(i.Multiplier*PriceList*QtyInvoiced, 2) AS LineListAmt,
 	CASE WHEN COALESCE(il.PriceLimit, 0)=0 THEN ROUND(i.Multiplier*LineNetAmt,2) ELSE
 		ROUND(i.Multiplier*PriceLimit*QtyInvoiced,2) END AS LineLimitAmt,
 	ROUND(i.Multiplier*PriceList*QtyInvoiced-LineNetAmt,2) AS LineDiscountAmt,
 	CASE WHEN COALESCE(il.PriceLimit,0)=0 THEN 0 ELSE
 		ROUND(i.Multiplier*LineNetAmt-PriceLimit*QtyInvoiced,2) END AS LineOverLimitAmt
 FROM  RV_C_Invoice i
   INNER JOIN C_InvoiceLine il ON (i.C_Invoice_ID=il.C_Invoice_ID)
   LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
   LEFT OUTER JOIN M_AttributeSetInstance pasi ON (il.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
 ;
 
 
  -- CHANGED VIEWS - but check them first - don't overwrite your customizations...
  
  CREATE OR REPLACE FORCE VIEW AD_ORG_V
  AS SELECT o.AD_Client_ID, o.AD_Org_ID, 
      o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
      o.Value, o.Name, o.Description, o.IsSummary,
      i.C_Location_ID, i.DUNS, i.TaxID,
      i.Supervisor_ID, i.Parent_Org_ID, 
      i.AD_OrgType_ID, i.M_Warehouse_ID,
      bp.C_BPartner_ID
  FROM AD_Org o
      INNER JOIN AD_OrgInfo i ON (o.AD_Org_ID=i.AD_Org_ID)
      LEFT OUTER JOIN C_BPartner bp ON (o.AD_Org_ID=bp.AD_OrgBP_ID)
 ;
 
  CREATE OR REPLACE FORCE VIEW RV_BPARTNER
  AS SELECT bp.AD_Client_ID, bp.AD_Org_ID, 
  	bp.IsActive, bp.Created, bp.CreatedBy, bp.Updated, bp.UpdatedBy,
      bp.C_BPartner_ID, bp.Value, bp.Name, bp.Name2, bp.Description, bp.IsSummary,
      bp.C_BP_Group_ID, bp.IsOneTime, bp.IsProspect, bp.IsVendor, bp.IsCustomer, bp.IsEmployee, bp.IsSalesRep,
      bp.ReferenceNo, bp.Duns, bp.URL, bp.AD_Language, bp.TaxID, bp.IsTaxExempt, 
      bp.C_InvoiceSchedule_ID, bp.Rating, bp.SalesVolume, bp.NumberEmployees, bp.NAICS,
      bp.FirstSale, bp.AcqusitionCost, bp.PotentialLifeTimeValue, bp.ActualLifeTimeValue,
      bp.ShareOfCustomer, bp.PaymentRule, 
      bp.SO_CreditLimit, bp.SO_CreditUsed, bp.SO_CreditUsed-bp.SO_CreditLimit AS SO_CreditAvailable,
      bp.C_PaymentTerm_ID, bp.M_PriceList_ID, bp.M_DiscountSchema_ID, bp.C_Dunning_ID,
      bp.IsDiscountPrinted, bp.SO_Description, bp.POReference, PaymentRulePO,
      bp.PO_PriceList_ID, bp.PO_DiscountSchema_ID, bp.PO_PaymentTerm_ID,
      bp.DocumentCopies, bp.C_Greeting_ID, bp.InvoiceRule, bp.DeliveryRule,
      bp.FreightCostRule, bp.DeliveryViaRule, bp.SalesRep_ID,
      bp.SendEMail, bp.BPartner_Parent_ID, bp.Invoice_PrintFormat_ID,
      bp.SOCreditStatus, bp.ShelfLifeMinPct, bp.AD_OrgBP_ID,
      bp.FlatDiscount, bp.TotalOpenBalance,
      -- Contact
      c.AD_User_ID, c.Name AS ContactName, c.Description AS ContactDescription,
      c.EMail, c.Supervisor_ID, 
      c.EMailUser, c.C_Greeting_ID AS BPContactGreeting,
      c.Title, c.Comments, c.Phone, c.Phone2, c.Fax,
      c.LastContact, c.LastResult, c.BirthDay, c.AD_OrgTrx_ID,
      c.EMailVerify, c.LDAPUser, c.EMailVerifyDate, c.NotificationType,
      -- Location
  	l.C_BPartner_Location_ID, a.Postal, a.City, a.Address1, a.Address2, a.Address3, 
      a.C_Region_ID, COALESCE(r.Name,a.RegionName) AS RegionName,
      a.C_Country_ID, cc.Name AS CountryName
  FROM C_BPartner bp
   LEFT OUTER JOIN C_BPartner_Location l ON (bp.C_BPartner_ID=l.C_BPartner_ID AND l.IsActive='Y')
   LEFT OUTER JOIN AD_User c ON (bp.C_BPartner_ID=c.C_BPartner_ID AND (c.C_BPartner_Location_ID IS NULL OR c.C_BPartner_Location_ID=l.C_BPartner_Location_ID) AND c.IsActive='Y')
   LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)
   LEFT OUTER JOIN C_Region r ON (a.C_Region_ID=r.C_Region_ID)
   INNER JOIN C_Country cc ON (a.C_Country_ID=cc.C_Country_ID)
 ;
 
  CREATE OR REPLACE FORCE VIEW M_INOUT_CANDIDATE_V
  AS SELECT	
  	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
  	o.DocumentNo, o.DateOrdered, o.C_DocType_ID, 
      o.POReference, o.Description, o.SalesRep_ID,
      l.M_Warehouse_ID,
  	SUM((l.QtyOrdered-l.QtyDelivered)*l.PriceActual) AS TotalLines
  FROM C_Order o
    INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
  WHERE	(o.DocStatus = 'CO' AND o.IsDelivered='N')  --  Status must be CO - not CL/RE
  	--	not Offers and open Walkin-Receipts
  	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
  		WHERE DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR'))
      --  Delivery Rule - not manual
      AND o.DeliveryRule<>'M'
      AND (l.M_Product_ID IS NULL OR EXISTS 
          (SELECT * FROM M_Product p 
          WHERE l.M_Product_ID=p.M_Product_ID AND p.IsExcludeAutoDelivery='N'))
  	--	we need to ship
  	AND	l.QtyOrdered <> l.QtyDelivered
  	AND o.IsDropShip='N'
      AND (l.M_Product_ID IS NOT NULL OR l.C_Charge_ID IS NOT NULL)
      --  Not confirmed shipment
      AND NOT EXISTS (SELECT * FROM M_InOutLine iol 
          INNER JOIN M_InOut io ON (iol.M_InOut_ID=io.M_InOut_ID)
          WHERE iol.C_OrderLine_ID=l.C_OrderLine_ID AND io.DocStatus IN ('IP','WC'))
  	--
  GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
  	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
      o.POReference, o.Description, o.SalesRep_ID, l.M_Warehouse_ID
 ;
 
 update AD_System set version='2006-01-20';
 
 COMMIT;
