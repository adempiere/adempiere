-- Jun 26, 2008 12:37:51 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Element (AD_Org_ID,UpdatedBy,Updated,PrintName,Name,IsActive,Help,EntityType,Description,CreatedBy,Created,ColumnName,AD_Element_ID,AD_Client_ID) VALUES (0,100,TO_DATE('2008-06-26 12:37:50','YYYY-MM-DD HH24:MI:SS'),'Autocomplete','Autocomplete','Y','The autocompletion uses all existing values (from the same client and organization) of the field.','D','Automatic completion for textfields',100,TO_DATE('2008-06-26 12:37:50','YYYY-MM-DD HH24:MI:SS'),'IsAutocomplete',53655,0)
;

-- Jun 26, 2008 12:37:51 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, PrintName,PO_PrintName,PO_Name,PO_Help,PO_Description,Name,Help,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.PrintName,t.PO_PrintName,t.PO_Name,t.PO_Help,t.PO_Description,t.Name,t.Help,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53655 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 26, 2008 12:37:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Element SET EntityType='D',Updated=TO_DATE('2008-06-26 12:37:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53655
;

-- Jun 26, 2008 12:37:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Column SET ColumnName='IsAutocomplete', Name='Autocomplete', Description='Automatic completion for textfields', Help='The autocompletion uses all existing values (from the same client and organization) of the field.' WHERE AD_Element_ID=53655
;

-- Jun 26, 2008 12:37:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET Name='Autocomplete', Description='Automatic completion for textfields', Help='The autocompletion uses all existing values (from the same client and organization) of the field.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53655) AND IsCentrallyMaintained='Y'
;

-- Jun 26, 2008 12:37:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Process_Para SET ColumnName='IsAutocomplete', Name='Autocomplete', Description='Automatic completion for textfields', Help='The autocompletion uses all existing values (from the same client and organization) of the field.', AD_Element_ID=53655 WHERE UPPER(ColumnName)='ISAUTOCOMPLETE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jun 26, 2008 12:38:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Process_Para SET ColumnName='IsAutocomplete', Name='Autocomplete', Description='Automatic completion for textfields', Help='The autocompletion uses all existing values (from the same client and organization) of the field.' WHERE AD_Element_ID=53655 AND IsCentrallyMaintained='Y'
;

-- Jun 26, 2008 12:38:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_PrintFormatItem pi SET PrintName='Autocomplete', Name='Autocomplete' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53655)
;

-- Jun 26, 2008 12:38:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_PrintFormatItem pi SET PrintName='Autocomplete', Name='Autocomplete' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53655)
;

-- Jun 26, 2008 12:39:11 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Org_ID,Version,UpdatedBy,Updated,SeqNo,Name,IsUpdateable,IsTranslated,IsSyncDatabase,IsSelectionColumn,IsParent,IsMandatory,IsKey,IsIdentifier,IsEncrypted,IsAlwaysUpdateable,IsActive,Help,FieldLength,EntityType,Description,DefaultValue,CreatedBy,Created,ColumnName,AD_Table_ID,AD_Reference_ID,AD_Element_ID,AD_Column_ID,AD_Client_ID) VALUES (0,0,100,TO_DATE('2008-06-26 12:39:10','YYYY-MM-DD HH24:MI:SS'),0,'Autocomplete','Y','N','N','N','N','Y','N','N','N','N','Y','The autocompletion uses all existing values (from the same client and organization) of the field.',1,'D','Automatic completion for textfields','N',100,TO_DATE('2008-06-26 12:39:10','YYYY-MM-DD HH24:MI:SS'),'IsAutocomplete',101,20,53655,56149,0)
;

-- Jun 26, 2008 12:39:11 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56149 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 26, 2008 12:39:16 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-06-26 12:39:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56149
;

-- Jun 26, 2008 12:39:22 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
ALTER TABLE AD_Column ADD IsAutocomplete CHAR(1) DEFAULT 'N' CHECK (IsAutocomplete IN ('Y','N')) NOT NULL
;

-- Jun 26, 2008 12:40:30 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,UpdatedBy,Updated,Name,IsSameLine,IsReadOnly,IsHeading,IsFieldOnly,IsEncrypted,IsDisplayed,IsCentrallyMaintained,IsActive,Help,EntityType,DisplayLength,Description,CreatedBy,Created,AD_Tab_ID,AD_Field_ID,AD_Client_ID) VALUES (56149,0,100,TO_DATE('2008-06-26 12:40:30','YYYY-MM-DD HH24:MI:SS'),'Autocomplete','N','N','N','N','N','Y','Y','Y','The autocompletion uses all existing values (from the same client and organization) of the field.','D',1,'Automatic completion for textfields',100,TO_DATE('2008-06-26 12:40:30','YYYY-MM-DD HH24:MI:SS'),101,56279,0)
;

-- Jun 26, 2008 12:40:31 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Name,Help,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Name,t.Help,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56279 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56279
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=2526
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=171
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=54403
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=2574
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=2573
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=160
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=161
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=162
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=166
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=2370
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=169
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=10128
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=4941
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=50188
;

-- Jun 26, 2008 12:52:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=168
;

-- Jun 26, 2008 12:53:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=159
;

-- Jun 26, 2008 12:53:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=825
;

-- Jun 26, 2008 12:53:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=4940
;

-- Jun 26, 2008 12:53:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=167
;

