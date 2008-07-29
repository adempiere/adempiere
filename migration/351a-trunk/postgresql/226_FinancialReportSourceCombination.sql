-- Jul 9, 2008 3:33:39 PM COT
-- Jul 9, 2008 3:33:39 PM COT
-- Financial Report Source with Type Combination

-- recreate index to include the two new columns

drop index FACT_ACCT_BALANCE_AKEY;

CREATE UNIQUE INDEX FACT_ACCT_BALANCE_AKEY ON FACT_ACCT_BALANCE
(AD_CLIENT_ID, AD_ORG_ID, C_ACCTSCHEMA_ID, DATEACCT, ACCOUNT_ID, 
POSTINGTYPE, M_PRODUCT_ID, C_BPARTNER_ID, C_PROJECT_ID, AD_ORGTRX_ID, 
C_SALESREGION_ID, C_ACTIVITY_ID, C_CAMPAIGN_ID, C_LOCTO_ID, C_LOCFROM_ID, 
USER1_ID, USER2_ID, USERELEMENT1_ID, USERELEMENT2_ID, GL_BUDGET_ID);


/*

Alternatively - in case you replaced fact_acct_balance with a view - this is the view replacement:

CREATE OR REPLACE VIEW fact_acct_balance
AS
   SELECT   ad_client_id, ad_org_id, c_acctschema_id,
            TRUNC (dateacct) as dateacct, account_id, postingtype,
            m_product_id, c_bpartner_id, c_project_id, ad_orgtrx_id,
            c_salesregion_id, c_activity_id, c_campaign_id, c_locto_id,
            c_locfrom_id, user1_id, user2_id, gl_budget_id,
            COALESCE (SUM (amtacctdr), 0) as amtacctdr,
            COALESCE (SUM (amtacctcr), 0) as amtacctcr,
            COALESCE (SUM (qty), 0) as qty, MAX (createdby) as createdby,
            MAX (created) as created, MAX (updatedby) as updatedby,
            MAX (updated) as updated, MAX (isactive) as isactive,
            MAX (c_subacct_id) as c_subacct_id, userelement1_id,
            userelement2_id, MAX (c_projectphase_id) as c_projectphase_id,
            MAX (c_projecttask_id) as c_projecttask_id
       FROM fact_acct a
   GROUP BY ad_client_id, ad_org_id, c_acctschema_id,
            TRUNC (dateacct), account_id, postingtype,
            m_product_id, c_bpartner_id, c_project_id, ad_orgtrx_id,
            c_salesregion_id, c_activity_id, c_campaign_id, c_locto_id,
            c_locfrom_id, user1_id, user2_id,
            userelement1_id, userelement2_id,
            gl_budget_id
/

*/

-- Jul 9, 2008 3:33:39 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Reference (UpdatedBy,ValidationType,AD_Org_ID,Updated,VFormat,Help,Created,AD_Client_ID,AD_Reference_ID,EntityType,Name,CreatedBy,IsActive,Description,IsOrderByValue) VALUES (100,'L',0,TO_TIMESTAMP('2008-07-09 15:33:11','YYYY-MM-DD HH24:MI:SS'),'AA','Hardcoded Element Types',TO_TIMESTAMP('2008-07-09 15:33:11','YYYY-MM-DD HH24:MI:SS'),0,53280,'D','PA_ReportSource ElementType',100,'Y','Element Types for Accounting Elements - equal to C_AcctSchema ElementType plus Combination','N')
;

-- Jul 9, 2008 3:33:39 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53280 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

SET CLIENT_ENCODING TO 'LATIN1';

-- Jul 9, 2008 3:34:47 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Reference_Trl SET Description='Tipos de elementos para elementos contables - igual a C_AcctSchema ElementType mas Combinación',Help='Tipos de elementos definidos en forma fija',Updated=TO_TIMESTAMP('2008-07-09 15:34:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53280 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:35:28 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:35:10','YYYY-MM-DD HH24:MI:SS'),'AC',TO_TIMESTAMP('2008-07-09 15:35:10','YYYY-MM-DD HH24:MI:SS'),0,53280,53418,'D','Account',100,'Y','Natural Account')
;

-- Jul 9, 2008 3:35:28 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53418 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:36:12 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:35:58','YYYY-MM-DD HH24:MI:SS'),'AY',TO_TIMESTAMP('2008-07-09 15:35:58','YYYY-MM-DD HH24:MI:SS'),0,53280,53419,'D','Activity',100,'Y','Business Activity')
;

-- Jul 9, 2008 3:36:12 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53419 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:36:42 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:36:38','YYYY-MM-DD HH24:MI:SS'),'BP',TO_TIMESTAMP('2008-07-09 15:36:38','YYYY-MM-DD HH24:MI:SS'),0,53280,53420,'D','BPartner',100,'Y','Business Partner')
;

-- Jul 9, 2008 3:36:42 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53420 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:37:02 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:36:55','YYYY-MM-DD HH24:MI:SS'),'LF',TO_TIMESTAMP('2008-07-09 15:36:55','YYYY-MM-DD HH24:MI:SS'),0,53280,53421,'D','Location From',100,'Y','Location From')
;

-- Jul 9, 2008 3:37:03 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53421 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:37:14 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:37:13','YYYY-MM-DD HH24:MI:SS'),'LT',TO_TIMESTAMP('2008-07-09 15:37:13','YYYY-MM-DD HH24:MI:SS'),0,53280,53422,'D','Location To',100,'Y','Location To')
;

-- Jul 9, 2008 3:37:14 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53422 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:37:32 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:37:31','YYYY-MM-DD HH24:MI:SS'),'MC',TO_TIMESTAMP('2008-07-09 15:37:31','YYYY-MM-DD HH24:MI:SS'),0,53280,53423,'D','Campaign',100,'Y','Marketing Campaign')
;

-- Jul 9, 2008 3:37:32 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53423 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:37:46 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:37:45','YYYY-MM-DD HH24:MI:SS'),'OO',TO_TIMESTAMP('2008-07-09 15:37:45','YYYY-MM-DD HH24:MI:SS'),0,53280,53424,'D','Organization',100,'Y','Owning Organization')
;

