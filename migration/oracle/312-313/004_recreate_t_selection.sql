DROP TABLE t_selection;

CREATE TABLE t_selection
(
  ad_pinstance_id NUMBER(10) NOT NULL,
  t_selection_id  NUMBER(10) NOT NULL
);

CREATE UNIQUE INDEX t_selection_key ON t_selection
(ad_pinstance_id, t_selection_id);

ALTER TABLE t_selection ADD (
  CONSTRAINT t_selection_key
 PRIMARY KEY (ad_pinstance_id, t_selection_id));

DROP TABLE t_selection2;

CREATE TABLE t_selection2
(
  ad_pinstance_id NUMBER(10) NOT NULL,
  query_id        NUMBER     NOT NULL,
  t_selection_id  NUMBER(10) NOT NULL
);

CREATE UNIQUE INDEX t_selection2_key ON t_selection2
(ad_pinstance_id, query_id, t_selection_id);


ALTER TABLE t_selection2 ADD (
  CONSTRAINT t_selection2_key
 PRIMARY KEY (ad_pinstance_id, query_id, t_selection_id));