-- Jun 26, 2008 12:53:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=5121
;

-- Jun 26, 2008 12:53:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=5122
;

-- Jun 26, 2008 12:53:41 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@AD_Reference_ID@=10',Updated=TO_DATE('2008-06-26 12:53:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56279
;

-- Jul 29, 2008 12:51:57 PM COT
UPDATE AD_Field SET SeqNo=380,Updated=TO_DATE('2008-07-29 12:51:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=5122
;

  drop view AD_FIELD_V; 
  
  CREATE OR REPLACE VIEW AD_FIELD_V 
  (AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, AD_COLUMN_ID, NAME, DESCRIPTION, HELP, ISDISPLAYED, 
  DISPLAYLOGIC, DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, ISHEADING, ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, 
  OBSCURETYPE, COLUMNNAME, COLUMNSQL, FIELDLENGTH, VFORMAT, DEFAULTVALUE, ISKEY, ISPARENT, ISMANDATORY, ISIDENTIFIER, 
  ISTRANSLATED, AD_REFERENCE_VALUE_ID, CALLOUT, AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, ISALWAYSUPDATEABLE, 
  READONLYLOGIC, MANDATORYLOGIC, ISUPDATEABLE, ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, TABLENAME, VALUEMIN, VALUEMAX, 
  FIELDGROUP, VALIDATIONCODE, INCLUDED_TAB_ID, FIELDGROUPTYPE, ISCOLLAPSEDBYDEFAULT, INFOFACTORYCLASS, ISAUTOCOMPLETE) AS 
  SELECT t.AD_Window_ID, f.AD_Tab_ID, f.AD_Field_ID, tbl.AD_Table_ID, f.AD_Column_ID, 
 f.NAME, f.Description, f.Help, f.IsDisplayed, f.DisplayLogic, f.DisplayLength, 
 f.SeqNo, f.SortNo, f.IsSameLine, f.IsHeading, f.IsFieldOnly, f.IsReadOnly, 
 f.IsEncrypted AS IsEncryptedField, f.ObscureType,
 c.ColumnName, c.ColumnSQL, c.FieldLength, c.VFormat,
 COALESCE(f.DefaultValue, c.DefaultValue) AS DefaultValue,
 c.IsKey, c.IsParent, 
 COALESCE(f.IsMandatory, c.IsMandatory) AS IsMandatory, 
    c.IsIdentifier, c.IsTranslated, COALESCE(f.AD_Reference_Value_ID, c.AD_Reference_Value_ID) AS AD_Reference_Value_ID, 
 c.Callout, COALESCE(f.AD_Reference_ID, c.AD_Reference_ID) AS AD_Reference_ID, 
    COALESCE(f.AD_Val_Rule_ID, c.AD_Val_Rule_ID) AS AD_Val_Rule_ID, c.AD_Process_ID, c.IsAlwaysUpdateable,
 c.ReadOnlyLogic, c.MandatoryLogic, c.IsUpdateable, c.IsEncrypted AS IsEncryptedColumn, 
    c.IsSelectionColumn,
 tbl.TableName, c.ValueMin, c.ValueMax,
 fg.NAME AS FieldGroup, vr.Code AS ValidationCode,
 f.Included_Tab_ID, fg.FieldGroupType, fg.IsCollapsedByDefault,
 COALESCE(f.InfoFactoryClass, c.InfoFactoryClass) as InfoFactoryClass,
 c.IsAutocomplete
FROM AD_FIELD f 
  INNER JOIN AD_TAB t ON (f.AD_Tab_ID = t.AD_Tab_ID)
  LEFT OUTER JOIN AD_FIELDGROUP fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
  LEFT OUTER JOIN AD_COLUMN c ON (f.AD_Column_ID = c.AD_Column_ID)
 INNER JOIN AD_TABLE tbl ON (c.AD_Table_ID = tbl.AD_Table_ID)
 INNER JOIN AD_REFERENCE r ON (c.AD_Reference_ID = r.AD_Reference_ID)
 LEFT OUTER JOIN AD_VAL_RULE vr ON (vr.AD_Val_Rule_ID = COALESCE(f.AD_Val_Rule_ID, c.AD_Val_Rule_ID))
WHERE f.IsActive = 'Y' 
  AND c.IsActive = 'Y';
  
    drop view AD_FIELD_VT; 
    
    CREATE OR REPLACE VIEW AD_FIELD_VT (AD_LANGUAGE, AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, 
    AD_COLUMN_ID, NAME, DESCRIPTION, HELP, ISDISPLAYED, DISPLAYLOGIC, DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, 
    ISHEADING, ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, OBSCURETYPE, COLUMNNAME, COLUMNSQL, FIELDLENGTH, VFORMAT, 
    DEFAULTVALUE, ISKEY, ISPARENT, ISMANDATORY, ISIDENTIFIER, ISTRANSLATED, AD_REFERENCE_VALUE_ID, CALLOUT, 
    AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, ISALWAYSUPDATEABLE, READONLYLOGIC, MANDATORYLOGIC, ISUPDATEABLE, 
    ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, TABLENAME, VALUEMIN, VALUEMAX, FIELDGROUP, VALIDATIONCODE, INCLUDED_TAB_ID, 
    FIELDGROUPTYPE, ISCOLLAPSEDBYDEFAULT, INFOFACTORYCLASS, ISAUTOCOMPLETE) AS 
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
   c.IsAutocomplete
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
