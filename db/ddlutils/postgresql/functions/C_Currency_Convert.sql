   
CREATE OR REPLACE FUNCTION currencyconvert(
      IN NUMERIC, -- $1 amount to convert
      IN INTEGER, -- $2 from currency id
      IN INTEGER, -- $3 to currency id
      IN TIMESTAMP WITH TIME ZONE, -- $4 conversion date
      IN INTEGER, -- $5 conversion type id
      IN INTEGER, -- $6 client id
      IN INTEGER  -- $7 org id
) RETURNS numeric AS
$$
  DECLARE
    rate  NUMERIC;
  BEGIN
    IF $1 IS NULL OR $2 IS NULL OR $3 IS NULL THEN
      RETURN NULL;
    END IF;
    IF $1 = 0 OR $2 = $3 THEN
      RETURN $1;
    END IF;
    rate := currencyrate($2,$3,$4,$5,$6,$7);
    IF rate IS NULL THEN
      RETURN NULL;
    END IF;
    RETURN currencyround((rate * $1), $3, FALSE);
  END;
$$ LANGUAGE plpgsql;

