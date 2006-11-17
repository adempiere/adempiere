/**
 *	Missing Translations
 */
DECLARE
	CURSOR CUR_Trl IS
		SELECT	TableName 
		FROM	AD_Table 
		WHERE	TableName like '%Trl'
		ORDER BY 1;
	v_BaseName		VARCHAR2(60);
	v_Cmd			VARCHAR2(256);
	v_NoBase		NUMBER;
	v_NoTrl			NUMBER;
BEGIN
	FOR t IN CUR_Trl LOOP
		v_BaseName := SUBSTR(t.TableName, 1, LENGTH(t.TableName) - 4);
		--
		v_Cmd := 'SELECT COUNT(*) FROM ' || t.TableName;
		EXECUTE IMMEDIATE v_Cmd INTO v_NoTrl;
		v_Cmd := 'SELECT COUNT(*) FROM ' || v_BaseName;
		EXECUTE IMMEDIATE v_Cmd INTO v_NoBase;

		IF (v_NoBase <> v_NoTrl) THEN
			DBMS_OUTPUT.PUT_LINE(t.TableName || '=' || v_NoTrl 
				|| '  <>  ' ||	v_BaseName || '=' || v_NoBase);
		END IF;
		--
		SELECT	COUNT(*) 
		  INTO	v_NoBase
		FROM	USER_Triggers 
		WHERE	Trigger_Name LIKE UPPER(v_BaseName || '%');
		IF (v_NoBase = 0) THEN
			DBMS_OUTPUT.PUT_LINE('No Trigger for ' || v_BaseName);
		END IF;
	END LOOP;
END;
