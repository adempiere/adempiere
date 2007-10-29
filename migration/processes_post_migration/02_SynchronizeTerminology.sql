-- Synchronize Terminology

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

UPDATE AD_COLUMN c
   SET ad_element_id = (SELECT ad_element_id
                          FROM AD_ELEMENT e
                         WHERE UPPER (c.columnname) = UPPER (e.columnname))
 WHERE ad_element_id IS NULL;

DELETE      AD_ELEMENT_TRL
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

DELETE      AD_ELEMENT e
      WHERE NOT EXISTS (SELECT 1
                          FROM AD_COLUMN c
                         WHERE UPPER (e.columnname) = UPPER (c.columnname))
        AND NOT EXISTS (SELECT 1
                          FROM AD_PROCESS_PARA p
                         WHERE UPPER (e.columnname) = UPPER (p.columnname));

UPDATE AD_COLUMN c
   SET (columnname, NAME, description, HELP) =
          (SELECT columnname, NAME, description, HELP
             FROM AD_ELEMENT e
            WHERE c.ad_element_id = e.ad_element_id),
       updated = SYSDATE
 WHERE EXISTS (
          SELECT 1
            FROM AD_ELEMENT e
           WHERE c.ad_element_id = e.ad_element_id
             AND (   c.columnname <> e.columnname
                  OR c.NAME <> e.NAME
                  OR NVL (c.description, ' ') <> NVL (e.description, ' ')
                  OR NVL (c.HELP, ' ') <> NVL (e.HELP, ' ')
                 ));

UPDATE AD_FIELD f
   SET (NAME, description, HELP) =
          (SELECT e.NAME, e.description, e.HELP
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id),
       updated = SYSDATE
 WHERE f.iscentrallymaintained = 'Y'
   AND f.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e, AD_COLUMN c
           WHERE f.ad_column_id = c.ad_column_id
             AND c.ad_element_id = e.ad_element_id
             AND c.ad_process_id IS NULL
             AND (   f.NAME <> e.NAME
                  OR NVL (f.description, ' ') <> NVL (e.description, ' ')
                  OR NVL (f.HELP, ' ') <> NVL (e.HELP, ' ')
                 ));

UPDATE AD_FIELD_TRL trl
   SET NAME =
          (SELECT e.NAME
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id),
       description =
          (SELECT e.description
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id),
       HELP =
          (SELECT e.HELP
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id),
       istranslated =
          (SELECT e.istranslated
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id),
       updated = SYSDATE
 WHERE EXISTS (
          SELECT 1
            FROM AD_FIELD f, AD_ELEMENT_TRL e, AD_COLUMN c
           WHERE trl.ad_field_id = f.ad_field_id
             AND f.ad_column_id = c.ad_column_id
             AND c.ad_element_id = e.ad_element_id
             AND c.ad_process_id IS NULL
             AND trl.AD_LANGUAGE = e.AD_LANGUAGE
             AND f.iscentrallymaintained = 'Y'
             AND f.isactive = 'Y'
             AND (   trl.NAME <> e.NAME
                  OR NVL (trl.description, ' ') <> NVL (e.description, ' ')
                  OR NVL (trl.HELP, ' ') <> NVL (e.HELP, ' ')
                 ));

UPDATE AD_FIELD f
   SET NAME =
          (SELECT e.po_name
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id),
       description =
          (SELECT e.po_description
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id),
       HELP =
          (SELECT e.po_help
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id),
       updated = SYSDATE
 WHERE f.iscentrallymaintained = 'Y'
   AND f.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e, AD_COLUMN c
           WHERE f.ad_column_id = c.ad_column_id
             AND c.ad_element_id = e.ad_element_id
             AND c.ad_process_id IS NULL
             AND (   f.NAME <> e.po_name
                  OR NVL (f.description, ' ') <> NVL (e.po_description, ' ')
                  OR NVL (f.HELP, ' ') <> NVL (e.po_help, ' ')
                 )
             AND e.po_name IS NOT NULL)
   AND EXISTS (
          SELECT 1
            FROM AD_TAB t, AD_WINDOW w
           WHERE f.ad_tab_id = t.ad_tab_id
             AND t.ad_window_id = w.ad_window_id
             AND w.issotrx = 'N');

