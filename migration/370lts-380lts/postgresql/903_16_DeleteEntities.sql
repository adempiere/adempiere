-- Oct 25, 2013 7:26:50 PM IST
-- 'Delete Entities' Form into ADempiere380
INSERT INTO AD_Form (AccessLevel,AD_Client_ID,AD_Form_ID,AD_Org_ID,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,Name,Updated,UpdatedBy) VALUES ('3',0,53039,0,'org.compiere.apps.form.VDelete',TO_TIMESTAMP('2013-10-25 19:26:48','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','Delete Entities',TO_TIMESTAMP('2013-10-25 19:26:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2013 7:26:50 PM IST
-- 'Delete Entities' Form into ADempiere380
INSERT INTO AD_Form_Trl (AD_Language,AD_Form_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Form_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Form t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Form_ID=53039 AND NOT EXISTS (SELECT * FROM AD_Form_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Form_ID=t.AD_Form_ID)
;

-- Oct 25, 2013 7:28:19 PM IST
-- 'Delete Entities' Form into ADempiere380
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Form_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('X',0,53039,53671,0,TO_TIMESTAMP('2013-10-25 19:28:19','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Y','N','N','N','Delete Entities',TO_TIMESTAMP('2013-10-25 19:28:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 25, 2013 7:28:19 PM IST
-- 'Delete Entities' Form into ADempiere380
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53671 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Oct 25, 2013 7:28:19 PM IST
-- 'Delete Entities' Form into ADempiere380
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID,AD_Tree_ID,Created,CreatedBy,IsActive,Node_ID,Parent_ID,SeqNo,Updated,UpdatedBy) VALUES (0,0,10,TO_TIMESTAMP('2013-10-25 19:28:19','YYYY-MM-DD HH24:MI:SS'),0,'Y',53671,0,999,TO_TIMESTAMP('2013-10-25 19:28:19','YYYY-MM-DD HH24:MI:SS'),0)
;

