/**
 *  PrintFormatItem
 */
BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding missing PrintFormatItem Translations');
    INSERT INTO AD_PrintFormatItem_Trl (AD_PrintFormatItem_ID, AD_Language, AD_Client_ID, AD_Org_ID,
        IsActive, Created, CreatedBy, Updated, UpdatedBy,
        PrintName, IsTranslated)
    SELECT m.AD_PrintFormatItem_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
        m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
        m.PrintName, 'N'
    FROM    AD_PrintFormatItem m, AD_Language l
   	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	AD_PrintFormatItem_ID || AD_Language NOT IN 
		(SELECT AD_PrintFormatItem_ID || AD_Language FROM AD_PrintFormatItem_Trl);
	DBMS_OUTPUT.PUT_LINE('Rows added: ' || SQL%ROWCOUNT);
END;
/
