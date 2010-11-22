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
 * Title.........: Calculate Payment Discount Amount
 * Description...:  
 *                 - Calculate discountable amount (i.e. with or without tax)
 *                 - Calculate and return payment discount
 
 * Test..........: SELECT invoiceDiscount(109,  '2003-11-1', 102); => 0.0000000000 
 *                 SELECT invoiceDiscount(109, '2003-11-15', 103); => 11.4400000000 -- Have changed Payment Term
 * 
 *                                Amt, Cur, PaymentTerm, DocDate, PayDate
 * SELECT paymentTermDiscount (228.85,   0,         103, '2003-11-1', '2003-11-2'); => 
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS invoiceDiscount;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION invoiceDiscount (
  p_C_Invoice_ID DECIMAL(10, 0),
  p_PayDate DATE,
  p_C_InvoicePaySchedule_ID DECIMAL(10, 0)
)
  RETURNS DECIMAL(20, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_Amount              DECIMAL(20, 10);
  DECLARE v_IsDiscountLineAmt   CHAR(1);
  DECLARE v_GrandTotal          DECIMAL(20, 10);
  DECLARE v_TotalLines          DECIMAL(20, 10);
  DECLARE v_C_PaymentTerm_ID    DECIMAL(10, 0);
  DECLARE v_DocDate             DATE;
  DECLARE v_PayDate             DATE DEFAULT now();
  DECLARE v_IsPayScheduleValid  CHAR(1);

  
  SELECT ci.IsDiscountLineAmt, i.GrandTotal, i.TotalLines, i.C_PaymentTerm_ID, i.DateInvoiced, i.IsPayScheduleValid
   INTO   v_IsDiscountLineAmt, v_GrandTotal, v_TotalLines, v_C_PaymentTerm_ID, v_DocDate, v_IsPayScheduleValid
  FROM AD_ClientInfo ci, C_Invoice i
  WHERE ci.AD_Client_ID=i.AD_Client_ID
   AND i.C_Invoice_ID=p_C_Invoice_ID;
  
  -- What Amount is the Discount Base?
  IF (v_IsDiscountLineAmt = 'Y') THEN
    SET v_Amount = v_TotalLines;
  ELSE
    SET v_Amount = v_GrandTotal;
  END IF;

  -- Anything to discount?
  IF (v_Amount = 0) THEN
    RETURN 0;
  END IF;
  IF (p_PayDate IS NOT NULL) THEN
    SET v_PayDate = p_PayDate;
  END IF;

  -- Valid Payment Schedule
  IF (v_IsPayScheduleValid='Y' AND p_C_InvoicePaySchedule_ID > 0) THEN
    SELECT COALESCE(MAX(ps.DiscountAmt), 0)
     INTO v_Amount
    FROM C_InvoicePaySchedule ps
    WHERE ps.C_InvoicePaySchedule_ID=p_C_InvoicePaySchedule_ID
     AND v_PayDate <= ps.DiscountDate;
    --
    RETURN v_Amount;
  END IF;

  -- Return discount amount	
  RETURN paymentTermDiscount (v_Amount, 0, v_C_PaymentTerm_ID, v_DocDate, p_PayDate);
END$$
DELIMITER ;