UPDATE ad_entitytype
   SET NAME = 'Appliactions'
 WHERE entitytype = 'A';

UPDATE ad_entitytype
   SET description =
                    'Application Dictionary Ownership ** System Maintained **'
 WHERE entitytype = 'D';

COMMIT ;
