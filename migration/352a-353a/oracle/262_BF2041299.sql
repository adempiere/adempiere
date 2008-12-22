create or replace FUNCTION documentNo
(
      p_PP_MRP_ID IN PP_MRP.PP_MRP_ID%TYPE
)
RETURN PP_MRP.Value%TYPE
/************************************************************************
 * Function documentNofunc - PL/SQL equivalent to pljava SQLJ function
 * Author: Tony Snook (tspc)
 * Author: Teo Sarca, SC ARHIPAC SERVICE SRL
************************************************************************/
AS
	v_DocumentNo PP_MRP.Value%TYPE := '';
	
	CURSOR cur_mrp IS
		SELECT ordertype, m_forecast_id, c_order_id, dd_order_id, pp_order_id, m_requisition_id
		FROM pp_mrp
		WHERE pp_mrp_id = p_pp_mrp_id;   
BEGIN
	-- If NO id return empty string
	IF p_PP_MRP_ID <= 0 THEN
		RETURN '';
	END IF;
	
	FOR cur IN cur_mrp LOOP
		CASE
			WHEN cur.ordertype = 'FTC' THEN
				SELECT f.Name INTO v_DocumentNo FROM M_Forecast f WHERE f.M_Forecast_ID=cur.M_Forecast_ID;
			WHEN cur.ordertype = 'POO' THEN
				SELECT co.DocumentNo INTO v_DocumentNo FROM C_Order co WHERE co.C_Order_ID=cur.C_Order_ID;
			WHEN cur.ordertype = 'DOO' THEN
				SELECT do.DocumentNo INTO v_DocumentNo FROM DD_Order DO WHERE do.DD_Order_ID=cur.DD_Order_ID;
			WHEN cur.ordertype = 'SOO' THEN
				SELECT co.DocumentNo INTO v_DocumentNo FROM C_Order co WHERE co.C_Order_ID=cur.C_Order_ID;
			WHEN cur.ordertype = 'MOP' THEN
				SELECT po.DocumentNo INTO v_DocumentNo FROM PP_Order po WHERE po.PP_Order_ID=cur.PP_Order_ID;
			WHEN cur.ordertype = 'POR' THEN
				SELECT r.DocumentNo INTO v_DocumentNo FROM M_Requisition r WHERE r.M_Requisition_ID=cur.M_Requisition_ID;
			ELSE
				v_documentno := '';
		END CASE;
	END LOOP;
	
	RETURN v_DocumentNo;
END documentNo;
/
