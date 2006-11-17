CREATE OR REPLACE PROCEDURE T_InventoryValue_Create
(
	p_PInstance_ID			IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: T_InventoryValue_Create.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Inventory Valuation Temporary Table
 * Description:
 ************************************************************************/
AS
	--	Logistice
	v_ResultStr					VARCHAR2(2000);
	v_Message						VARCHAR2(2000);
	v_Result						NUMBER := 1;	-- 0=failure
	v_Record_ID					NUMBER;
	v_AD_User_ID					NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (pp_PInstance NUMBER) IS
		SELECT i.Record_ID, i.AD_User_ID,
			p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=pp_PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	p_M_PriceList_Version_ID			NUMBER(10);
	p_DateValue					DATE;
	p_M_Warehouse_ID				NUMBER(10);
	p_C_Currency_ID				NUMBER(10);

BEGIN
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || p_PInstance_ID);
	v_ResultStr := 'PInstanceNotFound';
	UPDATE AD_PInstance
	SET Created = SysDate,
		IsProcessing = 'Y'
	WHERE AD_PInstance_ID=p_PInstance_ID;
	COMMIT;

	--	Get Parameters
	v_ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (p_PInstance_ID) LOOP
		v_Record_ID := p.Record_ID;
		v_AD_User_ID := p.AD_User_ID;
		IF (p.ParameterName = 'M_PriceList_Version_ID') THEN
			p_M_PriceList_Version_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  M_PriceList_Version_ID=' || p_M_PriceList_Version_ID);
		ELSIF (p.ParameterName = 'DateValue') THEN
			p_DateValue := p.P_Date;
			DBMS_OUTPUT.PUT_LINE('  DateValue=' || p_DateValue);
		ELSIF (p.ParameterName = 'M_Warehouse_ID') THEN
			p_M_Warehouse_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  M_Warehouse_ID=' || p_M_Warehouse_ID);
		ELSIF (p.ParameterName = 'C_Currency_ID') THEN
			p_C_Currency_ID := p.P_Number;
			DBMS_OUTPUT.PUT_LINE('  C_Currency_ID=' || p_C_Currency_ID);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
		END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || v_Record_ID);

	-- Clear
