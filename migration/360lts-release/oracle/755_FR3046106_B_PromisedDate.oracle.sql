-- Updates promised date on given product and bpartner

CREATE OR REPLACE 
FUNCTION update_promiseddate
 (
		a_productId IN NUMBER,
		a_bpartnerId IN NUMBER,
		a_promisedDate IN TIMESTAMP,
		a_msgTime IN TIMESTAMP,
		a_promisedDatePrecision IN CHAR,
		a_count IN NUMBER
		)
 RETURN NUMBER AS

	v_counter	NUMBER;
	v_updateCount NUMBER;
	v_promisedDateUpdate	DATE;

	TYPE OrderRec IS RECORD (
		C_Order_ID NUMBER,
		C_OrderLine_ID NUMBER,
		DatePromisedUpdated	DATE,
		Delta NUMBER
	);

	v_rec OrderRec;

BEGIN

	v_updateCount := 0;

	-- Update the purchase order lines
	FOR v_rec IN
		(SELECT C_OrderLine.C_Order_ID, C_OrderLine_ID, DatePromisedUpdated, 0 AS Delta FROM C_OrderLine
			LEFT JOIN C_Order ON C_OrderLine.C_Order_ID=C_Order.C_Order_ID
			WHERE M_Product_ID=a_productId
			AND C_Order.IsSOTrx='N' AND C_Order.C_BPartner_ID=a_bpartnerId AND QtyDelivered<QtyOrdered
			AND C_Order.IsActive='Y' AND DocStatus NOT IN ('VO'))
	LOOP
		UPDATE C_OrderLine SET DatePromisedPrecision=a_promisedDatePrecision, DatePromised=a_promisedDate
			WHERE C_OrderLine_ID=v_rec.C_OrderLine_ID;
		v_updateCount := v_updateCount + 1;
	END LOOP;


	-- Update as many order lines as possible
	v_counter := 0;
	FOR v_rec IN
		(SELECT C_OrderLine.C_Order_ID, C_OrderLine_ID, DatePromisedUpdated, QtyOrdered-QtyAllocated AS Delta FROM C_OrderLine
			LEFT JOIN C_Order ON C_OrderLine.C_Order_ID=C_Order.C_Order_ID
			WHERE M_Product_ID=a_productId
			AND C_Order.IsSOTrx='Y' AND QtyDelivered<QtyOrdered AND QtyAllocated<QtyOrdered
			AND C_Order.IsActive='Y' AND DocStatus IN ('CO')
			ORDER BY C_OrderLine.DateOrdered)

	LOOP
		v_counter := v_counter + v_rec.Delta;
		IF v_counter < a_count THEN
			UPDATE C_OrderLine SET DatePromisedPrecision=a_promisedDatePrecision, DatePromised=a_promisedDate
				WHERE C_OrderLine_ID=v_rec.C_OrderLine_ID;
			v_updateCount := v_updateCount + 1;
		END IF;
	END LOOP;

	RETURN v_updateCount;
END update_promiseddate;
/

CREATE OR REPLACE 
PROCEDURE get_best_datepromised
(
		a_productid IN NUMBER, 
		a_warehouseid IN NUMBER, 
		a_count IN NUMBER, 
		a_datePromised OUT DATE, 
		a_datePromisedPrecision OUT CHAR
) AS 

	v_qty		NUMBER;
	v_available	NUMBER;
	v_reserve	NUMBER;
	v_reserved	NUMBER;
	v_ordered	NUMBER;
	v_datePromisedUpdate	DATE;
	v_datePromised	DATE;
	v_datePromisedPrecision	CHAR;
	v_now DATE;
	TYPE QtyRec IS RECORD (
		qty		NUMBER,
		datePromised	DATE,
		datePromisedPrecision CHAR
	);
	v_rec	QtyRec;

BEGIN
	
	-- Check for availability. If there are enough available then today's date will be returned.
	SELECT QtyAvailable, QtyReserved INTO v_available, v_reserved	
		FROM M_Product_Stock_V WHERE M_Product_ID=a_productid AND M_Warehouse_ID=a_warehouseid;

	IF v_available < a_count THEN

		v_ordered := 0;

				FOR v_rec IN 
				(SELECT QtyOrdered-QtyDelivered AS qty, C_OrderLine.DatePromised, DatePromisedPrecision FROM C_OrderLine
					JOIN C_Order ON C_OrderLine.C_Order_ID=C_Order.C_Order_ID
					WHERE M_Product_ID=a_productid AND C_Order.IsSOTrx='N' AND QtyDelivered<QtyOrdered 
					AND DocStatus IN ('CO') AND C_Order.IsActive='Y' AND C_OrderLine.M_Warehouse_ID=a_warehouseid
					ORDER BY C_OrderLine.DatePromised ASC)
		   LOOP
					v_ordered := v_ordered + v_rec.qty;
					v_reserve := v_ordered - v_reserved;
					IF v_reserve >= a_count THEN
						a_datePromised := v_rec.datePromised;
						a_datePromisedPrecision := v_rec.datePromisedPrecision;
						EXIT;
					END IF;
			END LOOP;

	END IF;

	IF v_reserve < a_count THEN
		a_datePromised := NULL;
		a_datePromisedPrecision := 'U';
		RETURN;
	END IF;

	SELECT SYSDATE() INTO v_now FROM DUAL;

--	RAISE NOTICE 'Datepromised %', a_datePromised;
	IF a_datePromised < v_now THEN
			a_datePromised := v_now;
	END IF;

	RETURN;
END get_best_datepromised;
/