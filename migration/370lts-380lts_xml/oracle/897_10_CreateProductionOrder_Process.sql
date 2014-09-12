-- Oct 1, 2013 10:26:33 AM IST
-- "Create Production Order" process is created
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('3',0,0,53477,'org.compiere.process.OrderLineCreateProduction','N',TO_DATE('2013-10-01 10:26:28','YYYY-MM-DD HH24:MI:SS'),0,'Create Production Order','D','Create Production Order','Y','N','N','N','N','Create Production Order','Y',0,0,TO_DATE('2013-10-01 10:26:28','YYYY-MM-DD HH24:MI:SS'),0,'Create Production Order')
;

-- Oct 1, 2013 10:26:33 AM IST
-- "Create Production Order" process is created
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53477 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Oct 1, 2013 10:27:39 AM IST
-- "Create Production Order" process is created
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1037,0,53477,54106,15,'MovementDate',TO_DATE('2013-10-01 10:27:37','YYYY-MM-DD HH24:MI:SS'),0,'@DateOrdered@','MovementDate','D',7,'MovementDate','Y','Y','N','N','MovementDate',10,TO_DATE('2013-10-01 10:27:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 1, 2013 10:27:39 AM IST
-- "Create Production Order" process is created
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54106 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Oct 1, 2013 10:28:25 AM IST
-- "Create Production Order" process is created
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56423,0,20,'IgnorePrevProduction',TO_DATE('2013-10-01 10:28:24','YYYY-MM-DD HH24:MI:SS'),0,'IgnorePrevProduction','D',1,'IgnorePrevProduction','Y','IgnorePrevProduction','IgnorePrevProduction',TO_DATE('2013-10-01 10:28:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 1, 2013 10:28:25 AM IST
-- "Create Production Order" process is created
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56423 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 1, 2013 10:28:47 AM IST
-- "Create Production Order" process is created
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,56423,0,53477,54107,20,'IgnorePrevProduction',TO_DATE('2013-10-01 10:28:46','YYYY-MM-DD HH24:MI:SS'),0,'IgnorePrevProduction','D',1,'IgnorePrevProduction','Y','Y','N','N','IgnorePrevProduction',20,TO_DATE('2013-10-01 10:28:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 1, 2013 10:28:47 AM IST
-- "Create Production Order" process is created
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=54107 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Oct 1, 2013 10:35:09 AM IST
-- Create Production order button is added in C_OrderLine Table
UPDATE AD_Process SET Name='CreateProductionOrder', Value='CreateProductionOrder',Updated=TO_DATE('2013-10-01 10:35:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53477
;

-- Oct 1, 2013 10:35:09 AM IST
-- Create Production order button is added in C_OrderLine Table
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53477
;