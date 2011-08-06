-- Aug 6, 2011 12:05:38 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,62038,1762,0,14,53021,'IsAvailable',TO_TIMESTAMP('2011-08-06 00:04:19','YYYY-MM-DD HH24:MI:SS'),100,'Resource is available','EE01',2147483647,'Resource is available for assignments','Y','N','N','N','N','N','N','N','N','N','Available',TO_TIMESTAMP('2011-08-06 00:04:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 6, 2011 12:05:41 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=62038 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 6, 2011 12:05:48 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,62039,275,0,14,53021,'Description',TO_TIMESTAMP('2011-08-06 00:05:41','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','EE01',2147483647,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','Y','N','N','Description',TO_TIMESTAMP('2011-08-06 00:05:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 6, 2011 12:05:48 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=62039 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 6, 2011 12:05:49 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,62040,53275,0,19,53021,'PP_Order_BOMLine_ID',TO_TIMESTAMP('2011-08-06 00:05:48','YYYY-MM-DD HH24:MI:SS'),100,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Manufacturing Order BOM Line',TO_TIMESTAMP('2011-08-06 00:05:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 6, 2011 12:05:49 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=62040 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 6, 2011 12:05:49 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,62041,53311,0,19,53021,'DD_Order_ID',TO_TIMESTAMP('2011-08-06 00:05:49','YYYY-MM-DD HH24:MI:SS'),100,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Distribution Order',TO_TIMESTAMP('2011-08-06 00:05:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 6, 2011 12:05:49 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=62041 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 6, 2011 12:05:50 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,62042,53313,0,19,53021,'DD_OrderLine_ID',TO_TIMESTAMP('2011-08-06 00:05:49','YYYY-MM-DD HH24:MI:SS'),100,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Distribution Order Line',TO_TIMESTAMP('2011-08-06 00:05:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 6, 2011 12:05:50 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=62042 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 6, 2011 12:05:50 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,62043,53269,0,19,53021,'Planner_ID',TO_TIMESTAMP('2011-08-06 00:05:50','YYYY-MM-DD HH24:MI:SS'),100,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Planner',TO_TIMESTAMP('2011-08-06 00:05:50','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 6, 2011 12:05:50 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=62043 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 6, 2011 12:05:51 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,62044,624,0,22,53021,'Version',TO_TIMESTAMP('2011-08-06 00:05:50','YYYY-MM-DD HH24:MI:SS'),100,'Version of the table definition','EE01',131089,'The Version indicates the version of this table definition.','Y','N','N','N','N','N','N','N','N','N','Version',TO_TIMESTAMP('2011-08-06 00:05:50','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 6, 2011 12:05:51 AM CDT
-- Synchronize RV_PP_MRP
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=62044 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 6, 2011 12:06:56 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2011-08-06 00:06:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58803
;

-- Aug 6, 2011 12:07:00 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2011-08-06 00:07:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=62042
;

-- Aug 6, 2011 12:07:02 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2011-08-06 00:07:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=62041
;

-- Aug 6, 2011 12:07:19 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2011-08-06 00:07:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=62039
;

-- Aug 6, 2011 12:07:43 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET AD_Reference_ID=20, FieldLength=1,Updated=TO_TIMESTAMP('2011-08-06 00:07:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=62038
;

-- Aug 6, 2011 12:07:51 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2011-08-06 00:07:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58801
;

-- Aug 6, 2011 12:07:54 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2011-08-06 00:07:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=62040
;

-- Aug 6, 2011 12:07:57 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2011-08-06 00:07:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=62043
;

-- Aug 6, 2011 12:08:03 AM CDT
-- Synchronize RV_PP_MRP
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2011-08-06 00:08:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=62044
;

