/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DBA_Recompile_Run.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Run Recompile and list invalids and disabeled
 * Description:
 ************************************************************************/
BEGIN
	DBA_Recompile(NULL);
	DBA_Cleanup();
END;
/
--	Check General Status
SELECT	Object_Name AS Object_Invalid, Status
FROM	User_Objects
WHERE	Status <> 'VALID'
--AND	Object_Type='VIEW'
/
--	Trigger Info
SELECT	Trigger_Name AS Trigger_NotEnabled, Status 
FROM	User_Triggers 
WHERE	Status != 'ENABLED'
/
--	Constraint Info
SELECT	Constraint_Name AS Constraint_Problem, Status, Validated 
FROM	User_Constraints 
WHERE	Status != 'ENABLED' OR Validated != 'VALIDATED'
/
SELECT	* 
FROM	USER_ERRORS
/
