CREATE OR REPLACE FUNCTION add_missing_translations() RETURNS void as $func$
DECLARE
   ins        VARCHAR (2000);
   sel        VARCHAR (2000);
   inssel     VARCHAR (4001);
   table_id   NUMERIC;
   t	      RECORD;
   c	      RECORD;
BEGIN

   FOR t IN (SELECT ad_table_id,
                    SUBSTR (tablename, 1, LENGTH (tablename) - 4) as tablename
               FROM AD_TABLE
              WHERE tablename LIKE '%_Trl' AND isactive = 'Y'
                    AND isview = 'N')
   LOOP
      ins :=
            'INSERT INTO '
         || t.tablename
         || '_TRL ('
         || 'ad_language,ad_client_id,ad_org_id,created,createdby,updated,updatedby,isactive,istranslated,'
         || t.tablename
         || '_id';
      sel :=
            'SELECT l.ad_language,t.ad_client_id,t.ad_org_id,t.created,t.createdby,t.updated,t.updatedby,t.isactive,''N'' as istranslated,'
         || t.tablename
         || '_id';

      SELECT ad_table_id
        INTO table_id
        FROM AD_TABLE
       WHERE tablename = t.tablename;

      FOR c IN (SELECT   col.columnname
                    FROM AD_COLUMN col INNER JOIN AD_TABLE tab
                         ON (col.ad_table_id = tab.ad_table_id)
                   WHERE col.ad_table_id = table_id
                     AND col.istranslated = 'Y'
                     AND col.isactive = 'Y'
                ORDER BY 1)
      LOOP
         ins := TRIM (ins) || ',' || TRIM (c.columnname);
         sel := TRIM (sel) || ',t.' || TRIM (c.columnname);
      END LOOP;

      ins := TRIM (ins) || ')';
      sel :=
            TRIM (sel)
         || ' from '
         || t.tablename
         || ' t, ad_language l WHERE l.issystemlanguage=''Y'' AND NOT EXISTS (SELECT 1 FROM '
         || t.tablename
         || '_TRL b WHERE b.'
         || t.tablename
         || '_id=t.'
         || t.tablename
         || '_id AND b.AD_LANGUAGE=l.AD_LANGUAGE)';
      inssel := TRIM (ins) || ' ' || TRIM (sel);

      EXECUTE inssel;
   END LOOP;

END;
$func$ LANGUAGE plpgsql;

select add_missing_translations();

commit;

-- Synchronize Terminology

/*
-- take account of the output for these two selects

SELECT DISTINCT columnname, NAME, description, HELP, entitytype
           FROM AD_COLUMN c
          WHERE NOT EXISTS (SELECT 1
                              FROM AD_ELEMENT e
                             WHERE UPPER (c.columnname) = UPPER (e.columnname));

SELECT DISTINCT columnname, NAME, description, HELP, entitytype
           FROM AD_PROCESS_PARA p
          WHERE NOT EXISTS (SELECT 1
                              FROM AD_ELEMENT e
                             WHERE UPPER (p.columnname) = UPPER (e.columnname));

*/
-- execute							 

INSERT INTO AD_ELEMENT_TRL
            (ad_element_id, AD_LANGUAGE, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, NAME, printname,
             description, HELP, istranslated)
   SELECT m.ad_element_id, l.AD_LANGUAGE, m.ad_client_id, m.ad_org_id,
          m.isactive, m.created, m.createdby, m.updated, m.updatedby, m.NAME,
          m.printname, m.description, m.HELP, 'N'
     FROM AD_ELEMENT m, AD_LANGUAGE l
    WHERE l.isactive = 'Y'
      AND l.issystemlanguage = 'Y'
      AND ad_element_id || AD_LANGUAGE NOT IN (
                                           SELECT ad_element_id || AD_LANGUAGE
                                             FROM AD_ELEMENT_TRL);

UPDATE AD_COLUMN
   SET ad_element_id = (SELECT ad_element_id
                          FROM AD_ELEMENT e
                         WHERE UPPER (AD_COLUMN.columnname) = UPPER (e.columnname))
 WHERE ad_element_id IS NULL;

DELETE FROM AD_ELEMENT_TRL
      WHERE ad_element_id IN (
               SELECT ad_element_id
                 FROM AD_ELEMENT e
                WHERE NOT EXISTS (
                              SELECT 1
                                FROM AD_COLUMN c
                               WHERE UPPER (e.columnname) =
                                                          UPPER (c.columnname))
                  AND NOT EXISTS (
                              SELECT 1
                                FROM AD_PROCESS_PARA p
                               WHERE UPPER (e.columnname) =
                                                          UPPER (p.columnname)));

DELETE FROM AD_ELEMENT
      WHERE NOT EXISTS (SELECT 1
                          FROM AD_COLUMN c
                         WHERE UPPER (AD_ELEMENT.columnname) = UPPER (c.columnname))
        AND NOT EXISTS (SELECT 1
                          FROM AD_PROCESS_PARA p
                         WHERE UPPER (AD_ELEMENT.columnname) = UPPER (p.columnname));

UPDATE AD_COLUMN
   SET columnname =
          (SELECT columnname
             FROM AD_ELEMENT e
            WHERE AD_COLUMN.ad_element_id = e.ad_element_id),
       NAME =
          (SELECT NAME
             FROM AD_ELEMENT e
            WHERE AD_COLUMN.ad_element_id = e.ad_element_id),
       description =
          (SELECT description
             FROM AD_ELEMENT e
            WHERE AD_COLUMN.ad_element_id = e.ad_element_id),
       HELP =
          (SELECT HELP
             FROM AD_ELEMENT e
            WHERE AD_COLUMN.ad_element_id = e.ad_element_id),
       updated = current_timestamp
 WHERE EXISTS (
          SELECT 1
            FROM AD_ELEMENT e
           WHERE AD_COLUMN.ad_element_id = e.ad_element_id
             AND (   AD_COLUMN.columnname <> e.columnname
                  OR AD_COLUMN.NAME <> e.NAME
                  OR COALESCE (AD_COLUMN.description, ' ') <> COALESCE (e.description, ' ')
                  OR COALESCE (AD_COLUMN.HELP, ' ') <> COALESCE (e.HELP, ' ')
                 ));

