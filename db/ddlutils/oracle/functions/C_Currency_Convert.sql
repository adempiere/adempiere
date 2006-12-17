CREATE OR REPLACE FUNCTION currencyConvert
(
	p_Amount			IN	NUMBER,
	p_CurFrom_ID		IN	NUMBER,
	p_CurTo_ID		    IN	NUMBER,
	p_ConvDate		    IN	DATE,
	p_ConversionType_ID IN	NUMBER,
	p_Client_ID		    IN	NUMBER,
	p_Org_ID			IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Currency_Convert.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Convert Amount (using IDs)
 * Description:
 *		from CurrencyFrom_ID to CurrencyTo_ID
 *		Returns NULL, if conversion not found
 *		Standard Rounding
 * Test:
 *		SELECT C_Currency_Convert(100,116,100,null,null) FROM DUAL  => 64.72
 *		SELECT C_Currency_Convert(100,116,100) FROM DUAL            => 64.72
 ************************************************************************/
AS
	v_Rate				NUMBER;
BEGIN
	--	Return Amount
	IF (p_Amount = 0 OR p_CurFrom_ID = p_CurTo_ID) THEN
		RETURN p_Amount;
	END IF;
	--	Return NULL
	IF (p_Amount IS NULL OR p_CurFrom_ID IS NULL OR p_CurTo_ID IS NULL) THEN
		RETURN NULL;
	END IF;

	--	Get Rate
	v_Rate := currencyRate (p_CurFrom_ID, p_CurTo_ID, p_ConvDate, p_ConversionType_ID, p_Client_ID, p_Org_ID);
	IF (v_Rate IS NULL) THEN
		RETURN NULL;
	END IF;

	--	Standard Precision
	RETURN currencyRound(p_Amount * v_Rate, p_CurTo_ID, null);	
END currencyConvert;
/