-- Jul 8, 2008 6:53:30 PM CDT
-- Fix Forecast Report
UPDATE AD_Process_Para SET Description='Material Forecast', Name='Forecast',Updated=TO_DATE('2008-07-08 18:53:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53202
;

-- Jul 8, 2008 6:53:30 PM CDT
-- Fix Forecast Report
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53202
;

-- Jul 8, 2008 6:53:47 PM CDT
-- Fix Forecast Report
UPDATE AD_Process_Para_Trl SET Name='Pronostico',Description='Pronostico de Venta',Updated=TO_DATE('2008-07-08 18:53:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53202 AND AD_Language='es_MX'
;

-- Jul 8, 2008 6:53:50 PM CDT
-- Fix Forecast Report
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Updated=TO_DATE('2008-07-08 18:53:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53202 AND AD_Language='es_MX'
;

-- Jul 8, 2008 6:54:06 PM CDT
-- Fix Forecast Report
UPDATE AD_Process_Para SET Name='Rule',Updated=TO_DATE('2008-07-08 18:54:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53201
;

-- Jul 8, 2008 6:54:06 PM CDT
-- Fix Forecast Report
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53201
;

-- Jul 8, 2008 6:54:33 PM CDT
-- Fix Forecast Report
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Regla de C�lculo',Updated=TO_DATE('2008-07-08 18:54:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53201 AND AD_Language='es_MX'
;

-- Jul 8, 2008 7:02:10 PM CDT
-- Forecast
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,453,0,53148,53209,19,'M_Product_Category_ID',TO_DATE('2008-07-08 19:01:47','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','EE01',22,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','N','N','Product Category',30,TO_DATE('2008-07-08 19:01:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 8, 2008 7:02:10 PM CDT
-- Forecast
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53209 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 8, 2008 7:03:14 PM CDT
-- Forecast
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53148,53210,30,'M_Product_ID',TO_DATE('2008-07-08 19:03:12','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',22,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','Product',40,TO_DATE('2008-07-08 19:03:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 8, 2008 7:03:14 PM CDT
-- Forecast
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53210 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 8, 2008 7:04:08 PM CDT
-- Forecast
UPDATE AD_Process_Para SET Description='Material Forecast', Help='Material Forecast', Name='Forecast',Updated=TO_DATE('2008-07-08 19:04:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53193
;

-- Jul 8, 2008 7:04:08 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53193
;

-- Jul 8, 2008 7:04:22 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Pronostico',Description='Pronostico',Updated=TO_DATE('2008-07-08 19:04:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53193 AND AD_Language='es_MX'
;

-- Jul 8, 2008 7:04:51 PM CDT
-- Forecast
UPDATE AD_Process_Para SET Description='Accounting Calendar Name', Help='The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.', Name='Calendar',Updated=TO_DATE('2008-07-08 19:04:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53192
;

-- Jul 8, 2008 7:04:51 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53192
;

-- Jul 8, 2008 7:05:27 PM CDT
-- Forecast
UPDATE AD_Process_Para SET Description='Calendar Year', Help='The Year uniquely identifies an accounting year for a calendar.', Name='Year',Updated=TO_DATE('2008-07-08 19:05:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53191
;

-- Jul 8, 2008 7:05:27 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53191
;

-- Jul 8, 2008 7:05:55 PM CDT
-- Forecast
UPDATE AD_Process_Para SET Description='Period of the Calendar', Help='The Period indicates an exclusive range of dates for a calendar.', Name='Period',Updated=TO_DATE('2008-07-08 19:05:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53190
;

-- Jul 8, 2008 7:05:55 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53190
;

-- Jul 8, 2008 7:06:18 PM CDT
-- Forecast
UPDATE AD_Process_Para SET Description='Category of a Product', Help='Identifies the category which this product belongs to.  Product categories are used for pricing and selection.', Name='Product Category',Updated=TO_DATE('2008-07-08 19:06:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53188
;

-- Jul 8, 2008 7:06:18 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53188
;

-- Jul 8, 2008 7:07:13 PM CDT
-- Forecast
UPDATE AD_Process_Para SET AD_Element_ID=1063, Description='Sales Representative or Company Agent', Help='The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.', Name='Sales Representative',Updated=TO_DATE('2008-07-08 19:07:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53186
;

-- Jul 8, 2008 7:07:13 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53186
;

-- Jul 8, 2008 7:07:38 PM CDT
-- Forecast
UPDATE AD_Process_Para SET Description='Product, Service, Item', Help='Identifies an item which is either purchased or sold in this organization.', Name='Product',Updated=TO_DATE('2008-07-08 19:07:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53189
;

-- Jul 8, 2008 7:07:38 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53189
;

-- Jul 8, 2008 7:08:02 PM CDT
-- Forecast
UPDATE AD_Process_Para SET Description='Date Order was promised', Help='The Date Promised indicates the date, if any, that an Order was promised for.', Name='Date Promised',Updated=TO_DATE('2008-07-08 19:08:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53187
;

-- Jul 8, 2008 7:08:02 PM CDT
-- Forecast
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53187
;

-- Jul 8, 2008 7:10:35 PM CDT
-- Forecast
UPDATE AD_Column SET AD_Element_ID=1539, ColumnName='TotalAmt', Description='Total Amount', FieldLength=10, Help='The Total Amount indicates the total document amount.', Name='Total Amount',Updated=TO_DATE('2008-07-08 19:10:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56134
;

-- Jul 8, 2008 7:10:35 PM CDT
-- Forecast
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56134
;

-- Jul 8, 2008 7:10:35 PM CDT
-- Forecast
UPDATE AD_Field SET Name='Total Amount', Description='Total Amount', Help='The Total Amount indicates the total document amount.' WHERE AD_Column_ID=56134 AND IsCentrallyMaintained='Y'
;

-- Jul 8, 2008 7:11:32 PM CDT
-- Forecast
UPDATE AD_Column SET AD_Element_ID=1539, ColumnName='TotalAmt', Description='Total Amount', Help='The Total Amount indicates the total document amount.', Name='Total Amount',Updated=TO_DATE('2008-07-08 19:11:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56148
;

-- Jul 8, 2008 7:11:32 PM CDT
-- Forecast
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56148
;

-- Jul 8, 2008 7:11:32 PM CDT
-- Forecast
UPDATE AD_Field SET Name='Total Amount', Description='Total Amount', Help='The Total Amount indicates the total document amount.' WHERE AD_Column_ID=56148 AND IsCentrallyMaintained='Y'
;

-- Jul 8, 2008 7:59:40 PM CDT
-- Forecast
UPDATE AD_Column SET AD_Element_ID=469, ColumnName='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', Name='Name',Updated=TO_DATE('2008-07-08 19:59:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56147
;

-- Jul 8, 2008 7:59:40 PM CDT
-- Forecast
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56147
;

-- Jul 8, 2008 7:59:40 PM CDT
-- Forecast
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=56147 AND IsCentrallyMaintained='Y'
;



-- Jul 9, 2008 10:48:20 AM CDT
-- Forecast
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-09 10:48:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56144
;

-- Jul 9, 2008 10:48:36 AM CDT
-- Forecast
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-09 10:48:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56145
;

-- Jul 9, 2008 10:48:47 AM CDT
-- Forecast
UPDATE AD_Column SET FieldLength=60,Updated=TO_DATE('2008-07-09 10:48:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56147
;

-- Jul 9, 2008 10:48:53 AM CDT
-- Forecast
UPDATE AD_Column SET FieldLength=22,Updated=TO_DATE('2008-07-09 10:48:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56148
;

-- Jul 9, 2008 10:49:12 AM CDT
-- Forecast
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2008-07-09 10:49:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56148
;

-- Jul 9, 2008 10:49:51 AM CDT
-- Forecast
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56142
;

-- Jul 9, 2008 10:49:51 AM CDT
-- Forecast
DELETE FROM AD_Column WHERE AD_Column_ID=56142
;

-- Jul 9, 2008 10:58:32 AM CDT
-- Forecast
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56146
;

-- Jul 9, 2008 10:58:32 AM CDT
-- Forecast
DELETE FROM AD_Column WHERE AD_Column_ID=56146
;

-- Jul 9, 2008 11:30:19 AM CDT
-- Forecast
DELETE  FROM  AD_Process_Para_Trl WHERE AD_Process_Para_ID=53196
;

-- Jul 9, 2008 11:30:19 AM CDT
-- Forecast
DELETE FROM AD_Process_Para WHERE AD_Process_Para_ID=53196
;

-- Jul 9, 2008 11:30:29 AM CDT
-- Forecast
DELETE  FROM  AD_Process_Para_Trl WHERE AD_Process_Para_ID=53194
;

-- Jul 9, 2008 11:30:29 AM CDT
-- Forecast
DELETE FROM AD_Process_Para WHERE AD_Process_Para_ID=53194
;


-- Jul 8, 2008 7:59:52 PM CDT
-- Forecast
DELETE  FROM  AD_Element_Trl WHERE AD_Element_ID=53653
;

-- Jul 8, 2008 7:59:52 PM CDT
-- Forecast
DELETE FROM AD_Element WHERE AD_Element_ID=53653
;


-- Jul 8, 2008 7:59:46 PM CDT
-- Forecast
DELETE  FROM  AD_Element_Trl WHERE AD_Element_ID=53654
;


-- Jul 8, 2008 7:59:46 PM CDT
-- Forecast
DELETE FROM AD_Element WHERE AD_Element_ID=53654
;

CREATE OR REPLACE VIEW RV_M_Forecast AS
SELECT
f.AD_Client_ID ,
fl.AD_Org_ID,
f.M_Forecast_ID ,
f.Name ,
f.C_Calendar_ID ,
f.C_Year_ID ,
fl.C_Period_ID,
fl.DatePromised,
fl.M_Product_ID ,
fl.SalesRep_ID,
p.C_UOM_ID,
fl.Qty,
fl.QtyCalculated,
 (SELECT pp.PriceList FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
  AS PriceList,
   (SELECT pp.PriceStd FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
    AS PriceStd,
     (SELECT pp.PriceLimit FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
      AS PriceLimit,
       (SELECT pp.PriceStd FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
        * fl.Qty AS TotalAmt,
	p.M_Product_Category_ID,
	p.Classification,
	p.Group1,
	p.Group2
	FROM M_Forecast f
	INNER JOIN M_ForecastLine fl ON (f.M_Forecast_ID = fl.M_Forecast_ID)
	INNER JOIN M_Product p ON (p.M_Product_ID=fl.M_Product_ID)
	INNER JOIN M_PriceList pl ON (pl.M_PriceList_ID=f.M_PriceList_ID);

CREATE OR REPLACE VIEW RV_M_Forecast_Period AS
SELECT AD_Client_ID , 
AD_Org_ID, 
M_Forecast_ID , 
MAX(Name) AS Name, 
C_Calendar_ID , 
C_Year_ID , 
C_Period_ID, 
M_Product_ID , 
C_UOM_ID, 
SUM(Qty) AS Qty,
SUM(QtyCalculated) AS QtyCalculated,
SUM(TotalAmt) AS TotalAmt
FROM RV_M_Forecast f GROUP BY AD_Client_ID , AD_Org_ID , M_Forecast_ID , M_Product_ID , C_UOM_ID, C_Calendar_ID , C_Year_ID , C_Period_ID 
;



-- Jul 9, 2008 10:04:30 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=10300
;

-- Jul 9, 2008 10:04:30 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=10298
;

-- Jul 9, 2008 10:05:06 PM CDT
-- Forecast
UPDATE AD_Field SET Included_Tab_ID=654,Updated=TO_DATE('2008-07-09 22:05:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10300
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=10304
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=10307
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=10305
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=53638
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=10301
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=10303
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=53639
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=10306
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=10310
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56277
;

-- Jul 9, 2008 10:08:15 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=10308
;

-- Jul 9, 2008 10:08:27 PM CDT
-- Forecast
UPDATE AD_Tab SET IsSingleRow='Y',Updated=TO_DATE('2008-07-09 22:08:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=653
;

-- Jul 9, 2008 10:11:20 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56277
;

-- Jul 9, 2008 10:11:20 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=53638
;

-- Jul 9, 2008 10:11:20 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=10301
;

-- Jul 9, 2008 10:11:20 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=10306
;

-- Jul 9, 2008 10:11:20 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=10310
;

-- Jul 9, 2008 10:11:20 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=10303
;

-- Jul 9, 2008 10:11:20 PM CDT
-- Forecast
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53639
;

-- Jul 9, 2008 10:12:55 PM CDT
-- Forecast
UPDATE AD_Column SET DefaultValue='@#AD_User_ID@',Updated=TO_DATE('2008-07-09 22:12:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56113
;

-- Jul 9, 2008 10:20:17 PM CDT
-- Forecast
UPDATE AD_Column SET DefaultValue='@AD_Org_ID@',Updated=TO_DATE('2008-07-09 22:20:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11937
;

-- Jul 9, 2008 10:20:27 PM CDT
-- Forecast
UPDATE AD_Column SET ReadOnlyLogic='@M_Warehouse_ID@',Updated=TO_DATE('2008-07-09 22:20:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53411
;

