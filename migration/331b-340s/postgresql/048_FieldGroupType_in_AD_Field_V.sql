drop view ad_field_v
;

drop view ad_field_vt
;

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
	C.columnname,
	C.columnsql,
	C.fieldlength,
	C.vformat,
	C.defaultvalue,
	C.iskey,
	C.isparent,
	COALESCE(f.ismandatory, C.ismandatory)         AS ismandatory,
	C.isidentifier,
	C.istranslated,
	C.ad_reference_value_id,
	C.callout,
	COALESCE(f.ad_reference_id, C.ad_reference_id) AS ad_reference_id,
	C.ad_val_rule_id,
	C.ad_process_id,
	C.isalwaysupdateable,
	C.readonlylogic,
	C.isupdateable,
	C.isencrypted                                  AS isencryptedcolumn,
	C.isselectioncolumn,
	tbl.tablename,
	C.valuemin,
	C.valuemax,
	fg.NAME                                        AS fieldgroup,
	vr.code                                        AS validationcode,
	f.Included_Tab_ID,
	fg.FieldGroupType 
FROM ((((((ad_field f 
		JOIN ad_tab t 
		ON ((f.ad_tab_id = t.ad_tab_id))) 
		LEFT JOIN ad_fieldgroup fg 
		ON ((f.ad_fieldgroup_id = fg.ad_fieldgroup_id))) 
		LEFT JOIN ad_column C 
		ON ((f.ad_column_id = C.ad_column_id))) 
		JOIN ad_table tbl 
		ON ((C.ad_table_id = tbl.ad_table_id))) 
		JOIN ad_reference r 
		ON ((C.ad_reference_id = r.ad_reference_id))) 
		LEFT JOIN ad_val_rule vr 
		ON ((C.ad_val_rule_id = vr.ad_val_rule_id))) 
WHERE ((f.isactive = 'Y'::bpchar) AND
	(C.isactive = 'Y'::bpchar))
;

CREATE OR
REPLACE VIEW ad_field_vt AS 
SELECT trl.ad_language,
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
	C.columnname,
	C.columnsql,
	C.fieldlength,
	C.vformat,
	C.defaultvalue,
	C.iskey,
	C.isparent,
	COALESCE(f.ismandatory, C.ismandatory)         AS ismandatory,
	C.isidentifier,
	C.istranslated,
	C.ad_reference_value_id,
	C.callout,
	COALESCE(f.ad_reference_id, C.ad_reference_id) AS ad_reference_id,
	C.ad_val_rule_id,
	C.ad_process_id,
	C.isalwaysupdateable,
	C.readonlylogic,
	C.isupdateable,
	C.isencrypted                                  AS isencryptedcolumn,
	C.isselectioncolumn,
	tbl.tablename,
	C.valuemin,
	C.valuemax,
	fgt.NAME                                       AS fieldgroup,
	vr.code                                        AS validationcode,
	f.Included_Tab_ID,
	fg.FieldGroupType 
FROM (((((((ad_field f 
		JOIN ad_field_trl trl 
		ON ((f.ad_field_id = trl.ad_field_id))) 
		JOIN ad_tab t 
		ON ((f.ad_tab_id = t.ad_tab_id))) 
		LEFT JOIN AD_FIELDGROUP fg 
		ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID) 
			LEFT JOIN ad_fieldgroup_trl fgt 
			ON (((f.ad_fieldgroup_id = fgt.ad_fieldgroup_id) AND
			((trl.ad_language)::text = (fgt.ad_language)::text)))) 
		LEFT JOIN ad_column C 
		ON ((f.ad_column_id = C.ad_column_id))) 
		JOIN ad_table tbl 
		ON ((C.ad_table_id = tbl.ad_table_id))) 
		JOIN ad_reference r 
		ON ((C.ad_reference_id = r.ad_reference_id))) 
		LEFT JOIN ad_val_rule vr 
		ON ((C.ad_val_rule_id = vr.ad_val_rule_id))) 
WHERE ((f.isactive = 'Y'::bpchar) AND
	(C.isactive = 'Y'::bpchar))
;