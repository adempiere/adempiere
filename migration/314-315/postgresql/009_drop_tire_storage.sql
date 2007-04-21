DROP TABLE tire_storage;

DELETE FROM ad_treenodemm
      WHERE node_id = 226;

DELETE FROM ad_menu_trl
      WHERE ad_menu_id = 226;

DELETE FROM ad_menu
      WHERE ad_menu_id = 226;

DELETE FROM ad_field_trl
      WHERE ad_field_id IN (SELECT ad_field_id
                              FROM ad_field
                             WHERE ad_tab_id = 318);

DELETE FROM ad_field
      WHERE ad_tab_id = 318;

DELETE FROM ad_tab_trl
      WHERE ad_tab_id = 318;

DELETE FROM ad_tab
      WHERE ad_tab_id = 318;

DELETE FROM ad_window_access
      WHERE ad_window_id = 190;

DELETE FROM ad_window_trl
      WHERE ad_window_id = 190;

DELETE FROM ad_window
      WHERE ad_window_id = 190;

DELETE FROM ad_process_trl
      WHERE ad_process_id = 160;

DELETE FROM ad_process
      WHERE ad_process_id = 160;

DELETE FROM ad_reportview
      WHERE ad_reportview_id = 121;

DELETE FROM ad_column_trl
      WHERE ad_column_id IN (SELECT ad_column_id
                               FROM ad_column
                              WHERE ad_table_id = 384);

DELETE FROM ad_column
      WHERE ad_table_id = 384;

DELETE FROM ad_table_trl
      WHERE ad_table_id = 384;

DELETE FROM ad_table
      WHERE ad_table_id = 384;

DELETE FROM ad_element_trl
      WHERE ad_element_id IN
               (1527,
                1325,
                1333,
                1339,
                1330,
                1340,
                1331,
                1337,
                1335,
                1327,
                1336,
                1332,
                1334,
                1328,
                1338
               );


DELETE FROM ad_element
      WHERE ad_element_id IN
               (1527,
                1325,
                1333,
                1339,
                1330,
                1340,
                1331,
                1337,
                1335,
                1327,
                1336,
                1332,
                1334,
                1328,
                1338
               );

COMMIT;			   
