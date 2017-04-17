/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: CheckDB.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	TabeSpace Sizing	 
 * Description:
 *			Make sure, that Tablespace 
 *				USERS is 150 MB, 10 MB Autoextend
 *				INDX is 100 MB, 10 MB Autoextend
 *				TEMP is 100 MB, 10 MB Autoextend
 *			Set SGA Size (optional)
 * Executed with System user and parameter %ADEMPIERE_DB_USER% from RUN_Env
 ************************************************************************/

-- Check existance
SELECT 'Tablespace USERS does not exist - You need to create it first' AS Missing FROM DUAL
WHERE NOT EXISTS (SELECT * FROM DBA_TABLESPACES WHERE TABLESPACE_NAME='USERS');
SELECT 'Tablespace INDX does not exist - You need to create it first' AS Missing FROM DUAL
WHERE NOT EXISTS (SELECT * FROM DBA_TABLESPACES WHERE TABLESPACE_NAME='INDX');
SELECT 'Tablespace TEMP does not exist - You need to create it first' AS Missing FROM DUAL
WHERE NOT EXISTS (SELECT * FROM DBA_TABLESPACES WHERE TABLESPACE_NAME='TEMP');

/*****
 *	Changing System Parameters
 *	directly - (e.g. 400 MB for 10 Users)
		ALTER SYSTEM SET SGA_MAX_SIZE=400M COMMENT='400MB' SCOPE=SPFILE;
		ALTER SYSTEM SET SHARED_POOL_SIZE=100M SCOPE=SPFILE;
		ALTER SYSTEM SET DB_CACHE_SIZE=200M SCOPE=SPFILE;
		ALTER SYSTEM SET JAVA_POOL_SIZE=40M SCOPE=SPFILE;
		ALTER SYSTEM SET LARGE_POOL_SIZE=10M SCOPE=SPFILE;
 **	indirectly - sqlplus "system/manager@adempiere AS SYSDBA"
		CREATE PFile='pfileAdempiere.ora' FROM SPFile;
 *	creates file in $ORACLE_HOME\database or $ORACLE_HOME/dbs
 *	edit file and then overwrite the fila via
		CREATE SPFile FROM PFile='pfileAdempiere.ora';
 *****/

--	Create System Record - OK, if it fails
--	Schema is parameter.
INSERT INTO &1..AD_System
  (AD_System_ID,AD_Client_ID,AD_Org_ID, 
  IsActive,Created,CreatedBy,Updated,UpdatedBy,
  Name, UserName, Info)
SELECT	0,0,0,'Y', SysDate,0,SysDate,0, '?','?','?'
FROM	Dual
WHERE NOT EXISTS 
  (SELECT * FROM &1..AD_System WHERE AD_System_ID=0);

-- Add Info - OK, if fails
UPDATE &1..AD_System
SET Info = (SELECT SYS_CONTEXT('USERENV', 'DB_DOMAIN')
	|| ',' || SYS_CONTEXT('USERENV', 'DB_NAME')
	|| ',IP=' || SYS_CONTEXT('USERENV', 'IP_ADDRESS')
	|| ',' || SYS_CONTEXT('USERENV', 'HOST')
	|| ',' || SYS_CONTEXT('USERENV', 'INSTANCE')
	|| ',UID=' || SYS_CONTEXT('USERENV', 'CURRENT_USER')
	|| ',' || SYS_CONTEXT('USERENV', 'CURRENT_USERID')
	|| ',C#=' || (SELECT COUNT(*) FROM &1..AD_Client)
	FROM DUAL),
	Updated=SysDate;
COMMIT;

set serveroutput on
--	Correct sizing
DECLARE
	CURSOR Cur_TS IS
		SELECT	FILE_NAME, Tablespace_Name, Bytes/1024/1024 as MB
		FROM	DBA_DATA_FILES
		WHERE	(TABLESPACE_NAME='USERS' AND BYTES < 100*1024*1024)
		  OR	(TABLESPACE_NAME='INDX' AND BYTES < 100*1024*1024)
		  OR	(TABLESPACE_NAME='TEMP' AND BYTES < 100*1024*1024);
	v_CMD			VARCHAR2(300);
BEGIN
	DBMS_OUTPUT.PUT_LINE('Resize:');
	FOR ts IN Cur_TS LOOP 
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

EXIT
