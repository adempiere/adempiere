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
 * Title.........: Get Next ID - no Commit
 * Description...: Returns the next id of the sequence.
 * Test..........: 
 *                 set @a = 100 $$
 *                 CALL nextId(34, 'Y', @a) $$     => .....
 *                 SELECT @a $$                    => 50006
 * * 
 *                 START TRANSACTION $$
 *                 set @a = 100 $$
 *                 CALL nextId(34, 'Y', @a) $$     => .....
 *                 SELECT @a $$                    => 50010
 *                 ROLLBACK $$
 *                 CALL nextId(34, 'Y', @a) $$     => .....
 *                 SELECT @a $$                    => 50010
 * * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
Drop PROCEDURE IF EXISTS nextId;

DELIMITER $$
-- ## Create statement
CREATE PROCEDURE nextId(
  IN p_AD_Sequence_ID  DECIMAL(10, 0), 
  IN p_System          CHAR(1),
  OUT o_NextID         DECIMAL(10, 0)
)
BEGIN
  IF (p_System = 'Y') THEN
        SELECT CurrentNextSys
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --
        UPDATE AD_Sequence
          SET CurrentNextSys = CurrentNextSys + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
    ELSE
        SELECT CurrentNext
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --
        UPDATE AD_Sequence
          SET CurrentNext = CurrentNext + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
    END IF;
  
END$$
DELIMITER ;