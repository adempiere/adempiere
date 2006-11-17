/**
 *	Access Workflow for All
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
			DELETE AD_WorkFlow_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_WorkFlow_Access
				(AD_WorkFlow_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_WorkFlow_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_WorkFlow p
			WHERE	p.AccessLevel IN ('4','7','6');
			No := SQL%ROWCOUNT;
		  	DBMS_OUTPUT.PUT_LINE(' > System WorkFlows ' || No);
		--	--
		ELSIF (r.UserLevel=' CO') THEN
			DELETE AD_WorkFlow_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_WorkFlow_Access
				(AD_WorkFlow_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_WorkFlow_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_WorkFlow p
			WHERE	p.AccessLevel IN ('7','6','3','2','1');
			No := SQL%ROWCOUNT;
		  	DBMS_OUTPUT.PUT_LINE(' > Client/Org WorkFlows ' || No);
		--	--
		ELSIF (r.UserLevel='  O') THEN
			DELETE AD_WorkFlow_Access
			WHERE AD_Role_ID = r.AD_Role_ID;
			--
			INSERT INTO AD_WorkFlow_Access
				(AD_WorkFlow_ID, AD_Role_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				IsReadWrite)
			SELECT DISTINCT p.AD_WorkFlow_ID, r.AD_Role_ID,
				r.AD_CLIENT_ID, r.AD_ORG_ID, 'Y', SysDate, 0, SysDate, 0,
				'Y'
			FROM	AD_WorkFlow p
			WHERE	p.AccessLevel IN ('3','1','7');
			No := SQL%ROWCOUNT;
		  	DBMS_OUTPUT.PUT_LINE(' > Org WorkFlows ' || No);
		END IF;
		--
		COMMIT;
	END LOOP;
END;
/