-- Jul 9, 2008 3:37:46 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53424 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:38:00 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:37:59','YYYY-MM-DD HH24:MI:SS'),'OT',TO_TIMESTAMP('2008-07-09 15:37:59','YYYY-MM-DD HH24:MI:SS'),0,53280,53425,'D','Org Trx',100,'Y','Transaction Organization')
;

-- Jul 9, 2008 3:38:00 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53425 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:38:16 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:38:11','YYYY-MM-DD HH24:MI:SS'),'PJ',TO_TIMESTAMP('2008-07-09 15:38:11','YYYY-MM-DD HH24:MI:SS'),0,53280,53426,'D','Project',100,'Y','Project')
;

-- Jul 9, 2008 3:38:16 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53426 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:38:26 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:38:25','YYYY-MM-DD HH24:MI:SS'),'PR',TO_TIMESTAMP('2008-07-09 15:38:25','YYYY-MM-DD HH24:MI:SS'),0,53280,53427,'D','Product',100,'Y','Product')
;

-- Jul 9, 2008 3:38:26 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53427 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:38:37 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:38:37','YYYY-MM-DD HH24:MI:SS'),'SA',TO_TIMESTAMP('2008-07-09 15:38:37','YYYY-MM-DD HH24:MI:SS'),0,53280,53428,'D','Sub Account',100,'Y','Sub Account for Element Value')
;

-- Jul 9, 2008 3:38:37 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53428 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:38:50 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:38:49','YYYY-MM-DD HH24:MI:SS'),'SR',TO_TIMESTAMP('2008-07-09 15:38:49','YYYY-MM-DD HH24:MI:SS'),0,53280,53429,'D','Sales Region',100,'Y','Sales Region')
;

-- Jul 9, 2008 3:38:50 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53429 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:39:01 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:39:01','YYYY-MM-DD HH24:MI:SS'),'U1',TO_TIMESTAMP('2008-07-09 15:39:01','YYYY-MM-DD HH24:MI:SS'),0,53280,53430,'D','User List 1',100,'Y','User 1')
;

-- Jul 9, 2008 3:39:01 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53430 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:39:16 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:39:15','YYYY-MM-DD HH24:MI:SS'),'U2',TO_TIMESTAMP('2008-07-09 15:39:15','YYYY-MM-DD HH24:MI:SS'),0,53280,53431,'D','User List 2',100,'Y','User 2')
;

-- Jul 9, 2008 3:39:16 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53431 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:39:28 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:39:27','YYYY-MM-DD HH24:MI:SS'),'X1',TO_TIMESTAMP('2008-07-09 15:39:27','YYYY-MM-DD HH24:MI:SS'),0,53280,53432,'D','User Element 1',100,'Y')
;

-- Jul 9, 2008 3:39:28 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53432 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:39:40 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:39:39','YYYY-MM-DD HH24:MI:SS'),'X2',TO_TIMESTAMP('2008-07-09 15:39:39','YYYY-MM-DD HH24:MI:SS'),0,53280,53433,'D','User Element 2',100,'Y')
;

-- Jul 9, 2008 3:39:40 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53433 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:40:02 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List (UpdatedBy,AD_Org_ID,Updated,Value,Created,AD_Client_ID,AD_Reference_ID,AD_Ref_List_ID,EntityType,Name,CreatedBy,IsActive,Description) VALUES (100,0,TO_TIMESTAMP('2008-07-09 15:39:57','YYYY-MM-DD HH24:MI:SS'),'CO',TO_TIMESTAMP('2008-07-09 15:39:57','YYYY-MM-DD HH24:MI:SS'),0,53280,53434,'D','Combination',100,'Y','Combination of accounting dimensions')
;

-- Jul 9, 2008 3:40:02 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53434 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 9, 2008 3:40:17 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Cuenta',Description='Cuenta Natural',Updated=TO_TIMESTAMP('2008-07-09 15:40:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53418 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:40:32 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Actividad',Description=NULL,Updated=TO_TIMESTAMP('2008-07-09 15:40:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53419 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:40:49 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Socio del Negocio',Description='Socio del Negocio',Updated=TO_TIMESTAMP('2008-07-09 15:40:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53420 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:41:05 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Localización Desde',Description='Localización Desde',Updated=TO_TIMESTAMP('2008-07-09 15:41:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53421 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:41:18 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Localización A',Description='Localización A',Updated=TO_TIMESTAMP('2008-07-09 15:41:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53422 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:41:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Campaña',Description='Campaña de Mercadotecnia',Updated=TO_TIMESTAMP('2008-07-09 15:41:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53423 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:41:50 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Organización',Description='Organización Poseedora',Updated=TO_TIMESTAMP('2008-07-09 15:41:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53424 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:42:05 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Trans Org',Description='Transacción de la Organización',Updated=TO_TIMESTAMP('2008-07-09 15:42:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53425 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:42:19 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Proyecto',Description='Proyecto',Updated=TO_TIMESTAMP('2008-07-09 15:42:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53426 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:42:31 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Producto',Description='Producto',Updated=TO_TIMESTAMP('2008-07-09 15:42:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53427 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:42:47 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Sub Cuenta',Description='Sub Cuenta para Valor del Elemento',Updated=TO_TIMESTAMP('2008-07-09 15:42:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53428 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:43:02 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Región de Ventas',Description='Región de Ventas',Updated=TO_TIMESTAMP('2008-07-09 15:43:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53429 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:43:24 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Lista Usuario 1',Description='Usuario 1',Updated=TO_TIMESTAMP('2008-07-09 15:43:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53430 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:43:39 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Lista Usuario 2',Description='Usuario 2',Updated=TO_TIMESTAMP('2008-07-09 15:43:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53431 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:43:52 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Elemento de Usuario 1',Updated=TO_TIMESTAMP('2008-07-09 15:43:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53432 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:44:01 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Elemento de Usuario 2',Updated=TO_TIMESTAMP('2008-07-09 15:44:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53433 AND AD_Language LIKE 'es_%'
;

