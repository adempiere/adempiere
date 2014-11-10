SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

ALTER TABLE T_REPORT ADD AccountType CHAR(1) NOT NULL;

ALTER TABLE T_REPORT ADD Ax_Case CHAR(1);

-- Oct 16, 2013 7:50:02 PM IST
-- Added the columns Account Type and Ax_Case in T_Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68923,147,0,20,117,544,'AccountType',TO_DATE('2013-10-16 19:50:01','YYYY-MM-DD HH24:MI:SS'),0,'Indicates the type of account','D',1,'Valid account types are A - Asset, E - Expense, L - Liability, O- Owner''s Equity, R -Revenue and M- Memo.  The account type is used to determine what taxes, if any are applicable, validating payables and receivables for business partners.  Note:  Memo account amounts are ignored when checking for balancing','Y','N','N','N','N','N','N','N','N','Y','Account Type',TO_DATE('2013-10-16 19:50:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 16, 2013 7:50:02 PM IST
-- Added the columns Account Type and Ax_Case in T_Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68923 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 16, 2013 7:50:59 PM IST
-- Added the columns Account Type and Ax_Case in T_Report
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56492,0,'ax_case',TO_DATE('2013-10-16 19:50:58','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','ax_case','ax_case',TO_DATE('2013-10-16 19:50:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 16, 2013 7:50:59 PM IST
-- Added the columns Account Type and Ax_Case in T_Report
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56492 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 16, 2013 7:50:59 PM IST
-- Added the columns Account Type and Ax_Case in T_Report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68924,56492,0,20,544,'ax_case',TO_DATE('2013-10-16 19:50:58','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','ax_case',TO_DATE('2013-10-16 19:50:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 16, 2013 7:50:59 PM IST
-- Added the columns Account Type and Ax_Case in T_Report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68924 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;