--  Translation Status

SELECT IsTranslated, count(*) "AD_Message_Trl" from AD_Message_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Menu_Trl" from AD_Menu_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Window_Trl" from AD_Window_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Tab_Trl" from AD_Tab_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Field_Trl" from AD_Field_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Reference_Trl" from AD_Reference_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Ref_List_Trl" from AD_Ref_List_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Workflow_Trl" from AD_Workflow_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_WF_Node_Trl" from AD_WF_Node_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Element_Trl" from AD_Element_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Process_Trl" from AD_Process_Trl
GROUP BY IsTranslated;
SELECT IsTranslated, count(*) "AD_Process_Para_Trl" from AD_Process_Para_Trl
GROUP BY IsTranslated;


