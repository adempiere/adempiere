/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *
 *Copyright (C) 2006 Gavin Dunse
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

/** Get Character at Position */
SET search_path = adempiere, pg_catalog;

CREATE OR REPLACE FUNCTION charAt (
 IN VARCHAR, -- $1 the string
 IN INTEGER -- $2 the position
) RETURNS VARCHAR AS
$$
 BEGIN
 RETURN SUBSTR($1, $2, 1);
 END;
$$ LANGUAGE plpgsql;
