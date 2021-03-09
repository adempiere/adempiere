/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) Trifon Trifonov.                                             *
 * Copyright (C) Contributors                                                 * 
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

/******************************************************************************
* Contributors:                                                               *
* - Trifon Trifonov (trifonnt@users.sourceforge.net)                          *
*                                                                             *
* Sponsors:                                                                   *
* - Company (http://www.d3-soft.com)                                          *
******************************************************************************/
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.compiere.server.AdempiereServer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MAlert")
@Tag("MAlertProcessor")
class IT_MAlertProcessor extends CommonGWSetup {

    private MAlertProcessor processor;
    private MAlert alert;
    private MAlert[] alerts;

    @Test
    void createAlertWithNoName_throwsException() {

        MAlert alert = new MAlert(ctx, 0, trxName);
        assertThrows(AdempiereException.class, () -> {
            alert.saveEx();
        });

    }

    @Test
    void createAlertWithNoSubject_throwsException() {

        MAlert alert = new MAlert(ctx, 0, trxName);
        alert.setName("Trifon test");
        assertThrows(AdempiereException.class, () -> {
            alert.saveEx();
        });

    }

    @Test
    void createAlertWithNoMessage_throwsException() {

        MAlert alert = new MAlert(ctx, 0, trxName);
        alert.setName("Trifon test");
        alert.setAlertSubject("Alert Subject");
        assertThrows(AdempiereException.class, () -> {
            alert.saveEx();
        });

    }

    @Test
    void testAlertProcessor() {

        createAlertProcessor();
        createAlert();
        runAlertServer();

        assertProcessorHasLogs();
        assertProcessorHasAlerts();
        assertProcessorAlertsContainsAlert();
        assertTheProcessorIsActive();

    }

    private void assertTheProcessorIsActive() {

        MAlertProcessor[] activeProcessors = MAlertProcessor.getActive(ctx, trxName);
        assertTrue(activeProcessors.length > 0, "There should be active recs");
        boolean found = Stream.of(activeProcessors)
                .anyMatch((activeProcessor) -> activeProcessor
                        .getAD_AlertProcessor_ID() == processor.getAD_AlertProcessor_ID());

        assertTrue(found, "Didn't find the alert processor");

    }

    private void assertProcessorAlertsContainsAlert() {

        assertEquals(alert.getAD_Alert_ID(), alerts[0].getAD_Alert_ID());

    }

    private void assertProcessorHasAlerts() {

        alerts = (MAlert[]) processor.getAlerts(true);
        assertTrue(alerts.length > 0, "There are alerts");

    }

    private void assertProcessorHasLogs() {

        MAlertProcessorLog[] test = (MAlertProcessorLog[]) processor.getLogs();
        assertTrue(test.length > 0, "Alert Processor has logs");

    }

    private void createAlertProcessor() {

        processor = new MAlertProcessor(ctx, 0, trxName);
        processor.setName("Test Processor");
        processor.setFrequencyType(MAlertProcessor.FREQUENCYTYPE_Day);
        processor.setFrequency(1);
        processor.setSupervisor_ID(1);
        processor.saveEx();

    }

    private void createAlert() {

        alert = new MAlert(ctx, 0, trxName);
        alert.setAD_AlertProcessor_ID(processor.getAD_AlertProcessor_ID());
        alert.setName("Trifon test");
        alert.setAlertSubject("Alert Subject");
        alert.setAlertMessage("Alert Message");
        alert.saveEx();

    }

    private void runAlertServer() {

        AdempiereServer server = AdempiereServer.create(processor);
        server.runNow();

    }

}
