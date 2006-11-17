CREATE OR REPLACE PROCEDURE C_Order_DrillDown
(
	PInstance_ID		IN NUMBER
)
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
 ******************************************************************************
 *	List Orders with their Shipments and Invoices
 *	Spool to T_Spool
 */
AS
	ResultStr						VARCHAR2(2000);
	Message							VARCHAR2(2000);
	Record_ID						NUMBER;
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter
	C_Order_ID						NUMBER;
	--
	CURSOR Cur_Order IS
		SELECT	o.C_Order_ID, d.Name, o.DocumentNo, o.DocStatus, o.DocAction, o.Processed
		FROM	C_Order o, C_DocType d
		WHERE	o.C_Order_ID=C_Order_ID
		  AND	o.C_DocType_ID=d.C_DocType_ID
		ORDER BY o.DocumentNo DESC;

BEGIN
	--	No locking or Updating

	--	Get Parameters
	ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (PInstance_ID) LOOP
		Record_ID := p.Record_ID;
		IF (p.ParameterName = 'C_Order_ID') THEN
 			C_Order_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  C_Order_ID=' || C_Order_ID);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
	 	END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || Record_ID);

	IF (C_Order_ID IS NULL) THEN
		C_Order_ID := Record_ID;
	END IF;

	--	Should be nothing there
	DELETE 	T_Spool
	WHERE	AD_PInstance_ID=PInstance_ID;

	--	Order Info
	FOR o IN Cur_Order LOOP

		INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
			o.Name || ' ' || o.DocumentNo || ':  @DocStatus@=' || o.DocStatus
			|| ', @DocAction@=' || o.DocAction || ', @Processed@=' || o.Processed);

		--	Order Lines
		DECLARE
			CURSOR	Cur_OrderLine	IS
				SELECT	*
				FROM	C_OrderLine
				WHERE	C_Order_ID=o.C_Order_ID
				ORDER BY Line;
		BEGIN
			FOR ol IN Cur_OrderLine LOOP
				INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
					'   @QtyOrdered@=' || ol.QtyOrdered || ', @QtyReserved@=' || ol.QtyReserved
					|| ', @QtyDelivered@=' || ol.QtyDelivered || ', @QtyInvoiced@=' || ol.QtyInvoiced
					|| ' - Wh=' || ol.M_Warehouse_ID
					|| ', Prd=' || ol.M_Product_ID);
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
				INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
					'> ' || s.Name || ' ' || s.DocumentNo || ':  @DocStatus@=' || s.DocStatus 
					|| ', @Processed@=' || s.Processed || ', Wh=' || s.M_Warehouse_ID);

				--	Shipment Lines
				DECLARE
					CURSOR	Cur_InOutLine	IS
						SELECT	*
						FROM	M_InOutLine
						WHERE	M_InOut_ID=s.M_InOut_ID
						ORDER BY Line;
				BEGIN
					FOR sl IN Cur_InOutLine LOOP
						INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
							'   @QtyDelivered@=' || sl.MovementQty || ', Prd=' || sl.M_Product_ID);
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

				INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
					'> ' || i.Name || ' ' || i.DocumentNo || ':  @DocStatus@=' || i.DocStatus
					|| ', @Processed@=' || i.Processed);

				--	Invoice Lines
				DECLARE
					CURSOR	Cur_InvoiceLine	IS
						SELECT	*
						FROM	C_InvoiceLine
						WHERE	C_Invoice_ID=i.C_Invoice_ID
						ORDER BY Line;
				BEGIN
					FOR il IN Cur_InvoiceLine LOOP
						INSERT INTO T_Spool (AD_PInstance_ID, SeqNo, MsgText) VALUES (PInstance_ID, T_Spool_Seq.NextVal, 
							'   @QtyInvoiced@=' || il.QtyInvoiced || ', Prd=' || il.M_Product_ID);
					END LOOP;
				END;	-- 	Invoice Lines
 			END LOOP;	--	Invoices
		END; --	Invoice
	
	END LOOP;	-- Order


<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = 1,                 -- success
        ErrorMsg = Message
    WHERE   AD_PInstance_ID=PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		ResultStr := ResultStr || ': ' || SQLERRM || ' - ' || Message;
		DBMS_OUTPUT.PUT_LINE(ResultStr);
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = ResultStr
        WHERE   AD_PInstance_ID=PInstance_ID;
        COMMIT;
        RETURN;

END C_Order_DrillDown;
/
