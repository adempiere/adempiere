/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_Tables.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	 Table check
 * Description:
 *	Assuming
 *		db_block_size = 8192
 *		db_file_multiblock_read_count = 8
 *	the extent size should be 
 *		small table 	320k 	(8 * 8 * 5)
 *		large table		1600k	(8 * 8 * 25)
 *
 *	a) Build missing Statistics
 *	b) Ensure storage is PctIncrease 0 and MaxExtents Unlimited
 *  c) Size Table Extents
 *
************************************************************************/
DECLARE
	SmallTableSize		NUMBER :=  320 * 1024;
	LargeTableSize		NUMBER := 1600 * 1024;

	--	Not Analyzed within 7 days
	CURSOR Cur_Stat IS
		SELECT	Table_Name, Blocks
		FROM	USER_TABLES 
		WHERE	DURATION IS NULL		--	No temporary tables
		  AND	(LAST_ANALYZED IS NULL OR LAST_ANALYZED < SysDate-7);
	--	Fix Storage issues
	CURSOR Cur_Storage IS
		SELECT	Table_Name 
		FROM	USER_TABLES
		WHERE	DURATION IS NULL		--	No temporary tables
		  AND	Pct_Increase > 0 OR Max_Extents < 4097 OR Next_Extent < SmallTableSize;
	--
	CURSOR CUR_BigTables IS
		SELECT TableName 
		FROM AD_Table tt, User_Tables ut
		WHERE UPPER(tt.TableName)=ut.Table_Name
		  AND Next_Extent < LargeTableSize
		  AND (IsHighVolume='Y'
			  OR EXISTS 
				(SELECT * FROM AD_Tab t 
				WHERE t.AD_Table_ID=tt.AD_Table_ID 
				  AND EXISTS 
					(SELECT * FROM AD_Window w 
					WHERE WindowType='T' AND w.AD_Window_ID=t.AD_Window_ID))
			  OR (TableName LIKE 'C_Project%' OR TableName='AD_Attachment'))
		ORDER BY 1;
	--
	Cmd				VARCHAR2(256);
	NoE				NUMBER := 0;
	NoC				NUMBER := 0;
BEGIN
	FOR s IN Cur_Stat LOOP
		Cmd := 'ANALYZE TABLE ' || s.Table_Name;
		IF (s.Blocks < 100000) THEN	--	Compute for smaller tables
			Cmd := Cmd || ' COMPUTE STATISTICS';
			NoC := NoC + 1;
		ELSE
			Cmd := Cmd || ' ESTIMATE STATISTICS';
			NoE := NoE + 1;
		END IF;
		EXECUTE IMMEDIATE Cmd;
	END LOOP;
	DBMS_OUTPUT.PUT_LINE('Statistics updated: ' || NoE || ' estimated, ' || NoC || ' computed');
	--
	FOR t IN Cur_Storage LOOP
		DBMS_OUTPUT.PUT_LINE('Table ' || t.Table_Name || ' storage fixed');
		Cmd := 'ALTER TABLE ' || t.Table_Name 
			|| ' PCTFREE 10 PCTUSED 40 STORAGE (NEXT ' || SmallTableSize || ' PCTINCREASE 0 MAXEXTENTS UNLIMITED)';
		EXECUTE IMMEDIATE cmd;
	END LOOP;
	--
	FOR t IN Cur_BigTables LOOP
		DBMS_OUTPUT.PUT_LINE('Table ' || t.TableName || ' extent fixed');
		Cmd := 'ALTER TABLE ' || t.TableName 
			|| ' PCTFREE 20 PCTUSED 40 STORAGE (NEXT ' || LargeTableSize || ' PCTINCREASE 0)';
		EXECUTE IMMEDIATE cmd;
	END LOOP;
END;
/
