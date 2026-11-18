package org.compiere.migration;

/**
 * Exception thrown when SQL function execution fails.
 * Allows ShadowExecutor to distinguish database failures from NULL results.
 * This is a RuntimeException to avoid polluting method signatures,
 * but should be caught explicitly in ShadowExecutor.
 */
public class SqlFunctionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String functionName;

    public SqlFunctionException(String functionName, Throwable cause) {
        super("SQL function failed: " + functionName, cause);
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }
}
