CREATE OR REPLACE VIEW C_RFQRESPONSELINE_VT
(C_RFQRESPONSE_ID, C_RFQRESPONSELINE_ID, C_RFQLINE_ID, C_RFQRESPONSELINEQTY_ID, C_RFQLINEQTY_ID, 
 AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, LINE, M_PRODUCT_ID, 
 M_ATTRIBUTESETINSTANCE_ID, NAME, DESCRIPTION, DOCUMENTNOTE, UPC, 
 SKU, PRODUCTVALUE, HELP, DATEWORKSTART, DELIVERYDAYS, 
 C_UOM_ID, UOMSYMBOL, QTY, PRICE, DISCOUNT)
AS 
SELECT rrl.C_RfQResponse_ID, rrl.C_RfQResponseLine_ID, rrl.C_RfQLine_ID, 
    rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rrl.AD_Client_ID, rrl.AD_Org_ID, rrl.IsActive, rrl.Created, rrl.CreatedBy, rrl.Updated, rrl.UpdatedBy,
	l.AD_Language,
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
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
WHERE rq.IsActive='Y' AND q.IsActive='Y'
  AND rrl.IsActive='Y' AND rl.IsActive='Y';



