--DROP VIEW RV_OrderRMA_CreateFrom;
CREATE OR REPLACE VIEW RV_OrderRMA_CreateFrom AS
--  From InOut fro RMA
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.M_InOutLine_ID AS RV_OrderRMA_CreateFrom_ID, l.Line,
((CASE WHEN l.MovementQty = 0 THEN 0 ELSE l.QtyEntered / l.MovementQty END) * (l.MovementQty - SUM(COALESCE(ol.QtyOrdered, 0)))) QtyEntered,
l.C_UOM_ID, (l.MovementQty - SUM(COALESCE(ol.QtyOrdered, 0))) MovementQty,
(CASE WHEN l.MovementQty = 0 THEN 0 ELSE l.QtyEntered / l.MovementQty END) Multiplier,
COALESCE(p.Name, c.Name) AS Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
-- Reference
io.C_Order_ID, 0 C_Invoice_ID, io.M_InOut_ID, 0 AS M_RMA_ID, io.MovementDate AS DateDoc, io.C_BPartner_ID, io.DocStatus
FROM M_InOut io
INNER JOIN M_InOutLine l ON (l.M_InOut_ID = io.M_InOut_ID)
LEFT JOIN M_Product p ON (l.M_Product_ID = p.M_Product_ID)
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID)
LEFT JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND io.C_BPartner_ID = po.C_BPartner_ID)
LEFT JOIN (SELECT ol.Ref_InOutLine_ID, ol.QtyOrdered 
					FROM C_Order o
					INNER JOIN C_OrderLine ol ON(ol.C_Order_ID = o.C_Order_ID)
					WHERE o.DocStatus NOT IN('VO', 'CL')
					AND ol.Ref_InOutLine_ID IS NOT NULL) ol ON(ol.Ref_InOutLine_ID = l.M_InOutLine_ID)
WHERE l.MovementQty <> 0
GROUP BY l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.M_InOutLine_ID, l.Line,
l.MovementQty, l.QtyEntered,
l.C_UOM_ID, p.Name, c.Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
io.C_Order_ID, io.M_InOut_ID, io.MovementDate, io.C_BPartner_ID, io.DocStatus
HAVING(l.MovementQty - SUM(COALESCE(ol.QtyOrdered, 0)) <> 0);