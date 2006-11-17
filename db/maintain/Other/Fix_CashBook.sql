-- Fix CashBook
-------------------------------
-- Alter trigger C_CashLine_Trg Disable

--  List Invoices
SELECT * FROM C_Invoice WHERE C_CashLine_ID IN
(SELECT C_CashLine_ID FROM C_Invoice GROUP BY C_CashLine_ID, C_Payment_ID HAVING Count(*)>1)
ORDER BY C_Order_ID;

-- List CashLines
SELECT * FROM C_CashLine WHERE C_CashLine_ID IN
(SELECT C_CashLine_ID FROM C_Invoice GROUP BY C_CashLine_ID, C_Payment_ID HAVING Count(*)>1)
ORDER BY C_CashLine_ID;

--  Check for Amount
SELECT * FROM C_CashLine WHERE ABS(Amount)=89.32 ORDER BY C_CashLine_ID;
SELECT * FROM C_Invoice WHERE ABS(GrandTotal)=158.22;
SELECT * FROM C_Order WHERE ABS(GrandTotal)=158.22;



--  Fix:
SELECT Amount FROM C_CashLine WHERE C_CashLine_ID= 1021225
;
UPDATE C_Order SET C_CashLine_ID=NULL WHERE C_CashLine_ID= 1016680
;
UPDATE C_Invoice SET C_CashLine_ID=NULL WHERE C_CashLine_ID= 1016680
;
DELETE C_CashLine WHERE C_CashLine_ID= 1021225
;
COMMIT
;


-- Alter Trigger C_CashLine_Trg Enable
