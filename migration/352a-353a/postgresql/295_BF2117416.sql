CREATE OR REPLACE VIEW C_INVOICE_CANDIDATE_V
(AD_CLIENT_ID, AD_ORG_ID, C_BPARTNER_ID, C_ORDER_ID, DOCUMENTNO, 
 DATEORDERED, C_DOCTYPE_ID, TOTALLINES)
AS 
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
	SUM((l.QtyOrdered-l.QtyInvoiced)*l.PriceActual) AS TotalLines
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
  INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
  LEFT OUTER JOIN C_InvoiceSchedule si ON (bp.C_InvoiceSchedule_ID=si.C_InvoiceSchedule_ID)
WHERE	o.DocStatus IN ('CO','CL','IP')	-- Standard Orders are IP
	--	not Offers and open Walkin-Receipts
	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR'))
	--	we need to invoice
	AND	l.QtyOrdered <> l.QtyInvoiced
	--
	AND (
		--	Immediate
		o.InvoiceRule='I'
		--	Order complete
		OR	(o.InvoiceRule='O' AND NOT EXISTS (SELECT 1 FROM C_OrderLine zz1
												WHERE zz1.C_Order_ID=o.C_Order_ID AND zz1.QtyOrdered<>zz1.QtyDelivered))
		--	Delivery
		OR	(o.InvoiceRule='D' AND l.QtyInvoiced<>l.QtyDelivered)
		--	Order Schedule, but none defined on Business Partner level
		OR	(o.InvoiceRule='S' AND bp.C_InvoiceSchedule_ID IS NULL)
		--	Schedule defined at BP
		OR	(o.InvoiceRule='S' AND bp.C_InvoiceSchedule_ID IS NOT NULL AND
				(
				--	Daily or none
					(si.InvoiceFrequency IS NULL OR si.InvoiceFrequency='D')
				--	Weekly
				OR	(si.InvoiceFrequency='W')
				--	Bi-Monthly
				OR	(si.InvoiceFrequency='T'
					AND ((TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff-1
						AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay-1)
					OR	(TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff+14
						AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay+14))
					)
				--	Monthly
				OR	(si.InvoiceFrequency='M'
					AND TRUNC(o.DateOrdered) <= firstOf(getdate(),'MM')+si.InvoiceDayCutoff-1	-- after cutoff
					AND TRUNC(getdate()) >= firstOf(o.DateOrdered,'MM')+si.InvoiceDay-1)		-- after invoice day
				)
			)
		)
GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID;
