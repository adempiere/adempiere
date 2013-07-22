-- Jun 17, 2013 4:44:12 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2013-06-17 16:44:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100
WHERE AD_Element_ID=1682 AND ColumnName='EntityType'
;
commit;


INSERT INTO t_alter_column values('AD_Table','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Column','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Reference','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Ref_Table','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Ref_List','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Window','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Tab','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Field','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Val_Rule','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Message','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Menu','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Workflow','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Task','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_WF_Node','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_WF_NodeNext','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Element','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Process','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Process_Para','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_ReportView','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Form','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_FieldGroup','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('PA_MeasureCalc','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Image','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Workbench','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_WorkbenchWindow','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_ReplicationTable','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_ReplicationStrategy','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_WF_Node_Para','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_WF_Responsible','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_WF_NextCondition','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('PA_ColorSchema','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_EntityType','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Modification','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_InfoWindow','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_InfoColumn','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_SysConfig','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_ModelValidator','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('PP_WF_Node_Product','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('PP_Order_Node','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('PP_Order_NodeNext','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('PP_Order_Workflow','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Rule','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Migration','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Browse_Field','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_Browse','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_View','EntityType','VARCHAR(255)',null,null);
INSERT INTO t_alter_column values('AD_View_Column','EntityType','VARCHAR(255)',null,null);



/* Built with:
select t.TableName
	, 'INSERT INTO t_alter_column values('''||t.TableName||''',''EntityType'',''VARCHAR(255)'',null,null);' as sql_pg
	, 'ALTER TABLE '||t.tableName||' MODIFY EntityType VARCHAR2(255);' as sql_oracle
from AD_Column c
inner join AD_Table t on (t.AD_Table_ID=c.AD_Table_ID)
where c.AD_Element_ID=1682 and c.ColumnName='EntityType'
;
*/
