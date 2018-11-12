DROP VIEW AD_FIELD_VT ;
CREATE OR REPLACE VIEW AD_FIELD_VT (AD_LANGUAGE, AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, 
    AD_COLUMN_ID, NAME, DESCRIPTION, HELP, ISDISPLAYED, DISPLAYLOGIC, DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, 
    ISHEADING, ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, OBSCURETYPE, COLUMNNAME, COLUMNSQL, FIELDLENGTH, VFORMAT, 
    DEFAULTVALUE, ISKEY, ISPARENT, ISMANDATORY, ISIDENTIFIER, ISTRANSLATED, AD_REFERENCE_VALUE_ID, CALLOUT, 
    AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, ISALWAYSUPDATEABLE, READONLYLOGIC, MANDATORYLOGIC, ISUPDATEABLE, 
    ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, TABLENAME, VALUEMIN, VALUEMAX, FIELDGROUP, VALIDATIONCODE, INCLUDED_TAB_ID, 
    FIELDGROUPTYPE, ISCOLLAPSEDBYDEFAULT, INFOFACTORYCLASS, ISAUTOCOMPLETE,
    PREFERREDWITH, AD_CHART_ID, ISDISPLAYEDGRID, SEQNOGRID ,  ISEMBEDDED , ISALLOWCOPY , AD_CONTEXTINFO_ID , ISQUICKENTRY, AD_IMAGE_ID, AD_FieldDefinition_ID) AS
    SELECT trl.AD_LANGUAGE, t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
   trl.NAME, trl.Description, trl.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
   f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
   f.IsEncrypted AS IsEncryptedField, f.ObscureType,
   c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat,
   COALESCE(f.DefaultValue, c.DefaultValue) AS DefaultValue,
   c.IsKey, c.IsParent, 
   COALESCE(f.IsMandatory, c.IsMandatory) AS IsMandatory, 
      c.IsIdentifier, c.IsTranslated, COALESCE(f.AD_Reference_Value_ID, c.AD_Reference_Value_ID) AS AD_Reference_Value_ID, 
   c.Callout, COALESCE(f.AD_Reference_ID, c.AD_Reference_ID) AS AD_Reference_ID, 
      COALESCE(f.AD_Val_Rule_ID, c.AD_Val_Rule_ID) as AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
   c.ReadOnlyLogic, c.MandatoryLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, c.IsSelectionColumn,
   tbl.TableName, c.ValueMin, c.ValueMax, 
   fgt.NAME AS FieldGroup, vr.Code AS ValidationCode,
   f.Included_Tab_ID, fg.FieldGroupType, fg.IsCollapsedByDefault,
   COALESCE(f.InfoFactoryClass, c.InfoFactoryClass) as InfoFactoryClass,
   c.IsAutocomplete, f.PreferredWidth, c.AD_Chart_ID , f.ISDISPLAYEDGRID, f.SEQNOGRID , f.isEmbedded ,
  COALESCE(f.IsAllowCopy, c.IsAllowCopy) AS IsAllowCopy,
  f.AD_ContextInfo_ID,
  f.IsQuickEntry,
  COALESCE(f.AD_Image_ID, c.AD_Image_ID) AS AD_Image_ID,
 f.AD_FieldDefinition_ID
  FROM AD_FIELD f 
   INNER JOIN AD_FIELD_TRL trl ON (f.AD_Field_ID = trl.AD_Field_ID)
    INNER JOIN AD_TAB t ON (f.AD_Tab_ID = t.AD_Tab_ID)
    LEFT OUTER JOIN AD_FIELDGROUP fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
    LEFT OUTER JOIN AD_FIELDGROUP_TRL fgt ON 
   (f.AD_FieldGroup_ID = fgt.AD_FieldGroup_ID AND trl.AD_LANGUAGE=fgt.AD_LANGUAGE)
    LEFT OUTER JOIN AD_COLUMN c ON (f.AD_Column_ID = c.AD_Column_ID)
   INNER JOIN AD_TABLE tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
   INNER JOIN AD_REFERENCE r ON (c.AD_Reference_ID = r.AD_Reference_ID)
   LEFT OUTER JOIN AD_VAL_RULE vr ON (vr.AD_Val_Rule_ID=COALESCE(f.AD_Val_Rule_ID, c.AD_Val_Rule_ID))
  WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y';