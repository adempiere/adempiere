/** Change Numeric for Integer   */
CREATE OR REPLACE FUNCTION adempiere.ID (record NUMERIC) 
RETURNS INTEGER AS $$
DECLARE 
ID integer := 0;
BEGIN
    ID := CAST(record AS INTEGER);
    --RAISE NOTICE 'Quantity here is %', ID;
    RETURN ID;
END;
$$ LANGUAGE plpgsql VOLATILE;
  
CREATE OR REPLACE FUNCTION adempiere.getdate()
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN now();
END;
$$ LANGUAGE plpgsql VOLATILE;

/**TIMESTAMP WITH TIME ZONE    **/
CREATE OR REPLACE FUNCTION adempiere.addDays (day TIMESTAMP WITH TIME ZONE, days DECIMAL)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.addDays(day,adempiere.ID(days));
END;
$$ LANGUAGE plpgsql VOLATILE;


CREATE OR REPLACE FUNCTION adempiere.subtractdays (day TIMESTAMP WITH TIME ZONE, days DECIMAL)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.addDays(day,adempiere.ID(days * -1));
END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE FUNCTION adempiere.addDays (day TIMESTAMP WITH TIME ZONE, days INTEGER)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.addDays(day,Idays);
END;
$$ LANGUAGE plpgsql VOLATILE;


CREATE OR REPLACE FUNCTION adempiere.subtractdays (day TIMESTAMP WITH TIME ZONE, days INTEGER)
RETURNS TIMESTAMP WITH TIME ZONE AS $$
BEGIN
    RETURN adempiere.addDays(day,days * -1);
END;
$$ LANGUAGE plpgsql VOLATILE;

DROP OPERATOR adempiere.+ (timestamptz, INTEGER);
CREATE OPERATOR adempiere.+ ( PROCEDURE = adempiere.adddays,
LEFTARG = TIMESTAMPTZ, RIGHTARG = INTEGER,
COMMUTATOR = +);

DROP OPERATOR adempiere.- (timestamptz, INTEGER);
CREATE OPERATOR adempiere.- ( PROCEDURE = adempiere. subtractdays,
LEFTARG = TIMESTAMPTZ, RIGHTARG = INTEGER,
COMMUTATOR = -);
