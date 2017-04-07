CREATE OR REPLACE VIEW ad_field_v 

  (AD_WINDOW_ID, AD_TAB_ID, AD_FIELD_ID, AD_TABLE_ID, AD_COLUMN_ID, NAME, DESCRIPTION, HELP, ISDISPLAYED, 
  DISPLAYLOGIC, DISPLAYLENGTH, SEQNO, SORTNO, ISSAMELINE, ISHEADING, ISFIELDONLY, ISREADONLY, ISENCRYPTEDFIELD, 
  OBSCURETYPE, COLUMNNAME, COLUMNSQL, FIELDLENGTH, VFORMAT, DEFAULTVALUE, ISKEY, ISPARENT, ISMANDATORY, ISIDENTIFIER, 
  ISTRANSLATED, AD_REFERENCE_VALUE_ID, CALLOUT, AD_REFERENCE_ID, AD_VAL_RULE_ID, AD_PROCESS_ID, ISALWAYSUPDATEABLE, 
  READONLYLOGIC, MANDATORYLOGIC, ISUPDATEABLE, ISENCRYPTEDCOLUMN, ISSELECTIONCOLUMN, ISRANGE, TABLENAME, VALUEMIN, VALUEMAX, 
  FIELDGROUP, VALIDATIONCODE, INCLUDED_TAB_ID, FIELDGROUPTYPE, ISCOLLAPSEDBYDEFAULT, INFOFACTORYCLASS, ISAUTOCOMPLETE, 
  PREFERREDWIDTH, AD_CHART_ID, ISDISPLAYEDGRID, SEQNOGRID , ISEMBEDDED, ISALLOWCOPY)  AS 
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
 c.isencrypted AS isencryptedcolumn, c.isselectioncolumn, c.isrange, tbl.tablename, c.valuemin, c.valuemax, 
 fg.name AS fieldgroup, vr.code AS validationcode, f.included_tab_id, fg.fieldgrouptype, fg.iscollapsedbydefault, 
 COALESCE(f.infofactoryclass, c.infofactoryclass) AS infofactoryclass, c.isautocomplete, f.preferredwidth, c.ad_chart_id, f.isdisplayedgrid, f.seqnogrid, f.isembedded,
 COALESCE(f.IsAllowCopy, c.IsAllowCopy) AS IsAllowCopy
   FROM ad_field f
   JOIN ad_tab t ON f.ad_tab_id = t.ad_tab_id
   LEFT JOIN ad_fieldgroup fg ON f.ad_fieldgroup_id = fg.ad_fieldgroup_id
   LEFT JOIN ad_column c ON f.ad_column_id = c.ad_column_id
   JOIN ad_table tbl ON c.ad_table_id = tbl.ad_table_id
   JOIN ad_reference r ON c.ad_reference_id = r.ad_reference_id
   LEFT JOIN ad_val_rule vr ON vr.ad_val_rule_id = COALESCE(f.ad_val_rule_id, c.ad_val_rule_id)
  WHERE f.isactive = 'Y' AND c.isactive = 'Y';