UPDATE AD_Column c
SET IsUpdateable = (SELECT IsUpdateable FROM AD_Field f 
                    WHERE c.AD_Column_ID=f.AD_Column_ID
-- needs to be single record
                    AND ROWNUM=1)
-- required, if source table has nulls
WHERE EXISTS        (SELECT * FROM AD_Field f 
                    WHERE c.AD_Column_ID=f.AD_Column_ID)
;
