--  Update Column with Elements
--
UPDATE AD_Column c
SET ColumnName = (SELECT ColumnName FROM AD_Element e 
            WHERE c.AD_Element_ID=e.AD_Element_ID AND ROWNUM=1),
    Name = (SELECT Name FROM AD_Element e 
            WHERE c.AD_Element_ID=e.AD_Element_ID AND ROWNUM=1),
    Description = (SELECT Description FROM AD_Element e 
            WHERE c.AD_Element_ID=e.AD_Element_ID AND ROWNUM=1),
    Help = (SELECT Help FROM AD_Element e 
            WHERE c.AD_Element_ID=e.AD_Element_ID AND ROWNUM=1)
WHERE EXISTS    (SELECT * FROM AD_Element e 
            WHERE c.AD_Element_ID=e.AD_Element_ID)
;
COMMIT
;
--  Process Info
UPDATE AD_Column c
SET Name = (SELECT Name FROM AD_Process p 
            WHERE c.AD_Process_ID=p.AD_Process_ID AND ROWNUM=1),
    Description = (SELECT Description FROM AD_Process p 
            WHERE c.AD_Process_ID=p.AD_Process_ID AND ROWNUM=1),
    Help = (SELECT Help FROM AD_Process p 
            WHERE c.AD_Process_ID=p.AD_Process_ID AND ROWNUM=1)
WHERE EXISTS    (SELECT * FROM AD_Process p 
            WHERE c.AD_Process_ID=p.AD_Process_ID)
;
COMMIT
;

