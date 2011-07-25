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
 * Title.........: Return Conversion Rate
 * Description...: Returns Returns NULL, if rate not found
 *                 C_Currency
 *                  USD = 100
 *                  EUR = 102
 *                  CAD = 116
 *                 C_ConversionType_ID
 *                  Spot       = 114
 *                  Average    = 200
 *                  Company    = 201
 *                  Period End = 115
 *                                     CAD, EUR
 * Test (Oracle) : SELECT currencyRate(116, 100, null, null, null, null) FROM DUAL;     => .647169 --> works in Oracle!
 *                 SELECT currencyRate(116, 100, null, null) FROM DUAL;     => .647169 --> do not work in Oracle!
 *                 SELECT currencyRate(116, 100) FROM DUAL;                 => .647169 --> do not work in Oracle!
 * 
 * Test (MySQL)  : SELECT currencyRate(116, 100, null, null, null, null)$$  => 0.6471690000 --> Works in MySQL
 *                 SELECT currencyRate(116, 100, now(), null, null, null)$$ => 0.6471690000
 *                 SELECT currencyRate(100, 102, now(), 114, 11, 0)$$       => 0.8006405124
 *                 SELECT currencyRate(116, 116, null, null, null, null)$$  => 1.0000000000 --> Data truncated for column 'v_ConvDate' at row 1
 * 
 *                 SELECT currencyRate(116, 100, null, null);     => ERROR 1318 (42000): Incorrect number of arguments for FUNCTION adempiere.currencyRate; expected 6, got 4
 *                 SELECT currencyRate(116, 100);                 => ERROR 1318 (42000): Incorrect number of arguments for FUNCTION adempiere.currencyRate; expected 6, got 2
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
Drop function IF EXISTS currencyRate $$

-- ## Create statement
CREATE FUNCTION currencyRate(
	p_CurFrom_ID        DECIMAL(10,0),
	p_CurTo_ID          DECIMAL(10,0),
	p_ConvDate          DATE,
	p_ConversionType_ID DECIMAL(10,0),
	p_Client_ID         DECIMAL(10,0),
	p_Org_ID            DECIMAL(10,0)  
)
	RETURNS DECIMAL(20,10)
 	DETERMINISTIC
