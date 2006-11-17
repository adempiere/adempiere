/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Policy_Add.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:		Create Policies	 
 * Description:
 ************************************************************************/
DECLARE
	CURSOR	Cur_Tables	IS
		SELECT 	TableName 
		FROM 	AD_Table
		  --	General accessible tables
		WHERE 	TableName NOT LIKE 'RV%' 	--	Report Views
		  AND	TableName NOT LIKE 'T%' 	--	Temporary
		  --	Login Tables
		  AND	TableName NOT IN ('AD_User', 'AD_User_Roles', 'AD_Role', 'AD_Client', 'AD_Org', 'M_Warehouse')
		  --	Non Standard Tables
		  AND	TableName NOT LIKE 'AD_PInstance%'
		  AND	TableName NOT IN ('AD_Find')
		ORDER BY TableName;
BEGIN
	DBMS_OUTPUT.PUT_LINE('Loging in ...');
	Adempiere_Context.Login('SuperUser','System','System Administrator');
--  Adempiere_Context.Login('Adempiere','Internal','Server');
	--
	DBMS_OUTPUT.PUT_LINE('Creating Policies ...');
    FOR t IN Cur_Tables LOOP
		BEGIN
			DBMS_OUTPUT.PUT(t.TableName);
			DBMS_RLS.ADD_POLICY (
				'Adempiere',					--	object_schema
				t.TableName,				--  object_name
				t.TableName || '_Pol',		--	policy_name
				'Adempiere',					--	function_schema
				'Adempiere_Context.GetPredicate',	--	policy_function
				NULL,						--	statement_types 'SELECT,INSERT,UPDATE,DELETE'
				TRUE,						--	update_check
				TRUE);						--	enable          
			DBMS_OUTPUT.PUT_LINE(' ok');
			EXCEPTION
				WHEN OTHERS THEN
					DBMS_OUTPUT.PUT_LINE(' *** error ***');
		END;
	END LOOP;
END;
/
