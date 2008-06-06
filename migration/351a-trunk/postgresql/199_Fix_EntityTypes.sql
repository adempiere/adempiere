update AD_Table set EntityType='EE01'
where TableName in ('RV_PP_MRP', 'RV_PP_Order_BOMLine', 'RV_PP_WIP') and EntityType<>'EE01';
