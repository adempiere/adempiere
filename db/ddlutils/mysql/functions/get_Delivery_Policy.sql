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
 * Title.........: get_delivery_policy
 * Description...: Returns Return delivery policy of warehouse
 * Test..........: SELECT get_delivery_policy(106);   => 
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS get_delivery_policy;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION get_delivery_policy (
  warehouse_id DECIMAL(10, 0)
)
  RETURNS CHAR(1)
  DETERMINISTIC
BEGIN
  DECLARE v_orgId      DECIMAL(10, 0);
  DECLARE v_clientId   DECIMAL(10, 0);
  DECLARE v_return     CHAR(1);
  
  SELECT ad_client_id, ad_org_id INTO v_clientId, v_orgId 
  FROM M_Warehouse 
  WHERE M_Warehouse_ID=warehouse_id;
  
  SELECT COALESCE(org.deliverypolicy, cl.deliverypolicy) INTO v_return
  FROM AD_ClientInfo cl
  JOIN AD_OrgInfo of ON (cl.AD_Client_ID=org.AD_Client_ID)
  WHERE cl.AD_Client_ID = v_clientId 
   AND org.AD_Org_ID = v_orgId;
   
  RETURN v_return;
END$$

DELIMITER ;