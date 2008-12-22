-- Revert 006_C1648920_EnhancedDocNum - incomplete contribution

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53200;

DELETE FROM AD_ELEMENT
      WHERE ad_element_id = 53200;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53201;

DELETE FROM AD_ELEMENT
      WHERE ad_element_id = 53201;

ALTER TABLE AD_SEQUENCE_NO DROP CONSTRAINT ad_sequence_no_key;

DROP INDEX ad_sequence_no_key;

CREATE UNIQUE INDEX ad_sequence_no_key ON AD_SEQUENCE_NO
(ad_sequence_id, calendaryear);

ALTER TABLE AD_SEQUENCE_NO ADD (
  CONSTRAINT ad_sequence_no_key
 PRIMARY KEY
 (ad_sequence_id, calendaryear));

ALTER TABLE AD_SEQUENCE_NO DROP COLUMN DAY;

ALTER TABLE AD_SEQUENCE_NO DROP COLUMN MONTH;

ALTER TABLE AD_SEQUENCE DROP COLUMN datetrx;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53202;

ALTER TABLE AD_SEQUENCE DROP COLUMN isadddate;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53203;

DELETE FROM AD_ELEMENT
      WHERE ad_element_id = 53203;

ALTER TABLE AD_SEQUENCE DROP COLUMN isaddmonth;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53204;

DELETE FROM AD_ELEMENT
      WHERE ad_element_id = 53204;

ALTER TABLE AD_SEQUENCE DROP COLUMN isaddyear;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53205;

DELETE FROM AD_ELEMENT
      WHERE ad_element_id = 53205;

ALTER TABLE AD_SEQUENCE DROP COLUMN isromancharacter;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53206;

DELETE FROM AD_ELEMENT
      WHERE ad_element_id = 53206;

ALTER TABLE AD_SEQUENCE DROP COLUMN separator;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53207;

ALTER TABLE AD_SEQUENCE DROP COLUMN dateposition;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53208;

DELETE FROM AD_ELEMENT
      WHERE ad_element_id = 53208;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53200;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53201;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53202;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53203;

DELETE FROM AD_REFERENCE
      WHERE ad_reference_id = 53200;

ALTER TABLE AD_SEQUENCE DROP COLUMN DATEFORMAT;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53204;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53205;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53206;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53209;

DELETE FROM AD_REFERENCE
      WHERE ad_reference_id = 53201;


DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53207;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53208;

DELETE FROM AD_REF_LIST
      WHERE ad_ref_list_id = 53209;

DELETE FROM AD_COLUMN
      WHERE ad_column_id = 53210;

DELETE FROM AD_REFERENCE
      WHERE ad_reference_id = 53202;

DELETE FROM AD_ELEMENT
      WHERE ad_element_id = 53210;

ALTER TABLE AD_SEQUENCE DROP COLUMN RESET;

