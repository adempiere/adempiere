-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53528
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53533
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53522
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53524
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53525
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53526
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53529
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53530
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53531
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53532
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=54420
;

-- 03.02.2009 08:32:27 EET
-- 
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53534
;

-- 03.02.2009 08:33:09 EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-03 08:33:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53523
;

-- 03.02.2009 08:33:12 EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-03 08:33:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53522
;

-- 03.02.2009 08:33:22 EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-03 08:33:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53525
;

-- 03.02.2009 08:33:27 EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-03 08:33:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53526
;

-- 03.02.2009 08:33:33 EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-03 08:33:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53529
;

-- 03.02.2009 08:34:08 EET
-- 
UPDATE AD_Field SET DisplayLength=10, IsSameLine='N',Updated=TO_DATE('2009-02-03 08:34:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53530
;

-- 03.02.2009 08:34:13 EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-03 08:34:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53531
;





-- 03.02.2009 08:36:29 EET
-- 
UPDATE AD_Element SET Description='Maximum order quantity in UOM', Help='The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.', Name='Maximum Order Qty', PrintName='Maximum Order Qty',Updated=TO_DATE('2009-02-03 08:36:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53264
;

-- 03.02.2009 08:36:29 EET
-- 
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53264
;

-- 03.02.2009 08:36:29 EET
-- 
UPDATE AD_Column SET ColumnName='Order_Max', Name='Maximum Order Qty', Description='Maximum order quantity in UOM', Help='The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.' WHERE AD_Element_ID=53264
;

-- 03.02.2009 08:36:29 EET
-- 
UPDATE AD_Process_Para SET ColumnName='Order_Max', Name='Maximum Order Qty', Description='Maximum order quantity in UOM', Help='The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.', AD_Element_ID=53264 WHERE UPPER(ColumnName)='ORDER_MAX' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 03.02.2009 08:36:29 EET
-- 
UPDATE AD_Process_Para SET ColumnName='Order_Max', Name='Maximum Order Qty', Description='Maximum order quantity in UOM', Help='The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.' WHERE AD_Element_ID=53264 AND IsCentrallyMaintained='Y'
;

-- 03.02.2009 08:36:29 EET
-- 
UPDATE AD_Field SET Name='Maximum Order Qty', Description='Maximum order quantity in UOM', Help='The Maximum Order Quantity indicates the biggest quantity of this product which can be ordered.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53264) AND IsCentrallyMaintained='Y'
;

-- 03.02.2009 08:36:29 EET
-- 
UPDATE AD_PrintFormatItem pi SET PrintName='Maximum Order Qty', Name='Maximum Order Qty' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53264)
;

-- 03.02.2009 08:36:34 EET
-- 
UPDATE AD_Element_Trl SET IsTranslated='Y',Updated=TO_DATE('2009-02-03 08:36:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53264 AND AD_Language='ro_RO'
;

-- 03.02.2009 08:37:01 EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-03 08:37:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54420
;

-- 03.02.2009 08:37:06 EET
-- 
UPDATE AD_Field SET DisplayLength=10,Updated=TO_DATE('2009-02-03 08:37:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53534
;

-- 03.02.2009 08:38:25 EET
-- 
UPDATE AD_Field SET DisplayLogic='@IsPurchased@=N',Updated=TO_DATE('2009-02-03 08:38:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53523
;

-- 03.02.2009 08:38:33 EET
-- 
UPDATE AD_Field SET DisplayLogic='@IsPurchased@=N',Updated=TO_DATE('2009-02-03 08:38:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53531
;

-- 03.02.2009 08:38:39 EET
-- 
UPDATE AD_Field SET DisplayLogic='@IsPurchased@=N',Updated=TO_DATE('2009-02-03 08:38:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53532
;

-- 03.02.2009 08:38:46 EET
-- 
UPDATE AD_Field SET DisplayLogic='@IsPurchased@=N',Updated=TO_DATE('2009-02-03 08:38:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53530
;

-- 03.02.2009 08:44:24 EET
-- 
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_DATE('2009-02-03 08:44:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53390
;

-- 03.02.2009 08:44:49 EET
-- 
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_DATE('2009-02-03 08:44:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53400
;

-- 03.02.2009 08:49:07 EET
-- 
UPDATE AD_Column SET DefaultValue='-1',Updated=TO_DATE('2009-02-03 08:49:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53390
;

-- 03.02.2009 08:49:22 EET
-- 
UPDATE AD_Column SET DefaultValue='-1',Updated=TO_DATE('2009-02-03 08:49:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53400
;

