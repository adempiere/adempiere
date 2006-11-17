/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 0_CleanUpAD.sql,v 1.2 2006/06/18 00:44:13 jjanke Exp $
 ***
 * Title:	Clean up Application Dictionary
 * Description:
 *		=> Run TempTables_Create/Drop <=	first
 *                                          otherwise you'll delete all views
 * Please check EVERY statement before execution. 
 * This is a script desiged for the reference database not extensions.
 * (e.g. assigns ownership to system, etc.)
 ************************************************************************/
 
SELECT (SELECT COUNT(*) FROM USER_Procedures) "Procedures", 
(SELECT COUNT(*) FROM USER_Triggers) "Triggers" FROM DUAL;

SELECT 'Clean up Application Dictionary' FROM DUAL;

-- Create temp table of all colums in AD and DB
SELECT '<<< Create temp table of all colums in AD and DB >>>' FROM DUAL;

CREATE OR REPLACE VIEW temp_db_columns AS
	SELECT t.TableName, c.ColumnName, t.AD_Table_ID, c.AD_Column_ID, 
		uc.Data_Type, COALESCE(uc.Char_Col_Decl_Length,uc.Data_Length) AS DataLength,
		uc.Data_Precision, uc.Data_Scale, uc.Nullable, c.ColumnSQL,
        CASE WHEN uc.Data_Type IS NULL AND c.ColumnSQL IS NULL THEN 'Y' ELSE 'N' END AS IsNotInDB
	FROM AD_Table t
      INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID)
      LEFT JOIN User_Tab_Columns uc ON (uc.Table_Name=UPPER(t.TableName)
		AND uc.Column_Name=UPPER(c.ColumnName));

-- Delete columns not in DB = translated fields
SELECT	Name "Delete fields not in DB", AD_Field_ID
FROM	AD_Field f
WHERE NOT EXISTS 
	(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=f.AD_Column_ID AND db.IsNotInDB='N');

DELETE	AD_Field_Trl
WHERE	AD_Field_ID IN 
	(SELECT AD_Field_ID FROM AD_Field f WHERE NOT EXISTS 
		(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=f.AD_Column_ID AND db.IsNotInDB='N'));

-- Delete fields not in DB
DELETE	AD_Field
WHERE	AD_Field_ID IN 
	(SELECT AD_Field_ID FROM AD_Field f WHERE NOT EXISTS 
		(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=f.AD_Column_ID AND db.IsNotInDB='N'));

-- Delete columns not in DB
SELECT	Name "Delete columns not in DB", AD_Column_ID
FROM	AD_Column c
WHERE NOT EXISTS 
	(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=c.AD_Column_ID AND db.IsNotInDB='N'); 

-- column used in table reference
DELETE	AD_Ref_Table
WHERE	AD_Key IN 
	(SELECT AD_Column_ID FROM AD_Column c WHERE NOT EXISTS 
		(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=c.AD_Column_ID AND db.IsNotInDB='N'));
DELETE	AD_Ref_Table
WHERE	AD_Display IN
	(SELECT AD_Column_ID FROM AD_Column c WHERE NOT EXISTS 
		(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=c.AD_Column_ID AND db.IsNotInDB='N'));

-- remove references
UPDATE	AD_Tab
SET		AD_Column_ID = NULL
WHERE	AD_Column_ID IN
	(SELECT AD_Column_ID FROM AD_Column c WHERE NOT EXISTS 
		(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=c.AD_Column_ID AND db.IsNotInDB='N'));

DELETE	AD_PrintFormatItem
WHERE	AD_Column_ID IN 
	(SELECT AD_Column_ID FROM AD_Column c WHERE NOT EXISTS 
		(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=c.AD_Column_ID AND db.IsNotInDB='N'));

