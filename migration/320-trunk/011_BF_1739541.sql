--
-- [ 1739541 ] Organization in Window "Role" problem
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1739541&group_id=176962&atid=879332
--
-- Column AD_Role_OrgAccess.AD_Org_ID:
UPDATE AD_Column SET IsParent='N' WHERE AD_Column_ID=5508;
--
COMMIT;
