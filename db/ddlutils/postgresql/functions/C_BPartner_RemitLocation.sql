CREATE OR REPLACE FUNCTION bpartnerRemitLocation(p_C_BPartner_ID C_BPartner.C_BPartner_ID%TYPE) 
RETURNS numeric AS $body$

DECLARE
	v_C_Location_ID	NUMERIC := NULL;
	l RECORD;

BEGIN
	FOR l IN 
		SELECT	IsRemitTo, C_Location_ID
		FROM	C_BPartner_Location
		WHERE	C_BPartner_ID=p_C_BPartner_ID
		ORDER BY IsRemitTo DESC
	LOOP
		IF (v_C_Location_ID IS NULL) THEN
			v_C_Location_ID := l.C_Location_ID;
		END IF;
	END LOOP;
	RETURN v_C_Location_ID;
	
END;

$body$ LANGUAGE plpgsql;
  