DELETE	AD_Column
WHERE	AD_Column_ID IN 
	(SELECT AD_Column_ID FROM AD_Column c WHERE NOT EXISTS 
		(SELECT * FROM temp_db_columns db WHERE db.AD_Column_ID=c.AD_Column_ID AND db.IsNotInDB='N'));

-- Delete all AD_Tab rows of tables not in DB = translated tabs
SELECT	Name "Delete Tabs w tables not in DB", AD_Tab_ID
FROM	AD_Tab 
WHERE	AD_Table_ID IN
	(SELECT AD_Table_ID FROM AD_Table t WHERE NOT EXISTS
		(SELECT * FROM temp_db_columns db WHERE db.AD_Table_ID=t.AD_Table_ID AND db.IsNotInDB='N'));

DELETE	AD_Tab_Trl
WHERE	AD_Tab_ID IN 
	(SELECT AD_Tab_ID FROM AD_Tab
	WHERE AD_Table_ID IN
		(SELECT AD_Table_ID FROM AD_Table t WHERE NOT EXISTS
			(SELECT * FROM temp_db_columns db WHERE db.AD_Table_ID=t.AD_Table_ID AND db.IsNotInDB='N')));

DELETE	AD_Tab
WHERE	AD_Table_ID IN
	(SELECT AD_Table_ID FROM AD_Table t WHERE NOT EXISTS
		(SELECT * FROM temp_db_columns db WHERE db.AD_Table_ID=t.AD_Table_ID AND db.IsNotInDB='N'));


-- Delete all AD_Table rows of tables not in DB
SELECT	Name "Delete Tables not in DB", AD_Table_ID
FROM	AD_Table t
WHERE NOT EXISTS
	(SELECT * FROM temp_db_columns db WHERE db.AD_Table_ID=t.AD_Table_ID AND db.IsNotInDB='N');

DELETE	AD_Table t
WHERE NOT EXISTS
	(SELECT * FROM temp_db_columns db WHERE db.AD_Table_ID=t.AD_Table_ID AND db.IsNotInDB='N');

-- Update: IsMandatory
SELECT '<<< Update: IsMandatory >>>' FROM DUAL;

UPDATE	AD_Column c
SET		IsMandatory = 'Y'
WHERE	IsMandatory = 'N' 
	AND EXISTS 
		(SELECT * FROM temp_db_columns db 
		WHERE c.AD_Column_ID=db.AD_Column_ID AND Nullable='N');

--	set AD_Client_ID/AD_Org_ID to TableDirect
SELECT '<<< set AD_Client_ID, AD_Org_ID to TableDir >>>' FROM DUAL;

UPDATE	AD_Column
SET		AD_Reference_ID = 19
WHERE	(UPPER(ColumnName) = 'AD_CLIENT_ID' OR UPPER(ColumnName) = 'AD_ORG_ID') 
	AND AD_Reference_ID NOT IN (13, 18, 19);

--	set _ID to Type ID (DisplayType)
SELECT '<<< set _ID to Type ID >>>' FROM DUAL;

Update	AD_Column
  Set	AD_Reference_ID = 13    --  ID
WHERE	ColumnName LIKE '%\_ID' ESCAPE '\' 
	AND AD_Reference_ID not in (13, 17, 18, 19, 21, 25, 27, 30, 31, 32, 33, 35)
	AND ColumnName NOT IN ('Record_ID', 'Find_ID')
    -- Buttons with Process
    AND NOT (AD_Reference_ID=28 AND AD_Process_ID IS NOT NULL);

--	set Record_ID/Find_ID
UPDATE	AD_Column
  SET	AD_Reference_ID = 22	--	Number
WHERE	ColumnName = 'Find_ID'
  AND AD_Reference_ID <> 22;
UPDATE	AD_Column
  SET	AD_Reference_ID = 28	-- Button
WHERE	AD_Reference_ID NOT IN (28, 11)	--	Integer 
  AND	ColumnName = 'Record_ID';

