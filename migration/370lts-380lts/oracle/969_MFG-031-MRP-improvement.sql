SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- Aug 20, 2012 10:34:30 AM CDT
-- MFG-31
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53562,53020,140,TO_DATE('2012-08-20 10:34:25','YYYY-MM-DD HH24:MI:SS'),100,'Maintain Product Planning Data','EE01','N','in the Window Product Planning Data you enter the product information which will serve as a base to execute the algorithms of Material Requirement Planning, along with MPS, open orders and inventories','N','Y','N','N','Y','N','Y','N','N','Planning Data','N',55,0,TO_DATE('2012-08-20 10:34:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:30 AM CDT
-- MFG-31
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53562 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Aug 20, 2012 10:34:48 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53405,65084,0,53562,TO_DATE('2012-08-20 10:34:47','YYYY-MM-DD HH24:MI:SS'),100,'Workflow Simulation Execution Time',22,'EE01','Amount of time the performer of the activity needs to perform the task in Duration Unit

In the field Working Time you enter the accumulated time (using the Promising Delivery Time) in the critical path of the BOM for this product. It is the required time to produce the product as if you would not have any component on hand.','N','Y','N','N','N','N','N','N','Y','Working Time',0,0,TO_DATE('2012-08-20 10:34:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:48 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65084 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:34:50 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53398,65085,0,53562,TO_DATE('2012-08-20 10:34:48','YYYY-MM-DD HH24:MI:SS'),100,22,'EE01','N','Y','Y','N','N','N','N','N','N','Product Planning',0,0,TO_DATE('2012-08-20 10:34:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:50 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65085 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:34:51 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53386,65086,0,53562,TO_DATE('2012-08-20 10:34:50','YYYY-MM-DD HH24:MI:SS'),100,'Phantom Component',1,'EE01','Phantom Component are not stored and produced with the product. This is an option to avild maintaining an Engineering and Manufacturing Bill of Materials.','N','Y','Y','N','N','N','N','N','N','Phantom',0,0,TO_DATE('2012-08-20 10:34:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:51 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65086 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:34:53 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53375,65087,0,53562,TO_DATE('2012-08-20 10:34:51','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',0,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','N','Y','Y','Y','N','N','N','N','N','Client',10,0,TO_DATE('2012-08-20 10:34:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:53 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65087 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:34:54 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53376,65088,0,53562,TO_DATE('2012-08-20 10:34:53','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',0,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','N','Y','Y','Y','N','N','N','N','Y','Organization',20,0,TO_DATE('2012-08-20 10:34:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:54 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65088 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:34:56 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53389,65089,0,53562,TO_DATE('2012-08-20 10:34:54','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',0,'EE01','Identifies an item which is either purchased or sold in this organization.','N','Y','Y','Y','N','N','N','N','N','Product',30,0,TO_DATE('2012-08-20 10:34:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:56 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65089 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:34:57 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53381,65090,0,53562,TO_DATE('2012-08-20 10:34:56','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','N','Y','Y','Y','N','N','N','N','N','Active',40,0,TO_DATE('2012-08-20 10:34:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:57 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65090 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:34:59 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53400,65091,0,53562,TO_DATE('2012-08-20 10:34:57','YYYY-MM-DD HH24:MI:SS'),100,'Resource',0,'EE01','A manufacturing resource is a place where a product will be made.','N','Y','N','Y','N','N','N','N','N','Resource',50,0,TO_DATE('2012-08-20 10:34:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:34:59 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65091 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:00 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53390,65092,0,53562,TO_DATE('2012-08-20 10:34:59','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point',0,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.

Warehouse place where you locate and control the products','N','Y','N','Y','N','N','N','N','Y','Warehouse',60,0,TO_DATE('2012-08-20 10:34:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:00 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65092 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:01 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53399,65093,0,53562,TO_DATE('2012-08-20 10:35:00','YYYY-MM-DD HH24:MI:SS'),100,22,'EE01','N','Y','Y','Y','N','N','N','N','N','Planner',70,0,TO_DATE('2012-08-20 10:35:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:01 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65093 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:03 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53397,65094,0,53562,TO_DATE('2012-08-20 10:35:01','YYYY-MM-DD HH24:MI:SS'),100,30,'EE01','The name BOM/Formula that you introduce in this window will be considered the default BOM to produce the product in this Organization-Plant-Warehouse. If you do not fill this field the default BOM & Formula for the entity will be the BOM/Formula which has the same name as the product.','N','Y','N','Y','N','N','N','N','N','BOM & Formula',80,0,TO_DATE('2012-08-20 10:35:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:03 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65094 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:04 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53377,65095,0,53562,TO_DATE('2012-08-20 10:35:03','YYYY-MM-DD HH24:MI:SS'),100,'Workflow or combination of tasks',30,'EE01','The Workflow field identifies a unique Workflow in the system.

The Workflow you introduce in this window will be considered the default Workflow to produce the product in this Organization-Plant-Warehouse. If you do not fill this field the defaul Workflow for the entity will be the Workflow with the same name as the product.','N','Y','N','Y','N','N','N','N','N','Workflow',90,0,TO_DATE('2012-08-20 10:35:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:04 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65095 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:06 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,54310,65096,0,53562,TO_DATE('2012-08-20 10:35:04','YYYY-MM-DD HH24:MI:SS'),100,30,'EE01','N','Y','Y','Y','N','N','N','N','N','Network Distribution',100,TO_DATE('2012-08-20 10:35:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:06 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65096 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:07 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53385,65097,0,53562,TO_DATE('2012-08-20 10:35:06','YYYY-MM-DD HH24:MI:SS'),100,1,'EE01','N','Y','Y','Y','N','N','N','N','N','Is MPS',110,0,TO_DATE('2012-08-20 10:35:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:07 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65097 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:09 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53382,65098,0,53562,TO_DATE('2012-08-20 10:35:07','YYYY-MM-DD HH24:MI:SS'),100,'Indicates whether planned orders will be generated by MRP',1,'EE01','Indicates whether planned orders will be generated by MRP, if this flag is not just MRP generate a ''Create'' action notice','N','Y','Y','Y','N','N','N','N','Y','Create Plan',120,0,TO_DATE('2012-08-20 10:35:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:09 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65098 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:11 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53387,65099,0,53562,TO_DATE('2012-08-20 10:35:09','YYYY-MM-DD HH24:MI:SS'),100,1,'EE01','N','Y','Y','Y','N','N','N','Y','N','Required Calculate MRP',130,0,TO_DATE('2012-08-20 10:35:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:11 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65099 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:13 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,55334,65100,0,53562,TO_DATE('2012-08-20 10:35:11','YYYY-MM-DD HH24:MI:SS'),100,1,'EE01','N','Y','Y','Y','N','N','N','Y','Y','Required Calculate DRP',140,TO_DATE('2012-08-20 10:35:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:13 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65100 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:14 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53380,65101,0,53562,TO_DATE('2012-08-20 10:35:13','YYYY-MM-DD HH24:MI:SS'),100,'Promised days between order and delivery',10,'@IsPurchased@=N','EE01','The Promised Delivery Time indicates the number of days between the order date and the date that delivery was promised.

You must enter the average number of days to receive the product in the warehouse since you approve the requisition or manufacturing order until you receive the material in the warehouse . If the product is bought you must register the calendar days required since you make the PO until you receive the material in the warehouse. If the product is manufactured in your plant you must register the number of working days since you release the MO until you receive the material in the warehouse.','N','Y','N','Y','N','N','N','N','N','Promised Delivery Time',150,0,TO_DATE('2012-08-20 10:35:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:14 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65101 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:16 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53401,65102,0,53562,TO_DATE('2012-08-20 10:35:14','YYYY-MM-DD HH24:MI:SS'),100,10,'EE01','N','Y','Y','Y','N','N','N','N','N','Time Fence',160,0,TO_DATE('2012-08-20 10:35:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:16 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65102 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:18 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53402,65103,0,53562,TO_DATE('2012-08-20 10:35:16','YYYY-MM-DD HH24:MI:SS'),100,10,'EE01','N','Y','Y','Y','N','N','N','N','Y','Transfert Time',170,0,TO_DATE('2012-08-20 10:35:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:18 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65103 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:19 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53395,65104,0,53562,TO_DATE('2012-08-20 10:35:18','YYYY-MM-DD HH24:MI:SS'),100,10,'EE01','N','Y','Y','Y','N','N','N','N','N','Order Policy',180,0,TO_DATE('2012-08-20 10:35:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:19 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65104 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:21 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53394,65105,0,53562,TO_DATE('2012-08-20 10:35:19','YYYY-MM-DD HH24:MI:SS'),100,10,'@Order_Policy@=''POQ''','EE01','N','Y','Y','Y','N','N','N','N','N','Order Period',190,0,TO_DATE('2012-08-20 10:35:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:21 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65105 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:22 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53396,65106,0,53562,TO_DATE('2012-08-20 10:35:21','YYYY-MM-DD HH24:MI:SS'),100,10,'EE01','N','Y','Y','Y','N','N','N','N','N','Order Qty',200,0,TO_DATE('2012-08-20 10:35:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:22 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65106 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:23 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53393,65107,0,53562,TO_DATE('2012-08-20 10:35:22','YYYY-MM-DD HH24:MI:SS'),100,'Package order size in UOM (e.g. order set of 5 units)',10,'@IsPurchased@=N','EE01','The Order Pack Quantity indicates the number of units in each pack of this product.','N','Y','Y','Y','N','N','N','N','N','Order Pack Qty',210,0,TO_DATE('2012-08-20 10:35:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:23 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65107 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:28 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53392,65108,0,53562,TO_DATE('2012-08-20 10:35:23','YYYY-MM-DD HH24:MI:SS'),100,'Minimum order quantity in UOM',10,'@IsPurchased@=N','EE01','The Minimum Order Quantity indicates the smallest quantity of this product which can be ordered.','N','Y','Y','Y','N','N','N','N','N','Minimum Order Qty',220,0,TO_DATE('2012-08-20 10:35:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:28 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65108 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:29 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53391,65109,0,53562,TO_DATE('2012-08-20 10:35:28','YYYY-MM-DD HH24:MI:SS'),100,'Maximum order quantity in UOM',10,'@IsPurchased@=N','EE01','The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.','N','Y','Y','Y','N','N','N','N','Y','Maximum Order Qty',230,0,TO_DATE('2012-08-20 10:35:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:29 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65109 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:31 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,54380,65110,0,53562,TO_DATE('2012-08-20 10:35:29','YYYY-MM-DD HH24:MI:SS'),100,'Safety stock is a term used to describe a level of stock that is maintained below the cycle stock to buffer against stock-outs',10,'EE01','Safety stock is defined as extra units of inventory carried as protection against possible stockouts. It is held when an organization cannot accurately predict demand and/or lead time for the product.

Rereference:
http://en.wikipedia.org/wiki/Safety_stock','N','Y','Y','Y','N','N','N','N','N','Safety Stock Qty',240,TO_DATE('2012-08-20 10:35:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:31 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65110 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:35:32 AM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,53406,65111,0,53562,TO_DATE('2012-08-20 10:35:31','YYYY-MM-DD HH24:MI:SS'),100,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent',10,'EE01','ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities','N','Y','Y','Y','N','N','N','N','Y','Yield %',250,0,TO_DATE('2012-08-20 10:35:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:35:32 AM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65111 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:43:14 AM CDT
-- MFG-31
UPDATE AD_Element SET Description='Company Agent for Planning', Help='The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.',Updated=TO_DATE('2012-08-20 10:43:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53269
;

-- Aug 20, 2012 10:43:14 AM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53269
;

-- Aug 20, 2012 10:43:14 AM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='Planner_ID', Name='Planner', Description='Company Agent for Planning', Help='The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.' WHERE AD_Element_ID=53269
;

-- Aug 20, 2012 10:43:14 AM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Planner_ID', Name='Planner', Description='Company Agent for Planning', Help='The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.', AD_Element_ID=53269 WHERE UPPER(ColumnName)='PLANNER_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 10:43:14 AM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Planner_ID', Name='Planner', Description='Company Agent for Planning', Help='The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.' WHERE AD_Element_ID=53269 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 10:43:15 AM CDT
-- MFG-31
UPDATE AD_Field SET Name='Planner', Description='Company Agent for Planning', Help='The Master Planner indicates the company agent in charge of the MPS management. Any Master Planner must be a valid internal user.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53269) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 10:43:44 AM CDT
-- MFG-31
UPDATE AD_Field SET Description='BOM & Formula',Updated=TO_DATE('2012-08-20 10:43:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65094
;

-- Aug 20, 2012 10:43:44 AM CDT
-- MFG-31
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=65094
;

-- Aug 20, 2012 12:09:26 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Is MRP Required', Help='If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.', Name='Is MRP Required', PrintName='Is MRP Required',Updated=TO_DATE('2012-08-20 12:09:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53262
;

-- Aug 20, 2012 12:09:26 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53262
;

-- Aug 20, 2012 12:09:26 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='IsRequiredMRP', Name='Is MRP Required', Description='Is MRP Required', Help='If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Element_ID=53262
;

-- Aug 20, 2012 12:09:26 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsRequiredMRP', Name='Is MRP Required', Description='Is MRP Required', Help='If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.', AD_Element_ID=53262 WHERE UPPER(ColumnName)='ISREQUIREDMRP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 12:09:26 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsRequiredMRP', Name='Is MRP Required', Description='Is MRP Required', Help='If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Element_ID=53262 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 12:09:26 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Is MRP Required', Description='Is MRP Required', Help='If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53262) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 12:09:26 PM CDT
-- MFG-31
UPDATE AD_PrintFormatItem pi SET PrintName='Is MRP Required', Name='Is MRP Required' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53262)
;

-- Aug 20, 2012 12:29:08 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Transfert Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.',Updated=TO_DATE('2012-08-20 12:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53271
;

-- Aug 20, 2012 12:29:08 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53271
;

-- Aug 20, 2012 12:29:08 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='TransfertTime', Name='Transfert Time', Description='Transfert Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.' WHERE AD_Element_ID=53271
;

-- Aug 20, 2012 12:29:08 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='TransfertTime', Name='Transfert Time', Description='Transfert Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.', AD_Element_ID=53271 WHERE UPPER(ColumnName)='TRANSFERTTIME' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 12:29:08 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='TransfertTime', Name='Transfert Time', Description='Transfert Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.' WHERE AD_Element_ID=53271 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 12:29:08 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Transfert Time', Description='Transfert Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53271) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 12:39:06 PM CDT
-- MFG-31
UPDATE AD_Tab SET TabLevel=2,Updated=TO_DATE('2012-08-20 12:39:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53562
;

-- Aug 20, 2012 12:39:45 PM CDT
-- MFG-31
UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2012-08-20 12:39:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53562
;

-- Aug 20, 2012 12:40:08 PM CDT
-- MFG-31
UPDATE AD_Tab SET AD_Column_ID=53389,Updated=TO_DATE('2012-08-20 12:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53562
;

-- Aug 20, 2012 1:02:15 PM CDT
-- MFG-31
UPDATE AD_Tab SET WhereClause='(AD_Note.AD_User_ID IN (0,@#AD_User_ID@) OR AD_Note.AD_User_ID IS NULL) AND EXISTS (SELECT 1 FROM AD_Message m WHERE m.AD_Message_ID=AD_Note.AD_Message_ID AND (m.Value LIKE ''MRP-%'' OR m.Value LIKE ''DRP-%''))',Updated=TO_DATE('2012-08-20 13:02:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53035
;

-- Aug 20, 2012 1:02:59 PM CDT
-- MFG-31
UPDATE AD_Tab SET WhereClause='EXISTS (SELECT 1 FROM AD_Message m WHERE m.AD_Message_ID=AD_Note.AD_Message_ID AND (m.Value like ''MRP-%'' OR m.Value like ''DRP-%''))',Updated=TO_DATE('2012-08-20 13:02:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53237
;

-- Aug 20, 2012 1:06:48 PM CDT
-- MFG-31
UPDATE AD_Table SET AccessLevel='3',Updated=TO_DATE('2012-08-20 13:06:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53027
;

-- Aug 20, 2012 1:09:01 PM CDT
-- MFG-31
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64176,53262,0,20,53021,'IsRequiredMRP',TO_DATE('2012-08-20 13:09:00','YYYY-MM-DD HH24:MI:SS'),100,'Is MRP Required','EE01',1,'If the MRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e BOM, Orders, Inventory, MPS, etc. and therefore  you need to executed again MRP to adjust the Planned Orders to the new conditions and to get the updated action messages.','Y','N','N','N','N','N','N','N','N','N','Is MRP Required',TO_DATE('2012-08-20 13:09:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 20, 2012 1:09:01 PM CDT
-- MFG-31
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64176 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 20, 2012 1:09:03 PM CDT
-- MFG-31
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64177,53470,0,20,53021,'IsRequiredDRP',TO_DATE('2012-08-20 13:09:01','YYYY-MM-DD HH24:MI:SS'),100,'EE01',1,'Y','N','N','N','N','N','N','N','N','N','Required Calculate DRP',TO_DATE('2012-08-20 13:09:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 20, 2012 1:09:03 PM CDT
-- MFG-31
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64177 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 20, 2012 1:09:04 PM CDT
-- MFG-31
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64178,1326,0,20,53021,'IsBOM',TO_DATE('2012-08-20 13:09:03','YYYY-MM-DD HH24:MI:SS'),100,'Bill of Materials','EE01',1,'The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','N','N','N','N','N','N','N','N','N','Bill of Materials',TO_DATE('2012-08-20 13:09:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 20, 2012 1:09:04 PM CDT
-- MFG-31
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64178 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 20, 2012 1:09:05 PM CDT
-- MFG-31
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64179,403,0,20,53021,'IsPurchased',TO_DATE('2012-08-20 13:09:04','YYYY-MM-DD HH24:MI:SS'),100,'Organization purchases this product','EE01',1,'The Purchased check box indicates if this product is purchased by this organization.','Y','N','N','N','N','N','N','N','N','N','Purchased',TO_DATE('2012-08-20 13:09:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 20, 2012 1:09:05 PM CDT
-- MFG-31
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64179 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 20, 2012 1:09:06 PM CDT
-- MFG-31
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64180,453,0,19,163,53021,'M_Product_Category_ID',TO_DATE('2012-08-20 13:09:05','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','EE01',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','N','N','Product Category',TO_DATE('2012-08-20 13:09:05','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 20, 2012 1:09:06 PM CDT
-- MFG-31
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64180 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 20, 2012 1:10:09 PM CDT
-- MFG-31
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=131,Updated=TO_DATE('2012-08-20 13:10:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53426
;

-- Aug 20, 2012 1:11:15 PM CDT
-- MFG-31
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=286,Updated=TO_DATE('2012-08-20 13:11:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=62043
;

-- Aug 20, 2012 1:11:28 PM CDT
-- MFG-31
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=154,Updated=TO_DATE('2012-08-20 13:11:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53436
;

-- Aug 20, 2012 1:11:40 PM CDT
-- MFG-31
UPDATE AD_Column SET AD_Val_Rule_ID=52002,Updated=TO_DATE('2012-08-20 13:11:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53438
;

-- Aug 20, 2012 1:12:38 PM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64178,65113,0,53180,TO_DATE('2012-08-20 13:12:37','YYYY-MM-DD HH24:MI:SS'),100,'Bill of Materials',1,'EE01','The Bill of Materials check box indicates if this product consists of a bill of materials.','Y','Y','Y','N','N','N','N','N','Bill of Materials',TO_DATE('2012-08-20 13:12:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 1:12:38 PM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65113 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 1:12:45 PM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64180,65120,0,53180,TO_DATE('2012-08-20 13:12:44','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product',10,'EE01','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','Y','N','N','N','N','N','Product Category',TO_DATE('2012-08-20 13:12:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 1:12:45 PM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65120 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 1:12:46 PM CDT
-- MFG-31
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64179,65121,0,53180,TO_DATE('2012-08-20 13:12:45','YYYY-MM-DD HH24:MI:SS'),100,'Organization purchases this product',1,'EE01','The Purchased check box indicates if this product is purchased by this organization.','Y','Y','Y','N','N','N','N','N','Purchased',TO_DATE('2012-08-20 13:12:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 1:12:46 PM CDT
-- MFG-31
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65121 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=65112
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=65113
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=65117
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=65120
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=65121
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=65122
;

-- Aug 20, 2012 1:17:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=65123
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=65120
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=65113
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=65121
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=65117
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=65122
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Aug 20, 2012 1:18:18 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=65112
;

-- Aug 20, 2012 1:18:28 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y', IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:18:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65120
;

-- Aug 20, 2012 1:18:32 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-08-20 13:18:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65113
;

-- Aug 20, 2012 1:18:35 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y', IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:18:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65121
;

-- Aug 20, 2012 1:18:41 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-08-20 13:18:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65117
;

-- Aug 20, 2012 1:18:44 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y', IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:18:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65122
;

-- Aug 20, 2012 1:18:51 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-08-20 13:18:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56444
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56430
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56421
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56436
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=65120
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=65113
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=65121
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=65117
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=65122
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 20, 2012 1:19:55 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Aug 20, 2012 1:20:29 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Aug 20, 2012 1:20:38 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-08-20 13:20:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56443
;

-- Aug 20, 2012 1:20:41 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y', IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:20:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56432
;

-- Aug 20, 2012 1:20:44 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2012-08-20 13:20:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:20:47 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y', IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:20:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:20:50 PM CDT
-- MFG-31
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-08-20 13:20:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:20:58 PM CDT
-- MFG-31
UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2012-08-20 13:20:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:22:08 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=NULL,Updated=TO_DATE('2012-08-20 13:22:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:22:44 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2012-08-20 13:22:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56443
;

-- Aug 20, 2012 1:23:09 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- Aug 20, 2012 1:23:09 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- Aug 20, 2012 1:23:09 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:23:42 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2012-08-20 13:23:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56444
;

-- Aug 20, 2012 1:23:47 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2012-08-20 13:23:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:24:38 PM CDT
-- MFG-31
UPDATE AD_Field SET DisplayLength=39,Updated=TO_DATE('2012-08-20 13:24:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:24:49 PM CDT
-- MFG-31
UPDATE AD_Field SET DisplayLength=22,Updated=TO_DATE('2012-08-20 13:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:25:15 PM CDT
-- MFG-31
UPDATE AD_Field SET DisplayLength=20,Updated=TO_DATE('2012-08-20 13:25:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:25:34 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 20, 2012 1:25:34 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 20, 2012 1:25:53 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 20, 2012 1:26:16 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:26:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56412
;

-- Aug 20, 2012 1:26:43 PM CDT
-- MFG-31
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=65123
;

-- Aug 20, 2012 1:26:43 PM CDT
-- MFG-31
DELETE FROM AD_Field WHERE AD_Field_ID=65123
;

-- Aug 20, 2012 1:26:58 PM CDT
-- MFG-31
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=65112
;

-- Aug 20, 2012 1:26:58 PM CDT
-- MFG-31
DELETE FROM AD_Field WHERE AD_Field_ID=65112
;

-- Aug 20, 2012 1:27:10 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=50003,Updated=TO_DATE('2012-08-20 13:27:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56417
;

-- Aug 20, 2012 1:27:20 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=50003,Updated=TO_DATE('2012-08-20 13:27:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56418
;

-- Aug 20, 2012 1:28:15 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:28:15 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:28:15 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Aug 20, 2012 1:28:15 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Aug 20, 2012 1:28:28 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2012-08-20 13:28:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:28:31 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2012-08-20 13:28:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:28:56 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56436
;

-- Aug 20, 2012 1:28:59 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:28:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65120
;

-- Aug 20, 2012 1:29:02 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:29:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65113
;

-- Aug 20, 2012 1:29:06 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65121
;

-- Aug 20, 2012 1:29:11 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65117
;

-- Aug 20, 2012 1:29:14 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65122
;

-- Aug 20, 2012 1:29:17 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:29:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56440
;

-- Aug 20, 2012 1:29:20 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:29:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56445
;

-- Aug 20, 2012 1:29:23 PM CDT
-- MFG-31
UPDATE AD_Field SET AD_FieldGroup_ID=106,Updated=TO_DATE('2012-08-20 13:29:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:30:13 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:30:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56420
;

-- Aug 20, 2012 1:31:00 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2012-08-20 13:31:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56417
;

-- Aug 20, 2012 1:31:46 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- Aug 20, 2012 1:31:46 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- Aug 20, 2012 1:31:46 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=65117
;

-- Aug 20, 2012 1:31:46 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=65122
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58573
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Aug 20, 2012 1:32:38 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Aug 20, 2012 1:32:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Aug 20, 2012 1:32:39 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Aug 20, 2012 1:32:52 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:32:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:33:13 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56435
;

-- Aug 20, 2012 1:33:13 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Aug 20, 2012 1:33:13 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 20, 2012 1:33:13 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 20, 2012 1:33:13 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Aug 20, 2012 1:33:13 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Aug 20, 2012 1:33:13 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Aug 20, 2012 1:33:13 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Aug 20, 2012 1:33:21 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:33:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56435
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56442
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56444
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56430
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56421
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56436
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=65120
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=65113
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=65121
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=65117
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=65122
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=58573
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=58574
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=58572
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56435
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=56414
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=56416
;

-- Aug 20, 2012 1:34:17 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=56418
;

-- Aug 20, 2012 1:34:28 PM CDT
-- MFG-31
UPDATE AD_Field SET DisplayLength=30,Updated=TO_DATE('2012-08-20 13:34:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56442
;

-- Aug 20, 2012 1:34:36 PM CDT
-- MFG-31
UPDATE AD_Field SET DisplayLength=30, IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:34:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56444
;

-- Aug 20, 2012 1:35:06 PM CDT
-- MFG-31
UPDATE AD_Field SET DisplayLength=22,Updated=TO_DATE('2012-08-20 13:35:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56444
;

-- Aug 20, 2012 1:35:14 PM CDT
-- MFG-31
UPDATE AD_Field SET DisplayLength=22,Updated=TO_DATE('2012-08-20 13:35:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56442
;

-- Aug 20, 2012 1:37:14 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56419
;

-- Aug 20, 2012 1:37:14 PM CDT
-- MFG-31
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- Aug 20, 2012 1:37:45 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2012-08-20 13:37:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56420
;

-- Aug 20, 2012 1:37:49 PM CDT
-- MFG-31
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-08-20 13:37:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56417
;

-- Aug 20, 2012 1:44:45 PM CDT
-- MFG-31
UPDATE AD_Process SET Name='Regenerative Material Plan', Value='MRP_Regenerative Material Plan',Updated=TO_DATE('2012-08-20 13:44:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53016
;

-- Aug 20, 2012 1:44:45 PM CDT
-- MFG-31
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53016
;

-- Aug 20, 2012 1:44:45 PM CDT
-- MFG-31
UPDATE AD_Menu SET Description='This process calcualte the demand, approved and open orders for a product.', IsActive='Y', Name='Regenerative Material Plan',Updated=TO_DATE('2012-08-20 13:44:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53038
;

-- Aug 20, 2012 1:44:45 PM CDT
-- MFG-31
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53038
;

-- Aug 20, 2012 1:47:32 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET AD_Element_ID=NULL, ColumnName='Synchronize', IsCentrallyMaintained='N', Name='Synchronize with DRP',Updated=TO_DATE('2012-08-20 13:47:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53149
;

-- Aug 20, 2012 1:47:32 PM CDT
-- MFG-31
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53149
;

-- Aug 20, 2012 1:48:23 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET IsActive='N',Updated=TO_DATE('2012-08-20 13:48:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53053
;

-- Aug 20, 2012 1:50:00 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Product Planning', Help='It defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.',Updated=TO_DATE('2012-08-20 13:50:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53268
;

-- Aug 20, 2012 1:50:00 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53268
;

-- Aug 20, 2012 1:50:00 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='PP_Product_Planning_ID', Name='Product Planning', Description='Product Planning', Help='It defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.' WHERE AD_Element_ID=53268
;

-- Aug 20, 2012 1:50:00 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='PP_Product_Planning_ID', Name='Product Planning', Description='Product Planning', Help='It defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.', AD_Element_ID=53268 WHERE UPPER(ColumnName)='PP_PRODUCT_PLANNING_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:50:00 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='PP_Product_Planning_ID', Name='Product Planning', Description='Product Planning', Help='It defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.' WHERE AD_Element_ID=53268 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:50:00 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Product Planning', Description='Product Planning', Help='It defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53268) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:50:52 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='It identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.',Updated=TO_DATE('2012-08-20 13:50:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53340
;

-- Aug 20, 2012 1:50:52 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53340
;

-- Aug 20, 2012 1:50:52 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description='It identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.' WHERE AD_Element_ID=53340
;

-- Aug 20, 2012 1:50:52 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description='It identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.', AD_Element_ID=53340 WHERE UPPER(ColumnName)='DD_NETWORKDISTRIBUTION_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:50:52 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description='It identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.' WHERE AD_Element_ID=53340 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:50:52 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Network Distribution', Description='It identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53340) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:51:21 PM CDT
-- MFG-31
UPDATE AD_Column SET Description='Identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain',Updated=TO_DATE('2012-08-20 13:51:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54310
;

-- Aug 20, 2012 1:51:21 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Network Distribution', Description='Identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.' WHERE AD_Column_ID=54310 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:51:38 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain',Updated=TO_DATE('2012-08-20 13:51:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53340
;

-- Aug 20, 2012 1:51:38 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53340
;

-- Aug 20, 2012 1:51:38 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description='Identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.' WHERE AD_Element_ID=53340
;

-- Aug 20, 2012 1:51:38 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description='Identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.', AD_Element_ID=53340 WHERE UPPER(ColumnName)='DD_NETWORKDISTRIBUTION_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:51:38 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description='Identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.' WHERE AD_Element_ID=53340 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:51:38 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Network Distribution', Description='Identifies a distribution network, distribution networks are used to establish the source and target of the materials in the supply chain', Help='DRP uses the distribution networks to generate the distribution plan.

A distribution network defines the supply path by a relationship between the source and target warehouse and a percentage of the supply quantity.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53340) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:52:01 PM CDT
-- MFG-31
UPDATE AD_Element SET Help='Defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.',Updated=TO_DATE('2012-08-20 13:52:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53268
;

-- Aug 20, 2012 1:52:01 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53268
;

-- Aug 20, 2012 1:52:01 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='PP_Product_Planning_ID', Name='Product Planning', Description='Product Planning', Help='Defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.' WHERE AD_Element_ID=53268
;

-- Aug 20, 2012 1:52:01 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='PP_Product_Planning_ID', Name='Product Planning', Description='Product Planning', Help='Defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.', AD_Element_ID=53268 WHERE UPPER(ColumnName)='PP_PRODUCT_PLANNING_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:52:01 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='PP_Product_Planning_ID', Name='Product Planning', Description='Product Planning', Help='Defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.' WHERE AD_Element_ID=53268 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:52:01 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Product Planning', Description='Product Planning', Help='Defines the planning data for each product to be able to calculate the Materials plan, these data can be maintained by Organization, Plant and Warehouse.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53268) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:54:37 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.

This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.',Updated=TO_DATE('2012-08-20 13:54:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:54:37 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:54:37 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.

This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:54:37 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.

This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.', AD_Element_ID=53261 WHERE UPPER(ColumnName)='ISMPS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:54:37 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.

This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Element_ID=53261 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:54:37 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.

This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53261) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:55:05 PM CDT
-- MFG-31
UPDATE AD_Element SET Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.',Updated=TO_DATE('2012-08-20 13:55:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:05 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:05 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:05 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.', AD_Element_ID=53261 WHERE UPPER(ColumnName)='ISMPS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:55:05 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Element_ID=53261 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:55:05 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53261) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:55:23 PM CDT
-- MFG-31
UPDATE AD_Element SET Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.',Updated=TO_DATE('2012-08-20 13:55:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:23 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:23 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:23 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.', AD_Element_ID=53261 WHERE UPPER(ColumnName)='ISMPS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:55:23 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Element_ID=53261 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:55:23 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Is MPS', Description='Product of Master Production Shechelude', Help='Indicates if this product is part of the master production schedule, the independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53261) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:55:57 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Indicates if this product is part of the master production schedule', Help='The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.',Updated=TO_DATE('2012-08-20 13:55:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:57 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:57 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='IsMPS', Name='Is MPS', Description='Indicates if this product is part of the master production schedule', Help='The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Element_ID=53261
;

-- Aug 20, 2012 1:55:57 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Indicates if this product is part of the master production schedule', Help='The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.', AD_Element_ID=53261 WHERE UPPER(ColumnName)='ISMPS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:55:57 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsMPS', Name='Is MPS', Description='Indicates if this product is part of the master production schedule', Help='The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Element_ID=53261 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:55:57 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Is MPS', Description='Indicates if this product is part of the master production schedule', Help='The independent demand products such as end products or spare parts, should be part of the MPS.
<br>
<br>
This flag is used to segregate the products to be used in reports and inquiries of the MPS and allows to calculate the MPS by the execution of a selective MRP process.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53261) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:57:33 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Is DRP Required', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.', Name='Is DRP Required', PrintName='Is DRP Required',Updated=TO_DATE('2012-08-20 13:57:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53470
;

-- Aug 20, 2012 1:57:33 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53470
;

-- Aug 20, 2012 1:57:33 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='IsRequiredDRP', Name='Is DRP Required', Description='Is DRP Required', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Element_ID=53470
;

-- Aug 20, 2012 1:57:33 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsRequiredDRP', Name='Is DRP Required', Description='Is DRP Required', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.', AD_Element_ID=53470 WHERE UPPER(ColumnName)='ISREQUIREDDRP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:57:33 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='IsRequiredDRP', Name='Is DRP Required', Description='Is DRP Required', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Element_ID=53470 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:57:33 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Is DRP Required', Description='Is DRP Required', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53470) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:57:34 PM CDT
-- MFG-31
UPDATE AD_PrintFormatItem pi SET PrintName='Is DRP Required', Name='Is DRP Required' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53470)
;

-- Aug 20, 2012 1:58:14 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.',Updated=TO_DATE('2012-08-20 13:58:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53266
;

-- Aug 20, 2012 1:58:14 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53266
;

-- Aug 20, 2012 1:58:14 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='Order_Policy', Name='Order Policy', Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Element_ID=53266
;

-- Aug 20, 2012 1:58:14 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Policy', Name='Order Policy', Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.', AD_Element_ID=53266 WHERE UPPER(ColumnName)='ORDER_POLICY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:58:14 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Policy', Name='Order Policy', Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Element_ID=53266 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:58:14 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Order Policy', Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53266) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:59:57 PM CDT
-- MFG-31
UPDATE AD_Element SET Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.
<br>
<p>
Lot-For-Lot  (LFL): Creates planned orders to satisfy the demand, an order is created to satisfy each net requirement. so MRP process must generate one planned order for each demand not satisfied.
<p>
Period Order Quantity (POQ): Creates planned orders to satisfy the demand, the requirements are accumulated in a defined period and a planned order is created for the period quantity accumulation. The number of days are entered in the field Order Period.
<p>
Use  Fixed Order Quantity when you always need to ask for  the same Quantity of product, this Quantity is entered in the field Order Qty.
<br><br>
If the order policy is not FOQ and you enter a quantity in the Order Qty field, this quantity is the Economic Order Quantity.',Updated=TO_DATE('2012-08-20 13:59:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53266
;

-- Aug 20, 2012 1:59:57 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53266
;

-- Aug 20, 2012 1:59:57 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='Order_Policy', Name='Order Policy', Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.
<br>
<p>
Lot-For-Lot  (LFL): Creates planned orders to satisfy the demand, an order is created to satisfy each net requirement. so MRP process must generate one planned order for each demand not satisfied.
<p>
Period Order Quantity (POQ): Creates planned orders to satisfy the demand, the requirements are accumulated in a defined period and a planned order is created for the period quantity accumulation. The number of days are entered in the field Order Period.
<p>
Use  Fixed Order Quantity when you always need to ask for  the same Quantity of product, this Quantity is entered in the field Order Qty.
<br><br>
If the order policy is not FOQ and you enter a quantity in the Order Qty field, this quantity is the Economic Order Quantity.' WHERE AD_Element_ID=53266
;

-- Aug 20, 2012 1:59:57 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Policy', Name='Order Policy', Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.
<br>
<p>
Lot-For-Lot  (LFL): Creates planned orders to satisfy the demand, an order is created to satisfy each net requirement. so MRP process must generate one planned order for each demand not satisfied.
<p>
Period Order Quantity (POQ): Creates planned orders to satisfy the demand, the requirements are accumulated in a defined period and a planned order is created for the period quantity accumulation. The number of days are entered in the field Order Period.
<p>
Use  Fixed Order Quantity when you always need to ask for  the same Quantity of product, this Quantity is entered in the field Order Qty.
<br><br>
If the order policy is not FOQ and you enter a quantity in the Order Qty field, this quantity is the Economic Order Quantity.', AD_Element_ID=53266 WHERE UPPER(ColumnName)='ORDER_POLICY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 1:59:57 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Policy', Name='Order Policy', Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.
<br>
<p>
Lot-For-Lot  (LFL): Creates planned orders to satisfy the demand, an order is created to satisfy each net requirement. so MRP process must generate one planned order for each demand not satisfied.
<p>
Period Order Quantity (POQ): Creates planned orders to satisfy the demand, the requirements are accumulated in a defined period and a planned order is created for the period quantity accumulation. The number of days are entered in the field Order Period.
<p>
Use  Fixed Order Quantity when you always need to ask for  the same Quantity of product, this Quantity is entered in the field Order Qty.
<br><br>
If the order policy is not FOQ and you enter a quantity in the Order Qty field, this quantity is the Economic Order Quantity.' WHERE AD_Element_ID=53266 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 1:59:57 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Order Policy', Description='Order Policy', Help='If the DRP Required checkbox is ticked, this means it has been a change in some element which affect the material plan  for this product, i.e Network Distribution, Orders, Inventory, MPS, etc. and therefore  you need to executed again DRP to adjust the Planned Orders to the new conditions and to get the updated action messages.
<br>
<p>
Lot-For-Lot  (LFL): Creates planned orders to satisfy the demand, an order is created to satisfy each net requirement. so MRP process must generate one planned order for each demand not satisfied.
<p>
Period Order Quantity (POQ): Creates planned orders to satisfy the demand, the requirements are accumulated in a defined period and a planned order is created for the period quantity accumulation. The number of days are entered in the field Order Period.
<p>
Use  Fixed Order Quantity when you always need to ask for  the same Quantity of product, this Quantity is entered in the field Order Qty.
<br><br>
If the order policy is not FOQ and you enter a quantity in the Order Qty field, this quantity is the Economic Order Quantity.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53266) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:00:52 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Order Qty', Help='Define the fixed quantity to be ordered when the order policy used is FOQ',Updated=TO_DATE('2012-08-20 14:00:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53267
;

-- Aug 20, 2012 2:00:52 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53267
;

-- Aug 20, 2012 2:00:52 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='Order_Qty', Name='Order Qty', Description='Order Qty', Help='Define the fixed quantity to be ordered when the order policy used is FOQ' WHERE AD_Element_ID=53267
;

-- Aug 20, 2012 2:00:52 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Qty', Name='Order Qty', Description='Order Qty', Help='Define the fixed quantity to be ordered when the order policy used is FOQ', AD_Element_ID=53267 WHERE UPPER(ColumnName)='ORDER_QTY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 2:00:52 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Qty', Name='Order Qty', Description='Order Qty', Help='Define the fixed quantity to be ordered when the order policy used is FOQ' WHERE AD_Element_ID=53267 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:00:52 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Order Qty', Description='Order Qty', Help='Define the fixed quantity to be ordered when the order policy used is FOQ' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53267) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:01:16 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ',Updated=TO_DATE('2012-08-20 14:01:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53265
;

-- Aug 20, 2012 2:01:16 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53265
;

-- Aug 20, 2012 2:01:16 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='Order_Period', Name='Order Period', Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ' WHERE AD_Element_ID=53265
;

-- Aug 20, 2012 2:01:16 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Period', Name='Order Period', Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ', AD_Element_ID=53265 WHERE UPPER(ColumnName)='ORDER_PERIOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 2:01:16 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Period', Name='Order Period', Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ' WHERE AD_Element_ID=53265 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:01:16 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Order Period', Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53265) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:01:23 PM CDT
-- MFG-31
UPDATE AD_Element SET Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ.',Updated=TO_DATE('2012-08-20 14:01:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53265
;

-- Aug 20, 2012 2:01:23 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53265
;

-- Aug 20, 2012 2:01:23 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='Order_Period', Name='Order Period', Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ.' WHERE AD_Element_ID=53265
;

-- Aug 20, 2012 2:01:23 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Period', Name='Order Period', Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ.', AD_Element_ID=53265 WHERE UPPER(ColumnName)='ORDER_PERIOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 2:01:23 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='Order_Period', Name='Order Period', Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ.' WHERE AD_Element_ID=53265 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:01:23 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Order Period', Description='Order Period', Help='Number of calendar days used to accumulate  the net requirements to integrate the quantity of a planned order under the policy of POQ.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53265) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:15:07 PM CDT
-- MFG-31
UPDATE AD_Element SET Description='The Time Fence is the number of days since you execute the MRP process inside of which  the system must not change the planned orders. ', Help='The system will generate  action messages warning if some order needs to be modified or created into the time fence.
<p>
The Limit time is used for the master plan products, the number of days is the equal or bigger than the product’s delivery time.
<p>
It is recommended to establish a limit time, so you don’t have a nervous manufacturing system or a systems that reacts to any change or plan modification.',Updated=TO_DATE('2012-08-20 14:15:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53270
;

-- Aug 20, 2012 2:15:07 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53270
;

-- Aug 20, 2012 2:15:07 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='TimeFence', Name='Time Fence', Description='The Time Fence is the number of days since you execute the MRP process inside of which  the system must not change the planned orders. ', Help='The system will generate  action messages warning if some order needs to be modified or created into the time fence.
<p>
The Limit time is used for the master plan products, the number of days is the equal or bigger than the product’s delivery time.
<p>
It is recommended to establish a limit time, so you don’t have a nervous manufacturing system or a systems that reacts to any change or plan modification.' WHERE AD_Element_ID=53270
;

-- Aug 20, 2012 2:15:07 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='TimeFence', Name='Time Fence', Description='The Time Fence is the number of days since you execute the MRP process inside of which  the system must not change the planned orders. ', Help='The system will generate  action messages warning if some order needs to be modified or created into the time fence.
<p>
The Limit time is used for the master plan products, the number of days is the equal or bigger than the product’s delivery time.
<p>
It is recommended to establish a limit time, so you don’t have a nervous manufacturing system or a systems that reacts to any change or plan modification.', AD_Element_ID=53270 WHERE UPPER(ColumnName)='TIMEFENCE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 2:15:07 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='TimeFence', Name='Time Fence', Description='The Time Fence is the number of days since you execute the MRP process inside of which  the system must not change the planned orders. ', Help='The system will generate  action messages warning if some order needs to be modified or created into the time fence.
<p>
The Limit time is used for the master plan products, the number of days is the equal or bigger than the product’s delivery time.
<p>
It is recommended to establish a limit time, so you don’t have a nervous manufacturing system or a systems that reacts to any change or plan modification.' WHERE AD_Element_ID=53270 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:15:07 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Time Fence', Description='The Time Fence is the number of days since you execute the MRP process inside of which  the system must not change the planned orders. ', Help='The system will generate  action messages warning if some order needs to be modified or created into the time fence.
<p>
The Limit time is used for the master plan products, the number of days is the equal or bigger than the product’s delivery time.
<p>
It is recommended to establish a limit time, so you don’t have a nervous manufacturing system or a systems that reacts to any change or plan modification.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53270) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:15:50 PM CDT
-- MFG-31
UPDATE AD_Element SET ColumnName='TransferTime', Description='Transfer Time', Name='Transfer Time', PrintName='Transfer Time',Updated=TO_DATE('2012-08-20 14:15:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53271
;

-- Aug 20, 2012 2:15:50 PM CDT
-- MFG-31
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53271
;

-- Aug 20, 2012 2:15:50 PM CDT
-- MFG-31
UPDATE AD_Column SET ColumnName='TransferTime', Name='Transfer Time', Description='Transfer Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.' WHERE AD_Element_ID=53271
;

-- Aug 20, 2012 2:15:50 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='TransferTime', Name='Transfer Time', Description='Transfer Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.', AD_Element_ID=53271 WHERE UPPER(ColumnName)='TRANSFERTIME' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 2:15:50 PM CDT
-- MFG-31
UPDATE AD_Process_Para SET ColumnName='TransferTime', Name='Transfer Time', Description='Transfer Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.' WHERE AD_Element_ID=53271 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:15:50 PM CDT
-- MFG-31
UPDATE AD_Field SET Name='Transfer Time', Description='Transfer Time', Help='Indicates the number of days the product needs to be moved from one warehouse to another.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53271) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 2:15:50 PM CDT
-- MFG-31
UPDATE AD_PrintFormatItem pi SET PrintName='Transfer Time', Name='Transfer Time' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53271)
;

ALTER TABLE PP_Product_Planning
 RENAME COLUMN TransfertTime to TransferTime;
 
ALTER TABLE I_ProductPlanning
 RENAME COLUMN TransfertTime to TransferTime;

ALTER TABLE DD_NetworkDistributionLine
 RENAME COLUMN TransfertTime to TransferTime;
 
ALTER TABLE pp_product_planning ADD CONSTRAINT pp_product_planning_unique UNIQUE (ad_client_id, ad_org_id, s_resource_id , m_warehouse_id , m_product_id); 