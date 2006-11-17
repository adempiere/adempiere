/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Translation_Copy.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Copy Translation
 * Description:
 *	- Copy from translated tables of user "jjanke" and specific language "fr_FR"
 *  - see also AD_Language_Create
 ************************************************************************/

--	AD_Desktop_Trl
UPDATE AD_Desktop_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Desktop_Trl tt 
  WHERE t.AD_Desktop_ID=tt.AD_Desktop_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Desktop_Trl tt 
    WHERE t.AD_Desktop_ID=tt.AD_Desktop_ID AND t.AD_Language=tt.AD_Language);


--	AD_Element_Trl
UPDATE AD_Element_Trl t
SET (Name,PrintName,Description,Help,IsTranslated,PO_Name,PO_PrintName,PO_Description,PO_Help) =
  (SELECT tt.Name,tt.PrintName,tt.Description,tt.Help,tt.IsTranslated,tt.PO_Name,tt.PO_PrintName,tt.PO_Description,tt.PO_Help
  FROM jjanke.AD_Element_Trl tt 
  WHERE t.AD_Element_ID=tt.AD_Element_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Element_Trl tt 
    WHERE t.AD_Element_ID=tt.AD_Element_ID AND t.AD_Language=tt.AD_Language);

--	AD_FieldGroup
UPDATE AD_FieldGroup_Trl t
SET (Name,IsTranslated) =
  (SELECT tt.Name,tt.IsTranslated
  FROM jjanke.AD_FieldGroup_Trl tt 
  WHERE t.AD_FieldGroup_ID=tt.AD_FieldGroup_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_FieldGroup_Trl tt 
    WHERE t.AD_FieldGroup_ID=tt.AD_FieldGroup_ID AND t.AD_Language=tt.AD_Language);

--	AD_Field
UPDATE AD_Field_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Field_Trl tt 
  WHERE t.AD_Field_ID=tt.AD_Field_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Field_Trl tt 
    WHERE t.AD_Field_ID=tt.AD_Field_ID AND t.AD_Language=tt.AD_Language);

--	AD_Form
UPDATE AD_Form_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Form_Trl tt 
  WHERE t.AD_Form_ID=tt.AD_Form_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Form_Trl tt 
    WHERE t.AD_Form_ID=tt.AD_Form_ID AND t.AD_Language=tt.AD_Language);

--	AD_Menu
UPDATE AD_Menu_Trl t
SET (Name,Description,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.IsTranslated
  FROM jjanke.AD_Menu_Trl tt 
  WHERE t.AD_Menu_ID=tt.AD_Menu_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Menu_Trl tt 
    WHERE t.AD_Menu_ID=tt.AD_Menu_ID AND t.AD_Language=tt.AD_Language);

--	AD_Message
--ALTER TABLE jjanke.AD_Message_Trl ADD AD_Message_ID NUMBER(10);
--UPDATE jjanke.AD_Message_Trl t
--SET AD_Message_ID = (SELECT AD_Message_ID FROM AD_Message m WHERE m.Value=t.AD_Message)
--WHERE EXISTS (SELECT * FROM AD_Message m WHERE m.Value=t.AD_Message)
UPDATE AD_Message_Trl t
SET (MsgText, MsgTip ,IsTranslated) =
  (SELECT tt.MsgText, tt.MsgTip, tt.IsTranslated
  FROM jjanke.AD_Message_Trl tt 
  WHERE t.AD_Message_ID=tt.AD_Message_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Message_Trl tt 
    WHERE t.AD_Message_ID=tt.AD_Message_ID AND t.AD_Language=tt.AD_Language);

--	AD_Process
UPDATE AD_Process_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Process_Trl tt 
  WHERE t.AD_Process_ID=tt.AD_Process_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Process_Trl tt 
    WHERE t.AD_Process_ID=tt.AD_Process_ID AND t.AD_Language=tt.AD_Language);

--	AD_Process_Para
UPDATE AD_Process_Para_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Process_Para_Trl tt 
  WHERE t.AD_Process_Para_ID=tt.AD_Process_Para_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Process_Para_Trl tt 
    WHERE t.AD_Process_Para_ID=tt.AD_Process_Para_ID AND t.AD_Language=tt.AD_Language);

--	AD_Reference
UPDATE AD_Reference_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Reference_Trl tt 
  WHERE t.AD_Reference_ID=tt.AD_Reference_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Reference_Trl tt 
    WHERE t.AD_Reference_ID=tt.AD_Reference_ID AND t.AD_Language=tt.AD_Language);

--	AD_Ref_List
UPDATE AD_Ref_List_Trl t
SET (Name,Description,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.IsTranslated
  FROM jjanke.AD_Ref_List_Trl tt 
  WHERE t.AD_Ref_List_ID=tt.AD_Ref_List_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Ref_List_Trl tt 
    WHERE t.AD_Ref_List_ID=tt.AD_Ref_List_ID AND t.AD_Language=tt.AD_Language);

