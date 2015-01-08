SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

-- Sep 13, 2010 12:14:14 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59790,2071,0,19,478,'M_CostType_ID',TO_DATE('2010-09-13 12:14:10','YYYY-MM-DD HH24:MI:SS'),0,'Type of Cost (e.g. Current, Plan, Future)','D',10,'You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Type',0,TO_DATE('2010-09-13 12:14:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 13, 2010 12:14:14 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59790 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 13, 2010 12:14:20 PM CDT
-- Cost Engine
ALTER TABLE T_InventoryValue ADD M_CostType_ID NUMBER(10) DEFAULT NULL 
;

-- Sep 13, 2010 12:15:34 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59791,453,0,19,478,'M_Product_Category_ID',TO_DATE('2010-09-13 12:15:30','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','D',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product Category',0,TO_DATE('2010-09-13 12:15:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 13, 2010 12:15:34 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59791 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 13, 2010 12:15:38 PM CDT
-- Cost Engine
ALTER TABLE T_InventoryValue ADD M_Product_Category_ID NUMBER(10) DEFAULT NULL 
;

-- Sep 13, 2010 12:17:28 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59792,852,0,10,478,'Classification',TO_DATE('2010-09-13 12:17:27','YYYY-MM-DD HH24:MI:SS'),0,'Classification for grouping','D',12,'The Classification can be used to optionally group products.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Classification',0,TO_DATE('2010-09-13 12:17:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 13, 2010 12:17:28 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59792 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 13, 2010 12:17:30 PM CDT
-- Cost Engine
ALTER TABLE T_InventoryValue ADD Classification NVARCHAR2(12) DEFAULT NULL 
;

-- Sep 13, 2010 12:18:24 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59793,52018,0,10,478,'Group1',TO_DATE('2010-09-13 12:18:23','YYYY-MM-DD HH24:MI:SS'),0,'D',255,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Group1',0,TO_DATE('2010-09-13 12:18:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 13, 2010 12:18:24 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59793 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 13, 2010 12:18:28 PM CDT
-- Cost Engine
ALTER TABLE T_InventoryValue ADD Group1 NVARCHAR2(255) DEFAULT NULL 
;

-- Sep 13, 2010 12:18:44 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59794,52019,0,10,478,'Group2',TO_DATE('2010-09-13 12:18:42','YYYY-MM-DD HH24:MI:SS'),0,'D',255,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Group2',0,TO_DATE('2010-09-13 12:18:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 13, 2010 12:18:44 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59794 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 13, 2010 12:18:49 PM CDT
-- Cost Engine
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2010-09-13 12:18:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=59794
;

-- Sep 13, 2010 12:18:55 PM CDT
-- Cost Engine
ALTER TABLE T_InventoryValue ADD Group2 NVARCHAR2(255) DEFAULT NULL 
;

