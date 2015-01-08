SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- Oct 5, 2009 12:35:38 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET WhereClause='iob.IsSOTrx=''Y'' AND iob.DocStatus=''DR'' AND iobl.Processed=''N''',Updated=TO_DATE('2009-10-05 12:35:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50001
;

-- Oct 5, 2009 12:36:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse SET WhereClause='NOT EXISTS (SELECT 1 FROM M_InOutLine WHERE M_InOutLine.C_OrderLine_ID = iobl.C_OrderLine_ID AND iobl.PickedQty >= M_InOutLine.MovementQty)  AND iob.IsSOTrx=''Y'' AND iobl.Processed=''Y''',Updated=TO_DATE('2009-10-05 12:36:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50002
;

-- Oct 5, 2009 1:00:58 PM ECT
-- Warehouse Management
INSERT INTO AD_View_Column (AD_Client_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,50604,50007,50001,'QtyOnhand','bomqtyonhand(iobl.M_Product_ID, iob.M_Warehouse_ID, 0 )',TO_DATE('2009-10-05 13:00:51','YYYY-MM-DD HH24:MI:SS'),0,'On Hand Quantity','EE03','The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.','Y','QtyOnhand',TO_DATE('2009-10-05 13:00:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 5, 2009 1:04:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50802,50002,0,530,0,29,50604,TO_DATE('2009-10-05 13:04:39','YYYY-MM-DD HH24:MI:SS'),0,'On Hand Quantity','EE03','The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.','Y','Y','Y','N','N','N','N','N','QtyOnHand',55,TO_DATE('2009-10-05 13:04:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 5, 2009 1:04:42 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50802 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50550
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50802
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50578
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50721
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50744
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50752
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50684
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50668
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50656
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50577
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50581
;

-- Oct 5, 2009 1:05:17 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50579
;

-- Oct 5, 2009 1:05:51 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET Help=NULL,Updated=TO_DATE('2009-10-05 13:05:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_Field_ID=50344
;

-- Oct 5, 2009 1:05:51 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field_Trl SET IsTranslated='N' WHERE AD_Browse_Field_ID=50344
;

-- Oct 5, 2009 1:06:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50803,50001,0,530,0,29,50604,TO_DATE('2009-10-05 13:06:43','YYYY-MM-DD HH24:MI:SS'),0,'On Hand Quantity','EE03','The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.','Y','Y','Y','N','N','N','N','N','QtyOnHand',65,TO_DATE('2009-10-05 13:06:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 5, 2009 1:06:48 PM ECT
-- Warehouse Management
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50803 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Browse_Field_ID=50352
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50803
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50344
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50382
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50346
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50438
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50395
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50470
;

-- Oct 5, 2009 1:07:04 PM ECT
-- Warehouse Management
UPDATE AD_Browse_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50381
;

-- Oct 5, 2009 1:12:49 PM ECT
-- Warehouse Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-10-05 13:12:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57558
;

-- Oct 5, 2009 1:13:44 PM ECT
-- Warehouse Management
UPDATE AD_Menu SET Name='Release Outbound Order',Updated=TO_DATE('2009-10-05 13:13:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53232
;

-- Oct 5, 2009 1:13:44 PM ECT
-- Warehouse Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53232
;

-- Oct 5, 2009 1:14:03 PM ECT
-- Warehouse Management
UPDATE AD_Menu SET Name='Generate Shipments from Outbound Order',Updated=TO_DATE('2009-10-05 13:14:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=193
;

-- Oct 5, 2009 1:14:03 PM ECT
-- Warehouse Management
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=193
;

-- Oct 5, 2009 1:17:36 PM ECT
-- Warehouse Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-10-05 13:17:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57554
;

-- Oct 5, 2009 1:17:39 PM ECT
-- Warehouse Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-10-05 13:17:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57555
;