UPDATE AD_FIELD_TRL trl
   SET NAME =
          (SELECT e.po_name
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id),
       description =
          (SELECT e.po_description
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id),
       HELP =
          (SELECT e.po_help
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id),
       istranslated =
          (SELECT e.istranslated
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_FIELD f
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id),
       updated = SYSDATE
 WHERE EXISTS (
          SELECT 1
            FROM AD_FIELD f, AD_ELEMENT_TRL e, AD_COLUMN c
           WHERE trl.ad_field_id = f.ad_field_id
             AND f.ad_column_id = c.ad_column_id
             AND c.ad_element_id = e.ad_element_id
             AND c.ad_process_id IS NULL
             AND trl.AD_LANGUAGE = e.AD_LANGUAGE
             AND f.iscentrallymaintained = 'Y'
             AND f.isactive = 'Y'
             AND (   trl.NAME <> e.po_name
                  OR NVL (trl.description, ' ') <> NVL (e.po_description, ' ')
                  OR NVL (trl.HELP, ' ') <> NVL (e.po_help, ' ')
                 )
             AND e.po_name IS NOT NULL)
   AND EXISTS (
          SELECT 1
            FROM AD_FIELD f, AD_TAB t, AD_WINDOW w
           WHERE trl.ad_field_id = f.ad_field_id
             AND f.ad_tab_id = t.ad_tab_id
             AND t.ad_window_id = w.ad_window_id
             AND w.issotrx = 'N');

UPDATE AD_FIELD f
   SET NAME =
          (SELECT p.NAME
             FROM AD_PROCESS p, AD_COLUMN c
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id),
       description =
          (SELECT p.description
             FROM AD_PROCESS p, AD_COLUMN c
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id),
       HELP =
          (SELECT p.HELP
             FROM AD_PROCESS p, AD_COLUMN c
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id),
       updated = SYSDATE
 WHERE f.iscentrallymaintained = 'Y'
   AND f.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_PROCESS p, AD_COLUMN c
           WHERE c.ad_process_id = p.ad_process_id
             AND f.ad_column_id = c.ad_column_id
             AND (   f.NAME <> p.NAME
                  OR NVL (f.description, ' ') <> NVL (p.description, ' ')
                  OR NVL (f.HELP, ' ') <> NVL (p.HELP, ' ')
                 ));

UPDATE AD_FIELD_TRL trl
   SET NAME =
          (SELECT p.NAME
             FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id
              AND p.AD_LANGUAGE = trl.AD_LANGUAGE),
       description =
          (SELECT p.description
             FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id
              AND p.AD_LANGUAGE = trl.AD_LANGUAGE),
       HELP =
          (SELECT p.HELP
             FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id
              AND p.AD_LANGUAGE = trl.AD_LANGUAGE),
       istranslated =
          (SELECT p.istranslated
             FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
            WHERE p.ad_process_id = c.ad_process_id
              AND c.ad_column_id = f.ad_column_id
              AND f.ad_field_id = trl.ad_field_id
              AND p.AD_LANGUAGE = trl.AD_LANGUAGE),
       updated = SYSDATE
 WHERE EXISTS (
          SELECT 1
            FROM AD_PROCESS_TRL p, AD_COLUMN c, AD_FIELD f
           WHERE c.ad_process_id = p.ad_process_id
             AND f.ad_column_id = c.ad_column_id
             AND f.ad_field_id = trl.ad_field_id
             AND p.AD_LANGUAGE = trl.AD_LANGUAGE
             AND f.iscentrallymaintained = 'Y'
             AND f.isactive = 'Y'
             AND (   trl.NAME <> p.NAME
                  OR NVL (trl.description, ' ') <> NVL (p.description, ' ')
                  OR NVL (trl.HELP, ' ') <> NVL (p.HELP, ' ')
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

UPDATE AD_PROCESS_PARA f
   SET columnname = (SELECT e.columnname
                       FROM AD_ELEMENT e
                      -- WHERE UPPER (e.columnname) = UPPER (f.columnname))
                      WHERE e.columnname = f.columnname) -- Temporary patch Fixed Assets are broking it
 WHERE f.iscentrallymaintained = 'Y'
   AND f.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e
           WHERE UPPER (e.columnname) = UPPER (f.columnname)
             AND e.columnname <> f.columnname);

