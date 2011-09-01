CREATE OR REPLACE VIEW RV_M_REQUISITION
(M_REQUISITION_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, DOCUMENTNO, DESCRIPTION, 
 HELP, AD_USER_ID, M_PRICELIST_ID, M_WAREHOUSE_ID, ISAPPROVED, 
 PRIORITYRULE, DATEREQUIRED, TOTALLINES, DOCACTION, DOCSTATUS, 
 PROCESSED, M_REQUISITIONLINE_ID, LINE, QTY, QTYORDERED,
 M_PRODUCT_ID, LINEDESCRIPTION, PRICEACTUAL, LINENETAMT)
AS 
SELECT r.M_Requisition_ID,
    r.AD_Client_ID, r.AD_Org_ID, r.IsActive, r.Created, r.CreatedBy, r.Updated, r.UpdatedBy,
    r.DocumentNo, r.Description, r.Help,
    r.AD_User_ID, r.M_PriceList_ID, r.M_Warehouse_ID, r.IsApproved, r.PriorityRule,
    r.DateRequired, r.TotalLines, r.DocAction, r.DocStatus, r.Processed,
    l.M_RequisitionLine_ID, l.Line,
    l.Qty,
	(CASE WHEN l.C_OrderLine_ID IS NOT NULL THEN l.Qty ELSE 0 END) AS QtyOrdered,
	l.M_Product_ID,
    l.Description AS LineDescription,
    l.PriceActual, l.LineNetAmt
FROM M_Requisition r
  INNER JOIN M_RequisitionLine l ON (r.M_Requisition_ID=l.M_Requisition_ID);