--	AD_Tab
UPDATE AD_Tab_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Tab_Trl tt 
  WHERE t.AD_Tab_ID=tt.AD_Tab_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Tab_Trl tt 
    WHERE t.AD_Tab_ID=tt.AD_Tab_ID AND t.AD_Language=tt.AD_Language);

--	AD_Task
UPDATE AD_Task_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Task_Trl tt 
  WHERE t.AD_Task_ID=tt.AD_Task_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Task_Trl tt 
    WHERE t.AD_Task_ID=tt.AD_Task_ID AND t.AD_Language=tt.AD_Language);

--	AD_Workflow
UPDATE AD_Workflow_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Workflow_Trl tt 
  WHERE t.AD_Workflow_ID=tt.AD_Workflow_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Workflow_Trl tt 
    WHERE t.AD_Workflow_ID=tt.AD_Workflow_ID AND t.AD_Language=tt.AD_Language);

--	AD_WF_Node
UPDATE AD_WF_Node_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_WF_Node_Trl tt 
  WHERE t.AD_WF_Node_ID=tt.AD_WF_Node_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_WF_Node_Trl tt 
    WHERE t.AD_WF_Node_ID=tt.AD_WF_Node_ID AND t.AD_Language=tt.AD_Language);

--	AD_Window
UPDATE AD_Window_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Window_Trl tt 
  WHERE t.AD_Window_ID=tt.AD_Window_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Window_Trl tt 
    WHERE t.AD_Window_ID=tt.AD_Window_ID AND t.AD_Language=tt.AD_Language);

--	AD_Workbench
UPDATE AD_Workbench_Trl t
SET (Name,Description,Help,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.Help,tt.IsTranslated
  FROM jjanke.AD_Workbench_Trl tt 
  WHERE t.AD_Workbench_ID=tt.AD_Workbench_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.AD_Workbench_Trl tt 
    WHERE t.AD_Workbench_ID=tt.AD_Workbench_ID AND t.AD_Language=tt.AD_Language);

-------------------------------------------------------------------------------

--	C_DocType
UPDATE C_DocType_Trl t
SET (Name,PrintName,DocumentNote,IsTranslated) =
  (SELECT tt.Name,tt.PrintName,tt.DocumentNote,tt.IsTranslated
  FROM jjanke.C_DocType_Trl tt 
  WHERE t.C_DocType_ID=tt.C_DocType_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.C_DocType_Trl tt 
    WHERE t.C_DocType_ID=tt.C_DocType_ID AND t.AD_Language=tt.AD_Language);

UPDATE C_DocType_Trl t
SET (AD_Client_ID,AD_Org_ID,Name,PrintName,DocumentNote) =
  (SELECT AD_Client_ID,AD_Org_ID,Name,PrintName,DocumentNote 
    FROM C_DocType d WHERE t.C_DocType_ID=d.C_DocType_ID)
WHERE EXISTS
    (SELECT * FROM C_DocType d WHERE t.C_DocType_ID=d.C_DocType_ID);


--	C_DunningLevel
UPDATE C_DunningLevel_Trl t
SET (PrintName,Note,IsTranslated) =
  (SELECT tt.PrintName,tt.Note,tt.IsTranslated
  FROM jjanke.C_DunningLevel_Trl tt 
  WHERE t.C_DunningLevel_ID=tt.C_DunningLevel_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.C_DunningLevel_Trl tt 
    WHERE t.C_DunningLevel_ID=tt.C_DunningLevel_ID AND t.AD_Language=tt.AD_Language);

UPDATE C_DunningLevel_Trl t
SET (AD_Client_ID,AD_Org_ID,PrintName,Note) =
  (SELECT AD_Client_ID,AD_Org_ID,tt.PrintName,tt.Note
    FROM C_DunningLevel tt WHERE t.C_DunningLevel_ID=tt.C_DunningLevel_ID)
WHERE EXISTS
    (SELECT * FROM C_DunningLevel tt WHERE t.C_DunningLevel_ID=tt.C_DunningLevel_ID);


--	C_ElementValue
UPDATE C_ElementValue_Trl t
SET (Name,Description,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.IsTranslated
  FROM jjanke.C_ElementValue_Trl tt 
  WHERE t.C_ElementValue_ID=tt.C_ElementValue_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.C_ElementValue_Trl tt 
    WHERE t.C_ElementValue_ID=tt.C_ElementValue_ID AND t.AD_Language=tt.AD_Language);

UPDATE C_ElementValue_Trl t
SET (AD_Client_ID,AD_Org_ID,Name,Description) =
  (SELECT AD_Client_ID,AD_Org_ID,tt.Name,tt.Description
  FROM C_ElementValue tt 
  WHERE t.C_ElementValue_ID=tt.C_ElementValue_ID)
