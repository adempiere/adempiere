INSERT INTO ad_element_trl
            (ad_element_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, printname, istranslated
            )
     VALUES (50040, 'es_MX', 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:31:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/13/2007 23:31:53', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Copia Columnas desde Tabla', 'Copia Columnas desde Tabla', 'Y'
            );

INSERT INTO ad_process_trl
            (ad_process_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description,
             istranslated
            )
     VALUES (50011, 'es_MX', 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:35:07', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/13/2007 23:35:40', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Copiar Columnas desde Tabla',
             'Crear columnas del diccionario para una tabla tomando otra como base',
             'N'
            );

INSERT INTO ad_process_para_trl
            (ad_process_para_id, ad_language, ad_client_id, ad_org_id,
             isactive, created,
             createdby, updated,
             updatedby, NAME, description,
             HELP,
             istranslated
            )
     VALUES (50005, 'es_MX', 0, 0,
             'Y', TO_DATE ('02/13/2007 23:39:09', 'MM/DD/YYYY HH24:MI:SS'),
             100, TO_DATE ('02/13/2007 23:43:32', 'MM/DD/YYYY HH24:MI:SS'),
             100, 'Tabla', 'Información de tabla de la base de datos',
             'La tabla de la base de datos provee información sobre definición de la tabla',
             'Y'
            );

INSERT INTO ad_column_trl
            (ad_column_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, istranslated
            )
     VALUES (50183, 'es_MX', 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:46:03', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/13/2007 23:46:22', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Copiar Columnas de Tabla', 'Y'
            );

INSERT INTO ad_field_trl
            (ad_field_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, istranslated
            )
     VALUES (50157, 'es_MX', 0, 0, 'Y',
             TO_DATE ('02/13/2007 23:56:24', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/13/2007 23:57:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Copiar Columnas desde Tabla', 'Copiar Columnas desde Tabla', 'Y'
            );

COMMIT ;
