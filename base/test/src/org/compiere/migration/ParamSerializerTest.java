// base/test/src/org/compiere/migration/ParamSerializerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class ParamSerializerTest {

    @Test
    void testEmptyParams() {
        assertEquals("[]", ParamSerializer.toJson());
    }

    @Test
    void testNullParams() {
        assertEquals("[]", ParamSerializer.toJson((Object[]) null));
    }

    @Test
    void testSingleString() {
        assertEquals("[\"hello\"]", ParamSerializer.toJson("hello"));
    }

    @Test
    void testTimestamp() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 14:30:00");
        assertEquals("[\"2026-01-15 14:30:00.0\"]", ParamSerializer.toJson(ts));
    }

    @Test
    void testSqlDate() {
        java.sql.Date date = java.sql.Date.valueOf("2026-01-15");
        assertEquals("[\"2026-01-15\"]", ParamSerializer.toJson(date));
    }

    @Test
    void testBigDecimal() {
        assertEquals("[123.456]", ParamSerializer.toJson(new BigDecimal("123.456")));
    }

    @Test
    void testMixedParams() {
        String result = ParamSerializer.toJson("hello", 42, null, true);
        assertEquals("[\"hello\",42,null,true]", result);
    }

    @Test
    void testEscapesSpecialChars() {
        String result = ParamSerializer.toJson("line1\nline2\ttab\"quote");
        assertEquals("[\"line1\\nline2\\ttab\\\"quote\"]", result);
    }
}
