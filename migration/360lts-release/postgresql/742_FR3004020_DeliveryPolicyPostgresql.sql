-- 2010-maj-19 14:36:56 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54156,0,'QtyAllocated',TO_TIMESTAMP('2010-05-19 14:36:56','YYYY-MM-DD HH24:MI:SS'),100,'Allocated quantity','D','Allocated quantity is the quantity that is actually reserved for a specific customer. The customer "owns" this quantity. The allocated quantity can never be more than what''s in stock (as opposed to resevedQty).','Y','Qty Allocated','Qty Allocated',TO_TIMESTAMP('2010-05-19 14:36:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2010-maj-19 14:36:56 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54156 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 2010-maj-19 14:42:55 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54157,0,'DeliveryPolicy',TO_TIMESTAMP('2010-05-19 14:42:55','YYYY-MM-DD HH24:MI:SS'),100,'Delivery Policy','D','The delivery policy determines how outbound orders will be allocated.
The default delivery policy is to deliver fulfilled orders as soon as possible even if it means
other non fulfilled orders also needs the items being delivered on the fulfilled orders.','Y','Delivery Policy','Delivery Policy',TO_TIMESTAMP('2010-05-19 14:42:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2010-maj-19 14:42:55 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54157 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 2010-maj-19 14:43:59 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53355,TO_TIMESTAMP('2010-05-19 14:43:59','YYYY-MM-DD HH24:MI:SS'),100,'List of Delivery Policies','D','Y','N','DeliveryPolicies',TO_TIMESTAMP('2010-05-19 14:43:59','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- 2010-maj-19 14:43:59 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53355 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 2010-maj-19 14:44:40 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53355,53581,TO_TIMESTAMP('2010-05-19 14:44:40','YYYY-MM-DD HH24:MI:SS'),100,'Default delivery policy - deliver as soon as orders are fulfilled according to delivery rule','D','Y','No Hold',TO_TIMESTAMP('2010-05-19 14:44:40','YYYY-MM-DD HH24:MI:SS'),100,'N')
;

-- 2010-maj-19 14:44:40 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53581 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- 2010-maj-19 14:45:59 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53355,53582,TO_TIMESTAMP('2010-05-19 14:45:59','YYYY-MM-DD HH24:MI:SS'),100,'Allocate items to orders and fulfill them in strict order.','D','Y','Strict order',TO_TIMESTAMP('2010-05-19 14:45:59','YYYY-MM-DD HH24:MI:SS'),100,'O')
;

-- 2010-maj-19 14:45:59 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53582 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- 2010-maj-19 14:47:45 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59188,54156,0,29,260,'QtyAllocated',TO_TIMESTAMP('2010-05-19 14:47:45','YYYY-MM-DD HH24:MI:SS'),100,'0','Allocated quantity','D',22,'Allocated quantity is the quantity that is actually reserved for a specific customer. The customer "owns" this quantity. The allocated quantity can never be more than what''s in stock (as opposed to resevedQty).','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Qty Allocated',0,TO_TIMESTAMP('2010-05-19 14:47:45','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- 2010-maj-19 14:47:45 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59188 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2010-maj-19 14:48:05 CEST
-- FR 3002040 - Delivery Policy
ALTER TABLE C_OrderLine ADD COLUMN QtyAllocated NUMERIC DEFAULT '0' 
;

-- 2010-maj-19 14:49:07 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59189,54156,0,29,250,'QtyAllocated',TO_TIMESTAMP('2010-05-19 14:49:07','YYYY-MM-DD HH24:MI:SS'),100,'Allocated quantity','D',22,'Allocated quantity is the quantity that is actually reserved for a specific customer. The customer "owns" this quantity. The allocated quantity can never be more than what''s in stock (as opposed to resevedQty).','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Qty Allocated',0,TO_TIMESTAMP('2010-05-19 14:49:07','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- 2010-maj-19 14:49:07 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59189 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2010-maj-19 14:49:18 CEST
-- FR 3002040 - Delivery Policy
ALTER TABLE M_Storage ADD COLUMN QtyAllocated NUMERIC DEFAULT '0'
;

-- 2010-maj-19 14:49:28 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Column SET DefaultValue='0',Updated=TO_TIMESTAMP('2010-05-19 14:49:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59189
;

-- Add field QtyAllocated to Sales Order Line

-- 2010-maj-19 14:54:08 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,59188,102,58877,0,187,TO_TIMESTAMP('2010-05-19 14:54:08','YYYY-MM-DD HH24:MI:SS'),100,'Allocated quantity',26,'@OrderType@=''OB'' | @OrderType@=''SO'' | @Processed@=''Y''','D','Allocated quantity is the quantity that is actually reserved for a specific customer. The customer "owns" this quantity. The allocated quantity can never be more than what''s in stock (as opposed to resevedQty).','Y','Y','Y','N','N','N','Y','N','Qty Allocated',190,TO_TIMESTAMP('2010-05-19 14:54:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2010-maj-19 14:54:08 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58877 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=58877
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=1135
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=10829
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=1138
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=1137
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=2115
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=1141
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=3124
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=12745
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=13644
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=13645
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=13691
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=13650
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=13651
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=2880
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=12744
;

-- 2010-maj-19 14:54:35 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=10332
;

-- 2010-maj-19 14:55:07 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET Name='Allocated Quantity',Updated=TO_TIMESTAMP('2010-05-19 14:55:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58877
;

-- 2010-maj-19 14:55:07 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=58877
;

-- Add field QtyAllocated to MStorage

-- 2010-maj-19 14:56:52 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59189,58878,0,179,TO_TIMESTAMP('2010-05-19 14:56:52','YYYY-MM-DD HH24:MI:SS'),100,'Allocated quantity',26,'D','Allocated quantity is the quantity that is actually reserved for a specific customer. The customer "owns" this quantity. The allocated quantity can never be more than what''s in stock (as opposed to resevedQty).','Y','Y','Y','N','N','N','N','N','Qty Allocated',110,0,TO_TIMESTAMP('2010-05-19 14:56:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2010-maj-19 14:56:52 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58878 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2010-maj-19 14:57:28 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET Name='Allocated Quantity',Updated=TO_TIMESTAMP('2010-05-19 14:57:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58878
;

-- 2010-maj-19 14:57:28 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=58878
;

-- Add column Delivery Policy to OrganizationInfo

-- 2010-maj-19 15:02:03 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59190,54157,0,17,53355,228,'DeliveryPolicy',TO_TIMESTAMP('2010-05-19 15:02:03','YYYY-MM-DD HH24:MI:SS'),100,'Delivery Policy','D',1,'The delivery policy determines how outbound orders will be allocated.
The default delivery policy is to deliver fulfilled orders as soon as possible even if it means
other non fulfilled orders also needs the items being delivered on the fulfilled orders.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Delivery Policy',0,TO_TIMESTAMP('2010-05-19 15:02:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2010-maj-19 15:02:03 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59190 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2010-maj-19 15:02:11 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Column SET Version=1.000000000000,Updated=TO_TIMESTAMP('2010-05-19 15:02:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59190
;

-- 2010-maj-19 15:02:18 CEST
-- FR 3002040 - Delivery Policy
ALTER TABLE AD_OrgInfo ADD COLUMN DeliveryPolicy CHAR(1) DEFAULT NULL 
;

-- 2010-maj-19 15:06:47 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59191,54157,0,17,53355,227,'DeliveryPolicy',TO_TIMESTAMP('2010-05-19 15:06:46','YYYY-MM-DD HH24:MI:SS'),100,'N','Delivery Policy','D',1,'The delivery policy determines how outbound orders will be allocated.
The default delivery policy is to deliver fulfilled orders as soon as possible even if it means
other non fulfilled orders also needs the items being delivered on the fulfilled orders.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Delivery Policy',0,TO_TIMESTAMP('2010-05-19 15:06:46','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- 2010-maj-19 15:06:47 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59191 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- 2010-maj-19 15:06:53 CEST
-- FR 3002040 - Delivery Policy
ALTER TABLE AD_ClientInfo ADD COLUMN DeliveryPolicy CHAR(1) DEFAULT 'N' 
;

-- Add field to Client Info Window

-- 2010-maj-19 15:10:10 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,59191,118,58879,0,169,TO_TIMESTAMP('2010-05-19 15:10:10','YYYY-MM-DD HH24:MI:SS'),100,'N','Delivery Policy',14,'U','The delivery policy determines how outbound orders will be allocated.
The default delivery policy is to deliver fulfilled orders as soon as possible even if it means
other non fulfilled orders also needs the items being delivered on the fulfilled orders.','Y','Y','Y','N','N','N','N','Y','Delivery Policy',155,TO_TIMESTAMP('2010-05-19 15:10:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2010-maj-19 15:10:10 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58879 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Add DeliveryPolicy field to Organization Window

-- 2010-maj-19 15:12:01 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59190,58880,0,170,TO_TIMESTAMP('2010-05-19 15:12:01','YYYY-MM-DD HH24:MI:SS'),100,'Delivery Policy',0,'D','The delivery policy determines how outbound orders will be allocated.
The default delivery policy is to deliver fulfilled orders as soon as possible even if it means
other non fulfilled orders also needs the items being delivered on the fulfilled orders.','Y','Y','Y','N','N','N','N','N','Delivery Policy',85,0,TO_TIMESTAMP('2010-05-19 15:12:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2010-maj-19 15:12:01 CEST
-- FR 3002040 - Delivery Policy
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58880 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- 2010-maj-19 15:12:11 CEST
-- FR 3002040 - Delivery Policy
UPDATE AD_Field SET DisplayLength=22,Updated=TO_TIMESTAMP('2010-05-19 15:12:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58880
;

-- Jun 22, 2010 1:47:33 PM CEST
-- FR 3004020 Delivery Policy
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('3',0,0,53217,'org.adempiere.process.AllocateSalesOrders','N',TO_TIMESTAMP('2010-06-22 13:47:31','YYYY-MM-DD HH24:MI:SS'),100,'Allocate available on hand stock to sales orders','D','This process is only necessary for delivery policy = Strict order','Y','N','N','N','N','Allocate Sales Orders','Y',0,0,TO_TIMESTAMP('2010-06-22 13:47:31','YYYY-MM-DD HH24:MI:SS'),100,'AllocateSalesOrders')
;

-- Jun 22, 2010 1:47:33 PM CEST
-- FR 3004020 Delivery Policy
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53217 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Jun 22, 2010 1:48:24 PM CEST
-- FR 3004020 Delivery Policy
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,459,0,53217,53422,18,'M_Warehouse_ID',TO_TIMESTAMP('2010-06-22 13:48:23','YYYY-MM-DD HH24:MI:SS'),100,'Warehouse where allocation should be executed.','D',0,'Y','Y','N','N','Warehouse',10,TO_TIMESTAMP('2010-06-22 13:48:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 22, 2010 1:48:24 PM CEST
-- FR 3004020 Delivery Policy
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53422 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 22, 2010 1:49:05 PM CEST
-- FR 3004020 Delivery Policy
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53283,0,53217,TO_TIMESTAMP('2010-06-22 13:49:04','YYYY-MM-DD HH24:MI:SS'),100,'Allocate available on hand stock to sales orders','D','Y','Y','N','N','N','Allocate Sales Orders',TO_TIMESTAMP('2010-06-22 13:49:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 22, 2010 1:49:05 PM CEST
-- FR 3004020 Delivery Policy
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53283 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Jun 22, 2010 1:49:05 PM CEST
-- FR 3004020 Delivery Policy
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', CURRENT_TIMESTAMP, 100, CURRENT_TIMESTAMP, 100,t.AD_Tree_ID, 53283, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53283)
;

-- Jun 22, 2010 1:49:15 PM CEST
-- FR 3004020 Delivery Policy
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53283
;

-- Jun 22, 2010 1:49:15 PM CEST
-- FR 3004020 Delivery Policy
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=346
;

-- Jun 22, 2010 1:49:15 PM CEST
-- FR 3004020 Delivery Policy
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- Jun 22, 2010 1:49:15 PM CEST
-- FR 3004020 Delivery Policy
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=193
;

-- Jun 22, 2010 1:49:15 PM CEST
-- FR 3004020 Delivery Policy
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=180
;

-- Jun 22, 2010 1:49:15 PM CEST
-- FR 3004020 Delivery Policy
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=494
;

-- Jun 22, 2010 1:49:15 PM CEST
-- FR 3004020 Delivery Policy
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=444
;

