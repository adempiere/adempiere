Alter table AD_Ref_Table add column isalert character(1) DEFAULT 'N'::bpchar NOT NULL;

Alter table AD_Ref_Table add column displaysql text;

Alter table AD_Ref_Table add column isdisplayidentifier character(1) DEFAULT 'N'::bpchar NOT NULL;
