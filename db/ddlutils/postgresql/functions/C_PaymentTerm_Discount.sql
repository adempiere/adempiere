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
 
CREATE OR REPLACE FUNCTION paymenttermdiscount
(
  NUMERIC, --$1 amount
  IN  INTEGER, --$2 Currency ID
	IN	INTEGER, --$3 Payment term id
	IN	TIMESTAMP WITH TIME ZONE, --$4 document date
  IN	TIMESTAMP WITH TIME ZONE --$5 payment date
)
RETURNS NUMERIC AS $$
  DECLARE
    discount      NUMERIC;
    discount1     NUMERIC;
    discount2     NUMERIC;
    discount1date DATE;
    discount2date DATE;
    add1date      NUMERIC;
    add2date      NUMERIC;
    isnextbd      BOOLEAN;
    payDate       DATE;
  BEGIN
    IF $1 IS NULL OR $2 IS NULL OR $4 IS NULL THEN
      RETURN 0;
    END IF;

    IF $5 IS NULL THEN 
      payDate := current_date;
    ELSE
      payDate := CAST($5 AS DATE);
    END IF;

    discount1date := CAST($4 AS Date);
    discount2date := discount1date;

    SELECT 
        (discount1date + t.discountdays + t.gracedays),
        (discount2date + t.discountdays2 + t.gracedays),
        t.discount,
        t.discount2,
        (t.isnextbusinessday = 'Y')
      INTO 
        discount1date, 
        discount2date,
        discount1,
        discount2,
        isnextbd
      FROM c_paymentterm AS t
      WHERE c_paymentterm_id=p_C_PaymentTerm_ID;

    --this checks only weekdays should check holidays too
    IF isnextbd THEN
      SELECT 
        CASE EXTRACT(DOW FROM discount1date) 
          WHEN 0 THEN (discount1date + 1) --sunday => monday
          WHEN 6 THEN (discount1date + 2) --saturday => monday
          ELSE discount1date
        END
      INTO discount1date;
        
      SELECT 
        CASE extract(DOW FROM discount2date) 
          WHEN 0 THEN (discount2date + 1)
          WHEN 6 THEN (discount2date + 2)
          ELSE discount2date
        END
      INTO discount2date;
    END IF;
   
    IF payDate < discount1date THEN --Would <= be better than = ?
      RETURN currencyround(((p_amount * discount1) / 100), $2, FALSE);
    ELSIF payDate < discount2date THEN
      RETURN currencyround(((p_amount * discount2) / 100), $2, FALSE);
    ELSE
      RETURN 0;
    END IF;
 END;
$$ LANGUAGE plpgsql;

