-- May 31, 2009 11:58:22 PM MYT
-- RMA Feature - ID: 1756793
UPDATE AD_Tab SET WhereClause='MovementType IN (''C-'', ''V-'')',Updated=TO_DATE('2009-05-31 23:58:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=257
;

-- May 31, 2009 11:58:58 PM MYT
-- RMA Feature - ID: 1756793
UPDATE AD_Tab SET WhereClause='MovementType IN (''V+'', ''C+'')',Updated=TO_DATE('2009-05-31 23:58:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=296
;

-- Jun 1, 2009 12:01:38 AM MYT
-- RMA Feature - ID: 1756793
UPDATE AD_Val_Rule SET Code='C_DocType.DocBaseType IN (''MMR'', ''MMS'')',Updated=TO_DATE('2009-06-01 00:01:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=125
;

-- Jun 1, 2009 12:07:49 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52053,'C_DocType.DocBaseType IN (''MMS'')',TO_DATE('2009-06-01 00:07:45','YYYY-MM-DD HH24:MI:SS'),100,'Document Type Material Shipments','D','Y','C_DocType Material Shipments','S',TO_DATE('2009-06-01 00:07:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 1, 2009 12:08:41 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52054,'C_DocType.DocBaseType IN (''MMR'')',TO_DATE('2009-06-01 00:08:39','YYYY-MM-DD HH24:MI:SS'),100,'Document Type Material Receipts','D','Y','C_DocType Material Receipts','S',TO_DATE('2009-06-01 00:08:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 1, 2009 12:10:25 AM MYT
-- RMA Feature - ID: 1756793
UPDATE AD_Field SET AD_Reference_ID=19, AD_Val_Rule_ID=52054,Updated=TO_DATE('2009-06-01 00:10:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=3489
;

-- Jun 1, 2009 12:11:25 AM MYT
-- RMA Feature - ID: 1756793
UPDATE AD_Field SET AD_Reference_ID=19, AD_Val_Rule_ID=52053,Updated=TO_DATE('2009-06-01 00:11:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2931
;

-- Jun 1, 2009 12:17:54 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53866,0,'Ref_RMA_ID',TO_DATE('2009-06-01 00:17:47','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Referenced RMA','Ref RMA',TO_DATE('2009-06-01 00:17:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 1, 2009 12:17:54 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53866 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 1, 2009 12:18:40 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53867,0,'Ref_RMALine_ID',TO_DATE('2009-06-01 00:18:37','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Referenced RMA Line','Ref RMA Line',TO_DATE('2009-06-01 00:18:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 1, 2009 12:18:40 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53867 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 1, 2009 12:33:03 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53306,TO_DATE('2009-06-01 00:33:01','YYYY-MM-DD HH24:MI:SS'),100,'RMA','D','Y','N','M_RMA',TO_DATE('2009-06-01 00:33:01','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Jun 1, 2009 12:33:03 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53306 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Jun 1, 2009 12:36:11 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy) VALUES (0,10841,10847,0,53306,661,TO_DATE('2009-06-01 00:36:11','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','M_RMA.DocumentNo',TO_DATE('2009-06-01 00:36:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 1, 2009 12:37:45 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57790,53866,0,30,53306,661,'Ref_RMA_ID',TO_DATE('2009-06-01 00:37:41','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Referenced RMA',0,TO_DATE('2009-06-01 00:37:41','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jun 1, 2009 12:37:45 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57790 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 1, 2009 12:37:54 AM MYT
-- RMA Feature - ID: 1756793
ALTER TABLE M_RMA ADD Ref_RMA_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 1, 2009 12:41:24 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53307,TO_DATE('2009-06-01 00:41:21','YYYY-MM-DD HH24:MI:SS'),100,'RMA Line','D',NULL,'Y','N','M_RMALine',TO_DATE('2009-06-01 00:41:21','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Jun 1, 2009 12:41:24 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53307 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Jun 1, 2009 12:41:52 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,52004,10831,0,53307,660,TO_DATE('2009-06-01 00:41:52','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_DATE('2009-06-01 00:41:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 1, 2009 12:43:30 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57791,53867,0,18,53307,660,'Ref_RMALine_ID',TO_DATE('2009-06-01 00:43:28','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Referenced RMA Line',0,TO_DATE('2009-06-01 00:43:28','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jun 1, 2009 12:43:30 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57791 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 1, 2009 12:47:11 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57792,529,0,29,660,'QtyInvoiced',TO_DATE('2009-06-01 00:47:09','YYYY-MM-DD HH24:MI:SS'),100,'Invoiced Quantity','D',22,'The Invoiced Quantity indicates the quantity of a product that have been invoiced.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Quantity Invoiced',0,TO_DATE('2009-06-01 00:47:09','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jun 1, 2009 12:47:11 AM MYT
-- RMA Feature - ID: 1756793
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57792 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 1, 2009 12:47:30 AM MYT
-- RMA Feature - ID: 1756793
ALTER TABLE M_RMALine ADD QtyInvoiced NUMBER DEFAULT NULL 
;

-- Jun 1, 2009 12:49:19 AM MYT
-- RMA Feature - ID: 1756793
ALTER TABLE M_RMALine ADD Ref_RMALine_ID NUMBER(10) DEFAULT NULL 
;

