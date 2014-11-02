-- Table: ad_table_process

-- DROP TABLE ad_table_process;

CREATE TABLE ad_table_process
(
  ad_client_id numeric(10,0) NOT NULL,
  ad_org_id numeric(10,0) NOT NULL,
  ad_process_id numeric(10,0) NOT NULL,
  ad_table_id numeric(10,0) NOT NULL,
  created timestamp with time zone NOT NULL,
  createdby numeric(10,0) NOT NULL,
  isactive character(1) NOT NULL,
  updated timestamp with time zone NOT NULL,
  updatedby numeric(10,0) NOT NULL,
  entitytype character varying(255) default 'U' NOT NULL,
  CONSTRAINT ad_table_process_key PRIMARY KEY (ad_process_id, ad_table_id),
  CONSTRAINT adprocess_adtableprocess FOREIGN KEY (ad_process_id)
      REFERENCES ad_process (ad_process_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED,
  CONSTRAINT adtable_adtableprocess FOREIGN KEY (ad_table_id)
      REFERENCES ad_table (ad_table_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED,
  CONSTRAINT ad_table_process_isactive_check CHECK (isactive = ANY (ARRAY['Y'::bpchar, 'N'::bpchar]))
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ad_table_process OWNER TO adempiere;


