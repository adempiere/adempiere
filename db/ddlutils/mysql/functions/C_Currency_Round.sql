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
 * Title.........: Round amount for Target Currency
 * Description...: Round Amount using Costing or Standard Precision
 *		           Returns unmodified amount if currency not found
 * Test..........: SELECT currencyRound(currencyConvert(100,116,100,null,null),100,null) => 64.72
 *                 SELECT currencyRound(1234.56789, 299, 'Y')$$  => 1234.57
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
Drop function IF EXISTS currencyRound $$

-- ## Create statement
CREATE FUNCTION currencyRound(
	p_Amount    DECIMAL(38, 2),
	p_CurTo_ID  DECIMAL(38),
	p_Costing   VARCHAR(1)     -- Default 'N'
)
	RETURNS DECIMAL(38,2)
 	DETERMINISTIC
BEGIN
	DECLARE v_StdPrecision DECIMAL(10);
	DECLARE v_CostPrecision DECIMAL(10);
	
	-- Nothing to convert
	IF (p_Amount IS NULL OR p_CurTo_ID IS NULL) THEN
		RETURN p_Amount;
	END IF;

	-- Ger Precision
	SELECT MAX(StdPrecision), MAX(CostingPrecision)
	  INTO v_StdPrecision, v_CostPrecision
	FROM C_Currency
	  WHERE C_Currency_ID = p_CurTo_ID;
	-- Currency Not Found
	IF (v_StdPrecision IS NULL) THEN
		RETURN p_Amount;
	END IF;

	IF (p_Costing = 'Y') THEN
		RETURN ROUND (p_Amount, v_CostPrecision);
	END IF;

	RETURN ROUND (p_Amount, v_StdPrecision);
END$$
DELIMITER ;