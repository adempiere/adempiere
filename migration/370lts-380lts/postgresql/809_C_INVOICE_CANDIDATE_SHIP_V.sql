--DROP VIEW C_Invoice_Candidate_Ship_v;

CREATE OR REPLACE VIEW C_INVOICE_CANDIDATE_SHIP_V (
  AD_Client_ID, AD_Org_ID, C_BPartner_ID, M_InOut_ID, Description
, DocumentNo, DateOrdered, MovementDate, C_DocType_ID, QtyToInvoice)
AS 
SELECT
 inOut.AD_Client_ID, inOut.AD_Org_ID, inOut.C_BPartner_ID, inOut.M_InOut_ID, inOut.Description
,inOut.DocumentNo, inOut.DateOrdered, inOut.MovementDate, inOut.C_DocType_ID, SUM(l.MovementQty) AS QtyToInvoice
FROM M_InOut inOut
INNER JOIN M_InOutLine l ON (inOut.M_InOut_ID=l.M_InOut_ID AND l.IsInvoiced='N')
INNER JOIN C_BPartner bp ON (inOut.C_BPartner_ID=bp.C_BPartner_ID)
LEFT OUTER JOIN C_InvoiceSchedule si ON (bp.C_InvoiceSchedule_ID=si.C_InvoiceSchedule_ID)
WHERE
 -- Only Completed or Closed documents
    inOut.DocStatus IN ('CO','CL')
 -- Only Sales Stransactions
AND inOut.IsSoTrx='Y' 
--
--AND (
--	--	Immediate
--	o.InvoiceRule='I'
--	--	Complete Order
--	OR	(o.InvoiceRule='O' AND NOT EXISTS (SELECT 1 FROM C_OrderLine zz1 WHERE zz1.C_Order_ID=o.C_Order_ID AND zz1.QtyOrdered<>zz1.QtyDelivered))
--	--	Delivery
--	OR	(o.InvoiceRule='D' AND l.QtyInvoiced<>l.QtyDelivered)
--	--	Order Schedule, but none defined on Business Partner level
--	OR	(o.InvoiceRule='S' AND bp.C_InvoiceSchedule_ID IS NULL)
--	--	Schedule defined at BP
--	OR	(o.InvoiceRule='S' AND bp.C_InvoiceSchedule_ID IS NOT NULL AND
--			(
--			--	Daily or none
--				(si.InvoiceFrequency IS NULL OR si.InvoiceFrequency='D')
--			--	Weekly
--			OR	(si.InvoiceFrequency='W')
--			--	Bi-Monthly
--			OR	(si.InvoiceFrequency='T'
--				AND ((TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff-1 AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay-1)
--				OR	(TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff+14 AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay+14))
--				)
--			--	Monthly
--			OR	(si.InvoiceFrequency='M'
--				AND TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff-1	-- after cutoff
--				AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay-1)		-- after invoice day
--			)
--		)
--	)
GROUP BY inOut.AD_Client_ID, inOut.AD_Org_ID, inOut.C_BPartner_ID, inOut.M_InOut_ID, inOut.Description
 ,inOut.DocumentNo, inOut.DateOrdered, inOut.MovementDate, inOut.C_DocType_ID;
