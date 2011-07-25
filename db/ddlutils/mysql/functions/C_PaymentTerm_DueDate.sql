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
 * Title.........: Get Due Date
 * Description...: Returns the due date. Based on Postgres function.
 * Test-Oracle...: SELECT paymenttermDueDate(106, now() ) from Test;  => now()+30 days
 * Test-MySQL....: SELECT paymenttermDueDate(106, now() )$$  => now()+30 days
 *                 SELECT paymenttermDueDate(106, '2010-09-08' )$$
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
Drop function IF EXISTS paymentTermDueDate $$

-- ## Create statement
CREATE FUNCTION paymentTermDueDate
(
  PaymentTerm_ID   DECIMAL(10,0),
  DocDate          DATETIME
)
  RETURNS DATE
  DETERMINISTIC
BEGIN
 	DECLARE Days INT DEFAULT 0;
	DECLARE DueDate DATE DEFAULT TRUNC(DocDate, null);
	DECLARE FirstDay DATE;
	DECLARE NoDays INTEGER;
	--
	DECLARE cur_IsDueFixed char(1);
	DECLARE cur_FixMonthDay decimal(10,0);
	DECLARE cur_FixMonthOffset decimal(10,0);
	DECLARE cur_FixMonthCutoff decimal(10,0);
	DECLARE cur_NetDays decimal(10,0);
	
	-- Declare variables used just for cursor and loop control
	DECLARE no_more_rows BOOLEAN;
	DECLARE loop_cntr INT DEFAULT 0;
	DECLARE num_rows INT DEFAULT 0;
  
	-- Declare the cursor
	DECLARE paymentTerm_cur CURSOR FOR
		SELECT  IsDueFixed, FixMonthDay, FixMonthOffset, FixMonthCutoff, NetDays
		FROM    C_PaymentTerm
		WHERE   C_PaymentTerm_ID = PaymentTerm_ID;
	
	-- Declare 'handlers' for exceptions
	DECLARE CONTINUE HANDLER FOR NOT FOUND
		SET no_more_rows = TRUE;

	OPEN paymentTerm_cur;
	select FOUND_ROWS() into num_rows;
	
  the_loop: LOOP

    FETCH paymentTerm_cur
    INTO  cur_IsDueFixed, cur_FixMonthDay, cur_FixMonthOffset, cur_FixMonthCutoff, cur_NetDays;

    -- break out of the loop if
      -- 1) there were no records, or
      -- 2) we've processed them all
    IF no_more_rows THEN
        CLOSE paymentTerm_cur;
        LEAVE the_loop;
    END IF;

    IF (cur_IsDueFixed = 'Y') THEN
      BEGIN
		SET FirstDay = trunc(DocDate, 'MM');  -- Extract first day of the Month
		SET NoDays = DATEDIFF(DocDate, FirstDay);
		SET DueDate = FirstDay + (cur_FixMonthDay-1);  -- starting on 1st
		SET DueDate = date_add(DueDate, INTERVAL cur_FixMonthOffset month);
		IF (NoDays > cur_FixMonthCutoff) THEN
			SET DueDate = date_add(DueDate, INTERVAL 1 MONTH);
		END IF;
	  END;
	ELSE
		SET DueDate = date_add(trunc(DocDate, null), INTERVAL cur_NetDays DAY);
	END IF;
		
    -- count the number of times looped
    SET loop_cntr = loop_cntr + 1;
  END LOOP the_loop;
	
  RETURN (DueDate);
END$$
DELIMITER ;