-- Fixed wrong reference id for the entitytype column in the 020 script.

UPDATE AD_Column
SET AD_Reference_ID = 18,
AD_Reference_Value_ID = 389
WHERE AD_Column_ID = 53263;

COMMIT;

