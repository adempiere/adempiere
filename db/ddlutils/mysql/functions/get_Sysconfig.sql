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
 * Test..........: SELECT get_Sysconfig('QTY_TO_SHOW_FOR_SERVICES', '99999', 11, 0); => 99999
 *                 SELECT get_Sysconfig('', '88888', 11, 0);                         => 88888
 *                 SELECT get_Sysconfig(null, '77777', 11, 0);                       => 77777
 *                 SELECT get_Sysconfig(null, '66666', 0, 0);                        => 66666
 *                 SELECT get_Sysconfig(null, null, 0, 0);                           => NULL
 *                 SELECT get_Sysconfig('REAL_TIME_POS', 'Y', 0, 0);                 => N
 *                 SELECT get_Sysconfig('REAL_TIME_POS', 'Y', 11, 0);                => N
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
Drop function IF EXISTS get_Sysconfig;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION get_Sysconfig (
  sysconfig_name VARCHAR(50),
  defaultvalue VARCHAR(255),
  client_id DECIMAL(10, 0),
  org_id DECIMAL(10, 0)
)
  RETURNS VARCHAR(255)
  DETERMINISTIC
BEGIN
  DECLARE v_value VARCHAR(255);
  -- Declare 'handlers' for exceptions
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET v_value = defaultvalue;
  
  SELECT Value INTO v_value
  FROM AD_SysConfig 
  WHERE Name=sysconfig_name 
   AND AD_Client_ID IN (0, client_id) 
   AND AD_Org_ID IN (0, org_id) 
   AND IsActive='Y' 
  ORDER BY AD_Client_ID DESC, AD_Org_ID DESC
  LIMIT 0, 1;
  
  RETURN v_value;
END$$
DELIMITER ;