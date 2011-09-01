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
 * Test..........: INSERT INTO AD_Sequence_No VALUES (34, '2010', 11, 0, 'Y', '2010-09-09', 100, '2010-09-09', 100, 1000000);
 *                 SELECT nextIDByYear(34, 1, '2010'); => 1000000
 *                 SELECT nextIDByYear(34, 1, '2010'); => 1000001
 * 
 * Table: AD_Sequence
 * AD_Sequence_ID | Name
 *+---------------+------
 *             34 | Test
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS nextIDByYear;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION nextIDByYear (
  p_AD_Sequence_ID  DECIMAL(10, 0),
  p_IncrementNo     DECIMAL(10, 0),
  p_CalendarYear    VARCHAR(4)
)
  RETURNS DECIMAL(10, 0)
  DETERMINISTIC
BEGIN
  DECLARE o_NextID DECIMAL(10, 0);  

  SELECT CurrentNext
   INTO o_NextID
  FROM AD_Sequence_No
  WHERE AD_Sequence_ID=p_AD_Sequence_ID 
   AND CalendarYear = p_CalendarYear 
  FOR UPDATE;  -- OF AD_Sequence_No; Not supported by MySQL.
  
  --
  UPDATE AD_Sequence_No
   SET CurrentNext = CurrentNext + p_IncrementNo
  WHERE AD_Sequence_ID=p_AD_Sequence_ID
   AND CalendarYear = p_CalendarYear;
   
  RETURN o_NextID;
END$$
DELIMITER ;