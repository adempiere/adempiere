--  Rename Table
RENAME SO_Charge TO C_Charge;
UPDATE AD_Table SET TableName='C_Charge' WHERE UPPER(TableName)='SO_CHARGE';
COMMIT;
