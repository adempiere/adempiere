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
 
CREATE OR REPLACE FUNCTION currencyconvert(
      IN NUMERIC, -- $1 amount to convert
      IN INTEGER, -- $2 from currency id
      IN INTEGER, -- $3 to currency id
      IN TIMESTAMP WITH TIME ZONE, -- $4 conversion date
      IN INTEGER, -- $5 conversion type id
      IN INTEGER, -- $6 client id
      IN INTEGER  -- $7 org id
) RETURNS numeric AS
$$
  DECLARE
    rate  NUMERIC;
  BEGIN
    IF $1 IS NULL OR $2 IS NULL OR $3 IS NULL THEN
      RETURN NULL;
    END IF;
    IF $1 = 0 OR $2 = $3 THEN
      RETURN $1;
    END IF;
    rate := currencyrate($2,$3,$4,$5,$6,$7);
    IF rate IS NULL THEN
      RETURN NULL;
    END IF;
    RETURN currencyround((rate * $1), $3, FALSE);
  END;
$$ LANGUAGE plpgsql;

