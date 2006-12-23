/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *
 *Copyright (C) 2006 Timo Kontro
 *Copyright (C) 1999-2006 ComPiere, inc
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.of 
 */

SET search_path = adempiere, pg_catalog;

CREATE OR REPLACE FUNCTION currencybase(
      IN NUMERIC, -- $1 p_Amount
      IN INTEGER, -- $2 p_C_CurrencyFrom_ID
      IN TIMESTAMP WITH TIME ZONE, -- $3 p_ConversionDate
      IN INTEGER, -- $4 p_AD_Client_ID
      IN INTEGER -- $5 p_AD_Org_ID
) RETURNS NUMERIC
AS $$
  DECLARE
    currency_to   INTEGER;
  BEGIN
    IF $1 IS NULL OR $2 IS NULL THEN
      RETURN NULL;
    END IF;
    
    IF $1 = 0 THEN 
      RETURN 0;
    END IF;
    /*Finds out org's default currency*/    
    SELECT ac.C_Currency_ID 
      INTO currency_to 
      FROM AD_ClientInfo AS ci
      INNER JOIN C_AcctSchema AS ac 
      ON (ci.C_AcctSchema1_ID = ac.C_AcctSchema_ID)
      WHERE ci.AD_Client_ID = $4;
    IF currency_to IS NULL THEN
      RETURN NULL;
    END IF;
    IF $2 = currency_to THEN
      RETURN $1;
    END IF;
    RETURN currencyconvert($1, $2, currency_to, $3,0,$4,$5); 
  END;
$$ LANGUAGE plpgsql;
