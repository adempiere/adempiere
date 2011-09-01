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
 * Description...: Replace:
 *                   YYYY -> %Y
 *                   MM   -> %m
 *                   DD   -> %d
 *                   HH24 -> %H
 *                   MI   -> %i
 *                   SS   -> %s
 * Test..........: SELECT to_date('2010-08-17', 'YYYY-MM-DD');                     => 2010-08-17 00:00:00
 *                 SELECT to_date('2010-08-17 13:01:35', 'YYYY-MM-DD HH24:MI:SS'); => 2010-08-17 13:01:35
 * 
 * ***
 *                 SELECT STR_TO_DATE('2010-08-17 13:01:35','%Y-%m-%d %H:%i:%s'); => 2010-08-17 13:01:35
 *                 SELECT STR_TO_DATE(null, '%Y-%m-%d %H:%i:%s');                 => NULL
 *                 SELECT STR_TO_DATE('2010-08-17 13:01:35', null);               => NULL
 *                 SELECT STR_TO_DATE('2010-08-17 13:01:35', '');                 => 0000-00-00
 * 
 *                 SELECT STR_TO_DATE('2010-08-17', '%Y-%m-%d')                   => 2010-08-17
 * 
 * ***
 *                 SELECT REPLACE('www.mysql.com', 'w', 'Ww');                    => 'WwWwWw.mysql.com'
 *                 select REPLACE('YYYY-MM-DD HH24:MI:SS', 'YYYY', '%Y')          => %Y-MM-DD HH24:MI:SS
 *                 select REPLACE('%Y-MM-DD HH24:MI:SS', 'MM',   '%m')            => %Y-%m-DD HH24:MI:SS
 *                 select REPLACE('%Y-%m-DD HH24:MI:SS', 'DD',   '%d')            => %Y-%m-%d HH24:MI:SS
 *                 select REPLACE('%Y-%m-%d HH24:MI:SS', 'HH24', '%H')            => %Y-%m-%d %H:MI:SS
 *                 select REPLACE('%Y-%m-%d %H:MI:SS', 'MI',   '%i')              => %Y-%m-%d %H:%i:SS
 *                 select REPLACE('%Y-%m-%d %H:%i:SS', 'SS',   '%s')              => %Y-%m-%d %H:%i:%s
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS to_date;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION to_date (
  p_StrDate VARCHAR(40),
  p_formatStr VARCHAR(40)
)
	RETURNS TIMESTAMP
 	DETERMINISTIC
BEGIN
  DECLARE new_formatStr VARCHAR(255);
  SET new_formatStr = p_formatStr;
  SET new_formatStr = REPLACE(new_formatStr, 'YYYY', '%Y');
  SET new_formatStr = REPLACE(new_formatStr, 'MM',   '%m');
  SET new_formatStr = REPLACE(new_formatStr, 'DD',   '%d');
  SET new_formatStr = REPLACE(new_formatStr, 'HH24', '%H');
  SET new_formatStr = REPLACE(new_formatStr, 'MI',   '%i');
  SET new_formatStr = REPLACE(new_formatStr, 'SS',   '%s');
  RETURN STR_TO_DATE(p_StrDate, new_formatStr);
END$$
DELIMITER ;