ALTER TABLE AD_REF_LIST MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WF_NEXTCONDITION MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_VAL_RULE MODIFY entitytype VARCHAR2(40);

ALTER TABLE PA_MEASURECALC MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_MENU MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_INFOCOLUMN MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WF_NODENEXT MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WF_NODE_PARA MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_ELEMENT MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_TASK MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WORKBENCH MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_ENTITYTYPE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_REF_TABLE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_TAB MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_FIELD MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_PROCESS_PARA MODIFY entitytype VARCHAR2(40);

ALTER TABLE PA_COLORSCHEMA MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_MODIFICATION MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_REPLICATIONSTRATEGY MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_REPLICATIONTABLE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_IMAGE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_FIELDGROUP MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_INFOWINDOW MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_TABLE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WF_NODE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WF_RESPONSIBLE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_FORM MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WINDOW MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_COLUMN MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WORKBENCHWINDOW MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_PROCESS MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_REPORTVIEW MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_REFERENCE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_MESSAGE MODIFY entitytype VARCHAR2(40);

ALTER TABLE AD_WORKFLOW MODIFY entitytype VARCHAR2(40);

UPDATE AD_COLUMN
   SET fieldlength = 40
 WHERE columnname = 'EntityType'
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

UPDATE AD_FIELD
   SET displaylength = 20
 WHERE ad_column_id IN (
          SELECT ad_column_id
            FROM AD_COLUMN
           WHERE columnname = 'EntityType'
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
   SET isdisplayed = 'N',
       isactive = 'N'
 WHERE ad_field_id = 13498;

-- hide the register extension button that is not implemented
UPDATE AD_FIELD
   SET isdisplayed = 'N',
       isactive = 'N'
 WHERE ad_field_id = 13507;

COMMIT ;
