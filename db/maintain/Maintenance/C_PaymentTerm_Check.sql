/**
 *  PaymentTerm
 */
BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding missing PaymentTerm Translations');
    INSERT INTO C_PaymentTerm_Trl (C_PaymentTerm_ID, AD_Language, AD_Client_ID, AD_Org_ID,
        IsActive, Created, CreatedBy, Updated, UpdatedBy,
        Name, Description, DocumentNote, IsTranslated)
    SELECT m.C_PaymentTerm_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
        m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
        m.Name, m.Description, m.DocumentNote, 'N'
    FROM    C_PaymentTerm m, AD_Language l
   	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	C_PaymentTerm_ID || AD_Language NOT IN 
		(SELECT C_PaymentTerm_ID || AD_Language FROM C_PaymentTerm_Trl);
	DBMS_OUTPUT.PUT_LINE('Rows added: ' || SQL%ROWCOUNT);
END;