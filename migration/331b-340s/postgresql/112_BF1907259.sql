-- [ 1907259 ] Function charat wrong in 331b

CREATE OR REPLACE FUNCTION charat(character varying, integer) RETURNS character varying
    AS $_$
 BEGIN
 RETURN SUBSTR($1, $2, 1);
 END;
$_$
    LANGUAGE plpgsql;