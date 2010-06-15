SET SQLBLANKLINES ON
SET DEFINE OFF
-- Dec 2, 2009 2:53:35 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,13469,0,53289,771,140,TO_DATE('2009-12-02 14:53:34','YYYY-MM-DD HH24:MI:SS'),100,'D','N',NULL,'N','Y','N','Y','Y','N','N','N','N','Costs','N',150,0,TO_DATE('2009-12-02 14:53:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:35 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53289 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Dec 2, 2009 2:53:46 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,13467,58303,0,53289,TO_DATE('2009-12-02 14:53:45','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',14,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','Y','N','Client',10,TO_DATE('2009-12-02 14:53:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:46 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58303 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:47 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,13468,58304,0,53289,TO_DATE('2009-12-02 14:53:46','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',14,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','Y','Organization',20,TO_DATE('2009-12-02 14:53:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:47 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58304 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:48 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,13469,58305,0,53289,TO_DATE('2009-12-02 14:53:47','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',26,'D','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','Y','N','Product',30,TO_DATE('2009-12-02 14:53:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:48 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58305 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:48 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,14196,58306,0,53289,TO_DATE('2009-12-02 14:53:48','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance',10,'D','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','Y','N','N','N','N','Y','Attribute Set Instance',40,1,TO_DATE('2009-12-02 14:53:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:48 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58306 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:49 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,13471,58307,0,53289,TO_DATE('2009-12-02 14:53:48','YYYY-MM-DD HH24:MI:SS'),100,'Rules for accounting',14,'D','An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','Y','N','N','N','N','N','Accounting Schema',50,2,TO_DATE('2009-12-02 14:53:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:49 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58307 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:50 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,13470,58308,0,53289,TO_DATE('2009-12-02 14:53:49','YYYY-MM-DD HH24:MI:SS'),100,'Type of Cost (e.g. Current, Plan, Future)',14,'D','You can define multiple cost types. A cost type selected in an Accounting Schema is used for accounting.','Y','Y','Y','N','N','N','N','Y','Cost Type',60,3,TO_DATE('2009-12-02 14:53:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:50 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58308 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,13472,58309,0,53289,TO_DATE('2009-12-02 14:53:50','YYYY-MM-DD HH24:MI:SS'),100,'Product Cost Element',14,'D','Y','Y','Y','N','N','N','N','N','Cost Element',70,4,TO_DATE('2009-12-02 14:53:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58309 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:52 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,13480,58310,0,53289,TO_DATE('2009-12-02 14:53:51','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',60,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',80,TO_DATE('2009-12-02 14:53:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:52 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58310 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:53 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,13473,58311,0,53289,TO_DATE('2009-12-02 14:53:52','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',90,TO_DATE('2009-12-02 14:53:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:53 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58311 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:55 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,13481,58312,0,53289,TO_DATE('2009-12-02 14:53:53','YYYY-MM-DD HH24:MI:SS'),100,'Indicates how Costs will be calculated',1,'1=2','D','The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FoFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).','Y','Y','Y','N','N','N','Y','Y','Costing Method',100,TO_DATE('2009-12-02 14:53:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:55 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58312 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:55 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,13478,58313,0,53289,TO_DATE('2009-12-02 14:53:55','YYYY-MM-DD HH24:MI:SS'),100,'The currently used cost price',26,'D','Y','Y','Y','N','N','N','N','N','Current Cost Price',110,TO_DATE('2009-12-02 14:53:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:55 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58313 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:56 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,56076,58314,0,53289,TO_DATE('2009-12-02 14:53:55','YYYY-MM-DD HH24:MI:SS'),100,'Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.',22,'D','Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
','Y','Y','Y','N','N','N','Y','Y','Current Cost Price Lower Level',120,TO_DATE('2009-12-02 14:53:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:56 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58314 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:57 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,13479,58315,0,53289,TO_DATE('2009-12-02 14:53:56','YYYY-MM-DD HH24:MI:SS'),100,26,'@CostingMethod@=S','D','Y','Y','Y','N','N','N','N','N','Future Cost Price',130,TO_DATE('2009-12-02 14:53:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:57 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58315 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:57 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,56683,58316,0,53289,TO_DATE('2009-12-02 14:53:57','YYYY-MM-DD HH24:MI:SS'),100,22,'@CostingMethod@=S','D','Y','Y','Y','N','N','N','Y','Y','Future Cost Price Lower Level',140,TO_DATE('2009-12-02 14:53:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:57 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58316 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:58 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,14394,58317,0,53289,TO_DATE('2009-12-02 14:53:57','YYYY-MM-DD HH24:MI:SS'),100,'Percentage',10,'@CostingMethod@=x','D','The Percent indicates the percentage used.','Y','Y','Y','N','N','N','N','N','Percent',150,TO_DATE('2009-12-02 14:53:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:58 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58317 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:59 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,14401,58318,0,53289,TO_DATE('2009-12-02 14:53:58','YYYY-MM-DD HH24:MI:SS'),100,'Current Quantity',22,'D','Y','Y','Y','N','N','N','Y','N','Current Quantity',160,TO_DATE('2009-12-02 14:53:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:59 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58318 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:53:59 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,56684,58319,0,53289,TO_DATE('2009-12-02 14:53:59','YYYY-MM-DD HH24:MI:SS'),100,'Indicated that the Standard Cost is frozen',1,'@CostingMethod@=S','D','Y','Y','Y','N','N','N','Y','Y','Cost Frozen',170,TO_DATE('2009-12-02 14:53:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:53:59 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58319 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:54:00 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,14201,58320,0,53289,TO_DATE('2009-12-02 14:53:59','YYYY-MM-DD HH24:MI:SS'),100,'Total Amount',22,'D','Sum of all amounts','Y','Y','Y','N','N','N','Y','N','Accumulated Amt',180,TO_DATE('2009-12-02 14:53:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:54:00 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58320 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:54:01 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,14202,58321,0,53289,TO_DATE('2009-12-02 14:54:00','YYYY-MM-DD HH24:MI:SS'),100,'Total Quantity',22,'D','Sum of the quantities','Y','Y','Y','N','N','N','Y','Y','Accumulated Qty',190,TO_DATE('2009-12-02 14:54:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:54:01 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58321 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:54:01 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,14395,58322,0,53289,TO_DATE('2009-12-02 14:54:01','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',0,'1=2','D','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','N','Y','N','Processed',200,TO_DATE('2009-12-02 14:54:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 2:54:01 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58322 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58311
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58319
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58314
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58310
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58316
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58317
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58322
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=58307
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=58305
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=58306
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=58312
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=58313
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=58315
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=58318
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=58320
;

-- Dec 2, 2009 2:54:54 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58321
;

-- Dec 2, 2009 2:57:16 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2009-12-02 14:57:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53289
;

-- Dec 2, 2009 3:11:14 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
--INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58688,275,0,10,208,'Description',TO_DATE('2009-12-02 15:11:13','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',1,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-12-02 15:11:13','YYYY-MM-DD HH24:MI:SS'),100,0)
--;

-- Dec 2, 2009 3:12:08 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58689,2813,0,10,208,'TestValue',TO_DATE('2009-12-02 15:12:07','YYYY-MM-DD HH24:MI:SS'),100,'Value to test','D',1,'Y','N','N','N','N','N','N','N','N','N','N','N','Y','Test Value',0,TO_DATE('2009-12-02 15:12:07','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 2, 2009 3:12:08 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58689 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 2, 2009 3:12:18 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
ALTER TABLE M_Product ADD TestValue NVARCHAR2(1) DEFAULT NULL 
;

-- Dec 2, 2009 3:12:52 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Included_Tab_ID,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58689,58323,0,180,TO_DATE('2009-12-02 15:12:51','YYYY-MM-DD HH24:MI:SS'),100,'Value to test',0,'D',53289,'Y','Y','Y','N','N','N','N','N','Test Value',540,0,TO_DATE('2009-12-02 15:12:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 2, 2009 3:12:52 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58323 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58307
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58303
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58304
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58305
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=58306
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=58308
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=58309
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=58312
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=58313
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=58315
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=58318
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=58320
;

-- Dec 2, 2009 3:14:20 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=58321
;

-- Dec 2, 2009 3:14:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58308
;

-- Dec 2, 2009 3:14:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=58309
;

-- Dec 2, 2009 3:14:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=58312
;

-- Dec 2, 2009 3:14:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=58313
;

-- Dec 2, 2009 3:14:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=58315
;

-- Dec 2, 2009 3:14:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=58318
;

-- Dec 2, 2009 3:14:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=58320
;

-- Dec 2, 2009 3:14:51 PM CET
-- FR [2906574] - Product Central Data; https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2906574&group_id=176962
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=58321
;

