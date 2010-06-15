-- Apr 16, 2010 1:01:06 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53347,10,'OrgValue',TO_TIMESTAMP('2010-04-16 13:01:06','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','Y','N','N','Organization Key',160,TO_TIMESTAMP('2010-04-16 13:01:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 16, 2010 1:01:06 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53347 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 16, 2010 1:02:45 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
UPDATE AD_Process_Para SET SeqNo=15,Updated=TO_TIMESTAMP('2010-04-16 13:02:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53347
;

-- Apr 16, 2010 1:10:48 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,512,0,53161,53348,10,'Postal',TO_TIMESTAMP('2010-04-16 13:10:47','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','Postal Code',94,TO_TIMESTAMP('2010-04-16 13:10:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 16, 2010 1:10:48 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53348 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 16, 2010 1:14:16 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,505,0,53161,53349,10,'Phone',TO_TIMESTAMP('2010-04-16 13:14:16','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','Y','N','N','Phone',160,TO_TIMESTAMP('2010-04-16 13:14:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 16, 2010 1:14:16 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53349 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 16, 2010 1:15:16 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,506,0,53161,53350,10,'Phone2',TO_TIMESTAMP('2010-04-16 13:15:16','YYYY-MM-DD HH24:MI:SS'),100,'Identifies an alternate telephone number.','D',40,'Y','Y','N','N','2nd Phone',170,TO_TIMESTAMP('2010-04-16 13:15:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 16, 2010 1:15:16 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53350 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 16, 2010 1:19:22 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,301,0,53161,53351,10,'Fax',TO_TIMESTAMP('2010-04-16 13:19:22','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','Y','N','N','Fax',180,TO_TIMESTAMP('2010-04-16 13:19:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 16, 2010 1:19:22 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53351 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 16, 2010 1:20:28 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,881,0,53161,53352,10,'EMail',TO_TIMESTAMP('2010-04-16 13:20:28','YYYY-MM-DD HH24:MI:SS'),100,'D',60,'Y','Y','N','N','EMail',190,TO_TIMESTAMP('2010-04-16 13:20:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 16, 2010 1:20:28 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53352 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 16, 2010 1:23:16 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,156,0,53161,53353,10,'Address1',TO_TIMESTAMP('2010-04-16 13:23:16','YYYY-MM-DD HH24:MI:SS'),100,'D',60,'Y','Y','N','N','Address 1',200,TO_TIMESTAMP('2010-04-16 13:23:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 16, 2010 1:23:16 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53353 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Apr 16, 2010 1:24:08 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
UPDATE AD_Process_Para SET SeqNo=96,Updated=TO_TIMESTAMP('2010-04-16 13:24:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53353
;

-- Apr 21, 2010 12:13:35 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,590,0,53161,53407,10,'TaxID',TO_TIMESTAMP('2010-04-21 12:13:33','YYYY-MM-DD HH24:MI:SS'),100,'D',20,'Y','Y','N','N','Tax ID',200,TO_TIMESTAMP('2010-04-21 12:13:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 21, 2010 12:13:35 PM CEST
-- FR [2990358] - Extend Initial Tenant setup
-- https://sourceforge.net/tracker/?func=detail&aid=2990358&group_id=176962&atid=879335
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53407 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

