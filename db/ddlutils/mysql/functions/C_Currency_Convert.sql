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
 * Title.........: Convert Amount (using IDs)
 * Description...: from CurrencyFrom_ID to CurrencyTo_ID
 *                 Returns NULL, if conversion not found
 *                 Standard Rounding
 * Test..........: SELECT currencyConvert(100, 116, 100, null, null, null, null)    => 64.7200000000
 *                 SELECT currencyConvert(100.10, 116, 100, null, null, null, null) => 64.7800000000
 * 
 *                 SELECT currencyConvert(100, 116, 100, null, null)              => ERROR 1318 (42000): Incorrect number of arguments for FUNCTION adempiere.currencyConvert; expected 7, got 5
 *                 SELECT currencyConvert(100, 116, 100)                          => ERROR 1318 (42000): Incorrect number of arguments for FUNCTION adempiere.currencyConvert; expected 7, got 3
 *                 SELECT currencyConvert(p_Amount, p_CurFrom_ID, v_CurTo_ID, p_ConvDate, p_ConversionType_ID, p_Client_ID, p_Org_ID);
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/

-- ## Drop
Drop function IF EXISTS currencyConvert;

DELIMITER $$
-- ## Create
CREATE FUNCTION currencyConvert(
	p_Amount            DECIMAL(20, 10),
	p_CurFrom_ID        DECIMAL(10, 0),
	p_CurTo_ID          DECIMAL(10, 0),
	p_ConvDate          DATE,
	p_ConversionType_ID DECIMAL(10, 0),
	p_Client_ID         DECIMAL(10, 0),
	p_Org_ID            DECIMAL(10, 0)
)
	RETURNS DECIMAL(20, 10)
 	DETERMINISTIC
BEGIN
	DECLARE v_Rate DECIMAL(20, 10);
    -- Return Amount
	IF (p_Amount = 0 OR p_CurFrom_ID = p_CurTo_ID) THEN
		RETURN p_Amount;
	END IF;
	-- Return NULL
	IF (p_Amount IS NULL OR p_CurFrom_ID IS NULL OR p_CurTo_ID IS NULL) THEN
		RETURN NULL;
	END IF;

	-- Get Rate
	SET v_Rate = currencyRate(p_CurFrom_ID, p_CurTo_ID, p_ConvDate, p_ConversionType_ID, p_Client_ID, p_Org_ID);
	IF (v_Rate IS NULL) THEN
		RETURN NULL;
	END IF;

	-- Standard Precision
	RETURN currencyRound(p_Amount * v_Rate, p_CurTo_ID, null);
END$$
DELIMITER ;