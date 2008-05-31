-- Feb 11, 2008 12:44:51 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54310,53340,0,19,53020,'DD_NetworkDistribution_ID',TO_DATE('2008-02-11 12:44:48','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','Y','Network Distribution',0,TO_DATE('2008-02-11 12:44:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 11, 2008 12:44:51 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54310 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 11, 2008 12:44:58 PM CST
-- Implementing Distribution Network
ALTER TABLE PP_Product_Planning ADD DD_NetworkDistribution_ID NUMBER(10)
;

-- Feb 11, 2008 12:45:22 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54310,54392,0,53030,TO_DATE('2008-02-11 12:45:18','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','N','N','Network Distribution',TO_DATE('2008-02-11 12:45:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 11, 2008 12:45:22 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54392 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=54392
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53519
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53520
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53521
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53522
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53523
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53524
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53525
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53526
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53527
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53528
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53529
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53530
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53531
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53532
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53533
;

-- Feb 11, 2008 12:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53534
;

-- Feb 11, 2008 12:46:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Is Create Plan', PrintName='Is Create Plan',Updated=TO_DATE('2008-02-11 12:46:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53258
;

-- Feb 11, 2008 12:46:18 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53258
;

-- Feb 11, 2008 12:46:18 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='IsCreatePlan', Name='Is Create Plan', Description=NULL, Help=NULL WHERE AD_Element_ID=53258
;

-- Feb 11, 2008 12:46:18 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Is Create Plan', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53258) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:46:18 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsCreatePlan', Name='Is Create Plan', Description=NULL, Help=NULL, AD_Element_ID=53258 WHERE UPPER(ColumnName)='ISCREATEPLAN' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:46:18 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsCreatePlan', Name='Is Create Plan', Description=NULL, Help=NULL WHERE AD_Element_ID=53258 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:46:18 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Create Plan', Name='Is Create Plan' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53258)
;

-- Feb 11, 2008 12:46:18 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Create Plan', Name='Is Create Plan' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53258)
;

-- Feb 11, 2008 12:46:40 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Is Demand', PrintName='Is Demand',Updated=TO_DATE('2008-02-11 12:46:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53259
;

-- Feb 11, 2008 12:46:40 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53259
;

-- Feb 11, 2008 12:46:40 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='IsDemand', Name='Is Demand', Description=NULL, Help=NULL WHERE AD_Element_ID=53259
;

-- Feb 11, 2008 12:46:40 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Is Demand', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53259) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:46:40 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsDemand', Name='Is Demand', Description=NULL, Help=NULL, AD_Element_ID=53259 WHERE UPPER(ColumnName)='ISDEMAND' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:46:41 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsDemand', Name='Is Demand', Description=NULL, Help=NULL WHERE AD_Element_ID=53259 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:46:41 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Demand', Name='Is Demand' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53259)
;

-- Feb 11, 2008 12:46:41 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Demand', Name='Is Demand' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53259)
;

-- Feb 11, 2008 12:47:02 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Is Issue', PrintName='Is Issue',Updated=TO_DATE('2008-02-11 12:47:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53260
;

-- Feb 11, 2008 12:47:02 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53260
;

-- Feb 11, 2008 12:47:02 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='IsIssue', Name='Is Issue', Description=NULL, Help=NULL WHERE AD_Element_ID=53260
;

-- Feb 11, 2008 12:47:02 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Is Issue', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53260) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:47:02 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsIssue', Name='Is Issue', Description=NULL, Help=NULL, AD_Element_ID=53260 WHERE UPPER(ColumnName)='ISISSUE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:47:02 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsIssue', Name='Is Issue', Description=NULL, Help=NULL WHERE AD_Element_ID=53260 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:47:02 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Issue', Name='Is Issue' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53260)
;

-- Feb 11, 2008 12:47:02 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Issue', Name='Is Issue' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53260)
;

-- Feb 11, 2008 12:47:27 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Is MPS', PrintName='Is MPS',Updated=TO_DATE('2008-02-11 12:47:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53261
;

-- Feb 11, 2008 12:47:27 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53261
;

-- Feb 11, 2008 12:47:27 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='IsMPS', Name='Is MPS', Description=NULL, Help=NULL WHERE AD_Element_ID=53261
;

-- Feb 11, 2008 12:47:27 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Is MPS', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53261) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:47:27 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description=NULL, Help=NULL, AD_Element_ID=53261 WHERE UPPER(ColumnName)='ISMPS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:47:27 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description=NULL, Help=NULL WHERE AD_Element_ID=53261 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:47:27 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is MPS', Name='Is MPS' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53261)
;

-- Feb 11, 2008 12:47:27 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is MPS', Name='Is MPS' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53261)
;

-- Feb 11, 2008 12:47:50 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Is Required MRP', PrintName='Is Required MRP',Updated=TO_DATE('2008-02-11 12:47:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53262
;

-- Feb 11, 2008 12:47:50 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53262
;

-- Feb 11, 2008 12:47:50 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='IsRequiredMRP', Name='Is Required MRP', Description=NULL, Help=NULL WHERE AD_Element_ID=53262
;

-- Feb 11, 2008 12:47:50 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Is Required MRP', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53262) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:47:50 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsRequiredMRP', Name='Is Required MRP', Description=NULL, Help=NULL, AD_Element_ID=53262 WHERE UPPER(ColumnName)='ISREQUIREDMRP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:47:50 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsRequiredMRP', Name='Is Required MRP', Description=NULL, Help=NULL WHERE AD_Element_ID=53262 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:47:50 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Required MRP', Name='Is Required MRP' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53262)
;

-- Feb 11, 2008 12:47:50 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Required MRP', Name='Is Required MRP' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53262)
;

-- Feb 11, 2008 12:48:11 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Is Supply', PrintName='Is Supply',Updated=TO_DATE('2008-02-11 12:48:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53263
;

-- Feb 11, 2008 12:48:11 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53263
;

-- Feb 11, 2008 12:48:11 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='IsSupply', Name='Is Supply', Description=NULL, Help=NULL WHERE AD_Element_ID=53263
;

-- Feb 11, 2008 12:48:11 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Is Supply', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53263) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:48:11 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsSupply', Name='Is Supply', Description=NULL, Help=NULL, AD_Element_ID=53263 WHERE UPPER(ColumnName)='ISSUPPLY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:48:11 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='IsSupply', Name='Is Supply', Description=NULL, Help=NULL WHERE AD_Element_ID=53263 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:48:11 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Supply', Name='Is Supply' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53263)
;

-- Feb 11, 2008 12:48:11 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Is Supply', Name='Is Supply' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53263)
;

-- Feb 11, 2008 12:48:33 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET PrintName='Order Max',Updated=TO_DATE('2008-02-11 12:48:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53264
;

-- Feb 11, 2008 12:48:33 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53264
;

-- Feb 11, 2008 12:48:33 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='Order_Max', Name='Order_Max', Description=NULL, Help=NULL WHERE AD_Element_ID=53264
;

-- Feb 11, 2008 12:48:33 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Order_Max', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53264) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:48:33 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Order_Max', Name='Order_Max', Description=NULL, Help=NULL, AD_Element_ID=53264 WHERE UPPER(ColumnName)='ORDER_MAX' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:48:33 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Order_Max', Name='Order_Max', Description=NULL, Help=NULL WHERE AD_Element_ID=53264 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:48:33 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Order Max', Name='Order_Max' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53264)
;

-- Feb 11, 2008 12:48:33 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Order Max', Name='Order_Max' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53264)
;

-- Feb 11, 2008 12:48:39 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET Name='Order Max',Updated=TO_DATE('2008-02-11 12:48:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53391
;

-- Feb 11, 2008 12:48:39 PM CST
-- Implementing Distribution Network
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=53391
;

-- Feb 11, 2008 12:48:39 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Order Max', Description=NULL, Help=NULL WHERE AD_Column_ID=53391 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:48:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Order Period', PrintName='Order Period',Updated=TO_DATE('2008-02-11 12:48:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53265
;

-- Feb 11, 2008 12:48:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53265
;

-- Feb 11, 2008 12:48:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='Order_Period', Name='Order Period', Description=NULL, Help=NULL WHERE AD_Element_ID=53265
;

-- Feb 11, 2008 12:48:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Order Period', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53265) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:48:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Order_Period', Name='Order Period', Description=NULL, Help=NULL, AD_Element_ID=53265 WHERE UPPER(ColumnName)='ORDER_PERIOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:48:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Order_Period', Name='Order Period', Description=NULL, Help=NULL WHERE AD_Element_ID=53265 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:48:57 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Order Period', Name='Order Period' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53265)
;

-- Feb 11, 2008 12:48:57 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Order Period', Name='Order Period' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53265)
;

-- Feb 11, 2008 12:49:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Order Policy', PrintName='Order Policy',Updated=TO_DATE('2008-02-11 12:49:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53266
;

-- Feb 11, 2008 12:49:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53266
;

-- Feb 11, 2008 12:49:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='Order_Policy', Name='Order Policy', Description=NULL, Help=NULL WHERE AD_Element_ID=53266
;

-- Feb 11, 2008 12:49:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Order Policy', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53266) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:49:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Order_Policy', Name='Order Policy', Description=NULL, Help=NULL, AD_Element_ID=53266 WHERE UPPER(ColumnName)='ORDER_POLICY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:49:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Order_Policy', Name='Order Policy', Description=NULL, Help=NULL WHERE AD_Element_ID=53266 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:49:17 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Order Policy', Name='Order Policy' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53266)
;

-- Feb 11, 2008 12:49:17 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Order Policy', Name='Order Policy' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53266)
;

-- Feb 11, 2008 12:49:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Order Qty', PrintName='Order Qty',Updated=TO_DATE('2008-02-11 12:49:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53267
;

-- Feb 11, 2008 12:49:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53267
;

-- Feb 11, 2008 12:49:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='Order_Qty', Name='Order Qty', Description=NULL, Help=NULL WHERE AD_Element_ID=53267
;

-- Feb 11, 2008 12:49:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Order Qty', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53267) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:49:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Order_Qty', Name='Order Qty', Description=NULL, Help=NULL, AD_Element_ID=53267 WHERE UPPER(ColumnName)='ORDER_QTY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:49:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Order_Qty', Name='Order Qty', Description=NULL, Help=NULL WHERE AD_Element_ID=53267 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:49:37 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Order Qty', Name='Order Qty' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53267)
;

-- Feb 11, 2008 12:49:37 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Order Qty', Name='Order Qty' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53267)
;

-- Feb 11, 2008 12:49:58 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Planner ID', PrintName='Planner ID',Updated=TO_DATE('2008-02-11 12:49:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53269
;

-- Feb 11, 2008 12:49:58 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53269
;

-- Feb 11, 2008 12:49:58 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='Planner_ID', Name='Planner ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53269
;

-- Feb 11, 2008 12:49:58 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Planner ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53269) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:49:58 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Planner_ID', Name='Planner ID', Description=NULL, Help=NULL, AD_Element_ID=53269 WHERE UPPER(ColumnName)='PLANNER_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:49:58 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Planner_ID', Name='Planner ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53269 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:49:58 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Planner ID', Name='Planner ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53269)
;

-- Feb 11, 2008 12:49:58 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Planner ID', Name='Planner ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53269)
;

