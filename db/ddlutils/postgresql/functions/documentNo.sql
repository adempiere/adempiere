/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *Copyright (C) 2006-2008 Antonio Cañaveral, e-Evolution
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.of 
 * Return the Document for Dcocument Type
 */
create or replace FUNCTION documentNo
(
      p_PP_MRP_ID IN PP_MRP.PP_MRP_ID%TYPE
)
RETURNS PP_MRP.Value%TYPE
AS
$BODY$
DECLARE
	v_DocumentNo PP_MRP.Value%TYPE := '';
BEGIN
	-- If NO id return empty string
	IF p_PP_MRP_ID <= 0 THEN
		RETURN '';
	END IF;
	SELECT --ordertype, m_forecast_id, c_order_id, dd_order_id, pp_order_id, m_requisition_id,
	CASE
			WHEN trim(mrp.ordertype) = 'FTC' THEN (SELECT f.Name FROM M_Forecast f WHERE f.M_Forecast_ID=mrp.M_Forecast_ID)
			WHEN trim(mrp.ordertype) = 'POO' THEN (SELECT co.DocumentNo  FROM C_Order co WHERE co.C_Order_ID=mrp.C_Order_ID)
			WHEN trim(mrp.ordertype) = 'DOO' THEN (SELECT dd.DocumentNo  FROM DD_Order dd WHERE dd.DD_Order_ID=mrp.DD_Order_ID)
			WHEN trim(mrp.ordertype) = 'SOO' THEN (SELECT co.DocumentNo  FROM C_Order co WHERE co.C_Order_ID=mrp.C_Order_ID)
			WHEN trim(mrp.ordertype) = 'MOP' THEN (SELECT po.DocumentNo FROM PP_Order po WHERE po.PP_Order_ID=mrp.PP_Order_ID)
			WHEN trim(mrp.ordertype) = 'POR' THEN (SELECT r.DocumentNo  FROM M_Requisition r WHERE r.M_Requisition_ID=mrp.M_Requisition_ID)
			
	END INTO v_DocumentNo
	FROM pp_mrp mrp
	WHERE mrp.pp_mrp_id = p_PP_MRP_ID;
	RETURN v_DocumentNo;
END;	
$BODY$
  LANGUAGE 'plpgsql' ;
