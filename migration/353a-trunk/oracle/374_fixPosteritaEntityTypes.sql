update AD_COLUMN set entitytype = 'D' where entitytype in ('A','U') and AD_COLUMN_id < 1000000;

update AD_ELEMENT set entitytype = 'D' where entitytype in ('A','U') and AD_ELEMENT_id < 1000000;

update AD_FIELD set entitytype = 'D' where entitytype in ('A','U') and AD_FIELD_id < 1000000;

update AD_MENU set entitytype = 'D' where entitytype in ('A','U') and AD_MENU_id < 1000000;

update AD_MESSAGE set entitytype = 'D' where entitytype in ('A','U') and AD_MESSAGE_id < 1000000;

update AD_REFERENCE set entitytype = 'D' where entitytype in ('A','U') and AD_REFERENCE_id < 1000000;

update AD_REF_LIST set entitytype = 'D' where entitytype in ('A','U') and AD_REFERENCE_id < 1000000;

update AD_REF_TABLE set entitytype = 'D' where entitytype in ('A','U') and AD_REFERENCE_id < 1000000;

update AD_TAB set entitytype = 'D' where entitytype in ('A','U') and AD_TAB_id < 1000000;

update AD_TABLE set entitytype = 'D' where entitytype in ('A','U') and AD_TABLE_id < 1000000;

update AD_VAL_RULE set entitytype = 'D' where entitytype in ('A','U') and AD_VAL_RULE_id < 1000000;

update AD_WINDOW set entitytype = 'D' where entitytype in ('A','U') and AD_WINDOW_id < 1000000;