UPDATE AD_FIELD
   SET NAME =
          (SELECT e.NAME
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       description =
          (SELECT e.description
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       HELP =
          (SELECT e.HELP
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       updated = current_timestamp
 WHERE AD_FIELD.iscentrallymaintained = 'Y'
   AND AD_FIELD.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e, AD_COLUMN c
           WHERE AD_FIELD.ad_column_id = c.ad_column_id
             AND c.ad_element_id = e.ad_element_id
             AND c.ad_process_id IS NULL
             AND (   AD_FIELD.NAME <> e.NAME
                  OR COALESCE (AD_FIELD.description, ' ') <> COALESCE (e.description, ' ')
                  OR COALESCE (AD_FIELD.HELP, ' ') <> COALESCE (e.HELP, ' ')
                 ));

UPDATE AD_FIELD_TRL
   SET NAME =
          (SELECT e.NAME
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id),
       description =
          (SELECT e.description
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id),
       HELP =
          (SELECT e.HELP
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id),
       istranslated =
          (SELECT e.istranslated
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id),
       updated = current_timestamp
 WHERE EXISTS (
          SELECT 1
            FROM AD_FIELD f, AD_ELEMENT_TRL e, AD_COLUMN c
           WHERE AD_FIELD_TRL.ad_field_id = f.ad_field_id
             AND f.ad_column_id = c.ad_column_id
             AND c.ad_element_id = e.ad_element_id
             AND c.ad_process_id IS NULL
             AND AD_FIELD_TRL.AD_LANGUAGE = e.AD_LANGUAGE
             AND f.iscentrallymaintained = 'Y'
             AND f.isactive = 'Y'
             AND (   AD_FIELD_TRL.NAME <> e.NAME
                  OR COALESCE (AD_FIELD_TRL.description, ' ') <> COALESCE (e.description, ' ')
                  OR COALESCE (AD_FIELD_TRL.HELP, ' ') <> COALESCE (e.HELP, ' ')
                 ));

UPDATE AD_FIELD
   SET NAME =
          (SELECT e.po_name
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       description =
          (SELECT e.po_description
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       HELP =
          (SELECT e.po_help
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       updated = current_timestamp
 WHERE AD_FIELD.iscentrallymaintained = 'Y'
   AND AD_FIELD.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e, AD_COLUMN c
           WHERE AD_FIELD.ad_column_id = c.ad_column_id
             AND c.ad_element_id = e.ad_element_id
             AND c.ad_process_id IS NULL
             AND (   AD_FIELD.NAME <> e.po_name
                  OR COALESCE (AD_FIELD.description, ' ') <> COALESCE (e.po_description, ' ')
                  OR COALESCE (AD_FIELD.HELP, ' ') <> COALESCE (e.po_help, ' ')
                 )
             AND e.po_name IS NOT NULL)
   AND EXISTS (
          SELECT 1
            FROM AD_TAB t, AD_WINDOW w
           WHERE AD_FIELD.ad_tab_id = t.ad_tab_id
             AND t.ad_window_id = w.ad_window_id
             AND w.issotrx = 'N');

UPDATE AD_FIELD_TRL
   SET NAME =
          (SELECT e.po_name
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id),
       description =
          (SELECT e.po_description
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id),
       HELP =
          (SELECT e.po_help
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id),
       istranslated =
          (SELECT e.istranslated
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id),
       updated = current_timestamp
 WHERE EXISTS (
          SELECT 1
            FROM AD_FIELD f, AD_ELEMENT_TRL e, AD_COLUMN c
           WHERE AD_FIELD_TRL.ad_field_id = f.ad_field_id
             AND f.ad_column_id = c.ad_column_id
             AND c.ad_element_id = e.ad_element_id
             AND c.ad_process_id IS NULL
             AND AD_FIELD_TRL.AD_LANGUAGE = e.AD_LANGUAGE
             AND f.iscentrallymaintained = 'Y'
             AND f.isactive = 'Y'
             AND (   AD_FIELD_TRL.NAME <> e.po_name
                  OR COALESCE (AD_FIELD_TRL.description, ' ') <> COALESCE (e.po_description, ' ')
                  OR COALESCE (AD_FIELD_TRL.HELP, ' ') <> COALESCE (e.po_help, ' ')
                 )
             AND e.po_name IS NOT NULL)
   AND EXISTS (
          SELECT 1
            FROM AD_FIELD f, AD_TAB t, AD_WINDOW w
           WHERE AD_FIELD_TRL.ad_field_id = f.ad_field_id
             AND f.ad_tab_id = t.ad_tab_id
             AND t.ad_window_id = w.ad_window_id
             AND w.issotrx = 'N');

UPDATE AD_FIELD
   SET NAME =
          (SELECT p.NAME
             FROM AD_PROCESS p, AD_COLUMN c
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       description =
          (SELECT p.description
             FROM AD_PROCESS p, AD_COLUMN c
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       HELP =
          (SELECT p.HELP
             FROM AD_PROCESS p, AD_COLUMN c
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = AD_FIELD.ad_column_id),
       updated = current_timestamp
 WHERE AD_FIELD.iscentrallymaintained = 'Y'
   AND AD_FIELD.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_PROCESS p, AD_COLUMN c
           WHERE c.ad_process_id = p.ad_process_id
             AND AD_FIELD.ad_column_id = c.ad_column_id
             AND (   AD_FIELD.NAME <> p.NAME
                  OR COALESCE (AD_FIELD.description, ' ') <> COALESCE (p.description, ' ')
                  OR COALESCE (AD_FIELD.HELP, ' ') <> COALESCE (p.HELP, ' ')
                 ));

UPDATE AD_FIELD_TRL
   SET NAME =
          (SELECT p.NAME
             FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id
              AND p.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE),
       description =
          (SELECT p.description
             FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id
              AND p.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE),
       HELP =
          (SELECT p.HELP
             FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id
              AND p.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE),
       istranslated =
          (SELECT p.istranslated
             FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = AD_FIELD_TRL.ad_field_id
              AND p.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE),
       updated = current_timestamp
 WHERE EXISTS (
          SELECT 1
            FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
           WHERE c.ad_process_id = p.ad_process_id
             AND f.ad_column_id = c.ad_column_id
             AND f.ad_field_id = AD_FIELD_TRL.ad_field_id
             AND p.AD_LANGUAGE = AD_FIELD_TRL.AD_LANGUAGE
             AND f.iscentrallymaintained = 'Y'
             AND f.isactive = 'Y'
             AND (   AD_FIELD_TRL.NAME <> p.NAME
                  OR COALESCE (AD_FIELD_TRL.description, ' ') <> COALESCE (p.description, ' ')
                  OR COALESCE (AD_FIELD_TRL.HELP, ' ') <> COALESCE (p.HELP, ' ')
                 ));

/*
-- check for element errors				 
SELECT   UPPER (e.columnname), COUNT (*)
    FROM AD_ELEMENT e
GROUP BY UPPER (e.columnname)
  HAVING COUNT (*) > 1;

SELECT   ROWID, ad_element_id, columnname,
         (SELECT COUNT (*)
            FROM AD_COLUMN c
           WHERE c.ad_element_id = AD_ELEMENT.ad_element_id) cnt
    FROM AD_ELEMENT
   WHERE UPPER (columnname) IN (SELECT   UPPER (e.columnname)
                                    FROM AD_ELEMENT e
                                GROUP BY UPPER (e.columnname)
                                  HAVING COUNT (*) > 1)
ORDER BY UPPER (columnname), columnname;
*/

UPDATE AD_PROCESS_PARA
   SET columnname = (SELECT e.columnname
                       FROM AD_ELEMENT e
                      -- WHERE UPPER (e.columnname) = UPPER (AD_PROCESS_PARA.columnname))
                      WHERE e.columnname = AD_PROCESS_PARA.columnname) -- Temporary patch Fixed Assets are broking it
 WHERE AD_PROCESS_PARA.iscentrallymaintained = 'Y'
   AND AD_PROCESS_PARA.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e
           WHERE UPPER (e.columnname) = UPPER (AD_PROCESS_PARA.columnname)
             AND e.columnname <> AD_PROCESS_PARA.columnname);

UPDATE AD_PROCESS_PARA
   SET iscentrallymaintained = 'N'
 WHERE iscentrallymaintained <> 'N'
   AND NOT EXISTS (SELECT 1
                     FROM AD_ELEMENT e
                    WHERE AD_PROCESS_PARA.columnname = e.columnname);

UPDATE AD_PROCESS_PARA
   SET NAME = (SELECT e.NAME
                 FROM AD_ELEMENT e
                WHERE e.columnname = AD_PROCESS_PARA.columnname),
       description = (SELECT e.description
                        FROM AD_ELEMENT e
                       WHERE e.columnname = AD_PROCESS_PARA.columnname),
       HELP = (SELECT e.HELP
                 FROM AD_ELEMENT e
                WHERE e.columnname = AD_PROCESS_PARA.columnname),
       updated = current_timestamp
 WHERE AD_PROCESS_PARA.iscentrallymaintained = 'Y'
   AND AD_PROCESS_PARA.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e
           WHERE e.columnname = AD_PROCESS_PARA.columnname
             AND (   AD_PROCESS_PARA.NAME <> e.NAME
                  OR COALESCE (AD_PROCESS_PARA.description, ' ') <> COALESCE (e.description, ' ')
                  OR COALESCE (AD_PROCESS_PARA.HELP, ' ') <> COALESCE (e.HELP, ' ')
                 ));

UPDATE AD_PROCESS_PARA_TRL
   SET NAME =
          (SELECT et.NAME
             FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
            WHERE et.AD_LANGUAGE = AD_PROCESS_PARA_TRL.AD_LANGUAGE
              AND et.ad_element_id = e.ad_element_id
              AND e.columnname = f.columnname
              AND f.ad_process_para_id = AD_PROCESS_PARA_TRL.ad_process_para_id),
       description =
          (SELECT et.description
             FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
            WHERE et.AD_LANGUAGE = AD_PROCESS_PARA_TRL.AD_LANGUAGE
              AND et.ad_element_id = e.ad_element_id
              AND e.columnname = f.columnname
              AND f.ad_process_para_id = AD_PROCESS_PARA_TRL.ad_process_para_id),
       HELP =
          (SELECT et.HELP
             FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
            WHERE et.AD_LANGUAGE = AD_PROCESS_PARA_TRL.AD_LANGUAGE
              AND et.ad_element_id = e.ad_element_id
              AND e.columnname = f.columnname
              AND f.ad_process_para_id = AD_PROCESS_PARA_TRL.ad_process_para_id),
       istranslated =
          (SELECT et.istranslated
             FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
            WHERE et.AD_LANGUAGE = AD_PROCESS_PARA_TRL.AD_LANGUAGE
              AND et.ad_element_id = e.ad_element_id
              AND e.columnname = f.columnname
              AND f.ad_process_para_id = AD_PROCESS_PARA_TRL.ad_process_para_id),
       updated = current_timestamp
 WHERE EXISTS (
          SELECT 1
            FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
           WHERE et.AD_LANGUAGE = AD_PROCESS_PARA_TRL.AD_LANGUAGE
             AND et.ad_element_id = e.ad_element_id
             AND e.columnname = f.columnname
             AND f.ad_process_para_id = AD_PROCESS_PARA_TRL.ad_process_para_id
             AND f.iscentrallymaintained = 'Y'
             AND f.isactive = 'Y'
             AND (   AD_PROCESS_PARA_TRL.NAME <> et.NAME
                  OR COALESCE (AD_PROCESS_PARA_TRL.description, ' ') <> COALESCE (et.description, ' ')
                  OR COALESCE (AD_PROCESS_PARA_TRL.HELP, ' ') <> COALESCE (et.HELP, ' ')
                 ));

UPDATE AD_WF_NODE
   SET NAME = (SELECT w.NAME
                 FROM AD_WINDOW w
                WHERE w.ad_window_id = AD_WF_NODE.ad_window_id),
       description = (SELECT w.description
                        FROM AD_WINDOW w
                       WHERE w.ad_window_id = AD_WF_NODE.ad_window_id),
       HELP = (SELECT w.HELP
                 FROM AD_WINDOW w
                WHERE w.ad_window_id = AD_WF_NODE.ad_window_id)
 WHERE AD_WF_NODE.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_WINDOW w
           WHERE w.ad_window_id = AD_WF_NODE.ad_window_id
             AND (   w.NAME <> AD_WF_NODE.NAME
                  OR COALESCE (w.description, ' ') <> COALESCE (AD_WF_NODE.description, ' ')
                  OR COALESCE (w.HELP, ' ') <> COALESCE (AD_WF_NODE.HELP, ' ')
                 ));

UPDATE AD_WF_NODE_TRL
   SET NAME =
          (SELECT t.NAME
             FROM AD_WINDOW_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_window_id = t.ad_window_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE),
       description =
          (SELECT t.description
             FROM AD_WINDOW_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_window_id = t.ad_window_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE),
       HELP =
          (SELECT t.HELP
             FROM AD_WINDOW_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_window_id = t.ad_window_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_WINDOW_TRL t, AD_WF_NODE n
           WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
             AND n.ad_window_id = t.ad_window_id
             AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE
             AND n.iscentrallymaintained = 'Y'
             AND n.isactive = 'Y'
             AND (   AD_WF_NODE_TRL.NAME <> t.NAME
                  OR COALESCE (AD_WF_NODE_TRL.description, ' ') <> COALESCE (t.description, ' ')
                  OR COALESCE (AD_WF_NODE_TRL.HELP, ' ') <> COALESCE (t.HELP, ' ')
                 ));

UPDATE AD_WF_NODE
   SET NAME = 
       (SELECT f.NAME
            FROM AD_FORM f
           WHERE f.ad_form_id = AD_WF_NODE.ad_form_id),
       description = 
       (SELECT f.description
            FROM AD_FORM f
           WHERE f.ad_form_id = AD_WF_NODE.ad_form_id),
       HELP = 
       (SELECT f.HELP
            FROM AD_FORM f
           WHERE f.ad_form_id = AD_WF_NODE.ad_form_id)
 WHERE AD_WF_NODE.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_FORM f
           WHERE f.ad_form_id = AD_WF_NODE.ad_form_id
             AND (   f.NAME <> AD_WF_NODE.NAME
                  OR COALESCE (f.description, ' ') <> COALESCE (AD_WF_NODE.description, ' ')
                  OR COALESCE (f.HELP, ' ') <> COALESCE (AD_WF_NODE.HELP, ' ')
                 ));

