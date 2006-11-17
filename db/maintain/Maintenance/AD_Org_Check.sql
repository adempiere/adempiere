/**
 *  For AD_Org create
 *  - Missing Org Tree Structure
 */
BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding to Base Org Tree');
	DECLARE
		CURSOR	Cur_Tree IS
			SELECT  *
        	FROM    AD_ClientInfo;
		CURSOR Cur_Org	(Client NUMBER, Tree NUMBER) IS
			SELECT *
			FROM   AD_Org
			WHERE  AD_Org_ID NOT IN 
				(SELECT Node_ID FROM AD_TreeNode WHERE AD_Tree_ID=Tree)
			  AND AD_Client_ID=Client;
		CURSOR Cur_OrgInfo	IS
			SELECT *
			FROM   AD_Org o
			WHERE NOT EXISTS 
				(SELECT * FROM AD_OrgInfo oi WHERE oi.AD_Org_ID=o.AD_Org_ID);
	BEGIN
		FOR CT IN Cur_Tree LOOP
		    DBMS_OUTPUT.PUT_LINE('  For Tree ' || CT.AD_Tree_Org_ID 
				|| ' Client=' || CT.AD_Client_ID);
			--
			FOR CO IN Cur_Org (CT.AD_Client_ID, CT.AD_Tree_Org_ID) LOOP
	         	INSERT INTO AD_TreeNode
					(AD_Client_ID, AD_Org_ID,
					IsActive, Created, CreatedBy, Updated, UpdatedBy,
					AD_Tree_ID, Node_ID, Parent_ID, SeqNo)
				VALUES
					(CO.AD_Client_ID, CO.AD_Org_ID, 
					CO.IsActive, CO.Created, CO.CreatedBy, CO.Updated, CO.UpdatedBy,
					CT.AD_Tree_Org_ID, CO.AD_Org_ID, 0, 999);
	            DBMS_OUTPUT.PUT_LINE('    added: ' || CO.Name
					|| ' Client=' || CO.AD_Client_ID || ' Org=' || CO.AD_Org_ID);
       		END LOOP;	-- Org Loop
		END LOOP;	--	Tree Loop

		FOR o IN Cur_OrgInfo () LOOP
		    DBMS_OUTPUT.PUT_LINE('Create OrgInfo For Org ' || o.AD_Org_ID || ' ' || o.Name);
			INSERT INTO AD_OrgInfo
				(AD_Org_ID, AD_Client_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				C_Location_ID, Duns, TaxID, PA_Goal_ID)
			VALUES
				(o.AD_Org_ID, o.AD_Client_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
				NULL, '?', '?', NULL);
		END LOOP;
	END;


    COMMIT;
END;