UPDATE AD_PROCESS_PARA p
   SET iscentrallymaintained = 'N'
 WHERE iscentrallymaintained <> 'N'
   AND NOT EXISTS (SELECT 1
                     FROM AD_ELEMENT e
                    WHERE p.columnname = e.columnname);

UPDATE AD_PROCESS_PARA f
   SET NAME = (SELECT e.NAME
                 FROM AD_ELEMENT e
                WHERE e.columnname = f.columnname),
       description = (SELECT e.description
                        FROM AD_ELEMENT e
                       WHERE e.columnname = f.columnname),
       HELP = (SELECT e.HELP
                 FROM AD_ELEMENT e
                WHERE e.columnname = f.columnname),
       updated = SYSDATE
 WHERE f.iscentrallymaintained = 'Y'
   AND f.isactive = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e
           WHERE e.columnname = f.columnname
             AND (   f.NAME <> e.NAME
                  OR NVL (f.description, ' ') <> NVL (e.description, ' ')
                  OR NVL (f.HELP, ' ') <> NVL (e.HELP, ' ')
                 ));

UPDATE AD_PROCESS_PARA_TRL trl
   SET NAME =
          (SELECT et.NAME
             FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
            WHERE et.AD_LANGUAGE = trl.AD_LANGUAGE
              AND et.ad_element_id = e.ad_element_id
              AND e.columnname = f.columnname
              AND f.ad_process_para_id = trl.ad_process_para_id),
       description =
          (SELECT et.description
             FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
            WHERE et.AD_LANGUAGE = trl.AD_LANGUAGE
              AND et.ad_element_id = e.ad_element_id
              AND e.columnname = f.columnname
              AND f.ad_process_para_id = trl.ad_process_para_id),
       HELP =
          (SELECT et.HELP
             FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
            WHERE et.AD_LANGUAGE = trl.AD_LANGUAGE
              AND et.ad_element_id = e.ad_element_id
              AND e.columnname = f.columnname
              AND f.ad_process_para_id = trl.ad_process_para_id),
       istranslated =
          (SELECT et.istranslated
             FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
            WHERE et.AD_LANGUAGE = trl.AD_LANGUAGE
              AND et.ad_element_id = e.ad_element_id
              AND e.columnname = f.columnname
              AND f.ad_process_para_id = trl.ad_process_para_id),
       updated = SYSDATE
 WHERE EXISTS (
          SELECT 1
            FROM AD_ELEMENT_TRL et, AD_ELEMENT e, AD_PROCESS_PARA f
           WHERE et.AD_LANGUAGE = trl.AD_LANGUAGE
             AND et.ad_element_id = e.ad_element_id
             AND e.columnname = f.columnname
             AND f.ad_process_para_id = trl.ad_process_para_id
             AND f.iscentrallymaintained = 'Y'
             AND f.isactive = 'Y'
             AND (   trl.NAME <> et.NAME
                  OR NVL (trl.description, ' ') <> NVL (et.description, ' ')
                  OR NVL (trl.HELP, ' ') <> NVL (et.HELP, ' ')
                 ));

UPDATE AD_WF_NODE n
   SET NAME = (SELECT w.NAME
                 FROM AD_WINDOW w
                WHERE w.ad_window_id = n.ad_window_id),
       description = (SELECT w.description
                        FROM AD_WINDOW w
                       WHERE w.ad_window_id = n.ad_window_id),
       HELP = (SELECT w.HELP
                 FROM AD_WINDOW w
                WHERE w.ad_window_id = n.ad_window_id)
 WHERE n.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_WINDOW w
           WHERE w.ad_window_id = n.ad_window_id
             AND (   w.NAME <> n.NAME
                  OR NVL (w.description, ' ') <> NVL (n.description, ' ')
                  OR NVL (w.HELP, ' ') <> NVL (n.HELP, ' ')
                 ));

