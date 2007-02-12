CREATE OR REPLACE VIEW AD_CHANGELOG_V
(AD_SESSION_ID, AD_CHANGELOG_ID, TABLENAME, RECORD_ID, COLUMNNAME, 
 OLDVALUE, NEWVALUE, NAME, CREATED)
AS 
SELECT l.AD_Session_ID, l.AD_ChangeLog_ID, 
    t.TableName, l.Record_ID, c.ColumnName, 
    l.OldValue, l.NewValue, 
    u.Name, l.Created
FROM AD_ChangeLog l
    INNER JOIN AD_Table t ON (l.AD_Table_ID=t.AD_Table_ID)
    INNER JOIN AD_Column c ON (l.AD_Column_ID=c.AD_Column_ID)
    INNER JOIN AD_User u ON (l.CreatedBy=u.AD_User_ID)
ORDER BY l.AD_Session_ID, l.AD_ChangeLog_ID, t.TableName, l.Record_ID, c.ColumnName;



