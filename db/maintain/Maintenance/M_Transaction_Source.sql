DECLARE
	CURSOR M_Trx IS
		SELECT * FROM M_Transaction
		WHERE M_Locator_ID=1000001
		AND M_Product_ID IN (1008347)
		ORDER BY M_Product_ID, MovementDate;

	ProductName		VARCHAR2(60);
	SourceInfo		VARCHAR2(256);
BEGIN
	FOR m IN M_Trx LOOP
		SELECT Name INTO ProductName FROM M_Product WHERE M_Product_ID=m.M_Product_ID;
		SourceInfo := '';
		IF (m.M_InventoryLine_ID IS NOT NULL) THEN
			SELECT m.M_InventoryLine_ID || ': Inventur ' || i.Name || ' Zeile ' || l.Line 
				INTO SourceInfo
			FROM M_InventoryLine l, M_Inventory i
			WHERE l.M_Inventory_ID=i.M_Inventory_ID
			  AND l.M_InventoryLine_ID=m.M_InventoryLine_ID;
		ELSIF (m.M_InOutLine_ID IS NOT NULL) THEN
			SELECT m.M_InOutLine_ID || ': Lieferschein ' || i.DocumentNo || l.Line || ' Auftrag ' || NVL(o.DocumentNo, '-')
			  INTO SourceInfo
			FROM M_InOutLine l, M_InOut i, C_Order o
			WHERE l.M_InOut_ID=i.M_InOut_ID
			  AND i.C_Order_ID=o.C_Order_ID(+)
			  AND l.M_InOutLine_ID=m.M_InOutLine_ID;
		END IF;

		DBMS_OUTPUT.PUT_LINE(m.M_Transaction_ID || ': ' || ProductName || ' Menge=' || m.MovementQty || ' ' || m.MovementDate
			|| ' - ' || SourceInfo);
	END LOOP;
END;
	
