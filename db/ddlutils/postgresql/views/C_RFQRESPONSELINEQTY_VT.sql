CREATE OR REPLACE VIEW C_RFQRESPONSELINEQTY_VT
(C_RFQRESPONSELINE_ID, C_RFQRESPONSELINEQTY_ID, C_RFQLINEQTY_ID, AD_CLIENT_ID, AD_ORG_ID, 
 ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY, 
 AD_LANGUAGE, C_UOM_ID, UOMSYMBOL, QTY, PRICE, 
 DISCOUNT)
AS 
SELECT rq.C_RfQResponseLine_ID, rq.C_RfQResponseLineQty_ID, rq.C_RfQLineQty_ID,
    rq.AD_Client_ID, rq.AD_Org_ID, rq.IsActive, rq.Created, rq.CreatedBy, rq.Updated, rq.UpdatedBy,
	l.AD_Language,
    q.C_UOM_ID, uom.UOMSymbol,
    q.Qty, rq.Price, rq.Discount
FROM C_RfQResponseLineQty rq
  INNER JOIN C_RfQLineQty q ON (rq.C_RfQLineQty_ID=q.C_RfQLineQty_ID)
  INNER JOIN C_UOM uom ON (q.C_UOM_ID=uom.C_UOM_ID)
  INNER JOIN AD_Language l ON (l.IsSystemLanguage='Y')
WHERE rq.IsActive='Y' AND q.IsActive='Y';



