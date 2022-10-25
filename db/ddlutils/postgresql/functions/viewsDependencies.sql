-- @formatter:off
-- Code Based on https://github.com/rvkulikov/pg-deps-management
drop function if exists deps_restore_dependencies(
  p_view_schema name,
  p_view_name name,
  p_options jsonb
);

drop function if exists deps_save_and_drop_dependencies(
  p_view_schema name,
  p_view_name name,
  p_options jsonb
);

drop table if exists i_deps_saved_ddl;





create table if not exists i_deps_saved_ddl (
  src_nsp_name name,
  src_rel_name name,
  dep_nsp_name name,
  dep_rel_name name,
  ddl_order int,
  ddl_statement text,
  ddl_at timestamptz default now(),
  constraint i_deps_saved_ddl_pk
    primary key (src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order)
);

grant select, insert, update, delete, truncate on i_deps_saved_ddl to public;

create or replace function deps_save_and_drop_dependencies(
  p_src_nsp_name name,
  p_src_rel_name name,
  p_options jsonb default '{}'::jsonb
) returns void as
$$
declare
  v_curr record;
  v_sequence text = 'deps_sequence_' || md5(random()::text);
  v_verbose bool;
  v_dry bool;
  v_populate bool;
  v_stmt text;
begin

  -- initializing defaults
  p_options = '{
    "dry_run": true,
    "verbose": false,
    "populate_materialized_view": false
  }'::jsonb || p_options;
  v_verbose = (p_options->'verbose')::bool;
  v_dry = (p_options->'dry_run')::bool;
  v_populate = (p_options->'populate_materialized_view')::bool;

  if v_verbose then
    set local client_min_messages to 'debug';
  end if;

  raise debug 'Running with options: %', p_options::text;

  raise debug 'Creating temp sequence: %', v_sequence::text;
  execute format(
    'CREATE TEMPORARY SEQUENCE %I',
    v_sequence
  );


  raise debug 'flush previous ddl';
  delete from
    i_deps_saved_ddl
  where
    src_nsp_name = p_src_nsp_name and
    src_rel_name = p_src_rel_name;

  for v_curr in (
    select
      obj_schema,
      obj_name,
      obj_type
    from (
      with recursive recursive_deps(obj_schema, obj_name, obj_type, depth) as (
        select
          p_src_nsp_name,
          p_src_rel_name,
          null::char,
          0
        union
        select
          dep_schema::name,
          dep_name::name,
          dep_type::char,
          recursive_deps.depth + 1
        from (
          select
            ref_nsp.nspname ref_schema,
            ref_cl.relname ref_name,
            rwr_cl.relkind dep_type,
            rwr_nsp.nspname dep_schema,
            rwr_cl.relname dep_name
          from pg_depend dep
            join pg_class ref_cl
              on dep.refobjid = ref_cl.oid
            join pg_namespace ref_nsp
              on ref_cl.relnamespace = ref_nsp.oid
            join pg_rewrite rwr
              on dep.objid = rwr.oid
            join pg_class rwr_cl
              on rwr.ev_class = rwr_cl.oid
            join pg_namespace rwr_nsp
              on rwr_cl.relnamespace = rwr_nsp.oid
          where
            dep.deptype = 'n' and
            dep.classid = 'pg_rewrite'::regclass
        ) deps
        join recursive_deps
          on deps.ref_schema = recursive_deps.obj_schema and
             deps.ref_name = recursive_deps.obj_name
        where
          deps.ref_schema != deps.dep_schema or
          deps.ref_name != deps.dep_name
      )
      select
        obj_schema,
        obj_name,
        obj_type,
        depth
      from
        recursive_deps
      where
        depth > 0
    ) t
    group by
      obj_schema,
      obj_name,
      obj_type
    order by
      max(depth) desc
  ) loop

    raise debug 'Dumping view: %.% %', v_curr.obj_schema, v_curr.obj_name, v_curr.obj_type;

    raise debug 'Building owners';
    insert into
      i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
    select distinct
      p_src_nsp_name,
      p_src_rel_name,
      v_curr.obj_schema,
      v_curr.obj_name,
      nextval(v_sequence::regclass),
      format(
        'ALTER %s %I.%I OWNER TO %I',
        case
          when pc.relkind = 'v'
            then 'VIEW'
          when pc.relkind = 'm'
            then 'MATERIALIZED VIEW'
        end,
        v_curr.obj_schema,
        v_curr.obj_name,
        pg_get_userbyid(pc.relowner)
      )
    from
      pg_class pc
        inner join pg_namespace pn
        on pc.relnamespace = pn.oid and
           pn.nspname = v_curr.obj_schema
    where
      pc.relname = v_curr.obj_name;

    raise debug 'Building indexes';
    insert into
      i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
    select distinct
      p_src_nsp_name,
      p_src_rel_name,
      v_curr.obj_schema,
      v_curr.obj_name,
      nextval(v_sequence::regclass),
      indexdef
    from
      pg_indexes
    where
      schemaname = v_curr.obj_schema and
      tablename = v_curr.obj_name;

    raise debug 'Building rules';
    insert into
      i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
    select distinct
      p_src_nsp_name,
      p_src_rel_name,
      v_curr.obj_schema,
      v_curr.obj_name,
      nextval(v_sequence::regclass),
      definition
    from
      pg_rules
    where
      schemaname = v_curr.obj_schema and
      tablename = v_curr.obj_name;

    raise debug 'Building view comments';
    insert into
      i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
    select
      p_src_nsp_name,
      p_src_rel_name,
      v_curr.obj_schema,
      v_curr.obj_name,
      nextval(v_sequence::regclass),
      format(
        'COMMENT ON %s %I.%I IS %L',
        case
          when pc.relkind = 'v'
            then 'VIEW'
          when pc.relkind = 'm'
            then 'MATERIALIZED VIEW'
        end,
        pn.nspname,
        pc.relname,
        pd.description
      )
    from
      pg_class pc
        inner join pg_namespace pn
          on pn.oid = pc.relnamespace and
             pn.nspname = v_curr.obj_schema
        inner join pg_description pd
          on pd.objoid = pc.oid and
             pd.objsubid = 0 and
             pd.description is not null
    where
      pc.relname = v_curr.obj_name;

    raise debug 'Building column comments';
    insert into
      i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
    select
      p_src_nsp_name,
      p_src_rel_name,
      v_curr.obj_schema,
      v_curr.obj_name,
      nextval(v_sequence::regclass),
      format(
        'COMMENT ON COLUMN %I.%I.%I IS %L',
        pn.nspname,
        pc.relname,
        pa.attname,
        pd.description
      )
    from
      pg_class pc
        inner join pg_namespace pn
          on pn.oid = pc.relnamespace and
             pn.nspname = v_curr.obj_schema
        inner join pg_attribute pa
          on pc.oid = pa.attrelid
        inner join pg_description pd
          on pd.objoid = pc.oid and
             pd.objsubid = pa.attnum and
             pd.description is not null
    where
      pc.relname = v_curr.obj_name;

    if v_curr.obj_type = 'v' then
      raise debug 'Building view privilege grants';
      insert into
        i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
      select
        p_src_nsp_name,
        p_src_rel_name,
        v_curr.obj_schema,
        v_curr.obj_name,
        nextval(v_sequence::regclass),
        format(
          'GRANT %s ON %I.%I TO %I %s',
          privilege_type,
          table_schema,
          table_name,
          grantee,
          case
            when is_grantable = 'YES' -- or with_hierarchy = 'YES'
              then format(
                'WITH %s OPTION',
                array_to_string(ARRAY[
                  case when is_grantable = 'YES' then 'GRANT' else '' end
               -- case when with_hierarchy = 'YES' then 'HIERARCHY' else '' end -- for the future, todo ask postgres pro community
                ]::text[], ' ')
              )
            else ''
          end
        )
      from
        information_schema.role_table_grants
      where
        table_schema = v_curr.obj_schema and
        table_name = v_curr.obj_name;
    elseif v_curr.obj_type = 'm' then
      raise debug 'Building material view privilege grants';
      insert into
        i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
      select
        p_src_nsp_name,
        p_src_rel_name,
        table_schema,
        table_name,
        nextval(v_sequence::regclass),
        format(
          'GRANT %s ON %I.%I to %I %s',
          privilege_type,
          table_schema,
          table_name,
          grantee,
          case
            when is_grantable = 'YES'
              then format(
                'WITH %s OPTION',
                array_to_string(ARRAY[
                  case when is_grantable = 'YES' then 'GRANT' else '' end
                ]::text[], ' ')
              )
            else ''
          end
        )
      from (
        select
          table_schema,
          table_name,
          grantor,
          grantee,
          unnest(ARRAY[
            case when acl ~ 'a|a\*' then 'INSERT' end,
            case when acl ~ 'r|r\*' then 'SELECT' end,
            case when acl ~ 'w|w\*' then 'UPDATE' end,
            case when acl ~ 'd|d\*' then 'DELETE' end,
            case when acl ~ 'D|D\*' then 'TRUNCATE' end,
            case when acl ~ 'x|x\*' then 'REFERENCES' end,
            case when acl ~ 't|t\*' then 'TRIGGER' end
          ]::text[]) as privilege_type,
          unnest(ARRAY[
            case when acl ~ 'a\*' then 'YES' else 'NO' end,
            case when acl ~ 'r\*' then 'YES' else 'NO' end,
            case when acl ~ 'w\*' then 'YES' else 'NO' end,
            case when acl ~ 'd\*' then 'YES' else 'NO' end,
            case when acl ~ 'D\*' then 'YES' else 'NO' end,
            case when acl ~ 'x\*' then 'YES' else 'NO' end,
            case when acl ~ 't\*' then 'YES' else 'NO' end
          ]::text[]) as is_grantable
        from (
          select
            table_schema,
            table_name,
            (regexp_match(acl, '([^=]*)=([^/]+)/(.+)'))[3] as grantor,
            coalesce(nullif((regexp_match(acl, '([^=]*)=([^/]+)/(.+)'))[1], ''), 'public') as grantee,
            (regexp_match(acl, '([^=]*)=([^/]+)/(.+)'))[2] as acl
          from (
            select
              pn.nspname as table_schema,
              pc.relname as table_name,
              unnest(pc.relacl)::text as acl
            from
              pg_class pc
                inner join pg_namespace pn
                  on pn.oid = pc.relnamespace and
                     pn.nspname = v_curr.obj_schema
            where
              pc.relname = v_curr.obj_name
          ) as v1
        ) as v2
      ) as v3
      where
        privilege_type is not null;
    end if;


    if v_curr.obj_type = 'v' then
      raise debug 'Building view column privilege grants';
      insert into
        i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
      select
        p_src_nsp_name,
        p_src_rel_name,
        v_curr.obj_schema,
        v_curr.obj_name,
        nextval(v_sequence::regclass),
        format(
          'GRANT %s (%I) ON %I.%I TO %I %s',
          privilege_type,
          column_name,
          table_schema,
          table_name,
          grantee,
          case
            when is_grantable = 'YES'
              then format(
                'WITH %s OPTION',
                array_to_string(ARRAY[
                  case when is_grantable = 'YES' then 'GRANT' else '' end
                ]::text[], ' ')
              )
            else ''
          end
        )
      from
        information_schema.role_column_grants
      where
        table_schema = v_curr.obj_schema and
        table_name = v_curr.obj_name;
    elseif v_curr.obj_type = 'm' then
      raise debug 'Building material view column privilege grants';
      insert into
        i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
      select
        p_src_nsp_name,
        p_src_rel_name,
        v_curr.obj_schema,
        v_curr.obj_name,
        nextval(v_sequence::regclass),
        format(
          'GRANT %s (%I) ON %I.%I to %I %s',
          privilege_type,
          column_name,
          table_schema,
          table_name,
          grantee,
          case
            when is_grantable = 'YES'
              then format(
                'WITH %s OPTION',
                array_to_string(ARRAY[
                  case when is_grantable = 'YES' then 'GRANT' else '' end
                ]::text[], ' ')
              )
            else ''
          end
        )
      from (
        select
          table_schema,
          table_name,
          column_name,
          grantor,
          grantee,
          unnest(ARRAY[
            case when acl ~ 'a|a\*' then 'INSERT' end,
            case when acl ~ 'r|r\*' then 'SELECT' end,
            case when acl ~ 'w|w\*' then 'UPDATE' end,
            case when acl ~ 'x|x\*' then 'REFERENCES' end
          ]::text[]) as privilege_type,
          unnest(ARRAY[
            case when acl ~ 'a\*' then 'YES' else 'NO' end,
            case when acl ~ 'r\*' then 'YES' else 'NO' end,
            case when acl ~ 'w\*' then 'YES' else 'NO' end,
            case when acl ~ 'x\*' then 'YES' else 'NO' end
          ]::text[]) as is_grantable
        from (
          select
            table_schema,
            table_name,
            column_name,
            (regexp_match(acl, '([^=]*)=([^/]+)/(.+)'))[3] as grantor,
            coalesce(nullif((regexp_match(acl, '([^=]*)=([^/]+)/(.+)'))[1], ''), 'public') as grantee,
            (regexp_match(acl, '([^=]*)=([^/]+)/(.+)'))[2] as acl
          from (
            select
              pn.nspname as table_schema,
              pc.relname as table_name,
              pa.attname as column_name,
              unnest(pa.attacl)::text as acl
            from
              pg_attribute pa
                inner join pg_class pc
                  on pc.oid = pa.attrelid and
                     pc.relname = 'view_material'
                join pg_namespace pn
                  on pn.oid = pc.relnamespace and
                     pn.nspname = v_curr.obj_schema
            where
              pa.attisdropped = false and
              pa.attacl is not null
          ) as v1
        ) as v2
      ) as v3
      where
        privilege_type is not null;
    end if;


    if v_curr.obj_type = 'v' then
      raise debug 'Building view ddl';
      insert into
        i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
      select
        p_src_nsp_name,
        p_src_rel_name,
        v_curr.obj_schema,
        v_curr.obj_name,
        nextval(v_sequence::regclass),
        format(
          'CREATE VIEW %I.%I %s AS %s',
          v_curr.obj_schema,
          v_curr.obj_name,
          case
            when array_length(pg_class.reloptions::text[], 1) > 0
              then format('WITH ( %s )', array_to_string(pg_class.reloptions, ', '))
            else ''
          end,
          view_definition
        )
      from
        information_schema.views
          join pg_class
            on pg_class.relname = v_curr.obj_name
          join pg_namespace pn 
            on pg_class.relnamespace = pn.oid and
               pn.nspname = v_curr.obj_schema
      where
        table_schema = v_curr.obj_schema and
        table_name = v_curr.obj_name;
    elseif v_curr.obj_type = 'm' then
      raise debug 'Building materialized view ddl';
      insert into
        i_deps_saved_ddl(src_nsp_name, src_rel_name, dep_nsp_name, dep_rel_name, ddl_order, ddl_statement)
      select
        p_src_nsp_name,
        p_src_rel_name,
        v_curr.obj_schema,
        v_curr.obj_name,
        nextval(v_sequence::regclass),
        format(
          'CREATE MATERIALIZED VIEW %I.%I %s %s %s AS %s %s',
          v_curr.obj_schema,
          v_curr.obj_name,
          '', -- format('USING %s', pa.amname), -- for the future, doesn't work in v13.1 todo ask postgres pro community
          case
            when array_length(pg_class.reloptions::text[], 1) > 0
              then format('WITH ( %s )', array_to_string(pg_class.reloptions, ', '))
            else ''
          end,
          case
            when pt.spcname is not null
              then format('TABLESPACE %s', pt.spcname)
            else ''
          end,
          trim(';' from definition),
          case
            when v_populate
              then 'WITH DATA'
            else 'WITH NO DATA'
          end
        )
      from
        pg_matviews
          join pg_class
            on pg_class.relname = v_curr.obj_name
          join pg_namespace pn 
            on pg_class.relnamespace = pn.oid and
               pn.nspname = v_curr.obj_schema
          join pg_am pa
            on pg_class.relam = pa.oid
          left join pg_tablespace pt
            on pg_class.reltablespace = pt.oid
      where
        schemaname = v_curr.obj_schema and
        matviewname = v_curr.obj_name;
    end if;

    v_stmt = format(
      'DROP %s %I.%I',
      case
        when v_curr.obj_type = 'v'
          then 'VIEW'
        when v_curr.obj_type = 'm'
          then 'MATERIALIZED VIEW'
      end,
      v_curr.obj_schema,
      v_curr.obj_name
    );

    if not v_dry then
      raise debug 'Executing drop statement: %', v_stmt;
      execute v_stmt;
    else
      raise debug 'Emulating drop statement: %', v_stmt;
    end if;

  end loop;

  raise debug 'Dropping temp sequence: %', v_sequence;
  execute format(
    'DROP SEQUENCE %I',
    v_sequence
  );

  if v_verbose then
    reset client_min_messages;
  end if;
