-- Jul 26, 2008 7:59:19 PM CDT
-- POReference
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56246,952,0,10,323,'POReference',TO_TIMESTAMP('2008-07-26 19:59:18','YYYY-MM-DD HH24:MI:SS'),100,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner','EE01',20,'The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','N','N','N','N','N','N','N','N','N','Y','Order Reference',0,TO_TIMESTAMP('2008-07-26 19:59:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 26, 2008 7:59:19 PM CDT
-- POReference
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56246 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 26, 2008 7:59:22 PM CDT
-- POReference
ALTER TABLE M_Movement ADD COLUMN POReference VARCHAR(20)
;

-- Jul 26, 2008 8:00:02 PM CDT
-- POReference
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56246,56323,0,259,TO_TIMESTAMP('2008-07-26 20:00:01','YYYY-MM-DD HH24:MI:SS'),100,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner',20,'EE01','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','Y','N','N','N','N','N','Order Reference',TO_TIMESTAMP('2008-07-26 20:00:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 26, 2008 8:00:02 PM CDT
-- POReference
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56323 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56323
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=2726
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=54046
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=2722
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=2725
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=10418
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54047
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=54048
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=54049
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=54050
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=54051
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=54052
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=54053
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=54054
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=7791
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=7788
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=7792
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=7793
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=7790
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=7789
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=10530
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=10529
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=54055
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=9223
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=9222
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=9224
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=9221
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=2727
;

-- Jul 26, 2008 8:00:30 PM CDT
-- POReference
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=5144
;

-- Jul 26, 2008 8:00:48 PM CDT
-- POReference
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-07-26 20:00:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56323
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=52001
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=460
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=301
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=129
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=543
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=195
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=1000019
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=407
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=406
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=335
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=436
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=507
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=472
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=448
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=1000006
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=449
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53050
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53049
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=445
;

-- Jul 26, 2008 8:29:02 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53051
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=52001
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=460
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=301
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=129
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=543
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=195
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=1000019
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=407
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=406
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=335
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=436
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=507
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=448
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=1000006
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=449
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53050
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53049
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=445
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=472
;

-- Jul 26, 2008 8:29:11 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53048, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53051
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=167
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=357
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=229
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=412
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=256
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=197
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=179
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=503
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=196
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=228
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=479
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=482
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=481
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=411
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=537
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=311
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=292
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=515
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=183, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=545
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53067
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=381
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53088
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=128
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53068
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=1000041
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53069
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53070
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=181
;

-- Jul 26, 2008 8:29:55 PM CDT
-- Order Distribution
UPDATE AD_TreeNodeMM SET Parent_ID=53066, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=484
;



