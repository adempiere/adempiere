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

CREATE OR REPLACE FUNCTION acctBalance(
    IN INTEGER, -- $1 account id
    IN NUMERIC, -- $2 amount debit
    IN NUMERIC  -- $3 amount credit
) RETURNS NUMERIC AS 
$$
  DECLARE
    accType CHAR(1);
    accSign CHAR(1);
  BEGIN
    IF COALESCE($1, 0) != 0 THEN
      SELECT t.AccountType, t.AccountSign
        INTO accType, accSign
        FROM C_ElementValue AS t  WHERE t.C_ElementValue_ID = $1;
      IF accSign = "N" AND accType NOT IN ("A", "E") THEN 
          RETURN (COALESCE($3, 0) - COALESCE($2, 0));
      END IF;
    END IF;
  
    RETURN (COALESCE($2, 0) - COALESCE($3, 0));
  END;
$$ LANGUAGE plpgsql;
