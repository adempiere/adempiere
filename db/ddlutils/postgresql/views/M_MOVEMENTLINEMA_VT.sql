CREATE OR REPLACE VIEW M_MOVEMENTLINEMA_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_MOVEMENT_ID, M_MOVEMENTLINE_ID, LINE, 
 M_PRODUCT_ID, M_ATTRIBUTESETINSTANCE_ID, MOVEMENTQTY, M_LOCATOR_ID, M_LOCATORTO_ID)
AS 
SELECT m.AD_Client_ID, m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
    l.M_Movement_ID, m.M_MovementLine_ID, l.Line, l.M_Product_ID, 
    m.M_AttributeSetInstance_ID, m.MovementQty, l.M_Locator_ID, l.M_LocatorTo_ID
FROM M_MovementLineMA m INNER JOIN M_MovementLine l ON (m.M_MovementLine_ID=l.M_MovementLine_ID)
UNION
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    M_Movement_ID, M_MovementLine_ID, Line, M_Product_ID, 
    M_AttributeSetInstance_ID, MovementQty, M_Locator_ID, M_LocatorTo_ID
FROM M_MovementLine;



