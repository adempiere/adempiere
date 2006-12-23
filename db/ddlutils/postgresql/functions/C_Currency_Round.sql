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

CREATE OR REPLACE FUNCTION currencyround (
    IN NUMERIC, -- $1 amount
    IN INTEGER, -- $2 Currency_ID
    IN BOOLEAN  -- $3 Costing
) RETURNS NUMERIC AS
$$
  DECLARE
    precision INTEGER;
  BEGIN
    IF $1 IS NULL OR $2 IS NULL THEN
      RETURN $1;
    END IF;
    IF COALESCE($3,FALSE) THEN
      SELECT t.CostingPrecision
        INTO precision
        FROM C_Currency AS t
        WHERE C_Currency_ID = $2;
    ELSE
      SELECT t.stdprecision
        INTO precision
        FROM C_Currency AS t
        WHERE C_Currency_ID = $2;
    END IF; 
    RETURN ROUND($1, precision);
  END;
$$ LANGUAGE plpgsql;
    
