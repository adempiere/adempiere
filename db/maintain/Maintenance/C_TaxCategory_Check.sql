/**
 *  C_TaxCategory_Check.sql
 *
 *  For C_TaxCategory create
 *  - Missing Translations
 */
BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding missing TaxCatregory Translations');
    INSERT INTO C_TaxCategory_Trl (C_TaxCategory_ID, AD_Language, AD_Client_ID, AD_Org_ID,
        IsActive, Created, CreatedBy, Updated, UpdatedBy,
        Name, Description, IsTranslated)
    SELECT m.C_TaxCategory_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
        m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
        m.Name, m.Description, 'N'
    FROM    AD_Language l, C_TaxCategory m 
   	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	C_TaxCategory_ID || AD_Language NOT IN 
		(SELECT C_TaxCategory_ID || AD_Language FROM C_TaxCategory_Trl);
	DBMS_OUTPUT.PUT_LINE('  rows added: ' || SQL%ROWCOUNT);
END;