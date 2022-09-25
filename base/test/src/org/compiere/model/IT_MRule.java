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
package org.compiere.model;

import static org.adempiere.core.domains.models.X_AD_Rule.RULETYPE_AspectOrientProgram;
import static org.adempiere.core.domains.models.X_AD_Rule.RULETYPE_JSR223ScriptingAPIs;
import static org.adempiere.core.domains.models.X_AD_Rule.RULETYPE_JSR94RuleEngineAPI;
import static org.adempiere.core.domains.models.X_AD_Rule.RULETYPE_SQL;
import static org.compiere.model.ModelTestUtilities.verifyExceptionForMissingMandatoryField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Teo Sarca, www.arhipac.ro
 */
@Tag("Model")
@Tag("MRule")
class IT_MRule extends CommonGWSetup {

    private Exception createRuleWithoutField(String fieldName) {

        MRule rule = new MRule(ctx, 0, trxName);
        rule.setRuleType(RULETYPE_JSR94RuleEngineAPI);
        if (!fieldName.equals("name"))
            rule.setName("TestRule");
        Exception e = assertThrows(AdempiereException.class, () -> {
            rule.saveEx();
        });
        return e;

    }

    @Test
    void simpleConstructorWillThrowNPE() {

        // TODO - this is an indication of a problem with the model setup
        // likely there is a missing default setting on the
        // RuleType as this error is triggered in the beforeSave method.
        MRule rule = new MRule(ctx, 0, trxName);

        Exception e = assertThrows(AdempiereException.class, () -> {
            rule.saveEx();
        });

        assertEquals("SaveError",
                e.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = { "name" })
    void constructorWithMissingMandatoryFielsThrowsException(String fieldName) {

        Exception e = createRuleWithoutField(fieldName);
        verifyExceptionForMissingMandatoryField(fieldName, e);

    }

    @ParameterizedTest
    @ValueSource(
            strings = { RULETYPE_JSR94RuleEngineAPI, RULETYPE_SQL,
                    RULETYPE_AspectOrientProgram })
    void constructor(String ruleType) {

        MRule rule = new MRule(ctx, 0, trxName);
        rule.setRuleType(ruleType);
        rule.setName("TestRule");
        rule.saveEx();

        assertTrue(rule.getAD_Rule_ID() > 0, "Saved rule does not have an ID");

    }

    @ParameterizedTest
    @ValueSource(
            strings = { "MyWrongRule", "WrongRule:MyWrongRule",
                    "groovy MyWrongRule" })
    void whenMalformedEnginePovided_saveShouldFail(String value) {

        MRule rule = new MRule(ctx, 0, trxName);
        rule.setRuleType(RULETYPE_JSR223ScriptingAPIs);
        rule.setName("TestRule");
        rule.setValue(value);

        Exception e = assertThrows(AdempiereException.class, () -> {
            rule.saveEx();
        });

        assertEquals(
                "Wrong Script Value - format for JSR 223 Script "
                        + "must be engine:scriptName where supported engines "
                        + "must be something like groovy, jython, beanshell",
                e.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = { "groovy", "jython", "beanshell" })
    void whenEngineProvided_saveShouldSucceed(String engineName) {

        MRule rule = new MRule(ctx, 0, trxName);
        rule.setRuleType(RULETYPE_JSR223ScriptingAPIs);
        rule.setName("TestRule");
        rule.setValue(engineName + ":");
        rule.saveEx();

        assertTrue(rule.getAD_Rule_ID() > 0, "Rule not saved as expected");

    }

}
