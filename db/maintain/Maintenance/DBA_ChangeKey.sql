/**
 *	--	Change Key Information
 */
---------------------------------------------------------------------
/*-- Preparation */
DROP TABLE Temp_Constraints
/
CREATE TABLE Temp_Constraints AS 
SELECT Constraint_Name, Table_Name, Column_Name
FROM User_Cons_Columns WHERE 1=2
/
ALTER TABLE Temp_Constraints ADD Constraint_Type CHAR(1)
/
/**/
---------------------------------------------------------------------
DECLARE
	p_KeyName			VARCHAR2(50) := UPPER('GL_Category_ID');
	--
--	p_SetInfo			VARCHAR(60) := 'C_TaxCategory_ID+999900';
--	p_WhereInfo			VARCHAR(60) := '<105';
	p_SetInfo			VARCHAR(60) := '0';
	p_WhereInfo			VARCHAR(60) := '=105';
	p_execute			BOOLEAN := TRUE;

-----------------------------------------------------------------
	v_Cmd				VARCHAR2(2000);
	--	First PK then R(FK) records
	CURSOR CUR_ConstraintsPR IS	
		SELECT * FROM Temp_Constraints ORDER BY 4;
	--	First R(FK) then PKrecords
	CURSOR CUR_ConstraintsRP IS	
		SELECT * FROM Temp_Constraints ORDER BY 4 DESC;
BEGIN
--	Create Temp Table Content
	DELETE Temp_Constraints;
	--
	INSERT INTO Temp_Constraints
	SELECT Constraint_Name, Table_Name, Column_Name, NULL
	FROM User_Cons_Columns 
	WHERE Constraint_Name IN
		(SELECT Constraint_Name FROM USER_Constraints 
		WHERE R_Constraint_Name IN
			(SELECT Constraint_Name FROM USER_Cons_Columns
			WHERE Constraint_Name NOT LIKE 'SYS%' AND Column_Name = p_KeyName));
	--
	INSERT INTO Temp_Constraints
	SELECT Constraint_Name, Table_Name, Column_Name, NULL
	FROM User_Cons_Columns uc
	WHERE Column_Name = p_KeyName
		AND Constraint_Name NOT LIKE 'SYS%'
		AND NOT EXISTS 
			(SELECT * FROM Temp_Constraints t 
			WHERE t.Constraint_Name=uc.Constraint_Name);
	--
	UPDATE Temp_Constraints t
	  SET Constraint_Type = (SELECT Constraint_Type FROM User_Constraints c 
  							WHERE c.Constraint_Name=t.Constraint_Name);
	--
--	Disable Constraints FK first
	FOR c IN CUR_ConstraintsRP LOOP
		v_Cmd := 'ALTER TABLE ' || c.Table_Name || ' DISABLE ';
		IF (c.Constraint_Type = 'R') THEN
			v_Cmd := v_Cmd || 'CONSTRAINT ' || c.Constraint_Name;
		ELSE
			v_Cmd := v_Cmd || 'PRIMARY KEY';
		END IF;
		DBMS_OUTPUT.PUT_LINE (v_Cmd || ';');
		IF (p_execute) THEN
			EXECUTE IMMEDIATE (v_Cmd);
		END IF;
	END LOOP;
--	Update IDs - FK first
	FOR c IN CUR_ConstraintsRP LOOP
		v_Cmd := 'UPDATE ' || c.Table_Name 
			|| ' SET ' || c.Column_Name || '=' || p_SetInfo
			|| ' WHERE ' || c.Column_Name || p_WhereInfo;
		DBMS_OUTPUT.PUT_LINE (v_Cmd || ';');
		IF (p_execute) THEN
			EXECUTE IMMEDIATE (v_Cmd);
			DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT);
		END IF;
	END LOOP;
--  Enable Constraints - PK first
	FOR c IN CUR_ConstraintsPR LOOP
		v_Cmd := 'ALTER TABLE ' || c.Table_Name || ' ENABLE ';
		IF (c.Constraint_Type = 'R') THEN
			v_Cmd := v_Cmd || 'CONSTRAINT ' || c.Constraint_Name;
		ELSE
			v_Cmd := v_Cmd || 'PRIMARY KEY';
		END IF;
		DBMS_OUTPUT.PUT_LINE (v_Cmd || ';');
		IF (p_execute) THEN
			EXECUTE IMMEDIATE (v_Cmd);
		END IF;
	END LOOP;
END;
/
DROP TABLE Temp_Constraints
/

