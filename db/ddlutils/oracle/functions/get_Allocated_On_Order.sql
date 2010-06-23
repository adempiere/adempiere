/************************************************************************
 * Function Get_Allocated_On_Order - Return number of allocated products
 * of the specific product in the given warehouse.
 * Author: Daniel Tamm (usrdno)
************************************************************************/
CREATE OR REPLACE FUNCTION get_allocated_on_order
(
	p_product_id IN NUMBER, 
	p_warehouse_id IN NUMBER)
  RETURN NUMBER AS

    v_sum		NUMBER;

BEGIN
    --  Get Product Attribute Set Instance
	SELECT sum(qtyallocated) into v_sum from C_OrderLine ol
		JOIN C_Order o on (o.C_Order_ID=ol.C_Order_ID)
		WHERE 
		M_Product_ID=p_product_id AND
		COALESCE(ol.M_Warehouse_ID, o.M_Warehouse_ID)=p_warehouse_id;

        RETURN v_sum;
END get_allocated_on_order;
/
