CREATE OR REPLACE FUNCTION nextidfunc(
	p_AD_Sequence_ID 	IN 	INTEGER, 
	p_System 		IN 	VARCHAR
)
  RETURNS INTEGER AS $body$
DECLARE
          o_NextIDFunc INTEGER;
	  dummy INTEGER;
BEGIN
    o_NextIDFunc := nextid(p_AD_Sequence_ID, p_System);
    RETURN o_NextIDFunc;
END;
$body$ LANGUAGE plpgsql;
