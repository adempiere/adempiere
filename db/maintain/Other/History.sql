DECLARE
	--	Get Tables
	CURSOR	Cur_Tables IS
		SELECT 	TableName
		FROM 	AD_Table
		WHERE	TableName LIKE 'C_Order';-- OR TableName LIKE 'C_Invoice%'; 
	--	Column Definition
	CURSOR	Cur_Columns	(TableName VARCHAR2) IS
		SELECT 	Column_Name || ' ' || Data_Type || 
			DECODE(Data_Type, 'VARCHAR2','('||Data_Length||')', 'CHAR','('||Data_Length||')', '') ||
			DECODE(Nullable, 'N',' NOT NULL, ', ' NULL, ') AS ColDef
		FROM 	User_Tab_Columns
		WHERE 	Table_Name = UPPER(TableName);
	--	Primary Columns
	CURSOR Cur_PK (TableName VARCHAR2) IS
		SELECT 	cc.Column_Name
		FROM 	USER_CONS_COLUMNS cc, USER_CONSTRAINTS c
		WHERE 	cc.Table_Name = UPPER(TableName)
		  AND 	c.Table_Name=cc.Table_Name
		  AND 	c.Constraint_Name=cc.Constraint_Name
		  AND 	c.Constraint_Type='P';
	--
	Command			VARCHAR(2000);
BEGIN
	FOR t IN Cur_Tables LOOP
		Command := 'CREATE TABLE H_' || t.TableName || ' (';
		FOR c IN Cur_Columns (t.TableName) LOOP
--			DBMS_OUTPUT.PUT_LINE(c.ColDef);
			Command := Command || c.ColDef;
		END LOOP;
		Command := Command || 'CONSTRAINT H_' || t.TableName || '_KEY PRIMARY KEY (';
		FOR pk IN Cur_PK (t.TableName) LOOP
			Command := Command || pk.Column_Name || ',';
		END LOOP;
		Command := Command || 'Updated) USING INDEX TABLESPACE INDX))';

		DBMS_OUTPUT.PUT_LINE(Command);
	END LOOP;	--	Cur_Table
END;

