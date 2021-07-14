DROP VIEW RV_M_SlowMovingWarehouseInventory;

CREATE VIEW RV_M_SlowMovingWarehouseInventory AS
SELECT
  p.AD_Client_ID,
  p.AD_Org_ID,
  p.Created,
  p.CreatedBy,
  p.Updated,
  p.UpdatedBy,
  p.M_Product_ID,
  p.Value,
  p.Name,
  p.Description,
  p.M_Product_Category_ID,
  p.M_Product_Group_ID,
  p.M_PRoduct_Class_ID,
  p.M_Product_Classification_ID,
  p.C_UOM_ID,
  p.IsActive,
  p.isStocked,
  p.Discontinued,
  l.M_Warehouse_ID,
  COALESCE(SUM(s.QtyOnhand), 0) AS QtyOnhand,
  ppo.PriceLastPO,
  ppo.PriceLastInv,
  pp.M_PriceList_Version_ID,
  pp.PriceList,
  pp.PriceStd,
  pp.PriceLimit,
  (
    SELECT
      MAX(t.MovementDate)
    FROM
      M_Transaction t
      INNER JOIN M_Locator sl ON (t.M_Locator_ID = sl.M_Locator_ID)
    WHERE
      t.M_Product_ID = p.M_Product_ID
      AND sl.M_Warehouse_ID = l.M_Warehouse_ID
      AND t.MovementType LIKE '%+'
  ) AS LastDateReceipted,
  (
    SELECT
      MAX(t.MovementDate)
    FROM
      M_Transaction t
      INNER JOIN M_Locator sl ON (t.M_Locator_ID = sl.M_Locator_ID)
    WHERE
      t.M_Product_ID = p.M_Product_ID
      AND sl.M_Warehouse_ID = l.M_Warehouse_ID
      AND t.MovementType LIKE '%-'
  ) AS LastDateDelivered,
  getDate() :: date - COALESCE (
    (
      SELECT
        MAX(t.MovementDate)
      FROM
        M_Transaction t
        INNER JOIN M_Locator sl ON (t.M_Locator_ID = sl.M_Locator_ID)
      WHERE
        t.M_Product_ID = p.M_Product_ID
        AND sl.M_Warehouse_ID = l.M_Warehouse_ID
        AND t.MovementType LIKE '%-'
    ) :: date,
    (
      SELECT
        MAX(t.MovementDate)
      FROM
        M_Transaction t
        INNER JOIN M_Locator sl ON (t.M_Locator_ID = sl.M_Locator_ID)
      WHERE
        t.M_Product_ID = p.M_Product_ID
        AND sl.M_Warehouse_ID = l.M_Warehouse_ID
        AND t.MovementType LIKE '%+'
    ) :: date
  ) AS DaysWithoutMovement
FROM
  M_Product p
  INNER JOIN M_Storage s ON (p.M_Product_ID = s.M_Product_ID)
  INNER JOIN M_Locator l ON (s.M_Locator_ID = l.M_Locator_ID)
  LEFT JOIN M_Product_Category pc ON (
    p.M_Product_Category_ID = pc.M_Product_Category_ID
  )
  LEFT JOIN M_Product_Group pg ON (p.M_Product_Group_ID = pg.M_Product_Group_ID)
  LEFT JOIN M_Product_Class pcl ON (p.M_Product_Class_ID = pcl.M_Product_Class_ID)
  LEFT JOIN M_Product_Classification pcls ON (
    p.M_Product_Classification_ID = pcls.M_Product_Classification_ID
  )
  LEFT JOIN M_Product_PO ppo ON (
    p.M_Product_ID = ppo.M_Product_ID
    AND ppo.IsCurrentVendor = 'Y'
  )
  LEFT JOIN M_ProductPrice pp ON (p.M_Product_ID = pp.M_Product_ID)
  LEFT JOIN M_PriceList_Version plv ON (
    pp.M_PriceList_Version_ID = plv.M_PriceList_Version_ID
    AND plv.isActive = 'Y'
  )
  LEFT JOIN M_PriceList pl ON (
    plv.M_PriceList_ID = pl.M_PriceList_ID
    AND pl.IsSoPriceList = 'Y'
  )
WHERE
  p.isStocked = 'Y'
  AND s.QtyOnhand <> 0
GROUP BY
  p.M_Product_ID,
  l.M_Warehouse_ID,
  ppo.PriceLastPO,
  ppo.PriceLastinv,
  pp.M_PriceList_Version_ID,
  pp.PriceList,
  pp.PriceStd,
  pp.PriceLimit;