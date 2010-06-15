-- Dec 5, 2009 4:16:56 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58786,1394,0,12,572,'CurrentCostPrice',TO_TIMESTAMP('2009-12-05 16:16:54','YYYY-MM-DD HH24:MI:SS'),100,'The currently used cost price','D',14,'Y','N','N','N','N','N','N','N','N','N','N','N','Y','Current Cost Price',0,TO_TIMESTAMP('2009-12-05 16:16:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 5, 2009 4:16:56 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58786 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 5, 2009 4:17:41 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
ALTER TABLE I_Inventory ADD COLUMN CurrentCostPrice NUMERIC DEFAULT NULL 
;

-- Dec 5, 2009 4:18:42 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58786,58565,0,481,TO_TIMESTAMP('2009-12-05 16:18:41','YYYY-MM-DD HH24:MI:SS'),100,'The currently used cost price',0,'D','Y','Y','Y','N','N','N','N','N','Current Cost Price',270,0,TO_TIMESTAMP('2009-12-05 16:18:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2009 4:18:42 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58565 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 5, 2009 4:19:20 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET DisplayLength=14,Updated=TO_TIMESTAMP('2009-12-05 16:19:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58565
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=6691
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=6696
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=8193
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=6692
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=6693
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=8191
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=6700
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=6695
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=6690
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=6694
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=8192
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=6701
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=6709
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=6702
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=6707
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=7064
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=6699
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=6704
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=6708
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=6710
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=6698
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=6711
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=6705
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=6703
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=6706
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=58565
;

-- Dec 5, 2009 4:19:35 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=6697
;

-- Dec 5, 2009 4:35:28 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54095,0,'IsUpdateCosting',TO_TIMESTAMP('2009-12-05 16:35:27','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Update Costing','Update Costing',TO_TIMESTAMP('2009-12-05 16:35:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2009 4:35:28 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54095 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 5, 2009 4:36:06 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,54095,0,219,53374,20,'IsUpdateCosting',TO_TIMESTAMP('2009-12-05 16:36:06','YYYY-MM-DD HH24:MI:SS'),100,'N','D',1,'Y','Y','N','N','IsUpdateCosting',60,TO_TIMESTAMP('2009-12-05 16:36:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2009 4:36:06 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53374 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 5, 2009 4:36:29 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET Name='Update Costing',Updated=TO_TIMESTAMP('2009-12-05 16:36:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53374
;

-- Dec 5, 2009 4:36:29 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53374
;

-- Dec 5, 2009 4:38:13 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,181,0,219,53375,19,'C_AcctSchema_ID',TO_TIMESTAMP('2009-12-05 16:38:13','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','Accounting Schema',70,TO_TIMESTAMP('2009-12-05 16:38:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2009 4:38:13 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53375 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 5, 2009 4:41:14 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DefaultValue='$C_AcctSchema_ID',Updated=TO_TIMESTAMP('2009-12-05 16:41:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53375
;

-- Dec 5, 2009 4:44:32 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2071,0,219,53376,19,'M_CostType_ID',TO_TIMESTAMP('2009-12-05 16:44:31','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','Cost Type',80,TO_TIMESTAMP('2009-12-05 16:44:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2009 4:44:32 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53376 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 5, 2009 4:45:30 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2700,0,219,53377,19,'M_CostElement_ID',TO_TIMESTAMP('2009-12-05 16:45:29','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','Cost Element',90,TO_TIMESTAMP('2009-12-05 16:45:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2009 4:45:30 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53377 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 5, 2009 4:47:15 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic='IsUpdateCosting=''Y''',Updated=TO_TIMESTAMP('2009-12-05 16:47:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53375
;

-- Dec 5, 2009 4:47:18 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic='IsUpdateCosting=''Y''',Updated=TO_TIMESTAMP('2009-12-05 16:47:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53376
;

-- Dec 5, 2009 4:47:21 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic='IsUpdateCosting=''Y''',Updated=TO_TIMESTAMP('2009-12-05 16:47:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53377
;

-- Dec 5, 2009 4:48:43 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic=NULL, ReadOnlyLogic='IsUpdateCosting=''Y''',Updated=TO_TIMESTAMP('2009-12-05 16:48:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53377
;

-- Dec 5, 2009 4:48:48 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic=NULL, ReadOnlyLogic='IsUpdateCosting=''Y''',Updated=TO_TIMESTAMP('2009-12-05 16:48:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53376
;

-- Dec 5, 2009 4:48:53 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic=NULL, ReadOnlyLogic='IsUpdateCosting=''Y''',Updated=TO_TIMESTAMP('2009-12-05 16:48:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53375
;

-- Dec 5, 2009 4:49:40 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic='@IsUpdateCosting@=''Y''', ReadOnlyLogic=NULL,Updated=TO_TIMESTAMP('2009-12-05 16:49:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53375
;

-- Dec 5, 2009 4:49:51 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic='@IsUpdateCosting@=''Y''', ReadOnlyLogic=NULL,Updated=TO_TIMESTAMP('2009-12-05 16:49:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53376
;

-- Dec 5, 2009 4:49:56 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic='@IsUpdateCosting@=''Y''', ReadOnlyLogic=NULL,Updated=TO_TIMESTAMP('2009-12-05 16:49:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53377
;

-- Dec 6, 2009 12:23:41 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,112,0,219,53378,18,276,'AD_OrgTrx_ID',TO_TIMESTAMP('2009-12-06 12:23:40','YYYY-MM-DD HH24:MI:SS'),100,'0','D',10,'Y','Y','N','N','Organization for Cost Record',100,TO_TIMESTAMP('2009-12-06 12:23:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 6, 2009 12:23:41 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53378 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 6, 2009 12:33:02 PM CET
-- FR [2909559] Update Product Cost record when importing Inventory
-- https://sourceforge.net/tracker/?func=detail&aid=2909559&group_id=176962&atid=883808
UPDATE AD_Process_Para SET DisplayLogic='@IsUpdateCosting@=''Y''',Updated=TO_TIMESTAMP('2009-12-06 12:33:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53378
;

