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
 * Title.........: Get Due Days
 * Description...: Returns the days due (positive) or the days till due (negative)
 *                 Grace days are not considered!
 *	               If record is not found it assumes due immediately
 *
 * Test..........: SELECT paymentTermDueDays(100, '2000-12-01', '2000-12-01'); => -30
 *                 SELECT paymentTermDueDays(100, '2000-12-01', '2000-12-15'); => -16
 *                 SELECT paymentTermDueDays(100, '2000-12-01', '2000-12-29'); => -2
 *                 SELECT paymentTermDueDays(100, '2000-12-01', '2000-12-30'); => -1
 *                 SELECT paymentTermDueDays(100, '2000-12-01', '2000-12-31'); =>  0
 *                 SELECT paymentTermDueDays(100, '2000-12-01', '2001-01-01'); =>  1
 *                 SELECT paymentTermDueDays(100, '2000-12-01', '2001-01-31'); => 31
 * 
 *                 SELECT paymentTermDueDays(100, '2000-12-01', '2000-12-15'); => -16
 *                 SELECT paymenttermDueDays(100, now(), now())                => -30
 *                 
 * Test (Oracle).: SELECT paymentTermDueDays(100, '01-DEC-2000', '01-DEC-2000') FROM DUAL; => -30
 *                 SELECT paymentTermDueDays(100, '01-DEC-2000', '15-DEC-2000') FROM DUAL; => -16
 *                 SELECT paymentTermDueDays(100, '01-DEC-2000', '29-DEC-2000') FROM DUAL; => -2
 *                 SELECT paymentTermDueDays(100, '01-DEC-2000', '30-DEC-2000') FROM DUAL; => -1
 *                 SELECT paymentTermDueDays(100, '01-DEC-2000', '31-DEC-2000') FROM DUAL; =>  0
 *                 SELECT paymentTermDueDays(100, '01-DEC-2000', '01-JAN-2001') FROM DUAL; =>  1
 *                 SELECT paymentTermDueDays(100, '01-DEC-2000', '31-JAN-2001') FROM DUAL; => 31
 * +------------------+--------------------------------+------------+
 * | C_PaymentTerm_ID | Name                           | IsDueFixed |
 * +------------------+--------------------------------+------------+
 * |              100 | 30 Net                         | N          | 
 * |              105 | Immediate                      | N          | 
 * |              106 | 2%10 Net 30                    | N          | 
 * |              107 | 30 Days Net                    | N          | 
 * |              108 | 50% Immediate - 50% in 30 days | N          | 
 * +------------------+--------------------------------+------------+
 * 
 * Misc. MySQL Date function tests...: SELECT DATE_ADD('2010-01-01', INTERVAL 1 MONTH);  => 2010-02-01
 *                                     SELECT DATE_ADD('2010-01-01', INTERVAL 11 DAY);   => 2009-12-31
 *                                     SELECT EXTRACT(DAY FROM '2004-01-15 12:34:45');   =>  15
 *                                     SELECT EXTRACT(MONTH FROM '2004-02-15 12:34:45'); =>   2
 *                                     SELECT DATEDIFF('2007-12-31', '2007-12-30');      =>   1
 *                                     SELECT DATEDIFF('2010-11-30', '2010-12-30');      => -30 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS paymentTermDueDays;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION paymentTermDueDays (
  p_PaymentTerm_ID DECIMAL(10, 0),
  p_DocDate        DATE,
  p_PayDate        DATE
)
  RETURNS DECIMAL(10, 0)
  DETERMINISTIC
