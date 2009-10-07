-- Oct 7, 2009 8:06:26 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Element SET Description='Workflow Node (activity), step or process', Help='The Workflow Node indicates a unique step or process in a Workflow.',Updated=TO_TIMESTAMP('2009-10-07 08:06:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53285
;

-- Oct 7, 2009 8:06:26 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53285
;

-- Oct 7, 2009 8:06:26 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Column SET ColumnName='PP_Order_Node_ID', Name='Manufacturing Order Activity', Description='Workflow Node (activity), step or process', Help='The Workflow Node indicates a unique step or process in a Workflow.' WHERE AD_Element_ID=53285
;

-- Oct 7, 2009 8:06:26 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Process_Para SET ColumnName='PP_Order_Node_ID', Name='Manufacturing Order Activity', Description='Workflow Node (activity), step or process', Help='The Workflow Node indicates a unique step or process in a Workflow.', AD_Element_ID=53285 WHERE UPPER(ColumnName)='PP_ORDER_NODE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 7, 2009 8:06:26 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Process_Para SET ColumnName='PP_Order_Node_ID', Name='Manufacturing Order Activity', Description='Workflow Node (activity), step or process', Help='The Workflow Node indicates a unique step or process in a Workflow.' WHERE AD_Element_ID=53285 AND IsCentrallyMaintained='Y'
;

-- Oct 7, 2009 8:06:26 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Field SET Name='Manufacturing Order Activity', Description='Workflow Node (activity), step or process', Help='The Workflow Node indicates a unique step or process in a Workflow.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53285) AND IsCentrallyMaintained='Y'
;

-- Oct 7, 2009 8:08:23 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Column SET Help='The Workflow Node indicates a unique step or process in a Workflow.', Description='Workflow Node (activity), step or process', Name='Manufacturing Order Activity', IsUpdateable='N', AD_Element_ID=53285, ColumnName='PP_Order_Node_ID',Updated=TO_TIMESTAMP('2009-10-07 08:08:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57204
;

-- Oct 7, 2009 8:08:23 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=57204
;

-- Oct 7, 2009 8:08:23 AM EST
-- Fix PPOrderNodeTrl - Column AD_WF_Node_ID to PP_Order_Node_ID
UPDATE AD_Field SET Name='Manufacturing Order Activity', Description='Workflow Node (activity), step or process', Help='The Workflow Node indicates a unique step or process in a Workflow.' WHERE AD_Column_ID=57204 AND IsCentrallyMaintained='Y'
;

