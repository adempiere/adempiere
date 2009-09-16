ALTER TABLE c_orderline DROP CONSTRAINT linkorderline_corderline;

ALTER TABLE c_orderline
    ADD CONSTRAINT linkorderline_corderline FOREIGN KEY (link_orderline_id)
    REFERENCES c_orderline (c_orderline_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE SET NULL;
