-- Table: t_query_selection

-- DROP TABLE t_query_selection;

CREATE TABLE t_query_selection
(
  uuid nvarchar2(40) NOT NULL,
  line number(10,0) NOT NULL,
  record_id number(10,0) NOT NULL,
  created timestamp NOT NULL DEFAULT getDate(),
  CONSTRAINT t_query_selection_pkey PRIMARY KEY (uuid , line , record_id )
);

ALTER TABLE t_query_selection OWNER TO adempiere;

CREATE INDEX t_query_selection_search ON t_query_selection (uuid, record_id );

CREATE INDEX t_query_selection_uuid ON t_query_selection (uuid);
