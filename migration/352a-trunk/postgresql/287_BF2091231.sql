drop table PP_WF_NODE_ASSET
;

CREATE TABLE pp_wf_node_asset ( 
    pp_wf_node_asset_id	numeric(10,0) NOT NULL,
    --
    ad_client_id       	numeric(10,0) NOT NULL,
    ad_org_id          	numeric(10,0) NOT NULL,
    created            	timestamp NOT NULL,
    createdby          	numeric(10,0) NOT NULL,
    updated            	timestamp NOT NULL,
    updatedby          	numeric(10,0) NOT NULL,
    isactive           	char(1) NOT NULL,
    --
    ad_wf_node_id      	numeric(10,0) NOT NULL,
    a_asset_id         	numeric(10,0) NOT NULL,
    seqno              	numeric(10,0) NOT NULL
)
;

ALTER TABLE pp_wf_node_asset
    ADD CONSTRAINT PP_WF_NODE_ASSET_KEY
	PRIMARY KEY(PP_WF_NODE_ASSET_ID)
;

ALTER TABLE pp_wf_node_asset
    ADD CONSTRAINT pp_wf_node_asset_isactive_check
	CHECK (isactive = ANY (ARRAY['Y'::bpchar, 'N'::bpchar]))
;

ALTER TABLE pp_wf_node_asset
    ADD CONSTRAINT adwfnode_ppwfnodeasset
	FOREIGN KEY(ad_wf_node_id)
	REFERENCES ad_wf_node(ad_wf_node_id)
;

ALTER TABLE pp_wf_node_asset
    ADD CONSTRAINT aasset_ppwfnodeasset
	FOREIGN KEY(a_asset_id)
	REFERENCES a_asset(a_asset_id)
;

-- 29.08.2008 18:32:06 EEST
-- 
UPDATE AD_Column SET IsKey='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-29 18:32:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53300
;

-- 29.08.2008 18:32:28 EEST
-- 
UPDATE AD_Column SET IsKey='Y', IsUpdateable='N',Updated=TO_TIMESTAMP('2008-08-29 18:32:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53299
;
