package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NextIDRouterTest {

    @Test
    void classExists() {
        assertDoesNotThrow(() -> Class.forName("org.compiere.migration.NextIDRouter"));
    }

    @Test
    void nextID_methodExists() {
        assertDoesNotThrow(() -> {
            var method = NextIDRouter.class.getMethod(
                "nextID", Integer.class, String.class, String.class);
            assertNotNull(method);
        });
    }
}
