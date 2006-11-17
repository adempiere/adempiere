/**
 *	Drop all Java Items  DO NOT RUN UNMODIFIED
 */
DECLARE
	Cmd			VARCHAR2(255);		--	DROP JAVA command

	CURSOR Cur_Java IS
    	SELECT 	*
		FROM 	User_Objects
		WHERE 	Object_Type LIKE 'JAVA%';

BEGIN
	DBMS_OUTPUT.PUT_LINE('Deleting Java objects:');
	FOR j IN Cur_Java LOOP
		IF (j.Object_Type = 'JAVA RESOURCE') THEN
			CMD := 'DROP JAVA RESOURCE "' || j.Object_Name || '"';
		ELSIF (j.Object_Type = 'JAVA SOURCE') THEN
			CMD := 'DROP JAVA SOURCE "' || j.Object_Name || '"';
		ELSE
			CMD := 'DROP JAVA CLASS "' || j.Object_Name || '"';
		END IF;
	--	DBMS_OUTPUT.PUT(CMD);
		BEGIN
			EXECUTE IMMEDIATE CMD;
	--		DBMS_OUTPUT.PUT_LINE('  ** ok');
			EXCEPTION
				WHEN OTHERS THEN
					DBMS_OUTPUT.PUT_LINE('  ** error **');		
	  	END;
	END LOOP;
	--	Delete remaining entries
--	DELETE JAVA$CLASS$MD5$Table;
	COMMIT;
END;
/
/**
 *
 */
DECLARE
	CURSOR Cur_Invalids IS
		SELECT Object_Name
		FROM USER_Objects
		WHERE Object_Type LIKE 'JAVA%' AND Status <> 'VALID'
		ORDER BY 1;
	ClassName				VARCHAR2(256);
BEGIN
	FOR i IN CUR_Invalids LOOP
		ClassName := DBMS_JAVA.LONGNAME(i.Object_Name);
		DBMS_OUTPUT.PUT_LINE(ClassName);
		DBMS_JAVA.DROPJAVA(ClassName);
	END LOOP;
END;
