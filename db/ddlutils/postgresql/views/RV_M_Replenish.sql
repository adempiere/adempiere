-- View: RV_M_Replenish
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
FROM (SELECT rep.ad_client_id,
            rep.ad_org_id,
            rep.m_product_id,
            rep.replenishtype,
            COALESCE(rep.level_min, 0 ) AS level_min,
            COALESCE(rep.level_max, 0 ) AS level_max,
            COALESCE(s.qtyavailable, 0 ) AS qtyavailable,
            COALESCE(s.qtyonhand, 0 ) AS qtyonhand,
            COALESCE(s.qtyreserved, 0 ) AS qtyreserved,
            COALESCE(s.qtyordered, 0 ) AS qtyordered,
            COALESCE(
                CASE
                    WHEN rep.replenishtype = '1'  THEN
                    CASE
                        WHEN (COALESCE(s.qtyonhand, 0 ) - COALESCE(s.qtyreserved, 0 ) + COALESCE(s.qtyordered, 0 )) <= rep.level_min THEN rep.level_max - COALESCE(s.qtyonhand, 0 ) + COALESCE(s.qtyreserved, 0 ) - COALESCE(s.qtyordered, 0 )
                        ELSE 0 
                    END
                    WHEN rep.replenishtype = '2'  THEN rep.level_max - COALESCE(s.qtyonhand, 0 ) + COALESCE(s.qtyreserved, 0 ) - COALESCE(s.qtyordered, 0 )
                    ELSE 0 
                END, 0 ) AS qtytoorder,
            p.m_product_category_id,
            p.m_product_group_id,
            p.m_product_class_id,
            p.m_product_classification_id,
            p.c_uom_id,
            rep.m_locator_id,
            rep.m_warehouse_id,
            rep.m_warehousesource_id,
            po.c_bpartner_id,
            COALESCE(po.order_min, 0 ) AS order_min,
            COALESCE(po.order_pack, 0 ) AS order_pack
           FROM m_replenish rep
             JOIN m_product p ON p.m_product_id = rep.m_product_id
             LEFT JOIN m_product_po po ON po.m_product_id = rep.m_product_id AND po.isactive = 'Y'  AND po.iscurrentvendor = 'Y'
    LEFT JOIN (SELECT sto.m_product_id,
                    sum(sto.qtyonhand) AS qtyonhand,
                    sum(sto.qtyordered) AS qtyordered,
                    sum(sto.qtyreserved) AS qtyreserved,
                    sum(sto.qtyavailable) AS qtyavailable,
            sto.M_Warehouse_ID
                   FROM rv_storage sto
                  GROUP BY sto.m_product_id,sto.M_Warehouse_ID) s ON(s.M_Product_ID = p.M_Product_ID AND s.M_Warehouse_ID=rep.M_Warehouse_ID)
    WHERE rep.IsActive = 'Y' AND p.IsActive = 'Y') r
WHERE (r.QtyToOrder > 0
OR (r.ReplenishType IN('0', '9')));