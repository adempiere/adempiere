DROP VIEW rv_c_salesdashboard;

CREATE OR REPLACE VIEW rv_c_salesdashboard AS
 SELECT ad_client_id,
    ad_org_id,
    created,
    createdby,
    updated,
    updatedby,
    isactive,
    ad_user_id,
    name,
    0 AS salespipeline,
    uuid
   FROM ad_user;