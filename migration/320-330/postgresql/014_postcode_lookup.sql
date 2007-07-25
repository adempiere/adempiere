---
-- Feature 1741222 - Add Post code lookup infrastructure
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1741222&group_id=176962&atid=879335
--

--- Modify C_COUNTRY Table
ALTER TABLE C_COUNTRY ADD 
   IsPostcodeLookup CHAR(1)  DEFAULT 'N' NOT NULL;
ALTER TABLE C_COUNTRY ADD 
   LookupClassname VARCHAR(255)  DEFAULT NULL  NULL;
ALTER TABLE C_COUNTRY ADD 
   LookupClientID VARCHAR(50)  DEFAULT NULL  NULL;
ALTER TABLE C_COUNTRY ADD 
   LookupPassword VARCHAR(50)  DEFAULT NULL  NULL;
ALTER TABLE C_COUNTRY ADD 
   LookupUrl VARCHAR(100) DEFAULT NULL  NULL;

-- Add Postcode Constraint
ALTER TABLE C_COUNTRY ADD CHECK (IsPostcodeLookup IN ('Y','N'));

-- Insert Element Definitions

INSERT INTO ad_element VALUES (51000, 0, 0, 'Y', '2007-06-19 22:43:07', 100, '2007-06-19 23:09:22', 100, 'IsPostcodeLookup', 'D', 'IsPostcodeLookup', 'IsPostcodeLookup', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_element VALUES (51001, 0, 0, 'Y', '2007-06-19 22:43:07', 100, '2007-06-19 23:09:54', 100, 'LookupClassName', 'D', 'LookupClassName', 'LookupClassName', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_element VALUES (51002, 0, 0, 'Y', '2007-06-19 22:43:07', 100, '2007-06-19 23:10:06', 100, 'LookupClientID', 'D', 'LookupClientID', 'LookupClientID', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_element VALUES (51003, 0, 0, 'Y', '2007-06-19 22:43:07', 100, '2007-06-19 23:10:19', 100, 'LookupUrl', 'D', 'LookupUrl', 'LookupUrl', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_element VALUES (51004, 0, 0, 'Y', '2007-06-22 02:03:37', 100, '2007-06-22 02:04:31', 100, 'LookupPassword', 'D', 'LookupPassword', 'LookupPassword', NULL, NULL, NULL, NULL, NULL, NULL);

-- Insert Column Definitions
INSERT INTO ad_column VALUES (51000, 0, 0, 'Y', '2007-06-19 22:43:07', '2007-06-19 23:14:47', 100, 100, 'IsPostcodeLookup', NULL, NULL, 0, 'D', 'IsPostcodeLookup', 170, 20, NULL, NULL, 1, 'N', 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 51000, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column VALUES (51001, 0, 0, 'Y', '2007-06-19 22:43:07', '2007-06-19 23:04:48', 100, 100, 'LookupClassName', NULL, NULL, 0, 'D', 'LookupClassName', 170, 10, NULL, NULL, 255, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 51001, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column VALUES (51002, 0, 0, 'Y', '2007-06-19 22:43:07', '2007-06-19 23:04:48', 100, 100, 'LookupClientID', NULL, NULL, 0, 'D', 'LookupClientID', 170, 10, NULL, NULL, 50, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 51002, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column VALUES (51003, 0, 0, 'Y', '2007-06-19 22:43:07', '2007-06-19 23:04:48', 100, 100, 'LookupUrl', NULL, NULL, 0, 'D', 'LookupUrl', 170, 10, NULL, NULL, 100, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 51003, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column VALUES (51004, 0, 0, 'Y', '2007-06-22 02:03:37', '2007-06-22 02:05:17', 100, 100, 'LookupPassword', NULL, NULL, 0, 'D', 'LookupPassword', 170, 10, NULL, NULL, 50, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 51004, NULL, 'N', 'N', NULL, NULL);

-- Insert Field Definitions
INSERT INTO ad_field VALUES (51000, 0, 0, 'Y', '2007-06-19 23:17:05', 100, '2007-06-19 23:17:05', 100, 'IsPostcodeLookup', NULL, NULL, 'Y', 135, 51000, NULL, 'Y', NULL, 1, 'N', 220, NULL, 'N', 'N', 'N', 'N', 'D', NULL, NULL, NULL);
INSERT INTO ad_field VALUES (51001, 0, 0, 'Y', '2007-06-19 23:17:06', 100, '2007-06-20 09:10:31', 100, 'LookupClassName', NULL, NULL, 'Y', 135, 51001, NULL, 'Y', '@IsPostcodeLookup@ = ''Y''', 255, 'N', 260, NULL, 'N', 'N', 'N', 'N', 'D', NULL, NULL, NULL);
INSERT INTO ad_field VALUES (51002, 0, 0, 'Y', '2007-06-19 23:17:06', 100, '2007-06-20 09:10:17', 100, 'LookupClientID', NULL, NULL, 'Y', 135, 51002, NULL, 'Y', '@IsPostcodeLookup@ = ''Y''', 50, 'N', 240, NULL, 'N', 'N', 'N', 'N', 'D', NULL, NULL, NULL);
INSERT INTO ad_field VALUES (51003, 0, 0, 'Y', '2007-06-19 23:17:06', 100, '2007-06-20 09:10:12', 100, 'LookupUrl', NULL, NULL, 'Y', 135, 51003, NULL, 'Y', '@IsPostcodeLookup@ = ''Y''', 100, 'N', 230, NULL, 'N', 'N', 'N', 'N', 'D', NULL, NULL, NULL);
INSERT INTO ad_field VALUES (51004, 0, 0, 'Y', '2007-06-19 23:17:06', 100, '2007-06-22 02:07:11', 100, 'LookupPassword', NULL, NULL, 'Y', 135, 51004, NULL, 'Y', '@IsPostcodeLookup@ = ''Y''', 50, 'N', 250, NULL, 'N', 'N', 'N', 'N', 'D', NULL, NULL, NULL);

-- Update Sequences
UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                           FROM ad_element
                          WHERE ad_element_id < 1000000)
 WHERE NAME = 'AD_Element';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_column_id) + 1
                           FROM ad_column
                          WHERE ad_column_id < 1000000)
 WHERE NAME = 'AD_Column';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_field_id) + 1
                           FROM ad_field
                          WHERE ad_field_id < 1000000)
 WHERE NAME = 'AD_Field';
 
COMMIT;