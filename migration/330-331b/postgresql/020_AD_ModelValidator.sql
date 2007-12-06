CREATE TABLE AD_MODELVALIDATOR 
(	AD_CLIENT_ID NUMERIC(10,0) DEFAULT 0 NOT NULL , 
	AD_MODELVALIDATOR_ID NUMERIC(10,0) NOT NULL , 
	AD_ORG_ID NUMERIC(10,0) DEFAULT 0 NOT NULL , 
	CREATED TIMESTAMP NOT NULL , 
	CREATEDBY NUMERIC(10,0) NOT NULL ,
	UPDATED DATE NOT NULL , 
	UPDATEDBY NUMERIC(10,0) NOT NULL ,
	ISACTIVE CHAR(1) NOT NULL ,
	NAME VARCHAR(60) NOT NULL , 
	DESCRIPTION VARCHAR(255),
	HELP VARCHAR(2000), 
	ENTITYTYPE VARCHAR(40) NOT NULL , 
	MODELVALIDATIONCLASS VARCHAR(255) NOT NULL , 
	CHECK (IsActive IN ('Y','N')) , 
	CONSTRAINT AD_MODELVALIDATOR_KEY PRIMARY KEY (AD_MODELVALIDATOR_ID)
);
 
INSERT INTO AD_Element
(AD_Element_ID, AD_Client_ID, AD_Org_ID, IsActive,
Created, CreatedBy,
Updated, UpdatedBy,
ColumnName, EntityType, Name,
PrintName
)
VALUES (53225, 0, 0, 'Y',
TO_DATE ('10/21/2007 18:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
TO_DATE ('10/21/2007 18:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
'AD_ModelValidator_ID', 'D', 'Model Validator',
'Model Validator'
);

INSERT INTO AD_Element
(AD_Element_ID, AD_Client_ID, AD_Org_ID, IsActive,
Created, CreatedBy,
Updated, UpdatedBy,
ColumnName, EntityType, Name,
PrintName
)
VALUES (53226, 0, 0, 'Y',
TO_DATE ('10/21/2007 18:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
TO_DATE ('10/21/2007 18:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
'ModelValidationClass', 'D', 'Model Validation Class',
'Model Validation Class'
);

-- INSERTING into AD_Table
Insert into AD_Table 
(AD_TABLE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,HELP,TABLENAME,ISVIEW,ACCESSLEVEL,ENTITYTYPE,AD_WINDOW_ID,AD_VAL_RULE_ID,LOADSEQ,ISSECURITYENABLED,ISDELETEABLE,ISHIGHVOLUME,IMPORTTABLE,ISCHANGELOG,REPLICATIONTYPE,PO_WINDOW_ID,COPYCOLUMNSFROMTABLE) 
values 
(53014,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,'Model Validator','Global Model Validator',null,'AD_ModelValidator','N','4','D',null,null,0,'N','Y','N','N','N','L',null,'N');

-- INSERTING into AD_Column
Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53252,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Client','Client/Tenant for this installation.','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',0,'D','AD_Client_ID',53014,19,null,null,10,'0','N','N','Y','N',null,'N',0,'N','N',null,null,null,null,'N',102,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53253,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Model Validator',null,null,0,'D','AD_ModelValidator_ID',53014,13,null,null,10,null,'Y','N','Y','N',null,'N',0,'N','N',null,null,null,null,'N',53225,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53254,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Organization','Organizational entity within client','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',0,'D','AD_Org_ID',53014,19,null,null,10,'0','N','N','Y','N',null,'N',0,'N','N',null,null,null,null,'N',113,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53255,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Created','Date this record was created','The Created field indicates the date that this record was created.',0,'D','Created',53014,16,null,null,7,null,'N','N','Y','N',null,'N',0,'N','N',null,null,null,null,'N',245,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53256,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Created By','User who created this records','The Created By field indicates the user who created this record.',0,'D','CreatedBy',53014,18,110,null,10,null,'N','N','Y','N',null,'N',0,'N','N',null,null,null,null,'N',246,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53257,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Updated','Date this record was updated','The Updated field indicates the date that this record was updated.',0,'D','Updated',53014,16,null,null,7,null,'N','N','Y','N',null,'N',0,'N','N',null,null,null,null,'N',607,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53258,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Updated By','User who updated this records','The Updated By field indicates the user who updated this record.',0,'D','UpdatedBy',53014,18,110,null,10,null,'N','N','Y','N',null,'N',0,'N','N',null,null,null,null,'N',608,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53259,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Active','The record is active in the system','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports. 
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',0,'D','IsActive',53014,20,null,null,1,null,'N','N','Y','Y',null,'N',0,'N','N',null,null,null,null,'N',348,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53260,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Name','Alphanumeric identifier of the entity','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',0,'D','Name',53014,10,null,null,120,null,'N','N','Y','Y',null,'Y',1,'N','N',null,null,null,null,'N',469,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53261,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Description','Optional short description of the record','A description is limited to 255 characters.',0,'D','Description',53014,10,null,null,255,null,'N','N','N','Y',null,'N',0,'N','N',null,null,null,null,'N',275,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53262,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Comment/Help','Comment or Hint','The Help field contains a hint, comment or help about the use of this item.',0,'D','Help',53014,10,null,null,2000,null,'N','N','N','Y',null,'N',0,'N','N',null,null,null,null,'N',326,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53263,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Entity Type','Dictionary Entity Type; Determines ownership and synchronization','The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!',0,'D','EntityType',53014,10,null,null,40,null,'N','N','Y','N',null,'N',0,'N','N',null,null,null,null,'N',1682,null,'N','N',null,null);

Insert into AD_Column 
(AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC) 
values 
(53264,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,100,'Model Validation Class',null,null,1,'D','ModelValidationClass',53014,10,null,null,255,null,'N','N','Y','Y',null,'N',0,'N','N',null,null,null,null,'N',53226,null,'N','N',null,null);

-- INSERTING into AD_Window
Insert into AD_Window 
(AD_WINDOW_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,HELP,WINDOWTYPE,ISSOTRX,ENTITYTYPE,PROCESSING,AD_IMAGE_ID,AD_COLOR_ID,ISDEFAULT,WINHEIGHT,WINWIDTH,ISBETAFUNCTIONALITY) 
values 
(53003,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,'Model Validator',null,null,'M','N','D','N',null,null,'N',0,0,'N');

-- INSERTING into AD_Tab
Insert into AD_Tab 
(AD_TAB_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,HELP,AD_TABLE_ID,AD_WINDOW_ID,SEQNO,TABLEVEL,ISSINGLEROW,ISINFOTAB,ISTRANSLATIONTAB,ISREADONLY,AD_COLUMN_ID,HASTREE,WHERECLAUSE,ORDERBYCLAUSE,COMMITWARNING,AD_PROCESS_ID,PROCESSING,AD_IMAGE_ID,IMPORTFIELDS,AD_COLUMNSORTORDER_ID,AD_COLUMNSORTYESNO_ID,ISSORTTAB,ENTITYTYPE,INCLUDED_TAB_ID,READONLYLOGIC,DISPLAYLOGIC,ISINSERTRECORD,ISADVANCEDTAB) 
values 
(53014,0,0,'Y',TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),100,'Model Validator',null,null,53014,53003,10,0,'N','N','N','N',null,'N',null,null,null,null,'N',null,'N',null,null,'N','D',null,null,null,'Y','N');

-- INSERTING into AD_Field
INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53271,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Client',   'Client/Tenant for this installation.',   'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',   'Y',   53014,   53252,   NULL,   'Y',   NULL,   10,   'N',   10,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53272,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Organization',   'Organizational entity within client',   'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',   'Y',   53014,   53254,   NULL,   'Y',   NULL,   10,   'N',   20,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53273,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Entity Type',   'Dictionary Entity Type; Determines ownership and synchronization',   'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!',   'Y',   53014,   53263,   NULL,   'Y',   NULL,   40,   'N',   30,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53274,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Name',   'Alphanumeric identifier of the entity',   'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',   'Y',   53014,   53260,   NULL,   'Y',   NULL,   120,   'N',   40,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53275,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Description',   'Optional short description of the record',   'A description is limited to 255 characters.',   'Y',   53014,   53261,   NULL,   'Y',   NULL,   255,   'N',   50,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53276,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Comment/Help',   'Comment or Hint',   'The Help field contains a hint, comment or help about the use of this item.',   'Y',   53014,   53262,   NULL,   'Y',   NULL,   2000,   'N',   60,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53277,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Active',   'The record is active in the system',   'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',   'Y',   53014,   53259,   NULL,   'Y',   NULL,   1,   'N',   70,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53278,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Model Validation Class',   NULL,   NULL,   'Y',   53014,   53264,   NULL,   'Y',   NULL,   255,   'N',   80,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

INSERT
INTO ad_field(ad_field_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   HELP,   iscentrallymaintained,   ad_tab_id,   ad_column_id,   ad_fieldgroup_id,   isdisplayed,   displaylogic,   displaylength,   isreadonly,   seqno,   sortno,   issameline,   isheading,   isfieldonly,   isencrypted,   entitytype,   obscuretype,   ad_reference_id,   ismandatory,   included_tab_id)
VALUES(53279,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'Model Validator',   NULL,   NULL,   'Y',   53014,   53253,   NULL,   'N',   NULL,   10,   'N',   NULL,   NULL,   'N',   'N',   'N',   'N',   'D',   NULL,   NULL,   NULL,   NULL);

-- INSERTING into AD_Menu
INSERT
INTO ad_menu(ad_menu_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   name,   updatedby,   description,   issummary,   issotrx,   isreadonly,   ACTION,   ad_window_id,   ad_workflow_id,   ad_task_id,   ad_process_id,   ad_form_id,   ad_workbench_id,   entitytype)
VALUES(53012,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   'Model Validator',   100,   NULL,   'N',   'N',   'N',   'W',   53003,   NULL,   NULL,   NULL,   NULL,   NULL,   'D');

-- INSERTING into AD_TreeNodeMM
INSERT
INTO ad_treenodemm(ad_tree_id,   node_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   parent_id,   seqno)
VALUES(10,   53012,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   0,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   0,   153,   9);

-- INSERTING into AD_Sequence
INSERT
INTO ad_sequence(ad_sequence_id,   ad_client_id,   ad_org_id,   isactive,   created,   createdby,   updated,   updatedby,   name,   description,   vformat,   isautosequence,   incrementno,   startno,   currentnext,   currentnextsys,   isaudited,   istableid,   prefix,   suffix,   startnewyear)
VALUES(53012,   0,   0,   'Y',   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   TO_DATE ('10/22/2007 09:30:00', 'MM/DD/YYYY HH24:MI:SS'),   100,   'AD_ModelValidator',   'Table AD_ModelValidator',   NULL,   'Y',   1,   1000000,   1000000,   50000,   'N',   'Y',   NULL,   NULL,   'N');
