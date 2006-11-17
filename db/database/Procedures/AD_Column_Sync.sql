CREATE OR REPLACE PROCEDURE AD_Column_Sync
(
	p_PInstance_ID    		IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Column_Sync.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Synchronize Column with Database
 * Description:
 ************************************************************************/
AS
	--	Logistice
	v_ResultStr						VARCHAR2(2000);
	v_Message						VARCHAR2(2000);
	v_Result						NUMBER := 1;	-- 0=failure
	v_Record_ID						NUMBER;
	v_AD_User_ID					NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (pp_PInstance NUMBER) IS
		SELECT i.Record_ID, i.AD_User_ID,
			p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=pp_PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Parameter Variables
	--	Variables
	v_TableName					AD_Table.TableName%TYPE;
	v_ColumnName				AD_Column.ColumnName%TYPE;
	v_AD_Reference_ID			AD_Column.AD_Reference_ID%TYPE;
	v_FieldLength				AD_Column.FieldLength%TYPE;
	v_DefaultValue				AD_Column.DefaultValue%TYPE;
	v_IsMandatory				AD_Column.IsMandatory%TYPE;
	--
	v_DB_DataType				USER_TAB_COLUMNS.DATA_TYPE%TYPE;
	v_Cmd						VARCHAR2(255);
	v_DB_TableName				VARCHAR(60) := NULL;

BEGIN
    --  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || p_PInstance_ID);
	v_ResultStr := 'PInstanceNotFound';
    UPDATE AD_PInstance
    SET Created = SysDate,
        IsProcessing = 'Y'
    WHERE AD_PInstance_ID=p_PInstance_ID;
    COMMIT;

	--	Get Parameters
	v_ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (p_PInstance_ID) LOOP
		v_Record_ID := p.Record_ID;
		v_AD_User_ID := p.AD_User_ID;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || v_Record_ID);

	--	Get Table/Column Info
	v_ResultStr := 'ReadingColumnInfo';
	SELECT	t.TableName, c.ColumnName, c.AD_Reference_ID, c.FieldLength, 
		c.DefaultValue, c.IsMandatory
	  INTO	v_TableName, v_ColumnName, v_AD_Reference_ID, v_FieldLength, 
		v_DefaultValue, v_IsMandatory
   	FROM	AD_Table t, AD_Column c
	WHERE	t.AD_Table_ID = c.AD_Table_ID
	  AND	c.AD_Column_ID = v_Record_ID;


	--	Check if Table exists
	v_ResultStr := 'ReadingDBTableInfo';
	BEGIN
		SELECT	Table_Name 
		  INTO	v_DB_TableName
		FROM 	USER_TABLES 
		WHERE 	Table_Name=UPPER(v_TableName);
		EXCEPTION
			WHEN OTHERS THEN NULL;
	END;

	--	Table does not exists
	IF (v_DB_TableName IS NULL) THEN
		v_ResultStr := 'CreateTableCommand';
		BEGIN
			v_CMD := 'CREATE TABLE ' || SYS_CONTEXT('USERENV', 'CURRENT_USER') || '.'
				|| UPPER(v_TableName) || ' (XXXX CHAR(1))';
			EXECUTE IMMEDIATE v_Cmd;
		EXCEPTION
			WHEN OTHERS THEN
				v_Result := 0;	-- failure
				v_Message := 'Error: ' || SQLERRM || ' - Command: ' || v_Cmd;
				GOTO FINISH_PROCESS;
		END;		
	END IF;


	--	Get Data Dictionary Info
	v_ResultStr := 'ReadingDBColumnInfo';
	BEGIN
		SELECT 	DATA_TYPE	--, DATA_LENGTH, DATA_PRECISION, DATA_SCALE, NULLABLE, DATA_DEFAULT
		  INTO	v_DB_DataType
		FROM 	USER_TAB_COLUMNS
		WHERE 	TABLE_NAME=UPPER(v_TableName)
		  AND 	COLUMN_NAME=UPPER(v_ColumnName);
		EXCEPTION
			WHEN OTHERS THEN NULL;
	END;


	/**
	 *	Create Column in Database
	 */
	IF (v_DB_DataType IS NULL) THEN
		v_ResultStr := 'CreateALTERCommand';
		BEGIN
			--	 Get TableName
			v_Cmd := 'ALTER TABLE ' || v_TableName || ' ADD ' || v_ColumnName || ' ';
			--	Map Data Type
			IF (v_AD_Reference_ID IN (10,14)) THEN	
				--	String, Text
				v_Cmd := v_Cmd || 'NVARCHAR2(' || v_FieldLength || ')';
		   	ELSIF (v_AD_Reference_ID IN (17,20,28)) THEN	
				--	List,YesNo,Button
				v_Cmd := v_Cmd || 'CHAR(' || v_FieldLength || ')';
		   	ELSIF (v_AD_Reference_ID IN (13,18,19,21,25,27,30,31)) THEN	
				--	ID,Table,TableDir,Location,Account,Color,Search,Locator
				v_Cmd := v_Cmd || 'NUMBER(10)';
		   	ELSIF (v_AD_Reference_ID IN (11,12,22,29)) THEN	
				--	Integer,Amount,Number,Quantity
				v_Cmd := v_Cmd || 'NUMBER';
		   	ELSIF (v_AD_Reference_ID IN (15,16)) THEN	
				--	Date,DateTime
				v_Cmd := v_Cmd || 'DATE';
			ELSE	--	23-Binary, 24-Radio, 26-RowID, 32-Image
				v_Result := 0;	-- failure
				v_Message := 'DisplayType Not Supported';
			END IF;
			--	Default (literal)
			IF (v_DefaultValue IS NOT NULL AND LENGTH(v_DefaultValue) <> 0) THEN
				IF (v_AD_Reference_ID IN (10,14,17,20,28)) THEN	
					v_Cmd := v_Cmd || ' DEFAULT (''' || v_DefaultValue || ''')';
				ELSE
					v_Cmd := v_Cmd || ' DEFAULT ' || v_DefaultValue;
				END IF;
			END IF;
			--	Mandatory
			IF (v_IsMandatory = 'Y') THEN
				IF (v_DefaultValue IS NULL OR LENGTH(v_DefaultValue) = 0) THEN
					v_Result := 0;	-- failure
					v_Message := 'Mandatory requites literal default value';
			   	ELSE
					v_Cmd := v_Cmd || ' NOT NULL';
				END IF;
		  	END IF;
			--	Execute it
			IF (v_Result = 1) THEN
				EXECUTE IMMEDIATE v_Cmd;
				v_Message := '@Created@ - ' || v_Cmd;
			END IF;
		EXCEPTION
			WHEN OTHERS THEN
				v_Result := 0;	-- failure
				v_Message := 'Error: ' || SQLERRM || ' - Command: ' || v_Cmd;
		END;

	/**
	 *	Change certain Attributes
	 */
	ELSE
		v_ResultStr := 'CreateALTERCommand';
		BEGIN
			--	 Get TableName
			v_Cmd := 'ALTER TABLE ' || v_TableName || ' MODIFY ' || v_ColumnName || ' ';
			--	Map Data Type
			IF (v_AD_Reference_ID IN (10,14)) THEN	
				--	String, Text
				v_Cmd := v_Cmd || 'NVARCHAR2(' || v_FieldLength || ')';
		   	ELSIF (v_AD_Reference_ID IN (17,20,28)) THEN	
				--	List,YesNo,Button
				v_Cmd := v_Cmd || 'CHAR(' || v_FieldLength || ')';
		   	ELSIF (v_AD_Reference_ID IN (13,18,19,21,25,27,30,31)) THEN	
				--	ID,Table,TableDir,Location,Account,Color,Search,Locator
				v_Cmd := v_Cmd || 'NUMBER(10)';
		   	ELSIF (v_AD_Reference_ID IN (11,12,22,29)) THEN	
				--	Integer,Amount,Number,Quantity
				v_Cmd := v_Cmd || 'NUMBER';
		   	ELSIF (v_AD_Reference_ID IN (15,16)) THEN	
				--	Date,DateTime
				v_Cmd := v_Cmd || 'DATE';
			ELSE	--	23-Binary, 24-Radio, 26-RowID, 32-Image
				v_Result := 0;	-- failure
				v_Message := 'DisplayType Not Supported';
			END IF;
			--	Default (literal)
			IF (v_DefaultValue IS NOT NULL AND LENGTH(v_DefaultValue) <> 0) THEN
				IF (v_AD_Reference_ID IN (10,14,17,20,28)) THEN	
					v_Cmd := v_Cmd || ' DEFAULT (''' || v_DefaultValue || ''')';
				ELSE
					v_Cmd := v_Cmd || ' DEFAULT ' || v_DefaultValue;
				END IF;
			END IF;
			--	Mandatory
			IF (v_IsMandatory = 'Y') THEN
				IF (v_DefaultValue IS NULL OR LENGTH(v_DefaultValue) = 0) THEN
					v_Result := 0;	-- failure
					v_Message := 'Mandatory requites literal default value';
			   	ELSE
					v_Cmd := v_Cmd || ' NOT NULL';
				END IF;
		  	END IF;
			--	Execute it
			IF (v_Result = 1) THEN
				EXECUTE IMMEDIATE v_Cmd;
				v_Message := '@Updated@ - ' || v_Cmd;
			END IF;
		EXCEPTION
			WHEN OTHERS THEN
				v_Result := 0;	-- failure
				v_Message := 'Error: ' || SQLERRM || ' - Command: ' || v_Cmd;
		END;
	END IF;

	/**
	 *	Delete Column
	 *
	ELSE
		v_Cmd := 'ALTER TABLE ' || v_TableName 
			|| ' DROP COLUMN ' || v_ColumnName;
		--	Execute it
		EXECUTE IMMEDIATE v_Cmd;
	END IF;
	/**/


	--	Table did not exist - drop initial column
	IF (v_DB_TableName IS NULL) THEN
		v_ResultStr := 'CreateDropXXColumnCommand';
		BEGIN
			v_CMD := 'ALTER TABLE ' || v_TableName || ' DROP COLUMN XXXX';
			EXECUTE IMMEDIATE v_Cmd;
		EXCEPTION
			WHEN OTHERS THEN
				v_Result := 0;	-- failure
				v_Message := 'Error: ' || SQLERRM || ' - Command: ' || v_Cmd;
		END;		
	END IF;


<<FINISH_PROCESS>>
	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || v_Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = v_Result,			-- 1=success
        ErrorMsg = v_Message
    WHERE   AD_PInstance_ID=p_PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		v_ResultStr := v_ResultStr || ': ' || SQLERRM || ' - ' || v_Message;
		DBMS_OUTPUT.PUT_LINE(v_ResultStr);
		ROLLBACK;
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = v_ResultStr
        WHERE   AD_PInstance_ID=p_PInstance_ID;
        COMMIT;
        RETURN;

END AD_Column_Sync;
/
