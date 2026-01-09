package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Wave5FunctionRouterTest {

    @BeforeEach
    void setUp() {
        MigrationConfig.clearCache();
    }

    @Test
    void testRouterClassExists() {
        assertNotNull(Wave5FunctionRouter.class);
    }
}
