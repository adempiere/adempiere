-- Sep 30, 2009 4:35:13 PM COT
-- Fix 2871006-TableName must be fully qualified in Tab WhereClause
UPDATE AD_Tab SET WhereClause='AD_Column.AD_Reference_Value_ID=@AD_Reference_ID@',Updated=TO_TIMESTAMP('2009-09-30 16:35:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=773
;

-- Sep 30, 2009 4:36:08 PM COT
UPDATE AD_Tab SET WhereClause='(AD_Note.AD_User_ID IN (0,@#AD_User_ID@) OR AD_Note.AD_User_ID IS NULL)',Updated=TO_TIMESTAMP('2009-09-30 16:36:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=325
;

-- Sep 30, 2009 4:36:44 PM COT
UPDATE AD_Tab SET WhereClause='(AD_Note.AD_User_ID IN (0,@#AD_User_ID@) OR AD_Note.AD_User_ID IS NULL) AND EXISTS (SELECT 1 FROM AD_Message m WHERE m.AD_Message_ID=AD_Note.AD_Message_ID AND m.Value LIKE ''MRP-%'')',Updated=TO_TIMESTAMP('2009-09-30 16:36:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53035
;

-- Sep 30, 2009 4:37:37 PM COT
UPDATE AD_Tab SET WhereClause='AD_Workflow.WorkflowType=''M'' OR AD_Workflow.WorkflowType=''Q''',Updated=TO_TIMESTAMP('2009-09-30 16:37:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53027
;

-- Sep 30, 2009 4:37:55 PM COT
UPDATE AD_Tab SET WhereClause='C_BPartner.IsEmployee = ''Y''',Updated=TO_TIMESTAMP('2009-09-30 16:37:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53104
;

-- Sep 30, 2009 4:38:17 PM COT
UPDATE AD_Tab SET WhereClause='C_Invoice.IsPaid=''N'' AND C_Invoice.IsSOTrx=''Y''',Updated=TO_TIMESTAMP('2009-09-30 16:38:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=501
;

-- Sep 30, 2009 4:38:36 PM COT
UPDATE AD_Tab SET WhereClause='C_Invoice.IsSOTrx=''N''',Updated=TO_TIMESTAMP('2009-09-30 16:38:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=290
;

-- Sep 30, 2009 4:38:50 PM COT
UPDATE AD_Tab SET WhereClause='C_Invoice.IsSOTrx=''Y''',Updated=TO_TIMESTAMP('2009-09-30 16:38:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=263
;

-- Sep 30, 2009 4:39:05 PM COT
UPDATE AD_Tab SET WhereClause='C_Order.IsSOTrx=''N''',Updated=TO_TIMESTAMP('2009-09-30 16:39:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=294
;

-- Sep 30, 2009 4:39:18 PM COT
UPDATE AD_Tab SET WhereClause='C_Order.IsSOTrx=''Y''',Updated=TO_TIMESTAMP('2009-09-30 16:39:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=186
;

-- Sep 30, 2009 4:39:38 PM COT
UPDATE AD_Tab SET WhereClause='C_Payment.C_BPartner_ID IS NOT NULL',Updated=TO_TIMESTAMP('2009-09-30 16:39:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=330
;

-- Sep 30, 2009 4:40:12 PM COT
UPDATE AD_Tab SET WhereClause='C_ProjectLine.C_ProjectPhase_ID > 0 AND COALESCE(C_ProjectLine.C_ProjectTask_ID,0)=0',Updated=TO_TIMESTAMP('2009-09-30 16:40:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=799
;

-- Sep 30, 2009 4:40:23 PM COT
UPDATE AD_Tab SET WhereClause='C_ProjectLine.C_ProjectTask_ID > 0',Updated=TO_TIMESTAMP('2009-09-30 16:40:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=796
;

-- Sep 30, 2009 4:40:48 PM COT
UPDATE AD_Tab SET WhereClause='COALESCE(C_ProjectLine.C_ProjectTask_ID,0)=0 AND COALESCE(C_ProjectLine.C_ProjectPhase_ID,0)=0',Updated=TO_TIMESTAMP('2009-09-30 16:40:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=797
;

-- Sep 30, 2009 4:41:04 PM COT
UPDATE AD_Tab SET WhereClause='C_Tax.IsSummary=''Y''',Updated=TO_TIMESTAMP('2009-09-30 16:41:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53079
;

-- Sep 30, 2009 4:41:28 PM COT
UPDATE AD_Tab SET WhereClause='C_Tax.IsSummary=''N'' AND C_Tax.Parent_Tax_ID=@C_Tax_ID@',Updated=TO_TIMESTAMP('2009-09-30 16:41:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53078
;

-- Sep 30, 2009 4:41:49 PM COT
UPDATE AD_Tab SET WhereClause='M_DiscountSchema.DiscountType<>''P''',Updated=TO_TIMESTAMP('2009-09-30 16:41:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=404
;

-- Sep 30, 2009 4:42:02 PM COT
UPDATE AD_Tab SET WhereClause='M_DiscountSchema.DiscountType=''P''',Updated=TO_TIMESTAMP('2009-09-30 16:42:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=675
;

-- Sep 30, 2009 4:42:17 PM COT
UPDATE AD_Tab SET WhereClause='M_InOut.MovementType IN (''C-'')',Updated=TO_TIMESTAMP('2009-09-30 16:42:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=257
;

-- Sep 30, 2009 4:42:31 PM COT
UPDATE AD_Tab SET WhereClause='M_InOut.MovementType IN (''C+'')',Updated=TO_TIMESTAMP('2009-09-30 16:42:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53271
;

-- Sep 30, 2009 4:42:42 PM COT
UPDATE AD_Tab SET WhereClause='M_InOut.MovementType IN (''V-'')',Updated=TO_TIMESTAMP('2009-09-30 16:42:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53276
;

-- Sep 30, 2009 4:42:53 PM COT
UPDATE AD_Tab SET WhereClause='M_InOut.MovementType IN (''V+'')',Updated=TO_TIMESTAMP('2009-09-30 16:42:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=296
;

-- Sep 30, 2009 4:43:48 PM COT
UPDATE AD_Tab SET WhereClause='(M_Product.ProductType=''I'' OR M_Product.ProductType=''E'')',Updated=TO_TIMESTAMP('2009-09-30 16:43:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53044
;

-- Sep 30, 2009 4:44:07 PM COT
UPDATE AD_Tab SET WhereClause='M_RMA.IsSOTrx=''N''',Updated=TO_TIMESTAMP('2009-09-30 16:44:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53280
;

-- Sep 30, 2009 4:44:23 PM COT
UPDATE AD_Tab SET WhereClause='M_RMA.IsSOTrx=''Y''',Updated=TO_TIMESTAMP('2009-09-30 16:44:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=628
;

-- Sep 30, 2009 4:44:38 PM COT
UPDATE AD_Tab SET WhereClause='PP_Cost_Collector.CostCollectorType=''160''',Updated=TO_TIMESTAMP('2009-09-30 16:44:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53049
;

-- Sep 30, 2009 4:45:30 PM COT
UPDATE AD_Tab SET WhereClause='PP_Order_BOMLine.PP_Order_ID=@PP_Order_ID@ AND PP_Order_BOMLine.PP_Order_BOM_ID=@PP_Order_BOM_ID@',Updated=TO_TIMESTAMP('2009-09-30 16:45:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53039
;

-- Sep 30, 2009 4:46:14 PM COT
UPDATE AD_Tab SET WhereClause='(R_Request.SalesRep_ID=@#AD_User_ID@ OR R_Request.AD_Role_ID=@#AD_Role_ID@) AND (R_Request.R_Status_ID IS NULL OR R_Request.R_Status_ID IN (SELECT R_Status_ID FROM R_Status WHERE IsClosed=''N''))',Updated=TO_TIMESTAMP('2009-09-30 16:46:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=344
;

-- Sep 30, 2009 4:46:29 PM COT
UPDATE AD_Tab SET WhereClause='RV_Unprocessed.CreatedBy=@#AD_User_ID@',Updated=TO_TIMESTAMP('2009-09-30 16:46:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53238
;

-- Sep 30, 2009 4:46:44 PM COT
UPDATE AD_Tab SET WhereClause='S_Resource.IsManufacturingResource=''Y''',Updated=TO_TIMESTAMP('2009-09-30 16:46:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53015
;

