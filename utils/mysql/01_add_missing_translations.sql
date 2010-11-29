DELIMITER $$
-- ## Create statement
CREATE PROCEDURE add_missing_translations (
  -- No parameters
)
--  RETURNS CHAR(1)
--  DETERMINISTIC
BEGIN
  DECLARE ins       VARCHAR (2000);
  DECLARE sel       VARCHAR (2000);
  DECLARE inssel    VARCHAR (4001);
  DECLARE table_id  DECIMAL (10, 0);

  -- Declare 'curTableNames_' variables to read in each record from the cursor
  DECLARE curTableNames_table_id  DECIMAL(10, 0);
  DECLARE curTableNames_tableName VARCHAR(40);
  -- Declare variables used just for cursor and loop control
  DECLARE done BOOL DEFAULT FALSE;

  -- Declare SECOND cursor
  -- Declare 'curColumns_' variables to read in each record from the second cursor
  DECLARE curColumns_ColumnName VARCHAR(30);
  -- Declare variables used just for the second cursor and loop control
  DECLARE done2 BOOL DEFAULT FALSE;

  
  -- Declare the cursor
  DECLARE curTableNames CURSOR FOR
    SELECT ad_table_id, SUBSTR (tablename, 1, LENGTH (tablename) - 4) as tablename
    FROM AD_TABLE
    WHERE tablename LIKE '%_Trl' 
     AND isactive = 'Y'
     AND isview = 'N';
  
  -- Declare SECOND cursor
  DECLARE curColumns CURSOR FOR
    SELECT col.columnname
    FROM AD_COLUMN col 
    INNER JOIN AD_TABLE tab ON (col.ad_table_id = tab.ad_table_id)
    WHERE col.ad_table_id = table_id
     AND col.istranslated = 'Y'
     AND col.isactive = 'Y'
    ORDER BY 1;

  -- Declare 'handlers' for exceptions
  DECLARE
    CONTINUE HANDLER FOR SQLSTATE '02000' SET done = TRUE;
  
    
  OPEN curTableNames;
  myLoop: LOOP
    FETCH curTableNames INTO curTableNames_table_id, curTableNames_tableName;
    
    IF done THEN
      CLOSE curTableNames;
      LEAVE myLoop;
    END IF;
    
      SET ins =
            'INSERT INTO '
         || curTableNames_tableName
         || '_TRL ('
         || 'ad_language, ad_client_id, ad_org_id, created, createdby, updated, updatedby, isactive, istranslated,'
         || curTableNames_tableName
         || '_id';
      SET sel =
            'SELECT l.ad_language, t.ad_client_id, t.ad_org_id, t.created, t.createdby, t.updated, t.updatedby, t.isactive,''N'' as istranslated,'
         || curTableNames_tableName
         || '_id';

      SELECT ad_table_id
        INTO table_id
        FROM AD_TABLE
      WHERE tablename = curTableNames_tableName;

      BEGIN
	    -- Declare 'handlers' for exceptions
        DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done2 = TRUE;
        OPEN curColumns;
        myLoop2: LOOP
          FETCH curColumns INTO curColumns_ColumnName;
    
          IF done2 THEN
            CLOSE curColumns;
            LEAVE myLoop2;
          END IF;
          
         SET ins = TRIM (ins) || ',' || TRIM (curColumns_ColumnName);
         SET sel = TRIM (sel) || ',t.' || TRIM (curColumns_ColumnName);
        END LOOP myLoop2;
      END;
      
      SET ins = TRIM (ins) || ')';
      SET sel =
            TRIM (sel)
         || ' from '
         || curTableNames_tableName
         || ' t, ad_language l WHERE l.issystemlanguage=''Y'' AND NOT EXISTS (SELECT 1 FROM '
         || curTableNames_tableName
         || '_TRL b WHERE b.'
         || curTableNames_tableName
         || '_id=t.'
         || curTableNames_tableName
         || '_id AND b.AD_LANGUAGE=l.AD_LANGUAGE)';
      SET @inssel := TRIM (ins) || ' ' || TRIM (sel);

      PREPARE mySt FROM @inssel;
      EXECUTE mySt;
  END LOOP myLoop;
  
END$$

-- select add_missing_translations() $$
CALL add_missing_translations() $$

commit $$

DELIMITER ;

