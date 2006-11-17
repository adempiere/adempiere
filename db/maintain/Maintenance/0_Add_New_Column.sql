/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 0_Add_New_Column.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	 Add New Table Columns
 * Description:
 *	Create new (missing) Tables and Columns when in Database, but not in AD
 *  You should change the entity type variables from D to User or Application.
 *
 *	SELECT TableName, ColumnName FROM AD_Table t, AD_Column c WHERE TRUNC(c.Created)=TRUNC(SysDate) AND t.AD_Table_ID=c.AD_Table_ID
 *
 *	Next Steps:	
 *	=> Run 0_TempTables_Create/Drop <= for 0_CleanUpAD
 *		- 0_CleanUpAD
 *		- AD_Element_Check
 *		- Application: 
 *			Maintain Elements, Table; 
 *			Add Windows/Tabs
 *		- 0_Add_New_Field
 *		- 0_SyncNames
 *		- AD_Menu_Check
 *		- Access: 
 *			Window (Process, Form, Workflow, Task)
 *		- Application: 
 *			Maintain Window
 *	Other:
 *		- 0_DataCheck
 *****************************************************************************/
BEGIN
	DBMS_OUTPUT.ENABLE(80000);
	/**	**/
	DBMS_OUTPUT.PUT_LINE('Create missing Tables');
	DECLARE
        v_EntityType    CHAR(1) := 'D';     -- change to User (also below!)
		NextNo		NUMBER;
		CURSOR Cur_Table	IS
			SELECT	Table_Name 
			FROM	User_Tables ut
			WHERE NOT EXISTS 
				(SELECT * FROM AD_Table t WHERE ut.Table_Name=UPPER(t.TableName))
				--	No Selection Temporary and no Log tables
				AND NOT (Table_Name LIKE 'T_SELECTION%' 
					OR Table_Name LIKE 'DBA%' 
					OR Table_Name LIKE '%$%' -- 
					OR Table_Name LIKE 'A_A%_ACCT' OR Table_Name LIKE 'A_A%_ADD%' OR Table_Name LIKE 'A_A%_CHANGE%' OR Table_Name LIKE 'A_A%_USE' 
					OR Table_Name LIKE 'A_D%' -- Depreciation
					OR Table_Name LIKE '%EXPLAIN%');
	BEGIN
		FOR CT IN Cur_Table LOOP
            IF (CT.Table_Name LIKE 'XX%' OR CT.Table_Name LIKE 'CUST%' OR CT.Table_Name LIKE 'EXT%') THEN
                v_EntityType := 'U';
            END IF;
			AD_Sequence_Next('AD_Table', 0, NextNo);	--	get ID
			INSERT INTO AD_Table
				(AD_TABLE_ID, AD_CLIENT_ID, AD_ORG_ID, 
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
				Name, TableName, EntityType,
				AccessLevel, LoadSeq, IsSecurityEnabled, IsDeleteable, ReplicationType)
			VALUES
				(NextNo, 0, 0,
				'Y', SysDate, 0, SysDate, 0,
				InitCap(CT.Table_Name), InitCap(CT.Table_Name), v_EntityType,
				'4', 999, 'N', 'Y','L');
			DBMS_OUTPUT.PUT_LINE('adding Table ' || InitCap(CT.Table_Name));
		END LOOP;	--  All new Tables
	END;
	COMMIT;
	/**	**/

	DBMS_OUTPUT.PUT_LINE('Create missing Columns');
	DECLARE
        v_EntityType    CHAR(1) := 'D';     -- change to User (also above!)
		NextNo		NUMBER;
		CURSOR Cur_Column	IS
			SELECT	Column_Name, Data_Type, COALESCE(Char_Col_Decl_Length, Data_Length) AS Data_Length, 
                Nullable, AD_Table_ID, Table_Name, EntityType
			FROM	User_Tab_Columns uc, AD_Table t
			WHERE	uc.Table_Name=UPPER(t.TableName)
				AND NOT EXISTS 
					(SELECT * FROM AD_Table t, AD_Column c 
					WHERE t.AD_Table_ID=c.AD_Table_ID
						AND uc.Table_Name=UPPER(t.TableName)
						AND uc.Column_Name=UPPER(c.ColumnName));
	BEGIN
		FOR CC IN Cur_Column LOOP
			AD_Sequence_Next('AD_Column', 0, NextNo);	--	get ID
			INSERT INTO AD_COLUMN
				(AD_COLUMN_ID, AD_CLIENT_ID, AD_ORG_ID,
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY,
				NAME, VERSION, COLUMNNAME,
				AD_TABLE_ID, AD_REFERENCE_ID,
				FIELDLENGTH, ISKEY, ISPARENT, ISMANDATORY, ISIDENTIFIER,
				SEQNO, ISTRANSLATED, ISENCRYPTED, EntityType)
			VALUES
				(NextNo, 0, 0,
				'Y', SysDate, 0, SysDate, 0,
				InitCap(CC.Column_Name), 1, InitCap(CC.Column_Name),
				CC.AD_Table_ID, DECODE(CC.Data_Type, 'NUMBER', 11, 'CHAR', 20, 'DATE', 15, 10),
				CC.Data_Length, 'N', 'N', DECODE(CC.Nullable, 'Y', 'N', 'Y'), 'N',
				0, 'N', 'N', CC.EntityType);				  
			DBMS_OUTPUT.PUT_LINE('adding Column ' || InitCap(CC.Table_Name) || ' Column ' || InitCap(CC.Column_Name));
		END LOOP;	--  All new columns
	END;
	COMMIT;

END;
/