WHERE EXISTS 
    (SELECT * FROM C_ElementValue tt WHERE t.C_ElementValue_ID=tt.C_ElementValue_ID);


--	C_Greeting
UPDATE C_Greeting_Trl t
SET (Name,Greeting,IsTranslated) =
  (SELECT tt.Name,tt.Greeting,tt.IsTranslated
  FROM jjanke.C_Greeting_Trl tt 
  WHERE t.C_Greeting_ID=tt.C_Greeting_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.C_Greeting_Trl tt 
    WHERE t.C_Greeting_ID=tt.C_Greeting_ID AND t.AD_Language=tt.AD_Language);

UPDATE C_Greeting_Trl t
SET (AD_Client_ID,AD_Org_ID,Name,Greeting) =
  (SELECT AD_Client_ID,AD_Org_ID,tt.Name,tt.Greeting
  FROM C_Greeting tt 
  WHERE t.C_Greeting_ID=tt.C_Greeting_ID)
WHERE EXISTS 
    (SELECT * FROM C_Greeting tt WHERE t.C_Greeting_ID=tt.C_Greeting_ID);


--	C_PaymentTerm
UPDATE C_PaymentTerm_Trl t
SET (Name,Description,DocumentNote,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.DocumentNote,tt.IsTranslated
  FROM jjanke.C_PaymentTerm_Trl tt 
  WHERE t.C_PaymentTerm_ID=tt.C_PaymentTerm_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.C_PaymentTerm_Trl tt 
    WHERE t.C_PaymentTerm_ID=tt.C_PaymentTerm_ID AND t.AD_Language=tt.AD_Language);

UPDATE C_PaymentTerm_Trl t
SET (AD_Client_ID,AD_Org_ID,Name,Description,DocumentNote) =
  (SELECT AD_Client_ID,AD_Org_ID,tt.Name,tt.Description,tt.DocumentNote
  FROM C_PaymentTerm tt 
  WHERE t.C_PaymentTerm_ID=tt.C_PaymentTerm_ID)
WHERE EXISTS 
    (SELECT * FROM C_PaymentTerm tt WHERE t.C_PaymentTerm_ID=tt.C_PaymentTerm_ID);


--	C_TaxCategory
UPDATE C_TaxCategory_Trl t
SET (Name,Description,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.IsTranslated
  FROM jjanke.C_TaxCategory_Trl tt 
  WHERE t.C_TaxCategory_ID=tt.C_TaxCategory_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.C_TaxCategory_Trl tt 
    WHERE t.C_TaxCategory_ID=tt.C_TaxCategory_ID AND t.AD_Language=tt.AD_Language);

UPDATE C_TaxCategory_Trl t
SET (AD_Client_ID,AD_Org_ID,Name,Description) =
  (SELECT AD_Client_ID,AD_Org_ID,tt.Name,tt.Description
  FROM C_TaxCategory tt 
  WHERE t.C_TaxCategory_ID=tt.C_TaxCategory_ID)
WHERE EXISTS 
    (SELECT * FROM C_TaxCategory tt WHERE t.C_TaxCategory_ID=tt.C_TaxCategory_ID);


--	C_UOM
UPDATE C_UOM_Trl t
SET (Name,Description,UOMSymbol,IsTranslated) =
  (SELECT tt.Name,tt.Description,tt.UOMSymbol,tt.IsTranslated
  FROM jjanke.C_UOM_Trl tt 
  WHERE t.C_UOM_ID=tt.C_UOM_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.C_UOM_Trl tt 
    WHERE t.C_UOM_ID=tt.C_UOM_ID AND t.AD_Language=tt.AD_Language);

UPDATE C_UOM_Trl t
SET (AD_Client_ID,AD_Org_ID,Name,Description,UOMSymbol) =
  (SELECT AD_Client_ID,AD_Org_ID,tt.Name,tt.Description,tt.UOMSymbol
  FROM C_UOM tt 
  WHERE t.C_UOM_ID=tt.C_UOM_ID)
WHERE EXISTS 
    (SELECT * FROM C_UOM tt WHERE t.C_UOM_ID=tt.C_UOM_ID);


/**
--	M_Product
UPDATE M_Product_Trl t
SET (Name,DocumentNote,IsTranslated) =
  (SELECT tt.Name,tt.DocumentNote,tt.IsTranslated
  FROM jjanke.M_Product_Trl tt 
  WHERE t.M_Product_ID=tt.M_Product_ID AND t.AD_Language=tt.AD_Language)
WHERE t.AD_Language='fr_FR'
  AND EXISTS (SELECT * FROM jjanke.M_Product_Trl tt 
    WHERE t.M_Product_ID=tt.M_Product_ID AND t.AD_Language=tt.AD_Language);
**/

--
COMMIT;

