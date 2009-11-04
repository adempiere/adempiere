-- Sep 28, 2009 11:47:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53326,TO_DATE('2009-09-28 23:47:54','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','N','WM_InOutBound Draft Status',TO_DATE('2009-09-28 23:47:54','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Sep 28, 2009 11:47:57 PM ECT
-- Warehouse Management
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53326 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Sep 28, 2009 11:49:39 PM ECT
-- Warehouse Management
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,WhereClause) VALUES (0,58210,58199,0,53326,53233,TO_DATE('2009-09-28 23:49:39','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','Y',TO_DATE('2009-09-28 23:49:39','YYYY-MM-DD HH24:MI:SS'),0,'DocStatus=''DR''')
;

-- Sep 28, 2009 11:51:09 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET AD_Reference_ID=18, AD_Reference_Value_ID=53326,Updated=TO_DATE('2009-09-28 23:51:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50395
;

-- Sep 28, 2009 11:53:20 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET IsValueDisplayed='N',Updated=TO_DATE('2009-09-28 23:53:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53326
;

-- Sep 28, 2009 11:56:49 PM ECT
-- Warehouse Management
UPDATE AD_Ref_Table SET OrderByClause='WM_InOutBound.DocumentNo', WhereClause='WM_InOutBound.DocStatus=''DR''',Updated=TO_DATE('2009-09-28 23:56:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53326
;

-- Sep 28, 2009 11:58:38 PM ECT
-- Warehouse Management
UPDATE AD_Element SET Name='In & Out Bound Order', PrintName='In & Out Bound Order',Updated=TO_DATE('2009-09-28 23:58:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53912
;

-- Sep 28, 2009 11:58:38 PM ECT
-- Warehouse Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53912
;

-- Sep 28, 2009 11:58:38 PM ECT
-- Warehouse Management
UPDATE AD_Column SET ColumnName='WM_InOutBound_ID', Name='In & Out Bound Order', Description=NULL, Help=NULL WHERE AD_Element_ID=53912
;

-- Sep 28, 2009 11:58:41 PM ECT
-- Warehouse Management
UPDATE AD_Process_Para SET ColumnName='WM_InOutBound_ID', Name='In & Out Bound Order', Description=NULL, Help=NULL, AD_Element_ID=53912 WHERE UPPER(ColumnName)='WM_INOUTBOUND_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 28, 2009 11:58:41 PM ECT
-- Warehouse Management
UPDATE AD_Process_Para SET ColumnName='WM_InOutBound_ID', Name='In & Out Bound Order', Description=NULL, Help=NULL WHERE AD_Element_ID=53912 AND IsCentrallyMaintained='Y'
;

-- Sep 28, 2009 11:58:41 PM ECT
-- Warehouse Management
UPDATE AD_Field SET Name='In & Out Bound Order', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53912) AND IsCentrallyMaintained='Y'
;

-- Sep 28, 2009 11:58:41 PM ECT
-- Warehouse Management
UPDATE AD_PrintFormatItem pi SET PrintName='In & Out Bound Order', Name='In & Out Bound Order' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53912)
;

-- Sep 29, 2009 12:10:20 AM ECT
-- Warehouse Management
UPDATE AD_Column SET FieldLength=255,Updated=TO_DATE('2009-09-29 00:10:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58107
;

-- Sep 29, 2009 12:13:22 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_OrderLine_C_BPartner_Location_ID',Updated=TO_DATE('2009-09-29 00:13:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50088
;

-- Sep 29, 2009 12:13:42 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_OrderLine_M_AttributeSetInstance_ID',Updated=TO_DATE('2009-09-29 00:13:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50113
;

-- Sep 29, 2009 12:13:44 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_OrderLine_PP_Cost_Collector_ID',Updated=TO_DATE('2009-09-29 00:13:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50118
;

-- Sep 29, 2009 12:14:17 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_OrderLine_S_ResourceAssignment_ID',Updated=TO_DATE('2009-09-29 00:14:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50134
;

-- Sep 29, 2009 12:14:45 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET EntityType='D',Updated=TO_DATE('2009-09-29 00:14:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50152
;

-- Sep 29, 2009 12:15:09 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_PO_DiscountSchema_ID',Updated=TO_DATE('2009-09-29 00:15:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50183
;

-- Sep 29, 2009 12:15:22 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_PotentialLifeTimeValue',Updated=TO_DATE('2009-09-29 00:15:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50188
;

-- Sep 29, 2009 12:15:35 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_Location_C_BPartner_ID',Updated=TO_DATE('2009-09-29 00:15:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50208
;

-- Sep 29, 2009 12:15:47 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_Location_C_BPartner_Location_ID',Updated=TO_DATE('2009-09-29 00:15:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50209
;

-- Sep 29, 2009 12:15:49 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_Location_C_Location_ID',Updated=TO_DATE('2009-09-29 00:15:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50210
;

-- Sep 29, 2009 12:15:55 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_Location_C_SalesRegion_ID',Updated=TO_DATE('2009-09-29 00:15:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50211
;

-- Sep 29, 2009 12:16:30 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_IsExcludeAutoDelivery',Updated=TO_DATE('2009-09-29 00:16:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50268
;

-- Sep 29, 2009 12:16:35 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_IsPickListPrintDetails',Updated=TO_DATE('2009-09-29 00:16:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50270
;

-- Sep 29, 2009 12:16:36 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_IsInvoicePrintDetails',Updated=TO_DATE('2009-09-29 00:16:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50269
;

-- Sep 29, 2009 12:16:51 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_M_AttributeSetInstance_ID',Updated=TO_DATE('2009-09-29 00:16:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50279
;

-- Sep 29, 2009 12:17:07 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_M_Product_Category_ID',Updated=TO_DATE('2009-09-29 00:17:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50283
;

-- Sep 29, 2009 12:17:22 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_C_SubscriptionType_ID',Updated=TO_DATE('2009-09-29 00:17:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50248
;

-- Sep 29, 2009 12:17:25 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_C_RevenueRecognition_ID',Updated=TO_DATE('2009-09-29 00:17:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50247
;

-- Sep 29, 2009 12:17:52 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_Category_AD_Client_ID',Updated=TO_DATE('2009-09-29 00:17:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50305
;

-- Sep 29, 2009 12:17:59 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_Category_AD_PrintColor_ID',Updated=TO_DATE('2009-09-29 00:17:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50307
;

-- Sep 29, 2009 12:18:06 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_Category_A_Asset_Group_ID',Updated=TO_DATE('2009-09-29 00:18:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50308
;

-- Sep 29, 2009 12:18:17 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_Category_M_Product_Category_ID',Updated=TO_DATE('2009-09-29 00:18:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50316
;

-- Sep 29, 2009 12:18:29 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='M_Product_Category_M_Product_Category_Parent_ID',Updated=TO_DATE('2009-09-29 00:18:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50317
;

-- Sep 29, 2009 12:38:31 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_C_Activity_ID',Updated=TO_DATE('2009-09-29 00:38:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50326
;

-- Sep 29, 2009 12:38:33 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_C_Campaign_ID',Updated=TO_DATE('2009-09-29 00:38:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50327
;

-- Sep 29, 2009 12:38:38 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_C_OrderLine_ID',Updated=TO_DATE('2009-09-29 00:38:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50329
;

-- Sep 29, 2009 12:38:45 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_C_ProjectPhase_ID',Updated=TO_DATE('2009-09-29 00:38:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50330
;

-- Sep 29, 2009 12:38:55 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_C_ProjectTask_ID',Updated=TO_DATE('2009-09-29 00:38:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50331
;

-- Sep 29, 2009 12:38:56 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_IsDescription',Updated=TO_DATE('2009-09-29 00:38:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50338
;

-- Sep 29, 2009 12:39:08 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_M_AttributeSetInstance_ID',Updated=TO_DATE('2009-09-29 00:39:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50340
;

-- Sep 29, 2009 12:39:39 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_WM_InOutBoundLine_ID',Updated=TO_DATE('2009-09-29 00:39:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50351
;

-- Sep 29, 2009 12:39:47 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBoundLine_WM_InOutBound_ID',Updated=TO_DATE('2009-09-29 00:39:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50352
;

-- Sep 29, 2009 12:40:10 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBound_DropShip_BPartner_ID',Updated=TO_DATE('2009-09-29 00:40:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50368
;

-- Sep 29, 2009 12:40:14 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='WM_InOutBound_DropShip_Location_ID',Updated=TO_DATE('2009-09-29 00:40:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50369
;

-- Sep 29, 2009 12:40:35 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_OrderLine_C_BPartner_Location_ID',Updated=TO_DATE('2009-09-29 00:40:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50470
;

-- Sep 29, 2009 12:40:49 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_OrderLine_M_M_AttributeSetInstance_ID',Updated=TO_DATE('2009-09-29 00:40:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50495
;

-- Sep 29, 2009 12:40:54 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_OrderLine_PP_Cost_Collector_ID',Updated=TO_DATE('2009-09-29 00:40:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50500
;

-- Sep 29, 2009 12:41:09 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_OrderLine_S_ResourceAssignment_ID',Updated=TO_DATE('2009-09-29 00:41:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50516
;

-- Sep 29, 2009 12:41:49 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_C_InvoiceSchedule_ID',Updated=TO_DATE('2009-09-29 00:41:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50408
;

-- Sep 29, 2009 12:42:01 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET EntityType='D',Updated=TO_DATE('2009-09-29 00:42:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50410
;

-- Sep 29, 2009 12:42:03 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_PO_DiscountSchema_ID',Updated=TO_DATE('2009-09-29 00:42:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50442
;

-- Sep 29, 2009 12:42:15 AM ECT
-- Warehouse Management
UPDATE AD_View_Column SET ColumnName='C_BPartner_PotentialLifeTimeValue',Updated=TO_DATE('2009-09-29 00:42:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50447
;

