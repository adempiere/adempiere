-- Dec 10, 2009 12:32:24 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54096,0,'IsImportPriceList',TO_TIMESTAMP('2009-12-10 12:32:23','YYYY-MM-DD HH24:MI:SS'),100,'D',NULL,'Y','Import List price','Import List price',TO_TIMESTAMP('2009-12-10 12:32:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 10, 2009 12:32:24 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54096 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 10, 2009 12:33:05 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,54096,0,53163,53379,20,'IsImportPriceList',TO_TIMESTAMP('2009-12-10 12:33:05','YYYY-MM-DD HH24:MI:SS'),100,'Y','D',1,'Y','Y','N','N','Import ',30,TO_TIMESTAMP('2009-12-10 12:33:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 10, 2009 12:33:05 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53379 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 10, 2009 12:33:28 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
UPDATE AD_Process_Para SET Name='Import List price',Updated=TO_TIMESTAMP('2009-12-10 12:33:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53379
;

-- Dec 10, 2009 12:33:28 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53379
;

-- Dec 10, 2009 12:34:14 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54097,0,'IsImportPriceStd',TO_TIMESTAMP('2009-12-10 12:34:14','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Import Standard Price','Import Standard Price',TO_TIMESTAMP('2009-12-10 12:34:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 10, 2009 12:34:14 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54097 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 10, 2009 12:34:47 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,54097,0,53163,53380,20,'IsImportPriceStd',TO_TIMESTAMP('2009-12-10 12:34:46','YYYY-MM-DD HH24:MI:SS'),100,'Y','D',1,'Y','Y','N','N','Import Standard Price',40,TO_TIMESTAMP('2009-12-10 12:34:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 10, 2009 12:34:47 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53380 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 10, 2009 12:35:34 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54098,0,'IsImportPriceLimit',TO_TIMESTAMP('2009-12-10 12:35:33','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Import Limit Price','Import Limit Price',TO_TIMESTAMP('2009-12-10 12:35:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 10, 2009 12:35:34 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54098 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 10, 2009 12:35:47 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,54098,0,53163,53381,20,'IsImportPriceLimit',TO_TIMESTAMP('2009-12-10 12:35:46','YYYY-MM-DD HH24:MI:SS'),100,'Y','D',1,'Y','Y','N','N','Import Limit Price',50,TO_TIMESTAMP('2009-12-10 12:35:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 10, 2009 12:35:47 PM CET
-- FR [2912011] Import specific Price type(Catalogue, Standard, Limit)
-- https://sourceforge.net/tracker/?func=detail&aid=2912011&group_id=176962&atid=883808
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53381 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