UPDATE AD_WF_NODE_TRL
   SET NAME =
          (SELECT t.NAME
             FROM AD_FORM_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_form_id = t.ad_form_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE),
       description =
          (SELECT t.description
             FROM AD_FORM_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_form_id = t.ad_form_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE),
       HELP =
          (SELECT t.HELP
             FROM AD_FORM_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_form_id = t.ad_form_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_FORM_TRL t, AD_WF_NODE n
           WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
             AND n.ad_form_id = t.ad_form_id
             AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE
             AND n.iscentrallymaintained = 'Y'
             AND n.isactive = 'Y'
             AND (   AD_WF_NODE_TRL.NAME <> t.NAME
                  OR COALESCE (AD_WF_NODE_TRL.description, ' ') <> COALESCE (t.description, ' ')
                  OR COALESCE (AD_WF_NODE_TRL.HELP, ' ') <> COALESCE (t.HELP, ' ')
                 ));

UPDATE AD_WF_NODE
   SET NAME = 
       (SELECT f.NAME
            FROM AD_PROCESS f
           WHERE f.ad_process_id = AD_WF_NODE.ad_process_id),
       description = 
       (SELECT f.description
            FROM AD_PROCESS f
           WHERE f.ad_process_id = AD_WF_NODE.ad_process_id),
       HELP = 
       (SELECT f.HELP
            FROM AD_PROCESS f
           WHERE f.ad_process_id = AD_WF_NODE.ad_process_id)
 WHERE AD_WF_NODE.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_PROCESS f
           WHERE f.ad_process_id = AD_WF_NODE.ad_process_id
             AND (   f.NAME <> AD_WF_NODE.NAME
                  OR COALESCE (f.description, ' ') <> COALESCE (AD_WF_NODE.description, ' ')
                  OR COALESCE (f.HELP, ' ') <> COALESCE (AD_WF_NODE.HELP, ' ')
                 ));

