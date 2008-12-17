Insert into AD_ELEMENT
	(AD_ELEMENT_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,
	UPDATED,UPDATEDBY,COLUMNNAME,ENTITYTYPE,NAME,PRINTNAME,DESCRIPTION,
	HELP,PO_NAME,PO_PRINTNAME,PO_DESCRIPTION,PO_HELP)
	values
	(50064,0,0,'Y',to_date('27.02.07','DD.MM.RR'),0,
	to_date('27.02.07','DD.MM.RR'),0,'JasperProcess_ID','D',
	'Jasper Process','Jasper Process',
	'The Jasper Process used by the printengine if any process defined'
	,null,null,null,null,null);

Insert into AD_VAL_RULE
	(AD_VAL_RULE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,
	UPDATED,UPDATEDBY,NAME,DESCRIPTION,TYPE,CODE,ENTITYTYPE)
	values
	(270,0,0,'Y',to_date('27.02.07','DD.MM.RR'),0,to_date('27.02.07','DD.MM.RR'),
	0,'AD_Process Jasper Reports',null,'S','AD_Process.JasperReport IS NOT NULL','D');

UPDATE AD_VAL_RULE
	SET CODE = 'AD_Process.IsReport=''Y'' AND AD_Process.JasperReport IS NULL'
	WHERE NAME = 'AD_Process Reports';

Insert into AD_REFERENCE
	(AD_REFERENCE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,
	UPDATED,UPDATEDBY,NAME,DESCRIPTION,HELP,VALIDATIONTYPE,VFORMAT,ENTITYTYPE)
	values
	(400,0,0,'Y',to_date('27.02.07','DD.MM.RR'),0,to_date('27.02.07','DD.MM.RR'),
	0,'AD_Process_JasperReports',null,null,'T',null,'D');

Insert into AD_REF_TABLE
	(AD_REFERENCE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,
	UPDATED,UPDATEDBY,AD_TABLE_ID,AD_KEY,AD_DISPLAY,ISVALUEDISPLAYED,
	WHERECLAUSE,ORDERBYCLAUSE,ENTITYTYPE)
	values
	(400,0,0,'Y',to_date('27.02.07','DD.MM.RR'),0,to_date('27.02.07','DD.MM.RR'),
	0,284,2801,2809,'N','AD_Process.JasperReport IS NOT NULL',null,'D');

Insert into AD_COLUMN
	(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,
	CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,
	COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,
	AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,
	ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,
	CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,
	AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL)
	values
	(50209,0,0,'Y',to_date('27.02.07','DD.MM.RR'),to_date('27.02.07','DD.MM.RR'),0,
	0,'Jasper Process','The Jasper Process used by the printengine if any process defined',
	null,1,'D','JasperProcess_ID',493,18,400,null,22,null,'N','N','N','Y',null,'N',0,'N',
	'N',null,null,null,null,'N',50064,null,'N','N',null);

Insert into AD_FIELD
	(AD_FIELD_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,
	UPDATED,UPDATEDBY,NAME,DESCRIPTION,HELP,ISCENTRALLYMAINTAINED,
	AD_TAB_ID,AD_COLUMN_ID,AD_FIELDGROUP_ID,ISDISPLAYED,DISPLAYLOGIC,
	DISPLAYLENGTH,ISREADONLY,SEQNO,SORTNO,ISSAMELINE,ISHEADING,
	ISFIELDONLY,ISENCRYPTED,ENTITYTYPE,OBSCURETYPE,AD_REFERENCE_ID,
	ISMANDATORY)
	values
	(50179,0,0,'Y',to_date('27.02.07','DD.MM.RR'),0,to_date('27.02.07','DD.MM.RR'),
	0,'Jasper Process','The Jasper Process used by the printengine if any process defined',
	null,'Y',425,50209,null,'Y',null,14,'N',195,0,'N','N','N','N','D',null,null,null);

COMMIT;

UPDATE ad_sequence
  SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                        FROM ad_element
                        WHERE ad_element_id < 1000000)
  WHERE NAME = 'AD_Element';

UPDATE ad_sequence
  SET currentnextsys = (SELECT MAX (ad_val_rule_id) + 1
                        FROM ad_val_rule
                        WHERE ad_val_rule_id < 1000000)
  WHERE NAME = 'AD_Val_Rule';

UPDATE ad_sequence
  SET currentnextsys = (SELECT MAX (ad_reference_id) + 1
                        FROM ad_reference
                        WHERE ad_reference_id < 1000000)
  WHERE NAME = 'AD_Reference';

UPDATE ad_sequence
  SET currentnextsys = (SELECT MAX (ad_reference_id) + 1
                        FROM ad_ref_table
                        WHERE ad_reference_id < 1000000)
  WHERE NAME = 'AD_Ref_Table';

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

COMMIT;

ALTER TABLE AD_PrintFormat ADD JasperProcess_ID NUMERIC(10);

COMMIT;
