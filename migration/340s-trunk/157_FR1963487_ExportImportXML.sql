-- May 11, 2008 11:19:42 PM CDT
-- Fixed Libero Multi Print BOM
UPDATE AD_Process SET AD_PrintFormat_ID=NULL, AD_ReportView_ID=NULL,Updated=TO_DATE('2008-05-11 23:19:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53006
;

-- May 13, 2008 7:14:59 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53089,'3','org.adempiere.server.rpl.imp.ModelExporter',TO_DATE('2008-05-13 19:14:56','YYYY-MM-DD HH24:MI:SS'),0,'Test Export of XML files','EE05','Y','N','N','N','N','Test Export Model','Y',6,8,TO_DATE('2008-05-13 19:14:56','YYYY-MM-DD HH24:MI:SS'),0,'TestExportModel')
;

-- May 13, 2008 7:14:59 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53089 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- May 13, 2008 7:15:00 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53089,0,TO_DATE('2008-05-13 19:15:00','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-05-13 19:15:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:15:00 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53089,102,TO_DATE('2008-05-13 19:15:00','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-05-13 19:15:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:15:00 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53089,103,TO_DATE('2008-05-13 19:15:00','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-05-13 19:15:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:15:00 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53089,50001,TO_DATE('2008-05-13 19:15:00','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-05-13 19:15:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:15:54 PM CDT
-- New Process to Export Format
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53467,0,'TestExportModel',TO_DATE('2008-05-13 19:15:53','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','Test Export Model','Test Export Model',TO_DATE('2008-05-13 19:15:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:15:55 PM CDT
-- New Process to Export Format
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53467 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 13, 2008 7:17:10 PM CDT
-- New Process to Export Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55328,53467,0,53089,28,53072,'TestExportModel',TO_DATE('2008-05-13 19:17:07','YYYY-MM-DD HH24:MI:SS'),0,'EE05',1,'Y','Y','N','N','N','N','N','N','Y','N','Y','Test Export Model',150,TO_DATE('2008-05-13 19:17:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 13, 2008 7:17:10 PM CDT
-- New Process to Export Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55328 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 13, 2008 7:17:14 PM CDT
-- New Process to Export Format
ALTER TABLE EXP_Format ADD TestExportModel CHAR(1)
;

-- May 13, 2008 7:17:28 PM CDT
-- New Process to Export Format
UPDATE AD_Process SET Value='EXP_Format TestImportModel',Updated=TO_DATE('2008-05-13 19:17:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53074
;

-- May 13, 2008 7:17:47 PM CDT
-- New Process to Export Format
UPDATE AD_Process SET Value='EXP_Format TestExportModel',Updated=TO_DATE('2008-05-13 19:17:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53089
;

-- May 13, 2008 7:20:25 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,50022,0,53074,53145,39,'File_Directory',TO_DATE('2008-05-13 19:20:23','YYYY-MM-DD HH24:MI:SS'),0,'EE05',20,'Y','Y','N','N','File Directory',10,TO_DATE('2008-05-13 19:20:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:20:25 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53145 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 13, 2008 7:22:40 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,50022,0,53089,53146,38,'File_Directory',TO_DATE('2008-05-13 19:22:39','YYYY-MM-DD HH24:MI:SS'),0,'EE05',20,'Y','Y','N','N','File Directory',10,TO_DATE('2008-05-13 19:22:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:22:40 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53146 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 13, 2008 7:23:10 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para SET AD_Element_ID=2295, ColumnName='FileName', Name='FileName',Updated=TO_DATE('2008-05-13 19:23:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53145
;

-- May 13, 2008 7:23:10 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53145
;

-- May 13, 2008 7:23:36 PM CDT
-- New Process to Export Format
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55328,55417,0,53085,TO_DATE('2008-05-13 19:23:34','YYYY-MM-DD HH24:MI:SS'),0,1,'EE05','Y','Y','Y','N','N','N','N','N','Test Export Model',TO_DATE('2008-05-13 19:23:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:23:36 PM CDT
-- New Process to Export Format
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55417 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- May 13, 2008 7:23:53 PM CDT
-- New Process to Export Format
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-05-13 19:23:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=55417
;

-- May 13, 2008 7:25:13 PM CDT
-- New Process to Export Format
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53130,0,53089,'P',TO_DATE('2008-05-13 19:25:11','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','Export Record',TO_DATE('2008-05-13 19:25:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:25:13 PM CDT
-- New Process to Export Format
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53130 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- May 13, 2008 7:25:13 PM CDT
-- New Process to Export Format
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53130, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53130)
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53099
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=385
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=386
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53100
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53125
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53102
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53130
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53101
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53104
;

-- May 13, 2008 7:25:17 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53103
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53099
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=385
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=386
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53100
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53125
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53130
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53102
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53101
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53104
;

-- May 13, 2008 7:25:28 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53103
;

-- May 13, 2008 7:25:51 PM CDT
-- New Process to Export Format
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53131,0,53074,'P',TO_DATE('2008-05-13 19:25:48','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','Import Record',TO_DATE('2008-05-13 19:25:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:25:51 PM CDT
-- New Process to Export Format
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53131 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- May 13, 2008 7:25:51 PM CDT
-- New Process to Export Format
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53131, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53131)
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53099
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=385
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=386
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53100
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53125
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53130
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53131
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53102
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53101
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53104
;

-- May 13, 2008 7:25:56 PM CDT
-- New Process to Export Format
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53103
;

-- May 13, 2008 7:27:07 PM CDT
-- New Process to Export Format
UPDATE AD_Element SET Name='Export Format ID', PrintName='Export Format ID',Updated=TO_DATE('2008-05-13 19:27:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53368
;

-- May 13, 2008 7:27:08 PM CDT
-- New Process to Export Format
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53368
;

-- May 13, 2008 7:27:08 PM CDT
-- New Process to Export Format
UPDATE AD_Column SET ColumnName='EXP_Format_ID', Name='Export Format ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53368
;

-- May 13, 2008 7:27:08 PM CDT
-- New Process to Export Format
UPDATE AD_Field SET Name='Export Format ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53368) AND IsCentrallyMaintained='Y'
;

-- May 13, 2008 7:27:08 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para SET ColumnName='EXP_Format_ID', Name='Export Format ID', Description=NULL, Help=NULL, AD_Element_ID=53368 WHERE UPPER(ColumnName)='EXP_FORMAT_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 13, 2008 7:27:08 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para SET ColumnName='EXP_Format_ID', Name='Export Format ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53368 AND IsCentrallyMaintained='Y'
;

-- May 13, 2008 7:27:08 PM CDT
-- New Process to Export Format
UPDATE AD_PrintFormatItem pi SET PrintName='Export Format ID', Name='Export Format ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53368)
;

-- May 13, 2008 7:27:08 PM CDT
-- New Process to Export Format
UPDATE AD_PrintFormatItem pi SET PrintName='Export Format ID', Name='Export Format ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53368)
;

-- May 13, 2008 7:27:31 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53368,0,53074,53147,19,'EXP_Format_ID',TO_DATE('2008-05-13 19:27:30','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','Y','N','N','Export Format',5,TO_DATE('2008-05-13 19:27:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:27:31 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53147 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 13, 2008 7:28:35 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53368,0,53089,53148,19,'EXP_Format_ID',TO_DATE('2008-05-13 19:28:34','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','Y','N','N','Export Format',5,TO_DATE('2008-05-13 19:28:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 13, 2008 7:28:35 PM CDT
-- New Process to Export Format
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53148 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 13, 2008 7:29:13 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para SET DefaultValue='@EXP_Format_ID@',Updated=TO_DATE('2008-05-13 19:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53148
;

-- May 13, 2008 7:29:22 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para SET DefaultValue='@EXP_Format_ID@',Updated=TO_DATE('2008-05-13 19:29:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53147
;

-- May 13, 2008 7:33:24 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para SET AD_Element_ID=2295, ColumnName='FileName', FieldLength=255, Name='File Name',Updated=TO_DATE('2008-05-13 19:33:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53146
;

-- May 13, 2008 7:33:24 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53146
;

-- May 13, 2008 7:33:52 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para SET FieldLength=255, Name='File Name',Updated=TO_DATE('2008-05-13 19:33:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53145
;

-- May 13, 2008 7:33:52 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53145
;

-- May 13, 2008 8:30:52 PM CDT
-- New Process to Export Format
UPDATE AD_Process SET Classname='org.adempiere.server.rpl.exp.ModelExporter',Updated=TO_DATE('2008-05-13 20:30:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53089
;

-- May 13, 2008 8:36:38 PM CDT
-- New Process to Export Format
UPDATE AD_Process SET Classname='org.adempiere.process.rpl.exp.ModelExporter',Updated=TO_DATE('2008-05-13 20:36:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53089
;

-- May 13, 2008 9:37:36 PM CDT
-- New Process to Export Format
UPDATE AD_Process SET Name='Export Format Generator',Updated=TO_DATE('2008-05-13 21:37:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53085
;

-- May 13, 2008 9:37:36 PM CDT
-- New Process to Export Format
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53085
;

-- May 13, 2008 9:37:36 PM CDT
-- New Process to Export Format
UPDATE AD_Menu SET Description='Create multiple Export Format based in a Window', IsActive='Y', Name='Export Format Generator',Updated=TO_DATE('2008-05-13 21:37:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53125
;

-- May 13, 2008 9:37:36 PM CDT
-- New Process to Export Format
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53125
;