UPDATE AD_WF_NODE_TRL
   SET NAME =
          (SELECT t.NAME
             FROM AD_PROCESS_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_process_id = t.ad_process_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE),
       description =
          (SELECT t.description
             FROM AD_PROCESS_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_process_id = t.ad_process_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE),
       HELP =
          (SELECT t.HELP
             FROM AD_PROCESS_TRL t, AD_WF_NODE n
            WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_process_id = t.ad_process_id
              AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_PROCESS_TRL t, AD_WF_NODE n
           WHERE AD_WF_NODE_TRL.ad_wf_node_id = n.ad_wf_node_id
             AND n.ad_process_id = t.ad_process_id
             AND AD_WF_NODE_TRL.AD_LANGUAGE = t.AD_LANGUAGE
             AND n.iscentrallymaintained = 'Y'
             AND n.isactive = 'Y'
             AND (   AD_WF_NODE_TRL.NAME <> t.NAME
                  OR COALESCE (AD_WF_NODE_TRL.description, ' ') <> COALESCE (t.description, ' ')
                  OR COALESCE (AD_WF_NODE_TRL.HELP, ' ') <> COALESCE (t.HELP, ' ')
                 ));

UPDATE AD_PRINTFORMATITEM
   SET NAME =
          (SELECT e.NAME
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = AD_PRINTFORMATITEM.ad_column_id)
 WHERE AD_PRINTFORMATITEM.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e, AD_COLUMN c
           WHERE e.ad_element_id = c.ad_element_id
             AND c.ad_column_id = AD_PRINTFORMATITEM.ad_column_id
             AND e.NAME <> AD_PRINTFORMATITEM.NAME)
   AND EXISTS (
          SELECT 1
            FROM AD_CLIENT
           WHERE ad_client_id = AD_PRINTFORMATITEM.ad_client_id
             AND ismultilingualdocument = 'Y');

