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
 * Title.........: Calculate Open Item Amount in Invoice Currency
 * Description...: Add up total amount open for C_Invoice_ID if no split payment.
 *                 Grand Total minus Sum of Allocations in Invoice Currency
 *
 *                 For Split Payments:
 *                  Allocate Payments starting from first schedule.
 *                  Cannot be used for IsPaid as mutating
 * 
 * Test..........: SELECT invoiceOpenToDate(109, null, now());  => 228.8500000000 - converted to default client currency
 *                 SELECT invoiceOpenToDate(108, null, now());  => 0.0000000000
 *                 SELECT invoiceOpenToDate(107, null, now());  => 0.0000000000
 *                 SELECT invoiceOpenToDate(106, null, now());  => 3657.5000000000
 *                 SELECT invoiceOpenToDate(105, null, now());  => 2731.5000000000
 *                 SELECT invoiceOpenToDate(104, null, now());  => 360.0000000000
 *                 SELECT invoiceOpenToDate(103, null, now());  => 161.1200000000
 *                 SELECT invoiceOpenToDate(102, null, now());  => 200.0000000000
 *                 SELECT invoiceOpenToDate(101, null, now());  => 0.0000000000
 *                 SELECT invoiceOpenToDate(100, null, now());  => 0.0000000000

 *                 SELECT invoiceOpenToDate(109,  11, now());   => 228.8500000000 - converted to default client currency
 *                 SELECT invoiceOpenToDate(109, 102, now());   => 114.4300000000 - 
 *                 SELECT invoiceOpenToDate(109, 103, now());   => 114.4200000000 - 
 * 
 *                 SELECT invoiceOpenToDate(110, null, now());  => 0.0000000000
 * 
 *                 SELECT * FROM RV_OpenItem WHERE C_Invoice_ID=109;
 *                 SELECT C_InvoicePaySchedule_ID, DueAmt FROM C_InvoicePaySchedule WHERE C_Invoice_ID=109 ORDER BY DueDate;
 *
 * Test-PostgreSQL:  
 *                  SELECT invoiceOpenToDate(109, null, now());  => 228.85
 *                   SELECT invoiceOpenToDate(109, null, null);  => 0.00
 *                  SELECT invoiceOpenToDate(108, null, now());  => 0.00
 *                  SELECT invoiceOpenToDate(107, null, now());  => 0.00
 *                  SELECT invoiceOpenToDate(106, null, now());  => 3657.50
 *                  SELECT invoiceOpenToDate(105, null, now());  => 2731.50
 *                  SELECT invoiceOpenToDate(104, null, now());  => 360.00
 *                  SELECT invoiceOpenToDate(103, null, now());  => 161.12
 *                  SELECT invoiceOpenToDate(102, null, now());  => 200.00
 *                  SELECT invoiceOpenToDate(101, null, now());  => 0.00
 *                  SELECT invoiceOpenToDate(100, null, now());  => 0.00
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
DROP FUNCTION IF EXISTS invoiceOpenToDate $$

