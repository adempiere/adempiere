-- Nov 29, 2009 12:18:52 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54078,0,'IsManufacturer',TO_DATE('2009-11-29 00:18:51','YYYY-MM-DD HH24:MI:SS'),100,'Indicate role of this Business partner as Manufacturer','D','Y','Manufacturer','Manufacturer',TO_DATE('2009-11-29 00:18:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:18:52 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54078 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Nov 29, 2009 12:19:37 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58596,54078,0,20,291,'IsManufacturer',TO_DATE('2009-11-29 00:19:36','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Indicate role of this Business partner as Manufacturer','U',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Manufacturer',0,TO_DATE('2009-11-29 00:19:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 29, 2009 12:19:37 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58596 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 29, 2009 12:19:45 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

ALTER TABLE C_BPartner ADD IsManufacturer CHAR(1) DEFAULT NULL  CHECK (IsManufacturer IN ('Y','N'))
;

-- Nov 29, 2009 12:21:44 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,2893,0,53288,291,123,TO_DATE('2009-11-29 00:21:44','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','Y','N','Y','N','N','Manufacturer','N',130,0,TO_DATE('2009-11-29 00:21:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:21:44 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53288 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Nov 29, 2009 12:23:34 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,12533,58128,0,53288,TO_DATE('2009-11-29 00:23:32','YYYY-MM-DD HH24:MI:SS'),100,'Total Open Balance Amount in primary Accounting Currency',26,'D','The Total Open Balance Amount is the calculated open item amount for Customer and Vendor activity.  If the Balance is below zero, we owe the Business Partner.  The amout is used for Credit Management.
Invoices and Payment Allocations determine the Open Balance (i.e. not Orders or Payments).','Y','Y','N','N','N','N','N','N','Open Balance',0,TO_DATE('2009-11-29 00:23:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:34 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58128 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:37 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,8768,58129,0,53288,TO_DATE('2009-11-29 00:23:34','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Parent',14,'D','The parent (organization) of the Business Partner for reporting purposes.','Y','Y','N','N','N','N','N','N','Partner Parent',0,TO_DATE('2009-11-29 00:23:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:37 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58129 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:37 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,9332,58130,0,53288,TO_DATE('2009-11-29 00:23:37','YYYY-MM-DD HH24:MI:SS'),100,'Print Format for printing Invoices',14,'D','You need to define a Print Format to print the document.','Y','Y','N','N','N','N','N','N','Invoice Print Format',0,TO_DATE('2009-11-29 00:23:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:37 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58130 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:38 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,9862,58131,0,53288,TO_DATE('2009-11-29 00:23:37','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Credit Status',14,'D','Credit Management is inactive if Credit Status is No Credit Check, Credit Stop or if the Credit Limit is 0.
If active, the status is set automatically set to Credit Hold, if the Total Open Balance (including Vendor activities)  is higher then the Credit Limit. It is set to Credit Watch, if above 90% of the Credit Limit and Credit OK otherwise.','Y','Y','N','N','N','N','N','N','Credit Status',0,TO_DATE('2009-11-29 00:23:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:38 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58131 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:38 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,10122,58132,0,53288,TO_DATE('2009-11-29 00:23:38','YYYY-MM-DD HH24:MI:SS'),100,'Minimum Shelf Life in percent based on Product Instance Guarantee Date',11,'D','Miminum Shelf Life of products with Guarantee Date instance. If > 0 you cannot select products with a shelf life ((Guarantee Date-Today) / Guarantee Days) less than the minum shelf life, unless you select "Show All"','Y','Y','N','N','N','N','N','N','Min Shelf Life %',0,TO_DATE('2009-11-29 00:23:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:38 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58132 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:39 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,10927,58133,0,53288,TO_DATE('2009-11-29 00:23:38','YYYY-MM-DD HH24:MI:SS'),100,'The Business Partner is another Organization for explicit Inter-Org transactions',23,'D','The business partner is another organization in the system. So when performing transactions, the counter-document is created automatically. Example: You have BPartnerA linked to OrgA and BPartnerB linked to OrgB.  If you create a sales order for BPartnerB in OrgA a purchase order is created for BPartnerA in OrgB.  This allows to have explicit documents for Inter-Org transactions.','Y','Y','N','N','N','N','N','N','Linked Organization',0,TO_DATE('2009-11-29 00:23:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:39 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58133 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:39 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,12406,58134,0,53288,TO_DATE('2009-11-29 00:23:39','YYYY-MM-DD HH24:MI:SS'),100,'Flat discount percentage ',26,'D','Y','Y','N','N','N','N','N','N','Flat Discount %',0,TO_DATE('2009-11-29 00:23:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:39 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58134 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:40 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2893,58135,0,53288,TO_DATE('2009-11-29 00:23:39','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',14,'D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','N','N','Business Partner ',0,TO_DATE('2009-11-29 00:23:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:40 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58135 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:40 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2903,58136,0,53288,TO_DATE('2009-11-29 00:23:40','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',60,'D','A description is limited to 255 characters.','Y','Y','N','N','N','N','N','N','Description',0,TO_DATE('2009-11-29 00:23:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:40 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58136 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:41 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2904,58137,0,53288,TO_DATE('2009-11-29 00:23:40','YYYY-MM-DD HH24:MI:SS'),100,'Total Volume of Sales in Thousands of Currency',11,'D','The Sales Volume indicates the total volume of sales for a Business Partner.','Y','Y','N','N','N','N','N','N','Sales Volume in 1.000',0,TO_DATE('2009-11-29 00:23:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:41 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58137 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:42 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2905,58138,0,53288,TO_DATE('2009-11-29 00:23:41','YYYY-MM-DD HH24:MI:SS'),100,'Your customer or vendor number at the Business Partner''s site',40,'D','The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.','Y','Y','N','N','N','N','N','N','Reference No',0,TO_DATE('2009-11-29 00:23:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:42 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58138 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:42 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2906,58139,0,53288,TO_DATE('2009-11-29 00:23:42','YYYY-MM-DD HH24:MI:SS'),100,'International Location Number',11,'D','Y','Y','N','N','N','N','N','N','ILN',0,TO_DATE('2009-11-29 00:23:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:43 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58139 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:43 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2907,58140,0,53288,TO_DATE('2009-11-29 00:23:43','YYYY-MM-DD HH24:MI:SS'),100,'Number of employees',11,'D','Indicates the number of employees for this Business Partner.  This field displays only for Prospects.','Y','Y','N','N','N','N','N','N','Employees',0,TO_DATE('2009-11-29 00:23:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:43 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58140 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:44 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2909,58141,0,53288,TO_DATE('2009-11-29 00:23:43','YYYY-MM-DD HH24:MI:SS'),100,'VAT Identification',20,'D','The VAT ID field identifies the legal Identification number of this Entity.','Y','Y','N','N','N','N','N','N','VAT ID',0,TO_DATE('2009-11-29 00:23:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:44 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58141 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:44 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2910,58142,0,53288,TO_DATE('2009-11-29 00:23:44','YYYY-MM-DD HH24:MI:SS'),100,'Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html',6,'D','The NAICS/SIC identifies either of these codes that may be applicable to this Business Partner.','Y','Y','N','N','N','N','N','N','NAICS/SIC',0,TO_DATE('2009-11-29 00:23:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:44 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58142 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:45 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2911,58143,0,53288,TO_DATE('2009-11-29 00:23:44','YYYY-MM-DD HH24:MI:SS'),100,'This is a summary entity',1,'D','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Y','N','N','N','N','N','N','Summary Level',0,TO_DATE('2009-11-29 00:23:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:45 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58143 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:45 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2914,58144,0,53288,TO_DATE('2009-11-29 00:23:45','YYYY-MM-DD HH24:MI:SS'),100,'Language for this entity',14,'D','The Language identifies the language to use for display and formatting','Y','Y','N','N','N','N','N','N','Language',0,TO_DATE('2009-11-29 00:23:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:45 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58144 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:46 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2915,58145,0,53288,TO_DATE('2009-11-29 00:23:45','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this Business Partner is a Vendor',1,'D','The Vendor checkbox indicates if this Business Partner is a Vendor.  If it is selected, additional fields will display which further identify this vendor.','Y','Y','N','N','N','N','N','N','Vendor',0,TO_DATE('2009-11-29 00:23:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:46 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58145 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:46 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2916,58146,0,53288,TO_DATE('2009-11-29 00:23:46','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this Business Partner is a Customer',1,'D','The Customer checkbox indicates if this Business Partner is a customer.  If it is select additional fields will display which further define this customer.','Y','Y','N','N','N','N','N','N','Customer',0,TO_DATE('2009-11-29 00:23:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:46 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58146 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:47 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2917,58147,0,53288,TO_DATE('2009-11-29 00:23:46','YYYY-MM-DD HH24:MI:SS'),100,'Schedule for generating Invoices',14,'D','The Invoice Schedule identifies the frequency used when generating invoices.','Y','Y','N','N','N','N','N','N','Invoice Schedule',0,TO_DATE('2009-11-29 00:23:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:47 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58147 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:48 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2918,58148,0,53288,TO_DATE('2009-11-29 00:23:47','YYYY-MM-DD HH24:MI:SS'),100,'Indicates this is a Prospect',1,'D','The Prospect checkbox indicates an entity that is an active prospect.','Y','Y','N','N','N','N','N','N','Prospect',0,TO_DATE('2009-11-29 00:23:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:48 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58148 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:48 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2919,58149,0,53288,TO_DATE('2009-11-29 00:23:48','YYYY-MM-DD HH24:MI:SS'),100,'Date of First Sale',14,'D','The First Sale Date identifies the date of the first sale to this Business Partner','Y','Y','N','N','N','N','N','N','First Sale',0,TO_DATE('2009-11-29 00:23:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:48 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58149 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:49 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2920,58150,0,53288,TO_DATE('2009-11-29 00:23:48','YYYY-MM-DD HH24:MI:SS'),100,'Total outstanding invoice amounts allowed',26,'D','The Credit Limit indicates the total amount allowed ''on account'' in primary accounting currency.  If the Credit Limit is 0, no ckeck is performed.  Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Y','N','N','N','N','N','N','Credit Limit',0,TO_DATE('2009-11-29 00:23:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:49 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58150 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:49 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2921,58151,0,53288,TO_DATE('2009-11-29 00:23:49','YYYY-MM-DD HH24:MI:SS'),100,'Current open balance',26,'D','The Credit Used indicates the total amount of open or unpaid invoices in primary accounting currency for the Business Partner. Credit Management is based on the Total Open Amount, which includes Vendor activities.','Y','Y','N','N','N','N','N','N','Credit Used',0,TO_DATE('2009-11-29 00:23:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:49 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58151 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:50 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2922,58152,0,53288,TO_DATE('2009-11-29 00:23:49','YYYY-MM-DD HH24:MI:SS'),100,'The cost of gaining the prospect as a customer',26,'D','The Acquisition Cost identifies the cost associated with making this prospect a customer.','Y','Y','N','N','N','N','N','N','Acquisition Cost',0,TO_DATE('2009-11-29 00:23:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:50 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58152 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:50 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2923,58153,0,53288,TO_DATE('2009-11-29 00:23:50','YYYY-MM-DD HH24:MI:SS'),100,'Total Revenue expected',26,'D','The Potential Life Time Value is the anticipated revenue in primary accounting currency to be generated by the Business Partner.','Y','Y','N','N','N','N','N','N','Potential Life Time Value',0,TO_DATE('2009-11-29 00:23:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:50 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58153 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:52 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2924,58154,0,53288,TO_DATE('2009-11-29 00:23:50','YYYY-MM-DD HH24:MI:SS'),100,'The terms of Payment (timing, discount)',14,'D','Payment Terms identify the method and timing of payment.','Y','Y','N','N','N','N','N','N','Payment Term',0,TO_DATE('2009-11-29 00:23:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:52 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58154 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:53 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2925,58155,0,53288,TO_DATE('2009-11-29 00:23:52','YYYY-MM-DD HH24:MI:SS'),100,'Actual Life Time Revenue',26,'D','The Actual Life Time Value is the recorded revenue in primary accounting currency generated by the Business Partner.','Y','Y','N','N','N','N','N','N','Actual Life Time Value',0,TO_DATE('2009-11-29 00:23:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:53 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58155 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:53 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2926,58156,0,53288,TO_DATE('2009-11-29 00:23:53','YYYY-MM-DD HH24:MI:SS'),100,'Share of Customer''s business as a percentage',11,'D','The Share indicates the percentage of this Business Partner''s volume of the products supplied.','Y','Y','N','N','N','N','N','N','Share',0,TO_DATE('2009-11-29 00:23:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:53 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58156 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:54 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2930,58157,0,53288,TO_DATE('2009-11-29 00:23:53','YYYY-MM-DD HH24:MI:SS'),100,'Unique identifier of a Price List',14,'D','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Y','N','N','N','N','N','N','Price List',0,TO_DATE('2009-11-29 00:23:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:54 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58157 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:54 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2931,58158,0,53288,TO_DATE('2009-11-29 00:23:54','YYYY-MM-DD HH24:MI:SS'),100,'Price List used by this Business Partner',14,'D','Identifies the price list used by a Vendor for products purchased by this organization.','Y','Y','N','N','N','N','N','N','Purchase Pricelist',0,TO_DATE('2009-11-29 00:23:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:54 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58158 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:55 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,3080,58159,0,53288,TO_DATE('2009-11-29 00:23:54','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','N','N','N','N','N','N','One time transaction',0,TO_DATE('2009-11-29 00:23:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:55 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58159 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:56 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,3081,58160,0,53288,TO_DATE('2009-11-29 00:23:55','YYYY-MM-DD HH24:MI:SS'),100,'Full URL address - e.g. http://www.adempiere.org',60,'D','The URL defines an fully qualified web address like http://www.adempiere.org','Y','Y','N','N','N','N','N','N','URL',0,TO_DATE('2009-11-29 00:23:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:56 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58160 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:56 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,3082,58161,0,53288,TO_DATE('2009-11-29 00:23:56','YYYY-MM-DD HH24:MI:SS'),100,'Business partner is exempt from tax on sales',1,'D','If a business partner is exempt from tax on sales, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Y','N','N','N','N','N','N','SO Tax exempt',0,TO_DATE('2009-11-29 00:23:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:56 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58161 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:57 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,3083,58162,0,53288,TO_DATE('2009-11-29 00:23:56','YYYY-MM-DD HH24:MI:SS'),100,'Classification or Importance',1,'D','The Rating is used to differentiate the importance','Y','Y','N','N','N','N','N','N','Rating',0,TO_DATE('2009-11-29 00:23:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:57 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58162 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:57 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,3084,58163,0,53288,TO_DATE('2009-11-29 00:23:57','YYYY-MM-DD HH24:MI:SS'),100,'How you pay the invoice',14,'D','The Payment Rule indicates the method of invoice payment.','Y','Y','N','N','N','N','N','N','Payment Rule',0,TO_DATE('2009-11-29 00:23:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:57 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58163 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:58 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,3085,58164,0,53288,TO_DATE('2009-11-29 00:23:57','YYYY-MM-DD HH24:MI:SS'),100,'Dunning Rules for overdue invoices',14,'D','The Dunning indicates the rules and method of dunning for past due payments.','Y','Y','N','N','N','N','N','N','Dunning',0,TO_DATE('2009-11-29 00:23:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:58 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58164 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:58 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,3086,58165,0,53288,TO_DATE('2009-11-29 00:23:58','YYYY-MM-DD HH24:MI:SS'),100,'Number of copies to be printed',11,'D','The Document Copies indicates the number of copies of each document that will be generated.','Y','Y','N','N','N','N','N','N','Document Copies',0,TO_DATE('2009-11-29 00:23:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:58 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58165 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:59 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4021,58166,0,53288,TO_DATE('2009-11-29 00:23:58','YYYY-MM-DD HH24:MI:SS'),100,'Purchase payment option',14,'D','The Payment Rule indicates the method of purchase payment.','Y','Y','N','N','N','N','N','N','Payment Rule',0,TO_DATE('2009-11-29 00:23:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:59 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58166 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:23:59 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4215,58167,0,53288,TO_DATE('2009-11-29 00:23:59','YYYY-MM-DD HH24:MI:SS'),100,'Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner',20,'D','The business partner order reference is the order reference for this specific transaction; Often Purchase Order numbers are given to print on Invoices for easier reference.  A standard number can be defined in the Business Partner (Customer) window.','Y','Y','N','N','N','N','N','N','Order Reference',0,TO_DATE('2009-11-29 00:23:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:23:59 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58167 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:00 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4216,58168,0,53288,TO_DATE('2009-11-29 00:23:59','YYYY-MM-DD HH24:MI:SS'),100,'Additional Name',60,'D','Y','Y','N','N','N','N','N','N','Name 2',0,TO_DATE('2009-11-29 00:23:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:00 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58168 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:00 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4291,58169,0,53288,TO_DATE('2009-11-29 00:24:00','YYYY-MM-DD HH24:MI:SS'),100,'Greeting to print on correspondence',14,'D','The Greeting identifies the greeting to print on correspondence.','Y','Y','N','N','N','N','N','N','Greeting',0,TO_DATE('2009-11-29 00:24:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:00 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58169 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:01 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4301,58170,0,53288,TO_DATE('2009-11-29 00:24:00','YYYY-MM-DD HH24:MI:SS'),100,'Print Discount on Invoice and Order',1,'D','The Discount Printed Checkbox indicates if the discount will be printed on the document.','Y','Y','N','N','N','N','N','N','Discount Printed',0,TO_DATE('2009-11-29 00:24:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:01 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58170 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:02 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4302,58171,0,53288,TO_DATE('2009-11-29 00:24:01','YYYY-MM-DD HH24:MI:SS'),100,'Description to be used on orders',60,'D','The Order Description identifies the standard description to use on orders for this Customer.','Y','Y','N','N','N','N','N','N','Order Description',0,TO_DATE('2009-11-29 00:24:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:02 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58171 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:03 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4429,58172,0,53288,TO_DATE('2009-11-29 00:24:02','YYYY-MM-DD HH24:MI:SS'),100,'Frequency and method of invoicing ',14,'D','The Invoice Rule defines how a Business Partner is invoiced and the frequency of invoicing.','Y','Y','N','N','N','N','N','N','Invoice Rule',0,TO_DATE('2009-11-29 00:24:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:03 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58172 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:03 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4430,58173,0,53288,TO_DATE('2009-11-29 00:24:03','YYYY-MM-DD HH24:MI:SS'),100,'Defines the timing of Delivery',14,'D','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','N','N','Delivery Rule',0,TO_DATE('2009-11-29 00:24:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:03 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58173 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:04 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4431,58174,0,53288,TO_DATE('2009-11-29 00:24:03','YYYY-MM-DD HH24:MI:SS'),100,'Sales Representative or Company Agent',14,'D','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','N','N','N','N','N','N','Sales Representative',0,TO_DATE('2009-11-29 00:24:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:04 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58174 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:04 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4432,58175,0,53288,TO_DATE('2009-11-29 00:24:04','YYYY-MM-DD HH24:MI:SS'),100,'Method for charging Freight',14,'D','The Freight Cost Rule indicates the method used when charging for freight.','Y','Y','N','N','N','N','N','N','Freight Cost Rule',0,TO_DATE('2009-11-29 00:24:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:04 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58175 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:05 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4433,58176,0,53288,TO_DATE('2009-11-29 00:24:04','YYYY-MM-DD HH24:MI:SS'),100,'How the order will be delivered',14,'D','The Delivery Via indicates how the products should be delivered. For example, will the order be picked up or shipped.','Y','Y','N','N','N','N','N','N','Delivery Via',0,TO_DATE('2009-11-29 00:24:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:05 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58176 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:05 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,4940,58177,0,53288,TO_DATE('2009-11-29 00:24:05','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Group',14,'D','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','N','N','N','N','N','N','Business Partner Group',0,TO_DATE('2009-11-29 00:24:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:05 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58177 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:06 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,5826,58178,0,53288,TO_DATE('2009-11-29 00:24:05','YYYY-MM-DD HH24:MI:SS'),100,'Payment rules for a purchase order',14,'D','The PO Payment Term indicates the payment term that will be used when this purchase order becomes an invoice.','Y','Y','N','N','N','N','N','N','PO Payment Term',0,TO_DATE('2009-11-29 00:24:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:06 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58178 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:06 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,6579,58179,0,53288,TO_DATE('2009-11-29 00:24:06','YYYY-MM-DD HH24:MI:SS'),100,'Schema to calculate the trade discount percentage',14,'D','After calculation of the (standard) price, the trade discount percentage is calculated and applied resulting in the final price.','Y','Y','N','N','N','N','N','N','Discount Schema',0,TO_DATE('2009-11-29 00:24:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:06 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58179 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:07 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,6580,58180,0,53288,TO_DATE('2009-11-29 00:24:06','YYYY-MM-DD HH24:MI:SS'),100,'Schema to calculate the purchase trade discount percentage',14,'D','Y','Y','N','N','N','N','N','N','PO Discount Schema',0,TO_DATE('2009-11-29 00:24:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:07 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58180 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:08 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,8167,58181,0,53288,TO_DATE('2009-11-29 00:24:07','YYYY-MM-DD HH24:MI:SS'),100,'Enable sending Document EMail',1,'D','Send emails with document attached (e.g. Invoice, Delivery Note, etc.)','Y','Y','N','N','N','N','N','N','Send EMail',0,TO_DATE('2009-11-29 00:24:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:08 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58181 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:08 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2894,58182,0,53288,TO_DATE('2009-11-29 00:24:08','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',14,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','Y','N','Client',10,TO_DATE('2009-11-29 00:24:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:08 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58182 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:09 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2895,58183,0,53288,TO_DATE('2009-11-29 00:24:08','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',14,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','Y','N','N','Y','Y','Organization',20,TO_DATE('2009-11-29 00:24:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:09 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58183 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:10 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2901,58184,0,53288,TO_DATE('2009-11-29 00:24:09','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique',11,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','Y','N','Search Key',30,TO_DATE('2009-11-29 00:24:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:10 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58184 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:11 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2902,58185,0,53288,TO_DATE('2009-11-29 00:24:10','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',11,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','Y','N','Name',40,1,TO_DATE('2009-11-29 00:24:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:11 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58185 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:12 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2896,58186,0,53288,TO_DATE('2009-11-29 00:24:11','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','Y','Y','Active',50,TO_DATE('2009-11-29 00:24:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:12 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58186 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:13 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2927,58187,0,53288,TO_DATE('2009-11-29 00:24:12','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if  this Business Partner is an employee',1,'D','The Employee checkbox indicates if this Business Partner is an Employee.  If it is selected, additional fields will display which further identify this employee.','Y','Y','Y','N','N','N','N','N','Employee',60,TO_DATE('2009-11-29 00:24:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:13 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58187 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:24:13 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2929,58188,0,53288,TO_DATE('2009-11-29 00:24:13','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if  the business partner is a sales representative or company agent',1,'D','The Sales Rep checkbox indicates if this business partner is a sales representative. A sales representative may also be an emplyee, but does not need to be.','Y','Y','Y','N','N','N','N','Y','Sales Representative',70,TO_DATE('2009-11-29 00:24:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:24:13 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58188 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:25:54 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=58187
;

-- Nov 29, 2009 12:25:54 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

DELETE FROM AD_Field WHERE AD_Field_ID=58187
;

-- Nov 29, 2009 12:26:47 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58596,58189,0,53288,TO_DATE('2009-11-29 00:26:46','YYYY-MM-DD HH24:MI:SS'),100,'Indicate role of this Business partner as Manufacturer',0,'D','Y','Y','Y','N','N','N','N','N','Manufacturer',80,0,TO_DATE('2009-11-29 00:26:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:26:47 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58189 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:28:35 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2009-11-29 00:28:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53288
;

-- Nov 29, 2009 12:29:19 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=58188
;

-- Nov 29, 2009 12:29:19 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=58189
;

-- Nov 29, 2009 12:31:34 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

UPDATE AD_Tab SET SeqNo=65,Updated=TO_DATE('2009-11-29 00:31:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53288
;

-- Nov 29, 2009 12:49:37 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54079,0,'Manufacturer_ID',TO_DATE('2009-11-29 00:49:37','YYYY-MM-DD HH24:MI:SS'),100,'U','Y','Manufacturer','Manufacturer',TO_DATE('2009-11-29 00:49:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:49:37 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54079 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Nov 29, 2009 12:52:04 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52071,'C_BPartner.IsActive=''Y'' AND C_BPartner.IsManufacturer=''Y''',TO_DATE('2009-11-29 00:52:04','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','BPartner - Manufacturer','S',TO_DATE('2009-11-29 00:52:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:52:26 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

UPDATE AD_Val_Rule SET Name='C_BPartner - Manufacturer',Updated=TO_DATE('2009-11-29 00:52:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52071
;

-- Nov 29, 2009 12:52:43 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58597,54079,0,18,208,52071,'Manufacturer_ID',TO_DATE('2009-11-29 00:52:43','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Manufacturer',0,TO_DATE('2009-11-29 00:52:43','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 29, 2009 12:52:44 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58597 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Nov 29, 2009 12:52:46 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

ALTER TABLE M_Product ADD Manufacturer_ID NUMBER(10) DEFAULT NULL 
;

-- Nov 29, 2009 12:55:46 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58597,58190,0,180,TO_DATE('2009-11-29 00:55:45','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','N','N','N','N','N','Manufacturer',540,0,TO_DATE('2009-11-29 00:55:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 12:55:46 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58190 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Nov 29, 2009 12:56:01 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

UPDATE AD_Field SET DisplayLength=14,Updated=TO_DATE('2009-11-29 00:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58190
;

-- Nov 29, 2009 1:01:10 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53335,TO_DATE('2009-11-29 01:01:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','C_BPartner -Active,Manufacturer, Non summary',TO_DATE('2009-11-29 01:01:09','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Nov 29, 2009 1:01:10 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53335 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Nov 29, 2009 1:02:00 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,WhereClause) VALUES (0,2902,2893,0,53335,291,TO_DATE('2009-11-29 01:02:00','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','C_BPartner.Value',TO_DATE('2009-11-29 01:02:00','YYYY-MM-DD HH24:MI:SS'),100,'C_BPartner.IsSummary=''N'' AND C_BPartner.IsActive=''Y'' AND C_BPartner.IsManufacturer=''Y''')
;

-- Nov 29, 2009 1:02:34 AM CET
-- FR [2913358] BPartner role - Manufacturer
-- https://sourceforge.net/tracker/?func=detail&aid=2913358&group_id=176962&atid=883808

UPDATE AD_Column SET AD_Reference_Value_ID=53335, AD_Val_Rule_ID=NULL,Updated=TO_DATE('2009-11-29 01:02:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58597
;


UPDATE AD_Column SET DefaultValue='''N''',Updated=TO_DATE('2009-12-04 11:17:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58596
;

UPDATE C_BPartner SET IsManufacturer = 'N' WHERE IsManufacturer is null;

ALTER TABLE C_BPartner MODIFY IsManufacturer DEFAULT 'N';