--	set UpdatedBy, CreatedBy to Table
SELECT '<<< set UpdatedBy, CreatedBy to Table & User >>>' FROM DUAL;

UPDATE	AD_Column
  SET	AD_Reference_ID = 18,   --  Table
		AD_Reference_Value_ID = 110
WHERE	UPPER(ColumnName) IN ('CREATEDBY', 'UPDATEDBY') 
	AND AD_Reference_ID != 18;

--	set Updated, Created to DateTime
SELECT '<<< set Updated, Created to DateTime >>>' FROM DUAL;

UPDATE	AD_Column
SET		AD_Reference_ID = 16    --  DateTime
WHERE	ColumnName IN ('Created', 'Updated') 
	AND AD_Reference_ID != 16;

--	set Updated, Created to not updateable
SELECT '<<< set Updated, Created to not updateable >>>' FROM DUAL;

UPDATE	AD_Column
  SET	IsUpdateable = 'N'
WHERE	UPPER(ColumnName) IN ('CREATEDBY', 'UPDATEDBY','CREATED', 'UPDATED')
	AND IsUpdateable != 'N';


--	set _Acct
SELECT '<<< set _Acct >>>' FROM DUAL;

UPDATE	AD_Column
SET		AD_Reference_ID = 25,
		AD_Reference_Value_ID = NULL
WHERE	ColumnName LIKE '%\_Acct' ESCAPE '\'
	AND AD_Reference_ID != 25;

--	set IsActive
SELECT '<<< set IsActive >>>' FROM DUAL;

UPDATE	AD_Column
SET		DefaultValue = 'Y'
WHERE	ColumnName = 'IsActive' and DefaultValue IS Null;

--  Set Replication Strategy
SELECT '<<< Set Replication Strategy to Local >>>' FROM DUAL;

UPDATE	AD_Table
SET		ReplicationType = 'L'
WHERE	ReplicationType IS Null;

--  Clean up Field SortNo
SELECT '<<< Clean up Field SortNo >>>' FROM DUAL;

UPDATE	AD_Field
SET		SortNo = Null
WHERE	SortNo = 0;

--
SELECT '<<< Create Identifiers for Column Name for tables w/o identifier >>>' FROM DUAL;
UPDATE	AD_Column c
SET		IsIdentifier = 'Y',
		SeqNo = 1
WHERE	ColumnName = 'Name'
AND	NOT EXISTS
	(SELECT * FROM AD_Table t, AD_Column c2
	 WHERE t.AD_Table_ID=c2.AD_Table_ID
		AND c2.IsIdentifier='Y');

SELECT '<<< Create Identifiers for Column DocumentNo for tables w/o identifier >>>' FROM DUAL;
UPDATE	AD_Column c
SET		IsIdentifier = 'Y',
		SeqNo = 1
WHERE	ColumnName = 'DocumentNo'
AND	NOT EXISTS
	(SELECT * FROM AD_Table t, AD_Column c2
	 WHERE t.AD_Table_ID=c2.AD_Table_ID
		AND c2.IsIdentifier='Y');

SELECT '<<< Create Identifiers for Column Line for tables w/o identifier >>>' FROM DUAL;
UPDATE	AD_Column c
SET		IsIdentifier = 'Y',
		SeqNo = 1
WHERE	ColumnName = 'Line'
AND	NOT EXISTS
	(SELECT * FROM AD_Table t, AD_Column c2
	 WHERE t.AD_Table_ID=c2.AD_Table_ID
		AND c2.IsIdentifier='Y');

--  tables w/o Key
SELECT	ColumnName "<<< Create KeyCol >>>" 
FROM	AD_Column c
WHERE	UPPER(ColumnName) = (Select UPPER(TableName) || '_ID' from ad_table t 
						where c.ad_table_id=t.ad_table_id)
  AND NOT EXISTS
	(SELECT * FROM AD_Table t INNER JOIN AD_Column c2 ON (t.AD_Table_ID=c2.AD_Table_ID)
	 WHERE c.AD_Column_ID=c2.AD_Column_ID AND c2.IsKey='Y') 
  AND c.ColumnName<>'AD_ChangeLog_ID';

