-- Table: c_dunning_candidate_recompute

-- DROP TABLE c_dunning_candidate_recompute;

CREATE TABLE c_dunning_candidate_recompute
(
  c_dunning_candidate_id numeric(10,0) NOT NULL,
  ad_pinstance_id numeric(10,0)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE c_dunning_candidate_recompute
  OWNER TO adempiere;

-- Index: c_dunning_candidate_recompute_c_dunning_candidate

-- DROP INDEX c_dunning_candidate_recompute_c_dunning_candidate;

CREATE INDEX c_dunning_candidate_recompute_c_dunning_candidate
  ON c_dunning_candidate_recompute
  USING btree
  (c_dunning_candidate_id );
