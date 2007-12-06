CREATE OR REPLACE FUNCTION "adempiere"."subtractdays" (in inter interval, in days numeric) RETURNS integer AS
$BODY$
BEGIN
RETURN ( EXTRACT( EPOCH FROM ( inter ) ) / 86400 ) - days;
END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OPERATOR - (
PROCEDURE = subtractdays,
LEFTARG = interval,
RIGHTARG = numeric,
COMMUTATOR = -
);


CREATE OR REPLACE FUNCTION "adempiere"."adddays" (in inter interval, in days numeric) RETURNS integer AS
$BODY$
BEGIN
RETURN ( EXTRACT( EPOCH FROM ( inter ) ) / 86400 ) + days;
END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OPERATOR + (
PROCEDURE = adddays,
LEFTARG = interval,
RIGHTARG = numeric,
COMMUTATOR = -
);

ALTER OPERATOR adempiere.+ (interval, numeric) OWNER TO adempiere;