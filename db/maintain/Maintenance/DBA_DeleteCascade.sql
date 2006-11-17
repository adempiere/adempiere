/**
 *	*** DANGEROUS ***
 *
 *	Delete Cascade Rows in a standard table with optional FKs
 *		Key is in the form of MyTable_ID
 *
 *	Creates and executes Commands like
 *	--
 *	DELETE FK_Table_Name fk WHERE EXISTS 
 *		(SELECT * FROM p_TableName t 
 *		WHERE t.p_ColumnName=fk.FK_Column_Name AND p_WhereClause);
 *	DELETE p_TableName WHERE p_WhereClause;
 */
DECLARE
	--	The Table Name
	p_TableName		VARCHAR2(256) := 'AD_Window';
	p_ColumnName	VARCHAR2(256) := p_TableName || '_ID';
	--	The (fully qualified) where clause
	p_WhereClause	VARCHAR2(256) := 'IsActive=''N''';
	--	Execute directly
	p_exe			BOOLEAN := FALSE;
	---------------------------------------------------------------------------
	--	Command Buffer
	v_Cmd			VARCHAR(2000);
	--	Dependent Tables
	CURSOR CUR_Dep IS
		SELECT Table_Name, Column_Name FROM USER_Cons_Columns
		WHERE Constraint_Name IN
			(SELECT  dep.Constraint_Name --dep.Table_Name, 
    		FROM    User_Constraints tab, User_Constraints dep
		    WHERE   tab.Constraint_Name=dep.R_Constraint_Name
    		    AND tab.Constraint_Type='P' AND dep.Constraint_Type='R'
				AND tab.Table_Name=UPPER(p_TableName));
	--
BEGIN
	DBMS_OUTPUT.PUT_LINE('-- Deleting FK references to ' || p_TableName || ' with ' || p_WhereClause);
	--
	v_Cmd := 'SAVEPOINT DelFK';
	DBMS_OUTPUT.PUT_LINE(v_Cmd);	
	IF (p_exe) THEN
		EXECUTE IMMEDIATE (v_Cmd);
	END IF;
	--
	FOR d IN CUR_Dep LOOP
		v_Cmd := 'DELETE ' || d.Table_Name 
			|| ' fk WHERE EXISTS (SELECT * FROM ' || p_TableName
			|| ' t WHERE t.' || p_ColumnName || '=fk.' || d.Column_Name || ' AND '
			|| p_WhereClause || ')';
		DBMS_OUTPUT.PUT_LINE(v_Cmd);
		IF (p_exe) THEN
			EXECUTE IMMEDIATE (v_Cmd);
			DBMS_OUTPUT.PUT_LINE(' .. deleted: ' || SQL%ROWCOUNT);
		END IF;
	END LOOP;
	--	The Table itself
	v_Cmd := 'DELETE ' || p_TableName || ' WHERE ' || p_WhereClause;
	DBMS_OUTPUT.PUT_LINE(v_Cmd);
	IF (p_exe) THEN
		EXECUTE IMMEDIATE (v_Cmd);
		DBMS_OUTPUT.PUT_LINE(' .. deleted: ' || SQL%ROWCOUNT);
	END IF;
END;
/
