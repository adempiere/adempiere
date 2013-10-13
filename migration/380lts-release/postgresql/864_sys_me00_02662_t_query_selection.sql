-- Table: t_query_selection

-- DROP TABLE t_query_selection;

CREATE TABLE t_query_selection
(
  uuid character varying(40) NOT NULL,
  line numeric(10,0) NOT NULL,
  record_id numeric(10,0) NOT NULL,
  created timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT t_query_selection_pkey PRIMARY KEY (uuid , line , record_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE t_query_selection
  OWNER TO adempiere;

-- Index: t_query_selection_search

-- DROP INDEX t_query_selection_search;

CREATE INDEX t_query_selection_search
  ON t_query_selection
  USING btree
  (uuid COLLATE pg_catalog."default" , record_id );

-- Index: t_query_selection_uuid

-- DROP INDEX t_query_selection_uuid;

CREATE INDEX t_query_selection_uuid
  ON t_query_selection
  USING btree
  (uuid COLLATE pg_catalog."default" );