UPDATE AD_WF_NODE_TRL trl
   SET NAME =
          (SELECT t.NAME
             FROM AD_WINDOW_TRL t, AD_WF_NODE n
            WHERE trl.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_window_id = t.ad_window_id
              AND trl.AD_LANGUAGE = t.AD_LANGUAGE),
       description =
          (SELECT t.description
             FROM AD_WINDOW_TRL t, AD_WF_NODE n
            WHERE trl.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_window_id = t.ad_window_id
              AND trl.AD_LANGUAGE = t.AD_LANGUAGE),
       HELP =
          (SELECT t.HELP
             FROM AD_WINDOW_TRL t, AD_WF_NODE n
            WHERE trl.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_window_id = t.ad_window_id
              AND trl.AD_LANGUAGE = t.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_WINDOW_TRL t, AD_WF_NODE n
           WHERE trl.ad_wf_node_id = n.ad_wf_node_id
             AND n.ad_window_id = t.ad_window_id
             AND trl.AD_LANGUAGE = t.AD_LANGUAGE
             AND n.iscentrallymaintained = 'Y'
             AND n.isactive = 'Y'
             AND (   trl.NAME <> t.NAME
                  OR NVL (trl.description, ' ') <> NVL (t.description, ' ')
                  OR NVL (trl.HELP, ' ') <> NVL (t.HELP, ' ')
                 ));

UPDATE AD_WF_NODE n
   SET (NAME, description, HELP) = (SELECT f.NAME, f.description, f.HELP
                                      FROM AD_FORM f
                                     WHERE f.ad_form_id = n.ad_form_id)
 WHERE n.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_FORM f
           WHERE f.ad_form_id = n.ad_form_id
             AND (   f.NAME <> n.NAME
                  OR NVL (f.description, ' ') <> NVL (n.description, ' ')
                  OR NVL (f.HELP, ' ') <> NVL (n.HELP, ' ')
                 ));

UPDATE AD_WF_NODE_TRL trl
   SET (NAME, description, HELP) =
          (SELECT t.NAME, t.description, t.HELP
             FROM AD_FORM_TRL t, AD_WF_NODE n
            WHERE trl.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_form_id = t.ad_form_id
              AND trl.AD_LANGUAGE = t.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_FORM_TRL t, AD_WF_NODE n
           WHERE trl.ad_wf_node_id = n.ad_wf_node_id
             AND n.ad_form_id = t.ad_form_id
             AND trl.AD_LANGUAGE = t.AD_LANGUAGE
             AND n.iscentrallymaintained = 'Y'
             AND n.isactive = 'Y'
             AND (   trl.NAME <> t.NAME
                  OR NVL (trl.description, ' ') <> NVL (t.description, ' ')
                  OR NVL (trl.HELP, ' ') <> NVL (t.HELP, ' ')
                 ));

UPDATE AD_WF_NODE n
   SET (NAME, description, HELP) = (SELECT f.NAME, f.description, f.HELP
                                      FROM AD_PROCESS f
                                     WHERE f.ad_process_id = n.ad_process_id)
 WHERE n.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_PROCESS f
           WHERE f.ad_process_id = n.ad_process_id
             AND (   f.NAME <> n.NAME
                  OR NVL (f.description, ' ') <> NVL (n.description, ' ')
                  OR NVL (f.HELP, ' ') <> NVL (n.HELP, ' ')
                 ));

