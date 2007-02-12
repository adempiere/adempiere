CREATE OR REPLACE VIEW M_INOUT_LINECONFIRM_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, M_INOUTLINECONFIRM_ID, M_INOUTCONFIRM_ID, 
 TARGETQTY, CONFIRMEDQTY, DIFFERENCEQTY, SCRAPPEDQTY, DESCRIPTION, 
 PROCESSED, M_INOUT_ID, M_INOUTLINE_ID, LINE, M_PRODUCT_ID, 
 MOVEMENTQTY, UOMSYMBOL, QTYBACKORDERED, NAME, SHIPDESCRIPTION, 
 DOCUMENTNOTE, UPC, SKU, PRODUCTVALUE, M_LOCATOR_ID, 
 M_WAREHOUSE_ID, X, Y, Z, M_ATTRIBUTESETINSTANCE_ID, 
 M_ATTRIBUTESET_ID, SERNO, LOT, M_LOT_ID, GUARANTEEDATE)
AS 
SELECT iolc.AD_Client_ID, iolc.AD_Org_ID, iolc.IsActive, iolc.Created, iolc.CreatedBy, iolc.Updated, iolc.UpdatedBy,
	cast('en_US' as varchar) AS AD_Language,
    iolc.M_InOutLineConfirm_ID, iolc.M_InOutConfirm_ID,
    iolc.TargetQty, iolc.ConfirmedQty, iolc.DifferenceQty, iolc.ScrappedQty,
    iolc.Description, iolc.Processed,
	iol.M_InOut_ID, iol.M_InOutLine_ID,
	iol.Line, p.M_Product_ID,
	iol.MovementQty, uom.UOMSymbol, ol.QtyOrdered-ol.QtyDelivered AS QtyBackOrdered,
	COALESCE(p.Name, iol.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN iol.Description END AS ShipDescription, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate
FROM M_InOutLineConfirm iolc
    INNER JOIN M_InOutLine iol ON (iolc.M_InOutLine_ID=iol.M_InOutLine_ID)
	INNER JOIN C_UOM uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID);



