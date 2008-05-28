CREATE OR REPLACE VIEW C_INVOICE_LINETAX_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_INVOICE_ID, C_INVOICELINE_ID, 
 C_TAX_ID, TAXAMT, LINETOTALAMT, TAXINDICATOR, LINE, 
 M_PRODUCT_ID, QTYINVOICED, QTYENTERED, UOMSYMBOL, NAME, 
 DESCRIPTION, DOCUMENTNOTE, UPC, SKU, PRODUCTVALUE, 
 RESOURCEDESCRIPTION, PRICELIST, PRICEENTEREDLIST, DISCOUNT, PRICEACTUAL, 
 PRICEENTERED, LINENETAMT, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, SERNO, 
 LOT, M_LOT_ID, GUARANTEEDATE, PRODUCTDESCRIPTION, IMAGEURL, 
 C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, C_PROJECTPHASE_ID, C_PROJECTTASK_ID)
AS 
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US' AS AD_LANGUAGE,
	il.C_Invoice_ID, il.C_InvoiceLine_ID,
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, p.M_Product_ID,
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced,
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered,
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
	COALESCE(c.NAME,p.NAME||Productattribute(il.M_AttributeSetInstance_ID), il.Description) AS NAME, -- main line
	CASE WHEN COALESCE(c.NAME,p.NAME) IS NOT NULL THEN il.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.VALUE) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0
        THEN il.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 AND il.QtyEntered<>0
        THEN il.PriceList*il.QtyInvoiced/il.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList>il.PriceActual AND il.PriceList<>0
        THEN (il.PriceList-il.PriceActual)/il.PriceList*100 END AS Discount,
	CASE WHEN il.PriceActual<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceActual END AS PriceActual,
	CASE WHEN il.PriceEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceEntered END AS PriceEntered,
	CASE WHEN il.LineNetAmt<>0 OR il.M_Product_ID IS NOT NULL THEN il.LineNetAmt END AS LineNetAmt,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID,
    asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_INVOICELINE il
	INNER JOIN C_UOM uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_INVOICE i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_TAX t ON (il.C_Tax_ID=t.C_Tax_ID)
	LEFT OUTER JOIN M_PRODUCT p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_CHARGE c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPARTNER_PRODUCT pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN S_RESOURCEASSIGNMENT ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_ATTRIBUTESETINSTANCE asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US' AS AD_LANGUAGE,
	il.C_Invoice_ID, il.C_InvoiceLine_ID,
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(bl.Line/100) AS Line, p.M_Product_ID,
	--il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
	CASE WHEN bl.IsQtyPercentage = 'N' THEN il.QtyInvoiced*bl.QtyBOM ELSE il.QtyInvoiced*(bl.QtyBatch / 100) END AS QtyInvoiced, 
    --il.QtyEntered*b.BOMQty AS QtyEntered,
    CASE WHEN bl.IsQtyPercentage = 'N' THEN il.QtyEntered*bl.QtyBOM ELSE il.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered, 
    uom.UOMSymbol,
	p.NAME,	-- main
	b.Description,
	p.DocumentNote, p.UPC, p.SKU, p.VALUE AS ProductValue,
	NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
