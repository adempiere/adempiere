-- 12.08.2008 18:05:13 EEST
-- FR [ 2048081 ] Mf. Workflow editor should display only mf. workflows
INSERT INTO AD_Form (AD_Client_ID,AD_Form_ID,AD_Org_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,Name,Updated,UpdatedBy) VALUES (0,53013,0,'6','org.eevolution.form.WFPanelManufacturing',TO_TIMESTAMP('2008-08-12 18:05:12','YYYY-MM-DD HH24:MI:SS'),0,'Edit Manufacturing Workflows','D','Edit the graphical layout of manufacturing workflows','Y','N','Manufacturing Workflow Editor',TO_TIMESTAMP('2008-08-12 18:05:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 12.08.2008 18:05:13 EEST
-- FR [ 2048081 ] Mf. Workflow editor should display only mf. workflows
INSERT INTO AD_Form_Trl (AD_Language,AD_Form_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Form_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Form t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Form_ID=53013 AND EXISTS (SELECT * FROM AD_Form_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Form_ID!=t.AD_Form_ID)
;

-- 12.08.2008 18:05:13 EEST
-- FR [ 2048081 ] Mf. Workflow editor should display only mf. workflows
INSERT INTO AD_Form_Access (AD_Client_ID,AD_Form_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,53013,0,0,TO_TIMESTAMP('2008-08-12 18:05:13','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-08-12 18:05:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 12.08.2008 18:07:33 EEST
-- FR [ 2048081 ] Mf. Workflow editor should display only mf. workflows
UPDATE AD_Menu SET AD_Form_ID=53013, Description='Edit Manufacturing Workflows', Name='Manufacturing Workflow Editor',Updated=TO_TIMESTAMP('2008-08-12 18:07:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53128
;

-- 12.08.2008 18:07:33 EEST
-- FR [ 2048081 ] Mf. Workflow editor should display only mf. workflows
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53128
;

