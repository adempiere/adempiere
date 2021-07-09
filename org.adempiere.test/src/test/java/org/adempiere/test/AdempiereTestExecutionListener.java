/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.test;

import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;

import org.junit.platform.engine.TestDescriptor.Type;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestExecutionResult.Status;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

/**
 * Adapted from https://www.selikoff.net/2018/07/28/ant-and-
 * junit-5-outputting-test-duration-and-failure-to-the-log/
 *
 */
public class AdempiereTestExecutionListener implements TestExecutionListener {

    private int numSkippedInCurrentClass;
    private int numAbortedInCurrentClass;
    private int numSucceededInCurrentClass;
    private int numFailedInCurrentClass;
    private Instant startCurrentClass;

    private PrintStream printStream;
    private String failedList;

    public AdempiereTestExecutionListener() {

        printStream = new PrintStream(System.out);

    }

    private boolean isTestOfInterest(TestIdentifier testIdentifier) {

        return "[engine:junit-jupiter]".equals(testIdentifier.getParentId().orElse(""))
                || "[engine:junit-vintage]".equals(testIdentifier.getParentId().orElse(""));

    }

    private void println(String str) {

        printStream.println(str + "\n");

    }

    private void resetCountsForNewClass() {

        numSkippedInCurrentClass = 0;
        numAbortedInCurrentClass = 0;
        numSucceededInCurrentClass = 0;
        numFailedInCurrentClass = 0;
        startCurrentClass = Instant.now();
        failedList = "";

    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {

        if (isTestOfInterest(testIdentifier)) {
            println("Running " + testIdentifier.getLegacyReportingName());
            resetCountsForNewClass();
        }

    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {

        numSkippedInCurrentClass++;

    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier,
            TestExecutionResult testExecutionResult) {

        if (isTestOfInterest(testIdentifier)) {
            int totalTestsInClass = numSucceededInCurrentClass + numAbortedInCurrentClass
                    + numFailedInCurrentClass + numSkippedInCurrentClass;
            Duration duration = Duration.between(startCurrentClass, Instant.now());
            double numSeconds = duration.toNanos() / (double) 1_000_000_000;
            String output = String.format(
                    "  Tests run: %d, Failures: %d, Aborted: %d, Skipped: %d, Time elapsed: %f sec",
                    totalTestsInClass, numFailedInCurrentClass, numAbortedInCurrentClass,
                    numSkippedInCurrentClass, numSeconds);
            println(output);
            if (!failedList.isEmpty())
                println(failedList);

        }
        // don't count containers since looking for legacy JUnit 4 counting style
        if (testIdentifier.getType() == Type.TEST) {
            if (testExecutionResult.getStatus() == Status.SUCCESSFUL) {
                numSucceededInCurrentClass++;
            } else if (testExecutionResult.getStatus() == Status.ABORTED) {
                failedList += "    ABORTED: " + testIdentifier.getDisplayName() + "\n";
                numAbortedInCurrentClass++;
            } else if (testExecutionResult.getStatus() == Status.FAILED) {
                failedList += "    FAILED: " + testIdentifier.getDisplayName() + "\n";
                numFailedInCurrentClass++;
            }
        }

    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {

        printStream.flush();

    }

}
