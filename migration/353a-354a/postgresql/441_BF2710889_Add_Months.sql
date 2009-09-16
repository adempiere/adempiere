CREATE OR REPLACE FUNCTION add_months (in datetime timestamptz, in months numeric) RETURNS date AS
$BODY$
declare duration varchar;
BEGIN
	if datetime is null or months is null then
		return null;
	end if;
	duration = months || ' month';	 
	return cast(datetime + cast(duration as interval) as date);
END;
$BODY$
LANGUAGE 'plpgsql'
;
