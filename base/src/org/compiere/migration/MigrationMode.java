// base/src/org/compiere/migration/MigrationMode.java
package org.compiere.migration;

/**
 * Migration modes for function cutover.
 * DUAL_WRITE intentionally omitted - not needed for stateless Wave 0 functions.
 */
public enum MigrationMode {
    SQL_ONLY,    // Legacy path only
    SHADOW,      // Both execute, compare, return Java
    JAVA_ONLY    // Java only, SQL can be deleted
}
