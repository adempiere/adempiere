ALTER TABLE AD_OrgInfo ADD COLUMN TransferBank_ID NUMERIC;
ALTER TABLE AD_OrgInfo ADD COLUMN TransferCashBook_ID NUMERIC;

ALTER TABLE AD_OrgInfo
	ADD CONSTRAINT "cbank_adorginfo"
	FOREIGN KEY(TransferBank_ID)
	REFERENCES C_Bank(C_Bank_ID);


ALTER TABLE AD_OrgInfo
	ADD CONSTRAINT "ccashbook_adorginfo"
	FOREIGN KEY(TransferCashBook_ID)
	REFERENCES C_CashBook(C_CashBook_ID);




-- May 26, 2008 23:25:51 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52028,0,'TransferBank_ID',TO_DATE('2008-05-26 23:25:50','YYYY-MM-DD HH24:MI:SS'),100,'Bank account depending on currency will be used from this bank for doing transfers','D','Y','Bank for transfers','Bank for transfers',TO_DATE('2008-05-26 23:25:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 26, 2008 23:25:51 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52028 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 26, 2008 23:26:38 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,52001,TO_DATE('2008-05-26 23:26:38','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Bank',TO_DATE('2008-05-26 23:26:38','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- May 26, 2008 23:26:38 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=52001 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- May 26, 2008 23:28:08 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,WhereClause) VALUES (0,3039,3031,0,52001,296,TO_DATE('2008-05-26 23:28:08','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_DATE('2008-05-26 23:28:08','YYYY-MM-DD HH24:MI:SS'),100,'C_Bank.IsOwnBank=''Y''')
;

-- May 26, 2008 23:28:33 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52075,52028,0,18,52001,228,'TransferBank_ID',TO_DATE('2008-05-26 23:28:33','YYYY-MM-DD HH24:MI:SS'),100,'Bank account depending on currency will be used from this bank for doing transfers','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Bank for transfers',0,TO_DATE('2008-05-26 23:28:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- May 26, 2008 23:28:33 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52075 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;



-- Cash Book

-- May 26, 2008 23:29:40 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52029,0,'TransferCashBook_ID',TO_DATE('2008-05-23 23:29:40','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','CashBook for transfers','CashBook for transfers',TO_DATE('2008-05-23 23:29:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 26, 2008 23:29:40 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52029 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 26, 2008 23:30:32 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52076,52029,0,18,52004,228,'TransferCashBook_ID',TO_DATE('2008-05-23 23:30:31','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','N','N','N','N','N','Y','CashBook for transfers',0,TO_DATE('2008-05-23 23:30:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- May 26, 2008 23:30:32 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52076 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;