--	v_ResultStr := 'ClearTable';
--	DELETE T_InventoryValue WHERE M_Warehouse_ID=p_M_Warehouse_ID;
--	COMMIT;

	--	Insert Products
	v_ResultStr := 'InsertStockedProducts';
	INSERT INTO T_InventoryValue 
		(AD_Client_ID,AD_Org_ID, AD_PInstance_ID, M_Warehouse_ID,M_Product_ID)
	SELECT AD_Client_ID,AD_Org_ID, p_PInstance_ID, p_M_Warehouse_ID,M_Product_ID
	FROM M_Product 
	WHERE IsStocked='Y';
	--
	IF (SQL%ROWCOUNT = 0) THEN
		v_Message := '@Created@ = 0';
		GOTO FINISH_PROCESS;
	END IF;

	-- Update Constants
	v_ResultStr := 'UpdateConstants';
	UPDATE T_InventoryValue 
	  SET	DateValue = TRUNC(p_DateValue) + 0.9993,
			M_PriceList_Version_ID = p_M_PriceList_Version_ID,
			C_Currency_ID = p_C_Currency_ID
	WHERE	M_Warehouse_ID = p_M_Warehouse_ID;

	--	Get current QtyOnHand
	v_ResultStr := 'GetQtyOnHand';
	UPDATE T_InventoryValue iv
	  SET	QtyOnHand = (SELECT SUM(QtyOnHand) FROM M_Storage s, M_Locator l
				WHERE iv.M_Product_ID=s.M_Product_ID
				 AND l.M_Locator_ID=s.M_Locator_ID
				 AND l.M_Warehouse_ID=iv.M_Warehouse_ID)
	WHERE	iv.M_Warehouse_ID = p_M_Warehouse_ID;

	-- Adjust for Valuation Date
	v_ResultStr := 'AdjustQtyOnHand';
	UPDATE T_InventoryValue iv 
	  SET	QtyOnHand = 
				(SELECT iv.QtyOnHand - NVL(SUM(t.MovementQty), 0) 
				FROM M_Transaction t, M_Locator l
				WHERE t.M_Product_ID=iv.M_Product_ID 
    --            AND t.M_AttributeSetInstance_ID=iv.M_AttributeSetInstance_ID
				  AND t.MovementDate > iv.DateValue
				  AND t.M_Locator_ID=l.M_Locator_ID 
				  AND l.M_Warehouse_ID=iv.M_Warehouse_ID)
	WHERE	iv.M_Warehouse_ID = p_M_Warehouse_ID;

	--	Delete Records w/o OnHand Qty
	v_ResultStr := 'DeleteZeroQtyOnHand';
	DELETE T_InventoryValue 
	WHERE	QtyOnHand=0 
	  OR	QtyOnHand IS NULL;

	-- Update Prices
	v_ResultStr := 'GetPrices';
	UPDATE T_InventoryValue iv
	  SET	PricePO = 
				(SELECT currencyConvert (po.PriceList,po.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM M_Product_PO po WHERE po.M_Product_ID=iv.M_Product_ID
				AND po.IsCurrentVendor='Y' AND RowNum=1),
			PriceList = 
				(SELECT currencyConvert(pp.PriceList,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp
				WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID
				 AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID
				 AND plv.M_PriceList_ID=pl.M_PriceList_ID),
			PriceStd = 
				(SELECT currencyConvert(pp.PriceStd,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp
				WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID
				 AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID
				 AND plv.M_PriceList_ID=pl.M_PriceList_ID), 
			PriceLimit = 
				(SELECT currencyConvert(pp.PriceLimit,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp
				WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID
				 AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID
				 AND plv.M_PriceList_ID=pl.M_PriceList_ID),
			CostStandard = 
				(SELECT currencyConvert(pc.CurrentCostPrice,acs.C_Currency_ID,iv.C_Currency_ID,iv.DateValue, null, iv.AD_Client_ID, iv.AD_Org_ID)
				FROM AD_ClientInfo ci, C_AcctSchema acs, M_Product_Costing pc
				WHERE iv.AD_Client_ID=ci.AD_Client_ID AND ci.C_AcctSchema1_ID=acs.C_AcctSchema_ID
				 AND acs.C_AcctSchema_ID=pc.C_AcctSchema_ID
				 AND iv.M_Product_ID=pc.M_Product_ID)
	WHERE	iv.M_Warehouse_ID = p_M_Warehouse_ID;

	--	Update Values
	v_ResultStr := 'UpdateValue';
	UPDATE T_InventoryValue 
	  SET	PricePOAmt = QtyOnHand * PricePO, 
			PriceListAmt = QtyOnHand * PriceList, 
			PriceStdAmt = QtyOnHand * PriceStd, 
			PriceLimitAmt = QtyOnHand * PriceLimit, 
			CostStandardAmt = QtyOnHand * CostStandard
	WHERE	M_Warehouse_ID = p_M_Warehouse_ID;

	v_Message := '@Created@ = ' || SQL%ROWCOUNT;


<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || v_Message);
	UPDATE	AD_PInstance
	SET Updated = SysDate,
		IsProcessing = 'N',
		Result = v_Result,			-- 1=success
		ErrorMsg = v_Message
	WHERE	AD_PInstance_ID=p_PInstance_ID;
	COMMIT;
	RETURN;

EXCEPTION
	WHEN  OTHERS THEN
		v_ResultStr := v_ResultStr || ': ' || SQLERRM || ' - ' || v_Message;
		DBMS_OUTPUT.PUT_LINE(v_ResultStr);
		ROLLBACK;
		UPDATE	AD_PInstance
		SET Updated = SysDate,
			IsProcessing = 'N',
			Result = 0,				-- failure
			ErrorMsg = v_ResultStr
		WHERE	AD_PInstance_ID=p_PInstance_ID;
		COMMIT;
		RETURN;

END T_InventoryValue_Create;
/
