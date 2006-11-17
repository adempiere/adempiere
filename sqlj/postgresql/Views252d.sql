-- 
-- VIEW: AD_Field_v 
--
DROP VIEW AD_Field_v;
CREATE OR REPLACE VIEW AD_Field_v 
AS
SELECT t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
	f.Name, f.Description, f.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
	f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
	f.IsEncrypted AS IsEncryptedField, f.ObscureType,
	c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat, c.DefaultValue, c.IsKey, c.IsParent, 
	c.IsMandatory, c.IsIdentifier, c.IsTranslated, c.AD_Reference_Value_ID, 
	c.Callout, c.AD_Reference_ID, c.AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
	c.ReadOnlyLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fg.Name AS FieldGroup, vr.Code AS ValidationCode,
--begin vpj-cd e-evolution 08/09/2005
        f.Included_Tab_ID
--end vpj-cd  e-evolution 08/09/2005
FROM AD_Field f 
  INNER JOIN AD_Tab t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FieldGroup fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
  LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID)
	INNER JOIN AD_Table tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID)
	LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y'
;

-- 
-- VIEW: AD_Field_vt 
--
CREATE OR REPLACE VIEW AD_Field_vt 
AS
SELECT trl.AD_Language, t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
	trl.Name, trl.Description, trl.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
	f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
	f.IsEncrypted AS IsEncryptedField, f.ObscureType,
	c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat, c.DefaultValue, c.IsKey, c.IsParent, 
	c.IsMandatory, c.IsIdentifier, c.IsTranslated, c.AD_Reference_Value_ID, 
	c.Callout, c.AD_Reference_ID, c.AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
	c.ReadOnlyLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fgt.Name AS FieldGroup, vr.Code AS ValidationCode,
--begin vpj-cd e-evolution 08/09/2005
        f.Included_Tab_ID
--end vpj-cd e-evolution 08/09/2005
FROM AD_Field f 
	INNER JOIN AD_Field_Trl trl ON (f.AD_Field_ID = trl.AD_Field_ID)
  INNER JOIN AD_Tab t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FieldGroup_Trl fgt ON 
	(f.AD_FieldGroup_ID = fgt.AD_FieldGroup_ID AND trl.AD_Language=fgt.AD_Language)
  LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID)
	INNER JOIN AD_Table tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID)
	LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y'
;

-- 
-- VIEW: AD_Tab_v 
--
CREATE OR REPLACE VIEW AD_Tab_v 
AS
SELECT t.AD_Tab_ID, t.AD_Window_ID, t.AD_Table_ID, t.Name, t.Description, 
    t.Help, t.SeqNo, t.IsSingleRow, t.HasTree, t.IsInfoTab, tbl.ReplicationType,
    tbl.TableName, tbl.AccessLevel, tbl.IsSecurityEnabled, tbl.IsDeleteable, 
    tbl.IsHighVolume, tbl.IsView, 'N' AS HasAssociation, -- compatibility
    t.IsTranslationTab, t.IsReadOnly, t.AD_Image_ID, t.TabLevel, 
    t.WhereClause, t.OrderByClause, t.CommitWarning, t.ReadOnlyLogic, t.DisplayLogic,
    t.AD_Column_ID, t.AD_Process_ID, t.IsSortTab, t.IsInsertRecord, t.IsAdvancedTab,
    t.AD_ColumnSortOrder_ID, t.AD_ColumnSortYesNo_ID, t.Included_Tab_ID
FROM AD_Tab t 
	INNER JOIN AD_Table tbl ON (t.AD_Table_ID = tbl.AD_Table_ID)
WHERE t.IsActive='Y'
  AND tbl.IsActive='Y'
;

-- 
-- VIEW: AD_Tab_vt 
--
CREATE OR REPLACE VIEW AD_Tab_vt 
AS
SELECT trl.AD_Language, t.AD_Tab_ID, t.AD_Window_ID, t.AD_Table_ID, trl.Name, trl.Description, 
    trl.Help, t.SeqNo, t.IsSingleRow, t.HasTree, t.IsInfoTab, tbl.ReplicationType,
    tbl.TableName, tbl.AccessLevel, tbl.IsSecurityEnabled, tbl.IsDeleteable, 
    tbl.IsHighVolume, tbl.IsView, 'N' AS HasAssociation, -- compatibility
    t.IsTranslationTab, t.IsReadOnly, t.AD_Image_ID, t.TabLevel, 
    t.WhereClause, t.OrderByClause, trl.CommitWarning, t.ReadOnlyLogic, t.DisplayLogic,
    t.AD_Column_ID, t.AD_Process_ID, t.IsSortTab, t.IsInsertRecord, t.IsAdvancedTab,
    t.AD_ColumnSortOrder_ID, t.AD_ColumnSortYesNo_ID, t.Included_Tab_ID
FROM AD_Tab t 
	INNER JOIN AD_Table tbl ON (t.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Tab_Trl trl ON (t.AD_Tab_ID = trl.AD_Tab_ID)
WHERE t.IsActive='Y'
  AND tbl.IsActive='Y'
;

-- 
-- VIEW: AD_User_Roles_v 
--
CREATE OR REPLACE VIEW AD_User_Roles_v 
AS
SELECT	u.Name, r.Name AS RoleName
FROM AD_User_Roles ur
	INNER JOIN AD_User u ON (ur.AD_User_ID=u.AD_User_ID)
	INNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID)
;

-- 
-- VIEW: AD_Org_v 
--
CREATE OR REPLACE VIEW AD_Org_v 
AS
SELECT o.AD_Client_ID, o.AD_Org_ID, 
    o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
    o.Value, o.Name, o.Description, o.IsSummary,
    i.C_Location_ID, i.DUNS, i.TaxID,
    i.PA_Goal_ID, i.Supervisor_ID, i.Parent_Org_ID, 
    i.AD_OrgType_ID, i.M_Warehouse_ID,
    bp.C_BPartner_ID
FROM AD_Org o
    INNER JOIN AD_OrgInfo i ON (o.AD_Org_ID=i.AD_Org_ID)
    LEFT OUTER JOIN C_BPartner bp ON (o.AD_Org_ID=bp.AD_OrgBP_ID)
;

-- 
-- VIEW: AD_Window_vt 
--
CREATE OR REPLACE VIEW AD_Window_vt 
AS 
SELECT trl.AD_Language, 
	bt.AD_Window_ID, trl.Name, trl.Description, trl.Help, bt.WindowType, 
	bt.AD_Color_ID, bt.AD_Image_ID, bt.IsActive, bt.WinWidth, bt.WinHeight,
    bt.IsSOTrx
FROM AD_Window bt
	INNER JOIN AD_Window_Trl trl ON (bt.AD_Window_ID=trl.AD_Window_ID)
WHERE bt.IsActive='Y'
;

-- 
-- VIEW: C_Invoice_Header_v 
--
CREATE OR REPLACE VIEW C_Invoice_Header_v 
AS
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
	'en_US' AS AD_Language,
	i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus,  i.C_DocType_ID,
	i.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	i.C_Order_ID, i.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	i.DateInvoiced,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo,
	i.Description,
	i.POReference,
	i.DateOrdered,
	i.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines,
	i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID,
	i.IsTaxIncluded,
	i.C_Campaign_ID,
	i.C_Project_ID,
	i.C_Activity_ID,
	i.IsPaid
