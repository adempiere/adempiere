CREATE OR REPLACE VIEW RV_M_TRANSACTION
(AD_CLIENT_ID, AD_ORG_ID, MOVEMENTDATE, MOVEMENTQTY, M_PRODUCT_ID, 
 M_LOCATOR_ID, M_ATTRIBUTESETINSTANCE_ID, M_PRODUCT_CATEGORY_ID, VALUE, C_BPARTNER_ID, 
 PRICEPO, PRICELASTPO, PRICELIST)
AS 
SELECT t.AD_Client_ID,t.AD_Org_ID, t.MovementDate, t.MovementQty, 
	t.M_Product_ID, t.M_Locator_ID, t.M_AttributeSetInstance_ID,
	p.M_Product_Category_ID, p.Value, 
	po.C_BPartner_ID, po.PricePO, po.PriceLastPO, po.PriceList
FROM M_Transaction t
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  INNER JOIN M_Product_PO po ON (t.M_Product_ID=po.M_Product_ID)
WHERE po.IsCurrentVendor='Y';



