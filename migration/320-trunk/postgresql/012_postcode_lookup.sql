---
-- Feature 1741222 - Add Post code lookup infrastructure
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1741222&group_id=176962&atid=879335
--


ALTER TABLE C_BPARTNER_LOCATION ADD 
   AddressLookup CHAR(1)  DEFAULT NULL  NULL 
;

ALTER TABLE C_COUNTRY ADD 
   IsPostcodeLookup CHAR(1)  DEFAULT 'N' NULL 
;
ALTER TABLE C_COUNTRY ADD 
   LookupClassname VARCHAR(255)  DEFAULT NULL  NULL 
;
ALTER TABLE C_COUNTRY ADD 
   LookupClientID VARCHAR(50)  DEFAULT NULL  NULL 
;
ALTER TABLE C_COUNTRY ADD 
   LookupPassword VARCHAR(50)  DEFAULT NULL  NULL 
;
ALTER TABLE C_COUNTRY ADD 
   LookupUrl VARCHAR(100) DEFAULT NULL  NULL 
;

ALTER TABLE C_COUNTRY ADD CHECK (IsPostcodeLookup IN ('Y','N'));
COMMIT;