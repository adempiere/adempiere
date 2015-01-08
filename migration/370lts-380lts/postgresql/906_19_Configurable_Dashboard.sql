ALTER TABLE PA_DashboardContent ADD COLUMN AD_Tab_ID Numeric(10,0)
;

ALTER TABLE PA_DashboardContent ADD COLUMN IsDynamicDashboard character(1) DEFAULT 'N'::bpchar
;

ALTER TABLE PA_DashboardContent ADD COLUMN IsEventRequired character(1) DEFAULT 'N'::bpchar
;

ALTER TABLE PA_DashboardContent ADD COLUMN Zoom_Window_ID Numeric(10,0)
;

ALTER TABLE PA_DashboardContent ADD COLUMN Zoom_Tab_ID Numeric(10,0)
;

ALTER TABLE PA_DashboardContent ADD COLUMN PageSize Numeric(10,0)
;

ALTER TABLE PA_DashboardContent ADD COLUMN OnEvent character varying(30)
;

-- Oct 22, 2013 3:53:52 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56522,0,'isdynamicdashboard',TO_TIMESTAMP('2013-10-22 15:53:51','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','isdynamicdashboard','isdynamicdashboard',TO_TIMESTAMP('2013-10-22 15:53:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 3:53:52 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56522 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 22, 2013 3:53:53 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69110,56522,0,20,50010,'isdynamicdashboard',TO_TIMESTAMP('2013-10-22 15:53:51','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','isdynamicdashboard',TO_TIMESTAMP('2013-10-22 15:53:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 22, 2013 3:53:53 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69110 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 22, 2013 3:53:53 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56523,0,'iseventrequired',TO_TIMESTAMP('2013-10-22 15:53:53','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','iseventrequired','iseventrequired',TO_TIMESTAMP('2013-10-22 15:53:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 3:53:53 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56523 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 22, 2013 3:53:54 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69111,56523,0,20,50010,'iseventrequired',TO_TIMESTAMP('2013-10-22 15:53:53','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','iseventrequired',TO_TIMESTAMP('2013-10-22 15:53:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 22, 2013 3:53:54 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69111 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 22, 2013 3:53:54 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69112,125,0,19,50010,'AD_Tab_ID',TO_TIMESTAMP('2013-10-22 15:53:54','YYYY-MM-DD HH24:MI:SS'),0,'Tab within a Window','D',10,'The Tab indicates a tab that displays within a window.','Y','N','N','N','N','N','N','N','N','Y','Tab',TO_TIMESTAMP('2013-10-22 15:53:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 22, 2013 3:53:54 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69112 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 22, 2013 3:53:55 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56524,0,'zoom_window_id',TO_TIMESTAMP('2013-10-22 15:53:54','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','zoom_window_id','zoom_window_id',TO_TIMESTAMP('2013-10-22 15:53:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 3:53:55 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56524 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 22, 2013 3:53:55 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69113,56524,0,19,50010,'zoom_window_id',TO_TIMESTAMP('2013-10-22 15:53:54','YYYY-MM-DD HH24:MI:SS'),0,'D',10,'Y','N','N','N','N','N','N','N','N','Y','zoom_window_id',TO_TIMESTAMP('2013-10-22 15:53:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 22, 2013 3:53:55 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69113 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 22, 2013 3:53:56 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56525,0,'zoom_tab_id',TO_TIMESTAMP('2013-10-22 15:53:55','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','zoom_tab_id','zoom_tab_id',TO_TIMESTAMP('2013-10-22 15:53:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 3:53:56 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56525 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 22, 2013 3:53:56 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69114,56525,0,19,50010,'zoom_tab_id',TO_TIMESTAMP('2013-10-22 15:53:55','YYYY-MM-DD HH24:MI:SS'),0,'D',10,'Y','N','N','N','N','N','N','N','N','Y','zoom_tab_id',TO_TIMESTAMP('2013-10-22 15:53:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 22, 2013 3:53:56 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69114 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 22, 2013 3:53:56 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56526,0,'pagesize',TO_TIMESTAMP('2013-10-22 15:53:56','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','pagesize','pagesize',TO_TIMESTAMP('2013-10-22 15:53:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 3:53:56 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56526 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 22, 2013 3:53:57 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69115,56526,0,22,50010,'pagesize',TO_TIMESTAMP('2013-10-22 15:53:56','YYYY-MM-DD HH24:MI:SS'),0,'D',131089,'Y','N','N','N','N','N','N','N','N','Y','pagesize',TO_TIMESTAMP('2013-10-22 15:53:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 22, 2013 3:53:57 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69115 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 22, 2013 3:53:57 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56527,0,'onevent',TO_TIMESTAMP('2013-10-22 15:53:57','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','onevent','onevent',TO_TIMESTAMP('2013-10-22 15:53:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 3:53:57 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56527 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 22, 2013 3:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69116,56527,0,10,50010,'onevent',TO_TIMESTAMP('2013-10-22 15:53:57','YYYY-MM-DD HH24:MI:SS'),0,'D',30,'Y','N','N','N','N','N','N','N','N','Y','onevent',TO_TIMESTAMP('2013-10-22 15:53:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 22, 2013 3:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69116 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 22, 2013 3:54:54 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET Name='Is Dynamic Dashboard',Updated=TO_TIMESTAMP('2013-10-22 15:54:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69110
;

-- Oct 22, 2013 3:54:54 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69110
;

-- Oct 22, 2013 3:54:54 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Is Dynamic Dashboard', Description=NULL, Help=NULL WHERE AD_Column_ID=69110 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 3:55:12 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET Name='Is Event Required',Updated=TO_TIMESTAMP('2013-10-22 15:55:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69111
;

-- Oct 22, 2013 3:55:12 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69111
;

-- Oct 22, 2013 3:55:12 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Is Event Required', Description=NULL, Help=NULL WHERE AD_Column_ID=69111 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 3:55:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET Name='Page Size',Updated=TO_TIMESTAMP('2013-10-22 15:55:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69115
;

-- Oct 22, 2013 3:55:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69115
;

-- Oct 22, 2013 3:55:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Page Size', Description=NULL, Help=NULL WHERE AD_Column_ID=69115 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 3:56:17 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET Name='On Event',Updated=TO_TIMESTAMP('2013-10-22 15:56:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69116
;

-- Oct 22, 2013 3:56:17 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69116
;

-- Oct 22, 2013 3:56:17 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='On Event', Description=NULL, Help=NULL WHERE AD_Column_ID=69116 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 3:58:07 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET Name='Zoom Tab ID',Updated=TO_TIMESTAMP('2013-10-22 15:58:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69114
;

-- Oct 22, 2013 3:58:07 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69114
;

-- Oct 22, 2013 3:58:07 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Zoom Tab ID', Description=NULL, Help=NULL WHERE AD_Column_ID=69114 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 3:59:26 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET Name='Zoom_Window_ID',Updated=TO_TIMESTAMP('2013-10-22 15:59:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69113
;

-- Oct 22, 2013 3:59:26 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69113
;

-- Oct 22, 2013 3:59:26 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Zoom_Window_ID', Description=NULL, Help=NULL WHERE AD_Column_ID=69113 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 3:59:37 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET Name='Zoom_Tab_ID',Updated=TO_TIMESTAMP('2013-10-22 15:59:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69114
;

-- Oct 22, 2013 3:59:37 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69114
;

-- Oct 22, 2013 3:59:37 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Zoom_Tab_ID', Description=NULL, Help=NULL WHERE AD_Column_ID=69114 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 4:00:04 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69110,69998,0,50010,TO_TIMESTAMP('2013-10-22 16:00:03','YYYY-MM-DD HH24:MI:SS'),0,1,'D','Y','Y','Y','N','N','N','N','N','isdynamicdashboard',TO_TIMESTAMP('2013-10-22 16:00:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:00:04 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69998 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 22, 2013 4:00:04 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69111,69999,0,50010,TO_TIMESTAMP('2013-10-22 16:00:04','YYYY-MM-DD HH24:MI:SS'),0,1,'D','Y','Y','Y','N','N','N','N','N','iseventrequired',TO_TIMESTAMP('2013-10-22 16:00:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:00:04 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69999 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 22, 2013 4:00:05 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69116,70000,0,50010,TO_TIMESTAMP('2013-10-22 16:00:04','YYYY-MM-DD HH24:MI:SS'),0,30,'D','Y','Y','Y','N','N','N','N','N','onevent',TO_TIMESTAMP('2013-10-22 16:00:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:00:05 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70000 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 22, 2013 4:00:05 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69115,70001,0,50010,TO_TIMESTAMP('2013-10-22 16:00:05','YYYY-MM-DD HH24:MI:SS'),0,131089,'D','Y','Y','Y','N','N','N','N','N','pagesize',TO_TIMESTAMP('2013-10-22 16:00:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:00:05 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70001 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 22, 2013 4:00:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69112,70002,0,50010,TO_TIMESTAMP('2013-10-22 16:00:05','YYYY-MM-DD HH24:MI:SS'),0,'Tab within a Window',10,'D','The Tab indicates a tab that displays within a window.','Y','Y','Y','N','N','N','N','N','Tab',TO_TIMESTAMP('2013-10-22 16:00:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:00:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70002 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 22, 2013 4:00:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69114,70003,0,50010,TO_TIMESTAMP('2013-10-22 16:00:06','YYYY-MM-DD HH24:MI:SS'),0,10,'D','Y','Y','Y','N','N','N','N','N','zoom_tab_id',TO_TIMESTAMP('2013-10-22 16:00:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:00:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70003 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 22, 2013 4:00:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69113,70004,0,50010,TO_TIMESTAMP('2013-10-22 16:00:06','YYYY-MM-DD HH24:MI:SS'),0,10,'D','Y','Y','Y','N','N','N','N','N','zoom_window_id',TO_TIMESTAMP('2013-10-22 16:00:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:00:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70004 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 22, 2013 4:01:12 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Zoom Window',Updated=TO_TIMESTAMP('2013-10-22 16:01:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:01:12 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:01:40 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Zoom Tab',Updated=TO_TIMESTAMP('2013-10-22 16:01:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:01:40 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:02:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Page Size',Updated=TO_TIMESTAMP('2013-10-22 16:02:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 4:02:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 4:02:15 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Event',Updated=TO_TIMESTAMP('2013-10-22 16:02:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:02:15 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:02:39 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Is Dynamic Dashboard',Updated=TO_TIMESTAMP('2013-10-22 16:02:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 4:02:39 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 4:04:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2013-10-22 16:04:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 4:04:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-10-22 16:04:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70002
;

-- Oct 22, 2013 4:04:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-10-22 16:04:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57344
;

-- Oct 22, 2013 4:04:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-10-22 16:04:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56504
;

-- Oct 22, 2013 4:04:01 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-10-22 16:04:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=60822
;

-- Oct 22, 2013 4:04:01 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-10-22 16:04:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:04:01 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:04:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 4:04:01 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-10-22 16:04:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 4:04:01 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-10-22 16:04:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:04:01 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-10-22 16:04:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:04:33 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:04:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:04:33 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-10-22 16:04:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:04:33 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-10-22 16:04:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 4:04:33 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-10-22 16:04:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 4:05:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-10-22 16:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 4:05:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-10-22 16:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57344
;

-- Oct 22, 2013 4:05:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-10-22 16:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56504
;

-- Oct 22, 2013 4:05:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-10-22 16:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=60822
;

-- Oct 22, 2013 4:05:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:05:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-10-22 16:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:05:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-10-22 16:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:05:29 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Is Event Required',Updated=TO_TIMESTAMP('2013-10-22 16:05:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 4:05:29 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 4:05:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-10-22 16:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 4:05:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-10-22 16:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57344
;

-- Oct 22, 2013 4:05:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-10-22 16:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56504
;

-- Oct 22, 2013 4:05:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=60822
;

-- Oct 22, 2013 4:05:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-10-22 16:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:05:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-10-22 16:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:05:50 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-10-22 16:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:06:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-10-22 16:06:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:06:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-10-22 16:06:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57344
;

-- Oct 22, 2013 4:06:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:06:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56504
;

-- Oct 22, 2013 4:06:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-10-22 16:06:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=60822
;

-- Oct 22, 2013 4:06:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-10-22 16:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:06:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57344
;

-- Oct 22, 2013 4:06:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-10-22 16:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56504
;

-- Oct 22, 2013 4:06:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-10-22 16:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=60822
;

-- Oct 22, 2013 4:06:13 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:06:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:06:13 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-10-22 16:06:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57344
;

-- Oct 22, 2013 4:06:13 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-10-22 16:06:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56504
;

-- Oct 22, 2013 4:06:13 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-10-22 16:06:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=60822
;

-- Oct 22, 2013 4:06:41 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-10-22 16:06:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 4:07:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-10-22 16:07:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:08:26 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET DisplayLogic='@isdynamicdashboard@=''''Y''',Updated=TO_TIMESTAMP('2013-10-22 16:08:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70002
;

-- Oct 22, 2013 4:09:36 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET DisplayLogic='@ad_zoom_window_id@>0',Updated=TO_TIMESTAMP('2013-10-22 16:09:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:10:41 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET DisplayLogic='@AD_Window_ID@!0',Updated=TO_TIMESTAMP('2013-10-22 16:10:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 4:16:03 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET DisplayLogic='@isdynamicdashboard@=''Y''', IsSameLine='Y',Updated=TO_TIMESTAMP('2013-10-22 16:16:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 4:16:20 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET DisplayLogic='@isdynamicdashboard@=''Y''',Updated=TO_TIMESTAMP('2013-10-22 16:16:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 4:16:37 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET DisplayLogic='@iseventrequired@=''Y''', IsSameLine='Y',Updated=TO_TIMESTAMP('2013-10-22 16:16:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:17:57 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET DisplayLogic='@iseventrequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-22 16:17:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=51012
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=51013
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70002
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:18:42 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56486
;

-- Oct 22, 2013 4:18:59 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET DisplayLogic='@isdynamicdashboard@=''N''',Updated=TO_TIMESTAMP('2013-10-22 16:18:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56486
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=51013
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70002
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56486
;

-- Oct 22, 2013 4:19:16 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-22 16:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=51012
;

-- Oct 22, 2013 4:29:37 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53572,TO_TIMESTAMP('2013-10-22 16:29:36','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','PA_DashBoard',TO_TIMESTAMP('2013-10-22 16:29:36','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Oct 22, 2013 4:29:37 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53572 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Oct 22, 2013 4:30:55 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsAlert,IsDisplayIdentifier,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,161,160,0,53572,106,TO_TIMESTAMP('2013-10-22 16:30:55','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','N','N',TO_TIMESTAMP('2013-10-22 16:30:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:32:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52232,'AD_Tab.AD_Window_ID=@Zoom_Window_ID@',TO_TIMESTAMP('2013-10-22 16:32:09','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','AD_Tabs Of Zoom Window','S',TO_TIMESTAMP('2013-10-22 16:32:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:33:00 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53572, AD_Val_Rule_ID=52232, MandatoryLogic='@iseventrequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-22 16:33:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69114
;

-- Oct 22, 2013 4:34:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53573,TO_TIMESTAMP('2013-10-22 16:34:06','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','AD_Zoom_Win',TO_TIMESTAMP('2013-10-22 16:34:06','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Oct 22, 2013 4:34:06 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53573 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Oct 22, 2013 4:34:49 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsAlert,IsDisplayIdentifier,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,156,155,0,53573,105,TO_TIMESTAMP('2013-10-22 16:34:49','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','N','N',TO_TIMESTAMP('2013-10-22 16:34:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 4:36:30 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53573, MandatoryLogic='@iseventrequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-22 16:36:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69113
;

-- Oct 22, 2013 4:37:40 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53574,TO_TIMESTAMP('2013-10-22 16:37:40','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','Event List',TO_TIMESTAMP('2013-10-22 16:37:40','YYYY-MM-DD HH24:MI:SS'),0,'L')
;

-- Oct 22, 2013 4:37:40 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53574 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Oct 22, 2013 4:38:14 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54119,53574,TO_TIMESTAMP('2013-10-22 16:38:14','YYYY-MM-DD HH24:MI:SS'),0,'onClick','D','Y','onClick',TO_TIMESTAMP('2013-10-22 16:38:14','YYYY-MM-DD HH24:MI:SS'),0,'onClick')
;

-- Oct 22, 2013 4:38:14 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54119 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 22, 2013 4:38:37 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54120,53574,TO_TIMESTAMP('2013-10-22 16:38:37','YYYY-MM-DD HH24:MI:SS'),0,'onDoubleClick','D','Y','onDoubleClick',TO_TIMESTAMP('2013-10-22 16:38:37','YYYY-MM-DD HH24:MI:SS'),0,'onDoubleClick')
;

-- Oct 22, 2013 4:38:37 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54120 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 22, 2013 4:39:11 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=53574,Updated=TO_TIMESTAMP('2013-10-22 16:39:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69116
;

-- Oct 22, 2013 4:51:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Element SET Name='Zoom_Window_ID', PrintName='Zoom_Window_ID',Updated=TO_TIMESTAMP('2013-10-22 16:51:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=56524
;

-- Oct 22, 2013 4:51:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=56524
;

-- Oct 22, 2013 4:51:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET ColumnName='zoom_window_id', Name='Zoom_Window_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=56524
;

-- Oct 22, 2013 4:51:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Process_Para SET ColumnName='zoom_window_id', Name='Zoom_Window_ID', Description=NULL, Help=NULL, AD_Element_ID=56524 WHERE UPPER(ColumnName)='ZOOM_WINDOW_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 22, 2013 4:51:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Process_Para SET ColumnName='zoom_window_id', Name='Zoom_Window_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=56524 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 4:51:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Zoom_Window_ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=56524) AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 4:51:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_PrintFormatItem SET PrintName='Zoom_Window_ID', Name='Zoom_Window_ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=56524)
;

-- Oct 22, 2013 4:53:08 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Element SET ColumnName='Zoom_Window_ID',Updated=TO_TIMESTAMP('2013-10-22 16:53:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=56524
;

-- Oct 22, 2013 4:53:08 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET ColumnName='Zoom_Window_ID', Name='Zoom_Window_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=56524
;

-- Oct 22, 2013 4:53:08 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Process_Para SET ColumnName='Zoom_Window_ID', Name='Zoom_Window_ID', Description=NULL, Help=NULL, AD_Element_ID=56524 WHERE UPPER(ColumnName)='ZOOM_WINDOW_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 22, 2013 4:53:08 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Process_Para SET ColumnName='Zoom_Window_ID', Name='Zoom_Window_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=56524 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 4:53:47 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET ColumnName='Zoom_Tab_ID',Updated=TO_TIMESTAMP('2013-10-22 16:53:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69114
;

-- Oct 22, 2013 4:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Element SET ColumnName='Zoom_Tab_ID', Name='Zoom_Tab_ID', PrintName='Zoom_Tab_ID',Updated=TO_TIMESTAMP('2013-10-22 16:53:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=56525
;

-- Oct 22, 2013 4:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=56525
;

-- Oct 22, 2013 4:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET ColumnName='Zoom_Tab_ID', Name='Zoom_Tab_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=56525
;

-- Oct 22, 2013 4:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Process_Para SET ColumnName='Zoom_Tab_ID', Name='Zoom_Tab_ID', Description=NULL, Help=NULL, AD_Element_ID=56525 WHERE UPPER(ColumnName)='ZOOM_TAB_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 22, 2013 4:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Process_Para SET ColumnName='Zoom_Tab_ID', Name='Zoom_Tab_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=56525 AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 4:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Zoom_Tab_ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=56525) AND IsCentrallyMaintained='Y'
;

-- Oct 22, 2013 4:53:58 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_PrintFormatItem SET PrintName='Zoom_Tab_ID', Name='Zoom_Tab_ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=56525)
;

-- Oct 22, 2013 4:54:35 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET ColumnName='zoom_tab_id',Updated=TO_TIMESTAMP('2013-10-22 16:54:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69114
;

-- Oct 22, 2013 4:54:43 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET ColumnName='Zoom_Tab_ID',Updated=TO_TIMESTAMP('2013-10-22 16:54:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69114
;

-- Oct 22, 2013 5:02:23 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52233,'AD_Tab.AD_Window_ID=@AD_Window_ID@',TO_TIMESTAMP('2013-10-22 17:02:23','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','AD_Tab''s in AD_Window','S',TO_TIMESTAMP('2013-10-22 17:02:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 22, 2013 5:03:43 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Column SET AD_Val_Rule_ID=52233, MandatoryLogic='@isdynamicdashboard@=''Y''',Updated=TO_TIMESTAMP('2013-10-22 17:03:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69112
;

-- Oct 22, 2013 5:08:10 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Name='Zoom Window',Updated=TO_TIMESTAMP('2013-10-22 17:08:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 5:08:10 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 5:09:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Description='The zoom tab must have default Adempiere Columns. If the table is View Then  View Name must be Actual Table Name and It must ends with "_v" or  "_vt". This table or view ID must be exist in selected dashboard tab.', DisplayLogic='@Zoom_Window_ID@>0', Help='Select tab from selected zoom window to display as zoom tab, The zoom tab must have default Adempiere Columns. If the table is View Then  View Name must be Actual Table Name and It must ends with "_v" or  "_vt". This table or view ID must be exist in selected dashboard tab.', Name='Zoom Tab',Updated=TO_TIMESTAMP('2013-10-22 17:09:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 5:09:09 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70003
;

-- Oct 22, 2013 5:09:26 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Description='Select window to display as zoom window', Help='Select window to display as zoom window',Updated=TO_TIMESTAMP('2013-10-22 17:09:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 5:09:26 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70004
;

-- Oct 22, 2013 5:09:47 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Description='Check this to create Dynamic Dashboard', Help='Check this to create Dynamic Dashboard',Updated=TO_TIMESTAMP('2013-10-22 17:09:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 5:09:47 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69998
;

-- Oct 22, 2013 5:10:11 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Description='Select tab of selected window, which you would like to display as Dash Board ', Help='Select tab of selected window, which you would like to display as Dash Board ',Updated=TO_TIMESTAMP('2013-10-22 17:10:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70002
;

-- Oct 22, 2013 5:10:11 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70002
;

-- Oct 22, 2013 5:10:23 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Description='Check if you wish to enable mouse events on user Dash Board. (ex: On Click, On Double Click , etc..)', Help='Check if you wish to enable mouse events on user Dash Board. (ex: On Click, On Double Click , etc..)',Updated=TO_TIMESTAMP('2013-10-22 17:10:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 5:10:23 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69999
;

-- Oct 22, 2013 5:10:34 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Description='Specify a number to show number of rows on each page of dash board.', Help='Specify a number to show number of rows on each page of dash board.',Updated=TO_TIMESTAMP('2013-10-22 17:10:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 5:10:34 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70001
;

-- Oct 22, 2013 5:10:48 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field SET Description='Select a mouse event OnClick or Double Click. default is Double Click.', Help='Select a mouse event OnClick or Double Click. default is Double Click.',Updated=TO_TIMESTAMP('2013-10-22 17:10:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 22, 2013 5:10:48 PM IST
-- Configurable Dashboard Functionality in ADempiere 380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70000
;

ALTER TABLE PA_DashboardContent DROP COLUMN AD_Tab_ID
;

ALTER TABLE PA_DashboardContent DROP COLUMN IsDynamicDashboard
;

ALTER TABLE PA_DashboardContent ADD COLUMN AD_Browse_ID Numeric(10,0)
;

ALTER TABLE PA_DashboardContent ADD COLUMN Zoom_Field_ID Numeric(10,0)
;

-- Oct 29, 2013 10:34:29 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=69998
;

-- Oct 29, 2013 10:34:29 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Field WHERE AD_Field_ID=69998
;

-- Oct 29, 2013 10:34:35 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=70002
;

-- Oct 29, 2013 10:34:35 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Field WHERE AD_Field_ID=70002
;

-- Oct 29, 2013 10:40:08 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=69112
;

-- Oct 29, 2013 10:40:08 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Column WHERE AD_Column_ID=69112
;

-- Oct 29, 2013 10:40:16 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Val_Rule WHERE AD_Val_Rule_ID=52233
;

-- Oct 29, 2013 10:40:46 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=69110
;

-- Oct 29, 2013 10:40:46 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Column WHERE AD_Column_ID=69110
;

-- Oct 29, 2013 10:41:30 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Element SET ColumnName='IsEventRequired', Name='IsEventRequired', PrintName='IsEventRequired',Updated=TO_TIMESTAMP('2013-10-29 10:41:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=56523
;

-- Oct 29, 2013 10:41:30 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=56523
;

-- Oct 29, 2013 10:41:30 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET ColumnName='IsEventRequired', Name='IsEventRequired', Description=NULL, Help=NULL WHERE AD_Element_ID=56523
;

-- Oct 29, 2013 10:41:30 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Process_Para SET ColumnName='IsEventRequired', Name='IsEventRequired', Description=NULL, Help=NULL, AD_Element_ID=56523 WHERE UPPER(ColumnName)='ISEVENTREQUIRED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 29, 2013 10:41:30 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Process_Para SET ColumnName='IsEventRequired', Name='IsEventRequired', Description=NULL, Help=NULL WHERE AD_Element_ID=56523 AND IsCentrallyMaintained='Y'
;

-- Oct 29, 2013 10:41:30 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET Name='IsEventRequired', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=56523) AND IsCentrallyMaintained='Y'
;

-- Oct 29, 2013 10:41:30 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_PrintFormatItem SET PrintName='IsEventRequired', Name='IsEventRequired' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=56523)
;

-- Oct 29, 2013 10:43:51 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET Name='Is Event Required',Updated=TO_TIMESTAMP('2013-10-29 10:43:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 29, 2013 10:43:51 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69999
;

-- Oct 29, 2013 10:44:54 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2013-10-29 10:44:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 29, 2013 10:45:34 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2013-10-29 10:45:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 29, 2013 10:46:16 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2013-10-29 10:46:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56486
;

-- Oct 29, 2013 10:47:12 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Element SET ColumnName='PageSize', Name='PageSize', PrintName='PageSize',Updated=TO_TIMESTAMP('2013-10-29 10:47:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=56526
;

-- Oct 29, 2013 10:47:12 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=56526
;

-- Oct 29, 2013 10:47:12 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET ColumnName='PageSize', Name='PageSize', Description=NULL, Help=NULL WHERE AD_Element_ID=56526
;

-- Oct 29, 2013 10:47:12 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Process_Para SET ColumnName='PageSize', Name='PageSize', Description=NULL, Help=NULL, AD_Element_ID=56526 WHERE UPPER(ColumnName)='PAGESIZE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 29, 2013 10:47:12 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Process_Para SET ColumnName='PageSize', Name='PageSize', Description=NULL, Help=NULL WHERE AD_Element_ID=56526 AND IsCentrallyMaintained='Y'
;

-- Oct 29, 2013 10:47:12 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET Name='PageSize', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=56526) AND IsCentrallyMaintained='Y'
;

-- Oct 29, 2013 10:47:12 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_PrintFormatItem SET PrintName='PageSize', Name='PageSize' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=56526)
;

-- Oct 29, 2013 10:47:42 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET Name='Page Size',Updated=TO_TIMESTAMP('2013-10-29 10:47:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69115
;

-- Oct 29, 2013 10:47:42 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=69115
;

-- Oct 29, 2013 10:47:42 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET Name='Page Size', Description=NULL, Help=NULL WHERE AD_Column_ID=69115 AND IsCentrallyMaintained='Y'
;

-- Oct 29, 2013 10:48:13 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69430,56522,0,20,50010,'isdynamicdashboard',TO_TIMESTAMP('2013-10-29 10:48:12','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','isdynamicdashboard',TO_TIMESTAMP('2013-10-29 10:48:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 29, 2013 10:48:13 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69430 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 29, 2013 10:48:13 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69431,125,0,19,50010,'AD_Tab_ID',TO_TIMESTAMP('2013-10-29 10:48:13','YYYY-MM-DD HH24:MI:SS'),0,'Tab within a Window','D',10,'The Tab indicates a tab that displays within a window.','Y','N','N','N','N','N','N','N','N','Y','Tab',TO_TIMESTAMP('2013-10-29 10:48:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 29, 2013 10:48:13 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69431 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 29, 2013 10:48:14 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69432,53902,0,19,50010,'AD_Browse_ID',TO_TIMESTAMP('2013-10-29 10:48:13','YYYY-MM-DD HH24:MI:SS'),0,'D',10,'Y','N','N','N','N','N','N','N','N','Y','Smart Browse',TO_TIMESTAMP('2013-10-29 10:48:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 29, 2013 10:48:14 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69432 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 29, 2013 10:48:15 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56568,0,'zoom_field_id',TO_TIMESTAMP('2013-10-29 10:48:14','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','zoom_field_id','zoom_field_id',TO_TIMESTAMP('2013-10-29 10:48:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 29, 2013 10:48:15 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56568 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 29, 2013 10:48:17 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,69433,56568,0,19,50010,'zoom_field_id',TO_TIMESTAMP('2013-10-29 10:48:14','YYYY-MM-DD HH24:MI:SS'),0,'D',10,'Y','N','N','N','N','N','N','N','N','Y','zoom_field_id',TO_TIMESTAMP('2013-10-29 10:48:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 29, 2013 10:48:17 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69433 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 29, 2013 10:48:38 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=69431
;

-- Oct 29, 2013 10:48:38 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Column WHERE AD_Column_ID=69431
;

-- Oct 29, 2013 10:48:52 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=69430
;

-- Oct 29, 2013 10:48:52 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Column WHERE AD_Column_ID=69430
;

-- Oct 29, 2013 10:48:57 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Element_Trl WHERE AD_Element_ID=56522
;

-- Oct 29, 2013 10:48:57 AM IST
-- Modifications of Dashboard Content Edit Window
DELETE FROM AD_Element WHERE AD_Element_ID=56522
;

-- Oct 29, 2013 10:49:24 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Element SET ColumnName='Zoom_Field_ID', Name='Zoom_Field_ID', PrintName='Zoom_Field_ID',Updated=TO_TIMESTAMP('2013-10-29 10:49:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=56568
;

-- Oct 29, 2013 10:49:24 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=56568
;

-- Oct 29, 2013 10:49:24 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET ColumnName='Zoom_Field_ID', Name='Zoom_Field_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=56568
;

-- Oct 29, 2013 10:49:24 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Process_Para SET ColumnName='Zoom_Field_ID', Name='Zoom_Field_ID', Description=NULL, Help=NULL, AD_Element_ID=56568 WHERE UPPER(ColumnName)='ZOOM_FIELD_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 29, 2013 10:49:24 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Process_Para SET ColumnName='Zoom_Field_ID', Name='Zoom_Field_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=56568 AND IsCentrallyMaintained='Y'
;

-- Oct 29, 2013 10:49:24 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET Name='Zoom_Field_ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=56568) AND IsCentrallyMaintained='Y'
;

-- Oct 29, 2013 10:49:24 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_PrintFormatItem SET PrintName='Zoom_Field_ID', Name='Zoom_Field_ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=56568)
;

-- Oct 29, 2013 10:50:28 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69432,70189,0,50010,TO_TIMESTAMP('2013-10-29 10:50:27','YYYY-MM-DD HH24:MI:SS'),0,10,'D','Y','Y','Y','N','N','N','N','N','Smart Browse',TO_TIMESTAMP('2013-10-29 10:50:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 29, 2013 10:50:28 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70189 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 29, 2013 10:50:28 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,69433,70190,0,50010,TO_TIMESTAMP('2013-10-29 10:50:28','YYYY-MM-DD HH24:MI:SS'),0,10,'D','Y','Y','Y','N','N','N','N','N','Zoom_Field_ID',TO_TIMESTAMP('2013-10-29 10:50:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 29, 2013 10:50:28 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70190 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70189
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69999
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70001
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56486
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=51012
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57344
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56504
;

-- Oct 29, 2013 10:50:55 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-10-29 10:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=60822
;

-- Oct 29, 2013 10:51:08 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET Name='Zoom Field',Updated=TO_TIMESTAMP('2013-10-29 10:51:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 10:51:08 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 10:53:06 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET Description='Zoom Field will display all the columns in drop down from the Selected Tab.', Help='Zoom Field is used to zoom to the particular window based on the field selected.',Updated=TO_TIMESTAMP('2013-10-29 10:53:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 10:53:06 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 10:54:46 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52242,'AD_Field.AD_Tab_ID=@Zoom_Tab_ID@',TO_TIMESTAMP('2013-10-29 10:54:46','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','AD_Fields of Zoom Tab','S',TO_TIMESTAMP('2013-10-29 10:54:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 29, 2013 10:55:11 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET AD_Val_Rule_ID=52242,Updated=TO_TIMESTAMP('2013-10-29 10:55:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69433
;

-- Oct 29, 2013 10:56:42 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53588,TO_TIMESTAMP('2013-10-29 10:56:41','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','PA_Dashboard_Fields',TO_TIMESTAMP('2013-10-29 10:56:41','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Oct 29, 2013 10:56:42 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53588 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Oct 29, 2013 10:57:08 AM IST
-- Modifications of Dashboard Content Edit Window
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsAlert,IsDisplayIdentifier,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,168,167,0,53588,107,TO_TIMESTAMP('2013-10-29 10:57:08','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','N','N',TO_TIMESTAMP('2013-10-29 10:57:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 29, 2013 10:57:21 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53588,Updated=TO_TIMESTAMP('2013-10-29 10:57:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69433
;

-- Oct 29, 2013 11:00:01 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic='@iseventrequired@=''Y'' AND @Zoom_Window_ID@>0  ',Updated=TO_TIMESTAMP('2013-10-29 11:00:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 29, 2013 11:00:45 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic='@iseventrequired@=''Y'' AND @Zoom_Tab_ID@>0  ',Updated=TO_TIMESTAMP('2013-10-29 11:00:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 11:00:53 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-10-29 11:00:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 11:04:02 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic='@Zoom_Window_ID@>0  ',Updated=TO_TIMESTAMP('2013-10-29 11:04:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Oct 29, 2013 11:04:12 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic='@Zoom_Tab_ID@>0  ',Updated=TO_TIMESTAMP('2013-10-29 11:04:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 11:05:31 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET MandatoryLogic='@iseventrequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-29 11:05:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69433
;

-- Oct 29, 2013 11:10:48 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Val_Rule SET Code='AD_Field.AD_Tab_ID=@Zoom_Tab_ID@ AND AD_Field.IsDisplayed=''Y''',Updated=TO_TIMESTAMP('2013-10-29 11:10:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=52242
;

-- Oct 29, 2013 11:15:33 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic='@IsEventRequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-29 11:15:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70004
;

-- Oct 29, 2013 11:15:53 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET DisplayLogic='@IsEventRequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-29 11:15:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70000
;

-- Oct 29, 2013 11:18:15 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET MandatoryLogic='@IsEventRequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-29 11:18:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69433
;

-- Oct 29, 2013 11:18:25 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET MandatoryLogic='@IsEventRequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-29 11:18:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69114
;

-- Oct 29, 2013 11:18:34 AM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Column SET MandatoryLogic='@IsEventRequired@=''Y''',Updated=TO_TIMESTAMP('2013-10-29 11:18:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=69113
;

-- Oct 29, 2013 3:26:53 PM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Ref_Table SET AD_Display=57981, AD_Key=57968, AD_Table_ID=53223,Updated=TO_TIMESTAMP('2013-10-29 15:26:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53588
;

-- Oct 29, 2013 3:27:08 PM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Reference SET Name='AD_Browse_Fields',Updated=TO_TIMESTAMP('2013-10-29 15:27:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53588
;

-- Oct 29, 2013 3:27:08 PM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53588
;

-- Oct 29, 2013 3:28:17 PM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Val_Rule SET Code='AD_Browse_Field.AD_Browse_ID=@AD_Browse_ID@',Updated=TO_TIMESTAMP('2013-10-29 15:28:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=52242
;

-- Oct 29, 2013 4:11:05 PM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field SET Help='Zoom Field has to be the primary key of the Zoom Tab selected. For example, for Zoom Tab Business Partner in Business Partner window, Zoom Field must be C_BPartner_ID.',Updated=TO_TIMESTAMP('2013-10-29 16:11:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;

-- Oct 29, 2013 4:11:05 PM IST
-- Modifications of Dashboard Content Edit Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70190
;

-- Oct 23, 2013 11:31:22 AM IST
-- System Configuration Parameter " DASHBOARD_PAGE_SIZE "
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50075,'C',TO_TIMESTAMP('2013-10-23 11:31:21','YYYY-MM-DD HH24:MI:SS'),0,'Default page size for a Dashboard','D','Y','DASHBOARD_PAGE_SIZE',TO_TIMESTAMP('2013-10-23 11:31:21','YYYY-MM-DD HH24:MI:SS'),0,'5')
;