UPDATE AD_PRINTFORMATITEM
   SET printname =
          (SELECT e.printname
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = AD_PRINTFORMATITEM.ad_column_id)
 WHERE AD_PRINTFORMATITEM.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e, AD_COLUMN c, AD_PRINTFORMAT pf
           WHERE e.ad_element_id = c.ad_element_id
             AND c.ad_column_id = AD_PRINTFORMATITEM.ad_column_id
             AND LENGTH (AD_PRINTFORMATITEM.printname) > 0
             AND e.printname <> AD_PRINTFORMATITEM.printname
             AND pf.ad_printformat_id = AD_PRINTFORMATITEM.ad_printformat_id
             AND pf.isform = 'N'
             AND istablebased = 'Y')
   AND EXISTS (
          SELECT 1
            FROM AD_CLIENT
           WHERE ad_client_id = AD_PRINTFORMATITEM.ad_client_id
             AND ismultilingualdocument = 'Y');

UPDATE AD_PRINTFORMATITEM_TRL
   SET printname =
          (SELECT e.printname
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_PRINTFORMATITEM pfi
            WHERE e.AD_LANGUAGE = AD_PRINTFORMATITEM_TRL.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = pfi.ad_column_id
              AND pfi.ad_printformatitem_id = AD_PRINTFORMATITEM_TRL.ad_printformatitem_id)
 WHERE EXISTS (
          SELECT 1
            FROM AD_ELEMENT_TRL e,
                 AD_COLUMN c,
                 AD_PRINTFORMATITEM pfi,
                 AD_PRINTFORMAT pf
           WHERE e.AD_LANGUAGE = AD_PRINTFORMATITEM_TRL.AD_LANGUAGE
             AND e.ad_element_id = c.ad_element_id
             AND c.ad_column_id = pfi.ad_column_id
             AND pfi.ad_printformatitem_id = AD_PRINTFORMATITEM_TRL.ad_printformatitem_id
             AND pfi.iscentrallymaintained = 'Y'
             AND LENGTH (pfi.printname) > 0
             AND (e.printname <> AD_PRINTFORMATITEM_TRL.printname OR AD_PRINTFORMATITEM_TRL.printname IS NULL)
             AND pf.ad_printformat_id = pfi.ad_printformat_id
             AND pf.isform = 'N'
             AND istablebased = 'Y')
   AND EXISTS (
          SELECT 1
            FROM AD_CLIENT
           WHERE ad_client_id = AD_PRINTFORMATITEM_TRL.ad_client_id
             AND ismultilingualdocument = 'Y');

UPDATE AD_PRINTFORMATITEM_TRL
   SET printname =
                (SELECT pfi.printname
                   FROM AD_PRINTFORMATITEM pfi
                  WHERE pfi.ad_printformatitem_id = AD_PRINTFORMATITEM_TRL.ad_printformatitem_id)
 WHERE EXISTS (
          SELECT 1
            FROM AD_PRINTFORMATITEM pfi, AD_PRINTFORMAT pf
           WHERE pfi.ad_printformatitem_id = AD_PRINTFORMATITEM_TRL.ad_printformatitem_id
             AND pfi.iscentrallymaintained = 'Y'
             AND LENGTH (pfi.printname) > 0
             AND pfi.printname <> AD_PRINTFORMATITEM_TRL.printname
             AND pf.ad_printformat_id = pfi.ad_printformat_id
             AND pf.isform = 'N'
             AND pf.istablebased = 'Y')
   AND EXISTS (
          SELECT 1
            FROM AD_CLIENT
           WHERE ad_client_id = AD_PRINTFORMATITEM_TRL.ad_client_id
             AND ismultilingualdocument = 'N');

UPDATE AD_PRINTFORMATITEM_TRL
   SET printname = NULL
 WHERE printname IS NOT NULL
   AND EXISTS (
          SELECT 1
            FROM AD_PRINTFORMATITEM pfi
           WHERE pfi.ad_printformatitem_id = AD_PRINTFORMATITEM_TRL.ad_printformatitem_id
             AND pfi.iscentrallymaintained = 'Y'
             AND (LENGTH (pfi.printname) = 0 OR pfi.printname IS NULL));

UPDATE AD_MENU
   SET NAME = (SELECT NAME
                 FROM AD_WINDOW w
                WHERE AD_MENU.ad_window_id = w.ad_window_id),
       description = (SELECT description
                        FROM AD_WINDOW w
                       WHERE AD_MENU.ad_window_id = w.ad_window_id)
 WHERE ad_window_id IS NOT NULL AND action = 'W';

