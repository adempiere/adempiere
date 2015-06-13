-- Sep 25, 2013 12:06:09 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
ALTER TABLE AD_Column ADD COLUMN IsRange CHARACTER(1) DEFAULT 'N'::bpchar NOT NULL;

-- Sep 25, 2013 12:06:09 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
DROP VIEW AD_Field_V;

CREATE OR REPLACE VIEW AD_Field_V AS
     SELECT t.ad_window_id, f.ad_tab_id, f.ad_field_id, tbl.ad_table_id, f.ad_column_id, f.name, f.description, f.help, f.isdisplayed, f.displaylogic,  
            f.displaylength, f.seqno, f.sortno, f.issameline, f.isheading, f.isfieldonly, f.isreadonly, f.isencrypted AS isencryptedfield, f.obscuretype,
            c.columnname, c.columnsql, c.fieldlength, c.vformat, COALESCE(f.defaultvalue, c.defaultvalue) AS defaultvalue, c.iskey, c.isparent, 
            COALESCE(f.ismandatory, c.ismandatory) AS ismandatory, c.isidentifier, c.istranslated, 
            COALESCE(f.ad_reference_value_id, c.ad_reference_value_id) AS ad_reference_value_id, c.callout, 
            COALESCE(f.ad_reference_id, c.ad_reference_id) AS ad_reference_id, COALESCE(f.ad_val_rule_id, c.ad_val_rule_id) AS ad_val_rule_id, 
            c.ad_process_id, c.isalwaysupdateable, c.readonlylogic, c.mandatorylogic, c.isupdateable, c.isencrypted AS isencryptedcolumn, 
            c.isselectioncolumn, c.isrange,tbl.tablename, c.valuemin, c.valuemax, fg.name AS fieldgroup, vr.code AS validationcode, f.included_tab_id, 
            fg.fieldgrouptype, fg.iscollapsedbydefault, COALESCE(f.infofactoryclass, c.infofactoryclass) AS infofactoryclass, c.isautocomplete
     FROM ad_field f
     JOIN ad_tab t ON f.ad_tab_id = t.ad_tab_id
     LEFT JOIN ad_fieldgroup fg ON f.ad_fieldgroup_id = fg.ad_fieldgroup_id
     LEFT JOIN ad_column c ON f.ad_column_id = c.ad_column_id
     JOIN ad_table tbl ON c.ad_table_id = tbl.ad_table_id
     JOIN ad_reference r ON c.ad_reference_id = r.ad_reference_id
     LEFT JOIN ad_val_rule vr ON vr.ad_val_rule_id = COALESCE(f.ad_val_rule_id, c.ad_val_rule_id)
     WHERE f.isactive = 'Y'::bpchar AND c.isactive = 'Y'::bpchar;

-- Sep 25, 2013 12:06:09 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68024,404,0,20,101,'IsRange',TO_TIMESTAMP('2013-09-25 12:06:07','YYYY-MM-DD HH24:MI:SS'),0,'The parameter is a range of values','D',1,'The Range checkbox indicates that this parameter is a range of values.','Y','N','N','N','N','Y','N','N','N','Y','Range',TO_TIMESTAMP('2013-09-25 12:06:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 25, 2013 12:06:09 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68024 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 25, 2013 12:26:04 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68024,69653,0,101,TO_TIMESTAMP('2013-09-25 12:26:02','YYYY-MM-DD HH24:MI:SS'),0,'The parameter is a range of values',1,'D','The Range checkbox indicates that this parameter is a range of values.','Y','Y','Y','N','N','N','N','N','Range',TO_TIMESTAMP('2013-09-25 12:26:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 25, 2013 12:26:04 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69653 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 25, 2013 12:26:25 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_TIMESTAMP('2013-09-25 12:26:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69653
;

-- Sep 25, 2013 12:26:25 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_TIMESTAMP('2013-09-25 12:26:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=167
;

-- Sep 25, 2013 12:26:25 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_TIMESTAMP('2013-09-25 12:26:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5121
;

-- Sep 25, 2013 12:26:26 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=400,Updated=TO_TIMESTAMP('2013-09-25 12:26:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56317
;

-- Sep 25, 2013 12:26:26 PM IST
-- Adding the new column " IsRange" to the " AD_Column"  table for  "Range Based Look Up"
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=410,Updated=TO_TIMESTAMP('2013-09-25 12:26:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5122
;

UPDATE AD_Column SET IsRange='N';

UPDATE AD_Column SET DefaultValue = 'N' WHERE AD_Column_ID=68024;