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

CREATE OR REPLACE FUNCTION addDays(datetime TIMESTAMP WITH TIME ZONE, days Numeric)
RETURNS DATE AS $$
declare duration varchar;
BEGIN
	if datetime is null or days is null then
		return null;
	end if;
	duration = days || ' day';	 
	return cast(date_trunc('day',datetime) + cast(duration as interval) as date);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION subtractdays (day TIMESTAMP WITH TIME ZONE, days NUMERIC)
RETURNS DATE AS $$
BEGIN
    RETURN addDays(day,(days * -1));
END;
$$ LANGUAGE plpgsql;