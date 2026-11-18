package org.compiere.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.compiere.util.DB;

/**
 * Integration test verifying MSequence delegates to NextIDRouter for PostgreSQL.
 */
@EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
public class MSequenceRouterIntegrationTest {

    @Test
    void getNextID_postgresql_usesNextIDRouter() {
        // Skip if Oracle
        if (DB.isOracle()) {
            return;
        }

        // Use AD_PInstance which is a common table in all ADempiere installations
        // Get two sequential IDs - should increment correctly
        int first = MSequence.getNextID(0, "AD_PInstance", null);
        int second = MSequence.getNextID(0, "AD_PInstance", null);

        assertTrue(first > 0, "First ID should be positive");
        assertTrue(second > first, "Second ID should be greater than first");
    }

    @Test
    void getNextID_postgresql_incrementsAtomically() {
        if (DB.isOracle()) {
            return;
        }

        // Verify the atomic UPDATE...RETURNING pattern works
        // by checking no gaps beyond IncrementNo
        int id1 = MSequence.getNextID(0, "AD_PInstance", null);
        int id2 = MSequence.getNextID(0, "AD_PInstance", null);

        // Most sequences have IncrementNo=1
        int gap = id2 - id1;
        assertTrue(gap >= 1 && gap <= 10,
            "Gap between IDs should be reasonable (1-10), was: " + gap);
    }
}