-- Jul 9, 2008 3:44:20 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Ref_List_Trl SET IsTranslated='Y',Name='Combinación',Description='Combinacion de dimensiones contables',Updated=TO_TIMESTAMP('2008-07-09 15:44:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53434 AND AD_Language LIKE 'es_%'
;

SET CLIENT_ENCODING TO 'UTF8';

-- Jul 9, 2008 3:45:38 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET AD_Reference_Value_ID=53280,Updated=TO_TIMESTAMP('2008-07-09 15:45:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6085
;

-- Jul 9, 2008 3:46:39 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET AD_Reference_Value_ID=53280,Updated=TO_TIMESTAMP('2008-07-09 15:46:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6024
;

-- Jul 9, 2008 3:48:55 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=OO | @ElementType@=OT | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:48:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4815
;

-- Jul 9, 2008 3:48:57 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=AC | @ElementType@=U1 | @ElementType@=U2 | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:48:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4805
;

-- Jul 9, 2008 3:48:59 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=BP | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:48:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4816
;

-- Jul 9, 2008 3:49:01 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=PR | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4807
;

-- Jul 9, 2008 3:49:02 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=LF | @ElementType@=LT | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4808
;

-- Jul 9, 2008 3:49:04 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=PJ | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4806
;

-- Jul 9, 2008 3:49:05 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=SR | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4809
;

-- Jul 9, 2008 3:49:06 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=AY | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4810
;

-- Jul 9, 2008 3:49:09 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=MC | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4817
;

-- Jul 9, 2008 3:49:44 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=OO | @ElementType@=OT | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4764
;

-- Jul 9, 2008 3:49:45 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=AC | @ElementType@=U1 | @ElementType@=U2 | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4765
;

-- Jul 9, 2008 3:49:47 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=PR | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4766
;

-- Jul 9, 2008 3:49:49 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=MC | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4777
;

-- Jul 9, 2008 3:49:50 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=BP | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4776
;

-- Jul 9, 2008 3:49:51 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=PJ | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4775
;

-- Jul 9, 2008 3:49:53 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=LF | @ElementType@=LT | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4767
;

-- Jul 9, 2008 3:49:54 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=SR | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:49:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4768
;

-- Jul 9, 2008 3:50:03 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=AY | @ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-09 15:50:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4769
;

-- Jul 9, 2008 3:50:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-07-09 15:50:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4756
;

-- Jul 9, 2008 3:57:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Val_Rule SET Code='C_ElementValue.C_Element_ID IN 
(SELECT C_Element_ID FROM C_AcctSchema_Element  WHERE C_AcctSchema_ID=@$C_AcctSchema_ID@ AND (ElementType=''@ElementType@'' OR (''@ElementType@''=''CO'' AND ElementType=''AC'')))',Updated=TO_TIMESTAMP('2008-07-09 15:57:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=258
;

-- Jul 9, 2008 5:40:22 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,Help,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',10,100,'UserElement1_ID',0,'N',0,TO_TIMESTAMP('2008-07-09 17:40:17','YYYY-MM-DD HH24:MI:SS'),'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','N',TO_TIMESTAMP('2008-07-09 17:40:17','YYYY-MM-DD HH24:MI:SS'),0,56151,13,'D','User Element 1','N',100,'N','N','Y',2877,'User defined accounting Element','N','N')
;

-- Jul 9, 2008 5:40:22 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56151 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 9, 2008 5:40:28 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN UserElement1_ID NUMERIC(10)
;

-- Jul 9, 2008 5:41:13 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,Help,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',10,100,'UserElement2_ID',0,'N',0,TO_TIMESTAMP('2008-07-09 17:41:12','YYYY-MM-DD HH24:MI:SS'),'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) ','N',TO_TIMESTAMP('2008-07-09 17:41:12','YYYY-MM-DD HH24:MI:SS'),0,56152,13,'D','User Element 2','N',100,'N','N','Y',2878,'User defined accounting Element','N','N')
;

-- Jul 9, 2008 5:41:13 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56152 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 9, 2008 5:41:16 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN UserElement2_ID NUMERIC(10)
;

-- Jul 9, 2008 5:42:04 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,Help,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',10,100,'UserElement1_ID',0,'N',0,TO_TIMESTAMP('2008-07-09 17:42:00','YYYY-MM-DD HH24:MI:SS'),'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','N',TO_TIMESTAMP('2008-07-09 17:42:00','YYYY-MM-DD HH24:MI:SS'),0,56153,13,'D','User Element 1','N',100,'N','N','Y',2877,'User defined accounting Element','N','N')
;

-- Jul 9, 2008 5:42:04 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56153 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 9, 2008 5:42:07 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN UserElement1_ID NUMERIC(10)
;

-- Jul 9, 2008 5:42:48 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,Help,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',10,100,'UserElement2_ID',0,'N',0,TO_TIMESTAMP('2008-07-09 17:42:43','YYYY-MM-DD HH24:MI:SS'),'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) ','N',TO_TIMESTAMP('2008-07-09 17:42:43','YYYY-MM-DD HH24:MI:SS'),0,56154,13,'D','User Element 2','N',100,'N','N','Y',2878,'User defined accounting Element','N','N')
;

-- Jul 9, 2008 5:42:48 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56154 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 9, 2008 5:42:49 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN UserElement2_ID NUMERIC(10)
;

-- Jul 9, 2008 5:48:36 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,SeqNo,Updated,DisplayLength,Help,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,DisplayLogic,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,310,TO_TIMESTAMP('2008-07-09 17:48:33','YYYY-MM-DD HH24:MI:SS'),14,'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)',TO_TIMESTAMP('2008-07-09 17:48:33','YYYY-MM-DD HH24:MI:SS'),'Y',0,56151,'N','D','N','User Element 1','N','@ElementType@=X1 | @ElementType@=CO & @$Element_X1@=Y',100,'Y','Y',56281,'User defined accounting Element','N')
;