-- ## Create statement
CREATE FUNCTION invoiceOpenToDate (
  p_C_Invoice_ID	          DECIMAL(10, 0),
  p_C_InvoicePaySchedule_ID   DECIMAL(10, 0),
  p_DateAcct                  DATETIME
)
  RETURNS DECIMAL(20, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_Currency_ID    DECIMAL(10, 0);
  DECLARE v_TotalOpenAmt   DECIMAL(20, 10) DEFAULT 0;
  DECLARE v_PaidAmt  	   DECIMAL(20, 10) DEFAULT 0;
  DECLARE v_Remaining      DECIMAL(20, 10) DEFAULT 0;
  DECLARE v_MultiplierAP   DECIMAL(20, 10) DEFAULT 0;
  DECLARE v_MultiplierCM   DECIMAL(20, 10) DEFAULT 0;
  DECLARE v_Temp           DECIMAL(20, 10) DEFAULT 0;
  DECLARE v_Precision      DECIMAL(20, 10) DEFAULT 0;
  DECLARE v_Min            DECIMAL(20, 10) DEFAULT 0;
  
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;
  
  -- Declare 'curAlloc_' variables to read in each record from the cursor
  DECLARE curAlloc_AD_Client_ID DECIMAL(10, 0);
  DECLARE curAlloc_AD_Org_ID DECIMAL(10, 0);
  DECLARE curAlloc_Amount DECIMAL(20, 10);
  DECLARE curAlloc_DiscountAmt DECIMAL(20, 10);
  DECLARE curAlloc_WriteOffAmt DECIMAL(20, 10);
  DECLARE curAlloc_C_Currency_ID DECIMAL(10, 0);
  DECLARE curAlloc_DateTrx DATE;
  -- Declare 'curPaySchedule_' variables to read in each record from the cursor
  DECLARE curPaySchedule_C_InvoicePaySchedule_ID DECIMAL(10, 0);
  DECLARE curPaySchedule_DueAmt DECIMAL(20, 10);
  -- Declare "curAlloc" cursor
  DECLARE curAlloc CURSOR FOR 
    SELECT a.AD_Client_ID, a.AD_Org_ID,
           al.Amount, al.DiscountAmt, al.WriteOffAmt,
           a.C_Currency_ID, a.DateTrx
    FROM C_AllocationLine al
    INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
    WHERE al.C_Invoice_ID = p_C_Invoice_ID
     AND a.DateAcct <= p_DateAcct
     AND a.IsActive='Y';
  
  -- Declare "curPaySchedule" cursor
  DECLARE curPaySchedule CURSOR FOR
    SELECT C_InvoicePaySchedule_ID, DueAmt
    FROM C_InvoicePaySchedule
    WHERE C_Invoice_ID = p_C_Invoice_ID
     AND IsValid='Y'
    ORDER BY DueDate;
  
  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = TRUE;
        
  -- Get Currency
    SELECT MAX(C_Currency_ID), SUM(GrandTotal), MAX(MultiplierAP), MAX(Multiplier)
      INTO v_Currency_ID, v_TotalOpenAmt, v_MultiplierAP, v_MultiplierCM
    FROM C_Invoice_v		-- corrected for CM / Split Payment
    WHERE C_Invoice_ID = p_C_Invoice_ID
     AND DateAcct <= p_DateAcct;
  
  -- Calculate Allocated Amount 
  OPEN curAlloc;
  myLoop: LOOP
    -- curAlloc_AD_Client_ID, 
    FETCH curAlloc INTO curAlloc_AD_Client_ID, curAlloc_AD_Org_ID
       , curAlloc_Amount, curAlloc_DiscountAmt, curAlloc_WriteOffAmt
       , curAlloc_C_Currency_ID, curAlloc_DateTrx;

    IF done THEN
        SET done = false;
        CLOSE curAlloc;
        LEAVE myLoop;
    END IF;

    SET v_Temp = curAlloc_Amount + curAlloc_DiscountAmt + curAlloc_WriteOffAmt;
    SET v_PaidAmt = v_PaidAmt
     -- Allocation
       + currencyConvert(v_Temp * v_MultiplierAP, curAlloc_C_Currency_ID, v_Currency_ID
          , curAlloc_DateTrx, null, curAlloc_AD_Client_ID, curAlloc_AD_Org_ID);
  END LOOP myLoop;
    
  -- Do we have a Payment Schedule?
  IF (p_C_InvoicePaySchedule_ID > 0) THEN -- if not valid = lists invoice amount
      SET v_Remaining = v_PaidAmt;
      
      OPEN curPaySchedule;
      myLoop2: LOOP
        FETCH curPaySchedule INTO curPaySchedule_C_InvoicePaySchedule_ID, curPaySchedule_DueAmt;

        IF done THEN
            CLOSE curPaySchedule;
            LEAVE myLoop2;
        END IF;

        IF (curPaySchedule_C_InvoicePaySchedule_ID = p_C_InvoicePaySchedule_ID) THEN
            SET v_TotalOpenAmt = (curPaySchedule_DueAmt*v_MultiplierCM) - v_Remaining;
            IF (curPaySchedule_DueAmt - v_Remaining < 0) THEN
                SET v_TotalOpenAmt = 0;
            END IF;
        ELSE -- calculate amount, which can be allocated to next schedule
            SET v_Remaining = v_Remaining - curPaySchedule_DueAmt;
            IF (v_Remaining < 0) THEN
                SET v_Remaining = 0;
            END IF;
        END IF;
      END LOOP myLoop2;
  ELSE
      SET v_TotalOpenAmt = v_TotalOpenAmt - v_PaidAmt;
  END IF;

  -- Ignore Rounding
  IF (v_TotalOpenAmt BETWEEN -0.00999 AND 0.00999) THEN
    SET v_TotalOpenAmt = 0;
  END IF;

  -- Round to penny
  SET v_TotalOpenAmt = ROUND(COALESCE(v_TotalOpenAmt, 0), 2);
  RETURN v_TotalOpenAmt;
END$$
DELIMITER ;