UPDATE AD_WF_NODE_TRL trl
   SET (NAME, description, HELP) =
          (SELECT t.NAME, t.description, t.HELP
             FROM AD_PROCESS_TRL t, AD_WF_NODE n
            WHERE trl.ad_wf_node_id = n.ad_wf_node_id
              AND n.ad_process_id = t.ad_process_id
              AND trl.AD_LANGUAGE = t.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_PROCESS_TRL t, AD_WF_NODE n
           WHERE trl.ad_wf_node_id = n.ad_wf_node_id
             AND n.ad_process_id = t.ad_process_id
             AND trl.AD_LANGUAGE = t.AD_LANGUAGE
             AND n.iscentrallymaintained = 'Y'
             AND n.isactive = 'Y'
             AND (   trl.NAME <> t.NAME
                  OR NVL (trl.description, ' ') <> NVL (t.description, ' ')
                  OR NVL (trl.HELP, ' ') <> NVL (t.HELP, ' ')
                 ));

UPDATE AD_PRINTFORMATITEM pfi
   SET NAME =
          (SELECT e.NAME
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = pfi.ad_column_id)
 WHERE pfi.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e, AD_COLUMN c
           WHERE e.ad_element_id = c.ad_element_id
             AND c.ad_column_id = pfi.ad_column_id
             AND e.NAME <> pfi.NAME)
   AND EXISTS (
          SELECT 1
            FROM AD_CLIENT
           WHERE ad_client_id = pfi.ad_client_id
             AND ismultilingualdocument = 'Y');

UPDATE AD_PRINTFORMATITEM pfi
   SET printname =
          (SELECT e.printname
             FROM AD_ELEMENT e, AD_COLUMN c
            WHERE e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = pfi.ad_column_id)
 WHERE pfi.iscentrallymaintained = 'Y'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e, AD_COLUMN c, AD_PRINTFORMAT pf
           WHERE e.ad_element_id = c.ad_element_id
             AND c.ad_column_id = pfi.ad_column_id
             AND LENGTH (pfi.printname) > 0
             AND e.printname <> pfi.printname
             AND pf.ad_printformat_id = pfi.ad_printformat_id
             AND pf.isform = 'N'
             AND istablebased = 'Y')
   AND EXISTS (
          SELECT 1
            FROM AD_CLIENT
           WHERE ad_client_id = pfi.ad_client_id
             AND ismultilingualdocument = 'Y');

UPDATE AD_PRINTFORMATITEM_TRL trl
   SET printname =
          (SELECT e.printname
             FROM AD_ELEMENT_TRL e, AD_COLUMN c, AD_PRINTFORMATITEM pfi
            WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
              AND e.ad_element_id = c.ad_element_id
              AND c.ad_column_id = pfi.ad_column_id
              AND pfi.ad_printformatitem_id = trl.ad_printformatitem_id)
 WHERE EXISTS (
          SELECT 1
            FROM AD_ELEMENT_TRL e,
                 AD_COLUMN c,
                 AD_PRINTFORMATITEM pfi,
                 AD_PRINTFORMAT pf
           WHERE e.AD_LANGUAGE = trl.AD_LANGUAGE
             AND e.ad_element_id = c.ad_element_id
             AND c.ad_column_id = pfi.ad_column_id
             AND pfi.ad_printformatitem_id = trl.ad_printformatitem_id
             AND pfi.iscentrallymaintained = 'Y'
             AND LENGTH (pfi.printname) > 0
             AND (e.printname <> trl.printname OR trl.printname IS NULL)
             AND pf.ad_printformat_id = pfi.ad_printformat_id
             AND pf.isform = 'N'
             AND istablebased = 'Y')
   AND EXISTS (
          SELECT 1
            FROM AD_CLIENT
           WHERE ad_client_id = trl.ad_client_id
             AND ismultilingualdocument = 'Y');

UPDATE AD_PRINTFORMATITEM_TRL trl
   SET printname =
                (SELECT pfi.printname
                   FROM AD_PRINTFORMATITEM pfi
                  WHERE pfi.ad_printformatitem_id = trl.ad_printformatitem_id)
 WHERE EXISTS (
          SELECT 1
            FROM AD_PRINTFORMATITEM pfi, AD_PRINTFORMAT pf
           WHERE pfi.ad_printformatitem_id = trl.ad_printformatitem_id
             AND pfi.iscentrallymaintained = 'Y'
             AND LENGTH (pfi.printname) > 0
             AND pfi.printname <> trl.printname
             AND pf.ad_printformat_id = pfi.ad_printformat_id
             AND pf.isform = 'N'
             AND pf.istablebased = 'Y')
   AND EXISTS (
          SELECT 1
            FROM AD_CLIENT
           WHERE ad_client_id = trl.ad_client_id
             AND ismultilingualdocument = 'N');

