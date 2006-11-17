------------------
-- Created by: Andre Whittick Nasser, OCP
-- Email: andre_nasser@yahoo.com.br
-- Website: www.geocities.com/andre_nasser
-- Date: Oct 24, 2001
-- Description: Reverse Engineer your existing schema objects
------------------ 

set termout off
set feedback off
set serveroutput on size 1000000
spool table.sql

begin
dbms_output.put_line('--');
dbms_output.put_line('-- DROP TABLES --');
dbms_output.put_line('--');
    for rt in (select tname from tab order by tname) loop
        dbms_output.put_line('DROP TABLE '||rt.tname||' CASCADE CONSTRAINTS;');
    end loop;
end;
/        

declare 
    v_tname  varchar2(30);
    v_cname  char(32);
    v_type     char(20);
    v_null   varchar2(10);
    v_maxcol number;
    v_virg     varchar2(1);
begin
dbms_output.put_line('--');
dbms_output.put_line('-- CREATE TABLES --');
dbms_output.put_line('--');
    for rt in (select table_name from user_tables order by 1) loop
        v_tname:=rt.table_name;
        v_virg:=',';
        dbms_output.put_line('CREATE TABLE '||v_tname||' (');
        for rc in (select table_name,column_name,data_type,data_length,
                            data_precision,data_scale,nullable,column_id
                from user_tab_columns tc
                where tc.table_name=rt.table_name
                order by table_name,column_id) loop
                    v_cname:=rc.column_name;
                    if rc.data_type='VARCHAR2' then
                        v_type:='VARCHAR2('||rc.data_length||')';
                    elsif rc.data_type='NUMBER' and rc.data_precision is null 
and
                                         rc.data_scale=0 then
                        v_type:='INTEGER';
                    elsif rc.data_type='NUMBER' and rc.data_precision is null 
and
                                     rc.data_scale is null then
                        v_type:='NUMBER';
                    elsif rc.data_type='NUMBER' and rc.data_scale='0' then
                        v_type:='NUMBER('||rc.data_precision||')';
                    elsif rc.data_type='NUMBER' and rc.data_scale<>'0' then
                        
v_type:='NUMBER('||rc.data_precision||','||rc.data_scale||')';
                    elsif rc.data_type='CHAR' then
                         v_type:='CHAR('||rc.data_length||')';
                    else v_type:=rc.data_type;
                    end if;
                    
                    if rc.nullable='Y' then
                        v_null:='NULL';
                    else
                        v_null:='NOT NULL';
                    end if;
                    select max(column_id)
                        into v_maxcol
                        from user_tab_columns c
                        where c.table_name=rt.table_name;
                    if rc.column_id=v_maxcol then
                        v_virg:='';
                    end if;
                    dbms_output.put_line (v_cname||v_type||v_null||v_virg);
        end loop;
        dbms_output.put_line(');');
    end loop;
end;  
/

spool off

spool index.sql
declare 
    v_virg        varchar2(1);
    v_maxcol    number;
begin
dbms_output.put_line('--');
dbms_output.put_line('-- PRIMARY KEYS --');
dbms_output.put_line('--');
    for rcn in (select table_name,constraint_name 
            from user_constraints 
            where constraint_type='P' 
            order by table_name) loop
        dbms_output.put_line ('ALTER TABLE '||rcn.table_name||' ADD ');
        dbms_output.put_line ('CONSTRAINT '||rcn.constraint_name);
        dbms_output.put_line ('PRIMARY KEY (');
        v_virg:=',';
        for rcl in (select column_name,position 
                from user_cons_columns cl 
                where cl.constraint_name=rcn.constraint_name
                order by position) loop
            select max(position)
                into v_maxcol
                from user_cons_columns c
                where c.constraint_name=rcn.constraint_name;
            if rcl.position=v_maxcol then
                v_virg:='';
            end if;
            dbms_output.put_line (rcl.column_name||v_virg);
        end loop;
        dbms_output.put_line(');');
        --dbms_output.put_line('USING INDEX );');
    end loop;
end;
/

