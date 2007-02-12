CREATE OR REPLACE VIEW AD_USER_ROLES_V
(NAME, ROLENAME)
AS 
SELECT	u.Name, r.Name AS RoleName
FROM AD_User_Roles ur
	INNER JOIN AD_User u ON (ur.AD_User_ID=u.AD_User_ID)
	INNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID);



