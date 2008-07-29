-- Jun 26, 2008 12:37:51 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Element (AD_Org_ID,UpdatedBy,Updated,PrintName,Name,IsActive,Help,EntityType,Description,CreatedBy,Created,ColumnName,AD_Element_ID,AD_Client_ID) VALUES (0,100,TO_TIMESTAMP('2008-06-26 12:37:50','YYYY-MM-DD HH24:MI:SS'),'Autocomplete','Autocomplete','Y','The autocompletion uses all existing values (from the same client and organization) of the field.','D','Automatic completion for textfields',100,TO_TIMESTAMP('2008-06-26 12:37:50','YYYY-MM-DD HH24:MI:SS'),'IsAutocomplete',53655,0)
;

-- Jun 26, 2008 12:37:52 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, PrintName,PO_PrintName,PO_Name,PO_Help,PO_Description,Name,Help,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.PrintName,t.PO_PrintName,t.PO_Name,t.PO_Help,t.PO_Description,t.Name,t.Help,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53655 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 26, 2008 12:37:59 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Element SET EntityType='D',Updated=TO_TIMESTAMP('2008-06-26 12:37:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53655
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
UPDATE AD_PrintFormatItem SET PrintName='Autocomplete', Name='Autocomplete' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53655)
;

-- Jun 26, 2008 12:38:00 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_PrintFormatItem SET PrintName='Autocomplete', Name='Autocomplete' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53655)
;

-- Jun 26, 2008 12:39:11 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Org_ID,Version,UpdatedBy,Updated,SeqNo,Name,IsUpdateable,IsTranslated,IsSyncDatabase,IsSelectionColumn,IsParent,IsMandatory,IsKey,IsIdentifier,IsEncrypted,IsAlwaysUpdateable,IsActive,Help,FieldLength,EntityType,Description,DefaultValue,CreatedBy,Created,ColumnName,AD_Table_ID,AD_Reference_ID,AD_Element_ID,AD_Column_ID,AD_Client_ID) VALUES (0,0,100,TO_TIMESTAMP('2008-06-26 12:39:10','YYYY-MM-DD HH24:MI:SS'),0,'Autocomplete','Y','N','N','N','N','Y','N','N','N','N','Y','The autocompletion uses all existing values (from the same client and organization) of the field.',1,'D','Automatic completion for textfields','N',100,TO_TIMESTAMP('2008-06-26 12:39:10','YYYY-MM-DD HH24:MI:SS'),'IsAutocomplete',101,20,53655,56149,0)
;

-- Jun 26, 2008 12:39:11 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56149 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 26, 2008 12:39:16 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
UPDATE AD_Column SET EntityType='D',Updated=TO_TIMESTAMP('2008-06-26 12:39:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56149
;

-- Jun 26, 2008 12:39:22 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
ALTER TABLE AD_Column ADD COLUMN IsAutocomplete CHAR(1) DEFAULT 'N' CHECK (IsAutocomplete IN ('Y','N')) NOT NULL
;

-- Jun 26, 2008 12:40:30 PM CEST
-- FR[ 2003044 ] Autocomplete for Textfields - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,UpdatedBy,Updated,Name,IsSameLine,IsReadOnly,IsHeading,IsFieldOnly,IsEncrypted,IsDisplayed,IsCentrallyMaintained,IsActive,Help,EntityType,DisplayLength,Description,CreatedBy,Created,AD_Tab_ID,AD_Field_ID,AD_Client_ID) VALUES (56149,0,100,TO_TIMESTAMP('2008-06-26 12:40:30','YYYY-MM-DD HH24:MI:SS'),'Autocomplete','N','N','N','N','N','Y','Y','Y','The autocompletion uses all existing values (from the same client and organization) of the field.','D',1,'Automatic completion for textfields',100,TO_TIMESTAMP('2008-06-26 12:40:30','YYYY-MM-DD HH24:MI:SS'),101,56279,0)
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

-- Jun 26, 2008 12:53:00 PM CEST
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
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@AD_Reference_ID@=10',Updated=TO_TIMESTAMP('2008-06-26 12:53:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56279
;

