CREATE OR REPLACE VIEW C_PROJECT_DETAILS_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_PROJECT_ID, C_PROJECTLINE_ID, 
 LINE, PLANNEDQTY, PLANNEDPRICE, PLANNEDAMT, PLANNEDMARGINAMT, 
 COMMITTEDAMT, M_PRODUCT_ID, NAME, DESCRIPTION, DOCUMENTNOTE, 
 UPC, SKU, PRODUCTVALUE, M_PRODUCT_CATEGORY_ID, INVOICEDAMT, 
 INVOICEDQTY, COMMITTEDQTY)
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
WHERE pl.IsPrinted='Y';



