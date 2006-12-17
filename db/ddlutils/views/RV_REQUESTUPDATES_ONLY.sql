CREATE OR REPLACE VIEW RV_REQUESTUPDATES_ONLY
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, R_REQUEST_ID, AD_USER_ID)
AS 
SELECT MIN(AD_Client_ID) AS AD_Client_ID, MIN(AD_Org_ID) AS AD_ORG_ID, 'Y' AS IsActive, 
    getdate() AS Created, 0 AS CreatedBy, getdate() AS Updated, 0 AS UpdatedBy,
    R_Request_ID, AD_User_ID
FROM RV_RequestUpdates
WHERE IsActive='Y'
GROUP BY R_Request_ID, AD_User_ID;



