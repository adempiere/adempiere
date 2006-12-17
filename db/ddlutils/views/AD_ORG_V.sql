CREATE OR REPLACE VIEW AD_ORG_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, VALUE, NAME, DESCRIPTION, 
 ISSUMMARY, C_LOCATION_ID, DUNS, TAXID, SUPERVISOR_ID, 
 PARENT_ORG_ID, AD_ORGTYPE_ID, M_WAREHOUSE_ID, C_BPARTNER_ID)
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, 
    o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
    o.Value, o.Name, o.Description, o.IsSummary,
    i.C_Location_ID, i.DUNS, i.TaxID,
    i.Supervisor_ID, i.Parent_Org_ID, 
    i.AD_OrgType_ID, i.M_Warehouse_ID,
    bp.C_BPartner_ID
FROM AD_Org o
    INNER JOIN AD_OrgInfo i ON (o.AD_Org_ID=i.AD_Org_ID)
    LEFT OUTER JOIN C_BPartner bp ON (o.AD_Org_ID=bp.AD_OrgBP_ID);