UPDATE	AD_Column c
SET		Iskey = 'Y', AD_Reference_ID=13
WHERE	UPPER(ColumnName) = (Select UPPER(TableName) || '_ID' from ad_table t 
						where c.ad_table_id=t.ad_table_id)
  AND NOT EXISTS
	(SELECT * FROM AD_Table t INNER JOIN AD_Column c2 ON (t.AD_Table_ID=c2.AD_Table_ID)
	 WHERE c.AD_Column_ID=c2.AD_Column_ID AND c2.IsKey='Y')
  AND c.ColumnName<>'AD_ChangeLog_ID';

 
SELECT '<<< Key Columns not updateable >>>' FROM DUAL;
UPDATE	AD_Column
  SET	IsUpdateable = 'N' 
WHERE	IsUpdateable='Y' AND (IsKey='Y' OR IsParent='Y');


SELECT '<<< Parent Fields Read/Only >>>' FROM DUAL;

-- Make Parent Fields r/o for tabs with two parents
UPDATE AD_Field
  SET IsReadOnly='Y'
WHERE IsReadOnly='N'
  AND AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE IsParent='Y');

-- Make Parent Fields r/w for tabs with two parents and the defined actual parent column
UPDATE	AD_Field f
  SET	IsReadOnly='N'
WHERE	IsReadOnly='Y' 
  AND EXISTS
	(SELECT * FROM AD_Column c WHERE c.AD_Column_ID=f.AD_Column_ID AND c.IsParent='Y')
  AND AD_Tab_ID IN
	(SELECT AD_Tab_ID
	FROM AD_Tab
	WHERE AD_Column_ID IS NOT NULL
	-- tables with two parent fields
	AND AD_Table_ID IN
		(SELECT AD_Table_ID FROM AD_Table t
		WHERE (SELECT COUNT(*) FROM AD_Column c WHERE c.AD_Table_ID=t.AD_Table_ID AND IsParent='Y')>1)
	);

-- Make Actual Parent fields r/o in tabs with two parent fields
UPDATE	AD_Field f
  SET	IsReadOnly='Y'
WHERE	IsReadOnly='N' 
  AND (AD_Tab_ID, AD_Column_ID) IN
	(SELECT AD_Tab_ID, AD_Column_ID
	FROM AD_Tab
	WHERE AD_Column_ID IS NOT NULL
	-- tables with two parent fields
	AND AD_Table_ID IN
		(SELECT AD_Table_ID FROM AD_Table t
		WHERE (SELECT COUNT(*) FROM AD_Column c WHERE c.AD_Table_ID=t.AD_Table_ID AND IsParent='Y')>1)
	);

--  Exceptions
UPDATE AD_Field
  SET IsReadOnly='N'
WHERE IsReadOnly='Y' AND AD_Field_ID IN (
    4394,   -- role/org
    6523    -- cycle/phase
    );

-- Set Client R/O in subsequent tabs
UPDATE AD_Field
 SET IsReadOnly='Y'
WHERE AD_Field_ID IN
    (SELECT AD_Field_ID
    FROM AD_Field f
        INNER JOIN AD_Tab t on (f.AD_Tab_ID=t.AD_Tab_ID)
        INNER JOIN AD_Column c on (f.AD_Column_ID=c.AD_Column_ID)
    WHERE f.IsReadOnly='N'
      AND t.TabLevel > 0
      AND c.ColumnName='AD_Client_ID');

SELECT '<<< COMMIT >>>' FROM DUAL;
COMMIT;

--
SELECT '<<< Set Validation Rules for TableDir Client & Org >>>' FROM DUAL;

