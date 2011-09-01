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
 * Title.........: Calculate Discount
 * Description...: Calculate the allowable Discount Amount of the Payment Term
 *                                              Amt, Cur, PTr,      DocDate, PayDate
 *                                                              1999-DEC-10
 * Test..........: SELECT paymentTermDiscount(17777, 103, 106, '1999-12-10', null); => 0.0000000000
 *                 SELECT paymentTermDiscount(17777, 103, 106, '1999-12-10', '1999-12-11'); => 355.5400000000
 *                 SELECT paymentTermDiscount(17777, 103, 106, '1999-12-10', '1999-12-19'); => 355.5400000000
 *                 SELECT paymentTermDiscount(17777, 103, 106, '1999-12-10', '1999-12-20'); => 355.5400000000
 *                 SELECT paymentTermDiscount(17777, 103, 106, '1999-12-10', '1999-12-21'); => 0.0000000000
 *  
 * +------------------+--------------------------------+
 * | c_paymentterm_id | name                           |
 * +------------------+--------------------------------+
 * |              106 | 2%10 Net 30                    | 
 * |              107 | 30 Days Net                    | 
 * |              100 | 30 Net                         | 
 * |              108 | 50% Immediate - 50% in 30 days | 
 * |              105 | Immediate                      | 
 * +------------------+--------------------------------+
 * 
 * SELECT TRUNC('10-dec-1999' + 10 + 0, null);
 * SELECT DATE_ADD('1999-12-10', INTERVAL (10 + 0) DAY)
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS paymentTermDiscount;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION paymentTermDiscount (
  Amount           DECIMAL(20, 10),
  Currency_ID      DECIMAL(10, 0),
  PaymentTerm_ID   DECIMAL(10, 0),
  DocDate          DATE,
  PayDate          DATE
)
  RETURNS DECIMAL(20, 10)
  DETERMINISTIC
BEGIN
  DECLARE Discount DECIMAL(20, 10) DEFAULT 0;
  DECLARE Discount1Date DATE;
  DECLARE Discount2Date DATE;
  DECLARE Add1Date DECIMAL(20, 10) DEFAULT 0;
  DECLARE Add2Date DECIMAL(20, 10) DEFAULT 0;
  
  -- Variables for the Cursor
  DECLARE v_AD_Client_ID       DECIMAL(10, 0);
  DECLARE v_GraceDays          DECIMAL(10, 0);
  DECLARE v_DiscountDays       DECIMAL(10, 0);
  DECLARE v_DiscountDays2      DECIMAL(10, 0);
  DECLARE v_IsNextBusinessDay  CHAR(1);
  DECLARE v_Discount           DECIMAL(20, 10);
  DECLARE v_Discount2          DECIMAL(20, 10);
  
  -- No Data - No Discount
  IF (Amount IS NULL OR PaymentTerm_ID IS NULL OR DocDate IS NULL) THEN
    RETURN 0;
  END IF;

  SELECT pt.AD_Client_ID, pt.GraceDays, pt.DiscountDays, pt.DiscountDays2, pt.IsNextBusinessDay, pt.Discount, pt.Discount2
   INTO v_AD_Client_ID, v_GraceDays, v_DiscountDays, v_DiscountDays2, v_IsNextBusinessDay, v_Discount, v_Discount2
  FROM C_PaymentTerm pt
  WHERE pt.C_PaymentTerm_ID = PaymentTerm_ID;

  SET Discount1Date = TRUNC(DATE_ADD(DocDate, INTERVAL (v_DiscountDays + v_GraceDays) DAY), null);
  SET Discount2Date = TRUNC(DATE_ADD(DocDate, INTERVAL (v_DiscountDays2 + v_GraceDays) DAY), null);
  
  -- Next Business Day
  IF (v_IsNextBusinessDay='Y') THEN
    SET Discount1Date = nextBusinessDay(Discount1Date, v_AD_Client_ID);
    SET Discount2Date = nextBusinessDay(Discount2Date, v_AD_Client_ID);
  END IF;
  
  IF (Discount1Date >= TRUNC(PayDate, null)) THEN
    -- Discount 1
    SET Discount = Amount * v_Discount / 100;
  ELSEIF (Discount2Date >= TRUNC(PayDate, null)) THEN
    -- Discount 2
    SET Discount = Amount * v_Discount2 / 100;
  END IF;
  
  RETURN ROUND(COALESCE(Discount, 0), 2); -- fixed rounding
END$$
DELIMITER ;