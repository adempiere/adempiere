-- DROP VIEW RV_InOut_CreateFrom;
CREATE OR REPLACE VIEW RV_InOut_CreateFrom
(AD_Client_ID, AD_Org_ID, CreatedBy, Created, UpdatedBy,
Updated, IsActive, RV_InOut_CreateFrom_ID, Line, QtyEntered, C_UOM_ID, MovementQty, Multiplier,
M_Locator_ID, Name, M_Product_ID, M_AttributeSetInstance_ID, C_Charge_ID, Description,
VendorProductNo, C_Order_ID, C_Invoice_ID, M_RMA_ID, DateDoc, C_BPartner_ID, DocStatus, CreateFromType,
C_Activity_ID, C_Project_ID, C_Campaign_ID, User1_ID, User2_ID)
AS
--  From Order
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_OrderLine_ID AS RV_InOut_CreateFrom_ID, l.Line,
((CASE WHEN l.QtyOrdered = 0 THEN 0 ELSE l.QtyEntered / l.QtyOrdered END) * (l.QtyOrdered - SUM(COALESCE(m.Qty, 0)))) QtyEntered,
l.C_UOM_ID, (l.QtyOrdered - SUM(COALESCE(m.Qty, 0))) MovementQty,
(CASE WHEN l.QtyOrdered = 0 THEN 0 ELSE l.QtyEntered / l.QtyOrdered END) Multiplier, p.M_Locator_ID,
COALESCE(p.Name, c.Name) AS Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
-- Reference
o.C_Order_ID, 0 AS C_Invoice_ID, 0 AS M_RMA_ID, o.DateOrdered AS DateDoc, o.C_BPartner_ID, o.DocStatus, 'O' AS CreateFromType,
l.C_Activity_ID, l.C_Project_ID, l.C_Campaign_ID, l.User1_ID, l.User2_ID
FROM C_Order o
INNER JOIN C_OrderLine l ON(l.C_Order_ID = o.C_Order_ID)
LEFT JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND l.C_BPartner_ID = po.C_BPartner_ID)
LEFT JOIN M_MatchPO m ON (l.C_OrderLine_ID = m.C_OrderLine_ID AND m.M_InOutLine_ID IS NOT NULL)
LEFT JOIN M_Product p ON (l.M_Product_ID = p.M_Product_ID)
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID)
WHERE l.QtyOrdered <> 0
GROUP BY l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_OrderLine_ID, l.Line,
l.QtyOrdered, l.QtyEntered, l.C_UOM_ID, p.M_Locator_ID, p.Name, c.Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID,
l.Description, po.VendorProductNo, o.C_Order_ID, o.DateOrdered, o.C_BPartner_ID, o.DocStatus,
l.C_Activity_ID, l.C_Project_ID, l.C_Campaign_ID, l.User1_ID, l.User2_ID
HAVING(l.QtyOrdered - SUM(COALESCE(m.Qty, 0)) <> 0)

UNION ALL

--  From Invoice
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_InvoiceLine_ID AS RV_InOut_CreateFrom_ID, l.Line,
((CASE WHEN l.QtyInvoiced = 0 THEN 0 ELSE l.QtyEntered / l.QtyInvoiced END) * (l.QtyInvoiced - SUM(COALESCE(m.Qty, 0)))) QtyEntered,
l.C_UOM_ID, (l.QtyInvoiced - SUM(COALESCE(m.Qty, 0))) MovementQty,
(CASE WHEN l.QtyInvoiced = 0 THEN 0 ELSE l.QtyEntered / l.QtyInvoiced END) Multiplier, p.M_Locator_ID,
COALESCE(p.Name, c.Name) AS Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID, l.Description, po.VendorProductNo,
-- Reference
inv.C_Order_ID, inv.C_Invoice_ID, 0 AS M_RMA_ID, inv.DateInvoiced AS DateDoc, inv.C_BPartner_ID, inv.DocStatus, 'I' AS CreateFromType,
l.C_Activity_ID, l.C_Project_ID, l.C_Campaign_ID, l.User1_ID, l.User2_ID
FROM C_Invoice inv
INNER JOIN C_InvoiceLine l ON (l.C_Invoice_ID = inv.C_Invoice_ID)
LEFT JOIN M_Product p ON (l.M_Product_ID = p.M_Product_ID)
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID)
LEFT JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND inv.C_BPartner_ID = po.C_BPartner_ID)
LEFT JOIN M_MatchInv m ON (l.C_InvoiceLine_ID = m.C_InvoiceLine_ID)
AND l.QtyInvoiced <> 0
GROUP BY l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.C_InvoiceLine_ID, l.Line,
l.QtyInvoiced, l.QtyEntered, l.C_UOM_ID, p.M_Locator_ID, p.Name, c.Name, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_Charge_ID,
l.Description, po.VendorProductNo, inv.C_Order_ID, inv.C_Invoice_ID, inv.DateInvoiced, inv.C_BPartner_ID, inv.DocStatus,
l.C_Activity_ID, l.C_Project_ID, l.C_Campaign_ID, l.User1_ID, l.User2_ID
HAVING(l.QtyInvoiced - SUM(COALESCE(m.Qty, 0)) <> 0)

UNION ALL

-- From RMA
SELECT l.AD_Client_ID, l.AD_Org_ID, l.CreatedBy, l.Created, l.UpdatedBy, l.Updated, l.IsActive, l.M_RMALine_ID AS RV_InOut_CreateFrom_ID, l.line,
(l.Qty - COALESCE(iolp.MovementQty, 0)) QtyEntered,
COALESCE(iol.C_UOM_ID, 100) C_UOM_ID, (l.Qty - COALESCE(iolp.MovementQty, 0)) MovementQty,
1 Multiplier, p.M_Locator_ID,
COALESCE(p.Name, c.Name) AS Name, iol.M_Product_ID, iol.M_AttributeSetInstance_ID, NULL C_Charge_ID, l.Description, NULL VendorProductNo,
-- Reference
NULL C_Order_ID, NULL C_Invoice_ID, r.M_RMA_ID, r.Created AS DateDoc, io.C_BPartner_ID, r.DocStatus, 'A' AS CreateFromType,
0 C_Activity_ID, 0 C_Project_ID, 0 C_Campaign_ID, 0 User1_ID, 0 User2_ID
FROM M_RMA r
INNER JOIN M_RMALine l ON(l.M_RMA_ID = r.M_RMA_ID)
INNER JOIN M_InOut io ON(io.M_InOut_ID = r.InOut_ID)
LEFT JOIN M_InOutLine iol ON(iol.M_InOutLine_ID = l.M_InOutLine_ID)
LEFT JOIN (SELECT iolr.M_RMALine_ID, SUM(iolr.MovementQty) MovementQty
		FROM M_InOut ior
		INNER JOIN M_InOutLine iolr ON(iolr.M_InOut_ID = ior.M_InOut_ID)
		WHERE ior.DocStatus IN('CO', 'CL')
		GROUP BY iolr.M_RMALine_ID) iolp ON(iolp.M_RMALine_ID = l.M_RMALine_ID)
LEFT JOIN M_Product p ON p.M_Product_ID=iol.M_Product_ID
LEFT JOIN C_Charge c ON (l.C_Charge_ID = c.C_Charge_ID)
WHERE l.Qty <> 0
AND (l.M_InOutLine_ID IS NOT NULL OR l.C_Charge_ID IS NOT NULL);