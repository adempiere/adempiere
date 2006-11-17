/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_Indexes.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Rebuild Indexes in Tablespace USERS
 * Description:
 *		Ensure storage is PctIncrease 0 and MaxExtents Unlimited
 ************************************************************************/
DECLARE
	--	In wrong tablespace
	CURSOR Cur_Move IS
		SELECT	Index_Name
		FROM	USER_INDEXES
		WHERE	Index_Type<>'LOB'		--	no LOB
		  AND	TableSpace_Name != 'USERS';
	--	Fix Storage issues
	CURSOR Cur_Storage IS
		SELECT	Index_Name 
		FROM	USER_INDEXES
		WHERE	Index_Type<>'LOB'		--	no LOB
		  AND	Pct_Increase > 0 OR Max_Extents < 4097;
	--	Not Analyzed within the last 7 days
	CURSOR Cur_Stat IS
		SELECT	Index_Name
		FROM	USER_INDEXES
		WHERE	Index_Type<>'LOB'		--	no LOB 
		  AND DURATION IS NULL			--	no Temp Table
		  AND	(Last_Analyzed IS NULL OR Last_Analyzed < SysDate-7);
	--
	Cmd					VARCHAR2(256);
BEGIN
	FOR i IN Cur_Move LOOP
		DBMS_OUTPUT.PUT_LINE('Index ' || i.Index_Name || ' moved');
		Cmd := 'ALTER INDEX ' || i.Index_Name || ' REBUILD TABLESPACE USERS COMPUTE STATISTICS';
		EXECUTE IMMEDIATE cmd;
	END LOOP;
	FOR i IN Cur_Storage LOOP
		DBMS_OUTPUT.PUT_LINE('Index ' || i.Index_Name || ' storage fixed');
		Cmd := 'ALTER INDEX ' || i.Index_Name 
			|| ' STORAGE (PCTINCREASE 0 MAXEXTENTS UNLIMITED)';
		EXECUTE IMMEDIATE cmd;
	END LOOP;
	FOR i IN Cur_Stat LOOP
		DBMS_OUTPUT.PUT_LINE('Index ' || i.Index_Name || ' analyzed');
		Cmd := 'ALTER INDEX ' || i.Index_Name || ' REBUILD COMPUTE STATISTICS';
		EXECUTE IMMEDIATE cmd;
	END LOOP;
	COMMIT;
END;
/

