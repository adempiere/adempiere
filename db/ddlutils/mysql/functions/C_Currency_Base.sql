 /**********************************************************************
 * This file is part of ADempiere Business Suite                       *
 * http://www.adempiere.org                                            *
 *                                                                     *
 * Copyright (C) Trifon Trifonov.                                      *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
 *                                                                     *
 ***********************************************************************
 * 
 * Title.........: Convert Amount to Base Currency of Client
 * Description...: Get CurrencyTo from Client
 *                 Returns NULL, if conversion not found
 *                 Standard Rounding
 * Test..........: SELECT currencyBase(100, 116, now(), 11, null)   => 64.7200000000
 *                 SELECT currencyBase(100, 116, now(), null, null) => NULL
 *                 SELECT currencyBase(100, 116, null, null, null)  => NULL
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop
Drop function IF EXISTS currencyBase;

DELIMITER $$
-- ## Create
CREATE FUNCTION currencyBase(
	p_Amount        DECIMAL(20, 10),
	p_CurFrom_ID    DECIMAL(10, 0),
	p_ConvDate      DATE,
	p_Client_ID     DECIMAL(10, 0),
	p_Org_ID        DECIMAL(10, 0)
)
	RETURNS DECIMAL(20, 10)
 	DETERMINISTIC
BEGIN
    DECLARE v_CurTo_ID DECIMAL(10, 0);

	-- Get Currency
	SELECT MAX(ac.C_Currency_ID)
	  INTO v_CurTo_ID
	FROM AD_ClientInfo ci, C_AcctSchema ac
	WHERE ci.C_AcctSchema1_ID=ac.C_AcctSchema_ID
	  AND ci.AD_Client_ID=p_Client_ID;
	-- Same as Currency_Conversion - if currency/rate not found - return 0
	IF (v_CurTo_ID IS NULL) THEN
		RETURN NULL;
	END IF;
	-- Same currency
	IF (p_CurFrom_ID = v_CurTo_ID) THEN
		RETURN p_Amount;
	END IF;

	RETURN currencyConvert(p_Amount, p_CurFrom_ID, v_CurTo_ID, p_ConvDate, null, p_Client_ID, p_Org_ID);
END$$
DELIMITER ;