-- Feb 11, 2008 12:50:07 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Planner', PrintName='Planner',Updated=TO_DATE('2008-02-11 12:50:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53269
;

-- Feb 11, 2008 12:50:07 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53269
;

-- Feb 11, 2008 12:50:07 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='Planner_ID', Name='Planner', Description=NULL, Help=NULL WHERE AD_Element_ID=53269
;

-- Feb 11, 2008 12:50:07 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Planner', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53269) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:50:07 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Planner_ID', Name='Planner', Description=NULL, Help=NULL, AD_Element_ID=53269 WHERE UPPER(ColumnName)='PLANNER_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:50:07 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='Planner_ID', Name='Planner', Description=NULL, Help=NULL WHERE AD_Element_ID=53269 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:50:07 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Planner', Name='Planner' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53269)
;

-- Feb 11, 2008 12:50:07 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Planner', Name='Planner' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53269)
;

-- Feb 11, 2008 12:50:22 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Time Fence', PrintName='Time Fence',Updated=TO_DATE('2008-02-11 12:50:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53270
;

-- Feb 11, 2008 12:50:22 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53270
;

-- Feb 11, 2008 12:50:22 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='TimeFence', Name='Time Fence', Description=NULL, Help=NULL WHERE AD_Element_ID=53270
;