-- Jul 9, 2008 5:48:37 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56281 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 9, 2008 5:48:58 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,SeqNo,Updated,DisplayLength,Help,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,DisplayLogic,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,320,TO_TIMESTAMP('2008-07-09 17:48:58','YYYY-MM-DD HH24:MI:SS'),14,'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) ',TO_TIMESTAMP('2008-07-09 17:48:58','YYYY-MM-DD HH24:MI:SS'),'Y',0,56152,'N','D','N','User Element 2','N','@ElementType@=X2 | @ElementType@=CO & @$Element_X2@=Y',100,'Y','Y',56282,'User defined accounting Element','N')
;

-- Jul 9, 2008 5:48:59 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56282 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 9, 2008 5:50:43 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,SeqNo,Updated,DisplayLength,Help,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,DisplayLogic,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,170,TO_TIMESTAMP('2008-07-09 17:50:43','YYYY-MM-DD HH24:MI:SS'),14,'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)',TO_TIMESTAMP('2008-07-09 17:50:43','YYYY-MM-DD HH24:MI:SS'),'Y',0,56153,'N','D','N','User Element 1','N','@ElementType@=X1 | @ElementType@=CO & @$Element_X1@=Y',100,'Y','Y',56283,'User defined accounting Element','N')
;

-- Jul 9, 2008 5:50:44 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56283 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 9, 2008 5:51:07 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,SeqNo,Updated,DisplayLength,Help,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,DisplayLogic,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,180,TO_TIMESTAMP('2008-07-09 17:51:02','YYYY-MM-DD HH24:MI:SS'),14,'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) ',TO_TIMESTAMP('2008-07-09 17:51:02','YYYY-MM-DD HH24:MI:SS'),'Y',0,56154,'N','D','N','User Element 2','N','@ElementType@=X2 | @ElementType@=CO & @$Element_X2@=Y',100,'Y','Y',56284,'User defined accounting Element','N')
;

-- Jul 9, 2008 5:51:07 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56284 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:40:12 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsOrg',0,TO_TIMESTAMP('2008-07-10 16:40:08','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:40:08','YYYY-MM-DD HH24:MI:SS'),'Include Nulls Org',0,'D','Include Nulls Org',100,'Y',53657,'Include nulls in the selection of the organization')
;

-- Jul 10, 2008 4:40:12 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53657 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:41:23 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsElementValue',0,TO_TIMESTAMP('2008-07-10 16:41:22','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:41:22','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in Account',0,'D','Include Nulls in Account',100,'Y',53658,'Include nulls in the selection of the account')
;

-- Jul 10, 2008 4:41:23 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53658 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:41:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Element SET PrintName='Include Nulls in Org', Name='Include Nulls in Org',Updated=TO_TIMESTAMP('2008-07-10 16:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53657
;

-- Jul 10, 2008 4:41:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53657
;

-- Jul 10, 2008 4:41:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET ColumnName='IsIncludeNullsOrg', Name='Include Nulls in Org', Description='Include nulls in the selection of the organization', Help=NULL WHERE AD_Element_ID=53657
;

-- Jul 10, 2008 4:41:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET Name='Include Nulls in Org', Description='Include nulls in the selection of the organization', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53657) AND IsCentrallyMaintained='Y'
;

-- Jul 10, 2008 4:41:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Process_Para SET ColumnName='IsIncludeNullsOrg', Name='Include Nulls in Org', Description='Include nulls in the selection of the organization', Help=NULL, AD_Element_ID=53657 WHERE UPPER(ColumnName)='ISINCLUDENULLSORG' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jul 10, 2008 4:41:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Process_Para SET ColumnName='IsIncludeNullsOrg', Name='Include Nulls in Org', Description='Include nulls in the selection of the organization', Help=NULL WHERE AD_Element_ID=53657 AND IsCentrallyMaintained='Y'
;

-- Jul 10, 2008 4:41:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_PrintFormatItem SET PrintName='Include Nulls in Org', Name='Include Nulls in Org' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53657)
;

-- Jul 10, 2008 4:41:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_PrintFormatItem SET PrintName='Include Nulls in Org', Name='Include Nulls in Org' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53657)
;

-- Jul 10, 2008 4:41:55 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Help,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsBpartner',0,TO_TIMESTAMP('2008-07-10 16:41:54','YYYY-MM-DD HH24:MI:SS'),NULL,TO_TIMESTAMP('2008-07-10 16:41:54','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in BPartner',0,'D','Include Nulls in BPartner',100,'Y',53659,'Include nulls in the selection of the business partner')
;

-- Jul 10, 2008 4:41:55 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53659 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:42:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Element SET ColumnName='IsIncludeNullsBPartner',Updated=TO_TIMESTAMP('2008-07-10 16:42:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53659
;

-- Jul 10, 2008 4:42:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET ColumnName='IsIncludeNullsBPartner', Name='Include Nulls in BPartner', Description='Include nulls in the selection of the business partner', Help=NULL WHERE AD_Element_ID=53659
;

-- Jul 10, 2008 4:42:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET Name='Include Nulls in BPartner', Description='Include nulls in the selection of the business partner', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53659) AND IsCentrallyMaintained='Y'
;

-- Jul 10, 2008 4:42:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Process_Para SET ColumnName='IsIncludeNullsBPartner', Name='Include Nulls in BPartner', Description='Include nulls in the selection of the business partner', Help=NULL, AD_Element_ID=53659 WHERE UPPER(ColumnName)='ISINCLUDENULLSBPARTNER' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jul 10, 2008 4:42:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Process_Para SET ColumnName='IsIncludeNullsBPartner', Name='Include Nulls in BPartner', Description='Include nulls in the selection of the business partner', Help=NULL WHERE AD_Element_ID=53659 AND IsCentrallyMaintained='Y'
;

-- Jul 10, 2008 4:42:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_PrintFormatItem SET PrintName='Include Nulls in BPartner', Name='Include Nulls in BPartner' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53659)
;

-- Jul 10, 2008 4:42:07 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_PrintFormatItem SET PrintName='Include Nulls in BPartner', Name='Include Nulls in BPartner' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53659)
;

