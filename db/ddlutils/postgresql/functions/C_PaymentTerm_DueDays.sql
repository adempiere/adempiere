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

CREATE OR REPLACE FUNCTION paymenttermduedays(
    IN INTEGER, -- $1 payment term id
    IN TIMESTAMP WITH TIME ZONE, -- $2 document date
    IN TIMESTAMP WITH TIME ZONE -- $3 paydate
) RETURNS INTEGER AS 
$$
  DECLARE
    due_date      TIMESTAMP WITH TIME ZONE;
  BEGIN
    due_date := paymenttermduedate($1,$2);

    IF due_date IS NULL  THEN
      RETURN 0;
    END IF;

    RETURN CAST(EXTRACT(DAY FROM (due_date - $3)) AS INTEGER);
  END;
$$ LANGUAGE plpgsql;
