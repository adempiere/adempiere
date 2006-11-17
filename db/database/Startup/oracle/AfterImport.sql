/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AfterImport.sql,v 1.2 2006/05/28 22:52:07 jjanke Exp $
 ***
 * Title:	After Import
 * Description:	
 *	- General Info
 *	- Create Context
 *	- Check Imported User and list ivalid objects
 *	You can ignore import warnings as long as this does not 
 *	return error messages or records.
 ************************************************************************/

SELECT 'DB_Name=' || SYS_CONTEXT('USERENV', 'DB_NAME')
	|| ', Language=' || SYS_CONTEXT('USERENV', 'LANGUAGE')
	|| ', Host=' || SYS_CONTEXT('USERENV', 'HOST')
	|| ', IP=' || SYS_CONTEXT('USERENV', 'IP_ADDRESS')
	|| ', User=' || SYS_CONTEXT('USERENV', 'CURRENT_USER')
	|| ', ID=' || SYS_CONTEXT('USERENV', 'CURRENT_USERID')
	|| ', Session=' || SYS_CONTEXT('USERENV', 'SESSIONID')
	AS "DBInfo"
FROM DUAL
/
SET serveroutput ON

--	Recompile invalids
BEGIN
	DBA_Cleanup();
	DBA_AfterImport;
--	DBA_Recompile(NULL);    -- called in DBA_AfterImport
END;
/
--	Correct DataFile sizing
DECLARE
	CURSOR Cur_TS IS
		SELECT	FILE_NAME, Tablespace_Name, Bytes/1024/1024 as MB
		FROM	DBA_DATA_FILES
		WHERE	(TABLESPACE_NAME='USERS' AND BYTES < 100*1024*1024)
		  OR	(TABLESPACE_NAME='INDX' AND BYTES < 100*1024*1024)
		  OR	(TABLESPACE_NAME='TEMP' AND BYTES < 100*1024*1024);
	v_CMD			VARCHAR2(300);
BEGIN
	FOR ts IN Cur_TS LOOP 
    	DBMS_OUTPUT.PUT_LINE('Resize:');
		v_CMD := 'ALTER DATABASE DATAFILE ''' || ts.FILE_NAME
			|| ''' RESIZE 100M';
		DBMS_OUTPUT.PUT_LINE(' executing: ' || v_CMD);
		EXECUTE IMMEDIATE v_CMD;
		v_CMD := 'ALTER DATABASE DATAFILE ''' || ts.FILE_NAME
			|| ''' AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED';
		DBMS_OUTPUT.PUT_LINE(' executing: ' || v_CMD);
		EXECUTE IMMEDIATE v_CMD;
	END LOOP;
END;
/

/** Database Sizing
 ** Oracle 10g should use Automatic Shared Memory Management -
 ** Example 300MB
 *
 *  ALTER SYSTEM documentation
http://download-east.oracle.com/docs/cd/B14117_01/server.101/b10759/statements_2013.htm#SQLRF00902
 *
SELECT Name, Value, Description, IsDefault FROM v$parameter 
WHERE Name LIKE '%cursor%' OR Name LIKE '%process%' OR NAME LIKE '%servers%' ORDER BY 1
/
SELECT Name, Value/1024/1024 "MB", Description, IsDefault, Update_Comment FROM v$parameter 
WHERE Name LIKE '%_pool_size' OR Name LIKE 'sga%'
/
ALTER SYSTEM SET statistics_level = TYPICAL Comment='Adempiere' SCOPE=BOTH
/
ALTER SYSTEM SET sga_target = 314572800 Comment='Adempiere' SCOPE=BOTH
/
ALTER SYSTEM SET open_cursors = 900 Comment='Adempiere' SCOPE=BOTH
/

/** Oracle 9i needs to set explicitly (and restart)
 ** Example 300MB
ALTER SYSTEM SET sga_max_size=314572800 SCOPE=SPFILE
/
ALTER SYSTEM SET large_pool_size=33554432 SCOPE=SPFILE
/
ALTER SYSTEM SET java_pool_size=67108864 SCOPE=SPFILE
/
/** SELECT 32*1024*1024 FROM DUAL **/

--	Any invalids
SELECT	Object_Type "Type", Object_Name "Invalid", Status
FROM	User_Objects
WHERE	Status <> 'VALID'
/
--	Trigger Info
SELECT	Trigger_Name AS Trigger_NotEnabled, Status 
FROM	User_Triggers 
WHERE	Status != 'ENABLED'
/
--	Constraint Info
SELECT	Constraint_Name AS Constraint_Problem, Status, Validated, Table_Name, Search_Condition, R_Constraint_Name 
FROM	User_Constraints 
WHERE	Status <> 'ENABLED' OR Validated <> 'VALIDATED'
/
SELECT	* 
FROM	USER_ERRORS
/
COMMIT
/
EXIT
