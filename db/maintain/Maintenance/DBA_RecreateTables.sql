/**
 *	Create Script for Re-Creating Table
 *	- Drop Dependencies (so that deleting rows does not restrict / trigger deleting dependent tows)
 *	- Copy into temp table
 *	- Drop original table
 *	- Create new Table (not generated)
 *	- Insert data
 *	- Drop temp table
 */
CREATE TABLE TempOut (
	Line Number(10), Text VARCHAR(2000))
/
DECLARE
	p_TableName		VARCHAR2(200) := 'M_ProductionPlan';

	--	Dependency Info
	CURSOR CUR_Dep IS
		SELECT  dep.Table_Name, dep.Constraint_Name, DBA_ConstraintCmd(dep.Constraint_Name) AS Cmd
    	FROM    User_Constraints tab, User_Constraints dep
	    WHERE   tab.Constraint_Name=dep.R_Constraint_Name
    	    AND tab.Constraint_Type='P' AND dep.Constraint_Type='R'
			AND tab.Table_Name=UPPER(p_TableName);
	No					NUMBER(10) := 1;
	--	Table Column Info
	CURSOR CUR_Col IS
		SELECT 	COLUMN_NAME 
		FROM 	USER_TAB_COLUMNS 
		WHERE 	TABLE_NAME = UPPER(p_TableName)
		ORDER BY COLUMN_ID;
	v_Line			VARCHAR2(2000) := '';
BEGIN
	--	Dependency Info
	INSERT INTO TempOut VALUES (0, '-- 1 -- Dependencies');
	FOR d IN CUR_Dep LOOP
		--	Drop first
		INSERT INTO TempOut VALUES (No, 'ALTER TABLE ' || d.Table_Name 
			|| ' DROP CONSTRAINT ' || d.Constraint_Name || ';');
		--	Create then
		INSERT INTO TempOut VALUES (No+500, d.Cmd || ';');
		No := No + 1;
	END LOOP;

	/**
	 *	Create List of Columns
	 */
	v_Line := '';
	FOR c IN CUR_Col LOOP
		IF (LENGTH(v_Line) > 0) THEN
			v_Line := v_Line || ',' || InitCap(c.Column_Name);
	   	ELSE
			v_Line := v_Line || InitCap(c.Column_Name);
	   	END IF;
	END LOOP;

	/**
	 *	Output
	 */
	INSERT INTO TempOut VALUES (100, '-- 2 -- Create/Copy');
	--	Copy into temp table
	INSERT INTO TempOut VALUES (101, 'CREATE TABLE ' || p_TableName || '_TT AS SELECT * FROM ' 
		|| p_TableName || ';'); 
	INSERT INTO TempOut VALUES (102, 'SELECT COUNT(*) FROM ' || p_TableName || '_TT;');
	--	Drop original table
	INSERT INTO TempOut VALUES (103, 'DROP TABLE ' || p_TableName || ' CASCADE CONSTRAINTS;');
	--	Create new Table (not generated)
	INSERT INTO TempOut VALUES (150, '-- 3 -- New Table');
	INSERT INTO TempOut VALUES (151, '-- >> CREATE TABLE HERE <<');
	--	Insert data
	INSERT INTO TempOut VALUES (200, '-- 4 -- Copy');
	INSERT INTO TempOut VALUES (201, 'INSERT INTO ' || p_TableName || ' (' || v_Line || ')');
	INSERT INTO TempOut VALUES (202, 'SELECT ' || v_Line || ' FROM ' || p_TableName || '_TT;');
	INSERT INTO TempOut VALUES (203, 'SELECT COUNT(*) FROM ' || p_TableName || ';');
	INSERT INTO TempOut VALUES (300, '-- 5 -- Finish');
	--	Drop temp table
	INSERT INTO TempOut VALUES (999, '-- DROP TABLE ' || p_TableName || '_TT;');
END;
/
SELECT Text FROM TempOut ORDER BY Line
/
DROP TABLE TempOut
/
