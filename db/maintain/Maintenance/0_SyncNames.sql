/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 0_SyncNames.sql,v 1.3 2006/05/28 22:52:07 jjanke Exp $
 ***
 * Title:	Synchronize Names
 * Description:
 *		Update Column and Field with Names from Element and Process
 *		Update Process Parameters from Elements
 *		Update Workflow Nodes from Windows
 *
-- Not centrally maintained fields
SELECT w.Name "Window", t.Name "Tab", f.Name "Field", c.Name "Column"
FROM AD_Field f
INNER JOIN AD_Tab t ON (t.AD_Tab_ID=f.AD_Tab_ID)
INNER JOIN AD_Window w ON (w.AD_Window_ID=t.AD_Window_ID)
INNER JOIN AD_Column c ON (c.AD_Column_ID=f.AD_Column_ID)
WHERE f.IsCentrallyMaintained='N'
ORDER BY 1,2 
/
-- Not centrally maintained parameters
SELECT p.Name "Process", pp.Name "Parameter", pp.ColumnName "Par Column", e.Name "Element", e.ColumnName "Ele Column"
FROM AD_Process_Para pp
INNER JOIN AD_Process p ON (p.AD_Process_ID=pp.AD_Process_ID)
LEFT OUTER JOIN AD_Element e ON (e.AD_Element_ID=pp.AD_Element_ID)
WHERE pp.IsCentrallyMaintained='N' 
/
-- Duplicate ColumnName - Element is Case sensitive, but database is not
SELECT UPPER(ColumnName)
FROM AD_Element
GROUP BY UPPER(ColumnName)
HAVING COUNT(UPPER(ColumnName)) > 1
/
-- Invalid ColumnName - ORA-00001: unique constraint (ADEMPIERE.AD_COLUMN_NAME) violated 
SELECT t.TableName, c.ColumnName, e.ColumnName
FROM AD_Element e
INNER JOIN AD_Column c ON (e.AD_Element_ID=c.AD_Element_ID)
INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)
WHERE c.ColumnName<>e.ColumnName
/
 *
 ************************************************************************/

BEGIN
	AD_Synchronize(null);
	COMMIT;
END;
/
