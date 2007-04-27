CREATE OR REPLACE
PROCEDURE             "M_PRICELIST_CREATE" 
( 
	PInstance_ID			IN NUMBER
)
AS
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_PriceList_Create.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Create Pricelist
 * Description:
 *		Create PriceList by copying purchase prices (M_Product_PO) 
 *		and applying product category discounts (M_CategoryDiscount)
 *  Carlos Ruiz - globalqss - Make T_Selection tables permanent
 ************************************************************************/
	--	Logistice
	ResultStr						VARCHAR2(2000);
	Message							VARCHAR2(2000) := '';
	NoRate							EXCEPTION;
	--	Parameter
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	p_PriceList_Version_ID			NUMBER;
	p_DeleteOld						CHAR(1) := 'N';
	--
	v_Currency_ID					NUMBER;
	v_Client_ID						NUMBER;
	v_Org_ID						NUMBER;
	v_UpdatedBy						NUMBER;
	v_StdPrecision					NUMBER;
	v_DiscountSchema_ID				NUMBER;
	v_PriceList_Version_Base_ID		NUMBER;
	--
	v_NextNo						NUMBER := 0;

	--	Get PL Parameter
	CURSOR Cur_DiscountLine (DiscountSchema_ID NUMBER) IS
		SELECT	* 
		FROM	M_DiscountSchemaLine
		WHERE	M_DiscountSchema_ID=DiscountSchema_ID
		  AND	IsActive='Y'
		ORDER BY SeqNo;

