-- Jul 13, 2009 2:22:40 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2009-07-13 14:22:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57921
;

-- Jul 13, 2009 2:23:07 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
insert into t_alter_column values('pa_goal','ChartType','VARCHAR(2)',null,'BC')
;

-- Jul 13, 2009 2:23:10 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
UPDATE PA_Goal SET ChartType='BC' WHERE ChartType IS NULL
;

-- Jul 13, 2009 2:23:10 PM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
insert into t_alter_column values('pa_goal','ChartType',null,'NOT NULL',null)
;

