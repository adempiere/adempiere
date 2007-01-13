-- ----------------------------------------------------------------------- 
-- AD_ACCESSLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_accesslog"
(
    "ad_accesslog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_table_id" INTEGER,
    "ad_column_id" INTEGER,
    "record_id" INTEGER,
    "remote_addr" VARCHAR(60),
    "remote_host" VARCHAR(60),
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    "reply" VARCHAR(2000),
    PRIMARY KEY ("ad_accesslog_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ALERT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_alert"
(
    "ad_alert_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "alertsubject" VARCHAR(60) NOT NULL,
    "alertmessage" VARCHAR(2000) NOT NULL,
    "enforceclientsecurity" CHAR(1) DEFAULT 'Y' NOT NULL,
    "enforcerolesecurity" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ad_alertprocessor_id" INTEGER,
    "isvalid" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_alert_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (EnforceClientSecurity in ('Y','N')),
    CHECK (EnforceRoleSecurity in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ALERTPROCESSOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_alertprocessor"
(
    "ad_alertprocessor_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "frequencytype" CHAR(1) NOT NULL,
    "frequency" INTEGER NOT NULL,
    "datelastrun" TIMESTAMP,
    "datenextrun" TIMESTAMP,
    "supervisor_id" INTEGER NOT NULL,
    "keeplogdays" INTEGER NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("ad_alertprocessor_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ALERTPROCESSORLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_alertprocessorlog"
(
    "ad_alertprocessor_id" INTEGER NOT NULL,
    "ad_alertprocessorlog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "iserror" CHAR(1) DEFAULT 'N' NOT NULL,
    "summary" VARCHAR(2000),
    "reference" VARCHAR(60),
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    "binarydata" BYTEA,
    PRIMARY KEY ("ad_alertprocessor_id", "ad_alertprocessorlog_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsError in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ALERTRECIPIENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_alertrecipient"
(
    "ad_alertrecipient_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_alert_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "ad_role_id" INTEGER,
    PRIMARY KEY ("ad_alertrecipient_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ALERTRULE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_alertrule"
(
    "ad_alertrule_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "ad_alert_id" INTEGER NOT NULL,
    "selectclause" VARCHAR(2000) NOT NULL,
    "fromclause" VARCHAR(2000) NOT NULL,
    "whereclause" VARCHAR(2000),
    "ad_table_id" INTEGER,
    "preprocessing" VARCHAR(2000),
    "postprocessing" VARCHAR(2000),
    "isvalid" CHAR(1) DEFAULT 'Y' NOT NULL,
    "errormsg" VARCHAR(2000),
    "otherclause" VARCHAR(2000),
    PRIMARY KEY ("ad_alertrule_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ARCHIVE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_archive"
(
    "ad_archive_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_table_id" INTEGER,
    "record_id" INTEGER,
    "ad_process_id" INTEGER,
    "binarydata" BYTEA,
    "c_bpartner_id" INTEGER,
    "isreport" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_archive_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ATTACHMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_attachment"
(
    "ad_attachment_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "title" VARCHAR(60) NOT NULL,
    "binarydata" BYTEA,
    "textmsg" VARCHAR(2000),
    PRIMARY KEY ("ad_attachment_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_attachment_record" ON "ad_attachment" ("ad_table_id", "record_id");

-- ----------------------------------------------------------------------- 
-- AD_ATTACHMENTNOTE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_attachmentnote"
(
    "ad_attachmentnote_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_attachment_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "title" VARCHAR(60) NOT NULL,
    "textmsg" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("ad_attachmentnote_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ATTRIBUTE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_attribute"
(
    "ad_attribute_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_table_id" INTEGER NOT NULL,
    "ad_reference_id" INTEGER NOT NULL,
    "ad_reference_value_id" INTEGER,
    "ad_val_rule_id" INTEGER,
    "callout" VARCHAR(60),
    "valuemin" VARCHAR(20),
    "valuemax" VARCHAR(20),
    "defaultvalue" VARCHAR(2000),
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "isupdateable" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismandatory" CHAR(1) DEFAULT 'N' NOT NULL,
    "isencrypted" CHAR(1) DEFAULT 'N' NOT NULL,
    "fieldlength" INTEGER,
    "displaylength" INTEGER,
    "displaylogic" VARCHAR(2000),
    "vformat" VARCHAR(60),
    "issameline" CHAR(1) DEFAULT 'N' NOT NULL,
    "isheading" CHAR(1) DEFAULT 'N' NOT NULL,
    "isfieldonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "seqno" INTEGER,
    PRIMARY KEY ("ad_attribute_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReadOnly in ('Y','N')),
    CHECK (IsUpdateable in ('Y','N')),
    CHECK (IsMandatory in ('Y','N')),
    CHECK (IsEncrypted in ('Y','N')),
    CHECK (IsSameLine in ('Y','N')),
    CHECK (IsHeading in ('Y','N')),
    CHECK (IsFieldOnly in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ATTRIBUTE_VALUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_attribute_value"
(
    "ad_attribute_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "v_number" NUMERIC,
    "v_date" TIMESTAMP,
    "v_string" VARCHAR(2000),
    PRIMARY KEY ("ad_attribute_id", "record_id")
);

-- ----------------------------------------------------------------------- 
-- AD_CHANGELOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_changelog"
(
    "ad_changelog_id" INTEGER NOT NULL,
    "ad_session_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "ad_column_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "oldvalue" VARCHAR(2000),
    "newvalue" VARCHAR(2000),
    "undo" CHAR(1),
    "redo" CHAR(1),
    "iscustomization" CHAR(1) DEFAULT 'N' NOT NULL,
    "trxname" VARCHAR(60),
    "description" VARCHAR(255),
    PRIMARY KEY ("ad_changelog_id", "ad_session_id", "ad_table_id", "ad_column_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCustomization in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_CLIENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_client"
(
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "smtphost" VARCHAR(60),
    "requestemail" VARCHAR(60),
    "requestuser" VARCHAR(60),
    "requestuserpw" VARCHAR(20),
    "requestfolder" VARCHAR(20),
    "ad_language" VARCHAR(6),
    "ismultilingualdocument" CHAR(1) DEFAULT 'N' NOT NULL,
    "issmtpauthorization" CHAR(1) DEFAULT 'N' NOT NULL,
    "isusebetafunctions" CHAR(1) DEFAULT 'N' NOT NULL,
    "ldapquery" VARCHAR(255),
    "modelvalidationclasses" VARCHAR(255),
    "autoarchive" CHAR(1) DEFAULT 'N' NOT NULL,
    "mmpolicy" CHAR(1) DEFAULT 'F' NOT NULL,
    "emailtest" CHAR(1),
    "isserveremail" CHAR(1) DEFAULT 'N' NOT NULL,
    "documentdir" VARCHAR(60),
    "ispostimmediate" CHAR(1) DEFAULT 'N' NOT NULL,
    "iscostimmediate" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_client_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_client_name" ON "ad_client" ("name");

-- ----------------------------------------------------------------------- 
-- AD_CLIENTINFO 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_clientinfo"
(
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isdiscountlineamt" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_calendar_id" INTEGER,
    "c_acctschema1_id" INTEGER,
    "c_uom_volume_id" INTEGER,
    "c_uom_weight_id" INTEGER,
    "c_uom_length_id" INTEGER,
    "c_uom_time_id" INTEGER,
    "ad_tree_menu_id" INTEGER,
    "ad_tree_org_id" INTEGER,
    "ad_tree_bpartner_id" INTEGER,
    "ad_tree_project_id" INTEGER,
    "ad_tree_salesregion_id" INTEGER,
    "ad_tree_product_id" INTEGER,
    "m_productfreight_id" INTEGER,
    "c_bpartnercashtrx_id" INTEGER,
    "keeplogdays" INTEGER,
    "ad_tree_activity_id" INTEGER,
    "ad_tree_campaign_id" INTEGER,
    PRIMARY KEY ("ad_client_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDiscountLineAmt in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_CLIENTSHARE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_clientshare"
(
    "ad_clientshare_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_table_id" INTEGER NOT NULL,
    "sharetype" CHAR(1) NOT NULL,
    PRIMARY KEY ("ad_clientshare_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_clientshare_table" ON "ad_clientshare" ("ad_client_id", "ad_table_id");

-- ----------------------------------------------------------------------- 
-- AD_COLOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_color"
(
    "ad_color_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "colortype" CHAR(1) NOT NULL,
    "red" INTEGER NOT NULL,
    "green" INTEGER NOT NULL,
    "blue" INTEGER NOT NULL,
    "alpha" INTEGER NOT NULL,
    "ad_image_id" INTEGER,
    "imagealpha" NUMERIC NOT NULL,
    "red_1" INTEGER,
    "green_1" INTEGER,
    "blue_1" INTEGER,
    "alpha_1" INTEGER,
    "linewidth" INTEGER,
    "linedistance" INTEGER,
    "startpoint" INTEGER,
    "repeatdistance" INTEGER,
    PRIMARY KEY ("ad_color_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_COLUMN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_column"
(
    "ad_column_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "version" NUMERIC NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "columnname" VARCHAR(40) NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "ad_reference_id" INTEGER NOT NULL,
    "ad_reference_value_id" INTEGER,
    "ad_val_rule_id" INTEGER,
    "fieldlength" INTEGER,
    "defaultvalue" VARCHAR(2000),
    "iskey" CHAR(1) DEFAULT 'N' NOT NULL,
    "isparent" CHAR(1) DEFAULT 'N' NOT NULL,
    "ismandatory" CHAR(1) DEFAULT 'N' NOT NULL,
    "isupdateable" CHAR(1) DEFAULT 'Y' NOT NULL,
    "readonlylogic" VARCHAR(2000),
    "isidentifier" CHAR(1) DEFAULT 'N' NOT NULL,
    "seqno" INTEGER,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "isencrypted" CHAR(1) DEFAULT 'N' NOT NULL,
    "callout" VARCHAR(255),
    "vformat" VARCHAR(60),
    "valuemin" VARCHAR(20),
    "valuemax" VARCHAR(20),
    "isselectioncolumn" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_element_id" INTEGER,
    "ad_process_id" INTEGER,
    "issyncdatabase" CHAR(1) DEFAULT 'N',
    "isalwaysupdateable" CHAR(1) DEFAULT 'N' NOT NULL,
    "columnsql" VARCHAR(2000),
    PRIMARY KEY ("ad_column_id"),
    CHECK (IsParent in ('Y','N')),
    CHECK (IsMandatory in ('Y','N')),
    CHECK (IsUpdateable in ('Y','N')),
    CHECK (IsIdentifier in ('Y','N')),
    CHECK (IsTranslated in ('Y','N')),
    CHECK (IsEncrypted in ('Y','N')),
    CHECK (IsSelectionColumn in ('Y','N')),
    CHECK (IsSyncDatabase in ('Y','N')),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsKey in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_column_name" ON "ad_column" ("ad_table_id", "columnname");

-- ----------------------------------------------------------------------- 
-- AD_COLUMN_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_column_access"
(
    "ad_role_id" INTEGER NOT NULL,
    "ad_column_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "isexclude" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ad_table_id" INTEGER,
    PRIMARY KEY ("ad_role_id", "ad_column_id")
);

-- ----------------------------------------------------------------------- 
-- AD_COLUMN_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_column_trl"
(
    "ad_column_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_column_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_DESKTOP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_desktop"
(
    "ad_desktop_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_image_id" INTEGER,
    "ad_color_id" INTEGER,
    PRIMARY KEY ("ad_desktop_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_DESKTOPWORKBENCH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_desktopworkbench"
(
    "ad_desktopworkbench_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_workbench_id" INTEGER NOT NULL,
    "ad_desktop_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    PRIMARY KEY ("ad_desktopworkbench_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_DESKTOP_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_desktop_trl"
(
    "ad_desktop_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_desktop_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ELEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_element"
(
    "ad_element_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER,
    "columnname" VARCHAR(40) NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "name" VARCHAR(60),
    "printname" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "po_name" VARCHAR(60),
    "po_printname" VARCHAR(60),
    "po_description" VARCHAR(255),
    "po_help" VARCHAR(2000),
    PRIMARY KEY ("ad_element_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_element_clientorg" ON "ad_element" ("ad_client_id", "ad_org_id");

CREATE UNIQUE INDEX "ad_element_columnname" ON "ad_element" ("columnname");

CREATE INDEX "ad_element_name" ON "ad_element" ("name");

-- ----------------------------------------------------------------------- 
-- AD_ELEMENT_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_element_trl"
(
    "ad_element_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "printname" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "po_name" VARCHAR(60),
    "po_printname" VARCHAR(60),
    "po_description" VARCHAR(255),
    "po_help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_element_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ENTITYTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_entitytype"
(
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "ad_entitytype_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "version" VARCHAR(20),
    "modelpackage" VARCHAR(255),
    "classpath" VARCHAR(255),
    "processing" CHAR(1),
    PRIMARY KEY ("entitytype"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ERROR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_error"
(
    "ad_error_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "code" VARCHAR(2000),
    "ad_language" VARCHAR(6),
    PRIMARY KEY ("ad_error_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_FIELD 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_field"
(
    "ad_field_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "iscentrallymaintained" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ad_tab_id" INTEGER NOT NULL,
    "ad_column_id" INTEGER,
    "ad_fieldgroup_id" INTEGER,
    "isdisplayed" CHAR(1) DEFAULT 'Y' NOT NULL,
    "displaylogic" VARCHAR(2000),
    "displaylength" INTEGER,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "seqno" INTEGER,
    "sortno" INTEGER,
    "issameline" CHAR(1) DEFAULT 'N' NOT NULL,
    "isheading" CHAR(1) DEFAULT 'N' NOT NULL,
    "isfieldonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "isencrypted" CHAR(1) DEFAULT 'N' NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "obscuretype" CHAR(3),
    "ad_reference_id" INTEGER,
    "ismandatory" CHAR(1),
    PRIMARY KEY ("ad_field_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCentrallyMaintained in ('Y','N')),
    CHECK (IsDisplayed in ('Y','N')),
    CHECK (IsReadOnly in ('Y','N')),
    CHECK (IsSameLine in ('Y','N')),
    CHECK (IsHeading in ('Y','N')),
    CHECK (IsFieldOnly in ('Y','N')),
    CHECK (IsEncrypted in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_field_column" ON "ad_field" ("ad_tab_id", "ad_column_id");

-- ----------------------------------------------------------------------- 
-- AD_FIELDGROUP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_fieldgroup"
(
    "ad_fieldgroup_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_fieldgroup_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_FIELDGROUP_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_fieldgroup_trl"
(
    "ad_fieldgroup_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_fieldgroup_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_FIELD_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_field_trl"
(
    "ad_field_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_field_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_FIND 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_find"
(
    "ad_find_id" INTEGER NOT NULL,
    "find_id" NUMERIC NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "andor" CHAR(1) NOT NULL,
    "ad_column_id" INTEGER NOT NULL,
    "operation" CHAR(2) NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "value2" VARCHAR(40),
    PRIMARY KEY ("ad_find_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_FORM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_form"
(
    "ad_form_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "accesslevel" CHAR(1) NOT NULL,
    "classname" VARCHAR(60),
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "isbetafunctionality" CHAR(1) DEFAULT 'N' NOT NULL,
    "jspurl" VARCHAR(120),
    PRIMARY KEY ("ad_form_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_FORM_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_form_access"
(
    "ad_form_id" INTEGER NOT NULL,
    "ad_role_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadwrite" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_form_id", "ad_role_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReadWrite in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_FORM_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_form_trl"
(
    "ad_form_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_form_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_IMAGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_image"
(
    "ad_image_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "imageurl" VARCHAR(120),
    "binarydata" BYTEA,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("ad_image_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_IMPFORMAT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_impformat"
(
    "ad_impformat_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_table_id" INTEGER NOT NULL,
    "formattype" CHAR(1) NOT NULL,
    "processing" CHAR(1) NOT NULL,
    PRIMARY KEY ("ad_impformat_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_impformat_name" ON "ad_impformat" ("name");

-- ----------------------------------------------------------------------- 
-- AD_IMPFORMAT_ROW 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_impformat_row"
(
    "ad_impformat_row_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_impformat_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "ad_column_id" INTEGER NOT NULL,
    "startno" INTEGER,
    "endno" INTEGER,
    "datatype" CHAR(1) NOT NULL,
    "dataformat" VARCHAR(20),
    "decimalpoint" CHAR(1) NOT NULL,
    "divideby100" CHAR(1) DEFAULT 'N' NOT NULL,
    "constantvalue" VARCHAR(60),
    "callout" VARCHAR(60),
    "script" VARCHAR(2000),
    PRIMARY KEY ("ad_impformat_row_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (DivideBy100 in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_INFOCOLUMN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_infocolumn"
(
    "ad_infocolumn_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_infowindow_id" INTEGER NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "selectclause" VARCHAR(255) NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    "isdisplayed" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isquerycriteria" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_element_id" INTEGER,
    "ad_reference_id" INTEGER NOT NULL,
    PRIMARY KEY ("ad_infocolumn_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDisplayed in ('Y','N')),
    CHECK (IsQueryCriteria in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_INFOCOLUMN_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_infocolumn_trl"
(
    "ad_infocolumn_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("ad_infocolumn_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_INFOWINDOW 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_infowindow"
(
    "ad_infowindow_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_table_id" INTEGER NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "fromclause" VARCHAR(2000) NOT NULL,
    "otherclause" VARCHAR(2000),
    "processing" CHAR(1),
    PRIMARY KEY ("ad_infowindow_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_INFOWINDOW_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_infowindow_trl"
(
    "ad_infowindow_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("ad_infowindow_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ISSUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_issue"
(
    "ad_issue_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "releaseno" CHAR(4) NOT NULL,
    "version" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "username" VARCHAR(60) NOT NULL,
    "supportemail" VARCHAR(60),
    "dbaddress" VARCHAR(255),
    "local_host" VARCHAR(120),
    "operatingsysteminfo" VARCHAR(255),
    "releasetag" VARCHAR(60),
    "databaseinfo" VARCHAR(255),
    "javainfo" VARCHAR(255),
    "remote_addr" VARCHAR(60),
    "remote_host" VARCHAR(120),
    "issuesummary" VARCHAR(2000) NOT NULL,
    "comments" VARCHAR(2000),
    "sourceclassname" VARCHAR(60),
    "sourcemethodname" VARCHAR(60),
    "loggername" VARCHAR(60),
    "lineno" INTEGER DEFAULT 0,
    "stacktrace" VARCHAR(2000),
    "errortrace" VARCHAR(2000),
    "record_id" INTEGER,
    "requestdocumentno" VARCHAR(30),
    "a_asset_id" INTEGER,
    "r_request_id" INTEGER,
    "responsetext" VARCHAR(2000),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "isvanillasystem" CHAR(1) DEFAULT 'N',
    "isreproducible" CHAR(1) DEFAULT 'N',
    "r_issueknown_id" INTEGER,
    "statisticsinfo" VARCHAR(255),
    "profileinfo" VARCHAR(255),
    "systemstatus" CHAR(1) NOT NULL,
    "r_issueproject_id" INTEGER,
    "r_issueuser_id" INTEGER,
    "r_issuesystem_id" INTEGER,
    "issuesource" CHAR(1),
    "ad_window_id" INTEGER,
    "ad_process_id" INTEGER,
    "ad_form_id" INTEGER,
    PRIMARY KEY ("ad_issue_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsVanillaSystem in ('Y','N')),
    CHECK (IsReproducible in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_LABELPRINTER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_labelprinter"
(
    "ad_labelprinter_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("ad_labelprinter_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_LABELPRINTERFUNCTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_labelprinterfunction"
(
    "ad_labelprinterfunction_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_labelprinter_id" INTEGER NOT NULL,
    "functionprefix" VARCHAR(40),
    "functionsuffix" VARCHAR(40),
    "isxyposition" CHAR(1) DEFAULT 'N' NOT NULL,
    "xyseparator" VARCHAR(20),
    PRIMARY KEY ("ad_labelprinterfunction_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsXYPosition in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_LANGUAGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_language"
(
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'N' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "languageiso" CHAR(2),
    "countrycode" CHAR(2),
    "isbaselanguage" CHAR(1) DEFAULT 'N' NOT NULL,
    "issystemlanguage" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "ad_language_id" INTEGER NOT NULL,
    "isdecimalpoint" CHAR(1) DEFAULT 'Y' NOT NULL,
    "datepattern" VARCHAR(20),
    "timepattern" VARCHAR(20),
    PRIMARY KEY ("ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsBaseLanguage in ('Y','N')),
    CHECK (IsSystemLanguage in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_LDAPACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_ldapaccess"
(
    "ad_ldapaccess_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_ldapprocessor_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "r_interestarea_id" INTEGER,
    "iserror" CHAR(1) DEFAULT 'N' NOT NULL,
    "summary" VARCHAR(2000),
    "description" VARCHAR(255),
    PRIMARY KEY ("ad_ldapaccess_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsError in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_LDAPPROCESSOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_ldapprocessor"
(
    "ad_ldapprocessor_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ldapport" INTEGER DEFAULT 0 NOT NULL,
    "datelastrun" TIMESTAMP,
    "datenextrun" TIMESTAMP,
    "supervisor_id" INTEGER NOT NULL,
    "keeplogdays" INTEGER DEFAULT 0 NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("ad_ldapprocessor_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_ldapprocessorport" ON "ad_ldapprocessor" ("ldapport");

-- ----------------------------------------------------------------------- 
-- AD_LDAPPROCESSORLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_ldapprocessorlog"
(
    "ad_ldapprocessor_id" INTEGER NOT NULL,
    "ad_ldapprocessorlog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "summary" VARCHAR(2000),
    "iserror" CHAR(1) DEFAULT 'N' NOT NULL,
    "reference" VARCHAR(60),
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    "binarydata" BYTEA,
    PRIMARY KEY ("ad_ldapprocessor_id", "ad_ldapprocessorlog_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsError in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_MENU 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_menu"
(
    "ad_menu_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "action" CHAR(1),
    "ad_window_id" INTEGER,
    "ad_workflow_id" INTEGER,
    "ad_task_id" INTEGER,
    "ad_process_id" INTEGER,
    "ad_form_id" INTEGER,
    "ad_workbench_id" INTEGER,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_menu_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N')),
    CHECK (IsReadOnly in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_MENU_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_menu_trl"
(
    "ad_menu_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_menu_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_MESSAGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_message"
(
    "ad_message_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "msgtext" VARCHAR(2000) NOT NULL,
    "msgtip" VARCHAR(2000),
    "msgtype" CHAR(1) NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_message_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_message_value" ON "ad_message" ("value");

-- ----------------------------------------------------------------------- 
-- AD_MESSAGE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_message_trl"
(
    "ad_message_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "msgtext" VARCHAR(2000) NOT NULL,
    "msgtip" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_message_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_MODIFICATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_modification"
(
    "ad_modification_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "version" VARCHAR(20),
    PRIMARY KEY ("ad_modification_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_NOTE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_note"
(
    "ad_note_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "ad_message_id" INTEGER NOT NULL,
    "reference" VARCHAR(60),
    "ad_table_id" INTEGER,
    "record_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N',
    "processing" CHAR(1),
    "description" VARCHAR(255),
    "ad_wf_activity_id" INTEGER,
    "textmsg" VARCHAR(2000),
    PRIMARY KEY ("ad_note_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ORG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_org"
(
    "ad_org_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_org_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_org_value" ON "ad_org" ("ad_client_id", "value");

-- ----------------------------------------------------------------------- 
-- AD_ORGINFO 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_orginfo"
(
    "ad_org_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_location_id" INTEGER,
    "duns" CHAR(11) NOT NULL,
    "taxid" VARCHAR(20) NOT NULL,
    "pa_goal_id" INTEGER,
    "supervisor_id" INTEGER,
    "parent_org_id" INTEGER,
    "ad_orgtype_id" INTEGER,
    "m_warehouse_id" INTEGER,
    PRIMARY KEY ("ad_org_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ORGTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_orgtype"
(
    "ad_orgtype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_printcolor_id" INTEGER,
    PRIMARY KEY ("ad_orgtype_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PACKAGE_EXP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_package_exp"
(
    "ad_package_exp_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_package_type" VARCHAR(1),
    "email" VARCHAR(30) NOT NULL,
    "instructions" VARCHAR(1000) NOT NULL,
    "pk_name" VARCHAR(60) NOT NULL,
    "processed" CHAR(1),
    "releaseno" VARCHAR(20) NOT NULL,
    "version" VARCHAR(20) NOT NULL,
    "username" VARCHAR(30) NOT NULL,
    "processing" CHAR(1) NOT NULL,
    "pk_version" VARCHAR(20) NOT NULL,
    "file_directory" VARCHAR(255) NOT NULL,
    "description" VARCHAR(1000) NOT NULL,
    PRIMARY KEY ("ad_package_exp_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PACKAGE_EXP_COMMON 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_package_exp_common"
(
    "ad_package_exp_common_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_form_id" INTEGER,
    "ad_impformat_id" INTEGER,
    "ad_reportview_id" INTEGER,
    "ad_table_id" INTEGER,
    "ad_workbench_id" INTEGER,
    "dbtype" VARCHAR(22),
    "processed" CHAR(1),
    "pk_name" VARCHAR(60),
    "name2" VARCHAR(60),
    "line" NUMERIC,
    "file_directory" VARCHAR(255),
    "filename" VARCHAR(255),
    "destination_directory" VARCHAR(255),
    "description" VARCHAR(1000),
    "type" VARCHAR(10),
    "target_directory" VARCHAR(255),
    "sqlstatement" VARCHAR(255),
    "processing" CHAR(1),
    "ad_workflow_id" INTEGER,
    "ad_window_id" INTEGER,
    "ad_role_id" INTEGER,
    "ad_process_id" INTEGER,
    "ad_menu_id" INTEGER,
    PRIMARY KEY ("ad_package_exp_common_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PACKAGE_EXP_DETAIL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_package_exp_detail"
(
    "ad_package_exp_detail_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" NUMERIC NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" NUMERIC NOT NULL,
    "ad_form_id" INTEGER,
    "ad_impformat_id" INTEGER,
    "ad_menu_id" INTEGER,
    "ad_process_id" INTEGER,
    "ad_role_id" INTEGER,
    "ad_window_id" INTEGER,
    "ad_workflow_id" INTEGER,
    "file_directory" VARCHAR(255),
    "filename" VARCHAR(255),
    "destination_filename" VARCHAR(255),
    "destination_directory" VARCHAR(255),
    "description" VARCHAR(1000) NOT NULL,
    "dbtype" VARCHAR(22),
    "type" VARCHAR(10) NOT NULL,
    "target_directory" VARCHAR(255),
    "sqlstatement" VARCHAR(2000),
    "releaseno" VARCHAR(20),
    "processing" CHAR(1) NOT NULL,
    "processed" CHAR(1),
    "pk_name" VARCHAR(60) NOT NULL,
    "name2" VARCHAR(60),
    "line" NUMERIC,
    "ad_workbench_id" INTEGER,
    "ad_table_id" INTEGER,
    "ad_reportview_id" INTEGER,
    "ad_package_exp_id" INTEGER NOT NULL,
    "ad_package_code_new" VARCHAR(2000),
    "ad_package_code_old" VARCHAR(2000),
    PRIMARY KEY ("ad_package_exp_detail_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PACKAGE_IMP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_package_imp"
(
    "ad_package_imp_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "pk_status" VARCHAR(22),
    "releaseno" VARCHAR(20),
    "pk_version" VARCHAR(20),
    "version" VARCHAR(20),
    "description" VARCHAR(1000) NOT NULL,
    "email" VARCHAR(60),
    "processed" CHAR(1) DEFAULT 'N',
    "processing" CHAR(1) DEFAULT 'N' NOT NULL,
    "creator" VARCHAR(60),
    "creatorcontact" VARCHAR(255),
    "createddate" VARCHAR(25),
    "updateddate" VARCHAR(25),
    "uninstall" CHAR(1),
    PRIMARY KEY ("ad_package_imp_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PACKAGE_IMP_BACKUP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_package_imp_backup"
(
    "ad_package_imp_backup_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_package_imp_id" INTEGER NOT NULL,
    "ad_package_imp_detail_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER,
    "ad_column_id" INTEGER,
    "ad_reference_id" INTEGER,
    "ad_package_imp_bck_dir" VARCHAR(255),
    "ad_package_imp_org_dir" VARCHAR(255),
    "colvalue" VARCHAR(2000),
    "uninstall" CHAR(1),
    PRIMARY KEY ("ad_package_imp_backup_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PACKAGE_IMP_DETAIL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_package_imp_detail"
(
    "ad_package_imp_detail_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60),
    "ad_package_imp_id" INTEGER NOT NULL,
    "ad_original_id" INTEGER NOT NULL,
    "ad_backup_id" INTEGER,
    "action" VARCHAR(20),
    "success" VARCHAR(20),
    "type" VARCHAR(60),
    "tablename" VARCHAR(60),
    "ad_table_id" INTEGER,
    "uninstall" CHAR(1),
    PRIMARY KEY ("ad_package_imp_detail_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PACKAGE_IMP_INST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_package_imp_inst"
(
    "ad_package_imp_inst_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "name" VARCHAR(240),
    "pk_status" VARCHAR(44),
    "releaseno" VARCHAR(40),
    "pk_version" VARCHAR(40),
    "version" VARCHAR(40),
    "description" VARCHAR(2000),
    "email" VARCHAR(120),
    "processed" CHAR(1) DEFAULT 'N',
    "processing" CHAR(1) DEFAULT 'N',
    "creator" VARCHAR(120),
    "creatorcontact" VARCHAR(510),
    "createddate" VARCHAR(50),
    "updateddate" VARCHAR(50),
    "uninstall" CHAR(1),
    PRIMARY KEY ("ad_package_imp_inst_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PACKAGE_IMP_PROC 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_package_imp_proc"
(
    "ad_package_imp_proc_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_override_dict" CHAR(1),
    "ad_package_dir" VARCHAR(255),
    "ad_package_source" VARCHAR(255),
    "ad_package_source_type" VARCHAR(10) NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("ad_package_imp_proc_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PINSTANCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_pinstance"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "ad_process_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "isprocessing" CHAR(1) DEFAULT 'N' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "ad_user_id" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "result" INTEGER,
    "errormsg" VARCHAR(2000),
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "createdby" INTEGER,
    "updatedby" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y',
    PRIMARY KEY ("ad_pinstance_id"),
    CHECK (IsProcessing in ('Y','N'))
);

CREATE INDEX "ad_pinstance_record" ON "ad_pinstance" ("ad_process_id", "record_id");

-- ----------------------------------------------------------------------- 
-- AD_PINSTANCE_LOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_pinstance_log"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "log_id" INTEGER NOT NULL,
    "p_date" TIMESTAMP DEFAULT NOW(),
    "p_id" INTEGER,
    "p_number" NUMERIC,
    "p_msg" VARCHAR(2000),
    PRIMARY KEY ("ad_pinstance_id", "log_id")
);

-- ----------------------------------------------------------------------- 
-- AD_PINSTANCE_PARA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_pinstance_para"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "parametername" VARCHAR(60),
    "p_string" VARCHAR(60),
    "p_string_to" VARCHAR(60),
    "p_number" NUMERIC,
    "p_number_to" NUMERIC,
    "p_date" TIMESTAMP,
    "p_date_to" TIMESTAMP,
    "info" VARCHAR(60),
    "info_to" VARCHAR(60),
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    PRIMARY KEY ("ad_pinstance_id", "seqno")
);

-- ----------------------------------------------------------------------- 
-- AD_PREFERENCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_preference"
(
    "ad_preference_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_window_id" INTEGER,
    "ad_user_id" INTEGER,
    "attribute" VARCHAR(60) NOT NULL,
    "value" VARCHAR(60) NOT NULL,
    PRIMARY KEY ("ad_preference_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_preference_attribute" ON "ad_preference" ("ad_client_id", "ad_org_id", "ad_window_id", "ad_user_id", "attribute");

-- ----------------------------------------------------------------------- 
-- AD_PRINTCOLOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printcolor"
(
    "ad_printcolor_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "code" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("ad_printcolor_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_printcolor_name" ON "ad_printcolor" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- AD_PRINTFONT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printfont"
(
    "ad_printfont_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "code" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("ad_printfont_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_printfont_name" ON "ad_printfont" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- AD_PRINTFORM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printform"
(
    "ad_printform_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "invoice_printformat_id" INTEGER,
    "order_printformat_id" INTEGER,
    "remittance_printformat_id" INTEGER,
    "shipment_printformat_id" INTEGER,
    "invoice_mailtext_id" INTEGER,
    "order_mailtext_id" INTEGER,
    "remittance_mailtext_id" INTEGER,
    "shipment_mailtext_id" INTEGER,
    "project_mailtext_id" INTEGER,
    "project_printformat_id" INTEGER,
    PRIMARY KEY ("ad_printform_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_printform_client" ON "ad_printform" ("ad_client_id", "ad_org_id");

-- ----------------------------------------------------------------------- 
-- AD_PRINTFORMAT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printformat"
(
    "ad_printformat_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "istablebased" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isform" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "ad_printpaper_id" INTEGER NOT NULL,
    "ad_printcolor_id" INTEGER NOT NULL,
    "ad_printfont_id" INTEGER NOT NULL,
    "isstandardheaderfooter" CHAR(1) DEFAULT 'Y' NOT NULL,
    "headermargin" INTEGER NOT NULL,
    "footermargin" INTEGER NOT NULL,
    "createcopy" CHAR(1),
    "ad_reportview_id" INTEGER,
    "ad_printtableformat_id" INTEGER,
    "printername" VARCHAR(40),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_printformat_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTableBased in ('Y','N')),
    CHECK (IsForm in ('Y','N')),
    CHECK (IsStandardHeaderFooter in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_printformat_name" ON "ad_printformat" ("ad_client_id", "ad_table_id", "name");

CREATE INDEX "ad_printformat_table" ON "ad_printformat" ("ad_table_id");

-- ----------------------------------------------------------------------- 
-- AD_PRINTFORMATITEM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printformatitem"
(
    "ad_printformatitem_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_printformat_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "printname" VARCHAR(2000),
    "isprinted" CHAR(1) DEFAULT 'Y' NOT NULL,
    "printareatype" CHAR(1) NOT NULL,
    "seqno" INTEGER NOT NULL,
    "printformattype" CHAR(1) NOT NULL,
    "ad_column_id" INTEGER,
    "ad_printformatchild_id" INTEGER,
    "isrelativeposition" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isnextline" CHAR(1) DEFAULT 'Y' NOT NULL,
    "xspace" INTEGER NOT NULL,
    "yspace" INTEGER NOT NULL,
    "xposition" INTEGER NOT NULL,
    "yposition" INTEGER NOT NULL,
    "maxwidth" INTEGER NOT NULL,
    "isheightoneline" CHAR(1) DEFAULT 'Y' NOT NULL,
    "maxheight" INTEGER NOT NULL,
    "fieldalignmenttype" CHAR(1) NOT NULL,
    "linealignmenttype" CHAR(1) NOT NULL,
    "ad_printcolor_id" INTEGER,
    "ad_printfont_id" INTEGER,
    "isorderby" CHAR(1) DEFAULT 'N' NOT NULL,
    "sortno" INTEGER NOT NULL,
    "isgroupby" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispagebreak" CHAR(1) DEFAULT 'N' NOT NULL,
    "issummarized" CHAR(1) DEFAULT 'N' NOT NULL,
    "imageisattached" CHAR(1) DEFAULT 'N' NOT NULL,
    "imageurl" VARCHAR(120),
    "isaveraged" CHAR(1) DEFAULT 'N' NOT NULL,
    "iscounted" CHAR(1) DEFAULT 'N' NOT NULL,
    "issetnlposition" CHAR(1) DEFAULT 'N' NOT NULL,
    "issuppressnull" CHAR(1) DEFAULT 'N' NOT NULL,
    "belowcolumn" INTEGER,
    "ad_printgraph_id" INTEGER,
    "isfixedwidth" CHAR(1) DEFAULT 'N' NOT NULL,
    "isnextpage" CHAR(1) DEFAULT 'N' NOT NULL,
    "printnamesuffix" VARCHAR(60),
    "ismincalc" CHAR(1) DEFAULT 'N' NOT NULL,
    "ismaxcalc" CHAR(1) DEFAULT 'N' NOT NULL,
    "isrunningtotal" CHAR(1) DEFAULT 'N' NOT NULL,
    "runningtotallines" INTEGER,
    "isvariancecalc" CHAR(1) DEFAULT 'N' NOT NULL,
    "isdeviationcalc" CHAR(1) DEFAULT 'N' NOT NULL,
    "isfilledrectangle" CHAR(1) DEFAULT 'N' NOT NULL,
    "linewidth" INTEGER,
    "arcdiameter" INTEGER,
    "shapetype" CHAR(1),
    "iscentrallymaintained" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isimagefield" CHAR(1) DEFAULT 'N' NOT NULL,
    "barcodetype" CHAR(3),
    PRIMARY KEY ("ad_printformatitem_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPrinted in ('Y','N')),
    CHECK (IsRelativePosition in ('Y','N')),
    CHECK (IsNextLine in ('Y','N')),
    CHECK (IsHeightOneLine in ('Y','N')),
    CHECK (IsOrderBy in ('Y','N')),
    CHECK (IsGroupBy in ('Y','N')),
    CHECK (IsPageBreak in ('Y','N')),
    CHECK (IsSummarized in ('Y','N'))
);

CREATE INDEX "ad_printformatitem_format" ON "ad_printformatitem" ("ad_printformat_id");

-- ----------------------------------------------------------------------- 
-- AD_PRINTFORMATITEM_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printformatitem_trl"
(
    "ad_printformatitem_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "printname" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "printnamesuffix" VARCHAR(60),
    PRIMARY KEY ("ad_printformatitem_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PRINTGRAPH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printgraph"
(
    "ad_printgraph_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "graphtype" CHAR(1) NOT NULL,
    "description_printformatitem_id" INTEGER NOT NULL,
    "data_printformatitem_id" INTEGER NOT NULL,
    "data1_printformatitem_id" INTEGER,
    "data2_printformatitem_id" INTEGER,
    "data3_printformatitem_id" INTEGER,
    "data4_printformatitem_id" INTEGER,
    "ad_printformat_id" INTEGER NOT NULL,
    PRIMARY KEY ("ad_printgraph_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PRINTLABEL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printlabel"
(
    "ad_printlabel_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_table_id" INTEGER NOT NULL,
    "printername" VARCHAR(40),
    "islandscape" CHAR(1) DEFAULT 'Y' NOT NULL,
    "labelheight" INTEGER NOT NULL,
    "labelwidth" INTEGER NOT NULL,
    "ad_labelprinter_id" INTEGER NOT NULL,
    PRIMARY KEY ("ad_printlabel_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsLandscape in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PRINTLABELLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printlabelline"
(
    "ad_printlabelline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_printlabel_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "seqno" INTEGER NOT NULL,
    "labelformattype" CHAR(1) NOT NULL,
    "printname" VARCHAR(60),
    "ad_column_id" INTEGER,
    "ad_labelprinterfunction_id" INTEGER NOT NULL,
    "xposition" INTEGER NOT NULL,
    "yposition" INTEGER NOT NULL,
    PRIMARY KEY ("ad_printlabelline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PRINTLABELLINE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printlabelline_trl"
(
    "ad_printlabelline_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "printname" VARCHAR(60),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_printlabelline_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PRINTPAPER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printpaper"
(
    "ad_printpaper_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "islandscape" CHAR(1) DEFAULT 'Y' NOT NULL,
    "code" VARCHAR(2000) NOT NULL,
    "margintop" INTEGER DEFAULT 36 NOT NULL,
    "marginleft" INTEGER DEFAULT 36 NOT NULL,
    "marginright" INTEGER DEFAULT 36 NOT NULL,
    "marginbottom" INTEGER DEFAULT 36 NOT NULL,
    "processing" CHAR(1),
    "sizex" NUMERIC,
    "sizey" NUMERIC,
    "dimensionunits" CHAR(1),
    PRIMARY KEY ("ad_printpaper_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsLandscape in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_printpaper_name" ON "ad_printpaper" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- AD_PRINTTABLEFORMAT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_printtableformat"
(
    "ad_printtableformat_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "hdr_printfont_id" INTEGER,
    "hdrtextfg_printcolor_id" INTEGER,
    "hdrtextbg_printcolor_id" INTEGER,
    "hdrline_printcolor_id" INTEGER,
    "funct_printfont_id" INTEGER,
    "functbg_printcolor_id" INTEGER,
    "functfg_printcolor_id" INTEGER,
    "line_printcolor_id" INTEGER,
    "description" VARCHAR(255),
    "ispaintboundarylines" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispainthlines" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispaintvlines" CHAR(1) DEFAULT 'N' NOT NULL,
    "isprintfunctionsymbols" CHAR(1) DEFAULT 'Y' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "imageurl" VARCHAR(120),
    "headerleft" VARCHAR(255),
    "headercenter" VARCHAR(255),
    "headerright" VARCHAR(255),
    "footerleft" VARCHAR(255),
    "footercenter" VARCHAR(255),
    "footerright" VARCHAR(255),
    "imageisattached" CHAR(1) DEFAULT 'N',
    "hdrstroke" NUMERIC,
    "linestroke" NUMERIC,
    "hdrstroketype" CHAR(1),
    "linestroketype" CHAR(1),
    "ispaintheaderlines" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_printtableformat_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PRIVATE_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_private_access"
(
    "ad_user_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("ad_user_id", "ad_table_id", "record_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PROCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_process"
(
    "ad_process_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "accesslevel" CHAR(1) NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "procedurename" VARCHAR(60),
    "isreport" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isdirectprint" CHAR(1) DEFAULT 'N',
    "ad_reportview_id" INTEGER,
    "classname" VARCHAR(60),
    "statistic_count" INTEGER,
    "statistic_seconds" NUMERIC,
    "ad_printformat_id" INTEGER,
    "workflowvalue" VARCHAR(40),
    "ad_workflow_id" INTEGER,
    "isbetafunctionality" CHAR(1) DEFAULT 'N' NOT NULL,
    "isserverprocess" CHAR(1) DEFAULT 'N' NOT NULL,
    "showhelp" CHAR(1) DEFAULT 'Y',
    PRIMARY KEY ("ad_process_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReport in ('Y','N')),
    CHECK (IsDirectPrint in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PROCESS_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_process_access"
(
    "ad_process_id" INTEGER NOT NULL,
    "ad_role_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadwrite" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_process_id", "ad_role_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReadWrite in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PROCESS_PARA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_process_para"
(
    "ad_process_para_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_process_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "ad_reference_id" INTEGER NOT NULL,
    "ad_reference_value_id" INTEGER,
    "ad_val_rule_id" INTEGER,
    "columnname" VARCHAR(40) NOT NULL,
    "iscentrallymaintained" CHAR(1) DEFAULT 'Y' NOT NULL,
    "fieldlength" INTEGER NOT NULL,
    "ismandatory" CHAR(1) DEFAULT 'N' NOT NULL,
    "isrange" CHAR(1) DEFAULT 'N' NOT NULL,
    "defaultvalue" VARCHAR(60),
    "defaultvalue2" VARCHAR(60),
    "vformat" VARCHAR(20),
    "valuemin" VARCHAR(20),
    "valuemax" VARCHAR(20),
    "ad_element_id" INTEGER,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_process_para_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCentrallyMaintained in ('Y','N')),
    CHECK (IsMandatory in ('Y','N')),
    CHECK (IsRange in ('Y','N'))
);

CREATE INDEX "ad_process_para_process" ON "ad_process_para" ("ad_process_id");

-- ----------------------------------------------------------------------- 
-- AD_PROCESS_PARA_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_process_para_trl"
(
    "ad_process_para_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_process_para_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_PROCESS_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_process_trl"
(
    "ad_process_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_process_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_RECORD_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_record_access"
(
    "ad_role_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "isexclude" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isdependententities" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_role_id", "ad_table_id", "record_id"),
    CHECK (IsDependentEntities in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REFERENCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_reference"
(
    "ad_reference_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "validationtype" CHAR(1) NOT NULL,
    "vformat" VARCHAR(40),
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_reference_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_reference_name" ON "ad_reference" ("name");

-- ----------------------------------------------------------------------- 
-- AD_REFERENCE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_reference_trl"
(
    "ad_reference_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("ad_reference_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REF_LIST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_ref_list"
(
    "ad_ref_list_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(60) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_reference_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP,
    "validto" TIMESTAMP,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_ref_list_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_ref_list_value" ON "ad_ref_list" ("ad_reference_id", "value");

-- ----------------------------------------------------------------------- 
-- AD_REF_LIST_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_ref_list_trl"
(
    "ad_ref_list_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_ref_list_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REF_TABLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_ref_table"
(
    "ad_reference_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "ad_key" INTEGER NOT NULL,
    "ad_display" INTEGER NOT NULL,
    "isvaluedisplayed" CHAR(1) DEFAULT 'N' NOT NULL,
    "whereclause" VARCHAR(2000),
    "orderbyclause" VARCHAR(2000),
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_reference_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (isValueDisplayed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REGISTRATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_registration"
(
    "ad_registration_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_system_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isregistered" CHAR(1) DEFAULT 'N' NOT NULL,
    "record_id" INTEGER,
    "description" VARCHAR(255),
    "c_location_id" INTEGER,
    "isinproduction" CHAR(1) DEFAULT 'N' NOT NULL,
    "startproductiondate" TIMESTAMP,
    "isallowpublish" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isallowstatistics" CHAR(1) DEFAULT 'Y' NOT NULL,
    "platforminfo" VARCHAR(255),
    "industryinfo" VARCHAR(255),
    "salesvolume" INTEGER DEFAULT 0,
    "c_currency_id" INTEGER,
    "numberemployees" INTEGER DEFAULT 0,
    "processing" CHAR(1),
    "remote_host" VARCHAR(120),
    "remote_addr" VARCHAR(60),
    PRIMARY KEY ("ad_registration_id", "ad_client_id", "ad_system_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsRegistered in ('Y','N')),
    CHECK (IsInProduction in ('Y','N')),
    CHECK (IsAllowPublish in ('Y','N')),
    CHECK (IsAllowStatistics in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REPLICATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_replication"
(
    "ad_replication_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "hostaddress" VARCHAR(60) NOT NULL,
    "hostport" INTEGER NOT NULL,
    "ad_replicationstrategy_id" INTEGER NOT NULL,
    "isrmioverhttp" CHAR(1) DEFAULT 'Y' NOT NULL,
    "processing" CHAR(1),
    "idrangestart" NUMERIC,
    "idrangeend" NUMERIC,
    "remote_client_id" INTEGER,
    "remote_org_id" INTEGER,
    "prefix" VARCHAR(10),
    "suffix" VARCHAR(10),
    "datelastrun" TIMESTAMP,
    PRIMARY KEY ("ad_replication_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REPLICATIONSTRATEGY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_replicationstrategy"
(
    "ad_replicationstrategy_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_replicationstrategy_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REPLICATIONTABLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_replicationtable"
(
    "ad_replicationtable_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_replicationstrategy_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "replicationtype" CHAR(1) DEFAULT 'L' NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_replicationtable_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REPLICATION_LOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_replication_log"
(
    "ad_replication_log_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_replication_run_id" INTEGER NOT NULL,
    "ad_replicationtable_id" INTEGER,
    "p_msg" VARCHAR(2000),
    "isreplicated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_replication_log_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReplicated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REPLICATION_RUN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_replication_run"
(
    "ad_replication_run_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "ad_replication_id" INTEGER NOT NULL,
    "isreplicated" CHAR(1) DEFAULT 'N' NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("ad_replication_run_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReplicated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REPORTVIEW 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_reportview"
(
    "ad_reportview_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_table_id" INTEGER NOT NULL,
    "whereclause" VARCHAR(2000),
    "orderbyclause" VARCHAR(2000),
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_reportview_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_REPORTVIEW_COL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_reportview_col"
(
    "ad_reportview_col_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_reportview_id" INTEGER NOT NULL,
    "ad_column_id" INTEGER,
    "functioncolumn" VARCHAR(60) NOT NULL,
    "isgroupfunction" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_reportview_col_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsGroupFunction in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ROLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_role"
(
    "ad_role_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "userlevel" CHAR(3) DEFAULT '  O' NOT NULL,
    "c_currency_id" INTEGER,
    "amtapproval" NUMERIC DEFAULT 0,
    "ad_tree_menu_id" INTEGER,
    "ismanual" CHAR(1) DEFAULT 'N' NOT NULL,
    "isshowacct" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ispersonallock" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispersonalaccess" CHAR(1) DEFAULT 'N' NOT NULL,
    "iscanexport" CHAR(1) DEFAULT 'Y' NOT NULL,
    "iscanreport" CHAR(1) DEFAULT 'Y' NOT NULL,
    "supervisor_id" INTEGER,
    "iscanapproveowndoc" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isaccessallorgs" CHAR(1) DEFAULT 'N' NOT NULL,
    "ischangelog" CHAR(1) DEFAULT 'N' NOT NULL,
    "preferencetype" CHAR(1) DEFAULT 'C' NOT NULL,
    "overwritepricelimit" CHAR(1) DEFAULT 'N' NOT NULL,
    "isuseuserorgaccess" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_tree_org_id" INTEGER,
    "confirmqueryrecords" INTEGER DEFAULT 0 NOT NULL,
    "maxqueryrecords" INTEGER DEFAULT 0 NOT NULL,
    "connectionprofile" CHAR(1),
    PRIMARY KEY ("ad_role_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_ROLE_ORGACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_role_orgaccess"
(
    "ad_role_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_role_id", "ad_org_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_SCHEDULER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_scheduler"
(
    "ad_scheduler_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_process_id" INTEGER NOT NULL,
    "frequencytype" CHAR(1) NOT NULL,
    "frequency" INTEGER NOT NULL,
    "datelastrun" TIMESTAMP,
    "datenextrun" TIMESTAMP,
    "supervisor_id" INTEGER NOT NULL,
    "keeplogdays" INTEGER NOT NULL,
    "processing" CHAR(1),
    "weekday" CHAR(1),
    "scheduletype" CHAR(1) DEFAULT 'F' NOT NULL,
    "monthday" INTEGER,
    PRIMARY KEY ("ad_scheduler_id")
);

-- ----------------------------------------------------------------------- 
-- AD_SCHEDULERLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_schedulerlog"
(
    "ad_scheduler_id" INTEGER NOT NULL,
    "ad_schedulerlog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "iserror" CHAR(1) DEFAULT 'N' NOT NULL,
    "summary" VARCHAR(2000),
    "reference" VARCHAR(60),
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    "binarydata" BYTEA,
    PRIMARY KEY ("ad_scheduler_id", "ad_schedulerlog_id")
);

-- ----------------------------------------------------------------------- 
-- AD_SCHEDULERRECIPIENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_schedulerrecipient"
(
    "ad_schedulerrecipient_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_scheduler_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "ad_role_id" INTEGER,
    PRIMARY KEY ("ad_schedulerrecipient_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_SCHEDULER_PARA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_scheduler_para"
(
    "ad_scheduler_id" INTEGER NOT NULL,
    "ad_process_para_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "parameterdefault" VARCHAR(60),
    "description" VARCHAR(255),
    PRIMARY KEY ("ad_scheduler_id", "ad_process_para_id")
);

-- ----------------------------------------------------------------------- 
-- AD_SEQUENCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_sequence"
(
    "ad_sequence_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "vformat" VARCHAR(40),
    "isautosequence" CHAR(1) DEFAULT 'Y' NOT NULL,
    "incrementno" INTEGER NOT NULL,
    "startno" INTEGER NOT NULL,
    "currentnext" INTEGER NOT NULL,
    "currentnextsys" INTEGER NOT NULL,
    "isaudited" CHAR(1) DEFAULT 'N',
    "istableid" CHAR(1) DEFAULT 'N',
    "prefix" VARCHAR(10),
    "suffix" VARCHAR(10),
    "startnewyear" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("ad_sequence_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsAutoSequence in ('Y','N')),
    CHECK (IsAudited in ('Y','N')),
    CHECK (IsTableID in ('Y','N')),
    CHECK (StartNewYear in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_sequence_name" ON "ad_sequence" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- AD_SEQUENCE_AUDIT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_sequence_audit"
(
    "ad_sequence_id" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    PRIMARY KEY ("ad_sequence_id", "documentno"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_SEQUENCE_NO 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_sequence_no"
(
    "ad_sequence_id" INTEGER NOT NULL,
    "calendaryear" VARCHAR(4) DEFAULT '0000' NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "currentnext" INTEGER NOT NULL,
    PRIMARY KEY ("ad_sequence_id", "calendaryear"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_SESSION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_session"
(
    "ad_session_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "websession" VARCHAR(40),
    "remote_addr" VARCHAR(60),
    "remote_host" VARCHAR(120),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_session_id")
);

-- ----------------------------------------------------------------------- 
-- AD_SYSTEM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_system"
(
    "ad_system_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "username" VARCHAR(60) NOT NULL,
    "info" VARCHAR(255),
    "version" VARCHAR(20),
    "releaseno" CHAR(4) NOT NULL,
    "supportunits" INTEGER,
    "password" VARCHAR(20),
    "replicationtype" CHAR(1) DEFAULT 'L' NOT NULL,
    "idrangestart" NUMERIC,
    "idrangeend" NUMERIC,
    "ldaphost" VARCHAR(60),
    "customprefix" VARCHAR(60),
    "isjustmigrated" CHAR(1) DEFAULT 'N',
    "dbinstance" VARCHAR(60),
    "dbaddress" VARCHAR(255),
    "noprocessors" INTEGER,
    "summary" VARCHAR(255),
    "encryptionkey" VARCHAR(255),
    "ldapdomain" VARCHAR(255),
    "isautoerrorreport" CHAR(1) DEFAULT 'Y' NOT NULL,
    "record_id" INTEGER,
    "supportexpdate" TIMESTAMP,
    "processing" CHAR(1),
    "supportemail" VARCHAR(60),
    "isallowstatistics" CHAR(1) DEFAULT 'Y' NOT NULL,
    "statisticsinfo" VARCHAR(60),
    "profileinfo" VARCHAR(60),
    "oldname" VARCHAR(60),
    "description" VARCHAR(255),
    "systemstatus" CHAR(1) DEFAULT 'E' NOT NULL,
    PRIMARY KEY ("ad_system_id", "ad_client_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TAB 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_tab"
(
    "ad_tab_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_table_id" INTEGER NOT NULL,
    "ad_window_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "tablevel" INTEGER NOT NULL,
    "issinglerow" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isinfotab" CHAR(1) DEFAULT 'N',
    "istranslationtab" CHAR(1) DEFAULT 'N' NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_column_id" INTEGER,
    "hastree" CHAR(1) DEFAULT 'N' NOT NULL,
    "whereclause" VARCHAR(2000),
    "orderbyclause" VARCHAR(2000),
    "commitwarning" VARCHAR(2000),
    "ad_process_id" INTEGER,
    "processing" CHAR(1),
    "ad_image_id" INTEGER,
    "importfields" CHAR(1),
    "ad_columnsortorder_id" INTEGER,
    "ad_columnsortyesno_id" INTEGER,
    "issorttab" CHAR(1) DEFAULT 'N' NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "included_tab_id" INTEGER,
    "readonlylogic" VARCHAR(2000),
    "displaylogic" VARCHAR(2000),
    "isinsertrecord" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isadvancedtab" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_tab_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSingleRow in ('Y','N')),
    CHECK (IsInfoTab in ('Y','N')),
    CHECK (IsTranslationTab in ('Y','N')),
    CHECK (IsReadOnly in ('Y','N')),
    CHECK (HasTree in ('Y','N'))
);

CREATE INDEX "ad_tab_table" ON "ad_tab" ("ad_table_id");

CREATE INDEX "ad_tab_window" ON "ad_tab" ("ad_window_id");

-- ----------------------------------------------------------------------- 
-- AD_TABLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_table"
(
    "ad_table_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "tablename" VARCHAR(40) NOT NULL,
    "isview" CHAR(1) DEFAULT 'N' NOT NULL,
    "accesslevel" CHAR(1) NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "ad_window_id" INTEGER,
    "ad_val_rule_id" INTEGER,
    "loadseq" INTEGER,
    "issecurityenabled" CHAR(1) DEFAULT 'N' NOT NULL,
    "isdeleteable" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ishighvolume" CHAR(1) DEFAULT 'N' NOT NULL,
    "importtable" CHAR(1),
    "ischangelog" CHAR(1) DEFAULT 'N' NOT NULL,
    "replicationtype" CHAR(1) DEFAULT 'L' NOT NULL,
    "po_window_id" INTEGER,
    PRIMARY KEY ("ad_table_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsView in ('Y','N')),
    CHECK (IsSecurityEnabled in ('Y','N')),
    CHECK (IsDeleteable in ('Y','N')),
    CHECK (IsHighVolume in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_table_name" ON "ad_table" ("tablename");

-- ----------------------------------------------------------------------- 
-- AD_TABLE_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_table_access"
(
    "ad_role_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "accesstyperule" CHAR(1) DEFAULT 'G' NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "iscanreport" CHAR(1) DEFAULT 'Y' NOT NULL,
    "iscanexport" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isexclude" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_role_id", "ad_table_id", "accesstyperule"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReadOnly in ('Y','N')),
    CHECK (IsCanReport in ('Y','N')),
    CHECK (IsCanExport in ('Y','N')),
    CHECK (IsExclude in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TABLE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_table_trl"
(
    "ad_table_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_table_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TAB_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_tab_trl"
(
    "ad_tab_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "commitwarning" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_tab_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TASK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_task"
(
    "ad_task_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "accesslevel" CHAR(1) NOT NULL,
    "os_command" VARCHAR(2000) NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "isserverprocess" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_task_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_task_name" ON "ad_task" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- AD_TASKINSTANCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_taskinstance"
(
    "ad_taskinstance_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER,
    "ad_task_id" INTEGER NOT NULL,
    PRIMARY KEY ("ad_taskinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_taskinstance_task" ON "ad_taskinstance" ("ad_task_id");

-- ----------------------------------------------------------------------- 
-- AD_TASK_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_task_access"
(
    "ad_task_id" INTEGER NOT NULL,
    "ad_role_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadwrite" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_task_id", "ad_role_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReadWrite in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TASK_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_task_trl"
(
    "ad_task_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_task_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_tree"
(
    "ad_tree_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "treetype" CHAR(2) NOT NULL,
    "isallnodes" CHAR(1) DEFAULT 'Y' NOT NULL,
    "processing" CHAR(1),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_tree_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsAllNodes in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_tree_name" ON "ad_tree" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- AD_TREEBAR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treebar"
(
    "ad_tree_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("ad_tree_id", "ad_user_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREENODE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenode"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "parent_id" INTEGER,
    "seqno" INTEGER,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_treenode_parentid" ON "ad_treenode" ("parent_id");

-- ----------------------------------------------------------------------- 
-- AD_TREENODEBP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodebp"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "parent_id" INTEGER,
    "seqno" INTEGER,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_treenodebp_parent" ON "ad_treenodebp" ("parent_id");

-- ----------------------------------------------------------------------- 
-- AD_TREENODECMC 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodecmc"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "parent_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREENODECMM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodecmm"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "parent_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREENODECMS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodecms"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "parent_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREENODECMT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodecmt"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "parent_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREENODEMM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodemm"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "parent_id" INTEGER,
    "seqno" INTEGER,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_treenodemm_parent" ON "ad_treenodemm" ("parent_id");

-- ----------------------------------------------------------------------- 
-- AD_TREENODEPR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodepr"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "parent_id" INTEGER,
    "seqno" INTEGER,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_treenodepr_parent" ON "ad_treenodepr" ("parent_id");

-- ----------------------------------------------------------------------- 
-- AD_TREENODEU1 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodeu1"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "parent_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREENODEU2 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodeu2"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "parent_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREENODEU3 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodeu3"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "parent_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_TREENODEU4 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_treenodeu4"
(
    "ad_tree_id" INTEGER NOT NULL,
    "node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "parent_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_tree_id", "node_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_user"
(
    "ad_user_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "password" VARCHAR(40),
    "email" VARCHAR(60),
    "supervisor_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "processing" CHAR(1),
    "emailuser" VARCHAR(60),
    "emailuserpw" VARCHAR(20),
    "c_bpartner_location_id" INTEGER,
    "c_greeting_id" INTEGER,
    "title" VARCHAR(40),
    "comments" VARCHAR(2000),
    "phone" VARCHAR(40),
    "phone2" VARCHAR(40),
    "fax" VARCHAR(40),
    "lastcontact" TIMESTAMP,
    "lastresult" VARCHAR(255),
    "birthday" TIMESTAMP,
    "ad_orgtrx_id" INTEGER,
    "emailverify" VARCHAR(40),
    "emailverifydate" TIMESTAMP,
    "notificationtype" CHAR(1) DEFAULT 'E' NOT NULL,
    "isfullbpaccess" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_job_id" INTEGER,
    "ldapuser" VARCHAR(60),
    "connectionprofile" CHAR(1),
    "value" VARCHAR(40),
    PRIMARY KEY ("ad_user_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_user_email" ON "ad_user" ("email");

-- ----------------------------------------------------------------------- 
-- AD_USERBPACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_userbpaccess"
(
    "ad_userbpaccess_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "bpaccesstype" CHAR(1) NOT NULL,
    "r_requesttype_id" INTEGER,
    "docbasetype" CHAR(3),
    PRIMARY KEY ("ad_userbpaccess_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USERDEF_FIELD 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_userdef_field"
(
    "ad_userdef_field_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_userdef_tab_id" INTEGER NOT NULL,
    "ad_field_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isdisplayed" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "issameline" CHAR(1) DEFAULT 'N' NOT NULL,
    "isupdateable" CHAR(1) DEFAULT 'Y' NOT NULL,
    "displaylength" INTEGER DEFAULT 0 NOT NULL,
    "displaylogic" VARCHAR(2000) NOT NULL,
    "defaultvalue" VARCHAR(2000) NOT NULL,
    "sortno" INTEGER DEFAULT 0 NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("ad_userdef_field_id"),
    CHECK (IsReadOnly in ('Y','N')),
    CHECK (IsSameLine in ('Y','N')),
    CHECK (IsUpdateable in ('Y','N')),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDisplayed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USERDEF_TAB 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_userdef_tab"
(
    "ad_userdef_tab_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_userdef_win_id" INTEGER NOT NULL,
    "ad_tab_id" INTEGER NOT NULL,
    "ismultirowonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "issinglerow" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_userdef_tab_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsMultiRowOnly in ('Y','N')),
    CHECK (IsReadOnly in ('Y','N')),
    CHECK (IsSingleRow in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USERDEF_WIN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_userdef_win"
(
    "ad_userdef_win_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_role_id" INTEGER,
    "ad_user_id" INTEGER,
    "ad_window_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_language" VARCHAR(6),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "isuserupdateable" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_userdef_win_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDefault in ('Y','N')),
    CHECK (IsReadOnly in ('Y','N')),
    CHECK (IsUserUpdateable in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USERMAIL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_usermail"
(
    "ad_usermail_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "r_mailtext_id" INTEGER,
    "w_mailmsg_id" INTEGER,
    "messageid" VARCHAR(120),
    "deliveryconfirmation" VARCHAR(120),
    "isdelivered" CHAR(1),
    "subject" VARCHAR(255),
    "mailtext" VARCHAR(2000),
    PRIMARY KEY ("ad_usermail_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USERQUERY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_userquery"
(
    "ad_userquery_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_user_id" INTEGER,
    "ad_table_id" INTEGER NOT NULL,
    "code" VARCHAR(2000),
    PRIMARY KEY ("ad_userquery_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USER_ORGACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_user_orgaccess"
(
    "ad_user_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadonly" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_user_id", "ad_org_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USER_ROLES 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_user_roles"
(
    "ad_user_id" INTEGER NOT NULL,
    "ad_role_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("ad_user_id", "ad_role_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_USER_SUBSTITUTE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_user_substitute"
(
    "ad_user_substitute_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "substitute_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP,
    "validto" TIMESTAMP,
    PRIMARY KEY ("ad_user_substitute_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_VAL_RULE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_val_rule"
(
    "ad_val_rule_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "type" CHAR(1),
    "code" VARCHAR(2000),
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_val_rule_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WF_ACTIVITY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_activity"
(
    "ad_wf_activity_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_wf_process_id" INTEGER NOT NULL,
    "ad_wf_node_id" INTEGER NOT NULL,
    "ad_wf_responsible_id" INTEGER,
    "ad_user_id" INTEGER,
    "wfstate" CHAR(2) NOT NULL,
    "ad_message_id" INTEGER,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "textmsg" VARCHAR(2000),
    "ad_workflow_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "priority" INTEGER,
    "endwaittime" TIMESTAMP,
    "datelastalert" TIMESTAMP,
    "dynprioritystart" INTEGER,
    PRIMARY KEY ("ad_wf_activity_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE INDEX "ad_wf_activity_status" ON "ad_wf_activity" ("processed", "wfstate");

CREATE INDEX "ad_wf_activity_who" ON "ad_wf_activity" ("ad_wf_responsible_id", "ad_user_id");

CREATE INDEX "ad_wf_activity_item" ON "ad_wf_activity" ("ad_wf_process_id", "ad_wf_node_id");

-- ----------------------------------------------------------------------- 
-- AD_WF_ACTIVITYRESULT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_activityresult"
(
    "ad_wf_activityresult_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_wf_activity_id" INTEGER NOT NULL,
    "attributename" VARCHAR(60) NOT NULL,
    "attributevalue" VARCHAR(2000),
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("ad_wf_activityresult_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WF_BLOCK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_block"
(
    "ad_wf_block_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_workflow_id" INTEGER NOT NULL,
    PRIMARY KEY ("ad_wf_block_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WF_EVENTAUDIT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_eventaudit"
(
    "ad_wf_eventaudit_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "eventtype" CHAR(2) NOT NULL,
    "wfstate" CHAR(2) NOT NULL,
    "ad_wf_process_id" INTEGER NOT NULL,
    "ad_wf_node_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "ad_wf_responsible_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "elapsedtimems" NUMERIC NOT NULL,
    "attributename" VARCHAR(60),
    "oldvalue" VARCHAR(2000),
    "newvalue" VARCHAR(2000),
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    PRIMARY KEY ("ad_wf_eventaudit_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_wf_eventaudit_parent" ON "ad_wf_eventaudit" ("ad_wf_process_id", "ad_wf_node_id");

-- ----------------------------------------------------------------------- 
-- AD_WF_NEXTCONDITION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_nextcondition"
(
    "ad_wf_nextcondition_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_wf_nodenext_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "andor" CHAR(1) NOT NULL,
    "ad_column_id" INTEGER NOT NULL,
    "operation" CHAR(2) NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "value2" VARCHAR(40),
    PRIMARY KEY ("ad_wf_nextcondition_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WF_NODE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_node"
(
    "ad_wf_node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_workflow_id" INTEGER NOT NULL,
    "iscentrallymaintained" CHAR(1) DEFAULT 'Y' NOT NULL,
    "action" CHAR(1) NOT NULL,
    "ad_window_id" INTEGER,
    "workflow_id" INTEGER,
    "ad_task_id" INTEGER,
    "ad_process_id" INTEGER,
    "ad_form_id" INTEGER,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "xposition" INTEGER DEFAULT 0 NOT NULL,
    "yposition" INTEGER DEFAULT 0 NOT NULL,
    "ad_wf_block_id" INTEGER,
    "subflowexecution" CHAR(1),
    "startmode" CHAR(1),
    "finishmode" CHAR(1),
    "limit" INTEGER DEFAULT 0 NOT NULL,
    "priority" INTEGER,
    "duration" INTEGER DEFAULT 0 NOT NULL,
    "cost" NUMERIC DEFAULT 0 NOT NULL,
    "workingtime" INTEGER,
    "waitingtime" INTEGER DEFAULT 0 NOT NULL,
    "ad_wf_responsible_id" INTEGER,
    "ad_image_id" INTEGER,
    "joinelement" CHAR(1) NOT NULL,
    "splitelement" CHAR(1) NOT NULL,
    "waittime" INTEGER,
    "ad_column_id" INTEGER,
    "attributename" VARCHAR(60),
    "attributevalue" VARCHAR(60),
    "docaction" CHAR(2),
    "value" VARCHAR(40) NOT NULL,
    "dynpriorityunit" CHAR(1),
    "dynprioritychange" NUMERIC,
    "emailrecipient" CHAR(1),
    "email" VARCHAR(60),
    "r_mailtext_id" INTEGER,
    PRIMARY KEY ("ad_wf_node_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCentrallyMaintained in ('Y','N'))
);

CREATE INDEX "ad_wf_node_workflow" ON "ad_wf_node" ("ad_workflow_id");

-- ----------------------------------------------------------------------- 
-- AD_WF_NODENEXT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_nodenext"
(
    "ad_wf_nodenext_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_wf_node_id" INTEGER NOT NULL,
    "ad_wf_next_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "seqno" INTEGER NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "transitioncode" VARCHAR(2000),
    "isstduserworkflow" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_wf_nodenext_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WF_NODE_PARA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_node_para"
(
    "ad_wf_node_para_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_wf_node_id" INTEGER NOT NULL,
    "attributename" VARCHAR(60),
    "ad_process_para_id" INTEGER,
    "description" VARCHAR(255),
    "attributevalue" VARCHAR(60),
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_wf_node_para_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WF_NODE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_node_trl"
(
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_wf_node_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_language", "ad_wf_node_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WF_PROCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_process"
(
    "ad_wf_process_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_workflow_id" INTEGER NOT NULL,
    "ad_wf_responsible_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "wfstate" CHAR(2) NOT NULL,
    "ad_message_id" INTEGER,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "textmsg" VARCHAR(2000),
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "priority" INTEGER,
    PRIMARY KEY ("ad_wf_process_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE INDEX "ad_wf_process_workflow" ON "ad_wf_process" ("ad_workflow_id");

-- ----------------------------------------------------------------------- 
-- AD_WF_PROCESSDATA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_processdata"
(
    "ad_wf_processdata_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_wf_process_id" INTEGER NOT NULL,
    "attributename" VARCHAR(60) NOT NULL,
    "attributevalue" VARCHAR(60),
    PRIMARY KEY ("ad_wf_processdata_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "ad_wf_processdata_process" ON "ad_wf_processdata" ("ad_wf_process_id");

-- ----------------------------------------------------------------------- 
-- AD_WF_RESPONSIBLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_wf_responsible"
(
    "ad_wf_responsible_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "responsibletype" CHAR(1) NOT NULL,
    "ad_user_id" INTEGER,
    "ad_role_id" INTEGER,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_wf_responsible_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WINDOW 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_window"
(
    "ad_window_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "windowtype" CHAR(1),
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "processing" CHAR(1),
    "ad_image_id" INTEGER,
    "ad_color_id" INTEGER,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "winheight" INTEGER,
    "winwidth" INTEGER,
    "isbetafunctionality" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_window_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_window_name" ON "ad_window" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- AD_WINDOW_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_window_access"
(
    "ad_window_id" INTEGER NOT NULL,
    "ad_role_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadwrite" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_window_id", "ad_role_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReadWrite in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WINDOW_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_window_trl"
(
    "ad_window_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_window_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WORKBENCH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_workbench"
(
    "ad_workbench_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_column_id" INTEGER NOT NULL,
    "ad_image_id" INTEGER,
    "ad_color_id" INTEGER,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_workbench_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WORKBENCHWINDOW 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_workbenchwindow"
(
    "ad_workbenchwindow_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_workbench_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "isprimary" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_window_id" INTEGER,
    "ad_form_id" INTEGER,
    "ad_process_id" INTEGER,
    "ad_task_id" INTEGER,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    PRIMARY KEY ("ad_workbenchwindow_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPrimary in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WORKBENCH_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_workbench_trl"
(
    "ad_workbench_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_workbench_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WORKFLOW 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_workflow"
(
    "ad_workflow_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "accesslevel" CHAR(1) NOT NULL,
    "ad_wf_node_id" INTEGER,
    "entitytype" VARCHAR(4) DEFAULT 'D' NOT NULL,
    "durationunit" CHAR(1),
    "author" VARCHAR(20) DEFAULT 'ComPiere' NOT NULL,
    "version" INTEGER DEFAULT 0 NOT NULL,
    "validfrom" TIMESTAMP,
    "validto" TIMESTAMP,
    "priority" INTEGER,
    "limit" INTEGER,
    "duration" INTEGER DEFAULT 0 NOT NULL,
    "cost" NUMERIC DEFAULT 0 NOT NULL,
    "workingtime" INTEGER DEFAULT 0 NOT NULL,
    "waitingtime" INTEGER DEFAULT 0 NOT NULL,
    "ad_wf_responsible_id" INTEGER,
    "publishstatus" CHAR(1) NOT NULL,
    "ad_workflowprocessor_id" INTEGER,
    "value" VARCHAR(40) NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_table_id" INTEGER,
    "validateworkflow" CHAR(1),
    "workflowtype" CHAR(1) DEFAULT 'G' NOT NULL,
    "docvaluelogic" VARCHAR(2000),
    "isvalid" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_workflow_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "ad_workflow_name" ON "ad_workflow" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- AD_WORKFLOWPROCESSOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_workflowprocessor"
(
    "ad_workflowprocessor_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "frequencytype" CHAR(1) NOT NULL,
    "frequency" INTEGER NOT NULL,
    "datelastrun" TIMESTAMP,
    "datenextrun" TIMESTAMP,
    "supervisor_id" INTEGER NOT NULL,
    "keeplogdays" INTEGER NOT NULL,
    "processing" CHAR(1),
    "inactivityalertdays" INTEGER DEFAULT 0,
    "reminddays" INTEGER DEFAULT 0,
    "alertoverpriority" INTEGER,
    PRIMARY KEY ("ad_workflowprocessor_id")
);

-- ----------------------------------------------------------------------- 
-- AD_WORKFLOWPROCESSORLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_workflowprocessorlog"
(
    "ad_workflowprocessor_id" INTEGER NOT NULL,
    "ad_workflowprocessorlog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "iserror" CHAR(1) DEFAULT 'N' NOT NULL,
    "summary" VARCHAR(2000),
    "reference" VARCHAR(60),
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    "binarydata" BYTEA,
    PRIMARY KEY ("ad_workflowprocessor_id", "ad_workflowprocessorlog_id")
);

-- ----------------------------------------------------------------------- 
-- AD_WORKFLOW_ACCESS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_workflow_access"
(
    "ad_workflow_id" INTEGER NOT NULL,
    "ad_role_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isreadwrite" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("ad_workflow_id", "ad_role_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReadWrite in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- AD_WORKFLOW_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "ad_workflow_trl"
(
    "ad_workflow_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_workflow_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset"
(
    "a_asset_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "a_asset_group_id" INTEGER NOT NULL,
    "m_product_id" INTEGER,
    "serno" VARCHAR(255),
    "lot" VARCHAR(255),
    "versionno" VARCHAR(20),
    "guaranteedate" TIMESTAMP,
    "assetservicedate" TIMESTAMP,
    "isowned" CHAR(1) DEFAULT 'Y' NOT NULL,
    "assetdepreciationdate" TIMESTAMP,
    "uselifeyears" INTEGER,
    "uselifemonths" INTEGER,
    "lifeuseunits" NUMERIC,
    "useunits" NUMERIC,
    "isdisposed" CHAR(1) DEFAULT 'N' NOT NULL,
    "assetdisposaldate" TIMESTAMP,
    "isinposession" CHAR(1) DEFAULT 'Y' NOT NULL,
    "locationcomment" VARCHAR(255),
    "m_locator_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "c_bpartner_location_id" INTEGER,
    "c_location_id" INTEGER,
    "processing" CHAR(1),
    "isdepreciated" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isfullydepreciated" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_user_id" INTEGER,
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "qty" NUMERIC,
    "c_project_id" INTEGER,
    "c_bpartnersr_id" INTEGER,
    "m_inoutline_id" INTEGER,
    "lastmaintenencedate" TIMESTAMP,
    "nextmaintenencedate" TIMESTAMP,
    "lastmaintenanceuseunit" INTEGER,
    "nextmaintenanceuseunit" INTEGER,
    "leaseterminationdate" TIMESTAMP,
    "lease_bpartner_id" INTEGER,
    "lastmaintenancenote" VARCHAR(60),
    "lastmaintenancedate" TIMESTAMP,
    "lastmaintenanceunit" INTEGER,
    "nextmaintenenceunit" INTEGER,
    PRIMARY KEY ("a_asset_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsOwned in ('Y','N')),
    CHECK (IsDisposed in ('Y','N')),
    CHECK (IsInPosession in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_acct"
(
    "a_asset_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "a_depreciation_id" INTEGER NOT NULL,
    "a_depreciation_acct" INTEGER NOT NULL,
    "a_accumdepreciation_acct" INTEGER NOT NULL,
    "a_disposal_loss" INTEGER NOT NULL,
    "a_disposal_gain" INTEGER NOT NULL,
    "a_asset_acct" INTEGER NOT NULL,
    PRIMARY KEY ("a_asset_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_ADDITION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_addition"
(
    "a_asset_addition_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "a_asset_id" INTEGER NOT NULL,
    "assetvalueamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_invoiceline_id" INTEGER,
    PRIMARY KEY ("a_asset_addition_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_CHANGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_change"
(
    "a_asset_change_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "a_asset_id" INTEGER NOT NULL,
    "changetype" CHAR(1) NOT NULL,
    "changedate" TIMESTAMP NOT NULL,
    "changeamt" NUMERIC DEFAULT 0,
    "uselifeyears" INTEGER,
    "uselifemonths" INTEGER,
    "lifeuseunits" NUMERIC,
    "assetdepreciationdate" TIMESTAMP,
    "a_asset_retirement_id" INTEGER,
    "a_asset_addition_id" INTEGER,
    "serno" VARCHAR(20),
    "lot" VARCHAR(20),
    "versionno" VARCHAR(20),
    PRIMARY KEY ("a_asset_change_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_CHANGE_AMT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_change_amt"
(
    "a_asset_change_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "assetvalueamt" NUMERIC DEFAULT 0 NOT NULL,
    "assetmarketvalueamt" NUMERIC DEFAULT 0 NOT NULL,
    "assetbookvalueamt" NUMERIC DEFAULT 0 NOT NULL,
    "assetaccumdepreciationamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("a_asset_change_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_DELIVERY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_delivery"
(
    "a_asset_delivery_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "a_asset_id" INTEGER NOT NULL,
    "movementdate" TIMESTAMP NOT NULL,
    "serno" VARCHAR(40),
    "lot" VARCHAR(40),
    "versionno" VARCHAR(20),
    "m_inoutline_id" INTEGER,
    "email" VARCHAR(60),
    "messageid" VARCHAR(120),
    "deliveryconfirmation" VARCHAR(120),
    "url" VARCHAR(120),
    "remote_addr" VARCHAR(60),
    "remote_host" VARCHAR(60),
    "referrer" VARCHAR(255),
    "ad_user_id" INTEGER,
    "description" VARCHAR(255),
    "m_productdownload_id" INTEGER,
    PRIMARY KEY ("a_asset_delivery_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_GROUP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_group"
(
    "a_asset_group_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isowned" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isdepreciated" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isoneassetperuom" CHAR(1) DEFAULT 'N' NOT NULL,
    "iscreateasactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "istrackissues" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("a_asset_group_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsOwned in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_GROUP_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_group_acct"
(
    "a_asset_group_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "a_asset_acct" INTEGER NOT NULL,
    "a_depreciation_acct" INTEGER NOT NULL,
    "a_accumdepreciation_acct" INTEGER NOT NULL,
    "a_disposal_loss" INTEGER NOT NULL,
    "a_disposal_gain" INTEGER NOT NULL,
    "a_depreciation_id" INTEGER NOT NULL,
    PRIMARY KEY ("a_asset_group_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_RETIREMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_retirement"
(
    "a_asset_retirement_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "a_asset_id" INTEGER NOT NULL,
    "assetvalueamt" NUMERIC DEFAULT 0 NOT NULL,
    "assetmarketvalueamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_invoiceline_id" INTEGER,
    PRIMARY KEY ("a_asset_retirement_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_ASSET_USE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_asset_use"
(
    "a_asset_use_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "a_asset_id" INTEGER NOT NULL,
    "usedate" TIMESTAMP NOT NULL,
    "useunits" NUMERIC NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("a_asset_use_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_DEPRECIATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_depreciation"
(
    "a_depreciation_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "depreciationtype" CHAR(2) NOT NULL,
    "script" VARCHAR(2000),
    PRIMARY KEY ("a_depreciation_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_REGISTRATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_registration"
(
    "a_registration_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "a_asset_id" INTEGER,
    "m_product_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "ad_user_id" INTEGER,
    "isregistered" CHAR(1) DEFAULT 'N' NOT NULL,
    "isinproduction" CHAR(1) DEFAULT 'N' NOT NULL,
    "isallowpublish" CHAR(1) DEFAULT 'Y' NOT NULL,
    "remote_host" VARCHAR(120),
    "remote_addr" VARCHAR(60),
    "processing" CHAR(1),
    "assetservicedate" TIMESTAMP,
    "note" VARCHAR(2000),
    PRIMARY KEY ("a_registration_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsRegistered in ('Y','N')),
    CHECK (IsInProduction in ('Y','N')),
    CHECK (IsAllowPublish in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_REGISTRATIONATTRIBUTE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_registrationattribute"
(
    "a_registrationattribute_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    "ad_reference_id" INTEGER NOT NULL,
    "columnname" VARCHAR(40),
    "ad_reference_value_id" INTEGER,
    "isselfservice" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("a_registrationattribute_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_REGISTRATIONPRODUCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_registrationproduct"
(
    "a_registrationattribute_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("a_registrationattribute_id", "m_product_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- A_REGISTRATIONVALUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "a_registrationvalue"
(
    "a_registration_id" INTEGER NOT NULL,
    "a_registrationattribute_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("a_registration_id", "a_registrationattribute_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_BID 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_bid"
(
    "b_bid_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "b_topic_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "iswillingtocommit" CHAR(1) DEFAULT 'Y' NOT NULL,
    "b_buyerfunds_id" INTEGER NOT NULL,
    "textmsg" VARCHAR(2000),
    "privatenote" VARCHAR(2000),
    PRIMARY KEY ("b_bid_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsWillingToCommit in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_BIDCOMMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_bidcomment"
(
    "b_topic_id" INTEGER NOT NULL,
    "b_bidcomment_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "textmsg" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("b_topic_id", "b_bidcomment_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_BUYER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_buyer"
(
    "ad_user_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "validto" TIMESTAMP NOT NULL,
    PRIMARY KEY ("ad_user_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_BUYERFUNDS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_buyerfunds"
(
    "b_buyerfunds_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "c_order_id" INTEGER,
    "c_payment_id" INTEGER,
    "committedamt" NUMERIC DEFAULT 0 NOT NULL,
    "noncommittedamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("b_buyerfunds_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_OFFER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_offer"
(
    "b_offer_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "b_topic_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "iswillingtocommit" CHAR(1) DEFAULT 'Y' NOT NULL,
    "b_sellerfunds_id" INTEGER NOT NULL,
    "textmsg" VARCHAR(2000),
    "privatenote" VARCHAR(2000),
    PRIMARY KEY ("b_offer_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsWillingToCommit in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_SELLER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_seller"
(
    "ad_user_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "validto" TIMESTAMP NOT NULL,
    "isinternal" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_user_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsInternal in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_SELLERFUNDS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_sellerfunds"
(
    "b_sellerfunds_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "c_order_id" INTEGER,
    "c_payment_id" INTEGER,
    "committedamt" NUMERIC DEFAULT 0 NOT NULL,
    "noncommittedamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("b_sellerfunds_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_TOPIC 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_topic"
(
    "b_topic_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    "topicstatus" CHAR(2) NOT NULL,
    "topicaction" CHAR(2) NOT NULL,
    "ispublished" CHAR(1) DEFAULT 'Y' NOT NULL,
    "textdetails" TEXT,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "b_topictype_id" INTEGER NOT NULL,
    "b_topiccategory_id" INTEGER NOT NULL,
    "decisiondate" TIMESTAMP NOT NULL,
    PRIMARY KEY ("b_topic_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPublished in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_TOPICCATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_topiccategory"
(
    "b_topiccategory_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "b_topictype_id" INTEGER NOT NULL,
    PRIMARY KEY ("b_topiccategory_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- B_TOPICTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "b_topictype"
(
    "b_topictype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "m_pricelist_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "auctiontype" CHAR(1) NOT NULL,
    "m_productmember_id" INTEGER NOT NULL,
    PRIMARY KEY ("b_topictype_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_ACCESSCONTAINER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_accesscontainer"
(
    "cm_accessprofile_id" INTEGER NOT NULL,
    "cm_container_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("cm_accessprofile_id", "cm_container_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_ACCESSLISTBPGROUP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_accesslistbpgroup"
(
    "cm_accessprofile_id" INTEGER NOT NULL,
    "c_bp_group_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("cm_accessprofile_id", "c_bp_group_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_ACCESSLISTROLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_accesslistrole"
(
    "cm_accessprofile_id" INTEGER NOT NULL,
    "ad_role_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("cm_accessprofile_id", "ad_role_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_ACCESSMEDIA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_accessmedia"
(
    "cm_media_id" INTEGER NOT NULL,
    "cm_accessprofile_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("cm_media_id", "cm_accessprofile_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_ACCESSNEWSCHANNEL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_accessnewschannel"
(
    "cm_accessprofile_id" INTEGER NOT NULL,
    "cm_newschannel_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("cm_accessprofile_id", "cm_newschannel_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_ACCESSPROFILE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_accessprofile"
(
    "cm_accessprofile_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isexclude" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("cm_accessprofile_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsExclude in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_ACCESSSTAGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_accessstage"
(
    "cm_accessprofile_id" INTEGER NOT NULL,
    "cm_cstage_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("cm_accessprofile_id", "cm_cstage_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_AD 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_ad"
(
    "cm_ad_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "cm_ad_cat_id" INTEGER NOT NULL,
    "cm_media_id" INTEGER NOT NULL,
    "targeturl" VARCHAR(120),
    "target_frame" VARCHAR(20) NOT NULL,
    "actualclick" INTEGER DEFAULT 0 NOT NULL,
    "maxclick" INTEGER DEFAULT 0 NOT NULL,
    "actualimpression" INTEGER DEFAULT 0 NOT NULL,
    "maximpression" INTEGER DEFAULT 0 NOT NULL,
    "startimpression" INTEGER DEFAULT 0 NOT NULL,
    "startdate" TIMESTAMP NOT NULL,
    "enddate" TIMESTAMP,
    "contenthtml" VARCHAR(2000),
    "isadflag" CHAR(1) DEFAULT 'Y' NOT NULL,
    "islogged" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("cm_ad_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsAdFlag in ('Y','N')),
    CHECK (IsLogged in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_AD_CAT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_ad_cat"
(
    "cm_ad_cat_id" INTEGER NOT NULL,
    "cm_webproject_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("cm_ad_cat_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_BROADCASTSERVER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_broadcastserver"
(
    "cm_broadcastserver_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ip_address" VARCHAR(20) NOT NULL,
    "lastsynchronized" TIMESTAMP,
    "cm_webproject_id" INTEGER,
    PRIMARY KEY ("cm_broadcastserver_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CHAT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_chat"
(
    "cm_chat_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "confidentialtype" CHAR(1) NOT NULL,
    "cm_chattype_id" INTEGER,
    "moderationtype" CHAR(1),
    PRIMARY KEY ("cm_chat_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CHATENTRY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_chatentry"
(
    "cm_chatentry_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "cm_chat_id" INTEGER NOT NULL,
    "confidentialtype" CHAR(1) NOT NULL,
    "characterdata" TEXT,
    "cm_chatentryparent_id" INTEGER,
    "cm_chatentrygrandparent_id" INTEGER,
    "chatentrytype" CHAR(1) NOT NULL,
    "moderatorstatus" CHAR(1),
    "subject" VARCHAR(255),
    "ad_user_id" INTEGER,
    PRIMARY KEY ("cm_chatentry_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CHATTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_chattype"
(
    "cm_chattype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_table_id" INTEGER NOT NULL,
    "moderationtype" CHAR(1),
    PRIMARY KEY ("cm_chattype_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "cm_chattype_table" ON "cm_chattype" ("ad_client_id", "ad_table_id");

-- ----------------------------------------------------------------------- 
-- CM_CHATTYPEUPDATE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_chattypeupdate"
(
    "cm_chattype_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("cm_chattype_id", "ad_user_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSelfService in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CHATUPDATE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_chatupdate"
(
    "cm_chat_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("cm_chat_id", "ad_user_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSelfService in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CONTAINER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_container"
(
    "cm_container_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(2000),
    "help" VARCHAR(2000),
    "cm_webproject_id" INTEGER NOT NULL,
    "cm_template_id" INTEGER,
    "title" VARCHAR(60),
    "notice" VARCHAR(2000),
    "containertype" CHAR(1),
    "containerlinkurl" VARCHAR(60),
    "relativeurl" VARCHAR(120),
    "priority" INTEGER DEFAULT 0,
    "isindexed" CHAR(1) DEFAULT 'Y' NOT NULL,
    "issecure" CHAR(1) DEFAULT 'Y' NOT NULL,
    "meta_robotstag" VARCHAR(2000),
    "meta_author" VARCHAR(2000),
    "meta_copyright" VARCHAR(2000),
    "meta_content" VARCHAR(2000),
    "meta_description" VARCHAR(2000),
    "meta_keywords" VARCHAR(2000),
    "meta_publisher" VARCHAR(2000),
    "structurexml" VARCHAR(2000),
    "containerxml" VARCHAR(2000),
    "cm_containerlink_id" INTEGER,
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "meta_language" CHAR(2),
    "isvalid" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("cm_container_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsIndexed in ('Y','N')),
    CHECK (IsSecure in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CONTAINERTTABLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_containerttable"
(
    "cm_containerttable_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "cm_container_id" INTEGER NOT NULL,
    "cm_templatetable_id" INTEGER NOT NULL,
    "record_id" INTEGER,
    "whereclause" VARCHAR(2000),
    "otherclause" VARCHAR(2000),
    PRIMARY KEY ("cm_containerttable_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CONTAINER_ELEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_container_element"
(
    "cm_container_element_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "cm_container_id" INTEGER NOT NULL,
    "contenthtml" TEXT,
    "isvalid" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("cm_container_element_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CONTAINER_ELEMENT_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_container_element_trl"
(
    "cm_container_element_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "contenthtml" TEXT NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("cm_container_element_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CONTAINER_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_container_trl"
(
    "cm_container_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "title" VARCHAR(60),
    "meta_description" VARCHAR(2000),
    "meta_keywords" VARCHAR(2000),
    "structurexml" VARCHAR(2000),
    "containerxml" VARCHAR(2000),
    PRIMARY KEY ("cm_container_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CONTAINER_URL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_container_url"
(
    "cm_container_url_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "cm_container_id" INTEGER NOT NULL,
    "checked" TIMESTAMP NOT NULL,
    "status" CHAR(2) NOT NULL,
    "last_result" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("cm_container_url_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CSTAGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_cstage"
(
    "cm_cstage_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(2000),
    "help" VARCHAR(2000),
    "cm_webproject_id" INTEGER NOT NULL,
    "cm_template_id" INTEGER,
    "title" VARCHAR(60),
    "notice" VARCHAR(2000),
    "containertype" CHAR(1),
    "containerlinkurl" VARCHAR(60),
    "relativeurl" VARCHAR(120),
    "priority" INTEGER DEFAULT 0,
    "isindexed" CHAR(1) DEFAULT 'Y' NOT NULL,
    "issecure" CHAR(1) DEFAULT 'Y' NOT NULL,
    "meta_robotstag" VARCHAR(2000),
    "meta_author" VARCHAR(2000),
    "meta_copyright" VARCHAR(2000),
    "meta_content" VARCHAR(2000),
    "meta_description" VARCHAR(2000),
    "meta_keywords" VARCHAR(2000),
    "meta_publisher" VARCHAR(2000),
    "structurexml" VARCHAR(2000),
    "containerxml" VARCHAR(2000),
    "cm_cstagelink_id" INTEGER,
    "ismodified" CHAR(1) NOT NULL,
    "processing" CHAR(1),
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "meta_language" CHAR(2),
    "isvalid" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("cm_cstage_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsIndexed in ('Y','N')),
    CHECK (IsSecure in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CSTAGETTABLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_cstagettable"
(
    "cm_cstagettable_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "cm_cstage_id" INTEGER NOT NULL,
    "cm_templatetable_id" INTEGER NOT NULL,
    "record_id" INTEGER,
    "whereclause" VARCHAR(2000),
    "otherclause" VARCHAR(2000),
    PRIMARY KEY ("cm_cstagettable_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CSTAGE_ELEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_cstage_element"
(
    "cm_cstage_element_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "cm_cstage_id" INTEGER NOT NULL,
    "contenthtml" TEXT,
    "isvalid" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("cm_cstage_element_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "cm_cstage_element_name" ON "cm_cstage_element" ("cm_cstage_id", "name");

-- ----------------------------------------------------------------------- 
-- CM_CSTAGE_ELEMENT_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_cstage_element_trl"
(
    "cm_cstage_element_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "contenthtml" TEXT NOT NULL,
    PRIMARY KEY ("cm_cstage_element_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_CSTAGE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_cstage_trl"
(
    "cm_cstage_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "title" VARCHAR(60),
    "meta_description" VARCHAR(2000),
    "meta_keywords" VARCHAR(2000),
    "structurexml" VARCHAR(2000),
    "containerxml" VARCHAR(2000),
    PRIMARY KEY ("cm_cstage_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_MEDIA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_media"
(
    "cm_media_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "mediatype" CHAR(3),
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "cm_webproject_id" INTEGER NOT NULL,
    "ad_image_id" INTEGER,
    "contenttext" TEXT,
    "directdeploy" CHAR(1),
    PRIMARY KEY ("cm_media_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_MEDIADEPLOY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_mediadeploy"
(
    "cm_mediadeploy_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "cm_media_server_id" INTEGER NOT NULL,
    "cm_media_id" INTEGER NOT NULL,
    "isdeployed" CHAR(1) DEFAULT 'N' NOT NULL,
    "lastsynchronized" TIMESTAMP,
    "description" VARCHAR(255),
    PRIMARY KEY ("cm_mediadeploy_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDeployed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_MEDIA_SERVER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_media_server"
(
    "cm_media_server_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "cm_webproject_id" INTEGER NOT NULL,
    "ispassive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "url" VARCHAR(120),
    "ip_address" VARCHAR(20),
    "username" VARCHAR(40),
    "password" VARCHAR(40),
    "folder" VARCHAR(60),
    PRIMARY KEY ("cm_media_server_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPassive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_NEWSCHANNEL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_newschannel"
(
    "cm_newschannel_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(2000) NOT NULL,
    "help" VARCHAR(2000),
    "ad_language" VARCHAR(40),
    "cm_webproject_id" INTEGER NOT NULL,
    "link" VARCHAR(255),
    PRIMARY KEY ("cm_newschannel_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_NEWSITEM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_newsitem"
(
    "cm_newsitem_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "cm_newschannel_id" INTEGER NOT NULL,
    "title" VARCHAR(255),
    "description" VARCHAR(255),
    "author" VARCHAR(255),
    "linkurl" VARCHAR(120),
    "pubdate" TIMESTAMP,
    "contenthtml" TEXT,
    PRIMARY KEY ("cm_newsitem_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_TEMPLATE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_template"
(
    "cm_template_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "cm_webproject_id" INTEGER,
    "value" VARCHAR(40) NOT NULL,
    "isinclude" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isusead" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isnews" CHAR(1) DEFAULT 'Y' NOT NULL,
    "elements" VARCHAR(2000),
    "templatexst" TEXT,
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "isvalid" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("cm_template_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsInclude in ('Y','N')),
    CHECK (IsUseAd in ('Y','N')),
    CHECK (IsNews in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_TEMPLATETABLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_templatetable"
(
    "cm_templatetable_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "cm_template_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "whereclause" VARCHAR(2000),
    "otherclause" VARCHAR(2000),
    PRIMARY KEY ("cm_templatetable_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_TEMPLATE_AD_CAT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_template_ad_cat"
(
    "cm_ad_cat_id" INTEGER NOT NULL,
    "cm_template_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("cm_ad_cat_id", "cm_template_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_WEBACCESSLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_webaccesslog"
(
    "cm_webaccesslog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "logtype" CHAR(1) NOT NULL,
    "cm_webproject_id" INTEGER,
    "ip_address" VARCHAR(20) NOT NULL,
    "cm_broadcastserver_id" INTEGER,
    "requesttype" VARCHAR(4) NOT NULL,
    "pageurl" VARCHAR(120),
    "referrer" VARCHAR(120),
    "remote_host" VARCHAR(120),
    "remote_addr" VARCHAR(60),
    "useragent" VARCHAR(255),
    "acceptlanguage" VARCHAR(60),
    "websession" VARCHAR(40),
    "hyphen" VARCHAR(20),
    "protocol" VARCHAR(20) NOT NULL,
    "statuscode" INTEGER DEFAULT 0,
    "filesize" NUMERIC,
    "ad_user_id" INTEGER,
    "cm_media_id" INTEGER,
    PRIMARY KEY ("cm_webaccesslog_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_WEBPROJECT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_webproject"
(
    "cm_webproject_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "meta_copyright" VARCHAR(2000) NOT NULL,
    "meta_publisher" VARCHAR(2000) NOT NULL,
    "meta_robotstag" VARCHAR(2000) NOT NULL,
    "meta_author" VARCHAR(2000) NOT NULL,
    "meta_content" VARCHAR(2000) NOT NULL,
    "ad_treecmc_id" INTEGER NOT NULL,
    "ad_treecms_id" INTEGER NOT NULL,
    "ad_treecmm_id" INTEGER NOT NULL,
    "ad_treecmt_id" INTEGER NOT NULL,
    PRIMARY KEY ("cm_webproject_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_WEBPROJECT_DOMAIN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_webproject_domain"
(
    "cm_webproject_domain_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "cm_webproject_id" INTEGER NOT NULL,
    "cm_container_id" INTEGER,
    "fqdn" VARCHAR(120) NOT NULL,
    PRIMARY KEY ("cm_webproject_domain_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- CM_WIKITOKEN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "cm_wikitoken"
(
    "cm_wikitoken_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "tokentype" CHAR(1) NOT NULL,
    "selectclause" VARCHAR(2000),
    "ad_table_id" INTEGER,
    "whereclause" VARCHAR(2000),
    "macro" VARCHAR(2000),
    PRIMARY KEY ("cm_wikitoken_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_ACCTPROCESSOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_acctprocessor"
(
    "c_acctprocessor_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "frequencytype" CHAR(1) NOT NULL,
    "frequency" INTEGER NOT NULL,
    "datelastrun" TIMESTAMP,
    "datenextrun" TIMESTAMP,
    "supervisor_id" INTEGER NOT NULL,
    "keeplogdays" INTEGER NOT NULL,
    "processing" CHAR(1),
    "c_acctschema_id" INTEGER,
    "ad_table_id" INTEGER,
    PRIMARY KEY ("c_acctprocessor_id")
);

-- ----------------------------------------------------------------------- 
-- C_ACCTPROCESSORLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_acctprocessorlog"
(
    "c_acctprocessor_id" INTEGER NOT NULL,
    "c_acctprocessorlog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "iserror" CHAR(1) DEFAULT 'N' NOT NULL,
    "summary" VARCHAR(2000),
    "reference" VARCHAR(60),
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    "binarydata" BYTEA,
    PRIMARY KEY ("c_acctprocessor_id", "c_acctprocessorlog_id")
);

-- ----------------------------------------------------------------------- 
-- C_ACCTSCHEMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_acctschema"
(
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "gaap" CHAR(2) NOT NULL,
    "isaccrual" CHAR(1) DEFAULT 'Y' NOT NULL,
    "costingmethod" CHAR(1) NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "autoperiodcontrol" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_period_id" INTEGER,
    "period_openhistory" INTEGER,
    "period_openfuture" INTEGER,
    "separator" CHAR(1) NOT NULL,
    "hasalias" CHAR(1) DEFAULT 'Y' NOT NULL,
    "hascombination" CHAR(1) DEFAULT 'Y' NOT NULL,
    "istradediscountposted" CHAR(1) DEFAULT 'N' NOT NULL,
    "isdiscountcorrectstax" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_costtype_id" INTEGER,
    "costinglevel" CHAR(1) DEFAULT 'C' NOT NULL,
    "isadjustcogs" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_orgonly_id" INTEGER,
    "ispostservices" CHAR(1) DEFAULT 'N' NOT NULL,
    "isexplicitcostadjustment" CHAR(1) DEFAULT 'N' NOT NULL,
    "commitmenttype" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "taxcorrectiontype" CHAR(1),
    PRIMARY KEY ("c_acctschema_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsAccrual in ('Y','N')),
    CHECK (AutoPeriodControl in ('Y','N')),
    CHECK (HasAlias in ('Y','N')),
    CHECK (HasCombination in ('Y','N')),
    CHECK (IsTradeDiscountPosted in ('Y','N')),
    CHECK (IsDiscountCorrectsTax in ('Y','N'))
);

CREATE UNIQUE INDEX "c_acctschema_name" ON "c_acctschema" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_ACCTSCHEMA_DEFAULT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_acctschema_default"
(
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "w_inventory_acct" INTEGER NOT NULL,
    "w_invactualadjust_acct" INTEGER NOT NULL,
    "w_differences_acct" INTEGER NOT NULL,
    "w_revaluation_acct" INTEGER NOT NULL,
    "p_revenue_acct" INTEGER NOT NULL,
    "p_expense_acct" INTEGER NOT NULL,
    "p_asset_acct" INTEGER NOT NULL,
    "p_purchasepricevariance_acct" INTEGER NOT NULL,
    "p_invoicepricevariance_acct" INTEGER NOT NULL,
    "p_tradediscountrec_acct" INTEGER NOT NULL,
    "p_tradediscountgrant_acct" INTEGER NOT NULL,
    "p_cogs_acct" INTEGER NOT NULL,
    "c_receivable_acct" INTEGER NOT NULL,
    "c_prepayment_acct" INTEGER NOT NULL,
    "v_liability_acct" INTEGER NOT NULL,
    "v_liability_services_acct" INTEGER NOT NULL,
    "v_prepayment_acct" INTEGER NOT NULL,
    "paydiscount_exp_acct" INTEGER NOT NULL,
    "writeoff_acct" INTEGER NOT NULL,
    "paydiscount_rev_acct" INTEGER NOT NULL,
    "unrealizedgain_acct" INTEGER NOT NULL,
    "unrealizedloss_acct" INTEGER NOT NULL,
    "realizedgain_acct" INTEGER NOT NULL,
    "realizedloss_acct" INTEGER NOT NULL,
    "withholding_acct" INTEGER NOT NULL,
    "e_prepayment_acct" INTEGER NOT NULL,
    "e_expense_acct" INTEGER NOT NULL,
    "pj_asset_acct" INTEGER NOT NULL,
    "pj_wip_acct" INTEGER NOT NULL,
    "t_expense_acct" INTEGER NOT NULL,
    "t_liability_acct" INTEGER NOT NULL,
    "t_receivables_acct" INTEGER NOT NULL,
    "t_due_acct" INTEGER NOT NULL,
    "t_credit_acct" INTEGER NOT NULL,
    "b_intransit_acct" INTEGER NOT NULL,
    "b_asset_acct" INTEGER NOT NULL,
    "b_expense_acct" INTEGER NOT NULL,
    "b_interestrev_acct" INTEGER NOT NULL,
    "b_interestexp_acct" INTEGER NOT NULL,
    "b_unidentified_acct" INTEGER NOT NULL,
    "b_unallocatedcash_acct" INTEGER NOT NULL,
    "b_paymentselect_acct" INTEGER NOT NULL,
    "b_settlementgain_acct" INTEGER NOT NULL,
    "b_settlementloss_acct" INTEGER NOT NULL,
    "b_revaluationgain_acct" INTEGER NOT NULL,
    "b_revaluationloss_acct" INTEGER NOT NULL,
    "ch_expense_acct" INTEGER NOT NULL,
    "ch_revenue_acct" INTEGER NOT NULL,
    "unearnedrevenue_acct" INTEGER NOT NULL,
    "notinvoicedreceivables_acct" INTEGER NOT NULL,
    "notinvoicedrevenue_acct" INTEGER NOT NULL,
    "notinvoicedreceipts_acct" INTEGER NOT NULL,
    "cb_asset_acct" INTEGER NOT NULL,
    "cb_cashtransfer_acct" INTEGER NOT NULL,
    "cb_differences_acct" INTEGER NOT NULL,
    "cb_expense_acct" INTEGER NOT NULL,
    "cb_receipt_acct" INTEGER NOT NULL,
    "processing" CHAR(1),
    "c_receivable_services_acct" INTEGER NOT NULL,
    "p_inventoryclearing_acct" INTEGER NOT NULL,
    "p_costadjustment_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_ACCTSCHEMA_ELEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_acctschema_element"
(
    "c_acctschema_element_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "elementtype" CHAR(2) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "seqno" INTEGER NOT NULL,
    "c_element_id" INTEGER,
    "ad_client_id" INTEGER NOT NULL,
    "ismandatory" CHAR(1) DEFAULT 'N' NOT NULL,
    "isbalanced" CHAR(1) DEFAULT 'N' NOT NULL,
    "org_id" INTEGER,
    "c_elementvalue_id" INTEGER,
    "m_product_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "c_location_id" INTEGER,
    "c_salesregion_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "ad_column_id" INTEGER,
    PRIMARY KEY ("c_acctschema_element_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsMandatory in ('Y','N')),
    CHECK (IsBalanced in ('Y','N'))
);

CREATE INDEX "c_acctschema_element_schema" ON "c_acctschema_element" ("c_acctschema_id");

-- ----------------------------------------------------------------------- 
-- C_ACCTSCHEMA_GL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_acctschema_gl"
(
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "usesuspensebalancing" CHAR(1) DEFAULT 'Y' NOT NULL,
    "suspensebalancing_acct" INTEGER,
    "usesuspenseerror" CHAR(1) DEFAULT 'Y' NOT NULL,
    "suspenseerror_acct" INTEGER,
    "usecurrencybalancing" CHAR(1) DEFAULT 'Y' NOT NULL,
    "currencybalancing_acct" INTEGER,
    "retainedearning_acct" INTEGER NOT NULL,
    "incomesummary_acct" INTEGER NOT NULL,
    "intercompanydueto_acct" INTEGER NOT NULL,
    "intercompanyduefrom_acct" INTEGER NOT NULL,
    "ppvoffset_acct" INTEGER NOT NULL,
    "commitmentoffset_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_acctschema_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (UseSuspenseBalancing in ('Y','N')),
    CHECK (UseSuspenseError in ('Y','N')),
    CHECK (UseCurrencyBalancing in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_ACTIVITY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_activity"
(
    "c_activity_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "help" VARCHAR(2000),
    PRIMARY KEY ("c_activity_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_activity_value" ON "c_activity" ("ad_client_id", "value");

-- ----------------------------------------------------------------------- 
-- C_ALLOCATIONHDR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_allocationhdr"
(
    "c_allocationhdr_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "description" VARCHAR(255),
    "datetrx" TIMESTAMP NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "approvalamt" NUMERIC DEFAULT 0 NOT NULL,
    "ismanual" CHAR(1) DEFAULT 'N' NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_allocationhdr_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsManual in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_ALLOCATIONLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_allocationline"
(
    "c_allocationline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "allocationno" INTEGER,
    "datetrx" TIMESTAMP,
    "ismanual" CHAR(1) DEFAULT 'N',
    "c_invoice_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "c_order_id" INTEGER,
    "c_payment_id" INTEGER,
    "c_cashline_id" INTEGER,
    "amount" NUMERIC DEFAULT 0 NOT NULL,
    "discountamt" NUMERIC DEFAULT 0 NOT NULL,
    "writeoffamt" NUMERIC DEFAULT 0 NOT NULL,
    "posted" CHAR(1) DEFAULT 'N',
    "overunderamt" NUMERIC DEFAULT 0,
    "c_allocationhdr_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_allocationline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsManual in ('Y','N'))
);

CREATE INDEX "c_allocationline_payment" ON "c_allocationline" ("c_payment_id");

CREATE INDEX "c_allocationline_invoice" ON "c_allocationline" ("c_invoice_id");

-- ----------------------------------------------------------------------- 
-- C_BANK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bank"
(
    "c_bank_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "routingno" VARCHAR(20) NOT NULL,
    "c_location_id" INTEGER,
    "swiftcode" VARCHAR(20),
    "isownbank" CHAR(1) DEFAULT 'N' NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("c_bank_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsOwnBank in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BANKACCOUNT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bankaccount"
(
    "c_bankaccount_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_bank_id" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "bankaccounttype" CHAR(1) NOT NULL,
    "accountno" VARCHAR(20) NOT NULL,
    "currentbalance" NUMERIC DEFAULT 0 NOT NULL,
    "creditlimit" NUMERIC DEFAULT 0 NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "iban" VARCHAR(40),
    "description" VARCHAR(255),
    "bban" VARCHAR(40),
    PRIMARY KEY ("c_bankaccount_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "c_bankacct_bank" ON "c_bankaccount" ("c_bank_id");

-- ----------------------------------------------------------------------- 
-- C_BANKACCOUNTDOC 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bankaccountdoc"
(
    "c_bankaccountdoc_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_bankaccount_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "paymentrule" CHAR(1) NOT NULL,
    "currentnext" INTEGER NOT NULL,
    "check_printformat_id" INTEGER,
    PRIMARY KEY ("c_bankaccountdoc_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BANKACCOUNT_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bankaccount_acct"
(
    "c_bankaccount_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "b_intransit_acct" INTEGER NOT NULL,
    "b_asset_acct" INTEGER NOT NULL,
    "b_expense_acct" INTEGER NOT NULL,
    "b_interestrev_acct" INTEGER NOT NULL,
    "b_interestexp_acct" INTEGER NOT NULL,
    "b_unidentified_acct" INTEGER NOT NULL,
    "b_unallocatedcash_acct" INTEGER NOT NULL,
    "b_paymentselect_acct" INTEGER NOT NULL,
    "b_settlementgain_acct" INTEGER NOT NULL,
    "b_settlementloss_acct" INTEGER NOT NULL,
    "b_revaluationgain_acct" INTEGER NOT NULL,
    "b_revaluationloss_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_bankaccount_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BANKSTATEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bankstatement"
(
    "c_bankstatement_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_bankaccount_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ismanual" CHAR(1) DEFAULT 'Y' NOT NULL,
    "statementdate" TIMESTAMP NOT NULL,
    "beginningbalance" NUMERIC DEFAULT 0,
    "endingbalance" NUMERIC DEFAULT 0 NOT NULL,
    "statementdifference" NUMERIC DEFAULT 0,
    "createfrom" CHAR(1) DEFAULT 'N',
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "eftstatementreference" VARCHAR(60),
    "eftstatementdate" TIMESTAMP,
    "matchstatement" CHAR(1),
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    PRIMARY KEY ("c_bankstatement_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsManual in ('Y','N')),
    CHECK (CreateFrom in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BANKSTATEMENTLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bankstatementline"
(
    "c_bankstatementline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_bankstatement_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "isreversal" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_payment_id" INTEGER,
    "valutadate" TIMESTAMP NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "trxamt" NUMERIC DEFAULT 0 NOT NULL,
    "stmtamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_charge_id" INTEGER,
    "chargeamt" NUMERIC DEFAULT 0 NOT NULL,
    "interestamt" NUMERIC DEFAULT 0 NOT NULL,
    "memo" VARCHAR(255),
    "referenceno" VARCHAR(40),
    "ismanual" CHAR(1) DEFAULT 'N' NOT NULL,
    "efttrxid" VARCHAR(40),
    "efttrxtype" VARCHAR(20),
    "eftmemo" VARCHAR(2000),
    "eftpayee" VARCHAR(255),
    "eftpayeeaccount" VARCHAR(40),
    "createpayment" CHAR(1),
    "statementlinedate" TIMESTAMP NOT NULL,
    "eftstatementlinedate" TIMESTAMP,
    "eftvalutadate" TIMESTAMP,
    "eftreference" VARCHAR(60),
    "eftcurrency" VARCHAR(20),
    "eftamt" NUMERIC DEFAULT 0,
    "eftcheckno" VARCHAR(20),
    "matchstatement" CHAR(1),
    "c_bpartner_id" INTEGER,
    "c_invoice_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_bankstatementline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReversal in ('Y','N'))
);

CREATE INDEX "c_bankstmtline_bankstmt" ON "c_bankstatementline" ("c_bankstatement_id");

-- ----------------------------------------------------------------------- 
-- C_BANKSTATEMENTLOADER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bankstatementloader"
(
    "c_bankstatementloader_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_bankaccount_id" INTEGER NOT NULL,
    "stmtloaderclass" VARCHAR(60),
    "financialinstitutionid" VARCHAR(20),
    "branchid" VARCHAR(20),
    "userid" VARCHAR(60),
    "password" VARCHAR(60),
    "pin" VARCHAR(20),
    "accountno" VARCHAR(20),
    "hostaddress" VARCHAR(60),
    "hostport" INTEGER,
    "proxyaddress" VARCHAR(60),
    "proxyport" INTEGER,
    "proxylogon" VARCHAR(60),
    "proxypassword" VARCHAR(60),
    "filename" VARCHAR(120),
    "datelastrun" TIMESTAMP,
    "dateformat" VARCHAR(20),
    PRIMARY KEY ("c_bankstatementloader_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BANKSTATEMENTMATCHER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bankstatementmatcher"
(
    "c_bankstatementmatcher_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "classname" VARCHAR(60) NOT NULL,
    "seqno" INTEGER NOT NULL,
    PRIMARY KEY ("c_bankstatementmatcher_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BPARTNER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bpartner"
(
    "c_bpartner_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "name2" VARCHAR(60),
    "description" VARCHAR(255),
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bp_group_id" INTEGER NOT NULL,
    "isonetime" CHAR(1) DEFAULT 'N' NOT NULL,
    "isprospect" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isvendor" CHAR(1) DEFAULT 'N' NOT NULL,
    "iscustomer" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isemployee" CHAR(1) DEFAULT 'N' NOT NULL,
    "issalesrep" CHAR(1) DEFAULT 'N' NOT NULL,
    "referenceno" VARCHAR(40),
    "duns" CHAR(11),
    "url" VARCHAR(120),
    "ad_language" VARCHAR(6),
    "taxid" VARCHAR(20),
    "istaxexempt" CHAR(1) DEFAULT 'N',
    "c_invoiceschedule_id" INTEGER,
    "rating" CHAR(1),
    "salesvolume" INTEGER,
    "numberemployees" INTEGER,
    "naics" CHAR(6),
    "firstsale" TIMESTAMP,
    "acqusitioncost" NUMERIC DEFAULT 0,
    "potentiallifetimevalue" NUMERIC DEFAULT 0,
    "actuallifetimevalue" NUMERIC DEFAULT 0,
    "shareofcustomer" INTEGER,
    "paymentrule" CHAR(1),
    "so_creditlimit" NUMERIC DEFAULT 0,
    "so_creditused" NUMERIC DEFAULT 0,
    "c_paymentterm_id" INTEGER,
    "m_pricelist_id" INTEGER,
    "m_discountschema_id" INTEGER,
    "c_dunning_id" INTEGER,
    "isdiscountprinted" CHAR(1) DEFAULT 'Y',
    "so_description" VARCHAR(255),
    "poreference" VARCHAR(20),
    "paymentrulepo" CHAR(1),
    "po_pricelist_id" INTEGER,
    "po_discountschema_id" INTEGER,
    "po_paymentterm_id" INTEGER,
    "documentcopies" INTEGER,
    "c_greeting_id" INTEGER,
    "invoicerule" CHAR(1),
    "deliveryrule" CHAR(1),
    "freightcostrule" CHAR(1),
    "deliveryviarule" CHAR(1),
    "salesrep_id" INTEGER,
    "sendemail" CHAR(1) DEFAULT 'N' NOT NULL,
    "bpartner_parent_id" INTEGER,
    "invoice_printformat_id" INTEGER,
    "socreditstatus" CHAR(1) DEFAULT 'O',
    "shelflifeminpct" INTEGER,
    "ad_orgbp_id" INTEGER,
    "flatdiscount" NUMERIC,
    "totalopenbalance" NUMERIC,
    PRIMARY KEY ("c_bpartner_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSummary in ('Y','N')),
    CHECK (IsOneTime in ('Y','N')),
    CHECK (IsProspect in ('Y','N')),
    CHECK (IsVendor in ('Y','N')),
    CHECK (IsCustomer in ('Y','N')),
    CHECK (IsEmployee in ('Y','N')),
    CHECK (IsSalesRep in ('Y','N')),
    CHECK (IsTaxExempt in ('Y','N')),
    CHECK (IsDiscountPrinted in ('Y','N'))
);

CREATE UNIQUE INDEX "c_bpartner_value" ON "c_bpartner" ("ad_client_id", "value");

CREATE INDEX "c_bpartner_name" ON "c_bpartner" ("name");

CREATE INDEX "c_bpartner_bporg" ON "c_bpartner" ("ad_orgbp_id");

-- ----------------------------------------------------------------------- 
-- C_BPARTNER_LOCATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bpartner_location"
(
    "c_bpartner_location_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "isbillto" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isshipto" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ispayfrom" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isremitto" CHAR(1) DEFAULT 'Y' NOT NULL,
    "phone" VARCHAR(40),
    "phone2" VARCHAR(40),
    "fax" VARCHAR(40),
    "isdn" VARCHAR(40),
    "c_salesregion_id" INTEGER,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_location_id" INTEGER,
    PRIMARY KEY ("c_bpartner_location_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsBillTo in ('Y','N')),
    CHECK (IsShipTo in ('Y','N')),
    CHECK (IsPayFrom in ('Y','N')),
    CHECK (IsRemitTo in ('Y','N'))
);

CREATE INDEX "c_bplocation_bpartner" ON "c_bpartner_location" ("c_bpartner_id");

CREATE INDEX "c_bplocation_updated" ON "c_bpartner_location" ("updated");

-- ----------------------------------------------------------------------- 
-- C_BPARTNER_PRODUCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bpartner_product"
(
    "c_bpartner_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "shelflifeminpct" INTEGER NOT NULL,
    "shelflifemindays" INTEGER NOT NULL,
    "qualityrating" NUMERIC,
    "vendorproductno" VARCHAR(30),
    "vendorcategory" VARCHAR(30),
    "manufacturer" VARCHAR(30),
    PRIMARY KEY ("c_bpartner_id", "m_product_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BP_BANKACCOUNT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_bankaccount"
(
    "c_bp_bankaccount_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bank_id" INTEGER,
    "isach" CHAR(1) DEFAULT 'N' NOT NULL,
    "bankaccounttype" CHAR(1),
    "routingno" VARCHAR(20),
    "accountno" VARCHAR(20),
    "creditcardtype" CHAR(1),
    "creditcardnumber" VARCHAR(20),
    "creditcardvv" VARCHAR(4),
    "creditcardexpmm" INTEGER,
    "creditcardexpyy" INTEGER,
    "a_name" VARCHAR(60),
    "a_street" VARCHAR(60),
    "a_city" VARCHAR(60),
    "a_state" VARCHAR(40),
    "a_zip" VARCHAR(20),
    "a_ident_dl" VARCHAR(20),
    "a_email" VARCHAR(60),
    "a_ident_ssn" VARCHAR(20),
    "r_avsaddr" CHAR(1),
    "r_avszip" CHAR(1),
    "a_country" VARCHAR(40),
    "ad_user_id" INTEGER,
    "bpbankacctuse" CHAR(1),
    PRIMARY KEY ("c_bp_bankaccount_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsACH in ('Y','N'))
);

CREATE INDEX "c_bpbankacct_bpartner" ON "c_bp_bankaccount" ("c_bpartner_id");

-- ----------------------------------------------------------------------- 
-- C_BP_CUSTOMER_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_customer_acct"
(
    "c_bpartner_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_receivable_acct" INTEGER,
    "c_prepayment_acct" INTEGER,
    "c_receivable_services_acct" INTEGER,
    PRIMARY KEY ("c_bpartner_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BP_EDI 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_edi"
(
    "c_bp_edi_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_bpartner_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "editype" CHAR(1) NOT NULL,
    "isaudited" CHAR(1) DEFAULT 'N' NOT NULL,
    "customerno" VARCHAR(20) NOT NULL,
    "ad_sequence_id" INTEGER NOT NULL,
    "email_to" VARCHAR(60),
    "email_from" VARCHAR(60),
    "email_from_uid" VARCHAR(20),
    "email_from_pwd" VARCHAR(20),
    "email_error_to" VARCHAR(60) NOT NULL,
    "isinfosent" CHAR(1) DEFAULT 'N' NOT NULL,
    "email_info_to" VARCHAR(60) NOT NULL,
    "sendinquiry" CHAR(1) DEFAULT 'Y' NOT NULL,
    "receiveinquiryreply" CHAR(1) DEFAULT 'Y' NOT NULL,
    "sendorder" CHAR(1) DEFAULT 'Y' NOT NULL,
    "receiveorderreply" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("c_bp_edi_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsAudited in ('Y','N')),
    CHECK (IsInfoSent in ('Y','N')),
    CHECK (SendInquiry in ('Y','N')),
    CHECK (ReceiveInquiryReply in ('Y','N')),
    CHECK (SendOrder in ('Y','N')),
    CHECK (ReceiveOrderReply in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BP_EMPLOYEE_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_employee_acct"
(
    "c_bpartner_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "e_expense_acct" INTEGER,
    "e_prepayment_acct" INTEGER,
    PRIMARY KEY ("c_bpartner_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BP_GROUP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_group"
(
    "c_bp_group_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_printcolor_id" INTEGER,
    "isconfidentialinfo" CHAR(1) DEFAULT 'N' NOT NULL,
    "prioritybase" CHAR(1),
    "m_pricelist_id" INTEGER,
    "po_pricelist_id" INTEGER,
    "m_discountschema_id" INTEGER,
    "po_discountschema_id" INTEGER,
    "creditwatchpercent" NUMERIC,
    "pricematchtolerance" NUMERIC,
    "c_dunning_id" INTEGER,
    PRIMARY KEY ("c_bp_group_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_bp_group_value" ON "c_bp_group" ("ad_client_id", "value");

-- ----------------------------------------------------------------------- 
-- C_BP_GROUP_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_group_acct"
(
    "c_acctschema_id" INTEGER NOT NULL,
    "c_bp_group_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_receivable_acct" INTEGER NOT NULL,
    "c_prepayment_acct" INTEGER NOT NULL,
    "v_liability_acct" INTEGER NOT NULL,
    "v_liability_services_acct" INTEGER NOT NULL,
    "v_prepayment_acct" INTEGER NOT NULL,
    "paydiscount_exp_acct" INTEGER NOT NULL,
    "paydiscount_rev_acct" INTEGER NOT NULL,
    "writeoff_acct" INTEGER NOT NULL,
    "notinvoicedreceipts_acct" INTEGER NOT NULL,
    "unearnedrevenue_acct" INTEGER NOT NULL,
    "notinvoicedrevenue_acct" INTEGER NOT NULL,
    "notinvoicedreceivables_acct" INTEGER NOT NULL,
    "processing" CHAR(1),
    "c_receivable_services_acct" INTEGER,
    PRIMARY KEY ("c_acctschema_id", "c_bp_group_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BP_RELATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_relation"
(
    "c_bp_relation_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER,
    "isshipto" CHAR(1) DEFAULT 'N' NOT NULL,
    "isbillto" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispayfrom" CHAR(1) DEFAULT 'N' NOT NULL,
    "isremitto" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartnerrelation_id" INTEGER NOT NULL,
    "c_bpartnerrelation_location_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_bp_relation_id")
);

-- ----------------------------------------------------------------------- 
-- C_BP_VENDOR_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_vendor_acct"
(
    "c_acctschema_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "v_liability_acct" INTEGER,
    "v_liability_services_acct" INTEGER,
    "v_prepayment_acct" INTEGER,
    PRIMARY KEY ("c_acctschema_id", "c_bpartner_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_BP_WITHHOLDING 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_bp_withholding"
(
    "c_bpartner_id" INTEGER NOT NULL,
    "c_withholding_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ismandatorywithholding" CHAR(1) DEFAULT 'N' NOT NULL,
    "istemporaryexempt" CHAR(1) DEFAULT 'N' NOT NULL,
    "exemptreason" VARCHAR(20),
    PRIMARY KEY ("c_bpartner_id", "c_withholding_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsMandatoryWithholding in ('Y','N')),
    CHECK (IsTemporaryExempt in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CALENDAR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_calendar"
(
    "c_calendar_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("c_calendar_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_calendar_name" ON "c_calendar" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_CAMPAIGN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_campaign"
(
    "c_campaign_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_channel_id" INTEGER,
    "startdate" TIMESTAMP,
    "enddate" TIMESTAMP,
    "costs" NUMERIC DEFAULT 0 NOT NULL,
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_campaign_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_campaign_value" ON "c_campaign" ("ad_client_id", "value");

-- ----------------------------------------------------------------------- 
-- C_CASH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_cash"
(
    "c_cash_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_cashbook_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "statementdate" TIMESTAMP NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "beginningbalance" NUMERIC DEFAULT 0 NOT NULL,
    "endingbalance" NUMERIC DEFAULT 0 NOT NULL,
    "statementdifference" NUMERIC DEFAULT 0,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_orgtrx_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    PRIMARY KEY ("c_cash_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CASHBOOK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_cashbook"
(
    "c_cashbook_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_cashbook_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CASHBOOK_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_cashbook_acct"
(
    "c_cashbook_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "cb_asset_acct" INTEGER NOT NULL,
    "cb_cashtransfer_acct" INTEGER NOT NULL,
    "cb_differences_acct" INTEGER NOT NULL,
    "cb_expense_acct" INTEGER NOT NULL,
    "cb_receipt_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_cashbook_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CASHLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_cashline"
(
    "c_cashline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_cash_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "cashtype" CHAR(1) NOT NULL,
    "c_bankaccount_id" INTEGER,
    "c_charge_id" INTEGER,
    "c_invoice_id" INTEGER,
    "c_currency_id" INTEGER,
    "amount" NUMERIC DEFAULT 0 NOT NULL,
    "discountamt" NUMERIC DEFAULT 0,
    "writeoffamt" NUMERIC DEFAULT 0,
    "isgenerated" CHAR(1) DEFAULT 'N',
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_cashline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsGenerated in ('Y','N'))
);

CREATE INDEX "c_cashline_cash" ON "c_cashline" ("c_cash_id");

-- ----------------------------------------------------------------------- 
-- C_CHANNEL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_channel"
(
    "c_channel_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_printcolor_id" INTEGER,
    PRIMARY KEY ("c_channel_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_channel_name" ON "c_channel" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_CHARGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_charge"
(
    "c_charge_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "chargeamt" NUMERIC DEFAULT 0 NOT NULL,
    "issametax" CHAR(1) DEFAULT 'N' NOT NULL,
    "issamecurrency" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_taxcategory_id" INTEGER,
    "istaxincluded" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartner_id" INTEGER,
    PRIMARY KEY ("c_charge_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSameTax in ('Y','N')),
    CHECK (IsSameCurrency in ('Y','N'))
);

CREATE UNIQUE INDEX "c_charge_name" ON "c_charge" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_CHARGE_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_charge_acct"
(
    "c_charge_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ch_expense_acct" INTEGER NOT NULL,
    "ch_revenue_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_charge_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CITY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_city"
(
    "c_city_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "locode" VARCHAR(10),
    "coordinates" VARCHAR(15),
    "postal" VARCHAR(10),
    "areacode" VARCHAR(10),
    "c_country_id" INTEGER,
    "c_region_id" INTEGER,
    PRIMARY KEY ("c_city_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_COMMISSION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_commission"
(
    "c_commission_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_bpartner_id" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "frequencytype" CHAR(1) NOT NULL,
    "docbasistype" CHAR(1) NOT NULL,
    "listdetails" CHAR(1) DEFAULT 'N' NOT NULL,
    "datelastrun" TIMESTAMP,
    "createfrom" CHAR(1),
    "processing" CHAR(1),
    "c_charge_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_commission_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (ListDetails in ('Y','N')),
    CHECK (CreateFrom in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_COMMISSIONAMT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_commissionamt"
(
    "c_commissionamt_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_commissionrun_id" INTEGER NOT NULL,
    "c_commissionline_id" INTEGER NOT NULL,
    "convertedamt" NUMERIC DEFAULT 0 NOT NULL,
    "actualqty" NUMERIC DEFAULT 0 NOT NULL,
    "commissionamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_commissionamt_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "c_commissionamt_comline" ON "c_commissionamt" ("c_commissionline_id");

CREATE INDEX "c_commissionamt_run" ON "c_commissionamt" ("c_commissionrun_id");

-- ----------------------------------------------------------------------- 
-- C_COMMISSIONDETAIL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_commissiondetail"
(
    "c_commissiondetail_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_commissionamt_id" INTEGER NOT NULL,
    "reference" VARCHAR(60),
    "c_orderline_id" INTEGER,
    "c_invoiceline_id" INTEGER,
    "info" VARCHAR(60),
    "c_currency_id" INTEGER NOT NULL,
    "actualamt" NUMERIC DEFAULT 0 NOT NULL,
    "convertedamt" NUMERIC DEFAULT 0 NOT NULL,
    "actualqty" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_commissiondetail_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_COMMISSIONLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_commissionline"
(
    "c_commissionline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_commission_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "amtsubtract" NUMERIC NOT NULL,
    "amtmultiplier" NUMERIC NOT NULL,
    "qtysubtract" NUMERIC NOT NULL,
    "qtymultiplier" NUMERIC NOT NULL,
    "ispositiveonly" CHAR(1) DEFAULT 'Y' NOT NULL,
    "commissionorders" CHAR(1) DEFAULT 'N' NOT NULL,
    "org_id" INTEGER,
    "m_product_category_id" INTEGER,
    "m_product_id" INTEGER,
    "c_bp_group_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "c_salesregion_id" INTEGER,
    PRIMARY KEY ("c_commissionline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPositiveOnly in ('Y','N')),
    CHECK (CommissionOrders in ('Y','N'))
);

CREATE INDEX "c_commissionline_commission" ON "c_commissionline" ("c_commission_id");

-- ----------------------------------------------------------------------- 
-- C_COMMISSIONRUN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_commissionrun"
(
    "c_commissionrun_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "description" VARCHAR(255),
    "c_commission_id" INTEGER NOT NULL,
    "startdate" TIMESTAMP NOT NULL,
    "grandtotal" NUMERIC DEFAULT 0 NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_commissionrun_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CONVERSIONTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_conversiontype"
(
    "c_conversiontype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_conversiontype_id"),
    CHECK (IsDefault in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CONVERSION_RATE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_conversion_rate"
(
    "c_conversion_rate_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "c_currency_id_to" INTEGER NOT NULL,
    "validfrom" TIMESTAMP NOT NULL,
    "validto" TIMESTAMP,
    "multiplyrate" NUMERIC DEFAULT 0 NOT NULL,
    "dividerate" NUMERIC DEFAULT 0 NOT NULL,
    "c_conversiontype_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_conversion_rate_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "c_conversionrate_once" ON "c_conversion_rate" ("ad_client_id", "ad_org_id", "c_currency_id", "c_currency_id_to", "c_conversiontype_id", "validfrom");

-- ----------------------------------------------------------------------- 
-- C_COUNTRY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_country"
(
    "c_country_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "countrycode" CHAR(2) NOT NULL,
    "hasregion" CHAR(1) DEFAULT 'N' NOT NULL,
    "regionname" VARCHAR(60),
    "expressionphone" VARCHAR(20),
    "displaysequence" VARCHAR(20) NOT NULL,
    "expressionpostal" VARCHAR(20),
    "haspostal_add" CHAR(1) DEFAULT 'N' NOT NULL,
    "expressionpostal_add" VARCHAR(20),
    "ad_language" VARCHAR(6),
    "c_currency_id" INTEGER,
    "displaysequencelocal" VARCHAR(20),
    "isaddresslinesreverse" CHAR(1) DEFAULT 'N' NOT NULL,
    "isaddresslineslocalreverse" CHAR(1) DEFAULT 'N' NOT NULL,
    "expressionbankroutingno" VARCHAR(20),
    "expressionbankaccountno" VARCHAR(20),
    "mediasize" VARCHAR(40),
    PRIMARY KEY ("c_country_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (HasRegion in ('Y','N')),
    CHECK (HasPostal_Add in ('Y','N'))
);

CREATE UNIQUE INDEX "c_countrycode" ON "c_country" ("countrycode");

-- ----------------------------------------------------------------------- 
-- C_COUNTRY_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_country_trl"
(
    "c_country_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "regionname" VARCHAR(60),
    PRIMARY KEY ("c_country_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CURRENCY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_currency"
(
    "c_currency_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "iso_code" CHAR(3) NOT NULL,
    "cursymbol" VARCHAR(10),
    "description" VARCHAR(255) NOT NULL,
    "stdprecision" INTEGER NOT NULL,
    "costingprecision" INTEGER NOT NULL,
    "iseuro" CHAR(1) DEFAULT 'N' NOT NULL,
    "isemumember" CHAR(1) DEFAULT 'N' NOT NULL,
    "emuentrydate" TIMESTAMP,
    "emurate" NUMERIC DEFAULT 0,
    PRIMARY KEY ("c_currency_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsEuro in ('Y','N')),
    CHECK (IsEMUMember in ('Y','N'))
);

CREATE UNIQUE INDEX "c_currencyisocode" ON "c_currency" ("iso_code");

-- ----------------------------------------------------------------------- 
-- C_CURRENCY_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_currency_acct"
(
    "c_acctschema_id" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "unrealizedgain_acct" INTEGER NOT NULL,
    "unrealizedloss_acct" INTEGER NOT NULL,
    "realizedgain_acct" INTEGER NOT NULL,
    "realizedloss_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_acctschema_id", "c_currency_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CURRENCY_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_currency_trl"
(
    "c_currency_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "cursymbol" VARCHAR(10),
    "description" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("c_currency_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_CYCLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_cycle"
(
    "c_cycle_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_currency_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_cycle_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_cycle_name" ON "c_cycle" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_CYCLEPHASE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_cyclephase"
(
    "c_cyclestep_id" INTEGER NOT NULL,
    "c_phase_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    PRIMARY KEY ("c_cyclestep_id", "c_phase_id")
);

-- ----------------------------------------------------------------------- 
-- C_CYCLESTEP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_cyclestep"
(
    "c_cyclestep_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_cycle_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "relativeweight" NUMERIC NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    PRIMARY KEY ("c_cyclestep_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_DOCTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_doctype"
(
    "c_doctype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "printname" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "docbasetype" CHAR(3) NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "docsubtypeso" CHAR(2),
    "hasproforma" CHAR(1) DEFAULT 'N',
    "c_doctypeproforma_id" INTEGER,
    "c_doctypeshipment_id" INTEGER,
    "c_doctypeinvoice_id" INTEGER,
    "isdocnocontrolled" CHAR(1) DEFAULT 'N' NOT NULL,
    "docnosequence_id" INTEGER,
    "gl_category_id" INTEGER NOT NULL,
    "hascharges" CHAR(1) DEFAULT 'N' NOT NULL,
    "documentnote" VARCHAR(2000),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "documentcopies" INTEGER NOT NULL,
    "ad_printformat_id" INTEGER,
    "isdefaultcounterdoc" CHAR(1) DEFAULT 'N' NOT NULL,
    "isshipconfirm" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispickqaconfirm" CHAR(1) DEFAULT 'N' NOT NULL,
    "isintransit" CHAR(1) DEFAULT 'N' NOT NULL,
    "issplitwhendifference" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_doctypedifference_id" INTEGER,
    "iscreatecounter" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isindexed" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("c_doctype_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N')),
    CHECK (HasProForma in ('Y','N')),
    CHECK (IsDocNoControlled in ('Y','N')),
    CHECK (HasCharges in ('Y','N'))
);

CREATE UNIQUE INDEX "c_doctype_name" ON "c_doctype" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_DOCTYPECOUNTER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_doctypecounter"
(
    "c_doctypecounter_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_doctype_id" INTEGER NOT NULL,
    "counter_c_doctype_id" INTEGER,
    "isvalid" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "docaction" CHAR(2),
    "iscreatecounter" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("c_doctypecounter_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_DOCTYPE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_doctype_trl"
(
    "c_doctype_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "printname" VARCHAR(60) NOT NULL,
    "documentnote" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_doctype_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_DUNNING 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_dunning"
(
    "c_dunning_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "senddunningletter" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "createlevelssequentially" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_dunning_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (SendDunningLetter in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_DUNNINGLEVEL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_dunninglevel"
(
    "c_dunninglevel_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_dunning_id" INTEGER NOT NULL,
    "printname" VARCHAR(60) NOT NULL,
    "daysafterdue" INTEGER NOT NULL,
    "daysbetweendunning" INTEGER NOT NULL,
    "note" VARCHAR(2000),
    "chargeinterest" CHAR(1) DEFAULT 'Y' NOT NULL,
    "interestpercent" NUMERIC DEFAULT 0,
    "chargefee" CHAR(1) DEFAULT 'Y' NOT NULL,
    "feeamt" NUMERIC DEFAULT 0,
    "dunning_printformat_id" INTEGER,
    "name" VARCHAR(60) DEFAULT 'x' NOT NULL,
    "description" VARCHAR(255),
    "isshowalldue" CHAR(1) DEFAULT 'N' NOT NULL,
    "isshownotdue" CHAR(1) DEFAULT 'N' NOT NULL,
    "issetcreditstop" CHAR(1) DEFAULT 'N' NOT NULL,
    "issetpaymentterm" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_paymentterm_id" INTEGER,
    PRIMARY KEY ("c_dunninglevel_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (ChargeInterest in ('Y','N')),
    CHECK (ChargeFee in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_DUNNINGLEVEL_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_dunninglevel_trl"
(
    "ad_language" VARCHAR(6) NOT NULL,
    "c_dunninglevel_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "printname" VARCHAR(60) NOT NULL,
    "note" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_language", "c_dunninglevel_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_DUNNINGRUN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_dunningrun"
(
    "c_dunningrun_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "dunningdate" TIMESTAMP NOT NULL,
    "c_dunninglevel_id" INTEGER NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "sendit" CHAR(1),
    "description" VARCHAR(255),
    PRIMARY KEY ("c_dunningrun_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_DUNNINGRUNENTRY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_dunningrunentry"
(
    "c_dunningrunentry_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "c_dunningrun_id" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "salesrep_id" INTEGER NOT NULL,
    "amt" NUMERIC DEFAULT 0 NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "note" VARCHAR(2000),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_dunningrunentry_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_DUNNINGRUNLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_dunningrunline"
(
    "c_dunningrunline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_dunningrunentry_id" INTEGER NOT NULL,
    "c_invoice_id" INTEGER,
    "c_payment_id" INTEGER,
    "amt" NUMERIC DEFAULT 0 NOT NULL,
    "convertedamt" NUMERIC DEFAULT 0 NOT NULL,
    "daysdue" INTEGER DEFAULT 0 NOT NULL,
    "timesdunned" INTEGER DEFAULT 0 NOT NULL,
    "interestamt" NUMERIC DEFAULT 0 NOT NULL,
    "feeamt" NUMERIC DEFAULT 0 NOT NULL,
    "totalamt" NUMERIC DEFAULT 0 NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "isindispute" CHAR(1) DEFAULT 'N' NOT NULL,
    "openamt" NUMERIC NOT NULL,
    PRIMARY KEY ("c_dunningrunline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_ELEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_element"
(
    "c_element_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "vformat" VARCHAR(40),
    "elementtype" CHAR(1) NOT NULL,
    "isbalancing" CHAR(1) DEFAULT 'N' NOT NULL,
    "isnaturalaccount" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ad_tree_id" INTEGER,
    PRIMARY KEY ("c_element_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsBalancing in ('Y','N')),
    CHECK (IsNaturalAccount in ('Y','N'))
);

CREATE UNIQUE INDEX "c_element_name" ON "c_element" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_ELEMENTVALUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_elementvalue"
(
    "c_elementvalue_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "accounttype" CHAR(1) NOT NULL,
    "accountsign" CHAR(1) NOT NULL,
    "isdoccontrolled" CHAR(1) DEFAULT 'N',
    "c_element_id" INTEGER NOT NULL,
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "validfrom" TIMESTAMP,
    "validto" TIMESTAMP,
    "postactual" CHAR(1) DEFAULT 'Y' NOT NULL,
    "postbudget" CHAR(1) DEFAULT 'Y' NOT NULL,
    "postencumbrance" CHAR(1) DEFAULT 'Y' NOT NULL,
    "poststatistical" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isbankaccount" CHAR(1) DEFAULT 'N',
    "c_bankaccount_id" INTEGER,
    "isforeigncurrency" CHAR(1) DEFAULT 'N',
    "c_currency_id" INTEGER,
    PRIMARY KEY ("c_elementvalue_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDocControlled in ('Y','N')),
    CHECK (PostActual in ('Y','N')),
    CHECK (PostBudget in ('Y','N')),
    CHECK (PostEncumbrance in ('Y','N')),
    CHECK (PostStatistical in ('Y','N')),
    CHECK (IsBankAccount in ('Y','N')),
    CHECK (IsForeignCurrency in ('Y','N'))
);

CREATE UNIQUE INDEX "c_elementvalue_value" ON "c_elementvalue" ("c_element_id", "value");

CREATE INDEX "c_elementvalue_name" ON "c_elementvalue" ("name");

-- ----------------------------------------------------------------------- 
-- C_ELEMENTVALUE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_elementvalue_trl"
(
    "c_elementvalue_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_elementvalue_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_GREETING 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_greeting"
(
    "c_greeting_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "greeting" VARCHAR(60),
    "isfirstnameonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_greeting_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsFirstNameOnly in ('Y','N')),
    CHECK (IsDefault in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_GREETING_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_greeting_trl"
(
    "ad_language" VARCHAR(6) NOT NULL,
    "c_greeting_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "greeting" VARCHAR(60),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_language", "c_greeting_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_INTERORG_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_interorg_acct"
(
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "ad_orgto_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "intercompanydueto_acct" INTEGER NOT NULL,
    "intercompanyduefrom_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_acctschema_id", "ad_org_id", "ad_orgto_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_INVOICE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_invoice"
(
    "c_invoice_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_doctype_id" INTEGER NOT NULL,
    "c_doctypetarget_id" INTEGER NOT NULL,
    "c_order_id" INTEGER,
    "description" VARCHAR(255),
    "isapproved" CHAR(1) DEFAULT 'Y' NOT NULL,
    "istransferred" CHAR(1) DEFAULT 'N' NOT NULL,
    "isprinted" CHAR(1) DEFAULT 'N' NOT NULL,
    "salesrep_id" INTEGER,
    "dateinvoiced" TIMESTAMP NOT NULL,
    "dateprinted" TIMESTAMP,
    "dateacct" TIMESTAMP NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    "poreference" VARCHAR(20),
    "isdiscountprinted" CHAR(1) DEFAULT 'Y' NOT NULL,
    "dateordered" TIMESTAMP,
    "c_currency_id" INTEGER NOT NULL,
    "paymentrule" CHAR(1) NOT NULL,
    "c_paymentterm_id" INTEGER NOT NULL,
    "c_charge_id" INTEGER,
    "chargeamt" NUMERIC DEFAULT 0,
    "totallines" NUMERIC DEFAULT 0 NOT NULL,
    "grandtotal" NUMERIC DEFAULT 0 NOT NULL,
    "m_pricelist_id" INTEGER NOT NULL,
    "istaxincluded" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_campaign_id" INTEGER,
    "c_project_id" INTEGER,
    "c_activity_id" INTEGER,
    "ispaid" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_payment_id" INTEGER,
    "c_cashline_id" INTEGER,
    "createfrom" CHAR(1),
    "generateto" CHAR(1),
    "sendemail" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_user_id" INTEGER,
    "copyfrom" CHAR(1),
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_orgtrx_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "c_conversiontype_id" INTEGER,
    "ispayschedulevalid" CHAR(1) DEFAULT 'N' NOT NULL,
    "ref_invoice_id" INTEGER,
    "isindispute" CHAR(1) DEFAULT 'N' NOT NULL,
    "invoicecollectiontype" CHAR(1),
    PRIMARY KEY ("c_invoice_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (IsTransferred in ('Y','N')),
    CHECK (IsPrinted in ('Y','N')),
    CHECK (IsDiscountPrinted in ('Y','N')),
    CHECK (IsTaxIncluded in ('Y','N')),
    CHECK (IsPaid in ('Y','N')),
    CHECK (CreateFrom in ('Y','N')),
    CHECK (GenerateTo in ('Y','N'))
);

CREATE INDEX "c_invoice_order" ON "c_invoice" ("c_order_id");

CREATE INDEX "c_invoice_bpartner" ON "c_invoice" ("c_bpartner_id");

CREATE UNIQUE INDEX "c_invoice_documentno" ON "c_invoice" ("documentno", "c_doctype_id", "c_bpartner_id");

CREATE INDEX "c_invoice_paid" ON "c_invoice" ("ad_client_id", "ispaid");

-- ----------------------------------------------------------------------- 
-- C_INVOICEBATCH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_invoicebatch"
(
    "c_invoicebatch_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "description" VARCHAR(255),
    "datedoc" TIMESTAMP NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'N' NOT NULL,
    "salesrep_id" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "c_conversiontype_id" INTEGER,
    "controlamt" NUMERIC DEFAULT 0 NOT NULL,
    "documentamt" NUMERIC DEFAULT 0 NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_invoicebatch_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_INVOICEBATCHLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_invoicebatchline"
(
    "c_invoicebatchline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_invoicebatch_id" INTEGER NOT NULL,
    "line" INTEGER DEFAULT 0 NOT NULL,
    "c_doctype_id" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "dateinvoiced" TIMESTAMP NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "c_charge_id" INTEGER NOT NULL,
    "qtyentered" NUMERIC DEFAULT 0 NOT NULL,
    "priceentered" NUMERIC DEFAULT 0 NOT NULL,
    "istaxincluded" CHAR(1) DEFAULT 'N' NOT NULL,
    "linenetamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_tax_id" INTEGER NOT NULL,
    "taxamt" NUMERIC DEFAULT 0 NOT NULL,
    "linetotalamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_project_id" INTEGER,
    "c_activity_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_invoice_id" INTEGER,
    "c_invoiceline_id" INTEGER,
    "description" VARCHAR(255),
    PRIMARY KEY ("c_invoicebatchline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTaxIncluded in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_INVOICELINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_invoiceline"
(
    "c_invoiceline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_invoice_id" INTEGER NOT NULL,
    "c_orderline_id" INTEGER,
    "m_inoutline_id" INTEGER,
    "line" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "m_product_id" INTEGER,
    "qtyinvoiced" NUMERIC DEFAULT 0 NOT NULL,
    "pricelist" NUMERIC DEFAULT 0 NOT NULL,
    "priceactual" NUMERIC DEFAULT 0 NOT NULL,
    "pricelimit" NUMERIC DEFAULT 0 NOT NULL,
    "linenetamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_charge_id" INTEGER,
    "c_uom_id" INTEGER,
    "c_tax_id" INTEGER,
    "s_resourceassignment_id" INTEGER,
    "a_asset_id" INTEGER,
    "taxamt" NUMERIC DEFAULT 0,
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "isdescription" CHAR(1) DEFAULT 'N' NOT NULL,
    "isprinted" CHAR(1) DEFAULT 'Y' NOT NULL,
    "linetotalamt" NUMERIC DEFAULT 0,
    "ref_invoiceline_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "qtyentered" NUMERIC NOT NULL,
    "priceentered" NUMERIC NOT NULL,
    "c_project_id" INTEGER,
    "c_projectphase_id" INTEGER,
    "c_projecttask_id" INTEGER,
    "rrstartdate" TIMESTAMP,
    "rramt" NUMERIC,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    PRIMARY KEY ("c_invoiceline_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "c_invoiceline_invoice" ON "c_invoiceline" ("c_invoice_id");

CREATE INDEX "c_invoiceline_product" ON "c_invoiceline" ("m_product_id");

CREATE INDEX "c_invoiceline_orderline" ON "c_invoiceline" ("c_orderline_id");

-- ----------------------------------------------------------------------- 
-- C_INVOICEPAYSCHEDULE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_invoicepayschedule"
(
    "c_invoicepayschedule_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_invoice_id" INTEGER NOT NULL,
    "c_payschedule_id" INTEGER,
    "duedate" TIMESTAMP NOT NULL,
    "dueamt" NUMERIC DEFAULT 0 NOT NULL,
    "discountdate" TIMESTAMP NOT NULL,
    "discountamt" NUMERIC DEFAULT 0 NOT NULL,
    "isvalid" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_invoicepayschedule_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsValid in ('Y','N'))
);

CREATE INDEX "c_invoicepayschedule_invoice" ON "c_invoicepayschedule" ("c_invoice_id");

-- ----------------------------------------------------------------------- 
-- C_INVOICESCHEDULE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_invoiceschedule"
(
    "c_invoiceschedule_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isamount" CHAR(1) DEFAULT 'N' NOT NULL,
    "amt" NUMERIC DEFAULT 0,
    "invoicefrequency" CHAR(1) NOT NULL,
    "invoiceweekday" CHAR(1),
    "invoiceweekdaycutoff" CHAR(1),
    "eveninvoiceweek" CHAR(1) DEFAULT 'Y',
    "invoiceday" INTEGER,
    "invoicedaycutoff" INTEGER,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_invoiceschedule_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsAmount in ('Y','N')),
    CHECK (EvenInvoiceWeek in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_INVOICETAX 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_invoicetax"
(
    "c_tax_id" INTEGER NOT NULL,
    "c_invoice_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "taxbaseamt" NUMERIC DEFAULT 0 NOT NULL,
    "taxamt" NUMERIC DEFAULT 0 NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "istaxincluded" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_tax_id", "c_invoice_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_JOB 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_job"
(
    "c_job_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_jobcategory_id" INTEGER NOT NULL,
    "isemployee" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_job_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_JOBASSIGNMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_jobassignment"
(
    "c_jobassignment_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "c_job_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP NOT NULL,
    "validto" TIMESTAMP,
    "description" VARCHAR(255),
    PRIMARY KEY ("c_jobassignment_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_JOBCATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_jobcategory"
(
    "c_jobcategory_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("c_jobcategory_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_JOBREMUNERATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_jobremuneration"
(
    "c_jobremuneration_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_job_id" INTEGER NOT NULL,
    "c_remuneration_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP NOT NULL,
    "validto" TIMESTAMP,
    "description" VARCHAR(255),
    PRIMARY KEY ("c_jobremuneration_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_LANDEDCOST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_landedcost"
(
    "c_landedcost_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "c_invoiceline_id" INTEGER NOT NULL,
    "m_costelement_id" INTEGER NOT NULL,
    "m_inoutline_id" INTEGER,
    "m_inout_id" INTEGER,
    "m_product_id" INTEGER,
    "landedcostdistribution" CHAR(1) NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("c_landedcost_id")
);

-- ----------------------------------------------------------------------- 
-- C_LANDEDCOSTALLOCATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_landedcostallocation"
(
    "c_landedcostallocation_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_invoiceline_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER,
    "m_costelement_id" INTEGER NOT NULL,
    "amt" NUMERIC DEFAULT 0 NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "base" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_landedcostallocation_id")
);

-- ----------------------------------------------------------------------- 
-- C_LOCATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_location"
(
    "c_location_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "address1" VARCHAR(60),
    "address2" VARCHAR(60),
    "city" VARCHAR(60),
    "postal" VARCHAR(10),
    "postal_add" VARCHAR(10),
    "c_country_id" INTEGER NOT NULL,
    "c_region_id" INTEGER,
    "c_city_id" INTEGER,
    "regionname" VARCHAR(40),
    "address3" VARCHAR(60),
    "address4" VARCHAR(60),
    PRIMARY KEY ("c_location_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_NONBUSINESSDAY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_nonbusinessday"
(
    "c_nonbusinessday_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60),
    "date1" TIMESTAMP NOT NULL,
    "c_calendar_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_nonbusinessday_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_ORDER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_order"
(
    "c_order_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_doctype_id" INTEGER NOT NULL,
    "c_doctypetarget_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "isapproved" CHAR(1) DEFAULT 'Y' NOT NULL,
    "iscreditapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "isdelivered" CHAR(1) DEFAULT 'N' NOT NULL,
    "isinvoiced" CHAR(1) DEFAULT 'N' NOT NULL,
    "isprinted" CHAR(1) DEFAULT 'N' NOT NULL,
    "istransferred" CHAR(1) DEFAULT 'N' NOT NULL,
    "isselected" CHAR(1) DEFAULT 'N' NOT NULL,
    "salesrep_id" INTEGER,
    "dateordered" TIMESTAMP NOT NULL,
    "datepromised" TIMESTAMP,
    "dateprinted" TIMESTAMP,
    "dateacct" TIMESTAMP NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    "poreference" VARCHAR(20),
    "isdiscountprinted" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "paymentrule" CHAR(1) NOT NULL,
    "c_paymentterm_id" INTEGER NOT NULL,
    "invoicerule" CHAR(1) NOT NULL,
    "deliveryrule" CHAR(1) NOT NULL,
    "freightcostrule" CHAR(1) NOT NULL,
    "freightamt" NUMERIC DEFAULT 0,
    "deliveryviarule" CHAR(1) NOT NULL,
    "m_shipper_id" INTEGER,
    "c_charge_id" INTEGER,
    "chargeamt" NUMERIC DEFAULT 0,
    "priorityrule" CHAR(1) NOT NULL,
    "totallines" NUMERIC DEFAULT 0 NOT NULL,
    "grandtotal" NUMERIC DEFAULT 0 NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "m_pricelist_id" INTEGER NOT NULL,
    "istaxincluded" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_campaign_id" INTEGER,
    "c_project_id" INTEGER,
    "c_activity_id" INTEGER,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_payment_id" INTEGER,
    "c_cashline_id" INTEGER,
    "sendemail" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_user_id" INTEGER,
    "copyfrom" CHAR(1),
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_orgtrx_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "c_conversiontype_id" INTEGER,
    "bill_bpartner_id" INTEGER,
    "bill_location_id" INTEGER,
    "bill_user_id" INTEGER,
    "pay_bpartner_id" INTEGER,
    "pay_location_id" INTEGER,
    "ref_order_id" INTEGER,
    "isdropship" CHAR(1) DEFAULT 'N' NOT NULL,
    "volume" NUMERIC,
    "weight" NUMERIC,
    PRIMARY KEY ("c_order_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (IsCreditApproved in ('Y','N')),
    CHECK (IsDelivered in ('Y','N')),
    CHECK (IsInvoiced in ('Y','N')),
    CHECK (IsPrinted in ('Y','N')),
    CHECK (IsTransferred in ('Y','N')),
    CHECK (IsSelected in ('Y','N')),
    CHECK (IsDiscountPrinted in ('Y','N')),
    CHECK (IsTaxIncluded in ('Y','N'))
);

CREATE INDEX "c_order_bpartner" ON "c_order" ("c_bpartner_id");

CREATE UNIQUE INDEX "c_order_documentno" ON "c_order" ("documentno", "c_doctype_id", "c_bpartner_id");

CREATE INDEX "c_order_processed" ON "c_order" ("ad_client_id", "processed");

-- ----------------------------------------------------------------------- 
-- C_ORDERLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_orderline"
(
    "c_orderline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_order_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER,
    "c_bpartner_location_id" INTEGER,
    "dateordered" TIMESTAMP NOT NULL,
    "datepromised" TIMESTAMP,
    "datedelivered" TIMESTAMP,
    "dateinvoiced" TIMESTAMP,
    "description" VARCHAR(255),
    "m_product_id" INTEGER,
    "m_warehouse_id" INTEGER NOT NULL,
    "c_uom_id" INTEGER NOT NULL,
    "qtyordered" NUMERIC DEFAULT 0 NOT NULL,
    "qtyreserved" NUMERIC DEFAULT 0 NOT NULL,
    "qtydelivered" NUMERIC DEFAULT 0 NOT NULL,
    "qtyinvoiced" NUMERIC DEFAULT 0 NOT NULL,
    "m_shipper_id" INTEGER,
    "c_currency_id" INTEGER NOT NULL,
    "pricelist" NUMERIC DEFAULT 0 NOT NULL,
    "priceactual" NUMERIC DEFAULT 0 NOT NULL,
    "pricelimit" NUMERIC DEFAULT 0 NOT NULL,
    "linenetamt" NUMERIC DEFAULT 0 NOT NULL,
    "discount" NUMERIC,
    "freightamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_charge_id" INTEGER,
    "c_tax_id" INTEGER NOT NULL,
    "s_resourceassignment_id" INTEGER,
    "ref_orderline_id" INTEGER,
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "isdescription" CHAR(1) DEFAULT 'N' NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "qtyentered" NUMERIC NOT NULL,
    "priceentered" NUMERIC NOT NULL,
    "c_project_id" INTEGER,
    "pricecost" NUMERIC,
    "qtylostsales" NUMERIC DEFAULT 0 NOT NULL,
    "c_projectphase_id" INTEGER,
    "c_projecttask_id" INTEGER,
    "rrstartdate" TIMESTAMP,
    "rramt" NUMERIC,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    PRIMARY KEY ("c_orderline_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "c_orderline_order" ON "c_orderline" ("c_order_id");

CREATE INDEX "c_orderline_product" ON "c_orderline" ("m_product_id");

-- ----------------------------------------------------------------------- 
-- C_ORDERTAX 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_ordertax"
(
    "c_order_id" INTEGER NOT NULL,
    "c_tax_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "taxbaseamt" NUMERIC DEFAULT 0 NOT NULL,
    "taxamt" NUMERIC DEFAULT 0 NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "istaxincluded" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_order_id", "c_tax_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_ORGASSIGNMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_orgassignment"
(
    "c_orgassignment_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP NOT NULL,
    "validto" TIMESTAMP NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("c_orgassignment_id")
);

-- ----------------------------------------------------------------------- 
-- C_PAYMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_payment"
(
    "c_payment_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "datetrx" TIMESTAMP NOT NULL,
    "isreceipt" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_doctype_id" INTEGER NOT NULL,
    "trxtype" CHAR(1) NOT NULL,
    "c_bankaccount_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER,
    "c_invoice_id" INTEGER,
    "c_bp_bankaccount_id" INTEGER,
    "c_paymentbatch_id" INTEGER,
    "tendertype" CHAR(1) NOT NULL,
    "creditcardtype" CHAR(1),
    "creditcardnumber" VARCHAR(20),
    "creditcardvv" VARCHAR(4),
    "creditcardexpmm" INTEGER,
    "creditcardexpyy" INTEGER,
    "micr" VARCHAR(20),
    "routingno" VARCHAR(20),
    "accountno" VARCHAR(20),
    "checkno" VARCHAR(20),
    "a_name" VARCHAR(60),
    "a_street" VARCHAR(60),
    "a_city" VARCHAR(60),
    "a_state" VARCHAR(40),
    "a_zip" VARCHAR(20),
    "a_ident_dl" VARCHAR(20),
    "a_ident_ssn" VARCHAR(20),
    "a_email" VARCHAR(60),
    "voiceauthcode" VARCHAR(20),
    "orig_trxid" VARCHAR(20),
    "ponum" VARCHAR(60),
    "c_currency_id" INTEGER NOT NULL,
    "payamt" NUMERIC DEFAULT 0 NOT NULL,
    "discountamt" NUMERIC DEFAULT 0,
    "writeoffamt" NUMERIC DEFAULT 0,
    "taxamt" NUMERIC DEFAULT 0,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "r_pnref" VARCHAR(20),
    "r_result" VARCHAR(20),
    "r_respmsg" VARCHAR(60),
    "r_authcode" VARCHAR(20),
    "r_avsaddr" CHAR(1),
    "r_avszip" CHAR(1),
    "r_info" VARCHAR(2000),
    "processing" CHAR(1),
    "oprocessing" CHAR(1),
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "isreconciled" CHAR(1) DEFAULT 'N' NOT NULL,
    "isallocated" CHAR(1) DEFAULT 'N' NOT NULL,
    "isonline" CHAR(1) DEFAULT 'N' NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "isoverunderpayment" CHAR(1) DEFAULT 'N' NOT NULL,
    "overunderamt" NUMERIC DEFAULT 0,
    "a_country" VARCHAR(40),
    "c_project_id" INTEGER,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "chargeamt" NUMERIC DEFAULT 0,
    "c_charge_id" INTEGER,
    "isdelayedcapture" CHAR(1) DEFAULT 'N' NOT NULL,
    "r_authcode_dc" VARCHAR(20),
    "r_cvv2match" CHAR(1),
    "r_pnref_dc" VARCHAR(20),
    "swipe" VARCHAR(80),
    "ad_orgtrx_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "c_conversiontype_id" INTEGER,
    "description" VARCHAR(255),
    "dateacct" TIMESTAMP NOT NULL,
    "c_order_id" INTEGER,
    "isprepayment" CHAR(1) DEFAULT 'N' NOT NULL,
    "ref_payment_id" INTEGER,
    PRIMARY KEY ("c_payment_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsReceipt in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (IsReconciled in ('Y','N')),
    CHECK (IsAllocated in ('Y','N')),
    CHECK (IsOnline in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE INDEX "c_payment_bankaccount" ON "c_payment" ("c_bankaccount_id");

CREATE INDEX "c_payment_bpartner" ON "c_payment" ("c_bpartner_id");

-- ----------------------------------------------------------------------- 
-- C_PAYMENTALLOCATE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_paymentallocate"
(
    "c_paymentallocate_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_payment_id" INTEGER NOT NULL,
    "c_invoice_id" INTEGER NOT NULL,
    "amount" NUMERIC DEFAULT 0 NOT NULL,
    "discountamt" NUMERIC DEFAULT 0 NOT NULL,
    "writeoffamt" NUMERIC DEFAULT 0 NOT NULL,
    "overunderamt" NUMERIC DEFAULT 0 NOT NULL,
    "invoiceamt" NUMERIC DEFAULT 0,
    "c_allocationline_id" INTEGER,
    PRIMARY KEY ("c_paymentallocate_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PAYMENTBATCH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_paymentbatch"
(
    "c_paymentbatch_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "c_paymentprocessor_id" INTEGER,
    "documentno" VARCHAR(30),
    "processingdate" TIMESTAMP,
    "processing" CHAR(1) NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_paymentbatch_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PAYMENTPROCESSOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_paymentprocessor"
(
    "c_paymentprocessor_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_bankaccount_id" INTEGER NOT NULL,
    "ad_sequence_id" INTEGER,
    "payprocessorclass" VARCHAR(60),
    "userid" VARCHAR(60),
    "password" VARCHAR(60),
    "hostaddress" VARCHAR(60),
    "hostport" INTEGER,
    "proxyaddress" VARCHAR(60),
    "proxyport" INTEGER,
    "proxylogon" VARCHAR(60),
    "proxypassword" VARCHAR(60),
    "acceptvisa" CHAR(1) DEFAULT 'Y' NOT NULL,
    "acceptmc" CHAR(1) DEFAULT 'Y' NOT NULL,
    "acceptamex" CHAR(1) DEFAULT 'Y' NOT NULL,
    "acceptdiners" CHAR(1) DEFAULT 'Y' NOT NULL,
    "acceptdiscover" CHAR(1) DEFAULT 'Y' NOT NULL,
    "acceptcorporate" CHAR(1) DEFAULT 'Y' NOT NULL,
    "acceptcheck" CHAR(1) DEFAULT 'Y' NOT NULL,
    "acceptatm" CHAR(1) DEFAULT 'Y' NOT NULL,
    "requirevv" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_currency_id" INTEGER,
    "costpertrx" NUMERIC DEFAULT 0 NOT NULL,
    "commission" NUMERIC NOT NULL,
    "partnerid" VARCHAR(60),
    "vendorid" VARCHAR(60),
    "minimumamt" NUMERIC DEFAULT 0,
    "acceptdirectdebit" CHAR(1) DEFAULT 'N' NOT NULL,
    "acceptdirectdeposit" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_paymentprocessor_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (AcceptVisa in ('Y','N')),
    CHECK (AcceptMC in ('Y','N')),
    CHECK (AcceptAmex in ('Y','N')),
    CHECK (AcceptDiners in ('Y','N')),
    CHECK (AcceptDiscover in ('Y','N')),
    CHECK (AcceptCorporate in ('Y','N')),
    CHECK (AcceptCheck in ('Y','N')),
    CHECK (AcceptATM in ('Y','N')),
    CHECK (RequireVV in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PAYMENTTERM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_paymentterm"
(
    "c_paymentterm_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "documentnote" VARCHAR(2000),
    "afterdelivery" CHAR(1) DEFAULT 'N' NOT NULL,
    "isduefixed" CHAR(1) DEFAULT 'N' NOT NULL,
    "netdays" INTEGER NOT NULL,
    "gracedays" INTEGER NOT NULL,
    "fixmonthcutoff" INTEGER,
    "fixmonthday" INTEGER,
    "fixmonthoffset" INTEGER,
    "discountdays" INTEGER NOT NULL,
    "discount" NUMERIC NOT NULL,
    "discountdays2" INTEGER NOT NULL,
    "discount2" NUMERIC NOT NULL,
    "isnextbusinessday" CHAR(1) DEFAULT 'Y',
    "isdefault" CHAR(1) DEFAULT 'N',
    "value" VARCHAR(40) NOT NULL,
    "netday" CHAR(1),
    "isvalid" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("c_paymentterm_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (AfterDelivery in ('Y','N')),
    CHECK (IsDueFixed in ('Y','N')),
    CHECK (IsNextBusinessDay in ('Y','N'))
);

CREATE UNIQUE INDEX "c_paymentterm_name" ON "c_paymentterm" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_PAYMENTTERM_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_paymentterm_trl"
(
    "c_paymentterm_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "documentnote" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_paymentterm_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PAYSCHEDULE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_payschedule"
(
    "c_payschedule_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_paymentterm_id" INTEGER NOT NULL,
    "percentage" NUMERIC NOT NULL,
    "netdays" INTEGER NOT NULL,
    "netday" CHAR(1),
    "discountdays" INTEGER NOT NULL,
    "discount" NUMERIC NOT NULL,
    "isvalid" CHAR(1) DEFAULT 'N' NOT NULL,
    "gracedays" INTEGER NOT NULL,
    PRIMARY KEY ("c_payschedule_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsValid in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PAYSELECTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_payselection"
(
    "c_payselection_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_bankaccount_id" INTEGER NOT NULL,
    "paydate" TIMESTAMP NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'Y' NOT NULL,
    "totalamt" NUMERIC DEFAULT 0 NOT NULL,
    "createfrom" CHAR(1),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_payselection_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PAYSELECTIONCHECK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_payselectioncheck"
(
    "c_payselectioncheck_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_payselection_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "payamt" NUMERIC DEFAULT 0 NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "c_payment_id" INTEGER,
    "documentno" VARCHAR(30),
    "isprinted" CHAR(1) DEFAULT 'N' NOT NULL,
    "paymentrule" CHAR(1) NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "discountamt" NUMERIC DEFAULT 0 NOT NULL,
    "isreceipt" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bp_bankaccount_id" INTEGER,
    PRIMARY KEY ("c_payselectioncheck_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PAYSELECTIONLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_payselectionline"
(
    "c_payselectionline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_payselection_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "paymentrule" CHAR(1) NOT NULL,
    "ismanual" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_invoice_id" INTEGER NOT NULL,
    "payamt" NUMERIC DEFAULT 0 NOT NULL,
    "differenceamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_payselectioncheck_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "discountamt" NUMERIC DEFAULT 0 NOT NULL,
    "openamt" NUMERIC DEFAULT 0 NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_payselectionline_id"),
    CHECK (IsManual in ('Y','N')),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "c_payselline_paysel" ON "c_payselectionline" ("c_payselection_id");

-- ----------------------------------------------------------------------- 
-- C_PERIOD 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_period"
(
    "c_period_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "periodno" INTEGER NOT NULL,
    "c_year_id" INTEGER NOT NULL,
    "startdate" TIMESTAMP NOT NULL,
    "enddate" TIMESTAMP,
    "periodtype" CHAR(1) NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("c_period_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_period_nounique" ON "c_period" ("c_year_id", "periodno");

-- ----------------------------------------------------------------------- 
-- C_PERIODCONTROL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_periodcontrol"
(
    "c_periodcontrol_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_period_id" INTEGER NOT NULL,
    "docbasetype" CHAR(3) NOT NULL,
    "periodstatus" CHAR(1),
    "periodaction" CHAR(1) NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("c_periodcontrol_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PHASE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_phase"
(
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_projecttype_id" INTEGER NOT NULL,
    "standardqty" NUMERIC DEFAULT 0 NOT NULL,
    "c_phase_id" INTEGER NOT NULL,
    "m_product_id" INTEGER,
    PRIMARY KEY ("c_phase_id")
);

-- ----------------------------------------------------------------------- 
-- C_POS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_pos"
(
    "c_pos_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "salesrep_id" INTEGER NOT NULL,
    "m_pricelist_id" INTEGER NOT NULL,
    "c_cashbook_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "printername" VARCHAR(60),
    "c_poskeylayout_id" INTEGER,
    "ismodifyprice" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartnercashtrx_id" INTEGER,
    "c_doctype_id" INTEGER,
    PRIMARY KEY ("c_pos_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_POSKEY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_poskey"
(
    "c_poskey_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_poskeylayout_id" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "qty" NUMERIC NOT NULL,
    "ad_printcolor_id" INTEGER,
    PRIMARY KEY ("c_poskey_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_POSKEYLAYOUT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_poskeylayout"
(
    "c_poskeylayout_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("c_poskeylayout_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PROJECT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_project"
(
    "c_project_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "note" VARCHAR(2000),
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_user_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "c_bpartner_location_id" INTEGER,
    "poreference" VARCHAR(20),
    "c_paymentterm_id" INTEGER,
    "c_currency_id" INTEGER NOT NULL,
    "m_pricelist_version_id" INTEGER,
    "c_campaign_id" INTEGER,
    "iscommitment" CHAR(1) DEFAULT 'Y' NOT NULL,
    "plannedamt" NUMERIC DEFAULT 0 NOT NULL,
    "plannedqty" NUMERIC DEFAULT 0 NOT NULL,
    "plannedmarginamt" NUMERIC DEFAULT 0 NOT NULL,
    "committedamt" NUMERIC DEFAULT 0 NOT NULL,
    "datecontract" TIMESTAMP,
    "datefinish" TIMESTAMP,
    "generateto" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "salesrep_id" INTEGER,
    "copyfrom" CHAR(1),
    "c_projecttype_id" INTEGER,
    "committedqty" NUMERIC DEFAULT 0 NOT NULL,
    "invoicedamt" NUMERIC DEFAULT 0 NOT NULL,
    "invoicedqty" NUMERIC DEFAULT 0 NOT NULL,
    "projectbalanceamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_phase_id" INTEGER,
    "iscommitceiling" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_warehouse_id" INTEGER,
    "projectcategory" CHAR(1) DEFAULT 'N',
    "processing" CHAR(1),
    "c_bpartnersr_id" INTEGER,
    "projinvoicerule" CHAR(1) DEFAULT '-' NOT NULL,
    "projectlinelevel" CHAR(1) DEFAULT 'P' NOT NULL,
    PRIMARY KEY ("c_project_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCommitment in ('Y','N')),
    CHECK (GenerateTo in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE UNIQUE INDEX "c_project_value" ON "c_project" ("ad_client_id", "value");

-- ----------------------------------------------------------------------- 
-- C_PROJECTISSUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_projectissue"
(
    "c_projectissue_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_project_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "m_locator_id" INTEGER NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "movementdate" TIMESTAMP NOT NULL,
    "s_timeexpenseline_id" INTEGER,
    "m_inoutline_id" INTEGER,
    "processing" CHAR(1),
    PRIMARY KEY ("c_projectissue_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PROJECTISSUEMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_projectissuema"
(
    "c_projectissue_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_projectissue_id", "m_attributesetinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PROJECTLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_projectline"
(
    "c_projectline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_project_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "plannedqty" NUMERIC DEFAULT 0 NOT NULL,
    "plannedprice" NUMERIC DEFAULT 0 NOT NULL,
    "plannedamt" NUMERIC DEFAULT 0 NOT NULL,
    "plannedmarginamt" NUMERIC DEFAULT 0 NOT NULL,
    "committedamt" NUMERIC DEFAULT 0,
    "m_product_id" INTEGER,
    "m_product_category_id" INTEGER,
    "invoicedamt" NUMERIC DEFAULT 0 NOT NULL,
    "invoicedqty" NUMERIC DEFAULT 0 NOT NULL,
    "committedqty" NUMERIC DEFAULT 0,
    "c_projectissue_id" INTEGER,
    "c_order_id" INTEGER,
    "c_orderpo_id" INTEGER,
    "isprinted" CHAR(1) DEFAULT 'Y' NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "dopricing" CHAR(1) DEFAULT 'Y',
    "c_projectphase_id" INTEGER,
    "c_projecttask_id" INTEGER,
    PRIMARY KEY ("c_projectline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_PROJECTPHASE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_projectphase"
(
    "c_project_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "startdate" TIMESTAMP,
    "enddate" TIMESTAMP,
    "iscomplete" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_product_id" INTEGER,
    "priceactual" NUMERIC DEFAULT 0,
    "generateorder" CHAR(1),
    "c_order_id" INTEGER,
    "c_phase_id" INTEGER,
    "c_projectphase_id" INTEGER NOT NULL,
    "help" VARCHAR(2000),
    "name" VARCHAR(60) NOT NULL,
    "qty" NUMERIC DEFAULT 0,
    "seqno" INTEGER NOT NULL,
    "committedamt" NUMERIC DEFAULT 0 NOT NULL,
    "iscommitceiling" CHAR(1) DEFAULT 'N' NOT NULL,
    "projinvoicerule" CHAR(1),
    "plannedamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_projectphase_id")
);

-- ----------------------------------------------------------------------- 
-- C_PROJECTTASK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_projecttask"
(
    "c_projecttask_id" INTEGER NOT NULL,
    "c_task_id" INTEGER,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "m_product_id" INTEGER,
    "c_projectphase_id" INTEGER NOT NULL,
    "qty" NUMERIC DEFAULT 0,
    "projinvoicerule" CHAR(1),
    "plannedamt" NUMERIC DEFAULT 0 NOT NULL,
    "committedamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_projecttask_id")
);

-- ----------------------------------------------------------------------- 
-- C_PROJECTTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_projecttype"
(
    "c_projecttype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "projectcategory" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_projecttype_id")
);

-- ----------------------------------------------------------------------- 
-- C_PROJECT_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_project_acct"
(
    "c_project_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "pj_asset_acct" INTEGER NOT NULL,
    "pj_wip_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_project_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RECURRING 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_recurring"
(
    "c_recurring_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "recurringtype" CHAR(1) NOT NULL,
    "c_order_id" INTEGER,
    "c_invoice_id" INTEGER,
    "c_payment_id" INTEGER,
    "c_project_id" INTEGER,
    "gl_journalbatch_id" INTEGER,
    "frequencytype" CHAR(1) NOT NULL,
    "runsmax" INTEGER NOT NULL,
    "runsremaining" INTEGER NOT NULL,
    "datelastrun" TIMESTAMP,
    "datenextrun" TIMESTAMP,
    "processing" CHAR(1),
    "frequency" INTEGER,
    PRIMARY KEY ("c_recurring_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RECURRING_RUN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_recurring_run"
(
    "c_recurring_run_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_payment_id" INTEGER,
    "c_order_id" INTEGER,
    "c_invoice_id" INTEGER,
    "c_project_id" INTEGER,
    "gl_journalbatch_id" INTEGER,
    "c_recurring_id" INTEGER NOT NULL,
    "datedoc" TIMESTAMP,
    PRIMARY KEY ("c_recurring_run_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_REGION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_region"
(
    "c_region_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_country_id" INTEGER NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("c_region_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDefault in ('Y','N'))
);

CREATE UNIQUE INDEX "c_region_name" ON "c_region" ("c_country_id", "name");

-- ----------------------------------------------------------------------- 
-- C_REMUNERATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_remuneration"
(
    "c_remuneration_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "remunerationtype" CHAR(1) NOT NULL,
    "standardhours" INTEGER DEFAULT 0 NOT NULL,
    "grossramt" NUMERIC DEFAULT 0 NOT NULL,
    "grossrcost" NUMERIC DEFAULT 0 NOT NULL,
    "overtimeamt" NUMERIC DEFAULT 0 NOT NULL,
    "overtimecost" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_remuneration_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_REVENUERECOGNITION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_revenuerecognition"
(
    "c_revenuerecognition_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "istimebased" CHAR(1) NOT NULL,
    "recognitionfrequency" CHAR(1),
    "nomonths" INTEGER,
    PRIMARY KEY ("c_revenuerecognition_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_REVENUERECOGNITION_PLAN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_revenuerecognition_plan"
(
    "c_revenuerecognition_plan_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "c_revenuerecognition_id" INTEGER NOT NULL,
    "c_invoiceline_id" INTEGER NOT NULL,
    "unearnedrevenue_acct" INTEGER NOT NULL,
    "p_revenue_acct" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "totalamt" NUMERIC DEFAULT 0 NOT NULL,
    "recognizedamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_revenuerecognition_plan_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_REVENUERECOGNITION_RUN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_revenuerecognition_run"
(
    "c_revenuerecognition_run_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_revenuerecognition_plan_id" INTEGER NOT NULL,
    "gl_journal_id" INTEGER NOT NULL,
    "recognizedamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_revenuerecognition_run_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQ 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfq"
(
    "c_rfq_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_rfq_topic_id" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "dateresponse" TIMESTAMP NOT NULL,
    "isrfqresponseaccepted" CHAR(1) DEFAULT 'Y' NOT NULL,
    "dateworkstart" TIMESTAMP,
    "deliverydays" INTEGER DEFAULT 0,
    "dateworkcomplete" TIMESTAMP,
    "quotetype" CHAR(1) NOT NULL,
    "isquotetotalamt" CHAR(1) DEFAULT 'N' NOT NULL,
    "isquoteallqty" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "isinvitedvendorsonly" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartner_id" INTEGER,
    "c_bpartner_location_id" INTEGER,
    "ad_user_id" INTEGER,
    "salesrep_id" INTEGER NOT NULL,
    "margin" NUMERIC,
    "createso" CHAR(1),
    "createpo" CHAR(1),
    "publishrfq" CHAR(1),
    "c_order_id" INTEGER,
    "copylines" CHAR(1),
    "rankrfq" CHAR(1),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "documentno" VARCHAR(30) DEFAULT '.' NOT NULL,
    PRIMARY KEY ("c_rfq_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsRfQResponseAccepted in ('Y','N')),
    CHECK (IsQuoteTotalAmt in ('Y','N')),
    CHECK (IsQuoteAllQty in ('Y','N')),
    CHECK (IsSelfService in ('Y','N')),
    CHECK (IsInvitedVendorsOnly in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfqline"
(
    "c_rfqline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_rfq_id" INTEGER NOT NULL,
    "line" INTEGER DEFAULT 0 NOT NULL,
    "m_product_id" INTEGER,
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "deliverydays" INTEGER DEFAULT 0,
    "dateworkcomplete" TIMESTAMP,
    "dateworkstart" TIMESTAMP,
    PRIMARY KEY ("c_rfqline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQLINEQTY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfqlineqty"
(
    "c_rfqlineqty_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_rfqline_id" INTEGER NOT NULL,
    "c_uom_id" INTEGER NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "margin" NUMERIC,
    "ispurchaseqty" CHAR(1) DEFAULT 'N' NOT NULL,
    "bestresponseamt" NUMERIC DEFAULT 0,
    "isofferqty" CHAR(1) DEFAULT 'N' NOT NULL,
    "offeramt" NUMERIC DEFAULT 0,
    "benchmarkprice" NUMERIC DEFAULT 0 NOT NULL,
    "isrfqqty" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("c_rfqlineqty_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPurchaseQty in ('Y','N')),
    CHECK (IsOfferQty in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQRESPONSE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfqresponse"
(
    "c_rfqresponse_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_rfq_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "c_currency_id" INTEGER NOT NULL,
    "dateinvited" TIMESTAMP,
    "dateresponse" TIMESTAMP,
    "dateworkstart" TIMESTAMP,
    "deliverydays" INTEGER DEFAULT 0,
    "dateworkcomplete" TIMESTAMP,
    "price" NUMERIC DEFAULT 0 NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "iscomplete" CHAR(1) DEFAULT 'N' NOT NULL,
    "isselectedwinner" CHAR(1) DEFAULT 'N' NOT NULL,
    "ranking" INTEGER DEFAULT 0,
    "processing" CHAR(1),
    "c_order_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "checkcomplete" CHAR(1),
    PRIMARY KEY ("c_rfqresponse_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSelfService in ('Y','N')),
    CHECK (IsComplete in ('Y','N')),
    CHECK (IsSelectedWinner in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQRESPONSELINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfqresponseline"
(
    "c_rfqresponseline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_rfqline_id" INTEGER NOT NULL,
    "c_rfqresponse_id" INTEGER NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "isselectedwinner" CHAR(1) DEFAULT 'N' NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "dateworkstart" TIMESTAMP,
    "deliverydays" INTEGER DEFAULT 0,
    "dateworkcomplete" TIMESTAMP,
    PRIMARY KEY ("c_rfqresponseline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSelfService in ('Y','N')),
    CHECK (IsSelectedWinner in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQRESPONSELINEQTY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfqresponselineqty"
(
    "c_rfqresponselineqty_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_rfqresponseline_id" INTEGER NOT NULL,
    "c_rfqlineqty_id" INTEGER NOT NULL,
    "price" NUMERIC DEFAULT 0 NOT NULL,
    "discount" NUMERIC,
    "ranking" INTEGER DEFAULT 0,
    PRIMARY KEY ("c_rfqresponselineqty_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQ_TOPIC 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfq_topic"
(
    "c_rfq_topic_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_printformat_id" INTEGER,
    PRIMARY KEY ("c_rfq_topic_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSelfService in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQ_TOPICSUBSCRIBER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfq_topicsubscriber"
(
    "c_rfq_topicsubscriber_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_rfq_topic_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "subscribedate" TIMESTAMP,
    "optoutdate" TIMESTAMP,
    PRIMARY KEY ("c_rfq_topicsubscriber_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_RFQ_TOPICSUBSCRIBERONLY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_rfq_topicsubscriberonly"
(
    "c_rfq_topicsubscriberonly_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_rfq_topicsubscriber_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "m_product_id" INTEGER,
    "m_product_category_id" INTEGER,
    PRIMARY KEY ("c_rfq_topicsubscriberonly_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_SALESREGION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_salesregion"
(
    "c_salesregion_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "salesrep_id" INTEGER,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_salesregion_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_salesregion_value" ON "c_salesregion" ("ad_client_id", "value");

-- ----------------------------------------------------------------------- 
-- C_SERVICELEVEL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_servicelevel"
(
    "c_servicelevel_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "servicelevelprovided" NUMERIC NOT NULL,
    "servicelevelinvoiced" NUMERIC NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    "c_revenuerecognition_plan_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_servicelevel_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_SERVICELEVELLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_servicelevelline"
(
    "c_servicelevelline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_servicelevel_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "servicelevelprovided" NUMERIC NOT NULL,
    "servicedate" TIMESTAMP NOT NULL,
    "processed" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("c_servicelevelline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_SUBACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_subacct"
(
    "c_subacct_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_elementvalue_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_subacct_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_SUBSCRIPTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_subscription"
(
    "c_subscription_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "c_subscriptiontype_id" INTEGER NOT NULL,
    "startdate" TIMESTAMP NOT NULL,
    "paiduntildate" TIMESTAMP NOT NULL,
    "isdue" CHAR(1) DEFAULT 'N' NOT NULL,
    "renewaldate" TIMESTAMP NOT NULL,
    PRIMARY KEY ("c_subscription_id")
);

-- ----------------------------------------------------------------------- 
-- C_SUBSCRIPTIONTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_subscriptiontype"
(
    "c_subscriptiontype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "frequencytype" CHAR(1) NOT NULL,
    "frequency" INTEGER NOT NULL,
    "ad_org_id" INTEGER,
    PRIMARY KEY ("c_subscriptiontype_id")
);

-- ----------------------------------------------------------------------- 
-- C_SUBSCRIPTION_DELIVERY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_subscription_delivery"
(
    "c_subscription_delivery_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_subscription_id" INTEGER NOT NULL,
    PRIMARY KEY ("c_subscription_delivery_id")
);

-- ----------------------------------------------------------------------- 
-- C_TASK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_task"
(
    "c_task_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_phase_id" INTEGER NOT NULL,
    "m_product_id" INTEGER,
    "standardqty" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("c_task_id")
);

-- ----------------------------------------------------------------------- 
-- C_TAX 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_tax"
(
    "c_tax_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "taxindicator" VARCHAR(10),
    "isdocumentlevel" CHAR(1) DEFAULT 'Y' NOT NULL,
    "validfrom" TIMESTAMP NOT NULL,
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "requirestaxcertificate" CHAR(1) DEFAULT 'N' NOT NULL,
    "rate" NUMERIC NOT NULL,
    "parent_tax_id" INTEGER,
    "c_country_id" INTEGER,
    "c_region_id" INTEGER,
    "to_country_id" INTEGER,
    "to_region_id" INTEGER,
    "c_taxcategory_id" INTEGER NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "istaxexempt" CHAR(1) DEFAULT 'N' NOT NULL,
    "sopotype" CHAR(1) DEFAULT 'B' NOT NULL,
    "issalestax" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_tax_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDocumentLevel in ('Y','N')),
    CHECK (RequiresTaxCertificate in ('Y','N'))
);

CREATE UNIQUE INDEX "c_tax_name" ON "c_tax" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_TAXCATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_taxcategory"
(
    "c_taxcategory_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "commoditycode" VARCHAR(20),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_taxcategory_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_taxcategory_name" ON "c_taxcategory" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- C_TAXCATEGORY_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_taxcategory_trl"
(
    "c_taxcategory_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_taxcategory_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_TAXDECLARATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_taxdeclaration"
(
    "c_taxdeclaration_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "datetrx" TIMESTAMP NOT NULL,
    "datefrom" TIMESTAMP NOT NULL,
    "dateto" TIMESTAMP NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_taxdeclaration_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_TAXDECLARATIONACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_taxdeclarationacct"
(
    "c_taxdeclarationacct_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "c_taxdeclaration_id" INTEGER NOT NULL,
    "fact_acct_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "line" INTEGER,
    PRIMARY KEY ("c_taxdeclarationacct_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_TAXDECLARATIONLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_taxdeclarationline"
(
    "c_taxdeclarationline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "line" INTEGER DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "ismanual" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_taxdeclaration_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_tax_id" INTEGER NOT NULL,
    "c_invoice_id" INTEGER,
    "c_invoiceline_id" INTEGER,
    "c_allocationline_id" INTEGER,
    "c_currency_id" INTEGER NOT NULL,
    "taxbaseamt" NUMERIC DEFAULT 0 NOT NULL,
    "taxamt" NUMERIC DEFAULT 0 NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    PRIMARY KEY ("c_taxdeclarationline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsManual in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_TAXPOSTAL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_taxpostal"
(
    "c_taxpostal_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_tax_id" INTEGER NOT NULL,
    "postal" VARCHAR(10) NOT NULL,
    "postal_to" VARCHAR(10),
    PRIMARY KEY ("c_taxpostal_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_TAX_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_tax_acct"
(
    "c_tax_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "t_due_acct" INTEGER NOT NULL,
    "t_liability_acct" INTEGER NOT NULL,
    "t_credit_acct" INTEGER NOT NULL,
    "t_receivables_acct" INTEGER NOT NULL,
    "t_expense_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_tax_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_TAX_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_tax_trl"
(
    "c_tax_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "taxindicator" VARCHAR(10),
    PRIMARY KEY ("c_tax_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_UOM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_uom"
(
    "c_uom_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "x12de355" VARCHAR(4) NOT NULL,
    "uomsymbol" VARCHAR(10),
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "stdprecision" INTEGER NOT NULL,
    "costingprecision" INTEGER NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_uom_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_uom_name" ON "c_uom" ("ad_client_id", "name");

CREATE INDEX "c_uom_x12" ON "c_uom" ("x12de355");

-- ----------------------------------------------------------------------- 
-- C_UOM_CONVERSION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_uom_conversion"
(
    "c_uom_conversion_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_uom_id" INTEGER NOT NULL,
    "c_uom_to_id" INTEGER NOT NULL,
    "multiplyrate" NUMERIC DEFAULT 0 NOT NULL,
    "dividerate" NUMERIC DEFAULT 0 NOT NULL,
    "m_product_id" INTEGER,
    PRIMARY KEY ("c_uom_conversion_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_uom_conversion_product" ON "c_uom_conversion" ("c_uom_id", "c_uom_to_id", "m_product_id");

-- ----------------------------------------------------------------------- 
-- C_UOM_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_uom_trl"
(
    "c_uom_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "uomsymbol" VARCHAR(10),
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("c_uom_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_USERREMUNERATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_userremuneration"
(
    "c_userremuneration_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ad_user_id" INTEGER NOT NULL,
    "c_remuneration_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP NOT NULL,
    "validto" TIMESTAMP,
    "grossramt" NUMERIC DEFAULT 0 NOT NULL,
    "grossrcost" NUMERIC DEFAULT 0 NOT NULL,
    "overtimeamt" NUMERIC DEFAULT 0 NOT NULL,
    "overtimecost" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("c_userremuneration_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_VALIDCOMBINATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_validcombination"
(
    "c_validcombination_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "alias" VARCHAR(40),
    "combination" VARCHAR(60),
    "description" VARCHAR(255),
    "isfullyqualified" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "account_id" INTEGER NOT NULL,
    "m_product_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    "c_locfrom_id" INTEGER,
    "c_locto_id" INTEGER,
    "c_salesregion_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "c_subacct_id" INTEGER,
    "userelement1_id" INTEGER,
    "userelement2_id" INTEGER,
    PRIMARY KEY ("c_validcombination_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsFullyQualified in ('Y','N'))
);

CREATE INDEX "c_validcombination_alias" ON "c_validcombination" ("ad_client_id", "alias");

CREATE UNIQUE INDEX "c_validcombination_alt" ON "c_validcombination" ("c_acctschema_id", "ad_org_id", "account_id", "c_subacct_id", "m_product_id", "c_bpartner_id", "ad_orgtrx_id", "c_locfrom_id", "c_locto_id", "c_salesregion_id", "c_project_id", "c_campaign_id", "c_activity_id", "user1_id", "user2_id", "userelement1_id", "userelement2_id");

-- ----------------------------------------------------------------------- 
-- C_WITHHOLDING 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_withholding"
(
    "c_withholding_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_paymentterm_id" INTEGER NOT NULL,
    "istaxwithholding" CHAR(1) DEFAULT 'Y' NOT NULL,
    "istaxprorated" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispaidto3party" CHAR(1) DEFAULT 'Y' NOT NULL,
    "beneficiary" INTEGER,
    "ispercentwithholding" CHAR(1) DEFAULT 'Y' NOT NULL,
    "percent" NUMERIC DEFAULT 0,
    "fixamt" NUMERIC DEFAULT 0,
    "thresholdmin" NUMERIC DEFAULT 0,
    "thresholdmax" NUMERIC DEFAULT 0,
    "minamt" NUMERIC DEFAULT 0,
    "maxamt" NUMERIC DEFAULT 0,
    PRIMARY KEY ("c_withholding_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTaxWithholding in ('Y','N')),
    CHECK (IsTaxProrated in ('Y','N')),
    CHECK (IsPaidTo3Party in ('Y','N')),
    CHECK (IsPercentWithholding in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_WITHHOLDING_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_withholding_acct"
(
    "c_withholding_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "withholding_acct" INTEGER NOT NULL,
    PRIMARY KEY ("c_withholding_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- C_YEAR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "c_year"
(
    "c_year_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "fiscalyear" VARCHAR(10) NOT NULL,
    "description" VARCHAR(255),
    "c_calendar_id" INTEGER NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("c_year_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "c_year_name" ON "c_year" ("c_calendar_id", "fiscalyear");

-- ----------------------------------------------------------------------- 
-- FACT_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "fact_acct"
(
    "fact_acct_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "account_id" INTEGER NOT NULL,
    "datetrx" TIMESTAMP NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "c_period_id" INTEGER,
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "line_id" INTEGER,
    "gl_category_id" INTEGER,
    "gl_budget_id" INTEGER,
    "c_tax_id" INTEGER,
    "m_locator_id" INTEGER,
    "postingtype" CHAR(1) NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "amtsourcedr" NUMERIC DEFAULT 0 NOT NULL,
    "amtsourcecr" NUMERIC DEFAULT 0 NOT NULL,
    "amtacctdr" NUMERIC DEFAULT 0 NOT NULL,
    "amtacctcr" NUMERIC DEFAULT 0 NOT NULL,
    "c_uom_id" INTEGER,
    "qty" NUMERIC DEFAULT 0,
    "m_product_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    "c_locfrom_id" INTEGER,
    "c_locto_id" INTEGER,
    "c_salesregion_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "description" VARCHAR(255),
    "a_asset_id" INTEGER,
    "c_subacct_id" INTEGER,
    "userelement1_id" INTEGER,
    "userelement2_id" INTEGER,
    "c_projectphase_id" INTEGER,
    "c_projecttask_id" INTEGER,
    PRIMARY KEY ("fact_acct_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "fact_acct_account" ON "fact_acct" ("ad_client_id", "ad_org_id", "c_acctschema_id", "account_id");

CREATE INDEX "fact_acct_dateacct" ON "fact_acct" ("dateacct");

-- ----------------------------------------------------------------------- 
-- FACT_ACCT_BALANCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "fact_acct_balance"
(
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "account_id" INTEGER NOT NULL,
    "postingtype" CHAR(1) NOT NULL,
    "m_product_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "c_project_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    "c_salesregion_id" INTEGER,
    "c_activity_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_locto_id" INTEGER,
    "c_locfrom_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "gl_budget_id" INTEGER,
    "amtacctdr" NUMERIC DEFAULT 0 NOT NULL,
    "amtacctcr" NUMERIC DEFAULT 0 NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "createdby" INTEGER DEFAULT 0 NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER DEFAULT 0 NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_subacct_id" INTEGER,
    "userelement1_id" INTEGER,
    "userelement2_id" INTEGER,
    "c_projectphase_id" INTEGER,
    "c_projecttask_id" INTEGER
);

CREATE UNIQUE INDEX "fact_acct_balance_akey" ON "fact_acct_balance" ("ad_client_id", "ad_org_id", "c_acctschema_id", "dateacct", "account_id", "postingtype", "m_product_id", "c_bpartner_id", "c_project_id", "ad_orgtrx_id", "c_salesregion_id", "c_activity_id", "c_campaign_id", "c_locto_id", "c_locfrom_id", "user1_id", "user2_id", "gl_budget_id");

-- ----------------------------------------------------------------------- 
-- GL_BUDGET 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_budget"
(
    "gl_budget_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isprimary" CHAR(1) DEFAULT 'Y' NOT NULL,
    "budgetstatus" CHAR(1),
    PRIMARY KEY ("gl_budget_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPrimary in ('Y','N'))
);

CREATE UNIQUE INDEX "gl_budget_name" ON "gl_budget" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- GL_BUDGETCONTROL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_budgetcontrol"
(
    "gl_budgetcontrol_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_acctschema_id" INTEGER NOT NULL,
    "gl_budget_id" INTEGER NOT NULL,
    "commitmenttype" CHAR(1) NOT NULL,
    "isbeforeapproval" CHAR(1) DEFAULT 'N' NOT NULL,
    "budgetcontrolscope" CHAR(1) NOT NULL,
    PRIMARY KEY ("gl_budgetcontrol_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsBeforeApproval in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- GL_CATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_category"
(
    "gl_category_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "categorytype" CHAR(1) NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "docbasetype" CHAR(3),
    PRIMARY KEY ("gl_category_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "gl_category_name" ON "gl_category" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- GL_DISTRIBUTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_distribution"
(
    "gl_distribution_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "postingtype" CHAR(1),
    "c_doctype_id" INTEGER,
    "c_acctschema_id" INTEGER NOT NULL,
    "anyorg" CHAR(1) DEFAULT 'Y' NOT NULL,
    "org_id" INTEGER,
    "anyacct" CHAR(1) DEFAULT 'Y' NOT NULL,
    "account_id" INTEGER,
    "anyproduct" CHAR(1) DEFAULT 'Y' NOT NULL,
    "m_product_id" INTEGER,
    "anybpartner" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_bpartner_id" INTEGER,
    "anyproject" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_project_id" INTEGER,
    "anycampaign" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_campaign_id" INTEGER,
    "anyactivity" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_activity_id" INTEGER,
    "anysalesregion" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_salesregion_id" INTEGER,
    "anyorgtrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ad_orgtrx_id" INTEGER,
    "anylocto" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_locto_id" INTEGER,
    "anylocfrom" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_locfrom_id" INTEGER,
    "anyuser1" CHAR(1) DEFAULT 'Y' NOT NULL,
    "user1_id" INTEGER,
    "anyuser2" CHAR(1) DEFAULT 'Y' NOT NULL,
    "user2_id" INTEGER,
    "percenttotal" NUMERIC NOT NULL,
    "isvalid" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("gl_distribution_id"),
    CHECK (AnySalesRegion in ('Y','N')),
    CHECK (AnyOrgTrx in ('Y','N')),
    CHECK (AnyLocTo in ('Y','N')),
    CHECK (AnyLocFrom in ('Y','N')),
    CHECK (AnyUser1 in ('Y','N')),
    CHECK (AnyUser2 in ('Y','N')),
    CHECK (IsValid in ('Y','N')),
    CHECK (IsActive in ('Y','N')),
    CHECK (AnyOrg in ('Y','N')),
    CHECK (AnyAcct in ('Y','N')),
    CHECK (AnyProduct in ('Y','N')),
    CHECK (AnyBPartner in ('Y','N')),
    CHECK (AnyProject in ('Y','N')),
    CHECK (AnyCampaign in ('Y','N')),
    CHECK (AnyActivity in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- GL_DISTRIBUTIONLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_distributionline"
(
    "gl_distributionline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "gl_distribution_id" INTEGER NOT NULL,
    "line" INTEGER DEFAULT 0 NOT NULL,
    "percent" NUMERIC NOT NULL,
    "description" VARCHAR(255),
    "overwriteorg" CHAR(1) DEFAULT 'N' NOT NULL,
    "org_id" INTEGER,
    "overwriteacct" CHAR(1) DEFAULT 'N' NOT NULL,
    "account_id" INTEGER,
    "overwriteproduct" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_product_id" INTEGER,
    "overwritebpartner" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartner_id" INTEGER,
    "overwriteproject" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_project_id" INTEGER,
    "overwritecampaign" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_campaign_id" INTEGER,
    "overwriteactivity" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_activity_id" INTEGER,
    "overwritesalesregion" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_salesregion_id" INTEGER,
    "overwriteorgtrx" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_orgtrx_id" INTEGER,
    "overwritelocto" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_locto_id" INTEGER,
    "overwritelocfrom" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_locfrom_id" INTEGER,
    "overwriteuser1" CHAR(1) DEFAULT 'N' NOT NULL,
    "user1_id" INTEGER,
    "overwriteuser2" CHAR(1) DEFAULT 'N' NOT NULL,
    "user2_id" INTEGER,
    PRIMARY KEY ("gl_distributionline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (OverwriteOrg in ('Y','N')),
    CHECK (OverwriteAcct in ('Y','N')),
    CHECK (OverwriteProduct in ('Y','N')),
    CHECK (OverwriteBPartner in ('Y','N')),
    CHECK (OverwriteProject in ('Y','N')),
    CHECK (OverwriteCampaign in ('Y','N')),
    CHECK (OverwriteActivity in ('Y','N')),
    CHECK (OverwriteSalesRegion in ('Y','N')),
    CHECK (OverwriteOrgTrx in ('Y','N')),
    CHECK (OverwriteLocTo in ('Y','N')),
    CHECK (OverwriteLocFrom in ('Y','N')),
    CHECK (OverwriteUser1 in ('Y','N')),
    CHECK (OverwriteUser2 in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- GL_FUND 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_fund"
(
    "gl_fund_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_acctschema_id" INTEGER NOT NULL,
    "amt" NUMERIC DEFAULT 0 NOT NULL,
    "datefrom" TIMESTAMP,
    "dateto" TIMESTAMP,
    PRIMARY KEY ("gl_fund_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- GL_FUNDRESTRICTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_fundrestriction"
(
    "gl_fundrestriction_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "gl_fund_id" INTEGER NOT NULL,
    "c_elementvalue_id" INTEGER NOT NULL,
    PRIMARY KEY ("gl_fundrestriction_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- GL_JOURNAL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_journal"
(
    "gl_journal_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "c_doctype_id" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "isprinted" CHAR(1) DEFAULT 'N' NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "postingtype" CHAR(1) NOT NULL,
    "gl_budget_id" INTEGER,
    "gl_category_id" INTEGER NOT NULL,
    "datedoc" TIMESTAMP NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "c_period_id" INTEGER NOT NULL,
    "c_currency_id" INTEGER,
    "currencyrate" NUMERIC DEFAULT 0 NOT NULL,
    "gl_journalbatch_id" INTEGER,
    "totaldr" NUMERIC DEFAULT 0 NOT NULL,
    "totalcr" NUMERIC DEFAULT 0 NOT NULL,
    "controlamt" NUMERIC DEFAULT 0,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_conversiontype_id" INTEGER NOT NULL,
    PRIMARY KEY ("gl_journal_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (IsPrinted in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE UNIQUE INDEX "gl_journal_docno" ON "gl_journal" ("ad_org_id", "c_period_id", "documentno");

-- ----------------------------------------------------------------------- 
-- GL_JOURNALBATCH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_journalbatch"
(
    "gl_journalbatch_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "postingtype" CHAR(1) NOT NULL,
    "gl_category_id" INTEGER,
    "datedoc" TIMESTAMP,
    "dateacct" TIMESTAMP,
    "c_period_id" INTEGER,
    "c_currency_id" INTEGER,
    "totaldr" NUMERIC DEFAULT 0 NOT NULL,
    "totalcr" NUMERIC DEFAULT 0 NOT NULL,
    "controlamt" NUMERIC DEFAULT 0,
    "processing" CHAR(1) DEFAULT 'N' NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "copyfrom" CHAR(1),
    "c_doctype_id" INTEGER NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "isapproved" CHAR(1),
    PRIMARY KEY ("gl_journalbatch_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processing in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE UNIQUE INDEX "gl_journalbatch_docno" ON "gl_journalbatch" ("ad_org_id", "c_period_id", "documentno");

-- ----------------------------------------------------------------------- 
-- GL_JOURNALLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "gl_journalline"
(
    "gl_journalline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "gl_journal_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "isgenerated" CHAR(1) DEFAULT 'N' NOT NULL,
    "description" VARCHAR(255),
    "amtsourcedr" NUMERIC DEFAULT 0 NOT NULL,
    "amtsourcecr" NUMERIC DEFAULT 0 NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "currencyrate" NUMERIC DEFAULT 0 NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "amtacctdr" NUMERIC DEFAULT 0 NOT NULL,
    "amtacctcr" NUMERIC DEFAULT 0 NOT NULL,
    "c_uom_id" INTEGER,
    "qty" NUMERIC DEFAULT 0,
    "c_validcombination_id" INTEGER NOT NULL,
    "c_conversiontype_id" INTEGER NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("gl_journalline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsGenerated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_BANKSTATEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_bankstatement"
(
    "i_bankstatement_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "processed" CHAR(1) DEFAULT 'N',
    "c_bankstatement_id" INTEGER,
    "statementdate" TIMESTAMP,
    "description" VARCHAR(255),
    "c_bankaccount_id" INTEGER,
    "routingno" VARCHAR(20),
    "bankaccountno" VARCHAR(20),
    "c_payment_id" INTEGER,
    "paymentdocumentno" VARCHAR(30),
    "c_currency_id" INTEGER,
    "iso_code" CHAR(3),
    "c_bpartner_id" INTEGER,
    "name" VARCHAR(60),
    "bpartnervalue" VARCHAR(40),
    "c_invoice_id" INTEGER,
    "invoicedocumentno" VARCHAR(30),
    "c_charge_id" INTEGER,
    "chargename" VARCHAR(60),
    "chargeamt" NUMERIC DEFAULT 0,
    "c_bankstatementline_id" INTEGER,
    "line" INTEGER,
    "dateacct" TIMESTAMP,
    "valutadate" TIMESTAMP,
    "statementlinedate" TIMESTAMP,
    "trxtype" VARCHAR(20),
    "referenceno" VARCHAR(40),
    "memo" VARCHAR(255),
    "isreversal" CHAR(1) DEFAULT 'N',
    "interestamt" NUMERIC DEFAULT 0,
    "trxamt" NUMERIC DEFAULT 0,
    "linedescription" VARCHAR(255),
    "stmtamt" NUMERIC DEFAULT 0,
    "eftstatementdate" TIMESTAMP,
    "eftstatementreference" VARCHAR(60),
    "eftstatementlinedate" TIMESTAMP,
    "eftvalutadate" TIMESTAMP,
    "eftreference" VARCHAR(60),
    "eftcheckno" VARCHAR(20),
    "efttrxid" VARCHAR(40),
    "efttrxtype" VARCHAR(20),
    "eftmemo" VARCHAR(2000),
    "eftpayee" VARCHAR(255),
    "eftpayeeaccount" VARCHAR(40),
    "eftamt" NUMERIC DEFAULT 0,
    "eftcurrency" VARCHAR(20),
    "createpayment" CHAR(1),
    "processing" CHAR(1),
    "matchstatement" CHAR(1),
    PRIMARY KEY ("i_bankstatement_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsReversal in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_BPARTNER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_bpartner"
(
    "i_bpartner_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "c_bpartner_id" INTEGER,
    "value" VARCHAR(40),
    "name" VARCHAR(60),
    "name2" VARCHAR(60),
    "description" VARCHAR(255),
    "duns" CHAR(11),
    "taxid" VARCHAR(20),
    "naics" CHAR(6),
    "groupvalue" VARCHAR(40),
    "c_bp_group_id" INTEGER,
    "c_bpartner_location_id" INTEGER,
    "address1" VARCHAR(60),
    "address2" VARCHAR(60),
    "postal" VARCHAR(10),
    "postal_add" VARCHAR(10),
    "city" VARCHAR(60),
    "c_region_id" INTEGER,
    "regionname" VARCHAR(60),
    "c_country_id" INTEGER,
    "countrycode" CHAR(2),
    "title" VARCHAR(40),
    "contactname" VARCHAR(60),
    "contactdescription" VARCHAR(255),
    "comments" VARCHAR(2000),
    "phone" VARCHAR(40),
    "phone2" VARCHAR(40),
    "fax" VARCHAR(40),
    "email" VARCHAR(60),
    "password" VARCHAR(20),
    "birthday" TIMESTAMP,
    "c_greeting_id" INTEGER,
    "bpcontactgreeting" VARCHAR(60),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    "ad_user_id" INTEGER,
    "r_interestarea_id" INTEGER,
    "interestareaname" VARCHAR(40),
    PRIMARY KEY ("i_bpartner_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_CONVERSION_RATE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_conversion_rate"
(
    "i_conversion_rate_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "c_conversion_rate_id" INTEGER,
    "iso_code" CHAR(3),
    "c_currency_id" INTEGER,
    "iso_code_to" CHAR(3),
    "c_currency_id_to" INTEGER,
    "conversiontypevalue" VARCHAR(40),
    "c_conversiontype_id" INTEGER,
    "validfrom" TIMESTAMP,
    "validto" TIMESTAMP,
    "multiplyrate" NUMERIC,
    "dividerate" NUMERIC,
    "createreciprocalrate" CHAR(1) DEFAULT 'N',
    "i_isimported" CHAR(1) DEFAULT 'N',
    "i_errormsg" VARCHAR(2000),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("i_conversion_rate_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (CreateReciprocalRate in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_ELEMENTVALUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_elementvalue"
(
    "i_elementvalue_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "createdby" INTEGER,
    "created" TIMESTAMP DEFAULT NOW(),
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "c_element_id" INTEGER,
    "elementname" VARCHAR(60),
    "c_elementvalue_id" INTEGER,
    "value" VARCHAR(40),
    "name" VARCHAR(60),
    "description" VARCHAR(255),
    "accounttype" CHAR(1),
    "accountsign" CHAR(1),
    "isdoccontrolled" CHAR(1) DEFAULT 'N',
    "issummary" CHAR(1) DEFAULT 'N',
    "parentvalue" VARCHAR(40),
    "parentelementvalue_id" INTEGER,
    "postactual" CHAR(1) DEFAULT 'Y',
    "postbudget" CHAR(1) DEFAULT 'Y',
    "poststatistical" CHAR(1) DEFAULT 'Y',
    "postencumbrance" CHAR(1) DEFAULT 'Y',
    "default_account" VARCHAR(30),
    "ad_column_id" INTEGER,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("i_elementvalue_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDocControlled in ('Y','N')),
    CHECK (PostActual in ('Y','N')),
    CHECK (PostBudget in ('Y','N')),
    CHECK (PostStatistical in ('Y','N')),
    CHECK (PostEncumbrance in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_GLJOURNAL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_gljournal"
(
    "i_gljournal_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "clientvalue" VARCHAR(40),
    "ad_orgdoc_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    "gl_journalbatch_id" INTEGER,
    "batchdocumentno" VARCHAR(30),
    "batchdescription" VARCHAR(255),
    "gl_journal_id" INTEGER,
    "journaldocumentno" VARCHAR(30),
    "postingtype" CHAR(1),
    "c_acctschema_id" INTEGER,
    "acctschemaname" VARCHAR(60),
    "c_doctype_id" INTEGER,
    "doctypename" VARCHAR(60),
    "gl_category_id" INTEGER,
    "categoryname" VARCHAR(60),
    "c_period_id" INTEGER,
    "gl_budget_id" INTEGER,
    "gl_journalline_id" INTEGER,
    "line" INTEGER,
    "dateacct" TIMESTAMP,
    "description" VARCHAR(255),
    "amtsourcedr" NUMERIC DEFAULT 0,
    "amtacctdr" NUMERIC DEFAULT 0,
    "amtsourcecr" NUMERIC DEFAULT 0,
    "amtacctcr" NUMERIC DEFAULT 0,
    "c_currency_id" INTEGER,
    "iso_code" CHAR(3),
    "conversiontypevalue" VARCHAR(40),
    "c_conversiontype_id" INTEGER,
    "currencyrate" NUMERIC DEFAULT 0,
    "c_uom_id" INTEGER,
    "qty" NUMERIC DEFAULT 0,
    "c_validcombination_id" INTEGER,
    "orgvalue" VARCHAR(40),
    "ad_org_id" INTEGER,
    "account_id" INTEGER,
    "accountvalue" VARCHAR(40),
    "ad_orgtrx_id" INTEGER,
    "orgtrxvalue" VARCHAR(40),
    "m_product_id" INTEGER,
    "productvalue" VARCHAR(40),
    "upc" VARCHAR(30),
    "sku" VARCHAR(30),
    "c_bpartner_id" INTEGER,
    "bpartnervalue" VARCHAR(40),
    "c_project_id" INTEGER,
    "projectvalue" VARCHAR(40),
    "c_locto_id" INTEGER,
    "c_locfrom_id" INTEGER,
    "c_salesregion_id" INTEGER,
    "c_activity_id" INTEGER,
    "c_campaign_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "iscreatenewbatch" CHAR(1),
    "iscreatenewjournal" CHAR(1),
    PRIMARY KEY ("i_gljournal_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_INOUTLINECONFIRM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_inoutlineconfirm"
(
    "i_inoutlineconfirm_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "m_inoutlineconfirm_id" INTEGER,
    "confirmationno" VARCHAR(20),
    "description" VARCHAR(255),
    "confirmedqty" NUMERIC DEFAULT 0,
    "scrappedqty" NUMERIC DEFAULT 0,
    "differenceqty" NUMERIC DEFAULT 0,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("i_inoutlineconfirm_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_INVENTORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_inventory"
(
    "i_inventory_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "m_product_id" INTEGER,
    "upc" VARCHAR(30),
    "value" VARCHAR(40),
    "lot" VARCHAR(20),
    "serno" VARCHAR(20),
    "m_locator_id" INTEGER,
    "m_warehouse_id" INTEGER,
    "warehousevalue" VARCHAR(40),
    "locatorvalue" VARCHAR(40),
    "x" VARCHAR(60),
    "y" VARCHAR(60),
    "z" VARCHAR(60),
    "m_inventory_id" INTEGER,
    "m_inventoryline_id" INTEGER,
    "qtybook" NUMERIC DEFAULT 0,
    "qtycount" NUMERIC DEFAULT 0,
    "movementdate" TIMESTAMP,
    "description" VARCHAR(255),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("i_inventory_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_INVOICE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_invoice"
(
    "i_invoice_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    "m_pricelist_id" INTEGER,
    "c_currency_id" INTEGER,
    "salesrep_id" INTEGER,
    "issotrx" CHAR(1) DEFAULT 'Y',
    "c_bpartner_id" INTEGER,
    "c_bpartner_location_id" INTEGER,
    "bpartnervalue" VARCHAR(40),
    "name" VARCHAR(60),
    "c_location_id" INTEGER,
    "address1" VARCHAR(60),
    "address2" VARCHAR(60),
    "postal" VARCHAR(10),
    "city" VARCHAR(60),
    "c_region_id" INTEGER,
    "regionname" VARCHAR(60),
    "ad_user_id" INTEGER,
    "email" VARCHAR(60),
    "contactname" VARCHAR(60),
    "phone" VARCHAR(40),
    "c_country_id" INTEGER,
    "countrycode" CHAR(2),
    "c_doctype_id" INTEGER,
    "doctypename" VARCHAR(60),
    "c_paymentterm_id" INTEGER,
    "paymenttermvalue" VARCHAR(40),
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "c_invoice_id" INTEGER,
    "documentno" VARCHAR(30),
    "dateinvoiced" TIMESTAMP,
    "dateacct" TIMESTAMP,
    "description" VARCHAR(255),
    "m_product_id" INTEGER,
    "productvalue" VARCHAR(40),
    "upc" VARCHAR(30),
    "sku" VARCHAR(30),
    "c_tax_id" INTEGER,
    "taxindicator" VARCHAR(5),
    "taxamt" NUMERIC DEFAULT 0,
    "c_invoiceline_id" INTEGER,
    "linedescription" VARCHAR(255),
    "qtyordered" NUMERIC DEFAULT 0,
    "priceactual" NUMERIC DEFAULT 0,
    PRIMARY KEY ("i_invoice_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_ORDER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_order"
(
    "i_order_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    "salesrep_id" INTEGER,
    "m_warehouse_id" INTEGER,
    "m_pricelist_id" INTEGER,
    "c_currency_id" INTEGER,
    "m_shipper_id" INTEGER,
    "issotrx" CHAR(1) DEFAULT 'Y',
    "c_bpartner_id" INTEGER,
    "bpartnervalue" VARCHAR(40),
    "name" VARCHAR(60),
    "c_bpartner_location_id" INTEGER,
    "billto_id" INTEGER,
    "c_location_id" INTEGER,
    "address1" VARCHAR(60),
    "address2" VARCHAR(60),
    "postal" VARCHAR(10),
    "city" VARCHAR(60),
    "c_region_id" INTEGER,
    "regionname" VARCHAR(60),
    "c_country_id" INTEGER,
    "countrycode" CHAR(2),
    "ad_user_id" INTEGER,
    "contactname" VARCHAR(60),
    "email" VARCHAR(60),
    "phone" VARCHAR(40),
    "c_project_id" INTEGER,
    "c_activity_id" INTEGER,
    "c_doctype_id" INTEGER,
    "doctypename" VARCHAR(60),
    "c_paymentterm_id" INTEGER,
    "paymenttermvalue" VARCHAR(40),
    "c_order_id" INTEGER,
    "documentno" VARCHAR(30),
    "dateordered" TIMESTAMP,
    "dateacct" TIMESTAMP,
    "description" VARCHAR(255),
    "m_product_id" INTEGER,
    "productvalue" VARCHAR(40),
    "upc" VARCHAR(30),
    "sku" VARCHAR(30),
    "c_tax_id" INTEGER,
    "taxindicator" VARCHAR(5),
    "taxamt" NUMERIC DEFAULT 0,
    "c_orderline_id" INTEGER,
    "linedescription" VARCHAR(255),
    "c_uom_id" INTEGER,
    "qtyordered" NUMERIC DEFAULT 0,
    "priceactual" NUMERIC DEFAULT 0,
    "freightamt" NUMERIC DEFAULT 0,
    "c_campaign_id" INTEGER,
    PRIMARY KEY ("i_order_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_PAYMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_payment"
(
    "i_payment_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "processed" CHAR(1) DEFAULT 'N',
    "c_payment_id" INTEGER,
    "documentno" VARCHAR(30),
    "datetrx" TIMESTAMP,
    "isreceipt" CHAR(1) DEFAULT 'Y',
    "c_doctype_id" INTEGER,
    "doctypename" VARCHAR(60),
    "trxtype" CHAR(1),
    "c_bankaccount_id" INTEGER,
    "bankaccountno" VARCHAR(20),
    "c_bpartner_id" INTEGER,
    "bpartnervalue" VARCHAR(40),
    "c_invoice_id" INTEGER,
    "invoicedocumentno" VARCHAR(30),
    "tendertype" CHAR(1),
    "creditcardtype" CHAR(1),
    "creditcardnumber" VARCHAR(20),
    "creditcardvv" VARCHAR(4),
    "creditcardexpmm" INTEGER,
    "creditcardexpyy" INTEGER,
    "micr" VARCHAR(20),
    "routingno" VARCHAR(20),
    "accountno" VARCHAR(20),
    "checkno" VARCHAR(20),
    "a_name" VARCHAR(60),
    "a_street" VARCHAR(60),
    "a_city" VARCHAR(60),
    "a_state" VARCHAR(40),
    "a_zip" VARCHAR(20),
    "a_country" VARCHAR(40),
    "a_ident_dl" VARCHAR(20),
    "a_ident_ssn" VARCHAR(20),
    "a_email" VARCHAR(60),
    "voiceauthcode" VARCHAR(20),
    "swipe" VARCHAR(80),
    "orig_trxid" VARCHAR(20),
    "ponum" VARCHAR(60),
    "c_currency_id" INTEGER,
    "payamt" NUMERIC DEFAULT 0,
    "discountamt" NUMERIC DEFAULT 0,
    "writeoffamt" NUMERIC DEFAULT 0,
    "isoverunderpayment" CHAR(1) DEFAULT 'N',
    "overunderamt" NUMERIC DEFAULT 0,
    "c_charge_id" INTEGER,
    "chargename" VARCHAR(60),
    "chargeamt" NUMERIC DEFAULT 0,
    "taxamt" NUMERIC DEFAULT 0,
    "isapproved" CHAR(1) DEFAULT 'N',
    "isselfservice" CHAR(1) DEFAULT 'N',
    "isdelayedcapture" CHAR(1) DEFAULT 'N',
    "r_pnref" VARCHAR(20),
    "r_result" VARCHAR(20),
    "r_respmsg" VARCHAR(60),
    "r_authcode" VARCHAR(20),
    "r_info" VARCHAR(2000),
    "processing" CHAR(1),
    "iso_code" CHAR(3),
    "dateacct" TIMESTAMP,
    PRIMARY KEY ("i_payment_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsReceipt in ('Y','N')),
    CHECK (IsOverUnderPayment in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (IsSelfService in ('Y','N')),
    CHECK (IsDelayedCapture in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_PRODUCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_product"
(
    "i_product_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "m_product_id" INTEGER,
    "value" VARCHAR(40),
    "name" VARCHAR(60),
    "description" VARCHAR(255),
    "documentnote" VARCHAR(2000),
    "help" VARCHAR(2000),
    "upc" VARCHAR(30),
    "sku" VARCHAR(30),
    "x12de355" CHAR(2),
    "c_uom_id" INTEGER,
    "productcategory_value" VARCHAR(40),
    "m_product_category_id" INTEGER,
    "producttype" CHAR(1) DEFAULT 'I',
    "classification" CHAR(1),
    "volume" NUMERIC DEFAULT 0,
    "weight" NUMERIC DEFAULT 0,
    "shelfwidth" INTEGER,
    "shelfheight" INTEGER,
    "shelfdepth" INTEGER,
    "unitsperpallet" INTEGER,
    "discontinued" CHAR(1) DEFAULT 'N',
    "discontinuedby" TIMESTAMP,
    "imageurl" VARCHAR(120),
    "descriptionurl" VARCHAR(120),
    "bpartner_value" VARCHAR(40),
    "c_bpartner_id" INTEGER,
    "iso_code" CHAR(3),
    "c_currency_id" INTEGER,
    "pricelist" NUMERIC DEFAULT 0,
    "pricepo" NUMERIC DEFAULT 0,
    "royaltyamt" NUMERIC DEFAULT 0,
    "priceeffective" TIMESTAMP,
    "vendorproductno" VARCHAR(30),
    "vendorcategory" VARCHAR(30),
    "manufacturer" VARCHAR(30),
    "order_min" NUMERIC DEFAULT 0,
    "order_pack" NUMERIC DEFAULT 0,
    "costperorder" NUMERIC DEFAULT 0,
    "deliverytime_promised" INTEGER,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    "pricestd" NUMERIC,
    "pricelimit" NUMERIC,
    PRIMARY KEY ("i_product_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Discontinued in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- I_REPORTLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "i_reportline"
(
    "i_reportline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y',
    "created" TIMESTAMP DEFAULT NOW(),
    "createdby" INTEGER,
    "updated" TIMESTAMP DEFAULT NOW(),
    "updatedby" INTEGER,
    "i_isimported" CHAR(1) DEFAULT 'N' NOT NULL,
    "i_errormsg" VARCHAR(2000),
    "reportlinesetname" VARCHAR(60),
    "pa_reportlineset_id" INTEGER,
    "name" VARCHAR(60),
    "pa_reportline_id" INTEGER,
    "description" VARCHAR(255),
    "seqno" INTEGER,
    "issummary" CHAR(1) DEFAULT 'N',
    "isprinted" CHAR(1) DEFAULT 'Y',
    "linetype" CHAR(1),
    "calculationtype" CHAR(1),
    "amounttype" CHAR(2),
    "postingtype" CHAR(1),
    "pa_reportsource_id" INTEGER,
    "c_elementvalue_id" INTEGER,
    "elementvalue" VARCHAR(40),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N',
    PRIMARY KEY ("i_reportline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPrinted in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_CATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_category"
(
    "k_category_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("k_category_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_CATEGORYVALUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_categoryvalue"
(
    "k_categoryvalue_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "k_category_id" INTEGER NOT NULL,
    PRIMARY KEY ("k_categoryvalue_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_COMMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_comment"
(
    "k_comment_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "k_entry_id" INTEGER NOT NULL,
    "rating" INTEGER NOT NULL,
    "ispublic" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ad_session_id" INTEGER,
    "textmsg" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("k_comment_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPublic in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_ENTRY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_entry"
(
    "k_entry_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "k_topic_id" INTEGER NOT NULL,
    "rating" INTEGER NOT NULL,
    "ispublic" CHAR(1) DEFAULT 'Y' NOT NULL,
    "keywords" VARCHAR(255),
    "k_source_id" INTEGER,
    "descriptionurl" VARCHAR(120),
    "validto" TIMESTAMP,
    "ad_session_id" INTEGER,
    "textmsg" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("k_entry_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPublic in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_ENTRYCATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_entrycategory"
(
    "k_category_id" INTEGER NOT NULL,
    "k_entry_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "k_categoryvalue_id" INTEGER NOT NULL,
    PRIMARY KEY ("k_category_id", "k_entry_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_ENTRYRELATED 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_entryrelated"
(
    "k_entry_id" INTEGER NOT NULL,
    "k_entryrelated_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60),
    PRIMARY KEY ("k_entry_id", "k_entryrelated_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_INDEX 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_index"
(
    "k_index_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "keyword" VARCHAR(255) NOT NULL,
    "excerpt" VARCHAR(2000),
    "ad_table_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "sourceupdated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "cm_webproject_id" INTEGER,
    "r_requesttype_id" INTEGER,
    "c_doctype_id" INTEGER,
    PRIMARY KEY ("k_index_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "k_index_keyword" ON "k_index" ("ad_client_id", "keyword");

-- ----------------------------------------------------------------------- 
-- K_INDEXLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_indexlog"
(
    "k_indexlog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "indexquery" VARCHAR(255) NOT NULL,
    "indexqueryresult" INTEGER DEFAULT 0 NOT NULL,
    "querysource" CHAR(1) NOT NULL,
    PRIMARY KEY ("k_indexlog_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_INDEXSTOP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_indexstop"
(
    "k_indexstop_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "keyword" VARCHAR(255) NOT NULL,
    "ismanual" CHAR(1) DEFAULT 'Y' NOT NULL,
    "cm_webproject_id" INTEGER,
    "r_requesttype_id" INTEGER,
    "c_doctype_id" INTEGER,
    PRIMARY KEY ("k_indexstop_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsManual in ('Y','N'))
);

CREATE INDEX "k_indexstop_keyword" ON "k_indexstop" ("ad_client_id", "keyword");

-- ----------------------------------------------------------------------- 
-- K_SOURCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_source"
(
    "k_source_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "descriptionurl" VARCHAR(120),
    PRIMARY KEY ("k_source_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_SYNONYM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_synonym"
(
    "k_synonym_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "synonymname" VARCHAR(60) NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    PRIMARY KEY ("k_synonym_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_TOPIC 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_topic"
(
    "k_topic_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "k_type_id" INTEGER NOT NULL,
    "ispublic" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ispublicwrite" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("k_topic_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPublic in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- K_TYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "k_type"
(
    "k_type_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ispublic" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispublicwrite" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("k_type_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPublic in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_ATTRIBUTE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_attribute"
(
    "m_attribute_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ismandatory" CHAR(1) DEFAULT 'N' NOT NULL,
    "isinstanceattribute" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_attributesearch_id" INTEGER,
    "attributevaluetype" CHAR(1) DEFAULT 'S' NOT NULL,
    PRIMARY KEY ("m_attribute_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsMandatory in ('Y','N')),
    CHECK (IsInstanceAttribute in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_ATTRIBUTEINSTANCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_attributeinstance"
(
    "m_attributesetinstance_id" INTEGER DEFAULT 0 NOT NULL,
    "m_attribute_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_attributevalue_id" INTEGER,
    "value" VARCHAR(40),
    "valuenumber" NUMERIC,
    PRIMARY KEY ("m_attributesetinstance_id", "m_attribute_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_ATTRIBUTESEARCH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_attributesearch"
(
    "m_attributesearch_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("m_attributesearch_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_ATTRIBUTESET 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_attributeset"
(
    "m_attributeset_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isserno" CHAR(1) DEFAULT 'Y' NOT NULL,
    "m_sernoctl_id" INTEGER,
    "islot" CHAR(1) DEFAULT 'Y' NOT NULL,
    "m_lotctl_id" INTEGER,
    "isguaranteedate" CHAR(1) DEFAULT 'N' NOT NULL,
    "guaranteedays" INTEGER,
    "isinstanceattribute" CHAR(1) DEFAULT 'N' NOT NULL,
    "mandatorytype" CHAR(1) DEFAULT 'N' NOT NULL,
    "isguaranteedatemandatory" CHAR(1) DEFAULT 'N' NOT NULL,
    "islotmandatory" CHAR(1) DEFAULT 'N' NOT NULL,
    "issernomandatory" CHAR(1) DEFAULT 'N' NOT NULL,
    "sernocharsoverwrite" CHAR(1),
    "lotcharsoverwrite" CHAR(1),
    "lotchareoverwrite" CHAR(1),
    "sernochareoverwrite" CHAR(1),
    PRIMARY KEY ("m_attributeset_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSerNo in ('Y','N')),
    CHECK (IsLot in ('Y','N')),
    CHECK (IsGuaranteeDate in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_ATTRIBUTESETEXCLUDE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_attributesetexclude"
(
    "m_attributesetexclude_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_attributeset_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("m_attributesetexclude_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_ATTRIBUTESETINSTANCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_attributesetinstance"
(
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_attributeset_id" INTEGER,
    "serno" VARCHAR(40),
    "lot" VARCHAR(40),
    "guaranteedate" TIMESTAMP,
    "description" VARCHAR(255),
    "m_lot_id" INTEGER,
    PRIMARY KEY ("m_attributesetinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_ATTRIBUTEUSE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_attributeuse"
(
    "m_attribute_id" INTEGER NOT NULL,
    "m_attributeset_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    PRIMARY KEY ("m_attribute_id", "m_attributeset_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_ATTRIBUTEVALUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_attributevalue"
(
    "m_attributevalue_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_attribute_id" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("m_attributevalue_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_BOM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_bom"
(
    "m_bom_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_changenotice_id" INTEGER,
    "bomtype" CHAR(1) NOT NULL,
    "bomuse" CHAR(1) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "processing" CHAR(1),
    PRIMARY KEY ("m_bom_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "m_bom_productversiontype" ON "m_bom" ("m_product_id", "m_changenotice_id");

-- ----------------------------------------------------------------------- 
-- M_BOMALTERNATIVE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_bomalternative"
(
    "m_bomalternative_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "m_product_id" INTEGER NOT NULL,
    PRIMARY KEY ("m_bomalternative_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_BOMPRODUCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_bomproduct"
(
    "m_bomproduct_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "line" INTEGER DEFAULT 0 NOT NULL,
    "m_bom_id" INTEGER NOT NULL,
    "bomproducttype" CHAR(1) NOT NULL,
    "isphantom" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_productbom_id" INTEGER,
    "m_changenotice_id" INTEGER,
    "m_attributesetinstance_id" INTEGER,
    "m_bomalternative_id" INTEGER,
    "bomqty" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "m_productoperation_id" INTEGER,
    "seqno" INTEGER DEFAULT 0,
    "leadtimeoffset" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_bomproduct_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPhantom in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_CHANGENOTICE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_changenotice"
(
    "m_changenotice_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "detailinfo" TEXT,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_changenotice_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_CHANGEREQUEST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_changerequest"
(
    "m_changerequest_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_bom_id" INTEGER NOT NULL,
    "m_changenotice_id" INTEGER,
    "documentno" VARCHAR(30) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "detailinfo" TEXT,
    "m_fixchangenotice_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_changerequest_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_COST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_cost"
(
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_costtype_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "m_costelement_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "currentcostprice" NUMERIC NOT NULL,
    "currentqty" NUMERIC DEFAULT 0 NOT NULL,
    "cumulatedamt" NUMERIC DEFAULT 0,
    "cumulatedqty" NUMERIC DEFAULT 0,
    "futurecostprice" NUMERIC,
    "description" VARCHAR(255),
    "percent" INTEGER DEFAULT 0,
    PRIMARY KEY ("ad_client_id", "ad_org_id", "m_product_id", "m_costtype_id", "c_acctschema_id", "m_costelement_id", "m_attributesetinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_COSTDETAIL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_costdetail"
(
    "m_costdetail_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_costelement_id" INTEGER,
    "c_orderline_id" INTEGER,
    "m_inoutline_id" INTEGER,
    "c_invoiceline_id" INTEGER,
    "m_movementline_id" INTEGER,
    "m_inventoryline_id" INTEGER,
    "m_productionline_id" INTEGER,
    "c_projectissue_id" INTEGER,
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "amt" NUMERIC DEFAULT 0 NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "deltaamt" NUMERIC DEFAULT 0,
    "deltaqty" NUMERIC DEFAULT 0,
    "description" VARCHAR(255),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_costdetail_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE INDEX "m_costdetail_product" ON "m_costdetail" ("ad_org_id", "m_product_id", "m_attributesetinstance_id");

-- ----------------------------------------------------------------------- 
-- M_COSTELEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_costelement"
(
    "m_costelement_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "costelementtype" CHAR(1) NOT NULL,
    "costingmethod" CHAR(1),
    "iscalculated" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_costelement_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCalculated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_COSTQUEUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_costqueue"
(
    "m_costqueue_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_costtype_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "m_costelement_id" INTEGER NOT NULL,
    "currentcostprice" NUMERIC DEFAULT 0 NOT NULL,
    "currentqty" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_costqueue_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "m_costqueue_product" ON "m_costqueue" ("c_acctschema_id", "m_product_id", "m_costelement_id");

-- ----------------------------------------------------------------------- 
-- M_COSTTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_costtype"
(
    "m_costtype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("m_costtype_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DEMAND 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_demand"
(
    "m_demand_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_calendar_id" INTEGER NOT NULL,
    "c_year_id" INTEGER NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("m_demand_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDefault in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DEMANDDETAIL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_demanddetail"
(
    "m_demanddetail_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_demandline_id" INTEGER NOT NULL,
    "m_forecastline_id" INTEGER,
    "m_requisitionline_id" INTEGER,
    "c_orderline_id" INTEGER,
    PRIMARY KEY ("m_demanddetail_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DEMANDLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_demandline"
(
    "m_demandline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_demand_id" INTEGER NOT NULL,
    "c_period_id" INTEGER NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "qtycalculated" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_demandline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DISCOUNTSCHEMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_discountschema"
(
    "m_discountschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "validfrom" TIMESTAMP NOT NULL,
    "discounttype" CHAR(1) NOT NULL,
    "script" VARCHAR(2000),
    "flatdiscount" NUMERIC,
    "isquantitybased" CHAR(1) DEFAULT 'Y' NOT NULL,
    "cumulativelevel" CHAR(1),
    "processing" CHAR(1),
    "isbpartnerflatdiscount" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_discountschema_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsQuantityBased in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DISCOUNTSCHEMABREAK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_discountschemabreak"
(
    "m_discountschemabreak_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_discountschema_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "breakvalue" NUMERIC NOT NULL,
    "breakdiscount" NUMERIC NOT NULL,
    "m_product_category_id" INTEGER,
    "m_product_id" INTEGER,
    "isbpartnerflatdiscount" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_discountschemabreak_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DISCOUNTSCHEMALINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_discountschemaline"
(
    "m_discountschemaline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_discountschema_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "m_product_category_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "m_product_id" INTEGER,
    "conversiondate" TIMESTAMP NOT NULL,
    "list_base" CHAR(1) NOT NULL,
    "list_addamt" NUMERIC DEFAULT 0 NOT NULL,
    "list_discount" NUMERIC NOT NULL,
    "list_rounding" CHAR(1) NOT NULL,
    "list_minamt" NUMERIC DEFAULT 0 NOT NULL,
    "list_maxamt" NUMERIC DEFAULT 0 NOT NULL,
    "list_fixed" NUMERIC DEFAULT 0,
    "std_base" CHAR(1) NOT NULL,
    "std_addamt" NUMERIC DEFAULT 0 NOT NULL,
    "std_discount" NUMERIC NOT NULL,
    "std_rounding" CHAR(1) NOT NULL,
    "std_minamt" NUMERIC DEFAULT 0 NOT NULL,
    "std_maxamt" NUMERIC DEFAULT 0 NOT NULL,
    "std_fixed" NUMERIC DEFAULT 0,
    "limit_base" CHAR(1) NOT NULL,
    "limit_addamt" NUMERIC DEFAULT 0 NOT NULL,
    "limit_discount" NUMERIC NOT NULL,
    "limit_rounding" CHAR(1) NOT NULL,
    "limit_minamt" NUMERIC DEFAULT 0 NOT NULL,
    "limit_maxamt" NUMERIC DEFAULT 0 NOT NULL,
    "limit_fixed" NUMERIC DEFAULT 0,
    "c_conversiontype_id" INTEGER NOT NULL,
    PRIMARY KEY ("m_discountschemaline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DISTRIBUTIONLIST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_distributionlist"
(
    "m_distributionlist_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ratiototal" NUMERIC,
    "processing" CHAR(1),
    PRIMARY KEY ("m_distributionlist_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DISTRIBUTIONLISTLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_distributionlistline"
(
    "m_distributionlistline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_distributionlist_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    "minqty" NUMERIC DEFAULT 0 NOT NULL,
    "ratio" NUMERIC,
    "description" VARCHAR(255),
    PRIMARY KEY ("m_distributionlistline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DISTRIBUTIONRUN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_distributionrun"
(
    "m_distributionrun_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "iscreatesingleorder" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartner_id" INTEGER,
    "c_bpartner_location_id" INTEGER,
    "processing" CHAR(1),
    PRIMARY KEY ("m_distributionrun_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCreateSingleOrder in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_DISTRIBUTIONRUNLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_distributionrunline"
(
    "m_distributionrunline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_distributionrun_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "m_distributionlist_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "totalqty" NUMERIC DEFAULT 0 NOT NULL,
    "minqty" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_distributionrunline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_EDI 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_edi"
(
    "m_edi_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_bp_edi_id" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "line" INTEGER NOT NULL,
    "trxtype" CHAR(1) NOT NULL,
    "edistatus" CHAR(1) NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "request_qty" NUMERIC DEFAULT 0 NOT NULL,
    "request_shipdate" TIMESTAMP NOT NULL,
    "request_price" NUMERIC DEFAULT 0,
    "trxsent" TIMESTAMP NOT NULL,
    "trxreceived" TIMESTAMP,
    "reply_received" TIMESTAMP,
    "reply_qtyconfirmed" NUMERIC DEFAULT 0,
    "reply_qtyavailable" NUMERIC DEFAULT 0,
    "reply_shipdate" TIMESTAMP,
    "reply_price" NUMERIC DEFAULT 0,
    "reply_remarks" VARCHAR(2000),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_edi_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE UNIQUE INDEX "m_edi_trx" ON "m_edi" ("c_bp_edi_id", "documentno", "line");

-- ----------------------------------------------------------------------- 
-- M_EDI_INFO 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_edi_info"
(
    "m_edi_info_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_edi_id" INTEGER NOT NULL,
    "info" TEXT NOT NULL,
    PRIMARY KEY ("m_edi_info_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_FORECAST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_forecast"
(
    "m_forecast_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_calendar_id" INTEGER NOT NULL,
    "c_year_id" INTEGER NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("m_forecast_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDefault in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_FORECASTLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_forecastline"
(
    "m_forecastline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_forecast_id" INTEGER NOT NULL,
    "c_period_id" INTEGER NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "qtycalculated" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_forecastline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_FREIGHT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_freight"
(
    "m_freight_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_shipper_id" INTEGER NOT NULL,
    "m_freightcategory_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP NOT NULL,
    "c_country_id" INTEGER,
    "to_country_id" INTEGER,
    "c_region_id" INTEGER,
    "to_region_id" INTEGER,
    "c_currency_id" INTEGER NOT NULL,
    "freightamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_freight_id")
);

-- ----------------------------------------------------------------------- 
-- M_FREIGHTCATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_freightcategory"
(
    "m_freightcategory_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("m_freightcategory_id")
);

-- ----------------------------------------------------------------------- 
-- M_INOUT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_inout"
(
    "m_inout_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_doctype_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "c_order_id" INTEGER,
    "dateordered" TIMESTAMP,
    "isprinted" CHAR(1) DEFAULT 'N' NOT NULL,
    "movementtype" CHAR(2) NOT NULL,
    "movementdate" TIMESTAMP NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "poreference" VARCHAR(20),
    "deliveryrule" CHAR(1) NOT NULL,
    "freightcostrule" CHAR(1) NOT NULL,
    "freightamt" NUMERIC DEFAULT 0,
    "deliveryviarule" CHAR(1) NOT NULL,
    "m_shipper_id" INTEGER,
    "c_charge_id" INTEGER,
    "chargeamt" NUMERIC DEFAULT 0,
    "priorityrule" CHAR(1) NOT NULL,
    "dateprinted" TIMESTAMP,
    "c_invoice_id" INTEGER,
    "createfrom" CHAR(1),
    "generateto" CHAR(1),
    "sendemail" CHAR(1) DEFAULT 'N' NOT NULL,
    "ad_user_id" INTEGER,
    "salesrep_id" INTEGER,
    "nopackages" INTEGER,
    "pickdate" TIMESTAMP,
    "shipdate" TIMESTAMP,
    "trackingno" VARCHAR(60),
    "ad_orgtrx_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "datereceived" TIMESTAMP,
    "isintransit" CHAR(1) DEFAULT 'N' NOT NULL,
    "ref_inout_id" INTEGER,
    "createconfirm" CHAR(1),
    "createpackage" CHAR(1),
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "isindispute" CHAR(1) DEFAULT 'N' NOT NULL,
    "volume" NUMERIC,
    "weight" NUMERIC,
    PRIMARY KEY ("m_inout_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsPrinted in ('Y','N')),
    CHECK (CreateFrom in ('Y','N')),
    CHECK (GenerateTo in ('Y','N'))
);

CREATE INDEX "m_inout_order" ON "m_inout" ("c_order_id");

CREATE INDEX "m_inout_bpartner" ON "m_inout" ("c_bpartner_id");

CREATE INDEX "m_inout_documentno" ON "m_inout" ("documentno");

-- ----------------------------------------------------------------------- 
-- M_INOUTCONFIRM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_inoutconfirm"
(
    "m_inoutconfirm_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "m_inout_id" INTEGER NOT NULL,
    "confirmtype" CHAR(2) NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "description" VARCHAR(255),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "createpackage" CHAR(1),
    "iscancelled" CHAR(1) DEFAULT 'N' NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "isindispute" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_inventory_id" INTEGER,
    "c_invoice_id" INTEGER,
    "approvalamt" NUMERIC,
    "confirmationno" VARCHAR(20),
    PRIMARY KEY ("m_inoutconfirm_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_INOUTLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_inoutline"
(
    "m_inoutline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "m_inout_id" INTEGER NOT NULL,
    "c_orderline_id" INTEGER,
    "m_locator_id" INTEGER,
    "m_product_id" INTEGER,
    "c_uom_id" INTEGER NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    "isinvoiced" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "isdescription" CHAR(1) DEFAULT 'N' NOT NULL,
    "confirmedqty" NUMERIC DEFAULT 0,
    "pickedqty" NUMERIC DEFAULT 0,
    "scrappedqty" NUMERIC DEFAULT 0,
    "targetqty" NUMERIC DEFAULT 0,
    "ref_inoutline_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "qtyentered" NUMERIC NOT NULL,
    "c_charge_id" INTEGER,
    "c_project_id" INTEGER,
    "c_projectphase_id" INTEGER,
    "c_projecttask_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    PRIMARY KEY ("m_inoutline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsInvoiced in ('Y','N'))
);

CREATE INDEX "m_inoutline_inout" ON "m_inoutline" ("m_inout_id");

CREATE INDEX "m_inoutline_product" ON "m_inoutline" ("m_product_id");

-- ----------------------------------------------------------------------- 
-- M_INOUTLINECONFIRM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_inoutlineconfirm"
(
    "m_inoutlineconfirm_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_inoutconfirm_id" INTEGER NOT NULL,
    "m_inoutline_id" INTEGER NOT NULL,
    "targetqty" NUMERIC DEFAULT 0 NOT NULL,
    "confirmedqty" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "differenceqty" NUMERIC,
    "scrappedqty" NUMERIC,
    "m_inventoryline_id" INTEGER,
    "c_invoiceline_id" INTEGER,
    "confirmationno" VARCHAR(20),
    PRIMARY KEY ("m_inoutlineconfirm_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_INOUTLINEMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_inoutlinema"
(
    "m_inoutline_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_inoutline_id", "m_attributesetinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_INVENTORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_inventory"
(
    "m_inventory_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "description" VARCHAR(255),
    "m_warehouse_id" INTEGER NOT NULL,
    "movementdate" TIMESTAMP NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "updateqty" CHAR(1) DEFAULT 'N',
    "generatelist" CHAR(1) DEFAULT 'Y',
    "m_perpetualinv_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "approvalamt" NUMERIC,
    "c_doctype_id" INTEGER NOT NULL,
    PRIMARY KEY ("m_inventory_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (UpdateQty in ('Y','N')),
    CHECK (GenerateList in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_INVENTORYLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_inventoryline"
(
    "m_inventoryline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_inventory_id" INTEGER NOT NULL,
    "m_locator_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "line" INTEGER,
    "qtybook" NUMERIC DEFAULT 0 NOT NULL,
    "qtycount" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "c_charge_id" INTEGER,
    "inventorytype" CHAR(1) DEFAULT 'D' NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "qtyinternaluse" NUMERIC,
    PRIMARY KEY ("m_inventoryline_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "m_inventoryline_productlocattr" ON "m_inventoryline" ("m_inventory_id", "m_locator_id", "m_product_id", "m_attributesetinstance_id");

-- ----------------------------------------------------------------------- 
-- M_INVENTORYLINEMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_inventorylinema"
(
    "m_inventoryline_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_inventoryline_id", "m_attributesetinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_LOCATOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_locator"
(
    "m_locator_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "priorityno" INTEGER NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "x" VARCHAR(60),
    "y" VARCHAR(60),
    "z" VARCHAR(60),
    PRIMARY KEY ("m_locator_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "m_location_where" ON "m_locator" ("m_warehouse_id", "x", "y", "z");

-- ----------------------------------------------------------------------- 
-- M_LOT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_lot"
(
    "m_lot_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "m_product_id" INTEGER NOT NULL,
    "help" VARCHAR(2000),
    "datefrom" TIMESTAMP,
    "dateto" TIMESTAMP,
    "m_lotctl_id" INTEGER,
    PRIMARY KEY ("m_lot_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_LOTCTL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_lotctl"
(
    "m_lotctl_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "startno" INTEGER NOT NULL,
    "incrementno" INTEGER NOT NULL,
    "currentnext" INTEGER NOT NULL,
    "prefix" VARCHAR(10),
    "suffix" VARCHAR(10),
    PRIMARY KEY ("m_lotctl_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_LOTCTLEXCLUDE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_lotctlexclude"
(
    "m_lotctlexclude_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_lotctl_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_lotctlexclude_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_MATCHINV 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_matchinv"
(
    "m_matchinv_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_inoutline_id" INTEGER NOT NULL,
    "c_invoiceline_id" INTEGER NOT NULL,
    "m_product_id" INTEGER,
    "datetrx" TIMESTAMP NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "processing" CHAR(1) NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "documentno" VARCHAR(30),
    "dateacct" TIMESTAMP,
    "m_attributesetinstance_id" INTEGER,
    "description" VARCHAR(255),
    PRIMARY KEY ("m_matchinv_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE INDEX "m_matchinv_ship" ON "m_matchinv" ("c_invoiceline_id", "m_inoutline_id");

-- ----------------------------------------------------------------------- 
-- M_MATCHPO 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_matchpo"
(
    "m_matchpo_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_orderline_id" INTEGER NOT NULL,
    "m_product_id" INTEGER,
    "m_inoutline_id" INTEGER,
    "c_invoiceline_id" INTEGER,
    "datetrx" TIMESTAMP NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "processing" CHAR(1) NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "documentno" VARCHAR(30),
    "dateacct" TIMESTAMP,
    "m_attributesetinstance_id" INTEGER,
    "pricematchdifference" NUMERIC,
    "isapproved" CHAR(1) DEFAULT 'Y',
    "description" VARCHAR(255),
    PRIMARY KEY ("m_matchpo_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

CREATE INDEX "m_matchpo_ship" ON "m_matchpo" ("c_orderline_id", "m_inoutline_id");

-- ----------------------------------------------------------------------- 
-- M_MOVEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_movement"
(
    "m_movement_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "description" VARCHAR(255),
    "movementdate" TIMESTAMP NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "ad_orgtrx_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "datereceived" TIMESTAMP,
    "docaction" CHAR(2) NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "isintransit" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_doctype_id" INTEGER NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "approvalamt" NUMERIC,
    PRIMARY KEY ("m_movement_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_MOVEMENTCONFIRM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_movementconfirm"
(
    "m_movementconfirm_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_movement_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "approvalamt" NUMERIC DEFAULT 0,
    "docaction" CHAR(2) NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_inventory_id" INTEGER,
    "documentno" VARCHAR(30) NOT NULL,
    PRIMARY KEY ("m_movementconfirm_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_MOVEMENTLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_movementline"
(
    "m_movementline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_movement_id" INTEGER NOT NULL,
    "m_locator_id" INTEGER NOT NULL,
    "m_locatorto_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "line" INTEGER,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "confirmedqty" NUMERIC DEFAULT 0,
    "scrappedqty" NUMERIC DEFAULT 0,
    "targetqty" NUMERIC DEFAULT 0,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_attributesetinstanceto_id" INTEGER,
    PRIMARY KEY ("m_movementline_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "m_movementline_movement" ON "m_movementline" ("m_movement_id");

-- ----------------------------------------------------------------------- 
-- M_MOVEMENTLINECONFIRM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_movementlineconfirm"
(
    "m_movementlineconfirm_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_movementconfirm_id" INTEGER NOT NULL,
    "m_movementline_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "targetqty" NUMERIC DEFAULT 0 NOT NULL,
    "confirmedqty" NUMERIC DEFAULT 0 NOT NULL,
    "differenceqty" NUMERIC DEFAULT 0 NOT NULL,
    "scrappedqty" NUMERIC DEFAULT 0 NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_inventoryline_id" INTEGER,
    PRIMARY KEY ("m_movementlineconfirm_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_MOVEMENTLINEMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_movementlinema"
(
    "m_movementline_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "movementqty" NUMERIC,
    PRIMARY KEY ("m_movementline_id", "m_attributesetinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_OPERATIONRESOURCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_operationresource"
(
    "m_operationresource_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_productoperation_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "a_asset_id" INTEGER,
    "c_job_id" INTEGER,
    "setuptime" NUMERIC DEFAULT 0 NOT NULL,
    "unitruntime" NUMERIC DEFAULT 0 NOT NULL,
    "teardowntime" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_operationresource_id")
);

-- ----------------------------------------------------------------------- 
-- M_PACKAGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_package"
(
    "m_package_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "m_inout_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "m_shipper_id" INTEGER NOT NULL,
    "trackinginfo" VARCHAR(255),
    "datereceived" TIMESTAMP,
    "receivedinfo" VARCHAR(255),
    "shipdate" TIMESTAMP,
    PRIMARY KEY ("m_package_id")
);

-- ----------------------------------------------------------------------- 
-- M_PACKAGELINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_packageline"
(
    "m_packageline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_package_id" INTEGER NOT NULL,
    "m_inoutline_id" INTEGER NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("m_packageline_id")
);

-- ----------------------------------------------------------------------- 
-- M_PERPETUALINV 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_perpetualinv"
(
    "m_perpetualinv_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "noinventorycount" INTEGER NOT NULL,
    "noproductcount" INTEGER NOT NULL,
    "counthighmovement" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "datelastrun" TIMESTAMP,
    "datenextrun" TIMESTAMP NOT NULL,
    "numberofruns" INTEGER NOT NULL,
    "m_product_category_id" INTEGER,
    "m_warehouse_id" INTEGER,
    PRIMARY KEY ("m_perpetualinv_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (CountHighMovement in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRICELIST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_pricelist"
(
    "m_pricelist_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "basepricelist_id" INTEGER,
    "istaxincluded" CHAR(1) DEFAULT 'N' NOT NULL,
    "issopricelist" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "enforcepricelimit" CHAR(1) DEFAULT 'N' NOT NULL,
    "priceprecision" INTEGER DEFAULT 2 NOT NULL,
    PRIMARY KEY ("m_pricelist_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTaxIncluded in ('Y','N')),
    CHECK (IsSOPriceList in ('Y','N')),
    CHECK (EnforcePriceLimit in ('Y','N'))
);

CREATE UNIQUE INDEX "m_pricelist_name" ON "m_pricelist" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- M_PRICELIST_VERSION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_pricelist_version"
(
    "m_pricelist_version_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "m_pricelist_id" INTEGER NOT NULL,
    "m_discountschema_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP NOT NULL,
    "proccreate" CHAR(1),
    "m_pricelist_version_base_id" INTEGER,
    PRIMARY KEY ("m_pricelist_version_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_product"
(
    "m_product_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "documentnote" VARCHAR(2000),
    "help" VARCHAR(2000),
    "upc" VARCHAR(30),
    "sku" VARCHAR(30),
    "c_uom_id" INTEGER NOT NULL,
    "salesrep_id" INTEGER,
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "isstocked" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ispurchased" CHAR(1) DEFAULT 'Y' NOT NULL,
    "issold" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isbom" CHAR(1) DEFAULT 'N' NOT NULL,
    "isinvoiceprintdetails" CHAR(1) DEFAULT 'N' NOT NULL,
    "ispicklistprintdetails" CHAR(1) DEFAULT 'N' NOT NULL,
    "isverified" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_revenuerecognition_id" INTEGER,
    "m_product_category_id" INTEGER NOT NULL,
    "classification" CHAR(1),
    "volume" NUMERIC DEFAULT 0,
    "weight" NUMERIC DEFAULT 0,
    "shelfwidth" INTEGER,
    "shelfheight" INTEGER,
    "shelfdepth" INTEGER,
    "unitsperpallet" INTEGER,
    "c_taxcategory_id" INTEGER NOT NULL,
    "s_resource_id" INTEGER,
    "discontinued" CHAR(1) DEFAULT 'N',
    "discontinuedby" TIMESTAMP,
    "processing" CHAR(1),
    "s_expensetype_id" INTEGER,
    "producttype" CHAR(1) DEFAULT 'I' NOT NULL,
    "imageurl" VARCHAR(120),
    "descriptionurl" VARCHAR(120),
    "guaranteedays" INTEGER,
    "r_mailtext_id" INTEGER,
    "versionno" VARCHAR(20),
    "m_attributeset_id" INTEGER,
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "downloadurl" VARCHAR(120),
    "m_freightcategory_id" INTEGER,
    "m_locator_id" INTEGER,
    "guaranteedaysmin" INTEGER,
    "iswebstorefeatured" CHAR(1) DEFAULT 'N' NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_subscriptiontype_id" INTEGER,
    "isdropship" CHAR(1) DEFAULT 'N' NOT NULL,
    "isexcludeautodelivery" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_product_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsStocked in ('Y','N')),
    CHECK (IsPurchased in ('Y','N')),
    CHECK (IsSold in ('Y','N')),
    CHECK (IsBOM in ('Y','N')),
    CHECK (IsInvoicePrintDetails in ('Y','N')),
    CHECK (IsPickListPrintDetails in ('Y','N')),
    CHECK (IsVerified in ('Y','N')),
    CHECK (Discontinued in ('Y','N'))
);

CREATE UNIQUE INDEX "m_product_value" ON "m_product" ("ad_client_id", "value");

CREATE INDEX "m_product_name" ON "m_product" ("name");

CREATE INDEX "m_product_productcategory" ON "m_product" ("m_product_category_id");

CREATE UNIQUE INDEX "m_product_resource" ON "m_product" ("s_resource_id");

CREATE UNIQUE INDEX "m_product_expensetype" ON "m_product" ("s_expensetype_id");

CREATE INDEX "m_product_upc" ON "m_product" ("upc");

-- ----------------------------------------------------------------------- 
-- M_PRODUCTDOWNLOAD 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_productdownload"
(
    "m_productdownload_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "downloadurl" VARCHAR(120) NOT NULL,
    PRIMARY KEY ("m_productdownload_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_production"
(
    "m_production_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "movementdate" TIMESTAMP NOT NULL,
    "iscreated" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    "ad_orgtrx_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    PRIMARY KEY ("m_production_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCreated in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCTIONLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_productionline"
(
    "m_productionline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_productionplan_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    "m_locator_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "m_attributesetinstance_id" INTEGER DEFAULT 0,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_productionline_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "m_productionline_prodplan" ON "m_productionline" ("m_productionplan_id");

-- ----------------------------------------------------------------------- 
-- M_PRODUCTIONLINEMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_productionlinema"
(
    "m_productionline_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_productionline_id", "m_attributesetinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCTIONPLAN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_productionplan"
(
    "m_productionplan_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_production_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "productionqty" NUMERIC DEFAULT 0 NOT NULL,
    "m_locator_id" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_productionplan_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "m_productionplan_production" ON "m_productionplan" ("m_production_id");

-- ----------------------------------------------------------------------- 
-- M_PRODUCTOPERATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_productoperation"
(
    "m_productoperation_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "m_product_id" INTEGER NOT NULL,
    "setuptime" NUMERIC DEFAULT 0,
    "unitruntime" NUMERIC DEFAULT 0,
    "teardowntime" NUMERIC DEFAULT 0,
    PRIMARY KEY ("m_productoperation_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCTPRICE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_productprice"
(
    "m_pricelist_version_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "pricelist" NUMERIC DEFAULT 0 NOT NULL,
    "pricestd" NUMERIC DEFAULT 0 NOT NULL,
    "pricelimit" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_pricelist_version_id", "m_product_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCT_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_product_acct"
(
    "m_product_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "p_revenue_acct" INTEGER NOT NULL,
    "p_expense_acct" INTEGER NOT NULL,
    "p_asset_acct" INTEGER NOT NULL,
    "p_purchasepricevariance_acct" INTEGER NOT NULL,
    "p_invoicepricevariance_acct" INTEGER NOT NULL,
    "p_cogs_acct" INTEGER NOT NULL,
    "p_tradediscountrec_acct" INTEGER NOT NULL,
    "p_tradediscountgrant_acct" INTEGER NOT NULL,
    "p_inventoryclearing_acct" INTEGER,
    "p_costadjustment_acct" INTEGER,
    PRIMARY KEY ("m_product_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCT_BOM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_product_bom"
(
    "m_product_bom_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_productbom_id" INTEGER NOT NULL,
    "bomqty" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "bomtype" CHAR(1),
    PRIMARY KEY ("m_product_bom_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCT_CATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_product_category"
(
    "m_product_category_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "plannedmargin" NUMERIC NOT NULL,
    "a_asset_group_id" INTEGER,
    "isselfservice" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ad_printcolor_id" INTEGER,
    "mmpolicy" CHAR(1) DEFAULT 'F' NOT NULL,
    PRIMARY KEY ("m_product_category_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "m_product_category_value" ON "m_product_category" ("ad_client_id", "value");

-- ----------------------------------------------------------------------- 
-- M_PRODUCT_CATEGORY_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_product_category_acct"
(
    "m_product_category_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "p_revenue_acct" INTEGER NOT NULL,
    "p_expense_acct" INTEGER NOT NULL,
    "p_asset_acct" INTEGER NOT NULL,
    "p_cogs_acct" INTEGER NOT NULL,
    "p_purchasepricevariance_acct" INTEGER NOT NULL,
    "p_invoicepricevariance_acct" INTEGER NOT NULL,
    "p_tradediscountrec_acct" INTEGER NOT NULL,
    "p_tradediscountgrant_acct" INTEGER NOT NULL,
    "processing" CHAR(1),
    "costingmethod" CHAR(1),
    "costinglevel" CHAR(1),
    "p_inventoryclearing_acct" INTEGER,
    "p_costadjustment_acct" INTEGER,
    PRIMARY KEY ("m_product_category_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCT_COSTING 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_product_costing"
(
    "m_product_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "currentcostprice" NUMERIC DEFAULT 0 NOT NULL,
    "futurecostprice" NUMERIC DEFAULT 0 NOT NULL,
    "coststandard" NUMERIC DEFAULT 0 NOT NULL,
    "coststandardpoqty" NUMERIC DEFAULT 0 NOT NULL,
    "coststandardpoamt" NUMERIC DEFAULT 0 NOT NULL,
    "coststandardcumqty" NUMERIC DEFAULT 0 NOT NULL,
    "coststandardcumamt" NUMERIC DEFAULT 0 NOT NULL,
    "costaverage" NUMERIC DEFAULT 0 NOT NULL,
    "costaveragecumqty" NUMERIC DEFAULT 0 NOT NULL,
    "costaveragecumamt" NUMERIC DEFAULT 0 NOT NULL,
    "pricelastpo" NUMERIC DEFAULT 0 NOT NULL,
    "pricelastinv" NUMERIC DEFAULT 0 NOT NULL,
    "totalinvqty" NUMERIC DEFAULT 0 NOT NULL,
    "totalinvamt" NUMERIC DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_product_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_PRODUCT_PO 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_product_po"
(
    "m_product_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "iscurrentvendor" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_uom_id" INTEGER,
    "c_currency_id" INTEGER,
    "pricelist" NUMERIC DEFAULT 0,
    "pricepo" NUMERIC DEFAULT 0,
    "priceeffective" TIMESTAMP,
    "pricelastpo" NUMERIC DEFAULT 0,
    "pricelastinv" NUMERIC DEFAULT 0,
    "vendorproductno" VARCHAR(30) NOT NULL,
    "upc" VARCHAR(20),
    "vendorcategory" VARCHAR(30),
    "discontinued" CHAR(1) DEFAULT 'N',
    "discontinuedby" TIMESTAMP,
    "order_min" NUMERIC DEFAULT 0,
    "order_pack" NUMERIC DEFAULT 0,
    "costperorder" NUMERIC DEFAULT 0,
    "deliverytime_promised" INTEGER,
    "deliverytime_actual" INTEGER,
    "qualityrating" NUMERIC,
    "royaltyamt" NUMERIC DEFAULT 0,
    "manufacturer" VARCHAR(30),
    PRIMARY KEY ("m_product_id", "c_bpartner_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsCurrentVendor in ('Y','N')),
    CHECK (Discontinued in ('Y','N'))
);

CREATE UNIQUE INDEX "m_product_po_vendorprodno" ON "m_product_po" ("c_bpartner_id", "vendorproductno");

-- ----------------------------------------------------------------------- 
-- M_PRODUCT_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_product_trl"
(
    "m_product_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "documentnote" VARCHAR(2000),
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("m_product_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_RELATEDPRODUCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_relatedproduct"
(
    "m_product_id" INTEGER NOT NULL,
    "relatedproduct_id" INTEGER NOT NULL,
    "relatedproducttype" CHAR(1) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("m_product_id", "relatedproduct_id", "relatedproducttype"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_REPLENISH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_replenish"
(
    "m_product_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "replenishtype" CHAR(1) NOT NULL,
    "level_min" NUMERIC DEFAULT 0 NOT NULL,
    "level_max" NUMERIC DEFAULT 0 NOT NULL,
    "m_warehousesource_id" INTEGER,
    PRIMARY KEY ("m_product_id", "m_warehouse_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_REQUISITION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_requisition"
(
    "m_requisition_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_user_id" INTEGER NOT NULL,
    "m_pricelist_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'Y' NOT NULL,
    "priorityrule" CHAR(1) NOT NULL,
    "daterequired" TIMESTAMP NOT NULL,
    "totallines" NUMERIC DEFAULT 0 NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "posted" CHAR(1) DEFAULT 'N' NOT NULL,
    "datedoc" TIMESTAMP DEFAULT NOW() NOT NULL,
    "c_doctype_id" INTEGER NOT NULL,
    PRIMARY KEY ("m_requisition_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsApproved in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_REQUISITIONLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_requisitionline"
(
    "m_requisitionline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "m_requisition_id" INTEGER NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "m_product_id" INTEGER,
    "description" VARCHAR(255),
    "priceactual" NUMERIC DEFAULT 0 NOT NULL,
    "linenetamt" NUMERIC DEFAULT 0 NOT NULL,
    "c_orderline_id" INTEGER,
    "m_attributesetinstance_id" NUMERIC,
    "c_charge_id" INTEGER,
    PRIMARY KEY ("m_requisitionline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_RMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_rma"
(
    "m_rma_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "m_inout_id" INTEGER NOT NULL,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_order_id" INTEGER,
    "c_doctype_id" INTEGER NOT NULL,
    "salesrep_id" INTEGER NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "amt" NUMERIC,
    "m_rmatype_id" INTEGER,
    "help" VARCHAR(2000),
    "c_currency_id" INTEGER,
    "c_bpartner_id" INTEGER,
    PRIMARY KEY ("m_rma_id")
);

-- ----------------------------------------------------------------------- 
-- M_RMALINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_rmaline"
(
    "m_rmaline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_rma_id" INTEGER NOT NULL,
    "m_inoutline_id" INTEGER NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_rmaline_id")
);

-- ----------------------------------------------------------------------- 
-- M_RMATYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_rmatype"
(
    "m_rmatype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("m_rmatype_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_SERNOCTL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_sernoctl"
(
    "m_sernoctl_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "startno" INTEGER NOT NULL,
    "incrementno" INTEGER NOT NULL,
    "currentnext" INTEGER NOT NULL,
    "prefix" VARCHAR(10),
    "suffix" VARCHAR(10),
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    PRIMARY KEY ("m_sernoctl_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_SERNOCTLEXCLUDE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_sernoctlexclude"
(
    "m_sernoctlexclude_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_sernoctl_id" INTEGER NOT NULL,
    "ad_table_id" INTEGER NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("m_sernoctlexclude_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_SHIPPER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_shipper"
(
    "m_shipper_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_bpartner_id" INTEGER,
    "trackingurl" VARCHAR(120),
    PRIMARY KEY ("m_shipper_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "m_shipper_name" ON "m_shipper" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- M_STORAGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_storage"
(
    "m_product_id" INTEGER NOT NULL,
    "m_locator_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "qtyonhand" NUMERIC DEFAULT 0 NOT NULL,
    "qtyreserved" NUMERIC DEFAULT 0 NOT NULL,
    "qtyordered" NUMERIC DEFAULT 0 NOT NULL,
    "datelastinventory" TIMESTAMP,
    "m_attributesetinstance_id" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_product_id", "m_locator_id", "m_attributesetinstance_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_SUBSTITUTE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_substitute"
(
    "m_product_id" INTEGER NOT NULL,
    "substitute_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60),
    "description" VARCHAR(255),
    PRIMARY KEY ("m_product_id", "substitute_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- M_TRANSACTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_transaction"
(
    "m_transaction_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "movementtype" CHAR(2) NOT NULL,
    "m_locator_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "movementdate" TIMESTAMP NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    "m_inventoryline_id" INTEGER,
    "m_movementline_id" INTEGER,
    "m_inoutline_id" INTEGER,
    "m_productionline_id" INTEGER,
    "c_projectissue_id" INTEGER,
    "m_attributesetinstance_id" INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY ("m_transaction_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "m_transsaction_product" ON "m_transaction" ("m_product_id");

-- ----------------------------------------------------------------------- 
-- M_TRANSACTIONALLOCATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_transactionallocation"
(
    "m_transaction_id" INTEGER NOT NULL,
    "allocationstrategytype" CHAR(1) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER DEFAULT 0 NOT NULL,
    "isallocated" CHAR(1) DEFAULT 'N' NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "ismanual" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_inoutline_id" INTEGER,
    "m_productionline_id" INTEGER,
    "m_inventoryline_id" INTEGER,
    "out_m_transaction_id" INTEGER,
    "out_m_inoutline_id" INTEGER,
    "out_m_productionline_id" INTEGER,
    "out_m_inventoryline_id" INTEGER,
    PRIMARY KEY ("m_transaction_id", "allocationstrategytype"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsAllocated in ('Y','N')),
    CHECK (IsManual in ('Y','N'))
);

CREATE INDEX "m_transactionallocation_prd" ON "m_transactionallocation" ("m_product_id", "m_attributesetinstance_id", "isallocated");

-- ----------------------------------------------------------------------- 
-- M_WAREHOUSE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_warehouse"
(
    "m_warehouse_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "c_location_id" INTEGER NOT NULL,
    "separator" CHAR(1) NOT NULL,
    "m_warehousesource_id" INTEGER,
    "replenishmentclass" VARCHAR(60),
    PRIMARY KEY ("m_warehouse_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "m_warehouse_name" ON "m_warehouse" ("ad_client_id", "name");

-- ----------------------------------------------------------------------- 
-- M_WAREHOUSE_ACCT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "m_warehouse_acct"
(
    "m_warehouse_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "w_inventory_acct" INTEGER NOT NULL,
    "w_invactualadjust_acct" INTEGER NOT NULL,
    "w_differences_acct" INTEGER NOT NULL,
    "w_revaluation_acct" INTEGER NOT NULL,
    PRIMARY KEY ("m_warehouse_id", "c_acctschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_ACHIEVEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_achievement"
(
    "pa_achievement_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "note" VARCHAR(2000),
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    "pa_measure_id" INTEGER NOT NULL,
    "manualactual" NUMERIC DEFAULT 0 NOT NULL,
    "isachieved" CHAR(1) NOT NULL,
    "datedoc" TIMESTAMP,
    PRIMARY KEY ("pa_achievement_id"),
    CHECK (IsAchieved IN ('Y','N')),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_BENCHMARK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_benchmark"
(
    "pa_benchmark_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "accumulationtype" CHAR(1) NOT NULL,
    PRIMARY KEY ("pa_benchmark_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_BENCHMARKDATA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_benchmarkdata"
(
    "pa_benchmarkdata_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "pa_benchmark_id" INTEGER NOT NULL,
    "benchmarkdate" TIMESTAMP NOT NULL,
    "benchmarkvalue" NUMERIC NOT NULL,
    PRIMARY KEY ("pa_benchmarkdata_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_COLORSCHEMA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_colorschema"
(
    "pa_colorschema_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "mark1percent" INTEGER DEFAULT 0 NOT NULL,
    "ad_printcolor1_id" INTEGER NOT NULL,
    "mark2percent" INTEGER DEFAULT 0 NOT NULL,
    "ad_printcolor2_id" INTEGER NOT NULL,
    "mark3percent" INTEGER DEFAULT 0,
    "ad_printcolor3_id" INTEGER,
    "mark4percent" INTEGER DEFAULT 0,
    "ad_printcolor4_id" INTEGER,
    "entitytype" VARCHAR(4) NOT NULL,
    PRIMARY KEY ("pa_colorschema_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_GOAL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_goal"
(
    "pa_goal_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "note" VARCHAR(2000),
    "ad_user_id" INTEGER,
    "pa_colorschema_id" INTEGER NOT NULL,
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "pa_goalparent_id" INTEGER,
    "pa_measure_id" INTEGER,
    "relativeweight" NUMERIC,
    "measuretarget" NUMERIC DEFAULT 0 NOT NULL,
    "measurescope" CHAR(1) NOT NULL,
    "measuredisplay" CHAR(1),
    "datefrom" TIMESTAMP,
    "dateto" TIMESTAMP,
    "measureactual" NUMERIC DEFAULT 0,
    "goalperformance" NUMERIC,
    "datelastrun" TIMESTAMP,
    "ad_role_id" INTEGER,
    PRIMARY KEY ("pa_goal_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_GOALRESTRICTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_goalrestriction"
(
    "pa_goalrestriction_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "goalrestrictiontype" CHAR(1) NOT NULL,
    "pa_goal_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER,
    "m_product_id" INTEGER,
    "org_id" INTEGER,
    "c_bp_group_id" INTEGER,
    "m_product_category_id" INTEGER,
    PRIMARY KEY ("pa_goalrestriction_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_HIERARCHY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_hierarchy"
(
    "pa_hierarchy_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ad_tree_org_id" INTEGER NOT NULL,
    "ad_tree_bpartner_id" INTEGER NOT NULL,
    "ad_tree_project_id" INTEGER NOT NULL,
    "ad_tree_salesregion_id" INTEGER NOT NULL,
    "ad_tree_product_id" INTEGER NOT NULL,
    "ad_tree_campaign_id" INTEGER NOT NULL,
    "ad_tree_activity_id" INTEGER NOT NULL,
    "ad_tree_account_id" INTEGER NOT NULL,
    PRIMARY KEY ("pa_hierarchy_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_MEASURE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_measure"
(
    "pa_measure_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "measuretype" CHAR(1) NOT NULL,
    "manualactual" NUMERIC,
    "manualnote" VARCHAR(2000),
    "calculationclass" VARCHAR(60),
    "pa_measurecalc_id" INTEGER,
    "pa_benchmark_id" INTEGER,
    "pa_ratio_id" INTEGER,
    "pa_hierarchy_id" INTEGER,
    "measuredatatype" CHAR(1) NOT NULL,
    "r_requesttype_id" INTEGER,
    "c_projecttype_id" INTEGER,
    PRIMARY KEY ("pa_measure_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_MEASURECALC 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_measurecalc"
(
    "pa_measurecalc_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "selectclause" VARCHAR(2000) NOT NULL,
    "whereclause" VARCHAR(2000) NOT NULL,
    "datecolumn" VARCHAR(60) NOT NULL,
    "orgcolumn" VARCHAR(60),
    "bpartnercolumn" VARCHAR(60),
    "productcolumn" VARCHAR(60),
    "ad_table_id" INTEGER NOT NULL,
    "keycolumn" VARCHAR(60) NOT NULL,
    "entitytype" VARCHAR(4) NOT NULL,
    PRIMARY KEY ("pa_measurecalc_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_RATIO 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_ratio"
(
    "pa_ratio_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "c_acctschema_id" INTEGER NOT NULL,
    PRIMARY KEY ("pa_ratio_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_RATIOELEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_ratioelement"
(
    "pa_ratioelement_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "pa_ratio_id" INTEGER NOT NULL,
    "ratiooperand" CHAR(1) NOT NULL,
    "ratioelementtype" CHAR(1) NOT NULL,
    "account_id" INTEGER,
    "pa_ratioused_id" INTEGER,
    "pa_measurecalc_id" INTEGER,
    "constantvalue" NUMERIC,
    "seqno" INTEGER NOT NULL,
    "postingtype" CHAR(1),
    PRIMARY KEY ("pa_ratioelement_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_REPORT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_report"
(
    "pa_report_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "pa_reportlineset_id" INTEGER NOT NULL,
    "pa_reportcolumnset_id" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "c_calendar_id" INTEGER NOT NULL,
    "processing" CHAR(1) NOT NULL,
    "ad_printformat_id" INTEGER,
    "listsources" CHAR(1) DEFAULT 'N' NOT NULL,
    "listtrx" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("pa_report_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_REPORTCOLUMN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_reportcolumn"
(
    "pa_reportcolumn_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "pa_reportcolumnset_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "seqno" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "isprinted" CHAR(1) DEFAULT 'Y' NOT NULL,
    "postingtype" CHAR(1) NOT NULL,
    "gl_budget_id" INTEGER,
    "columntype" CHAR(1) NOT NULL,
    "relativeperiod" INTEGER,
    "currencytype" CHAR(1),
    "calculationtype" CHAR(1),
    "amounttype" CHAR(2),
    "c_currency_id" INTEGER,
    "isadhocconversion" CHAR(1) DEFAULT 'N',
    "oper_1_id" INTEGER,
    "oper_2_id" INTEGER,
    "elementtype" CHAR(2),
    "org_id" INTEGER,
    "c_elementvalue_id" INTEGER,
    "c_project_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "m_product_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_location_id" INTEGER,
    "c_salesregion_id" INTEGER,
    "c_activity_id" INTEGER,
    PRIMARY KEY ("pa_reportcolumn_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPrinted in ('Y','N')),
    CHECK (IsAdHocConversion in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_REPORTCOLUMNSET 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_reportcolumnset"
(
    "pa_reportcolumnset_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "processing" CHAR(1) NOT NULL,
    PRIMARY KEY ("pa_reportcolumnset_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_REPORTLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_reportline"
(
    "pa_reportline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "pa_reportlineset_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "seqno" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "isprinted" CHAR(1) DEFAULT 'Y' NOT NULL,
    "parent_id" INTEGER,
    "issummary" CHAR(1) DEFAULT 'N' NOT NULL,
    "linetype" CHAR(1) NOT NULL,
    "calculationtype" CHAR(1),
    "oper_1_id" INTEGER,
    "oper_2_id" INTEGER,
    "postingtype" CHAR(1),
    "gl_budget_id" INTEGER,
    "amounttype" CHAR(2),
    PRIMARY KEY ("pa_reportline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsPrinted in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_REPORTLINESET 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_reportlineset"
(
    "pa_reportlineset_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "processing" CHAR(1) NOT NULL,
    PRIMARY KEY ("pa_reportlineset_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_REPORTSOURCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_reportsource"
(
    "pa_reportsource_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "description" VARCHAR(255),
    "pa_reportline_id" INTEGER NOT NULL,
    "elementtype" CHAR(2) NOT NULL,
    "org_id" INTEGER,
    "c_elementvalue_id" INTEGER,
    "c_project_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "m_product_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_location_id" INTEGER,
    "c_salesregion_id" INTEGER,
    "c_activity_id" INTEGER,
    PRIMARY KEY ("pa_reportsource_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_SLA_CRITERIA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_sla_criteria"
(
    "pa_sla_criteria_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "ismanual" CHAR(1) DEFAULT 'Y' NOT NULL,
    "classname" VARCHAR(60),
    PRIMARY KEY ("pa_sla_criteria_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsManual in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_SLA_GOAL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_sla_goal"
(
    "pa_sla_goal_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "pa_sla_criteria_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "validfrom" TIMESTAMP,
    "validto" TIMESTAMP,
    "measuretarget" NUMERIC DEFAULT 0 NOT NULL,
    "measureactual" NUMERIC DEFAULT 0 NOT NULL,
    "datelastrun" TIMESTAMP,
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("pa_sla_goal_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- PA_SLA_MEASURE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "pa_sla_measure"
(
    "pa_sla_measure_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "pa_sla_goal_id" INTEGER NOT NULL,
    "datetrx" TIMESTAMP NOT NULL,
    "measureactual" NUMERIC DEFAULT 0 NOT NULL,
    "description" VARCHAR(255),
    "ad_table_id" INTEGER,
    "record_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("pa_sla_measure_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_CATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_category"
(
    "r_category_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "m_product_id" INTEGER,
    PRIMARY KEY ("r_category_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_CATEGORYUPDATES 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_categoryupdates"
(
    "ad_user_id" INTEGER NOT NULL,
    "r_category_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_user_id", "r_category_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSelfService in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_CONTACTINTEREST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_contactinterest"
(
    "r_interestarea_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "subscribedate" TIMESTAMP,
    "optoutdate" TIMESTAMP,
    "ad_user_id" INTEGER NOT NULL,
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "r_contactinterest_key" ON "r_contactinterest" ("ad_user_id", "r_interestarea_id");

-- ----------------------------------------------------------------------- 
-- R_GROUP 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_group"
(
    "r_group_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "m_bom_id" INTEGER,
    "m_changenotice_id" INTEGER,
    PRIMARY KEY ("r_group_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_GROUPUPDATES 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_groupupdates"
(
    "ad_user_id" INTEGER NOT NULL,
    "r_group_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_user_id", "r_group_id"),
    CHECK (IsSelfService in ('Y','N')),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_INTERESTAREA 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_interestarea"
(
    "r_interestarea_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    PRIMARY KEY ("r_interestarea_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_ISSUEKNOWN 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_issueknown"
(
    "r_issueknown_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "issuesummary" VARCHAR(255) NOT NULL,
    "releaseno" CHAR(4) NOT NULL,
    "sourceclassname" VARCHAR(60),
    "sourcemethodname" VARCHAR(60),
    "loggername" VARCHAR(60),
    "lineno" INTEGER DEFAULT 0,
    "description" VARCHAR(255),
    "issuestatus" VARCHAR(2000),
    "r_issuestatus_id" INTEGER,
    "r_request_id" INTEGER,
    "processing" CHAR(1),
    "r_issuerecommendation_id" INTEGER,
    PRIMARY KEY ("r_issueknown_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "r_knownissue_alt" ON "r_issueknown" ("issuesummary", "releaseno", "sourceclassname", "sourcemethodname", "loggername", "lineno");

-- ----------------------------------------------------------------------- 
-- R_ISSUEPROJECT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_issueproject"
(
    "r_issueproject_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "a_asset_id" INTEGER,
    "c_project_id" INTEGER,
    "statisticsinfo" VARCHAR(60),
    "profileinfo" VARCHAR(60),
    "systemstatus" CHAR(1) NOT NULL,
    PRIMARY KEY ("r_issueproject_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE INDEX "r_issueproject_name" ON "r_issueproject" ("name");

-- ----------------------------------------------------------------------- 
-- R_ISSUERECOMMENDATION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_issuerecommendation"
(
    "r_issuerecommendation_id" CHAR(1) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("r_issuerecommendation_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_ISSUESOURCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_issuesource"
(
    "r_issuesource_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "r_issuesystem_id" INTEGER NOT NULL,
    "r_issueproject_id" INTEGER NOT NULL,
    "r_issueuser_id" INTEGER NOT NULL,
    "statisticsinfo" VARCHAR(60),
    "profileinfo" VARCHAR(60),
    PRIMARY KEY ("r_issuesource_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "r_issuesource_spo" ON "r_issuesource" ("r_issuesystem_id", "r_issueproject_id", "r_issueuser_id");

-- ----------------------------------------------------------------------- 
-- R_ISSUESTATUS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_issuestatus"
(
    "r_issuestatus_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    PRIMARY KEY ("r_issuestatus_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_ISSUESYSTEM 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_issuesystem"
(
    "r_issuesystem_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "dbaddress" VARCHAR(255) NOT NULL,
    "statisticsinfo" VARCHAR(60),
    "profileinfo" VARCHAR(60),
    "systemstatus" CHAR(1) NOT NULL,
    "a_asset_id" INTEGER,
    PRIMARY KEY ("r_issuesystem_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "r_issuesystem_address" ON "r_issuesystem" ("dbaddress");

-- ----------------------------------------------------------------------- 
-- R_ISSUEUSER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_issueuser"
(
    "r_issueuser_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "username" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "ad_user_id" INTEGER,
    PRIMARY KEY ("r_issueuser_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "r_issueuser_email" ON "r_issueuser" ("username", "ad_client_id");

-- ----------------------------------------------------------------------- 
-- R_MAILTEXT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_mailtext"
(
    "r_mailtext_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "ishtml" CHAR(1) DEFAULT 'N' NOT NULL,
    "mailheader" VARCHAR(2000),
    "mailtext" VARCHAR(2000) NOT NULL,
    "mailtext2" VARCHAR(2000),
    "mailtext3" VARCHAR(2000),
    PRIMARY KEY ("r_mailtext_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsHTML in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_MAILTEXT_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_mailtext_trl"
(
    "r_mailtext_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "mailheader" VARCHAR(2000) NOT NULL,
    "mailtext" VARCHAR(2000) NOT NULL,
    "mailtext2" VARCHAR(2000),
    "mailtext3" VARCHAR(2000),
    PRIMARY KEY ("r_mailtext_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_REQUEST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_request"
(
    "r_request_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "r_requesttype_id" INTEGER NOT NULL,
    "r_group_id" INTEGER,
    "r_category_id" INTEGER,
    "r_status_id" INTEGER,
    "r_resolution_id" INTEGER,
    "r_requestrelated_id" INTEGER,
    "priority" CHAR(1) NOT NULL,
    "priorityuser" CHAR(1),
    "duetype" CHAR(1) NOT NULL,
    "summary" VARCHAR(2000) NOT NULL,
    "confidentialtype" CHAR(1) DEFAULT 'C' NOT NULL,
    "isescalated" CHAR(1) DEFAULT 'N' NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "salesrep_id" INTEGER,
    "ad_role_id" INTEGER,
    "datelastaction" TIMESTAMP,
    "datelastalert" TIMESTAMP,
    "lastresult" VARCHAR(2000),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "isinvoiced" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartner_id" INTEGER,
    "ad_user_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_order_id" INTEGER,
    "c_invoice_id" INTEGER,
    "c_payment_id" INTEGER,
    "m_product_id" INTEGER,
    "c_project_id" INTEGER,
    "a_asset_id" INTEGER,
    "m_inout_id" INTEGER,
    "m_rma_id" INTEGER,
    "ad_table_id" INTEGER,
    "record_id" INTEGER,
    "requestamt" NUMERIC DEFAULT 0 NOT NULL,
    "r_mailtext_id" INTEGER,
    "result" VARCHAR(2000),
    "confidentialtypeentry" CHAR(1) NOT NULL,
    "r_standardresponse_id" INTEGER,
    "nextaction" CHAR(1),
    "datenextaction" TIMESTAMP,
    "starttime" TIMESTAMP,
    "endtime" TIMESTAMP,
    "qtyspent" NUMERIC DEFAULT 0,
    "qtyinvoiced" NUMERIC DEFAULT 0,
    "m_productspent_id" INTEGER,
    "c_activity_id" INTEGER,
    "startdate" TIMESTAMP,
    "closedate" TIMESTAMP,
    "c_invoicerequest_id" INTEGER,
    "m_changerequest_id" INTEGER,
    "taskstatus" CHAR(1),
    "qtyplan" NUMERIC,
    "datecompleteplan" TIMESTAMP,
    "datestartplan" TIMESTAMP,
    "m_fixchangenotice_id" INTEGER,
    PRIMARY KEY ("r_request_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsEscalated in ('Y','N')),
    CHECK (IsSelfService in ('Y','N')),
    CHECK (Processed in ('Y','N')),
    CHECK (IsInvoiced in ('Y','N'))
);

CREATE INDEX "r_request_bpartner" ON "r_request" ("c_bpartner_id");

CREATE INDEX "r_request_user" ON "r_request" ("ad_user_id");

-- ----------------------------------------------------------------------- 
-- R_REQUESTACTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_requestaction"
(
    "r_requestaction_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "r_request_id" INTEGER NOT NULL,
    "r_group_id" INTEGER,
    "r_category_id" INTEGER,
    "r_status_id" INTEGER,
    "r_resolution_id" INTEGER,
    "salesrep_id" INTEGER,
    "ad_role_id" INTEGER,
    "isescalated" CHAR(1),
    "isinvoiced" CHAR(1),
    "confidentialtype" CHAR(1),
    "isselfservice" CHAR(1),
    "priority" CHAR(1),
    "priorityuser" CHAR(1),
    "c_bpartner_id" INTEGER,
    "ad_user_id" INTEGER,
    "c_order_id" INTEGER,
    "c_invoice_id" INTEGER,
    "c_payment_id" INTEGER,
    "m_product_id" INTEGER,
    "c_project_id" INTEGER,
    "summary" VARCHAR(2000),
    "datenextaction" TIMESTAMP,
    "c_activity_id" INTEGER,
    "r_requesttype_id" INTEGER,
    "a_asset_id" INTEGER,
    "m_inout_id" INTEGER,
    "m_rma_id" INTEGER,
    "nullcolumns" VARCHAR(255),
    "taskstatus" CHAR(1),
    "datecompleteplan" TIMESTAMP,
    "qtyplan" NUMERIC,
    "qtyspent" NUMERIC,
    "startdate" TIMESTAMP,
    "enddate" TIMESTAMP,
    "datestartplan" TIMESTAMP,
    "qtyinvoiced" NUMERIC,
    "m_productspent_id" INTEGER,
    PRIMARY KEY ("r_requestaction_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_REQUESTPROCESSOR 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_requestprocessor"
(
    "r_requestprocessor_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "frequency" INTEGER NOT NULL,
    "frequencytype" CHAR(1) NOT NULL,
    "datelastrun" TIMESTAMP,
    "datenextrun" TIMESTAMP,
    "processing" CHAR(1),
    "overduealertdays" INTEGER NOT NULL,
    "overdueassigndays" INTEGER NOT NULL,
    "supervisor_id" INTEGER NOT NULL,
    "keeplogdays" INTEGER NOT NULL,
    "reminddays" INTEGER DEFAULT 7 NOT NULL,
    "inactivityalertdays" INTEGER DEFAULT 0 NOT NULL,
    "r_requesttype_id" INTEGER,
    PRIMARY KEY ("r_requestprocessor_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_REQUESTPROCESSORLOG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_requestprocessorlog"
(
    "r_requestprocessor_id" INTEGER NOT NULL,
    "r_requestprocessorlog_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "iserror" CHAR(1) DEFAULT 'N' NOT NULL,
    "summary" VARCHAR(2000),
    "reference" VARCHAR(60),
    "description" VARCHAR(255),
    "textmsg" VARCHAR(2000),
    "binarydata" BYTEA,
    PRIMARY KEY ("r_requestprocessor_id", "r_requestprocessorlog_id")
);

-- ----------------------------------------------------------------------- 
-- R_REQUESTPROCESSOR_ROUTE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_requestprocessor_route"
(
    "r_requestprocessor_route_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "r_requestprocessor_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "keyword" VARCHAR(60),
    "ad_user_id" INTEGER NOT NULL,
    "r_requesttype_id" INTEGER,
    PRIMARY KEY ("r_requestprocessor_route_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_REQUESTTYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_requesttype"
(
    "r_requesttype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'Y' NOT NULL,
    "duedatetolerance" INTEGER DEFAULT 7 NOT NULL,
    "isemailwhenoverdue" CHAR(1) DEFAULT 'N' NOT NULL,
    "isemailwhendue" CHAR(1) DEFAULT 'N' NOT NULL,
    "isinvoiced" CHAR(1),
    "autoduedatedays" INTEGER,
    "confidentialtype" CHAR(1) DEFAULT 'C' NOT NULL,
    "isautochangerequest" CHAR(1) DEFAULT 'N' NOT NULL,
    "isconfidentialinfo" CHAR(1) DEFAULT 'N' NOT NULL,
    "r_statuscategory_id" INTEGER NOT NULL,
    "isindexed" CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY ("r_requesttype_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_REQUESTTYPEUPDATES 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_requesttypeupdates"
(
    "ad_user_id" INTEGER NOT NULL,
    "r_requesttype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_user_id", "r_requesttype_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSelfService in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_REQUESTUPDATE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_requestupdate"
(
    "r_requestupdate_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "r_request_id" INTEGER NOT NULL,
    "confidentialtypeentry" CHAR(1) NOT NULL,
    "starttime" TIMESTAMP,
    "endtime" TIMESTAMP,
    "qtyspent" NUMERIC DEFAULT 0,
    "qtyinvoiced" NUMERIC DEFAULT 0,
    "m_productspent_id" INTEGER,
    "result" VARCHAR(2000),
    PRIMARY KEY ("r_requestupdate_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_REQUESTUPDATES 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_requestupdates"
(
    "ad_user_id" INTEGER NOT NULL,
    "r_request_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_user_id", "r_request_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSelfService in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_RESOLUTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_resolution"
(
    "r_resolution_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("r_resolution_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_STANDARDRESPONSE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_standardresponse"
(
    "r_standardresponse_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "responsetext" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("r_standardresponse_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_STATUS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_status"
(
    "r_status_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "isopen" CHAR(1) DEFAULT 'N' NOT NULL,
    "isclosed" CHAR(1) DEFAULT 'N' NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "next_status_id" INTEGER,
    "update_status_id" INTEGER,
    "timeoutdays" INTEGER,
    "iswebcanupdate" CHAR(1) DEFAULT 'Y' NOT NULL,
    "isfinalclose" CHAR(1) DEFAULT 'N' NOT NULL,
    "seqno" INTEGER DEFAULT 0 NOT NULL,
    "r_statuscategory_id" INTEGER NOT NULL,
    PRIMARY KEY ("r_status_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- R_STATUSCATEGORY 
-- ----------------------------------------------------------------------- 

CREATE TABLE "r_statuscategory"
(
    "r_statuscategory_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("r_statuscategory_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsDefault in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_EXPENSETYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_expensetype"
(
    "s_expensetype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "isinvoiced" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_uom_id" INTEGER NOT NULL,
    "m_product_category_id" INTEGER NOT NULL,
    "c_taxcategory_id" INTEGER NOT NULL,
    PRIMARY KEY ("s_expensetype_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsInvoiced in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_RESOURCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_resource"
(
    "s_resource_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "s_resourcetype_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "isavailable" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ad_user_id" INTEGER,
    "chargeableqty" NUMERIC DEFAULT 0,
    PRIMARY KEY ("s_resource_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsAvailable in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_RESOURCEASSIGNMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_resourceassignment"
(
    "s_resourceassignment_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "s_resource_id" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "assigndatefrom" TIMESTAMP NOT NULL,
    "assigndateto" TIMESTAMP,
    "qty" NUMERIC DEFAULT 0,
    "isconfirmed" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("s_resourceassignment_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsConfirmed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_RESOURCETYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_resourcetype"
(
    "s_resourcetype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "value" VARCHAR(40) NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "issingleassignment" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_uom_id" INTEGER NOT NULL,
    "allowuomfractions" CHAR(1) DEFAULT 'N' NOT NULL,
    "timeslotstart" TIMESTAMP,
    "timeslotend" TIMESTAMP,
    "istimeslot" CHAR(1) DEFAULT 'N' NOT NULL,
    "isdateslot" CHAR(1) DEFAULT 'N' NOT NULL,
    "onsunday" CHAR(1) DEFAULT 'N' NOT NULL,
    "onmonday" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ontuesday" CHAR(1) DEFAULT 'Y' NOT NULL,
    "onwednesday" CHAR(1) DEFAULT 'Y' NOT NULL,
    "onthursday" CHAR(1) DEFAULT 'Y' NOT NULL,
    "onfriday" CHAR(1) DEFAULT 'Y' NOT NULL,
    "onsaturday" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_product_category_id" INTEGER NOT NULL,
    "c_taxcategory_id" INTEGER NOT NULL,
    "chargeableqty" NUMERIC DEFAULT 0,
    PRIMARY KEY ("s_resourcetype_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsSingleAssignment in ('Y','N')),
    CHECK (AllowUOMFractions in ('Y','N')),
    CHECK (IsTimeSlot in ('Y','N')),
    CHECK (IsDateSlot in ('Y','N')),
    CHECK (OnSunday in ('Y','N')),
    CHECK (OnMonday in ('Y','N')),
    CHECK (OnTuesday in ('Y','N')),
    CHECK (OnWednesday in ('Y','N')),
    CHECK (OnThursday in ('Y','N')),
    CHECK (OnFriday in ('Y','N')),
    CHECK (OnSaturday in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_RESOURCEUNAVAILABLE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_resourceunavailable"
(
    "s_resourceunavailable_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "s_resource_id" INTEGER NOT NULL,
    "datefrom" TIMESTAMP NOT NULL,
    "dateto" TIMESTAMP,
    "description" VARCHAR(255),
    PRIMARY KEY ("s_resourceunavailable_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_TIMEEXPENSE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_timeexpense"
(
    "s_timeexpense_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "documentno" VARCHAR(30) NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "datereport" TIMESTAMP NOT NULL,
    "description" VARCHAR(255),
    "processing" CHAR(1),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_pricelist_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "isapproved" CHAR(1) DEFAULT 'N' NOT NULL,
    "docstatus" CHAR(2) NOT NULL,
    "docaction" CHAR(2) NOT NULL,
    "approvalamt" NUMERIC,
    PRIMARY KEY ("s_timeexpense_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_TIMEEXPENSELINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_timeexpenseline"
(
    "s_timeexpenseline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "s_timeexpense_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "istimereport" CHAR(1) DEFAULT 'N' NOT NULL,
    "dateexpense" TIMESTAMP,
    "m_product_id" INTEGER,
    "qty" NUMERIC DEFAULT 0,
    "expenseamt" NUMERIC DEFAULT 0,
    "c_currency_id" INTEGER,
    "convertedamt" NUMERIC DEFAULT 0,
    "s_resourceassignment_id" INTEGER,
    "description" VARCHAR(255),
    "note" VARCHAR(255),
    "isinvoiced" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartner_id" INTEGER,
    "c_project_id" INTEGER,
    "c_activity_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_invoiceline_id" INTEGER,
    "invoiceprice" NUMERIC DEFAULT 0,
    "c_uom_id" INTEGER,
    "c_orderline_id" INTEGER,
    "c_projectphase_id" INTEGER,
    "c_projecttask_id" INTEGER,
    "s_timetype_id" INTEGER,
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "qtyinvoiced" NUMERIC,
    "qtyreimbursed" NUMERIC,
    "priceinvoiced" NUMERIC,
    "pricereimbursed" NUMERIC,
    PRIMARY KEY ("s_timeexpenseline_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTimeReport in ('Y','N')),
    CHECK (IsInvoiced in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_TIMETYPE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_timetype"
(
    "s_timetype_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    PRIMARY KEY ("s_timetype_id")
);

-- ----------------------------------------------------------------------- 
-- S_TRAINING 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_training"
(
    "s_training_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "documentnote" VARCHAR(2000),
    "imageurl" VARCHAR(120),
    "descriptionurl" VARCHAR(120),
    "m_product_category_id" INTEGER NOT NULL,
    "c_taxcategory_id" INTEGER NOT NULL,
    "c_uom_id" INTEGER NOT NULL,
    "processing" CHAR(1),
    PRIMARY KEY ("s_training_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- S_TRAINING_CLASS 
-- ----------------------------------------------------------------------- 

CREATE TABLE "s_training_class"
(
    "s_training_class_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "s_training_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "startdate" TIMESTAMP NOT NULL,
    "enddate" TIMESTAMP NOT NULL,
    PRIMARY KEY ("s_training_class_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- TEST 
-- ----------------------------------------------------------------------- 

CREATE TABLE "test"
(
    "test_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "t_integer" INTEGER,
    "t_number" NUMERIC DEFAULT 0,
    "t_date" TIMESTAMP,
    "t_datetime" TIMESTAMP,
    "c_uom_id" INTEGER,
    "t_qty" NUMERIC DEFAULT 0,
    "c_currency_id" INTEGER,
    "t_amount" NUMERIC DEFAULT 0,
    "c_location_id" INTEGER,
    "account_acct" INTEGER,
    "c_payment_id" INTEGER,
    "m_product_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "m_locator_id" INTEGER,
    "processing" CHAR(1),
    "binarydata" BYTEA,
    "processed" CHAR(1) DEFAULT 'N',
    "characterdata" TEXT,
    PRIMARY KEY ("test_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- TIRE_STORAGE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "tire_storage"
(
    "tire_storage_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "c_bpartner_id" INTEGER,
    "registration" VARCHAR(20),
    "vehicle" VARCHAR(20),
    "description" VARCHAR(255),
    "tiretype" VARCHAR(20),
    "tiretype_b" VARCHAR(20),
    "tiresize" VARCHAR(20),
    "tiresize_b" VARCHAR(20),
    "tirequality" VARCHAR(20),
    "tirequality_b" VARCHAR(20),
    "rim" VARCHAR(20),
    "rim_b" VARCHAR(20),
    "datereceived" TIMESTAMP NOT NULL,
    "isstored" CHAR(1) DEFAULT 'N' NOT NULL,
    "m_locator_id" INTEGER,
    "remark" VARCHAR(60),
    "isreturned" CHAR(1) DEFAULT 'N' NOT NULL,
    "datereturned" TIMESTAMP,
    "ad_user_id" INTEGER,
    PRIMARY KEY ("tire_storage_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsStored in ('Y','N')),
    CHECK (IsReturned in ('Y','N')),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsStored in ('Y','N')),
    CHECK (IsReturned in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- T_AGING 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_aging"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_currency_id" INTEGER NOT NULL,
    "c_invoice_id" INTEGER NOT NULL,
    "c_invoicepayschedule_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "statementdate" TIMESTAMP NOT NULL,
    "duedate" TIMESTAMP NOT NULL,
    "daysdue" INTEGER DEFAULT 0,
    "islistinvoices" CHAR(1) DEFAULT 'N' NOT NULL,
    "issotrx" CHAR(1) DEFAULT 'Y' NOT NULL,
    "c_bp_group_id" INTEGER NOT NULL,
    "invoicedamt" NUMERIC DEFAULT 0 NOT NULL,
    "openamt" NUMERIC DEFAULT 0 NOT NULL,
    "pastdue91_plus" NUMERIC DEFAULT 0 NOT NULL,
    "pastdue61_90" NUMERIC DEFAULT 0 NOT NULL,
    "pastdue61_plus" NUMERIC DEFAULT 0 NOT NULL,
    "pastdue31_60" NUMERIC DEFAULT 0 NOT NULL,
    "pastdue31_plus" NUMERIC DEFAULT 0 NOT NULL,
    "pastdue1_30" NUMERIC DEFAULT 0 NOT NULL,
    "pastdue8_30" NUMERIC DEFAULT 0 NOT NULL,
    "pastdue1_7" NUMERIC DEFAULT 0 NOT NULL,
    "pastdueamt" NUMERIC DEFAULT 0 NOT NULL,
    "dueamt" NUMERIC DEFAULT 0 NOT NULL,
    "due0" NUMERIC DEFAULT 0 NOT NULL,
    "due0_7" NUMERIC DEFAULT 0 NOT NULL,
    "due1_7" NUMERIC DEFAULT 0 NOT NULL,
    "due8_30" NUMERIC DEFAULT 0 NOT NULL,
    "due0_30" NUMERIC DEFAULT 0 NOT NULL,
    "due31_plus" NUMERIC DEFAULT 0 NOT NULL,
    "due31_60" NUMERIC DEFAULT 0 NOT NULL,
    "due61_plus" NUMERIC DEFAULT 0 NOT NULL,
    "due61_90" NUMERIC DEFAULT 0 NOT NULL,
    "due91_plus" NUMERIC DEFAULT 0 NOT NULL,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    PRIMARY KEY ("ad_pinstance_id", "c_bpartner_id", "c_currency_id", "c_invoice_id", "c_invoicepayschedule_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsListInvoices in ('Y','N')),
    CHECK (IsSOTrx in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- T_DISTRIBUTIONRUNDETAIL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_distributionrundetail"
(
    "m_distributionrun_id" INTEGER NOT NULL,
    "m_distributionrunline_id" INTEGER NOT NULL,
    "m_distributionlist_id" INTEGER NOT NULL,
    "m_distributionlistline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "ratio" NUMERIC NOT NULL,
    "minqty" NUMERIC DEFAULT 0 NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "c_bpartner_location_id" INTEGER NOT NULL,
    PRIMARY KEY ("m_distributionrun_id", "m_distributionrunline_id", "m_distributionlist_id", "m_distributionlistline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- T_INVENTORYVALUE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_inventoryvalue"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER,
    "ad_org_id" INTEGER,
    "m_pricelist_version_id" INTEGER,
    "datevalue" TIMESTAMP,
    "c_currency_id" INTEGER,
    "qtyonhand" NUMERIC DEFAULT 0,
    "pricepo" NUMERIC DEFAULT 0,
    "pricelist" NUMERIC DEFAULT 0,
    "pricestd" NUMERIC DEFAULT 0,
    "pricelimit" NUMERIC DEFAULT 0,
    "coststandard" NUMERIC DEFAULT 0,
    "cost" NUMERIC DEFAULT 0,
    "pricepoamt" NUMERIC DEFAULT 0,
    "pricelistamt" NUMERIC DEFAULT 0,
    "pricestdamt" NUMERIC DEFAULT 0,
    "pricelimitamt" NUMERIC DEFAULT 0,
    "coststandardamt" NUMERIC DEFAULT 0,
    "costamt" NUMERIC DEFAULT 0,
    "m_costelement_id" INTEGER,
    PRIMARY KEY ("ad_pinstance_id", "m_warehouse_id", "m_product_id", "m_attributesetinstance_id")
);

-- ----------------------------------------------------------------------- 
-- T_INVOICEGL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_invoicegl"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "c_invoice_id" INTEGER NOT NULL,
    "fact_acct_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "grandtotal" NUMERIC DEFAULT 0 NOT NULL,
    "openamt" NUMERIC DEFAULT 0 NOT NULL,
    "percent" NUMERIC,
    "apar" CHAR(1),
    "amtsourcebalance" NUMERIC DEFAULT 0 NOT NULL,
    "amtacctbalance" NUMERIC DEFAULT 0 NOT NULL,
    "c_conversiontypereval_id" INTEGER NOT NULL,
    "amtrevaldr" NUMERIC DEFAULT 0 NOT NULL,
    "amtrevalcr" NUMERIC DEFAULT 0 NOT NULL,
    "datereval" TIMESTAMP NOT NULL,
    "amtrevaldrdiff" NUMERIC DEFAULT 0 NOT NULL,
    "amtrevalcrdiff" NUMERIC DEFAULT 0 NOT NULL,
    "c_doctypereval_id" INTEGER,
    "isallcurrencies" CHAR(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY ("ad_pinstance_id", "c_invoice_id", "fact_acct_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- T_REPLENISH 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_replenish"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "qtyonhand" NUMERIC DEFAULT 0 NOT NULL,
    "qtyreserved" NUMERIC DEFAULT 0 NOT NULL,
    "qtyordered" NUMERIC DEFAULT 0 NOT NULL,
    "replenishtype" CHAR(1) NOT NULL,
    "level_min" NUMERIC DEFAULT 0 NOT NULL,
    "level_max" NUMERIC DEFAULT 0 NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "order_min" NUMERIC DEFAULT 0 NOT NULL,
    "order_pack" NUMERIC DEFAULT 0 NOT NULL,
    "qtytoorder" NUMERIC DEFAULT 0 NOT NULL,
    "replenishmentcreate" CHAR(3),
    "m_warehousesource_id" INTEGER,
    "c_doctype_id" INTEGER,
    PRIMARY KEY ("ad_pinstance_id", "m_warehouse_id", "m_product_id")
);

-- ----------------------------------------------------------------------- 
-- T_REPORT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_report"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "pa_reportline_id" INTEGER NOT NULL,
    "record_id" INTEGER NOT NULL,
    "fact_acct_id" INTEGER NOT NULL,
    "seqno" NUMERIC,
    "levelno" INTEGER DEFAULT 0,
    "name" VARCHAR(60),
    "description" VARCHAR(255),
    "col_0" NUMERIC,
    "col_2" NUMERIC,
    "col_1" NUMERIC,
    "col_3" NUMERIC,
    "col_4" NUMERIC,
    "col_5" NUMERIC,
    "col_6" NUMERIC,
    "col_7" NUMERIC,
    "col_8" NUMERIC,
    "col_9" NUMERIC,
    "col_10" NUMERIC,
    "col_11" NUMERIC,
    "col_12" NUMERIC,
    "col_13" NUMERIC,
    "col_14" NUMERIC,
    "col_15" NUMERIC,
    "col_16" NUMERIC,
    "col_17" NUMERIC,
    "col_18" NUMERIC,
    "col_19" NUMERIC,
    "col_20" NUMERIC,
    PRIMARY KEY ("ad_pinstance_id", "pa_reportline_id", "record_id", "fact_acct_id")
);

-- ----------------------------------------------------------------------- 
-- T_REPORTSTATEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_reportstatement"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "fact_acct_id" INTEGER NOT NULL,
    "levelno" INTEGER NOT NULL,
    "dateacct" TIMESTAMP NOT NULL,
    "name" VARCHAR(60),
    "description" VARCHAR(255),
    "amtacctdr" NUMERIC DEFAULT 0,
    "amtacctcr" NUMERIC DEFAULT 0,
    "balance" NUMERIC DEFAULT 0,
    "qty" NUMERIC DEFAULT 0,
    PRIMARY KEY ("ad_pinstance_id", "fact_acct_id")
);

-- ----------------------------------------------------------------------- 
-- T_SELECTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_selection"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "t_selection_id" INTEGER NOT NULL,
    PRIMARY KEY ("ad_pinstance_id", "t_selection_id")
);

-- ----------------------------------------------------------------------- 
-- T_SELECTION2 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_selection2"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "query_id" NUMERIC NOT NULL,
    "t_selection_id" INTEGER NOT NULL,
    PRIMARY KEY ("ad_pinstance_id", "query_id", "t_selection_id")
);

-- ----------------------------------------------------------------------- 
-- T_SPOOL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_spool"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "seqno" INTEGER NOT NULL,
    "msgtext" VARCHAR(2000) NOT NULL,
    PRIMARY KEY ("ad_pinstance_id", "seqno")
);

-- ----------------------------------------------------------------------- 
-- T_TRANSACTION 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_transaction"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "m_transaction_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "movementtype" CHAR(2) NOT NULL,
    "m_locator_id" INTEGER NOT NULL,
    "m_product_id" INTEGER NOT NULL,
    "m_attributesetinstance_id" INTEGER NOT NULL,
    "movementdate" TIMESTAMP NOT NULL,
    "movementqty" NUMERIC DEFAULT 0 NOT NULL,
    "m_inoutline_id" INTEGER,
    "m_inout_id" INTEGER,
    "m_movementline_id" INTEGER,
    "m_movement_id" INTEGER,
    "m_inventoryline_id" INTEGER,
    "m_inventory_id" INTEGER,
    "m_productionline_id" INTEGER,
    "m_production_id" INTEGER,
    "c_projectissue_id" INTEGER,
    "c_project_id" INTEGER,
    "search_order_id" INTEGER,
    "search_invoice_id" INTEGER,
    "search_inout_id" INTEGER,
    PRIMARY KEY ("ad_pinstance_id", "m_transaction_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- T_TRIALBALANCE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "t_trialbalance"
(
    "ad_pinstance_id" INTEGER NOT NULL,
    "fact_acct_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER,
    "created" TIMESTAMP NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "c_acctschema_id" INTEGER NOT NULL,
    "account_id" INTEGER,
    "datetrx" TIMESTAMP,
    "dateacct" TIMESTAMP NOT NULL,
    "c_period_id" INTEGER,
    "ad_table_id" INTEGER,
    "record_id" INTEGER,
    "line_id" INTEGER,
    "gl_category_id" INTEGER,
    "gl_budget_id" INTEGER,
    "c_tax_id" INTEGER,
    "m_locator_id" INTEGER,
    "postingtype" CHAR(1) NOT NULL,
    "c_currency_id" INTEGER,
    "amtsourcedr" NUMERIC,
    "amtsourcecr" NUMERIC,
    "amtsourcebalance" NUMERIC,
    "amtacctdr" NUMERIC NOT NULL,
    "amtacctcr" NUMERIC NOT NULL,
    "amtacctbalance" NUMERIC NOT NULL,
    "c_uom_id" INTEGER,
    "qty" NUMERIC,
    "m_product_id" INTEGER,
    "c_bpartner_id" INTEGER,
    "ad_orgtrx_id" INTEGER,
    "c_locfrom_id" INTEGER,
    "c_locto_id" INTEGER,
    "c_salesregion_id" INTEGER,
    "c_project_id" INTEGER,
    "c_campaign_id" INTEGER,
    "c_activity_id" INTEGER,
    "user1_id" INTEGER,
    "user2_id" INTEGER,
    "a_asset_id" INTEGER,
    "description" VARCHAR(255),
    "accountvalue" VARCHAR(40),
    PRIMARY KEY ("ad_pinstance_id", "fact_acct_id")
);

-- ----------------------------------------------------------------------- 
-- W_ADVERTISEMENT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_advertisement"
(
    "w_advertisement_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "isselfservice" CHAR(1) DEFAULT 'N' NOT NULL,
    "c_bpartner_id" INTEGER NOT NULL,
    "ad_user_id" INTEGER,
    "w_clickcount_id" INTEGER,
    "w_countercount_id" INTEGER,
    "validfrom" TIMESTAMP,
    "validto" TIMESTAMP,
    "imageurl" VARCHAR(120),
    "adtext" VARCHAR(2000),
    "webparam1" VARCHAR(2000),
    "webparam2" VARCHAR(2000),
    "webparam3" VARCHAR(2000),
    "webparam4" VARCHAR(2000),
    "publishstatus" CHAR(1) NOT NULL,
    "version" INTEGER,
    "processing" CHAR(1),
    PRIMARY KEY ("w_advertisement_id")
);

-- ----------------------------------------------------------------------- 
-- W_BASKET 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_basket"
(
    "w_basket_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "session_id" VARCHAR(60) NOT NULL,
    "email" VARCHAR(60),
    "c_bpartner_id" INTEGER,
    "m_pricelist_id" INTEGER,
    "ad_user_id" INTEGER NOT NULL,
    PRIMARY KEY ("w_basket_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "w_basket_session" ON "w_basket" ("session_id");

CREATE INDEX "w_basket_cbpartner" ON "w_basket" ("c_bpartner_id");

-- ----------------------------------------------------------------------- 
-- W_BASKETLINE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_basketline"
(
    "w_basketline_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "w_basket_id" INTEGER NOT NULL,
    "line" INTEGER NOT NULL,
    "qty" NUMERIC DEFAULT 0 NOT NULL,
    "price" NUMERIC DEFAULT 0 NOT NULL,
    "product" VARCHAR(40) NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "m_product_id" INTEGER,
    PRIMARY KEY ("w_basketline_id"),
    CHECK (IsActive in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- W_CLICK 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_click"
(
    "w_click_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "targeturl" VARCHAR(120),
    "referrer" VARCHAR(120),
    "remote_host" VARCHAR(120),
    "remote_addr" VARCHAR(60),
    "useragent" VARCHAR(255),
    "acceptlanguage" VARCHAR(60),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "w_clickcount_id" INTEGER,
    "ad_user_id" INTEGER,
    "email" VARCHAR(60),
    PRIMARY KEY ("w_click_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- W_CLICKCOUNT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_clickcount"
(
    "w_clickcount_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "targeturl" VARCHAR(120) NOT NULL,
    "c_bpartner_id" INTEGER,
    PRIMARY KEY ("w_clickcount_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "w_clickcount_targeturl" ON "w_clickcount" ("ad_client_id", "targeturl");

-- ----------------------------------------------------------------------- 
-- W_COUNTER 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_counter"
(
    "w_counter_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "pageurl" VARCHAR(120) NOT NULL,
    "referrer" VARCHAR(120),
    "remote_host" VARCHAR(120),
    "remote_addr" VARCHAR(60),
    "useragent" VARCHAR(255),
    "acceptlanguage" VARCHAR(60),
    "processed" CHAR(1) DEFAULT 'N' NOT NULL,
    "w_countercount_id" INTEGER,
    "ad_user_id" INTEGER,
    "email" VARCHAR(60),
    PRIMARY KEY ("w_counter_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (Processed in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- W_COUNTERCOUNT 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_countercount"
(
    "w_countercount_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "pageurl" VARCHAR(120) NOT NULL,
    "c_bpartner_id" INTEGER,
    PRIMARY KEY ("w_countercount_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "w_countercount_pageurl" ON "w_countercount" ("ad_client_id", "pageurl");

-- ----------------------------------------------------------------------- 
-- W_MAILMSG 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_mailmsg"
(
    "w_mailmsg_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "w_store_id" INTEGER NOT NULL,
    "mailmsgtype" CHAR(2) NOT NULL,
    "subject" VARCHAR(255) NOT NULL,
    "message" VARCHAR(2000) NOT NULL,
    "message2" VARCHAR(2000),
    "message3" VARCHAR(2000),
    PRIMARY KEY ("w_mailmsg_id"),
    CHECK (IsActive in ('Y','N'))
);

CREATE UNIQUE INDEX "w_mailmsg_wstore" ON "w_mailmsg" ("w_store_id", "mailmsgtype");

-- ----------------------------------------------------------------------- 
-- W_MAILMSG_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_mailmsg_trl"
(
    "w_mailmsg_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "subject" VARCHAR(255) NOT NULL,
    "message" VARCHAR(2000) NOT NULL,
    "message2" VARCHAR(2000),
    "message3" VARCHAR(2000),
    PRIMARY KEY ("w_mailmsg_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

-- ----------------------------------------------------------------------- 
-- W_STORE 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_store"
(
    "w_store_id" INTEGER NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "name" VARCHAR(60) NOT NULL,
    "description" VARCHAR(255),
    "help" VARCHAR(2000),
    "wstoreemail" VARCHAR(60),
    "wstoreuser" VARCHAR(60),
    "wstoreuserpw" VARCHAR(20),
    "webinfo" VARCHAR(2000),
    "webparam1" VARCHAR(2000),
    "webparam2" VARCHAR(2000),
    "webparam3" VARCHAR(2000),
    "webparam4" VARCHAR(2000),
    "webparam5" VARCHAR(2000),
    "webparam6" VARCHAR(2000),
    "ismenuassets" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenuorders" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenuinvoices" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenushipments" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenupayments" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenurfqs" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenurequests" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenuinterests" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenuregistrations" CHAR(1) DEFAULT 'Y' NOT NULL,
    "ismenucontact" CHAR(1) DEFAULT 'Y' NOT NULL,
    "emailheader" VARCHAR(2000),
    "emailfooter" VARCHAR(2000),
    "salesrep_id" INTEGER NOT NULL,
    "m_warehouse_id" INTEGER NOT NULL,
    "m_pricelist_id" INTEGER NOT NULL,
    "webcontext" VARCHAR(20) NOT NULL,
    "weborderemail" VARCHAR(60),
    "c_paymentterm_id" INTEGER,
    "isdefault" CHAR(1) DEFAULT 'N' NOT NULL,
    "url" VARCHAR(120) DEFAULT 'http://localhost' NOT NULL,
    "stylesheet" VARCHAR(60),
    PRIMARY KEY ("w_store_id"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsMenuAssets in ('Y','N')),
    CHECK (IsMenuOrders in ('Y','N')),
    CHECK (IsMenuInvoices in ('Y','N')),
    CHECK (ISMENUSHIPMENTS='Y' OR ISMENUSHIPMENTS='N'),
    CHECK (ISMENUPAYMENTS='Y' OR ISMENUPAYMENTS='N'),
    CHECK (IsMenuRFQs in ('Y','N')),
    CHECK (IsMenuRequests in ('Y','N')),
    CHECK (IsMenuInterests in ('Y','N')),
    CHECK (IsMenuRegistrations in ('Y','N')),
    CHECK (IsMenuContact in ('Y','N'))
);

CREATE UNIQUE INDEX "w_store_webcontext" ON "w_store" ("webcontext");

-- ----------------------------------------------------------------------- 
-- W_STORE_TRL 
-- ----------------------------------------------------------------------- 

CREATE TABLE "w_store_trl"
(
    "w_store_id" INTEGER NOT NULL,
    "ad_language" VARCHAR(6) NOT NULL,
    "ad_client_id" INTEGER NOT NULL,
    "ad_org_id" INTEGER NOT NULL,
    "isactive" CHAR(1) DEFAULT 'Y' NOT NULL,
    "created" TIMESTAMP DEFAULT NOW() NOT NULL,
    "createdby" INTEGER NOT NULL,
    "updated" TIMESTAMP DEFAULT NOW() NOT NULL,
    "updatedby" INTEGER NOT NULL,
    "istranslated" CHAR(1) DEFAULT 'N' NOT NULL,
    "webinfo" VARCHAR(2000),
    "webparam1" VARCHAR(2000),
    "webparam2" VARCHAR(2000),
    "webparam3" VARCHAR(2000),
    "webparam4" VARCHAR(2000),
    "webparam5" VARCHAR(2000),
    "webparam6" VARCHAR(2000),
    "emailheader" VARCHAR(2000),
    "emailfooter" VARCHAR(2000),
    PRIMARY KEY ("w_store_id", "ad_language"),
    CHECK (IsActive in ('Y','N')),
    CHECK (IsTranslated in ('Y','N'))
);

