/**
 *  DocType
 */
UPDATE C_DocType_Trl t
SET (AD_Client_ID,AD_Org_ID,Name,PrintName,DocumentNote) =
  (SELECT AD_Client_ID,AD_Org_ID,Name,PrintName,DocumentNote 
    FROM C_DocType d WHERE t.C_DocType_ID=d.C_DocType_ID)
WHERE EXISTS
    (SELECT * FROM C_DocType d WHERE t.C_DocType_ID=d.C_DocType_ID);

BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding missing DocType Translations');
    INSERT INTO C_DocType_Trl (C_DocType_ID, AD_Language, AD_Client_ID, AD_Org_ID,
        IsActive, Created, CreatedBy, Updated, UpdatedBy,
        Name, PrintName, DocumentNote, IsTranslated)
    SELECT m.C_DocType_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
        m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
        m.Name, m.PrintName, m.DocumentNote, 'N'
    FROM    C_DocType m, AD_Language l
   	WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
	  AND	C_DocType_ID || AD_Language NOT IN 
		(SELECT C_DocType_ID || AD_Language FROM C_DocType_Trl);
	DBMS_OUTPUT.PUT_LINE('Rows added: ' || SQL%ROWCOUNT);
END;
/
