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
