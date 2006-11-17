/** Add Internal Contacts
**/
DECLARE
	CURSOR CUR_NoContact IS
		SELECT C_BPartner_ID, Name, AD_Client_ID, AD_Org_ID
		FROM C_BPartner bp
		WHERE EXISTS (SELECT * FROM AD_User u WHERE bp.C_BPartner_ID=u.C_BPartner_ID)
		  AND NOT EXISTS (SELECT * FROM AD_User bpc WHERE bp.C_BPartner_ID=bpc.C_BPartner_ID);
	v_AD_User_ID	NUMBER(10);
BEGIN
	FOR c IN CUR_NoContact LOOP
		AD_Sequence_Next ('AD_User', c.AD_Client_ID, v_AD_User_ID);
		INSERT INTO AD_User
		  (AD_User_ID,
		  AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,
		  C_BPartner_ID, Name)
		VALUES
		  (v_AD_User_ID,
		  c.AD_Client_ID,c.AD_Org_ID,'Y',SysDate,0,SysDate,0,
		  c.C_BPartner_ID, c.Name);
		DBMS_OUTPUT.PUT_LINE('AD_User=' || v_AD_User_ID);
	END LOOP;
	COMMIT;
END;
/
COMMIT
/
		  