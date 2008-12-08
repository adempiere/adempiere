package test.functional;

import junit.framework.Test;
import junit.framework.TestSuite;

public class FunctionalTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test.functional");
		//$JUnit-BEGIN$
		suite.addTestSuite(TrifonTest.class);
		suite.addTestSuite(MBPartnerTest.class);
		suite.addTestSuite(MBPartnerLocationTest.class);
		suite.addTestSuite(MUserTest.class);
		suite.addTestSuite(MBPGroupTest.class);
		suite.addTestSuite(MLocationTest.class);
		suite.addTestSuite(POTest.class);
		suite.addTestSuite(MStorageTest.class);
		suite.addTestSuite(MSysConfigTest.class);
		suite.addTestSuite(QueryTest.class);
		suite.addTestSuite(DBTest.class);
		suite.addTestSuite(TrxTest.class);
		suite.addTestSuite(MRefListTest.class);
		suite.addTestSuite(MUOMTest.class);
		//$JUnit-END$
		return suite;
	}

}
