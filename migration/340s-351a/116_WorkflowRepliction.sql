-- Mar 5, 2008 9:50:28 PM CST
-- Workflow Replication
UPDATE AD_WF_Node SET Description='Import Proccesor', Name='Import Proccesor',Updated=TO_DATE('2008-03-05 21:50:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50064
;

-- Mar 5, 2008 9:50:28 PM CST
-- Workflow Replication
UPDATE AD_WF_Node_Trl SET IsTranslated='N' WHERE AD_WF_Node_ID=50064
;

-- Mar 5, 2008 9:51:28 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Window_ID,AD_Workflow_ID,Action,Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Limit,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50065,53029,50012,'W',0,TO_DATE('2008-03-05 21:51:21','YYYY-MM-DD HH24:MI:SS'),100,'Import Processor Type','CO',0,0,'EE05','Y','N','X',0,'Import Processor Type',0,'X',TO_DATE('2008-03-05 21:51:21','YYYY-MM-DD HH24:MI:SS'),100,'Import Processor Type',0,0,0,0,0)
;

-- Mar 5, 2008 9:51:28 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50065 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Mar 5, 2008 9:52:12 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Window_ID,AD_Workflow_ID,Action,Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Limit,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50066,53025,50012,'W',0,TO_DATE('2008-03-05 21:52:11','YYYY-MM-DD HH24:MI:SS'),100,'Export Format','CO',0,0,'EE05','Y','N','X',0,'Export Format',0,'X',TO_DATE('2008-03-05 21:52:11','YYYY-MM-DD HH24:MI:SS'),100,'Export Format',0,0,0,0,0)
;

-- Mar 5, 2008 9:52:12 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50066 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Mar 5, 2008 9:53:10 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Window_ID,AD_Workflow_ID,Action,Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Limit,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50067,53026,50012,'W',0,TO_DATE('2008-03-05 21:53:09','YYYY-MM-DD HH24:MI:SS'),100,'Export Processor','CO',0,0,'EE05','Y','N','X',0,'Export Processor',0,'X',TO_DATE('2008-03-05 21:53:09','YYYY-MM-DD HH24:MI:SS'),100,'Export Processor',0,0,0,0,0)
;

-- Mar 5, 2008 9:53:10 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50067 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Mar 5, 2008 9:53:47 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Window_ID,AD_Workflow_ID,Action,Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Limit,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50068,53027,50012,'W',0,TO_DATE('2008-03-05 21:53:46','YYYY-MM-DD HH24:MI:SS'),100,'Export Processor Type','CO',0,0,'EE05','Y','N','X',0,'Export Processor Type',0,'X',TO_DATE('2008-03-05 21:53:46','YYYY-MM-DD HH24:MI:SS'),100,'Export Processor Type',0,0,0,0,0)
;

-- Mar 5, 2008 9:53:47 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50068 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Mar 5, 2008 9:55:00 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Window_ID,AD_Workflow_ID,Action,Cost,Created,CreatedBy,Description,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Limit,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50069,285,50012,'W',0,TO_DATE('2008-03-05 21:54:59','YYYY-MM-DD HH24:MI:SS'),100,'Replication Strategy','CO',0,0,'EE05','Y','N','X',0,'Replication Strategy',0,'X',TO_DATE('2008-03-05 21:54:59','YYYY-MM-DD HH24:MI:SS'),100,'Replication Strategy',0,0,0,0,0)
;

-- Mar 5, 2008 9:55:00 PM CST
-- Workflow Replication
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50069 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Mar 5, 2008 9:55:31 PM CST
-- Workflow Replication
UPDATE AD_Workflow SET AD_WF_Node_ID=50069, IsValid='Y',Updated=TO_DATE('2008-03-05 21:55:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50012
;

-- Mar 5, 2008 9:55:56 PM CST
-- Workflow Replication
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50066,50046,50069,TO_DATE('2008-03-05 21:55:55','YYYY-MM-DD HH24:MI:SS'),100,'EE05','Y','N',10,TO_DATE('2008-03-05 21:55:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 5, 2008 9:56:12 PM CST
-- Workflow Replication
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50068,50047,50066,TO_DATE('2008-03-05 21:56:12','YYYY-MM-DD HH24:MI:SS'),100,'EE05','Y','N',10,TO_DATE('2008-03-05 21:56:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 5, 2008 9:56:41 PM CST
-- Workflow Replication
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50067,50048,50068,TO_DATE('2008-03-05 21:56:35','YYYY-MM-DD HH24:MI:SS'),100,'EE05','Y','N',10,TO_DATE('2008-03-05 21:56:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 5, 2008 9:57:00 PM CST
-- Workflow Replication
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50065,50049,50067,TO_DATE('2008-03-05 21:56:59','YYYY-MM-DD HH24:MI:SS'),100,'EE05','Y','N',10,TO_DATE('2008-03-05 21:56:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 5, 2008 9:57:18 PM CST
-- Workflow Replication
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50064,50050,50065,TO_DATE('2008-03-05 21:57:17','YYYY-MM-DD HH24:MI:SS'),100,'EE05','Y','N',10,TO_DATE('2008-03-05 21:57:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 5, 2008 10:00:19 PM CST
-- Workflow Replication
DELETE FROM AD_WF_NodeNext WHERE AD_WF_NodeNext_ID=50045
;

