ALTER TABLE PA_ColorSchema ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Table ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_WF_NextCondition ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Val_Rule ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_ReplicationTable ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Workflow ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Image ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_EntityType ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_InfoColumn ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Reference ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Ref_List ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Tab ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_WF_NodeNext ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_WorkbenchWindow ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_WF_Node_Para ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Process ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Menu ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_ReplicationStrategy ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_WF_Responsible ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Workbench ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Column ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_WF_Node ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Task ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_ReportView ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Ref_Table ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Window ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE PA_MeasureCalc ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Form ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Modification ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_InfoWindow ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Field ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Element ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_FieldGroup ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Message ALTER COLUMN EntityType TYPE Varchar(40);
ALTER TABLE AD_Process_Para ALTER COLUMN EntityType TYPE Varchar(40);

UPDATE AD_Column SET FieldLength = 40 WHERE ColumnName = 'EntityType';

UPDATE AD_Field Set DisplayLength = 40 WHERE AD_Column_ID IN
(SELECT AD_Column_ID FROM AD_Column WHERE ColumnName = 'EntityType');

-- hide classpath field that is not implemented
UPDATE ad_field
SET isdisplayed = 'N', isactive = 'N'
WHERE ad_field_id = 13498

-- hide the register extension buttion that is not implemented
UPDATE ad_field
SET isdisplayed = 'N', isactive = 'N'
WHERE ad_field_id = 13507

COMMIT;