-- Jul 10, 2008 4:42:37 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsProduct',0,TO_TIMESTAMP('2008-07-10 16:42:37','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:42:37','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in Product',0,'D','Include Nulls in Product',100,'Y',53660,'Include nulls in the selection of the product')
;

-- Jul 10, 2008 4:42:37 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53660 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:42:54 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsLocation',0,TO_TIMESTAMP('2008-07-10 16:42:53','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:42:53','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in Location',0,'D','Include Nulls in Location',100,'Y',53661,'Include nulls in the selection of the location')
;

-- Jul 10, 2008 4:42:55 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53661 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:43:14 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsProject',0,TO_TIMESTAMP('2008-07-10 16:43:13','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:43:13','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in Project',0,'D','Include Nulls in Project',100,'Y',53662,'Include nulls in the selection of the project')
;

-- Jul 10, 2008 4:43:15 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53662 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:43:34 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsSalesRegion',0,TO_TIMESTAMP('2008-07-10 16:43:33','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:43:33','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in Sales Region',0,'D','Include Nulls in Sales Region',100,'Y',53663,'Include nulls in the selection of the sales region')
;

-- Jul 10, 2008 4:43:34 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53663 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:43:51 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsActivity',0,TO_TIMESTAMP('2008-07-10 16:43:50','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:43:50','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in Activity',0,'D','Include Nulls in Activity',100,'Y',53664,'Include nulls in the selection of the activity')
;

-- Jul 10, 2008 4:43:51 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53664 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:44:05 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsCampaign',0,TO_TIMESTAMP('2008-07-10 16:44:04','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:44:04','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in Campaign',0,'D','Include Nulls in Campaign',100,'Y',53665,'Include nulls in the selection of the campaign')
;

-- Jul 10, 2008 4:44:05 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53665 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:44:23 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsUserElement1',0,TO_TIMESTAMP('2008-07-10 16:44:22','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:44:22','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in User Element 1',0,'D','Include Nulls in User Element 1',100,'Y',53666,'Include nulls in the selection of the user element 1')
;

-- Jul 10, 2008 4:44:23 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53666 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:44:31 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (UpdatedBy,ColumnName,AD_Org_ID,Updated,Created,PrintName,AD_Client_ID,EntityType,Name,CreatedBy,IsActive,AD_Element_ID,Description) VALUES (100,'IsIncludeNullsUserElement2',0,TO_TIMESTAMP('2008-07-10 16:44:30','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-07-10 16:44:30','YYYY-MM-DD HH24:MI:SS'),'Include Nulls in User Element 2',0,'D','Include Nulls in User Element 2',100,'Y',53667,'Include nulls in the selection of the user element 2')
;

-- Jul 10, 2008 4:44:31 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Help,PrintName,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Help,t.PrintName,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53667 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2008 4:45:53 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsOrg',0,'N',0,TO_TIMESTAMP('2008-07-10 16:45:51','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:45:51','YYYY-MM-DD HH24:MI:SS'),0,56155,20,'D','Include Nulls in Org','N',100,'Y','N','Y',53657,'Include nulls in the selection of the organization','N','N')
;

-- Jul 10, 2008 4:45:53 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56155 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:45:57 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsOrg CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsOrg IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:46:26 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsElementValue',0,'N',0,TO_TIMESTAMP('2008-07-10 16:46:26','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:46:26','YYYY-MM-DD HH24:MI:SS'),0,56156,20,'D','Include Nulls in Account','N',100,'Y','N','Y',53658,'Include nulls in the selection of the account','N','N')
;

-- Jul 10, 2008 4:46:27 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56156 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:46:31 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsElementValue CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsElementValue IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:46:47 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsBPartner',0,'N',0,TO_TIMESTAMP('2008-07-10 16:46:46','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:46:46','YYYY-MM-DD HH24:MI:SS'),0,56157,20,'D','Include Nulls in BPartner','N',100,'Y','N','Y',53659,'Include nulls in the selection of the business partner','N','N')
;

-- Jul 10, 2008 4:46:47 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56157 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:46:50 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsBPartner CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsBPartner IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:47:02 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsProduct',0,'N',0,TO_TIMESTAMP('2008-07-10 16:47:02','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:47:02','YYYY-MM-DD HH24:MI:SS'),0,56158,20,'D','Include Nulls in Product','N',100,'Y','N','Y',53660,'Include nulls in the selection of the product','N','N')
;

-- Jul 10, 2008 4:47:03 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56158 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:47:04 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsProduct CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsProduct IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:47:16 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsLocation',0,'N',0,TO_TIMESTAMP('2008-07-10 16:47:15','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:47:15','YYYY-MM-DD HH24:MI:SS'),0,56159,20,'D','Include Nulls in Location','N',100,'Y','N','Y',53661,'Include nulls in the selection of the location','N','N')
;

-- Jul 10, 2008 4:47:16 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56159 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:47:18 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsLocation CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsLocation IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:47:30 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsProject',0,'N',0,TO_TIMESTAMP('2008-07-10 16:47:29','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:47:29','YYYY-MM-DD HH24:MI:SS'),0,56160,20,'D','Include Nulls in Project','N',100,'Y','N','Y',53662,'Include nulls in the selection of the project','N','N')
;

-- Jul 10, 2008 4:47:30 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56160 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:47:32 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsProject CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsProject IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:47:43 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsSalesRegion',0,'N',0,TO_TIMESTAMP('2008-07-10 16:47:42','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:47:42','YYYY-MM-DD HH24:MI:SS'),0,56161,20,'D','Include Nulls in Sales Region','N',100,'Y','N','Y',53663,'Include nulls in the selection of the sales region','N','N')
;

-- Jul 10, 2008 4:47:43 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56161 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:47:45 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsSalesRegion CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsSalesRegion IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:48:04 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsActivity',0,'N',0,TO_TIMESTAMP('2008-07-10 16:48:03','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:48:03','YYYY-MM-DD HH24:MI:SS'),0,56162,20,'D','Include Nulls in Activity','N',100,'Y','N','Y',53664,'Include nulls in the selection of the activity','N','N')
;

