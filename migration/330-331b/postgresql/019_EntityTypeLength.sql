ALTER TABLE PA_COLORSCHEMA ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_TABLE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WF_NEXTCONDITION ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_VAL_RULE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_REPLICATIONTABLE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WORKFLOW ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_IMAGE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_ENTITYTYPE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_INFOCOLUMN ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_REFERENCE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_REF_LIST ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_TAB ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WF_NODENEXT ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WORKBENCHWINDOW ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WF_NODE_PARA ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_PROCESS ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_MENU ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_REPLICATIONSTRATEGY ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WF_RESPONSIBLE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WORKBENCH ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_COLUMN ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WF_NODE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_TASK ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_REPORTVIEW ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_REF_TABLE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_WINDOW ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE PA_MEASURECALC ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_FORM ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_MODIFICATION ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_INFOWINDOW ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_FIELD ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_ELEMENT ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_FIELDGROUP ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_MESSAGE ALTER COLUMN EntityType TYPE VARCHAR(40);
ALTER TABLE AD_PROCESS_PARA ALTER COLUMN EntityType TYPE VARCHAR(40);

UPDATE AD_COLUMN SET FieldLength = 40 WHERE ColumnName = 'EntityType'
   AND ad_table_id IN (
          SELECT ad_table_id
            FROM AD_TABLE
           WHERE tablename IN
                    ('AD_Ref_List',
                     'AD_WF_NextCondition',
                     'AD_Val_Rule',
                     'PA_MeasureCalc',
                     'AD_Menu',
                     'AD_InfoColumn',
                     'AD_WF_NodeNext',
                     'AD_WF_Node_Para',
                     'AD_Element',
                     'AD_Task',
                     'AD_Workbench',
                     'AD_EntityType',
                     'AD_Ref_Table',
                     'AD_Tab',
                     'AD_Field',
                     'AD_Process_Para',
                     'PA_ColorSchema',
                     'AD_Modification',
                     'AD_ReplicationStrategy',
                     'AD_ReplicationTable',
                     'AD_Image',
                     'AD_FieldGroup',
                     'AD_InfoWindow',
                     'AD_Table',
                     'AD_WF_Node',
                     'AD_WF_Responsible',
                     'AD_Form',
                     'AD_Window',
                     'AD_Column',
                     'AD_WorkbenchWindow',
                     'AD_Process',
                     'AD_ReportView',
                     'AD_Reference',
                     'AD_Message',
                     'AD_Workflow'
                    ));

UPDATE AD_FIELD SET DisplayLength = 20 WHERE AD_Column_ID IN
(SELECT AD_Column_ID FROM AD_COLUMN WHERE ColumnName = 'EntityType'
   AND ad_table_id IN (
          SELECT ad_table_id
            FROM AD_TABLE
           WHERE tablename IN
                    ('AD_Ref_List',
                     'AD_WF_NextCondition',
                     'AD_Val_Rule',
                     'PA_MeasureCalc',
                     'AD_Menu',
                     'AD_InfoColumn',
                     'AD_WF_NodeNext',
                     'AD_WF_Node_Para',
                     'AD_Element',
                     'AD_Task',
                     'AD_Workbench',
                     'AD_EntityType',
                     'AD_Ref_Table',
                     'AD_Tab',
                     'AD_Field',
                     'AD_Process_Para',
                     'PA_ColorSchema',
                     'AD_Modification',
                     'AD_ReplicationStrategy',
                     'AD_ReplicationTable',
                     'AD_Image',
                     'AD_FieldGroup',
                     'AD_InfoWindow',
                     'AD_Table',
                     'AD_WF_Node',
                     'AD_WF_Responsible',
                     'AD_Form',
                     'AD_Window',
                     'AD_Column',
                     'AD_WorkbenchWindow',
                     'AD_Process',
                     'AD_ReportView',
                     'AD_Reference',
                     'AD_Message',
                     'AD_Workflow'
                    )));

-- hide classpath field that is not implemented
UPDATE AD_FIELD
SET isdisplayed = 'N', isactive = 'N'
WHERE ad_field_id = 13498;

-- hide the register extension buttion that is not implemented
UPDATE AD_FIELD
SET isdisplayed = 'N', isactive = 'N'
WHERE ad_field_id = 13507;

COMMIT;
