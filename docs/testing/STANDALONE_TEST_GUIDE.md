# ADempiere Standalone Test Guide

This guide explains how to run ADempiere tests standalone using the Garden World sample database.

## Prerequisites

- **Java 11+** - OpenJDK or Oracle JDK
- **Gradle 7+** - Or use included wrapper (if available)
- **PostgreSQL 14+** - With Garden World seed restored
- **Ant 1.10+** - For initial database setup (optional if seed already loaded)

## Quick Start

### 1. Set Up PostgreSQL with Garden World

```bash
# Create database and user
sudo -u postgres psql << EOF
CREATE USER adempiere WITH PASSWORD 'adempiere';
CREATE DATABASE adempiere OWNER adempiere;
GRANT ALL PRIVILEGES ON DATABASE adempiere TO adempiere;
EOF

# Import Garden World seed
cd data/seed
unzip Adempiere_pg.jar -d /tmp/adempiere_seed
sudo -u postgres pg_restore -d adempiere /tmp/adempiere_seed/Adempiere_pg.dmp
```

### 2. Configure ADempiere Properties

```bash
# Copy template to your home directory
cp Adempiere.properties.test.template ~/.Adempiere.properties

# Or set ADEMPIERE_HOME and copy there
export ADEMPIERE_HOME=/path/to/adempiere
cp Adempiere.properties.test.template $ADEMPIERE_HOME/Adempiere.properties

# Edit the file to match your database settings
# Key settings:
#   DBhost=localhost
#   DBport=5432
#   DBname=adempiere
#   UID=adempiere
#   PWD=adempiere
```

### 3. Run Tests

```bash
# Run integration tests (requires database)
gradle :base:test:integrationTest

# Run unit tests only (no database required)
gradle :base:test:unitTest

# Run specific test class
gradle :base:test:test --tests "org.compiere.model.IT_MBPartner"

# Run tests with specific tag
gradle :base:test:test -PincludeTags=Model
```

## Test Categories

| Tag | Description | Database Required |
|-----|-------------|-------------------|
| `UnitTest` | Pure unit tests, no external dependencies | No |
| `IntegrationTest` | Tests requiring database connection | Yes |
| `Model` | Model class tests (M* classes) | Yes |
| `Process` | Process/Report tests | Yes |

## Test Base Classes

### For Garden World Integration Tests

```java
import org.adempiere.test.CommonGWSetup;

class MyTest extends CommonGWSetup {
    @Test
    void testSomething() {
        // Uses Garden World data:
        // AD_CLIENT_ID = 11 (Garden World)
        // AD_ORG_ID = 11 (HQ)
        // AD_USER_ID = 100 (GardenAdmin)
    }
}
```

### For System-Level Tests

```java
import org.adempiere.test.CommonSystemSetup;

class MySystemTest extends CommonSystemSetup {
    @Test
    void testSomething() {
        // Uses System client:
        // AD_CLIENT_ID = 0
        // AD_ORG_ID = 0
        // AD_USER_ID = 0
    }
}
```

### For Unit Tests (No Database)

```java
import org.adempiere.test.CommonUnitTestSetup;

class MyUnitTest extends CommonUnitTestSetup {
    @Test
    void testSomething() {
        // No database connection
        // Context is mocked with GW IDs
    }
}
```

## Garden World Test Data

The `CommonGWData` class provides constants for Garden World entities:

```java
CommonGWData.AD_CLIENT_ID       // 11 - Garden World client
CommonGWData.AD_ORG_ID          // 11 - HQ organization
CommonGWData.AD_USER_ID         // 100 - GardenAdmin user
CommonGWData.USD_CURRENCY_ID    // 100 - US Dollar
CommonGWData.HQ_WAREHOUSE_ID    // 103 - HQ Warehouse
CommonGWData.SEEDFARM_ID        // 120 - Seed Farm business partner
CommonGWData.AZALEA_BUSH_PRODUCT_ID  // 128 - Azalea Bush product
```

## Transaction Management

Tests automatically rollback after each test method:

```java
@BeforeEach
public void setUp() {
    // Creates savepoint before each test
    testSavepoint = trx.setSavepoint("SingleTest_xxx");
}

@AfterEach
public void tearDown() {
    // Rolls back to savepoint
    trx.rollback(testSavepoint);
}
```

## Troubleshooting

### "Cannot connect to database"

1. Verify PostgreSQL is running: `pg_isready -h localhost -p 5432`
2. Check database exists: `psql -h localhost -U adempiere -d adempiere -c '\dt'`
3. Verify Adempiere.properties has correct connection string

### "No tests found"

1. Ensure test classes have `@Test` annotation
2. Check test class extends appropriate base class
3. Verify JUnit 5 is on classpath

### "ADEMPIERE_HOME not set"

```bash
export ADEMPIERE_HOME=/path/to/adempiere
# Or pass as system property
gradle test -DADEMPIERE_HOME=/path/to/adempiere
```

### "Java module access errors"

The Gradle test tasks include required JVM arguments. If running manually:

```bash
java --add-exports java.base/jdk.internal.misc=ALL-UNNAMED \
     --add-opens java.base/java.lang=ALL-UNNAMED \
     -Dfile.encoding=UTF-8 \
     ...
```

## CI/CD Integration

### GitHub Actions Example

```yaml
jobs:
  test:
    runs-on: ubuntu-latest
    services:
      postgres:
        image: postgres:14
        env:
          POSTGRES_PASSWORD: postgres
        ports:
          - 5432:5432
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '11'
      - name: Setup Database
        run: |
          # Create database and import seed
          PGPASSWORD=postgres psql -h localhost -U postgres -c "CREATE USER adempiere WITH PASSWORD 'adempiere';"
          PGPASSWORD=postgres psql -h localhost -U postgres -c "CREATE DATABASE adempiere OWNER adempiere;"
          # Import seed...
      - name: Run Tests
        run: gradle :base:test:integrationTest
        env:
          ADEMPIERE_HOME: ${{ github.workspace }}
```

## Writing New Tests

### Integration Test Template

```java
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.*;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("IntegrationTest")
@Tag("Model")
class IT_MyModel extends CommonGWSetup {

    @Test
    void testCreateRecord() {
        // Arrange
        MBPartner bp = new MBPartner(getCtx(), 0, getTrxName());
        bp.setName("Test Partner");
        bp.setValue("TEST_" + System.currentTimeMillis());

        // Act
        bp.saveEx();

        // Assert
        assertTrue(bp.getC_BPartner_ID() > 0);
        // Record will be rolled back after test
    }
}
```

### Unit Test Template

```java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import org.adempiere.test.CommonUnitTestSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
class TimeUtilTest extends CommonUnitTestSetup {

    @Test
    void testDaysBetween() {
        // No database required
        Timestamp t1 = Timestamp.valueOf("2024-01-01 00:00:00");
        Timestamp t2 = Timestamp.valueOf("2024-01-10 00:00:00");

        assertEquals(9, TimeUtil.getDaysBetween(t1, t2));
    }
}
```
