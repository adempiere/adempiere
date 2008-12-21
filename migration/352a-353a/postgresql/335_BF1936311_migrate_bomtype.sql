update pp_product_bomline pbl 
set componenttype = 
   (select  (case 
              when mbl.bomtype = 'O' then 'OP'
              when mbl.bomtype in ('1','2','3','4','5','6','7','8','9') then 'VA'
              else 'CO'
            end)
    from m_product_bom mbl, pp_product_bom pb, pp_product_bomline pbl2
    where pbl2.pp_product_bomline_id = pbl.pp_product_bomline_id 
          and pb.m_product_id = mbl.m_product_id
          and pbl2.pp_product_bom_id = pb.pp_product_bom_id
          and mbl.m_productbom_id = pbl2.m_product_id) ,
    feature =
   (select  (case 
              when mbl.bomtype in ('1','2','3','4','5','6','7','8','9') 
                    then (select name 
                          from ad_ref_list 
                          where ad_reference_id = 279 
                          and value = mbl.bomtype)
              else null
            end)  
    from m_product_bom mbl, pp_product_bom pb, pp_product_bomline pbl2
    where pbl2.pp_product_bomline_id = pbl.pp_product_bomline_id 
          and pb.m_product_id = mbl.m_product_id
          and pbl2.pp_product_bom_id = pb.pp_product_bom_id
          and mbl.m_productbom_id = pbl2.m_product_id)    
where exists 
   (select * 
    from m_product_bom mbl, pp_product_bom pb, pp_product_bomline pbl2 
    where (mbl.bomtype <> 'P' or pbl2.componenttype <> 'CO') 
          and pbl2.pp_product_bomline_id = pbl.pp_product_bomline_id 
          and pb.m_product_id = mbl.m_product_id
          and pbl2.pp_product_bom_id = pb.pp_product_bom_id
          and mbl.m_productbom_id = pbl2.m_product_id)
;
