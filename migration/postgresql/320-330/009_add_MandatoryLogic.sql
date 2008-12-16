INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50074, 0, 0, 'Y',
             TO_TIMESTAMP ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_TIMESTAMP ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'MandatoryLogic', 'D', 'Mandatory Logic',
             'Mandatory Logic'
            );


INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, 
             help, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id,  issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50218, 0, 0, 'Y',
             TO_TIMESTAMP ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_TIMESTAMP ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Mandatory Logic', 'Logic to determine if field is mandatory (applies only when field is not mandatory in general)', 
             'Logic to determine if field is mandatory (applies only when field is not mandatory in general). 
             format := {expression} [{logic} {expression}]<br> 
	expression := @{context}@{operand}{value} or @{context}@{operand}{value}<br> 
	logic := {|}|{&}<br>
	context := any global or window context <br>
	value := strings or numbers<br>
	logic operators	:= AND or OR with the previous result from left to right <br>
	operand := eq{=}, gt{&gt;}, le{&lt;}, not{~^!} <br>
	Examples: <br>
	@AD_Table_ID@=14 | @Language@!GERGER <br>
	@PriceLimit@>10 | @PriceList@>@PriceActual@<br>
	@Name@>J<br>
	Strings may be in single quotes (optional)', 1,
             'D', 'MandatoryLogic', 101, 14,
             2000, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 50074, 'N',
             'N'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, 
             help,
             iscentrallymaintained, seqno, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50188, 0, 0, 'Y',
             TO_TIMESTAMP ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_TIMESTAMP ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Mandatory Logic', 'Logic to determine if field is mandatory (applies only when field is not mandatory in general)', 
             'Logic to determine if field is mandatory (applies only when field is not mandatory in general). 
             format := {expression} [{logic} {expression}]<br> 
	expression := @{context}@{operand}{value} or @{context}@{operand}{value}<br> 
	logic := {|}|{&}<br>
	context := any global or window context <br>
	value := strings or numbers<br>
	logic operators	:= AND or OR with the previous result from left to right <br>
	operand := eq{=}, gt{&gt;}, le{&lt;}, not{~^!} <br>
	Examples: <br>
	@AD_Table_ID@=14 | @Language@!GERGER <br>
	@PriceLimit@>10 | @PriceList@>@PriceActual@<br>
	@Name@>J<br>
	Strings may be in single quotes (optional)',
             'Y', 275 ,101,
             50218, 'Y', 60, 'N',
             'N', 'N', 'N', 'N', 'D'
            );            
            
         

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                           FROM ad_element
                          WHERE ad_element_id < 1000000)
 WHERE NAME = 'AD_Element';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_column_id) + 1
                           FROM ad_column
                          WHERE ad_column_id < 1000000)
 WHERE NAME = 'AD_Column';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_field_id) + 1
                           FROM ad_field
                          WHERE ad_field_id < 1000000)
 WHERE NAME = 'AD_Field';
 


ALTER TABLE ad_column ADD MandatoryLogic character varying(2000);



DROP VIEW IF EXISTS AD_FIELD_V;
  CREATE OR REPLACE VIEW AD_FIELD_V  AS 
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
	fg.Name AS FieldGroup, vr.Code AS ValidationCode
FROM AD_Field f 
  INNER JOIN AD_Tab t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FieldGroup fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
  LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID)
	INNER JOIN AD_Table tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
	INNER JOIN AD_Reference r ON (c.AD_Reference_ID = r.AD_Reference_ID)
	LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y';
  
     DROP VIEW IF EXISTS AD_FIELD_VT;
     CREATE OR REPLACE VIEW AD_FIELD_VT  AS 
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
  

COMMIT;
