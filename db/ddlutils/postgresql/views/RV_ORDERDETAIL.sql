CREATE OR REPLACE VIEW RV_ORDERDETAIL
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_ORDER_ID, DOCSTATUS, DOCACTION, 
 C_DOCTYPE_ID, ISAPPROVED, ISCREDITAPPROVED, SALESREP_ID, BILL_BPARTNER_ID, 
 BILL_LOCATION_ID, BILL_USER_ID, ISDROPSHIP, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, 
 AD_USER_ID, POREFERENCE, C_CURRENCY_ID, ISSOTRX, C_CAMPAIGN_ID, 
 C_PROJECT_ID, C_ACTIVITY_ID, C_PROJECTPHASE_ID, C_PROJECTTASK_ID, C_ORDERLINE_ID, 
 DATEORDERED, DATEPROMISED, M_PRODUCT_ID, M_WAREHOUSE_ID, M_ATTRIBUTESETINSTANCE_ID, 
 PRODUCTATTRIBUTE, M_ATTRIBUTESET_ID, M_LOT_ID, GUARANTEEDATE, LOT, 
 SERNO, C_UOM_ID, QTYENTERED, QTYORDERED, QTYRESERVED, 
 QTYDELIVERED, QTYINVOICED, PRICEACTUAL, PRICEENTERED, QTYTODELIVER, 
 QTYTOINVOICE, NETAMTTOINVOICE, QTYLOSTSALES, AMTLOSTSALES, DISCOUNT, 
 MARGIN, MARGINAMT)
AS 
SELECT l.AD_Client_ID, l.AD_Org_ID, 
	l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,
	o.C_Order_ID, o.DocStatus, o.DocAction, o.C_DocType_ID, o.IsApproved, o.IsCreditApproved,
	o.SalesRep_ID, 
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID, o.IsDropShip,
    l.C_BPartner_ID, l.C_BPartner_Location_ID, o.AD_User_ID,
	o.POReference, o.C_Currency_ID, o.IsSOTrx,
    l.C_Campaign_ID, l.C_Project_ID, l.C_Activity_ID, l.C_ProjectPhase_ID, l.C_ProjectTask_ID,
	l.C_OrderLine_ID, l.DateOrdered, l.DatePromised, l.M_Product_ID, l.M_Warehouse_ID,
    l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
    pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	l.C_UOM_ID, l.QtyEntered, l.QtyOrdered, l.QtyReserved, l.QtyDelivered, l.QtyInvoiced, 
    l.PriceActual, l.PriceEntered,
	l.QtyOrdered-l.QtyDelivered AS QtyToDeliver,
	l.QtyOrdered-l.QtyInvoiced AS QtyToInvoice,
	(l.QtyOrdered-l.QtyInvoiced)*l.PriceActual AS NetAmtToInvoice,
    l.QtyLostSales, l.QtyLostSales*l.PriceActual AS AmtLostSales,
	CASE WHEN PriceList=0 THEN 0 ELSE
	  ROUND((PriceList-PriceActual)/PriceList*100,2) END AS Discount,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  ROUND((PriceActual-PriceLimit)/PriceLimit*100,2) END AS Margin,
	CASE WHEN PriceLimit=0 THEN 0 ELSE
	  (PriceActual-PriceLimit)*QtyDelivered END AS MarginAmt
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID);



