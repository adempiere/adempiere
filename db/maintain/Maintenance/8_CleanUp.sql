/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 8_CleanUp.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	 Clean up / delete all non table objects
 * Description:
 *		- Functions
 *		- Trigger
 *		- Procedures
 *		- Packages
 *		- Views
 ************************************************************************/

DECLARE
	CURSOR CUR_Func IS
		SELECT 	Object_Name		FROM 	USER_Objects	WHERE 	Object_Type='FUNCTION';
	CURSOR CUR_Trg IS
		SELECT 	Trigger_Name	FROM 	USER_Triggers;
	CURSOR CUR_Proc IS
		SELECT 	Object_Name		FROM 	USER_Procedures	WHERE 	Procedure_Name IS NULL;
	CURSOR CUR_Pack IS
		SELECT 	Object_Name		FROM 	USER_Procedures	WHERE 	Procedure_Name IS NOT NULL;
	CURSOR CUR_View IS
		SELECT 	View_Name		FROM 	USER_Views;

  	v_cmd			VARCHAR2(256);

BEGIN
	--	Functions
	FOR f IN CUR_Func LOOP
		v_cmd := 'DROP FUNCTION ' || f.Object_Name;
		BEGIN
			DBMS_OUTPUT.PUT_LINE (f.Object_Name);
			EXECUTE IMMEDIATE v_Cmd;
	   	EXCEPTION
			WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE ('** Error ** ' || v_cmd);
	   	END;
	END LOOP;

	--	Trigger
	FOR t IN CUR_Trg LOOP
		v_cmd := 'DROP TRIGGER ' || t.Trigger_Name;
		BEGIN
			DBMS_OUTPUT.PUT_LINE (t.Trigger_Name);
			EXECUTE IMMEDIATE v_Cmd;
	   	EXCEPTION
			WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE ('** Error ** ' || v_cmd);
	   	END;
	END LOOP;

	--	Function/Procedures
	FOR t IN CUR_Proc LOOP
		v_cmd := 'DROP PROCEDURE ' || t.Object_Name;
		BEGIN
			DBMS_OUTPUT.PUT_LINE (t.Object_Name);
			EXECUTE IMMEDIATE v_Cmd;
	   	EXCEPTION
			WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE ('** Error ** ' || v_cmd);
	   	END;
	END LOOP;

	--	Package
	FOR t IN CUR_Pack LOOP
		v_cmd := 'DROP PACKAGE ' || t.Object_Name;
		BEGIN
			DBMS_OUTPUT.PUT_LINE (t.Object_Name);
			EXECUTE IMMEDIATE v_Cmd;
	   	EXCEPTION
			WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE ('** Error ** ' || v_cmd);
	   	END;
	END LOOP;

 	--	Views
	FOR t IN CUR_View LOOP
		v_cmd := 'DROP VIEW ' || t.View_Name;
		BEGIN
			DBMS_OUTPUT.PUT_LINE (t.View_Name);
			EXECUTE IMMEDIATE v_Cmd;
	   	EXCEPTION
			WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE ('** Error ** ' || v_cmd);
	   	END;
	END LOOP;

END;
/

