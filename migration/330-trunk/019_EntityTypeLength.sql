ALTER TABLE AD_Ref_List MODIFY EntityType Varchar2(40);
ALTER TABLE AD_WF_NextCondition MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Val_Rule MODIFY EntityType Varchar2(40);
ALTER TABLE PA_MeasureCalc MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Menu MODIFY EntityType Varchar2(40);
ALTER TABLE AD_InfoColumn MODIFY EntityType Varchar2(40);
ALTER TABLE AD_WF_NodeNext MODIFY EntityType Varchar2(40);
ALTER TABLE AD_WF_Node_Para MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Element MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Task MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Workbench MODIFY EntityType Varchar2(40);
ALTER TABLE AD_EntityType MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Ref_Table MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Tab MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Field MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Process_Para MODIFY EntityType Varchar2(40);
ALTER TABLE PA_ColorSchema MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Modification MODIFY EntityType Varchar2(40);
ALTER TABLE AD_ReplicationStrategy MODIFY EntityType Varchar2(40);
ALTER TABLE AD_ReplicationTable MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Image MODIFY EntityType Varchar2(40);
ALTER TABLE AD_FieldGroup MODIFY EntityType Varchar2(40);
ALTER TABLE AD_InfoWindow MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Table MODIFY EntityType Varchar2(40);
ALTER TABLE AD_WF_Node MODIFY EntityType Varchar2(40);
ALTER TABLE AD_WF_Responsible MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Form MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Window MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Column MODIFY EntityType Varchar2(40);
ALTER TABLE AD_WorkbenchWindow MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Process MODIFY EntityType Varchar2(40);
ALTER TABLE AD_ReportView MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Reference MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Message MODIFY EntityType Varchar2(40);
ALTER TABLE AD_Workflow MODIFY EntityType Varchar2(40);

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

