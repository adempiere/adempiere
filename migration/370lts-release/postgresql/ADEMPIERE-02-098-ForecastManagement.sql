DROP  VIEW RV_M_Forecast_Period ;
DROP  VIEW RV_M_Forecast;
CREATE OR REPLACE VIEW RV_M_Forecast AS
SELECT
f.AD_Client_ID ,
fl.AD_Org_ID,
f.M_Forecast_ID ,
f.Name ,
f.PP_Calendar_ID ,
f.PP_PeriodDefinition_ID ,
fl.PP_Period_ID,
fl.DatePromised,
fl.M_Product_ID ,
fl.SalesRep_ID,
p.C_UOM_ID,
fl.Qty,
fl.QtyCalculated,
 (SELECT pp.PriceList FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID ) WHERE plv.IsActive = 'Y' AND pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
  AS PriceList,
   (SELECT pp.PriceStd FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE plv.IsActive = 'Y' AND pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
    AS PriceStd,
     (SELECT pp.PriceLimit FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE plv.IsActive = 'Y' AND pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
      AS PriceLimit,
       (SELECT pp.PriceStd FROM M_PriceList_Version plv INNER JOIN M_ProductPrice pp ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID) WHERE  plv.IsActive = 'Y' AND pp.M_Product_ID=fl.M_Product_ID AND plv.M_PriceList_ID=pl.M_PriceList_ID)
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
PP_Calendar_ID ,
PP_PeriodDefinition_ID ,
PP_Period_ID,
M_Product_ID ,
C_UOM_ID,
SUM(Qty) AS Qty,
SUM(QtyCalculated) AS QtyCalculated,
SUM(TotalAmt) AS TotalAmt
FROM RV_M_Forecast f GROUP BY AD_Client_ID , AD_Org_ID , M_Forecast_ID , M_Product_ID , C_UOM_ID, PP_Calendar_ID , PP_PeriodDefinition_ID  , PP_Period_ID
;
