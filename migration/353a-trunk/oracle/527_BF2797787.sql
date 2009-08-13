ALTER TABLE c_orderline DROP CONSTRAINT linkorderline_corderline;

ALTER TABLE c_orderline
    ADD ( CONSTRAINT linkorderline_corderline FOREIGN KEY (link_orderline_id)
    REFERENCES c_orderline (c_orderline_id) 
    ON DELETE CASCADE
);
