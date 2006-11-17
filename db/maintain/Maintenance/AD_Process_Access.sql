/**
 *	Fill AD_Process_Access for all Roles
 *	---------------------------------------------------------------------------
 *	Based on AccessLevel
 */
DECLARE
	CURSOR	Cur_Role	IS
		SELECT * FROM AD_Role;
	No			NUMBER;
BEGIN

	FOR r IN Cur_Role LOOP
		DBMS_OUTPUT.PUT_LINE('Role: ' || r.Name || ', Level=' || r.UserLevel);
		--	--
		IF (r.UserLevel='S') THEN
			DELETE AD_Process_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Process_Access
				(AD_Process_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_Process_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Process p
			WHERE	p.AccessLevel IN ('4','7','6');
			No := SQL%ROWCOUNT;
			DBMS_OUTPUT.PUT_LINE(' > System Processes ' || No);
		--	--
		ELSIF (r.UserLevel=' CO') THEN
			DELETE AD_Process_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Process_Access
				(AD_Process_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_Process_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Process p
			WHERE	p.AccessLevel IN ('7','6','3','2','1');
			No := SQL%ROWCOUNT;
			DBMS_OUTPUT.PUT_LINE(' > Client/Org Processes ' || No);
		--	--
		ELSIF (r.UserLevel='  O') THEN
			DELETE AD_Process_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_Process_Access
				(AD_Process_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_Process_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_Process p
			WHERE	p.AccessLevel IN ('3','1','7');
			No := SQL%ROWCOUNT;
			DBMS_OUTPUT.PUT_LINE(' > Org Processes ' || No);
		END IF;
		--
		COMMIT;
	END LOOP;
END;
/
