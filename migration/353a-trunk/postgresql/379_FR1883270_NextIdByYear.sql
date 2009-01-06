CREATE or REPLACE FUNCTION NextIDByYear
(
    p_AD_Sequence_ID    IN  numeric,
    p_IncrementNo       IN  numeric,
    p_CalendarYear      IN  varchar
)
RETURNS numeric AS $$
DECLARE
    o_NextID numeric;
BEGIN
   SELECT CurrentNext
		INTO o_NextID
	FROM ad_sequence_no
	WHERE AD_Sequence_ID=p_AD_Sequence_ID 
	AND CalendarYear = p_CalendarYear 
	FOR UPDATE OF ad_sequence_no;
	--
	UPDATE ad_sequence_no
	  SET CurrentNext = CurrentNext + p_IncrementNo
	WHERE AD_Sequence_ID=p_AD_Sequence_ID
	AND CalendarYear = p_CalendarYear;
	RETURN o_NextID;
END
$$ LANGUAGE plpgsql;

-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET IsActive='Y' WHERE AD_Field_ID=335
;
