-- May 13, 2008 10:18:11 AM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
ALTER TABLE T_BOMLine ADD Implotion CHAR(1) CHECK (Implotion IN ('Y','N'))
;

-- May 13, 2008 10:18:38 AM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
ALTER TABLE T_BOMLine ADD Sel_Product_ID NUMBER(10) NOT NULL
;

-- May 12, 2008 1:58:58 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
DROP VIEW "RV_PP_PRODUCT_BOMLINE";

CREATE VIEW "RV_PP_PRODUCT_BOMLINE" ("SEQNO", "LEVELNO", "LEVELS", "AD_CLIENT_ID", "AD_ORG_ID", "CREATEDBY", "UPDATEDBY", "UPDATED", "CREATED", "AD_PINSTANCE_ID", "IMPLOTION", "M_PRODUCT_ID", "ISACTIVE", "PP_PRODUCT_BOM_ID", "PP_PRODUCT_BOMLINE_ID", "DESCRIPTION", "ISCRITICAL", "COMPONENTTYPE", "TM_PRODUCT_ID", "C_UOM_ID", "ISSUEMETHOD", "LINE", "M_ATTRIBUTESETINSTANCE_ID", "SCRAP", "VALIDFROM", "VALIDTO", "QTYBOM", "QTYBATCH", "ISQTYPERCENTAGE") AS 
 SELECT t.seqno, t.levelno, t.levels, t.ad_client_id, t.ad_org_id, t.createdby, t.updatedby, t.updated, t.created, t.ad_pinstance_id, t.implotion, t.sel_product_id, bl.isactive, bl.pp_product_bom_id, bl.pp_product_bomline_id, bl.description, bl.iscritical, bl.componenttype, t.m_product_id, bl.c_uom_id, bl.issuemethod, bl.line, bl.m_attributesetinstance_id, bl.scrap, bl.validfrom, bl.validto, bl.qtybom, bl.qtybatch, bl.isqtypercentage
 FROM t_bomline t 
 LEFT JOIN pp_product_bomline bl ON t.pp_product_bomline_id = bl.pp_product_bomline_id 
 ORDER BY t.seqno;

-- May 12, 2008 1:58:58 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
DELETE FROM AD_Element_Trl WHERE AD_Element_ID=1000003
;

-- May 12, 2008 1:58:58 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
DELETE FROM AD_Element WHERE AD_Element_ID=1000003
;

-- May 12, 2008 2:01:04 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53466,0,'Implotion',TO_DATE('2008-05-12 14:01:03','YYYY-MM-DD HH24:MI:SS'),100,'Implosion of a Bill of Materials refers to finding all the BOM''''s in which a component is used.','EE01','Commonly called a Where-Used report.','Y','Implotion','Implosion',TO_DATE('2008-05-12 14:01:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 12, 2008 2:01:04 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53466 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 12, 2008 1:32:38 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55324,53466,0,20,53045,'Implotion',TO_DATE('2008-05-12 13:32:36','YYYY-MM-DD HH24:MI:SS'),100,'N','Implosion of a Bill of Materials refers to finding all the BOM''s in which a component is used.','EE01',1,'Commonly called a Where-Used report.','Y','N','N','N','N','N','N','N','N','N','Y','Implotion',0,TO_DATE('2008-05-12 13:32:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- May 12, 2008 1:32:38 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55324 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 12, 2008 1:32:26 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
UPDATE AD_Field SET Name='Implotion', Description='Implosion of a Bill of Materials refers to finding all the BOM''s in which a component is used.', Help='Commonly called a Where-Used report.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53466) AND IsCentrallyMaintained='Y'
;

-- May 12, 2008 1:32:26 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
UPDATE AD_Process_Para SET ColumnName='Implotion', Name='Implotion', Description='Implosion of a Bill of Materials refers to finding all the BOM''s in which a component is used.', Help='Commonly called a Where-Used report.', AD_Element_ID=53466 WHERE UPPER(ColumnName)='IMPLOTION' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 12, 2008 1:32:26 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
UPDATE AD_Process_Para SET ColumnName='Implotion', Name='Implotion', Description='Implosion of a Bill of Materials refers to finding all the BOM''s in which a component is used.', Help='Commonly called a Where-Used report.' WHERE AD_Element_ID=53466 AND IsCentrallyMaintained='Y'
;

-- May 12, 2008 1:32:26 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
UPDATE AD_PrintFormatItem pi SET PrintName='Implosion', Name='Implotion' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53466)
;

-- May 12, 2008 1:34:11 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53464,0,'Sel_Product_ID',TO_DATE('2008-05-12 13:34:10','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Sel_Product_ID','Selected Product',TO_DATE('2008-05-12 13:34:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 12, 2008 1:34:11 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53464 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 12, 2008 1:39:29 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55325,53464,0,13,53045,'Sel_Product_ID',TO_DATE('2008-05-12 13:39:28','YYYY-MM-DD HH24:MI:SS'),100,'EE01',10,'Y','N','N','N','N','Y','N','N','N','N','Y','Sel_Product_ID',0,TO_DATE('2008-05-12 13:39:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- May 12, 2008 1:39:29 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55325 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 12, 2008 1:42:52 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53465,0,'TM_Product_ID',TO_DATE('2008-05-12 13:42:51','YYYY-MM-DD HH24:MI:SS'),100,'T_Bomline M_Product_ID','EE01','Y','TM_Product_ID','TM_Product_ID',TO_DATE('2008-05-12 13:42:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 12, 2008 1:42:52 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53465 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 12, 2008 1:43:59 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55326,53465,0,13,53063,'TM_Product_ID',TO_DATE('2008-05-12 13:43:58','YYYY-MM-DD HH24:MI:SS'),100,'T_Bomline M_Product_ID','EE01',22,'Y','N','N','N','N','N','N','N','N','N','Y','TM_Product_ID',0,TO_DATE('2008-05-12 13:43:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- May 12, 2008 1:43:59 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55326 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 12, 2008 1:45:10 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55327,53466,0,20,53063,'Implotion',TO_DATE('2008-05-12 13:45:09','YYYY-MM-DD HH24:MI:SS'),100,'N','Implosion of a Bill of Materials refers to finding all the BOM''s in which a component is used.','EE01',1,'Commonly called a Where-Used report.','Y','N','N','N','N','N','N','N','N','N','Y','Implotion',0,TO_DATE('2008-05-12 13:45:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- May 12, 2008 1:45:10 PM EST
-- BF1949804 Broken Report - MultiLevel BOM & Formula Detail
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55327 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 13, 2008 12:06:52 PM EST
-- New Event Model Validator
UPDATE AD_PrintFormatItem SET AD_Column_ID=55326, IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_DATE('2008-05-13 12:06:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=50470
;

-- May 13, 2008 12:31:28 PM EST
-- New Event Model Validator
UPDATE AD_Column SET AD_Reference_ID=30, AD_Reference_Value_ID=162,Updated=TO_DATE('2008-05-13 12:31:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55326
;