/*FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)*/
FROM PP_PRODUCT_BOM b	-- BOM lines
	INNER JOIN C_INVOICELINE il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_PRODUCT bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_PRODUCT p ON (bl.M_Product_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN C_TAX t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_ATTRIBUTESETINSTANCE asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US', il.C_Invoice_ID, il.C_InvoiceLine_ID,
    NULL, NULL, NULL, NULL,
	il.Line, NULL,
	NULL, NULL, NULL,
	il.Description,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL, NULL, NULL, NULL, NULL,
    NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    NULL, NULL, NULL, NULL, NULL
FROM C_INVOICELINE il
WHERE il.C_UOM_ID IS NULL
UNION   --  empty line
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Invoice_ID, NULL,
    NULL, NULL, NULL, NULL,
	9998, NULL,
	NULL, NULL, NULL,
	NULL,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL, NULL, NULL, NULL, NULL,
    NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    NULL, NULL, NULL, NULL, NULL
FROM C_INVOICE
UNION   --   tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	'en_US', it.C_Invoice_ID, NULL,
    it.C_Tax_ID, NULL, NULL, t.TaxIndicator,
	9999, NULL,
	NULL, NULL, NULL,
	t.NAME,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL, NULL,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END,
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    NULL, NULL, NULL, NULL, NULL
FROM C_INVOICETAX it
	INNER JOIN C_TAX t ON (it.C_Tax_ID=t.C_Tax_ID);

CREATE OR REPLACE VIEW C_INVOICE_LINETAX_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_INVOICE_ID, C_INVOICELINE_ID, 
 C_TAX_ID, TAXAMT, LINETOTALAMT, TAXINDICATOR, LINE, 
 M_PRODUCT_ID, QTYINVOICED, QTYENTERED, UOMSYMBOL, NAME, 
 DESCRIPTION, DOCUMENTNOTE, UPC, SKU, PRODUCTVALUE, 
 RESOURCEDESCRIPTION, PRICELIST, PRICEENTEREDLIST, DISCOUNT, PRICEACTUAL, 
 PRICEENTERED, LINENETAMT, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, SERNO, 
 LOT, M_LOT_ID, GUARANTEEDATE, PRODUCTDESCRIPTION, IMAGEURL, 
 C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, C_PROJECTPHASE_ID, C_PROJECTTASK_ID)
AS 
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_LANGUAGE,
	il.C_Invoice_ID, il.C_InvoiceLine_ID,
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, p.M_Product_ID,
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced,
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered,
	CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
	COALESCE(c.NAME,COALESCE(pt.NAME,p.NAME)||Productattribute(il.M_AttributeSetInstance_ID), il.Description) AS NAME, -- main line
	CASE WHEN COALESCE(c.NAME,pt.NAME,p.NAME) IS NOT NULL THEN il.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.VALUE) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0
        THEN il.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList<>0 AND il.QtyEntered<>0
        THEN il.PriceList*il.QtyInvoiced/il.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND il.PriceList>il.PriceActual AND il.PriceList<>0
        THEN (il.PriceList-il.PriceActual)/il.PriceList*100 END AS Discount,
	CASE WHEN il.PriceActual<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceActual END AS PriceActual,
	CASE WHEN il.PriceEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.PriceEntered END AS PriceEntered,
	CASE WHEN il.LineNetAmt<>0 OR il.M_Product_ID IS NOT NULL THEN il.LineNetAmt END AS LineNetAmt,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_INVOICELINE il
	INNER JOIN C_UOM_TRL uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_INVOICE i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_TAX_TRL t ON (il.C_Tax_ID=t.C_Tax_ID AND uom.AD_LANGUAGE=t.AD_LANGUAGE)
	LEFT OUTER JOIN M_PRODUCT p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_CHARGE c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPARTNER_PRODUCT pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN M_PRODUCT_TRL pt ON (il.M_Product_ID=pt.M_Product_ID AND uom.AD_LANGUAGE=pt.AD_LANGUAGE)
	LEFT OUTER JOIN S_RESOURCEASSIGNMENT ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_ATTRIBUTESETINSTANCE asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_LANGUAGE,
	il.C_Invoice_ID, il.C_InvoiceLine_ID,
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(bl.Line/100) AS Line, p.M_Product_ID,
	--il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
	CASE WHEN bl.IsQtyPercentage = 'N' THEN il.QtyInvoiced*bl.QtyBOM ELSE il.QtyInvoiced*(bl.QtyBatch / 100) END AS QtyInvoiced, 	
    --il.QtyEntered*b.BOMQty AS QtyEntered,
    CASE WHEN bl.IsQtyPercentage = 'N' THEN il.QtyEntered*bl.QtyBOM ELSE il.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered, 
	uom.UOMSymbol,
	COALESCE(pt.NAME,p.NAME) AS NAME,	-- main
	b.Description,
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.VALUE AS ProductValue,
	NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
