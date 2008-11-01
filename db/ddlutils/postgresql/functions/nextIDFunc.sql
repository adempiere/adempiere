CREATE OR REPLACE FUNCTION nextidfunc(
	p_AD_Sequence_ID 	IN 	INTEGER, 
	p_System 		IN 	VARCHAR,
	o_NextID		OUT	INTEGER
)
  RETURNS INTEGER AS $body$
BEGIN
    RETURN nextid(p_AD_Sequence_ID, p_System, o_NextID);
END;
$body$ LANGUAGE plpgsql;
