-- Jan 28, 2009 7:42:27 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56679,241,0,17,122,53161,'CostingMethod',TO_TIMESTAMP('2009-01-28 19:42:23','YYYY-MM-DD HH24:MI:SS'),0,'Indicates how Costs will be calculated','EE01',1,'The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FoFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Costing Method',0,TO_TIMESTAMP('2009-01-28 19:42:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 28, 2009 7:42:27 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56679 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 28, 2009 7:46:06 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56680,241,0,17,122,53045,'CostingMethod',TO_TIMESTAMP('2009-01-28 19:46:03','YYYY-MM-DD HH24:MI:SS'),0,'Indicates how Costs will be calculated','EE01',1,'The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FoFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Costing Method',0,TO_TIMESTAMP('2009-01-28 19:46:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 28, 2009 7:46:07 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56680 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 28, 2009 7:46:34 PM ECT
-- Manufacturing Standard Cost
ALTER TABLE T_BOMLine ADD COLUMN CostingMethod CHAR(1) DEFAULT NULL 
;

-- Jan 28, 2009 8:11:02 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56681,2071,0,19,53045,'M_CostType_ID',TO_TIMESTAMP('2009-01-28 20:11:01','YYYY-MM-DD HH24:MI:SS'),0,'Type of Cost (e.g. Current, Plan, Future)','EE01',10,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Cost Type',0,TO_TIMESTAMP('2009-01-28 20:11:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 28, 2009 8:11:02 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56681 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 28, 2009 8:11:06 PM ECT
-- Manufacturing Standard Cost
ALTER TABLE T_BOMLine ADD COLUMN M_CostType_ID NUMERIC(10) DEFAULT NULL 
;

-- Jan 28, 2009 8:11:44 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56682,2071,0,19,53161,'M_CostType_ID',TO_TIMESTAMP('2009-01-28 20:11:42','YYYY-MM-DD HH24:MI:SS'),0,'Type of Cost (e.g. Current, Plan, Future)','EE01',10,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Cost Type',0,TO_TIMESTAMP('2009-01-28 20:11:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 28, 2009 8:11:44 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56682 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 28, 2009 11:26:31 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2009-01-28 23:26:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56549
;

-- Jan 28, 2009 11:26:37 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2009-01-28 23:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56542
;

-- Jan 28, 2009 11:43:47 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53980
;

-- Jan 28, 2009 11:43:47 PM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=53999
;

-- Jan 28, 2009 11:59:27 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53763,0,'FutureCostPriceLL',TO_TIMESTAMP('2009-01-28 23:59:25','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Future Cost Price Lower Level','Future Cost price Lower Level',TO_TIMESTAMP('2009-01-28 23:59:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 28, 2009 11:59:27 PM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53763 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jan 29, 2009 12:02:07 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56683,53763,0,37,771,'FutureCostPriceLL',TO_TIMESTAMP('2009-01-29 00:02:05','YYYY-MM-DD HH24:MI:SS'),0,'@CostingMethod@!S','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Future Cost Price Lower Level',0,TO_TIMESTAMP('2009-01-29 00:02:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:02:07 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56683 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:02:10 AM ECT
-- Manufacturing Standard Cost
ALTER TABLE M_Cost ADD COLUMN FutureCostPriceLL NUMERIC DEFAULT NULL 
;

-- Jan 29, 2009 12:02:47 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56683,56625,0,701,TO_TIMESTAMP('2009-01-29 00:02:46','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','N','N','Future Cost Price Lower Level',TO_TIMESTAMP('2009-01-29 00:02:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 12:02:47 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56625 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 29, 2009 12:03:13 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56266
;

-- Jan 29, 2009 12:03:13 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=11352
;

-- Jan 29, 2009 12:03:13 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56625
;

-- Jan 29, 2009 12:03:13 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12325
;

-- Jan 29, 2009 12:03:13 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=12175
;

-- Jan 29, 2009 12:03:13 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=12176
;

-- Jan 29, 2009 12:03:13 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=12319
;

-- Jan 29, 2009 12:03:39 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2009-01-29 00:03:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56625
;

-- Jan 29, 2009 12:03:42 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-01-29 00:03:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56625
;

-- Jan 29, 2009 12:03:51 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-01-29 00:03:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56266
;

-- Jan 29, 2009 12:05:13 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2009-01-29 00:05:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=11352
;

-- Jan 29, 2009 12:07:23 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Column SET DefaultValue=NULL, ReadOnlyLogic='@CostingMethod@!S',Updated=TO_TIMESTAMP('2009-01-29 00:07:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56683
;

-- Jan 29, 2009 12:08:07 AM ECT
-- Manufacturing Standard Cost
insert into t_alter_column values('m_cost','FutureCostPriceLL','NUMERIC',null,'NULL')
;

-- Jan 29, 2009 12:13:37 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53764,0,'IsCostFrozen',TO_TIMESTAMP('2009-01-29 00:13:36','YYYY-MM-DD HH24:MI:SS'),0,'Indicated that the Standard Cost is frozen','EE01','Y','Is Cost Frozen','Is Cost Frozen',TO_TIMESTAMP('2009-01-29 00:13:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 12:13:37 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53764 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jan 29, 2009 12:14:03 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56684,53764,0,20,771,'IsCostFrozen',TO_TIMESTAMP('2009-01-29 00:14:02','YYYY-MM-DD HH24:MI:SS'),0,'N','Indicated that the Standard Cost is frozen','EE01',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Is Cost Frozen',0,TO_TIMESTAMP('2009-01-29 00:14:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:14:03 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56684 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:14:07 AM ECT
-- Manufacturing Standard Cost
ALTER TABLE M_Cost ADD COLUMN IsCostFrozen CHAR(1) DEFAULT 'N' CHECK (IsCostFrozen IN ('Y','N'))
;

-- Jan 29, 2009 12:14:32 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56684,56626,0,701,TO_TIMESTAMP('2009-01-29 00:14:30','YYYY-MM-DD HH24:MI:SS'),0,'Indicated that the Standard Cost is frozen',1,'EE01','Y','Y','Y','N','N','N','N','N','Is Cost Frozen',TO_TIMESTAMP('2009-01-29 00:14:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 12:14:32 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56626 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 29, 2009 12:14:44 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56626
;

-- Jan 29, 2009 12:14:44 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=12175
;

-- Jan 29, 2009 12:14:44 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=12176
;

-- Jan 29, 2009 12:14:44 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=12319
;

-- Jan 29, 2009 12:14:58 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-01-29 00:14:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56626
;

-- Jan 29, 2009 12:15:01 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2009-01-29 00:15:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56626
;

-- Jan 29, 2009 12:15:40 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2009-01-29 00:15:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12325
;

-- Jan 29, 2009 12:15:57 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET DisplayLogic='@CostingMethod@=S',Updated=TO_TIMESTAMP('2009-01-29 00:15:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56625
;

-- Jan 29, 2009 12:16:07 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET DisplayLogic='@CostingMethod@=S',Updated=TO_TIMESTAMP('2009-01-29 00:16:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56626
;

-- Jan 29, 2009 12:17:22 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Element SET Name='Cost Frozen', PrintName='Cost Frozen',Updated=TO_TIMESTAMP('2009-01-29 00:17:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53764
;

-- Jan 29, 2009 12:17:22 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53764
;

-- Jan 29, 2009 12:17:22 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Column SET ColumnName='IsCostFrozen', Name='Cost Frozen', Description='Indicated that the Standard Cost is frozen', Help=NULL WHERE AD_Element_ID=53764
;

-- Jan 29, 2009 12:17:22 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET ColumnName='IsCostFrozen', Name='Cost Frozen', Description='Indicated that the Standard Cost is frozen', Help=NULL, AD_Element_ID=53764 WHERE UPPER(ColumnName)='ISCOSTFROZEN' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 29, 2009 12:17:22 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET ColumnName='IsCostFrozen', Name='Cost Frozen', Description='Indicated that the Standard Cost is frozen', Help=NULL WHERE AD_Element_ID=53764 AND IsCentrallyMaintained='Y'
;

-- Jan 29, 2009 12:17:22 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Field SET Name='Cost Frozen', Description='Indicated that the Standard Cost is frozen', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53764) AND IsCentrallyMaintained='Y'
;

-- Jan 29, 2009 12:17:22 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem SET PrintName='Cost Frozen', Name='Cost Frozen' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53764)
;

-- Jan 29, 2009 12:22:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56685,1397,0,37,53045,'FutureCostPrice',TO_TIMESTAMP('2009-01-29 00:22:01','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','N','N','Y','Future Cost Price',0,TO_TIMESTAMP('2009-01-29 00:22:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:22:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56685 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:22:07 AM ECT
-- Manufacturing Standard Cost
ALTER TABLE T_BOMLine ADD COLUMN FutureCostPrice NUMERIC DEFAULT NULL 
;

-- Jan 29, 2009 12:22:27 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56686,53763,0,37,53045,'FutureCostPriceLL',TO_TIMESTAMP('2009-01-29 00:22:26','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','N','N','Y','Future Cost Price Lower Level',0,TO_TIMESTAMP('2009-01-29 00:22:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:22:27 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56686 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:22:30 AM ECT
-- Manufacturing Standard Cost
ALTER TABLE T_BOMLine ADD COLUMN FutureCostPriceLL NUMERIC DEFAULT NULL 
;

-- Jan 29, 2009 12:23:21 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56687,1397,0,22,53161,'FutureCostPrice',TO_TIMESTAMP('2009-01-29 00:23:20','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','N','N','Y','Future Cost Price',TO_TIMESTAMP('2009-01-29 00:23:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:23:21 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56687 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:23:38 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56688,53763,0,22,53161,'FutureCostPriceLL',TO_TIMESTAMP('2009-01-29 00:23:37','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','N','N','N','N','Y','Future Cost Price Lower Level',TO_TIMESTAMP('2009-01-29 00:23:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:23:38 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56688 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:23:48 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Column SET AD_Reference_ID=37,Updated=TO_TIMESTAMP('2009-01-29 00:23:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56688
;

-- Jan 29, 2009 12:24:03 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Column SET AD_Reference_ID=37,Updated=TO_TIMESTAMP('2009-01-29 00:24:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56646
;

-- Jan 29, 2009 12:24:17 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Column SET AD_Reference_ID=37, FieldLength=22,Updated=TO_TIMESTAMP('2009-01-29 00:24:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56643
;

-- Jan 29, 2009 12:24:23 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Column SET AD_Reference_ID=37,Updated=TO_TIMESTAMP('2009-01-29 00:24:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56687
;

-- Jan 29, 2009 12:24:49 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Column SET AD_Reference_ID=37, FieldLength=22,Updated=TO_TIMESTAMP('2009-01-29 00:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56644
;

-- Jan 29, 2009 12:26:34 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56689,53764,0,20,53161,'IsCostFrozen',TO_TIMESTAMP('2009-01-29 00:26:34','YYYY-MM-DD HH24:MI:SS'),0,'Indicated that the Standard Cost is frozen','EE01',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Frozen',0,TO_TIMESTAMP('2009-01-29 00:26:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:26:34 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56689 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:27:30 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56690,53764,0,20,53045,'IsCostFrozen',TO_TIMESTAMP('2009-01-29 00:27:29','YYYY-MM-DD HH24:MI:SS'),0,'Indicated that the Standard Cost is frozen','EE01',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Frozen',0,TO_TIMESTAMP('2009-01-29 00:27:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:27:30 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56690 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:27:36 AM ECT
-- Manufacturing Standard Cost
ALTER TABLE T_BOMLine ADD COLUMN IsCostFrozen CHAR(1) DEFAULT NULL CHECK (IsCostFrozen IN ('Y','N'))
;

-- Jan 29, 2009 12:43:45 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56688,0,100,130,51066,50038,0,0,TO_TIMESTAMP('2009-01-29 00:43:42','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Future Cost Price','C','F','Future Cost Price',20,110,'N',0,TO_TIMESTAMP('2009-01-29 00:43:42','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 29, 2009 12:43:45 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51066 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 29, 2009 12:43:45 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56688) WHERE AD_PrintFormatItem_ID = 51066 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56688 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51066) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 29, 2009 12:44:02 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsNextLine='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-01-29 00:44:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51066
;

-- Jan 29, 2009 12:44:44 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-01-29 00:44:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51066
;

-- Jan 29, 2009 12:45:18 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem SET AD_PrintColor_ID=NULL, AD_PrintFont_ID=NULL, IsGroupBy='N', IsPageBreak='N', PrintName='F  Cost', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-01-29 00:45:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51066
;

-- Jan 29, 2009 12:45:18 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51066
;

-- Jan 29, 2009 12:45:49 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem SET FieldAlignmentType='T', IsGroupBy='N', IsPageBreak='N', Name='F Cost', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-01-29 00:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51066
;

-- Jan 29, 2009 12:46:16 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56688,0,51067,50038,0,0,TO_TIMESTAMP('2009-01-29 00:46:10','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','Y','N','N','N','N','N','N','N','Y','Y','N','N','Y','N','N','N','X',1,0,0,'F Cost LL','C','F','F  Cost LL',20,110,'N',0,TO_TIMESTAMP('2009-01-29 00:46:10','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 29, 2009 12:46:16 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51067 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 29, 2009 12:46:16 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56688) WHERE AD_PrintFormatItem_ID = 51067 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56688 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51067) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 29, 2009 12:46:26 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem SET AD_Column_ID=56687, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-01-29 00:46:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51066
;

-- Jan 29, 2009 12:51:41 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56691,240,0,37,53161,'CostStandard',TO_TIMESTAMP('2009-01-29 00:51:39','YYYY-MM-DD HH24:MI:SS'),0,'Standard Costs','EE01',22,'Standard (plan) costs.','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Standard Cost',TO_TIMESTAMP('2009-01-29 00:51:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:51:41 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56691 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:52:21 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56692,240,0,37,53045,'CostStandard',TO_TIMESTAMP('2009-01-29 00:52:18','YYYY-MM-DD HH24:MI:SS'),0,'Standard Costs','EE01',22,'Standard (plan) costs.','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Standard Cost',0,TO_TIMESTAMP('2009-01-29 00:52:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 29, 2009 12:52:21 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56692 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 29, 2009 12:52:24 AM ECT
-- Manufacturing Standard Cost
ALTER TABLE T_BOMLine ADD COLUMN CostStandard NUMERIC DEFAULT NULL 
;

-- Jan 29, 2009 12:53:40 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56691,0,51068,50038,0,0,TO_TIMESTAMP('2009-01-29 00:53:33','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','Y','N','N','N','N','N','N','N','Y','Y','N','N','Y','N','N','N','X',1,0,0,'S Cost','C','F','S Cost',20,110,'N',0,TO_TIMESTAMP('2009-01-29 00:53:33','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 29, 2009 12:53:40 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51068 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 29, 2009 12:53:40 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56691) WHERE AD_PrintFormatItem_ID = 51068 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56691 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51068) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 29, 2009 12:54:59 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56689,0,51069,50038,0,0,TO_TIMESTAMP('2009-01-29 00:54:54','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Frozen','C','F',20,120,'N',0,TO_TIMESTAMP('2009-01-29 00:54:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 29, 2009 12:55:00 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51069 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 29, 2009 1:05:27 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53764,0,53056,53274,20,'IsCostFrozen',TO_TIMESTAMP('2009-01-29 01:05:22','YYYY-MM-DD HH24:MI:SS'),0,'N','Cost Frozen','EE01',1,'Indicated that the Standard Cost is frozen','Y','Y','N','N','Cost Frozen',90,TO_TIMESTAMP('2009-01-29 01:05:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:05:27 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53274 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 29, 2009 1:07:29 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET IsActive='N',Updated=TO_TIMESTAMP('2009-01-29 01:07:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53274
;

-- Jan 29, 2009 1:31:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53160,'3','org.eevolution.process.FrozenUnFrozenCost',TO_TIMESTAMP('2009-01-29 01:30:49','YYYY-MM-DD HH24:MI:SS'),0,'Frozen or UnFrozen Cost','EE01','Lets freeze or unfreeze the cost, If the Cost is UnFrozen then can be changed','Y','Y','N','N','N','Frozen/UnFrozen Cost','Y',0,0,TO_TIMESTAMP('2009-01-29 01:30:49','YYYY-MM-DD HH24:MI:SS'),0,'PP_Cost Frozen/UnFrozen')
;

-- Jan 29, 2009 1:31:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53160 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Jan 29, 2009 1:31:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53160,50002,TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:31:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53160,0,TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:31:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53160,102,TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:31:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53160,103,TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:31:02 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53160,50001,TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2009-01-29 01:31:02','YYYY-MM-DD HH24:MI:SS'),0)
;


-- Jan 29, 2009 1:08:51 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53764,0,53160,53275,20,'IsCostFrozen',TO_TIMESTAMP('2009-01-29 01:08:47','YYYY-MM-DD HH24:MI:SS'),0,'N','Cost Frozen','U',1,'Indicated that the Standard Cost is frozen','Y','Y','N','N','Cost Frozen',50,TO_TIMESTAMP('2009-01-29 01:08:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:08:51 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53275 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 29, 2009 1:33:56 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2071,0,53160,53276,19,'M_CostType_ID',TO_TIMESTAMP('2009-01-29 01:33:47','YYYY-MM-DD HH24:MI:SS'),0,'Type of Cost (e.g. Current, Plan, Future)','EE01',10,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','Y','Y','N','Cost Type',10,TO_TIMESTAMP('2009-01-29 01:33:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:33:56 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53276 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 29, 2009 1:35:19 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53160,53277,19,'AD_Org_ID',TO_TIMESTAMP('2009-01-29 01:35:09','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','Organization',20,TO_TIMESTAMP('2009-01-29 01:35:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:35:19 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53277 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 29, 2009 1:36:23 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,181,0,53160,53278,19,'C_AcctSchema_ID',TO_TIMESTAMP('2009-01-29 01:36:16','YYYY-MM-DD HH24:MI:SS'),0,'Rules for accounting','EE01',10,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','Y','N','Accounting Schema',30,TO_TIMESTAMP('2009-01-29 01:36:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:36:23 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53278 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 29, 2009 1:37:06 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET AD_Element_ID=2700,Updated=TO_TIMESTAMP('2009-01-29 01:37:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53101
;

-- Jan 29, 2009 1:37:36 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2700,0,53160,53279,19,'M_CostElement_ID',TO_TIMESTAMP('2009-01-29 01:37:35','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Element','EE01',10,'Y','Y','Y','N','Cost Element',40,TO_TIMESTAMP('2009-01-29 01:37:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:37:36 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53279 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 29, 2009 1:40:47 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53199,0,53160,'P',TO_TIMESTAMP('2009-01-29 01:40:43','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','N','Frozen/UnFrozen Cost',TO_TIMESTAMP('2009-01-29 01:40:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:40:47 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53199 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Jan 29, 2009 1:40:47 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53199, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53199)
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 29, 2009 1:40:55 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53199
;

-- Jan 29, 2009 1:42:14 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET SeqNo=20,Updated=TO_TIMESTAMP('2009-01-29 01:42:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53276
;

-- Jan 29, 2009 1:42:19 AM ECT
-- Manufacturing Standard Cost
UPDATE AD_Process_Para SET SeqNo=10,Updated=TO_TIMESTAMP('2009-01-29 01:42:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53277
;

-- Jan 29, 2009 1:47:47 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53160,53280,19,'M_Product_ID',TO_TIMESTAMP('2009-01-29 01:47:46','YYYY-MM-DD HH24:MI:SS'),0,'Product','EE01',10,'Product, Service, Item','Y','Y','N','N','Product',50,TO_TIMESTAMP('2009-01-29 01:47:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:47:47 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53280 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 29, 2009 1:49:28 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,453,0,53160,53281,19,'M_Product_Category_ID',TO_TIMESTAMP('2009-01-29 01:49:26','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','EE01',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','N','N','Product Category',50,TO_TIMESTAMP('2009-01-29 01:49:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 29, 2009 1:49:28 AM ECT
-- Manufacturing Standard Cost
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53281 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;



DROP VIEW T_BOMLINE_COSTS ;
CREATE OR REPLACE VIEW T_BOMLINE_COSTS 
  AS 
    SELECT  t.seqno, t.levelno, t.levels, t.ad_client_id,t.C_AcctSchema_ID,
            t.ad_org_id, t.createdby, t.updatedby, t.updated,
            t.created, t.ad_pinstance_id, t.implosion, 
            t.sel_product_id as m_product_id,t.m_costelement_id, 
            t.currentcostprice,currentcostpricell,
            t.futurecostprice,futurecostpricell,t.iscostfrozen,t.qtybom,
            t.currentcostprice + currentcostpricell as cost,
            t.futurecostprice + futurecostpricell as CostStandard,
            t.m_costtype_ID,t.CostingMethod,
	    bl.isactive, bl.pp_product_bom_id, bl.pp_product_bomline_id, bl.description, 
            bl.iscritical, bl.componenttype, t.m_product_id as tm_product_id, bl.c_uom_id,
            bl.issuemethod, bl.line, bl.m_attributesetinstance_id, bl.scrap,
            bl.validfrom, bl.validto, bl.isqtypercentage
       FROM t_bomline t LEFT OUTER JOIN pp_product_bomline bl 
            ON t.pp_product_bomline_id = bl.pp_product_bomline_id 
;