BEGIN
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing');
	ResultStr := 'PInstanceNotFound';
	UPDATE AD_PInstance
	SET Created = SysDate,
		IsProcessing = 'Y'
	WHERE AD_PInstance_ID=PInstance_ID;
	COMMIT;

	--	Get Parameters
	ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (PInstance_ID) LOOP
		p_PriceList_Version_ID := p.Record_ID;
		IF (p.ParameterName = 'DeleteOld') THEN
			p_DeleteOld := p.P_String;
			DBMS_OUTPUT.PUT_LINE('  DeleteOld=' || p_DeleteOld);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
		END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  PriceList_Version_ID=' || p_PriceList_Version_ID);

	--	Checking Prerequisites
	--	--	PO Prices must exists
	ResultStr := 'CorrectingProductPO';
	DBMS_OUTPUT.PUT_LINE(ResultStr);
	UPDATE	M_Product_PO
	  SET	PriceList = 0
	WHERE	PriceList IS NULL;
	UPDATE	M_Product_PO	
	  SET	PriceLastPO = 0
	WHERE	PriceLastPO IS NULL;
	UPDATE	M_Product_PO
	  SET	PricePO = PriceLastPO
	WHERE	(PricePO IS NULL OR PricePO = 0) AND PriceLastPO <> 0;
	UPDATE	M_Product_PO
	  SET	PricePO = 0
	WHERE	PricePO IS NULL;
	-- Set default current vendor
	UPDATE	M_Product_PO p
	  SET	IsCurrentVendor = 'Y'
	WHERE	IsCurrentVendor = 'N' 
	  AND NOT EXISTS 
		(SELECT pp.M_Product_ID FROM M_Product_PO pp
		WHERE pp.M_Product_ID=p.M_Product_ID
		GROUP BY pp.M_Product_ID HAVING COUNT(*) > 1);
	COMMIT;

	/**
	 *	Make sure that we have only one active product
	 */
	ResultStr := 'CorrectingDuplicates';
	DBMS_OUTPUT.PUT_LINE(ResultStr);
	DECLARE
		--	All duplicate products
		CURSOR	Cur_Duplicates	IS
			SELECT	DISTINCT M_Product_ID
			FROM	M_Product_PO po
			WHERE	IsCurrentVendor='Y' AND IsActive='Y'
			  AND EXISTS (	SELECT M_Product_ID FROM M_Product_PO x 
							WHERE x.M_Product_ID=po.M_Product_ID 
							GROUP BY M_Product_ID HAVING COUNT(*) > 1 )
			ORDER BY 1;
		--	All vendors of Product - expensive first
		CURSOR	Cur_Vendors	(Product_ID NUMBER) IS
			SELECT	M_Product_ID, C_BPartner_ID
			FROM	M_Product_PO
			WHERE	IsCurrentVendor='Y' AND IsActive='Y'
			  AND	M_Product_ID=Product_ID
			ORDER BY PriceList DESC;
		--
		Product_ID				NUMBER;
		BPartner_ID				NUMBER;
	BEGIN
		FOR dupl IN Cur_Duplicates LOOP
			OPEN Cur_Vendors (dupl.M_Product_ID);
			FETCH Cur_Vendors INTO Product_ID, BPartner_ID;		--	Leave First
			LOOP
				FETCH Cur_Vendors INTO Product_ID, BPartner_ID;	--	Get Record ID
				EXIT WHEN Cur_Vendors%NOTFOUND;
				--
				DBMS_OUTPUT.PUT_LINE('  Record: ' || Product_ID || ' / ' || BPartner_ID);
				UPDATE	M_Product_PO
				  SET	IsCurrentVendor='N'
				WHERE	M_Product_ID=Product_ID AND C_BPartner_ID=BPartner_ID;
			END LOOP;
			CLOSE Cur_Vendors;
		END LOOP;
		COMMIT;
	END;
	
	/**	Delete Old Data	*/
	ResultStr := 'DeletingOld';
	IF (p_DeleteOld = 'Y') THEN
		DELETE	M_ProductPrice
		WHERE	M_PriceList_Version_ID = p_PriceList_Version_ID;
		Message := '@Deleted@=' || SQL%ROWCOUNT || ' - ';
		DBMS_OUTPUT.PUT_LINE(Message);
	END IF;

	--	Get PriceList Info
	ResultStr := 'GetPLInfo';
	DBMS_OUTPUT.PUT_LINE(ResultStr);
	SELECT	p.C_Currency_ID, c.StdPrecision,
		v.AD_Client_ID, v.AD_Org_ID, v.UpdatedBy, 
		v.M_DiscountSchema_ID, M_PriceList_Version_Base_ID
	  INTO	v_Currency_ID, v_StdPrecision,
		v_Client_ID, v_Org_ID, v_UpdatedBy, 
		v_DiscountSchema_ID, v_PriceList_Version_Base_ID
	FROM	M_PriceList p, M_PriceList_Version v, C_Currency c
	WHERE	p.M_PriceList_ID=v.M_PriceList_ID 
	  AND	p.C_Currency_ID=c.C_Currency_ID
	  AND	v.M_PriceList_Version_ID=p_PriceList_Version_ID;

	/**
	 *	For All Discount Lines in Sequence
	 */
	FOR dl IN Cur_DiscountLine (v_DiscountSchema_ID) LOOP
		ResultStr := 'Parameter Seq=' || dl.SeqNo;
	--	DBMS_OUTPUT.PUT_LINE(ResultStr);

		--	Clear Temporary Table
		DELETE FROM T_Selection WHERE AD_PInstance_ID = PInstance_ID;

		--	-----------------------------------
		--	Create Selection in temporary table
		--	-----------------------------------
		IF (v_PriceList_Version_Base_ID IS NULL) THEN
		--	Create Selection from M_Product_PO
			INSERT INTO T_Selection (AD_PInstance_ID, T_Selection_ID)
			SELECT	DISTINCT PInstance_ID, po.M_Product_ID 
			FROM	M_Product p, M_Product_PO po
			WHERE	p.M_Product_ID=po.M_Product_ID
			  AND	(p.AD_Client_ID=v_Client_ID OR p.AD_Client_ID=0)
			  AND	p.IsActive='Y' AND po.IsActive='Y' AND po.IsCurrentVendor='Y'
			--	Optional Restrictions
			  AND (dl.M_Product_Category_ID IS NULL OR p.M_Product_Category_ID IN (
                              SELECT M_Product_Category_ID FROM M_Product_Category 
                              START WITH M_Product_Category_ID=dl.M_Product_Category_ID
                              CONNECT BY M_Product_Category_Parent_ID = PRIOR M_Product_Category_ID
                          ))
			  AND (dl.C_BPartner_ID IS NULL OR po.C_BPartner_ID=dl.C_BPartner_ID)
			  AND (dl.M_Product_ID IS NULL OR p.M_Product_ID=dl.M_Product_ID);
		ELSE
		--	Create Selection from existing PriceList
			INSERT INTO T_Selection (AD_PInstance_ID, T_Selection_ID)
			SELECT	DISTINCT PInstance_ID, p.M_Product_ID 
			FROM	M_Product p, M_ProductPrice pp
			WHERE	p.M_Product_ID=pp.M_Product_ID
			  AND	pp.M_PriceList_Version_ID=v_PriceList_Version_Base_ID
			  AND	p.IsActive='Y' AND pp.IsActive='Y'
			--	Optional Restrictions
			  AND (dl.M_Product_Category_ID IS NULL OR p.M_Product_Category_ID IN (
                              SELECT M_Product_Category_ID FROM M_Product_Category 
                              START WITH M_Product_Category_ID=dl.M_Product_Category_ID
                              CONNECT BY M_Product_Category_Parent_ID = PRIOR M_Product_Category_ID
                          ))
			  AND	(dl.C_BPartner_ID IS NULL OR EXISTS 
					(SELECT * FROM M_Product_PO po WHERE po.M_Product_ID=p.M_Product_ID AND po.C_BPartner_ID=dl.C_BPartner_ID))
			  AND	(dl.M_Product_ID IS NULL OR p.M_Product_ID=dl.M_Product_ID);
		END IF;
		Message := Message || '@Selected@=' || SQL%ROWCOUNT;
	--	DBMS_OUTPUT.PUT_LINE(Message);

		--	Delete Prices in Selection, so that we can insert
		IF (v_PriceList_Version_Base_ID IS NULL
				OR v_PriceList_Version_Base_ID <> p_PriceList_Version_ID) THEN
			ResultStr := ResultStr || ', Delete';
			DELETE	M_ProductPrice pp
			WHERE	pp.M_PriceList_Version_ID = p_PriceList_Version_ID
			  AND EXISTS (SELECT * FROM T_Selection s WHERE pp.M_Product_ID=s.T_Selection_ID
			                       AND s.AD_PInstance_ID = PInstance_ID);
			Message := ', @Deleted@=' || SQL%ROWCOUNT;
		END IF;

		--	--------------------
		--	Copy (Insert) Prices
		--	--------------------
		IF (v_PriceList_Version_Base_ID = p_PriceList_Version_ID) THEN
		--	We have Prices already
			NULL;
		ELSIF (v_PriceList_Version_Base_ID IS NULL) THEN
		--	Copy and Convert from Product_PO
			ResultStr := ResultStr || ',Copy_PO';
			INSERT INTO M_ProductPrice
				(M_PriceList_Version_ID, M_Product_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				PriceList, PriceStd, PriceLimit)
			SELECT 
				p_PriceList_Version_ID, po.M_Product_ID, 
				v_Client_ID, v_Org_ID, 'Y', SysDate, v_UpdatedBy, SysDate, v_UpdatedBy,
				--	Price List
				COALESCE(currencyConvert(po.PriceList, 
					po.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0), 
				--	Price Std
				COALESCE(currencyConvert(po.PriceList, 
					po.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0), 
				--	Price Limit
				COALESCE(currencyConvert(po.PricePO,
						po.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0)
			FROM	M_Product_PO po
			WHERE EXISTS (SELECT * FROM T_Selection s WHERE po.M_Product_ID=s.T_Selection_ID
			                       AND s.AD_PInstance_ID = PInstance_ID)
			  AND	po.IsCurrentVendor='Y' AND po.IsActive='Y';
		ELSE
		--	Copy and Convert from other PriceList_Version
			ResultStr := ResultStr || ',Copy_PL';
			INSERT INTO M_ProductPrice
				(M_PriceList_Version_ID, M_Product_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				PriceList, PriceStd, PriceLimit)
			SELECT 
				p_PriceList_Version_ID, pp.M_Product_ID, 
				v_Client_ID, v_Org_ID, 'Y', SysDate, v_UpdatedBy, SysDate, v_UpdatedBy,
				--	Price List
				COALESCE(currencyConvert(pp.PriceList, 
					pl.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0), 
				--	Price Std
				COALESCE(currencyConvert(pp.PriceStd, 
					pl.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0), 
				--	Price Limit
				COALESCE(currencyConvert(pp.PriceLimit,
					pl.C_Currency_ID, v_Currency_ID, dl.ConversionDate, dl.C_ConversionType_ID, v_Client_ID, v_Org_ID),0)
			FROM M_ProductPrice pp
                INNER JOIN M_PriceList_Version plv ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID)
                INNER JOIN M_PriceList pl ON (plv.M_PriceList_ID=pl.M_PriceList_ID)
			WHERE	pp.M_PriceList_Version_ID=v_PriceList_Version_Base_ID
			  AND EXISTS (SELECT * FROM T_Selection s WHERE pp.M_Product_ID=s.T_Selection_ID
			                       AND s.AD_PInstance_ID = PInstance_ID)
			  AND	pp.IsActive='Y';
		END IF;
		Message := Message || ', @Inserted@=' || SQL%ROWCOUNT;

		--	-----------
		--	Calculation
		--	-----------
		ResultStr := ResultStr || ',Calc';
		UPDATE	M_ProductPrice p
		  SET	PriceList = (DECODE(dl.List_Base, 'S', PriceStd, 'X', PriceLimit, PriceList) 
					+ dl.List_AddAmt) * (1 - dl.List_Discount/100),
				PriceStd = (DECODE(dl.Std_Base, 'L', PriceList, 'X', PriceLimit, PriceStd) 
					+ dl.Std_AddAmt) * (1 - dl.Std_Discount/100),
				PriceLimit = (DECODE(dl.Limit_Base, 'L', PriceList, 'S', PriceStd, PriceLimit) 
					+ dl.Limit_AddAmt) * (1 - dl.Limit_Discount/100)
		WHERE	M_PriceList_Version_ID=p_PriceList_Version_ID
		  AND EXISTS	(SELECT * FROM T_Selection s
						WHERE s.T_Selection_ID=p.M_Product_ID
			                       AND s.AD_PInstance_ID = PInstance_ID);

		--	--------
		-- 	Rounding	(AD_Reference_ID=155)
		--	--------
		ResultStr := ResultStr || ',Round';
		UPDATE	M_ProductPrice p
		  SET	PriceList = DECODE(dl.List_Rounding, 
					'N', PriceList,
					'0', ROUND(PriceList, 0),	--	Even .00					
					'D', ROUND(PriceList, 1),	--	Dime .10
					'T', ROUND(PriceList, -1),	--	Ten 10.00
					'5', ROUND(PriceList*20,0)/20,	--	Nickle .05
					'Q', ROUND(PriceList*4,0)/4,	--	Quarter .25	
					ROUND(PriceList, v_StdPrecision)),--	Currency
				PriceStd = DECODE(dl.Std_Rounding, 
					'N', PriceStd, 
					'0', ROUND(PriceStd, 0),	--	Even .00					
					'D', ROUND(PriceStd, 1),	--	Dime .10
					'T', ROUND(PriceStd, -1),	--	Ten 10.00
					'5', ROUND(PriceStd*20,0)/20,	--	Nickle .05
					'Q', ROUND(PriceStd*4,0)/4,		--	Quarter .25	
					ROUND(PriceStd, v_StdPrecision)),	--	Currency
				PriceLimit = DECODE(dl.Limit_Rounding, 
					'N', PriceLimit,
					'0', ROUND(PriceLimit, 0),	--	Even .00					
					'D', ROUND(PriceLimit, 1),	--	Dime .10
					'T', ROUND(PriceLimit, -1),	--	Ten 10.00
					'5', ROUND(PriceLimit*20,0)/20,	--	Nickle .05
					'Q', ROUND(PriceLimit*4,0)/4,	--	Quarter .25	
					ROUND(PriceLimit, v_StdPrecision))--	Currency
		WHERE	M_PriceList_Version_ID=p_PriceList_Version_ID
		  AND EXISTS	(SELECT * FROM T_Selection s
						WHERE s.T_Selection_ID=p.M_Product_ID
			                      AND s.AD_PInstance_ID = PInstance_ID);
		Message := Message || ', @Updated@=' || SQL%ROWCOUNT;

		--	Fixed Price overwrite
		ResultStr := ResultStr || ',Fix';
		UPDATE	M_ProductPrice p
		  SET	PriceList = DECODE(dl.List_Base, 'F', dl.List_Fixed, PriceList), 
				PriceStd = DECODE(dl.Std_Base, 'F', dl.Std_Fixed, PriceStd),
				PriceLimit = DECODE(dl.Limit_Base, 'F', dl.Limit_Fixed, PriceLimit) 
		WHERE	M_PriceList_Version_ID=p_PriceList_Version_ID
		  AND EXISTS	(SELECT * FROM T_Selection s
						WHERE s.T_Selection_ID=p.M_Product_ID
			                       AND s.AD_PInstance_ID = PInstance_ID);

		--	Log Info
		INSERT INTO AD_PInstance_Log	(AD_PInstance_ID, Log_ID, P_ID, P_NUMBER, P_MSG)
		VALUES							(PInstance_ID, v_NextNo, null, dl.SeqNo, Message);
		--
		v_NextNo := v_NextNo + 1;
		Message := '';
	END LOOP;	--	For all DiscountLines

	--	Delete Temporary Selection
	DELETE FROM T_Selection WHERE AD_PInstance_ID = PInstance_ID;


<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE(Message);
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished');
	UPDATE	AD_PInstance
	SET Updated = SysDate,
		IsProcessing = 'N',
		Result = 1,					-- success
		ErrorMsg = Message
	WHERE	AD_PInstance_ID=PInstance_ID;
	COMMIT;
	RETURN;

EXCEPTION
	WHEN OTHERS THEN
		ResultStr := ResultStr || ':' || SQLERRM || ' ' || Message;
		DBMS_OUTPUT.PUT_LINE(ResultStr);
		UPDATE	AD_PInstance
		SET Updated = SysDate,
			IsProcessing = 'N',
			Result = 0,				-- failure
			ErrorMsg = ResultStr
		WHERE	AD_PInstance_ID=PInstance_ID;
		COMMIT;
		RETURN;

END M_PriceList_Create;
