-- Mar 10, 2008 8:51:52 AM CST
-- Improve RMA Functionality
UPDATE AD_Tab SET WhereClause='MovementType IN (''C-'', ''C+'')',Updated=TO_DATE('2008-03-10 08:51:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=257
;

-- Mar 10, 2008 8:52:19 AM CST
-- Improve RMA Functionality
UPDATE AD_Tab SET WhereClause='MovementType IN (''V+'', ''V-'')',Updated=TO_DATE('2008-03-10 08:52:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=296
;
-- 10-mar-2008 16:52:00 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@DocBaseType@=''SOO'' | ( @DocBaseType@=''POO'' & (DocSubTypeSO_Sub=null OR DocSubTypeSO_Sub=''RM'') )',Updated=TO_DATE('2008-03-10 16:52:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2581
;

-- 10-mar-2008 16:54:36 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@DocBaseType@=''SOO'' | ( @DocBaseType@=''POO'' & @DocSubTypeSO@=''RM'' )',Updated=TO_DATE('2008-03-10 16:54:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3071
;

-- 10-mar-2008 16:54:44 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@DocBaseType@=''SOO'' | ( @DocBaseType@=''POO'' & @DocSubTypeSO@=''RM'' )',Updated=TO_DATE('2008-03-10 16:54:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3073
;

-- 10-mar-2008 17:02:53 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@DocBaseType@=''SOO''  ^ @DocBaseType@=''POO'' & @DocSubTypeSO@=''RM''',Updated=TO_DATE('2008-03-10 17:02:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3073
;

-- 10-mar-2008 17:04:31 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@DocBaseType@=''SOO'' | @DocBaseType@=''POO''',Updated=TO_DATE('2008-03-10 17:04:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3071
;

-- 10-mar-2008 17:04:38 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@DocBaseType@=''SOO'' | @DocBaseType@=''POO''',Updated=TO_DATE('2008-03-10 17:04:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3073
;

-- 10-mar-2008 17:06:09 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@DocBaseType@=''SOO'' | @DocBaseType@=''POO''',Updated=TO_DATE('2008-03-10 17:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2581
;

-- 10-mar-2008 17:22:30 CST
-- Improve RMA Functionality
UPDATE C_DocType SET GL_Category_ID=0,Updated=TO_DATE('2008-03-10 17:22:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=150
;

-- 10-mar-2008 17:43:32 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=4242
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=4243
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=2930
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=2933
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=7831
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=7829
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=7830
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=7832
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=7828
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=7827
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=2707
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=10369
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=9463
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=9462
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=3280
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=3281
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=10568
;

-- 10-mar-2008 17:43:33 CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=5143
;

-- 10-mar-2008 17:48:10 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@MovementType@=''C+''',Updated=TO_DATE('2008-03-10 17:48:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4242
;

-- 10-mar-2008 17:48:14 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@MovementType@=''C+''',Updated=TO_DATE('2008-03-10 17:48:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4243
;

-- 10-mar-2008 17:58:26 CST
-- Improve RMA Functionality
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-03-10 17:58:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4243
;

-- 10-mar-2008 18:04:36 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@MovementType@=''C+''',Updated=TO_DATE('2008-03-10 18:04:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2932
;

-- 10-mar-2008 18:04:42 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@MovementType@!''C+''',Updated=TO_DATE('2008-03-10 18:04:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2932
;

-- 10-mar-2008 18:05:46 CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_DATE('2008-03-10 18:05:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2932
;

-- Mar 11, 2008 8:29:43 AM CST
-- Improve RMA Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,52009,54714,0,257,TO_DATE('2008-03-11 08:29:36','YYYY-MM-DD HH24:MI:SS'),0,'Return Material Authorization',22,'D','A Return Material Authorization may be required to accept returns and to create Credit Memos','Y','Y','Y','N','N','N','N','N','RMA',TO_DATE('2008-03-11 08:29:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 11, 2008 8:29:43 AM CST
-- Improve RMA Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54714 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 11, 2008 8:29:43 AM CST
-- Improve RMA Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,15903,54715,0,257,TO_DATE('2008-03-11 08:29:43','YYYY-MM-DD HH24:MI:SS'),0,'Volume of a product',22,'D','The Volume indicates the volume of the product in the Volume UOM of the Client','Y','Y','Y','N','N','N','N','N','Volume',TO_DATE('2008-03-11 08:29:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 11, 2008 8:29:43 AM CST
-- Improve RMA Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54715 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 11, 2008 8:29:46 AM CST
-- Improve RMA Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,15904,54716,0,257,TO_DATE('2008-03-11 08:29:43','YYYY-MM-DD HH24:MI:SS'),0,'Weight of a product',22,'D','The Weight indicates the weight  of the product in the Weight UOM of the Client','Y','Y','Y','N','N','N','N','N','Weight',TO_DATE('2008-03-11 08:29:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 11, 2008 8:29:46 AM CST
-- Improve RMA Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54716 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=54715
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=54716
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=54714
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=2938
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=2945
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=2703
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=2931
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=2706
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=2935
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=2928
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=2929
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=2927
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=2944
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=2946
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=2936
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=6877
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=6541
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=2937
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=2943
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=10379
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=6880
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=6879
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=6878
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=2940
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=2939
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=4242
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=4243
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=2930
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=2933
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=7831
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=7829
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=7830
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=7832
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=7828
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=7827
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=2707
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=10369
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=9463
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=9462
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=3280
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=3281
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=10568
;

-- Mar 11, 2008 8:30:31 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=5143
;

-- Mar 11, 2008 8:31:07 AM CST
-- Improve RMA Functionality
UPDATE AD_Field SET DisplayLogic='@M_RMA_ID@!0',Updated=TO_DATE('2008-03-11 08:31:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54714
;

-- Mar 11, 2008 8:33:45 AM CST
-- Improve RMA Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53100,TO_DATE('2008-03-11 08:33:44','YYYY-MM-DD HH24:MI:SS'),100,590001,59000,'MM Customer Return',1,'Y','N','Y','N','MM Customer Return','N',590000,TO_DATE('2008-03-11 08:33:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 11, 2008 8:34:12 AM CST
-- Improve RMA Functionality
UPDATE C_DocType SET DocNoSequence_ID=53100,Updated=TO_DATE('2008-03-11 08:34:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=149
;

-- Mar 11, 2008 8:34:48 AM CST
-- Improve RMA Functionality
UPDATE C_DocType SET DocBaseType='MMR',Updated=TO_DATE('2008-03-11 08:34:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=149
;

-- Mar 11, 2008 8:35:16 AM CST
-- Improve RMA Functionality
UPDATE C_DocType SET DocBaseType='MMS',Updated=TO_DATE('2008-03-11 08:35:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=151
;

-- Mar 11, 2008 9:39:08 AM CST
-- Improve RMA Functionality
UPDATE C_DocType SET IsDocNoControlled='Y', IsSOTrx='Y',Updated=TO_DATE('2008-03-11 09:39:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=149
;

-- Mar 11, 2008 9:39:28 AM CST
-- Improve RMA Functionality
UPDATE C_DocType SET C_DocTypeInvoice_ID=124,Updated=TO_DATE('2008-03-11 09:39:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=150
;

