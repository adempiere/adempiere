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

CREATE OR REPLACE FUNCTION paymenttermduedate(
    IN INTEGER, -- $1 payment term id
    IN TIMESTAMP WITH TIME ZONE -- $2 document date
) RETURNS TIMESTAMP WITH TIME ZONE AS 
$$
  DECLARE
    due_date      TIMESTAMP WITH TIME ZONE;
    fixed         BOOLEAN;
    monthOffset   INTEGER;
    monthCutOff    INTEGER;
    netDays       INTEGER;
    monthDay      INTEGER;
  BEGIN
    IF $1 IS NULL OR $2 IS NULL THEN
      RETURN 0;
    END IF;
    SELECT (t.isDueFixed = 'Y'), t.fixMonthOffset, t.fixMonthCutoff,
        t.netdays, t.FixMonthDay
      INTO fixed, monthOffset, monthCutOff, netDays, monthDay
      FROM C_PaymentTerm AS t WHERE t.C_PaymentTerm_ID = $1;
    IF fixed THEN
      --we check if montCutOff is bigger than number of days in month.
      IF monthCutOff > 28 THEN -- if smaller than days in february no need for further checks.
        -- montCutOff should not be greater than number of days in month.
        monthCutOff := LEAST(
          EXTRACT(DAY FROM (date_trunc('month', $2) + INTERVAL '1 month' - INTERVAL '1 day'))
          ,monthCutOff);
      END IF;
      IF monthCutOff < EXTRACT(DAY FROM $2) THEN
        monthOffset := COALESCE(monthOffset,0) + 1;
      END IF;
      due_date := date_trunc('month', $2) + (INTERVAL '1 month' * monthOffset);

      IF monthDay > 28 THEN
        --monthDay should not be greater than number of days in month.
        monthDay := LEAST(
          EXTRACT(DAY FROM (due_date + INTERVAL '1 month' - INTERVAL '1 days'))
          ,monthDay);
      END IF;
      due_date := due_date + INTERVAL '1 day' * (monthDay -1);
    ELSE
      due_date := $2 + (INTERVAL '1 day' * netDays);
    END IF;
    RETURN due_date;
  END;
$$ LANGUAGE plpgsql;
