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
FROM ((((((AD_FIELD f 
		JOIN AD_TAB t 
		ON ((f.ad_tab_id = t.ad_tab_id))) 
		LEFT JOIN AD_FIELDGROUP fg 
		ON ((f.ad_fieldgroup_id = fg.ad_fieldgroup_id))) 
		LEFT JOIN AD_COLUMN C 
		ON ((f.ad_column_id = C.ad_column_id))) 
		JOIN AD_TABLE tbl 
		ON ((C.ad_table_id = tbl.ad_table_id))) 
		JOIN AD_REFERENCE r 
		ON ((C.ad_reference_id = r.ad_reference_id))) 
		LEFT JOIN AD_VAL_RULE vr 
		ON ((C.ad_val_rule_id = vr.ad_val_rule_id))) 
WHERE ((f.isactive = 'Y'::bpchar) AND
	(C.isactive = 'Y'::bpchar))