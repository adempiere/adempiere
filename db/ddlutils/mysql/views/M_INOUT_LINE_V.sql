CREATE OR REPLACE VIEW M_INOUT_LINE_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, M_INOUT_ID, M_INOUTLINE_ID, 
 LINE, M_PRODUCT_ID, MOVEMENTQTY, QTYENTERED, UOMSYMBOL, 
 QTYORDERED, QTYDELIVERED, QTYBACKORDERED, NAME, DESCRIPTION, 
 DOCUMENTNOTE, UPC, SKU, PRODUCTVALUE, M_LOCATOR_ID, 
 M_WAREHOUSE_ID, X, Y, Z, M_ATTRIBUTESETINSTANCE_ID, 
 M_ATTRIBUTESET_ID, SERNO, LOT, M_LOT_ID, GUARANTEEDATE, 
 PRODUCTDESCRIPTION, IMAGEURL, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, 
 C_PROJECTPHASE_ID, C_PROJECTTASK_ID)
AS 
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	'en_US' AS AD_LANGUAGE,
	iol.M_InOut_ID, iol.M_InOutLine_ID,
	iol.Line, p.M_Product_ID,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty,
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
    ol.QtyOrdered, ol.QtyDelivered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(p.NAME||Productattribute(iol.M_AttributeSetInstance_ID), c.NAME, iol.Description) AS NAME, -- main line
	CASE WHEN COALESCE(c.NAME,p.NAME) IS NOT NULL THEN iol.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.VALUE AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
FROM M_INOUTLINE iol
	INNER JOIN C_UOM uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_PRODUCT p ON (iol.M_Product_ID=p.M_Product_ID)
    LEFT OUTER JOIN M_ATTRIBUTESETINSTANCE asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_LOCATOR l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_ORDERLINE ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_CHARGE c ON (iol.C_Charge_ID=c.C_Charge_ID)
UNION   --  BOM lines
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	'en_US' AS AD_LANGUAGE,
	iol.M_InOut_ID, iol.M_InOutLine_ID,
	iol.Line+(bl.Line/100) AS Line, p.M_Product_ID,
	/*iol.MovementQty*b.BOMQty AS QtyInvoiced,*/
	CASE WHEN bl.IsQtyPercentage = 'N' THEN iol.MovementQty*bl.QtyBOM ELSE iol.MovementQty*(bl.QtyBatch / 100) END AS QtyInvoiced, 	
	/*iol.QtyEntered*b.BOMQty AS QtyEntered,*/
	CASE WHEN bl.IsQtyPercentage = 'N' THEN iol.QtyEntered*bl.QtyBOM ELSE iol.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered,	
	uom.UOMSymbol,
    NULL, NULL, NULL,
	p.NAME, -- main line
	b.Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.VALUE AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
/*FROM M_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)*/
FROM PP_PRODUCT_BOM b	-- BOM lines
	INNER JOIN M_INOUTLINE iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_PRODUCT bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_PRODUCT p ON (bl.M_Product_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN M_ATTRIBUTESETINSTANCE asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_LOCATOR l ON (iol.M_Locator_ID=l.M_Locator_ID);