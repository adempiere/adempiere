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
