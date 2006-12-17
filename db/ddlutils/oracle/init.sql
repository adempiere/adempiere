/** Get Character at Position   */
CREATE OR REPLACE FUNCTION charAt
(
    p_string    VARCHAR2,
    p_pos       NUMBER
)
 	RETURN VARCHAR2
AS
BEGIN
    RETURN SUBSTR(p_string, p_pos, 1);
END;    
/
/** GetDate                     */
CREATE OR REPLACE FUNCTION getdate
 	RETURN DATE
AS
BEGIN
    RETURN SysDate;
END;    
/
/** First Of DD/DY/MM/Q         */
CREATE OR REPLACE FUNCTION firstOf
(
    p_date      DATE,
    p_datePart  VARCHAR2
)
 	RETURN DATE
AS
BEGIN
    RETURN TRUNC(p_date, p_datePart);
END;    
/
/** Add Number of Days      */
CREATE OR REPLACE FUNCTION addDays
(
    p_date      DATE,
    p_days      NUMBER
)
 	RETURN DATE
AS
BEGIN
    RETURN TRUNC(p_date) + p_days;
END;    
/
CREATE OR REPLACE FUNCTION daysBetween
(
    p_date1     DATE,
    p_date2     DATE
)
 	RETURN NUMBER
AS
BEGIN
    RETURN (TRUNC(p_date1) - TRUNC(p_date2));
END;
/
