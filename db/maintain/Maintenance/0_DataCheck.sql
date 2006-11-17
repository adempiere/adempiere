/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 0_DataCheck.sql,v 1.2 2006/07/03 23:19:11 jjanke Exp $
 ***
 * Title:	Data Consistency Check
 * Description:
 *			No updates
 ************************************************************************/

-- 0	Tables not in Dictionary ----------------------------------------------
SELECT Table_Name "Not in Dictionary" FROM CAT 
WHERE Table_Name NOT IN (SELECT UPPER(TableName) FROM AD_Table)
  AND Table_Name NOT LIKE '%_V' 
  AND Table_Name NOT LIKE '%_V_'
  AND Table_Name NOT LIKE '%$%'
ORDER BY 1;

-- 1	Windows w/o Tabs ------------------------------------------------------
Select Name "Windows w/o Tabs", IsActive "Active"
from AD_Window w
Where not exists (select * from ad_tab t where w.ad_window_id = t.ad_window_id)
order by 1;


-- 2	Tabs w/o Fields -------------------------------------------------------
Select Name "Tabs w/o Fields"
from AD_Tab t
Where not exists (select * from ad_field f where t.ad_tab_id = f.ad_tab_id)
  AND t.IsSortTab='N'
order by 1;


-- 3	Tables not used in Tabs -----------------------------------------------
Select TableName "Tables not used"
From AD_Table t
Where not exists
	(Select * from AD_Tab x where t.AD_Table_ID=x.AD_Table_ID)
  AND t.IsView='N'
Order by 1;


-- 4	Columns not used on Fields --------------------------------------------
Select t.TableName, c.ColumnName "Columns not used"
From AD_Column c, AD_Table t, AD_Tab x
WHERE	c.AD_Table_ID=t.AD_Table_ID
	AND t.AD_Table_ID=x.AD_Table_ID
	AND not exists 
		(Select * from AD_Field f where f.AD_Column_ID=c.AD_Column_ID)
	AND Not
		(ColumnName like 'Created%' or ColumnNAme like 'Updated%' or ColumnName='AD_Org_ID')
Order by 1, 2;


-- 5	Fields w/o corret SeqNo -----------------------------------------------
Select w.Name "Window Name", t.Name "Tab Name", f.Name "Field w/o correct SeqNo"
From AD_Field f, AD_Tab t, AD_Window w
WHERE	t.AD_Tab_ID=f.AD_Tab_ID
	AND t.AD_Window_ID=w.AD_Window_ID
	AND f.IsDisplayed='Y'
	AND (f.SeqNo is null OR f.SeqNo = 0)
Order by 1, 2, 3;


-- 6	Windows not in Menu ---------------------------------------------------
Select Name "Windows not in Menu"
From AD_Window w
Where not exists 
	(Select * from AD_Menu m where w.AD_Window_ID=m.AD_Window_ID);


-- 7	Window/Tabs without Sort Order ----------------------------------------
SELECT	w.Name "Window", t.Name "Tab w/o Sort Order"
FROM	AD_Window w, AD_Tab t
WHERE	w.AD_Window_ID = t.AD_Window_ID
	AND AD_Tab_ID NOT IN (SELECT DISTINCT AD_Tab_ID 
        FROM AD_Field WHERE SortNo <> 0)
	AND t.IsTranslationTab!='Y' AND t.IsSortTab='N'
ORDER BY 1;


-- 8	References without details --------------------------------------------
SELECT Name "Table References w/o Detail" FROM AD_Reference
WHERE ValidationType ='T' 
  AND AD_Reference_ID NOT IN (SELECT AD_Reference_ID FROM AD_Ref_Table);

SELECT Name "List References w/o Detail" FROM AD_Reference
WHERE ValidationType ='L' 
  AND AD_Reference_ID NOT IN (SELECT AD_Reference_ID FROM AD_Ref_List);


-- 10	References not used ---------------------------------------------------
SELECT Name "Reference not used", ValidationType, AD_Reference_ID
FROM AD_Reference r 
WHERE IsActive='Y'
  AND NOT EXISTS 
	(SELECT * FROM AD_Column c
	 WHERE c.AD_Reference_ID=r.AD_Reference_ID
	   OR c.AD_Reference_Value_ID=r.AD_Reference_ID)
  AND NOT EXISTS 
	(SELECT * FROM AD_Process_Para p
	 WHERE p.AD_Reference_ID=r.AD_Reference_ID
	   OR p.AD_Reference_Value_ID=r.AD_Reference_ID)
  AND NOT EXISTS 
	(SELECT * FROM AD_Attribute a
	 WHERE a.AD_Reference_ID=r.AD_Reference_ID
	   OR a.AD_Reference_Value_ID=r.AD_Reference_ID)
ORDER BY 2, 1;

