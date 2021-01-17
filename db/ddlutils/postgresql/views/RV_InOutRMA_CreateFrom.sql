--DROP VIEW RV_InOutRMA_CreateFrom;
CREATE OR REPLACE VIEW RV_InOutRMA_CreateFrom AS
--  From Order
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_OrderLine_ID AS RV_InOutRMA_CreateFrom_ID, l.Line,
((CASE WHEN l.QtyOrdered = 0 THEN 0 ELSE l.QtyEntered / l.QtyOrdered END) * (l.QtyOrdered - SUM(COALESCE(iol.MovementQty, 0)))) QtyEntered,
l.C_UOM_ID, (l.QtyOrdered - SUM(COALESCE(iol.MovementQty, 0))) MovementQty,
(CASE WHEN l.QtyOrdered = 0 THEN 0 ELSE l.QtyEntered / l.QtyOrdered END) Multiplier,
COALESCE(p.Name, c.Name) AS Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
-- Reference
o.C_Order_ID, 0 AS C_Invoice_ID, 0 AS M_InOut_ID, 0 AS M_RMA_ID, o.DateOrdered AS DateDoc, o.C_BPartner_ID, o.DocStatus
FROM C_Order o
INNER JOIN C_OrderLine l ON(l.C_Order_ID = o.C_Order_ID)
LEFT JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND l.C_BPartner_ID = po.C_BPartner_ID)
LEFT JOIN M_Product p ON (l.M_Product_ID = p.M_Product_ID)
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID)
LEFT JOIN (SELECT iol.C_OrderLine_ID, iol.MovementQty 
					FROM M_InOut io
					INNER JOIN M_InOutLine iol ON(iol.M_InOut_ID = io.M_InOut_ID)
					WHERE io.DocStatus NOT IN('VO', 'IN', 'IP', 'RE', 'CL')
					AND iol.C_OrderLine_ID IS NOT NULL) iol ON(iol.C_OrderLine_ID = l.C_OrderLine_ID)
WHERE l.QtyOrdered <> 0
GROUP BY l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_OrderLine_ID, l.Line,
l.QtyOrdered, l.QtyEntered, l.C_UOM_ID, p.Name, c.Name, l.M_Product_ID,
l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
o.C_Order_ID, o.DateOrdered, o.C_BPartner_ID, o.DocStatus
HAVING(l.QtyOrdered - SUM(COALESCE(iol.MovementQty, 0)) <> 0);