delete from ad_process_para_trl
where ad_process_para_id in 
( select ad_process_para_id
  from ad_process_para
  where columnname = 'IsRecreate'
  and ad_process_id =
 ( select ad_process_id
   from ad_process 
   where value = 'Balance_Update' )
);

delete from ad_process_para
where columnname = 'IsRecreate'
and ad_process_id =
( select ad_process_id
  from ad_process where value = 'Balance_Update' );

commit;

