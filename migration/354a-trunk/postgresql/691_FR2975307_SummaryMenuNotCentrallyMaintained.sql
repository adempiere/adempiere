-- FR [2975307] - AD_Menu_Trl overwritten by AD_Window
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2975307&group_id=176962&atid=879335
-- Summary Menu items must not be Centrally maintained as they can't be synchronized with any other element.

UPDATE AD_Menu 
SET IsCentrallyMaintained = 'N' 
WHERE AD_Menu_ID IN 
 (SELECT AD_Menu_ID FROM AD_Menu 
  WHERE (AD_Window_ID  IS NULL 
    AND AD_Process_ID  IS NULL 
    AND AD_Form_ID     IS NULL 
    AND AD_Workflow_ID IS NULL 
    AND AD_Task_ID     IS NULL)
 )
;