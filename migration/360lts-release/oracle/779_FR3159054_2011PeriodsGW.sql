-- Jan 15, 2011 7:06:56 PM COT
-- Generate 2011 accounting periods for GardenWorld
INSERT INTO C_Year (AD_Client_ID,AD_Org_ID,C_Calendar_ID,Created,CreatedBy,C_Year_ID,FiscalYear,IsActive,Processing,Updated,UpdatedBy) VALUES (11,0,102,TO_DATE('2011-01-15 19:06:32','YYYY-MM-DD HH24:MI:SS'),100,50004,'2011','Y','N',TO_DATE('2011-01-15 19:06:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:07:26 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50048,TO_DATE('2011-01-15 19:07:15','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-01-31','YYYY-MM-DD'),'Y','Jan-11',1,'S','N',TO_DATE('2011-01-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:07:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:07:39 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51236,50048,TO_DATE('2011-01-15 19:07:26','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:07:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:07:51 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51237,50048,TO_DATE('2011-01-15 19:07:39','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:07:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:07:58 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51238,50048,TO_DATE('2011-01-15 19:07:51','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:07:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:08:09 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51239,50048,TO_DATE('2011-01-15 19:07:58','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:07:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:08:22 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51240,50048,TO_DATE('2011-01-15 19:08:09','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:08:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:08:29 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51241,50048,TO_DATE('2011-01-15 19:08:22','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:08:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:08:46 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51242,50048,TO_DATE('2011-01-15 19:08:29','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:08:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:09:04 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51243,50048,TO_DATE('2011-01-15 19:08:46','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:08:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:09:17 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51244,50048,TO_DATE('2011-01-15 19:09:04','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:09:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:09:32 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51245,50048,TO_DATE('2011-01-15 19:09:17','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:09:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:09:42 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51246,50048,TO_DATE('2011-01-15 19:09:32','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:09:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:10:13 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51247,50048,TO_DATE('2011-01-15 19:09:42','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:09:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:10:25 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51248,50048,TO_DATE('2011-01-15 19:10:13','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:10:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:10:45 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51249,50048,TO_DATE('2011-01-15 19:10:25','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:10:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:11:14 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51250,50048,TO_DATE('2011-01-15 19:10:45','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:10:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:11:27 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51251,50048,TO_DATE('2011-01-15 19:11:14','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:11:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:11:44 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51252,50048,TO_DATE('2011-01-15 19:11:27','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:11:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:12:01 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51253,50048,TO_DATE('2011-01-15 19:11:44','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:11:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:12:13 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51254,50048,TO_DATE('2011-01-15 19:12:01','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:12:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:12:33 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51255,50048,TO_DATE('2011-01-15 19:12:13','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:12:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:12:49 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51256,50048,TO_DATE('2011-01-15 19:12:33','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:12:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:13:08 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51257,50048,TO_DATE('2011-01-15 19:12:49','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:12:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:13:18 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51258,50048,TO_DATE('2011-01-15 19:13:08','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:13:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:13:26 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51259,50048,TO_DATE('2011-01-15 19:13:18','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:13:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:13:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51260,50048,TO_DATE('2011-01-15 19:13:26','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:13:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:14:17 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51261,50048,TO_DATE('2011-01-15 19:13:50','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:13:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:14:43 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51262,50048,TO_DATE('2011-01-15 19:14:17','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:14:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:04 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51263,50048,TO_DATE('2011-01-15 19:14:43','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:14:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:11 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51264,50048,TO_DATE('2011-01-15 19:15:04','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:15:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:12 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50049,TO_DATE('2011-01-15 19:15:11','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-02-28','YYYY-MM-DD'),'Y','Feb-11',2,'S','N',TO_DATE('2011-02-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:15:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:14 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51265,50049,TO_DATE('2011-01-15 19:15:12','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:15:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:18 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51266,50049,TO_DATE('2011-01-15 19:15:14','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:15:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:20 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51267,50049,TO_DATE('2011-01-15 19:15:18','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:15:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:22 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51268,50049,TO_DATE('2011-01-15 19:15:20','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:15:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:43 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51269,50049,TO_DATE('2011-01-15 19:15:22','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:15:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:44 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51270,50049,TO_DATE('2011-01-15 19:15:43','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:15:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:53 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51271,50049,TO_DATE('2011-01-15 19:15:44','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:15:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:54 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51272,50049,TO_DATE('2011-01-15 19:15:53','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:15:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:15:54 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51273,50049,TO_DATE('2011-01-15 19:15:54','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:15:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:05 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51274,50049,TO_DATE('2011-01-15 19:15:54','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:15:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:05 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51275,50049,TO_DATE('2011-01-15 19:16:05','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:16:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:07 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51276,50049,TO_DATE('2011-01-15 19:16:05','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:16:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:09 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51277,50049,TO_DATE('2011-01-15 19:16:07','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:16:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:09 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51278,50049,TO_DATE('2011-01-15 19:16:09','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:16:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:10 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51279,50049,TO_DATE('2011-01-15 19:16:09','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:16:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:12 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51280,50049,TO_DATE('2011-01-15 19:16:10','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:16:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:13 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51281,50049,TO_DATE('2011-01-15 19:16:12','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:16:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:13 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51282,50049,TO_DATE('2011-01-15 19:16:13','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:16:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:15 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51283,50049,TO_DATE('2011-01-15 19:16:13','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:16:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:16 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51284,50049,TO_DATE('2011-01-15 19:16:15','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:16:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:26 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51285,50049,TO_DATE('2011-01-15 19:16:16','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:16:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:27 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51286,50049,TO_DATE('2011-01-15 19:16:26','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:16:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:29 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51287,50049,TO_DATE('2011-01-15 19:16:27','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:16:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:30 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51288,50049,TO_DATE('2011-01-15 19:16:29','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:16:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:31 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51289,50049,TO_DATE('2011-01-15 19:16:30','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:16:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:33 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51290,50049,TO_DATE('2011-01-15 19:16:31','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:16:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51291,50049,TO_DATE('2011-01-15 19:16:33','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:16:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:39 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51292,50049,TO_DATE('2011-01-15 19:16:36','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:16:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:40 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51293,50049,TO_DATE('2011-01-15 19:16:39','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:16:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:46 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50050,TO_DATE('2011-01-15 19:16:40','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-03-31','YYYY-MM-DD'),'Y','Mar-11',3,'S','N',TO_DATE('2011-03-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:16:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51294,50050,TO_DATE('2011-01-15 19:16:46','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:16:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:52 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51295,50050,TO_DATE('2011-01-15 19:16:50','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:16:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:54 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51296,50050,TO_DATE('2011-01-15 19:16:52','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:16:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:16:55 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51297,50050,TO_DATE('2011-01-15 19:16:54','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:16:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:06 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51298,50050,TO_DATE('2011-01-15 19:16:55','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:16:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:08 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51299,50050,TO_DATE('2011-01-15 19:17:06','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:17:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:09 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51300,50050,TO_DATE('2011-01-15 19:17:08','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:17:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:12 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51301,50050,TO_DATE('2011-01-15 19:17:09','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:17:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:14 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51302,50050,TO_DATE('2011-01-15 19:17:12','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:17:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:16 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51303,50050,TO_DATE('2011-01-15 19:17:14','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:17:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:17 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51304,50050,TO_DATE('2011-01-15 19:17:16','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:17:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:19 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51305,50050,TO_DATE('2011-01-15 19:17:17','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:17:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:20 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51306,50050,TO_DATE('2011-01-15 19:17:19','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:17:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:23 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51307,50050,TO_DATE('2011-01-15 19:17:20','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:17:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:26 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51308,50050,TO_DATE('2011-01-15 19:17:23','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:17:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:27 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51309,50050,TO_DATE('2011-01-15 19:17:26','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:17:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:28 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51310,50050,TO_DATE('2011-01-15 19:17:27','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:17:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:29 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51311,50050,TO_DATE('2011-01-15 19:17:28','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:17:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:31 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51312,50050,TO_DATE('2011-01-15 19:17:29','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:17:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:33 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51313,50050,TO_DATE('2011-01-15 19:17:31','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:17:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:34 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51314,50050,TO_DATE('2011-01-15 19:17:33','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:17:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51315,50050,TO_DATE('2011-01-15 19:17:34','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:17:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51316,50050,TO_DATE('2011-01-15 19:17:36','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:17:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:38 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51317,50050,TO_DATE('2011-01-15 19:17:36','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:17:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:38 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51318,50050,TO_DATE('2011-01-15 19:17:38','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:17:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:40 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51319,50050,TO_DATE('2011-01-15 19:17:38','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:17:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:41 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51320,50050,TO_DATE('2011-01-15 19:17:40','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:17:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:43 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51321,50050,TO_DATE('2011-01-15 19:17:41','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:17:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:44 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51322,50050,TO_DATE('2011-01-15 19:17:43','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:17:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:45 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50051,TO_DATE('2011-01-15 19:17:44','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-04-30','YYYY-MM-DD'),'Y','Apr-11',4,'S','N',TO_DATE('2011-04-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:17:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:46 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51323,50051,TO_DATE('2011-01-15 19:17:45','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:17:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:48 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51324,50051,TO_DATE('2011-01-15 19:17:46','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:17:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:49 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51325,50051,TO_DATE('2011-01-15 19:17:48','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:17:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51326,50051,TO_DATE('2011-01-15 19:17:49','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:17:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:52 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51327,50051,TO_DATE('2011-01-15 19:17:50','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:17:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:52 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51328,50051,TO_DATE('2011-01-15 19:17:52','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:17:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:54 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51329,50051,TO_DATE('2011-01-15 19:17:52','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:17:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:56 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51330,50051,TO_DATE('2011-01-15 19:17:54','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:17:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:17:57 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51331,50051,TO_DATE('2011-01-15 19:17:56','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:17:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:01 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51332,50051,TO_DATE('2011-01-15 19:17:57','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:17:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:03 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51333,50051,TO_DATE('2011-01-15 19:18:01','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:18:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:03 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51334,50051,TO_DATE('2011-01-15 19:18:03','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:18:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:04 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51335,50051,TO_DATE('2011-01-15 19:18:03','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:18:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:05 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51336,50051,TO_DATE('2011-01-15 19:18:04','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:18:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:07 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51337,50051,TO_DATE('2011-01-15 19:18:05','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:18:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:09 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51338,50051,TO_DATE('2011-01-15 19:18:07','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:18:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:10 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51339,50051,TO_DATE('2011-01-15 19:18:09','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:18:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:12 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51340,50051,TO_DATE('2011-01-15 19:18:10','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:18:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:19 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51341,50051,TO_DATE('2011-01-15 19:18:12','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:18:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:21 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51342,50051,TO_DATE('2011-01-15 19:18:19','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:18:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:23 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51343,50051,TO_DATE('2011-01-15 19:18:21','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:18:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:26 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51344,50051,TO_DATE('2011-01-15 19:18:23','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:18:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:28 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51345,50051,TO_DATE('2011-01-15 19:18:26','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:18:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:30 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51346,50051,TO_DATE('2011-01-15 19:18:28','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:18:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:32 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51347,50051,TO_DATE('2011-01-15 19:18:30','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:18:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:33 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51348,50051,TO_DATE('2011-01-15 19:18:32','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:18:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:34 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51349,50051,TO_DATE('2011-01-15 19:18:33','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:18:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:35 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51350,50051,TO_DATE('2011-01-15 19:18:34','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:18:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51351,50051,TO_DATE('2011-01-15 19:18:35','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:18:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:37 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50052,TO_DATE('2011-01-15 19:18:36','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-05-31','YYYY-MM-DD'),'Y','May-11',5,'S','N',TO_DATE('2011-05-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:18:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:39 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51352,50052,TO_DATE('2011-01-15 19:18:37','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:18:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:41 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51353,50052,TO_DATE('2011-01-15 19:18:39','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:18:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:43 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51354,50052,TO_DATE('2011-01-15 19:18:41','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:18:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:45 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51355,50052,TO_DATE('2011-01-15 19:18:43','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:18:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:48 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51356,50052,TO_DATE('2011-01-15 19:18:45','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:18:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51357,50052,TO_DATE('2011-01-15 19:18:48','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:18:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:52 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51358,50052,TO_DATE('2011-01-15 19:18:50','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:18:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:53 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51359,50052,TO_DATE('2011-01-15 19:18:52','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:18:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:54 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51360,50052,TO_DATE('2011-01-15 19:18:53','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:18:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:55 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51361,50052,TO_DATE('2011-01-15 19:18:54','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:18:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:56 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51362,50052,TO_DATE('2011-01-15 19:18:55','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:18:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:58 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51363,50052,TO_DATE('2011-01-15 19:18:56','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:18:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:18:59 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51364,50052,TO_DATE('2011-01-15 19:18:58','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:18:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:09 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51365,50052,TO_DATE('2011-01-15 19:18:59','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:18:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:11 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51366,50052,TO_DATE('2011-01-15 19:19:09','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:19:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:12 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51367,50052,TO_DATE('2011-01-15 19:19:11','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:19:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:14 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51368,50052,TO_DATE('2011-01-15 19:19:12','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:19:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:15 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51369,50052,TO_DATE('2011-01-15 19:19:14','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:19:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:17 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51370,50052,TO_DATE('2011-01-15 19:19:15','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:19:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:18 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51371,50052,TO_DATE('2011-01-15 19:19:17','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:19:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:18 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51372,50052,TO_DATE('2011-01-15 19:19:18','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:19:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:21 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51373,50052,TO_DATE('2011-01-15 19:19:18','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:19:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:23 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51374,50052,TO_DATE('2011-01-15 19:19:21','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:19:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:25 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51375,50052,TO_DATE('2011-01-15 19:19:23','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:19:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:26 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51376,50052,TO_DATE('2011-01-15 19:19:25','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:19:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:28 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51377,50052,TO_DATE('2011-01-15 19:19:26','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:19:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:29 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51378,50052,TO_DATE('2011-01-15 19:19:28','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:19:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:31 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51379,50052,TO_DATE('2011-01-15 19:19:29','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:19:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:32 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51380,50052,TO_DATE('2011-01-15 19:19:31','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:19:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:32 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50053,TO_DATE('2011-01-15 19:19:32','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-06-30','YYYY-MM-DD'),'Y','Jun-11',6,'S','N',TO_DATE('2011-06-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:19:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:34 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51381,50053,TO_DATE('2011-01-15 19:19:32','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:19:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51382,50053,TO_DATE('2011-01-15 19:19:34','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:19:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:37 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51383,50053,TO_DATE('2011-01-15 19:19:36','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:19:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:48 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51384,50053,TO_DATE('2011-01-15 19:19:37','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:19:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:49 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51385,50053,TO_DATE('2011-01-15 19:19:48','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:19:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51386,50053,TO_DATE('2011-01-15 19:19:49','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:19:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:57 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51387,50053,TO_DATE('2011-01-15 19:19:50','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:19:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:57 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51388,50053,TO_DATE('2011-01-15 19:19:57','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:19:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:58 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51389,50053,TO_DATE('2011-01-15 19:19:57','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:19:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:19:59 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51390,50053,TO_DATE('2011-01-15 19:19:58','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:19:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:01 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51391,50053,TO_DATE('2011-01-15 19:19:59','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:19:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:07 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51392,50053,TO_DATE('2011-01-15 19:20:01','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:20:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:08 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51393,50053,TO_DATE('2011-01-15 19:20:07','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:20:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:10 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51394,50053,TO_DATE('2011-01-15 19:20:08','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:20:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:10 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51395,50053,TO_DATE('2011-01-15 19:20:10','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:20:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:11 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51396,50053,TO_DATE('2011-01-15 19:20:10','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:20:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:21 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51397,50053,TO_DATE('2011-01-15 19:20:11','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:20:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:22 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51398,50053,TO_DATE('2011-01-15 19:20:21','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:20:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:23 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51399,50053,TO_DATE('2011-01-15 19:20:22','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:20:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:24 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51400,50053,TO_DATE('2011-01-15 19:20:23','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:20:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:25 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51401,50053,TO_DATE('2011-01-15 19:20:24','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:20:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:26 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51402,50053,TO_DATE('2011-01-15 19:20:25','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:20:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:27 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51403,50053,TO_DATE('2011-01-15 19:20:26','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:20:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:29 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51404,50053,TO_DATE('2011-01-15 19:20:27','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:20:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:30 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51405,50053,TO_DATE('2011-01-15 19:20:29','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:20:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:31 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51406,50053,TO_DATE('2011-01-15 19:20:30','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:20:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:33 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51407,50053,TO_DATE('2011-01-15 19:20:31','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:20:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:34 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51408,50053,TO_DATE('2011-01-15 19:20:33','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:20:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:34 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51409,50053,TO_DATE('2011-01-15 19:20:34','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:20:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:36 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50054,TO_DATE('2011-01-15 19:20:34','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-07-31','YYYY-MM-DD'),'Y','Jul-11',7,'S','N',TO_DATE('2011-07-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:20:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51410,50054,TO_DATE('2011-01-15 19:20:36','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:20:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:38 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51411,50054,TO_DATE('2011-01-15 19:20:36','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:20:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:39 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51412,50054,TO_DATE('2011-01-15 19:20:38','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:20:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:40 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51413,50054,TO_DATE('2011-01-15 19:20:39','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:20:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:42 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51414,50054,TO_DATE('2011-01-15 19:20:40','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:20:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:43 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51415,50054,TO_DATE('2011-01-15 19:20:42','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:20:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:45 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51416,50054,TO_DATE('2011-01-15 19:20:43','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:20:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:45 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51417,50054,TO_DATE('2011-01-15 19:20:45','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:20:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:51 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51418,50054,TO_DATE('2011-01-15 19:20:45','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:20:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:51 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51419,50054,TO_DATE('2011-01-15 19:20:51','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:20:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:53 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51420,50054,TO_DATE('2011-01-15 19:20:51','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:20:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:54 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51421,50054,TO_DATE('2011-01-15 19:20:53','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:20:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:55 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51422,50054,TO_DATE('2011-01-15 19:20:54','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:20:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:56 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51423,50054,TO_DATE('2011-01-15 19:20:55','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:20:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:57 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51424,50054,TO_DATE('2011-01-15 19:20:56','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:20:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:20:58 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51425,50054,TO_DATE('2011-01-15 19:20:57','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:20:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:04 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51426,50054,TO_DATE('2011-01-15 19:20:58','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:20:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:05 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51427,50054,TO_DATE('2011-01-15 19:21:04','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:21:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:07 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51428,50054,TO_DATE('2011-01-15 19:21:05','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:21:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:10 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51429,50054,TO_DATE('2011-01-15 19:21:07','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:21:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:12 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51430,50054,TO_DATE('2011-01-15 19:21:10','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:21:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:13 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51431,50054,TO_DATE('2011-01-15 19:21:12','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:21:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:13 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51432,50054,TO_DATE('2011-01-15 19:21:13','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:21:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:15 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51433,50054,TO_DATE('2011-01-15 19:21:13','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:21:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:15 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51434,50054,TO_DATE('2011-01-15 19:21:15','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:21:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:19 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51435,50054,TO_DATE('2011-01-15 19:21:15','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:21:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:20 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51436,50054,TO_DATE('2011-01-15 19:21:19','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:21:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:20 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51437,50054,TO_DATE('2011-01-15 19:21:20','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:21:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:23 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51438,50054,TO_DATE('2011-01-15 19:21:20','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:21:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:24 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50055,TO_DATE('2011-01-15 19:21:23','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-08-31','YYYY-MM-DD'),'Y','Aug-11',8,'S','N',TO_DATE('2011-08-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:21:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:26 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51439,50055,TO_DATE('2011-01-15 19:21:24','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:21:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:35 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51440,50055,TO_DATE('2011-01-15 19:21:26','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:21:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:35 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51441,50055,TO_DATE('2011-01-15 19:21:35','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:21:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:37 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51442,50055,TO_DATE('2011-01-15 19:21:35','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:21:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:38 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51443,50055,TO_DATE('2011-01-15 19:21:37','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:21:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:40 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51444,50055,TO_DATE('2011-01-15 19:21:38','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:21:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:40 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51445,50055,TO_DATE('2011-01-15 19:21:40','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:21:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:42 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51446,50055,TO_DATE('2011-01-15 19:21:40','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:21:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:43 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51447,50055,TO_DATE('2011-01-15 19:21:42','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:21:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:47 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51448,50055,TO_DATE('2011-01-15 19:21:43','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:21:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:48 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51449,50055,TO_DATE('2011-01-15 19:21:47','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:21:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51450,50055,TO_DATE('2011-01-15 19:21:48','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:21:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51451,50055,TO_DATE('2011-01-15 19:21:50','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:21:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:52 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51452,50055,TO_DATE('2011-01-15 19:21:50','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:21:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:56 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51453,50055,TO_DATE('2011-01-15 19:21:52','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:21:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:57 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51454,50055,TO_DATE('2011-01-15 19:21:56','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:21:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:21:59 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51455,50055,TO_DATE('2011-01-15 19:21:57','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:21:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:00 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51456,50055,TO_DATE('2011-01-15 19:21:59','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:21:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:01 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51457,50055,TO_DATE('2011-01-15 19:22:00','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:22:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:02 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51458,50055,TO_DATE('2011-01-15 19:22:01','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:22:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:07 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51459,50055,TO_DATE('2011-01-15 19:22:02','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:22:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:09 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51460,50055,TO_DATE('2011-01-15 19:22:07','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:22:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:10 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51461,50055,TO_DATE('2011-01-15 19:22:09','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:22:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:13 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51462,50055,TO_DATE('2011-01-15 19:22:10','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:22:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:15 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51463,50055,TO_DATE('2011-01-15 19:22:13','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:22:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:16 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51464,50055,TO_DATE('2011-01-15 19:22:15','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:22:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:17 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51465,50055,TO_DATE('2011-01-15 19:22:16','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:22:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:18 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51466,50055,TO_DATE('2011-01-15 19:22:17','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:22:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:20 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51467,50055,TO_DATE('2011-01-15 19:22:18','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:22:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:24 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50056,TO_DATE('2011-01-15 19:22:20','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-09-30','YYYY-MM-DD'),'Y','Sep-11',9,'S','N',TO_DATE('2011-09-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:22:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:25 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51468,50056,TO_DATE('2011-01-15 19:22:24','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:22:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:26 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51469,50056,TO_DATE('2011-01-15 19:22:25','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:22:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:28 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51470,50056,TO_DATE('2011-01-15 19:22:26','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:22:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:31 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51471,50056,TO_DATE('2011-01-15 19:22:28','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:22:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:31 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51472,50056,TO_DATE('2011-01-15 19:22:31','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:22:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:35 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51473,50056,TO_DATE('2011-01-15 19:22:31','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:22:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:35 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51474,50056,TO_DATE('2011-01-15 19:22:35','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:22:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51475,50056,TO_DATE('2011-01-15 19:22:35','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:22:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:38 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51476,50056,TO_DATE('2011-01-15 19:22:36','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:22:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:39 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51477,50056,TO_DATE('2011-01-15 19:22:38','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:22:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:41 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51478,50056,TO_DATE('2011-01-15 19:22:39','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:22:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:42 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51479,50056,TO_DATE('2011-01-15 19:22:41','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:22:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:43 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51480,50056,TO_DATE('2011-01-15 19:22:42','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:22:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:45 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51481,50056,TO_DATE('2011-01-15 19:22:43','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:22:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:46 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51482,50056,TO_DATE('2011-01-15 19:22:45','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:22:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:47 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51483,50056,TO_DATE('2011-01-15 19:22:46','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:22:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:49 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51484,50056,TO_DATE('2011-01-15 19:22:47','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:22:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51485,50056,TO_DATE('2011-01-15 19:22:49','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:22:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:52 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51486,50056,TO_DATE('2011-01-15 19:22:50','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:22:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:53 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51487,50056,TO_DATE('2011-01-15 19:22:52','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:22:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:55 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51488,50056,TO_DATE('2011-01-15 19:22:53','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:22:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:56 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51489,50056,TO_DATE('2011-01-15 19:22:55','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:22:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:57 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51490,50056,TO_DATE('2011-01-15 19:22:56','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:22:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:22:58 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51491,50056,TO_DATE('2011-01-15 19:22:57','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:22:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:00 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51492,50056,TO_DATE('2011-01-15 19:22:58','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:22:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:01 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51493,50056,TO_DATE('2011-01-15 19:23:00','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:23:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:02 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51494,50056,TO_DATE('2011-01-15 19:23:01','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:23:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:04 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51495,50056,TO_DATE('2011-01-15 19:23:02','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:23:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:05 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51496,50056,TO_DATE('2011-01-15 19:23:04','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:23:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:10 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50057,TO_DATE('2011-01-15 19:23:05','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-10-31','YYYY-MM-DD'),'Y','Oct-11',10,'S','N',TO_DATE('2011-10-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:23:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:14 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51497,50057,TO_DATE('2011-01-15 19:23:10','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:23:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:15 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51498,50057,TO_DATE('2011-01-15 19:23:14','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:23:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:16 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51499,50057,TO_DATE('2011-01-15 19:23:15','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:23:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:18 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51500,50057,TO_DATE('2011-01-15 19:23:16','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:23:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:20 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51501,50057,TO_DATE('2011-01-15 19:23:18','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:23:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:23 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51502,50057,TO_DATE('2011-01-15 19:23:20','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:23:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:30 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51503,50057,TO_DATE('2011-01-15 19:23:23','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:23:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:32 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51504,50057,TO_DATE('2011-01-15 19:23:30','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:23:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:32 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51505,50057,TO_DATE('2011-01-15 19:23:32','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:23:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:34 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51506,50057,TO_DATE('2011-01-15 19:23:32','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:23:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51507,50057,TO_DATE('2011-01-15 19:23:34','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:23:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:37 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51508,50057,TO_DATE('2011-01-15 19:23:36','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:23:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:39 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51509,50057,TO_DATE('2011-01-15 19:23:37','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:23:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:44 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51510,50057,TO_DATE('2011-01-15 19:23:39','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:23:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:45 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51511,50057,TO_DATE('2011-01-15 19:23:44','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:23:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:48 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51512,50057,TO_DATE('2011-01-15 19:23:45','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:23:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:49 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51513,50057,TO_DATE('2011-01-15 19:23:48','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:23:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51514,50057,TO_DATE('2011-01-15 19:23:49','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:23:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:51 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51515,50057,TO_DATE('2011-01-15 19:23:50','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:23:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:52 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51516,50057,TO_DATE('2011-01-15 19:23:51','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:23:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:53 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51517,50057,TO_DATE('2011-01-15 19:23:52','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:23:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:55 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51518,50057,TO_DATE('2011-01-15 19:23:53','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:23:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:23:56 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51519,50057,TO_DATE('2011-01-15 19:23:55','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:23:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:09 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51520,50057,TO_DATE('2011-01-15 19:23:56','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:23:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:11 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51521,50057,TO_DATE('2011-01-15 19:24:09','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:24:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:12 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51522,50057,TO_DATE('2011-01-15 19:24:11','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:24:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:13 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51523,50057,TO_DATE('2011-01-15 19:24:12','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:24:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:14 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51524,50057,TO_DATE('2011-01-15 19:24:13','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:24:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:15 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51525,50057,TO_DATE('2011-01-15 19:24:14','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:24:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:16 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50058,TO_DATE('2011-01-15 19:24:15','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-11-30','YYYY-MM-DD'),'Y','Nov-11',11,'S','N',TO_DATE('2011-11-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:24:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:18 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51526,50058,TO_DATE('2011-01-15 19:24:16','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:24:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:19 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51527,50058,TO_DATE('2011-01-15 19:24:18','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:24:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:20 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51528,50058,TO_DATE('2011-01-15 19:24:19','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:24:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:22 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51529,50058,TO_DATE('2011-01-15 19:24:20','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:24:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:23 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51530,50058,TO_DATE('2011-01-15 19:24:22','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:24:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:24 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51531,50058,TO_DATE('2011-01-15 19:24:23','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:24:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:25 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51532,50058,TO_DATE('2011-01-15 19:24:24','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:24:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:30 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51533,50058,TO_DATE('2011-01-15 19:24:25','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:24:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:30 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51534,50058,TO_DATE('2011-01-15 19:24:30','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:24:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:33 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51535,50058,TO_DATE('2011-01-15 19:24:30','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:24:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:34 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51536,50058,TO_DATE('2011-01-15 19:24:33','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:24:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:35 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51537,50058,TO_DATE('2011-01-15 19:24:34','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:24:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:38 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51538,50058,TO_DATE('2011-01-15 19:24:35','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:24:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:40 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51539,50058,TO_DATE('2011-01-15 19:24:38','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:24:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:42 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51540,50058,TO_DATE('2011-01-15 19:24:40','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:24:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:42 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51541,50058,TO_DATE('2011-01-15 19:24:42','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:24:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:44 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51542,50058,TO_DATE('2011-01-15 19:24:42','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:24:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:45 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51543,50058,TO_DATE('2011-01-15 19:24:44','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:24:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:49 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51544,50058,TO_DATE('2011-01-15 19:24:45','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:24:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:50 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51545,50058,TO_DATE('2011-01-15 19:24:49','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:24:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:51 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51546,50058,TO_DATE('2011-01-15 19:24:50','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:24:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:52 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51547,50058,TO_DATE('2011-01-15 19:24:51','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:24:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:54 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51548,50058,TO_DATE('2011-01-15 19:24:52','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:24:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:54 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51549,50058,TO_DATE('2011-01-15 19:24:54','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:24:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:56 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51550,50058,TO_DATE('2011-01-15 19:24:54','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:24:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:56 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51551,50058,TO_DATE('2011-01-15 19:24:56','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:24:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:58 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51552,50058,TO_DATE('2011-01-15 19:24:56','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:24:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:24:59 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51553,50058,TO_DATE('2011-01-15 19:24:58','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:24:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:00 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51554,50058,TO_DATE('2011-01-15 19:24:59','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:24:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:15 PM COT
INSERT INTO C_Period (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,C_Year_ID,EndDate,IsActive,Name,PeriodNo,PeriodType,Processing,StartDate,Updated,UpdatedBy) VALUES (11,0,50059,TO_DATE('2011-01-15 19:25:00','YYYY-MM-DD HH24:MI:SS'),100,50004,TO_DATE('2011-12-31','YYYY-MM-DD'),'Y','Dec-11',12,'S','N',TO_DATE('2011-12-01','YYYY-MM-DD'),TO_DATE('2011-01-15 19:25:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:16 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51555,50059,TO_DATE('2011-01-15 19:25:15','YYYY-MM-DD HH24:MI:SS'),100,'MMR','Y','N','N','N',TO_DATE('2011-01-15 19:25:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:17 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51556,50059,TO_DATE('2011-01-15 19:25:16','YYYY-MM-DD HH24:MI:SS'),100,'MMS','Y','N','N','N',TO_DATE('2011-01-15 19:25:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:18 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51557,50059,TO_DATE('2011-01-15 19:25:17','YYYY-MM-DD HH24:MI:SS'),100,'PJI','Y','N','N','N',TO_DATE('2011-01-15 19:25:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:20 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51558,50059,TO_DATE('2011-01-15 19:25:18','YYYY-MM-DD HH24:MI:SS'),100,'CMA','Y','N','N','N',TO_DATE('2011-01-15 19:25:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:22 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51559,50059,TO_DATE('2011-01-15 19:25:20','YYYY-MM-DD HH24:MI:SS'),100,'MXI','Y','N','N','N',TO_DATE('2011-01-15 19:25:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:23 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51560,50059,TO_DATE('2011-01-15 19:25:22','YYYY-MM-DD HH24:MI:SS'),100,'MMP','Y','N','N','N',TO_DATE('2011-01-15 19:25:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:24 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51561,50059,TO_DATE('2011-01-15 19:25:23','YYYY-MM-DD HH24:MI:SS'),100,'GLD','Y','N','N','N',TO_DATE('2011-01-15 19:25:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:25 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51562,50059,TO_DATE('2011-01-15 19:25:24','YYYY-MM-DD HH24:MI:SS'),100,'CMC','Y','N','N','N',TO_DATE('2011-01-15 19:25:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:27 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51563,50059,TO_DATE('2011-01-15 19:25:25','YYYY-MM-DD HH24:MI:SS'),100,'MXP','Y','N','N','N',TO_DATE('2011-01-15 19:25:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:27 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51564,50059,TO_DATE('2011-01-15 19:25:27','YYYY-MM-DD HH24:MI:SS'),100,'MMM','Y','N','N','N',TO_DATE('2011-01-15 19:25:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:28 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51565,50059,TO_DATE('2011-01-15 19:25:27','YYYY-MM-DD HH24:MI:SS'),100,'MMI','Y','N','N','N',TO_DATE('2011-01-15 19:25:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:35 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51566,50059,TO_DATE('2011-01-15 19:25:28','YYYY-MM-DD HH24:MI:SS'),100,'ARF','Y','N','N','N',TO_DATE('2011-01-15 19:25:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:36 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51567,50059,TO_DATE('2011-01-15 19:25:35','YYYY-MM-DD HH24:MI:SS'),100,'CMB','Y','N','N','N',TO_DATE('2011-01-15 19:25:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:37 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51568,50059,TO_DATE('2011-01-15 19:25:36','YYYY-MM-DD HH24:MI:SS'),100,'API','Y','N','N','N',TO_DATE('2011-01-15 19:25:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:38 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51569,50059,TO_DATE('2011-01-15 19:25:37','YYYY-MM-DD HH24:MI:SS'),100,'APC','Y','N','N','N',TO_DATE('2011-01-15 19:25:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:40 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51570,50059,TO_DATE('2011-01-15 19:25:38','YYYY-MM-DD HH24:MI:SS'),100,'GLJ','Y','N','N','N',TO_DATE('2011-01-15 19:25:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:41 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51571,50059,TO_DATE('2011-01-15 19:25:40','YYYY-MM-DD HH24:MI:SS'),100,'ARI','Y','N','N','N',TO_DATE('2011-01-15 19:25:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:43 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51572,50059,TO_DATE('2011-01-15 19:25:41','YYYY-MM-DD HH24:MI:SS'),100,'ARC','Y','N','N','N',TO_DATE('2011-01-15 19:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:44 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51573,50059,TO_DATE('2011-01-15 19:25:43','YYYY-MM-DD HH24:MI:SS'),100,'ARR','Y','N','N','N',TO_DATE('2011-01-15 19:25:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:46 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51574,50059,TO_DATE('2011-01-15 19:25:44','YYYY-MM-DD HH24:MI:SS'),100,'APP','Y','N','N','N',TO_DATE('2011-01-15 19:25:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:47 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51575,50059,TO_DATE('2011-01-15 19:25:46','YYYY-MM-DD HH24:MI:SS'),100,'POR','Y','N','N','N',TO_DATE('2011-01-15 19:25:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:51 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51576,50059,TO_DATE('2011-01-15 19:25:47','YYYY-MM-DD HH24:MI:SS'),100,'SOO','Y','N','N','N',TO_DATE('2011-01-15 19:25:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:55 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51577,50059,TO_DATE('2011-01-15 19:25:51','YYYY-MM-DD HH24:MI:SS'),100,'POO','Y','N','N','N',TO_DATE('2011-01-15 19:25:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:57 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51578,50059,TO_DATE('2011-01-15 19:25:55','YYYY-MM-DD HH24:MI:SS'),100,'HRP','Y','N','N','N',TO_DATE('2011-01-15 19:25:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:57 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51579,50059,TO_DATE('2011-01-15 19:25:57','YYYY-MM-DD HH24:MI:SS'),100,'MOP','Y','N','N','N',TO_DATE('2011-01-15 19:25:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:25:58 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51580,50059,TO_DATE('2011-01-15 19:25:57','YYYY-MM-DD HH24:MI:SS'),100,'MOF','Y','N','N','N',TO_DATE('2011-01-15 19:25:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:26:01 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51581,50059,TO_DATE('2011-01-15 19:25:58','YYYY-MM-DD HH24:MI:SS'),100,'MQO','Y','N','N','N',TO_DATE('2011-01-15 19:25:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:26:02 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51582,50059,TO_DATE('2011-01-15 19:26:01','YYYY-MM-DD HH24:MI:SS'),100,'DOO','Y','N','N','N',TO_DATE('2011-01-15 19:26:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 15, 2011 7:26:03 PM COT
INSERT INTO C_PeriodControl (AD_Client_ID,AD_Org_ID,C_PeriodControl_ID,C_Period_ID,Created,CreatedBy,DocBaseType,IsActive,PeriodAction,PeriodStatus,Processing,Updated,UpdatedBy) VALUES (11,0,51583,50059,TO_DATE('2011-01-15 19:26:02','YYYY-MM-DD HH24:MI:SS'),100,'MCC','Y','N','N','N',TO_DATE('2011-01-15 19:26:02','YYYY-MM-DD HH24:MI:SS'),100)
;