UPDATE AD_PRINTFORMATITEM_TRL trl
   SET printname = NULL
 WHERE printname IS NOT NULL
   AND EXISTS (
          SELECT 1
            FROM AD_PRINTFORMATITEM pfi
           WHERE pfi.ad_printformatitem_id = trl.ad_printformatitem_id
             AND pfi.iscentrallymaintained = 'Y'
             AND (LENGTH (pfi.printname) = 0 OR pfi.printname IS NULL));

UPDATE AD_MENU m
   SET NAME = (SELECT NAME
                 FROM AD_WINDOW w
                WHERE m.ad_window_id = w.ad_window_id),
       description = (SELECT description
                        FROM AD_WINDOW w
                       WHERE m.ad_window_id = w.ad_window_id)
 WHERE ad_window_id IS NOT NULL AND action = 'W';

UPDATE AD_MENU_TRL mt
   SET NAME =
          (SELECT wt.NAME
             FROM AD_WINDOW_TRL wt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_window_id = wt.ad_window_id
              AND mt.AD_LANGUAGE = wt.AD_LANGUAGE),
       description =
          (SELECT wt.description
             FROM AD_WINDOW_TRL wt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_window_id = wt.ad_window_id
              AND mt.AD_LANGUAGE = wt.AD_LANGUAGE),
       istranslated =
          (SELECT wt.istranslated
             FROM AD_WINDOW_TRL wt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_window_id = wt.ad_window_id
              AND mt.AD_LANGUAGE = wt.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_WINDOW_TRL wt, AD_MENU m
           WHERE mt.ad_menu_id = m.ad_menu_id
             AND m.ad_window_id = wt.ad_window_id
             AND mt.AD_LANGUAGE = wt.AD_LANGUAGE
             AND m.ad_window_id IS NOT NULL
             AND m.action = 'W');

UPDATE AD_MENU m
   SET NAME = (SELECT p.NAME
                 FROM AD_PROCESS p
                WHERE m.ad_process_id = p.ad_process_id),
       description = (SELECT p.description
                        FROM AD_PROCESS p
                       WHERE m.ad_process_id = p.ad_process_id)
 WHERE m.ad_process_id IS NOT NULL AND m.action IN ('R', 'P');

UPDATE AD_MENU_TRL mt
   SET NAME =
          (SELECT pt.NAME
             FROM AD_PROCESS_TRL pt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_process_id = pt.ad_process_id
              AND mt.AD_LANGUAGE = pt.AD_LANGUAGE),
       description =
          (SELECT pt.description
             FROM AD_PROCESS_TRL pt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_process_id = pt.ad_process_id
              AND mt.AD_LANGUAGE = pt.AD_LANGUAGE),
       istranslated =
          (SELECT pt.istranslated
             FROM AD_PROCESS_TRL pt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_process_id = pt.ad_process_id
              AND mt.AD_LANGUAGE = pt.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_PROCESS_TRL pt, AD_MENU m
           WHERE mt.ad_menu_id = m.ad_menu_id
             AND m.ad_process_id = pt.ad_process_id
             AND mt.AD_LANGUAGE = pt.AD_LANGUAGE
             AND m.ad_process_id IS NOT NULL
             AND action IN ('R', 'P'));

UPDATE AD_MENU m
   SET NAME = (SELECT NAME
                 FROM AD_FORM f
                WHERE m.ad_form_id = f.ad_form_id),
       description = (SELECT description
                        FROM AD_FORM f
                       WHERE m.ad_form_id = f.ad_form_id)
 WHERE ad_form_id IS NOT NULL AND action = 'X';

