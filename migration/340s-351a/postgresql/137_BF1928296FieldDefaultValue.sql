DROP VIEW ad_field_v;
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
	COALESCE(f.defaultvalue, c.defaultvalue) AS defaultvalue,
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
	(c.isactive = 'Y'::bpchar));
DROP VIEW ad_field_vt;
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
	COALESCE(f.defaultvalue, c.defaultvalue) AS defaultvalue,
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
	(c.isactive = 'Y'::bpchar));