-- Jul 29, 2008 12:51:57 PM COT
UPDATE AD_Field SET SeqNo=380,Updated=TO_TIMESTAMP('2008-07-29 12:51:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=5122
;

DROP VIEW ad_field_v;

CREATE OR REPLACE VIEW ad_field_v AS 
 SELECT t.ad_window_id, f.ad_tab_id, f.ad_field_id, tbl.ad_table_id, f.ad_column_id, 
 f.name, f.description, f.help, f.isdisplayed, f.displaylogic, f.displaylength, f.seqno, 
 f.sortno, f.issameline, f.isheading, f.isfieldonly, f.isreadonly, f.isencrypted AS isencryptedfield, 
 f.obscuretype, c.columnname, c.columnsql, c.fieldlength, c.vformat, 
 COALESCE(f.defaultvalue, c.defaultvalue) AS defaultvalue, c.iskey, c.isparent, 
 COALESCE(f.ismandatory, c.ismandatory) AS ismandatory, c.isidentifier, c.istranslated, 
 COALESCE(f.ad_reference_value_id, c.ad_reference_value_id) AS ad_reference_value_id, c.callout, 
 COALESCE(f.ad_reference_id, c.ad_reference_id) AS ad_reference_id, 
 COALESCE(f.ad_val_rule_id, c.ad_val_rule_id) AS ad_val_rule_id, c.ad_process_id, 
 c.isalwaysupdateable, c.readonlylogic, c.mandatorylogic, c.isupdateable, 
 c.isencrypted AS isencryptedcolumn, c.isselectioncolumn, tbl.tablename, c.valuemin, c.valuemax, 
 fg.name AS fieldgroup, vr.code AS validationcode, f.included_tab_id, fg.fieldgrouptype, fg.iscollapsedbydefault, 
 COALESCE(f.infofactoryclass, c.infofactoryclass) AS infofactoryclass, c.isautocomplete
   FROM ad_field f
   JOIN ad_tab t ON f.ad_tab_id = t.ad_tab_id
   LEFT JOIN ad_fieldgroup fg ON f.ad_fieldgroup_id = fg.ad_fieldgroup_id
   LEFT JOIN ad_column c ON f.ad_column_id = c.ad_column_id
   JOIN ad_table tbl ON c.ad_table_id = tbl.ad_table_id
   JOIN ad_reference r ON c.ad_reference_id = r.ad_reference_id
   LEFT JOIN ad_val_rule vr ON vr.ad_val_rule_id = COALESCE(f.ad_val_rule_id, c.ad_val_rule_id)
  WHERE f.isactive = 'Y'::bpchar AND c.isactive = 'Y'::bpchar;
  

DROP VIEW ad_field_vt;

CREATE OR REPLACE VIEW ad_field_vt AS 
 SELECT trl.ad_language, t.ad_window_id, f.ad_tab_id, f.ad_field_id, tbl.ad_table_id, f.ad_column_id, trl.name, trl.description, 
 trl.help, f.isdisplayed, f.displaylogic, f.displaylength, f.seqno, f.sortno, f.issameline, f.isheading, f.isfieldonly, f.isreadonly, 
 f.isencrypted AS isencryptedfield, f.obscuretype, c.columnname, c.columnsql, c.fieldlength, c.vformat, COALESCE(f.defaultvalue, c.defaultvalue) AS defaultvalue, 
 c.iskey, c.isparent, COALESCE(f.ismandatory, c.ismandatory) AS ismandatory, c.isidentifier, c.istranslated, 
 COALESCE(f.ad_reference_value_id, c.ad_reference_value_id) AS ad_reference_value_id, c.callout, COALESCE(f.ad_reference_id, c.ad_reference_id) AS ad_reference_id, 
 COALESCE(f.ad_val_rule_id, c.ad_val_rule_id) AS ad_val_rule_id, c.ad_process_id, c.isalwaysupdateable, c.readonlylogic, c.mandatorylogic, c.isupdateable, 
 c.isencrypted AS isencryptedcolumn, c.isselectioncolumn, tbl.tablename, c.valuemin, c.valuemax, fgt.name AS fieldgroup, vr.code AS validationcode, 
 f.included_tab_id, fg.fieldgrouptype, fg.iscollapsedbydefault, COALESCE(f.infofactoryclass, c.infofactoryclass) AS infofactoryclass, c.isautocomplete
   FROM ad_field f
   JOIN ad_field_trl trl ON f.ad_field_id = trl.ad_field_id
   JOIN ad_tab t ON f.ad_tab_id = t.ad_tab_id
   LEFT JOIN ad_fieldgroup fg ON f.ad_fieldgroup_id = fg.ad_fieldgroup_id
   LEFT JOIN ad_fieldgroup_trl fgt ON f.ad_fieldgroup_id = fgt.ad_fieldgroup_id AND trl.ad_language::text = fgt.ad_language::text
   LEFT JOIN ad_column c ON f.ad_column_id = c.ad_column_id
   JOIN ad_table tbl ON c.ad_table_id = tbl.ad_table_id
   JOIN ad_reference r ON c.ad_reference_id = r.ad_reference_id
   LEFT JOIN ad_val_rule vr ON vr.ad_val_rule_id = COALESCE(f.ad_val_rule_id, c.ad_val_rule_id)
  WHERE f.isactive = 'Y'::bpchar AND c.isactive = 'Y'::bpchar;
