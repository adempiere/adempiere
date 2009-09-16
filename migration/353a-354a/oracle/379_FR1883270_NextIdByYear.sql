CREATE or REPLACE PROCEDURE NextIDByYear
(
    p_AD_Sequence_ID    	IN  NUMBER,
    p_IncrementNo           IN  NUMBER,
    p_CalendarYear          IN  CHAR,
    o_NextID                OUT NUMBER
)
AS
BEGIN
   SELECT CurrentNext
		INTO o_NextID
	FROM AD_Sequence_No
	WHERE AD_Sequence_ID=p_AD_Sequence_ID 
	AND CalendarYear = p_CalendarYear 
	FOR UPDATE OF CurrentNext;
	--
	UPDATE AD_Sequence_No
	  SET CurrentNext = CurrentNext + p_IncrementNo
	WHERE AD_Sequence_ID=p_AD_Sequence_ID
	AND CalendarYear = p_CalendarYear;
EXCEPTION
    WHEN  OTHERS THEN
    	DBMS_OUTPUT.PUT_LINE(SQLERRM);
END NextIDByYear;
/

-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET IsActive='Y' WHERE AD_Field_ID=335
;
