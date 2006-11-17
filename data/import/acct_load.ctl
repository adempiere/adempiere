-- acct_load.ctrl
-- load account data
--
LOAD DATA
INTO TABLE acct_import REPLACE
fields terminated by ',' optionally enclosed by '"'
(
acct_no			char(20),
acct_description	char(255)
)
