CREATE OR REPLACE VIEW RV_M_Forecast AS
SELECT
f.AD_Client_ID ,
fl.AD_Org_ID,
f.M_Forecast_ID ,
f.Name ,
f.PP_Calendar_ID ,
f.PP_PeriodDefinition_ID ,
f.C_Project_ID,
f.C_ProjectPhase_ID,
f.C_Campaign_ID,
fl.PP_Period_ID,
fl.DatePromised,
fl.M_Product_ID ,
fl.SalesRep_ID,
p.C_UOM_ID,
fl.Qty,
fl.QtyCalculated,
 pp.PriceList,
 pp.PriceStd,
 pp.PriceLimit,
 (pp.PriceStd * fl.Qty) AS TotalAmt,
	p.M_Product_Category_ID,
	p.Classification,
	p.Group1,
	p.Group2
	FROM M_Forecast f
	INNER JOIN M_ForecastLine fl ON (f.M_Forecast_ID = fl.M_Forecast_ID)
	INNER JOIN M_Product p ON (p.M_Product_ID=fl.M_Product_ID)
	INNER JOIN M_PriceList pl ON (pl.M_PriceList_ID=f.M_PriceList_ID)
    LEFT JOIN(SELECT ffl.M_ForecastLine_ID, pp.PriceList, pp.PriceStd, pp.PriceLimit
                FROM M_Forecast ff
                INNER JOIN M_ForecastLine ffl ON(ffl.M_Forecast_ID = ff.M_Forecast_ID)
                INNER JOIN PP_Period pr ON(pr.PP_Period_ID = ffl.PP_Period_ID)
                INNER JOIN M_PriceList_Version plv ON(plv.M_PriceList_ID = ff.M_PriceList_ID)
                INNER JOIN M_ProductPrice pp ON(pp.M_PriceList_Version_ID = plv.M_PriceList_Version_ID 
                                                    AND pp.M_Product_ID = ffl.M_Product_ID)
                WHERE plv.IsActive = 'Y'
                AND plv.ValidFrom <= pr.StartDate
                AND ROWNUM <= 1
                ORDER BY plv.ValidFrom DESC) pp ON(pp.M_ForecastLine_ID = fl.M_ForecastLine_ID)