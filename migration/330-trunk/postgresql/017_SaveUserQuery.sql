ALTER TABLE AD_UserQuery
ADD COLUMN AD_Tab_ID NUMERIC(10);

INSERT INTO AD_Column
(AD_Column_ID, AD_Client_ID, AD_Org_ID, IsActive,
Created,
Updated, CreatedBy,
UpdatedBy, Name, Description, VERSION,
EntityType, ColumnName, AD_Table_ID, AD_Reference_ID, 
FieldLength, IsKey, IsParent, IsMandatory, IsUpdateable,
IsIdentifier, SeqNo, IsTranslated, IsEncrypted,
isselectioncolumn, ad_element_id, callout, issyncdatabase,
isalwaysupdateable
)
VALUES (53251, 0, 0, 'Y',
TO_DATE ('10/10/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
TO_DATE ('10/10/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
100, 'Tab', 'Tab within a Window', 1,
'D', 'AD_Tab_ID', 814, 19,
10, 'N', 'N', 'Y', 'Y',
'N', 0, 'N', 'N',
'N', 125, null, 'N',
'N'
);
COMMIT;