--  Fix Payment Cash Line
--  [ 1866214 ] Adempiere need can void a Cash Journal
--  https://sourceforge.net/tracker/?func=detail&atid=879335&aid=1866214&group_id=176962 
--  pl/sql translation of FixPaymentCashLine

BEGIN
   FOR rc IN (SELECT cl.C_CashLine_ID, c.NAME, cl.amount,
                     cl.C_BankAccount_ID, cl.AD_Client_ID
                FROM C_CASHLINE cl INNER JOIN C_CASH c
                     ON (c.C_Cash_ID = cl.C_Cash_ID)
               WHERE cl.CashType = 'T' AND cl.C_Payment_ID IS NULL)
   LOOP
      FOR rp IN (SELECT c_payment_id
                   FROM C_PAYMENT p
                  WHERE p.DocumentNo = rc.NAME
                    AND R_PnRef = rc.NAME
                    AND PayAmt = -rc.amount
                    AND C_BankAccount_ID = rc.C_BankAccount_ID
                    AND AD_Client_ID = rc.AD_Client_ID
                    AND TrxType = 'X'
                    AND TenderType = 'X')
      LOOP
         UPDATE C_CASHLINE
            SET C_Payment_ID = rp.C_Payment_ID
          WHERE C_CASHLINE_ID = rc.C_CashLine_ID;
      END LOOP;
   END LOOP;
  COMMIT;
END;
/