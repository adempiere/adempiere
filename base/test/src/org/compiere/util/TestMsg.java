package org.compiere.util;

import static org.compiere.util.Msg.MSG_TOKEN;
import static org.compiere.util.Msg.wrapMsg;
import static org.junit.jupiter.api.Assertions.*;

import org.adempiere.test.CommonUnitTestSetup;
import org.junit.jupiter.api.Test;


class TestMsg extends CommonUnitTestSetup {

    @Test
    final void whenPassedAString_ThenWrapMethodShouldReturnWrappedString() {
        
        assertEquals(MSG_TOKEN + "TestString" + MSG_TOKEN, 
                wrapMsg("TestString"));

    }

}
