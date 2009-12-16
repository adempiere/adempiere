-- Dec 15, 2009 8:56:44 PM COT
-- 2915244_Propose for Business Partner Setup workflow
UPDATE AD_WF_NodeNext SET AD_WF_Next_ID=136,Updated=TO_DATE('2009-12-15 20:56:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_NodeNext_ID=134
;

-- Dec 15, 2009 8:56:55 PM COT
UPDATE AD_WF_NodeNext SET AD_WF_Next_ID=138,Updated=TO_DATE('2009-12-15 20:56:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_NodeNext_ID=136
;

-- Dec 15, 2009 8:57:06 PM COT
DELETE FROM AD_WF_NodeNext WHERE AD_WF_NodeNext_ID=135
;

-- Dec 15, 2009 8:57:11 PM COT
DELETE  FROM  AD_WF_Node_Trl WHERE AD_WF_Node_ID=135
;

-- Dec 15, 2009 8:57:11 PM COT
DELETE FROM AD_WF_Node WHERE AD_WF_Node_ID=135
;

-- Dec 15, 2009 8:57:58 PM COT
DELETE FROM AD_WF_NodeNext WHERE AD_WF_NodeNext_ID=137
;

-- Dec 15, 2009 8:58:02 PM COT
DELETE  FROM  AD_WF_Node_Trl WHERE AD_WF_Node_ID=137
;

-- Dec 15, 2009 8:58:02 PM COT
DELETE FROM AD_WF_Node WHERE AD_WF_Node_ID=137
;

-- Dec 15, 2009 8:58:28 PM COT
UPDATE AD_WF_Node SET XPosition=167, YPosition=172,Updated=TO_DATE('2009-12-15 20:58:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=138
;

-- Dec 15, 2009 8:58:28 PM COT
UPDATE AD_WF_Node SET XPosition=3, YPosition=260,Updated=TO_DATE('2009-12-15 20:58:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=139
;

-- Dec 15, 2009 8:58:28 PM COT
UPDATE AD_WF_Node SET XPosition=2, YPosition=171,Updated=TO_DATE('2009-12-15 20:58:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=136
;

-- Dec 15, 2009 8:59:25 PM COT
INSERT INTO AD_WF_Node (Action,AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,Cost,Created,CreatedBy,Duration,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Limit,Name,SplitElement,Updated,UpdatedBy,Value,WaitingTime,XPosition,YPosition) VALUES ('Z',0,0,50098,107,0,TO_DATE('2009-12-15 20:59:23','YYYY-MM-DD HH24:MI:SS'),100,0,'U','Y','Y','X',0,'Revenue Recognition','X',TO_DATE('2009-12-15 20:59:23','YYYY-MM-DD HH24:MI:SS'),100,'Revenue Recognition',0,0,0)
;

-- Dec 15, 2009 8:59:25 PM COT
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50098 AND NOT EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_WF_Node_ID=t.AD_WF_Node_ID)
;

-- Dec 15, 2009 9:00:49 PM COT
UPDATE AD_WF_Node SET Action='W', AD_Window_ID=174, Description='Revenue Recognition Rules', EntityType='D', Help='The Revenue Recognition Window defines the intervals at which revenue will be recognized. Alternatively, the revenue recognition may be linked to service levels provided.',Updated=TO_DATE('2009-12-15 21:00:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50098
;

-- Dec 15, 2009 9:00:49 PM COT
UPDATE AD_WF_Node_Trl SET IsTranslated='N' WHERE AD_WF_Node_ID=50098
;

-- Dec 15, 2009 9:01:16 PM COT
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_Node_ID,AD_WF_NodeNext_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,161,50098,50075,TO_DATE('2009-12-15 21:01:15','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',10,TO_DATE('2009-12-15 21:01:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2009 9:01:27 PM COT
UPDATE AD_WF_NodeNext SET AD_WF_Next_ID=50098,Updated=TO_DATE('2009-12-15 21:01:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_NodeNext_ID=171
;

-- Dec 15, 2009 9:04:29 PM COT
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType,WinHeight,WinWidth) VALUES (0,0,53104,TO_DATE('2009-12-15 21:04:28','YYYY-MM-DD HH24:MI:SS'),100,'Define Withholding','D','The Withholding Window defines withholding information for business partners.','Y','Y','N','Y','Withholding','N',TO_DATE('2009-12-15 21:04:28','YYYY-MM-DD HH24:MI:SS'),100,'M',0,0)
;

-- Dec 15, 2009 9:04:29 PM COT
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53104 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- MANUAL
update ad_tab set ad_window_id = 53104, seqno=10, tablevel=0, ad_column_id=null, isadvancedtab='N' where AD_Tab_ID=229
;

-- Dec 15, 2009 9:09:00 PM COT
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('W',0,53256,0,53104,TO_DATE('2009-12-15 21:08:59','YYYY-MM-DD HH24:MI:SS'),100,'Define Withholding','D','Y','N','Y','N','Withholding',TO_DATE('2009-12-15 21:08:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2009 9:09:00 PM COT
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53256 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Dec 15, 2009 9:09:00 PM COT
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', SysDate, 100, SysDate, 100,t.AD_Tree_ID, 53256, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53256)
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=266
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=232
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=190
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=127
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=133
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=172
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=173
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53256
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=110
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=394
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=544
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=512
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=506
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=420
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=451
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=186
;

-- Dec 15, 2009 9:09:41 PM COT
UPDATE AD_TreeNodeMM SET Parent_ID=165, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=473
;

-- Dec 15, 2009 9:13:49 PM COT
UPDATE AD_Tab SET IsReadOnly='N',Updated=TO_DATE('2009-12-15 21:13:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=229
;

-- Dec 15, 2009 9:14:14 PM COT
UPDATE AD_Field SET IsReadOnly='N',Updated=TO_DATE('2009-12-15 21:14:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2225
;

-- Dec 15, 2009 9:29:44 PM COT
UPDATE AD_Column SET MandatoryLogic=NULL,Updated=TO_DATE('2009-12-15 21:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3116
;

-- Dec 15, 2009 9:29:49 PM COT
UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=1,Updated=TO_DATE('2009-12-15 21:29:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3113
;

-- Dec 15, 2009 9:29:53 PM COT
UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=2,Updated=TO_DATE('2009-12-15 21:29:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3114
;

-- Dec 15, 2009 9:29:56 PM COT
UPDATE AD_Column SET MandatoryLogic=NULL,Updated=TO_DATE('2009-12-15 21:29:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3119
;

-- Dec 15, 2009 9:34:18 PM COT
UPDATE AD_Tab SET IsInsertRecord='Y',Updated=TO_DATE('2009-12-15 21:34:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=229
;

