CREATE OR REPLACE VIEW RV_CopyProcess_Para 
(AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,
AD_Element_ID, ColumnName, AD_Reference_ID, IsMandatory, IsRange, 
DefaultValue, DefaultValue2, AD_ReportView_ID, CopyFromProcess, AD_Process_ID, AD_Process_Para_ID, AD_Column_ID) 
AS
SELECT c.AD_Client_ID, c.AD_Org_ID, c.Created, c.CreatedBy, c.Updated, c.UpdatedBy, c.IsActive,
c.AD_Element_ID, c.ColumnName, c.AD_Reference_ID, c.IsMandatory, 'N' AS IsRange, 
c.DefaultValue, null AS DefaultValue2, rv.AD_ReportView_ID, 'N' AS CopyFromProcess, null AS AD_Process_ID, null AS AD_Process_Para_ID, c.AD_Column_ID
FROM AD_ReportView rv
INNER JOIN AD_Table t ON(t.AD_Table_ID = rv.AD_Table_ID)
INNER JOIN AD_Column c ON(c.AD_Table_ID = t.AD_Table_ID)
UNION ALL
SELECT pp.AD_Client_ID, pp.AD_Org_ID, pp.Created, pp.CreatedBy, pp.Updated, pp.UpdatedBy, pp.IsActive,
pp.AD_Element_ID, pp.ColumnName, pp.AD_Reference_ID, pp.IsMandatory, 'N' AS IsRange, 
pp.DefaultValue, pp.DefaultValue2, null AS AD_ReportView_ID, 'Y' AS CopyFromProcess, p.AD_Process_ID, pp.AD_Process_Para_ID, null AS AD_Column_ID
FROM AD_Process p
INNER JOIN AD_Process_Para pp ON(pp.AD_Process_ID = p.AD_Process_ID)