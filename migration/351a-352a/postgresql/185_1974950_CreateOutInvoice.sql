-- May 29, 2008 11:15:59 PM CDT
-- New Form Shipments & Invoices
INSERT INTO AD_Form (AD_Client_ID,AD_Form_ID,AD_Org_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,Name,Updated,UpdatedBy) VALUES (0,53010,0,'1','org.eevolution.form.VInOutInvoiceGen',TO_TIMESTAMP('2008-05-29 23:15:59','YYYY-MM-DD HH24:MI:SS'),0,'Select and generate shipments & Invoices','D','Generate Shipments & Invoices from Orders.  
Select the orders to generate the shipments & Invoices for.','Y','N','Generate Shipments & Invoices (manual)',TO_TIMESTAMP('2008-05-29 23:15:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 29, 2008 11:15:59 PM CDT
-- New Form Shipments & Invoices
INSERT INTO AD_Form_Trl (AD_Language,AD_Form_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Form_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Form t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Form_ID=53010 AND EXISTS (SELECT * FROM AD_Form_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Form_ID!=t.AD_Form_ID)
;

-- May 29, 2008 11:16:00 PM CDT
-- New Form Shipments & Invoices
INSERT INTO AD_Form_Access (AD_Client_ID,AD_Form_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,53010,0,0,TO_TIMESTAMP('2008-05-29 23:16:00','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-05-29 23:16:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 29, 2008 11:18:19 PM CDT
-- New Form Shipments & Invoices
INSERT INTO AD_Menu (AD_Client_ID,AD_Form_ID,AD_Menu_ID,AD_Org_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53010,53132,0,'X',TO_TIMESTAMP('2008-05-29 23:18:19','YYYY-MM-DD HH24:MI:SS'),0,'Select and generate shipments & Invoices','D','Y','N','N','N','Generate Shipments & Invoices (manual)',TO_TIMESTAMP('2008-05-29 23:18:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 29, 2008 11:18:19 PM CDT
-- New Form Shipments & Invoices
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53132 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;


-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=346
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53132
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=193
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=180
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=494
;

-- May 30, 2008 12:01:18 AM CDT
-- New Form Shipments & Invoices
UPDATE AD_TreeNodeMM SET Parent_ID=459, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=444
;

