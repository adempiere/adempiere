CREATE OR REPLACE VIEW DD_ORDER_HEADER_V
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar) AS AD_Language,
	o.DD_Order_ID,o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	o.Description,
	o.POReference,
	o.C_Charge_ID, o.ChargeAmt,
	o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule
FROM DD_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);


CREATE OR REPLACE VIEW DD_ORDER_HEADER_VT
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	dt.AD_Language,o.DD_Order_ID,
	o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	o.Description,
	o.POReference,
	o.C_Charge_ID, o.ChargeAmt,
	o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule
FROM DD_Order o
	INNER JOIN C_DocType_Trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);

-- Feb 7, 2009 12:37:50 PM ECT
-- Fix Distrubution Order Format
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,ImageURL,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,0,100,130,51070,50011,0,0,TO_TIMESTAMP('2009-02-07 12:37:50','YYYY-MM-DD HH24:MI:SS'),0,'D','N','org/compiere/images/C10030HR.png','Y','N','Y','N','N','N','N','N','Y','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','N','X',1,30,100,'Logo','H','I',20,10,'N',0,TO_TIMESTAMP('2009-02-07 12:37:50','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 7, 2009 12:37:50 PM ECT
-- Fix Distrubution Order Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51070 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 7, 2009 12:40:55 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XSpace=0, YPosition=100, YSpace=0,Updated=TO_TIMESTAMP('2009-02-07 12:40:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50425
;

-- Feb 7, 2009 12:41:37 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsOrderBy='N', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 12:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50426
;

-- Feb 7, 2009 12:43:12 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSuppressNull='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 12:43:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50428
;

-- Feb 7, 2009 12:58:45 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=240, XSpace=0, YPosition=100, YSpace=0,Updated=TO_TIMESTAMP('2009-02-07 12:58:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50432
;

-- Feb 7, 2009 12:59:50 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Date Promised :', SortNo=0, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2009-02-07 12:59:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50432
;

-- Feb 7, 2009 12:59:50 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50432
;

-- Feb 7, 2009 1:01:40 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintName='Customer No  :', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 13:01:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50433
;

-- Feb 7, 2009 1:01:40 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50433
;

-- Feb 7, 2009 1:02:47 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSuppressNull='Y', PrintName='Reference No  :', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 13:02:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50434
;

-- Feb 7, 2009 1:02:47 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50434
;

-- Feb 7, 2009 1:06:45 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET XPosition=0, XSpace=10, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 13:06:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50438
;

-- Feb 7, 2009 1:07:20 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintNameSuffix=' - ', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 13:07:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50439
;

-- Feb 7, 2009 1:07:20 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50439
;

-- Feb 7, 2009 1:07:43 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=180,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50418
;

-- Feb 7, 2009 1:07:43 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=190,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50441
;

-- Feb 7, 2009 1:07:43 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=200,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50442
;

-- Feb 7, 2009 1:07:43 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=210,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50440
;

-- Feb 7, 2009 1:08:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XPosition=0, XSpace=10, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 13:08:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50418
;

-- Feb 7, 2009 1:08:57 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintAreaType='H', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 13:08:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50418
;

-- Feb 7, 2009 1:09:17 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', PrintAreaType='H', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 13:09:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50441
;

-- Feb 7, 2009 1:09:38 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsRelativePosition='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2009-02-07 13:09:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50442
;

-- Feb 7, 2009 1:10:07 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SortNo=0,IsOrderBy='N' WHERE AD_PrintFormatItem_ID=50364
;

-- Feb 7, 2009 1:10:07 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SortNo=0,IsOrderBy='N' WHERE AD_PrintFormatItem_ID=50382
;

-- Feb 7, 2009 1:10:07 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SortNo=0,IsOrderBy='N' WHERE AD_PrintFormatItem_ID=50363
;

-- Feb 7, 2009 1:10:07 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SortNo=0,IsOrderBy='N' WHERE AD_PrintFormatItem_ID=50385
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50360
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50369
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50367
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50368
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50370
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50371
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50358
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50373
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50374
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50375
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50376
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50377
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50378
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50359
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50379
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50380
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50381
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50357
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50382
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50383
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50384
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50385
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50387
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50388
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50390
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50392
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50393
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50391
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50364
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50389
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50365
;

-- Feb 7, 2009 1:11:25 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50366
;

-- Feb 7, 2009 1:11:54 PM ECT
-- Fix Distrubution Order Format
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsSuppressRepeats,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,53934,0,51071,50010,0,0,TO_TIMESTAMP('2009-02-07 13:11:54','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','N','X',1,0,0,'Confirmed Qty','C','F','Confirmed Qty',0,60,'N',0,TO_TIMESTAMP('2009-02-07 13:11:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 7, 2009 1:11:54 PM ECT
-- Fix Distrubution Order Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51071 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 7, 2009 1:11:55 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=53934) WHERE AD_PrintFormatItem_ID = 51071 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=53934 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51071) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Feb 7, 2009 1:29:34 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51071
;

-- Feb 7, 2009 1:29:34 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50365
;

-- Feb 7, 2009 1:29:34 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50366
;

-- Feb 7, 2009 1:32:52 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', SortNo=0, XSpace=0, YPosition=200, YSpace=0,Updated=TO_TIMESTAMP('2009-02-07 13:32:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50437
;

-- Feb 7, 2009 1:35:39 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_Column SET AD_Reference_ID=15,Updated=TO_TIMESTAMP('2009-02-07 13:35:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56216
;

-- Feb 7, 2009 1:35:48 PM ECT
-- Fix Distrubution Order Format
UPDATE AD_Column SET AD_Reference_ID=15,Updated=TO_TIMESTAMP('2009-02-07 13:35:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56217
;
