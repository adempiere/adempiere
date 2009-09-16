--- https://sourceforge.net/tracker/index.php?func=detail&aid=2791095&group_id=176962&atid=879332
--- increase size of pw field to cater for encrypted passwords
ALTER TABLE AD_User MODIFY emailuserpw NVARCHAR2(255) 
;
