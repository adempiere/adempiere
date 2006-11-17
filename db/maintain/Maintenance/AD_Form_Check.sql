/**
 *  AD_Form_Check.sql
 *
 *  For AD_Form create
 *  - Missing Translations
 */
BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding missing Form Translations');
    INSERT INTO AD_Form_Trl (AD_Form_ID, AD_Language, AD_Client_ID, AD_Org_ID,
        IsActive, Created, CreatedBy, Updated, UpdatedBy,
        Name, Description, IsTranslated)
    SELECT m.AD_Form_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
        m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
        m.Name, m.Description, 'N'
    FROM    AD_Language l, AD_Form m 
   	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	AD_Form_ID || AD_Language NOT IN 
		(SELECT AD_Form_ID || AD_Language FROM AD_Form_Trl);
	DBMS_OUTPUT.PUT_LINE('  rows added: ' || SQL%ROWCOUNT);
	--

END;
