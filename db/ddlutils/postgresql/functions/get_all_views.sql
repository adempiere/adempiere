CREATE OR REPLACE FUNCTION get_all_views(tablename varchar)  
RETURNS TABLE (
    view_name varchar, 
    view_oid integer,
    level integer
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY SELECT dependent_view.relname as view_name, dependent_view.oid, dependent_view.level FROM (WITH RECURSIVE views AS (
				   -- get the directly depending views
				   SELECT v.oid::regclass AS view,
				          1 AS level, v.oid
				   FROM pg_depend AS d
				      JOIN pg_rewrite AS r
				         ON r.oid = d.objid
				      JOIN pg_class AS v
				         ON v.oid = r.ev_class
				   WHERE v.relkind = 'v'
				     AND d.classid = 'pg_rewrite'::regclass
				     AND d.refclassid = 'pg_class'::regclass
				     AND d.deptype = 'n'
				     AND d.refobjid = lower(tablename)::regclass
				UNION ALL
				   -- add the views that depend on these
				   SELECT v.oid::regclass,
				          views.level + 1, v.oid
				   FROM views
				      JOIN pg_depend AS d
				         ON d.refobjid = views.view
				      JOIN pg_rewrite AS r  
				         ON r.oid = d.objid
				      JOIN pg_class AS v    
				         ON v.oid = r.ev_class
				   WHERE v.relkind = 'v'   
				     AND d.classid = 'pg_rewrite'::regclass
				     AND d.refclassid = 'pg_class'::regclass
				     AND d.deptype = 'n'   
				     AND v.oid <> views.view  -- avoid loop
				)
				SELECT e.view::varchar as relname, e.oid::integer as oid, e.level::integer as level
				FROM views e
				GROUP BY e.view, e.oid, e.level
				ORDER BY e.level DESC) as dependent_view;
END $$;