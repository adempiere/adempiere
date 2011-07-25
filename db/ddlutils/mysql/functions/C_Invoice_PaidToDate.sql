 /**********************************************************************
 * This file is part of ADempiere Business Suite                       *
 * http://www.adempiere.org                                            *
 *                                                                     *
 * Copyright (C) Trifon Trifonov.                                      *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
 *                                                                     *
 ***********************************************************************
 * 
 * Title.........: Calculate Paid/Allocated amount in Currency to specific Date
 * Description...: Add up total amount paid for for C_Invoice_ID.
 *                 Split Payments are ignored.
 *                 All allocation amounts are converted to invoice C_Currency_ID
 *                  round it to the nearest cent
 *                  and adjust for CreditMemos by using C_Invoice_v
 *                  and for Payments with the multiplierAP (-1, 1)
 * 
 * Test..........: SELECT C_Invoice_ID, IsPaid, IsSOTrx, GrandTotal, invoicePaidToDate(C_Invoice_ID, C_Currency_ID, MultiplierAP, now()) AS invoicePaidToDate FROM C_Invoice_v ORDER BY C_Invoice_ID;
 * 
 * +--------------+--------+---------+-----------------+-------------------+
 * | C_Invoice_ID | IsPaid | IsSOTrx | GrandTotal      | invoicePaidToDate |
 * +--------------+--------+---------+-----------------+-------------------+
 * |          100 | Y      | Y       |   50.3500000000 |     50.3500000000 | 
 * |          101 | Y      | Y       |  100.7000000000 |    100.7000000000 | 
 * |          102 | N      | N       |  200.0000000000 |      0.0000000000 | 
 * |          103 | N      | Y       |  161.1200000000 |      0.0000000000 | 
 * |          104 | N      | N       |  360.0000000000 |      0.0000000000 | 
 * |          105 | N      | N       | 2731.5000000000 |      0.0000000000 | 
 * |          106 | N      | N       | 3657.5000000000 |      0.0000000000 | 
 * |          109 | N      | Y       |  114.4200000000 |      0.0000000000 | 
 * |          109 | N      | Y       |  114.4300000000 |      0.0000000000 | 
 * +--------------+--------+---------+-----------------+-------------------+
 * 
 *                 UPDATE C_Invoice_v1 SET IsPaid = CASE WHEN C_Invoice_Paid(C_Invoice_ID,C_Currency_ID,MultiplierAP)=GrandTotal THEN 'Y' 
 *                   ELSE 'N' END 
 *                 WHERE C_Invoice_ID>1000000
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS invoicePaidToDate;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION invoicePaidToDate (
  p_C_Invoice_ID   DECIMAL(10, 0),
  p_C_Currency_ID  DECIMAL(10, 0),
  p_MultiplierAP   DECIMAL(20, 10),  -- DEFAULT 1
  p_DateAcct       DATE
)
  RETURNS DECIMAL(20, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_MultiplierAP  DECIMAL(20, 10);
  DECLARE v_PaymentAmt    DECIMAL(20, 10);

  -- Declare cursor variables
  DECLARE curAlloc_AD_Client_ID   DECIMAL(10, 0);
  DECLARE curAlloc_AD_Org_ID      DECIMAL(10, 0);
  DECLARE curAlloc_Amount         DECIMAL(20, 10);
  DECLARE curAlloc_DiscountAmt    DECIMAL(20, 10);
  DECLARE curAlloc_WriteOffAmt    DECIMAL(20, 10);
  DECLARE curAlloc_C_Currency_ID  DECIMAL(10, 0);
  DECLARE curAlloc_DateTrx        DATE;
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;
  
  -- Declare the cursor
  DECLARE curAlloc CURSOR FOR
    SELECT a.AD_Client_ID, a.AD_Org_ID, 
           al.Amount, al.DiscountAmt, al.WriteOffAmt, 
           a.C_Currency_ID, a.DateTrx
    FROM C_AllocationLine al
    INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
    WHERE al.C_Invoice_ID = p_C_Invoice_ID
     AND a.IsActive='Y'
     AND a.DateAcct <= p_DateAcct;
  
  -- Declare 'handlers' for exceptions
  DECLARE
    CONTINUE HANDLER FOR SQLSTATE '02000' SET done = TRUE;

  SET v_MultiplierAP = 1;
  SET v_PaymentAmt = 0;
  -- Default
  IF (p_MultiplierAP IS NOT NULL) THEN
    SET v_MultiplierAP = p_MultiplierAP;
  END IF;
  -- Calculate Allocated Amount
  OPEN curAlloc;
  myLoop: LOOP
    FETCH curAlloc INTO curAlloc_AD_Client_ID, curAlloc_AD_Org_ID, curAlloc_Amount, 
      curAlloc_DiscountAmt, curAlloc_WriteOffAmt, curAlloc_C_Currency_ID, curAlloc_DateTrx;
    
    IF done THEN
      CLOSE curAlloc;
      LEAVE myLoop;
    END IF;
    
    SET v_PaymentAmt = v_PaymentAmt + currencyConvert(curAlloc_Amount + curAlloc_DisCountAmt + curAlloc_WriteOffAmt,
                         curAlloc_C_Currency_ID, p_C_Currency_ID, curAlloc_DateTrx, null, curAlloc_AD_Client_ID, curAlloc_AD_Org_ID);
  END LOOP myLoop;
  --
  RETURN ROUND(COALESCE(v_PaymentAmt,0), 2) * v_MultiplierAP;
END$$
DELIMITER ;