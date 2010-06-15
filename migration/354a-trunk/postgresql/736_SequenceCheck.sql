-- Jun 14, 2010 4:19:35 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET Name = (SELECT TableName FROM AD_Table t WHERE t.IsView='N' AND UPPER(AD_Sequence.Name)=UPPER(t.TableName)) WHERE AD_Sequence.IsTableID='Y' AND EXISTS (SELECT * FROM AD_Table t WHERE t.IsActive='Y' AND t.IsView='N' AND UPPER(AD_Sequence.Name)=UPPER(t.TableName) AND AD_Sequence.Name<>t.TableName)
;

-- Jun 14, 2010 4:19:35 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=59231,Updated=TO_TIMESTAMP('2010-06-14 16:19:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=3
;

-- Jun 14, 2010 4:19:35 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=54159,Updated=TO_TIMESTAMP('2010-06-14 16:19:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=188
;

-- Jun 14, 2010 4:19:35 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=58883,Updated=TO_TIMESTAMP('2010-06-14 16:19:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=4
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50017,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=6
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53265,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=7
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=523854,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=9
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53201,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=199
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53408,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=200
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53355,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=14
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53584,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=11
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50006,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=53355
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53032,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=298
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53378,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=16
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50044,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=50009
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53308,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=19
;

-- Jun 14, 2010 4:19:36 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53261,Updated=TO_TIMESTAMP('2010-06-14 16:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=21
;

-- Jun 14, 2010 4:19:37 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=52085,Updated=TO_TIMESTAMP('2010-06-14 16:19:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=26
;

-- Jun 14, 2010 4:19:37 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50099,Updated=TO_TIMESTAMP('2010-06-14 16:19:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=32
;

-- Jun 14, 2010 4:19:37 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50076,Updated=TO_TIMESTAMP('2010-06-14 16:19:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=33
;

-- Jun 14, 2010 4:19:37 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=53110,Updated=TO_TIMESTAMP('2010-06-14 16:19:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=27
;

-- Jun 14, 2010 4:19:37 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50006,Updated=TO_TIMESTAMP('2010-06-14 16:19:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=57
;

-- Jun 14, 2010 4:19:37 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50018,Updated=TO_TIMESTAMP('2010-06-14 16:19:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=59
;

-- Jun 14, 2010 4:19:38 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50048,Updated=TO_TIMESTAMP('2010-06-14 16:19:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=43
;

-- Jun 14, 2010 4:19:38 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=51236,Updated=TO_TIMESTAMP('2010-06-14 16:19:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=136
;

-- Jun 14, 2010 4:19:38 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50018,Updated=TO_TIMESTAMP('2010-06-14 16:19:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=62
;

-- Jun 14, 2010 4:19:38 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50014,Updated=TO_TIMESTAMP('2010-06-14 16:19:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=191
;

-- Jun 14, 2010 4:19:39 PM COT
-- Preparing release 3.6.0LTS
UPDATE AD_Sequence SET CurrentNextSys=50004,Updated=TO_TIMESTAMP('2010-06-14 16:19:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Sequence_ID=65
;

-- Jun 14, 2010 4:19:42 PM COT
-- Preparing release 3.6.0LTS
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53379,TO_TIMESTAMP('2010-06-14 16:19:41','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_OrderSource',1,'Y','N','Y','N','DocumentNo_C_OrderSource','N',1000000,TO_TIMESTAMP('2010-06-14 16:19:41','YYYY-MM-DD HH24:MI:SS'),100)
;