FROM C_Invoice i
	INNER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_PaymentTerm pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
	INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (i.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (i.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (i.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
;

-- 
-- VIEW: C_Invoice_Header_vt
--
CREATE OR REPLACE VIEW C_Invoice_Header_vt 
AS
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
	dt.AD_Language,
	i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus,  i.C_DocType_ID,
	i.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	i.C_Order_ID, i.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	i.DateInvoiced,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo,
	i.Description,
	i.POReference,
	i.DateOrdered,
	i.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines,
	i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID,
	i.IsTaxIncluded,
	i.C_Campaign_ID,
	i.C_Project_ID,
	i.C_Activity_ID,
	i.IsPaid
FROM C_Invoice i
	INNER JOIN C_DocType_Trl dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_PaymentTerm_Trl pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID AND dt.AD_Language=pt.AD_Language)
	INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (i.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (i.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (i.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
;

-- 
-- VIEW: C_Invoice_LineTax_v 
--
CREATE OR REPLACE VIEW C_Invoice_LineTax_v 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US' AS AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, 
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(il.M_AttributeSetInstance_ID), il.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN il.Description END AS Description, -- second line
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
    p.Description as ProductDescription, p.ImageURL
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
	il.Line+(b.Line/100) AS Line,
	il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
    il.QtyEntered*b.BOMQty AS QtyEntered,
    uom.UOMSymbol,
	p.Name,	-- main
	b.Description,
	p.DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description as ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	'en_US', il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    null, null, null, null,
	il.Line,
	null, null, null,
	il.Description,
	null, null, null, null, null, null,
	null, null, null, null, null, null,
    null, null, null, null, null, null, null, null
FROM C_InvoiceLine il
WHERE il.C_UOM_ID IS NULL
UNION   --  empty line
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Invoice_ID, null,
    null, null, null, null,
	9998,
	null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null,
    null, null, null, null, null, null, null, null
FROM C_Invoice
UNION   --   tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	'en_US', it.C_Invoice_ID, null,
    it.C_Tax_ID, null, null, t.TaxIndicator,
	9999,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    null, null, null, null, null, null, null, null
FROM C_InvoiceTax it
	INNER JOIN C_Tax t ON (it.C_Tax_ID=t.C_Tax_ID)
;

-- 
-- VIEW: C_Invoice_LineTax_vt
--
CREATE OR REPLACE VIEW C_Invoice_LineTax_vt 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line, 
	CASE WHEN il.QtyInvoiced<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyInvoiced END AS QtyInvoiced, 
    CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN il.QtyEntered END AS QtyEntered, 
	CASE WHEN il.QtyEntered<>0 OR il.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,COALESCE(pt.Name,p.Name)||productAttribute(il.M_AttributeSetInstance_ID), il.Description) AS Name, -- main line
	CASE WHEN COALESCE(pt.Name,p.Name) IS NOT NULL THEN il.Description END AS Description, -- second line
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
    pt.Description as ProductDescription, p.ImageURL
FROM C_InvoiceLine il
	INNER JOIN C_UOM_Trl uom ON (il.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
    LEFT OUTER JOIN C_Tax_Trl t ON (il.C_Tax_ID=t.C_Tax_ID AND uom.AD_Language=t.AD_Language)
	LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN C_Charge c ON (il.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (il.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (il.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (il.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  bom lines
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	uom.AD_Language,
	il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    il.C_Tax_ID, il.TaxAmt, il.LineTotalAmt, t.TaxIndicator,
	il.Line+(b.Line/100) AS Line,
	il.QtyInvoiced*b.BOMQty AS QtyInvoiced,
    il.QtyEntered*b.BOMQty AS QtyEntered,
	uom.UOMSymbol,
	COALESCE(pt.Name,p.Name) AS Name,	-- main
	b.Description,
	COALESCE(pt.DocumentNote,p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null,
    il.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description as ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_InvoiceLine il ON (b.M_Product_ID=il.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=il.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (b.M_ProductBOM_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN C_Tax t ON (il.C_Tax_ID=t.C_Tax_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (il.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
UNION   --  comment line
SELECT il.AD_Client_ID, il.AD_Org_ID, il.IsActive, il.Created, il.CreatedBy, il.Updated, il.UpdatedBy,
	l.AD_Language, il.C_Invoice_ID, il.C_InvoiceLine_ID, 
    null, null, null, null,
	il.Line,
	null, null, null,
	il.Description,
	null, null, null, null, null, null,
	null, null,	null, null, null, null,
    null, null, null, null, null, null, null, null
FROM C_InvoiceLine il, AD_Language l
WHERE il.C_UOM_ID IS NULL
  AND l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  empty line
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
	AD_Language, i.C_Invoice_ID, null,
    null, null, null, null,
	9998,
	null, null, null,
	null,
	null, null, null, null, null, null,
	null, null,	null, null, null, null,
    null, null, null, null, null, null, null, null
FROM C_Invoice i, AD_Language l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION   --  tax lines
SELECT it.AD_Client_ID, it.AD_Org_ID, it.IsActive, it.Created, it.CreatedBy, it.Updated, it.UpdatedBy,
	t.AD_Language, it.C_Invoice_ID, null,
    it.C_Tax_ID, null, null, t.TaxIndicator,
	9999,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null,
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN it.TaxAmt ELSE it.TaxBaseAmt END, 
    CASE WHEN it.IsTaxIncluded='Y' THEN NULL ELSE it.TaxAmt END,
    null, null, null, null, null, null, null, null
FROM C_InvoiceTax it
	INNER JOIN C_Tax_Trl t ON (it.C_Tax_ID=t.C_Tax_ID)
;

-- 
-- VIEW: C_Order_Header_v 
--
CREATE OR REPLACE VIEW C_Order_Header_v 
AS
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	'en_US' AS AD_Language,
	o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
	bp.ReferenceNo,
    --  Bill to
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID,
    bbp.Value AS Bill_BPValue, bbp.TaxID AS Bill_BPTaxID,
    bbp.Name AS Bill_Name, bbp.Name2 AS Bill_Name2,
    bbpc.Title AS Bill_Title, bbpc.Phone AS Bill_Phone,
    NULLIF (bbpc.Name, bbp.Name) AS Bill_ContactName,
    bbpl.C_Location_ID AS Bill_C_Location_ID,
	o.Description,
	o.POReference,
	o.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	o.C_Charge_ID, o.ChargeAmt,
	o.TotalLines,
	o.GrandTotal, o.GrandTotal AS AmtInWords,
	o.M_PriceList_ID,
	o.IsTaxIncluded,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, o.InvoiceRule
FROM C_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_PaymentTerm pt ON (o.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID)    
;

-- 
-- VIEW: C_Order_Header_vt
--
CREATE OR REPLACE VIEW C_Order_Header_vt 
AS
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	dt.AD_Language,
	o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
    --  Bill to
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID,
    bbp.Value AS Bill_BPValue, bbp.TaxID AS Bill_BPTaxID,
    bbp.Name AS Bill_Name, bbp.Name2 AS Bill_Name2,
    bbpc.Title AS Bill_Title, bbpc.Phone AS Bill_Phone,
    NULLIF (bbpc.Name, bbp.Name) AS Bill_ContactName,
    bbpl.C_Location_ID AS Bill_C_Location_ID,
	bp.ReferenceNo,
	o.Description,
	o.POReference,
	o.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	o.C_Charge_ID, o.ChargeAmt,
	o.TotalLines,
	o.GrandTotal, o.GrandTotal AS AmtInWords,
	o.M_PriceList_ID,
	o.IsTaxIncluded,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, o.InvoiceRule
FROM C_Order o
	INNER JOIN C_DocType_Trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_PaymentTerm_Trl pt ON (o.C_PaymentTerm_ID=pt.C_PaymentTerm_ID AND dt.AD_Language=pt.AD_Language)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID)    
;


-- 
-- VIEW: C_Order_LineTax_v 
--
CREATE OR REPLACE VIEW C_Order_LineTax_v 
AS
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	'en_US' AS AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, t.TaxIndicator,
    ol.C_BPartner_ID, ol.C_BPartner_Location_ID, bp.Name AS BPName, bpl.C_Location_ID,
	ol.Line, 
	CASE WHEN ol.QtyOrdered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered END AS QtyOrdered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyEntered END AS QtyEntered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(ol.M_AttributeSetInstance_ID), ol.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN ol.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
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
    p.Description as ProductDescription, p.ImageURL
FROM C_OrderLine ol
	INNER JOIN C_UOM uom ON (ol.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Order i ON (ol.C_Order_ID=i.C_Order_ID)
	LEFT OUTER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (ol.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
	LEFT OUTER JOIN C_Charge c ON (ol.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (ol.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	INNER JOIN C_BPartner bp ON (ol.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bpl ON (ol.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_Tax t ON (ol.C_Tax_ID=t.C_Tax_ID)
UNION
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	'en_US' AS AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, null,
    null, null, null, null,
	ol.Line+(b.Line/100) AS Line,
	ol.QtyOrdered*b.BOMQty AS QtyInvoiced, ol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol,
	p.Name,	-- main
	b.Description,
	p.DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null, p.Description as ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_OrderLine ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	'en_US', C_Order_ID, null, null, null,
	null,
	null, null, null,
    null, null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null, null, null
FROM C_Order
UNION
SELECT ot.AD_Client_ID, ot.AD_Org_ID, ot.IsActive, ot.Created, ot.CreatedBy, ot.Updated, ot.UpdatedBy,
	'en_US', ot.C_Order_ID, null, ot.C_Tax_ID, t.TaxIndicator,
    null, null, null, null,
	null,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN NULL ELSE ot.TaxAmt END,
    null, null
FROM C_OrderTax ot
	INNER JOIN C_Tax t ON (ot.C_Tax_ID=t.C_Tax_ID)
;

-- 
-- VIEW: C_Order_LineTax_vt
--
CREATE OR REPLACE VIEW C_Order_LineTax_vt 
AS
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	uom.AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, t.TaxIndicator,
    ol.C_BPartner_ID, ol.C_BPartner_Location_ID, bp.Name AS BPName, bpl.C_Location_ID,
	ol.Line, 
	CASE WHEN ol.QtyOrdered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered END AS QtyOrdered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN ol.QtyEntered END AS QtyEntered, 
    CASE WHEN ol.QtyEntered<>0 OR ol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
	COALESCE(c.Name,p.Name||productAttribute(ol.M_AttributeSetInstance_ID), ol.Description) AS Name, -- main line
	CASE WHEN COALESCE(pt.Name, p.Name) IS NOT NULL THEN ol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, COALESCE(pp.VendorProductNo,p.Value) AS ProductValue,
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
    pt.Description as ProductDescription, p.ImageURL
From	C_OrderLine ol
	INNER JOIN C_UOM_Trl uom ON (ol.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN C_Order i ON (ol.C_Order_ID=i.C_Order_ID)
	LEFT OUTER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (ol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
	LEFT OUTER JOIN S_ResourceAssignment ra ON (ol.S_ResourceAssignment_ID=ra.S_ResourceAssignment_ID)
	LEFT OUTER JOIN C_Charge c ON (ol.C_Charge_ID=c.C_Charge_ID)
    LEFT OUTER JOIN C_BPartner_Product pp ON (ol.M_Product_ID=pp.M_Product_ID AND i.C_BPartner_ID=pp.C_BPartner_ID)
	INNER JOIN C_BPartner bp ON (ol.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bpl ON (ol.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_Tax_Trl t ON (ol.C_Tax_ID=t.C_Tax_ID AND uom.AD_Language=t.AD_Language)
UNION
SELECT ol.AD_Client_ID, ol.AD_Org_ID, ol.IsActive, ol.Created, ol.CreatedBy, ol.Updated, ol.UpdatedBy,
	uom.AD_Language,
	ol.C_Order_ID, ol.C_OrderLine_ID, ol.C_Tax_ID, null,
    null, null, null, null,
	ol.Line+(b.Line/100) AS Line,
	ol.QtyOrdered*b.BOMQty AS QtyInvoiced, ol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol,
	COALESCE(pt.Name, p.Name) AS Name,	-- main
	b.Description,
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, p.UPC, p.SKU, p.Value AS ProductValue,
	null, null, null, null, null, null, null, pt.Description AS ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN C_OrderLine ol ON (b.M_Product_ID=ol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=ol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsInvoicePrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (b.M_ProductBOM_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
UNION
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	l.AD_Language, o.C_Order_ID, null, null, null,
	null,
	null, null, null,
    null, null, null, null,
	null,
	null, null, null, null, null, null,
	null, null, null, null, null, null, null, null
FROM C_Order o, AD_Language l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
UNION
SELECT ot.AD_Client_ID, ot.AD_Org_ID, ot.IsActive, ot.Created, ot.CreatedBy, ot.Updated, ot.UpdatedBy,
	t.AD_Language, ot.C_Order_ID, null, ot.C_Tax_ID, t.TaxIndicator,
    null, null, null, null,
	null,
	null, null, null,
	t.Name,
	null, null, null, null, null, null,
	null, null, null, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN ot.TaxAmt ELSE ot.TaxBaseAmt END, 
    CASE WHEN ot.IsTaxIncluded='Y' THEN NULL ELSE ot.TaxAmt END,
    null, null
FROM C_OrderTax ot
	INNER JOIN C_Tax_Trl t ON (ot.C_Tax_ID=t.C_Tax_ID)
;

-- 
-- VIEW: C_PaySelection_Check_v 
--
CREATE OR REPLACE VIEW C_PaySelection_Check_v 
AS
SELECT psc.AD_Client_ID, psc.AD_Org_ID, 
	'en_US' AS AD_Language,
	psc.C_PaySelection_ID, psc.C_PaySelectionCheck_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 0 AS C_DocType_ID,
	bp.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpartnerRemitLocation(bp.C_BPartner_ID) AS C_Location_ID,
	bp.ReferenceNo, bp.POReference,
	ps.PayDate,
	psc.PayAmt, psc.PayAmt AS AmtInWords,
	psc.Qty, psc.PaymentRule, psc.DocumentNo
FROM C_PaySelectionCheck psc
	INNER JOIN C_PaySelection ps ON (psc.C_PaySelection_ID=ps.C_PaySelection_ID)
	INNER JOIN C_BPartner bp ON (psc.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (psc.AD_Org_ID=oi.AD_Org_ID)
;

-- 
-- VIEW: C_PaySelection_Check_v 
--
CREATE OR REPLACE VIEW C_PaySelection_Check_vt 
AS
SELECT psc.AD_Client_ID, psc.AD_Org_ID, 
	l.AD_Language,
	psc.C_PaySelection_ID, psc.C_PaySelectionCheck_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 0 AS C_DocType_ID,
	bp.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpartnerRemitLocation(bp.C_BPartner_ID) AS C_Location_ID,
	bp.ReferenceNo, bp.POReference,
	ps.PayDate,
	psc.PayAmt, psc.PayAmt AS AmtInWords,
	psc.Qty, psc.PaymentRule, psc.DocumentNo
FROM C_PaySelectionCheck psc
	INNER JOIN C_PaySelection ps ON (psc.C_PaySelection_ID=ps.C_PaySelection_ID)
	INNER JOIN C_BPartner bp ON (psc.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (psc.AD_Org_ID=oi.AD_Org_ID)
    LEFT OUTER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID AND bpg.AD_Language=l.AD_Language)
;

-- 
-- VIEW: C_PaySelection_Remittance_v 
--
CREATE OR REPLACE VIEW C_PaySelection_Remittance_v 
AS
SELECT psl.AD_Client_ID, psl.AD_Org_ID, 
	'en_US' AS AD_Language,
	psl.C_PaySelection_ID, psl.C_PaySelectionLine_ID, 
	psl.C_PaySelectionCheck_ID,
	psl.PaymentRule, psl.Line, psl.OpenAmt, psl.PayAmt, psl.DiscountAmt, psl.DifferenceAmt, 
	i.C_BPartner_ID, i.DocumentNo, i.DateInvoiced, i.GrandTotal, i.GrandTotal AS AmtInWords
FROM C_PaySelectionLine psl
  INNER JOIN C_Invoice i ON (psl.C_Invoice_ID=i.C_Invoice_ID)
;

-- 
-- VIEW: C_PaySelection_Remittance_vt
--
CREATE OR REPLACE VIEW C_PaySelection_Remittance_vt 
AS
SELECT psl.AD_Client_ID, psl.AD_Org_ID, 
	l.AD_Language,
	psl.C_PaySelection_ID, psl.C_PaySelectionLine_ID, 
	psl.C_PaySelectionCheck_ID,
	psl.PaymentRule, psl.Line, psl.OpenAmt, psl.PayAmt, psl.DiscountAmt, psl.DifferenceAmt, 
	i.C_BPartner_ID, i.DocumentNo, i.DateInvoiced, i.GrandTotal, i.GrandTotal AS AmtInWords
FROM C_PaySelectionLine psl
  INNER JOIN C_Invoice i ON (psl.C_Invoice_ID=i.C_Invoice_ID), AD_Language l
WHERE l.IsBaseLanguage='N' AND l.IsSystemLanguage='Y'
;

-- 
-- VIEW: M_InOut_Header_v 
--
CREATE OR REPLACE VIEW M_InOut_Header_v 
AS
SELECT io.AD_Client_ID, io.AD_Org_ID, io.IsActive, io.Created, io.CreatedBy, io.Updated, io.UpdatedBy,
	'en_US' AS AD_Language,
	io.M_InOut_ID, io.IsSOTrx, io.DocumentNo, io.DocStatus,	 io.C_DocType_ID,
	io.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	io.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	io.C_Order_ID, 
	io.MovementDate, io.MovementType,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
	bp.ReferenceNo,
	io.Description,
	io.POReference,
	io.DateOrdered,
	io.M_Shipper_ID, io.DeliveryRule, io.DeliveryViaRule, io.PriorityRule
FROM M_InOut io
	INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_BPartner bp ON (io.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (io.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (io.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (io.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID)
;

-- 
-- VIEW: M_InOut_Header_vt 
--
CREATE OR REPLACE VIEW M_InOut_Header_vt 
AS
SELECT io.AD_Client_ID, io.AD_Org_ID, io.IsActive, io.Created, io.CreatedBy, io.Updated, io.UpdatedBy,
	dt.AD_Language,
	io.M_InOut_ID, io.IsSOTrx, io.DocumentNo, io.DocStatus,	 io.C_DocType_ID,
	io.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	io.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	io.C_Order_ID, bpc.Phone,
	io.MovementDate, io.MovementType,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
	bp.ReferenceNo,
	io.Description,
	io.POReference,
	io.DateOrdered,
	io.M_Shipper_ID, io.DeliveryRule, io.DeliveryViaRule, io.PriorityRule
FROM M_InOut io
	INNER JOIN C_DocType_Trl dt ON (io.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_BPartner bp ON (io.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (io.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (io.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (io.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID)
;

-- 
-- VIEW: M_InOut_Line_v 
--
CREATE OR REPLACE VIEW M_InOut_Line_v 
AS
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	'en_US' AS AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty, 
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
    ol.QtyOrdered, ol.QtyDelivered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(p.Name||productAttribute(iol.M_AttributeSetInstance_ID), c.Name, iol.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN iol.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL
FROM M_InOutLine iol
	INNER JOIN C_UOM uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_Charge c ON (iol.C_Charge_ID=c.C_Charge_ID)
UNION   --  BOM lines
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	'en_US' AS AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line+(b.Line/100) AS Line,
	iol.MovementQty*b.BOMQty AS QtyInvoiced, iol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol, 
    null, null, null,
	p.Name, -- main line
	b.Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    p.Description AS ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
;

-- 
-- VIEW: M_InOut_Line_vt
--
CREATE OR REPLACE VIEW M_InOut_Line_vt 
AS
SELECT iol.AD_Client_ID, iol.AD_Org_ID, iol.IsActive, iol.Created, iol.CreatedBy, iol.Updated, iol.UpdatedBy,
	uom.AD_Language,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.MovementQty END AS MovementQty, 
	CASE WHEN iol.QtyEntered<>0 OR iol.M_Product_ID IS NOT NULL THEN iol.QtyEntered END AS QtyEntered, 
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN uom.UOMSymbol END AS UOMSymbol, 
    ol.QtyOrdered, ol.QtyDelivered,
	CASE WHEN iol.MovementQty<>0 OR iol.M_Product_ID IS NOT NULL THEN ol.QtyOrdered-ol.QtyDelivered END AS QtyBackOrdered,
	COALESCE(COALESCE(pt.Name,p.Name)||productAttribute(iol.M_AttributeSetInstance_ID), c.Name, iol.Description) AS Name, -- main line
	CASE WHEN COALESCE(pt.Name,p.Name) IS NOT NULL THEN iol.Description END AS Description, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL
From	M_InOutLine iol
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
	iol.Line+(b.Line/100) AS Line,
	iol.MovementQty*b.BOMQty AS QtyInvoiced, iol.QtyEntered*b.BOMQty AS QtyEntered, uom.UOMSymbol, 
    null, null, null,
	COALESCE (pt.Name, p.Name) AS Name, -- main line
	b.Description, -- second line
	COALESCE (pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    pt.Description AS ProductDescription, p.ImageURL
FROM M_Product_BOM b	-- BOM lines
	INNER JOIN M_InOutLine iol ON (b.M_Product_ID=iol.M_Product_ID)
	INNER JOIN M_Product bp ON (bp.M_Product_ID=iol.M_Product_ID -- BOM Product
		AND bp.IsBOM='Y' AND bp.IsVerified='Y' AND bp.IsPickListPrintDetails='Y')
	INNER JOIN M_Product p ON (b.M_ProductBOM_ID=p.M_Product_ID) -- BOM line product
	INNER JOIN C_UOM_Trl uom ON (p.C_UOM_ID=uom.C_UOM_ID)
	INNER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
;

-- 
-- VIEW: C_Project_Header_v 
--
CREATE OR REPLACE VIEW C_Project_Header_v 
AS
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created, p.CreatedBy, p.Updated, p.UpdatedBy, 
	'en_US' AS AD_Language, p.C_Project_ID,
    p.Value, p.Name AS ProjectName, p.Description, p.Note, p.IsSummary, p.ProjectCategory,
    oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    p.C_ProjectType_ID, pjt.Name AS ProjectTypeName, p.C_Phase_ID, pjp.Name AS ProjectPhaseName,
	p.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	p.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
	bp.ReferenceNo,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
    p.POReference,
    p.C_Currency_ID, p.M_PriceList_Version_ID,
    p.C_Campaign_ID,
    p.PlannedAmt, p.PlannedQty, p.PlannedMarginAmt, p.InvoicedAmt, p.InvoicedQty, p.ProjectBalanceAmt,
    p.IsCommitment, p.CommittedAmt, p.CommittedQty, p.DateContract, p.DateFinish, p.IsCommitCeiling,
    p.M_Warehouse_ID
FROM C_Project p
	LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (p.AD_Org_ID=oi.AD_Org_ID)
    LEFT OUTER JOIN C_ProjectType pjt ON (p.C_ProjectType_ID=pjt.C_ProjectType_ID)
    LEFT OUTER JOIN C_Phase pjp ON (p.C_Phase_ID=pjp.C_Phase_ID)
	LEFT OUTER JOIN AD_User u ON (p.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	LEFT OUTER JOIN AD_User bpc ON (p.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	LEFT OUTER JOIN C_BPartner_Location bpl ON (p.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_PaymentTerm pt ON (p.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
;

-- 
-- VIEW: C_Project_Header_vt
--
CREATE OR REPLACE VIEW C_Project_Header_vt 
AS
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created, p.CreatedBy, p.Updated, p.UpdatedBy, 
	pt.AD_Language, p.C_Project_ID,
    p.Value, p.Name AS ProjectName, p.Description, p.Note, p.IsSummary, p.ProjectCategory,
    oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    p.C_ProjectType_ID, pjt.Name AS ProjectTypeName, p.C_Phase_ID, pjp.Name AS ProjectPhaseName,
	p.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	p.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
	bp.ReferenceNo,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
    p.POReference,
    p.C_Currency_ID, p.M_PriceList_Version_ID,
    p.C_Campaign_ID,
    p.PlannedAmt, p.PlannedQty, p.PlannedMarginAmt, p.InvoicedAmt, p.InvoicedQty, p.ProjectBalanceAmt,
    p.IsCommitment, p.CommittedAmt, p.CommittedQty, p.DateContract, p.DateFinish, p.IsCommitCeiling,
    p.M_Warehouse_ID
FROM C_Project p
	LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (p.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN C_PaymentTerm_Trl pt ON (p.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
    LEFT OUTER JOIN C_ProjectType pjt ON (p.C_ProjectType_ID=pjt.C_ProjectType_ID)
    LEFT OUTER JOIN C_Phase pjp ON (p.C_Phase_ID=pjp.C_Phase_ID)
	LEFT OUTER JOIN AD_User u ON (p.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	LEFT OUTER JOIN AD_User bpc ON (p.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	LEFT OUTER JOIN C_BPartner_Location bpl ON (p.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
;

-- 
-- VIEW: C_Project_Details_v 
--
CREATE OR REPLACE VIEW C_Project_Details_v 
AS
SELECT pl.AD_Client_ID, pl.AD_Org_ID, pl.IsActive, pl.Created, pl.CreatedBy, pl.Updated, pl.UpdatedBy,
	'en_US' AS AD_Language,
    pj.C_Project_ID, pl.C_ProjectLine_ID,
    pl.Line, 
    pl.PlannedQty, pl.PlannedPrice, pl.PlannedAmt, pl.PlannedMarginAmt,
    pl.CommittedAmt,
    pl.M_Product_ID,
	COALESCE(p.Name, pl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN pl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    pl.M_Product_Category_ID,
    pl.InvoicedAmt, pl.InvoicedQty, pl.CommittedQty
FROM C_ProjectLine pl
  INNER JOIN C_Project pj ON (pl.C_Project_ID=pj.C_Project_ID)
  LEFT OUTER JOIN M_Product p ON (pl.M_Product_ID=p.M_Product_ID)
WHERE pl.IsPrinted='Y'
;

-- 
-- VIEW: C_Project_Details_vt
--
CREATE OR REPLACE VIEW C_Project_Details_vt 
AS
SELECT pl.AD_Client_ID, pl.AD_Org_ID, pl.IsActive, pl.Created, pl.CreatedBy, pl.Updated, pl.UpdatedBy,
	'en_US' AS AD_Language,
    pj.C_Project_ID, pl.C_ProjectLine_ID,
    pl.Line, 
    pl.PlannedQty, pl.PlannedPrice, pl.PlannedAmt, pl.PlannedMarginAmt,
    pl.CommittedAmt,
    pl.M_Product_ID,
	COALESCE(p.Name, pl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN pl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    pl.M_Product_Category_ID,
    pl.InvoicedAmt, pl.InvoicedQty, pl.CommittedQty
FROM C_ProjectLine pl
  INNER JOIN C_Project pj ON (pl.C_Project_ID=pj.C_Project_ID)
  LEFT OUTER JOIN M_Product p ON (pl.M_Product_ID=p.M_Product_ID)
WHERE pl.IsPrinted='Y'  
;

--
--  VIEW: RfQ Response Header
--
CREATE OR REPLACE VIEW C_RfQResponse_v
AS
SELECT rr.C_RfQResponse_ID, rr.C_RfQ_ID, 
    rr.AD_Client_ID, rr.AD_Org_ID, rr.IsActive, rr.Created, rr.CreatedBy, rr.Updated, rr.UpdatedBy,
	'en_US' AS AD_Language,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    r.Name, r.Description, r.Help,
    r.C_Currency_ID, c.ISO_Code,
    r.DateResponse, r.DateWorkStart, r.DeliveryDays,
    rr.C_BPartner_ID, bp.Name AS BPName, bp.Name2 AS BPName2,
    rr.C_BPartner_Location_ID, bpl.C_Location_ID,
    rr.AD_User_ID, bpc.Title, bpc.Phone,
    NULLIF (bpc.Name, bp.Name) AS ContactName
FROM C_RfQResponse rr
  INNER JOIN C_RfQ r ON (rr.C_RfQ_ID=r.C_RfQ_ID)
  INNER JOIN AD_OrgInfo oi ON (rr.AD_Org_ID=oi.AD_Org_ID)
  INNER JOIN C_Currency c ON (r.C_Currency_ID=c.C_Currency_ID)
  INNER JOIN C_BPartner bp ON (rr.C_BPartner_ID=bp.C_BPartner_ID)
  INNER JOIN C_BPartner_Location bpl ON (rr.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
  LEFT OUTER JOIN AD_User bpc ON (rr.AD_User_ID=bpc.AD_User_ID)
;

--
--  VIEW: RfQ Response Header (trl)
--
CREATE OR REPLACE VIEW C_RfQResponse_vt
AS
SELECT rr.C_RfQResponse_ID, rr.C_RfQ_ID, 
    rr.AD_Client_ID, rr.AD_Org_ID, rr.IsActive, rr.Created, rr.CreatedBy, rr.Updated, rr.UpdatedBy,
	'en_US' AS AD_Language,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    r.Name, r.Description, r.Help,
    r.C_Currency_ID, c.ISO_Code,
    r.DateResponse, r.DateWorkStart, r.DeliveryDays,
    rr.C_BPartner_ID, bp.Name AS BPName, bp.Name2 AS BPName2,
    rr.C_BPartner_Location_ID, bpl.C_Location_ID,
    rr.AD_User_ID, bpc.Title, bpc.Phone,
    NULLIF (bpc.Name, bp.Name) AS ContactName
FROM C_RfQResponse rr
  INNER JOIN C_RfQ r ON (rr.C_RfQ_ID=r.C_RfQ_ID)
  INNER JOIN AD_OrgInfo oi ON (rr.AD_Org_ID=oi.AD_Org_ID)
  INNER JOIN C_Currency c ON (r.C_Currency_ID=c.C_Currency_ID)
  INNER JOIN C_BPartner bp ON (rr.C_BPartner_ID=bp.C_BPartner_ID)
  INNER JOIN C_BPartner_Location bpl ON (rr.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
  LEFT OUTER JOIN AD_User bpc ON (rr.AD_User_ID=bpc.AD_User_ID)
;

--
--  VIEW: RfQ Response Line
--
CREATE OR REPLACE VIEW C_RfQResponseLine_v
AS
SELECT rrl.C_RfQResponse_ID, rrl.C_RfQResponseLine_ID, rrl.C_RfQLine_ID, 
    rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rrl.AD_Client_ID, rrl.AD_Org_ID, rrl.IsActive, rrl.Created, rrl.CreatedBy, rrl.Updated, rrl.UpdatedBy,
	'en_US' AS AD_Language,
    rl.Line,
    rl.M_Product_ID, rl.M_AttributeSetInstance_ID,
	COALESCE(p.Name||productAttribute(rl.M_AttributeSetInstance_ID), rl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN rl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    rl.Help,
    rl.DateWorkStart, rl.DeliveryDays,
    q.C_UOM_ID, uom.UOMSymbol, q.BenchmarkPrice,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
  INNER JOIN C_RfQResponseLine rrl ON (rq.C_RfQResponseLine_ID=rrl.C_RfQResponseLine_ID)
  INNER JOIN C_RfQLine rl ON (rrl.C_RfQLine_ID=rl.C_RfQLine_ID)
  LEFT OUTER JOIN M_Product p ON (rl.M_Product_ID=p.M_Product_ID)
WHERE rq.IsActive='Y' AND q.IsActive='Y'
  AND rrl.IsActive='Y' AND rl.IsActive='Y'
;

--
--  VIEW: RfQ Response Line (trl)
--
CREATE OR REPLACE VIEW C_RfQResponseLine_vt
AS
SELECT rrl.C_RfQResponse_ID, rrl.C_RfQResponseLine_ID, rrl.C_RfQLine_ID, 
    rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rrl.AD_Client_ID, rrl.AD_Org_ID, rrl.IsActive, rrl.Created, rrl.CreatedBy, rrl.Updated, rrl.UpdatedBy,
	'en_US' AS AD_Language,
    rl.Line,
    rl.M_Product_ID, rl.M_AttributeSetInstance_ID,
	COALESCE(p.Name||productAttribute(rl.M_AttributeSetInstance_ID), rl.Description) AS Name, -- main line
	CASE WHEN p.Name IS NOT NULL THEN rl.Description END AS Description, -- second line
	p.DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
    rl.Help,
    rl.DateWorkStart, rl.DeliveryDays,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
  INNER JOIN C_RfQResponseLine rrl ON (rq.C_RfQResponseLine_ID=rrl.C_RfQResponseLine_ID)
  INNER JOIN C_RfQLine rl ON (rrl.C_RfQLine_ID=rl.C_RfQLine_ID)
  LEFT OUTER JOIN M_Product p ON (rl.M_Product_ID=p.M_Product_ID)
WHERE rq.IsActive='Y' AND q.IsActive='Y'
  AND rrl.IsActive='Y' AND rl.IsActive='Y'
;

--
--  VIEW: RfQ Reponse Line Qty
--
CREATE OR REPLACE VIEW C_RfQResponseLineQty_v
AS
SELECT rq.C_RfQResponseLine_ID, rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rq.AD_Client_ID, rq.AD_Org_ID, rq.IsActive, rq.Created, rq.CreatedBy, rq.Updated, rq.UpdatedBy,
	'en_US' AS AD_Language,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
WHERE rq.IsActive='Y' AND q.IsActive='Y'
;

--
--  VIEW: RfQ Reponse Line Qty (trl)
--
CREATE OR REPLACE VIEW C_RfQResponseLineQty_vt
AS
SELECT rq.C_RfQResponseLine_ID, rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rq.AD_Client_ID, rq.AD_Org_ID, rq.IsActive, rq.Created, rq.CreatedBy, rq.Updated, rq.UpdatedBy,
	'en_US' AS AD_Language,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
WHERE rq.IsActive='Y' AND q.IsActive='Y'
;


-- 
-- VIEW: M_InOutConfirm_v 
--
CREATE OR REPLACE VIEW M_InOutConfirm_v 
AS
SELECT ioc.AD_Client_ID, ioc.AD_Org_ID, ioc.IsActive, ioc.Created, ioc.CreatedBy, ioc.Updated, ioc.UpdatedBy,
  'en_US' AS AD_Language,
  ioc.M_InOutConfirm_ID,
  ioc.DocumentNo, ioc.ConfirmType,
  ioc.IsApproved, ioc.IsCancelled, ioc.Description,
  --
  io.M_InOut_ID, io.Description AS ShipDescription,
  io.C_BPartner_ID, io.C_BPartner_Location_ID, io.AD_User_ID,
  io.SalesRep_ID, io.C_DocType_ID, dt.PrintName AS DocumentType,
  io.C_Order_ID, io.DateOrdered, io.MovementDate, io.MovementType, 
  io.M_Warehouse_ID, io.POReference,
  io.DeliveryRule, io.FreightCostRule,
  io.DeliveryViaRule, io.M_Shipper_ID, PriorityRule,
  ioc.Processed
FROM M_InOutConfirm ioc
  INNER JOIN M_InOut io ON (ioc.M_InOut_ID=io.M_InOut_ID)
  INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID)
;

-- 
-- VIEW: M_InOutConfirm_vt
--
CREATE OR REPLACE VIEW M_InOutConfirm_vt 
AS
SELECT ioc.AD_Client_ID, ioc.AD_Org_ID, ioc.IsActive, ioc.Created, ioc.CreatedBy, ioc.Updated, ioc.UpdatedBy,
  dt.AD_Language,
  ioc.M_InOutConfirm_ID,
  ioc.DocumentNo, ioc.ConfirmType,
  ioc.IsApproved, ioc.IsCancelled, ioc.Description,
  --
  io.M_InOut_ID, io.Description AS ShipDescription,
  io.C_BPartner_ID, io.C_BPartner_Location_ID, io.AD_User_ID,
  io.SalesRep_ID, io.C_DocType_ID, dt.PrintName AS DocumentType,
  io.C_Order_ID, io.DateOrdered, io.MovementDate, io.MovementType, 
  io.M_Warehouse_ID, io.POReference,
  io.DeliveryRule, io.FreightCostRule,
  io.DeliveryViaRule, io.M_Shipper_ID, PriorityRule,
  ioc.Processed
FROM M_InOutConfirm ioc
  INNER JOIN M_InOut io ON (ioc.M_InOut_ID=io.M_InOut_ID)
  INNER JOIN C_DocType_Trl dt ON (io.C_DocType_ID=dt.C_DocType_ID)
;

-- 
-- VIEW: M_InOut_LineConfirm_v 
--
CREATE OR REPLACE VIEW M_InOut_LineConfirm_v 
AS
SELECT iolc.AD_Client_ID, iolc.AD_Org_ID, iolc.IsActive, iolc.Created, iolc.CreatedBy, iolc.Updated, iolc.UpdatedBy,
	'en_US' AS AD_Language,
    iolc.M_InOutLineConfirm_ID, iolc.M_InOutConfirm_ID,
    iolc.TargetQty, iolc.ConfirmedQty, iolc.DifferenceQty, iolc.ScrappedQty,
    iolc.Description, iolc.Processed,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
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
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
;

-- 
-- VIEW: M_InOut_LineConfirm_vt
--
CREATE OR REPLACE VIEW M_InOut_LineConfirm_vt 
AS
SELECT iolc.AD_Client_ID, iolc.AD_Org_ID, iolc.IsActive, iolc.Created, iolc.CreatedBy, iolc.Updated, iolc.UpdatedBy,
	uom.AD_Language,
    iolc.M_InOutLineConfirm_ID, iolc.M_InOutConfirm_ID,
    iolc.TargetQty, iolc.ConfirmedQty, iolc.DifferenceQty, iolc.ScrappedQty,
    iolc.Description, iolc.Processed,
	iol.M_InOut_ID, iol.M_InOutLine_ID, 
	iol.Line, 
	iol.MovementQty, uom.UOMSymbol, ol.QtyOrdered-ol.QtyDelivered AS QtyBackOrdered,
	COALESCE(COALESCE(pt.Name,p.Name), iol.Description) AS Name, -- main line
	CASE WHEN COALESCE(pt.Name,p.Name) IS NOT NULL THEN iol.Description END AS ShipDescription, -- second line
	COALESCE(pt.DocumentNote, p.DocumentNote) AS DocumentNote, -- third line
    p.UPC, p.SKU, p.Value AS ProductValue,
	iol.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    iol.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate
FROM M_InOutLineConfirm iolc
    INNER JOIN M_InOutLine iol ON (iolc.M_InOutLine_ID=iol.M_InOutLine_ID)
	INNER JOIN C_UOM_Trl uom ON (iol.C_UOM_ID=uom.C_UOM_ID)
	LEFT OUTER JOIN M_Product p ON (iol.M_Product_ID=p.M_Product_ID)
	LEFT OUTER JOIN M_Product_Trl pt ON (iol.M_Product_ID=pt.M_Product_ID AND uom.AD_Language=pt.AD_Language)
    LEFT OUTER JOIN M_AttributeSetInstance asi ON (iol.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
    LEFT OUTER JOIN M_Locator l ON (iol.M_Locator_ID=l.M_Locator_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (iol.C_OrderLine_ID=ol.C_OrderLine_ID)
;

-- 
-- VIEW: C_Dunning_Header_v 
--
CREATE OR REPLACE VIEW C_Dunning_Header_v 
AS
SELECT dr.AD_Client_ID, dr.AD_Org_ID, dr.IsActive, dr.Created, dr.CreatedBy, dr.Updated, dr.UpdatedBy, 
	'en_US' AS AD_Language, dr.C_DunningRun_ID, C_DunningRunEntry_ID,
    dr.DunningDate, dl.PrintName, dl.Note AS DocumentNote,
    dre.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dre.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo,
    dre.Amt, dre.Qty, dre.Note
FROM C_DunningRun dr
    INNER JOIN C_DunningLevel dl ON (dr.C_DunningLevel_ID=dl.C_DunningLevel_ID)
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
;

CREATE OR REPLACE VIEW C_Dunning_Header_vt
AS
SELECT dr.AD_Client_ID, dr.AD_Org_ID, dr.IsActive, dr.Created, dr.CreatedBy, dr.Updated, dr.UpdatedBy, 
	dlt.AD_Language, dr.C_DunningRun_ID, C_DunningRunEntry_ID,
    dr.DunningDate, dlt.PrintName, dlt.Note AS DocumentNote,
    dre.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dre.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo,
    dre.Amt, dre.Qty, dre.Note
FROM C_DunningRun dr
    INNER JOIN C_DunningLevel dl ON (dr.C_DunningLevel_ID=dl.C_DunningLevel_ID)
    INNER JOIN C_DunningLevel_Trl dlt ON (dl.C_DunningLevel_ID=dlt.C_DunningLevel_ID)
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID
        AND dlt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID
        AND dlt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
;

CREATE OR REPLACE VIEW C_Dunning_Line_v
AS
SELECT drl.AD_Client_ID, drl.AD_Org_ID, drl.IsActive, drl.Created, drl.CreatedBy, drl.Updated, drl.UpdatedBy, 
	'en_US' AS AD_Language,
    drl.C_DunningRunLine_ID, drl.C_DunningRunEntry_ID,
    drl.Amt, drl.ConvertedAmt, drl.DaysDue, drl.TimesDunned, 
    drl.InterestAmt, drl.FeeAmt, drl.TotalAmt,
	drl.C_Invoice_ID, 
    COALESCE(i.IsSOTrx,p.IsReceipt) AS IsSOTrx,
    COALESCE(i.DocumentNo,p.DocumentNo) AS DocumentNo,
    COALESCE(i.DocStatus,p.DocStatus) AS DocStatus, 
	COALESCE(i.DateInvoiced, p.DateTrx) AS DateTrx,
    COALESCE(i.C_DocType_ID,p.C_DocType_ID) AS C_DocType_ID,
    COALESCE(dt.PrintName,dtp.PrintName) AS DocumentType, 
    COALESCE(i.Description,p.Description) AS Description, 
	COALESCE(i.C_Currency_ID,p.C_Currency_ID) AS C_Currency_ID, 
    COALESCE(i.C_Campaign_ID,p.C_Campaign_ID) AS C_Campaign_ID, 
    COALESCE(i.C_Project_ID,p.C_Project_ID) AS C_Project_ID,
    COALESCE(i.C_Activity_ID,p.C_Activity_ID) AS C_Activity_ID,
    COALESCE(i.User1_ID,p.User1_ID) AS User1_ID,
    COALESCE(i.User2_ID,p.User2_ID) AS User2_ID,
    COALESCE(i.DateAcct,p.DateAcct) AS DateAcct,
    COALESCE(i.C_ConversionType_ID,i.C_ConversionType_ID) AS C_ConversionType_ID,
    COALESCE(i.AD_OrgTrx_ID,p.AD_OrgTrx_ID) AS AD_OrgTrx_ID,
    i.POReference, i.DateOrdered,
	i.DateInvoiced, i.IsInDispute,
	pt.Name AS PaymentTerm,
    i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines, i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID, i.IsPaid,
    p.IsAllocated, p.TenderType, p.DiscountAmt
FROM C_DunningRunLine drl
    LEFT OUTER JOIN C_Invoice i ON (drl.C_Invoice_ID=i.C_Invoice_ID)
	LEFT OUTER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	LEFT OUTER JOIN C_PaymentTerm pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
    LEFT OUTER JOIN C_Payment p ON (drl.C_Payment_ID=p.C_Payment_ID)
	LEFT OUTER JOIN C_DocType dtp ON (p.C_DocType_ID=dtp.C_DocType_ID)
;

CREATE OR REPLACE VIEW C_Dunning_Line_vt
AS
SELECT drl.AD_Client_ID, drl.AD_Org_ID, drl.IsActive, drl.Created, drl.CreatedBy, drl.Updated, drl.UpdatedBy, 
	COALESCE(dt.AD_Language,dtp.AD_Language) AS AD_Language,
    drl.C_DunningRunLine_ID, drl.C_DunningRunEntry_ID,
    drl.Amt, drl.ConvertedAmt, drl.DaysDue, drl.TimesDunned, 
    drl.InterestAmt, drl.FeeAmt, drl.TotalAmt,
	drl.C_Invoice_ID, 
    COALESCE(i.IsSOTrx,p.IsReceipt) AS IsSOTrx,
    COALESCE(i.DocumentNo,p.DocumentNo) AS DocumentNo,
    COALESCE(i.DocStatus,p.DocStatus) AS DocStatus, 
	COALESCE(i.DateInvoiced, p.DateTrx) AS DateTrx,
    COALESCE(i.C_DocType_ID,p.C_DocType_ID) AS C_DocType_ID,
    COALESCE(dt.PrintName,dtp.PrintName) AS DocumentType, 
    COALESCE(i.Description,p.Description) AS Description, 
	COALESCE(i.C_Currency_ID,p.C_Currency_ID) AS C_Currency_ID, 
    COALESCE(i.C_Campaign_ID,p.C_Campaign_ID) AS C_Campaign_ID, 
    COALESCE(i.C_Project_ID,p.C_Project_ID) AS C_Project_ID,
    COALESCE(i.C_Activity_ID,p.C_Activity_ID) AS C_Activity_ID,
    COALESCE(i.User1_ID,p.User1_ID) AS User1_ID,
    COALESCE(i.User2_ID,p.User2_ID) AS User2_ID,
    COALESCE(i.DateAcct,p.DateAcct) AS DateAcct,
    COALESCE(i.C_ConversionType_ID,i.C_ConversionType_ID) AS C_ConversionType_ID,
    COALESCE(i.AD_OrgTrx_ID,p.AD_OrgTrx_ID) AS AD_OrgTrx_ID,
    i.POReference, i.DateOrdered,
	i.DateInvoiced, i.IsInDispute,
	pt.Name AS PaymentTerm,
    i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines, i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID, i.IsPaid,
    p.IsAllocated, p.TenderType, p.DiscountAmt
FROM C_DunningRunLine drl
    LEFT OUTER JOIN C_Invoice i ON (drl.C_Invoice_ID=i.C_Invoice_ID)
	LEFT OUTER JOIN C_DocType_Trl dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	LEFT OUTER JOIN C_PaymentTerm_Trl pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID
        AND pt.AD_Language=dt.AD_Language)
    LEFT OUTER JOIN C_Payment p ON (drl.C_Payment_ID=p.C_Payment_ID)
	LEFT OUTER JOIN C_DocType_Trl dtp ON (p.C_DocType_ID=dtp.C_DocType_ID) 
WHERE COALESCE(dt.AD_Language,dtp.AD_Language)=COALESCE(dtp.AD_Language,dt.AD_Language)
;

-- 
-- VIEW: R_Request_v 
--
CREATE OR REPLACE VIEW R_Request_v 
AS
SELECT *
FROM R_Request
WHERE IsActive='Y' AND Processed='N'
  AND getdate() > DateNextAction
;


-------------------------------------------------------------------------------

--	Invoice corrected for Credit Memo

CREATE OR REPLACE VIEW RV_C_Invoice
AS
SELECT i.C_Invoice_ID, 
	i.AD_Client_ID,i.AD_Org_ID,i.IsActive,i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
	i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, 
	i.IsPrinted, i.IsDiscountPrinted, i.Processing, i.Processed, i.IsTransferred, i.IsPaid,
	i.C_DocType_ID, i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, 
	i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct,
	i.C_BPartner_ID, i.C_BPartner_Location_ID, i.AD_User_ID, b.C_BP_Group_ID,
	i.POReference, i.DateOrdered, i.C_Currency_ID, C_ConversionType_ID, i.PaymentRule, i.C_PaymentTerm_ID,
	i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID, 
    i.IsPayScheduleValid,
    loc.C_Country_ID, loc.C_Region_ID, loc.Postal, loc.City,
	--	Amounts
	i.C_Charge_ID,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier
FROM  C_Invoice i
 INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
 INNER JOIN C_BPartner b ON (i.C_BPartner_ID=b.C_BPartner_ID)
 INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
 INNER JOIN C_Location loc ON (bpl.C_Location_ID=loc.C_Location_ID)
;

--	Invoice Lines corrected for Credit Memo

CREATE OR REPLACE VIEW RV_C_InvoiceLine 
AS SELECT 
	il.AD_Client_ID, il.AD_Org_ID,
	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID,
	i.C_BPartner_ID, i.C_BP_Group_ID,
	il.M_Product_ID, p.M_Product_Category_ID,
	i.DateInvoiced, i.DateAcct, i.IsSOTrx, i.C_DocType_ID, i.DocStatus,
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

--  Invoice Tax adjusted for CM

CREATE OR REPLACE VIEW RV_C_InvoiceTax
AS
SELECT 
    i.AD_Client_ID, i.AD_Org_ID, i.IsActive, t.Created, t.CreatedBy, t.Updated, t.UpdatedBy,
    t.C_Tax_ID, i.C_Invoice_ID, i.C_DocType_ID,
    i.C_BPartner_ID, bp.TaxID, bp.IsTaxExempt, 
    i.DateAcct, i.DateInvoiced, i.IsSOTrx, i.DocumentNo, i.IsPaid, i.C_Currency_ID,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN t.TaxBaseAmt*-1 ELSE t.TaxBaseAmt END AS TaxBaseAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN t.TaxAmt*-1 ELSE t.TaxAmt END AS TaxAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN (t.TaxBaseAmt + t.TaxAmt)*-1 ELSE (t.TaxBaseAmt + t.TaxAmt) END AS TaxLineTotal,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier
FROM C_InvoiceTax t
  INNER JOIN C_Invoice i ON (t.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
  INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)
;
  
--	Invoice By Day

CREATE OR REPLACE VIEW RV_C_Invoice_Day 
AS
SELECT AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DD') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
    IsSOTrx
FROM RV_C_InvoiceLine
GROUP BY AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DD'), IsSOTrx
;

--	Invoice By Week

CREATE OR REPLACE VIEW RV_C_Invoice_Week 
AS
SELECT AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DY') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
    IsSOTrx
FROM RV_C_InvoiceLine
GROUP BY AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DY'), IsSOTrx
;

--	Invoice By Month

CREATE OR REPLACE VIEW RV_C_Invoice_Month
AS
SELECT AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'MM') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
    IsSOTrx
FROM RV_C_InvoiceLine
GROUP BY AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'MM'), IsSOTrx
;

--	Invoice By Customer and Quarter

CREATE OR REPLACE VIEW RV_C_Invoice_CustomerProdQtr 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID,
	il.C_BPartner_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'Q') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
FROM RV_C_InvoiceLine il
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.C_BPartner_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'Q'), IsSOTrx
;

--	Invoice By Vendor and Quarter

CREATE OR REPLACE VIEW RV_C_Invoice_CustomerVendQtr 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID,
	il.C_BPartner_ID, po.C_BPartner_ID AS Vendor_ID,
	firstOf(il.DateInvoiced, 'Q') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced
FROM RV_C_InvoiceLine il
  INNER JOIN M_Product_PO po ON (il.M_Product_ID=po.M_Product_ID)
WHERE il.IsSOTrx='Y'
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.C_BPartner_ID, po.C_BPartner_ID,
	firstOf(il.DateInvoiced, 'Q')
;

--	Invoice By Product and Week

CREATE OR REPLACE VIEW RV_C_Invoice_ProdWeek 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'DY') AS DateInvoiced,
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
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'DY'), IsSOTrx
;

--	Invoice By Product and Month

CREATE OR REPLACE VIEW RV_C_Invoice_ProdMonth 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_Category_ID,
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
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'MM'), IsSOTrx
;

--	Invoice By Vendor and Month

CREATE OR REPLACE VIEW RV_C_Invoice_VendorMonth 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID,
	po.C_BPartner_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'MM') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced
FROM RV_C_InvoiceLine il
    INNER JOIN M_Product_PO po ON (il.M_Product_ID=po.M_Product_ID)
WHERE il.IsSOTrx='Y'
GROUP BY il.AD_Client_ID, il.AD_Org_ID, po.C_BPartner_ID, il.M_Product_Category_ID,
	firstOf(il.DateInvoiced, 'MM')
;

--	Product Transactions

CREATE OR REPLACE VIEW RV_M_Transaction_Sum
AS
SELECT t.AD_Client_ID, t.AD_Org_ID, 
	t.MovementType, l.M_Warehouse_ID, t.M_Locator_ID, t.M_Product_ID, t.MovementDate,
	SUM(t.MovementQty) AS MovementQty
FROM M_Transaction t, M_Locator l
WHERE t.M_Locator_ID=l.M_Locator_ID
GROUP BY t.AD_Client_ID, t.AD_Org_ID, 
	t.MovementType, l.M_Warehouse_ID, t.M_Locator_ID, t.M_Product_ID, t.MovementDate
;

--	Product Transactions Detail

CREATE OR REPLACE VIEW RV_M_Transaction 
AS
SELECT t.AD_Client_ID,t.AD_Org_ID, t.MovementDate, t.MovementQty, 
	t.M_Product_ID, t.M_Locator_ID, t.M_AttributeSetInstance_ID,
	p.M_Product_Category_ID, p.Value, 
	po.C_BPartner_ID, po.PricePO, po.PriceLastPO, po.PriceList
FROM M_Transaction t
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  INNER JOIN M_Product_PO po ON (t.M_Product_ID=po.M_Product_ID)
WHERE po.IsCurrentVendor='Y'
;

--	Business Partners

CREATE OR REPLACE VIEW RV_BPartner 
AS
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive,
	p.C_BPartner_ID, p.Value, p.Name, p.ReferenceNo,
	p.SO_CreditLimit - p.SO_CreditUsed AS SO_CreditAvailable, 
	p.SO_CreditLimit, p.SO_CreditUsed, p.TotalOpenBalance,
    p.IsCustomer, p.IsVendor,
	p.ActualLifeTimeValue AS Revenue,
    p.SOCreditStatus,
    -- Contact
	c.AD_User_ID, c.Name AS Contact, c.Phone, c.Email,
    -- Location
	l.C_BPartner_Location_ID, a.Postal, a.City, a.Address1, a.Address2, 
    a.C_Region_ID, COALESCE(r.Name,a.RegionName) AS RegionName,
    a.C_Country_ID, cc.Name AS CountryName
FROM C_BPartner p
  LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID AND c.IsActive='Y')
  INNER JOIN C_BPartner_Location l ON (p.C_BPartner_ID=l.C_BPartner_ID AND l.IsActive='Y')
  INNER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)
  LEFT OUTER JOIN C_Region r ON (a.C_Region_ID=r.C_Region_ID)
  INNER JOIN C_Country cc ON (a.C_Country_ID=cc.C_Country_ID)
;

--	Open Items

CREATE OR REPLACE VIEW RV_OpenItem 
AS
SELECT i.AD_Org_ID, i.AD_Client_ID, 
	i.DocumentNo, i.C_Invoice_ID, i.C_Order_ID, i.C_BPartner_ID, i.IsSOTrx,
	i.DateInvoiced, 
    p.NetDays, 
	paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) AS DueDate,
	paymentTermDueDays(i.C_PaymentTerm_ID, i.DateInvoiced, getdate()) AS DaysDue,
    addDays(i.DateInvoiced,p.DiscountDays) AS DiscountDate, 
    ROUND(i.GrandTotal*p.Discount/100,2) AS DiscountAmt,
	i.GrandTotal, 
	invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
	invoiceOpen(i.C_Invoice_ID,0) AS OpenAmt,
    i.C_Currency_ID, i.C_ConversionType_ID, 
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid, null AS C_InvoicePaySchedule_ID
FROM RV_C_Invoice i
    INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID)
WHERE --    i.IsPaid='N'
    invoiceOpen(i.C_Invoice_ID,0) <> 0
    AND i.IsPayScheduleValid<>'Y'
    AND i.DocStatus<>'DR'
UNION
SELECT i.AD_Org_ID, i.AD_Client_ID, 
    i.DocumentNo, i.C_Invoice_ID, i.C_Order_ID, i.C_BPartner_ID, i.IsSOTrx,
	i.DateInvoiced,
    daysBetween(ips.DueDate,i.DateInvoiced) AS NetDays,
    ips.DueDate,
    daysBetween(getdate(),ips.DueDate) AS DaysDue,
    ips.DiscountDate, 
    ips.DiscountAmt,
	ips.DueAmt AS GrandTotal, 
	invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
	invoiceOpen(i.C_Invoice_ID, ips.C_InvoicePaySchedule_ID) AS OpenAmt,
    i.C_Currency_ID, i.C_ConversionType_ID, 
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID
FROM RV_C_Invoice i
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE  --   i.IsPaid='N'
    invoiceOpen(i.C_Invoice_ID,ips.C_InvoicePaySchedule_ID) <> 0
    AND i.IsPayScheduleValid='Y'
    AND i.DocStatus<>'DR'
    AND ips.IsValid='Y'
;

--	Open Orders

CREATE OR REPLACE VIEW RV_Order_Open
AS
SELECT l.AD_Client_ID, l.AD_Org_ID, 
	l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,
	o.C_Order_ID, o.DocStatus, o.DocAction, o.C_DocType_ID, o.IsApproved, o.IsCreditApproved,
	o.SalesRep_ID, 
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID, o.IsDropShip,
    l.C_BPartner_ID, l.C_BPartner_Location_ID, o.AD_User_ID,
	o.POReference, o.C_Currency_ID, o.IsSOTrx,
	l.C_OrderLine_ID, l.DateOrdered, l.DatePromised, l.M_Product_ID, l.M_Warehouse_ID,
    l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
    pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	l.C_UOM_ID, l.QtyEntered, l.QtyOrdered, l.QtyReserved, l.QtyDelivered, l.QtyInvoiced, 
    l.PriceActual, l.PriceEntered,
	l.QtyOrdered-l.QtyDelivered AS QtyToDeliver,
	l.QtyOrdered-l.QtyInvoiced AS QtyToInvoice,
	(l.QtyOrdered-l.QtyInvoiced)*l.PriceActual AS NetAmtToInvoice
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
WHERE (l.QtyOrdered<>l.QtyDelivered OR l.QtyOrdered<>l.QtyInvoiced)
;

--	Cash Journal Detail

CREATE OR REPLACE VIEW RV_Cash_Detail
AS
SELECT cl.C_Cash_ID, cl.C_CashLine_ID,
	c.AD_Client_ID, c.AD_Org_ID, cl.IsActive, cl.Created, cl.CreatedBy, cl.Updated, cl.UpdatedBy,
	c.C_CashBook_ID, c.Name, c.StatementDate, c.DateAcct, c.Processed, c.Posted,
	cl.Line, cl.Description, cl.CashType, cl.C_Currency_ID, cl.Amount, 
	currencyConvert(cl.Amount,cl.C_Currency_ID,cb.C_Currency_ID,c.StatementDate,0, c.AD_Client_ID, c.AD_Org_ID) AS ConvertedAmt,
	cl.C_BankAccount_ID, cl.C_Invoice_ID, cl.C_Charge_ID
FROM C_Cash c
 INNER JOIN C_CashLine cl ON (c.C_Cash_ID=cl.C_Cash_ID)
 INNER JOIN C_CashBook cb ON (c.C_CashBook_ID=cb.C_CashBook_ID)
;

CREATE OR REPLACE VIEW RV_Product_Costing
AS
SELECT	pc.M_Product_ID, pc.C_AcctSchema_ID, p.Value, p.Name, p.M_Product_Category_ID,
	pc.AD_Client_ID, pc.AD_Org_ID, pc.IsActive, pc.Created,pc.CreatedBy,pc.Updated,pc.UpdatedBy,
	pc.CurrentCostPrice,
	--	Standard Costing
	pc.FutureCostPrice, pc.CostStandard, 
	pc.CostStandardPOQty, pc.CostStandardPOAmt, 
	CASE WHEN pc.CostStandardPOQty=0 THEN 0 ELSE pc.CostStandardPOAmt/pc.CostStandardPOQty END AS CostStandardPODiff,
	pc.CostStandardCumQty, pc.CostStandardCumAmt,
	CASE WHEN pc.CostStandardCumQty=0 THEN 0 ELSE pc.CostStandardCumAmt/pc.CostStandardCumQty END AS CostStandardInvDiff,
	--	Average Costing
	pc.CostAverage,
	pc.CostAverageCumQty, pc.CostAverageCumAmt,
	pc.TotalInvQty, pc.TotalInvAmt,
	CASE WHEN pc.TotalInvQty=0 THEN 0 ELSE pc.TotalInvAmt/pc.TotalInvQty END AS TotalInvCost,
	--	LastPrice
	pc.PriceLastPO, pc.PriceLastInv
FROM M_Product_Costing pc
  INNER JOIN M_Product p ON (pc.M_Product_ID=p.M_Product_ID)
;

CREATE OR REPLACE VIEW RV_ProjectCycle
AS
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created,p.CreatedBy, p.Updated,p.UpdatedBy,
    c.C_Cycle_ID, c.Name AS CycleName, c.C_Currency_ID,
    cs.C_CycleStep_ID, cs.Name AS CycleStepName, cs.SeqNo, cs.RelativeWeight,
    pp.C_Phase_ID, pp.Name AS ProjectPhaseName, 
    pt.C_ProjectType_ID, pt.Name AS ProjectTypeName,
    p.Value AS ProjectValue, p.Name AS ProjectName, p.Description, p.Note, 
    p.C_BPartner_ID, p.C_BPartner_Location_ID, p.AD_User_ID, p.POReference,
    p.SalesRep_ID, p.M_Warehouse_ID, p.ProjectCategory,
    p.DateContract, p.DateFinish,
    p.IsCommitment, p.IsCommitCeiling, 
    p.CommittedQty*cs.RelativeWeight AS CommittedQty,
    currencyConvert (p.CommittedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS CommittedAmt,
    p.PlannedQty*cs.RelativeWeight AS PlannedQty, 
    currencyConvert (p.PlannedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS PlannedAmt, 
    currencyConvert (p.PlannedMarginAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS PlannedMarginAmt,
    currencyConvert (p.InvoicedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS InvoicedAmt, 
    p.InvoicedQty*cs.RelativeWeight AS InvoicedQty,
    currencyConvert (p.ProjectBalanceAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS ProjectBalanceAmt
FROM C_Cycle c
  INNER JOIN C_CycleStep cs ON (c.C_Cycle_ID=cs.C_Cycle_ID)
  INNER JOIN C_CyclePhase cp ON (cs.C_CycleStep_ID=cp.C_CycleStep_ID)
  INNER JOIN C_Phase pp ON (cp.C_Phase_ID=pp.C_Phase_ID)
  INNER JOIN C_Project p ON (cp.C_Phase_ID=p.C_Phase_ID)
  INNER JOIN C_ProjectType pt ON (p.C_ProjectType_ID=pt.C_ProjectType_ID)
;

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_Asset_Customer
AS
SELECT A_Asset_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
  Value, Name, Description, Help, A_Asset_Group_ID, M_Product_ID, SerNo, Lot, VersionNo,
  GuaranteeDate, AssetServiceDate,
  C_BPartner_ID, C_BPartner_Location_ID, AD_User_ID,
  (SELECT COUNT(*) FROM A_Asset_Delivery ad WHERE a.A_Asset_ID=ad.A_Asset_ID) AS DeliveryCount
FROM A_Asset a
WHERE C_BPartner_ID IS NOT NULL
;

CREATE OR REPLACE VIEW RV_Asset_Delivery
AS
SELECT ad.A_Asset_Delivery_ID, ad.AD_Client_ID, ad.AD_Org_ID, ad.IsActive, ad.Created, ad.CreatedBy, ad.Updated, ad.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, ad.AD_User_ID,
  ad.MovementDate, ad.SerNo, ad.Lot, ad.VersionNo,
  ad.M_InOutLine_ID, 
  ad.Email, ad.MessageID, ad.DeliveryConfirmation,
  ad.URL, ad.Remote_Addr, ad.Remote_Host, ad.Referrer,
  ad.Description
FROM A_Asset_Delivery ad
 INNER JOIN A_Asset a ON (a.A_Asset_ID=ad.A_Asset_ID)
;

CREATE OR REPLACE VIEW RV_Asset_SumMonth
AS
SELECT a.AD_Client_ID, a.AD_Org_ID, a.IsActive, a.Created, a.CreatedBy, a.Updated, a.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.Value, a.Name, a.Description, a.Help,
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, a.AD_User_ID, a.SerNo, a.Lot, a.VersionNo,
  firstOf(ad.MovementDate, 'MM') AS MovementDate,
  COUNT(*) AS DeliveryCount
FROM A_Asset a
 INNER JOIN A_Asset_Delivery ad ON (a.A_Asset_ID=ad.A_Asset_ID)
GROUP BY a.AD_Client_ID, a.AD_Org_ID, a.IsActive, a.Created, a.CreatedBy, a.Updated, a.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.Value, a.Name, a.Description, a.Help,
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, a.AD_User_ID, a.SerNo, a.Lot, a.VersionNo,
  firstOf(ad.MovementDate, 'MM')
;

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_ProjectLineIssue
AS
SELECT COALESCE(l.AD_Client_ID,i.AD_Client_ID) AS AD_Client_ID,COALESCE(l.AD_Org_ID,i.AD_Org_ID) AS AD_Org_ID,
    COALESCE(l.IsActive,i.IsActive) AS IsActive,
    COALESCE(l.Created,i.Created) AS Created,COALESCE(l.CreatedBy,i.CreatedBy) AS CreatedBy,
    COALESCE(l.Updated,i.Updated) AS Updated,COALESCE(l.UpdatedBy,i.UpdatedBy) AS UpdatedBy,
    COALESCE(l.C_Project_ID,i.C_Project_ID) AS C_Project_ID,
    COALESCE(l.M_Product_ID,i.M_Product_ID) AS M_Product_ID,
    --
    l.C_ProjectLine_ID, l.Line, l.Description, l.PlannedQty, l.PlannedPrice, l.PlannedAmt, 
    l.PlannedMarginAmt, l.CommittedQty,
    --
    i.C_ProjectIssue_ID, i.M_Locator_ID, i.MovementQty, i.MovementDate,
    i.Line AS IssueLine, i.Description AS IssueDescription,
    i.M_InOutLine_ID, i.S_TimeExpenseLine_ID,
    --
    fa.C_AcctSchema_ID, fa.Account_ID, 
    fa.AmtSourceDr, fa.AmtSourceCr, fa.AmtAcctDr, fa.AmtAcctCr,
    --
    l.PlannedAmt-fa.AmtSourceDr+fa.AmtSourceCr AS LineMargin
FROM C_ProjectLine l
    FULL JOIN C_ProjectIssue i ON (i.C_Project_ID=l.C_Project_ID AND i.C_ProjectIssue_ID=l.C_ProjectIssue_ID)
    LEFT OUTER JOIN Fact_Acct fa ON (fa.AD_Table_ID=623 AND fa.Record_ID=i.C_ProjectIssue_ID AND fa.M_Locator_ID IS NULL)
;

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_UnPosted 
AS
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, DateDoc, DateAcct, 224 AS AD_Table_ID, GL_Journal_ID AS Record_ID, 'N' AS IsSOTrx
FROM GL_Journal  WHERE Posted<>'Y'
UNION
SELECT pi.AD_Client_ID, pi.AD_Org_ID, pi.Created, pi.CreatedBy, pi.Updated, pi.UpdatedBy, pi. IsActive,
    p.Name || '_' || pi.Line, pi.MovementDate, pi.MovementDate, 623, pi.C_ProjectIssue_ID, 'N' 
FROM C_ProjectIssue pi INNER JOIN C_Project p ON (pi.C_Project_ID=p.C_Project_ID)  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, DateInvoiced, DateAcct, 318, C_Invoice_ID, IsSOTrx
FROM C_Invoice  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, DateAcct, 319, M_InOut_ID, IsSOTrx 
FROM M_InOut  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, MovementDate, 321, M_Inventory_ID, 'N' 
FROM M_Inventory  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    DocumentNo, MovementDate, MovementDate, 323, M_Movement_ID, 'N' 
FROM M_Movement  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    Name, MovementDate, MovementDate, 325, M_Production_ID, 'N'
FROM M_Production  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    Name, StatementDate, DateAcct, 407, C_Cash_ID, 'N' 
FROM C_Cash  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 335, C_Payment_ID, 'N'
FROM C_Payment  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 735, C_AllocationHdr_ID, 'N' 
FROM C_AllocationHdr  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
    Name, StatementDate, StatementDate, 392, C_BankStatement_ID, 'N' 
FROM C_BankStatement  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 472, M_MatchInv_ID, 'N' 
FROM M_MatchInv  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateTrx, DateTrx, 473, M_MatchPO_ID, 'N'
FROM M_MatchPO  WHERE Posted<>'Y'
UNION
SELECT AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, 
    DocumentNo, DateOrdered, DateAcct, 259, C_Order_ID, IsSOTrx
FROM C_Order  WHERE Posted<>'Y'
;    

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_WarehousePrice
AS
SELECT w.AD_Client_ID, w.AD_Org_ID, 
    CASE WHEN p.Discontinued='N' THEN 'Y' ELSE 'N' END AS IsActive, 
    pr.Created, pr.CreatedBy, pr.Updated, pr.UpdatedBy,
    p.M_Product_ID, pr.M_PriceList_Version_ID, w.M_Warehouse_ID,
    p.Value, p.Name, p.UPC, p.SKU,
    uom.C_UOM_ID, uom.UOMSymbol,
    bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList, 
    bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd, 
    bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID)-bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin, 
    bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit, 
    w.Name AS WarehouseName,
    bomQtyAvailable(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyAvailable, 
    bomQtyOnHand(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyOnHand, 
    bomQtyReserved(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyReserved, 
    bomQtyOrdered(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyOrdered, 
    COALESCE (pa.IsInstanceAttribute, 'N') AS IsInstanceAttribute
FROM M_Product p 
    INNER JOIN M_ProductPrice pr ON (p.M_Product_ID=pr.M_Product_ID)
    INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN M_AttributeSet pa ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID)
    INNER JOIN M_Warehouse w ON (p.AD_Client_ID=w.AD_Client_ID)
WHERE p.IsSummary='N' AND p.IsActive='Y' AND pr.IsActive='Y' AND w.IsActive='Y'
--AND pr.M_PriceList_Version_ID=? 
--AND w.M_Warehouse_ID=?
--AND UPPER(p.Value) LIKE ? AND UPPER(p.Name) LIKE ? 
--AND UPPER(p.UPC) LIKE ? AND UPPER(p.SKU) LIKE ? 
--ORDER BY QtyAvailable DESC, Margin DESC
;

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_Storage 
AS
SELECT s.AD_Client_ID, s.AD_Org_ID,
    -- Product
    s.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
    p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Weight,p.Volume,p.VersionNo,
    p.GuaranteeDays,p.GuaranteeDaysMin,
    --  Locator
    s.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    -- Storage
    s.QtyOnHand, s.QtyReserved, s.QtyOnHand-s.QtyReserved AS QtyAvailable, 
    s.QtyOrdered, s.DateLastInventory,
    -- Instance
    s.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,
    asi.GuaranteeDate,  -- see PAttributeInstance.java
    daysBetween(asi.GuaranteeDate,getdate()) AS ShelfLifeDays,
    daysBetween(asi.GuaranteeDate,getdate())-p.GuaranteeDaysMin AS GoodForDays,
    ROUND((daysBetween(asi.GuaranteeDate,getdate())/p.GuaranteeDays)*100,0) AS ShelfLifeRemainingPct
FROM M_Storage s
  INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (s.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (s.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
;

CREATE OR REPLACE VIEW RV_Transaction 
AS
SELECT t.M_Transaction_ID, t.AD_Client_ID,t.AD_Org_ID,
    t.MovementType,t.MovementDate,t.MovementQty,
    -- Instance
    t.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    -- Product
    t.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
    p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Weight,p.Volume,p.VersionNo,
    -- Locator
    t.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    -- Inventory
    t.M_InventoryLine_ID,il.M_Inventory_ID,
    -- Movement
    t.M_MovementLine_ID,ml.M_Movement_ID,
    -- In/Out
    t.M_InOutLine_ID,iol.M_InOut_ID,
    -- Production
    t.M_ProductionLine_ID,prdl.M_ProductionPlan_ID,prdp.M_Production_ID,
    -- ProjectIssue
    t.C_ProjectIssue_ID,pjl.C_Project_ID,
    COALESCE(il.Line,ml.Line,iol.Line,prdl.Line,pjl.Line) AS Line
FROM M_Transaction t
  INNER JOIN M_Locator l ON (t.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (t.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine il ON (t.M_InventoryLine_ID=il.M_InventoryLine_ID)
  LEFT OUTER JOIN M_MovementLine ml ON (t.M_MovementLine_ID=ml.M_MovementLine_ID)
  LEFT OUTER JOIN M_InOutLine iol ON (t.M_InOutLine_ID=iol.M_InOutLine_ID)
  LEFT OUTER JOIN M_ProductionLine prdl ON (t.M_ProductionLine_ID=prdl.M_ProductionLine_ID)
    LEFT OUTER JOIN M_ProductionPlan prdp ON (prdl.M_ProductionPlan_ID=prdp.M_ProductionPlan_ID)
  LEFT OUTER JOIN C_ProjectIssue pjl ON (t.C_ProjectIssue_ID=pjl.C_ProjectIssue_ID)
;

-------------------------------------------------------------------------------

--  Click Count
CREATE OR REPLACE VIEW RV_Click_Month
AS
SELECT cc.AD_Client_ID, cc.AD_Org_ID,
    cc.Name, cc.Description, cc.TargetURL, cc.C_BPartner_ID, 
    firstOf(c.Created,'MM') AS Created,
    COUNT(*) AS Counter
FROM W_ClickCount cc
  INNER JOIN W_Click c ON (cc.W_ClickCount_ID=c.W_ClickCount_ID)
GROUP BY cc.AD_Client_ID, cc.AD_Org_ID,
    cc.Name, cc.Description, cc.TargetURL, cc.C_BPartner_ID,
    firstOf(c.Created,'MM')
;

CREATE OR REPLACE VIEW RV_Click_Unprocessed
AS
SELECT *
FROM W_Click
WHERE W_ClickCount_ID IS NULL OR Processed='N'
;

-------------------------------------------------------------------------------
--	Corrected for Credit Memo (See similar RV_C_Invoice)

CREATE OR REPLACE VIEW C_Invoice_v 
AS
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID, 
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred, 
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID, 
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule, 
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, 
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    --i.IsPayScheduleValid, null AS C_InvoicePaySchedule_ID, e-Evolution
    i.IsPayScheduleValid, 0 AS C_InvoicePaySchedule_ID,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal, 
    --begin vpj-cd e-evolution.com 
    --CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    --CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
    --end  vpj-cd e-evolution.com 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1.0 ELSE 1.0 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1.0 ELSE 1.0 END AS MultiplierAP,
    d.DocBaseType
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
WHERE i.IsPayScheduleValid<>'Y'
UNION
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID, 
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred, 
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID, 
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule, 
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, 
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID,
    null AS ChargeAmt, 
    null AS TotalLines, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN ips.DueAmt*-1 ELSE ips.DueAmt END AS GrandTotal, 
    --begin vpj-cd e-evolution.com 
    --CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    --CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1.0 ELSE 1.0 END AS MultiplierAP,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1.0 ELSE 1.0 END AS MultiplierAP,
    --end vpj-cd e-evolution.com 
    d.DocBaseType
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE i.IsPayScheduleValid='Y'
    AND ips.IsValid='Y'
;
--COMMENT ON TABLE C_Invoice_v IS 'Invoice Information corrected for Credit Memos (Split)'
--;

CREATE OR REPLACE VIEW C_Invoice_v1
AS
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID, 
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred, 
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID, 
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule, 
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, 
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, null AS C_InvoicePaySchedule_ID,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal, 
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
    d.DocBaseType
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
;
--COMMENT ON TABLE C_Invoice_v1 IS 'Invoice Information corrected for Credit Memos'
--;

CREATE OR REPLACE VIEW C_InvoiceLine_v 
AS
SELECT il.AD_Client_ID, il.AD_Org_ID, 
	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID, 
	i.C_BPartner_ID, il.M_Product_ID,  
	i.DocumentNo, i.DateInvoiced, i.DateAcct,
	i.IsSOTrx, i.DocStatus,
	ROUND(i.Multiplier*LineNetAmt, 2) AS LineNetAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced, 2) AS LineListAmt,
	CASE WHEN COALESCE(il.PriceLimit, 0)=0 THEN ROUND(i.Multiplier*LineNetAmt,2) ELSE ROUND(i.Multiplier*PriceLimit*QtyInvoiced,2) END AS LineLimitAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced-LineNetAmt,2) AS LineDiscountAmt,
	CASE WHEN COALESCE(il.PriceLimit,0)=0 THEN 0 ELSE ROUND(i.Multiplier*LineNetAmt-PriceLimit*QtyInvoiced,2) END AS LineOverLimitAmt,
	il.QtyInvoiced, il.QtyEntered,
	il.Line, il.C_OrderLine_ID, il.C_UOM_ID
FROM C_Invoice_v i, C_InvoiceLine il
WHERE i.C_Invoice_ID=il.C_Invoice_ID
;
--COMMENT ON TABLE C_InvoiceLine_v IS 'Invoice Line Summary for Reporting Views - Corrected for Credit Memos'
--;

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW C_Invoice_Candidate_v 
AS
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
	SUM((l.QtyOrdered-l.QtyInvoiced)*l.PriceActual) AS TotalLines
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
  INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
  LEFT OUTER JOIN C_InvoiceSchedule si ON (bp.C_InvoiceSchedule_ID=si.C_InvoiceSchedule_ID)
WHERE	o.DocStatus IN ('CO','CL','IP')	-- Standard Orders are IP
	--	not Offers and open Walkin-Receipts
	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR'))
	--	we need to invoice
	AND	l.QtyOrdered <> l.QtyInvoiced
	--
	AND (
		--	Immediate
		o.InvoiceRule='I'
		--	Order compete ** not supported **
		OR	o.InvoiceRule='O'
		--	Delivery
		OR	(o.InvoiceRule='D' AND l.QtyInvoiced<>l.QtyDelivered)
		--	Order Schedule, but none defined on Business Partner level
		OR	(o.InvoiceRule='S' AND bp.C_InvoiceSchedule_ID IS NULL)
		--	Schedule defined at BP
		OR	(o.InvoiceRule='S' AND bp.C_InvoiceSchedule_ID IS NOT NULL AND
				(
				--	Daily or none
					(si.InvoiceFrequency IS NULL OR si.InvoiceFrequency='D')
				--	Weekly
				OR	(si.InvoiceFrequency='W')
				--	Bi-Monthly
				OR	(si.InvoiceFrequency='T'
					AND (TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff-1
						AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay-1)
					OR	(TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff+14
						AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay+14)
					)
				--	Monthly
				OR	(si.InvoiceFrequency='M'
					AND TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff-1	-- after cutoff
					AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay-1)		-- after invoice day
				)
			)
		)
GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID
;

CREATE OR REPLACE VIEW M_InOut_Candidate_v 
AS
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID, 
    o.POReference, o.Description, o.SalesRep_ID,
    l.M_Warehouse_ID,
	SUM((l.QtyOrdered-l.QtyDelivered)*l.PriceActual) AS TotalLines
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
WHERE	(o.DocStatus = 'CO' AND o.IsDelivered='N')
	--	not Offers and open Walkin-Receipts
	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR'))
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

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW C_Payment_v 
AS
SELECT C_Payment_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    DocumentNo, DateTrx, IsReceipt, C_DocType_ID, TrxType,
    C_BankAccount_ID, C_BPartner_ID, C_Invoice_ID, C_BP_BankAccount_ID, C_PaymentBatch_ID,
    TenderType, CreditCardType, CreditCardNumber, CreditCardVV, CreditCardExpMM, CreditCardExpYY,
    Micr, RoutingNo, AccountNo, CheckNo,
    A_Name, A_Street, A_City, A_State, A_Zip, A_Ident_DL, A_Ident_SSN, A_EMail,
    VoiceAuthCode, Orig_TrxID, PONum,
    C_Currency_ID, C_ConversionType_ID,
    CASE IsReceipt WHEN 'Y' THEN PayAmt ELSE PayAmt*-1 END AS PayAmt,
    CASE IsReceipt WHEN 'Y' THEN DiscountAmt ELSE DiscountAmt*-1 END AS DiscountAmt, 
    CASE IsReceipt WHEN 'Y' THEN WriteOffAmt ELSE WriteOffAmt*-1 END AS WriteOffAmt,
    CASE IsReceipt WHEN 'Y' THEN TaxAmt ELSE TaxAmt*-1 END AS TaxAmt, 
    CASE IsReceipt WHEN 'Y' THEN OverUnderAmt ELSE OverUnderAmt*-1 END AS OverUnderAmt,
    --begin vpj-cd e-evolution
    --CASE IsReceipt WHEN 'Y' THEN 1 ELSE -1 END AS MultiplierAP,
    CASE IsReceipt WHEN 'Y' THEN 1.0 ELSE -1.0 END AS MultiplierAP,
    --end vpj-cd e-evolution
    IsOverUnderPayment, IsApproved,
    R_PnRef, R_Result, R_RespMsg, R_AuthCode, R_AvsAddr, R_AvsZip, R_Info,
    Processing, OProcessing, DocStatus, DocAction,
    IsPrepayment, C_Charge_ID,
    IsReconciled, IsAllocated, IsOnline, Processed, Posted
FROM C_Payment
;
--COMMENT ON TABLE C_Payment_v IS 'Payment Information corrected for AP/AR'
--;

CREATE OR REPLACE VIEW RV_Payment 
AS
SELECT C_Payment_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    DocumentNo, DateTrx, IsReceipt, C_DocType_ID, TrxType,
    C_BankAccount_ID, C_BPartner_ID, C_Invoice_ID, C_BP_BankAccount_ID, C_PaymentBatch_ID,
    TenderType, CreditCardType, CreditCardNumber, CreditCardVV, CreditCardExpMM, CreditCardExpYY,
    Micr, RoutingNo, AccountNo, CheckNo,
    A_Name, A_Street, A_City, A_State, A_Zip, A_Ident_DL, A_Ident_SSN, A_EMail,
    VoiceAuthCode, Orig_TrxID, PONum,
    C_Currency_ID, C_ConversionType_ID,
    CASE IsReceipt WHEN 'Y' THEN PayAmt ELSE PayAmt*-1 END AS PayAmt,
    CASE IsReceipt WHEN 'Y' THEN DiscountAmt ELSE DiscountAmt*-1 END AS DiscountAmt, 
    CASE IsReceipt WHEN 'Y' THEN WriteOffAmt ELSE WriteOffAmt*-1 END AS WriteOffAmt,
    CASE IsReceipt WHEN 'Y' THEN TaxAmt ELSE TaxAmt*-1 END AS TaxAmt, 
    CASE IsReceipt WHEN 'Y' THEN OverUnderAmt ELSE OverUnderAmt*-1 END AS OverUnderAmt,
    --begin vpj-cd e-evolution
    --CASE IsReceipt WHEN 'Y' THEN 1 ELSE -1 END AS MultiplierAP,
    CASE IsReceipt WHEN 'Y' THEN 1.0 ELSE -1.0 END AS MultiplierAP,
    --end
    paymentAllocated(C_Payment_ID, C_Currency_ID) AS AllocatedAmt,
    paymentAvailable(C_Payment_ID) AS AvailableAmt,
    IsOverUnderPayment, IsApproved,
    R_PnRef, R_Result, R_RespMsg, R_AuthCode, R_AvsAddr, R_AvsZip, R_Info,
    Processing, OProcessing, DocStatus, DocAction,
    IsPrepayment, C_Charge_ID,
    IsReconciled, IsAllocated, IsOnline, Processed, Posted
FROM C_Payment
;
--COMMENT ON TABLE C_Payment_v IS 'Payment Information corrected for AP/AR'
--;

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW GL_JournalLine_Acct_v
AS
SELECT 
	gl.GL_JournalLine_ID, gl.AD_Client_ID, gl.AD_Org_ID, gl.IsActive, 
	gl.Created, gl.CreatedBy, gl.Updated, gl.UpdatedBy, gl.GL_Journal_ID, 
	gl.Line, gl.IsGenerated, gl.Description, 
	gl.AmtSourceDr, gl.AmtSourceCr, gl.C_Currency_ID, 
	gl.C_ConversionType_ID, gl.CurrencyRate, gl.DateAcct, 
	gl.AmtAcctDr, gl.AmtAcctCr, gl.C_UOM_ID, gl.Qty, gl.C_ValidCombination_ID, 
	vc.C_AcctSchema_ID, vc.Account_ID, vc.M_Product_ID, vc.C_BPartner_ID,
	vc.AD_OrgTrx_ID, vc.C_LocFrom_ID, vc.C_LocTo_ID, vc.C_SalesRegion_ID, 
	vc.C_Project_ID, vc.C_Campaign_ID, vc.User1_ID, vc.User2_ID, 
	vc.IsFullyQualified, vc.C_Activity_ID
FROM GL_JournalLine gl, C_ValidCombination vc
WHERE gl.C_ValidCombination_ID = vc.C_ValidCombination_ID
;

--  Acct with Rate

CREATE OR REPLACE VIEW RV_Fact_Acct
AS
SELECT f.AD_Client_ID, f.AD_Org_ID, f.IsActive,f.Created,f.CreatedBy,f.Updated,f.UpdatedBy,
    f.Fact_Acct_ID,
    f.C_AcctSchema_ID, f.Account_ID, f.DateTrx, f.DateAcct, f.C_Period_ID, 
    f.AD_Table_ID, f.Record_ID, f.Line_ID,
    f.GL_Category_ID, f.GL_Budget_ID, f.C_Tax_ID, f.M_Locator_ID, 
    f.PostingType, f.C_Currency_ID,
    f.AmtSourceDr, f.AmtSourceCr, (f.AmtSourceDr - f.AmtSourceCr) AS AmtSource,
    f.AmtAcctDr, f.AmtAcctCr, (f.AmtAcctDr - f.AmtAcctCr) AS AmtAcct,
    CASE WHEN (f.AmtSourceDr - f.AmtSourceCr) = 0 THEN 0 ELSE
        (f.AmtAcctDr - f.AmtAcctCr) / (f.AmtSourceDr - f.AmtSourceCr) END AS Rate,
    f.C_UOM_ID, f.Qty,
    f.M_Product_ID, f.C_BPartner_ID, f.AD_OrgTrx_ID, 
    f.C_LocFrom_ID, f.C_LocTo_ID, f.C_SalesRegion_ID,
    f.C_Project_ID, f.C_Campaign_ID, f.C_Activity_ID, 
    f.User1_ID, f.User2_ID, f.A_Asset_ID,
    f.Description,
    o.Value AS OrgValue, o.Name AS OrgName,
    ev.Value AS AccountValue, ev.Name, ev.AccountType,
    bp.Value AS BPartnerValue, bp.Name AS BPName, bp.C_BP_Group_ID, 
    p.Value AS ProductValue, p.Name AS ProductName, p.UPC, p.M_Product_Category_ID
FROM Fact_Acct f
  INNER JOIN AD_Org o ON (f.AD_Org_ID=o.AD_Org_ID)
  INNER JOIN C_ElementValue ev ON (f.Account_ID=ev.C_ElementValue_ID)
  LEFT OUTER JOIN C_BPartner bp ON (f.C_BPartner_ID=bp.C_BPartner_ID)
  LEFT OUTER JOIN M_Product p ON (f.M_Product_ID=p.M_Product_ID)
;

--  Acct Details Daily

CREATE OR REPLACE VIEW RV_Fact_Acct_Day
AS
SELECT AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, firstOf(DateAcct, 'DD') AS DateAcct, C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    SUM(AmtSourceDr) AS AmtSourceDr, SUM(AmtSourceCr) AS AmtSourceCr, SUM(AmtSourceDr - AmtSourceCr) AS AmtSource,
    SUM(AmtAcctDr) AS AmtAcctDr, SUM(AmtAcctCr) AS AmtAcctCr, SUM(AmtAcctDr - AmtAcctCr) AS AmtAcct,
    CASE WHEN SUM(AmtSourceDr - AmtSourceCr) = 0 THEN 0 ELSE
        SUM(AmtAcctDr - AmtAcctCr) / SUM(AmtSourceDr - AmtSourceCr) END AS Rate,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
FROM Fact_Acct
GROUP BY AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, firstOf(DateAcct, 'DD'), C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
;

--  Acct Details Period

CREATE OR REPLACE VIEW RV_Fact_Acct_Period
AS
SELECT AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    SUM(AmtSourceDr) AS AmtSourceDr, SUM(AmtSourceCr) AS AmtSourceCr, SUM(AmtSourceDr - AmtSourceCr) AS AmtSource,
    SUM(AmtAcctDr) AS AmtAcctDr, SUM(AmtAcctCr) AS AmtAcctCr, SUM(AmtAcctDr - AmtAcctCr) AS AmtAcct,
    CASE WHEN SUM(AmtSourceDr - AmtSourceCr) = 0 THEN 0 ELSE
        SUM(AmtAcctDr - AmtAcctCr) / SUM(AmtSourceDr - AmtSourceCr) END AS Rate,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
FROM Fact_Acct
GROUP BY AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
;

-------------------------------------------------------------------------------

CREATE OR REPLACE VIEW RV_C_RfQ_UnAnswered
AS
SELECT q.AD_Client_ID, q.AD_Org_ID, q.C_RfQ_ID, q.Name, q.Description, q.Help, q.SalesRep_ID,
    q.C_RfQ_Topic_ID, q.QuoteType, q.IsQuoteTotalAmt, q.IsQuoteAllQty, q.C_Currency_ID,
    q.DateResponse, q.IsRfQResponseAccepted, q.DateWorkStart, q.DeliveryDays, q.DateWorkComplete,
    r.C_BPartner_ID, r.C_BPartner_Location_ID, r.AD_User_ID
FROM C_RfQ q
    INNER JOIN C_RfQResponse r ON (q.C_RfQ_ID=r.C_RfQ_ID)
WHERE r.IsComplete='N'
    AND q.Processed='N'
;

CREATE OR REPLACE VIEW RV_C_RfQResponse
AS
SELECT q.AD_Client_ID, q.AD_Org_ID, q.C_RfQ_ID, q.C_RfQ_Topic_ID,
    r.C_BPartner_ID, r.C_BPartner_Location_ID, r.AD_User_ID,
    r.C_RfQResponse_ID,
    r.C_Currency_ID, r.DateResponse, r.DateWorkStart, r.DeliveryDays, r.DateWorkComplete,
    r.Price, r.Ranking, r.IsSelfService,
    r.Description, r.Help,
    --  Line
    ql.M_Product_ID, ql.M_AttributeSetInstance_ID, 	
    ql.Line, rl.DateWorkStart AS LineDateWorkStart, rl.DeliveryDays AS LineDeliveryDays, rl.DateWorkComplete AS LineDateworkComplete,
    rl.Description AS LineDescription, rl.Help AS LineHelp,
    --  Qty
    qlq.C_UOM_ID, qlq.Qty, qlq.BenchmarkPrice, rlq.Price-qlq.BenchmarkPrice AS BenchmarkDifference,
    rlq.Price AS QtyPrice, rlq.Discount, rlq.Ranking AS QtyRanking
FROM C_RfQ q
    INNER JOIN C_RfQLine ql ON (q.C_RfQ_ID = ql.C_RfQ_ID)
    INNER JOIN C_RfQLineQty qlq ON (ql.C_RfQLine_ID = qlq.C_RfQLine_ID)
    INNER JOIN C_RfQResponse r ON (q.C_RfQ_ID = r.C_RfQ_ID)
    INNER JOIN C_RfQResponseLine rl ON 
        (r.C_RfQResponse_ID = rl.C_RfQResponse_ID AND ql.C_RfQLine_ID = rl.C_RfQLine_ID)
    INNER JOIN C_RfQResponseLineQty rlq ON 
        (rl.C_RfQResponseLine_ID = rlq.C_RfQResponseLine_ID AND qlq.C_RfQLineQty_ID = rlq.C_RfQLineQty_ID)
WHERE r.IsComplete='Y'
    AND q.Processed='N'
;

CREATE OR REPLACE VIEW RV_M_Requisition
AS
SELECT r.M_Requisition_ID,
    r.AD_Client_ID, r.AD_Org_ID, r.IsActive, r.Created, r.CreatedBy, r.Updated, r.UpdatedBy,
    r.DocumentNo, r.Description, r.Help,
    r.AD_User_ID, r.M_PriceList_ID, r.M_Warehouse_ID, r.IsApproved, r.PriorityRule,
    r.DateRequired, r.TotalLines, r.DocAction, r.DocStatus, r.Processed,
    l.M_RequisitionLine_ID, l.Line,
    l.Qty, l.M_Product_ID,
    l.Description AS LineDescription,
    l.PriceActual, l.LineNetAmt
FROM M_Requisition r
  INNER JOIN M_RequisitionLine l ON (r.M_Requisition_ID=l.M_Requisition_ID)
;

CREATE OR REPLACE VIEW RV_InOutConfirm
AS
SELECT c.M_InOutConfirm_ID,
  c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created, c.CreatedBy, c.Updated, c.UpdatedBy,
  c.M_InOut_ID, c.DocumentNo, c.ConfirmType, c.IsApproved, c.IsCancelled,
  c.Description, c.Processing, c.Processed,
  i.C_BPartner_ID, i.C_BPartner_Location_ID, i.M_Warehouse_ID, i.C_Order_ID, i.IsSOTrx
FROM M_InOutConfirm c
  INNER JOIN M_InOut i ON (c.M_InOut_ID=i.M_InOut_ID)
;

CREATE OR REPLACE VIEW RV_InOutLineConfirm
AS
SELECT cl.M_InOutConfirm_ID, cl.M_InOutLineConfirm_ID,
  cl.AD_Client_ID, cl.AD_Org_ID, cl.IsActive, cl.Created, cl.CreatedBy, cl.Updated, cl.UpdatedBy,
  cl.TargetQty, cl.ConfirmedQty, cl.DifferenceQty, cl.ScrappedQty,
  cl.Description, cl.Processed,
  c.M_InOut_ID, c.DocumentNo, c.ConfirmType, c.IsApproved, c.IsCancelled,
  i.C_BPartner_ID, i.C_BPartner_Location_ID, i.M_Warehouse_ID, i.C_Order_ID, i.IsSOTrx,
  cl.M_InOutLine_ID, il.M_Product_ID, il.M_AttributeSetInstance_ID, il.M_Locator_ID
FROM M_InOutLineConfirm cl
  INNER JOIN M_InOutConfirm c ON (cl.M_InOutConfirm_ID=c.M_InOutConfirm_ID)
  INNER JOIN M_InOut i ON (c.M_InOut_ID=i.M_InOut_ID)
  INNER JOIN M_InOutLine il ON (cl.M_InOutLine_ID=il.M_InOutLine_ID)
;

CREATE OR REPLACE VIEW RV_Allocation
AS
SELECT h.C_AllocationHdr_ID, h.AD_Client_ID, h.AD_Org_ID, 
  h.IsActive, h.Created, h.CreatedBy, h.Updated, h.UpdatedBy,
  h.DocumentNo, h.Description, h.DateTrx, h.DateAcct,
  h.C_Currency_ID, h.ApprovalAmt, h.IsManual, h.DocStatus, h.DocAction, h.Processed,
  l.C_AllocationLine_ID,
  l.C_Invoice_ID, l.C_BPartner_ID, l.C_Order_ID, l.C_Payment_ID, l.C_CashLine_ID,
  l.Amount, l.DiscountAmt, l.WriteOffAmt, l.OverUnderAmt
FROM C_AllocationHdr h
  INNER JOIN C_AllocationLine l ON (h.C_AllocationHdr_ID=l.C_AllocationHdr_ID)
;  


CREATE OR REPLACE VIEW RV_PrintFormatDetail
AS
SELECT f.AD_Client_ID, f.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
    f.AD_PrintFormat_ID,
    f.Name, f.Description,
    f.IsTableBased,f.IsForm,f.AD_Table_ID,f.AD_ReportView_ID,
    f.AD_PrintPaper_ID, 
    f.AD_PrintColor_ID AS Default_AD_PrintColor_ID,
    f.AD_PrintFont_ID AS Default_AD_PrintFont_ID,
    f.IsStandardHeaderFooter,
    f.AD_PrintTableFormat_ID,
    f.HeaderMargin,f.FooterMargin,
    f.PrinterName,f.IsDefault,
    i.AD_PrintFormatItem_ID,
    i.Name AS ItemName, i.PrintName, i.PrintNameSuffix, i.IsPrinted, i.PrintAreaType, i.SeqNo,
    i.PrintFormatType, i.AD_Column_ID, i.AD_PrintFormatChild_ID, i.ImageIsAttached,i.ImageURL,
    i.IsRelativePosition, i.IsNextLine, XSpace,YSpace, XPosition,YPosition,
    i.MaxWidth, IsHeightOneLine, MaxHeight, i.IsFixedWidth,
    i.IsSetNLPosition,i.IsSuppressNull, i.BelowColumn,
    i.FieldAlignmentType,i.LineAlignmentType,
    i.AD_PrintColor_ID,i.AD_PrintFont_ID,
    i.IsOrderBy,i.SortNo, i.IsGroupBy,i.IsPageBreak,i.IsNextPage,
    i.IsSummarized,i.IsAveraged,i.IsCounted,i.IsMinCalc,i.IsMaxCalc,
    i.IsVarianceCalc,i.IsDeviationCalc,
    i.IsRunningTotal,i.RunningTotalLines,
    i.AD_PrintGraph_ID
FROM AD_PrintFormat f
  INNER JOIN AD_PrintFormatItem i ON (f.AD_PrintFormat_ID=i.AD_PrintFormat_ID)
;

CREATE OR REPLACE VIEW RV_InOutDetails
AS
SELECT h.AD_Client_ID, h.AD_Org_ID, l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,
  h.M_InOut_ID,
  h.IsSOTrx, h.DocumentNo, h.DocAction, h.DocStatus, h.Posted, h.Processed,
  h.C_DocType_ID, h.Description, h.C_Order_ID, h.DateOrdered,
  h.MovementType, h.MovementDate, h.DateAcct,
  h.C_BPartner_ID, h.C_BPartner_Location_ID, h.AD_User_ID,
  h.SalesRep_ID,
  h.M_Warehouse_ID,
  h.POReference, h.DeliveryRule, h.FreightCostRule, h.FreightAmt,
  h.DeliveryViaRule, h.M_Shipper_ID, -- h.C_CHARGE_ID, h.CHARGEAmt,
  h.PriorityRule, h.DatePrinted,
  h.NoPackages, h.PickDate, h.ShipDate, h.TrackingNo,
  h.AD_OrgTrx_ID, h.C_Project_ID, h.C_Campaign_ID, h.C_Activity_ID, h.User1_ID, h.User2_ID,
  h.DateReceived, h.IsApproved, h.IsInDispute,
  l.M_InOutLine_ID, l.Line, l.Description AS LineDescription,
  l.C_OrderLine_ID, l.M_Locator_ID,
  l.M_Product_ID, l.C_UOM_ID,
  l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
  pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
  l.MovementQty, l.QtyEntered,
  l.IsDescription,
  l.ConfirmedQty, l.PickedQty, l.ScrappedQty, l.TargetQty,
  loc.Value AS LocatorValue, loc.X, loc.Y, loc.Z
FROM M_InOut h
  INNER JOIN M_InOutLine l ON (h.M_InOut_ID=l.M_InOut_ID)
  LEFT OUTER JOIN M_Locator loc ON (l.M_Locator_ID=loc.M_Locator_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
;

CREATE OR REPLACE VIEW AD_ChangeLog_v
AS
SELECT l.AD_Session_ID, l.AD_ChangeLog_ID, 
    t.TableName, l.Record_ID, c.ColumnName, 
    l.OldValue, l.NewValue, 
    u.Name, l.Created
FROM AD_ChangeLog l
    INNER JOIN AD_Table t ON (l.AD_Table_ID=t.AD_Table_ID)
    INNER JOIN AD_Column c ON (l.AD_Column_ID=c.AD_Column_ID)
    INNER JOIN AD_User u ON (l.CreatedBy=u.AD_User_ID)
ORDER BY l.AD_Session_ID, l.AD_ChangeLog_ID, t.TableName, l.Record_ID, c.ColumnName
;

CREATE OR REPLACE VIEW RV_BPartnerOpen
AS
SELECT i.AD_Client_ID,i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
    i.C_BPartner_ID, i.C_Currency_ID,
    i.GrandTotal*i.MultiplierAP AS Amt,
    invoiceOpen (i.C_Invoice_ID, i.C_InvoicePaySchedule_ID)*MultiplierAP AS OpenAmt,
    i.DateInvoiced AS DateDoc, 
    COALESCE(daysBetween(getdate(),ips.DueDate), paymentTermDueDays(C_PaymentTerm_ID,DateInvoiced,getdate())) AS DaysDue
FROM C_Invoice_v i 
  LEFT OUTER JOIN C_InvoicePaySchedule ips ON (i.C_InvoicePaySchedule_ID=ips.C_InvoicePaySchedule_ID)
WHERE IsPaid='N'
 AND DocStatus IN ('CO','CL')
UNION
SELECT p.AD_Client_ID,p.AD_Org_ID, p.IsActive, p.Created,p.CreatedBy,p.Updated,p.UpdatedBy,
    p.C_BPartner_ID, p.C_Currency_ID,
    p.PayAmt*MultiplierAP*-1 AS Amt,
    paymentAvailable(p.C_Payment_ID)*p.MultiplierAP*-1 AS OpenAmt,
    p.DateTrx AS DateDoc,
    null
FROM C_Payment_v p 
WHERE p.IsAllocated='N' AND p.C_BPartner_ID IS NOT NULL
 AND p.DocStatus IN ('CO','CL')
;

CREATE OR REPLACE VIEW RV_CommissionRunDetail
AS
SELECT cr.AD_Client_ID, cr.AD_Org_ID, cr.IsActive, cr.Created,cr.CreatedBy, cr.Updated,cr.UpdatedBy,
    -- Run
    cr.C_CommissionRun_ID, cr.DocumentNo, cr.Description, 
    cr.StartDate, cr.GrandTotal, cr.Processed,
    -- Commission
    c.C_Commission_ID, c.C_BPartner_ID AS Commission_BPartner_ID,
    --  Commission Amount
    ca.C_CommissionAmt_ID, 
    ca.ConvertedAmt AS CommissionConvertedAmt, ca.ActualQty AS CommissionQty, 
    ca.CommissionAmt,
    --  Commission Detail
    cd.C_CommissionDetail_ID,
    cd.Reference,
    cd.C_OrderLine_ID,
    cd.C_InvoiceLine_ID,
    cd.Info,
    cd.C_Currency_ID, cd.ActualAmt, cd.ConvertedAmt,
    cd.ActualQty,
    -- Invoice/Order
    i.DocumentNo AS InvoiceDocumentNo,
    COALESCE (i.DateInvoiced, o.DateOrdered) AS DateDoc,
    COALESCE (il.M_Product_ID,ol.M_Product_ID) AS M_Product_ID,
    COALESCE (i.C_BPartner_ID,o.C_BPartner_ID) AS C_BPartner_ID,
    COALESCE (i.C_BPartner_Location_ID,o.C_BPartner_Location_ID) AS C_BPartner_Location_ID,
    COALESCE (i.AD_User_ID,o.AD_User_ID) AS AD_User_ID,
    COALESCE (i.C_DocType_ID,o.C_DocType_ID) AS C_DocType_ID
FROM C_CommissionRun cr
    INNER JOIN C_Commission c ON (cr.C_Commission_ID=c.C_Commission_ID)
    INNER JOIN C_CommissionAmt ca ON (cr.C_CommissionRun_ID=ca.C_CommissionRun_ID)
    INNER JOIN C_CommissionDetail cd ON (ca.C_CommissionAmt_ID=cd.C_CommissionAmt_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (cd.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_InvoiceLine il ON (cd.C_InvoiceLine_ID=il.C_InvoiceLine_ID)
    LEFT OUTER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID)
    LEFT OUTER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID)
;

CREATE OR REPLACE VIEW M_Transaction_v
AS
SELECT M_Transaction_ID, 
  t.AD_Client_ID, t.AD_Org_ID, t.IsActive, t.Created,t.CreatedBy, t.Updated,t.UpdatedBy,
  t.MovementType, t.M_Locator_ID, t.M_Product_ID, t.MovementDate, t.MovementQty,
  t.M_InventoryLine_ID, i.M_Inventory_ID,
  t.M_MovementLine_ID, m.M_Movement_ID,
  t.M_InOutLine_ID, io.M_InOut_ID,
  t.M_ProductionLine_ID, pp.M_Production_ID,
  t.C_ProjectIssue_ID, pi.C_Project_ID,
  t.M_AttributeSetInstance_ID
FROM M_Transaction t
  LEFT OUTER JOIN M_InOutLine io ON (t.M_InOutLine_ID=io.M_InOutLine_ID AND t.M_AttributeSetInstance_ID=io.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_MovementLine m ON (t.M_MovementLine_ID=m.M_MovementLine_ID AND t.M_AttributeSetInstance_ID=m.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine i ON (t.M_InventoryLine_ID=i.M_InventoryLine_ID AND t.M_AttributeSetInstance_ID=i.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN C_ProjectIssue pi ON (t.C_ProjectIssue_ID=pi.C_ProjectIssue_ID AND t.M_AttributeSetInstance_ID=pi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_ProductionLine pl ON (t.M_ProductionLine_ID=pl.M_ProductionLine_ID AND t.M_AttributeSetInstance_ID=pl.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_ProductionPlan pp ON (pl.M_ProductionPlan_ID=pp.M_ProductionPlan_ID)
;


CREATE OR REPLACE VIEW RV_RequestUpdates
AS
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    R_Request_ID, AD_User_ID, IsSelfService, 
    -- null AS R_Group_ID, null AS R_RequestType_ID, null AS R_Category_ID
    0 AS R_Group_ID, 0 AS R_RequestType_ID, 0 AS R_Category_ID
FROM R_RequestUpdates
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    r.R_Group_ID, NULL, NULL
FROM R_GroupUpdates u
    INNER JOIN R_Request r ON (u.R_Group_ID=r.R_Group_ID)
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    NULL, r.R_RequestType_ID, NULL
FROM R_RequestTypeUpdates u
    INNER JOIN R_Request r ON (u.R_RequestType_ID=r.R_RequestType_ID)
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    NULL, NULL, r.R_Category_ID
FROM R_CategoryUpdates u
    INNER JOIN R_Request r ON (u.R_Category_ID=r.R_Category_ID)
UNION   --  BP User
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    R_Request_ID, AD_User_ID, IsSelfService, 
    NULL, NULL, NULL
FROM R_Request
WHERE AD_User_ID IS NOT NULL
UNION   --  SalesRep
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, NULL, 
    NULL, NULL, r.R_Category_ID
FROM AD_User u
    INNER JOIN R_Request r ON (u.AD_User_ID=r.SalesRep_ID)
UNION   --  Role
SELECT r.AD_Client_ID, r.AD_Org_ID, u.IsActive, r.Created, r.CreatedBy, r.Updated, r.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, NULL, 
    NULL, NULL, NULL
FROM R_Request r
    INNER JOIN AD_User_Roles u ON (u.AD_Role_ID=r.AD_Role_ID)
;
CREATE OR REPLACE VIEW RV_RequestUpdates_Only
AS
SELECT MIN(AD_Client_ID) AS AD_Client_ID, MIN(AD_Org_ID) AS AD_ORG_ID, 'Y' AS IsActive, 
    --SysDate AS Created, 0 AS CreatedBy, SysDate AS Updated, 0 AS UpdatedBy,
    NOW() AS Created, 0 AS CreatedBy, NOW() AS Updated, 0 AS UpdatedBy,
    R_Request_ID, AD_User_ID
FROM RV_RequestUpdates
WHERE IsActive='Y'
GROUP BY R_Request_ID, AD_User_ID
;


--SELECT 'End of Views' FROM DUAL