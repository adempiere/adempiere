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
 * Title.........: 
 * Description...: Returns 
 * Test..........: SELECT nextBusinessDay(now(), 11); =>
 *                 SELECT nextBusinessDay('2002-07-04', 11); => 2002-07-05
 *                 SELECT nextBusinessDay('2002-07-05', 11); => 2002-07-05
 *                 SELECT nextBusinessDay('2002-07-06', 11); => 2002-07-08
 *                 SELECT nextBusinessDay('2002-07-07', 11); => 2002-07-08
 *                 SELECT nextBusinessDay('2002-07-08', 11); => 2002-07-08 
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS nextBusinessDay;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION nextBusinessDay (
  p_Date DATE,
  p_AD_Client_ID DECIMAL(10, 0)
)
  RETURNS DATE
  DETERMINISTIC
BEGIN
  DECLARE v_nextDate   DATE             DEFAULT trunc(p_Date, null);
  DECLARE v_offset     DECIMAL(20, 10)  DEFAULT 0;
  DECLARE v_Saturday   DECIMAL(10, 0)   DEFAULT DayOfWeek('2000-01-01');
  DECLARE v_Sunday     DECIMAL(10, 0);
  DECLARE v_isHoliday  BOOLEAN DEFAULT  TRUE;
  
  -- Declare 'curNbd_' variables to read in each record from the cursor
  DECLARE curNbd_Date1 DATE;
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;
  -- Declare the cursor
  DECLARE curNbd CURSOR FOR
    SELECT nbd.Date1
    FROM C_NonBusinessDay nbd
    WHERE nbd.AD_Client_ID=p_AD_Client_ID 
      AND nbd.IsActive ='Y'
      AND nbd.Date1 >= v_nextDate
    ORDER BY nbd.Date1;
  
  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = TRUE;

  -- SELECT IF(v_Saturday = 7, 1, v_Saturday + 1);
  SET v_Sunday = CASE v_Saturday 
                  WHEN 7 THEN 1
                  ELSE (v_Saturday + 1)
                 END;

  firstLoop: LOOP
    SELECT CASE DayOfWeek(v_nextDate)
            WHEN v_Saturday THEN 2
            WHEN v_Sunday THEN 1
            ELSE 0
           END
    INTO v_offset;
    
    SET v_nextDate = v_nextDate + v_offset;
    SET v_isHoliday = false;
    OPEN curNbd;
    innerLoop: LOOP
      FETCH curNbd INTO curNbd_Date1;
      
      IF done THEN
        CLOSE curNbd;
        LEAVE innerLoop;
      END IF;
      
      IF v_nextDate <> trunc(curNbd_Date1, null) THEN 
        LEAVE innerLoop;
      END IF;
      SET v_nextDate = v_nextDate + 1;
      SET v_isHoliday = true;
    END LOOP innerLoop;
    
    IF v_isHoliday=false THEN 
      LEAVE firstLoop;
    END IF;
  end LOOP firstLoop;
  --
  return v_nextDate;
END$$
DELIMITER ;