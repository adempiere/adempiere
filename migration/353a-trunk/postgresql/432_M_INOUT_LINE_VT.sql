CREATE OR REPLACE VIEW M_INOUT_LINE_VT
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
	uom.AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID,
	iol.Line, p.M_Product_ID,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty,
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
    ol.QtyOrdered, ol.QtyDelivered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(COALESCE(pt.Name,p.Name)||productAttribute(iol.M_AttributeSetInstance_ID), c.Name, iol.Description) AS Name, -- main line
	CASE WHEN COALESCE(pt.Name,p.Name,c.Name) IS NOT NULL THEN iol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
FROM	M_InOutLine iol
	INNER JOIN C_UOM_Trl uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_Charge c ON (iol.C_Charge_ID=c.C_Charge_ID)
UNION
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	uom.AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID,
	iol.Line+(bl.Line/100) AS Line, p.M_Product_ID,
	--iol.MovementQty*b.BOMQty AS QtyInvoiced,
	CASE WHEN bl.IsQtyPercentage = 'N' THEN iol.MovementQty*bl.QtyBOM ELSE iol.MovementQty*(bl.QtyBatch / 100) END AS QtyInvoiced, 	 
	--iol.QtyEntered*b.BOMQty AS QtyEntered, 
	CASE WHEN bl.IsQtyPercentage = 'N' THEN iol.QtyEntered*bl.QtyBOM ELSE iol.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered,
	uom.UOMSymbol,
    null, null, null,
	COALESCE (pt.Name, p.Name) AS Name, -- main line
	b.Description, -- second line
	COALESCE (pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
/*FROM M_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID);*/
FROM PP_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN PP_Product_BOMLine bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_Product p ON (bl.M_Product_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (bl.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID);