end;
$$
language plpgsql;




create or replace function deps_restore_dependencies(
  p_view_schema name,
  p_view_name name,
  p_options jsonb default '{}'::jsonb
) returns void as
$$
declare
  v_curr record;
  v_verbose bool;
  v_dry bool;
begin
  -- initializing defaults
  p_options = '{
    "dry_run": true,
    "verbose": false
  }'::jsonb || p_options;
  v_verbose = (p_options->'verbose')::bool;
  v_dry = (p_options->'dry_run')::bool;

  if v_verbose then
    set local client_min_messages to 'debug';
  end if;

  raise debug 'Running with options: %', p_options::text;

  for v_curr in (
    select
      ddl_statement
    from
      i_deps_saved_ddl
    where
      src_nsp_name = p_view_schema and
      src_rel_name = p_view_name
    order by
      ddl_order desc
  ) loop

    if not v_dry then
      raise debug 'Executing ddl statement: %', v_curr.ddl_statement;
      execute v_curr.ddl_statement;
    else
      raise debug 'Skipping ddl statement: %', v_curr.ddl_statement;
    end if;

  end loop;

  raise debug 'Flushing ddl statements';
  delete from
    i_deps_saved_ddl
  where
    src_nsp_name = p_view_schema and
    src_rel_name = p_view_name;

  if v_verbose then
    reset client_min_messages;
  end if;
end;
$$
language plpgsql;
