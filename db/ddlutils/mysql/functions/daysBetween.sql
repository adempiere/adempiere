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
 * Test..........: select daysBetween('2010-10-10 00:00:00', '2010-10-20 00:00:00');  --> -10
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop
Drop function IF EXISTS daysBetween $$

-- ## Create
CREATE FUNCTION daysBetween(
	p_date1     DATE,
    p_date2     DATE
)
	RETURNS INT
 	DETERMINISTIC
BEGIN
    RETURN DATEDIFF(p_date1, p_date2);
END$$
DELIMITER ;