BEGIN
  DECLARE Days            DECIMAL(10, 0) DEFAULT 0;
  DECLARE DueDate         DATE;
  DECLARE calDueDate      DATE;
  DECLARE FixMonthOffset  DECIMAL(10, 0);
  DECLARE MaxDayCut       DECIMAL(10, 0);
  DECLARE MaxDay          DECIMAL(10, 0);
  DECLARE v_PayDate       DATE;
  --
  DECLARE FirstDayOfMonth    DATE;
  DECLARE NoDays             DECIMAL(10, 0);
  -- Used instead of Cursor
  DECLARE vp_IsDueFixed      CHAR(1);
  DECLARE vp_FixMonthDay     DECIMAL(10, 0);
  DECLARE vp_FixMonthOffset  DECIMAL(10, 0);
  DECLARE vp_FixMonthCutoff  DECIMAL(10, 0);
  DECLARE vp_NetDays         DECIMAL(10, 0);
  
  IF p_PaymentTerm_ID = 0 OR p_DocDate IS NULL THEN
    RETURN 0;
  END IF;

  SET v_PayDate = p_PayDate;
  IF v_PayDate IS NULL THEN
    SET v_PayDate = TRUNC(now(), NULL);
  END IF;

  SELECT IsDueFixed, FixMonthDay, FixMonthOffset, FixMonthCutoff, NetDays
   INTO vp_IsDueFixed, vp_FixMonthDay, vp_FixMonthOffset, vp_FixMonthCutoff, vp_NetDays
  FROM C_PaymentTerm
  WHERE C_PaymentTerm_ID = p_PaymentTerm_ID;

  -- Due 15th of following month
  IF (vp_IsDueFixed = 'Y') THEN
    SET FirstDayOfMonth = TRUNC(p_DocDate, 'MM');
    -- SET NoDays = extract (day from (TRUNC(p_DocDate, NULL) - FirstDayOfMonth));
    SET NoDays = DATEDIFF(p_DocDate, FirstDayOfMonth);
    SET DueDate = DATE_ADD(FirstDayOfMonth, INTERVAL (vp_FixMonthDay-1) DAY);  -- starting on 1st
    SET DueDate = DATE_ADD(DueDate, INTERVAL vp_FixMonthOffset MONTH);
    
    IF (NoDays > vp_FixMonthCutoff) THEN
      SET DueDate = DATE_ADD(DueDate, INTERVAL 1 MONTH);
    END IF;
    
    SET calDueDate = TRUNC(p_DocDate, NULL);
    --  MaxDayCut = extract(day from (cast(date_trunc('month', calDueDate) + '1 month'::interval as date) - 1) );
    SET MaxDayCut = EXTRACT(DAY FROM DATE_ADD( DATE_ADD(TRUNC(calDueDate, 'MM'), INTERVAL 1 MONTH), INTERVAL -1 DAY) );
    
    IF vp_FixMonthCutoff > MaxDayCut THEN
      -- calDueDate := cast(date_trunc('month', TRUNC(calDueDate)) + '1 month'::interval as date) - 1;
      SET calDueDate = DATE_ADD( DATE_ADD( TRUNC(calDueDate, 'MM'), INTERVAL 1 MONTH), INTERVAL -1 DAY);
    ELSE
      -- set day fixMonthCutoff on dueDate
      SET calDueDate = DATE_ADD(TRUNC(calDueDate, 'MM'), INTERVAL (vp_FixMonthCutoff-1) DAY);
    END IF;
    
    SET FixMonthOffset = vp_FixMonthOffset;
    IF p_DocDate > calDueDate THEN
      SET FixMonthOffset = FixMonthOffset + 1;
    END IF;
    
    SET calDueDate = DATE_ADD(calDueDate, INTERVAL FixMonthOffset MONTH);
    SET MaxDay = EXTRACT(DAY FROM (DATE_ADD( DATE_ADD(TRUNC(calDueDate, 'MM'), INTERVAL 1 MONTH), INTERVAL -1 DAY) ) );
    
    IF (vp_FixMonthDay > MaxDay) -- 32 -> 28
     OR (vp_FixMonthDay >= 30 AND MaxDay > vp_FixMonthDay) 
    THEN  -- 30 -> 31
      SET calDueDate = DATE_ADD(TRUNC(calDueDate, 'MM'), INTERVAL (MaxDay-1) DAY);
    ELSE
      SET calDueDate = DATE_ADD(TRUNC(calDueDate, 'MM'), INTERVAL (vp_FixMonthDay-1) DAY);
    END IF;
    SET DueDate = calDueDate; 
  ELSE
    -- Due Date is not Fixed
    SET DueDate = DATE_ADD(p_DocDate, INTERVAL vp_NetDays DAY);
  END IF;
  
  IF DueDate IS NULL THEN
    RETURN 0;
  END IF;
  
  -- SET Days = EXTRACT(day from (TRUNC(v_PayDate, NULL) - DueDate));
  SET Days = DATEDIFF(v_PayDate, DueDate);
  RETURN Days;
END$$
DELIMITER ;