UPDATE AD_MENU_TRL mt
   SET NAME =
          (SELECT ft.NAME
             FROM AD_FORM_TRL ft, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_form_id = ft.ad_form_id
              AND mt.AD_LANGUAGE = ft.AD_LANGUAGE),
       description =
          (SELECT ft.description
             FROM AD_FORM_TRL ft, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_form_id = ft.ad_form_id
              AND mt.AD_LANGUAGE = ft.AD_LANGUAGE),
       istranslated =
          (SELECT ft.istranslated
             FROM AD_FORM_TRL ft, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_form_id = ft.ad_form_id
              AND mt.AD_LANGUAGE = ft.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_FORM_TRL ft, AD_MENU m
           WHERE mt.ad_menu_id = m.ad_menu_id
             AND m.ad_form_id = ft.ad_form_id
             AND mt.AD_LANGUAGE = ft.AD_LANGUAGE
             AND m.ad_form_id IS NOT NULL
             AND action = 'X');

UPDATE AD_MENU m
   SET NAME = (SELECT p.NAME
                 FROM AD_WORKFLOW p
                WHERE m.ad_workflow_id = p.ad_workflow_id),
       description = (SELECT p.description
                        FROM AD_WORKFLOW p
                       WHERE m.ad_workflow_id = p.ad_workflow_id)
 WHERE m.ad_workflow_id IS NOT NULL AND m.action = 'F';

UPDATE AD_MENU_TRL mt
   SET NAME =
          (SELECT pt.NAME
             FROM AD_WORKFLOW_TRL pt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_workflow_id = pt.ad_workflow_id
              AND mt.AD_LANGUAGE = pt.AD_LANGUAGE),
       description =
          (SELECT pt.description
             FROM AD_WORKFLOW_TRL pt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_workflow_id = pt.ad_workflow_id
              AND mt.AD_LANGUAGE = pt.AD_LANGUAGE),
       istranslated =
          (SELECT pt.istranslated
             FROM AD_WORKFLOW_TRL pt, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_workflow_id = pt.ad_workflow_id
              AND mt.AD_LANGUAGE = pt.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_WORKFLOW_TRL pt, AD_MENU m
           WHERE mt.ad_menu_id = m.ad_menu_id
             AND m.ad_workflow_id = pt.ad_workflow_id
             AND mt.AD_LANGUAGE = pt.AD_LANGUAGE
             AND m.ad_workflow_id IS NOT NULL
             AND action = 'F');

UPDATE AD_MENU m
   SET NAME = (SELECT NAME
                 FROM AD_TASK f
                WHERE m.ad_task_id = f.ad_task_id),
       description = (SELECT description
                        FROM AD_TASK f
                       WHERE m.ad_task_id = f.ad_task_id)
 WHERE ad_task_id IS NOT NULL AND action = 'T';

UPDATE AD_MENU_TRL mt
   SET NAME =
          (SELECT ft.NAME
             FROM AD_TASK_TRL ft, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_task_id = ft.ad_task_id
              AND mt.AD_LANGUAGE = ft.AD_LANGUAGE),
       description =
          (SELECT ft.description
             FROM AD_TASK_TRL ft, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_task_id = ft.ad_task_id
              AND mt.AD_LANGUAGE = ft.AD_LANGUAGE),
       istranslated =
          (SELECT ft.istranslated
             FROM AD_TASK_TRL ft, AD_MENU m
            WHERE mt.ad_menu_id = m.ad_menu_id
              AND m.ad_task_id = ft.ad_task_id
              AND mt.AD_LANGUAGE = ft.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_TASK_TRL ft, AD_MENU m
           WHERE mt.ad_menu_id = m.ad_menu_id
             AND m.ad_task_id = ft.ad_task_id
             AND mt.AD_LANGUAGE = ft.AD_LANGUAGE
             AND m.ad_task_id IS NOT NULL
             AND action = 'T');

UPDATE AD_COLUMN c
   SET (NAME, description, HELP) = (SELECT e.NAME, e.description, e.HELP
                                      FROM AD_ELEMENT e
                                     WHERE c.ad_element_id = e.ad_element_id)
 WHERE EXISTS (SELECT 1
                 FROM AD_ELEMENT e
                WHERE c.ad_element_id = e.ad_element_id AND c.NAME <> e.NAME);

