/**
 *	Account Defaults *** DANGEROUS ***
 *
DECLARE
	--	Accounting Defaults for all clients
	CURSOR Cur_Clients	IS
		SELECT AD_Client_ID FROM AD_Client;
	--	All Bus Groups
	CURSOR Cur_BP	IS
		SELECT C_BP_Group_ID FROM C_BP_Group;
	--	All Product Categories
	CURSOR Cur_PC	IS
		SELECT M_Product_Category_ID FROM M_Product_Category;
	--
BEGIN
	--	Accounting Defaults
	FOR cc IN Cur_Clients LOOP
		DBMS_OUTPUT.PUT_LINE('------------------------');
	--	C_AcctSchema_Default_Copy(0, cc.AD_Client_ID);	--	DANGEROUS !!!
	END LOOP;
	--	BPartner  Defaults
	FOR bp IN Cur_BP LOOP
		DBMS_OUTPUT.PUT_LINE('------------------------');
		C_BP_Group_Acct_Copy(0, bp.C_BP_Group_ID);
	END LOOP;
	--	Accounting Defaults
	FOR pc IN Cur_PC LOOP
		DBMS_OUTPUT.PUT_LINE('------------------------');
		M_Product_Category_Acct_Copy(0, pc.M_Product_Category_ID);
	END LOOP;
END;
