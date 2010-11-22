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
 * Test..........:  
 *   millennium    SELECT firstOf('2010-09-17 13:42:44', '');      => 2010-01-01 should be: 2001-01-01
 *   century       SELECT firstOf('2010-09-17 13:42:44', '');      => 2010-01-01 should be: 2001-01-01
 *   decade        SELECT firstOf('2010-09-17 13:42:44', '');      => 2010-01-01 should be: 2001-01-01
 *   year          SELECT firstOf('2010-09-17 13:42:44', 'IYYY');  => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'IY');    => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'I');     => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'SYYYY'); => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'YYYY');  => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'YEAR');  => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'SYEAR'); => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'YYY');   => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'YY');    => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'Y');     => 2010-01-01
 *   quarter       SELECT firstOf('2010-09-17 13:42:44', 'Q');     => 2010-07-01
 *   month         SELECT firstOf('2010-09-17 13:42:44', 'MONTH'); => 2010-09-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'MON');   => 2010-09-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'MM');    => 2010-09-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'RM');    => 2010-09-01 
 *   week          SELECT firstOf('2010-09-17 13:42:44', 'IW');    => 2010-09-13 - postgreSQL: 2010-09-13 - oracle: 13-SEP-10
 *                 SELECT firstOf('2010-09-17 13:42:44', 'W');     => 2010-09-13 - postgreSQL: 2010-09-13 - oracle: 15-SEP-10
 *   day           SELECT firstOf('2010-09-17 13:42:44', 'DDD');   => 2010-09-17
 *                 SELECT firstOf('2010-09-17 13:42:44', 'DD');    => 2010-09-17
 *                 SELECT firstOf('2010-09-17 13:42:44', 'J');     => 2010-09-17
 * 
 *                 SELECT firstOf('2010-09-17 13:42:44', 'DAY');   => 2010-09-12 - postgreSQL: 2010-09-12 - oracle: 12-SEP-10
 *                 SELECT firstOf('2010-09-17 13:42:44', 'DY');    => 2010-09-12 - postgreSQL: 2010-09-12 - oracle: 12-SEP-10
 *                 SELECT firstOf('2010-09-17 13:42:44', 'D');     => 2010-09-12 - postgreSQL: 2010-09-12 - oracle: 12-SEP-10
 *   hour          SELECT firstOf('2010-09-17 13:42:44', 'HH');    => 2010-09-17
 *                 SELECT firstOf('2010-09-17 13:42:44', 'HH12');  => 2010-09-17
 *                 SELECT firstOf('2010-09-17 13:42:44', 'HH24');  => 2010-09-17
 *   minute        SELECT firstOf('2010-09-17 13:42:44', 'MI');    => 2010-09-17
 *   second        SELECT firstOf('2010-09-17 13:42:44', ''); =>
 *   milliseconds  SELECT firstOf('2010-09-17 13:42:44', ''); =>
 *   microseconds  SELECT firstOf('2010-09-17 13:42:44', ''); =>
 * 
 * 
 * 
 * Test (PostgreSQL)...:
 *   millennium    SELECT firstOf('2010-09-17 13:42:44', '');      => 2001-01-01
 *   century       SELECT firstOf('2010-09-17 13:42:44', '');      => 2001-01-01
 *   decade        SELECT firstOf('2010-09-17 13:42:44', '');      => 2001-01-01
 *   year          SELECT firstOf('2010-09-17 13:42:44', 'IYYY');  => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'IY');    => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'I');     => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'SYYYY'); => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'YYYY');  => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'YEAR');  => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'SYEAR'); => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'YYY');   => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'YY');    => 2010-01-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'Y');     => 2010-01-01
 *   quarter       SELECT firstOf('2010-09-17 13:42:44', 'Q');     => 2010-07-01
 *   month         SELECT firstOf('2010-09-17 13:42:44', 'MONTH'); => 2010-09-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'MON');   => 2010-09-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'MM');    => 2010-09-01
 *                 SELECT firstOf('2010-09-17 13:42:44', 'RM');    => 2010-09-01 
 *   week          SELECT firstOf('2010-09-17 13:42:44', 'IW');    => 2010-09-13
 *                 SELECT firstOf('2010-09-17 13:42:44', 'W');     => 2010-09-13
 *   day           SELECT firstOf('2010-09-17 13:42:44', 'DDD');   => 2010-09-17
 *                 SELECT firstOf('2010-09-17 13:42:44', 'DD');    => 2010-09-17
 *                 SELECT firstOf('2010-09-17 13:42:44', 'J');     => 2010-09-17
 * 
 *                 SELECT firstOf('2010-09-17 13:42:44', 'DAY');   => 2010-09-12
 *                 SELECT firstOf('2010-09-17 13:42:44', 'DY');    => 2010-09-12
 *                 SELECT firstOf('2010-09-17 13:42:44', 'D');     => 2010-09-12
 *   hour          SELECT firstOf('2010-09-17 13:42:44', 'HH');    => 2010-09-17
 *                 SELECT firstOf('2010-09-17 13:42:44', 'HH12');  => 2010-09-17
 *                 SELECT firstOf('2010-09-17 13:42:44', 'HH24');  => 2010-09-17
 *   minute        SELECT firstOf('2010-09-17 13:42:44', 'MI');    => 2010-09-17
 *   second        SELECT firstOf('2010-09-17 13:42:44', ''); =>
 *   milliseconds  SELECT firstOf('2010-09-17 13:42:44', ''); =>
 *   microseconds  SELECT firstOf('2010-09-17 13:42:44', ''); =>
 * 
 * 
 * 
 * Test (Oracle)...: 
 *  Format string                      | Meaning
 --------------------------------------+------------------------------------------------------ 
 *                             CC, SSC | Century
 * SYYY, YYYY, YEAR, SYEAR, YYY, YY, Y | Year (rounds up to next year on July 1)
 *                 IYYY, IYY, IY, or I | Standard ISO year
 *                                   Q | Quarter (rounds up on the sixteenth day of the second month of the quarter) 
 *               MONTH, MON, MM, or RM | Month (rounds up on the sixteenth day, which is not necessarily the same as the middle of the month)
 ************************************************************************************************
 *                  Unit | Valid format parameters
 * ----------------------+--------------------------------------
 *                  Year | SYYYY, YYYY, YEAR, SYEAR, YYY, YY, Y
 *              ISO Year | IYYY, IY, I
 *               Quarter | Q
 *                 Month | MONTH, MON, MM, RM
 *                  Week | WW
 *                    IW | IW
 *                     W | W
 *                   Day | DDD, DD, J
 * Start day of the week | DAY, DY, D
 *                  Hour | HH, HH12, HH24
 *                Minute | MI
 * 
 * Examples: trunc(to_date('22-AUG-03'), 'YEAR')  => '01-JAN-03'
 *           trunc(to_date('22-AUG-03'), 'Q')     => '01-JUL-03'
 *           trunc(to_date('22-AUG-03'), 'MONTH') => '01-AUG-03'
 *           trunc(to_date('22-AUG-03'), 'DDD')   => '22-AUG-03'
 *           trunc(to_date('22-AUG-03'), 'DAY')   => '17-AUG-03'
 ****
 *    SELECT TO_DATE ('11-SEP-1994 9:36:44', 'DD-MON-YYYY HH24:MI:SS') AS toDate FROM dual;  => 11-SEP-94
 *    SELECT TO_DATE ('11-SEP-1994', 'DD-MON-YYYY') AS toDate FROM dual;                     => 11-SEP-94
 ************************************************************************************************
 * 
 *   millennium    SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'millennium') AS firstOf FROM dual;    => date format not recognized
 *   century       SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'century') AS firstOf FROM dual;       => date format not recognized
 *   decade        SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'decade') AS firstOf FROM dual;        => too many precision specifiers
 *   year          SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'IYYY') AS firstOf FROM dual;          => 04-JAN-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'IY') AS firstOf FROM dual;            => 04-JAN-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'I') AS firstOf FROM dual;             => 04-JAN-10
 * 
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'SYYYY') AS firstOf FROM dual;         => 01-JAN-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'SYEAR') AS firstOf FROM dual;         => 01-JAN-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'YEAR') AS firstOf FROM dual;          => 01-JAN-10 
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'YYYY') AS firstOf FROM dual;          => 01-JAN-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'YYY') AS firstOf FROM dual;           => 01-JAN-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'YY') AS firstOf FROM dual;            => 01-JAN-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'Y') AS firstOf FROM dual;             => 01-JAN-10 
 *   quarter       SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'Q') AS firstOf FROM dual;             => 01-JUL-10
 *   month         SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'MONTH') AS firstOf FROM dual;         => 01-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'MON') AS firstOf FROM dual;           => 01-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'MM') AS firstOf FROM dual;            => 01-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'RM') AS firstOf FROM dual;            => 01-SEP-10
 *   week          SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'IW') AS firstOf FROM dual;            => 13-SEP-10
 * 
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'W') AS firstOf FROM dual;             => 15-SEP-10
 *   day           SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'DDD') AS firstOf FROM dual;           => 17-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'DD') AS firstOf FROM dual;            => 17-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'J') AS firstOf FROM dual;             => 17-SEP-10
 * 
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'DAY') AS firstOf FROM dual;           => 12-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'DY') AS firstOf FROM dual;            => 12-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'D') AS firstOf FROM dual;             => 12-SEP-10
 *   hour          SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'HH') AS firstOf FROM dual;            => 17-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'HH12') AS firstOf FROM dual;          => 17-SEP-10
 *                 SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'HH24') AS firstOf FROM dual;          => 17-SEP-10
 *   minute        SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'MI') AS firstOf FROM dual;            => 10-SEP-10
 *   second        SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'second') AS firstOf FROM dual;        => date format not recognized
 *   milliseconds  SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'milliseconds') AS firstOf FROM dual;  => date format not recognized
 *   microseconds  SELECT firstOf(TO_DATE('17-SEP-2010', 'DD-MON-YYYY'), 'microseconds') AS firstOf FROM dual;  => date format not recognized
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop
Drop function IF EXISTS firstOf;

