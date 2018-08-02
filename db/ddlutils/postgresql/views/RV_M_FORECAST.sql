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
    INNER JOIN PP_Period pr ON(pr.PP_Period_ID = fl.PP_Period_ID)
    LEFT JOIN M_PriceList_Version plv ON(plv.M_PriceList_ID = pl.M_PriceList_ID)
    LEFT JOIN M_ProductPrice pp ON(pp.M_PriceList_Version_ID = plv.M_PriceList_Version_ID AND pp.M_Product_ID = p.M_Product_ID)
    WHERE fl.IsActive = 'Y'
    AND f.IsActive = 'Y'
    AND pp.M_PriceList_Version_ID = (SELECT plvv.M_PriceList_Version_ID 
						FROM M_PriceList_Version plvv
						WHERE plvv.M_PriceList_ID = pl.M_PriceList_ID
						AND plvv.IsActive = 'Y'
                        AND plvv.ValidFrom <= pr.StartDate
						ORDER BY plvv.ValidFrom DESC
						LIMIT 1)