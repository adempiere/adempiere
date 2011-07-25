CREATE OR REPLACE VIEW M_INOUTLINEMA_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_INOUT_ID, M_INOUTLINE_ID, LINE, 
 M_PRODUCT_ID, M_ATTRIBUTESETINSTANCE_ID, MOVEMENTQTY, M_LOCATOR_ID)
AS 
SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_InOut_ID, m.M_InOutLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID
FROM M_InOutLineMA m INNER JOIN M_InOutLine l ON (m.M_InOutLine_ID=l.M_InOutLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_InOut_ID, M_InOutLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID
FROM M_InOutLine;



