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
		//$JUnit-END$
		return suite;
	}

}
