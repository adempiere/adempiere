SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- Table: ad_table_process

-- DROP TABLE ad_table_process;

CREATE TABLE ad_table_process
(
  ad_client_id NUMBER(10) NOT NULL,
  ad_org_id NUMBER(10) NOT NULL,
  ad_process_id NUMBER(10) NOT NULL,
  ad_table_id NUMBER(10) NOT NULL,
  created DATE NOT NULL, 
  createdby NUMBER(10) NOT NULL,
  isactive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL,
  updated DATE NOT NULL, 
  updatedby NUMBER(10) NOT NULL,
  entitytype VARCHAR2(40) DEFAULT 'U' NOT NULL
 );
ALTER TABLE ad_table_process ADD CONSTRAINT ad_table_process_key PRIMARY KEY (ad_process_id, ad_table_id);
ALTER TABLE ad_table_process ADD (CONSTRAINT adprocess_adtableprocess FOREIGN KEY (AD_Process_ID) REFERENCES AD_Process);   
ALTER TABLE ad_table_process ADD (CONSTRAINT adtable_adtableprocess FOREIGN KEY (ad_table_id) REFERENCES ad_table);    
 
