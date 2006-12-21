CREATE OR REPLACE FUNCTION currencybase(
      IN NUMERIC, -- $1 p_Amount
      IN INTEGER, -- $2 p_C_CurrencyFrom_ID
      IN TIMESTAMP WITH TIME ZONE, -- $3 p_ConversionDate
      IN INTEGER, -- $4 p_AD_Client_ID
      IN INTEGER -- $5 p_AD_Org_ID
) RETURNS NUMERIC
AS $$
  DECLARE
    currency_to   INTEGER;
  BEGIN
    IF $1 IS NULL OR $2 IS NULL THEN
      RETURN NULL;
    END IF;
    
    IF $1 = 0 THEN 
      RETURN 0;
    END IF;
    /*Finds out org's default currency*/    
    SELECT ac.C_Currency_ID 
      INTO currency_to 
      FROM AD_ClientInfo AS ci
      INNER JOIN C_AcctSchema AS ac 
      ON (ci.C_AcctSchema1_ID = ac.C_AcctSchema_ID)
      WHERE ci.AD_Client_ID = $4;
    IF currency_to IS NULL THEN
      RETURN NULL;
    END IF;
    IF $2 = currency_to THEN
      RETURN $1;
    END IF;
    RETURN currencyconvert($1, $2, currency_to, $3,0,$4,$5); 
  END;
$$ LANGUAGE plpgsql;
