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
 * Test..........: SELECT documentNo(50011);   => null
 *                 SELECT documentNo(null)     => EMPTY string
 *                 SELECT documentNo(50061);   => 8000
 *                 SELECT documentNo(1000000); => 50000
 *                 SELECT documentNo(1000005); => 800003
 *                 SELECT documentNo(1000011); => 50001
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop statement
DROP FUNCTION IF EXISTS documentNo;

DELIMITER $$
-- ## Create statement
CREATE FUNCTION documentNo (
  p_PP_MRP_ID DECIMAL(10, 0)
)
  RETURNS VARCHAR(80)
  DETERMINISTIC
BEGIN
  DECLARE v_DocumentNo VARCHAR(80) DEFAULT '';
  -- If NO id return empty string
  IF p_PP_MRP_ID <= 0 THEN
    RETURN '';
  END IF;
  SELECT -- ordertype, m_forecast_id, c_order_id, dd_order_id, pp_order_id, m_requisition_id,
    CASE trim(mrp.orderType) 
      WHEN 'FTC' THEN (SELECT f.Name FROM M_Forecast f WHERE f.M_Forecast_ID=mrp.M_Forecast_ID)
      WHEN 'POO' THEN (SELECT co.DocumentNo  FROM C_Order co WHERE co.C_Order_ID=mrp.C_Order_ID)
      WHEN 'DOO' THEN (SELECT dd.DocumentNo  FROM DD_Order dd WHERE dd.DD_Order_ID=mrp.DD_Order_ID)
      WHEN 'SOO' THEN (SELECT co.DocumentNo  FROM C_Order co WHERE co.C_Order_ID=mrp.C_Order_ID)
      WHEN 'MOP' THEN (SELECT po.DocumentNo FROM PP_Order po WHERE po.PP_Order_ID=mrp.PP_Order_ID)
      WHEN 'POR' THEN (SELECT r.DocumentNo  FROM M_Requisition r WHERE r.M_Requisition_ID=mrp.M_Requisition_ID)
    END 
  INTO v_DocumentNo
  FROM pp_mrp mrp
  WHERE mrp.pp_mrp_id = p_PP_MRP_ID;
  
  RETURN v_DocumentNo;  
END$$
DELIMITER ;