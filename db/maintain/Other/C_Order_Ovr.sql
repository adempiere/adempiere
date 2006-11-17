/**
 *	List Orders with their Shipments and Invoices
 */
DECLARE
	DocNo		VARCHAR2(60) := '9003591';
	--
	CURSOR	Cur_Order	IS
		SELECT	o.C_Order_ID, d.Name, o.DocumentNo, o.DocStatus, o.DocAction, o.Processed
		FROM	C_Order o, C_DocType d
		WHERE	o.DocumentNo LIKE DocNo
		  AND	o.C_DocType_ID=d.C_DocType_ID
		ORDER BY o.DocumentNo DESC;

BEGIN
	--	Order Info
	FOR o IN Cur_Order LOOP

		DBMS_OUTPUT.PUT_LINE (o.Name || ' ' || o.DocumentNo || '(' || o.C_Order_ID
			|| '):  Status=' || o.DocStatus
			|| ', Action=' || o.DocAction || ', Processed=' || o.Processed);

		--	Order Lines
		DECLARE
			CURSOR	Cur_OrderLine	IS
				SELECT	*
				FROM	C_OrderLine
				WHERE	C_Order_ID=o.C_Order_ID
				ORDER BY Line;
		BEGIN
			FOR ol IN Cur_OrderLine LOOP
				DBMS_OUTPUT.PUT_LINE ('   Ordered=' || ol.QtyOrdered || ', Reserved=' || ol.QtyReserved
					|| ', Delivered=' || ol.QtyDelivered || ', Invoiced=' || ol.QtyInvoiced
					|| ' - Warehouse=' || ol.M_Warehouse_ID || ', Direct=' || ol.DirectShip
					|| ', Product=' || ol.M_Product_ID);
			END LOOP;
		END;
	
		-- Shipment
		DECLARE
			CURSOR 	Cur_InOut		IS
				SELECT	s.M_InOut_ID, d.Name, s.DocumentNo, s.DocStatus, s.Processed, s.M_Warehouse_ID
				FROM	M_InOut s, C_DocType d
				WHERE	s.C_Order_ID = o.C_Order_ID
				  AND	s.C_DocType_ID=d.C_DocType_ID;
		BEGIN
			FOR s IN Cur_InOut LOOP
				DBMS_OUTPUT.PUT_LINE ('> ' || s.Name || ' ' || s.DocumentNo || '(' || s.M_InOut_ID
					|| '):  Status=' || s.DocStatus
					|| ', Processed=' || s.Processed || ', Warehouse=' || s.M_Warehouse_ID);
				--	Shipment Lines
				DECLARE
					CURSOR	Cur_InOutLine	IS
						SELECT	*
						FROM	M_InOutLine
						WHERE	M_InOut_ID=s.M_InOut_ID
						ORDER BY Line;
				BEGIN
					FOR sl IN Cur_InOutLine LOOP
						DBMS_OUTPUT.PUT_LINE ('   Delivered=' || sl.MovementQty || ', Product=' || sl.M_Product_ID);
					END LOOP;
				END;	-- 	Shipment Lines
 			END LOOP;	--	Shipments
		END; --	Shipment

		-- Invoice
		DECLARE
			CURSOR 	Cur_Invoice		IS
				SELECT	i.C_Invoice_ID, d.Name, i.DocumentNo, i.DocStatus, i.Processed
				FROM	C_Invoice i, C_DocType d
				WHERE	i.C_DocType_ID=d.C_DocType_ID
				  AND EXISTS (SELECT * FROM C_InvoiceLine l, C_OrderLine ol
 				  	WHERE 	i.C_Invoice_ID = l.C_Invoice_ID
					  AND	l.C_OrderLine_ID = ol.C_OrderLine_ID
					  AND	ol.C_Order_ID=o.C_Order_ID);
		BEGIN
			FOR i IN Cur_Invoice LOOP
				DBMS_OUTPUT.PUT_LINE ('> ' || i.Name || ' ' || i.DocumentNo || '(' || i.C_Invoice_ID
					|| '):  Status=' || i.DocStatus || ', Processed=' || i.Processed);
				--	Invoice Lines
				DECLARE
					CURSOR	Cur_InvoiceLine	IS
						SELECT	*
						FROM	C_InvoiceLine
						WHERE	C_Invoice_ID=i.C_Invoice_ID
						ORDER BY Line;
				BEGIN
					FOR il IN Cur_InvoiceLine LOOP
						DBMS_OUTPUT.PUT_LINE ('   Invoiced=' || il.QtyInvoiced || ', Product=' || il.M_Product_ID);
					END LOOP;
				END;	-- 	Invoice Lines
 			END LOOP;	--	Invoices
		END; --	Invoice
	
	END LOOP;	-- Order
END;
