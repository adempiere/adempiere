--
-- [ 1739541 ] Organization in Window "Role" problem
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1739541&group_id=176962&atid=879332
--
-- Original File 011_BF_1739541.sql was deleted - the original file put the IsParent='N'
-- restoring past value from IsParent because somebody could apply this patch into their system
-- 
-- Column AD_Role_OrgAccess.AD_Org_ID:
UPDATE AD_Column SET IsParent='Y' WHERE AD_Column_ID=5508 AND IsParent='N';
--
COMMIT;
