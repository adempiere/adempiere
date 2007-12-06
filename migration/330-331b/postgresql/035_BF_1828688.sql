UPDATE AD_TAB
   SET isinfotab = 'N'
 WHERE ad_tab_id = 684  -- Invoice (Customer) -> Allocation
    OR ad_tab_id = 685  -- Invoice (Vendor) -> Allocation
    OR ad_tab_id = 755  -- Payment -> Allocate
    OR ad_tab_id = 686  -- Payment -> Allocations
;

COMMIT;