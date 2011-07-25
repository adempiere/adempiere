CREATE OR REPLACE VIEW RV_M_TRANSACTION_SUM
(AD_CLIENT_ID, AD_ORG_ID, MOVEMENTTYPE, M_WAREHOUSE_ID, M_LOCATOR_ID, 
 M_PRODUCT_ID, MOVEMENTDATE, MOVEMENTQTY)
AS 
SELECT t.AD_Client_ID, t.AD_Org_ID, 
	t.MovementType, l.M_Warehouse_ID, t.M_Locator_ID, t.M_Product_ID, t.MovementDate,
	SUM(t.MovementQty) AS MovementQty
FROM M_Transaction t, M_Locator l
WHERE t.M_Locator_ID=l.M_Locator_ID
GROUP BY t.AD_Client_ID, t.AD_Org_ID, 
	t.MovementType, l.M_Warehouse_ID, t.M_Locator_ID, t.M_Product_ID, t.MovementDate;



