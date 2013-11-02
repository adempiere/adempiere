-- Oct 28, 2013 11:17:44 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,4937,0,53794,393,195,TO_DATE('2013-10-28 23:17:40','YYYY-MM-DD HH24:MI:SS'),0,'Bank Statement Line','D','N','N','Y','N','N','Y','N','N','N','N','Bank Statement Line','N',30,1,TO_DATE('2013-10-28 23:17:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:17:44 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53794 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Oct 28, 2013 11:17:55 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5216,70152,0,53794,TO_DATE('2013-10-28 23:17:54','YYYY-MM-DD HH24:MI:SS'),0,'Accounting Date',7,'D','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Y','Y','N','N','N','N','N','Account Date',TO_DATE('2013-10-28 23:17:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:17:55 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70152 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:17:56 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4929,70153,0,53794,TO_DATE('2013-10-28 23:17:55','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2013-10-28 23:17:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:17:56 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70153 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:17:57 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4934,70154,0,53794,TO_DATE('2013-10-28 23:17:56','YYYY-MM-DD HH24:MI:SS'),0,'Bank Statement of account',22,'D','The Bank Statement identifies a unique Bank Statement for a defined time period.  The statement defines all transactions that occurred','Y','Y','Y','N','N','N','N','N','Bank Statement',TO_DATE('2013-10-28 23:17:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:17:57 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70154 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:17:57 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4926,70155,0,53794,TO_DATE('2013-10-28 23:17:57','YYYY-MM-DD HH24:MI:SS'),0,'Line on a statement from this Bank',22,'D','The Bank Statement Line identifies a unique transaction (Payment, Withdrawal, Charge) for the defined time period at this Bank.','Y','Y','N','N','N','N','N','N','Bank statement line',TO_DATE('2013-10-28 23:17:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:17:57 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70155 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:17:58 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10780,70156,0,53794,TO_DATE('2013-10-28 23:17:57','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner',22,'D','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','N','Business Partner ',TO_DATE('2013-10-28 23:17:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:17:58 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70156 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:17:59 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4939,70157,0,53794,TO_DATE('2013-10-28 23:17:58','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges',22,'D','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','Y','N','N','N','N','N','Charge',TO_DATE('2013-10-28 23:17:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:17:59 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70157 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5160,70158,0,53794,TO_DATE('2013-10-28 23:17:59','YYYY-MM-DD HH24:MI:SS'),0,'Charge Amount',22,'D','The Charge Amount indicates the amount for an additional charge.','Y','Y','Y','N','N','N','N','N','Charge amount',TO_DATE('2013-10-28 23:17:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70158 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4927,70159,0,53794,TO_DATE('2013-10-28 23:18:00','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2013-10-28 23:18:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70159 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:02 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10027,70160,0,53794,TO_DATE('2013-10-28 23:18:01','YYYY-MM-DD HH24:MI:SS'),0,1,'D','Y','Y','Y','N','N','N','N','N','Create Payment',TO_DATE('2013-10-28 23:18:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:02 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70160 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:03 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5217,70161,0,53794,TO_DATE('2013-10-28 23:18:02','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record',22,'D','Indicates the Currency to be used when processing or reporting on this record','Y','Y','Y','N','N','N','N','N','Currency',TO_DATE('2013-10-28 23:18:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:03 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70161 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:03 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4936,70162,0,53794,TO_DATE('2013-10-28 23:18:03','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2013-10-28 23:18:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:03 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70162 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:04 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5222,70163,0,53794,TO_DATE('2013-10-28 23:18:03','YYYY-MM-DD HH24:MI:SS'),0,'Date when money is available',7,'D','The Effective Date indicates the date that money is available from the bank.','Y','Y','Y','N','N','N','N','N','Effective date',TO_DATE('2013-10-28 23:18:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:04 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70163 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:05 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10340,70164,0,53794,TO_DATE('2013-10-28 23:18:04','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Amount',22,'D','Y','Y','Y','N','N','N','N','N','EFT Amount',TO_DATE('2013-10-28 23:18:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:05 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70164 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:06 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10383,70165,0,53794,TO_DATE('2013-10-28 23:18:05','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Check No',20,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Check No',TO_DATE('2013-10-28 23:18:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:06 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70165 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:07 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10341,70166,0,53794,TO_DATE('2013-10-28 23:18:06','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Currency',20,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Currency',TO_DATE('2013-10-28 23:18:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:07 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70166 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:07 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10336,70167,0,53794,TO_DATE('2013-10-28 23:18:07','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Valuta (effective) Date',7,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Effective Date',TO_DATE('2013-10-28 23:18:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:07 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70167 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:08 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10029,70168,0,53794,TO_DATE('2013-10-28 23:18:07','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Memo',2000,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Memo',TO_DATE('2013-10-28 23:18:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:08 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70168 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:09 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10028,70169,0,53794,TO_DATE('2013-10-28 23:18:08','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Payee information',255,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Payee',TO_DATE('2013-10-28 23:18:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:09 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70169 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:10 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10030,70170,0,53794,TO_DATE('2013-10-28 23:18:09','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Payee Account Information',40,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Payee Account',TO_DATE('2013-10-28 23:18:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:10 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70170 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:10 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10339,70171,0,53794,TO_DATE('2013-10-28 23:18:10','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Reference',60,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Reference',TO_DATE('2013-10-28 23:18:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:10 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70171 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:11 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10338,70172,0,53794,TO_DATE('2013-10-28 23:18:10','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Statement Line Date',7,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Statement Line Date',TO_DATE('2013-10-28 23:18:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:11 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70172 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:12 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10024,70173,0,53794,TO_DATE('2013-10-28 23:18:11','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Transaction ID',40,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Trx ID',TO_DATE('2013-10-28 23:18:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:12 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70173 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:13 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10026,70174,0,53794,TO_DATE('2013-10-28 23:18:12','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Funds Transfer Transaction Type',20,'D','Information from EFT media','Y','Y','Y','N','N','N','N','N','EFT Trx Type',TO_DATE('2013-10-28 23:18:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:13 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70174 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:13 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5161,70175,0,53794,TO_DATE('2013-10-28 23:18:13','YYYY-MM-DD HH24:MI:SS'),0,'Interest Amount',22,'D','The Interest Amount indicates any interest charged or received on a Bank Statement.','Y','Y','Y','N','N','N','N','N','Interest Amount',TO_DATE('2013-10-28 23:18:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:13 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70175 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:14 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10779,70176,0,53794,TO_DATE('2013-10-28 23:18:13','YYYY-MM-DD HH24:MI:SS'),0,'Invoice Identifier',22,'D','The Invoice Document.','Y','Y','Y','N','N','N','N','N','Invoice',TO_DATE('2013-10-28 23:18:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:14 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70176 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:15 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5219,70177,0,53794,TO_DATE('2013-10-28 23:18:14','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document',22,'D','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','N','N','N','N','N','Line No',TO_DATE('2013-10-28 23:18:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:15 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70177 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:15 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10025,70178,0,53794,TO_DATE('2013-10-28 23:18:15','YYYY-MM-DD HH24:MI:SS'),0,'This is a manual process',1,'D','The Manual check box indicates if the process will done manually.','Y','Y','Y','N','N','N','N','N','Manual',TO_DATE('2013-10-28 23:18:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:16 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70178 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:16 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10781,70179,0,53794,TO_DATE('2013-10-28 23:18:16','YYYY-MM-DD HH24:MI:SS'),0,1,'D','Y','Y','Y','N','N','N','N','N','Match Statement',TO_DATE('2013-10-28 23:18:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:16 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70179 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:17 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,8987,70180,0,53794,TO_DATE('2013-10-28 23:18:16','YYYY-MM-DD HH24:MI:SS'),0,'Memo Text',255,'D','Y','Y','Y','N','N','N','N','N','Memo',TO_DATE('2013-10-28 23:18:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:17 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70180 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:18 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4928,70181,0,53794,TO_DATE('2013-10-28 23:18:17','YYYY-MM-DD HH24:MI:SS'),0,'Organisational entity within client',22,'D','An organisation is a unit of your client or legal entity - examples are store, department. You can share data between organisations.','Y','Y','Y','N','N','N','N','N','Organisation',TO_DATE('2013-10-28 23:18:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:18 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70181 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:19 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,4937,70182,0,53794,TO_DATE('2013-10-28 23:18:18','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier',22,'D','The Payment is a unique identifier of this payment.','Y','Y','Y','N','N','N','N','N','Payment',TO_DATE('2013-10-28 23:18:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:19 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70182 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:19 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,12463,70183,0,53794,TO_DATE('2013-10-28 23:18:19','YYYY-MM-DD HH24:MI:SS'),0,'The document has been processed',1,'D','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','N','N','N','Processed',TO_DATE('2013-10-28 23:18:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:19 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70183 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:20 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,8986,70184,0,53794,TO_DATE('2013-10-28 23:18:20','YYYY-MM-DD HH24:MI:SS'),0,'Your customer or vendor number at the Business Partner''s site',40,'D','The reference number can be printed on orders and invoices to allow your business partner to faster identify your records.','Y','Y','Y','N','N','N','N','N','Reference No',TO_DATE('2013-10-28 23:18:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:20 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70184 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:21 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5218,70185,0,53794,TO_DATE('2013-10-28 23:18:20','YYYY-MM-DD HH24:MI:SS'),0,'This is a reversing transaction',1,'D','The Reversal check box indicates if this is a reversal of a prior transaction.','Y','Y','Y','N','N','N','N','N','Reversal',TO_DATE('2013-10-28 23:18:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:21 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70185 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:22 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5220,70186,0,53794,TO_DATE('2013-10-28 23:18:21','YYYY-MM-DD HH24:MI:SS'),0,'Statement Amount',22,'D','The Statement Amount indicates the amount of a single statement line.','Y','Y','Y','N','N','N','N','N','Statement amount',TO_DATE('2013-10-28 23:18:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:22 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70186 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:23 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10337,70187,0,53794,TO_DATE('2013-10-28 23:18:22','YYYY-MM-DD HH24:MI:SS'),0,'Date of the Statement Line',7,'D','Y','Y','Y','N','N','N','N','N','Statement Line Date',TO_DATE('2013-10-28 23:18:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:23 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70187 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:18:23 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,5221,70188,0,53794,TO_DATE('2013-10-28 23:18:23','YYYY-MM-DD HH24:MI:SS'),0,'Amount of a transaction',22,'D','The Transaction Amount indicates the amount for a single transaction.','Y','Y','Y','N','N','N','N','N','Transaction Amount',TO_DATE('2013-10-28 23:18:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 28, 2013 11:18:23 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70188 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70153
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70157
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70158
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70160
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70162
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70164
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70165
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70166
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70167
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70168
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70169
;

-- Oct 28, 2013 11:21:00 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70170
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70171
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70172
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70173
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70174
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70163
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70175
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70176
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70178
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70179
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70180
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70182
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70183
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70184
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70185
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70187
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70188
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=10,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70159
;

-- Oct 28, 2013 11:21:01 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=20,Updated=TO_DATE('2013-10-28 23:21:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70181
;

-- Oct 28, 2013 11:21:02 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=30,Updated=TO_DATE('2013-10-28 23:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70154
;

-- Oct 28, 2013 11:21:02 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=40,Updated=TO_DATE('2013-10-28 23:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70177
;

-- Oct 28, 2013 11:21:02 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=50,Updated=TO_DATE('2013-10-28 23:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70152
;

-- Oct 28, 2013 11:21:02 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=60,Updated=TO_DATE('2013-10-28 23:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70156
;

-- Oct 28, 2013 11:21:02 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=70,Updated=TO_DATE('2013-10-28 23:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70161
;

-- Oct 28, 2013 11:21:02 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=80,Updated=TO_DATE('2013-10-28 23:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70186
;

-- Oct 28, 2013 11:21:21 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET Name='Statement Amount',Updated=TO_DATE('2013-10-28 23:21:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70186
;

-- Oct 28, 2013 11:21:21 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70186
;

-- Oct 28, 2013 11:21:36 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2013-10-28 23:21:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70181
;

-- Oct 28, 2013 11:21:44 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2013-10-28 23:21:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70177
;

-- Oct 28, 2013 11:21:53 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=50,Updated=TO_DATE('2013-10-28 23:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70156
;

-- Oct 28, 2013 11:21:53 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=60,Updated=TO_DATE('2013-10-28 23:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70152
;

-- Oct 28, 2013 11:21:53 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=70,Updated=TO_DATE('2013-10-28 23:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70186
;

-- Oct 28, 2013 11:21:53 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=80,Updated=TO_DATE('2013-10-28 23:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70161
;

-- Oct 28, 2013 11:22:03 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2013-10-28 23:22:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70152
;

-- Oct 28, 2013 11:22:09 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2013-10-28 23:22:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70161
;

-- Oct 28, 2013 11:34:19 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Tab SET IsInsertRecord='N', IsReadOnly='Y',Updated=TO_DATE('2013-10-28 23:34:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53794
;

-- Oct 28, 2013 11:37:52 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field SET Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', Name='Organization',Updated=TO_DATE('2013-10-28 23:37:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70181
;

-- Oct 28, 2013 11:37:52 PM IST
-- Adding a new Tab "Bank Statement Line' in Payment Window
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70181
;

