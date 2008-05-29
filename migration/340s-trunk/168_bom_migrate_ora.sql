INSERT INTO PP_PRODUCT_BOM
            (pp_product_bom_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, m_product_id, bomtype, VALUE,
             NAME, description, help, documentno, revision, copyfrom,
             m_changenotice_id, processing, validfrom, validto,
             m_attributesetinstance_id, bomuse, c_uom_id)
   SELECT m_product_id, ad_client_id, ad_org_id, isactive, created, createdby,
          updated, updatedby, m_product_id, 'A', VALUE, NAME, description,
          help,
          nextidfunc ((SELECT ad_sequence_id
                         FROM AD_SEQUENCE
                        WHERE ad_client_id = mp.ad_client_id
                          AND NAME = 'DocumentNo_PP_Product_BOM'),
                      'N'
                     ),
          NULL, NULL, NULL, NULL, created, NULL, NULL, 'M', c_uom_id
     FROM M_PRODUCT mp
    WHERE isbom = 'Y' AND m_product_id < 1000000;

INSERT INTO PP_PRODUCT_BOM
            (pp_product_bom_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, m_product_id, bomtype, VALUE,
             NAME, description, help, documentno, revision, copyfrom,
             m_changenotice_id, processing, validfrom, validto,
             m_attributesetinstance_id, bomuse, c_uom_id)
   SELECT nextidfunc (53015, 'N'), ad_client_id, ad_org_id, isactive, created,
          createdby, updated, updatedby, m_product_id, 'A', VALUE, NAME,
          description, help,
          nextidfunc ((SELECT ad_sequence_id
                         FROM AD_SEQUENCE
                        WHERE ad_client_id = mp.ad_client_id
                          AND NAME = 'DocumentNo_PP_Product_BOM'),
                      'N'
                     ),
          NULL, NULL, NULL, NULL, created, NULL, NULL, 'M', c_uom_id
     FROM M_PRODUCT mp
    WHERE isbom = 'Y' AND m_product_id > 999999;

INSERT INTO PP_PRODUCT_BOMLINE
            (pp_product_bomline_id, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, line, m_product_id,
             pp_product_bom_id, qtybom, description, help, feature, assay,
             backflushgroup, c_uom_id, componenttype, forecast, iscritical,
             isqtypercentage, issuemethod, leadtimeoffset,
             m_attributesetinstance_id, m_changenotice_id, qtybatch, scrap,
             validfrom, validto)
   SELECT mpb.m_product_bom_id, mpb.ad_client_id, mpb.ad_org_id, mpb.isactive,
          mpb.created, mpb.createdby, mpb.updated, mpb.updatedby, mpb.line,
          mpb.m_productbom_id, ppb.pp_product_bom_id, mpb.bomqty,
          mpb.description, NULL, NULL, 0, NULL, mp.c_uom_id, 'CO', 0, 'N',
          'N', '0', 0, NULL, NULL, 0, 0, mpb.created, NULL
     FROM M_PRODUCT_BOM mpb, PP_PRODUCT_BOM ppb, M_PRODUCT mp
    WHERE mpb.m_product_id = ppb.m_product_id
      AND mpb.m_productbom_id = mp.m_product_id
      AND mpb.m_product_bom_id < 1000000;

INSERT INTO PP_PRODUCT_BOMLINE
            (pp_product_bomline_id, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, line, m_product_id,
             pp_product_bom_id, qtybom, description, help, feature, assay,
             backflushgroup, c_uom_id, componenttype, forecast, iscritical,
             isqtypercentage, issuemethod, leadtimeoffset,
             m_attributesetinstance_id, m_changenotice_id, qtybatch, scrap,
             validfrom, validto)
   SELECT nextidfunc (53016, 'N'), mpb.ad_client_id, mpb.ad_org_id,
          mpb.isactive, mpb.created, mpb.createdby, mpb.updated,
          mpb.updatedby, mpb.line, mpb.m_productbom_id, ppb.pp_product_bom_id,
          mpb.bomqty, mpb.description, NULL, NULL, 0, NULL, mp.c_uom_id, 'CO',
          0, 'N', 'N', '0', 0, NULL, NULL, 0, 0, mpb.created, NULL
     FROM M_PRODUCT_BOM mpb, PP_PRODUCT_BOM ppb, M_PRODUCT mp
    WHERE mpb.m_product_id = ppb.m_product_id
      AND mpb.m_productbom_id = mp.m_product_id
      AND mpb.m_product_bom_id > 999999;