-- DROP FUNCTION update_promiseddate(numeric,numeric,timestamp,timestamp,character(1),numeric);
CREATE OR REPLACE FUNCTION update_promiseddate(
		a_productId numeric,
		a_bpartnerId numeric, 
		a_promisedDate timestamp,
		a_msgTime timestamp,
		a_promisedDatePrecision character(1),
		a_count numeric
		) RETURNS SETOF numeric AS
$BODY$
DECLARE
	v_orderId	numeric;
	v_orderLineId	numeric;
	v_diff		numeric;
	v_counter	numeric;
	v_promisedDateUpdate	time;
BEGIN

	-- Update the purchase order lines
	FOR v_orderId, v_orderLineId, v_promisedDateUpdate IN 
		SELECT C_OrderLine.C_Order_ID, C_OrderLine_ID, DatePromisedUpdated FROM C_OrderLine 
			LEFT JOIN C_Order ON C_OrderLine.C_Order_ID=C_Order.C_Order_ID 
			WHERE M_Product_ID=a_productId
			AND C_Order.IsSOTrx='N' AND C_Order.C_BPartner_ID=a_bpartnerId AND QtyDelivered<QtyOrdered
			AND C_Order.IsActive='Y' AND DocStatus NOT IN ('VO')
	LOOP
		UPDATE C_OrderLine SET DatePromisedPrecision=a_promisedDatePrecision, DatePromised=a_promisedDate
			WHERE C_OrderLine_ID=v_orderLineId;
		
		RETURN NEXT v_orderLineId;
	END LOOP;


	-- Update as many order lines as possible
	v_counter = 0;
	FOR v_orderId, v_orderLineId, v_promisedDateUpdate, v_diff IN
		SELECT C_OrderLine.C_Order_ID, C_OrderLine_ID, DatePromisedUpdated, QtyOrdered-QtyAllocated FROM C_OrderLine 
			LEFT JOIN C_Order ON C_OrderLine.C_Order_ID=C_Order.C_Order_ID 
			WHERE M_Product_ID=a_productId
			AND C_Order.IsSOTrx='Y' AND QtyDelivered<QtyOrdered AND QtyAllocated<QtyOrdered
			AND C_Order.IsActive='Y' AND DocStatus IN ('CO')
			ORDER BY C_OrderLine.DateOrdered

	LOOP
		v_counter = v_counter + v_diff;
		IF v_counter < a_count THEN 
			UPDATE C_OrderLine SET DatePromisedPrecision=a_promisedDatePrecision, DatePromised=a_promisedDate
				WHERE C_OrderLine_ID=v_orderLineId;
			RETURN NEXT v_orderLineId;
		END IF;
	END LOOP;
		
	
	RETURN;	
END
$BODY$
LANGUAGE 'plpgsql';



-- DROP FUNCTION get_best_datepromised(numeric, numeric, numeric);
CREATE OR REPLACE FUNCTION get_best_datepromised(a_productid numeric, a_warehouseid numeric, a_count numeric, OUT a_datePromised date, OUT a_datePromisedPrecision char(1)) AS 
$BODY$
DECLARE
	v_qty		numeric;
	v_available	numeric;
	v_reserve	numeric;
	v_reserved	numeric;
	v_ordered	numeric;
	v_datePromisedUpdate	time;
	v_datePromised	date;
	v_datePromisedPrecision	char(1);
BEGIN

	-- Check for availability. If there are enough available then today's date will be returned.
	SELECT QtyAvailable, QtyReserved 
	INTO v_available, v_reserved
	FROM M_Product_Stock_V WHERE M_Product_ID=a_productid AND M_Warehouse_ID=a_warehouseid;

	IF v_available < a_count THEN

		v_ordered := 0;
		FOR v_qty, v_datePromised, v_datePromisedPrecision IN
			SELECT QtyOrdered - QtyDelivered, C_OrderLine.DatePromised, DatePromisedPrecision FROM C_OrderLine
			LEFT JOIN C_Order ON C_OrderLine.C_Order_ID=C_Order.C_Order_ID
			WHERE M_Product_ID=a_productid AND C_Order.IsSOTrx='N' AND QtyDelivered<QtyOrdered 
			AND DocStatus IN ('CO') AND C_Order.IsActive='Y' AND C_OrderLine.M_Warehouse_ID=a_warehouseid
			ORDER BY C_OrderLine.DatePromised ASC
		LOOP
			v_ordered := v_ordered + v_qty;
			v_reserve := v_ordered - v_reserved;
			IF v_reserve >= a_count THEN
				a_datePromised := v_datePromised;
				a_datePromisedPrecision := v_datePromisedPrecision;
				EXIT;
			END IF;
		END LOOP;
	END IF;

	IF v_reserve < a_count THEN
		a_datePromised := NULL;
		a_datePromisedPrecision := 'U';
		RETURN;
	END IF;
--	RAISE NOTICE 'Datepromised %', a_datePromised;
	IF a_datePromised < GETDATE() THEN
		SELECT GETDATE() INTO a_datePromised;
	END IF;

	RETURN;
END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
