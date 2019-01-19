/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: BackupCold.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Cold Backup
 * Description:	
 *	Generate OS cold Backup script
 *	Change the parameter variables p_ as required
 *	Run as system
 ************************************************************************/
SPOOL BackupCold.bat
DECLARE
	p_backupLocation		VARCHAR2(255) 	:= 'D:\Backup';
	p_unix					BOOLEAN 		:= FALSE;
	p_useZip				BOOLEAN			:= TRUE;
	p_zipCmd				VARCHAR(60)		:= 'wzzip '; 
	--
	v_delimiter				VARCHAR(10);
	v_remark				VARCHAR(10);
	v_copy					VARCHAR(60);
	v_sid					VARCHAR(30);
	v_cmd					VARCHAR2(2000);
	--
	CURSOR	CUR_DataFiles	IS
		SELECT	Name	
		FROM 		v$datafile;
	CURSOR	CUR_CtlFiles	IS
		SELECT 	Name		
		FROM 		v$controlfile;
BEGIN
	--	OS Specifics
	IF (p_unix) THEN
		v_delimiter := '/';
		v_remark := '# ';
		v_copy := 'cp ';
	ELSE
		v_delimiter := '\';
		v_remark := 'Rem ';
		v_copy := 'copy ';
	END IF;
	--	
	SELECT 	Value 
	  INTO	v_sid
	FROM 	v$parameter 
	WHERE 	Name ='instance_name';
	p_backupLocation := p_backupLocation || v_delimiter || v_sid;
	--
	DBMS_OUTPUT.PUT_LINE(v_remark || 'Backup script for "' || v_sid || '" to  ' || p_backupLocation);
	DBMS_OUTPUT.PUT_LINE(v_remark || 'This script is automatically created by ColdBackup.sql and may need to be edited';
	DBMS_OUTPUT.NEW_LINE;
	--
	DBMS_OUTPUT.PUT_LINE(v_remark || 'mkdir ' || p_backupLocation);
	DBMS_OUTPUT.NEW_LINE;

	DBMS_OUTPUT.PUT_LINE('sqlplus "system/manager@%AccortoDBService% AS SYSDBA" @%AccortoHome%\util\orastop.sql');
	DBMS_OUTPUT.NEW_LINE;

	IF (p_useZip) THEN
		DBMS_OUTPUT.PUT_LINE(p_zipCmd || p_backupLocation || v_delimiter || v_sid || '.zip ');
		DBMS_OUTPUT.PUT(' ');
	END IF;
	FOR f IN CUR_DataFiles LOOP
		IF (p_useZip) THEN
			DBMS_OUTPUT.PUT(' ' || f.Name);
	  	ELSE
			DBMS_OUTPUT.PUT_LINE(v_copy || f.Name || ' ' || p_backupLocation);
	  	END IF;
	END LOOP;
	IF (p_useZip) THEN
		DBMS_OUTPUT.NEW_LINE;
		DBMS_OUTPUT.PUT(' ');
	END IF;
	FOR f IN CUR_CtlFiles LOOP
		IF (p_useZip) THEN
			DBMS_OUTPUT.PUT(' ' || f.Name);
	  	ELSE
			DBMS_OUTPUT.PUT_LINE(v_copy || f.Name || ' ' || p_backupLocation);
	  	END IF;
	END LOOP;
	IF (p_useZip) THEN
		DBMS_OUTPUT.NEW_LINE;
	END IF;
	DBMS_OUTPUT.NEW_LINE;

	DBMS_OUTPUT.PUT_LINE('sqlplus  "system/manager@%AccortoDBService% AS SYSDBA" @%AccortoHome%\util\orastart.sql');
END;
/
SPOOL OFF
EXIT
