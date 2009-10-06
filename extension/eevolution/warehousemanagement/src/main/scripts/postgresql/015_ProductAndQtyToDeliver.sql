-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50539
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50531
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50534
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50540
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50542
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50802
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50578
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50721
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50744
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50752
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50684
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50668
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50656
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50577
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50581
;

-- Oct 6, 2009 2:43:50 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50579
;

-- Oct 6, 2009 2:54:16 PM CDT
-- Warehouse Management Functionality
INSERT INTO AD_View_Column (AD_Client_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,50605,50007,50001,'QtyToDeliver','(SELECT QtyOrdered-QtyDelivered FROM C_OrderLine WHERE C_OrderLine.C_OrderLine_ID=iobl.C_OrderLine_ID)',TO_TIMESTAMP('2009-10-06 14:54:14','YYYY-MM-DD HH24:MI:SS'),0,'Qty to Deliver','EE03','Qty to Deliver','Y','QtyToDeliver',TO_TIMESTAMP('2009-10-06 14:54:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 6, 2009 2:55:45 PM CDT
-- Warehouse Management Functionality
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (50804,50002,0,1250,0,29,50605,TO_TIMESTAMP('2009-10-06 14:55:44','YYYY-MM-DD HH24:MI:SS'),0,'Qty to Deliver','EE03','Qty to Deliver','Y','N','Y','N','N','N','N','N','QtyToDeliver',105,TO_TIMESTAMP('2009-10-06 14:55:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 6, 2009 2:55:45 PM CDT
-- Warehouse Management Functionality
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50804 AND EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Browse_Field_ID!=t.AD_Browse_Field_ID)
;

-- Oct 6, 2009 2:56:10 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50804
;

-- Oct 6, 2009 2:56:10 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50802
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50578
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50721
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50744
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50752
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50684
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50668
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50656
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50577
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50581
;

-- Oct 6, 2009 2:56:11 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Browse_Field_ID=50579
;

-- Oct 6, 2009 5:53:09 PM CDT
-- Warehouse Management Functionality
UPDATE AD_Browse SET WhereClause='NOT EXISTS (SELECT 1 FROM M_InOutLine WHERE M_InOutLine.C_OrderLine_ID = iobl.C_OrderLine_ID AND iobl.PickedQty >= M_InOutLine.MovementQty)  AND iob.IsSOTrx=''Y'' AND iobl.Processed=''Y'' AND ol.QtyOrdered <> ol.QtyDelivered',Updated=TO_DATE('2009-10-06 17:53:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Browse_ID=50002
;

-- Oct 6, 2009 6:16:18 PM CDT
-- Warehouse Management Functionality
UPDATE AD_View_Column SET ColumnSQL='ol.QtyOrdered-ol.QtyDelivered',Updated=TO_DATE('2009-10-06 18:16:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_View_Column_ID=50605
;

;