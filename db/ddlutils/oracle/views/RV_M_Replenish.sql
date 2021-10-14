-- View: RV_M_Replenish;
-- DROP VIEW RV_M_Replenish;
CREATE OR REPLACE VIEW RV_M_Replenish AS
SELECT r.AD_Client_ID,
    r.AD_Org_ID,
    r.M_Product_ID,
    r.ReplenishType,
    r.Level_Min,
    r.Level_Max,
    r.QtyAvailable,
    r.QtyOnHand,
    r.QtyReserved,
    r.QtyOrdered,
    CASE
    	WHEN r.Order_Pack > 0 AND MOD(CASE
    				WHEN r.QtyToOrder < r.Order_Min AND r.QtyToOrder > 0
    				THEN r.Order_Min
    				ELSE r.QtyToOrder
    			END, r.Order_Pack) <> 0 AND r.QtyToOrder > 0
    	THEN CASE
    			WHEN r.QtyToOrder < r.Order_Min AND r.QtyToOrder > 0
    			THEN r.Order_Min
    			ELSE r.QtyToOrder
    		END - MOD(CASE
    					WHEN r.QtyToOrder < r.Order_Min AND r.QtyToOrder > 0
    					THEN r.Order_Min
    					ELSE r.QtyToOrder
    				END, r.Order_Pack) + r.Order_Pack
    	ELSE r.QtyToOrder
    END AS QtyToOrder,
    r.M_Product_Category_ID,
    r.M_Product_Group_ID,
    r.M_Product_Class_ID,
    r.M_Product_Classification_ID,
    r.C_UOM_ID,
    r.M_Locator_ID,
    r.M_Warehouse_ID,
    r.M_WarehouseSource_ID,
    r.C_BPartner_ID,
    r.Order_Min,
    r.Order_Pack
FROM (SELECT r.AD_Client_ID,
        r.AD_Org_ID,
        r.M_Product_ID,
        r.ReplenishType,
        COALESCE(r.Level_Min, 0) AS Level_Min,
    	COALESCE(r.Level_Max, 0) AS Level_Max,
    	COALESCE(s.QtyAvailable, 0) AS QtyAvailable,
    	COALESCE(s.QtyOnHand, 0) AS QtyOnHand,
    	COALESCE(s.QtyReserved, 0) AS QtyReserved,
    	COALESCE(s.QtyOrdered, 0) AS QtyOrdered,
        COALESCE(CASE
            WHEN r.ReplenishType = '1' THEN
                CASE
                    WHEN COALESCE(s.QtyOnHand, 0) - COALESCE(s.QtyReserved, 0) + COALESCE(s.QtyOrdered, 0) <= r.Level_Min
                    THEN r.Level_Max - COALESCE(s.QtyOnHand, 0) + COALESCE(s.QtyReserved, 0) - COALESCE(s.QtyOrdered, 0)
                    ELSE 0
                END
            WHEN r.ReplenishType = '2' THEN r.Level_Max - COALESCE(s.QtyOnHand, 0) + COALESCE(s.QtyReserved, 0) - COALESCE(s.QtyOrdered, 0)
            ELSE 0
        END, 0) AS QtyToOrder,
        p.M_Product_Category_ID,
        p.M_Product_Group_ID,
        p.M_Product_Class_ID,
        p.M_Product_Classification_ID,
        p.C_UOM_ID,
        r.M_Locator_ID,
        r.M_Warehouse_ID,
        r.M_WarehouseSource_ID,
        po.C_BPartner_ID,
        COALESCE(po.Order_Min, 0) AS Order_Min,
        COALESCE(po.Order_Pack, 0) AS Order_Pack
    FROM M_Replenish r
    INNER JOIN M_Product p ON(p.M_Product_ID = r.M_Product_ID)
    LEFT JOIN M_Product_PO po ON(po.M_Product_ID = r.M_Product_ID AND po.IsActive = 'Y' AND po.IsCurrentVendor = 'Y')
    LEFT JOIN (SELECT s.M_Product_ID,
                SUM(s.QtyOnHand) AS QtyOnHand,
                SUM(s.QtyOrdered) AS QtyOrdered,
                SUM(s.QtyReserved) AS QtyReserved,
                SUM(s.QtyAvailable) AS QtyAvailable
            FROM RV_Storage s
            GROUP BY s.M_Product_ID) s ON(s.M_Product_ID = p.M_Product_ID)
    WHERE r.IsActive = 'Y' AND p.IsActive = 'Y') r
WHERE (r.QtyToOrder > 0
OR (r.ReplenishType IN('0', '9')));