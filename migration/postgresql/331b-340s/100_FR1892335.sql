-- Feb 13, 2008 4:58:56 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53349,0,'InfoFactoryClass',TO_TIMESTAMP('2008-02-13 16:58:53','YYYY-MM-DD HH24:MI:SS'),100,'Fully qualified class name that implements the InfoFactory interface','D','Fully qualified class name that implements the InfoFactory interface. This can be use to provide custom Info class for column.','Y','Info Factory Class','Info Factory Class',TO_TIMESTAMP('2008-02-13 16:58:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 13, 2008 4:58:56 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53349 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 13, 2008 4:59:45 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54358,53349,0,10,101,'InfoFactoryClass',TO_TIMESTAMP('2008-02-13 16:59:44','YYYY-MM-DD HH24:MI:SS'),100,'Fully qualified class name that implements the InfoFactory interface','D',255,'Fully qualified class name that implements the InfoFactory interface. This can be use to provide custom Info class for column.','Y','N','N','N','N','N','N','N','N','N','Y','Info Factory Class',0,TO_TIMESTAMP('2008-02-13 16:59:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 13, 2008 4:59:46 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54358 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 13, 2008 4:59:50 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
ALTER TABLE AD_Column ADD COLUMN InfoFactoryClass VARCHAR(255)
;

-- Feb 13, 2008 5:01:42 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54359,53349,0,10,107,'InfoFactoryClass',TO_TIMESTAMP('2008-02-13 17:01:39','YYYY-MM-DD HH24:MI:SS'),100,'Fully qualified class name that implements the InfoFactory interface','D',255,'Fully qualified class name that implements the InfoFactory interface. This can be use to provide custom Info class for column.','Y','N','N','N','N','N','N','N','N','N','Y','Info Factory Class',0,TO_TIMESTAMP('2008-02-13 17:01:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 13, 2008 5:01:42 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54359 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 13, 2008 5:01:47 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
ALTER TABLE AD_Field ADD COLUMN InfoFactoryClass VARCHAR(255)
;

-- Feb 13, 2008 5:07:51 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54358,54403,0,101,TO_TIMESTAMP('2008-02-13 17:07:44','YYYY-MM-DD HH24:MI:SS'),100,'Fully qualified class name that implements the InfoFactory interface',14,'@AD_Reference_ID@=30','D','Fully qualified class name that implements the InfoFactory interface. This can be use to provide custom Info class for column.','Y','Y','Y','N','N','N','N','Y','Info Factory Class',350,0,TO_TIMESTAMP('2008-02-13 17:07:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 13, 2008 5:07:51 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54403 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=54403
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=2574
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=2573
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=160
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=161
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=162
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=166
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=2370
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=169
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=10128
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=4941
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=50188
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=168
;

-- Feb 13, 2008 5:08:32 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=159
;

-- Feb 13, 2008 5:08:33 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=825
;

-- Feb 13, 2008 5:08:33 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=4940
;

-- Feb 13, 2008 5:08:33 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=167
;

-- Feb 13, 2008 5:08:33 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=5121
;

-- Feb 13, 2008 5:08:33 PM SGT
-- [ 1892335 ] Define custom info class at ad_column or ad_field
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=5122
;

drop view ad_field_v;

CREATE OR
REPLACE VIEW ad_field_v AS 
SELECT t.ad_window_id,
	f.ad_tab_id,
	f.ad_field_id,
	tbl.ad_table_id,
	f.ad_column_id,
	f.NAME,
	f.description,
	f.help,
	f.isdisplayed,
	f.displaylogic,
	f.displaylength,
	f.seqno,
	f.sortno,
	f.issameline,
	f.isheading,
	f.isfieldonly,
	f.isreadonly,
	f.isencrypted                                  AS isencryptedfield,
	f.obscuretype,
	c.columnname,
	c.columnsql,
	c.fieldlength,
	c.vformat,
	c.defaultvalue,
	c.iskey,
	c.isparent,
	COALESCE(f.ismandatory, c.ismandatory)         AS ismandatory,
	c.isidentifier,
	c.istranslated,
	COALESCE(f.ad_reference_value_id, c.ad_reference_value_id) AS ad_reference_value_id,
	c.callout,
	COALESCE(f.ad_reference_id, c.ad_reference_id) AS ad_reference_id,
	COALESCE(f.ad_val_rule_id, c.ad_val_rule_id) AS ad_val_rule_id,
	c.ad_process_id,
	c.isalwaysupdateable,
	c.readonlylogic,
	c.isupdateable,
	c.isencrypted                                  AS isencryptedcolumn,
	c.isselectioncolumn,
	tbl.tablename,
	c.valuemin,
	c.valuemax,
	fg.NAME                                        AS fieldgroup,
	vr.code                                        AS validationcode,
	f.Included_Tab_ID,
	fg.FieldGroupType,
	fg.IsCollapsedByDefault						   AS iscollapsedbydefault,
	COALESCE(f.infofactoryclass, c.infofactoryclass) as infofactoryclass 
FROM ((((((AD_FIELD f 
		JOIN AD_TAB t 
		ON ((f.ad_tab_id = t.ad_tab_id))) 
		LEFT JOIN AD_FIELDGROUP fg 
		ON ((f.ad_fieldgroup_id = fg.ad_fieldgroup_id))) 
		LEFT JOIN AD_COLUMN c 
		ON ((f.ad_column_id = c.ad_column_id))) 
		JOIN AD_TABLE tbl 
		ON ((c.ad_table_id = tbl.ad_table_id))) 
		JOIN AD_REFERENCE r 
		ON ((c.ad_reference_id = r.ad_reference_id))) 
		LEFT JOIN AD_VAL_RULE vr 
		ON ((vr.ad_val_rule_id = COALESCE(f.ad_val_rule_id, c.ad_val_rule_id)))) 
WHERE ((f.isactive = 'Y'::bpchar) AND
	(c.isactive = 'Y'::bpchar))
;

drop view ad_field_vt;

CREATE OR
REPLACE VIEW ad_field_vt AS 
SELECT trl.AD_LANGUAGE,
	t.ad_window_id,
	f.ad_tab_id,
	f.ad_field_id,
	tbl.ad_table_id,
	f.ad_column_id,
	trl.NAME,
	trl.description,
	trl.help,
	f.isdisplayed,
	f.displaylogic,
	f.displaylength,
	f.seqno,
	f.sortno,
	f.issameline,
	f.isheading,
	f.isfieldonly,
	f.isreadonly,
	f.isencrypted                                  AS isencryptedfield,
	f.obscuretype,
	c.columnname,
	c.columnsql,
	c.fieldlength,
	c.vformat,
	c.defaultvalue,
	c.iskey,
	c.isparent,
	COALESCE(f.ismandatory, c.ismandatory)         AS ismandatory,
	c.isidentifier,
	c.istranslated,
	COALESCE(f.ad_reference_value_id, c.ad_reference_value_id) AS ad_reference_value_id,
	c.callout,
	COALESCE(f.ad_reference_id, c.ad_reference_id) AS ad_reference_id,
	COALESCE(f.ad_val_rule_id, c.ad_val_rule_id) AS ad_val_rule_id,
	c.ad_process_id,
	c.isalwaysupdateable,
	c.readonlylogic,
	c.isupdateable,
	c.isencrypted                                  AS isencryptedcolumn,
	c.isselectioncolumn,
	tbl.tablename,
	c.valuemin,
	c.valuemax,
	fgt.NAME                                       AS fieldgroup,
	vr.code                                        AS validationcode,
	f.Included_Tab_ID,
	fg.FieldGroupType,
	fg.IsCollapsedByDefault						   AS iscollapsedbydefault ,
	COALESCE(f.infofactoryclass, c.infofactoryclass) as infofactoryclass
FROM (((((((AD_FIELD f 
		JOIN AD_FIELD_TRL trl 
		ON ((f.ad_field_id = trl.ad_field_id))) 
		JOIN AD_TAB t 
		ON ((f.ad_tab_id = t.ad_tab_id))) 
		LEFT JOIN AD_FIELDGROUP fg 
		ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
			LEFT JOIN AD_FIELDGROUP_TRL fgt 
			ON (((f.ad_fieldgroup_id = fgt.ad_fieldgroup_id) AND
			((trl.AD_LANGUAGE)::text = (fgt.AD_LANGUAGE)::text)))) 
		LEFT JOIN AD_COLUMN c 
		ON ((f.ad_column_id = c.ad_column_id))) 
		JOIN AD_TABLE tbl 
		ON ((c.ad_table_id = tbl.ad_table_id))) 
		JOIN AD_REFERENCE r 
		ON ((c.ad_reference_id = r.ad_reference_id))) 
		LEFT JOIN AD_VAL_RULE vr 
		ON ((vr.ad_val_rule_id = COALESCE(f.ad_val_rule_id, c.ad_val_rule_id)))) 
WHERE ((f.isactive = 'Y'::bpchar) AND
	(c.isactive = 'Y'::bpchar))
;
