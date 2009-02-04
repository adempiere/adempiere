-- Update M_Locator_ID NULL columns using first locator form InOut header warehouse
UPDATE M_InOutLine iol SET M_Locator_ID = (
  SELECT MIN(loc.M_Locator_ID)
  FROM M_InOut io
  INNER JOIN M_Warehouse wh ON (wh.M_Warehouse_ID=io.M_Warehouse_ID)
  INNER JOIN M_Locator loc ON (loc.M_Warehouse_ID=wh.M_Warehouse_ID)
  WHERE io.M_InOut_ID=iol.M_InOut_ID
)
WHERE iol.M_Locator_ID is null
;

-- 04.02.2009 14:17:53 EET
-- 
insert into t_alter_column values('m_inoutline','M_Locator_ID','NUMERIC(10)',null,null)
;

-- 04.02.2009 14:17:53 EET
-- 
insert into t_alter_column values('m_inoutline','M_Locator_ID',null,'NOT NULL',null)
;