-- 10a Table Reference
SELECT r.AD_Reference_ID, r.Name "Reference"
FROM AD_Ref_Table rt
  INNER JOIN AD_Reference r ON (r.AD_Reference_ID=rt.AD_Reference_ID)
  INNER JOIN AD_Column ck ON (ck.AD_Column_ID=rt.AD_Key AND ck.AD_Table_ID!=rt.AD_Table_ID)
  INNER JOIN AD_Column cd ON (cd.AD_Column_ID=rt.AD_Key AND cd.AD_Table_ID!=rt.AD_Table_ID);

-- 11 	Validation rules not used ---------------------------------------------
SELECT Name "Validation not used", AD_Val_Rule_ID
FROM AD_Val_Rule v 
WHERE IsActive='Y'
  AND NOT EXISTS 
	(SELECT * FROM AD_Column c
	 WHERE c.AD_Val_Rule_ID=v.AD_Val_Rule_ID)
  AND NOT EXISTS 
	(SELECT * FROM AD_Table t
	 WHERE t.AD_Val_Rule_ID=v.AD_Val_Rule_ID)
  AND NOT EXISTS 
	(SELECT * FROM AD_Process_Para p
	 WHERE p.AD_Val_Rule_ID=v.AD_Val_Rule_ID)
  AND NOT EXISTS 
	(SELECT * FROM AD_Attribute a
	 WHERE a.AD_Val_Rule_ID=v.AD_Val_Rule_ID)
ORDER BY 1;


-- 12	Processes -------------------------------------------------------------
SELECT Name "Process", ProcedureName "Missing DB Procedure"
FROM AD_Process p
WHERE ProcedureName IS NOT NULL 
  AND NOT EXISTS 
	(SELECT * FROM USER_OBJECTS 
	 WHERE OBJECT_NAME = UPPER(p.ProcedureName));

SELECT Value "Process Not Used", Name
FROM AD_Process p
WHERE IsActive='Y' 
  AND NOT Value LIKE 'Rpt%'
  AND NOT EXISTS
		(SELECT * FROM AD_Column c WHERE c.AD_Process_ID=p.AD_Process_ID)
  AND NOT EXISTS
		(SELECT * FROM AD_Tab t WHERE  t.AD_Process_ID=p.AD_Process_ID)
  AND NOT EXISTS
		(SELECT * FROM AD_Menu m WHERE m.AD_Process_ID=p.AD_Process_ID);


-- 14	Tables w/o Key	-------------------------------------------------------
SELECT	TableName "Tables w/o IsKey"
FROM	AD_Table t
WHERE 
--	Tables with key columns	
		NOT EXISTS (SELECT * FROM AD_Column c 
            WHERE t.AD_Table_ID=c.AD_Table_ID AND c.IsKey='Y')
--	Tables with 2 parents - Association entries
  AND	NOT EXISTS (SELECT c.AD_Table_ID FROM AD_Column c
            WHERE t.AD_Table_ID=c.AD_Table_ID AND c.IsParent='Y' 
            GROUP BY c.AD_Table_ID HAVING COUNT(*) > 1)
--	No Views
  AND IsView='N'
--	tables with more than one key
  AND UPPER(TableName) NOT IN		-- assuming that they are P type constraints
	(SELECT Table_Name FROM USER_Cons_Columns cc
	GROUP BY Constraint_Name, Table_Name
	HAVING COUNT(*) > 1)
ORDER BY 1;
/**
SELECT	TableName "Tables with > 1 Key"
FROM	AD_Table t
WHERE IsView='N' AND TableName NOT LIKE '%_Trl' AND TableName NOT LIKE '%_Acct'
AND EXISTS (SELECT c.AD_Table_ID FROM AD_Column c
    WHERE t.AD_Table_ID=c.AD_Table_ID AND c.IsParent='Y' 
    GROUP BY c.AD_Table_ID HAVING COUNT(*) > 1)
ORDER BY 1;
**/

-- 15	Key ColumnName not is sync with TableName -----------------------------
SELECT t.TableName, e.ColumnName "Key ColumnName not in sync"
FROM AD_Table t, AD_Element e 
WHERE UPPER(e.ColumnName)=UPPER(t.TableName)||'_ID' 
 AND NOT e.ColumnName=t.TableName||'_ID';


-- 16	Tables w/o Identifier -------------------------------------------------
SELECT	TableName "Tables w/o IsIdentifier"
FROM AD_Table t
WHERE NOT EXISTS 
	(SELECT * FROM AD_Column c WHERE IsIdentifier = 'Y' AND t.AD_Table_ID=c.AD_Table_ID)
--	No Views
  AND IsView='N'
--	tables with more than one key
  AND UPPER(TableName) NOT IN		-- assuming that they are P type constraints
	(SELECT Table_Name FROM USER_Cons_Columns cc
	GROUP BY Constraint_Name, Table_Name
	HAVING COUNT(*) > 1)
ORDER BY 1;

-- 17   Table Reference w/o Value ----------------------------------------------
SELECT TableName, ColumnName, c.AD_Reference_ID "Table Reference w/o Value",
  AD_Reference_Value_ID, c.AD_Val_Rule_ID
FROM AD_Column c 
  INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)
WHERE AD_Reference_ID=18 AND AD_Reference_Value_ID IS NULL
ORDER By 1,2;
