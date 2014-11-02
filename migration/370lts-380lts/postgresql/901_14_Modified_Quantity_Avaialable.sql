-- Oct 3, 2013 3:17:08 PM IST
-- Quantity Available Column is Modified in Production Line Tab of Production Window
UPDATE AD_Column SET ColumnSQL=' (SELECT (CASE WHEN ((SELECT ISBOM FROM M_Product WHERE M_Product_ID=M_ProductionLine.M_Product_ID) != ''N'') THEN (SELECT bomQtyOnHand(M_ProductionLine.M_Product_ID,l.M_Warehouse_ID,0) - bomQtyReserved(M_ProductionLine.M_Product_ID,l.M_Warehouse_ID,0) FROM M_Locator l WHERE l.M_Locator_ID=M_ProductionLine.M_Locator_ID) ELSE (SELECT bomQtyOnHand(M_ProductionLine.M_Product_ID,l.M_Warehouse_ID,0) - prodQtyReserved(M_ProductionLine.M_Product_ID,l.M_Warehouse_ID,0) FROM M_Locator l WHERE l.M_Locator_ID=M_ProductionLine.M_Locator_ID) END) FROM M_Locator l WHERE l.M_Locator_ID=M_ProductionLine.M_Locator_ID) ',Updated=TO_TIMESTAMP('2013-10-03 15:17:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=61946
;

