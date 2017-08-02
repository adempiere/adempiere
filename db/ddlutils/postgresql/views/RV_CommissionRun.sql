CREATE OR REPLACE VIEW RV_CommissionRun AS
SELECT cr.AD_Client_ID, cr.AD_Org_ID, cr.IsActive, cr.C_CommissionRun_ID, cr.Created, cr.Updated, cr.CreatedBy, 
cr.UpdatedBy, cr.DateDoc, cr.StartDate, cr.EndDate, cr.GrandTotal, 
ca.C_CommissionAmt_ID, ca.C_BPartner_ID AS Commission_BPartner_ID, ca.ConvertedAmt, ca.ActualQty, ca.CommissionAmt, 
cl.QtyMultiplier, cl.C_BPartner_ID, cl.M_Product_ID, cl.QtySubtract, cl.Org_ID, cl.C_Commission_ID, cl.C_BP_Group_ID, 
cl.Line, cl.AmtSubtract, cl.C_SalesRegion_ID, cl.C_CommissionLine_ID, cl.CommissionOrders, cl.AmtMultiplier, cl.M_Product_Category_ID, 
cl.IsPositiveOnly, cl.PaymentRule, cl.DaysFrom, cl.DaysTo, cl.C_Channel_ID, cl.InvoiceCollectionType, cl.C_DunningLevel_ID, cl.Description, cl.M_Product_Class_ID, 
cl.M_Product_Classification_ID, cl.M_Product_Group_ID, cl.C_Campaign_ID, cl.C_Project_ID, cl.C_PaymentTerm_ID,
c.C_CommissionGroup_ID, c.Name, c.C_Currency_ID, c.DocBasisType, c.FrequencyType, c.C_Charge_ID, c.IsAllowRMA, c.ListDetails, c.DateLastRun, c.IsTotallyPaid,
cr.DocStatus, cr.C_DocType_ID
FROM C_CommissionRun cr
INNER JOIN C_CommissionAmt ca ON(ca.C_CommissionRun_ID = cr.C_CommissionRun_ID)
INNER JOIN C_CommissionLine cl ON(cl.C_CommissionLine_ID = ca.C_CommissionLine_ID)
INNER JOIN C_Commission c ON(c.C_Commission_ID = cl.C_Commission_ID)