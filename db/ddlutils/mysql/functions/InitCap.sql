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
 * Title.........: initCap
 * Description...: Returns all words with capitalized first letter
 * Test..........: SELECT initCap('trifon trifonov'); => Trifon Trifonov
 *                 SELECT initCap('trifon');          => Trifon
 *
 *                 SELECT SUBSTRING_INDEX('www domain com', ' ', 1); => www
 *                 SELECT SUBSTRING_INDEX('www domain com', ' ', 2); => www domain
 *  
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
DROP FUNCTION IF EXISTS initCap $$

-- ## Create statement
CREATE FUNCTION initCap (
  p_sentence VARCHAR(255)
)
  RETURNS VARCHAR(255)
  DETERMINISTIC
BEGIN
  DECLARE result VARCHAR(255) DEFAULT '';
  DECLARE left_side VARCHAR(255);
  
  WHILE p_sentence REGEXP ' ' DO
    SET left_side = SUBSTRING_INDEX(p_sentence, ' ', 1); 
    SET p_sentence = SUBSTRING(p_sentence, LOCATE(' ', p_sentence)+1);  -- p_sentence = right side 
    SET result = CONCAT(result, ' ', CONCAT(UPPER(SUBSTRING(left_side,1,1)), LOWER(SUBSTRING(left_side,2))));
  END WHILE;
  
  SET result = LTRIM(CONCAT(result, ' ', CONCAT(UPPER(SUBSTRING(p_sentence,1,1)), LOWER(SUBSTRING(p_sentence,2)))));
  RETURN result;
END$$
DELIMITER ;