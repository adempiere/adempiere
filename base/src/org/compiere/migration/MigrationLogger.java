// base/src/org/compiere/migration/MigrationLogger.java
package org.compiere.migration;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MigrationLogger {
    private static final CLogger log = CLogger.getCLogger(MigrationLogger.class);
    private static final int QUEUE_CAPACITY = 10_000;
    private static final int BATCH_SIZE = 100;
    private static final long SHUTDOWN_DRAIN_TIMEOUT_MS = Long.getLong(
        "migration.shutdown.timeout.ms", 5000);
    private static final BlockingQueue<LogEntry> queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
    private static final AtomicLong droppedCount = new AtomicLong(0);
    private static volatile boolean running = true;
    private static volatile boolean databaseEnabled = true;
    private static final Thread drainThread;

    static {
        drainThread = new Thread(MigrationLogger::drainQueue, "MigrationLogger-Drain");
        drainThread.setDaemon(true);
        drainThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running = false;
            drainThread.interrupt();

            // Wait for drain thread to stop before we drain ourselves
            try {
                drainThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Batch drain remaining entries
            List<LogEntry> batch = new ArrayList<>(BATCH_SIZE);
            long deadline = System.currentTimeMillis() + SHUTDOWN_DRAIN_TIMEOUT_MS;
            int drained = 0;
            while (!queue.isEmpty() && System.currentTimeMillis() < deadline) {
                batch.clear();
                queue.drainTo(batch, BATCH_SIZE);
                if (!batch.isEmpty()) {
                    drained += batch.size();
                    writeBatchToDatabase(batch);
                }
            }
            if (!queue.isEmpty()) {
                log.warning("MigrationLogger shutdown: drained " + drained + ", " + queue.size() + " entries still pending");
            } else if (drained > 0) {
                log.info("MigrationLogger shutdown: drained " + drained + " entries successfully");
            }
        }, "MigrationLogger-Shutdown"));
    }

    public static void logAsync(String functionName, String inputParams, String sqlResult,
                                 String javaResult, long sqlTimeMs, long javaTimeMs,
                                 boolean isMatch, String mismatchReason) {
        LogEntry entry = new LogEntry(functionName, inputParams, sqlResult, javaResult,
                                       sqlTimeMs, javaTimeMs, isMatch, mismatchReason);
        if (!queue.offer(entry)) {
            long dropped = droppedCount.incrementAndGet();
            if (dropped % 1000 == 0) {
                log.warning("Migration log queue full, " + dropped + " entries dropped total");
            }
        }
    }

    public static long getDroppedCount() {
        return droppedCount.get();
    }

    public static int getQueueDepth() {
        return queue.size();
    }

    /** Disable database writes for unit testing. */
    static void setDatabaseEnabled(boolean enabled) {
        databaseEnabled = enabled;
    }

    /** Reset state for unit testing. Package-private to limit scope. */
    static void resetForTesting() {
        queue.clear();
        droppedCount.set(0);
        databaseEnabled = false;
    }

    private static void drainQueue() {
        List<LogEntry> batch = new ArrayList<>(BATCH_SIZE);
        while (running) {
            try {
                // Wait for first entry (shorter timeout during shutdown)
                LogEntry entry = queue.poll(running ? 100 : 10, TimeUnit.MILLISECONDS);
                if (entry != null) {
                    batch.add(entry);
                    // Drain up to BATCH_SIZE - 1 more entries
                    queue.drainTo(batch, BATCH_SIZE - 1);
                    writeBatchToDatabase(batch);
                    batch.clear();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                log.log(Level.WARNING, "Failed to write migration log batch", e);
                batch.clear();
            }
        }
    }

    private static void writeBatchToDatabase(List<LogEntry> entries) {
        if (entries.isEmpty()) return;

        if (!databaseEnabled) {
            return; // Skip DB writes during unit tests
        }

        if (!DB.isConnected()) {
            log.warning("Database not connected, " + entries.size() + " migration log entries dropped");
            return;
        }

        String sql = "INSERT INTO migration.function_log " +
                     "(function_name, input_params, sql_result, java_result, " +
                     "sql_time_ms, java_time_ms, is_match, mismatch_reason) " +
                     "VALUES (?, ?::jsonb, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        if (pstmt == null) {
            log.warning("Failed to prepare statement, " + entries.size() + " entries dropped");
            return;
        }

        try (pstmt) {
            for (LogEntry entry : entries) {
                pstmt.setString(1, entry.functionName);
                pstmt.setString(2, entry.inputParams);
                pstmt.setString(3, entry.sqlResult);
                pstmt.setString(4, entry.javaResult);
                pstmt.setLong(5, entry.sqlTimeMs);
                pstmt.setLong(6, entry.javaTimeMs);
                pstmt.setBoolean(7, entry.isMatch);
                pstmt.setString(8, entry.mismatchReason);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to insert migration log batch of " + entries.size(), e);
        }
    }

    public static void shutdown() {
        running = false;
    }

    private static class LogEntry {
        final String functionName;
        final String inputParams;
        final String sqlResult;
        final String javaResult;
        final long sqlTimeMs;
        final long javaTimeMs;
        final boolean isMatch;
        final String mismatchReason;

        LogEntry(String functionName, String inputParams, String sqlResult,
                 String javaResult, long sqlTimeMs, long javaTimeMs,
                 boolean isMatch, String mismatchReason) {
            this.functionName = functionName;
            this.inputParams = inputParams;
            this.sqlResult = sqlResult;
            this.javaResult = javaResult;
            this.sqlTimeMs = sqlTimeMs;
            this.javaTimeMs = javaTimeMs;
            this.isMatch = isMatch;
            this.mismatchReason = mismatchReason;
        }
    }
}
