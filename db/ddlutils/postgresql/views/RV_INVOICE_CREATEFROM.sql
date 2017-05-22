--DROP VIEW RV_Invoice_CreateFrom;
CREATE OR REPLACE VIEW RV_Invoice_CreateFrom AS
--  From Order
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_OrderLine_ID AS RV_Invoice_CreateFrom_ID, l.Line,
((CASE WHEN l.QtyOrdered = 0 THEN 0 ELSE l.QtyEntered / l.QtyOrdered END) * (l.QtyOrdered - SUM(COALESCE(m.Qty, 0)))) QtyEntered,
l.C_UOM_ID, (l.QtyOrdered - SUM(COALESCE(m.Qty, 0))) MovementQty,
(CASE WHEN l.QtyOrdered = 0 THEN 0 ELSE l.QtyEntered / l.QtyOrdered END) Multiplier,
COALESCE(p.Name, c.Name) AS Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
-- Reference
o.C_Order_ID, 0 AS C_Invoice_ID, 0 AS M_InOut_ID, 0 AS M_RMA_ID, o.DateOrdered AS DateDoc, o.C_BPartner_ID, o.DocStatus, 'O' AS CreateFromType
FROM C_Order o
INNER JOIN C_OrderLine l ON(l.C_Order_ID = o.C_Order_ID)
LEFT JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND l.C_BPartner_ID = po.C_BPartner_ID)
LEFT JOIN M_MatchPO m ON (l.C_OrderLine_ID = m.C_OrderLine_ID AND m.C_InvoiceLine_ID IS NOT NULL)
LEFT JOIN M_Product p ON (l.M_Product_ID = p.M_Product_ID)
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID)
WHERE l.QtyOrdered <> 0
GROUP BY l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_OrderLine_ID, l.Line,
l.QtyOrdered, l.QtyEntered, l.C_UOM_ID, p.Name, c.Name, l.M_Product_ID,
l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
o.C_Order_ID, o.DateOrdered, o.C_BPartner_ID, o.DocStatus
HAVING(l.QtyOrdered - SUM(COALESCE(m.Qty, 0)) <> 0)

UNION ALL


--  From InOut
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.M_InOutLine_ID AS RV_Invoice_CreateFrom_ID, l.Line,
((CASE WHEN l.MovementQty = 0 THEN 0 ELSE l.QtyEntered / l.MovementQty END) * (l.MovementQty - SUM(COALESCE(m.Qty, 0)))) QtyEntered,
l.C_UOM_ID, (l.MovementQty - SUM(COALESCE(m.Qty, 0))) MovementQty,
(CASE WHEN l.MovementQty = 0 THEN 0 ELSE l.QtyEntered / l.MovementQty END) Multiplier,
COALESCE(p.Name, c.Name) AS Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
-- Reference
io.C_Order_ID, 0 C_Invoice_ID, io.M_InOut_ID, 0 AS M_RMA_ID, io.MovementDate AS DateDoc, io.C_BPartner_ID, io.DocStatus, 'R' AS CreateFromType
FROM M_InOut io
INNER JOIN M_InOutLine l ON (l.M_InOut_ID = io.M_InOut_ID)
LEFT JOIN M_Product p ON (l.M_Product_ID = p.M_Product_ID)
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID)
LEFT JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND io.C_BPartner_ID = po.C_BPartner_ID)
LEFT JOIN M_MatchInv m ON (l.M_InOutLine_ID = m.M_InOutLine_ID)
AND l.MovementQty <> 0
GROUP BY l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.M_InOutLine_ID, l.Line,
l.MovementQty, l.QtyEntered,
l.C_UOM_ID, p.Name, c.Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
io.C_Order_ID, io.M_InOut_ID, io.MovementDate, io.C_BPartner_ID, io.DocStatus
HAVING(l.MovementQty - SUM(COALESCE(m.Qty, 0)) <> 0)

UNION ALL

-- From RMA
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.M_RMALine_ID AS RV_Invoice_CreateFrom_ID, l.line,
(l.Qty - COALESCE(inlp.QtyInvoiced, 0)) QtyEntered,
COALESCE(iol.C_UOM_ID, 100) C_UOM_ID, (l.Qty - COALESCE(inlp.QtyInvoiced, 0)) MovementQty,
1 Multiplier,
COALESCE(p.Name, c.Name) AS Name, iol.M_Product_ID, iol.M_AttributeSetInstance_ID, NULL C_Charge_ID, l.Description, NULL VendorProductNo,
-- Reference
0 C_Order_ID, 0 C_Invoice_ID, 0 AS M_InOut_ID, r.M_RMA_ID, r.Created AS DateDoc, io.C_BPartner_ID, r.DocStatus, 'A' AS CreateFromType
FROM M_RMA r
INNER JOIN M_RMALine l ON(l.M_RMA_ID = r.M_RMA_ID)
INNER JOIN M_InOut io ON(io.M_InOut_ID = r.InOut_ID)
LEFT JOIN M_InOutLine iol ON(iol.M_InOutLine_ID = l.M_InOutLine_ID)
LEFT JOIN (SELECT inlr.M_RMALine_ID, SUM(inlr.QtyInvoiced) QtyInvoiced
		FROM C_Invoice inr
		INNER JOIN C_InvoiceLine inlr ON(inlr.C_Invoice_ID = inr.C_Invoice_ID)
		WHERE inr.DocStatus IN('CO', 'CL')
		GROUP BY inlr.M_RMALine_ID) inlp ON(inlp.M_RMALine_ID = l.M_RMALine_ID)
LEFT JOIN M_Product p ON p.M_Product_ID=iol.M_Product_ID
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID)
WHERE l.Qty <> 0
AND (l.M_InOutLine_ID IS NOT NULL OR l.C_Charge_ID IS NOT NULL)

UNION ALL

-- From Invoice
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_InvoiceLine_ID AS RV_Invoice_CreateFrom_ID, l.Line,
((CASE WHEN l.QtyInvoiced = 0 THEN 0 ELSE l.QtyEntered / l.QtyInvoiced END) * (l.QtyInvoiced)) QtyEntered,
l.C_UOM_ID, l.QtyInvoiced MovementQty,
(CASE WHEN l.QtyInvoiced = 0 THEN 0 ELSE l.QtyEntered / l.QtyInvoiced END) Multiplier,
COALESCE(p.Name, c.Name) AS Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
-- Reference
0 C_Order_ID, i.C_Invoice_ID, 0 AS M_InOut_ID, 0 AS M_RMA_ID, i.DateInvoiced AS DateDoc, i.C_BPartner_ID, i.DocStatus, 'I' AS CreateFromType
FROM C_Invoice i
INNER JOIN C_InvoiceLine l ON(l.C_Invoice_ID = i.C_Invoice_ID)
LEFT JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND i.C_BPartner_ID = po.C_BPartner_ID)
LEFT JOIN M_Product p ON (l.M_Product_ID = p.M_Product_ID)
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID);