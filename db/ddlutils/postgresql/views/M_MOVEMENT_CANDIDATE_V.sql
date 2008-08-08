DROP VIEW M_Movement_Candidate_v;
CREATE OR REPLACE VIEW M_Movement_Candidate_v AS
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.DD_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID, 
    o.POReference, o.Description, o.SalesRep_ID,
    l.M_Locator_ID, l.M_LocatorTo_ID
	--l.ConfirmedQty 
	--SUM((l.QtyOrdered-l.QtyDelivered)*l.PriceActual) AS TotalLines
FROM DD_Order o
INNER JOIN DD_OrderLine l ON (o.DD_Order_ID=l.DD_Order_ID)
--INNER JOIN M_Locator loc ON (loc.M_Locator_ID=l.M_Locator_ID) 
WHERE	(o.DocStatus = 'CO' AND o.IsDelivered='N')  --  Status must be CO - not CL/RE
	--	not Offers and open Walkin-Receipts
	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='DOO')
    --  Delivery Rule - not manual
    AND o.DeliveryRule<>'M'
    AND (l.M_Product_ID IS NULL OR EXISTS 
        (SELECT * FROM M_Product p 
        WHERE l.M_Product_ID=p.M_Product_ID AND p.IsExcludeAutoDelivery='N'))
	--	we need to ship
	AND	l.QtyOrdered <> l.QtyDelivered  AND ConfirmedQty > 0
	AND o.IsDropShip='N'
    AND (l.M_Product_ID IS NOT NULL OR l.C_Charge_ID IS NOT NULL)
    --  Not confirmed shipment
    AND NOT EXISTS (SELECT * FROM M_MovementLine iol 
        INNER JOIN M_Movement io ON (iol.M_Movement_ID=io.M_Movement_ID)
        WHERE iol.DD_OrderLine_ID=l.DD_OrderLine_ID AND io.DocStatus IN ('IP','WC'))
	--
GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.DD_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
    o.POReference, o.Description, o.SalesRep_ID, l.M_Locator_ID, l.M_LocatorTo_ID;
