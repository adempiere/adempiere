-- May 18, 2009 12:51:57 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53298,TO_DATE('2009-05-18 12:51:53','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table AD_SearchDefinition',1,'Y','N','Y','Y','AD_SearchDefinition','N',1000000,TO_DATE('2009-05-18 12:51:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:51:58 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53299,TO_DATE('2009-05-18 12:51:57','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table C_Charge_Trl',1,'Y','N','Y','Y','C_Charge_Trl','N',1000000,TO_DATE('2009-05-18 12:51:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:51:58 PM COT
-- Add missing sequences
UPDATE AD_Sequence s SET Name = (SELECT TableName FROM AD_Table t WHERE t.IsView='N' AND UPPER(s.Name)=UPPER(t.TableName)) WHERE s.IsTableID='Y' AND EXISTS (SELECT * FROM AD_Table t WHERE t.IsActive='Y' AND t.IsView='N' AND UPPER(s.Name)=UPPER(t.TableName) AND s.Name<>t.TableName)
;

-- May 18, 2009 12:52:01 PM COT
-- Add missing sequences
UPDATE AD_Sequence SET CurrentNextSys=50002,Updated=TO_DATE('2009-05-18 12:52:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=53298
;

-- May 18, 2009 12:52:06 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53300,TO_DATE('2009-05-18 12:52:05','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table AD_ReplicationStrategy',1,'Y','N','Y','N','DocumentNo_AD_ReplicationStrategy','N',1000000,TO_DATE('2009-05-18 12:52:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:52:07 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53301,TO_DATE('2009-05-18 12:52:06','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table PP_Cost_Collector',1,'Y','N','Y','N','DocumentNo_PP_Cost_Collector','N',1000000,TO_DATE('2009-05-18 12:52:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:52:07 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53302,TO_DATE('2009-05-18 12:52:07','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table U_POSTerminal',1,'Y','N','Y','N','DocumentNo_U_POSTerminal','N',1000000,TO_DATE('2009-05-18 12:52:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:52:08 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53303,TO_DATE('2009-05-18 12:52:07','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_ChargeType',1,'Y','N','Y','N','DocumentNo_C_ChargeType','N',1000000,TO_DATE('2009-05-18 12:52:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:52:08 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53304,TO_DATE('2009-05-18 12:52:08','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table AD_ReplicationStrategy',1,'Y','N','Y','N','DocumentNo_AD_ReplicationStrategy','N',1000000,TO_DATE('2009-05-18 12:52:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:52:09 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53305,TO_DATE('2009-05-18 12:52:08','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table PP_Cost_Collector',1,'Y','N','Y','N','DocumentNo_PP_Cost_Collector','N',1000000,TO_DATE('2009-05-18 12:52:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:52:10 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53306,TO_DATE('2009-05-18 12:52:09','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table U_POSTerminal',1,'Y','N','Y','N','DocumentNo_U_POSTerminal','N',1000000,TO_DATE('2009-05-18 12:52:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 18, 2009 12:52:11 PM COT
-- Add missing sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53307,TO_DATE('2009-05-18 12:52:10','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_ChargeType',1,'Y','N','Y','N','DocumentNo_C_ChargeType','N',1000000,TO_DATE('2009-05-18 12:52:10','YYYY-MM-DD HH24:MI:SS'),100)
;

