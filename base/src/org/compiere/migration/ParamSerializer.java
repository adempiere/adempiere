// base/src/org/compiere/migration/ParamSerializer.java
package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Simple JSON serialization for shadow mode parameter logging.
 * Avoids external dependencies - produces valid JSON for common types.
 */
public class ParamSerializer {

    public static String toJson(Object... params) {
        if (params == null || params.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < params.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(serializeValue(params[i]));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String serializeValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "\"" + escapeJson((String) value) + "\"";
        } else if (value instanceof Timestamp) {
            return "\"" + value.toString() + "\"";
        } else if (value instanceof java.sql.Date) {
            return "\"" + value.toString() + "\"";
        } else if (value instanceof java.util.Date) {
            // Handle java.util.Date (convert to Timestamp format)
            return "\"" + new Timestamp(((java.util.Date) value).getTime()).toString() + "\"";
        } else if (value instanceof BigDecimal) {
            return ((BigDecimal) value).toPlainString();
        } else if (value instanceof Number) {
            return value.toString();
        } else if (value instanceof Boolean) {
            return value.toString();
        } else {
            return "\"" + escapeJson(value.toString()) + "\"";
        }
    }

    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
