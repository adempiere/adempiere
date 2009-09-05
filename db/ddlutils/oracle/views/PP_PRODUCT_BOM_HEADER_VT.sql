CREATE OR REPLACE VIEW PP_Product_BOM_Header_vt
AS 
SELECT b.AD_Client_ID, b.AD_Org_ID, b.IsActive, b.Created, b.CreatedBy, b.Updated, b.UpdatedBy,
	'' AS AD_Language,
	b.DocumentNo,bt.Name,bt.help,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
 	bt.Description,processing, validfrom, validto, bomtype, bomuse, m_changenotice_id,
	b.M_Product_ID,b.M_AttributeSetInstance_ID,b.C_UOM_ID,b.PP_Product_BOM_ID, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM PP_Product_BOM b
	LEFT  JOIN PP_Product_BOM_Trl bt ON (bt.PP_Product_BOM_ID=b.PP_Product_BOM_ID)
	INNER JOIN AD_OrgInfo oi ON (oi.AD_Org_ID=b.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (b.AD_Client_ID=ci.AD_Client_ID);
