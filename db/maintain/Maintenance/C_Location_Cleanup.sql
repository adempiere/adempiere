/**
 *	Delete unused Locations
 */
CREATE OR REPLACE VIEW TEMP_LOCATION_V
AS
SELECT C_Location_ID FROM AD_OrgInfo
UNION
SELECT C_Location_ID FROM C_AcctSchema_Element
UNION
SELECT C_Location_ID FROM C_BPartner_Location
UNION
SELECT C_Location_ID FROM C_Bank
UNION
SELECT C_Location_ID FROM M_Warehouse
UNION
SELECT C_Location_ID FROM PA_ReportColumn
UNION
SELECT C_Location_ID FROM PA_Reportsource
UNION
SELECT C_Location_ID FROM Test
/
/**	To verify if all usages found
SELECT t.AD_Table_ID, t.TableName, c.AD_Column_ID, c.ColumnName, 
	DBA_DisplayType(c.AD_Reference_ID) AS DisplayType, AD_Reference_Value_ID, c.isKey, c.IsParent 
FROM AD_Table t, AD_Column c
WHERE t.AD_Table_ID=c.AD_Table_ID
  AND c.ColumnName='C_Location_ID'
ORDER BY c.IsKey Desc, c.IsParent Desc, t.TableName;
**/

DELETE	C_Location
WHERE C_Location_ID NOT IN
(SELECT DISTINCT C_Location_ID FROM TEMP_LOCATION_V WHERE C_Location_ID IS NOT NULL)
/
COMMIT
/
DROP VIEW TEMP_LOCATION_V
/
