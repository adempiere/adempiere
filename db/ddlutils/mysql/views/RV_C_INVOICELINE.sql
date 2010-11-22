CREATE OR REPLACE VIEW RV_C_INVOICELINE
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_INVOICELINE_ID, C_INVOICE_ID, SALESREP_ID, 
 C_BPARTNER_ID, C_BP_GROUP_ID, M_PRODUCT_ID, M_PRODUCT_CATEGORY_ID, DATEINVOICED, 
 DATEACCT, ISSOTRX, C_DOCTYPE_ID, DOCSTATUS, ISPAID, 
 C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, C_PROJECTPHASE_ID, C_PROJECTTASK_ID, 
 QTYINVOICED, QTYENTERED, M_ATTRIBUTESETINSTANCE_ID, PRODUCTATTRIBUTE, M_ATTRIBUTESET_ID, 
 M_LOT_ID, GUARANTEEDATE, LOT, SERNO, PRICELIST, 
 PRICEACTUAL, PRICELIMIT, PRICEENTERED, DISCOUNT, MARGIN, 
 MARGINAMT, LINENETAMT, LINELISTAMT, LINELIMITAMT, LINEDISCOUNTAMT, 
 LINEOVERLIMITAMT)
AS 
SELECT 
  il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
  il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID,
  i.C_BPartner_ID, i.C_BP_Group_ID,
  il.M_Product_ID, p.M_Product_Category_ID,
  i.DateInvoiced, i.DateAcct, i.IsSOTrx, i.C_DocType_ID, i.DocStatus, i.IsPaid,
  il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID,
  --  Qty
  il.QtyInvoiced*i.Multiplier AS QtyInvoiced,
  il.QtyEntered*i.Multiplier AS QtyEntered,
  -- Attributes
  il.M_AttributeSetInstance_ID, productAttribute(il.M_AttributeSetInstance_ID) AS ProductAttribute,
  pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
  --  Item Amounts
  il.PriceList, il.PriceActual, il.PriceLimit, il.PriceEntered,
  CASE WHEN PriceList=0 THEN 0 ELSE
    ROUND((PriceList-PriceActual)/PriceList*100,2) END AS Discount,
  CASE WHEN PriceLimit=0 THEN 0 ELSE
    ROUND((PriceActual-PriceLimit)/PriceLimit*100,2) END AS Margin,
  CASE WHEN PriceLimit=0 THEN 0 ELSE
    (PriceActual-PriceLimit)*QtyInvoiced END AS MarginAmt,
  --  Line Amounts
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
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (il.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID);
