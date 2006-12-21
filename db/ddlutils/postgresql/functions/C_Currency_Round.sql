CREATE OR REPLACE FUNCTION currencyround (
    IN NUMERIC, -- $1 amount
    IN INTEGER, -- $2 Currency_ID
    IN BOOLEAN  -- $3 Costing
) RETURNS NUMERIC AS
$$
  DECLARE
    precision INTEGER;
  BEGIN
    IF $1 IS NULL OR $2 IS NULL THEN
      RETURN $1;
    END IF;
    IF COALESCE($3,FALSE) THEN
      SELECT t.CostingPrecision
        INTO precision
        FROM C_Currency AS t
        WHERE C_Currency_ID = $2;
    ELSE
      SELECT t.stdprecision
        INTO precision
        FROM C_Currency AS t
        WHERE C_Currency_ID = $2;
    END IF; 
    RETURN ROUND($1, precision);
  END;
$$ LANGUAGE plpgsql;
    
