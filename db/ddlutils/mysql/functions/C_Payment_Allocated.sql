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
 * Title.........: Calculate Allocated Payment Amount in Payment Currency
 * Description...: Returns 
 * Test..........: 
 *   SELECT paymentAllocated(C_Payment_ID, C_Currency_ID) AS PaymentAllocated, PayAmt, IsAllocated 
     FROM C_Payment_v 
     WHERE C_Payment_ID<1000000; =>  
+------------------+---------------+-------------+
| PaymentAllocated | PAYAMT        | ISALLOCATED |
+------------------+---------------+-------------+
|    98.5000000000 | 98.5000000000 | Y           | 
|    50.0000000000 | 50.0000000000 | Y           | 
+------------------+---------------+-------------+
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS paymentAllocated;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION paymentAllocated (
  p_C_Payment_ID   DECIMAL(10, 0),
  p_C_Currency_ID  DECIMAL(10, 0)
)
  RETURNS DECIMAL(20, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_AllocatedAmt  DECIMAL(20, 10) DEFAULT 0;
  DECLARE v_PayAmt        DECIMAL(20, 10);
  
  -- Declare variables used for cursor
  DECLARE curAlloc_AD_Client_ID   DECIMAL(10, 0);
  DECLARE curAlloc_AD_Org_ID      DECIMAL(10, 0);
  DECLARE curAlloc_Amount         DECIMAL(20, 10);
  DECLARE curAlloc_C_Currency_ID  DECIMAL(10, 0);
  DECLARE curAlloc_DateTrx        DATE;
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;
  
  -- Declare the cursor
  DECLARE curAlloc CURSOR FOR
    SELECT a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
    FROM C_AllocationLine al
    INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
    WHERE al.C_Payment_ID = p_C_Payment_ID
     AND a.IsActive='Y';
     -- AND al.C_Invoice_ID IS NOT NULL;
  
  -- Declare 'handlers' for exceptions
  DECLARE
    CONTINUE HANDLER FOR SQLSTATE '02000' SET done = TRUE;

  -- Charge - nothing available
  SELECT MAX(p.PayAmt) 
   INTO v_PayAmt
  FROM C_Payment p
  WHERE p.C_Payment_ID=p_C_Payment_ID 
   AND p.C_Charge_ID > 0;
  
  IF (v_PayAmt IS NOT NULL) THEN
    RETURN v_PayAmt;
  END IF;
  
  -- Calculate Allocated Amount
  OPEN curAlloc;
  myLoop: LOOP
    FETCH curAlloc INTO curAlloc_AD_Client_ID, curAlloc_AD_Org_ID, curAlloc_Amount, curAlloc_C_Currency_ID, curAlloc_DateTrx;
    
    IF done THEN
      CLOSE curAlloc;
      LEAVE myLoop;
    END IF;
    
    SET v_AllocatedAmt = v_AllocatedAmt + currencyConvert(curAlloc_Amount, curAlloc_C_Currency_ID, p_C_Currency_ID, curAlloc_DateTrx, null, curAlloc_AD_Client_ID, curAlloc_AD_Org_ID);
  END LOOP myLoop;
  
  -- Round to penny
  SET v_AllocatedAmt = ROUND(COALESCE(v_AllocatedAmt, 0), 2);
  RETURN v_AllocatedAmt;  
END$$
DELIMITER ;