BEGIN
    -- Currency From variables
	DECLARE cf_IsEuro           CHAR(1);
	DECLARE cf_IsEMUMember      CHAR(1);
	DECLARE cf_EMUEntryDate     DATE;
	DECLARE cf_EMURate          DECIMAL(20,10);
	-- Currency To variables
	DECLARE ct_IsEuro           CHAR(1);
	DECLARE ct_IsEMUMember      CHAR(1);
	DECLARE ct_EMUEntryDate     DATE;
	DECLARE ct_EMURate          DECIMAL(20,10);
	-- Triangle
	DECLARE v_CurrencyFrom      DECIMAL(10,0);
	DECLARE v_CurrencyTo        DECIMAL(10,0);
	DECLARE v_CurrencyEuro      DECIMAL(10,0);
	--
	DECLARE v_ConvDate          DATE DEFAULT now();
	DECLARE v_ConversionType_ID DECIMAL(10,0) DEFAULT 0.0;
	DECLARE v_Rate			    DECIMAL(20,10);

	-- No Conversion
	IF (p_CurFrom_ID = p_CurTo_ID) THEN
		RETURN 1.0;
	END IF;
	-- Default Date Parameter
	IF (p_ConvDate IS NOT NULL) THEN
		SET v_ConvDate = p_ConvDate;
	END IF;

	-- Read Default Conversion Type
	IF (p_ConversionType_ID IS NULL OR p_ConversionType_ID = 0) THEN
        BEGIN
            SELECT convType.C_ConversionType_ID INTO v_ConversionType_ID
            FROM (
                SELECT C_ConversionType_ID 
                FROM C_ConversionType 
                WHERE IsDefault='Y'
                    AND AD_Client_ID IN (0, p_Client_ID)
                ORDER BY AD_Client_ID DESC
            ) AS convType Limit 0, 1;
        END;
    ELSE
        SET v_ConversionType_ID = p_ConversionType_ID;
	END IF;

	-- Get Currency Info
	SELECT MAX(IsEuro), MAX(IsEMUMember), MAX(EMUEntryDate), MAX(EMURate)
	  INTO cf_IsEuro, cf_IsEMUMember, cf_EMUEntryDate, cf_EMURate
	FROM C_Currency
	  WHERE C_Currency_ID = p_CurFrom_ID;
	-- Not Found
	IF (cf_IsEuro IS NULL) THEN
		RETURN NULL;
	END IF;
	SELECT MAX(IsEuro), MAX(IsEMUMember), MAX(EMUEntryDate), MAX(EMURate)
	  INTO ct_IsEuro, ct_IsEMUMember, ct_EMUEntryDate, ct_EMURate
	FROM C_Currency
	 WHERE C_Currency_ID = p_CurTo_ID;
	-- Not Found
	IF (ct_IsEuro IS NULL) THEN
		RETURN NULL;
	END IF;

	-- Fixed - From Euro to EMU
	IF (cf_IsEuro = 'Y' AND ct_IsEMUMember ='Y' AND v_ConvDate >= ct_EMUEntryDate) THEN
		RETURN ct_EMURate;
	END IF;

	-- Fixed - From EMU to Euro
	IF (ct_IsEuro = 'Y' AND cf_IsEMUMember ='Y' AND v_ConvDate >= cf_EMUEntryDate) THEN
		RETURN 1 / cf_EMURate;
	END IF;

	-- Fixed - From EMU to EMU
	IF (cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'
			AND v_ConvDate >= cf_EMUEntryDate AND v_ConvDate >= ct_EMUEntryDate) THEN
		RETURN ct_EMURate / cf_EMURate;
	END IF;

	-- Flexible Rates
	SET v_CurrencyFrom = p_CurFrom_ID;
	SET v_CurrencyTo = p_CurTo_ID;
	
	-- if EMU Member involved, replace From/To Currency
	IF ((cf_isEMUMember = 'Y' AND v_ConvDate >= cf_EMUEntryDate)
	  OR (ct_isEMUMember = 'Y' AND v_ConvDate >= ct_EMUEntryDate)) THEN
		SELECT MAX(C_Currency_ID)
		  INTO v_CurrencyEuro
		FROM C_Currency
		WHERE IsEuro = 'Y';
		-- Conversion Rate not Found
		IF (v_CurrencyEuro IS NULL) THEN
			RETURN NULL;
		END IF;
		IF (cf_isEMUMember = 'Y' AND v_ConvDate >= cf_EMUEntryDate) THEN
			SET v_CurrencyFrom = v_CurrencyEuro;
		ELSE
			SET v_CurrencyTo = v_CurrencyEuro;
		END IF;
	END IF;
	
	-- Get Rate
	SELECT MultiplyRate INTO v_Rate
	FROM C_Conversion_Rate
	WHERE C_Currency_ID=v_CurrencyFrom AND C_Currency_ID_To=v_CurrencyTo
	  AND C_ConversionType_ID=v_ConversionType_ID
	  AND v_ConvDate BETWEEN ValidFrom AND ValidTo
	  AND AD_Client_ID IN (0, p_Client_ID) AND AD_Org_ID IN (0, p_Org_ID)
	ORDER BY AD_Client_ID DESC, AD_Org_ID DESC, ValidFrom DESC
	LIMIT 0, 1;
	-- Not found
	IF (v_Rate IS NULL) THEN
		RETURN NULL;
	END IF;

	-- Currency From was EMU
	IF (cf_isEMUMember = 'Y' AND v_ConvDate >= cf_EMUEntryDate) THEN
		RETURN v_Rate / cf_EMURate;
	END IF;

	-- Currency To was EMU
	IF (ct_isEMUMember = 'Y' AND v_ConvDate >= ct_EMUEntryDate) THEN
		RETURN v_Rate * ct_EMURate;
	END IF;

	RETURN v_Rate;
END$$
DELIMITER ;