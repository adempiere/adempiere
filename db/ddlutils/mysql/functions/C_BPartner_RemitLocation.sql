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
 * Description...: Return the first RemitTo C_Location_ID of a Business Partner
 * Test..........: SELECT bpartnerRemitLocation( 112 )$$  => 115
 *                 SELECT bpartnerRemitLocation( 113 )$$  => 125
 *                 SELECT bpartnerRemitLocation( 114 )$$  => 116
 *                 SELECT bpartnerRemitLocation( 117 )$$  => 119
 *                 SELECT bpartnerRemitLocation( 118 )$$  => 120
 *                 SELECT bpartnerRemitLocation( 119 )$$  => 124
 * 
 * +--------------+-------------------+---------------+
 * | value        | name              | c_bpartner_id |
 * +--------------+-------------------+---------------+
 * | Standard     | Standard          |           112 | 
 * | GardenAdmin  | GardenAdmin BP    |           113 | 
 * | TreeFarm     | Tree Farm Inc.    |           114 | 
 * | C&W          | C&W Construction  |           117 | 
 * | JoeBlock     | Joe Block         |           118 | 
 * | GardenUser   | GardenUser BP     |           119 | 
 * | SeedFarm     | Seed Farm Inc.    |           120 |
 * 
 *  
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS bpartnerRemitLocation;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION bpartnerRemitLocation (
  p_C_BPartner_ID DECIMAL(10, 0)
)
  RETURNS DECIMAL(10, 0)
  DETERMINISTIC
BEGIN
  DECLARE v_C_Location_ID DECIMAL(10, 0) DEFAULT NULL;
  
  -- Declare 'bpLoc_' variables to read in each record from the cursor
  DECLARE bpLoc_IsRemitTo CHAR(1);
  DECLARE bpLoc_C_Location_ID DECIMAL(10, 0);
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;
  
  -- Declare the cursor
  DECLARE cur_BPLoc CURSOR FOR
    SELECT bpLoc.IsRemitTo, bpLoc.C_Location_ID
    FROM C_BPartner_Location bpLoc
    WHERE bpLoc.C_BPartner_ID=p_C_BPartner_ID
    ORDER BY bpLoc.IsRemitTo DESC;
  
  -- Declare 'handlers' for exceptions
  DECLARE
    CONTINUE HANDLER FOR SQLSTATE '02000' SET done = TRUE;
  
  OPEN cur_BPLoc;
  myLoop: LOOP
    FETCH cur_BPLoc INTO bpLoc_IsRemitTo, bpLoc_C_Location_ID;
    
    IF done THEN
      CLOSE cur_BPLoc;
      LEAVE myLoop;
    END IF;
    
    IF (v_C_Location_ID IS NULL) THEN
      SET v_C_Location_ID = bpLoc_C_Location_ID;
    END IF;
  END LOOP myLoop;
  
  RETURN v_C_Location_ID;
END$$
DELIMITER ;