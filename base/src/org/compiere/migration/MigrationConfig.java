// base/src/org/compiere/migration/MigrationConfig.java
package org.compiere.migration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MigrationConfig {
    private static final CLogger log = CLogger.getCLogger(MigrationConfig.class);
    private static final ConcurrentHashMap<String, CachedConfig> cache = new ConcurrentHashMap<>();
    private static final long CACHE_TTL_MS = 60_000; // 60 seconds

    // Lazy timezone validation (not in static initializer - DB may not be ready)
    private static volatile boolean timezoneValidated = false;

    private final String functionName;
    private final MigrationMode mode;
    private final double sampleRate;
    private final boolean circuitBreakerEnabled;

    private MigrationConfig(String functionName, MigrationMode mode, double sampleRate, boolean circuitBreakerEnabled) {
        this.functionName = functionName;
        this.mode = mode;
        this.sampleRate = sampleRate;
        this.circuitBreakerEnabled = circuitBreakerEnabled;
    }

    /**
     * Get configuration for a function with caching.
     * Uses double-check pattern to prevent cache stampede under high concurrency.
     */
    public static MigrationConfig get(String functionName) {
        // Lazy timezone validation on first call when DB is ready
        if (!timezoneValidated && DB.isConnected()) {
            validateTimezoneAlignmentOnce();
        }

        // Fast path: check cache without locking
        CachedConfig cached = cache.get(functionName);
        if (cached != null && !cached.isExpired()) {
            return cached.config;
        }

        // Slow path: compute under lock (per-key)
        return cache.compute(functionName, (k, v) -> {
            // Double-check inside compute (now under lock for this key)
            if (v != null && !v.isExpired()) return v;
            return loadFromDatabase(functionName);
        }).config;
    }

    private static synchronized void validateTimezoneAlignmentOnce() {
        if (timezoneValidated) return;
        try {
            String sql = "SELECT current_setting('TIMEZONE')";
            PreparedStatement pstmt = DB.prepareStatement(sql, null);
            if (pstmt == null) {
                log.warning("Cannot validate timezone - statement preparation failed");
                return;
            }
            try (pstmt; ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String pgTimezone = rs.getString(1);
                    String jvmTimezone = java.util.TimeZone.getDefault().getID();
                    if (!pgTimezone.equals(jvmTimezone)) {
                        log.warning("Timezone mismatch: PostgreSQL=" + pgTimezone +
                                   ", JVM=" + jvmTimezone +
                                   ". Shadow mode comparisons may show false mismatches.");
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to validate timezone alignment", e);
        } finally {
            timezoneValidated = true;
        }
    }

    private static CachedConfig loadFromDatabase(String functionName) {
        String sql = "SELECT mode, sample_rate, circuit_breaker_enabled " +
                     "FROM migration.function_config WHERE function_name = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            if (pstmt == null) {
                log.warning("Cannot prepare statement, defaulting to SQL_ONLY for " + functionName);
                return new CachedConfig(
                    new MigrationConfig(functionName, MigrationMode.SQL_ONLY, 1.0, true),
                    true); // isFallback = true
            }
            pstmt.setString(1, functionName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    MigrationMode mode = MigrationMode.valueOf(rs.getString("mode"));
                    double sampleRate = rs.getDouble("sample_rate");
                    if (sampleRate < 0.0 || sampleRate > 1.0) {
                        log.warning("Invalid sample_rate " + sampleRate + " for " + functionName + ", using 1.0");
                        sampleRate = 1.0;
                    }
                    boolean circuitBreaker = rs.getBoolean("circuit_breaker_enabled");
                    return new CachedConfig(
                        new MigrationConfig(functionName, mode, sampleRate, circuitBreaker),
                        false); // isFallback = false
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to load migration config for " + functionName + ", defaulting to SQL_ONLY", e);
        } finally {
            DB.close(pstmt);
        }
        // Default: SQL_ONLY if not found or error (marked as fallback)
        return new CachedConfig(
            new MigrationConfig(functionName, MigrationMode.SQL_ONLY, 1.0, true),
            true); // isFallback = true
    }

    public static MigrationConfig sqlOnly(String functionName) {
        return new MigrationConfig(functionName, MigrationMode.SQL_ONLY, 1.0, true);
    }

    public String getFunctionName() { return functionName; }
    public MigrationMode getMode() { return mode; }
    /** Used by ShadowExecutor in Part 2 for probabilistic sampling. */
    public double getSampleRate() { return sampleRate; }
    public boolean isCircuitBreakerEnabled() { return circuitBreakerEnabled; }

    private static class CachedConfig {
        final MigrationConfig config;
        final long cachedAt;
        final boolean isFallback;

        CachedConfig(MigrationConfig config, boolean isFallback) {
            this.config = config;
            this.cachedAt = System.currentTimeMillis();
            this.isFallback = isFallback;
        }

        boolean isExpired() {
            // Fallback configs expire immediately so we retry on next call
            if (isFallback) return true;
            return System.currentTimeMillis() - cachedAt > CACHE_TTL_MS;
        }
    }
}
