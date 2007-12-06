--@author - fer_luck @ centuryono
--Add Detail column
--The fields are already in the databse. Just needs to update them
BEGIN;
update ad_field set seqno = seqno + 10 where ad_tab_id = 107 and seqno > 70;

update ad_field 
set ad_tab_id = 107, isdisplayed = 'Y', seqno = 80
where ad_column_id = 8547;

--It's not physically in the database, so here we create it
alter table ad_field add included_tab_id numeric(10);

--Modify the views
drop view ad_field_v;
CREATE OR REPLACE VIEW AD_FIELD_V
(AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, AD_COLUMN_ID, 
 NAME, DESCRIPTION, HELP, ISDISPLAYED, DISPLAYLOGIC, 
 DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, ISHEADING, 
 ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, OBSCURETYPE, COLUMNNAME, 
 COLUMNSQL, FIELDLENGTH, VFORMAT, DEFAULTVALUE, ISKEY, 
 ISPARENT, ISMANDATORY, ISIDENTIFIER, ISTRANSLATED, AD_REFERENCE_VALUE_ID, 
 CALLOUT, AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, ISALWAYSUPDATEABLE, 
 READONLYLOGIC, MANDATORYLOGIC, ISUPDATEABLE, ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, TABLENAME, 
 VALUEMIN, VALUEMAX, FIELDGROUP, VALIDATIONCODE)
AS 
SELECT t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
	f.Name, f.Description, f.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
	f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
	f.IsEncrypted AS IsEncryptedField, f.ObscureType,
	c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat, c.DefaultValue, c.IsKey, c.IsParent, 
	COALESCE(f.IsMandatory, c.IsMandatory) AS IsMandatory, 
    c.IsIdentifier, c.IsTranslated, c.AD_Reference_Value_ID, 
	c.Callout, COALESCE(f.AD_Reference_ID, c.AD_Reference_ID) AS AD_Reference_ID, 
    c.AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
	c.ReadOnlyLogic, c.MandatoryLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, 
    c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fg.Name AS FieldGroup, vr.Code AS ValidationCode, f.included_tab_id
FROM AD_Field f 
  INNER JOIN AD_Tab t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FieldGroup fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
  LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID)
	INNER JOIN AD_Table tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID)
	LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y';


drop view ad_field_vt;
CREATE OR REPLACE VIEW AD_FIELD_VT
(AD_LANGUAGE, AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, 
 AD_COLUMN_ID, NAME, DESCRIPTION, HELP, ISDISPLAYED, 
 DISPLAYLOGIC, DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, 
 ISHEADING, ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, OBSCURETYPE, 
 COLUMNNAME, COLUMNSQL, FIELDLENGTH, VFORMAT, DEFAULTVALUE, 
 ISKEY, ISPARENT, ISMANDATORY, ISIDENTIFIER, ISTRANSLATED, 
 AD_REFERENCE_VALUE_ID, CALLOUT, AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, 
 ISALWAYSUPDATEABLE, READONLYLOGIC, MANDATORYLOGIC, ISUPDATEABLE, ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, 
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
	c.ReadOnlyLogic, c.MandatoryLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, c.IsSelectionColumn,
	tbl.TableName, c.ValueMin, c.ValueMax, 
	fgt.Name AS FieldGroup, vr.Code AS ValidationCode,  f.included_tab_id
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

--Add the Grid Collapse and Tabbed item navigation
INSERT INTO AD_ELEMENT
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME, printname
            )
     VALUES (53002, 0, 0, 'Y',
             TO_DATE ('07/18/2007 14:23:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/18/2007 14:23:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'FieldGroupType', 'D', 'Field Group Type', 'Field Group Type'
            );

insert into ad_reference
          (ad_reference_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby,
             name, description, help,
             validationtype, entitytype)
     VALUES (53000, 0, 0, 'Y',
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'AD_FieldGroup', 'Field Group Type', '',
             'L', 'D');

insert into ad_ref_list 
          (ad_ref_list_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby,
             value, name,
             ad_reference_id, entitytype)
VALUES(53000, 0, 0, 'Y',
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100,
             'T', 'Tab',
             53000, 'D');
insert into ad_ref_list 
          (ad_ref_list_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby,
             value, name,
             ad_reference_id, entitytype)
VALUES(53001, 0, 0, 'Y',
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100,
             'L', 'Label',
             53000, 'D');
insert into ad_ref_list 
          (ad_ref_list_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby,
             value, name,
             ad_reference_id, entitytype)
VALUES(53002, 0, 0, 'Y',
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100,
             'C', 'Collapse',
             53000, 'D');

INSERT INTO AD_COLUMN
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description,
             HELP,
             VERSION, entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable, ad_reference_value_id
            )
     VALUES (53002, 0, 0, 'Y',
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('07/18/2007 14:22:51', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Field Group Type', 'Field Group',
             'Field Group.',
             0, 'D', 'FieldGroupType', 414, 17,
             10, 'N', 'N', 'N', 'Y',
             'N', null, 'N', 'N',
             'N', 53002, 'Y',
             'N', 53000
            );

ALTER TABLE ad_fieldgroup ADD fieldgrouptype char(1);

INSERT INTO AD_FIELD
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, sortno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (53002, 0, 0, 'Y',
             TO_DATE ('07/18/2007 14:23:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('07/18/2007 14:23:17', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Field Group Type', 'Field Group Type',
             'The Field Group type',
             'Y', 342, 53002, 'Y',
             10, 'N', 60, 0, 'N', 'N',
             'N', 'N', 'D'
            );

COMMIT;

-- NOTE: Don't forget to run the three processes:
-- 1 - Add missing translations in the language screen
-- 2 - Synchronize terminology
-- 3 - Check sequences
