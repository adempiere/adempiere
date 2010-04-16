-- set M_MatchInv.ProcessedOn after C_Invoice.ProcessedOn for a correct recalculation of Average Invoice
UPDATE M_MatchInv
SET ProcessedOn = (SELECT ProcessedOn+0.000000000001
						FROM C_Invoice, C_InvoiceLine 
						WHERE C_InvoiceLine.C_InvoiceLine_ID=M_MatchInv.C_InvoiceLine_ID AND
							C_Invoice.C_Invoice_ID=C_InvoiceLine.C_Invoice_ID
)
WHERE ProcessedOn <= (SELECT ProcessedOn
						FROM C_Invoice, C_InvoiceLine 
						WHERE C_InvoiceLine.C_InvoiceLine_ID=M_MatchInv.C_InvoiceLine_ID AND
							C_Invoice.C_Invoice_ID=C_InvoiceLine.C_Invoice_ID
	);

-- set M_MatchPO.ProcessedOn after C_Invoice.ProcessedOn for a better recalculation of Standard
UPDATE M_MatchPO
SET ProcessedOn = (SELECT ProcessedOn+0.000000000001
						FROM C_Invoice, C_InvoiceLine 
						WHERE C_InvoiceLine.C_InvoiceLine_ID=M_MatchPO.C_InvoiceLine_ID AND
							C_Invoice.C_Invoice_ID=C_InvoiceLine.C_Invoice_ID
)
WHERE ProcessedOn <= (SELECT ProcessedOn
						FROM C_Invoice, C_InvoiceLine 
						WHERE C_InvoiceLine.C_InvoiceLine_ID=M_MatchPO.C_InvoiceLine_ID AND
							C_Invoice.C_Invoice_ID=C_InvoiceLine.C_Invoice_ID
	);
