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
-- ### Postgres results
SELECT date_trunc('year', TIMESTAMP '2001-02-16 20:38:45');     --> "2001-01-01 00:00:00"
SELECT date_trunc('quarter', TIMESTAMP '2001-02-16 20:38:45');  --> "2001-01-01 00:00:00"
 SELECT date_trunc('quarter', TIMESTAMP '2001-04-16 20:38:45'); --> "2001-04-01 00:00:00"
SELECT date_trunc('month', TIMESTAMP '2001-02-16 20:38:45');    --> "2001-02-01 00:00:00"
SELECT date_trunc('day', TIMESTAMP '2001-02-16 20:38:45');      --> "2001-02-16 00:00:00"
SELECT date_trunc('hour', TIMESTAMP '2001-02-16 20:38:45');     --> "2001-02-16 20:00:00"
SELECT date_trunc('minute', TIMESTAMP '2001-02-16 20:38:45');   --> "2001-02-16 20:38:00"
SELECT date_trunc('second', TIMESTAMP '2001-02-16 20:38:45');   --> "2001-02-16 20:38:45"


-- ### MySQL test results
  SELECT trunc('2004-07-15 12:34:45', null);  => 2004-07-15

SET @testDate = '2010-09-17 20:38:45';
SELECT @testDate;
 --'Q', 'Y', 'YEAR', 'MM', 'MONTH', 'DD', 'DY', 'W'
SELECT trunc(@testDate, 'Q')$$     --> 2010-07-01
SELECT trunc(@testDate, 'Y')$$     --> 2010-01-01
SELECT trunc(@testDate, 'YEAR')$$  --> 2010-01-01
SELECT trunc(@testDate, 'MM')$$    --> 2010-09-01
SELECT trunc(@testDate, 'MONTH')$$ --> 2010-09-01
SELECT trunc(@testDate, 'DD')$$    --> 2010-09-17
SELECT trunc(@testDate, 'DY')$$    --> 2010-09-17
SELECT trunc(@testDate, 'W')$$     --> 2010-09-13

select EXTRACT(YEAR FROM '2004-07-15 12:34:45');     --> 2004
select EXTRACT(QUARTER FROM '2004-07-15 12:34:45');  --> 3
 select EXTRACT(QUARTER FROM '2004-01-15 12:34:45'); --> 1
 select EXTRACT(QUARTER FROM '2004-02-15 12:34:45'); --> 1
 select EXTRACT(QUARTER FROM '2004-03-15 12:34:45'); --> 1
 select EXTRACT(QUARTER FROM '2004-04-15 12:34:45'); --> 2
 select EXTRACT(QUARTER FROM '2004-05-15 12:34:45'); --> 2
 select EXTRACT(QUARTER FROM '2004-06-15 12:34:45'); --> 2
 select EXTRACT(QUARTER FROM '2004-07-15 12:34:45'); --> 3
 select EXTRACT(QUARTER FROM '2004-08-15 12:34:45'); --> 3
 select EXTRACT(QUARTER FROM '2004-09-15 12:34:45'); --> 3
 select EXTRACT(QUARTER FROM '2004-10-15 12:34:45'); --> 4
 select EXTRACT(QUARTER FROM '2004-11-15 12:34:45'); --> 4
 select EXTRACT(QUARTER FROM '2004-12-15 12:34:45'); --> 4
select EXTRACT(MONTH FROM '2004-07-15 12:34:45');    --> 7

*/
-- ###

DELIMITER $$
 
-- ## Drop
Drop function IF EXISTS trunc $$

-- ## Create
CREATE FUNCTION trunc(
    p_datetime TIMESTAMP
  , format VARCHAR(5)
)
  RETURNS DATE
  DETERMINISTIC
BEGIN
  IF format IN ('Y', 'YEAR') THEN
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:01:01 00:00:00') AS DATE);
    
  ELSEIF format IN ('Q') THEN
    RETURN DATE_ADD(
             CAST(DATE_FORMAT(p_datetime, '%Y:01:01 00:00:00') AS DATE)
             , INTERVAL (EXTRACT(QUARTER FROM p_datetime) - 1) QUARTER
           );
    
  ELSEIF format IN ('MM', 'MONTH') THEN
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:%m:01 00:00:00') AS DATE);
    
  ELSEIF format IN ('W') THEN
    RETURN DATE_SUB(CAST(p_datetime AS DATE), INTERVAL (DayOfWeek(p_datetime) - 2 + 7)%7 DAY);

  ELSEIF format IN ('DD', 'DY') THEN
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:%m:%d 00:00:00') AS DATE);
    
  ELSE
    RETURN CAST(p_datetime AS DATE);
  END IF;
END$$
DELIMITER ;