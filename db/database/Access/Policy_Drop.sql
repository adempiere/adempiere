/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Policy_Drop.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Drop Policies
 * Description:
 ************************************************************************/
DECLARE
	CURSOR	Cur_Tables	IS
		SELECT 	* 
		FROM 	DBA_Policies
		ORDER BY 2;
BEGIN
	Adempiere_Context.Login('Adempiere','Internal','Server');
	DBMS_OUTPUT.PUT_LINE('Dropping Policies ...');
    FOR t IN Cur_Tables LOOP
		BEGIN
			DBMS_OUTPUT.PUT(InitCap(t.Object_Name));
			DBMS_RLS.DROP_POLICY (
				NULL,						--	object_schema
				t.Object_Name,				--  object_name
				t.Policy_Name);				--	policy_name
			DBMS_OUTPUT.PUT_LINE(' ok');
			EXCEPTION
				WHEN OTHERS THEN
					DBMS_OUTPUT.PUT_LINE(' * not found *');
		END;
	END LOOP;
END;

