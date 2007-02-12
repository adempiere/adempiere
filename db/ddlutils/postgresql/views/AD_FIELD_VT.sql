CREATE OR REPLACE VIEW AD_FIELD_VT
(AD_LANGUAGE, AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, 
 AD_COLUMN_ID, NAME, DESCRIPTION, HELP, ISDISPLAYED, 
 DISPLAYLOGIC, DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, 
 ISHEADING, ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, OBSCURETYPE, 
 COLUMNNAME, COLUMNSQL, FIELDLENGTH, VFORMAT, DEFAULTVALUE, 
 ISKEY, ISPARENT, ISMANDATORY, ISIDENTIFIER, ISTRANSLATED, 
 AD_REFERENCE_VALUE_ID, CALLOUT, AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, 
 ISALWAYSUPDATEABLE, READONLYLOGIC, ISUPDATEABLE, ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, 
 TABLENAME, VALUEMIN, VALUEMAX, FIELDGROUP, VALIDATIONCODE)
AS 
SELECT trl.AD_Language, t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
	trl.Name, trl.Description, trl.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
	f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
	f.IsEncrypted AS IsEncryptedField, f.ObscureType,
	c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat, c.DefaultValue, c.IsKey, c.IsParent, 
	COALESCE(f.IsMandatory, c.IsMandatory) AS IsMandatory, 
    c.IsIdentifier, c.IsTranslated, c.AD_Reference_Value_ID, 
	c.Callout, COALESCE(f.AD_Reference_ID, c.AD_Reference_ID) AS AD_Reference_ID, 
    c.AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
	c.ReadOnlyLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fgt.Name AS FieldGroup, vr.Code AS ValidationCode
FROM AD_Field f 
	INNER JOIN AD_Field_Trl trl ON (f.AD_Field_ID = trl.AD_Field_ID)
  INNER JOIN AD_Tab t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FieldGroup_Trl fgt ON 
	(f.AD_FieldGroup_ID = fgt.AD_FieldGroup_ID AND trl.AD_Language=fgt.AD_Language)
  LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID)
	INNER JOIN AD_Table tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID)
	LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y';



