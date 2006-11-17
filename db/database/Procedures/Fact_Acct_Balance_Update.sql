CREATE OR REPLACE PROCEDURE Fact_Acct_Balance_Update
(
	p_DeleteFirst		IN	VARCHAR2	DEFAULT 'N'
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Fact_Acct_Balance_Update.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:		Update ALL Balances	
 * Description:
 *	- Recreates all Balances
 ************************************************************************/
AS
BEGIN

	IF (p_DeleteFirst = 'Y') THEN
		DELETE Fact_Acct_Balance;
		DBMS_OUTPUT.PUT_LINE('  Deletes=' || SQL%ROWCOUNT);
	ELSE
		/** Update		**/
		UPDATE Fact_Acct_Balance ab
		  SET (AmtAcctDr, AmtAcctCr, Qty) =
			(SELECT COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), COALESCE(SUM(Qty),0)
			FROM Fact_Acct a
			WHERE a.AD_Client_ID=ab.AD_Client_ID AND a.AD_Org_ID=ab.AD_Org_ID
				AND a.C_AcctSchema_ID=ab.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(ab.DateAcct)
				AND a.Account_ID=ab.Account_ID AND a.PostingType=ab.PostingType
				AND COALESCE(a.M_Product_ID,0)=COALESCE(ab.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(ab.C_BPartner_ID,0)
				AND COALESCE(a.C_Project_ID,0)=COALESCE(ab.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(ab.AD_OrgTrx_ID,0)
				AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(ab.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(ab.C_Activity_ID,0)
				AND COALESCE(a.C_Campaign_ID,0)=COALESCE(ab.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(ab.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(ab.C_LocFrom_ID,0)
				AND COALESCE(a.User1_ID,0)=COALESCE(ab.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(ab.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(ab.GL_Budget_ID,0) 
			GROUP BY AD_Client_ID,AD_Org_ID, 
				C_AcctSchema_ID, TRUNC(DateAcct),
				Account_ID, PostingType,
				M_Product_ID, C_BPartner_ID,
				C_Project_ID, AD_OrgTrx_ID,
				C_SalesRegion_ID, C_Activity_ID,
				C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
				User1_ID, User2_ID, GL_Budget_ID)
		WHERE EXISTS 
			(SELECT *
			FROM Fact_Acct a
			WHERE a.AD_Client_ID=ab.AD_Client_ID AND a.AD_Org_ID=ab.AD_Org_ID
				AND a.C_AcctSchema_ID=ab.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(ab.DateAcct)
				AND a.Account_ID=ab.Account_ID AND a.PostingType=ab.PostingType
				AND COALESCE(a.M_Product_ID,0)=COALESCE(ab.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(ab.C_BPartner_ID,0)
				AND COALESCE(a.C_Project_ID,0)=COALESCE(ab.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(ab.AD_OrgTrx_ID,0)
				AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(ab.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(ab.C_Activity_ID,0)
				AND COALESCE(a.C_Campaign_ID,0)=COALESCE(ab.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(ab.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(ab.C_LocFrom_ID,0)
				AND COALESCE(a.User1_ID,0)=COALESCE(ab.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(ab.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(ab.GL_Budget_ID,0) 
			GROUP BY AD_Client_ID,AD_Org_ID, 
				C_AcctSchema_ID, TRUNC(DateAcct),
				Account_ID, PostingType,
				M_Product_ID, C_BPartner_ID,
				C_Project_ID, AD_OrgTrx_ID,
				C_SalesRegion_ID, C_Activity_ID,
				C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
				User1_ID, User2_ID, GL_Budget_ID);
		DBMS_OUTPUT.PUT_LINE('  Updates=' || SQL%ROWCOUNT);
	END IF;


	/** Insert		**/
	INSERT INTO Fact_Acct_Balance ab
		(AD_Client_ID, AD_Org_ID, 
		C_AcctSchema_ID, DateAcct,
		Account_ID, PostingType,
		M_Product_ID, C_BPartner_ID,
		C_Project_ID, AD_OrgTrx_ID,
		C_SalesRegion_ID,C_Activity_ID,
		C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
		User1_ID, User2_ID, GL_Budget_ID, 
		AmtAcctDr, AmtAcctCr, Qty)
	--
	SELECT AD_Client_ID, AD_Org_ID, 
		C_AcctSchema_ID, TRUNC(DateAcct),
		Account_ID, PostingType,
		M_Product_ID, C_BPartner_ID,
		C_Project_ID, AD_OrgTrx_ID,
		C_SalesRegion_ID,C_Activity_ID,
		C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
		User1_ID, User2_ID, GL_Budget_ID, 
		COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), COALESCE(SUM(Qty),0)
	FROM Fact_Acct a
	WHERE NOT EXISTS 
		(SELECT * 
		FROM Fact_Acct_Balance x
		WHERE a.AD_Client_ID=x.AD_Client_ID AND a.AD_Org_ID=x.AD_Org_ID
			AND a.C_AcctSchema_ID=x.C_AcctSchema_ID AND TRUNC(a.DateAcct)=TRUNC(x.DateAcct)
			AND a.Account_ID=x.Account_ID AND a.PostingType=x.PostingType
			AND COALESCE(a.M_Product_ID,0)=COALESCE(x.M_Product_ID,0) AND COALESCE(a.C_BPartner_ID,0)=COALESCE(x.C_BPartner_ID,0)
			AND COALESCE(a.C_Project_ID,0)=COALESCE(x.C_Project_ID,0) AND COALESCE(a.AD_OrgTrx_ID,0)=COALESCE(x.AD_OrgTrx_ID,0)
			AND COALESCE(a.C_SalesRegion_ID,0)=COALESCE(x.C_SalesRegion_ID,0) AND COALESCE(a.C_Activity_ID,0)=COALESCE(x.C_Activity_ID,0)
			AND COALESCE(a.C_Campaign_ID,0)=COALESCE(x.C_Campaign_ID,0) AND COALESCE(a.C_LocTo_ID,0)=COALESCE(x.C_LocTo_ID,0) AND COALESCE(a.C_LocFrom_ID,0)=COALESCE(x.C_LocFrom_ID,0)
			AND COALESCE(a.User1_ID,0)=COALESCE(x.User1_ID,0) AND COALESCE(a.User2_ID,0)=COALESCE(x.User2_ID,0) AND COALESCE(a.GL_Budget_ID,0)=COALESCE(x.GL_Budget_ID,0) )
	GROUP BY AD_Client_ID,AD_Org_ID, 
		C_AcctSchema_ID, TRUNC(DateAcct),
		Account_ID, PostingType,
		M_Product_ID, C_BPartner_ID,
		C_Project_ID, AD_OrgTrx_ID,
		C_SalesRegion_ID, C_Activity_ID,
		C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID,
		User1_ID, User2_ID, GL_Budget_ID;
	DBMS_OUTPUT.PUT_LINE('  Inserts=' || SQL%ROWCOUNT);

	-----------------------
	COMMIT;

END Fact_Acct_Balance_Update;
/