-- Jul 10, 2008 4:48:04 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56162 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:48:06 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsActivity CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsActivity IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:48:17 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsCampaign',0,'N',0,TO_TIMESTAMP('2008-07-10 16:48:16','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:48:16','YYYY-MM-DD HH24:MI:SS'),0,56163,20,'D','Include Nulls in Campaign','N',100,'Y','N','Y',53665,'Include nulls in the selection of the campaign','N','N')
;

-- Jul 10, 2008 4:48:17 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56163 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:48:19 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsCampaign CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsCampaign IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:49:00 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsUserElement1',0,'N',0,TO_TIMESTAMP('2008-07-10 16:48:59','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:48:59','YYYY-MM-DD HH24:MI:SS'),0,56164,20,'D','Include Nulls in User Element 1','N',100,'Y','N','Y',53666,'Include nulls in the selection of the user element 1','N','N')
;

-- Jul 10, 2008 4:49:00 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56164 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:49:03 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsUserElement1 CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsUserElement1 IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:49:13 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',450,0,'N','Y',1,100,'IsIncludeNullsUserElement2',0,'N',0,TO_TIMESTAMP('2008-07-10 16:49:12','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:49:12','YYYY-MM-DD HH24:MI:SS'),0,56165,20,'D','Include Nulls in User Element 2','N',100,'Y','N','Y',53667,'Include nulls in the selection of the user element 2','N','N')
;

-- Jul 10, 2008 4:49:13 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56165 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:49:15 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD COLUMN IsIncludeNullsUserElement2 CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsUserElement2 IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:51:52 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsOrg',0,'N',0,TO_TIMESTAMP('2008-07-10 16:51:51','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:51:51','YYYY-MM-DD HH24:MI:SS'),0,56166,20,'D','Include Nulls in Org','N',100,'Y','N','Y',53657,'Include nulls in the selection of the organization','N','N')
;

-- Jul 10, 2008 4:51:52 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56166 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:51:53 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsOrg CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsOrg IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:52:12 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsElementValue',0,'N',0,TO_TIMESTAMP('2008-07-10 16:52:12','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:52:12','YYYY-MM-DD HH24:MI:SS'),0,56167,20,'D','Include Nulls in Account','N',100,'Y','N','Y',53658,'Include nulls in the selection of the account','N','N')
;

-- Jul 10, 2008 4:52:12 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56167 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:52:14 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsElementValue CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsElementValue IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:52:32 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsBPartner',0,'N',0,TO_TIMESTAMP('2008-07-10 16:52:31','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:52:31','YYYY-MM-DD HH24:MI:SS'),0,56168,20,'D','Include Nulls in BPartner','N',100,'Y','N','Y',53659,'Include nulls in the selection of the business partner','N','N')
;

-- Jul 10, 2008 4:52:32 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56168 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:52:34 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsBPartner CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsBPartner IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:52:48 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsProduct',0,'N',0,TO_TIMESTAMP('2008-07-10 16:52:46','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:52:46','YYYY-MM-DD HH24:MI:SS'),0,56169,20,'D','Include Nulls in Product','N',100,'Y','N','Y',53660,'Include nulls in the selection of the product','N','N')
;

-- Jul 10, 2008 4:52:48 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56169 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:52:50 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsProduct CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsProduct IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:53:00 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsLocation',0,'N',0,TO_TIMESTAMP('2008-07-10 16:52:59','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:52:59','YYYY-MM-DD HH24:MI:SS'),0,56170,20,'D','Include Nulls in Location','N',100,'Y','N','Y',53661,'Include nulls in the selection of the location','N','N')
;

-- Jul 10, 2008 4:53:00 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56170 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:53:01 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsLocation CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsLocation IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:53:13 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsProject',0,'N',0,TO_TIMESTAMP('2008-07-10 16:53:12','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:53:12','YYYY-MM-DD HH24:MI:SS'),0,56171,20,'D','Include Nulls in Project','N',100,'Y','N','Y',53662,'Include nulls in the selection of the project','N','N')
;

-- Jul 10, 2008 4:53:13 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56171 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:53:14 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsProject CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsProject IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:53:28 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsSalesRegion',0,'N',0,TO_TIMESTAMP('2008-07-10 16:53:26','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:53:26','YYYY-MM-DD HH24:MI:SS'),0,56172,20,'D','Include Nulls in Sales Region','N',100,'Y','N','Y',53663,'Include nulls in the selection of the sales region','N','N')
;

-- Jul 10, 2008 4:53:28 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56172 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:53:29 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsSalesRegion CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsSalesRegion IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:53:42 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsActivity',0,'N',0,TO_TIMESTAMP('2008-07-10 16:53:40','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:53:40','YYYY-MM-DD HH24:MI:SS'),0,56173,20,'D','Include Nulls in Activity','N',100,'Y','N','Y',53664,'Include nulls in the selection of the activity','N','N')
;

-- Jul 10, 2008 4:53:42 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56173 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:53:44 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsActivity CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsActivity IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:53:54 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsCampaign',0,'N',0,TO_TIMESTAMP('2008-07-10 16:53:54','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:53:54','YYYY-MM-DD HH24:MI:SS'),0,56174,20,'D','Include Nulls in Campaign','N',100,'Y','N','Y',53665,'Include nulls in the selection of the campaign','N','N')
;

-- Jul 10, 2008 4:53:55 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56174 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:53:58 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsCampaign CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsCampaign IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:54:10 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsUserElement1',0,'N',0,TO_TIMESTAMP('2008-07-10 16:54:09','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:54:09','YYYY-MM-DD HH24:MI:SS'),0,56175,20,'D','Include Nulls in User Element 1','N',100,'Y','N','Y',53666,'Include nulls in the selection of the user element 1','N','N')
;

