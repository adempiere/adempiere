CREATE FUNCTION nextbusinessday(p_date timestamp with time zone, p_ad_client_id numeric) RETURNS timestamp with time zone
    AS $$
/**
*This file is part of Adempiere ERP Bazaar
*http://www.adempiere.org
*
*Copyright (C) 2007 Teo Sarca
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
*
* Converted to PostgreSQL by Tony Snook, 
* tspc@dodo.com.au 
*/
DECLARE
	v_nextDate	date := trunc(p_Date);
	v_offset	numeric	:= 0;
	v_Saturday	numeric	:= TO_CHAR(TO_DATE('2000-01-01', 'YYYY-MM-DD'), 'D');
	v_Sunday	numeric	:= (case when v_Saturday = 7 then 1 else v_Saturday + 1 end);
	v_isHoliday	boolean	:= true;
	nbd C_NonBusinessDay%ROWTYPE;
begin
	v_isHoliday := true;
	loop
		SELECT	CASE TO_CHAR(v_nextDate,'D')::numeric 
					WHEN v_Saturday THEN 2
					WHEN v_Sunday THEN 1
					ELSE 0
				END INTO v_offset;
		v_nextDate := v_nextDate + v_offset::integer;
		v_isHoliday := false;
		FOR nbd IN	SELECT * 
					FROM C_NonBusinessDay 
					WHERE AD_Client_ID=p_AD_Client_ID and IsActive ='Y' and Date1 >= v_nextDate
					ORDER BY Date1
		LOOP
			exit when v_nextDate <> trunc(nbd.Date1);
			v_nextDate := v_nextDate + 1;
			v_isHoliday := true;
		end loop;
		exit when v_isHoliday=false;
	end loop;
	--
	return v_nextDate::timestamp with time zone;
end;
$$
    LANGUAGE plpgsql;
