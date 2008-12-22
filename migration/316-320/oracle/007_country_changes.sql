-- juddm BugFix:(Country Changes) 

--add Serbian currency
INSERT INTO C_CURRENCY
            (c_currency_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, iso_code, cursymbol,
             description, stdprecision, costingprecision, iseuro, isemumember
            )
     VALUES (347, 0, 0, 'Y', TO_DATE ('2003-08-06', 'YYYY-MM-DD'),
             0, TO_DATE ('2000-01-02', 'YYYY-MM-DD'), 0, 'RSD', 'RSD',
             'Serbian Dinar', 2, 4, 'N', 'N'
            );

-- add country Serbia
INSERT INTO C_COUNTRY
            (c_country_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, NAME, description,
             countrycode, hasregion, displaysequence, haspostal_add,
             c_currency_id, isaddresslinesreverse, isaddresslineslocalreverse
            )
     VALUES (349, 0, 0, 'Y', TO_DATE ('2003-03-09', 'YYYY-MM-DD'),
             0, TO_DATE ('2000-01-02', 'YYYY-MM-DD'), 0, 'Serbia', 'Serbia',
             'RS', 'N', '@C@, @P@', 'N',
             347, 'N', 'N'
            );

-- add country Montenegro		
INSERT INTO C_COUNTRY
            (c_country_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, NAME,
             description, countrycode, hasregion, displaysequence,
             haspostal_add, c_currency_id, isaddresslinesreverse,
             isaddresslineslocalreverse
            )
     VALUES (350, 0, 0, 'Y', TO_DATE ('2003-03-09', 'YYYY-MM-DD'),
             0, TO_DATE ('2000-01-02', 'YYYY-MM-DD'), 0, 'Montenegro',
             'Montenegro', 'ME', 'N', '@C@, @P@',
             'N', 102, 'N',
             'N'
            );

-- deactivate Yugoslavia
UPDATE C_COUNTRY
   SET isactive = 'N'
 WHERE c_country_id = 346;


-- deactivate Yugoslavia currency
UPDATE C_CURRENCY
   SET isactive = 'N'
 WHERE c_currency_id = 314;

COMMIT ;
