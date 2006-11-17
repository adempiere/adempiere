/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_Trg_Enable.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Enable all triggers and self-referencing constraints
 * Description:
 ************************************************************************/
DECLARE
	-- Trigger
	CURSOR Cur_Trg IS
		SELECT 	TRIGGER_NAME
		FROM 	USER_TRIGGERS
		WHERE	STATUS = 'DISABLED'
		ORDER BY 1;
	-- Self referencing Constraints
	CURSOR Cur_Constraint IS
		SELECT 	Table_Name, Constraint_Name
		FROM 	USER_CONSTRAINTS c
		WHERE	c.Constraint_Type='R'
		  AND	c.Status = 'DISABLED'
		  AND EXISTS (SELECT * FROM USER_CONSTRAINTS cc 
		  	WHERE c.R_Constraint_Name=cc.Constraint_Name
			  AND c.Table_Name=cc.Table_Name)
		ORDER BY 1;
 	v_Cmd					VARCHAR2(256);
BEGIN
	DBMS_OUTPUT.PUT_LINE('Enabling:');
	FOR t IN CUR_Trg LOOP
		DBMS_OUTPUT.PUT_LINE('.. ' || t.Trigger_Name);
		v_Cmd := 'ALTER TRIGGER ' || t.Trigger_Name || ' ENABLE';
		EXECUTE IMMEDIATE v_Cmd;
	END LOOP;
	FOR c IN CUR_Constraint LOOP
		DBMS_OUTPUT.PUT_LINE('.. ' || c.Table_Name || ' ' || c.Constraint_Name);
		v_Cmd := 'ALTER TABLE ' || c.Table_Name || ' MODIFY CONSTRAINT ' || c.Constraint_Name || ' ENABLE';
		EXECUTE IMMEDIATE v_Cmd;
	END LOOP;
END;
/

