-- Apr 3, 2010 11:25:11 PM COT
-- FR2962094_Finish implementation of weighted average costing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59137,1394,0,37,808,'CurrentCostPrice',TO_TIMESTAMP('2010-04-03 23:25:05','YYYY-MM-DD HH24:MI:SS'),100,'The currently used cost price','D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Current Cost Price',0,TO_TIMESTAMP('2010-04-03 23:25:05','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 3, 2010 11:25:11 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59137 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 3, 2010 11:25:17 PM COT
ALTER TABLE M_CostDetail ADD COLUMN CurrentCostPrice NUMERIC DEFAULT NULL 
;

-- Apr 3, 2010 11:25:54 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59138,2842,0,29,808,'CurrentQty',TO_TIMESTAMP('2010-04-03 23:25:53','YYYY-MM-DD HH24:MI:SS'),100,'Current Quantity','D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Current Quantity',0,TO_TIMESTAMP('2010-04-03 23:25:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 3, 2010 11:25:54 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59138 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 3, 2010 11:26:02 PM COT
ALTER TABLE M_CostDetail ADD COLUMN CurrentQty NUMERIC DEFAULT NULL 
;

-- Apr 4, 2010 11:42:29 AM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59139,2822,0,12,808,'CumulatedAmt',TO_TIMESTAMP('2010-04-04 11:42:28','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount','D',22,'Sum of all amounts','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Accumulated Amt',0,TO_TIMESTAMP('2010-04-04 11:42:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 4, 2010 11:42:29 AM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59139 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 4, 2010 11:42:32 AM COT
ALTER TABLE M_CostDetail ADD COLUMN CumulatedAmt NUMERIC DEFAULT NULL 
;

-- Apr 4, 2010 11:42:58 AM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59140,2823,0,29,808,'CumulatedQty',TO_TIMESTAMP('2010-04-04 11:42:57','YYYY-MM-DD HH24:MI:SS'),100,'Total Quantity','D',22,'Sum of the quantities','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Accumulated Qty',0,TO_TIMESTAMP('2010-04-04 11:42:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 4, 2010 11:42:58 AM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59140 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 4, 2010 11:43:03 AM COT
ALTER TABLE M_CostDetail ADD COLUMN CumulatedQty NUMERIC DEFAULT NULL 
;

-- Apr 4, 2010 11:46:11 AM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59137,105,58846,0,748,TO_TIMESTAMP('2010-04-04 11:46:09','YYYY-MM-DD HH24:MI:SS'),100,'The currently used cost price',10,'D','Y','Y','Y','N','N','N','Y','N','Current Cost Price',230,0,TO_TIMESTAMP('2010-04-04 11:46:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 4, 2010 11:46:11 AM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58846 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 4, 2010 11:46:44 AM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59138,105,58847,0,748,TO_TIMESTAMP('2010-04-04 11:46:43','YYYY-MM-DD HH24:MI:SS'),100,'Current Quantity',10,'D','Y','Y','Y','N','N','N','Y','Y','Current Quantity',240,0,TO_TIMESTAMP('2010-04-04 11:46:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 4, 2010 11:46:44 AM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58847 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 4, 2010 11:47:15 AM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59139,58848,0,748,TO_TIMESTAMP('2010-04-04 11:47:14','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount',10,'D','Sum of all amounts','Y','Y','Y','N','N','N','Y','N','Accumulated Amt',250,0,TO_TIMESTAMP('2010-04-04 11:47:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 4, 2010 11:47:15 AM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58848 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 4, 2010 11:47:30 AM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59140,105,58849,0,748,TO_TIMESTAMP('2010-04-04 11:47:29','YYYY-MM-DD HH24:MI:SS'),100,'Total Quantity',10,'U','Sum of the quantities','Y','Y','Y','N','N','N','Y','Y','Accumulated Qty',260,0,TO_TIMESTAMP('2010-04-04 11:47:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 4, 2010 11:47:30 AM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58849 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 4, 2010 11:47:33 AM COT
UPDATE AD_Field SET AD_FieldGroup_ID=105,Updated=TO_TIMESTAMP('2010-04-04 11:47:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58848
;

