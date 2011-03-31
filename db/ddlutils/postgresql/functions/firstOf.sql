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

CREATE OR REPLACE FUNCTION firstOf (
IN TIMESTAMP WITH TIME ZONE, -- $1 date
IN VARCHAR -- $2 part of date
) RETURNS DATE AS
$$
DECLARE
datepart VARCHAR;
datetime TIMESTAMP WITH TIME ZONE;
offsetdays INTEGER;
BEGIN
	datepart = $2;
	offsetdays = 0;
	IF $2 IN ('') THEN
		datepart = 'millennium';
	ELSEIF $2 IN ('') THEN
		datepart = 'century';
	ELSEIF $2 IN ('') THEN
		datepart = 'decade';
	ELSEIF $2 IN ('IYYY','IY','I') THEN
		datepart = 'year';
	ELSEIF $2 IN ('SYYYY','YYYY','YEAR','SYEAR','YYY','YY','Y') THEN
		datepart = 'year';
	ELSEIF $2 IN ('Q') THEN
		datepart = 'quarter';
	ELSEIF $2 IN ('MONTH','MON','MM','RM') THEN
		datepart = 'month';
	ELSEIF $2 IN ('IW') THEN
		datepart = 'week';
	ELSEIF $2 IN ('W') THEN
		datepart = 'week';
	ELSEIF $2 IN ('DDD','DD','J') THEN
		datepart = 'day';
	ELSEIF $2 IN ('DAY','DY','D') THEN
		datepart = 'week';
		-- move to sunday to make it compatible with oracle and SQLJ
		offsetdays = -1;
	ELSEIF $2 IN ('HH','HH12','HH24') THEN
		datepart = 'hour';
	ELSEIF $2 IN ('MI') THEN
		datepart = 'minute';
	ELSEIF $2 IN ('') THEN
		datepart = 'second';
	ELSEIF $2 IN ('') THEN
		datepart = 'milliseconds';
	ELSEIF $2 IN ('') THEN
		datepart = 'microseconds';
	END IF;
	datetime = date_trunc(datepart, $1); 
RETURN cast(datetime as date) + offsetdays;
END;
$$ LANGUAGE plpgsql;