UPDATE AD_COLUMN_TRL ct
   SET NAME =
          (SELECT e.NAME
             FROM AD_COLUMN c INNER JOIN AD_ELEMENT_TRL e
                  ON (c.ad_element_id = e.ad_element_id)
            WHERE ct.ad_column_id = c.ad_column_id
              AND ct.AD_LANGUAGE = e.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_COLUMN c INNER JOIN AD_ELEMENT_TRL e
                 ON (c.ad_element_id = e.ad_element_id)
           WHERE ct.ad_column_id = c.ad_column_id
             AND ct.AD_LANGUAGE = e.AD_LANGUAGE
             AND ct.NAME <> e.NAME);

UPDATE AD_TABLE t
   SET (NAME, description) = (SELECT e.NAME, e.description
                                FROM AD_ELEMENT e
                               WHERE t.tablename || '_ID' = e.columnname)
 WHERE EXISTS (SELECT 1
                 FROM AD_ELEMENT e
                WHERE t.tablename || '_ID' = e.columnname AND t.NAME <> e.NAME);

UPDATE AD_TABLE_TRL tt
   SET NAME =
          (SELECT e.NAME
             FROM AD_TABLE t INNER JOIN AD_ELEMENT ex
                  ON (t.tablename || '_ID' = ex.columnname)
                  INNER JOIN AD_ELEMENT_TRL e
                  ON (ex.ad_element_id = e.ad_element_id)
            WHERE tt.ad_table_id = t.ad_table_id
              AND tt.AD_LANGUAGE = e.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_TABLE t INNER JOIN AD_ELEMENT ex
                 ON (t.tablename || '_ID' = ex.columnname)
                 INNER JOIN AD_ELEMENT_TRL e
                 ON (ex.ad_element_id = e.ad_element_id)
           WHERE tt.ad_table_id = t.ad_table_id
             AND tt.AD_LANGUAGE = e.AD_LANGUAGE
             AND tt.NAME <> e.NAME);

UPDATE AD_TABLE t
   SET (NAME, description) =
          (SELECT e.NAME || ' Trl', e.description
             FROM AD_ELEMENT e
            WHERE SUBSTR (t.tablename, 1, LENGTH (t.tablename) - 4) || '_ID' =
                                                                  e.columnname)
 WHERE tablename LIKE '%_Trl'
   AND EXISTS (
          SELECT 1
            FROM AD_ELEMENT e
           WHERE SUBSTR (t.tablename, 1, LENGTH (t.tablename) - 4) || '_ID' =
                                                                  e.columnname
             AND t.NAME <> e.NAME);

UPDATE AD_TABLE_TRL tt
   SET NAME =
          (SELECT e.NAME || ' **'
             FROM AD_TABLE t INNER JOIN AD_ELEMENT ex
                  ON (SUBSTR (t.tablename, 1, LENGTH (t.tablename) - 4)
                      || '_ID' = ex.columnname
                     )
                  INNER JOIN AD_ELEMENT_TRL e
                  ON (ex.ad_element_id = e.ad_element_id)
            WHERE tt.ad_table_id = t.ad_table_id
              AND tt.AD_LANGUAGE = e.AD_LANGUAGE)
 WHERE EXISTS (
          SELECT 1
            FROM AD_TABLE t INNER JOIN AD_ELEMENT ex
                 ON (SUBSTR (t.tablename, 1, LENGTH (t.tablename) - 4)
                     || '_ID' = ex.columnname
                    )
                 INNER JOIN AD_ELEMENT_TRL e
                 ON (ex.ad_element_id = e.ad_element_id)
           WHERE tt.ad_table_id = t.ad_table_id
             AND tt.AD_LANGUAGE = e.AD_LANGUAGE
             AND t.tablename LIKE '%_Trl'
             AND tt.NAME <> e.NAME);

COMMIT ;
