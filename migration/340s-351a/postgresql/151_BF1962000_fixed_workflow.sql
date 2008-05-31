-- May 11, 2008 1:57:39 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_Workflow SET AccessLevel='1', Author='e-Evolution', Duration=1, IsValid='Y',Updated=TO_TIMESTAMP('2008-05-11 13:57:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=50008
;

-- May 11, 2008 1:58:14 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 13:58:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50026
;

-- May 11, 2008 1:59:35 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Transition)',Updated=TO_TIMESTAMP('2008-05-11 13:59:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50010
;

-- May 11, 2008 2:00:15 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Approval)', IsStdUserWorkflow='Y',Updated=TO_TIMESTAMP('2008-05-11 14:00:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50011
;

-- May 11, 2008 2:01:11 PM CDT
-- Fixed workflow to Libero Document
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,Description,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50026,50063,50029,TO_TIMESTAMP('2008-05-11 14:01:09','YYYY-MM-DD HH24:MI:SS'),0,'(Standard Transition)','EE01','Y','N',100,TO_TIMESTAMP('2008-05-11 14:01:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 11, 2008 2:04:02 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_Workflow SET Description='(Standard Process Manufacturing Order)', IsValid='Y',Updated=TO_TIMESTAMP('2008-05-11 14:04:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=50008
;

-- May 11, 2008 2:04:02 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_Workflow_Trl SET IsTranslated='N' WHERE AD_Workflow_ID=50008
;

-- May 11, 2008 2:04:02 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET Description='(Standard Process Manufacturing Order)', Help=NULL, Name='Process_Manufacturing_Order',Updated=TO_TIMESTAMP('2008-05-11 14:04:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50026
;

-- May 11, 2008 2:04:02 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node_Trl SET IsTranslated='N' WHERE AD_WF_Node_ID=50026
;

-- May 11, 2008 2:04:19 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET XPosition=182, YPosition=143,Updated=TO_TIMESTAMP('2008-05-11 14:04:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50027
;

-- May 11, 2008 2:04:19 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET XPosition=182, YPosition=7,Updated=TO_TIMESTAMP('2008-05-11 14:04:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50028
;

-- May 11, 2008 2:04:19 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET XPosition=4, YPosition=6,Updated=TO_TIMESTAMP('2008-05-11 14:04:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50029
;

-- May 11, 2008 2:04:19 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET XPosition=4, YPosition=147,Updated=TO_TIMESTAMP('2008-05-11 14:04:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50026
;

-- May 11, 2008 2:06:59 PM CDT
-- Fixed workflow to Libero Document
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50071,50064,50070,TO_TIMESTAMP('2008-05-11 14:06:54','YYYY-MM-DD HH24:MI:SS'),0,'EE02','Y','Y',10,TO_TIMESTAMP('2008-05-11 14:06:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 11, 2008 2:08:54 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:08:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50073
;

-- May 11, 2008 2:09:20 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:09:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50072
;

-- May 11, 2008 2:09:49 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:09:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50071
;

-- May 11, 2008 2:10:04 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Transition)',Updated=TO_TIMESTAMP('2008-05-11 14:10:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50058
;

-- May 11, 2008 2:10:28 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:10:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50070
;

-- May 11, 2008 2:10:43 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Approval)',Updated=TO_TIMESTAMP('2008-05-11 14:10:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50064
;

-- May 11, 2008 2:10:54 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Transition)',Updated=TO_TIMESTAMP('2008-05-11 14:10:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50057
;

-- May 11, 2008 2:11:40 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET XPosition=1, YPosition=0,Updated=TO_TIMESTAMP('2008-05-11 14:11:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50070
;

-- May 11, 2008 2:11:40 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET XPosition=226, YPosition=0,Updated=TO_TIMESTAMP('2008-05-11 14:11:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50071
;

-- May 11, 2008 2:11:40 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET XPosition=223, YPosition=103,Updated=TO_TIMESTAMP('2008-05-11 14:11:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50072
;

-- May 11, 2008 2:11:41 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET XPosition=0, YPosition=99,Updated=TO_TIMESTAMP('2008-05-11 14:11:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50073
;

-- May 11, 2008 2:13:06 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_Workflow SET AD_WF_Node_ID=50041, Author='e-Evolution', IsValid='Y',Updated=TO_TIMESTAMP('2008-05-11 14:13:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=50011
;

-- May 11, 2008 2:13:21 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:13:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50043
;

-- May 11, 2008 2:13:40 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:13:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50044
;

-- May 11, 2008 2:13:57 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:13:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50042
;

-- May 11, 2008 2:14:14 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Transition)',Updated=TO_TIMESTAMP('2008-05-11 14:14:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50036
;

-- May 11, 2008 2:14:47 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:14:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50041
;

-- May 11, 2008 2:15:10 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Approval)', IsStdUserWorkflow='Y',Updated=TO_TIMESTAMP('2008-05-11 14:15:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50035
;

-- May 11, 2008 2:15:46 PM CDT
-- Fixed workflow to Libero Document
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,Description,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50043,50065,50041,TO_TIMESTAMP('2008-05-11 14:15:45','YYYY-MM-DD HH24:MI:SS'),0,'(Standard Transition)','EE01','Y','N',100,TO_TIMESTAMP('2008-05-11 14:15:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 11, 2008 2:17:16 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_Workflow SET Description='(Standard Process Manufacturing Cost Collector)', Duration=1, IsValid='Y',Updated=TO_TIMESTAMP('2008-05-11 14:17:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=50009
;

-- May 11, 2008 2:17:16 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_Workflow_Trl SET IsTranslated='N' WHERE AD_Workflow_ID=50009
;

-- May 11, 2008 2:17:30 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:17:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50030
;

-- May 11, 2008 2:17:39 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:17:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50031
;

-- May 11, 2008 2:17:48 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:17:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50032
;

-- May 11, 2008 2:18:04 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Transition)',Updated=TO_TIMESTAMP('2008-05-11 14:18:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50012
;

-- May 11, 2008 2:18:09 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET SeqNo=100,Updated=TO_TIMESTAMP('2008-05-11 14:18:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50012
;

-- May 11, 2008 2:18:26 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_Node SET IsCentrallyMaintained='Y',Updated=TO_TIMESTAMP('2008-05-11 14:18:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50033
;

-- May 11, 2008 2:18:40 PM CDT
-- Fixed workflow to Libero Document
UPDATE AD_WF_NodeNext SET Description='(Standard Approval)',Updated=TO_TIMESTAMP('2008-05-11 14:18:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50013
;

-- May 11, 2008 2:19:11 PM CDT
-- Fixed workflow to Libero Document
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,Description,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50030,50066,50033,TO_TIMESTAMP('2008-05-11 14:19:10','YYYY-MM-DD HH24:MI:SS'),0,'(Standard Transition)','EE01','Y','N',100,TO_TIMESTAMP('2008-05-11 14:19:10','YYYY-MM-DD HH24:MI:SS'),0)
;

