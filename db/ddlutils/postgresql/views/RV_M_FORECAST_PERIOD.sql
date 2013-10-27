CREATE OR REPLACE VIEW RV_M_Forecast_Period AS
SELECT AD_Client_ID ,
AD_Org_ID,
M_Forecast_ID ,
MAX(Name) AS Name,
PP_Calendar_ID ,
PP_PeriodDefinition_ID ,
PP_Period_ID,
C_Project_ID,
C_ProjectPhase_ID,
C_Campaign_ID,
M_Product_ID ,
C_UOM_ID,
SUM(Qty) AS Qty,
SUM(QtyCalculated) AS QtyCalculated,
SUM(TotalAmt) AS TotalAmt
FROM RV_M_Forecast f 
GROUP BY AD_Client_ID , AD_Org_ID , M_Forecast_ID , M_Product_ID , C_UOM_ID, PP_Calendar_ID , PP_PeriodDefinition_ID  , PP_Period_ID , C_Project_ID , C_ProjectPhase_ID,C_Campaign_ID;