-- Feb 11, 2008 12:50:22 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Time Fence', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53270) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:50:22 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='TimeFence', Name='Time Fence', Description=NULL, Help=NULL, AD_Element_ID=53270 WHERE UPPER(ColumnName)='TIMEFENCE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:50:22 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='TimeFence', Name='Time Fence', Description=NULL, Help=NULL WHERE AD_Element_ID=53270 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:50:22 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Time Fence', Name='Time Fence' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53270)
;

-- Feb 11, 2008 12:50:22 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Time Fence', Name='Time Fence' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53270)
;

-- Feb 11, 2008 12:50:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Transfert Time', PrintName='Transfert Time',Updated=TO_DATE('2008-02-11 12:50:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53271
;

-- Feb 11, 2008 12:50:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53271
;

-- Feb 11, 2008 12:50:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='TransfertTime', Name='Transfert Time', Description=NULL, Help=NULL WHERE AD_Element_ID=53271
;

-- Feb 11, 2008 12:50:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Transfert Time', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53271) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:50:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='TransfertTime', Name='Transfert Time', Description=NULL, Help=NULL, AD_Element_ID=53271 WHERE UPPER(ColumnName)='TRANSFERTTIME' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 12:50:37 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='TransfertTime', Name='Transfert Time', Description=NULL, Help=NULL WHERE AD_Element_ID=53271 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 12:50:37 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Transfert Time', Name='Transfert Time' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53271)
;

-- Feb 11, 2008 12:50:37 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Transfert Time', Name='Transfert Time' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53271)
;

