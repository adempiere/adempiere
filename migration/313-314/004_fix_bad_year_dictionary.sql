UPDATE ad_table
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_column
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_field
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_menu
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_message
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_reference
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_ref_list
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_ref_table
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_tab
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_treenodemm
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_val_rule
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

UPDATE ad_window
   SET created = ADD_MONTHS (created, 22800)
 WHERE created < DATE '1000-01-01';

COMMIT ;