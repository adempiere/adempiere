ALTER  TABLE AD_Menu RENAME COLUMN Action to ActionRun;
ALTER  TABLE AD_WF_Node RENAME COLUMN Action to ActionRun;
ALTER  TABLE AD_WF_Node RENAME COLUMN Limit to DurationLimit;
--ALTER  TABLE MPC_Order_Node RENAME COLUMN Action to ActionRun;
ALTER  TABLE AD_Workflow RENAME COLUMN Limit to DurationLimit;
--select table_name,column_name,data_type,data_length,
--                            data_precision,data_scale,nullable,column_id
--                from user_tab_columns WHERE COLUMN_NAME = 'Action';