UPDATE AD_MENU_TRL
   SET NAME =
          (SELECT wt.NAME
             FROM AD_WINDOW_TRL wt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_window_id = wt.ad_window_id
              AND AD_MENU_TRL.AD_LANGUAGE = wt.AD_LANGUAGE),
       description =
          (SELECT wt.description
             FROM AD_WINDOW_TRL wt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_window_id = wt.ad_window_id
              AND AD_MENU_TRL.AD_LANGUAGE = wt.AD_LANGUAGE),
       istranslated =
          (SELECT wt.istranslated
             FROM AD_WINDOW_TRL wt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_window_id = wt.ad_window_id
              AND AD_MENU_TRL.AD_LANGUAGE = wt.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_WINDOW_TRL wt, AD_MENU m
           WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
             AND m.ad_window_id = wt.ad_window_id
             AND AD_MENU_TRL.AD_LANGUAGE = wt.AD_LANGUAGE
             AND m.ad_window_id IS NOT NULL
             AND m.action = 'W');

UPDATE AD_MENU
   SET NAME = (SELECT p.NAME
                 FROM AD_PROCESS p
                WHERE AD_MENU.ad_process_id = p.ad_process_id),
       description = (SELECT p.description
                        FROM AD_PROCESS p
                       WHERE AD_MENU.ad_process_id = p.ad_process_id)
 WHERE AD_MENU.ad_process_id IS NOT NULL AND AD_MENU.action IN ('R', 'P');

UPDATE AD_MENU_TRL
   SET NAME =
          (SELECT pt.NAME
             FROM AD_PROCESS_TRL pt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_process_id = pt.ad_process_id
              AND AD_MENU_TRL.AD_LANGUAGE = pt.AD_LANGUAGE),
       description =
          (SELECT pt.description
             FROM AD_PROCESS_TRL pt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_process_id = pt.ad_process_id
              AND AD_MENU_TRL.AD_LANGUAGE = pt.AD_LANGUAGE),
       istranslated =
          (SELECT pt.istranslated
             FROM AD_PROCESS_TRL pt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_process_id = pt.ad_process_id
              AND AD_MENU_TRL.AD_LANGUAGE = pt.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_PROCESS_TRL pt, AD_MENU m
           WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
             AND m.ad_process_id = pt.ad_process_id
             AND AD_MENU_TRL.AD_LANGUAGE = pt.AD_LANGUAGE
             AND m.ad_process_id IS NOT NULL
             AND action IN ('R', 'P'));

UPDATE AD_MENU
   SET NAME = (SELECT NAME
                 FROM AD_FORM f
                WHERE AD_MENU.ad_form_id = f.ad_form_id),
       description = (SELECT description
                        FROM AD_FORM f
                       WHERE AD_MENU.ad_form_id = f.ad_form_id)
 WHERE ad_form_id IS NOT NULL AND action = 'X';

UPDATE AD_MENU_TRL
   SET NAME =
          (SELECT ft.NAME
             FROM AD_FORM_TRL ft, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_form_id = ft.ad_form_id
              AND AD_MENU_TRL.AD_LANGUAGE = ft.AD_LANGUAGE),
       description =
          (SELECT ft.description
             FROM AD_FORM_TRL ft, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_form_id = ft.ad_form_id
              AND AD_MENU_TRL.AD_LANGUAGE = ft.AD_LANGUAGE),
       istranslated =
          (SELECT ft.istranslated
             FROM AD_FORM_TRL ft, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_form_id = ft.ad_form_id
              AND AD_MENU_TRL.AD_LANGUAGE = ft.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_FORM_TRL ft, AD_MENU m
           WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
             AND m.ad_form_id = ft.ad_form_id
             AND AD_MENU_TRL.AD_LANGUAGE = ft.AD_LANGUAGE
             AND m.ad_form_id IS NOT NULL
             AND action = 'X');

UPDATE AD_MENU
   SET NAME = (SELECT p.NAME
                 FROM AD_WORKFLOW p
                WHERE AD_MENU.ad_workflow_id = p.ad_workflow_id),
       description = (SELECT p.description
                        FROM AD_WORKFLOW p
                       WHERE AD_MENU.ad_workflow_id = p.ad_workflow_id)
 WHERE AD_MENU.ad_workflow_id IS NOT NULL AND AD_MENU.action = 'F';

UPDATE AD_MENU_TRL
   SET NAME =
          (SELECT pt.NAME
             FROM AD_WORKFLOW_TRL pt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_workflow_id = pt.ad_workflow_id
              AND AD_MENU_TRL.AD_LANGUAGE = pt.AD_LANGUAGE),
       description =
          (SELECT pt.description
             FROM AD_WORKFLOW_TRL pt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_workflow_id = pt.ad_workflow_id
              AND AD_MENU_TRL.AD_LANGUAGE = pt.AD_LANGUAGE),
       istranslated =
          (SELECT pt.istranslated
             FROM AD_WORKFLOW_TRL pt, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_workflow_id = pt.ad_workflow_id
              AND AD_MENU_TRL.AD_LANGUAGE = pt.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_WORKFLOW_TRL pt, AD_MENU m
           WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
             AND m.ad_workflow_id = pt.ad_workflow_id
             AND AD_MENU_TRL.AD_LANGUAGE = pt.AD_LANGUAGE
             AND m.ad_workflow_id IS NOT NULL
             AND action = 'F');

UPDATE AD_MENU
   SET NAME = (SELECT NAME
                 FROM AD_TASK f
                WHERE AD_MENU.ad_task_id = f.ad_task_id),
       description = (SELECT description
                        FROM AD_TASK f
                       WHERE AD_MENU.ad_task_id = f.ad_task_id)
 WHERE ad_task_id IS NOT NULL AND action = 'T';

