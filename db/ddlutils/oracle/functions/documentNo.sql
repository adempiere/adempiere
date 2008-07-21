create or replace FUNCTION documentNo
    (
      p_PP_MRP_ID IN pp_mrp.pp_mrp_id%TYPE DEFAULT 0)
    RETURN pp_mrp.value%TYPE
    /*************************************************************************
    * Function documentNofunc - PL/SQL equivalent to pljava SQLJ function
    * Author: Tony Snook (tspc)
    ************************************************************************/
  AS
    v_DocumentNo pp_mrp.value%TYPE := '';

    CURSOR cur
    IS
       SELECT ordertype, m_forecast_id, c_order_id, dd_order_id, pp_order_id, m_requisition_id
        FROM pp_mrp
        WHERE pp_mrp_id = p_pp_mrp_id;   

    rec cur%ROWTYPE;

  BEGIN
    -- If NO id return null
    IF p_PP_MRP_ID = 0 THEN
      CLOSE cur;
      RETURN '';
    END IF;

    CASE
      WHEN rec.ordertype = 'FTC' THEN
         SELECT f.Name INTO v_DocumentNo FROM M_Forecast f WHERE f.M_Forecast_ID=rec.M_Forecast_ID;
      WHEN rec.ordertype = 'POO' THEN
         SELECT co.DocumentNo INTO v_DocumentNo FROM C_Order co WHERE co.C_Order_ID=rec.C_Order_ID;
      WHEN rec.ordertype = 'DOO' THEN
         SELECT do.DocumentNo INTO v_DocumentNo FROM DD_Order DO WHERE do.DD_Order_ID=rec.DD_Order_ID;
      WHEN rec.ordertype = 'SOO' THEN
         SELECT co.DocumentNo INTO v_DocumentNo FROM C_Order co WHERE co.C_Order_ID=rec.C_Order_ID;
      WHEN rec.ordertype = 'MOP' THEN
         SELECT po.DocumentNo INTO v_DocumentNo FROM PP_Order po WHERE po.PP_Order_ID=rec.PP_Order_ID;
      WHEN rec.ordertype = 'POR' THEN
         SELECT r.DocumentNo INTO v_DocumentNo FROM M_Requisition r WHERE r.M_Requisition_ID=rec.M_Requisition_ID;
      ELSE
        v_documentno := '';
    END CASE;

    CLOSE cur;
    --
    RETURN v_DocumentNo;
  END documentNo;
