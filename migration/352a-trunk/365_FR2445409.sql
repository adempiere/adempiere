-- 18.12.2008 16:48:43 EET
-- FR [ 2445409 ] Translate Info MRP
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53717,0,'QtyGrossReq',TO_DATE('2008-12-18 16:48:41','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Gross Requirements Qty','Gross Req.',TO_DATE('2008-12-18 16:48:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18.12.2008 16:48:43 EET
-- FR [ 2445409 ] Translate Info MRP
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53717 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 18.12.2008 16:50:31 EET
-- FR [ 2445409 ] Translate Info MRP
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53718,0,'QtyScheduledRec',TO_DATE('2008-12-18 16:50:30','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Scheduled Receipt Qty','Sched. Rec.',TO_DATE('2008-12-18 16:50:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18.12.2008 16:50:31 EET
-- FR [ 2445409 ] Translate Info MRP
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53718 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 18.12.2008 16:54:39 EET
-- FR [ 2445409 ] Translate Info MRP
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53719,0,'QtyPlannedOrders',TO_DATE('2008-12-18 16:54:33','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Planned Orders Qty','Planned Orders',TO_DATE('2008-12-18 16:54:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18.12.2008 16:54:39 EET
-- FR [ 2445409 ] Translate Info MRP
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53719 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 18.12.2008 16:56:46 EET
-- FR [ 2445409 ] Translate Info MRP
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53720,0,'QtyProjOH',TO_DATE('2008-12-18 16:56:45','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Projected On Hand Qty','Proj OH',TO_DATE('2008-12-18 16:56:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18.12.2008 16:56:46 EET
-- FR [ 2445409 ] Translate Info MRP
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53720 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 18.12.2008 16:58:50 EET
-- FR [ 2445409 ] Translate Info MRP
UPDATE AD_Element SET ColumnName='QtySchRcpt', PrintName='Sch. Rcpt.',Updated=TO_DATE('2008-12-18 16:58:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53718
;

-- 18.12.2008 16:58:50 EET
-- FR [ 2445409 ] Translate Info MRP
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53718
;

-- 18.12.2008 16:58:50 EET
-- FR [ 2445409 ] Translate Info MRP
UPDATE AD_Column SET ColumnName='QtySchRcpt', Name='Scheduled Receipt Qty', Description=NULL, Help=NULL WHERE AD_Element_ID=53718
;

-- 18.12.2008 16:58:50 EET
-- FR [ 2445409 ] Translate Info MRP
UPDATE AD_Process_Para SET ColumnName='QtySchRcpt', Name='Scheduled Receipt Qty', Description=NULL, Help=NULL, AD_Element_ID=53718 WHERE UPPER(ColumnName)='QTYSCHRCPT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 18.12.2008 16:58:50 EET
-- FR [ 2445409 ] Translate Info MRP
UPDATE AD_Process_Para SET ColumnName='QtySchRcpt', Name='Scheduled Receipt Qty', Description=NULL, Help=NULL WHERE AD_Element_ID=53718 AND IsCentrallyMaintained='Y'
;

-- 18.12.2008 16:58:50 EET
-- FR [ 2445409 ] Translate Info MRP
UPDATE AD_PrintFormatItem pi SET PrintName='Sch. Rcpt.', Name='Scheduled Receipt Qty' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53718)
;

