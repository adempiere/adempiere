-- 13.07.2009 10:10:01 EEST
-- Adding new column in PP_Cost_Collector
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2009-07-13 10:10:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53721
;

-- 13.07.2009 10:10:37 EEST
-- Adding new column in PP_Cost_Collector
insert into t_alter_column values('pp_order_workflow','ValidateWorkflow','CHAR(1)',null,'NULL')
;

-- 13.07.2009 10:10:38 EEST
-- Adding new column in PP_Cost_Collector
insert into t_alter_column values('pp_order_workflow','ValidateWorkflow',null,'NULL',null)
;

