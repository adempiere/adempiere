/*
*This file is part of Adempiere ERP Bazaar
*http://www.adempiere.org
*
*Copyright (C) 2007 Low Heng Sin
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
CREATE OR REPLACE FUNCTION trunc(datetime TIMESTAMP WITH TIME ZONE)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
	RETURN CAST(datetime AS DATE);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION trunc(datetime TIMESTAMP WITH TIME ZONE, format varchar)
RETURNS DATE AS $$
BEGIN
	IF format = 'Q' THEN
		RETURN CAST(DATE_Trunc('quarter',datetime) as DATE);
	ELSIF format = 'Y' or format = 'YEAR' THEN
		RETURN CAST(DATE_Trunc('year',datetime) as DATE);
	ELSIF format = 'MM' or format = 'MONTH' THEN
		RETURN CAST(DATE_Trunc('month',datetime) as DATE);
	ELSIF format = 'DD' THEN
		RETURN CAST(DATE_Trunc('day',datetime) as DATE);
	ELSIF format = 'DY' THEN
		RETURN CAST(DATE_Trunc('day',datetime) as DATE);
	ELSE
		RETURN CAST(datetime AS DATE);
	END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION trunc(i INTERVAL)
RETURNS INTEGER AS $$
BEGIN
	RETURN EXTRACT(DAY FROM i);
END;
$$ LANGUAGE plpgsql;

-- Function: trunc(interval)

-- DROP FUNCTION trunc(interval);

CREATE OR REPLACE FUNCTION trunc(i interval)
  RETURNS integer AS
$BODY$
BEGIN
	RETURN EXTRACT(DAY FROM i);
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION trunc(interval) OWNER TO adempiere;


-- Function: trunc(timestamp with time zone)

-- DROP FUNCTION trunc(timestamp with time zone);

CREATE OR REPLACE FUNCTION trunc(datetime timestamp with time zone)
  RETURNS timestamp with time zone AS
$BODY$
BEGIN
	RETURN CAST(datetime AS DATE);
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION trunc(timestamp with time zone) OWNER TO adempiere;

