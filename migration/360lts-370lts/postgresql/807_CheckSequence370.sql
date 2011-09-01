-- Aug 25, 2011 9:20:24 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET Name = (SELECT TableName FROM AD_Table t WHERE t.IsView='N' AND UPPER(AD_Sequence.Name)=UPPER(t.TableName)) WHERE AD_Sequence.IsTableID='Y' AND EXISTS (SELECT * FROM AD_Table t WHERE t.IsActive='Y' AND t.IsView='N' AND UPPER(AD_Sequence.Name)=UPPER(t.TableName) AND AD_Sequence.Name<>t.TableName)
;

-- Aug 25, 2011 9:20:25 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=62045,Updated=TO_TIMESTAMP('2011-08-25 09:20:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=3
;

-- Aug 25, 2011 9:20:25 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=54390,Updated=TO_TIMESTAMP('2011-08-25 09:20:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=188
;

-- Aug 25, 2011 9:20:25 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=60993,Updated=TO_TIMESTAMP('2011-08-25 09:20:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=4
;

-- Aug 25, 2011 9:20:25 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=50003,Updated=TO_TIMESTAMP('2011-08-25 09:20:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=318
;

-- Aug 25, 2011 9:20:25 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=50086,Updated=TO_TIMESTAMP('2011-08-25 09:20:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=319
;

-- Aug 25, 2011 9:20:25 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=53304,Updated=TO_TIMESTAMP('2011-08-25 09:20:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=7
;

-- Aug 25, 2011 9:20:26 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=53232,Updated=TO_TIMESTAMP('2011-08-25 09:20:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=199
;

-- Aug 25, 2011 9:20:26 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=53469,Updated=TO_TIMESTAMP('2011-08-25 09:20:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=200
;

-- Aug 25, 2011 9:20:26 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=53409,Updated=TO_TIMESTAMP('2011-08-25 09:20:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=16
;

-- Aug 25, 2011 9:20:26 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=50051,Updated=TO_TIMESTAMP('2011-08-25 09:20:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=50009
;

-- Aug 25, 2011 9:20:26 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=53373,Updated=TO_TIMESTAMP('2011-08-25 09:20:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=19
;

-- Aug 25, 2011 9:20:26 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=53296,Updated=TO_TIMESTAMP('2011-08-25 09:20:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=21
;

-- Aug 25, 2011 9:20:26 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=52098,Updated=TO_TIMESTAMP('2011-08-25 09:20:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=26
;

-- Aug 25, 2011 9:20:26 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=53130,Updated=TO_TIMESTAMP('2011-08-25 09:20:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=27
;

-- Aug 25, 2011 9:20:29 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=50164,Updated=TO_TIMESTAMP('2011-08-25 09:20:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=997
;

-- Aug 25, 2011 9:20:29 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=50004,Updated=TO_TIMESTAMP('2011-08-25 09:20:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=996
;

-- Aug 25, 2011 9:20:29 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=50060,Updated=TO_TIMESTAMP('2011-08-25 09:20:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=43
;

-- Aug 25, 2011 9:20:29 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=51584,Updated=TO_TIMESTAMP('2011-08-25 09:20:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=136
;

-- Aug 25, 2011 9:20:30 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=50104,Updated=TO_TIMESTAMP('2011-08-25 09:20:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=62
;

-- Aug 25, 2011 9:20:30 AM CDT
-- Check Sequences
UPDATE AD_Sequence SET CurrentNextSys=50005,Updated=TO_TIMESTAMP('2011-08-25 09:20:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=65
;

-- Aug 25, 2011 9:20:37 AM CDT
-- Check Sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53451,TO_TIMESTAMP('2011-08-25 09:20:35','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table I_Movement',1,'Y','N','Y','N','DocumentNo_I_Movement','N',1000000,TO_TIMESTAMP('2011-08-25 09:20:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 25, 2011 9:20:38 AM CDT
-- Check Sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53452,TO_TIMESTAMP('2011-08-25 09:20:37','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table I_Product_BOM',1,'Y','N','Y','N','DocumentNo_I_Product_BOM','N',1000000,TO_TIMESTAMP('2011-08-25 09:20:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 25, 2011 9:20:38 AM CDT
-- Check Sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53453,TO_TIMESTAMP('2011-08-25 09:20:38','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table I_Movement',1,'Y','N','Y','N','DocumentNo_I_Movement','N',1000000,TO_TIMESTAMP('2011-08-25 09:20:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 25, 2011 9:20:39 AM CDT
-- Check Sequences
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53454,TO_TIMESTAMP('2011-08-25 09:20:38','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table I_Product_BOM',1,'Y','N','Y','N','DocumentNo_I_Product_BOM','N',1000000,TO_TIMESTAMP('2011-08-25 09:20:38','YYYY-MM-DD HH24:MI:SS'),0)
;

