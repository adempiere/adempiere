package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Wave4FunctionsTest {

    @Test
    void nextID_methodExists() {
        assertDoesNotThrow(() -> {
            var method = Wave4Functions.class.getMethod(
                "nextID", Integer.class, String.class, String.class);
            assertNotNull(method);
        });
    }

    @Test
    void nextIDFunc_methodExists() {
        assertDoesNotThrow(() -> {
            var method = Wave4Functions.class.getMethod(
                "nextIDFunc", Integer.class, String.class, String.class);
            assertNotNull(method);
        });
    }
}
