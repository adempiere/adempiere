-- Dec 20, 2008 7:32:30 PM COT
-- Check sequence 342
UPDATE AD_Sequence s SET Name = (SELECT TableName FROM AD_Table t WHERE t.IsView='N' AND UPPER(s.Name)=UPPER(t.TableName)) WHERE s.IsTableID='Y' AND EXISTS (SELECT * FROM AD_Table t WHERE t.IsActive='Y' AND t.IsView='N' AND UPPER(s.Name)=UPPER(t.TableName) AND s.Name<>t.TableName)
;

-- Dec 20, 2008 7:32:58 PM COT
-- Check sequence 342
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53187,TO_DATE('2008-12-20 19:32:36','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table ASP_Module',1,'Y','N','Y','N','DocumentNo_ASP_Module','N',1000000,TO_DATE('2008-12-20 19:32:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 20, 2008 7:33:25 PM COT
-- Check sequence 342
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53188,TO_DATE('2008-12-20 19:32:58','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table ASP_Level',1,'Y','N','Y','N','DocumentNo_ASP_Level','N',1000000,TO_DATE('2008-12-20 19:32:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 20, 2008 7:33:45 PM COT
-- Check sequence 342
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53189,TO_DATE('2008-12-20 19:33:25','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table AD_Rule',1,'Y','N','Y','N','DocumentNo_AD_Rule','N',1000000,TO_DATE('2008-12-20 19:33:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 20, 2008 7:34:04 PM COT
-- Check sequence 342
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53190,TO_DATE('2008-12-20 19:33:45','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table ASP_Module',1,'Y','N','Y','N','DocumentNo_ASP_Module','N',1000000,TO_DATE('2008-12-20 19:33:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 20, 2008 7:34:19 PM COT
-- Check sequence 342
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53191,TO_DATE('2008-12-20 19:34:04','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table ASP_Level',1,'Y','N','Y','N','DocumentNo_ASP_Level','N',1000000,TO_DATE('2008-12-20 19:34:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 20, 2008 7:50:24 PM COT
-- Check sequence 342
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53193,TO_DATE('2008-12-20 19:50:23','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table AD_Rule',1,'Y','N','Y','N','DocumentNo_AD_Rule','N',1000000,TO_DATE('2008-12-20 19:50:23','YYYY-MM-DD HH24:MI:SS'),100)
;

