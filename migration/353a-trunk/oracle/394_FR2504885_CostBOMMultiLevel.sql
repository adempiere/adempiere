SET SQLBLANKLINES ON
SET DEFINE OFF
-- Jan 11, 2009 4:38:09 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET Description='This Process allow integrate Labor and Overhead Cost to a Manufacturing Workflow',Updated=TO_DATE('2009-01-11 16:38:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53079
;

-- Jan 11, 2009 4:38:09 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53079
;

-- Jan 11, 2009 4:39:43 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET Description='Let create every cost elements defined for a Organization, Accounting Schema, Warehouse, Resource, Cost Type ,Product and Product Attribute Set Instance.',Updated=TO_DATE('2009-01-11 16:39:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53076
;

-- Jan 11, 2009 4:39:43 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53076
;

-- Jan 11, 2009 4:40:04 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET Description='To watch the cost elements for every set of Product, Organization, Accounting Schema, Warehouse, Resource and Cost Type.',Updated=TO_DATE('2009-01-11 16:40:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53077
;

-- Jan 11, 2009 4:40:04 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53077
;

-- Jan 11, 2009 4:40:24 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET Description='This process allow copy a Price from Price list Version to Element Cost.',Updated=TO_DATE('2009-01-11 16:40:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53078
;

-- Jan 11, 2009 4:40:24 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53078
;

-- Jan 11, 2009 4:40:49 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET Description='This report show every cost element to a BOM or Formula.',Updated=TO_DATE('2009-01-11 16:40:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53080
;

-- Jan 11, 2009 4:40:49 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53080
;

-- Jan 11, 2009 4:41:12 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET Description='This Process allow integrate Bill of Material & Formula Cost.',Updated=TO_DATE('2009-01-11 16:41:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53081
;

-- Jan 11, 2009 4:41:12 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53081
;

-- Jan 11, 2009 4:42:53 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Process SET Value='PP_Bill of Material Cost Roll-UP',Updated=TO_DATE('2009-01-11 16:42:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53062
;

-- Jan 11, 2009 4:50:41 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53159,'3','org.eevolution.process.CostBillOfMaterial',TO_DATE('2009-01-11 16:50:39','YYYY-MM-DD HH24:MI:SS'),0,'This report show every cost element to a Multi Level BOM or Formula ','EE01','This report show every cost element to a Multi Level BOM or Formula ','Y','Y','N','N','N','Cost Cost BOM Multi Level Review','Y',0,0,TO_DATE('2009-01-11 16:50:39','YYYY-MM-DD HH24:MI:SS'),0,'PP_Cost Cost BOM Multi Level Review')
;

-- Jan 11, 2009 4:50:41 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53159 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Jan 11, 2009 4:52:19 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53159,53267,19,'M_Product_ID',TO_DATE('2009-01-11 16:52:17','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','Product',10,TO_DATE('2009-01-11 16:52:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 11, 2009 4:52:19 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53267 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 11, 2009 4:53:51 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Process SET Name='Cost BOM Multi Level Review', Value='PP_Cost BOM Multi Level Review',Updated=TO_DATE('2009-01-11 16:53:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53159
;

-- Jan 11, 2009 4:53:51 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53159
;

-- Jan 11, 2009 4:54:07 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET AD_Process_ID=53159, Name='Cost BOM Multi Level Review',Updated=TO_DATE('2009-01-11 16:54:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53080
;

-- Jan 11, 2009 4:54:07 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53080
;

-- Jan 11, 2009 4:55:45 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53198,0,53159,'R',TO_DATE('2009-01-11 16:55:44','YYYY-MM-DD HH24:MI:SS'),0,'This report show every cost element to a BOM or Formula.','EE01','Y','Y','N','N','Cost BOM Multi Level Review back',TO_DATE('2009-01-11 16:55:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 11, 2009 4:55:45 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53198 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;


-- Jan 11, 2009 4:56:54 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET AD_Process_ID=53060, Description='This report show every cost Manufacturing Workflow', Name='Cost Workflow & Process review',Updated=TO_DATE('2009-01-11 16:56:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53080
;

-- Jan 11, 2009 4:56:54 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53080
;

-- Jan 11, 2009 4:57:02 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu SET Name='Cost BOM Multi Level Review',Updated=TO_DATE('2009-01-11 16:57:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53198
;

-- Jan 11, 2009 4:57:02 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53198
;

-- Jan 13, 2009 10:44:21 AM ECT
UPDATE AD_Menu SET Description='This report show every cost element to a BOM & Formula.',Updated=TO_DATE('2009-01-13 10:44:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53198
;

-- Jan 13, 2009 10:44:21 AM ECT
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53198
;

-- Jan 13, 2009 10:44:31 AM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 11, 2009 4:01:34 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56625,2700,0,19,53045,'M_CostElement_ID',TO_DATE('2009-01-11 16:01:24','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Element','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Element',0,TO_DATE('2009-01-11 16:01:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 11, 2009 4:01:34 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56625 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 11, 2009 4:01:39 PM ECT
-- Manufacturing Cost Management
ALTER TABLE T_BOMLine ADD M_CostElement_ID NUMBER(10)
;

-- Jan 11, 2009 4:03:31 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56626,1394,0,37,53045,'CurrentCostPrice',TO_DATE('2009-01-11 16:03:27','YYYY-MM-DD HH24:MI:SS'),0,'The currently used cost price','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Current Cost Price',0,TO_DATE('2009-01-11 16:03:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 11, 2009 4:03:31 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56626 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 11, 2009 4:03:48 PM ECT
-- Manufacturing Cost Management
ALTER TABLE T_BOMLine ADD CurrentCostPrice NUMBER
;

-- Jan 11, 2009 4:04:22 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56627,53296,0,37,53045,'CurrentCostPriceLL',TO_DATE('2009-01-11 16:04:15','YYYY-MM-DD HH24:MI:SS'),0,'Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.','EE01',22,'Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Current Cost Price Lower Level',0,TO_DATE('2009-01-11 16:04:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 11, 2009 4:04:22 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56627 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 11, 2009 4:04:26 PM ECT
-- Manufacturing Cost Management
ALTER TABLE T_BOMLine ADD CurrentCostPriceLL NUMBER
;

-- Jan 11, 2009 4:12:04 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56628,53255,0,29,53045,'QtyBOM',TO_DATE('2009-01-11 16:11:58','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Quantity  use in this BOM','EE01',22,'Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Quantity',0,TO_DATE('2009-01-11 16:11:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 11, 2009 4:12:04 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56628 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 11, 2009 4:13:49 PM ECT
-- Manufacturing Cost Management
ALTER TABLE T_BOMLine ADD QtyBOM NUMBER
;

-- Jan 11, 2009 4:26:50 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56629,2319,0,19,53045,'Cost',TO_DATE('2009-01-11 16:26:31','YYYY-MM-DD HH24:MI:SS'),0,'Cost information','EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost',0,TO_DATE('2009-01-11 16:26:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 11, 2009 4:26:50 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56629 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 11, 2009 4:28:20 PM ECT
-- Manufacturing Cost Management
ALTER TABLE T_BOMLine ADD Cost VARCHAR2(22)
;

-- Jan 11, 2009 4:29:18 PM ECT
-- Manufacturing Cost Management
DELETE AD_TreeNodeMM WHERE AD_Tree_ID=10 AND Node_ID NOT IN (SELECT AD_Menu_ID FROM AD_Menu WHERE AD_Client_ID=0)
;

-- Jan 12, 2009 11:57:36 AM ECT
-- Standard Cost for Manufacturing
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=53635
;

-- Jan 12, 2009 11:57:37 AM ECT
-- Standard Cost for Manufacturing
DELETE FROM AD_Field WHERE AD_Field_ID=53635
;

-- Jan 12, 2009 11:58:36 AM ECT
-- Standard Cost for Manufacturing
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=53410
;

-- Jan 12, 2009 11:58:36 AM ECT
-- Standard Cost for Manufacturing
DELETE FROM AD_Column WHERE AD_Column_ID=53410
;

-- Jan 12, 2009 7:19:02 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53161,'3','N',TO_DATE('2009-01-12 19:18:57','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','Y','N','N','N',0,'Cost BOM Line','L','RV_PP_Cost_BOMLine ',TO_DATE('2009-01-12 19:18:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 12, 2009 7:19:02 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53161 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Jan 12, 2009 7:19:06 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53252,TO_DATE('2009-01-12 19:19:02','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table RV_PP_Cost_BOMLine ',1,'Y','N','Y','Y','RV_PP_Cost_BOMLine ','N',1000000,TO_DATE('2009-01-12 19:19:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 12, 2009 7:23:34 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_Table SET TableName='RV_PP_Cost_BOMLine',Updated=TO_DATE('2009-01-12 19:23:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53161
;

-- Jan 12, 2009 7:25:10 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53253,TO_DATE('2009-01-12 19:23:34','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table RV_PP_Cost_BOMLine',1,'Y','N','Y','Y','RV_PP_Cost_BOMLine','N',1000000,TO_DATE('2009-01-12 19:23:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 12, 2009 7:26:23 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56630,566,0,11,53161,'SeqNo',TO_DATE('2009-01-12 19:25:29','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first','EE01',10,'The Sequence indicates the order of records','Y','N','N','N','N','N','N','N','N','Y','Sequence',TO_DATE('2009-01-12 19:25:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:26:23 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56630 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:26:35 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56631,1982,0,11,53161,'LevelNo',TO_DATE('2009-01-12 19:26:23','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Level no',TO_DATE('2009-01-12 19:26:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:26:35 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56631 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:26:41 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56632,53318,0,10,53161,'Levels',TO_DATE('2009-01-12 19:26:35','YYYY-MM-DD HH24:MI:SS'),0,'EE01',250,'Y','N','N','N','N','N','N','N','N','Y','Levels',TO_DATE('2009-01-12 19:26:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:26:41 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56632 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:26:48 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56633,102,0,19,53161,'AD_Client_ID',TO_DATE('2009-01-12 19:26:41','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',TO_DATE('2009-01-12 19:26:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:26:48 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56633 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:26:51 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56634,113,0,19,53161,'AD_Org_ID',TO_DATE('2009-01-12 19:26:48','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',TO_DATE('2009-01-12 19:26:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:26:51 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56634 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:26:57 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56635,246,0,18,110,53161,'CreatedBy',TO_DATE('2009-01-12 19:26:51','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',TO_DATE('2009-01-12 19:26:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:26:57 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56635 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:26:59 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56636,608,0,18,110,53161,'UpdatedBy',TO_DATE('2009-01-12 19:26:57','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',TO_DATE('2009-01-12 19:26:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:26:59 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56636 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:27:03 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56637,607,0,16,53161,'Updated',TO_DATE('2009-01-12 19:26:59','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',TO_DATE('2009-01-12 19:26:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:27:03 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56637 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:27:08 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56638,245,0,16,53161,'Created',TO_DATE('2009-01-12 19:27:03','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',TO_DATE('2009-01-12 19:27:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:27:08 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56638 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:27:21 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56639,114,0,19,53161,'AD_PInstance_ID',TO_DATE('2009-01-12 19:27:08','YYYY-MM-DD HH24:MI:SS'),0,'Instance of the process','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Process Instance',TO_DATE('2009-01-12 19:27:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:27:21 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56639 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:27:31 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56640,53466,0,20,53161,'Implosion',TO_DATE('2009-01-12 19:27:22','YYYY-MM-DD HH24:MI:SS'),0,'Implosion of a Bill of Materials refers to finding all the BOM''''s in which a component is used.','EE01',1,'Commonly called a Where-Used report.','Y','N','N','N','N','N','N','N','N','Y','Implosion',TO_DATE('2009-01-12 19:27:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:27:31 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56640 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:27:34 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56641,454,0,19,53161,'M_Product_ID',TO_DATE('2009-01-12 19:27:31','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','N','Y','Product',TO_DATE('2009-01-12 19:27:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:27:34 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56641 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:27:45 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56642,2700,0,19,53161,'M_CostElement_ID',TO_DATE('2009-01-12 19:27:34','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Element','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Cost Element',TO_DATE('2009-01-12 19:27:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:27:45 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56642 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:27:55 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56643,1394,0,22,53161,'CurrentCostPrice',TO_DATE('2009-01-12 19:27:45','YYYY-MM-DD HH24:MI:SS'),0,'The currently used cost price','EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Current Cost Price',TO_DATE('2009-01-12 19:27:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:27:55 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56643 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:28:00 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56644,53296,0,22,53161,'CurrentCostPriceLL',TO_DATE('2009-01-12 19:27:55','YYYY-MM-DD HH24:MI:SS'),0,'Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.','EE01',131089,'Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
','Y','N','N','N','N','N','N','N','N','Y','Current Cost Price Lower Level',TO_DATE('2009-01-12 19:27:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:28:00 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56644 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:28:36 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56645,53255,0,29,53161,'QtyBOM',TO_DATE('2009-01-12 19:28:00','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Quantity  use in this BOM','EE01',131089,'Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','N','N','N','N','N','N','N','N','Y','Quantity',TO_DATE('2009-01-12 19:28:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:28:36 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56645 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:28:51 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56646,2319,0,22,53161,'Cost',TO_DATE('2009-01-12 19:28:36','YYYY-MM-DD HH24:MI:SS'),0,'Cost information','EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Cost',TO_DATE('2009-01-12 19:28:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:28:51 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56646 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:29:16 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56647,348,0,20,53161,'IsActive',TO_DATE('2009-01-12 19:28:51','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','Y','Active',TO_DATE('2009-01-12 19:28:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:29:16 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56647 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:29:36 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56648,53245,0,19,53161,'PP_Product_BOM_ID',TO_DATE('2009-01-12 19:29:16','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','BOM & Formula',TO_DATE('2009-01-12 19:29:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:29:36 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56648 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:29:44 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56649,53254,0,19,53161,'PP_Product_BOMLine_ID',TO_DATE('2009-01-12 19:29:36','YYYY-MM-DD HH24:MI:SS'),0,'BOM Line','EE01',10,'The BOM Line is a unique identifier for a BOM line in an BOM.','Y','N','N','N','N','N','N','N','N','Y','BOM Line',TO_DATE('2009-01-12 19:29:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:29:44 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56649 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:29:46 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56650,275,0,10,53161,'Description',TO_DATE('2009-01-12 19:29:44','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','Y','Description',TO_DATE('2009-01-12 19:29:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:29:46 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56650 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:29:52 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56651,53251,0,20,53161,'IsCritical',TO_DATE('2009-01-12 19:29:46','YYYY-MM-DD HH24:MI:SS'),0,'Indicate that a Manufacturing Order can not begin without have this component','EE01',1,'Indicate that a Manufacturing Order can not begin without have this component','Y','N','N','N','N','N','N','N','N','Y','Is Critical Component',TO_DATE('2009-01-12 19:29:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:29:52 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56651 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:30:07 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56652,53249,0,17,53161,'ComponentType',TO_DATE('2009-01-12 19:29:52','YYYY-MM-DD HH24:MI:SS'),0,'Component Type for a Bill of Material or Formula','EE01',2,'The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
','Y','N','N','N','N','N','N','N','N','Y','Component Type',TO_DATE('2009-01-12 19:29:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:30:07 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56652 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:30:16 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56653,53465,0,19,53161,'TM_Product_ID',TO_DATE('2009-01-12 19:30:07','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Product',TO_DATE('2009-01-12 19:30:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:30:16 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56653 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:30:44 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56654,215,0,19,53161,'C_UOM_ID',TO_DATE('2009-01-12 19:30:16','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','N','Y','UOM',TO_DATE('2009-01-12 19:30:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:30:44 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56654 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:30:51 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56655,53253,0,20,53161,'IssueMethod',TO_DATE('2009-01-12 19:30:44','YYYY-MM-DD HH24:MI:SS'),0,'There are two methods for issue the components to Manufacturing Order','EE01',1,'Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.','Y','N','N','N','N','N','N','N','N','Y','Issue Method',TO_DATE('2009-01-12 19:30:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:30:51 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56655 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:30:56 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56656,439,0,11,53161,'Line',TO_DATE('2009-01-12 19:30:51','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE01',10,'Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','N','N','N','N','N','N','N','N','Y','Line No',TO_DATE('2009-01-12 19:30:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:30:56 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56656 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:31:04 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56657,2019,0,35,53161,'M_AttributeSetInstance_ID',TO_DATE('2009-01-12 19:30:56','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','N','Y','Attribute Set Instance',TO_DATE('2009-01-12 19:30:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:31:04 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56657 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:31:08 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56658,53257,0,22,53161,'Scrap',TO_DATE('2009-01-12 19:31:04','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the % Scrap  for calculate the Scrap Quantity','EE01',131089,'Scrap is useful to determinate a rigth Standard Cost and management a good supply.','Y','N','N','N','N','N','N','N','N','Y','% Scrap',TO_DATE('2009-01-12 19:31:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:31:08 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56658 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:31:21 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56659,617,0,16,53161,'ValidFrom',TO_DATE('2009-01-12 19:31:08','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',29,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','N','Y','Valid from',TO_DATE('2009-01-12 19:31:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:31:21 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56659 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:31:28 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56660,618,0,16,53161,'ValidTo',TO_DATE('2009-01-12 19:31:21','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE01',29,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','N','Y','Valid to',TO_DATE('2009-01-12 19:31:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:31:28 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56660 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:31:33 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56661,53252,0,29,53161,'IsQtyPercentage',TO_DATE('2009-01-12 19:31:28','YYYY-MM-DD HH24:MI:SS'),0,'Indicate that this component is based in % Quantity','EE01',1,'Indicate that this component is based in % Quantity','Y','N','N','N','N','N','N','N','N','Y','Is Qty Percentage',TO_DATE('2009-01-12 19:31:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 12, 2009 7:31:33 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56661 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 12, 2009 7:32:26 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_Table SET IsDeleteable='N', IsView='Y',Updated=TO_DATE('2009-01-12 19:32:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53161
;

-- Jan 12, 2009 7:34:26 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53025,53161,TO_DATE('2009-01-12 19:34:14','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','RV_PP_Product_BOMLine',TO_DATE('2009-01-12 19:34:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 12, 2009 7:34:36 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_ReportView SET Name='RV_PP_Cost_BOMLine',Updated=TO_DATE('2009-01-12 19:34:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_ReportView_ID=53025
;

-- Jan 12, 2009 7:41:06 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_DATE('2009-01-12 19:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53267
;

-- Jan 12, 2009 7:42:51 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53159,53268,19,'AD_Org_ID',TO_DATE('2009-01-12 19:42:30','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','Organization',40,TO_DATE('2009-01-12 19:42:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 12, 2009 7:42:51 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53268 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 12, 2009 7:43:41 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_Process_Para SET SeqNo=10,Updated=TO_DATE('2009-01-12 19:43:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53268
;

-- Jan 12, 2009 7:45:06 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,181,0,53159,53269,19,'C_AcctSchema_ID',TO_DATE('2009-01-12 19:45:01','YYYY-MM-DD HH24:MI:SS'),0,'Rules for accounting','EE01',10,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','Y','N','Accounting Schema',20,TO_DATE('2009-01-12 19:45:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 12, 2009 7:45:06 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53269 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jan 12, 2009 7:45:30 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_DATE('2009-01-12 19:45:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53267
;

-- Jan 12, 2009 11:54:47 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_ReportView_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,Description,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,130,50038,100,100,53025,53161,'N',TO_DATE('2009-01-12 23:54:45','YYYY-MM-DD HH24:MI:SS'),0,'RV_PP_Cost_BOMLine',0,0,'Y','N','N','Y','Y','RV_PP_Cost_BOMLine',TO_DATE('2009-01-12 23:54:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 12, 2009 11:55:09 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormat SET AD_PrintColor_ID=100, AD_PrintFont_ID=130, AD_PrintPaper_ID=100, AD_Table_ID=53161, Name='RV_PP_Cost_BOMLine_2',Updated=TO_DATE('2009-01-12 23:55:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50038
;

-- Jan 12, 2009 11:55:11 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56658,0,50994,50038,0,TO_DATE('2009-01-12 23:55:09','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'% Scrap','C','F','% Scrap',1,'N',0,TO_DATE('2009-01-12 23:55:09','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:12 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50994 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:12 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56658) WHERE AD_PrintFormatItem_ID = 50994 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56658 AND trl.AD_PrintFormatItem_ID = 50994) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:13 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56647,0,50995,50038,0,TO_DATE('2009-01-12 23:55:12','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Active','C','F','Active',0,'N',0,TO_DATE('2009-01-12 23:55:12','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:13 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50995 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:13 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56647) WHERE AD_PrintFormatItem_ID = 50995 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56647 AND trl.AD_PrintFormatItem_ID = 50995) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:14 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56657,0,50996,50038,0,TO_DATE('2009-01-12 23:55:13','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Attribute Set Instance','C','F','Attribute Set Instance',3,'N',0,TO_DATE('2009-01-12 23:55:13','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:14 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50996 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:14 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56657) WHERE AD_PrintFormatItem_ID = 50996 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56657 AND trl.AD_PrintFormatItem_ID = 50996) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:15 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56648,0,50997,50038,0,TO_DATE('2009-01-12 23:55:14','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'BOM & Formula','C','F','BOM & Formula',4,'N',0,TO_DATE('2009-01-12 23:55:14','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:15 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50997 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:15 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56648) WHERE AD_PrintFormatItem_ID = 50997 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56648 AND trl.AD_PrintFormatItem_ID = 50997) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:16 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56649,0,50998,50038,0,TO_DATE('2009-01-12 23:55:15','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'BOM Line','C','F','BOM Line',5,'N',0,TO_DATE('2009-01-12 23:55:15','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:16 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50998 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:16 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56649) WHERE AD_PrintFormatItem_ID = 50998 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56649 AND trl.AD_PrintFormatItem_ID = 50998) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:17 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56633,0,50999,50038,0,TO_DATE('2009-01-12 23:55:16','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Client','C','F','Client',0,'N',0,TO_DATE('2009-01-12 23:55:16','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:17 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50999 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:17 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56633) WHERE AD_PrintFormatItem_ID = 50999 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56633 AND trl.AD_PrintFormatItem_ID = 50999) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:18 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56652,0,51000,50038,0,TO_DATE('2009-01-12 23:55:17','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Component Type','C','F','Component Type',7,'N',0,TO_DATE('2009-01-12 23:55:17','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:18 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51000 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:18 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56652) WHERE AD_PrintFormatItem_ID = 51000 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56652 AND trl.AD_PrintFormatItem_ID = 51000) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:19 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56646,0,51001,50038,0,TO_DATE('2009-01-12 23:55:18','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Cost','C','F','Cost',8,'N',0,TO_DATE('2009-01-12 23:55:18','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:19 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51001 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:19 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56646) WHERE AD_PrintFormatItem_ID = 51001 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56646 AND trl.AD_PrintFormatItem_ID = 51001) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:20 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56642,0,51002,50038,0,TO_DATE('2009-01-12 23:55:19','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Cost Element','C','F','Cost Element',9,'N',0,TO_DATE('2009-01-12 23:55:19','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:20 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51002 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:20 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56642) WHERE AD_PrintFormatItem_ID = 51002 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56642 AND trl.AD_PrintFormatItem_ID = 51002) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:20 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56638,0,51003,50038,0,TO_DATE('2009-01-12 23:55:20','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Created','C','F','Created',0,'N',0,TO_DATE('2009-01-12 23:55:20','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:20 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51003 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:20 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56638) WHERE AD_PrintFormatItem_ID = 51003 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56638 AND trl.AD_PrintFormatItem_ID = 51003) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:21 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56635,0,51004,50038,0,TO_DATE('2009-01-12 23:55:21','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Created By','C','F','Created By',0,'N',0,TO_DATE('2009-01-12 23:55:21','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:21 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51004 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:21 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56635) WHERE AD_PrintFormatItem_ID = 51004 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56635 AND trl.AD_PrintFormatItem_ID = 51004) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:23 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56643,0,51005,50038,0,TO_DATE('2009-01-12 23:55:22','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Current Cost Price','C','F','Current Cost Price',12,'N',0,TO_DATE('2009-01-12 23:55:22','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:23 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51005 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:23 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56643) WHERE AD_PrintFormatItem_ID = 51005 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56643 AND trl.AD_PrintFormatItem_ID = 51005) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:23 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56644,0,51006,50038,0,TO_DATE('2009-01-12 23:55:23','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Current Cost Price Lower Level','C','F','Current Cost Price Lower Level',13,'N',0,TO_DATE('2009-01-12 23:55:23','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:23 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51006 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:23 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56644) WHERE AD_PrintFormatItem_ID = 51006 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56644 AND trl.AD_PrintFormatItem_ID = 51006) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:24 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56650,0,51007,50038,0,TO_DATE('2009-01-12 23:55:23','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Description','C','F','Description',14,'N',0,TO_DATE('2009-01-12 23:55:23','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:24 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51007 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:24 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56650) WHERE AD_PrintFormatItem_ID = 51007 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56650 AND trl.AD_PrintFormatItem_ID = 51007) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:25 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56640,0,51008,50038,0,TO_DATE('2009-01-12 23:55:24','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Implosion','C','F','Implosion',15,'N',0,TO_DATE('2009-01-12 23:55:24','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:25 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51008 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:25 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56640) WHERE AD_PrintFormatItem_ID = 51008 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56640 AND trl.AD_PrintFormatItem_ID = 51008) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:26 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56651,0,51009,50038,0,TO_DATE('2009-01-12 23:55:25','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Is Critical Component','C','F','Is Critical Component',16,'N',0,TO_DATE('2009-01-12 23:55:25','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:26 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51009 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:26 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56651) WHERE AD_PrintFormatItem_ID = 51009 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56651 AND trl.AD_PrintFormatItem_ID = 51009) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:27 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56661,0,51010,50038,0,TO_DATE('2009-01-12 23:55:26','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Is Qty Percentage','C','F','Is Qty Percentage',17,'N',0,TO_DATE('2009-01-12 23:55:26','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:27 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51010 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:27 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56661) WHERE AD_PrintFormatItem_ID = 51010 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56661 AND trl.AD_PrintFormatItem_ID = 51010) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:28 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56655,0,51011,50038,0,TO_DATE('2009-01-12 23:55:27','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Issue Method','C','F','Issue Method',18,'N',0,TO_DATE('2009-01-12 23:55:27','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:28 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51011 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:28 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56655) WHERE AD_PrintFormatItem_ID = 51011 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56655 AND trl.AD_PrintFormatItem_ID = 51011) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:29 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56631,0,51012,50038,0,TO_DATE('2009-01-12 23:55:28','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Level no','C','F','Level no',19,'N',0,TO_DATE('2009-01-12 23:55:28','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:29 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51012 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:29 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56631) WHERE AD_PrintFormatItem_ID = 51012 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56631 AND trl.AD_PrintFormatItem_ID = 51012) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:30 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56632,0,51013,50038,0,TO_DATE('2009-01-12 23:55:29','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Levels','C','F','Levels',20,'N',0,TO_DATE('2009-01-12 23:55:29','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:30 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51013 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:30 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56632) WHERE AD_PrintFormatItem_ID = 51013 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56632 AND trl.AD_PrintFormatItem_ID = 51013) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:31 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56656,0,51014,50038,0,TO_DATE('2009-01-12 23:55:30','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Line No','C','F','Line No',21,'N',0,TO_DATE('2009-01-12 23:55:30','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:31 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51014 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:31 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56656) WHERE AD_PrintFormatItem_ID = 51014 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56656 AND trl.AD_PrintFormatItem_ID = 51014) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:32 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56634,0,51015,50038,0,TO_DATE('2009-01-12 23:55:31','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organization','C','F','Organization',0,'N',0,TO_DATE('2009-01-12 23:55:31','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:32 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51015 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:32 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56634) WHERE AD_PrintFormatItem_ID = 51015 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56634 AND trl.AD_PrintFormatItem_ID = 51015) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:33 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56639,0,51016,50038,0,TO_DATE('2009-01-12 23:55:32','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Process Instance','C','F','Process Instance',23,'N',0,TO_DATE('2009-01-12 23:55:32','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:33 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51016 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:33 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56639) WHERE AD_PrintFormatItem_ID = 51016 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56639 AND trl.AD_PrintFormatItem_ID = 51016) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:34 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56653,0,51017,50038,0,TO_DATE('2009-01-12 23:55:33','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product','C','F','Product',24,'N',0,TO_DATE('2009-01-12 23:55:33','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:34 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51017 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:34 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56653) WHERE AD_PrintFormatItem_ID = 51017 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56653 AND trl.AD_PrintFormatItem_ID = 51017) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:35 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56641,0,51018,50038,0,TO_DATE('2009-01-12 23:55:34','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product','C','F','Product',25,'N',0,TO_DATE('2009-01-12 23:55:34','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:35 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51018 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:35 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56641) WHERE AD_PrintFormatItem_ID = 51018 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56641 AND trl.AD_PrintFormatItem_ID = 51018) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:36 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56645,0,51019,50038,0,TO_DATE('2009-01-12 23:55:35','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Quantity','C','F','Quantity',26,'N',0,TO_DATE('2009-01-12 23:55:35','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:36 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51019 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:36 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56645) WHERE AD_PrintFormatItem_ID = 51019 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56645 AND trl.AD_PrintFormatItem_ID = 51019) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:39 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56630,0,51020,50038,0,TO_DATE('2009-01-12 23:55:36','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Sequence','C','F','Sequence',27,'N',0,TO_DATE('2009-01-12 23:55:36','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:39 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51020 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:39 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56630) WHERE AD_PrintFormatItem_ID = 51020 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56630 AND trl.AD_PrintFormatItem_ID = 51020) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:41 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56654,0,51021,50038,0,TO_DATE('2009-01-12 23:55:39','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'UOM','C','F','UOM',28,'N',0,TO_DATE('2009-01-12 23:55:39','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:41 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51021 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:41 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56654) WHERE AD_PrintFormatItem_ID = 51021 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56654 AND trl.AD_PrintFormatItem_ID = 51021) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:42 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56637,0,51022,50038,0,TO_DATE('2009-01-12 23:55:41','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Updated','C','F','Updated',0,'N',0,TO_DATE('2009-01-12 23:55:41','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:42 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51022 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:42 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56637) WHERE AD_PrintFormatItem_ID = 51022 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56637 AND trl.AD_PrintFormatItem_ID = 51022) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:43 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56636,0,51023,50038,0,TO_DATE('2009-01-12 23:55:42','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Updated By','C','F','Updated By',0,'N',0,TO_DATE('2009-01-12 23:55:42','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:43 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51023 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:43 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56636) WHERE AD_PrintFormatItem_ID = 51023 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56636 AND trl.AD_PrintFormatItem_ID = 51023) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:44 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56659,0,51024,50038,0,TO_DATE('2009-01-12 23:55:43','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Valid from','C','F','Valid from',31,'N',0,TO_DATE('2009-01-12 23:55:43','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:44 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51024 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:44 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56659) WHERE AD_PrintFormatItem_ID = 51024 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56659 AND trl.AD_PrintFormatItem_ID = 51024) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:55:45 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56660,0,51025,50038,0,TO_DATE('2009-01-12 23:55:44','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Valid to','C','F','Valid to',32,'N',0,TO_DATE('2009-01-12 23:55:44','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 12, 2009 11:55:45 PM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51025 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 12, 2009 11:55:45 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56660) WHERE AD_PrintFormatItem_ID = 51025 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56660 AND trl.AD_PrintFormatItem_ID = 51025) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Jan 12, 2009 11:56:34 PM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormat SET Description='Cost BOM Multi Level Review', Name='Cost BOM Multi Level Review',Updated=TO_DATE('2009-01-12 23:56:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50038
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50996
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50998
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51007
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51008
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51009
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51010
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51011
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51012
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51014
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51016
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51020
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51024
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51025
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=10,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51013
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51018
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51000
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50997
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51019
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51021
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50994
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51002
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 12:01:26 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51001
;

-- Jan 13, 2009 12:01:38 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SortNo=10,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51012
;

-- Jan 13, 2009 12:03:15 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51018
;

-- Jan 13, 2009 12:03:15 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 12:03:36 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='N', IsGroupBy='N', IsPageBreak='N', Name='Parent', PrintName='Parent', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 00:03:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51018
;

-- Jan 13, 2009 12:03:36 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51018
;

-- Jan 13, 2009 12:03:48 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Components', PrintName='Components', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 00:03:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 12:03:48 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 12:03:52 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='N', IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 00:03:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 12:04:37 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='N', IsGroupBy='N', IsPageBreak='N', Name='Cost This Level', PrintName='Cost This Level', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 00:04:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 12:04:37 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 12:04:55 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='N', IsGroupBy='N', IsPageBreak='N', Name='Cost Lower Cost', PrintName='Cost Lower Cost', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 00:04:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 12:04:55 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51007
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51000
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50997
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51019
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51021
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50994
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51002
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 12:05:28 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51001
;

-- Jan 13, 2009 12:06:39 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_Process_Para SET AD_Reference_ID=30,Updated=TO_DATE('2009-01-13 00:06:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53267
;

-- Jan 13, 2009 12:07:31 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SortNo=20,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51002
;

-- Jan 13, 2009 12:13:25 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=162,Updated=TO_DATE('2009-01-13 00:13:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56653
;

-- Jan 13, 2009 12:20:21 AM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56662,181,0,19,53161,'C_AcctSchema_ID',TO_DATE('2009-01-13 00:20:19','YYYY-MM-DD HH24:MI:SS'),0,'Rules for accounting','EE01',22,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Accounting Schema',0,TO_DATE('2009-01-13 00:20:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 13, 2009 12:20:21 AM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56662 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 13, 2009 12:21:49 AM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56663,181,0,19,53045,'C_AcctSchema_ID',TO_DATE('2009-01-13 00:21:48','YYYY-MM-DD HH24:MI:SS'),0,'Rules for accounting','EE01',22,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Accounting Schema',0,TO_DATE('2009-01-13 00:21:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 13, 2009 12:21:49 AM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56663 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 13, 2009 12:21:58 AM ECT
-- Cost BOM Roll-UP
ALTER TABLE T_BOMLine ADD C_AcctSchema_ID NUMBER(10)
;

-- Jan 13, 2009 12:22:57 AM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56662,0,100,130,51026,50038,0,0,TO_DATE('2009-01-13 00:22:55','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Acct Schema','C','F',20,140,'N',0,TO_DATE('2009-01-13 00:22:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jan 13, 2009 12:22:57 AM ECT
-- Cost BOM Roll-UP
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51026 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jan 13, 2009 12:23:11 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51026
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51018
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51007
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51000
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50997
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51019
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51021
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50994
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51002
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 12:24:40 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51001
;

-- Jan 13, 2009 12:25:02 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsCentrallyMaintained='Y', IsGroupBy='N', IsPageBreak='N', Name='Product', PrintName='Product', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 00:25:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 12:25:02 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=51007
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51000
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50997
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51019
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51021
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50994
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51002
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 12:54:12 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51001
;

-- Jan 13, 2009 12:54:31 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormat SET AD_PrintPaper_ID=102,Updated=TO_DATE('2009-01-13 00:54:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50038
;

-- Jan 13, 2009 1:01:01 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SortNo=0,IsOrderBy='N' WHERE AD_PrintFormatItem_ID=51012
;

-- Jan 13, 2009 1:01:01 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SortNo=10,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51020
;

-- Jan 13, 2009 1:01:27 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='Y', IsOrderBy='Y', XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:01:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 1:01:37 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:01:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 1:01:39 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:01:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 1:01:42 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:01:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51001
;

-- Jan 13, 2009 1:06:56 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50997
;

-- Jan 13, 2009 1:06:56 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51019
;

-- Jan 13, 2009 1:06:56 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51021
;

-- Jan 13, 2009 1:06:56 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50994
;

-- Jan 13, 2009 1:06:56 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51002
;

-- Jan 13, 2009 1:06:56 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 1:06:56 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 1:06:56 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51001
;

-- Jan 13, 2009 1:28:59 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Cost TL', PrintName='Cost TL', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:28:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 1:28:59 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51005
;

-- Jan 13, 2009 1:29:10 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', Name='Cost LL', PrintName='Cost LL', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:29:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 1:29:10 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51006
;

-- Jan 13, 2009 1:29:34 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', MaxWidth=80, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:29:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 1:29:44 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsPageBreak='N', MaxWidth=30, SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:29:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51000
;

-- Jan 13, 2009 1:45:31 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=60, Name='Type', PrintName='Type', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:45:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51000
;

-- Jan 13, 2009 1:45:31 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51000
;

-- Jan 13, 2009 1:45:49 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsPageBreak='N', MaxWidth=50, SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51019
;

-- Jan 13, 2009 1:50:07 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET MaxWidth=100, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:50:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51017
;

-- Jan 13, 2009 1:50:29 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', MaxWidth=50, SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:50:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51000
;

-- Jan 13, 2009 1:50:53 AM ECT
-- Cost BOM Roll-UP
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsPageBreak='N', MaxWidth=40, SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2009-01-13 01:50:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50994
;
-- Jan 13, 2009 12:11:33 PM ECT
DELETE AD_TreeNodeMM WHERE AD_Tree_ID=10 AND Node_ID NOT IN (SELECT AD_Menu_ID FROM AD_Menu WHERE AD_Client_ID=0)
;

-- Jan 13, 2009 12:11:33 PM ECT
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID,AD_Tree_ID,Created,CreatedBy,IsActive,Node_ID,SeqNo,Updated,UpdatedBy) VALUES (0,0,10,TO_DATE('2009-01-13 12:11:33','YYYY-MM-DD HH24:MI:SS'),100,'Y',53132,0,TO_DATE('2009-01-13 12:11:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 13, 2009 12:11:33 PM ECT
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID,AD_Tree_ID,Created,CreatedBy,IsActive,Node_ID,SeqNo,Updated,UpdatedBy) VALUES (0,0,10,TO_DATE('2009-01-13 12:11:33','YYYY-MM-DD HH24:MI:SS'),100,'Y',53198,0,TO_DATE('2009-01-13 12:11:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 13, 2009 12:12:50 PM ECT
DELETE FROM AD_Window_Access WHERE AD_Role_ID=50002
;

-- Jan 13, 2009 12:12:50 PM ECT
INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID, 50002,0,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID)AND tt.AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:50 PM ECT
DELETE FROM AD_Process_Access WHERE AD_Role_ID=50002
;

-- Jan 13, 2009 12:12:50 PM ECT
INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID, 50002,0,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Process p WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:50 PM ECT
DELETE FROM AD_Form_Access WHERE AD_Role_ID=50002
;

-- Jan 13, 2009 12:12:50 PM ECT
INSERT INTO AD_Form_Access (AD_Form_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT f.AD_Form_ID, 50002,0,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Form f WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:50 PM ECT
DELETE FROM AD_WorkFlow_Access WHERE AD_Role_ID=50002
;

-- Jan 13, 2009 12:12:50 PM ECT
INSERT INTO AD_WorkFlow_Access (AD_WorkFlow_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT w.AD_WorkFlow_ID, 50002,0,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_WorkFlow w WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:50 PM ECT
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=50002
;

-- Jan 13, 2009 12:12:50 PM ECT
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 0,0,'Y', SysDate,100, SysDate,100, doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=50002) )
;

-- Jan 13, 2009 12:12:50 PM ECT
DELETE FROM AD_Window_Access WHERE AD_Role_ID=50001
;

-- Jan 13, 2009 12:12:50 PM ECT
INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID, 50001,0,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID)AND tt.AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Process_Access WHERE AD_Role_ID=50001
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID, 50001,0,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Process p WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Form_Access WHERE AD_Role_ID=50001
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Form_Access (AD_Form_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT f.AD_Form_ID, 50001,0,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Form f WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_WorkFlow_Access WHERE AD_Role_ID=50001
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_WorkFlow_Access (AD_WorkFlow_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT w.AD_WorkFlow_ID, 50001,0,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_WorkFlow w WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=50001
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 0,0,'Y', SysDate,100, SysDate,100, doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=50001) )
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Window_Access WHERE AD_Role_ID=0
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID, 0,0,0,'Y', SysDate,0, SysDate,0,'Y' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID)AND tt.AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Process_Access WHERE AD_Role_ID=0
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID, 0,0,0,'Y', SysDate,0, SysDate,0,'Y' FROM AD_Process p WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Form_Access WHERE AD_Role_ID=0
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Form_Access (AD_Form_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT f.AD_Form_ID, 0,0,0,'Y', SysDate,0, SysDate,0,'Y' FROM AD_Form f WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_WorkFlow_Access WHERE AD_Role_ID=0
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_WorkFlow_Access (AD_WorkFlow_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT w.AD_WorkFlow_ID, 0,0,0,'Y', SysDate,0, SysDate,0,'Y' FROM AD_WorkFlow w WHERE AccessLevel IN ('4','7','6')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=0
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 0,0,'Y', SysDate,0, SysDate,0, doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=0) )
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Window_Access WHERE AD_Role_ID=102
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID, 102,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID)AND tt.AccessLevel IN ('7','6','3','2','1')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Process_Access WHERE AD_Role_ID=102
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID, 102,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Process p WHERE AccessLevel IN ('7','6','3','2','1')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Form_Access WHERE AD_Role_ID=102
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Form_Access (AD_Form_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT f.AD_Form_ID, 102,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Form f WHERE AccessLevel IN ('7','6','3','2','1')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_WorkFlow_Access WHERE AD_Role_ID=102
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_WorkFlow_Access (AD_WorkFlow_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT w.AD_WorkFlow_ID, 102,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_WorkFlow w WHERE AccessLevel IN ('7','6','3','2','1')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=102
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', SysDate,100, SysDate,100, doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=102) )
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Window_Access WHERE AD_Role_ID=103
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID, 103,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID)AND tt.AccessLevel IN ('3','1','7') AND w.Name NOT LIKE '%(all)%'
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Process_Access WHERE AD_Role_ID=103
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID, 103,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Process p WHERE AccessLevel IN ('3','1','7')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Form_Access WHERE AD_Role_ID=103
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Form_Access (AD_Form_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT f.AD_Form_ID, 103,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Form f WHERE AccessLevel IN ('3','1','7')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_WorkFlow_Access WHERE AD_Role_ID=103
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_WorkFlow_Access (AD_WorkFlow_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT w.AD_WorkFlow_ID, 103,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_WorkFlow w WHERE AccessLevel IN ('3','1','7')
;

-- Jan 13, 2009 12:12:51 PM ECT
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=103
;

-- Jan 13, 2009 12:12:51 PM ECT
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', SysDate,100, SysDate,100, doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=103) )
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:18:21 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:18:22 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:18:22 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:18:22 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:18:22 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:18:22 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:18:22 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:18:22 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:18:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:18:41 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53080
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:19:25 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53080
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:19:33 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53080
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:19:42 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53080
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:19:57 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53080
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:20:03 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53075
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53076
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53078
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53077
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53079
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53080
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53081
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53198
;

-- Jan 13, 2009 12:20:10 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=53074, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53082
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jan 13, 2009 12:20:26 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jan 13, 2009 12:20:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jan 13, 2009 12:20:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jan 13, 2009 12:20:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=346
;

-- Jan 13, 2009 12:20:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jan 13, 2009 12:20:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=193
;

-- Jan 13, 2009 12:20:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=180
;

-- Jan 13, 2009 12:20:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=494
;

-- Jan 13, 2009 12:20:27 PM ECT
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=444
;

CREATE OR REPLACE VIEW rv_pp_cost_bomline 
  AS 
    SELECT  t.seqno, t.levelno, t.levels, t.ad_client_id,t.C_AcctSchema_ID,
            t.ad_org_id, t.createdby, t.updatedby, t.updated,
            t.created, t.ad_pinstance_id, t.implosion, t.sel_product_id as m_product_id,t.m_costelement_id, t.currentcostprice,currentcostpricell,t.qtybom, t.currentcostprice + currentcostpricell as cost,
            bl.isactive, bl.pp_product_bom_id, bl.pp_product_bomline_id, bl.description, 
            bl.iscritical, bl.componenttype, t.m_product_id as tm_product_id, bl.c_uom_id,
            bl.issuemethod, bl.line, bl.m_attributesetinstance_id, bl.scrap,
            bl.validfrom, bl.validto, bl.isqtypercentage
       FROM t_bomline t LEFT OUTER JOIN pp_product_bomline bl 
            ON t.pp_product_bomline_id = bl.pp_product_bomline_id 
;