DELIMITER $$
-- ## Create
CREATE FUNCTION firstOf
(
  p_datetime  TIMESTAMP,
  p_datePart  VARCHAR(12)
)
  RETURNS DATE
  DETERMINISTIC
BEGIN
  IF p_datePart IN ('') THEN
    -- Oracle returns different result! Should be adjusted appropriately.
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:01:01 00:00:00') AS DATE);

  ELSEIF p_datePart IN ('IYYY','IY','I') THEN
    -- Oracle returns different result! Should be adjusted appropriately.
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:01:01 00:00:00') AS DATE);
  
  ELSEIF p_datePart IN ('SYYYY','SYEAR','YEAR','YYYY','YYY','YY','Y') THEN
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:01:01 00:00:00') AS DATE);
  
  ELSEIF p_datePart IN ('Q') THEN
    RETURN DATE_ADD(
             CAST(DATE_FORMAT(p_datetime, '%Y:01:01 00:00:00') AS DATE)
             , INTERVAL (EXTRACT(QUARTER FROM p_datetime) - 1) QUARTER
           );
  
  ELSEIF p_datePart IN ('MONTH','MON','MM','RM') THEN
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:%m:01 00:00:00') AS DATE);
  
  ELSEIF p_datePart IN ('W') THEN
    -- Oracle returns different result. Not sure why?
    RETURN DATE_SUB(CAST(p_datetime AS DATE), INTERVAL (DayOfWeek(p_datetime) - 2 + 7)%7 DAY);

  ELSEIF p_datePart IN ('IW') THEN
    RETURN DATE_SUB(CAST(p_datetime AS DATE), INTERVAL (DayOfWeek(p_datetime) - 2 + 7)%7 DAY);
  
  ELSEIF p_datePart IN ('DDD','DD','J') THEN
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:%m:%d 00:00:00') AS DATE);

  ELSEIF p_datePart IN ('DAY','DY','D') THEN
    -- Extract begin day of the week.
    RETURN DATE_SUB(CAST(p_datetime AS DATE), INTERVAL (DayOfWeek(p_datetime) - 1 + 7)%7 DAY);
  
  ELSEIF p_datePart IN ('HH','HH12','HH24') THEN
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:%m:%d %H:00:00') AS DATE);

  ELSEIF p_datePart IN ('MI') THEN
    RETURN CAST(DATE_FORMAT(p_datetime, '%Y:%m:%d %H:%i:00') AS DATE);

  ELSE
    RETURN CAST(p_datetime AS DATE);
  END IF;
END$$
DELIMITER ;