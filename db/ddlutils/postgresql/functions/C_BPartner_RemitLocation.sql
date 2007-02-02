/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *
 *Copyright (C) 2007 Gavin Dunse
 *Copyright (C) 1999-2006 ComPiere, inc
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.of
 */

/*
 * Return the first RemitTo C_Location_ID of a Business Partner
 */

CREATE OR REPLACE FUNCTION bpartnerRemitLocation (
  IN NUMERIC -- $1 C_BPartner_ID
) RETURNS NUMERIC AS
$$
  DECLARE
    clocationid	NUMERIC; 
  BEGIN
    SELECT	t.C_Location_ID
    INTO	clocationid
    FROM	C_BPartner_Location
    AS		t
    WHERE	t.C_BPartner_ID=$1
    AND		t.C_Location_ID IS NOT NULL
    ORDER BY	t.IsRemitTo DESC;
    /* Limit not needed as first row is only returned - LIMIT	1; */
    IF FOUND THEN
      RETURN	clocationid;
    ELSE
      RETURN -1;
      /* RAISE EXCEPTION 'Some error'*/
      /* RETURN NULL */
    END IF;
  END;
$$ LANGUAGE plpgsql;
