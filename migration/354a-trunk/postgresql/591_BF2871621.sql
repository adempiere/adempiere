-- Fix 2871621 - Invoice format not printing description
-- Surround ProductAttribute with COALESCE

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
	'en_US' AS AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID,
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, p.M_Product_ID,
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced,
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered,
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
	COALESCE(c.Name,p.Name||COALESCE(productAttribute(il.M_AttributeSetInstance_ID),''), il.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,p.Name) IS NOT NULL THEN il.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
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
    p.Description as ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_InvoiceLine il
	INNER JOIN C_UOM uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
	LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_Charge c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US' AS AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID,
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(bl.Line/100) AS Line, p.M_Product_ID,
	--il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
	CASE WHEN bl.IsQtyPercentage = 'N' THEN il.QtyInvoiced*bl.QtyBOM ELSE il.QtyInvoiced*(bl.QtyBatch / 100) END AS QtyInvoiced, 
    --il.QtyEntered*b.BOMQty AS QtyEntered,
    CASE WHEN bl.IsQtyPercentage = 'N' THEN il.QtyEntered*bl.QtyBOM ELSE il.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered, 
    uom.UOMSymbol,
	p.Name,	-- main
	b.Description,
	p.DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description as ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
/*FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)*/
FROM PP_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN PP_Product_BOMLine bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_Product p ON (bl.M_Product_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US', il.C_Invoice_ID, il.C_InvoiceLine_ID,
    null, null, null, null,
	il.Line, null,
	null, null, null,
	il.Description,
	null, null, null, null, null, null,
	null, null, null, null, null, null,
    null, null, null, null, null, null, null, null,
    null, null, null, null, null
FROM C_InvoiceLine il
WHERE il.C_UOM_ID IS NULL
UNION   --  empty line
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Invoice_ID, null,
    null, null, null, null,
	9998, null,
	null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null,
    null, null, null, null, null, null, null, null,
    null, null, null, null, null
FROM C_Invoice
UNION   --   tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	'en_US', it.C_Invoice_ID, null,
    it.C_Tax_ID, null, null, t.TaxIndicator,
	9999, null,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END,
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    null, null, null, null, null, null, null, null,
    null, null, null, null, null
FROM C_InvoiceTax it
	INNER JOIN C_Tax t ON (it.C_Tax_ID=t.C_Tax_ID)
;

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
	uom.AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID,
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, p.M_Product_ID,
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced,
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered,
	CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol,
	COALESCE(c.Name,COALESCE(pt.Name,p.Name)||COALESCE(productAttribute(il.M_AttributeSetInstance_ID),''), il.Description) AS Name, -- main line
	CASE WHEN COALESCE(c.Name,pt.Name,p.Name) IS NOT NULL THEN il.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
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
    pt.Description as ProductDescription, p.ImageURL,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_InvoiceLine il
	INNER JOIN C_UOM_Trl uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_Tax_Trl t ON (il.C_Tax_ID=t.C_Tax_ID AND uom.AD_Language=t.AD_Language)
	LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_Charge_Trl c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (il.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID,
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(bl.Line/100) AS Line, p.M_Product_ID,
	--il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
	CASE WHEN bl.IsQtyPercentage = 'N' THEN il.QtyInvoiced*bl.QtyBOM ELSE il.QtyInvoiced*(bl.QtyBatch / 100) END AS QtyInvoiced, 
    --il.QtyEntered*b.BOMQty AS QtyEntered,
    CASE WHEN bl.IsQtyPercentage = 'N' THEN il.QtyEntered*bl.QtyBOM ELSE il.QtyEntered*(bl.QtyBatch / 100) END AS QtyEntered, 
	uom.UOMSymbol,
	COALESCE(pt.Name,p.Name) AS Name,	-- main
	b.Description,
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description as ProductDescription, p.ImageURL,
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
FROM PP_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN PP_Product_BOMLine bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN M_Product p ON (bl.M_Product_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (bl.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment line
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	l.AD_Language, il.C_Invoice_ID, il.C_InvoiceLine_ID,
    null, null, null, null,
	il.Line, null,
	null, null, null,
	il.Description,
	null, null, null, null, null, null,
	null, null,	null, null, null, null,
    null, null, null, null, null, null, null, null,
    null,null,null,null,null
FROM C_InvoiceLine il, AD_Language l
WHERE il.C_UOM_ID IS NULL
  AND l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  empty line
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
	AD_Language, i.C_Invoice_ID, null,
    null, null, null, null,
	9998, null,
	null, null, null,
	null,
	null, null, null, null, null, null,
	null, null,	null, null, null, null,
    null, null, null, null, null, null, null, null,
    null,null,null,null,null
FROM C_Invoice i, AD_Language l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	t.AD_Language, it.C_Invoice_ID, null,
    it.C_Tax_ID, null, null, t.TaxIndicator,
	9999, null,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END,
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    null, null, null, null, null, null, null, null,
    null,null,null,null,null
FROM C_InvoiceTax it
	INNER JOIN C_Tax_Trl t ON (it.C_Tax_ID=t.C_Tax_ID)
;
