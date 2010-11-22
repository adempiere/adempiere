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
 * Title.........: Addeds number of months to given date.
 * Description...: Returns 
 * Test..........: SELECT add_months('2010-01-01', 1); => 2010-02-01
 *                 SELECT add_months('2010-01-31', 1); => 2010-02-28
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
DROP FUNCTION IF EXISTS add_months $$

-- ## Create statement
CREATE FUNCTION add_months (
  p_DateTime DATETIME,
  p_Months DECIMAL(10, 0)
)
  RETURNS DATE
  DETERMINISTIC
BEGIN
  IF p_DateTime is NULL OR p_Months is NULL THEN
    RETURN null;
  END IF;
  
  RETURN DATE_ADD(p_DateTime, INTERVAL p_Months MONTH);
END$$
DELIMITER ;