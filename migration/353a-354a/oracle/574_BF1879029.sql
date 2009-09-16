-- Oct 8, 2008 6:48:17 PM COT
-- [ 1879029 ] Added IsInternalUseInventory flag to M_Inventory table
UPDATE AD_Tab SET WhereClause='(SELECT COUNT(*) FROM M_InventoryLine WHERE M_InventoryLine.M_Inventory_ID = M_Inventory.M_Inventory_ID) = 0 OR (SELECT COUNT(*) FROM M_InventoryLine WHERE M_InventoryLine.M_Inventory_ID = M_Inventory.M_Inventory_ID AND M_InventoryLine.QtyInternalUse <> 0) > 0',Updated=TO_DATE('2008-10-08 18:48:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=682
;

UPDATE AD_Tab SET WhereClause='(SELECT COUNT(*) FROM M_InventoryLine WHERE M_InventoryLine.M_Inventory_ID = M_Inventory.M_Inventory_ID) = 0 OR (SELECT COUNT(*) FROM M_InventoryLine WHERE M_InventoryLine.M_Inventory_ID = M_Inventory.M_Inventory_ID AND M_InventoryLine.QtyInternalUse <> 0) <= 0',Updated=TO_DATE('2008-10-08 18:50:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=255
;

