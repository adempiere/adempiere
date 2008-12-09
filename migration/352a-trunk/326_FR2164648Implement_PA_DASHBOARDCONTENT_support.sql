-- Nov 19, 2008 4:49:00 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Column SET DefaultValue='@#AD_Org_ID@', IsMandatory='Y',Updated=TO_DATE('2008-11-19 16:49:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=51006
;

-- Nov 19, 2008 3:55:41 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
ALTER TABLE PA_DashboardContent ADD ColumnNo NUMBER(10) DEFAULT 1
;

-- Nov 19, 2008 4:00:41 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
ALTER TABLE PA_DashboardContent ADD ZulFilePath NVARCHAR2(255)
;

-- Nov 19, 2008 4:57:13 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO PA_DashboardContent (AD_Client_ID,AD_Org_ID,ColumnNo,Created,CreatedBy,Description,IsActive,Line,Name,PA_DashboardContent_ID,Updated,UpdatedBy,ZulFilePath) VALUES (0,0,0,TO_DATE('2008-11-19 16:57:08','YYYY-MM-DD HH24:MI:SS'),100,'Workflow activities, notices and requests','Y',0,'Activities',50000,TO_DATE('2008-11-19 16:57:08','YYYY-MM-DD HH24:MI:SS'),100,'/zul/activities.zul')
;

-- Nov 19, 2008 4:58:26 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO PA_DashboardContent (AD_Client_ID,AD_Org_ID,ColumnNo,Created,CreatedBy,Description,IsActive,Line,Name,PA_DashboardContent_ID,Updated,UpdatedBy,ZulFilePath) VALUES (0,0,0,TO_DATE('2008-11-19 16:58:24','YYYY-MM-DD HH24:MI:SS'),100,'User favourities','Y',1.000000000000,'Favourites',50001,TO_DATE('2008-11-19 16:58:24','YYYY-MM-DD HH24:MI:SS'),100,'/zul/favourites.zul')
;

-- Nov 19, 2008 4:58:52 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO PA_DashboardContent (AD_Client_ID,AD_Org_ID,ColumnNo,Created,CreatedBy,Description,IsActive,Line,Name,PA_DashboardContent_ID,Updated,UpdatedBy,ZulFilePath) VALUES (0,0,0,TO_DATE('2008-11-19 16:58:50','YYYY-MM-DD HH24:MI:SS'),100,'Info views','Y',2.000000000000,'Views',50002,TO_DATE('2008-11-19 16:58:50','YYYY-MM-DD HH24:MI:SS'),100,'/zul/views.zul')
;

-- Nov 19, 2008 5:00:34 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO PA_DashboardContent (AD_Client_ID,AD_Org_ID,ColumnNo,Created,CreatedBy,Description,IsActive,Line,Name,PA_DashboardContent_ID,Updated,UpdatedBy,ZulFilePath) VALUES (0,0,2,TO_DATE('2008-11-19 17:00:26','YYYY-MM-DD HH24:MI:SS'),100,'Performance meters','Y',0,'Performance',50003,TO_DATE('2008-11-19 17:00:26','YYYY-MM-DD HH24:MI:SS'),100,'/zul/performance.zul')
;

-- Nov 19, 2008 5:02:55 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO PA_DashboardContent (AD_Client_ID,AD_Org_ID,ColumnNo,Created,CreatedBy,Description,IsActive,Line,Name,PA_DashboardContent_ID,Updated,UpdatedBy) VALUES (11,0,1,TO_DATE('2008-11-19 17:02:54','YYYY-MM-DD HH24:MI:SS'),100,'Google Calendar','Y',0,'Calendar',50004,TO_DATE('2008-11-19 17:02:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 19, 2008 6:00:00 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE PA_DashboardContent SET UpdatedBy=100, ZulFilePath='/zul/calendar.zul',Updated=TO_DATE('2008-11-19 18:00:00','YYYY-MM-DD HH24:MI:SS') WHERE PA_DashboardContent_ID=50004
;

-- Nov 19, 2008 6:00:00 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE PA_DashboardContent SET HTML=null WHERE PA_DashboardContent_ID=50004
;

-- Nov 19, 2008 4:28:46 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Table SET AccessLevel='6',Updated=TO_DATE('2008-11-19 16:28:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=50010
;

-- Nov 19, 2008 3:54:09 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53709,0,'ColumnNo',TO_DATE('2008-11-19 15:54:08','YYYY-MM-DD HH24:MI:SS'),100,'Dashboard content column number','D','Dashboard content column number, not used by the swing client at the moment.','Y','Column No','Column No',TO_DATE('2008-11-19 15:54:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 19, 2008 3:54:09 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53709 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Nov 19, 2008 3:55:33 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56469,53709,0,11,50010,'ColumnNo',TO_DATE('2008-11-19 15:55:28','YYYY-MM-DD HH24:MI:SS'),100,'0','Dashboard content column number','D',14,'Dashboard content column number, not used by the swing client at the moment.','Y','N','N','N','N','N','N','N','N','N','Y','Column No',0,TO_DATE('2008-11-19 15:55:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 19, 2008 3:55:33 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56469 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;


-- Nov 19, 2008 3:59:41 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53710,0,'ZulFilePath',TO_DATE('2008-11-19 15:59:37','YYYY-MM-DD HH24:MI:SS'),100,'Absolute path to zul file','D','Absolute path to zul file that is use to generate dashboard content','Y','ZUL File Path','ZUL File Path',TO_DATE('2008-11-19 15:59:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 19, 2008 3:59:41 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53710 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Nov 19, 2008 4:00:37 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56470,53710,0,10,50010,'ZulFilePath',TO_DATE('2008-11-19 16:00:36','YYYY-MM-DD HH24:MI:SS'),100,'Absolute path to zul file','D',255,'Absolute path to zul file that is use to generate dashboard content','Y','N','N','N','N','N','N','N','N','N','Y','ZUL File Path',0,TO_DATE('2008-11-19 16:00:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 19, 2008 4:00:37 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56470 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 19, 2008 4:01:04 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56469,56485,0,50010,TO_DATE('2008-11-19 16:01:03','YYYY-MM-DD HH24:MI:SS'),100,'Dashboard content column number',14,'D','Dashboard content column number, not used by the swing client at the moment.','Y','Y','Y','N','N','N','N','N','Column No',TO_DATE('2008-11-19 16:01:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 19, 2008 4:01:05 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56485 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 19, 2008 4:01:12 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56470,56486,0,50010,TO_DATE('2008-11-19 16:01:05','YYYY-MM-DD HH24:MI:SS'),100,'Absolute path to zul file',255,'D','Absolute path to zul file that is use to generate dashboard content','Y','Y','Y','N','N','N','N','N','ZUL File Path',TO_DATE('2008-11-19 16:01:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 19, 2008 4:01:12 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56486 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 19, 2008 4:01:25 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56485
;

-- Nov 19, 2008 4:01:25 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=51009
;

-- Nov 19, 2008 4:01:25 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=51010
;

-- Nov 19, 2008 4:01:25 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=51011
;

-- Nov 19, 2008 4:01:25 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56486
;

-- Nov 19, 2008 4:01:26 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=51012
;

-- Nov 19, 2008 4:01:26 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=51013
;

-- Nov 19, 2008 4:01:54 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Field SET DisplayLength=255,Updated=TO_DATE('2008-11-19 16:01:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=51011
;

-- Nov 19, 2008 4:09:07 PM SGT
-- [ 2164648 ] FR:Implement PA_DASHBOARDCONTENT support
UPDATE AD_Column SET DefaultValue='1',Updated=TO_DATE('2008-11-19 16:09:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56469
;

