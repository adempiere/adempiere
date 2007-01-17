UPDATE ad_table
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_column
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_field
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_menu
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_message
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_reference
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_ref_list
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_ref_table
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_tab
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_treenodemm
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_val_rule
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

UPDATE ad_window
   SET created = created + interval '1 month'
 WHERE created < DATE '1000-01-01';

COMMIT ;