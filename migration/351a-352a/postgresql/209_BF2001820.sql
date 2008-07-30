-- Jun 25, 2008 9:00:46 AM EST
-- BF2001820 
insert into t_alter_column values('pp_order_workflow','EntityType','VARCHAR(40)',null,'U')
;

-- Jun 25, 2008 9:00:47 AM EST
-- BF2001820 
UPDATE PP_Order_Workflow SET EntityType='U' WHERE EntityType IS NULL
;

