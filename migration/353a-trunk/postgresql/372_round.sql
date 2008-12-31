CREATE OR REPLACE FUNCTION round (
 IN NUMERIC, -- $1 numeric
 IN NUMERIC -- $2 numeric
) RETURNS NUMERIC AS
$$
 BEGIN
	RETURN ROUND($1, cast($2 as integer));
 END;
$$ LANGUAGE plpgsql;