declare
    v_virg        varchar2(1);
    v_maxcol    number;
    v_tname        varchar2(30);
begin
dbms_output.put_line('--');
dbms_output.put_line('-- FOREIGN KEYS --');
dbms_output.put_line('--');
    for rcn in (select table_name,constraint_name,delete_rule,r_constraint_name 
            from dba_constraints 
            where constraint_type='R' AND OWNER = 'COMPIERE'
            order by table_name) loop
        --dbms_output.put_line ('ALTER TABLE '||rcn.table_name||' ADD (');
	dbms_output.put_line ('ALTER TABLE '||rcn.table_name||' ADD ');
        dbms_output.put_line ('CONSTRAINT '||rcn.constraint_name);
        dbms_output.put_line ('FOREIGN KEY (');
        v_virg:=',';
        for rcl in (select column_name,position 
                from user_cons_columns cl 
                where cl.constraint_name=rcn.constraint_name
                order by position) loop
            select max(position)
                into v_maxcol
                from user_cons_columns c
                where c.constraint_name=rcn.constraint_name;
            if rcl.position=v_maxcol then
                v_virg:='';
            end if;
            dbms_output.put_line (rcl.column_name||v_virg);
        end loop;
        select table_name 
            into v_tname
            from user_constraints c
            where c.constraint_name=rcn.r_constraint_name;
        dbms_output.put_line(') REFERENCES '||v_tname||' (');

        select max(position)
                into v_maxcol
                from user_cons_columns c
                where c.constraint_name=rcn.r_constraint_name;
        v_virg:=',';
        select max(position)
            into v_maxcol
            from user_cons_columns c
            where c.constraint_name=rcn.r_constraint_name;
        for rcr in (select column_name,position 
                from user_cons_columns cl
                where rcn.r_constraint_name=cl.constraint_name
                order by position) loop
            if rcr.position=v_maxcol then
                v_virg:='';
            end if;
            dbms_output.put_line (rcr.column_name||v_virg);
        end loop;
        dbms_output.put_line(' ) ');
	  if rcn.delete_rule='CASCADE' then
	   dbms_output.put_line(' ON DELETE CASCADE ; ');
	  else
	   dbms_output.put_line('; ');
	  end if;

    end loop;
end;
/
        
begin
dbms_output.put_line('--');
dbms_output.put_line('-- DROP SEQUENCES --');
dbms_output.put_line('--');
    for rs in (select sequence_name 
            from user_sequences
            where sequence_name like 'SQ%'
            order by sequence_name) loop
        dbms_output.put_line('DROP SEQUENCE '||rs.sequence_name||';');
    end loop;
dbms_output.put_line('--');
dbms_output.put_line('-- CREATE SEQUENCES --');
dbms_output.put_line('--');
    for rs in (select sequence_name 
            from user_sequences
            where sequence_name like 'SQ%'
            order by sequence_name) loop
        dbms_output.put_line('CREATE SEQUENCE '||rs.sequence_name||' NOCYCLE;');
    end loop;
end;
/

declare
    v_virg        varchar2(1);
    v_maxcol    number;
begin
dbms_output.put_line('--');
dbms_output.put_line('-- INDEXES --');
dbms_output.put_line('--');
    for rid in (select index_name, table_name 
            from user_indexes
            where index_name not in (select constraint_name from 
user_constraints) 
                and index_type<>'LOB'
            order by index_name) loop
        v_virg:=',';
        dbms_output.put_line('CREATE INDEX '||rid.index_name||' ON 
'||rid.table_name||' (');    
        for rcl in (select column_name,column_position 
                from user_ind_columns cl 
                where cl.index_name=rid.index_name
                order by column_position) loop
            select max(column_position)
                into v_maxcol
                from user_ind_columns c
                where c.index_name=rid.index_name;
            if rcl.column_position=v_maxcol then
                v_virg:='';
            end if;
            dbms_output.put_line (rcl.column_name||v_virg);
        end loop;
        dbms_output.put_line(');');
	
    end loop;
end;
/
    
spool off
set feedback on
set termout on

-- End of script
