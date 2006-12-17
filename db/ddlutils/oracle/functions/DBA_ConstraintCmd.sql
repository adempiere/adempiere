CREATE OR REPLACE FUNCTION DBA_ConstraintCmd
(
	p_ConstraintName	IN	VARCHAR2
)
RETURN VARCHAR2
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_ConstraintCmd.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 * $Source: /cvs/adempiere/db/database/Functions/DBA_ConstraintCmd.sql,v $
 ***
 * Title:	 Create DML command for given constraint
 * Description:
 *		SELECT DBA_ConstraintCmd(Constraint_Name) FROM User_Constraints WHERE CONSTRAINT_TYPE='R'
 ************************************************************************/
AS
	v_Result			VARCHAR2(2000);
	v_TableName			VARCHAR2(256);
	v_ColumnName		VARCHAR2(256);
	v_ConstraintName	VARCHAR2(256);
	v_DeleteRule		VARCHAR2(256);
BEGIN
	--	Get First Part
	SELECT c.Table_Name, cc.Column_name, c.R_Constraint_Name, c.Delete_Rule
	  INTO	v_TableName, v_ColumnName, v_ConstraintName, v_DeleteRule
	FROM USER_Constraints c, USER_Cons_Columns cc
	WHERE c.Constraint_Name=cc.Constraint_Name
	  AND cc.Constraint_Name=p_ConstraintName;
	--	Create First Part
	v_Result := 'ALTER TABLE ' || v_TableName || ' ADD CONSTRAINT ' || p_ConstraintName
		|| ' FOREIGN KEY (' || v_ColumnName || ') ';

	--	Not a valid FK Reference
	IF (v_ConstraintName IS NULL) THEN
		RETURN NULL;
   	END IF;

	--	Get Second Part
	SELECT c.Table_Name, cc.Column_name
	  INTO	v_TableName, v_ColumnName
	FROM USER_Constraints c, USER_Cons_Columns cc
	WHERE c.Constraint_Name=cc.Constraint_Name
	  AND cc.Constraint_Name=v_ConstraintName;
	--	Create Second Part
	v_Result := v_Result || 'REFERENCES ' || v_TableName || '(' || v_ColumnName || ')';

	IF (v_DeleteRule = 'CASCADE') THEN
		v_Result := v_Result || ' ON DELETE CASCADE';
   	END IF;
--	DBMS_OUTPUT.PUT_LINE(v_Result);
	RETURN v_Result;

END DBA_ConstraintCmd;
/

