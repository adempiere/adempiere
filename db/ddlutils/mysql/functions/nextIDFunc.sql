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
 * Test..........: SELECT nextIdFunc(106, ..);         => 
 *                  SELECT ad_sequence_id from ad_sequence where name = 'Test' => 34
 *                 SELECT nextIdFunc((select ad_sequence_id from ad_sequence where name = 'Test'), 'Y'); => ERROR 1442 (HY000): Can't update table 'ad_sequence' in stored function/trigger because it is already used by statement which invoked this stored function/trigger.
 * 
 *                 SELECT nextIdFunc(34, 'Y') $$ => 50011
 *                 SELECT nextIdFunc(34, 'Y') $$ => 50012
 *                 
 *                 START TRANSACTION $$
 *                 SELECT nextIdFunc(34, 'Y') $$ => 50013
 *                 ROLLBACK $$
 *                 SELECT nextIdFunc(34, 'Y') $$ => 50013
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS nextIdFunc;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION nextIdFunc (
  p_AD_Sequence_ID   DECIMAL(10, 0), 
  p_System           CHAR(1)
)
  RETURNS DECIMAL(10, 0)
  NOT DETERMINISTIC
BEGIN
  DECLARE o_NextIdFunc DECIMAL(10, 0);
  CALL nextId(p_AD_Sequence_ID, p_System, o_NextIdFunc);
  RETURN o_NextIDFunc;
END$$
DELIMITER ;