-- Jul 10, 2008 4:54:10 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56175 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:54:12 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsUserElement1 CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsUserElement1 IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:54:24 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (IsParent,AD_Table_ID,Version,IsIdentifier,IsUpdateable,FieldLength,UpdatedBy,ColumnName,AD_Org_ID,IsSyncDatabase,SeqNo,Updated,DefaultValue,IsTranslated,Created,AD_Client_ID,AD_Column_ID,AD_Reference_ID,EntityType,Name,IsSelectionColumn,CreatedBy,IsMandatory,IsKey,IsActive,AD_Element_ID,Description,IsEncrypted,IsAlwaysUpdateable) VALUES ('N',446,0,'N','Y',1,100,'IsIncludeNullsUserElement2',0,'N',0,TO_TIMESTAMP('2008-07-10 16:54:23','YYYY-MM-DD HH24:MI:SS'),'N','N',TO_TIMESTAMP('2008-07-10 16:54:23','YYYY-MM-DD HH24:MI:SS'),0,56176,20,'D','Include Nulls in User Element 2','N',100,'Y','N','Y',53667,'Include nulls in the selection of the user element 2','N','N')
;

-- Jul 10, 2008 4:54:24 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56176 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2008 4:54:26 PM COT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD COLUMN IsIncludeNullsUserElement2 CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsUserElement2 IN ('Y','N')) NOT NULL
;

-- Jul 10, 2008 4:55:32 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:29','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:29','YYYY-MM-DD HH24:MI:SS'),'Y',0,56156,'N','D','N','Include Nulls in Account','N',100,'Y','Y',56285,'Include nulls in the selection of the account','N')
;

-- Jul 10, 2008 4:55:33 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56285 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:34 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:33','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:33','YYYY-MM-DD HH24:MI:SS'),'Y',0,56162,'N','D','N','Include Nulls in Activity','N',100,'Y','Y',56286,'Include nulls in the selection of the activity','N')
;

-- Jul 10, 2008 4:55:34 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56286 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:35 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:34','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:34','YYYY-MM-DD HH24:MI:SS'),'Y',0,56157,'N','D','N','Include Nulls in BPartner','N',100,'Y','Y',56287,'Include nulls in the selection of the business partner','N')
;

-- Jul 10, 2008 4:55:36 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56287 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:36 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:36','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:36','YYYY-MM-DD HH24:MI:SS'),'Y',0,56163,'N','D','N','Include Nulls in Campaign','N',100,'Y','Y',56288,'Include nulls in the selection of the campaign','N')
;

-- Jul 10, 2008 4:55:36 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56288 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:37 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:36','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:36','YYYY-MM-DD HH24:MI:SS'),'Y',0,56159,'N','D','N','Include Nulls in Location','N',100,'Y','Y',56289,'Include nulls in the selection of the location','N')
;

-- Jul 10, 2008 4:55:38 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56289 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:39 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:38','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:38','YYYY-MM-DD HH24:MI:SS'),'Y',0,56155,'N','D','N','Include Nulls in Org','N',100,'Y','Y',56290,'Include nulls in the selection of the organization','N')
;

-- Jul 10, 2008 4:55:39 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56290 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:40 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:39','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:39','YYYY-MM-DD HH24:MI:SS'),'Y',0,56158,'N','D','N','Include Nulls in Product','N',100,'Y','Y',56291,'Include nulls in the selection of the product','N')
;

-- Jul 10, 2008 4:55:41 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56291 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:43 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:41','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:41','YYYY-MM-DD HH24:MI:SS'),'Y',0,56160,'N','D','N','Include Nulls in Project','N',100,'Y','Y',56292,'Include nulls in the selection of the project','N')
;

-- Jul 10, 2008 4:55:43 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56292 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:44 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:43','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:43','YYYY-MM-DD HH24:MI:SS'),'Y',0,56161,'N','D','N','Include Nulls in Sales Region','N',100,'Y','Y',56293,'Include nulls in the selection of the sales region','N')
;

-- Jul 10, 2008 4:55:45 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56293 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:45 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:45','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:45','YYYY-MM-DD HH24:MI:SS'),'Y',0,56164,'N','D','N','Include Nulls in User Element 1','N',100,'Y','Y',56294,'Include nulls in the selection of the user element 1','N')
;

-- Jul 10, 2008 4:55:46 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56294 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:55:47 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,377,TO_TIMESTAMP('2008-07-10 16:55:46','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:55:46','YYYY-MM-DD HH24:MI:SS'),'Y',0,56165,'N','D','N','Include Nulls in User Element 2','N',100,'Y','Y',56295,'Include nulls in the selection of the user element 2','N')
;

-- Jul 10, 2008 4:55:47 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56295 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=4811
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=4812
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=4813
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=4803
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=4802
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=4814
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=4804
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=4815
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56290
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56285
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=4816
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56287
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=4807
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56291
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=4808
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56289
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=4806
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56292
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=4809
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56293
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=4810
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56286
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=4817
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56288
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56283
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56294
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56284
;

-- Jul 10, 2008 4:57:15 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56295
;

-- Jul 10, 2008 4:57:40 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-07-10 16:57:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56290
;

-- Jul 10, 2008 4:57:52 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:57:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56290
;

-- Jul 10, 2008 4:57:56 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:57:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56285
;

-- Jul 10, 2008 4:57:59 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:57:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56287
;

-- Jul 10, 2008 4:58:03 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:58:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56291
;

-- Jul 10, 2008 4:58:06 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:58:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56289
;

-- Jul 10, 2008 4:58:10 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:58:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56292
;

-- Jul 10, 2008 4:58:12 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:58:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56293
;

-- Jul 10, 2008 4:58:16 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:58:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56286
;

-- Jul 10, 2008 4:58:19 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 16:58:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56288
;

-- Jul 10, 2008 4:58:29 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO & @$Element_X1@=Y',Updated=TO_TIMESTAMP('2008-07-10 16:58:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56294
;

-- Jul 10, 2008 4:58:38 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO & @$Element_X2@=Y',Updated=TO_TIMESTAMP('2008-07-10 16:58:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56295
;

