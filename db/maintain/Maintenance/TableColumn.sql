-- Table Column scripts

SELECT * --TableName, ColumnName, FieldLength 
FROM AD_Column c
 INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)
WHERE AD_Column_ID=5518 ;
--WHERE ColumnName = 'Description' AND FieldLength<>255;

SELECT t.AD_Table_ID, t.TableName, c.AD_Column_ID, c.ColumnName, 
	DBA_DisplayType(c.AD_Reference_ID) AS DisplayType, AD_Reference_Value_ID, c.isKey, c.IsParent 
FROM AD_Column c
 INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)
 AND c.ColumnName='EntityType'
ORDER BY c.IsKey Desc, c.IsParent Desc, t.TableName;


SELECT Table_Name, Column_Name, Char_Length, Data_Length
FROM USER_TAB_COLS 
WHERE COLUMN_NAME = 'NAME' AND CHAR_LENGTH<>60;

/**
UPDATE AD_Column
SET FieldLength=60
WHERE ColumnName='Name' AND FieldLength<>60;
COMMIT;

ALTER TABLE W_Store MODIFY WStoreUser NVARCHAR2(60)

UPDATE AD_Column c SET AD_Reference_ID=28 -- Button
WHERE AD_Reference_ID<>28 
  AND ColumnName='Posted';

COMMIT;

**/

--  Window, Tab, Field

SELECT w.Name, t.Name, f.Name, IsDisplayed
FROM AD_Window w
     INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID)
     INNER JOIN AD_Field f ON (t.AD_Tab_ID=f.AD_Tab_ID)
WHERE f.AD_Column_ID=5518 ;

SELECT w.Name, tab.Name, f.Name
FROM AD_Window w
 INNER JOIN AD_Tab tab ON (w.AD_Window_ID=tab.AD_Window_ID)
 INNER JOIN AD_Field f ON (tab.AD_Tab_ID=f.AD_Tab_ID)
 INNER JOIN AD_Column c ON (f.AD_Column_ID=c.AD_Column_ID)
WHERE ColumnName='PA_Goal_ID' 


/**
DELETE FROM AD_Field
WHERE AD_Column_ID=13517;
**/

