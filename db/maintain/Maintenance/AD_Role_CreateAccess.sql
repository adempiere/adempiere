/**
 *	Create Role _ClientAccess and _OrgAccess
 *	(assumes, that a role has only one organization (which should be the case)
 */
DECLARE
	CURSOR	CUR_Role	IS
		SELECT	*
		FROM	AD_Role;
	--
	v_Client				VARCHAR2(60);
	v_Org					VARCHAR2(60);
	v_ID					VARCHAR2(20);
	v_AD_Client_ID			NUMBER(10);
	v_AD_Org_ID				NUMBER(10);
BEGIN
	--	For each Role
	FOR r IN CUR_Role LOOP
		DBMS_OUTPUT.PUT_LINE(r.Name);
		v_Client := r.ClientList;
		v_Org := r.OrgList;
		WHILE (LENGTH(v_Client) > 0) LOOP
			--	Get ID
			IF (INSTR(v_Client,',') = 0) THEN
				v_ID := v_Client;
			ELSE
				v_ID := SUBSTR(v_Client,1,INSTR(v_Client,',')-1);
			END IF;
			v_AD_Client_ID := TO_NUMBER (v_ID);
			DBMS_OUTPUT.PUT_LINE(' Client=' || v_AD_Client_ID); 

			INSERT INTO AD_ROLE_ClientACCESS
				(AD_ROLE_ID, AD_CLIENT_ID, AD_ORG_ID,
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY)
			VALUES
				(r.AD_ROLE_ID, v_AD_CLIENT_ID, 0,
				'Y', SysDate, r.CREATEDBY, SysDate, r.UPDATEDBY);

			--	Next
			IF (INSTR(v_Client,',') = 0) THEN
				v_Client := '';
		  	ELSE
				v_Client := SUBSTR(v_Client,INSTR(v_Client,',')+1);
		   	END IF;
		END LOOP;
		WHILE (LENGTH(v_Org) > 0) LOOP
			--	Get ID
			IF (INSTR(v_Org,',') = 0) THEN
				v_ID := v_Org;
			ELSE
				v_ID := SUBSTR(v_Org,1,INSTR(v_Org,',')-1);
			END IF;
			v_AD_Org_ID := TO_NUMBER (v_ID);
			DBMS_OUTPUT.PUT_LINE(' Client=' || v_AD_Client_ID || ' Org=' || v_AD_Org_ID); 

			INSERT INTO AD_ROLE_OrgACCESS
				(AD_ROLE_ID, AD_CLIENT_ID, AD_ORG_ID,
				ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY)
			VALUES
				(r.AD_ROLE_ID, v_AD_CLIENT_ID, v_AD_Org_ID,
				'Y', SysDate, r.CREATEDBY, SysDate, r.UPDATEDBY);

			--	Next
			IF (INSTR(v_Org,',') = 0) THEN
				v_Org := '';
		  	ELSE
				v_Org := SUBSTR(v_Org,INSTR(v_Org,',')+1);
		   	END IF;
		END LOOP;

	END LOOP;
	COMMIT;
END;