UPDATE AD_MENU_TRL
   SET NAME =
          (SELECT ft.NAME
             FROM AD_TASK_TRL ft, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_task_id = ft.ad_task_id
              AND AD_MENU_TRL.AD_LANGUAGE = ft.AD_LANGUAGE),
       description =
          (SELECT ft.description
             FROM AD_TASK_TRL ft, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_task_id = ft.ad_task_id
              AND AD_MENU_TRL.AD_LANGUAGE = ft.AD_LANGUAGE),
       istranslated =
          (SELECT ft.istranslated
             FROM AD_TASK_TRL ft, AD_MENU m
            WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
              AND m.ad_task_id = ft.ad_task_id
              AND AD_MENU_TRL.AD_LANGUAGE = ft.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_TASK_TRL ft, AD_MENU m
           WHERE AD_MENU_TRL.ad_menu_id = m.ad_menu_id
             AND m.ad_task_id = ft.ad_task_id
             AND AD_MENU_TRL.AD_LANGUAGE = ft.AD_LANGUAGE
             AND m.ad_task_id IS NOT NULL
             AND action = 'T');

UPDATE AD_COLUMN
   SET NAME = 
       (SELECT e.NAME
            FROM AD_ELEMENT e
           WHERE AD_COLUMN.ad_element_id = e.ad_element_id),
       description = 
       (SELECT e.description
            FROM AD_ELEMENT e
           WHERE AD_COLUMN.ad_element_id = e.ad_element_id),
       HELP = 
       (SELECT e.HELP
            FROM AD_ELEMENT e
           WHERE AD_COLUMN.ad_element_id = e.ad_element_id)
 WHERE EXISTS (SELECT 1
                 FROM AD_ELEMENT e
                WHERE AD_COLUMN.ad_element_id = e.ad_element_id AND AD_COLUMN.NAME <> e.NAME);

UPDATE AD_COLUMN_TRL
   SET NAME =
          (SELECT e.NAME
             FROM AD_COLUMN c INNER JOIN AD_ELEMENT_TRL e
                  ON (c.ad_element_id = e.ad_element_id)
            WHERE AD_COLUMN_TRL.ad_column_id = c.ad_column_id
              AND AD_COLUMN_TRL.AD_LANGUAGE = e.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_COLUMN c INNER JOIN AD_ELEMENT_TRL e
                 ON (c.ad_element_id = e.ad_element_id)
           WHERE AD_COLUMN_TRL.ad_column_id = c.ad_column_id
             AND AD_COLUMN_TRL.AD_LANGUAGE = e.AD_LANGUAGE
             AND AD_COLUMN_TRL.NAME <> e.NAME);

UPDATE AD_TABLE
   SET NAME = 
        (SELECT e.NAME
            FROM AD_ELEMENT e
           WHERE AD_TABLE.tablename || '_ID' = e.columnname),
       description = 
        (SELECT e.description
            FROM AD_ELEMENT e
           WHERE AD_TABLE.tablename || '_ID' = e.columnname)
 WHERE EXISTS (SELECT 1
                 FROM AD_ELEMENT e
                WHERE AD_TABLE.tablename || '_ID' = e.columnname AND AD_TABLE.NAME <> e.NAME);

UPDATE AD_TABLE_TRL
   SET NAME =
          (SELECT e.NAME
             FROM AD_TABLE t INNER JOIN AD_ELEMENT ex
                  ON (t.tablename || '_ID' = ex.columnname)
                  INNER JOIN AD_ELEMENT_TRL e
                  ON (ex.ad_element_id = e.ad_element_id)
            WHERE AD_TABLE_TRL.ad_table_id = t.ad_table_id
              AND AD_TABLE_TRL.AD_LANGUAGE = e.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_TABLE t INNER JOIN AD_ELEMENT ex
                 ON (t.tablename || '_ID' = ex.columnname)
                 INNER JOIN AD_ELEMENT_TRL e
                 ON (ex.ad_element_id = e.ad_element_id)
           WHERE AD_TABLE_TRL.ad_table_id = t.ad_table_id
             AND AD_TABLE_TRL.AD_LANGUAGE = e.AD_LANGUAGE
             AND AD_TABLE_TRL.NAME <> e.NAME);

UPDATE AD_TABLE
     SET NAME =
          (SELECT e.NAME || ' Trl'
             FROM AD_ELEMENT e
            WHERE SUBSTR (AD_TABLE.tablename, 1, LENGTH (AD_TABLE.tablename) - 4) || '_ID' =
                                                                  e.columnname),
       description =
          (SELECT e.description
             FROM AD_ELEMENT e
            WHERE SUBSTR (AD_TABLE.tablename, 1, LENGTH (AD_TABLE.tablename) - 4) || '_ID' =
                                                                  e.columnname)
 WHERE tablename LIKE '%_Trl'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e
           WHERE SUBSTR (AD_TABLE.tablename, 1, LENGTH (AD_TABLE.tablename) - 4) || '_ID' =
                                                                  e.columnname
             AND AD_TABLE.NAME <> e.NAME);

UPDATE AD_TABLE_TRL
   SET NAME =
          (SELECT e.NAME || ' **'
             FROM AD_TABLE t INNER JOIN AD_ELEMENT ex
                  ON (SUBSTR (t.tablename, 1, LENGTH (t.tablename) - 4)
                      || '_ID' = ex.columnname
                     )
                  INNER JOIN AD_ELEMENT_TRL e
                  ON (ex.ad_element_id = e.ad_element_id)
            WHERE AD_TABLE_TRL.ad_table_id = t.ad_table_id
              AND AD_TABLE_TRL.AD_LANGUAGE = e.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_TABLE t INNER JOIN AD_ELEMENT ex
                 ON (SUBSTR (t.tablename, 1, LENGTH (t.tablename) - 4)
                     || '_ID' = ex.columnname
                    )
                 INNER JOIN AD_ELEMENT_TRL e
                 ON (ex.ad_element_id = e.ad_element_id)
           WHERE AD_TABLE_TRL.ad_table_id = t.ad_table_id
             AND AD_TABLE_TRL.AD_LANGUAGE = e.AD_LANGUAGE
             AND t.tablename LIKE '%_Trl'
             AND AD_TABLE_TRL.NAME <> e.NAME);