/*FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (b.M_ProductBOM_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)*/
FROM PP_PRODUCT_BOM b	-- BOM lines
	INNER JOIN C_INVOICELINE il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_PRODUCT bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_PRODUCT p ON (bl.M_Product_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_TRL uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_PRODUCT_TRL pt ON (bl.M_Product_ID=pt.M_Product_ID AND uom.AD_LANGUAGE=pt.AD_LANGUAGE)
    LEFT OUTER JOIN C_TAX t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_ATTRIBUTESETINSTANCE asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment line
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	l.AD_LANGUAGE, il.C_Invoice_ID, il.C_InvoiceLine_ID,
    NULL, NULL, NULL, NULL,
	il.Line, NULL,
	NULL, NULL, NULL,
	il.Description,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL,	NULL, NULL, NULL, NULL,
    NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    NULL,NULL,NULL,NULL,NULL
FROM C_INVOICELINE il, AD_LANGUAGE l
WHERE il.C_UOM_ID IS NULL
  AND l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  empty line
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
	AD_LANGUAGE, i.C_Invoice_ID, NULL,
    NULL, NULL, NULL, NULL,
	9998, NULL,
	NULL, NULL, NULL,
	NULL,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL,	NULL, NULL, NULL, NULL,
    NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    NULL,NULL,NULL,NULL,NULL
FROM C_INVOICE i, AD_LANGUAGE l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	t.AD_LANGUAGE, it.C_Invoice_ID, NULL,
    it.C_Tax_ID, NULL, NULL, t.TaxIndicator,
	9999, NULL,
	NULL, NULL, NULL,
	t.NAME,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL, NULL,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END,
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    NULL,NULL,NULL,NULL,NULL
FROM C_INVOICETAX it
	INNER JOIN C_TAX_TRL t ON (it.C_Tax_ID=t.C_Tax_ID);



CREATE OR REPLACE VIEW C_ORDER_LINETAX_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_ORDER_ID, C_ORDERLINE_ID, 
 C_TAX_ID, TAXINDICATOR, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, BPNAME, 
 C_LOCATION_ID, LINE, M_PRODUCT_ID, QTYORDERED, QTYENTERED, 
 UOMSYMBOL, NAME, DESCRIPTION, DOCUMENTNOTE, UPC, 
 SKU, PRODUCTVALUE, RESOURCEDESCRIPTION, PRICELIST, PRICEENTEREDLIST, 
 DISCOUNT, PRICEACTUAL, PRICEENTERED, LINENETAMT, PRODUCTDESCRIPTION, 
 IMAGEURL, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, C_PROJECTPHASE_ID, 
 C_PROJECTTASK_ID)
AS 
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	'en_US' AS AD_LANGUAGE,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, t.TaxIndicator,
    ol.C_BPartner_ID, ol.C_BPartner_Location_ID, bp.NAME AS BPName, bpl.C_Location_ID,
	ol.Line, p.M_Product_ID,
	CASE WHEN ol.QtyOrdered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered END AS QtyOrdered,
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyEntered END AS QtyEntered,
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
	COALESCE(c.NAME,p.NAME||Productattribute(ol.M_AttributeSetInstance_ID), ol.Description) AS NAME, -- main line
	CASE WHEN COALESCE(c.NAME,p.NAME) IS NOT NULL THEN ol.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.VALUE) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0
        THEN ol.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0 AND ol.QtyEntered<>0
        THEN ol.PriceList*ol.QtyOrdered/ol.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList>ol.PriceActual AND ol.PriceList<>0
        THEN (ol.PriceList-ol.PriceActual)/ol.PriceList*100 END AS Discount,
	CASE WHEN ol.PriceActual<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceActual END AS PriceActual,
	CASE WHEN ol.PriceEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceEntered END AS PriceEntered,
	CASE WHEN ol.LineNetAmt<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.LineNetAmt END AS LineNetAmt,
    p.Description AS ProductDescription, p.ImageURL,
    ol.C_Campaign_ID, ol.C_Project_ID, ol.C_Activity_ID, ol.C_ProjectPhase_ID, ol.C_ProjectTask_ID
