CREATE OR REPLACE PROCEDURE generate_insert_as
AUTHID CURRENT_USER
AS
   dbsource       VARCHAR2 (30)      := 'SOURCE';
   dbtarget       VARCHAR2 (30)      := 'TARGET';
   liscolcommon   VARCHAR2 (4000);
   liscoltarget   VARCHAR2 (4000);
   liscolsource   VARCHAR2 (4000);
   where_ids      VARCHAR2 (4000);
   insertstmt     VARCHAR2 (4000);
   deletestmt     VARCHAR2 (4000);
   hasclient      BOOLEAN;
   -- CREATE OR REPLACE DIRECTORY dir_tmp AS '/tmp'
   -- grant execute on utl_file to target
   f              UTL_FILE.file_type;
BEGIN
   f := UTL_FILE.fopen ('DIR_TMP', '04_InsertMigr.sql', 'w');
   UTL_FILE.put_line (f, '-- Started ' || SYSDATE);

   -- for each table common in source and target
   FOR t IN (SELECT   object_name AS table_name
                 FROM all_objects
                WHERE owner = dbsource AND object_type = 'TABLE'
             -- AND object_name = 'AD_ATTACHMENTNOTE'
             INTERSECT
             SELECT   object_name
                 FROM all_objects
                WHERE owner = dbtarget AND object_type = 'TABLE'
             ORDER BY 1)
   LOOP
      liscolcommon := NULL;
      liscoltarget := NULL;
      liscolsource := NULL;
      where_ids := NULL;
      insertstmt := NULL;
      hasclient := FALSE;

      -- construct list of common columns
      FOR c IN (SELECT column_name
                  FROM all_tab_columns
                 WHERE owner = dbsource AND table_name = t.table_name
                INTERSECT
                SELECT column_name
                  FROM all_tab_columns
                 WHERE owner = dbtarget AND table_name = t.table_name)
      LOOP
         IF liscolcommon IS NULL
         THEN
            liscolcommon := c.column_name;
         ELSE
            liscolcommon := liscolcommon || ',' || CHR (10) || c.column_name;
         END IF;

         IF c.column_name LIKE '%\_ID' ESCAPE '\'
         THEN
            IF where_ids IS NULL
            THEN
               where_ids := c.column_name || '>=1000000';
            ELSE
               where_ids :=
                  where_ids || ' OR ' || CHR (10) || c.column_name
                  || '>=1000000';
            END IF;
         END IF;

         IF c.column_name = 'AD_CLIENT_ID'
         THEN
            hasclient := TRUE;
         END IF;
      END LOOP;

      -- construct list of columns only in target  (to review how to fill them)
      FOR c IN (SELECT column_name
                  FROM all_tab_columns
                 WHERE owner = dbtarget AND table_name = t.table_name
                MINUS
                SELECT column_name
                  FROM all_tab_columns
                 WHERE owner = dbsource AND table_name = t.table_name)
      LOOP
         IF liscoltarget IS NULL
         THEN
            liscoltarget := c.column_name;
         ELSE
            liscoltarget := liscoltarget || ', ' || c.column_name;
         END IF;
      END LOOP;

      -- construct list of columns only in source  (to review - data can be lost)
      FOR c IN (SELECT column_name
                  FROM all_tab_columns
                 WHERE owner = dbsource AND table_name = t.table_name
                MINUS
                SELECT column_name
                  FROM all_tab_columns
                 WHERE owner = dbtarget AND table_name = t.table_name)
      LOOP
         IF liscolsource IS NULL
         THEN
            liscolsource := c.column_name;
         ELSE
            liscolsource := liscolsource || ', ' || c.column_name;
         END IF;
      END LOOP;

      UTL_FILE.put_line (f, CHR (10) || '-- migrate table ' || t.table_name);

      IF liscoltarget IS NOT NULL
      THEN
         UTL_FILE.put_line (f, '-- columns in target: ' || liscoltarget);
      END IF;

      IF liscolsource IS NOT NULL
      THEN
         UTL_FILE.put_line (f, '-- columns in source: ' || liscolsource);
      END IF;

      deletestmt :=
            'DELETE FROM '
         || dbtarget
         || '.'
         || t.table_name
         || CASE
               WHEN where_ids IS NOT NULL
                  THEN CHR (10) || 'WHERE (' || where_ids || ')'
            END
         || ';'
         || CHR (10);
      UTL_FILE.put_line (f, deletestmt);
      insertstmt :=
            'INSERT INTO '
         || dbtarget
         || '.'
         || t.table_name
         || '('
         || liscolcommon
         || ')'
         || CHR (10)
         || 'SELECT '
         || liscolcommon
         || ' FROM '
         || dbsource
         || '.'
         || t.table_name
         || CASE
               WHEN where_ids IS NOT NULL AND hasclient
                  THEN    CHR (10)
                       || 'WHERE AD_CLIENT_ID<>11 AND ('
                       || where_ids
                       || ')'
               WHEN where_ids IS NOT NULL AND NOT hasclient
                  THEN CHR (10) || 'WHERE (' || where_ids || ')'
            END
         || ';';
      UTL_FILE.put_line (f, insertstmt);
   END LOOP;

   UTL_FILE.put_line (f, '-- End ' || SYSDATE);
   UTL_FILE.fclose (f);
END;
