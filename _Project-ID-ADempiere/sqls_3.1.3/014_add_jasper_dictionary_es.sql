INSERT INTO ad_element_trl
            (ad_element_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, printname, istranslated
            )
     VALUES (50039, 'es_MX', 0, 0, 'Y',
             TO_DATE ('01/24/2007 00:55:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:56:58', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Reporte Jasper', 'Reporte Jasper', 'Y'
            );

INSERT INTO ad_column_trl
            (ad_column_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, istranslated
            )
     VALUES (50182, 'es_MX', 0, 0, 'Y',
             TO_DATE ('01/24/2007 00:55:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:57:23', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Reporte Jasper', 'Y'
            );

INSERT INTO ad_field_trl
            (ad_field_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description,
             istranslated
            )
     VALUES (50156, 'es_MX', 0, 0, 'Y',
             TO_DATE ('01/24/2007 01:00:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 01:01:49', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Reporte Jasper',
             'En esta columna se almacena el nombre de archivo del reporte Jasper',
             'Y'
            );

COMMIT ;