-- Jul 10, 2008 4:58:52 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLength=14,Updated=TO_TIMESTAMP('2008-07-10 16:58:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4816
;

-- Jul 10, 2008 4:58:54 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLength=14,Updated=TO_TIMESTAMP('2008-07-10 16:58:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4807
;

-- Jul 10, 2008 4:58:58 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLength=14,Updated=TO_TIMESTAMP('2008-07-10 16:58:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4808
;

-- Jul 10, 2008 4:59:19 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:18','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:18','YYYY-MM-DD HH24:MI:SS'),'Y',0,56167,'N','D','N','Include Nulls in Account','N',100,'Y','Y',56296,'Include nulls in the selection of the account','N')
;

-- Jul 10, 2008 4:59:20 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56296 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:21 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:20','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:20','YYYY-MM-DD HH24:MI:SS'),'Y',0,56173,'N','D','N','Include Nulls in Activity','N',100,'Y','Y',56297,'Include nulls in the selection of the activity','N')
;

-- Jul 10, 2008 4:59:21 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56297 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:22 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:21','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:21','YYYY-MM-DD HH24:MI:SS'),'Y',0,56168,'N','D','N','Include Nulls in BPartner','N',100,'Y','Y',56298,'Include nulls in the selection of the business partner','N')
;

-- Jul 10, 2008 4:59:22 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56298 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:23 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:22','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:22','YYYY-MM-DD HH24:MI:SS'),'Y',0,56174,'N','D','N','Include Nulls in Campaign','N',100,'Y','Y',56299,'Include nulls in the selection of the campaign','N')
;

-- Jul 10, 2008 4:59:23 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56299 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:24 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:23','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:23','YYYY-MM-DD HH24:MI:SS'),'Y',0,56170,'N','D','N','Include Nulls in Location','N',100,'Y','Y',56300,'Include nulls in the selection of the location','N')
;

-- Jul 10, 2008 4:59:24 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56300 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:25 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:24','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:24','YYYY-MM-DD HH24:MI:SS'),'Y',0,56166,'N','D','N','Include Nulls in Org','N',100,'Y','Y',56301,'Include nulls in the selection of the organization','N')
;

-- Jul 10, 2008 4:59:25 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56301 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:26 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:25','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:25','YYYY-MM-DD HH24:MI:SS'),'Y',0,56169,'N','D','N','Include Nulls in Product','N',100,'Y','Y',56302,'Include nulls in the selection of the product','N')
;

-- Jul 10, 2008 4:59:26 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56302 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:27 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:26','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:26','YYYY-MM-DD HH24:MI:SS'),'Y',0,56171,'N','D','N','Include Nulls in Project','N',100,'Y','Y',56303,'Include nulls in the selection of the project','N')
;

-- Jul 10, 2008 4:59:27 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56303 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:29 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:27','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:27','YYYY-MM-DD HH24:MI:SS'),'Y',0,56172,'N','D','N','Include Nulls in Sales Region','N',100,'Y','Y',56304,'Include nulls in the selection of the sales region','N')
;

-- Jul 10, 2008 4:59:29 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56304 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:30 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:29','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:29','YYYY-MM-DD HH24:MI:SS'),'Y',0,56175,'N','D','N','Include Nulls in User Element 1','N',100,'Y','Y',56305,'Include nulls in the selection of the user element 1','N')
;

-- Jul 10, 2008 4:59:30 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56305 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 4:59:31 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (IsFieldOnly,UpdatedBy,AD_Org_ID,AD_Tab_ID,Updated,DisplayLength,Created,IsCentrallyMaintained,AD_Client_ID,AD_Column_ID,IsReadOnly,EntityType,IsHeading,Name,IsSameLine,CreatedBy,IsDisplayed,IsActive,AD_Field_ID,Description,IsEncrypted) VALUES ('N',100,0,374,TO_TIMESTAMP('2008-07-10 16:59:30','YYYY-MM-DD HH24:MI:SS'),1,TO_TIMESTAMP('2008-07-10 16:59:30','YYYY-MM-DD HH24:MI:SS'),'Y',0,56176,'N','D','N','Include Nulls in User Element 2','N',100,'Y','Y',56306,'Include nulls in the selection of the user element 2','N')
;

-- Jul 10, 2008 4:59:31 PM COT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56306 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=4747
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=4748
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=4771
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=4749
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=4750
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=4751
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=4772
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=4752
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=4773
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=4753
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=4754
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=4760
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=4758
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=4774
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=4770
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=4756
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=4757
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=4759
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=4761
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=4762
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=4763
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=4764
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56301
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56296
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=4766
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56302
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=4777
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56299
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=4776
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56298
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=4775
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56303
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=4767
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56300
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=4768
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56304
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=4769
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56297
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56281
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=56305
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=56282
;

-- Jul 10, 2008 5:00:34 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=56306
;

-- Jul 10, 2008 5:04:32 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:04:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56301
;

-- Jul 10, 2008 5:04:35 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:04:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56296
;

-- Jul 10, 2008 5:04:39 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:04:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56302
;

-- Jul 10, 2008 5:04:47 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:04:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56299
;

-- Jul 10, 2008 5:04:50 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:04:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56298
;

-- Jul 10, 2008 5:04:52 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:04:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56303
;

-- Jul 10, 2008 5:04:54 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:04:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56300
;

-- Jul 10, 2008 5:04:57 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:04:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56304
;

-- Jul 10, 2008 5:05:02 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO',Updated=TO_TIMESTAMP('2008-07-10 17:05:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56297
;

-- Jul 10, 2008 5:05:09 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO & @$Element_X1@=Y',Updated=TO_TIMESTAMP('2008-07-10 17:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56305
;

-- Jul 10, 2008 5:05:14 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET IsSameLine='Y', DisplayLogic='@ElementType@=CO & @$Element_X2@=Y',Updated=TO_TIMESTAMP('2008-07-10 17:05:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56306
;

-- Jul 10, 2008 5:05:20 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLength=14,Updated=TO_TIMESTAMP('2008-07-10 17:05:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4766
;

-- Jul 10, 2008 5:05:22 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLength=14,Updated=TO_TIMESTAMP('2008-07-10 17:05:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4776
;

-- Jul 10, 2008 5:05:25 PM COT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLength=14,Updated=TO_TIMESTAMP('2008-07-10 17:05:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4767
;
