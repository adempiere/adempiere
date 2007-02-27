-- Just a sample, this script is generated using the 00_GENERATE_INSERT_AS.sql procedure

-- Started 26-FEB-07

-- migrate table A_ASSET
-- columns in target: C_BPARTNERSR_ID, C_PROJECT_ID, LASTMAINTENANCEDATE, LASTMAINTENANCENOTE, LASTMAINTENANCEUNIT, LASTMAINTENANCEUSEUNIT, LASTMAINTENENCEDATE, LEASETERMINATIONDATE, LEASE_BPARTNER_ID, M_INOUTLINE_ID, NEXTMAINTENANCEUSEUNIT, NEXTMAINTENENCEDATE, NEXTMAINTENENCEUNIT
DELETE FROM target.a_asset
      WHERE (   ad_client_id >= 1000000
             OR ad_org_id >= 1000000
             OR ad_user_id >= 1000000
             OR a_asset_group_id >= 1000000
             OR a_asset_id >= 1000000
             OR c_bpartner_id >= 1000000
             OR c_bpartner_location_id >= 1000000
             OR c_location_id >= 1000000
             OR m_attributesetinstance_id >= 1000000
             OR m_locator_id >= 1000000
             OR m_product_id >= 1000000
            );

INSERT INTO target.a_asset
            (ad_client_id, ad_org_id, ad_user_id, assetdepreciationdate,
             assetdisposaldate, assetservicedate, a_asset_group_id,
             a_asset_id, created, createdby, c_bpartner_id,
             c_bpartner_location_id, c_location_id, description,
             guaranteedate, HELP, isactive, isdepreciated, isdisposed,
             isfullydepreciated, isinposession, isowned, lifeuseunits,
             locationcomment, lot, m_attributesetinstance_id, m_locator_id,
             m_product_id, NAME, processing, qty, serno, updated, updatedby,
             uselifemonths, uselifeyears, useunits, VALUE, versionno)
   SELECT ad_client_id, ad_org_id, ad_user_id, assetdepreciationdate,
          assetdisposaldate, assetservicedate, a_asset_group_id, a_asset_id,
          created, createdby, c_bpartner_id, c_bpartner_location_id,
          c_location_id, description, guaranteedate, HELP, isactive,
          isdepreciated, isdisposed, isfullydepreciated, isinposession,
          isowned, lifeuseunits, locationcomment, lot,
          m_attributesetinstance_id, m_locator_id, m_product_id, NAME,
          processing, qty, serno, updated, updatedby, uselifemonths,
          uselifeyears, useunits, VALUE, versionno
     FROM source.a_asset
    WHERE ad_client_id <> 11
      AND (   ad_client_id >= 1000000
           OR ad_org_id >= 1000000
           OR ad_user_id >= 1000000
           OR a_asset_group_id >= 1000000
           OR a_asset_id >= 1000000
           OR c_bpartner_id >= 1000000
           OR c_bpartner_location_id >= 1000000
           OR c_location_id >= 1000000
           OR m_attributesetinstance_id >= 1000000
           OR m_locator_id >= 1000000
           OR m_product_id >= 1000000
          );

-- migrate table A_ASSET_ACCT
DELETE FROM target.a_asset_acct
      WHERE (   ad_client_id >= 1000000
             OR ad_org_id >= 1000000
             OR a_asset_id >= 1000000
             OR a_depreciation_id >= 1000000
             OR c_acctschema_id >= 1000000
            );

INSERT INTO target.a_asset_acct
            (ad_client_id, ad_org_id, a_accumdepreciation_acct, a_asset_acct,
             a_asset_id, a_depreciation_acct, a_depreciation_id,
             a_disposal_gain, a_disposal_loss, created, createdby,
             c_acctschema_id, isactive, updated, updatedby)
   SELECT ad_client_id, ad_org_id, a_accumdepreciation_acct, a_asset_acct,
          a_asset_id, a_depreciation_acct, a_depreciation_id, a_disposal_gain,
          a_disposal_loss, created, createdby, c_acctschema_id, isactive,
          updated, updatedby
     FROM source.a_asset_acct
    WHERE ad_client_id <> 11
      AND (   ad_client_id >= 1000000
           OR ad_org_id >= 1000000
           OR a_asset_id >= 1000000
           OR a_depreciation_id >= 1000000
           OR c_acctschema_id >= 1000000
          );

-- End 26-FEB-07
