/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: AD_Element_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	System Element Check
 * Description:
 *			Nice Names
 *			(see AD_Synchronize)
 *****************************************************************************/

--	DBMS_OUTPUT.PUT_LINE('Mixed case for lower case Names');
	UPDATE AD_Column
	SET Name = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(Name,
		'Pa_', 'PA_'), 'Ad_', 'AD_'), 'Gl_', 'GL_'), 'So_', 'SO_'), '_Id', '_ID') 
	WHERE AD_Element_ID IS NULL
        AND Name=InitCap(Name) 
		AND (Name LIKE 'Pa\_%' OR Name LIKE 'Ad\_%' OR Name LIKE 'Gl\_%' OR Name LIKE '%\_Id');

	UPDATE AD_Column
	SET ColumnName = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(ColumnName,
		'Pa_', 'PA_'), 'Ad_', 'AD_'), 'Gl_', 'GL_'), 'So_', 'SO_'), '_Id', '_ID') 
	WHERE AD_Element_ID IS NULL
        AND ColumnName=InitCap(ColumnName)
		AND (ColumnName LIKE 'Pa\_%' OR ColumnName LIKE 'Ad\_%' OR ColumnName LIKE 'Gl\_%' OR ColumnName LIKE '%\_Id');
	
	UPDATE AD_Table
	SET Name = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(Name,
		'Pa_', 'PA_'), 'Ad_', 'AD_'), 'Gl_', 'GL_'), 'So_', 'SO_'), '_Id', '_ID') 
	WHERE Name=InitCap(Name)
		AND (Name LIKE 'Pa\_%' OR Name LIKE 'Ad\_%' OR Name LIKE 'Gl\_%' OR Name LIKE '%\_Id');

	UPDATE AD_Table
	SET TableName = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(TableName,
		'Pa_', 'PA_'), 'Ad_', 'AD_'), 'Gl_', 'GL_'), 'So_', 'SO_'), '_Id', '_ID') 
	WHERE TableName=InitCap(TableName)
		AND (TableName LIKE 'Pa\_%' OR TableName LIKE 'Ad\_%' OR TableName LIKE 'Gl\_%' OR TableName LIKE '%\_Id');

	COMMIT;

--	Missing Translation
	INSERT INTO AD_Element_Trl (AD_Element_ID, AD_Language, AD_Client_ID, AD_Org_ID,
		IsActive, Created, CreatedBy, Updated, UpdatedBy,
		Name,PrintName,Description,Help,IsTranslated,PO_Name,PO_PrintName,PO_Description,PO_Help)
	SELECT e.AD_Element_ID, l.AD_Language, e.AD_Client_ID, e.AD_Org_ID,
		e.IsActive, e.Created, e.CreatedBy, e.Updated, e.UpdatedBy,
		e.Name,e.PrintName,e.Description,e.Help,'N',e.PO_Name,e.PO_PrintName,e.PO_Description,e.PO_Help
	FROM	AD_Element e, AD_Language l
	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	AD_Element_ID || AD_Language NOT IN 
		(SELECT AD_Element_ID || AD_Language FROM AD_Element_Trl);
	COMMIT;

BEGIN
	AD_Synchronize(null);
	COMMIT;
END;
/

