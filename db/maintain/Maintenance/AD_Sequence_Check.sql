/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Sequence_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Sequence Number Check
 * Description:
 *	-- Drop Temp Table first --
 *	Set System and Std Table Sequence Number
 *	Insert Document Sequence for DocumentNo / Value

SELECT IsTableID, Name, AD_Client_ID, StartNo, CurrentNext, CurrentNextSys 
FROM AD_Sequence Order BY IsTableID, Name, AD_Client_ID;

 ************************************************************************/
DECLARE
    v_IDRangeEnd                NUMBER;
	v_NextNo					NUMBER;
BEGIN

/** General Reset (don't create a support request if you execute this)
UPDATE AD_Sequence SET StartNo = 1000000, CurrentNext = 1000000, CurrentNextSys = 100
WHERE IsTableID='Y';
-- ?? (don't create a support request if you execute this)
UPDATE AD_Sequence SET StartNo = 10000000, CurrentNext = 10000000, CurrentNextSys = 100
WHERE Name LIKE 'DocumentNo%';
COMMIT;
**/

/** Clean Up **
--  Delete Views
DELETE AD_Sequence s
WHERE Name IN (SELECT Name FROM AD_Table WHERE IsView='Y')
OR Name IN (SELECT 'DocumentNo_' || Name FROM AD_Table WHERE IsView='Y');
--  Delete Non Existing Tables
DELETE AD_Sequence s
WHERE IsTableID='Y'
AND Name NOT IN (SELECT Name FROM AD_Table WHERE IsView='N');
DELETE AD_Sequence s
WHERE Name LIKE 'DocumentNo%'
AND Name NOT IN (SELECT 'DocumentNo_' || Name FROM AD_Table WHERE IsView='N');
**/

	DBMS_OUTPUT.ENABLE(80000);
    --  Ignore higher IDs
    SELECT IDRangeEnd 
      INTO v_IDRangeEnd
    FROM AD_System;
    IF (v_IDRangeEnd IS NULL) THEN
        SELECT MIN(IDRangeStart)-1 
          INTO v_IDRangeEnd
        FROM AD_Replication;
    END IF;
	DBMS_OUTPUT.PUT_LINE('Info: IDRangeEnd=' || v_IDRangeEnd);

	/**
	 *	Check Sequence Number is correct
	 */
	DBMS_OUTPUT.PUT_LINE('AD_Sequence Validity Check for TableID:');
	DECLARE
		CURSOR	Cur_Sequence	IS
			SELECT	*
			FROM	AD_Sequence
			WHERE	IsTableID='Y'
            ORDER BY Name
			FOR UPDATE;
		sql_stmt				VARCHAR2(200);
		Column_ID				NUMBER;
		MaxTableID				NUMBER;
		MaxTableSysID			NUMBER;
	BEGIN
		FOR s IN Cur_Sequence LOOP
			EXECUTE IMMEDIATE 'SELECT MAX(COLUMN_ID) FROM USER_TAB_COLUMNS '
				||	'WHERE TABLE_NAME=UPPER(''' || s.Name
				||	''') AND COLUMN_NAME=UPPER(''' || s.Name ||	'_ID'')'
				INTO Column_ID;
			--	We have a valid column "TableName_ID"
			IF (Column_ID IS NOT NULL) THEN
				--	Get Max ID used in table
				sql_stmt := 'SELECT MAX(' || s.Name || '_ID) FROM ' || s.Name;
                IF (v_IDRangeEnd IS NOT NULL) THEN
                    sql_stmt := sql_stmt || ' WHERE ' || s.Name || '_ID < ' || v_IDRangeEnd;
                END IF;
				EXECUTE IMMEDIATE sql_stmt INTO MaxTableID;
				IF (MaxTableID IS NULL OR MaxTableID < 1000000) THEN
					MaxTableID := 999999;
				END IF;
				MaxTableID := MaxTableID + 1;		--	Next
                
				--	Get Max System_ID used in Table
				sql_stmt := 'SELECT MAX(' || s.Name || '_ID) FROM ' || s.Name
				        || ' WHERE ' || s.Name || '_ID < 1000000';
				EXECUTE IMMEDIATE sql_stmt INTO MaxTableSysID;
				IF (MaxTableSysID IS NULL) THEN
					MaxTableSysID := 99;
				END IF;
				MaxTableSysID := MaxTableSysID + 1;	--	Next
	
				--	Update if next ID in actual table is not CurrentNext
				IF (s.CurrentNext < MaxTableID) THEN
					DBMS_OUTPUT.PUT_LINE(' ' || s.Name || ' CurrentNext=' || s.CurrentNext 
						|| ', Next in Table=' || MaxTableID);
					UPDATE	AD_Sequence
					  SET	CurrentNext = MaxTableID,
							Updated = SysDate
					WHERE CURRENT OF Cur_Sequence;
				END IF;
				--	System IDs
				IF (s.CurrentNextSys <> -1 AND s.CurrentNextSys < MaxTableSysID) THEN
					DBMS_OUTPUT.PUT_LINE(' ' || s.Name || ' CurrentNextSys=' || s.CurrentNextSys 
						|| ', Next in Table=' || MaxTableSysID);
					UPDATE	AD_Sequence
					  SET	CurrentNextSys = MaxTableSysID,
							Updated = SysDate
					WHERE CURRENT OF Cur_Sequence;
				END IF;
			END IF;	--	Valid Column
		END LOOP;
		COMMIT;
	END;	-- TableID Check

	/**
	 *	Make sure that we have all Table Sequences
	 */
	DBMS_OUTPUT.PUT_LINE('AD_Sequence Existence Check for TableID:');
	DECLARE
		CURSOR Cur_Table	IS
			SELECT	*
			FROM	AD_Table t
			WHERE   IsActive='Y' AND IsView='N'
              AND NOT EXISTS (SELECT * FROM AD_Sequence s 
				WHERE s.Name=t.TableName AND s.IsTableID='Y');
	BEGIN
		FOR t IN Cur_Table LOOP
			DBMS_OUTPUT.PUT_LINE(' ' || t.TableName);
			--
			AD_Sequence_Next ('AD_Sequence', t.AD_Client_ID, v_NextNo);
			INSERT INTO AD_Sequence
				(AD_Sequence_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				Name, Description, 
				VFormat, IsAutoSequence,
				IncrementNo, StartNo, CurrentNext, CurrentNextSys,
				IsAudited, IsTableID, Prefix, Suffix, StartNewYear)
			VALUES
				(v_NextNo,
				0, 0, 'Y', SysDate, 0, SysDate, 0,
				t.TableName, NULL,
				NULL, 'Y',
				1, 1000000, 1000000, 100, 
				'N', 'Y', NULL, NULL, 'N');
		END LOOP;
		COMMIT;
	END;	--	Existence check

	/**
	 *	Insert Document Sequence for DocumentNo / Value
	 */
	DBMS_OUTPUT.PUT_LINE('AD_Sequence for DocumentNo/Value:');
	DECLARE
		CURSOR CUR_Clients IS
			SELECT	AD_Client_ID
			FROM	AD_Client
            WHERE   IsActive='Y';
		--	TableNames to be added
		CURSOR CUR_DSequence (client NUMBER) IS
			SELECT	TableName
			FROM	AD_Table t
			WHERE	IsActive='Y' AND IsView='N'
			  -- Get all Tables with DocumentNo or Value
			  AND AD_Table_ID IN 
				(SELECT AD_Table_ID FROM AD_Column 
				WHERE ColumnName = 'DocumentNo' OR ColumnName = 'Value')
			  AND 'DocumentNo_' || TableName NOT IN
				(SELECT Name FROM AD_Sequence s
				WHERE s.AD_Client_ID=client);
	BEGIN
		--	See also: AD_Client_Trg
		FOR c IN CUR_Clients LOOP
			FOR s IN CUR_DSequence (c.AD_Client_ID) LOOP
				DBMS_OUTPUT.PUT_LINE(' ' || c.AD_Client_ID || ' - ' || s.TableName);
				--
				AD_Sequence_Next ('AD_Sequence', c.AD_Client_ID, v_NextNo);
				INSERT INTO AD_Sequence
					(AD_Sequence_ID,
					AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
					Name, Description, 
					VFormat, IsAutoSequence,
					IncrementNo, StartNo, CurrentNext, CurrentNextSys,
					IsAudited, IsTableID, Prefix, Suffix, StartNewYear)
				VALUES
					(v_NextNo,
					c.AD_Client_ID, 0, 'Y', SysDate, 0, SysDate, 0,
					'DocumentNo_' || s.TableName, 'DocumentNo/Value for Table ' || s.TableName,
					NULL, 'Y',
					1, 10000000, 10000000, 10000000, 
					'N', 'N', NULL, NULL, 'N');
			END LOOP;	--	Sequences
		END LOOP;	--	Clients
		COMMIT;
	END;	--	DocumentNo
    
END;
/

