-- FR [ 1732786 ] DefaultValue on AD_Field
-- https://sourceforge.net/tracker/index.php?func=detail&aid=1732786&group_id=176962&atid=879335
--
--delete from AD_Column where AD_Column_ID=53265;
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version,AD_Client_ID,AD_Column_ID)
VALUES (0,272,14,107,'DefaultValue',TO_TIMESTAMP('2007-10-22 14:22:58','YYYY-MM-DD HH24:MI:SS'),0,'Default value hierarchy, separated by ;','D',2000,'The defaults are evaluated in the order of definition, the first not null value becomes the default value of the column. The values are separated by comma or semicolon. a) Literals:. ''Text'' or 123 b) Variables - in format @Variable@ - Login e.g. #Date, #AD_Org_ID, #AD_Client_ID - Accounting Schema: e.g. $C_AcctSchema_ID, $C_Calendar_ID - Global defaults: e.g. DateFormat - Window values (all Picks, CheckBoxes, RadioButtons, and DateDoc/DateAcct) c) SQL code with the tag: @SQL=SELECT something AS DefaultValue FROM ... The SQL statement can contain variables.  There can be no other value other than the SQL statement. The default is only evaluated, if no user preference is defined.  Default definitions are ignored for record columns as Key, Parent, Client as well as Buttons.','Y','N','N','N','N','N','N','N','N','N','Y','Default Logic',0,TO_TIMESTAMP('2007-10-22 14:22:58','YYYY-MM-DD HH24:MI:SS'),0,0,0,53265);
--
--delete from AD_Field where AD_Field_ID=53280;
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,AD_Client_ID,UpdatedBy,SeqNo,AD_Field_ID)
VALUES (53265,0,107,TO_TIMESTAMP('2007-10-22 14:30:33','YYYY-MM-DD HH24:MI:SS'),0,'Default value hierarchy, separated by ;',60,'D','The defaults are evaluated in the order of definition, the first not null value becomes the default value of the column. The values are separated by comma or semicolon. a) Literals:. ''Text'' or 123 b) Variables - in format @Variable@ - Login e.g. #Date, #AD_Org_ID, #AD_Client_ID - Accounting Schema: e.g. $C_AcctSchema_ID, $C_Calendar_ID - Global defaults: e.g. DateFormat - Window values (all Picks, CheckBoxes, RadioButtons, and DateDoc/DateAcct) c) SQL code with the tag: @SQL=SELECT something AS DefaultValue FROM ... The SQL statement can contain variables.  There can be no other value other than the SQL statement. The default is only evaluated, if no user preference is defined.  Default definitions are ignored for record columns as Key, Parent, Client as well as Buttons.','Y','Y','Y','N','N','N','N','N','Default Logic',TO_TIMESTAMP('2007-10-22 14:30:33','YYYY-MM-DD HH24:MI:SS'),0,0,270,53280);
--
commit;
--
ALTER TABLE AD_Field ADD DefaultValue VARCHAR(2000);
--
CREATE OR REPLACE VIEW AD_FIELD_V
(AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, AD_COLUMN_ID, 
 NAME, DESCRIPTION, HELP, ISDISPLAYED, DISPLAYLOGIC, 
 DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, ISHEADING, 
 ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, OBSCURETYPE, COLUMNNAME, 
 COLUMNSQL, FIELDLENGTH, VFORMAT, DEFAULTVALUE, ISKEY, 
 ISPARENT, ISMANDATORY, ISIDENTIFIER, ISTRANSLATED, AD_REFERENCE_VALUE_ID, 
 CALLOUT, AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, ISALWAYSUPDATEABLE, 
 READONLYLOGIC, MANDATORYLOGIC, ISUPDATEABLE, ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, TABLENAME, 
 VALUEMIN, VALUEMAX, FIELDGROUP, VALIDATIONCODE, INCLUDED_TAB_ID)
AS 
SELECT t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
	f.Name, f.Description, f.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
	f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
	f.IsEncrypted AS IsEncryptedField, f.ObscureType,
	c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat,
	COALESCE(f.DefaultValue, c.DefaultValue) as DefaultValue,
	c.IsKey, c.IsParent, 
	COALESCE(f.IsMandatory, c.IsMandatory) AS IsMandatory, 
    c.IsIdentifier, c.IsTranslated, c.AD_Reference_Value_ID, 
	c.Callout, COALESCE(f.AD_Reference_ID, c.AD_Reference_ID) AS AD_Reference_ID, 
    c.AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
	c.ReadOnlyLogic, c.MandatoryLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, 
    c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fg.Name AS FieldGroup, vr.Code AS ValidationCode,
	f.Included_Tab_ID
FROM AD_Field f 
  INNER JOIN AD_Tab t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FieldGroup fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
  LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID)
	INNER JOIN AD_Table tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID)
	LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y';
--
CREATE OR REPLACE VIEW AD_FIELD_VT
(AD_LANGUAGE, AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, 
 AD_COLUMN_ID, NAME, DESCRIPTION, HELP, ISDISPLAYED, 
 DISPLAYLOGIC, DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, 
 ISHEADING, ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, OBSCURETYPE, 
 COLUMNNAME, COLUMNSQL, FIELDLENGTH, VFORMAT, DEFAULTVALUE, 
 ISKEY, ISPARENT, ISMANDATORY, ISIDENTIFIER, ISTRANSLATED, 
 AD_REFERENCE_VALUE_ID, CALLOUT, AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, 
 ISALWAYSUPDATEABLE, READONLYLOGIC, MANDATORYLOGIC, ISUPDATEABLE, ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, 
 TABLENAME, VALUEMIN, VALUEMAX, FIELDGROUP, VALIDATIONCODE, INCLUDED_TAB_ID)
AS 
SELECT trl.AD_Language, t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
	trl.Name, trl.Description, trl.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
	f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
	f.IsEncrypted AS IsEncryptedField, f.ObscureType,
	c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat,
	COALESCE(f.DefaultValue, c.DefaultValue) as DefaultValue,
	c.IsKey, c.IsParent, 
	COALESCE(f.IsMandatory, c.IsMandatory) AS IsMandatory, 
    c.IsIdentifier, c.IsTranslated, c.AD_Reference_Value_ID, 
	c.Callout, COALESCE(f.AD_Reference_ID, c.AD_Reference_ID) AS AD_Reference_ID, 
    c.AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
	c.ReadOnlyLogic, c.MandatoryLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fgt.Name AS FieldGroup, vr.Code AS ValidationCode,
	f.Included_Tab_ID
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
--
-- NOTE: Don't forget to run the three processes:
-- 1 - Add missing translations in the language screen
-- 2 - Synchronize terminology
-- 3 - Check sequences
