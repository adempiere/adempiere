-- Aug 4, 2008 5:26:52 PM CDT
-- DRP Functionality
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-04 17:26:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53869
;

-- Aug 4, 2008 5:27:00 PM CDT
-- DRP Functionality
insert into t_alter_column values('dd_order','C_BPartner_ID','NUMERIC(10)',null,'NULL')
;

-- Aug 4, 2008 5:27:00 PM CDT
-- DRP Functionality
insert into t_alter_column values('dd_order','C_BPartner_ID',null,'NOT NULL',null)
;

-- Aug 4, 2008 5:27:25 PM CDT
-- DRP Functionality
insert into t_alter_column values('pp_order_node','EntityType','VARCHAR(40)',null,'U')
;

-- Aug 4, 2008 5:27:27 PM CDT
-- DRP Functionality
UPDATE PP_Order_Node SET EntityType='U' WHERE EntityType IS NULL
;

-- Aug 4, 2008 5:27:46 PM CDT
-- DRP Functionality
insert into t_alter_column values('pp_order_nodenext','EntityType','VARCHAR(40)',null,'U')
;

-- Aug 4, 2008 5:27:47 PM CDT
-- DRP Functionality
UPDATE PP_Order_NodeNext SET EntityType='U' WHERE EntityType IS NULL
;

-- Aug 4, 2008 5:29:27 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-04 17:29:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53043
;