UPDATE AD_Column
SET AD_Val_Rule_ID=104
WHERE UPPER(ColumnName)='AD_ORG_ID' AND AD_Val_Rule_ID IS NULL AND AD_Reference_ID=19;

UPDATE AD_Column
SET IsUpdateable='Y'
WHERE IsUpdateable IS NULL;

UPDATE AD_Column
SET IsUpdateable = 'N'
WHERE (UPPER(ColumnName)='AD_CLIENT_ID' OR UPPER(ColumnName)='AD_ORG_ID')
AND IsUpdateable <> 'N';

---
SELECT '<<< Set Client/Org Login default of primary Tab >>>' FROM DUAL;

UPDATE AD_Column
SET DefaultValue='@#AD_Client_ID@'
WHERE ColumnName = 'AD_Client_ID' AND DefaultValue IS NULL
AND AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Tab WHERE TabLevel = 0);

UPDATE AD_Column
SET DefaultValue='@#AD_Org_ID@'
WHERE ColumnName = 'AD_Org_ID' AND DefaultValue IS NULL
AND AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Tab WHERE TabLevel = 0);


SELECT '<<< Set Client/Org default of dependent Tabs >>>' FROM DUAL;

UPDATE AD_Column
SET DefaultValue='@AD_Client_ID@'
WHERE ColumnName = 'AD_Client_ID' AND DefaultValue IS NULL
AND AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Tab WHERE TabLevel > 0);

UPDATE AD_Column
SET DefaultValue='@AD_Org_ID@'
WHERE ColumnName = 'AD_Org_ID' AND DefaultValue IS NULL
AND AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Tab WHERE TabLevel > 0);

--
SELECT '<<< Set Updateable if Views >>>' FROM DUAL;

UPDATE AD_Column
SET IsUpdateable='N'
WHERE IsUpdateable='Y'
AND AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Table WHERE IsView='Y');

UPDATE AD_Column c
SET IsAlwaysUpdateable='N'
WHERE IsUpdateable='N' AND (IsAlwaysUpdateable='Y' OR IsAlwaysUpdateable IS NULL);

-- 
SELECT '<<< Set System ownership >>>' FROM DUAL;

UPDATE AD_Table SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Column SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
--
UPDATE AD_Window SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Tab SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Field SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Window_Trl SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Tab_Trl SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Field_Trl SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
--
UPDATE AD_Reference SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Ref_List SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Ref_Table SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Reference_Trl SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Ref_List_Trl SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Val_Rule SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
--
UPDATE AD_Element SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Element_Trl SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
--
UPDATE AD_Menu SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
UPDATE AD_Menu_Trl SET AD_Client_ID=0, AD_Org_ID=0 WHERE AD_Client_ID!=0 OR AD_Org_ID!=0;
--

SELECT '<<< COMMIT >>>' FROM DUAL;
COMMIT;

--
SELECT '<<< Sync ColumnName & TableName_ID for TableDir >>>' FROM DUAL;

UPDATE AD_Element e
SET ColumnName = (SELECT t.TableName||'_ID' FROM AD_Table t 
	WHERE UPPER(e.ColumnName)=UPPER(t.TableName)||'_ID' 
	 AND NOT e.ColumnName=t.TableName||'_ID')
WHERE EXISTS (SELECT * FROM AD_Table t 
	WHERE UPPER(e.ColumnName)=UPPER(t.TableName)||'_ID' 
	 AND NOT e.ColumnName=t.TableName||'_ID');

SELECT '<<< COMMIT >>>' FROM DUAL;
COMMIT;

--
-- Update all: String length
SELECT '<<< Update Field length >>>' FROM DUAL;

-- sets all numeric to 22 and date to 7
UPDATE	AD_Column c
SET		FieldLength = (SELECT DataLength FROM temp_db_columns db 
						WHERE c.ad_column_id=db.ad_column_id)
