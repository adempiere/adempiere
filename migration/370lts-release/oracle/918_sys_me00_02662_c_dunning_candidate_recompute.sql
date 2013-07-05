-- Table: c_dunning_candidate_recompute

-- DROP TABLE c_dunning_candidate_recompute;

CREATE TABLE c_dunning_candidate_recompute
(
  c_dunning_candidate_id number(10,0) NOT NULL,
  ad_pinstance_id number(10,0)
);

ALTER TABLE c_dunning_candidate_recompute OWNER TO adempiere;

CREATE INDEX c_dunning_candidate_recompute_c_dunning_candidate ON c_dunning_candidate_recompute (c_dunning_candidate_id);
