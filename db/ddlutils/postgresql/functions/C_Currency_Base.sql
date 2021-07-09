create or replace FUNCTION currencyBase
(
	p_Amount	NUMERIC,
	p_CurFrom_ID	NUMERIC,
	p_ConvDate	timestamp with time zone,
	p_Client_ID	NUMERIC,
	p_Org_ID	NUMERIC
)
RETURNS numeric AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * 
 ***
 * Title:	Convert Amount to Base Currency of Client
 * Description:
 *		Get CurrencyTo from Client
 *		Returns NULL, if conversion not found
 *		Standard Rounding
 * Test:
 *		SELECT currencyBase(100,116,null,11,null) FROM AD_System; => 64.72
 ************************************************************************/
BEGIN
	RETURN currencyBase(p_Amount, p_CurFrom_ID, p_ConvDate, null, p_Client_ID, p_Org_ID);
END;

$body$ LANGUAGE plpgsql;