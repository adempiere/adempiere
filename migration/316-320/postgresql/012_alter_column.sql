create or replace function altercolumn(tablename name, columnname name, datatype name,
nullclause varchar, defaultclause varchar) returns void as $$
declare
   command text;
   viewtext text[];
   viewname name[];
   dropviews name[];
   i int;
   j int;
   v record;
   sqltype       text;
   sqltype_short text;
   typename name;
begin
   if datatype is not null then
	select pg_type.typname, format_type(pg_type.oid, pg_attribute.atttypmod)
        into typename, sqltype
        from pg_class, pg_attribute, pg_type
        where relname = lower(tablename)
        and relkind = 'r'
        and pg_class.oid = pg_attribute.attrelid
        and attname = lower(columnname)
        and atttypid = pg_type.oid;
        sqltype_short := sqltype;
        if typename = 'numeric' then
	   sqltype_short := replace(sqltype, ',0', '');
        elsif strpos(sqltype,'character varying') = 1 then
	   sqltype_short := replace(sqltype, 'character varying', 'varchar');
        elsif sqltype = 'timestamp without time zone' then
           sqltype_short := 'timestamp';
        end if;
        if lower(datatype) <> sqltype and lower(datatype) <> sqltype_short then
		i := 0;
		for v in select a.relname, a.oid 
			from pg_class a, pg_depend b, pg_depend c, pg_class d, pg_attribute e
			where a.oid = b.refobjid
			and b.objid = c.objid
			and b.refobjid <> c.refobjid
			and b.deptype = 'n'
			and c.refobjid = d.oid
			and d.relname = lower(tablename)
			and d.relkind = 'r'
			and d.oid = e.attrelid
			and e.attname = lower(columnname)
			and c.refobjsubid = e.attnum
			and a.relkind = 'v'
		 loop
		    i := i + 1;
		    viewtext[i] := pg_get_viewdef(v.oid);
		    viewname[i] := v.relname;
		end loop;
		if i > 0 then
		   begin
		     for j in 1 .. i loop
		        command := 'drop view ' || viewname[j];
		        execute command;
		        dropviews[j] := viewname[j];
		     end loop;
                     exception
                        when others then
                          i := array_upper(dropviews, 1);
                          if i > 0 then
                             for j in 1 .. i loop
                                command := 'create or replace view ' || dropviews[j] || ' as ' || viewtext[j];
		                execute command;
                             end loop;
                          end if;
                          raise exception 'Failed to recreate dependent view';
                   end;
		end if;
		command := 'alter table ' || lower(tablename) || ' alter column ' || lower(columnname) || ' type ' || lower(datatype);
		execute command;
                i := array_upper(dropviews, 1);
		if i > 0 then
		   for j in 1 .. i loop
		     command := 'create or replace view ' || dropviews[j] || ' as ' || viewtext[j];
		     execute command;
		   end loop;
		end if;
        end if;
   end if;
   
   if defaultclause is not null then
       if lower(defaultclause) = 'null' then
          command := 'alter table ' || lower(tablename) || ' alter column ' || lower(columnname) || ' drop default ';
       else
	  command := 'alter table ' || lower(tablename) || ' alter column ' || lower(columnname) || ' set default ''' || defaultclause || '''';
       end if;
       execute command;
   end if;
   
   if nullclause is not null then
      if lower(nullclause) = 'not null' then
          command := 'alter table ' || lower(tablename) || ' alter column ' || lower(columnname) || ' set not null';
          execute command;
      elsif lower(nullclause) = 'null' then
          command := 'alter table ' || lower(tablename) || ' alter column ' || lower(columnname) || ' drop not null';
          execute command;
      end if;
   end if;
end;
$$ language plpgsql;

create table t_alter_column
( tablename name, columnname name, datatype name, nullclause varchar(10), defaultclause varchar(200));

create rule alter_column_rule as on insert to t_alter_column
do instead select altercolumn(new.tablename, new.columnname, new.datatype, new.nullclause,
new.defaultclause);


