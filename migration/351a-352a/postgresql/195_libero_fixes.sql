-- 03.06.2008 13:08:34 EEST
-- Fix AD_WF_Node.C_BPartner_ID reference
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 13:08:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53309
;

-- 03.06.2008 16:25:28 EEST
-- Fix PP_Product_Planning.AD_Workflow_ID : set AD_Val_Rule_ID=MFG WF
UPDATE AD_Column SET AD_Val_Rule_ID=52003,Updated=TO_TIMESTAMP('2008-06-03 16:25:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53377
;

-- Create Product Planning unique contraint:
create unique index PP_Product_Planning_UQ on PP_Product_Planning (M_Product_ID, S_Resource_ID, M_Warehouse_ID, AD_Org_ID);

-- 03.06.2008 18:16:22 EEST
-- PP_Order_Node.A_Asset_ID set to Search
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:16:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53743
;

-- 03.06.2008 18:17:53 EEST
-- PP_Order_Node.C_BPartner_ID set to Search
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:17:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53460
;

-- 03.06.2008 18:18:34 EEST
-- PP_MRP.C_BPartner_ID set to Search
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:18:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54025
;


--
-- M_Product_ID fields in Manufacturing tables should have AD_Reference_ID=Search
UPDATE AD_Column SET AD_Reference_ID=30, IsUpdateable='N',Updated=TO_TIMESTAMP('2008-06-03 18:20:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53389
;
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:20:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53549
;
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:20:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53610
;
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:20:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53733
;
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:20:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53763
;
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53993
;
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_TIMESTAMP('2008-06-03 18:21:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54043
;

