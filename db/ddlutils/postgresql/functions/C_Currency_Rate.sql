CREATE OR REPLACE FUNCTION currencyrate(
    IN INTEGER, -- $1 currency from id
    IN INTEGER, -- $2 currency to id
    IN timestamp with time zone, -- $3 conversiondate
    IN INTEGER, -- $4 conversiontype id
    IN INTEGER, -- $5 client id
    IN INTEGER  -- $6 org id
) RETURNS NUMERIC AS 
$$
  DECLARE
    conversion_type_id  INTEGER;
    conversion_date     DATE;
    rate                NUMERIC;
  BEGIN
    IF $1 = $2 THEN 
      RETURN 1;
    END IF;
    conversion_type_id = COALESCE($4, 0);
    conversion_date = CAST($3 AS DATE);
    rate = NULL;
    IF conversion_type_id = 0 THEN
      SELECT C_ConversionType_ID
        INTO conversion_type_id 
        FROM C_ConversionType
        WHERE IsDefault = 'Y' AND AD_Client_ID IN (0, $5);
    END IF;
    SELECT t.MultiplyRate
      INTO rate
      FROM C_Conversion_Rate AS t
      WHERE t.C_Currency_ID = $1 
        AND t.C_Currency_To = $2
        AND date_trunc('day',t.ValidFrom) <= $3
        AND date_trunc('day',t.ValidTo) >= $3
        AND t.AD_Client_ID IN (0,$5)
        AND t.AD_Org_ID IN (0, $6)
        ORDER BY AD_Client_ID DESC, AD_Org_ID DESC, ValidFrom DESC
        LIMIT 1;
    return rate;
  END;
$$ LANGUAGE plpgsql;
