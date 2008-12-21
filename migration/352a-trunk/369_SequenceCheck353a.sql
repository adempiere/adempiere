-- Dec 21, 2008 3:13:38 AM COT
-- Sequence Check for 353a
UPDATE AD_Sequence s SET Name = (SELECT TableName FROM AD_Table t WHERE t.IsView='N' AND UPPER(s.Name)=UPPER(t.TableName)) WHERE s.IsTableID='Y' AND EXISTS (SELECT * FROM AD_Table t WHERE t.IsActive='Y' AND t.IsView='N' AND UPPER(s.Name)=UPPER(t.TableName) AND s.Name<>t.TableName)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53194,TO_DATE('2008-12-21 03:13:53','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_TaxGroup',1,'Y','N','Y','N','DocumentNo_C_TaxGroup','N',1000000,TO_DATE('2008-12-21 03:13:53','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53195,TO_DATE('2008-12-21 03:13:56','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Contract',1,'Y','N','Y','N','DocumentNo_HR_Contract','N',1000000,TO_DATE('2008-12-21 03:13:56','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53196,TO_DATE('2008-12-21 03:13:59','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Process',1,'Y','N','Y','N','DocumentNo_HR_Process','N',1000000,TO_DATE('2008-12-21 03:13:59','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53197,TO_DATE('2008-12-21 03:14:00','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Payroll',1,'Y','N','Y','N','DocumentNo_HR_Payroll','N',1000000,TO_DATE('2008-12-21 03:14:00','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53198,TO_DATE('2008-12-21 03:14:05','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Concept_Category',1,'Y','N','Y','N','DocumentNo_HR_Concept_Category','N',1000000,TO_DATE('2008-12-21 03:14:05','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53199,TO_DATE('2008-12-21 03:14:08','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_ListType',1,'Y','N','Y','N','DocumentNo_HR_ListType','N',1000000,TO_DATE('2008-12-21 03:14:08','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53200,TO_DATE('2008-12-21 03:14:09','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_List',1,'Y','N','Y','N','DocumentNo_HR_List','N',1000000,TO_DATE('2008-12-21 03:14:09','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53201,TO_DATE('2008-12-21 03:14:12','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table DD_NetworkDistribution',1,'Y','N','Y','N','DocumentNo_DD_NetworkDistribution','N',1000000,TO_DATE('2008-12-21 03:14:12','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53202,TO_DATE('2008-12-21 03:14:18','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table A_Asset_Reval_Entry',1,'Y','N','Y','N','DocumentNo_A_Asset_Reval_Entry','N',1000000,TO_DATE('2008-12-21 03:14:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53203,TO_DATE('2008-12-21 03:14:18','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table A_Depreciation_Entry',1,'Y','N','Y','N','DocumentNo_A_Depreciation_Entry','N',1000000,TO_DATE('2008-12-21 03:14:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53204,TO_DATE('2008-12-21 03:14:20','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table A_Asset_Addition',1,'Y','N','Y','N','DocumentNo_A_Asset_Addition','N',1000000,TO_DATE('2008-12-21 03:14:20','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53205,TO_DATE('2008-12-21 03:14:21','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table I_Asset',1,'Y','N','Y','N','DocumentNo_I_Asset','N',1000000,TO_DATE('2008-12-21 03:14:21','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53206,TO_DATE('2008-12-21 03:14:22','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_Processor',1,'Y','N','Y','N','DocumentNo_EXP_Processor','N',1000000,TO_DATE('2008-12-21 03:14:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53207,TO_DATE('2008-12-21 03:14:28','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table AD_HouseKeeping',1,'Y','N','Y','N','DocumentNo_AD_HouseKeeping','N',1000000,TO_DATE('2008-12-21 03:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53208,TO_DATE('2008-12-21 03:14:30','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Department',1,'Y','N','Y','N','DocumentNo_HR_Department','N',1000000,TO_DATE('2008-12-21 03:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53209,TO_DATE('2008-12-21 03:14:31','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Job',1,'Y','N','Y','N','DocumentNo_HR_Job','N',1000000,TO_DATE('2008-12-21 03:14:31','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53210,TO_DATE('2008-12-21 03:14:32','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_Format',1,'Y','N','Y','N','DocumentNo_EXP_Format','N',1000000,TO_DATE('2008-12-21 03:14:32','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53211,TO_DATE('2008-12-21 03:14:34','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_TaxDefinition',1,'Y','N','Y','N','DocumentNo_C_TaxDefinition','N',1000000,TO_DATE('2008-12-21 03:14:34','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53212,TO_DATE('2008-12-21 03:14:35','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_TaxType',1,'Y','N','Y','N','DocumentNo_C_TaxType','N',1000000,TO_DATE('2008-12-21 03:14:35','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53213,TO_DATE('2008-12-21 03:14:38','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_TaxBase',1,'Y','N','Y','N','DocumentNo_C_TaxBase','N',1000000,TO_DATE('2008-12-21 03:14:38','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53214,TO_DATE('2008-12-21 03:14:44','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_ProcessorParameter',1,'Y','N','Y','N','DocumentNo_EXP_ProcessorParameter','N',1000000,TO_DATE('2008-12-21 03:14:44','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53215,TO_DATE('2008-12-21 03:14:47','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_Processor_Type',1,'Y','N','Y','N','DocumentNo_EXP_Processor_Type','N',1000000,TO_DATE('2008-12-21 03:14:47','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53216,TO_DATE('2008-12-21 03:14:50','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table IMP_ProcessorParameter',1,'Y','N','Y','N','DocumentNo_IMP_ProcessorParameter','N',1000000,TO_DATE('2008-12-21 03:14:50','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53217,TO_DATE('2008-12-21 03:14:52','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table IMP_Processor_Type',1,'Y','N','Y','N','DocumentNo_IMP_Processor_Type','N',1000000,TO_DATE('2008-12-21 03:14:52','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53218,TO_DATE('2008-12-21 03:15:01','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Concept',1,'Y','N','Y','N','DocumentNo_HR_Concept','N',1000000,TO_DATE('2008-12-21 03:15:01','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53219,TO_DATE('2008-12-21 03:15:02','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_FormatLine',1,'Y','N','Y','N','DocumentNo_EXP_FormatLine','N',1000000,TO_DATE('2008-12-21 03:15:02','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53220,TO_DATE('2008-12-21 03:15:14','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table IMP_Processor',1,'Y','N','Y','N','DocumentNo_IMP_Processor','N',1000000,TO_DATE('2008-12-21 03:15:14','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53221,TO_DATE('2008-12-21 03:15:14','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_TaxGroup',1,'Y','N','Y','N','DocumentNo_C_TaxGroup','N',1000000,TO_DATE('2008-12-21 03:15:14','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53222,TO_DATE('2008-12-21 03:15:17','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Contract',1,'Y','N','Y','N','DocumentNo_HR_Contract','N',1000000,TO_DATE('2008-12-21 03:15:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53223,TO_DATE('2008-12-21 03:15:18','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Process',1,'Y','N','Y','N','DocumentNo_HR_Process','N',1000000,TO_DATE('2008-12-21 03:15:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53224,TO_DATE('2008-12-21 03:15:20','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Payroll',1,'Y','N','Y','N','DocumentNo_HR_Payroll','N',1000000,TO_DATE('2008-12-21 03:15:20','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53225,TO_DATE('2008-12-21 03:15:23','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Concept_Category',1,'Y','N','Y','N','DocumentNo_HR_Concept_Category','N',1000000,TO_DATE('2008-12-21 03:15:23','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53226,TO_DATE('2008-12-21 03:15:25','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_ListType',1,'Y','N','Y','N','DocumentNo_HR_ListType','N',1000000,TO_DATE('2008-12-21 03:15:25','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53227,TO_DATE('2008-12-21 03:15:26','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_List',1,'Y','N','Y','N','DocumentNo_HR_List','N',1000000,TO_DATE('2008-12-21 03:15:26','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53228,TO_DATE('2008-12-21 03:15:29','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table DD_NetworkDistribution',1,'Y','N','Y','N','DocumentNo_DD_NetworkDistribution','N',1000000,TO_DATE('2008-12-21 03:15:29','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53229,TO_DATE('2008-12-21 03:15:33','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table A_Asset_Reval_Entry',1,'Y','N','Y','N','DocumentNo_A_Asset_Reval_Entry','N',1000000,TO_DATE('2008-12-21 03:15:33','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53230,TO_DATE('2008-12-21 03:15:35','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table A_Depreciation_Entry',1,'Y','N','Y','N','DocumentNo_A_Depreciation_Entry','N',1000000,TO_DATE('2008-12-21 03:15:35','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53231,TO_DATE('2008-12-21 03:15:37','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table A_Asset_Addition',1,'Y','N','Y','N','DocumentNo_A_Asset_Addition','N',1000000,TO_DATE('2008-12-21 03:15:37','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53232,TO_DATE('2008-12-21 03:15:40','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table I_Asset',1,'Y','N','Y','N','DocumentNo_I_Asset','N',1000000,TO_DATE('2008-12-21 03:15:40','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53233,TO_DATE('2008-12-21 03:15:42','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_Processor',1,'Y','N','Y','N','DocumentNo_EXP_Processor','N',1000000,TO_DATE('2008-12-21 03:15:42','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53234,TO_DATE('2008-12-21 03:15:43','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table AD_HouseKeeping',1,'Y','N','Y','N','DocumentNo_AD_HouseKeeping','N',1000000,TO_DATE('2008-12-21 03:15:43','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53235,TO_DATE('2008-12-21 03:15:45','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Department',1,'Y','N','Y','N','DocumentNo_HR_Department','N',1000000,TO_DATE('2008-12-21 03:15:45','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53236,TO_DATE('2008-12-21 03:15:45','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Job',1,'Y','N','Y','N','DocumentNo_HR_Job','N',1000000,TO_DATE('2008-12-21 03:15:45','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53237,TO_DATE('2008-12-21 03:15:46','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_Format',1,'Y','N','Y','N','DocumentNo_EXP_Format','N',1000000,TO_DATE('2008-12-21 03:15:46','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53238,TO_DATE('2008-12-21 03:15:50','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_TaxDefinition',1,'Y','N','Y','N','DocumentNo_C_TaxDefinition','N',1000000,TO_DATE('2008-12-21 03:15:50','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53239,TO_DATE('2008-12-21 03:15:51','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_TaxType',1,'Y','N','Y','N','DocumentNo_C_TaxType','N',1000000,TO_DATE('2008-12-21 03:15:51','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53240,TO_DATE('2008-12-21 03:15:51','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table C_TaxBase',1,'Y','N','Y','N','DocumentNo_C_TaxBase','N',1000000,TO_DATE('2008-12-21 03:15:51','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53241,TO_DATE('2008-12-21 03:15:52','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_ProcessorParameter',1,'Y','N','Y','N','DocumentNo_EXP_ProcessorParameter','N',1000000,TO_DATE('2008-12-21 03:15:52','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53242,TO_DATE('2008-12-21 03:15:53','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_Processor_Type',1,'Y','N','Y','N','DocumentNo_EXP_Processor_Type','N',1000000,TO_DATE('2008-12-21 03:15:53','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53243,TO_DATE('2008-12-21 03:15:55','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table IMP_ProcessorParameter',1,'Y','N','Y','N','DocumentNo_IMP_ProcessorParameter','N',1000000,TO_DATE('2008-12-21 03:15:55','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53244,TO_DATE('2008-12-21 03:15:59','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table IMP_Processor_Type',1,'Y','N','Y','N','DocumentNo_IMP_Processor_Type','N',1000000,TO_DATE('2008-12-21 03:15:59','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53245,TO_DATE('2008-12-21 03:16:01','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table HR_Concept',1,'Y','N','Y','N','DocumentNo_HR_Concept','N',1000000,TO_DATE('2008-12-21 03:16:01','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53246,TO_DATE('2008-12-21 03:16:01','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table EXP_FormatLine',1,'Y','N','Y','N','DocumentNo_EXP_FormatLine','N',1000000,TO_DATE('2008-12-21 03:16:01','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53247,TO_DATE('2008-12-21 03:16:02','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'DocumentNo/Value for Table IMP_Processor',1,'Y','N','Y','N','DocumentNo_IMP_Processor','N',1000000,TO_DATE('2008-12-21 03:16:02','YYYY-MM-DD HH24:MI:SS'),100)
;

