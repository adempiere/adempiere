-- View: db_constraints

-- DROP VIEW db_constraints;

CREATE OR REPLACE VIEW db_constraints AS 
 SELECT co.contype AS constraint_type, co.conname AS constraint_name, ns.nspname AS schemaname, cl.relname AS tablename, a.attname AS columnname, nsf.nspname AS fschemaname, clf.relname AS ftablename, af.attname AS fcolumnname
   FROM pg_constraint co
   JOIN pg_class cl ON cl.oid = co.conrelid
   JOIN pg_namespace ns ON ns.oid = cl.relnamespace
   LEFT JOIN pg_attribute a ON a.attrelid = co.conrelid AND ARRAY[a.attnum] <@ co.conkey
   LEFT JOIN pg_class clf ON clf.oid = co.confrelid
   LEFT JOIN pg_namespace nsf ON nsf.oid = clf.relnamespace
   LEFT JOIN pg_attribute af ON af.attrelid = co.confrelid AND ARRAY[af.attnum] <@ co.confkey
  WHERE (co.contype = ANY (ARRAY['p'::"char", 'f'::"char"])) AND ns.nspname = 'adempiere'::name;

ALTER TABLE db_constraints
  OWNER TO adempiere;

-- View: db_columns_fk

-- DROP VIEW db_columns_fk;

CREATE OR REPLACE VIEW db_columns_fk AS 
 SELECT v.tablename, v.columnname, v.table_ref AS ref_tablename, v.tablename_entitytype, v.columnname_entitytype, v.ad_reference_id, v.ad_reference_name, v.ad_reference_value_id, 
        CASE
            WHEN (EXISTS ( SELECT 1
               FROM db_constraints c
              WHERE c.schemaname = 'adempiere'::name AND c.tablename::text = lower(v.tablename::text) AND c.columnname::text = lower(v.columnname::text) AND (c.constraint_type = ANY (ARRAY['p'::"char", 'f'::"char"])))) THEN 'N'::text
            ELSE 'Y'::text
        END AS ismissing, (((((((('ALTER TABLE '::text || v.tablename::text) || ' ADD CONSTRAINT '::text) || substr((replace(substr(v.columnname::text, 1, length(v.columnname::text) - 3), '_'::text, ''::text) || '_'::text) || replace(v.tablename::text, '_'::text, ''::text), 1, 30)) || ' FOREIGN KEY ('::text) || v.columnname::text) || ') REFERENCES '::text) || v.table_ref::text) || ' DEFERRABLE INITIALLY DEFERRED;'::text) || chr(10) AS sqltext
   FROM (        (         SELECT t.tablename, c.columnname, r.name AS ad_reference_name, c.ad_reference_id, c.ad_reference_value_id, substr(c.columnname::text, 1, length(c.columnname::text) - 3)::character varying(40) AS table_ref, t.entitytype AS tablename_entitytype, c.entitytype AS columnname_entitytype
                           FROM ad_table t, ad_column c, ad_reference r
                          WHERE t.ad_table_id = c.ad_table_id AND c.columnsql IS NULL AND c.ad_reference_id = r.ad_reference_id AND (c.ad_reference_id = 19::numeric OR c.ad_reference_id = 30::numeric AND c.ad_reference_value_id IS NULL)
                UNION 
                         SELECT t.tablename, c.columnname, r.name, c.ad_reference_id, c.ad_reference_value_id, tr.tablename AS table_ref, t.entitytype AS tablename_entitytype, c.entitytype AS columnname_entitytype
                           FROM ad_table t, ad_column c, ad_reference r, ad_ref_table rt, ad_table tr
                          WHERE t.ad_table_id = c.ad_table_id AND c.columnsql IS NULL AND c.ad_reference_id = r.ad_reference_id AND (c.ad_reference_id = 18::numeric OR c.ad_reference_id = 30::numeric AND c.ad_reference_value_id IS NOT NULL) AND c.ad_reference_value_id = rt.ad_reference_id AND rt.ad_table_id = tr.ad_table_id)
        UNION 
                 SELECT t.tablename, c.columnname, r.name, c.ad_reference_id, c.ad_reference_value_id, 
                        CASE
                            WHEN c.ad_reference_id = 25::numeric THEN 'C_ValidCombination'::text
                            WHEN c.ad_reference_id = 33::numeric THEN 'S_ResourceAssignment'::text
                            WHEN c.ad_reference_id = 32::numeric THEN 'AD_Image'::text
                            WHEN c.ad_reference_id = 21::numeric THEN 'C_Location'::text
                            WHEN c.ad_reference_id = 31::numeric THEN 'C_Locator'::text
                            WHEN c.ad_reference_id = 35::numeric THEN 'M_AttributeSetInstance'::text
                            ELSE NULL::text
                        END AS table_ref, t.entitytype AS tablename_entitytype, c.entitytype AS columnname_entitytype
                   FROM ad_table t
              JOIN ad_column c ON c.ad_table_id = t.ad_table_id
         JOIN ad_reference r ON r.ad_reference_id = c.ad_reference_id
        WHERE t.isview = 'N'::bpchar AND (c.ad_reference_id = ANY (ARRAY[25::numeric, 33::numeric, 32::numeric, 21::numeric, 31::numeric, 35::numeric]))) v
  WHERE 1 = 1 AND (v.columnname::text <> ALL (ARRAY['AD_Client_ID'::character varying::text, 'AD_Org_ID'::character varying::text, 'CreatedBy'::character varying::text, 'UpdatedBy'::character varying::text])) AND v.tablename::text !~~ like_escape('T|_%'::text, '|'::text) AND v.tablename::text <> 'Test'::text AND NOT (EXISTS ( SELECT 1
           FROM pg_views
          WHERE pg_views.viewname::text = lower(v.tablename::text)));

ALTER TABLE db_columns_fk
  OWNER TO adempiere;

