package org.adempiere.test;

import static org.adempiere.test.TestUtilities.randomString;

import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

@Tag("UnitTest")
public abstract class CommonUnitTestSetup {

	protected static final int AD_USER_ID = 100;
	protected static final int AD_CLIENT_ID = 11;
	protected static final int AD_ORG_ID = 11;
	protected static final boolean IS_CLIENT = true;

	protected static Properties ctx;
	protected static String trxName = null;
	protected static Trx trx = null;
	protected static Savepoint mainSavepoint = null;
	protected static Savepoint testSavepoint = null;
	protected static Timestamp loginDate;

	@BeforeAll
	public static void setUpBeforeAll() {
		
		loginDate = TimeUtil.getDay(System.currentTimeMillis());
		
		ctx = Env.getCtx();
		ctx.setProperty("#AD_Org_ID", Integer.toString(AD_ORG_ID));
		ctx.setProperty("#AD_User_ID", Integer.toString(AD_USER_ID));
		ctx.setProperty("#AD_Client_ID", Integer.toString(AD_CLIENT_ID));
		ctx.setProperty("#Date", loginDate.toString());

		trxName = Trx.createTrxName("TestRun_" + randomString(4));
		trx = Trx.get(trxName, false);
		
	}

	@BeforeEach
	public void setup() {
		// Added to prevent sonar warning that utility classes should not have public constructors
		// As an abstract class, an implicit public constructor is required.  I'd rather add this
		// method than deactivate the sonar rule.
	}
	
}
