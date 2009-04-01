--DROP VIEW RV_DD_ORDERDETAIL;
CREATE OR REPLACE VIEW RV_DD_ORDERDETAIL AS
SELECT l.AD_Client_ID, l.AD_Org_ID, 
	l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,o.DD_Order_ID,
	o.C_Order_ID, o.DocStatus, o.DocAction, o.C_DocType_ID, o.IsApproved, --o.IsCreditApproved,
	o.SalesRep_ID, 
	o.IsDropShip, 
    o.C_BPartner_ID,
    bp.C_BP_Group_ID,
	o.AD_User_ID,
	o.POReference, 
	o.IsSOTrx,
	l.C_Campaign_ID, l.C_Project_ID, l.C_Activity_ID, 
	--l.C_ProjectPhase_ID, l.C_ProjectTask_ID,
	l.DD_OrderLine_ID, l.DateOrdered, l.DatePromised, l.M_Product_ID, l.M_Locator_ID,l.M_LocatorTo_ID,
	l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
	l.M_AttributeSetInstanceTo_ID, productAttribute(l.M_AttributeSetInstanceTo_ID) AS ProductAttributeTo,
	pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	l.C_UOM_ID, l.QtyEntered, l.QtyOrdered, l.QtyReserved, l.QtyDelivered, l.Confirmedqty,  l.Qtyintransit, l.TargetQty,
	l.QtyOrdered-l.QtyDelivered AS QtyToDeliver,
	l.Description
FROM DD_Order o
  INNER JOIN DD_OrderLine l ON (l.DD_Order_ID=o.DD_Order_ID)
  INNER JOIN C_BPartner bp ON (bp.C_BPartner_ID=o.C_BPartner_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasito ON (l.M_AttributeSetInstanceTo_ID=pasito.M_AttributeSetInstance_ID);

