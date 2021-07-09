CREATE OR REPLACE FUNCTION currencyBase
(
	p_Amount			IN	NUMBER,
	p_CurFrom_ID		IN	NUMBER,
	p_ConvDate			IN	DATE,
	p_Client_ID			IN	NUMBER,
	p_Org_ID			IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Base_Convert.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Convert Amount to Base Currency of Client
 * Description:
 *		Get CurrencyTo from Client
 *		Returns NULL, if conversion not found
 *		Standard Rounding
 * Test:
 *		SELECT C_Base_Convert(100,116,11,null) FROM DUAL => 64.72
 ************************************************************************/
AS
BEGIN
	RETURN currencyBase(p_Amount, p_CurFrom_ID, p_ConvDate, null, p_Client_ID, p_Org_ID);
END currencyBase;
/