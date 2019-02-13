CREATE OR REPLACE FUNCTION maxpaydate(p_c_invoice_id IN NUMBER)
RETURN DATE
AS
          o_MaxPayDate  DATE;
BEGIN
	SELECT maxpaydatetrx INTO o_MaxPayDate
	FROM C_Invoice i  
	LEFT JOIN (  SELECT max(p.datetrx) as maxpaydatetrx, al2.c_invoice_ID          
           FROM C_AllocationLine al2               
           INNER JOIN C_AllocationHdr ah on (al2.c_allocationhdr_id=ah.c_allocationhdr_id)  			 
           INNER JOIN c_Payment p on al2.c_Payment_ID = p.c_Payment_ID                
           WHERE al2.C_Charge_ID IS NULL AND ah.docstatus<>'RE'            
           GROUP BY al2.C_Invoice_ID) al1 on (i.C_Invoice_ID = al1.C_Invoice_ID)  
	WHERE i.C_Invoice_ID=p_C_Invoice_ID;

    RETURN o_MaxPayDate;
END maxpaydate;