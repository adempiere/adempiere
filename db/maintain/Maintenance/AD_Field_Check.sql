/**
 *  AD_Field_Check.sql
 *
 *  For AD_Field create
 *  - Missing Translations
 *
 *	Sync Fields in tab with other tab
UPDATE AD_Field f
SET (IsDisplayed,IsReadOnly,DisplayLogic,SeqNo,IsSameLine) = 
	(SELECT IsDisplayed,IsReadOnly,DisplayLogic,SeqNo,IsSameLine 
FROM AD_Field ff WHERE f.AD_Column_ID=ff.AD_Column_ID AND ff.AD_Tab_ID=180)
WHERE AD_Tab_ID = 407 --IN (411,417);
COMMIT;
 *
 */
BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding missing Field Translations');
    INSERT INTO AD_Field_Trl (AD_Field_ID, AD_Language, AD_Client_ID, AD_Org_ID,
        IsActive, Created, CreatedBy, Updated, UpdatedBy,
        Name, Description, IsTranslated)
    SELECT m.AD_Field_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
        m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
        m.Name, m.Description, 'N'
    FROM    AD_Language l, AD_Field m 
   	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	AD_Field_ID || AD_Language NOT IN 
		(SELECT AD_Field_ID || AD_Language FROM AD_Field_Trl);
	DBMS_OUTPUT.PUT_LINE('  rows added: ' || SQL%ROWCOUNT);
END;
