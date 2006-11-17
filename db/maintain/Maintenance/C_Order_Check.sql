/**
 *	Check Orders
 */

 -- Complete Orders with lines with just a 1 in Qty ordered (no product, price)
-- Set Qty Ordered to 0
-- These lines should be zero anyway
UPDATE C_OrderLine ol SET QtyOrdered = 0
--SELECT * FROM C_OrderLine ol
WHERE M_Product_ID IS NULL 
AND PriceActual=0
AND QtyDelivered = 0
AND QtyOrdered = 1
AND QtyOrdered <> QtyInvoiced
AND QtyDelivered = QtyInvoiced
AND EXISTS (SELECT * FROM C_Order o WHERE o.C_Order_ID=ol.C_Order_ID AND o.DocStatus IN ('CO','CL'));
COMMIT;


--	Synchronize Business Partners of Lines with Header
UPDATE C_OrderLine ol
	SET C_BPartner_ID = 
		(SELECT C_BPartner_ID FROM C_Order o WHERE o.C_Order_ID=ol.C_Order_ID)
WHERE C_BPartner_ID <>
		(SELECT C_BPartner_ID FROM C_Order o WHERE o.C_Order_ID=ol.C_Order_ID);

UPDATE C_OrderLine ol
	SET C_BPartner_Location_ID = 
		(SELECT C_BPartner_Location_ID FROM C_Order o WHERE o.C_Order_ID=ol.C_Order_ID)
WHERE C_BPartner_ID <>
		(SELECT C_BPartner_Location_ID FROM C_Order o WHERE o.C_Order_ID=ol.C_Order_ID);

COMMIT;
