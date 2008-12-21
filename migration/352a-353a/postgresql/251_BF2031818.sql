-- Jul 31, 2008 9:16:30 PM COT
-- [ 2031818 ] AD_Element.ColumnName length should be 30
UPDATE AD_Column SET FieldLength=30,Updated=TO_TIMESTAMP('2008-07-31 21:16:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11819
;

insert into t_alter_column values('a_registrationattribute','ColumnName','VARCHAR(30)',null,'NULL')
;

UPDATE AD_Column SET FieldLength=30,Updated=TO_TIMESTAMP('2008-07-31 21:16:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=4017
;

insert into t_alter_column values('ad_process_para','ColumnName','VARCHAR(30)',null,'NULL')
;

UPDATE AD_Column SET FieldLength=30,Updated=TO_TIMESTAMP('2008-07-31 21:17:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2602
;

insert into t_alter_column values('ad_element','ColumnName','VARCHAR(30)',null,'NULL')
;

UPDATE AD_Column SET FieldLength=30,Updated=TO_TIMESTAMP('2008-07-31 21:17:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=116
;

insert into t_alter_column values('ad_column','ColumnName','VARCHAR(30)',null,'NULL')
;

