--DROP VIEW RV_M_SlowMovingInventory;

CREATE VIEW RV_M_SlowMovingInventory AS
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
  (
    SELECT
      COALESCE(SUM(ps.QtyOnhand), 0)
    FROM
      M_Product_Stock_v ps
    WHERE
      ps.M_Product_ID = p.M_Product_ID
  ) AS QtyOnhand,
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
      INNER JOIN M_Locator l ON (t.M_Locator_ID = l.M_Locator_ID)
    WHERE
      t.M_Product_ID = p.M_Product_ID
      AND t.MovementType = 'V+'
  ) AS LastDateReceipted,
  (
    SELECT
      MAX(t.MovementDate)
    FROM
      M_Transaction t
      INNER JOIN M_Locator l ON (t.M_Locator_ID = l.M_Locator_ID)
    WHERE
      t.M_Product_ID = p.M_Product_ID
      AND t.MovementType = 'C-'
  ) AS LastDateDelivered,
  getDate() :: date - COALESCE(
    (
      SELECT
        MAX(t.MovementDate)
      FROM
        M_Transaction t
        INNER JOIN M_Locator l ON (t.M_Locator_ID = l.M_Locator_ID)
      WHERE
        t.M_Product_ID = p.M_Product_ID
        AND t.MovementType = 'C-'),
        (
          SELECT
            MAX(t.MovementDate)
          FROM
            M_Transaction t
            INNER JOIN M_Locator l ON (t.M_Locator_ID = l.M_Locator_ID)
          WHERE
            t.M_Product_ID = p.M_Product_ID
            AND t.MovementType = 'V+'
        ) :: date
    ) AS DaysWithoutMovement
    FROM
      M_Product p
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
      AND (
        SELECT
          COALESCE(SUM(ps.QtyOnhand), 0)
        FROM
          M_Product_Stock_v ps
        WHERE
          ps.M_Product_ID = p.M_Product_ID
      ) > 0;