CREATE OR REPLACE PROCEDURE DBA_Recompile
(
	p_PInstance_ID			IN NUMBER --	DEFAULT NULL
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_Recompile.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	 Recompile all User_Objects
 * Description:
 *
 * Author: Teo Sarca, www.arhipac.ro
 *	* Recompile materialized views too
 ************************************************************************/
AS
	--	Logistice
	v_Message						VARCHAR2(2000) := ' ';
	v_Result						NUMBER := 1;	-- 	0=failure
	--
	v_Buffer						VARCHAR2(2000);
	v_Line						VARCHAR(100);
	v_PrintInfo					CHAR(1) := 'N';	--	Diagnostic
	--
	CURSOR	Cur_Invalids IS
		SELECT	object_id, object_name, object_type
		FROM	user_objects
		WHERE	status <> 'VALID'
		  AND	object_type IN ('VIEW', 'PACKAGE', 'PACKAGE BODY', 'FUNCTION', 
								'MATERIALIZED VIEW', -- arhipac: teo_sarca
								'PROCEDURE', 'TRIGGER', 'JAVA CLASS')
		ORDER BY object_type, object_name;

	CURSOR	Cur_Valids (p_id NUMBER) IS
		SELECT	'FOUND'
		FROM	user_objects
		WHERE	status = 'VALID'
		  AND	object_id = p_id;

	--  failed compile
	TYPE invalid_tab IS TABLE OF Cur_Invalids%ROWTYPE INDEX BY BINARY_INTEGER;
	invalid_tab_rec invalid_tab;

	count_compiled	PLS_INTEGER;
	valid_text	VARCHAR2(5);
	exec_cursor	PLS_INTEGER := DBMS_SQL.OPEN_CURSOR;
	sql_statement	VARCHAR2(200);
	count_object	PLS_INTEGER := 0;
	v_err		BINARY_INTEGER;

BEGIN
	LOOP
		count_compiled := 0;
		FOR ci IN Cur_Invalids LOOP
			--  not unsuccessfuly compiled yet
			IF NOT invalid_tab_rec.EXISTS(ci.object_id) THEN
				IF (ci.object_type = 'JAVA CLASS') THEN
					sql_statement := 'ALTER JAVA CLASS "' || ci.object_name || '" RESOLVE';
				ELSIF (ci.object_type = 'PACKAGE BODY') THEN
					sql_statement := 'ALTER PACKAGE ' || ci.object_name || ' COMPILE BODY';
				ELSE
					sql_statement := 'ALTER ' || ci.object_type || ' ' || ci.object_name || ' COMPILE';
				END IF;
				--  compile
				BEGIN
					count_object := count_object + 1;
					DBMS_SQL.PARSE(exec_cursor, sql_statement, DBMS_SQL.NATIVE);
				EXCEPTION
					WHEN OTHERS THEN
						NULL;
				END;
				-- 
				OPEN Cur_Valids (ci.object_ID);
				FETCH Cur_Valids INTO valid_text;
				IF Cur_Valids%ROWCOUNT > 0 THEN
					IF (v_PrintInfo = 'Y') THEN
						DBMS_OUTPUT.PUT_LINE('OK: ' || ci.object_type || ' ' || ci.object_name);
					END IF;
					count_compiled := count_compiled + 1;
					CLOSE Cur_Valids;
					EXIT;
				ELSE
					IF (LENGTH(v_Message) < 1950) THEN
						v_Message := v_Message || ci.object_name || ' ';
					END IF;
					IF (v_PrintInfo = 'Y') THEN
						DBMS_OUTPUT.PUT_LINE('Error: ' || ci.object_type || ' ' || ci.object_name);
					END IF;
					--
					invalid_tab_rec(ci.object_id).object_name := ci.object_name;
					invalid_tab_rec(ci.object_id).object_type := ci.object_type;
					CLOSE Cur_Valids;
				END IF;
			END IF; -- not unsuccessfuly compiled yet
		END LOOP;	-- Cur_Invalids
		--  any other to be compiled
		IF count_compiled = 0 THEN
			EXIT;
		END IF;
	END LOOP;	-- outer loop

	DBMS_SQL.CLOSE_CURSOR(exec_cursor);
	--
	--	Print Message
	IF (LENGTH(v_Message) = 1) THEN
		v_Message := 'All valid';
		DBMS_OUTPUT.PUT_LINE(v_Message);
	ELSIF (LENGTH(v_Message) > 80) THEN
		v_Buffer := v_Message;
		DBMS_OUTPUT.PUT_LINE('>');
		WHILE (LENGTH(v_Buffer) > 0) LOOP
			v_Line := SUBSTR(v_Buffer, 1, 80);
			DBMS_OUTPUT.PUT_LINE(v_Line);
			v_Buffer := SUBSTR(v_Buffer, 81);
		END LOOP;
		DBMS_OUTPUT.PUT_LINE('<');		
		v_Result := 0;
		DBMS_OUTPUT.PUT_LINE('ERROR');
	ELSE
		DBMS_OUTPUT.PUT_LINE('>' || v_Message || '<');
		v_Result := 0;
		DBMS_OUTPUT.PUT_LINE('ERROR');
	END IF;

<<FINISH_PROCESS>>
	IF (p_PInstance_ID IS NOT NULL) THEN
		--  Update AD_PInstance
		UPDATE	AD_PInstance
		SET Updated = SysDate,
			IsProcessing = 'N',
			Result = v_Result,			-- 1=success
			ErrorMsg = v_Message
		WHERE	AD_PInstance_ID=p_PInstance_ID;
	END IF;
	COMMIT;
	RETURN;


EXCEPTION
	WHEN OTHERS THEN
		DBMS_OUTPUT.PUT_LINE(SQLERRM);
		IF DBMS_SQL.IS_OPEN(exec_cursor) THEN
			DBMS_SQL.CLOSE_CURSOR(exec_cursor);
		END IF;
		IF Cur_Valids%ISOPEN THEN
			CLOSE Cur_Valids;
		END IF;
END DBA_Recompile;
/
