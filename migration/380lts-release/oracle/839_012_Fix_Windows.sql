-- Jul 10, 2012 10:25:39 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsMandatory='Y', IsUpdateable='N',Updated=TO_DATE('2012-07-10 22:25:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58087
;

-- Jul 10, 2012 10:25:42 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_View_Definition MODIFY AD_View_ID NUMBER(10)
;

-- Jul 10, 2012 10:25:42 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_View_Definition MODIFY AD_View_ID NOT NULL
;

-- Jul 10, 2012 10:26:24 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Tab SET AD_Column_ID=58101,Updated=TO_DATE('2012-07-10 22:26:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53257
;

-- Jul 10, 2012 10:26:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsMandatory='Y', IsUpdateable='N',Updated=TO_DATE('2012-07-10 22:26:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58101
;

-- Jul 10, 2012 10:26:36 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_View_Column MODIFY AD_View_Definition_ID NUMBER(10)
;

-- Jul 10, 2012 10:26:36 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_View_Column MODIFY AD_View_Definition_ID NOT NULL
;

-- Jul 10, 2012 10:27:14 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET Included_Tab_ID=NULL,Updated=TO_DATE('2012-07-10 22:27:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57515
;

-- Jul 10, 2012 10:27:32 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET Name='Create Columns',Updated=TO_DATE('2012-07-10 22:27:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57516
;

-- Jul 10, 2012 10:27:32 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=57516
;

-- Jul 10, 2012 10:27:43 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=57515
;

-- Jul 10, 2012 10:27:43 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57516
;

-- Jul 10, 2012 10:28:17 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Tab SET AD_Column_ID=58047,Updated=TO_DATE('2012-07-10 22:28:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53253
;

-- Jul 10, 2012 10:28:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Tab SET IsSingleRow='Y',Updated=TO_DATE('2012-07-10 22:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53253
;

-- Jul 10, 2012 10:30:17 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Tab SET AD_Column_ID=58021, IsSingleRow='Y',Updated=TO_DATE('2012-07-10 22:30:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53250
;

-- Jul 10, 2012 10:30:37 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Tab SET AD_Column_ID=57983,Updated=TO_DATE('2012-07-10 22:30:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53247
;

-- Jul 10, 2012 10:30:50 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsMandatory='Y', IsUpdateable='N',Updated=TO_DATE('2012-07-10 22:30:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57983
;

-- Jul 10, 2012 10:30:54 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_Browse_Field MODIFY AD_Browse_ID NUMBER(10)
;

-- Jul 10, 2012 10:30:54 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_Browse_Field MODIFY AD_Browse_ID NOT NULL
;

-- Jul 10, 2012 10:31:39 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Tab SET AD_Column_ID=58034,Updated=TO_DATE('2012-07-10 22:31:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53251
;

-- Jul 10, 2012 10:31:52 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsKey='N',Updated=TO_DATE('2012-07-10 22:31:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58034
;

-- Jul 10, 2012 10:33:02 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET Included_Tab_ID=NULL,Updated=TO_DATE('2012-07-10 22:33:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57453
;

-- Jul 10, 2012 10:33:11 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=57453
;

-- Jul 10, 2012 10:33:11 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=57454
;

-- Jul 10, 2012 10:33:11 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=57455
;

-- Jul 10, 2012 10:33:11 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=57530
;

-- Jul 10, 2012 10:33:50 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=57530
;

-- Jul 10, 2012 10:33:50 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=57455
;

-- Jul 10, 2012 10:33:58 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-07-10 22:33:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57530
;

-- Jul 10, 2012 10:35:51 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET DefaultValue='4', IsMandatory='Y',Updated=TO_DATE('2012-07-10 22:35:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58110
;

-- Jul 10, 2012 10:35:54 PM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_Browse MODIFY AccessLevel CHAR(1) DEFAULT '4'
;

-- Jul 10, 2012 10:35:54 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Browse SET AccessLevel='4' WHERE AccessLevel IS NULL
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=57521
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=57518
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=57520
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=57522
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57523
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57524
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57525
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57526
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57527
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=57528
;

-- Jul 10, 2012 10:58:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=57529
;

-- Jul 10, 2012 10:59:23 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-10 22:59:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57521
;

-- Jul 10, 2012 10:59:54 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=57483
;

-- Jul 10, 2012 10:59:54 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=57484
;

-- Jul 10, 2012 10:59:54 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57482
;

-- Jul 10, 2012 11:00:06 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-10 23:00:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57483
;

-- Jul 10, 2012 11:00:12 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-10 23:00:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57480
;

-- Jul 10, 2012 11:28:26 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57496
;

-- Jul 10, 2012 11:28:26 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57497
;

-- Jul 10, 2012 11:28:26 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57493
;

-- Jul 10, 2012 11:28:26 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57494
;

-- Jul 10, 2012 11:28:26 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57495
;

-- Jul 10, 2012 11:28:50 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57514
;

-- Jul 10, 2012 11:28:50 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57513
;

-- Jul 10, 2012 11:29:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57488
;

-- Jul 10, 2012 11:29:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57485
;

-- Jul 10, 2012 11:29:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57486
;

-- Jul 10, 2012 11:29:38 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57487
;

-- Jul 10, 2012 11:31:39 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-07-10 23:31:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57449
;

-- Jul 10, 2012 11:32:15 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57469
;

-- Jul 10, 2012 11:32:15 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57470
;

-- Jul 10, 2012 11:32:15 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57466
;

-- Jul 10, 2012 11:32:15 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57467
;

-- Jul 10, 2012 11:32:15 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57468
;

-- Jul 10, 2012 11:32:44 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-10 23:32:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57464
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=57424
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=57421
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=57425
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=57426
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57427
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57428
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57429
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57430
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57431
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=57432
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=57433
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=57434
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=57435
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=57436
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57437
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=57438
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=64654
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=57439
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=57440
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=64393
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=64394
;

-- Jul 10, 2012 11:33:34 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=57441
;

-- Jul 10, 2012 11:33:40 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-10 23:33:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57424
;

-- Jul 10, 2012 11:34:18 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57478
;

-- Jul 10, 2012 11:34:18 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57479
;

-- Jul 10, 2012 11:34:18 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57475
;

-- Jul 10, 2012 11:34:18 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57476
;

-- Jul 10, 2012 11:34:18 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57477
;

-- Jul 10, 2012 11:34:24 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-10 23:34:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57471
;

-- Jul 10, 2012 11:34:29 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-10 23:34:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57473
;

-- Jul 10, 2012 11:34:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_DATE('2012-07-10 23:34:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58034
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=57422
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=57425
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57426
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57427
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57428
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57429
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57430
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=57431
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=57432
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=57433
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=57434
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=57435
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57436
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=57437
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=57438
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=64654
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=57439
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=57440
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=64393
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=64394
;

-- Jul 10, 2012 11:35:45 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=57441
;

-- Jul 10, 2012 11:35:57 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-10 23:35:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57422
;

-- Jul 10, 2012 11:37:47 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_DATE('2012-07-10 23:37:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58011
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53203
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=586
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=138
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=139
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53228
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=249
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=141
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=300
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=589
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=295
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=216
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=140
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=142
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53228
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53227
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53012
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=143
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=201
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=176
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53086
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=239
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=517
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=499
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53089
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53090
;

-- Jul 10, 2012 11:42:42 PM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=50001
;

-- Jul 11, 2012 12:07:31 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-11 00:07:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57507
;

-- Jul 11, 2012 12:08:16 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2012-07-11 00:08:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58089
;

-- Jul 11, 2012 12:08:19 AM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_View_Definition MODIFY AD_Table_ID NUMBER(10)
;

-- Jul 11, 2012 12:08:19 AM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_View_Definition MODIFY AD_Table_ID NOT NULL
;

-- Jul 11, 2012 12:10:29 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET DefaultValue='@SQL=SELECT COALESCE(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_View_Definition WHERE AD_View_ID=@AD_View_ID@',Updated=TO_DATE('2012-07-11 00:10:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58088
;

-- Jul 11, 2012 12:20:23 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET Description='Creates the columns of View based on the table columns.',Updated=TO_DATE('2012-07-11 00:20:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57516
;

-- Jul 11, 2012 12:20:23 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=57516
;

-- Jul 11, 2012 12:22:09 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Process SET Description='Creates the columns of View based on the table columns.', Name='Create Columns',Updated=TO_DATE('2012-07-11 00:22:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53181
;

-- Jul 11, 2012 12:22:09 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53181
;

-- Jul 11, 2012 12:25:09 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='Y',Updated=TO_DATE('2012-07-11 00:25:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58064
;

-- Jul 11, 2012 12:26:15 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-11 00:26:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57489
;

-- Jul 11, 2012 12:26:44 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-11 00:26:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57490
;

-- Jul 11, 2012 12:31:40 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:31:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57987
;

-- Jul 11, 2012 12:32:03 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:32:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58067
;

-- Jul 11, 2012 12:34:53 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET Description='Creates the fields of Browser based on the View Columns.',Updated=TO_DATE('2012-07-11 00:34:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57455
;

-- Jul 11, 2012 12:34:53 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=57455
;

-- Jul 11, 2012 12:35:10 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Process SET Description='Creates the fields of Browser based on the View Columns.',Updated=TO_DATE('2012-07-11 00:35:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53180
;

-- Jul 11, 2012 12:35:10 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53180
;

-- Jul 11, 2012 12:36:39 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:36:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58004
;

-- Jul 11, 2012 12:36:43 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:36:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58005
;

-- Jul 11, 2012 12:36:47 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2012-07-11 00:36:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57456
;

-- Jul 11, 2012 12:37:08 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2012-07-11 00:37:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58013
;

-- Jul 11, 2012 12:37:10 AM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_Browse_Access MODIFY IsReadWrite CHAR(1) DEFAULT 'Y'
;

-- Jul 11, 2012 12:37:10 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Browse_Access SET IsReadWrite='Y' WHERE IsReadWrite IS NULL
;

-- Jul 11, 2012 12:38:35 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:38:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58014
;

-- Jul 11, 2012 12:38:44 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:38:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58015
;

-- Jul 11, 2012 12:40:30 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET SeqNo=2,Updated=TO_DATE('2012-07-11 00:40:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57997
;

-- Jul 11, 2012 12:40:42 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=1,Updated=TO_DATE('2012-07-11 00:40:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57998
;

-- Jul 11, 2012 12:41:59 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET AD_Reference_ID=19, IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:41:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58021
;

-- Jul 11, 2012 12:44:17 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2012-07-11 00:44:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57975
;

-- Jul 11, 2012 12:44:19 AM CDT
-- ADEMPIERE-10 Smart Browser
ALTER TABLE AD_Browse_Field MODIFY IsCentrallyMaintained CHAR(1) DEFAULT 'Y'
;

-- Jul 11, 2012 12:51:46 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:51:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58028
;

-- Jul 11, 2012 12:58:31 AM CDT
-- ADEMPIERE-10 Smart Browser
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-07-11 00:58:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58066
;



