--  Rename Column
--  a) Add new Column to Table & copy data
ALTER TABLE C_Charge ADD C_Charge_ID Number(10);
UPDATE C_Charge SET C_Charge_ID = SO_Charge_ID;
--  b) Change in Dictionary
UPDATE AD_Element SET ColumnName='C_Charge_ID' WHERE UPPER(ColumnName)='SO_CHARGE_ID';
UPDATE AD_Column SET ColumnName='C_Charge_ID' WHERE UPPER(ColumnName)='SO_CHARGE_ID';
Commit;

