CREATE OR REPLACE PROCEDURE DBA_AfterImport
AS
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_AfterImport.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 * $Source: /cvs/adempiere/db/database/Procedures/DBA_AfterImport.sql,v $
 ***
 * Title:	 Run after Import 
 * Description:
 *  - Set Java Permissions
 *	- Recompile
 *	- Compute Statistics
 *****************************************************************************/
 
	--	Statistics
	CURSOR Cur_Stat IS
		SELECT 	Table_Name, Blocks
		FROM 	USER_TABLES 
		WHERE 	DURATION IS NULL		--	No temporary tables
          AND   Table_Name NOT LIKE '%$%'
		  AND	(LAST_ANALYZED IS NULL OR LAST_ANALYZED < SysDate-7);
	--
	v_Cmd				VARCHAR2(256);
	v_NoC				NUMBER := 0;
	--
BEGIN
	--	Recompile
	DBA_Recompile(NULL);
	
	--	Statistics
	FOR s IN Cur_Stat LOOP
		v_Cmd := 'ANALYZE TABLE ' || s.Table_Name || ' COMPUTE STATISTICS';
    --	DBMS_OUTPUT.PUT_LINE (v_Cmd);
		v_NoC := v_NoC + 1;
		EXECUTE IMMEDIATE v_Cmd;
	END LOOP;
	DBMS_OUTPUT.PUT_LINE ('Statistics computed: ' || v_NoC);
	--
END DBA_AfterImport;
/