COMMIT ;
CREATE OR REPLACE FUNCTION update_sequences() RETURNS void as $func$
-- TODO: Currently not inserting new sequences
DECLARE
   cmdsys           VARCHAR (1000);
   cmdnosys         VARCHAR (1000);
   cmdseq           VARCHAR (1000);
   cmdupd           VARCHAR (1000);
   currentnextsys   NUMERIC (10);
   currentnext      NUMERIC (10);
   currentseqsys    NUMERIC (10);
   currentseq       NUMERIC (10);
   ok               BOOLEAN;
   r                RECORD;
BEGIN

   FOR r IN (SELECT   tablename
                 FROM AD_TABLE t
                WHERE EXISTS (
                         SELECT 1
                           FROM AD_COLUMN c
                          WHERE t.ad_table_id = c.ad_table_id
                            AND c.columnname = t.tablename || '_ID')
             ORDER BY 1)
   LOOP
      cmdsys :=
            'SELECT  MAX ('
         || r.tablename
         || '_id) as currentnextsys FROM '
         || r.tablename
         || ' where '
         || r.tablename
         || '_id<1000000';

      ok := true;
      BEGIN
         EXECUTE cmdsys INTO currentnextsys;
      EXCEPTION
         WHEN OTHERS
         THEN
            ok := false;
      END;

    IF ok THEN
      IF currentnextsys IS NULL
      THEN
         currentnextsys := 0;
      END IF;

      SELECT INTO currentnextsys CASE SIGN (currentnextsys - 50000)
                     WHEN -1 THEN 50000
                     ELSE coalesce (currentnextsys + 1, 50000)
                     END;

      cmdnosys :=
            'SELECT  MAX ('
         || r.tablename
         || '_id) as currentnext FROM '
         || r.tablename
         || ' where '
         || r.tablename
         || '_id>=1000000';

      EXECUTE cmdnosys INTO currentnext;

      IF currentnext IS NULL
      THEN
         currentnext := 0;
      END IF;

      SELECT INTO currentnext CASE SIGN (currentnext - 1000000)
                     WHEN -1 THEN 1000000
                     ELSE coalesce (currentnext + 1, 1000000)
                     END ;

      cmdseq :=
            'SELECT currentnext, currentnextsys FROM AD_Sequence '
         || 'WHERE Name = '''
         || r.tablename
         || ''' AND istableid = ''Y''';

      EXECUTE cmdseq INTO currentseq, currentseqsys;

      IF currentnextsys <> currentseqsys OR currentnext <> currentseq
      THEN
         cmdupd :=
               'update ad_sequence set currentnextsys = '
            || currentnextsys
            || ', currentnext='
            || currentnext
            || ' where name='''
            || r.tablename
            || ''' and istableid=''Y''';

         EXECUTE cmdupd;
      END IF;
    END IF;

   END LOOP;
END;
$func$ LANGUAGE plpgsql;

select update_sequences();

commit;
CREATE OR REPLACE FUNCTION role_access_update() RETURNS void as $func$
DECLARE
   roleaccesslevel      VARCHAR (200);
   roleaccesslevelwin   VARCHAR (200);
   sqlins               VARCHAR (2000);
   r                    RECORD;
BEGIN
   FOR r IN (SELECT   ad_role_id, userlevel, NAME
                 FROM AD_ROLE
                WHERE ismanual = 'N'
             ORDER BY ad_role_id)
   LOOP
      DELETE FROM AD_WINDOW_ACCESS
            WHERE ad_role_id = r.ad_role_id;

      DELETE FROM AD_PROCESS_ACCESS
            WHERE ad_role_id = r.ad_role_id;

      DELETE FROM AD_FORM_ACCESS
            WHERE ad_role_id = r.ad_role_id;

      DELETE FROM AD_WORKFLOW_ACCESS
            WHERE ad_role_id = r.ad_role_id;

      IF r.userlevel = 'S  '                                        -- system
      THEN
         roleaccesslevel := '(''4'',''7'',''6'')';
         roleaccesslevelwin := roleaccesslevel;
      ELSIF r.userlevel = ' C '                                      -- client
      THEN
         roleaccesslevel := '(''7'',''6'',''3'',''2'')';
         roleaccesslevelwin := roleaccesslevel;
      ELSIF r.userlevel = ' CO'                                -- client + org
      THEN
         roleaccesslevel := '(''7'',''6'',''3'',''2'',''1'')';
         roleaccesslevelwin := roleaccesslevel;
      ELSE                                                    -- org or others
         roleaccesslevel := '(''3'',''1'',''7'')';
         roleaccesslevelwin :=
                        roleaccesslevel || ' AND w.Name NOT LIKE ''%(all)%''';
      END IF;

      sqlins :=
            'INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID, '
         || r.ad_role_id
         || ',0,0,''Y'', Current_Timestamp,0, Current_Timestamp,0,''Y'' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID)AND tt.AccessLevel IN '
         || roleaccesslevelwin;

      EXECUTE sqlins;

      sqlins :=
            'INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID, '
         || r.ad_role_id
         || ',0,0,''Y'', Current_Timestamp,0, Current_Timestamp,0,''Y'' FROM AD_Process p WHERE AccessLevel IN '
         || roleaccesslevel;

      EXECUTE sqlins;

      sqlins :=
            'INSERT INTO AD_Form_Access (AD_Form_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT f.AD_Form_ID, '
         || r.ad_role_id
         || ',0,0,''Y'', Current_Timestamp,0, Current_Timestamp,0,''Y'' FROM AD_Form f WHERE AccessLevel IN '
         || roleaccesslevel;

      EXECUTE sqlins;

      sqlins :=
            'INSERT INTO AD_WorkFlow_Access (AD_WorkFlow_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT w.AD_WorkFlow_ID, '
         || r.ad_role_id
         || ',0,0,''Y'', Current_Timestamp,0, Current_Timestamp,0,''Y'' FROM AD_WorkFlow w WHERE AccessLevel IN '
         || roleaccesslevel;

      EXECUTE sqlins;
   END LOOP;

END;
$func$ LANGUAGE plpgsql;

select role_access_update();

commit;