WHERE	FieldLength <> (SELECT DataLength FROM temp_db_columns db 
						WHERE c.ad_column_id=db.ad_column_id);

SELECT '<<< Set Display Length >>>' FROM DUAL;

update ad_field set displaylength = 1		-- Checks, Radio
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id in (20, 24))
and displaylength != 1;

update ad_field set displaylength = 11		-- integer
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id = 11)
and displaylength != 11;

update ad_field set displaylength = 14		-- date fields
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id = 15)
and displaylength != 14;

update ad_field set displaylength = 20		-- datetime
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id = 16)
and displaylength != 20;

update ad_field set displaylength = 26		-- amount, number, quantity
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id in (12, 22, 29, 37))
and displaylength != 26;

update ad_field set displaylength = 26		-- account, location, search
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id in (25, 21, 30, 35))
and displaylength != 26;

update ad_field set displaylength = 14		-- starting point for picks
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id in (13,17,18,19,26,27))
and displaylength != 14;

update ad_field set displaylength = 60		-- Text /Long
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id IN (14,36))
and displaylength NOT IN (20,60);

update ad_field set displaylength = 60		-- Long Strings
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id = 10)
and displaylength > 60;

update ad_field set displaylength = 23		-- starting point for button
where ad_column_id in (select ad_column_id from ad_column where ad_reference_id = 28)
and displaylength != 23;

update ad_field set displaylength = 20		-- document no
where name = 'Document No' and displaylength != 20;

update ad_field set displaylength = 20		-- value, password, alias
where (name like 'Value%' or name = 'Password' or name = 'Alias')
and displaylength > 20;


-- Normalize Length

update ad_field set displaylength = 5
where displaylength != 5 and (displaylength > 1 and displaylength < 6);

update ad_field set displaylength = 11		-- integers = 11
where displaylength is null or displaylength = 0 or displaylength = 10 or displaylength = 12;

update ad_field set displaylength = 17
where displaylength = 16 or displaylength = 18;

update ad_field set displaylength = 20
where displaylength = 19 or displaylength = 21;

update ad_field set displaylength = 23
where displaylength = 22 or displaylength = 24;

update ad_field set displaylength = 26		-- numbers = 26
where displaylength = 25 or displaylength = 27;

update ad_field set displaylength = 29
where displaylength > 29 and displaylength < 35;

select DisplayLength "Odd DisplayLength", Name from ad_field
where displaylength not in (1, 5, 6, 8, 11,14,15,17,20,23,26,29, 40, 60, 2000,4000)
order by 1;
--

--  Set to List where not list/button
UPDATE AD_Column SET AD_Reference_ID=17 
WHERE AD_Reference_ID NOT IN (17,28) AND ColumnName IN ('Posted','DocAction');
--  Set to _Posted Status
UPDATE AD_Column SET AD_Reference_Value_ID=234
WHERE AD_Reference_Value_ID IS NULL AND ColumnName='Posted';

--  Set parameters to empty selection
UPDATE AD_Process_Para
  SET DefaultValue = '-1'
WHERE ColumnName = 'AD_User_ID'
  AND DefaultValue IS NULL;
UPDATE AD_Process_Para
  SET DefaultValue = '0'
WHERE ColumnName IN ('C_BP_Group_ID','M_Product_Category_ID','M_Locator_ID') 
  AND DefaultValue IS NULL;


UPDATE AD_Column
SET DefaultValue='U'
WHERE ColumnName='EntityType' AND (DefaultValue<>'U' OR DefaultValue IS NULL);
UPDATE AD_Column
SET ReadOnlyLogic='@EntityType@=D'
WHERE ColumnName='EntityType' AND ReadOnlyLogic IS NULL;


SELECT '<<< COMMIT >>>' FROM DUAL;
COMMIT;

-- Fini
SELECT '<<< FINI - Dropping Views >>>' FROM DUAL;
DROP VIEW Temp_db_columns;
--
COMMIT;
