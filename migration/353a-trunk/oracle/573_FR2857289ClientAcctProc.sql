-- Sep 13, 2009 5:53:15 PM COT
-- FR2857289-Run Accounting Embedded Server
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('2',0,0,53187,'org.adempiere.process.ClientAcctProcessor',TO_DATE('2009-09-13 17:53:15','YYYY-MM-DD HH24:MI:SS'),100,'Client Accounting Processor','D','The client accounting processor allows a user to run the accounting processor in a client session.  The system configurator parameter CLIENT_ACCOUNTING must be enabled (configured as [I]mmediate or [Q]ueue).','Y','N','N','N','N','Client Accounting Processor','Y',0,0,TO_DATE('2009-09-13 17:53:15','YYYY-MM-DD HH24:MI:SS'),100,'Client_Acct_Processor')
;

-- Sep 13, 2009 5:53:15 PM COT
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53187 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 13, 2009 5:57:59 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,181,0,53187,53345,19,'C_AcctSchema_ID',TO_DATE('2009-09-13 17:57:59','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Schema','D',0,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','N','N','Accounting Schema',10,TO_DATE('2009-09-13 17:57:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 13, 2009 5:57:59 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53345 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 13, 2009 5:59:21 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,126,0,53187,53346,19,213,'AD_Table_ID',TO_DATE('2009-09-13 17:59:17','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Database Table information','D',10,'The Database Table provides the information of the table definition','Y','Y','N','N','Table',10,TO_DATE('2009-09-13 17:59:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 13, 2009 5:59:21 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53346 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 13, 2009 5:59:25 PM COT
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2009-09-13 17:59:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53345
;

-- Sep 13, 2009 5:59:59 PM COT
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53248,0,53187,TO_DATE('2009-09-13 17:59:58','YYYY-MM-DD HH24:MI:SS'),100,'Client Accounting Processor','D','Y','N','N','N','Client Accounting Processor',TO_DATE('2009-09-13 17:59:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 13, 2009 5:59:59 PM COT
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53248 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 13, 2009 5:59:59 PM COT
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53248, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53248)
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53242
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=265
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=104
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=105
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=384
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=111
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=106
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=117
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=418
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=102
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=103
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=270
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=121
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=476
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=409
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=151
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53087
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=464
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=124
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=123
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=547
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53189
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=174
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=254
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=120
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=135
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=550
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=26, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=551
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=27, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=306
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=28, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53091
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=29, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=417
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=30, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=307
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=31, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=393
;

-- Sep 13, 2009 6:00:04 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=32, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53248
;

-- Sep 13, 2009 6:15:25 PM COT
-- FR2857289-Run Accounting Embedded Server
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53083,0,TO_DATE('2009-09-13 18:15:24','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Client Accounting is not enabled','In order to run this process you need to enable client accounting, this can be done in window System Configurator, setting the parameter CLIENT_ACCOUNTING to [I]mmediate or [Q]ueue','E',TO_DATE('2009-09-13 18:15:24','YYYY-MM-DD HH24:MI:SS'),100,'ClientAccountingNotEnabled')
;

-- Sep 13, 2009 6:15:25 PM COT
-- FR2857289-Run Accounting Embedded Server
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53083 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- Sep 13, 2009 6:18:05 PM COT
-- FR2857289-Run Accounting Embedded Server
UPDATE AD_Process_Para SET SeqNo=20,Updated=TO_DATE('2009-09-13 18:18:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53346
;

