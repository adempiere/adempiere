CREATE OR REPLACE VIEW RV_REQUESTUPDATES
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, R_REQUEST_ID, AD_USER_ID, ISSELFSERVICE, 
 R_GROUP_ID, R_REQUESTTYPE_ID, R_CATEGORY_ID)
AS 
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    R_Request_ID, AD_User_ID, IsSelfService, 
    cast(NULL as DECIMAL(10, 0)) AS R_Group_ID, cast(NULL as DECIMAL(10, 0)) AS R_RequestType_ID, 
    cast(NULL as DECIMAL(10, 0)) AS R_Category_ID
FROM R_RequestUpdates
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    r.R_Group_ID, NULL, NULL
FROM R_GroupUpdates u
    INNER JOIN R_Request r ON (u.R_Group_ID=r.R_Group_ID)
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    NULL, r.R_RequestType_ID, NULL
FROM R_RequestTypeUpdates u
    INNER JOIN R_Request r ON (u.R_RequestType_ID=r.R_RequestType_ID)
UNION
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, u.IsSelfService, 
    NULL, NULL, r.R_Category_ID
FROM R_CategoryUpdates u
    INNER JOIN R_Request r ON (u.R_Category_ID=r.R_Category_ID)
UNION   --  BP User
SELECT AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    R_Request_ID, AD_User_ID, IsSelfService, 
    NULL, NULL, NULL
FROM R_Request
WHERE AD_User_ID IS NOT NULL
UNION   --  SalesRep
SELECT u.AD_Client_ID, u.AD_Org_ID, u.IsActive, u.Created, u.CreatedBy, u.Updated, u.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, NULL, 
    NULL, NULL, r.R_Category_ID
FROM AD_User u
    INNER JOIN R_Request r ON (u.AD_User_ID=r.SalesRep_ID)
UNION   --  Role
SELECT r.AD_Client_ID, r.AD_Org_ID, u.IsActive, r.Created, r.CreatedBy, r.Updated, r.UpdatedBy,
    r.R_Request_ID, u.AD_User_ID, NULL, 
    NULL, NULL, NULL
FROM R_Request r
    INNER JOIN AD_User_Roles u ON (u.AD_Role_ID=r.AD_Role_ID);
