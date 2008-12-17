INSERT INTO ad_treebar
            (ad_tree_id, ad_user_id, node_id, ad_client_id, ad_org_id,
             isactive, created,
             createdby, updated,
             updatedby
            )
     VALUES (10, 100, 144, 0, 0,
             'Y', TO_DATE ('01/23/2007 23:59:53', 'MM/DD/YYYY HH24:MI:SS'),
             100, TO_DATE ('01/23/2007 23:59:53', 'MM/DD/YYYY HH24:MI:SS'),
             100
            );

COMMIT ;
