-- Aug 28, 2009 6:16:12 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53244,250,140,TO_DATE('2009-08-28 18:16:11','YYYY-MM-DD HH24:MI:SS'),100,'Where are my units located?','D','N','N','Y','N','N','N','Y','N','N','N','Located at','N',130,2,TO_DATE('2009-08-28 18:16:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:12 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53244 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Aug 28, 2009 6:16:42 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,1974,57390,0,53244,TO_DATE('2009-08-28 18:16:41','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-08-28 18:16:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:42 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57390 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:42 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,8762,57391,0,53244,TO_DATE('2009-08-28 18:16:42','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance',22,'D','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','Y','N','N','N','N','N','Attribute Set Instance',TO_DATE('2009-08-28 18:16:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:42 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57391 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:43 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,1973,57392,0,53244,TO_DATE('2009-08-28 18:16:42','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-08-28 18:16:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:43 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57392 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:44 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,3884,57393,0,53244,TO_DATE('2009-08-28 18:16:43','YYYY-MM-DD HH24:MI:SS'),100,'Date of Last Inventory Count',7,'D','The Date Last Inventory Count indicates the last time an Inventory count was done.','Y','Y','Y','N','N','N','N','N','Date last inventory count',TO_DATE('2009-08-28 18:16:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:44 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57393 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:44 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,1979,57394,0,53244,TO_DATE('2009-08-28 18:16:44','YYYY-MM-DD HH24:MI:SS'),100,'Warehouse Locator',22,'D','The Locator indicates where in a Warehouse a product is located.','Y','Y','Y','N','N','N','N','N','Locator',TO_DATE('2009-08-28 18:16:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:44 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57394 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:46 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,2025,57395,0,53244,TO_DATE('2009-08-28 18:16:44','YYYY-MM-DD HH24:MI:SS'),100,'On Hand Quantity',22,'D','The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.','Y','Y','Y','N','N','N','N','N','On Hand Quantity',TO_DATE('2009-08-28 18:16:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:46 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57395 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:47 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4382,57396,0,53244,TO_DATE('2009-08-28 18:16:46','YYYY-MM-DD HH24:MI:SS'),100,'Ordered Quantity',22,'D','The Ordered Quantity indicates the quantity of a product that was ordered.','Y','Y','Y','N','N','N','N','N','Ordered Quantity',TO_DATE('2009-08-28 18:16:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:47 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57396 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:48 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,1972,57397,0,53244,TO_DATE('2009-08-28 18:16:47','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-08-28 18:16:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:48 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57397 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:48 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,1980,57398,0,53244,TO_DATE('2009-08-28 18:16:48','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',22,'D','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','N','Product',TO_DATE('2009-08-28 18:16:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:48 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57398 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:16:49 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,2026,57399,0,53244,TO_DATE('2009-08-28 18:16:48','YYYY-MM-DD HH24:MI:SS'),100,'Reserved Quantity',22,'D','The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','Y','Y','N','N','N','N','N','Reserved Quantity',TO_DATE('2009-08-28 18:16:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2009 6:16:49 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57399 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=57392
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=57397
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=57398
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=57390
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57391
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57393
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57394
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57395
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57396
;

-- Aug 28, 2009 6:17:19 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=57399
;

-- Aug 28, 2009 6:18:25 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2009-08-28 18:18:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53244
;

-- Aug 28, 2009 6:19:37 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-08-28 18:19:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57397
;

-- Aug 28, 2009 6:20:02 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-08-28 18:20:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57393
;

-- Aug 28, 2009 6:20:14 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-08-28 18:20:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57399
;

-- Aug 28, 2009 6:21:51 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57394
;

-- Aug 28, 2009 6:21:51 PM EEST
--FR [1768749] Product located At - https://sourceforge.net/tracker/?func=detail&aid=1768749&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57393
;

