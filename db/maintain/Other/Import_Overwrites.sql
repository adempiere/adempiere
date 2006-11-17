/**
 * Import records overwriting others & being overwritten
 */
SELECT H_ItemDesc, I_ErrorMsg, H_UPC, H_Commodity1, H_ItemDefn
FROM I_061_Sync_Item
WHERE SUBSTR(I_ErrorMsg, 14,7) IN
	(SELECT SUBSTR(I_ErrorMsg, 14,7) FROM I_061_Sync_Item
		GROUP BY SUBSTR(I_ErrorMsg, 14,7) HAVING Count(*) <> 1)
Order By SUBSTR(I_ErrorMsg, 14,7);
-- UPDATE I_061_Sync_Item SET I_ErrorMsg = 'XX' || I_ErrorMsg WHERE I_ErrorMsg IS NOT NULL;

-- Non unique UPCs
SELECT H_ItemDesc, I_ErrorMsg, H_UPC, H_Commodity1, H_ItemDefn
FROM I_061_Sync_Item
WHERE H_UPC IN (SELECT H_UPC FROM I_061_Sync_Item GROUP BY H_UPC HAVING COUNT(*) > 1)
ORDER BY 3;
SELECT Value, Name, UPC, Help
FROM M_Product
WHERE UPC IN (SELECT UPC FROM M_Product GROUP BY UPC HAVING COUNT(*) > 1)
ORDER BY 3;
--	Multiple vendors
SELECT Value, Name FROM M_Product
WHERE M_Product_ID IN (
	SELECT M_Product_ID FROM M_Product_PO WHERE IsCurrentVendor='Y' GROUP BY M_Product_ID HAVING COUNT(*) > 1)

