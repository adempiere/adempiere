-- 07.11.2008 10:27:05 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WinHeight,WinWidth,WindowType) VALUES (0,0,53064,TO_DATE('2008-11-07 10:27:03','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','N','Y','View MRP Records','N',TO_DATE('2008-11-07 10:27:03','YYYY-MM-DD HH24:MI:SS'),0,0,0,'M')
;

-- 07.11.2008 10:27:05 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53064 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- 07.11.2008 10:27:06 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53064,TO_DATE('2008-11-07 10:27:06','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-11-07 10:27:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:27:07 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53064,TO_DATE('2008-11-07 10:27:07','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-11-07 10:27:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:27:07 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53064,TO_DATE('2008-11-07 10:27:07','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-11-07 10:27:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:27:07 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53064,TO_DATE('2008-11-07 10:27:07','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-11-07 10:27:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:47:33 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53180,53043,53064,TO_DATE('2008-11-07 10:47:30','YYYY-MM-DD HH24:MI:SS'),0,NULL,'D','N','N','Y','N','N','Y','N','N','N','N','Records','N',10,0,TO_DATE('2008-11-07 10:47:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:47:33 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53180 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- 07.11.2008 10:48:05 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54039,56410,0,53180,TO_DATE('2008-11-07 10:47:59','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports. 
There are two reasons for de-activating and not deleting records: 
(1) The system requires the record for audit purposes. 
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2008-11-07 10:47:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:06 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56410 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:08 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54040,56411,0,53180,TO_DATE('2008-11-07 10:48:07','YYYY-MM-DD HH24:MI:SS'),0,'Resource is available',1,'EE01','Resource is available for assignments','Y','Y','Y','N','N','N','N','N','Available',TO_DATE('2008-11-07 10:48:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:08 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56411 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:11 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54025,56412,0,53180,TO_DATE('2008-11-07 10:48:09','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner',10,'EE01','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','N','Business Partner ',TO_DATE('2008-11-07 10:48:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:11 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56412 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:15 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54059,56413,0,53180,TO_DATE('2008-11-07 10:48:12','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2008-11-07 10:48:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:15 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56413 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:18 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54032,56414,0,53180,TO_DATE('2008-11-07 10:48:15','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order',29,'EE01','Indicates the Date an item was ordered.','Y','Y','Y','N','N','N','N','N','Date Ordered',TO_DATE('2008-11-07 10:48:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:19 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56414 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:23 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54033,56415,0,53180,TO_DATE('2008-11-07 10:48:19','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised',29,'EE01','The Date Promised indicates the date, if any, that an Order was promised for.','Y','Y','Y','N','N','N','N','N','Date Promised',TO_DATE('2008-11-07 10:48:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:23 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56415 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:25 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54030,56416,0,53180,TO_DATE('2008-11-07 10:48:24','YYYY-MM-DD HH24:MI:SS'),0,29,'EE01','Y','Y','Y','N','N','N','N','N','DateConfirm',TO_DATE('2008-11-07 10:48:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:25 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56416 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:27 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54031,56417,0,53180,TO_DATE('2008-11-07 10:48:26','YYYY-MM-DD HH24:MI:SS'),0,29,'EE01','Y','Y','Y','N','N','N','N','N','DateFinishSchedule',TO_DATE('2008-11-07 10:48:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:27 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56417 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:30 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54034,56418,0,53180,TO_DATE('2008-11-07 10:48:27','YYYY-MM-DD HH24:MI:SS'),0,29,'EE01','Y','Y','Y','N','N','N','N','N','DateSimulation',TO_DATE('2008-11-07 10:48:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:31 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56418 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:36 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54035,56419,0,53180,TO_DATE('2008-11-07 10:48:31','YYYY-MM-DD HH24:MI:SS'),0,29,'EE01','Y','Y','Y','N','N','N','N','N','DateStart',TO_DATE('2008-11-07 10:48:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:36 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56419 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:39 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54036,56420,0,53180,TO_DATE('2008-11-07 10:48:36','YYYY-MM-DD HH24:MI:SS'),0,29,'EE01','Y','Y','Y','N','N','N','N','N','DateStartSchedule',TO_DATE('2008-11-07 10:48:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:39 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56420 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:43 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54037,56421,0,53180,TO_DATE('2008-11-07 10:48:40','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',1020,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2008-11-07 10:48:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:43 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56421 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:45 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55336,56422,0,53180,TO_DATE('2008-11-07 10:48:43','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','N','N','Distribution Order',TO_DATE('2008-11-07 10:48:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:46 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56422 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:48 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55337,56423,0,53180,TO_DATE('2008-11-07 10:48:46','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','N','N','Distribution Order Line',TO_DATE('2008-11-07 10:48:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:48 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56423 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:52 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54038,56424,0,53180,TO_DATE('2008-11-07 10:48:49','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document',2,'EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','Y','N','N','N','N','N','Document Status',TO_DATE('2008-11-07 10:48:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:53 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56424 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:48:56 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54042,56425,0,53180,TO_DATE('2008-11-07 10:48:53','YYYY-MM-DD HH24:MI:SS'),0,'Material Forecast',10,'EE01','Material Forecast','Y','Y','Y','N','N','N','N','N','Forecast',TO_DATE('2008-11-07 10:48:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:48:56 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56425 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:01 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54041,56426,0,53180,TO_DATE('2008-11-07 10:48:58','YYYY-MM-DD HH24:MI:SS'),0,'Forecast Line',10,'EE01','Forecast of Product Qyantity by Period','Y','Y','Y','N','N','N','N','N','Forecast Line',TO_DATE('2008-11-07 10:48:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:03 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56426 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:07 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54049,56427,0,53180,TO_DATE('2008-11-07 10:49:03','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Manufacturing Order',TO_DATE('2008-11-07 10:49:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:07 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56427 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:09 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54048,56428,0,53180,TO_DATE('2008-11-07 10:49:07','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Manufacturing Order BOM Line',TO_DATE('2008-11-07 10:49:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:10 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56428 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:12 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54047,56429,0,53180,TO_DATE('2008-11-07 10:49:10','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','N','N','N','N','N','N','Material Requirement Planning',TO_DATE('2008-11-07 10:49:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:13 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56429 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:16 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54023,56430,0,53180,TO_DATE('2008-11-07 10:49:13','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',120,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2008-11-07 10:49:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:17 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56430 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:20 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54027,56431,0,53180,TO_DATE('2008-11-07 10:49:17','YYYY-MM-DD HH24:MI:SS'),0,'Order',10,'EE01','The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you cloase an order, unshipped (backordered) quantities are cancelled.','Y','Y','Y','N','N','N','N','N','Order',TO_DATE('2008-11-07 10:49:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:20 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56431 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:23 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54055,56432,0,53180,TO_DATE('2008-11-07 10:49:20','YYYY-MM-DD HH24:MI:SS'),0,3,'EE01','Y','Y','Y','N','N','N','N','N','OrderType',TO_DATE('2008-11-07 10:49:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:23 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56432 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:26 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54024,56433,0,53180,TO_DATE('2008-11-07 10:49:24','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2008-11-07 10:49:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:27 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56433 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:31 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54050,56434,0,53180,TO_DATE('2008-11-07 10:49:27','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Planner',TO_DATE('2008-11-07 10:49:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:32 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56434 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:35 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54051,56435,0,53180,TO_DATE('2008-11-07 10:49:32','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this request is of a high, medium or low priority.',10,'EE01','The Priority indicates the importance of this request.','Y','Y','Y','N','N','N','N','N','Priority',TO_DATE('2008-11-07 10:49:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:35 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56435 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:37 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54043,56436,0,53180,TO_DATE('2008-11-07 10:49:35','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item',10,'EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','N','Product',TO_DATE('2008-11-07 10:49:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:39 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56436 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:42 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54052,56437,0,53180,TO_DATE('2008-11-07 10:49:39','YYYY-MM-DD HH24:MI:SS'),0,'Quantity',131089,'EE01','The Quantity indicates the number of a specific product or item for this document.','Y','Y','Y','N','N','N','N','N','Quantity',TO_DATE('2008-11-07 10:49:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:42 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56437 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54045,56438,0,53180,TO_DATE('2008-11-07 10:49:42','YYYY-MM-DD HH24:MI:SS'),0,'Material Requisition',10,'EE01','Y','Y','Y','N','N','N','N','N','Requisition',TO_DATE('2008-11-07 10:49:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56438 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:47 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54044,56439,0,53180,TO_DATE('2008-11-07 10:49:44','YYYY-MM-DD HH24:MI:SS'),0,'Material Requisition Line',10,'EE01','Y','Y','Y','N','N','N','N','N','Requisition Line',TO_DATE('2008-11-07 10:49:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:47 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56439 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:50 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54053,56440,0,53180,TO_DATE('2008-11-07 10:49:47','YYYY-MM-DD HH24:MI:SS'),0,'Resource',10,'EE01','Y','Y','Y','N','N','N','N','N','Resource',TO_DATE('2008-11-07 10:49:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:50 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56440 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:53 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54026,56441,0,53180,TO_DATE('2008-11-07 10:49:51','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line',10,'EE01','The Sales Order Line is a unique identifier for a line in an order.','Y','Y','Y','N','N','N','N','N','Sales Order Line',TO_DATE('2008-11-07 10:49:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:53 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56441 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:49:56 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54058,56442,0,53180,TO_DATE('2008-11-07 10:49:53','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',80,'EE01','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Search Key',TO_DATE('2008-11-07 10:49:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:49:56 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56442 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:50:00 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54054,56443,0,53180,TO_DATE('2008-11-07 10:49:57','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','TypeMRP',TO_DATE('2008-11-07 10:49:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:50:00 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56443 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:50:04 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54060,56444,0,53180,TO_DATE('2008-11-07 10:50:00','YYYY-MM-DD HH24:MI:SS'),0,'Version of the table definition',131089,'EE01','The Version indicates the version of this table definition.','Y','Y','Y','N','N','N','N','N','Version',TO_DATE('2008-11-07 10:50:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:50:04 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56444 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:50:06 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54046,56445,0,53180,TO_DATE('2008-11-07 10:50:04','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point',10,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','N','N','N','N','Warehouse',TO_DATE('2008-11-07 10:50:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.11.2008 10:50:06 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56445 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.11.2008 10:51:06 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Tab SET IsInsertRecord='N', IsReadOnly='Y',Updated=TO_DATE('2008-11-07 10:51:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53180
;

-- 07.11.2008 10:52:07 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Window_Trl SET IsTranslated='Y',Name='Vizualizare MRP',Updated=TO_DATE('2008-11-07 10:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=53064 AND AD_Language='ro_RO'
;

-- 07.11.2008 10:52:16 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Tab_Trl SET IsTranslated='Y',Name='Inregistrari',Updated=TO_DATE('2008-11-07 10:52:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53180 AND AD_Language='ro_RO'
;

-- 07.11.2008 10:58:40 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56410
;

-- 07.11.2008 10:58:40 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56411
;

-- 07.11.2008 10:58:40 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56414
;

-- 07.11.2008 10:58:40 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56416
;

-- 07.11.2008 10:58:40 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56418
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56419
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56421
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56430
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56435
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56442
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56444
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56413
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56433
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56436
;

-- 07.11.2008 10:58:41 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56440
;

-- 07.11.2008 10:58:42 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56445
;

-- 07.11.2008 10:58:42 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56415
;

-- 07.11.2008 10:58:42 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56420
;

-- 07.11.2008 10:58:43 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56417
;

-- 07.11.2008 10:58:43 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56437
;

-- 07.11.2008 10:58:43 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56443
;

-- 07.11.2008 10:58:43 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56432
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56431
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56441
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56427
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56428
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56422
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56423
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56438
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56439
;

-- 07.11.2008 10:58:44 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56425
;

-- 07.11.2008 10:58:45 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56426
;

-- 07.11.2008 10:58:45 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56412
;

-- 07.11.2008 10:58:45 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56424
;

-- 07.11.2008 10:58:45 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56434
;

-- 07.11.2008 11:02:20 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:02:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56433
;

-- 07.11.2008 11:02:34 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:02:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56440
;

-- 07.11.2008 11:02:43 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:02:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56420
;

-- 07.11.2008 11:02:49 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-11-07 11:02:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56420
;

-- 07.11.2008 11:02:55 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:02:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56417
;

-- 07.11.2008 11:03:10 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2008-11-07 11:03:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56415
;

-- 07.11.2008 11:03:15 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2008-11-07 11:03:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56420
;

-- 07.11.2008 11:04:09 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2008-11-07 11:04:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56417
;

-- 07.11.2008 11:04:50 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:04:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54028
;

-- 07.11.2008 11:04:58 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:04:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54030
;

-- 07.11.2008 11:05:04 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:05:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54031
;

-- 07.11.2008 11:05:09 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54032
;

-- 07.11.2008 11:05:16 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:05:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54033
;

-- 07.11.2008 11:05:22 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:05:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54034
;

-- 07.11.2008 11:05:27 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:05:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54035
;

-- 07.11.2008 11:05:33 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:05:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54036
;

-- 07.11.2008 11:05:58 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=22,Updated=TO_DATE('2008-11-07 11:05:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54052
;

-- 07.11.2008 11:06:07 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=7,Updated=TO_DATE('2008-11-07 11:06:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54056
;

-- 07.11.2008 11:06:31 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET FieldLength=22,Updated=TO_DATE('2008-11-07 11:06:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54060
;

-- 07.11.2008 11:06:38 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2008-11-07 11:06:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56437
;

-- 07.11.2008 11:06:54 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2008-11-07 11:06:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56432
;

-- 07.11.2008 11:07:00 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:07:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56441
;

-- 07.11.2008 11:07:04 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:07:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56428
;

-- 07.11.2008 11:07:09 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2008-11-07 11:07:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56422
;

-- 07.11.2008 11:07:13 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET DisplayLength=10, IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:07:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56423
;

-- 07.11.2008 11:07:19 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:07:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56439
;

-- 07.11.2008 11:07:24 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-11-07 11:07:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56426
;

-- 07.11.2008 11:07:34 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Field SET AD_FieldGroup_ID=101,Updated=TO_DATE('2008-11-07 11:07:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56424
;

-- 07.11.2008 11:08:16 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Table SET AD_Window_ID=53064,Updated=TO_DATE('2008-11-07 11:08:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53043
;

-- 07.11.2008 11:12:48 EET
-- FR [ 2233277 ] Create window for PP_MRP table.
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=131,Updated=TO_DATE('2008-11-07 11:12:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54038
;

update AD_Tab set EntityType='EE01' where AD_Tab_ID=53180;
update AD_Window set EntityType='EE01' where AD_Window_ID=53064;
