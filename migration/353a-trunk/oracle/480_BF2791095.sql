--- https://sourceforge.net/tracker/index.php?func=detail&aid=2791095&group_id=176962&atid=879332
--- increase size of pw field to cater for encrypted passwords
ALTER TABLE ad_user ALTER COLUMN emailuserpw TYPE varchar(255)
;