FROM C_ORDERLINE ol
	INNER JOIN C_UOM uom ON (ol.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_ORDER i ON (ol.C_Order_ID=i.C_Order_ID)
	LEFT OUTER JOIN M_PRODUCT p ON (ol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN S_RESOURCEASSIGNMENT ra ON (ol.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
	LEFT OUTER JOIN C_CHARGE c ON (ol.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPARTNER_PRODUCT pp ON (ol.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	INNER JOIN C_BPARTNER bp ON (ol.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN C_BPARTNER_LOCATION bpl ON (ol.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_TAX t ON (ol.C_Tax_ID=t.C_Tax_ID)
UNION
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	'en_US' AS AD_LANGUAGE,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, NULL,
    NULL, NULL, NULL, NULL,
	ol.Line+(bl.Line/100) AS Line, p.M_Product_ID,
    --ol.QtyOrdered*bl.BOMQty AS QtyInvoiced
	CASE WHEN bl.IsQtyPercentage = 'N' THEN ol.QtyOrdered*bl.QtyBOM ELSE ol.QtyOrdered*(bl.QtyBatch / 100) END AS QtyInvoiced, 	
	--ol.QtyEntered*bl.BOMQty AS QtyEntered, 
	CASE WHEN bl.IsQtyPercentage = 'N' THEN ol.QtyEntered*bl.QtyBOM ELSE ol.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered, 	
	uom.UOMSymbol,
	p.NAME,	-- main
	bl.Description,
	p.DocumentNote, p.UPC, p.SKU, p.VALUE AS ProductValue,
	NULL, NULL, NULL, NULL, NULL, NULL, NULL, p.Description AS ProductDescription, p.ImageURL,
    ol.C_Campaign_ID, ol.C_Project_ID, ol.C_Activity_ID, ol.C_ProjectPhase_ID, ol.C_ProjectTask_ID
/*FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_OrderLine ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)*/
FROM PP_PRODUCT_BOM b 	
	INNER JOIN  C_ORDERLINE ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN  M_PRODUCT bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_PRODUCT p ON (p.M_Product_ID=bl.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Order_ID, NULL, NULL, NULL,
	NULL,
	NULL, NULL, NULL,
    NULL, NULL, NULL, NULL,
	NULL, NULL,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    NULL,NULL,NULL,NULL,NULL
FROM C_ORDER
UNION
SELECT ot.AD_Client_ID, ot.AD_Org_ID, ot.IsActive, ot.Created, ot.CreatedBy, ot.Updated, ot.UpdatedBy,
	'en_US', ot.C_Order_ID, NULL, ot.C_Tax_ID, t.TaxIndicator,
    NULL, NULL, NULL, NULL,
	NULL, NULL,
	NULL, NULL, NULL,
	t.NAME,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL, NULL,
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END,
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END,
    CASE WHEN ot.IsTaxIncluded='Y' THEN NULL ELSE ot.TaxAmt END,
    NULL, NULL,
    NULL,NULL,NULL,NULL,NULL
FROM C_ORDERTAX ot
	INNER JOIN C_TAX t ON (ot.C_Tax_ID=t.C_Tax_ID);



CREATE OR REPLACE VIEW C_ORDER_LINETAX_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_ORDER_ID, C_ORDERLINE_ID, 
 C_TAX_ID, TAXINDICATOR, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, BPNAME, 
 C_LOCATION_ID, LINE, M_PRODUCT_ID, QTYORDERED, QTYENTERED, 
 UOMSYMBOL, NAME, DESCRIPTION, DOCUMENTNOTE, UPC, 
 SKU, PRODUCTVALUE, RESOURCEDESCRIPTION, PRICELIST, PRICEENTEREDLIST, 
 DISCOUNT, PRICEACTUAL, PRICEENTERED, LINENETAMT, PRODUCTDESCRIPTION, 
 IMAGEURL, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, C_PROJECTPHASE_ID, 
 C_PROJECTTASK_ID)
AS 
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	uom.AD_LANGUAGE,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, t.TaxIndicator,
    ol.C_BPartner_ID, ol.C_BPartner_Location_ID, bp.NAME AS BPName, bpl.C_Location_ID,
	ol.Line, p.M_Product_ID,
	CASE WHEN ol.QtyOrdered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered END AS QtyOrdered,
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyEntered END AS QtyEntered,
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
	COALESCE(c.NAME,p.NAME||Productattribute(ol.M_AttributeSetInstance_ID), ol.Description) AS NAME, -- main line
	CASE WHEN COALESCE(c.NAME,pt.NAME, p.NAME) IS NOT NULL THEN ol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.VALUE) AS ProductValue,
	ra.Description AS ResourceDescription, -- forth line
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0
        THEN ol.PriceList END AS PriceList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList<>0 AND ol.QtyEntered<>0
        THEN ol.PriceList*ol.QtyOrdered/ol.QtyEntered END AS PriceEnteredList,
	CASE WHEN i.IsDiscountPrinted='Y' AND ol.PriceList>ol.PriceActual AND ol.PriceList<>0
        THEN (ol.PriceList-ol.PriceActual)/ol.PriceList*100 END AS Discount,
	CASE WHEN ol.PriceActual<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceActual END AS PriceActual,
	CASE WHEN ol.PriceEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.PriceEntered END AS PriceEntered,
	CASE WHEN ol.LineNetAmt<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.LineNetAmt END AS LineNetAmt,
    pt.Description AS ProductDescription, p.ImageURL,
    ol.C_Campaign_ID, ol.C_Project_ID, ol.C_Activity_ID, ol.C_ProjectPhase_ID, ol.C_ProjectTask_ID
FROM C_ORDERLINE ol
	INNER JOIN C_UOM_TRL uom ON (ol.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_ORDER i ON (ol.C_Order_ID=i.C_Order_ID)
	LEFT OUTER JOIN M_PRODUCT p ON (ol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_PRODUCT_TRL pt ON (ol.M_Product_ID=pt.M_Product_ID AND uom.AD_LANGUAGE=pt.AD_LANGUAGE)
	LEFT OUTER JOIN S_RESOURCEASSIGNMENT ra ON (ol.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
	LEFT OUTER JOIN C_CHARGE c ON (ol.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPARTNER_PRODUCT pp ON (ol.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	INNER JOIN C_BPARTNER bp ON (ol.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN C_BPARTNER_LOCATION bpl ON (ol.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_TAX_TRL t ON (ol.C_Tax_ID=t.C_Tax_ID AND uom.AD_LANGUAGE=t.AD_LANGUAGE)
UNION
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	uom.AD_LANGUAGE,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, NULL,
    NULL, NULL, NULL, NULL,
	ol.Line+(bl.Line/100) AS Line, p.M_Product_ID,
	--ol.QtyOrdered*b.BOMQty AS QtyInvoiced, 
	CASE WHEN bl.IsQtyPercentage = 'N' THEN ol.QtyOrdered*bl.QtyBOM ELSE ol.QtyOrdered*(bl.QtyBatch / 100) END AS QtyInvoiced,
	--ol.QtyEntered*b.BOMQty AS QtyEntered, 
    CASE WHEN bl.IsQtyPercentage = 'N' THEN ol.QtyEntered*bl.QtyBOM ELSE ol.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered, 	
	uom.UOMSymbol,
	COALESCE(pt.NAME, p.NAME) AS NAME,	-- main
	b.Description,
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.VALUE AS ProductValue,
	NULL, NULL, NULL, NULL, NULL, NULL, NULL, pt.Description AS ProductDescription, p.ImageURL,
    ol.C_Campaign_ID, ol.C_Project_ID, ol.C_Activity_ID, ol.C_ProjectPhase_ID, ol.C_ProjectTask_ID
/*FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_OrderLine ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (b.M_ProductBOM_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)*/
FROM PP_PRODUCT_BOM b 	
	INNER JOIN C_ORDERLINE ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_PRODUCT bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_PRODUCT p ON (p.M_Product_ID=bl.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_TRL uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_PRODUCT_TRL pt ON (pt.M_Product_ID=bl.M_Product_ID AND uom.AD_LANGUAGE=pt.AD_LANGUAGE)
UNION
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	l.AD_LANGUAGE, o.C_Order_ID, NULL, NULL, NULL,
	NULL,
	NULL, NULL, NULL,
    NULL, NULL, NULL, NULL,
	NULL, NULL,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
    NULL,NULL,NULL,NULL,NULL
FROM C_ORDER o, AD_LANGUAGE l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION
SELECT ot.AD_Client_ID, ot.AD_Org_ID, ot.IsActive, ot.Created, ot.CreatedBy, ot.Updated, ot.UpdatedBy,
	t.AD_LANGUAGE, ot.C_Order_ID, NULL, ot.C_Tax_ID, t.TaxIndicator,
    NULL, NULL, NULL, NULL,
	NULL, NULL,
	NULL, NULL, NULL,
	t.NAME,
	NULL, NULL, NULL, NULL, NULL, NULL,
	NULL, NULL, NULL,
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END,
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END,
    CASE WHEN ot.IsTaxIncluded='Y' THEN NULL ELSE ot.TaxAmt END,
    NULL, NULL,
    NULL,NULL,NULL,NULL,NULL
FROM C_ORDERTAX ot
	INNER JOIN C_TAX_TRL t ON (ot.C_Tax_ID=t.C_Tax_ID);



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
	--iol.MovementQty*b.BOMQty AS QtyInvoiced,
	CASE WHEN bl.IsQtyPercentage = 'N' THEN iol.MovementQty*bl.QtyBOM ELSE iol.MovementQty*(bl.QtyBatch / 100) END AS QtyInvoiced, 	
	--iol.QtyEntered*b.BOMQty AS QtyEntered,
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
	uom.AD_LANGUAGE,
	iol.M_InOut_ID, iol.M_InOutLine_ID,
	iol.Line, p.M_Product_ID,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty,
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
    ol.QtyOrdered, ol.QtyDelivered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(COALESCE(pt.NAME,p.NAME)||Productattribute(iol.M_AttributeSetInstance_ID), c.NAME, iol.Description) AS NAME, -- main line
	CASE WHEN COALESCE(pt.NAME,p.NAME,c.NAME) IS NOT NULL THEN iol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.VALUE AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL,
    iol.C_Campaign_ID, iol.C_Project_ID, iol.C_Activity_ID, iol.C_ProjectPhase_ID, iol.C_ProjectTask_ID
FROM	M_INOUTLINE iol
	INNER JOIN C_UOM_TRL uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_PRODUCT p ON (iol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_PRODUCT_TRL pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_LANGUAGE=pt.AD_LANGUAGE)
    LEFT OUTER JOIN M_ATTRIBUTESETINSTANCE asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_LOCATOR l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_ORDERLINE ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_CHARGE c ON (iol.C_Charge_ID=c.C_Charge_ID)
UNION
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	uom.AD_LANGUAGE,
	iol.M_InOut_ID, iol.M_InOutLine_ID,
	iol.Line+(bl.Line/100) AS Line, p.M_Product_ID,
	--iol.MovementQty*b.BOMQty AS QtyInvoiced,
	CASE WHEN bl.IsQtyPercentage = 'N' THEN iol.MovementQty*bl.QtyBOM ELSE iol.MovementQty*(bl.QtyBatch / 100) END AS QtyInvoiced, 	 
	--iol.QtyEntered*b.BOMQty AS QtyEntered,
	CASE WHEN bl.IsQtyPercentage = 'N' THEN iol.QtyEntered*bl.QtyBOM ELSE iol.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered,
	uom.UOMSymbol,
    NULL, NULL, NULL,
	COALESCE (pt.NAME, p.NAME) AS NAME, -- main line
	b.Description, -- second line
	COALESCE (pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.VALUE AS ProductValue,
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
FROM PP_PRODUCT_BOM b	-- BOM lines
	INNER JOIN M_INOUTLINE iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_PRODUCT bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN PP_PRODUCT_BOMLINE bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_PRODUCT p ON (bl.M_Product_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_TRL uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_PRODUCT_TRL pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_LANGUAGE=pt.AD_LANGUAGE)
    LEFT OUTER JOIN M_ATTRIBUTESETINSTANCE asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_LOCATOR l ON (iol.M_Locator_ID=l.M_Locator_ID);



