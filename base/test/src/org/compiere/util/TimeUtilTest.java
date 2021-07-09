/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A.,              *
 *    All Rights Reserved.                                                    *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import org.adempiere.test.CommonUnitTestSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Contributor(s): Yamel Senih www.erpya.com * Time Utilities Test
 * 
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *         <a href="https://github.com/adempiere/adempiere/issues/1873">
 * @see FR [ 1873 ] Add hepler method to TimeUtil class</a>
 */
@Tag("TimeUtil")
class TimeUtilTest extends CommonUnitTestSetup {

    static long nowMillis = 0;
    static Timestamp now;

    private void assertEqualsDay(Calendar actualDay,
            Calendar expectedDay) {

        assertEquals(expectedDay.get(Calendar.ERA), actualDay.get(Calendar.ERA),
                "ERA");
        assertEquals(expectedDay.get(Calendar.YEAR),
                actualDay.get(Calendar.YEAR), "YEAR");
        assertEquals(expectedDay.get(Calendar.MONTH),
                actualDay.get(Calendar.MONTH), "MONTH");
        assertEquals(expectedDay.get(Calendar.DAY_OF_MONTH),
                actualDay.get(Calendar.DAY_OF_MONTH), "DAY");
        assertEquals(0, actualDay.get(Calendar.HOUR_OF_DAY),
                "HOUR_OF_DAY should be zero");
        assertEquals(0, actualDay.get(Calendar.MINUTE),
                "MINUTE should be zero");
        assertEquals(0, actualDay.get(Calendar.SECOND),
                "SECOND should be zero");
        assertEquals(0, actualDay.get(Calendar.MILLISECOND),
                "MILLISECOND should be zero");

    }

    private void assertEqualsDayTime(Calendar actualDayTime,
            Calendar expectedDayTime) {

        assertEquals(expectedDayTime.get(Calendar.ERA),
                actualDayTime.get(Calendar.ERA),
                "ERA");
        assertEquals(expectedDayTime.get(Calendar.YEAR),
                actualDayTime.get(Calendar.YEAR), "YEAR");
        assertEquals(expectedDayTime.get(Calendar.MONTH),
                actualDayTime.get(Calendar.MONTH), "MONTH");
        assertEquals(expectedDayTime.get(Calendar.DAY_OF_MONTH),
                actualDayTime.get(Calendar.DAY_OF_MONTH), "DAY");
        assertEquals(expectedDayTime.get(Calendar.HOUR_OF_DAY),
                actualDayTime.get(Calendar.HOUR_OF_DAY));
        assertEquals(expectedDayTime.get(Calendar.MINUTE),
                actualDayTime.get(Calendar.MINUTE));
        assertEquals(actualDayTime.get(Calendar.SECOND),
                actualDayTime.get(Calendar.SECOND));
        assertEquals(0, actualDayTime.get(Calendar.MILLISECOND),
                "MILLISECOND should be zero");

    }

    private void assertEqualsDayTime(Timestamp dayTime,
            Timestamp expectedDayTime) {

        GregorianCalendar dayTimeCalendar =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        dayTimeCalendar.setTimeInMillis(dayTime.getTime());

        GregorianCalendar expectedCalendar =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        expectedCalendar.setTimeInMillis(expectedDayTime.getTime());

        assertEqualsDayTime(dayTimeCalendar, expectedCalendar);

    }

    private void assertCalendarEqualsDay(Calendar calendarUnderTest,
            long expectedTimeInMillis) {

        GregorianCalendar calNow =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        calNow.setTimeInMillis(expectedTimeInMillis);

        assertEqualsDay(calendarUnderTest, calNow);

    }

    private void assertTimestampEqualsDay(Timestamp timestampUnderTest,
            long expectedTimeInMillis) {

        GregorianCalendar calTime =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        calTime.setTimeInMillis(timestampUnderTest.getTime());

        GregorianCalendar calNow =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        calNow.setTimeInMillis(expectedTimeInMillis);

        assertEqualsDay(calTime, calNow);

    }

    private void assertTimestampEqualsDay(Timestamp timestampUnderTest,
            Calendar expectedTime) {

        GregorianCalendar calTime =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        calTime.setTimeInMillis(timestampUnderTest.getTime());

        assertEqualsDay(calTime, expectedTime);

    }

    private void assertCalendarEqualsNextDay(Calendar calendarUnderTest,
            long previousDayInMillis) {

        GregorianCalendar nextDay =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        nextDay.setTimeInMillis(previousDayInMillis);

        nextDay.add(Calendar.DAY_OF_YEAR, +1);

        assertEqualsDay(calendarUnderTest, nextDay);

    }

    private void assertTimestampEqualsNextDay(Timestamp nextDay,
            long expectedTimeInMillis) {

        GregorianCalendar calNextDay =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        calNextDay.setTimeInMillis(nextDay.getTime());

        assertCalendarEqualsNextDay(calNextDay, expectedTimeInMillis);

    }

    private void assertTimestampEqualsMonthLastDay(Timestamp dayUnderTest,
            Calendar monthLastDay) {

        GregorianCalendar calendarUnderTest =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        calendarUnderTest.setTimeInMillis(dayUnderTest.getTime());

        assertEqualsDay(calendarUnderTest, monthLastDay);

    }

    private void assertTimestampEqualsMonthLastDay(Timestamp timestampUnderTest,
            long dayInMonthMillis) {

        GregorianCalendar lastDayInMonth =
                new GregorianCalendar(Language.getBaseLanguage().getLocale());
        lastDayInMonth.setTimeInMillis(dayInMonthMillis);

        lastDayInMonth.set(Calendar.DAY_OF_MONTH, 1);
        lastDayInMonth.add(Calendar.MONTH, 1);
        lastDayInMonth.add(Calendar.DAY_OF_YEAR, -1);

        assertTimestampEqualsDay(timestampUnderTest, lastDayInMonth);

    }

    @BeforeEach
    void localSetup() {

        nowMillis = System.currentTimeMillis();
        now = new Timestamp(nowMillis);

    }

    @Test
    void getDay_ifPassedZero_returnsCurrentDay() {

        Timestamp testTime = TimeUtil.getDay(0);
        assertTimestampEqualsDay(testTime, nowMillis);

    }

    @Test
    void getDay_ifPassedNull_returnsCurrentDay() {

        Timestamp testTime = TimeUtil.getDay((Timestamp) null);
        assertTimestampEqualsDay(testTime, nowMillis);

    }

    @Test
    void getDay_ifPassedTimeInMillis_returnsDay() {

        Timestamp testTime = TimeUtil.getDay(nowMillis);
        assertTimestampEqualsDay(testTime, nowMillis);

    }

    @Test
    void getDay_ifPassedTimestamp_returnsDay() {

        Timestamp testTime = TimeUtil.getDay(new Timestamp(nowMillis));
        assertTimestampEqualsDay(testTime, nowMillis);

    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 32 })
    void getDayYMD_ifPassedInvalidDay_throwsIllegalArgumentEx(int day) {

        assertThrows(IllegalArgumentException.class, () -> {
            TimeUtil.getDay(0, 1, day);
        });

    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 13 })
    void getDayYMD_ifPassedInvalidMonth_throwsIllegalArgumentEx(int month) {

        assertThrows(IllegalArgumentException.class, () -> {
            TimeUtil.getDay(0, month, 0);
        });

    }

    static Stream<Arguments> yearMonthDayProvider() {

        return Stream.of(

                arguments(0, 1, 1, new GregorianCalendar(2000, 0, 1)),
                arguments(49, 1, 1, new GregorianCalendar(2049, 0, 1)),
                arguments(50, 1, 1, new GregorianCalendar(1950, 0, 1)),
                arguments(99, 1, 1, new GregorianCalendar(1999, 0, 1)),
                arguments(100, 1, 1, new GregorianCalendar(100, 0, 1)),
                arguments(1000, 1, 1, new GregorianCalendar(1000, 0, 1)),
                arguments(2000, 1, 1, new GregorianCalendar(2000, 0, 1)),
                arguments(1949, 1, 1, new GregorianCalendar(1949, 0, 1)),
                arguments(1950, 1, 1, new GregorianCalendar(1950, 0, 1)),
                arguments(1999, 1, 1, new GregorianCalendar(1999, 0, 1)),
                arguments(2049, 1, 1, new GregorianCalendar(2049, 0, 1)),
                arguments(2050, 1, 1, new GregorianCalendar(2050, 0, 1)),
                arguments(2099, 1, 1, new GregorianCalendar(2099, 0, 1)),
                arguments(2100, 1, 1, new GregorianCalendar(2100, 0, 1)),
                arguments(2020, 1, 31, new GregorianCalendar(2020, 0, 31)),
                arguments(2020, 2, 29, new GregorianCalendar(2020, 1, 29)),
                arguments(2020, 2, 31, new GregorianCalendar(2020, 2, 2)));

    }

    @ParameterizedTest
    @MethodSource("yearMonthDayProvider")
    void getDay_YMD_testValidDates(int year, int month, int day,
            GregorianCalendar expectedDay) {

        Timestamp testTime = TimeUtil.getDay(year, month, day);
        assertTimestampEqualsDay(testTime, expectedDay);

    }

    @Test
    void getToday() {

        Calendar today = TimeUtil.getToday();
        assertCalendarEqualsDay(today, nowMillis);

    }

    @Test
    void getNextDay_givenNull() {

        Timestamp nextDay = TimeUtil.getNextDay(null);
        assertTimestampEqualsNextDay(nextDay, nowMillis);

    }

    @Test
    void getNextDay() {

        Timestamp nextDay = TimeUtil.getNextDay(now);
        assertTimestampEqualsNextDay(nextDay, nowMillis);

    }

    static Stream<Arguments> monthLastDayProvider() {

        return Stream.of(

                arguments(2020, 1, 1, new GregorianCalendar(2020, 0, 31)),
                arguments(2020, 2, 1, new GregorianCalendar(2020, 1, 29)),
                arguments(2020, 12, 31, new GregorianCalendar(2020, 11, 31)),
                arguments(2020, 6, 31, new GregorianCalendar(2020, 6, 31)),
                arguments(2019, 2, 1, new GregorianCalendar(2019, 1, 28))

        );

    }

    @ParameterizedTest
    @MethodSource("monthLastDayProvider")
    void getMonthLastDay(int year, int month, int day, Calendar monthLastDay) {

        Timestamp dayUnderTest = TimeUtil.getDay(year, month, day);
        Timestamp monthLastDayUnderTest =
                TimeUtil.getMonthLastDay(dayUnderTest);
        assertTimestampEqualsMonthLastDay(monthLastDayUnderTest, monthLastDay);

    }

    @Test
    void getMonthLastDay_givenNull() {

        Timestamp nextDay = TimeUtil.getMonthLastDay(null);
        assertTimestampEqualsMonthLastDay(nextDay, nowMillis);

    }

    @Test
    void getDayTime() {

        Timestamp day = now;
        Timestamp time = now;

        Timestamp dayTime = TimeUtil.getDayTime(day, time);
        assertEqualsDayTime(dayTime, now);

    }

    static Stream<Arguments> rangeProvider() {

        return Stream.of(
                arguments(2020, 1, 1, 2020, 1, 31, 2020, 2, 1, 2020, 2, 29,
                        false),
                arguments(2020, 1, 1, 2020, 1, 31, 2020, 1, 31, 2020, 2, 29,
                        false),
                arguments(2020, 1, 1, 2020, 1, 31, 2020, 1, 30, 2020, 2, 29,
                        true),
                arguments(2020, 1, 1, 2020, 1, 31, 2020, 1, 1, 2020, 1, 1,
                        false),
                arguments(2020, 1, 1, 2020, 1, 31, 2020, 1, 2, 2020, 1, 2,
                        true),
                arguments(2020, 1, 1, 2020, 1, 31, 2020, 1, 31, 2020, 1, 31,
                        false),
                arguments(2020, 1, 1, 2020, 1, 31, 2020, 1, 30, 2020, 1, 30,
                        true),
                arguments(2020, 2, 1, 2020, 2, 29, 2020, 1, 1, 2020, 1, 31,
                        false),
                arguments(2020, 2, 1, 2020, 2, 29, 2020, 1, 30, 2020, 2, 1,
                        false),
                arguments(2020, 2, 1, 2020, 2, 29, 2020, 1, 30, 2020, 2, 2,
                        true),
                arguments(2020, 2, 1, 2020, 2, 1, 2020, 2, 1, 2020, 2, 1,
                        false),
                arguments(2020, 2, 1, 2020, 2, 2, 2020, 2, 1, 2020, 2, 2, true)

        );

    }

    @ParameterizedTest
    @MethodSource("rangeProvider")
    void inRange(int y1s, int m1s, int d1s, int y1e, int m1e, int d1e,
            int y2s, int m2s, int d2s, int y2e, int m2e, int d2e,
            boolean expectedResult) {

        Timestamp s1 = TimeUtil.getDay(y1s, m1s, d1s);
        Timestamp e1 = TimeUtil.getDay(y1e, m1e, d1e);
        Timestamp s2 = TimeUtil.getDay(y2s, m2s, d2s);
        Timestamp e2 = TimeUtil.getDay(y2e, m2e, d2e);

        boolean result = TimeUtil.inRange(s1, e1, s2, e2);
        assertEquals(expectedResult, result);

    }

    static Stream<Arguments> badRangeProvider() {

        return Stream.of(

                arguments(2020, 1, 31, 2020, 1, 1, 2020, 2, 1, 2020, 2, 29),
                arguments(2020, 2, 1, 2020, 2, 29, 2020, 1, 31, 2020, 1, 1)

        );

    }

    @ParameterizedTest
    @MethodSource("badRangeProvider")
    void inRange_badRange(int y1s, int m1s, int d1s, int y1e, int m1e, int d1e,
            int y2s, int m2s, int d2s, int y2e, int m2e, int d2e) {

        Timestamp s1 = TimeUtil.getDay(y1s, m1s, d1s);
        Timestamp e1 = TimeUtil.getDay(y1e, m1e, d1e);
        Timestamp s2 = TimeUtil.getDay(y2s, m2s, d2s);
        Timestamp e2 = TimeUtil.getDay(y2e, m2e, d2e);

        assertThrows(UnsupportedOperationException.class, () -> {
            TimeUtil.inRange(s1, e1, s2, e2);
        });

    }

    static Stream<Arguments> inRangeOnDayProvider() {

        boolean nm = true;
        boolean m = false;
        boolean nt = true;
        boolean t = false;
        boolean nw = true;
        boolean w = false;
        boolean nth = true;
        boolean th = false;
        boolean nf = true;
        boolean f = false;
        boolean nsa = true;
        boolean sa = false;
        boolean nsu = true;
        boolean su = false;

        return Stream.of(

                // 1 June 2020 = Monday
                arguments(2020, 6, 1, 2020, 6, 1, nm, nt, nw, nth, nf, nsa, nsu,
                        false),
                arguments(2020, 6, 1, 2021, 6, 1, m, nt, nw, nth, nf, nsa, nsu,
                        true),
                arguments(2020, 6, 1, 2020, 7, 1, m, nt, nw, nth, nf, nsa, nsu,
                        true),
                arguments(2020, 6, 1, 2020, 6, 1, m, t, w, th, f, sa, su, true),
                arguments(2020, 6, 1, 2020, 6, 2, nm, nt, nw, nth, nf, nsa, nsu,
                        false),
                arguments(2020, 6, 1, 2020, 6, 2, m, t, w, th, f, sa, su, true),
                arguments(2020, 6, 1, 2020, 6, 2, nm, t, w, th, f, sa, su,
                        false),
                arguments(2020, 6, 1, 2020, 6, 2, m, nt, w, th, f, sa, su,
                        true),
                arguments(2020, 6, 1, 2020, 6, 2, m, t, nw, th, f, sa, su,
                        true),
                arguments(2020, 6, 1, 2020, 6, 2, m, t, w, nth, f, sa, su,
                        true),
                arguments(2020, 6, 1, 2020, 6, 2, m, t, w, th, nf, sa, su,
                        true),
                arguments(2020, 6, 1, 2020, 6, 2, m, t, w, th, f, nsa, su,
                        true),
                arguments(2020, 6, 1, 2020, 6, 2, m, nt, nw, nth, nf, nsa, nsu,
                        true),
                arguments(2020, 6, 1, 2020, 6, 2, nm, t, nw, nth, nf, nsa, nsu,
                        false),
                arguments(2020, 6, 1, 2020, 6, 3, nm, t, nw, nth, nf, nsa, nsu,
                        false),
                arguments(2020, 6, 1, 2020, 6, 3, nm, nt, w, nth, nf, nsa, nsu,
                        false),
                arguments(2020, 6, 1, 2020, 6, 4, nm, t, nw, nth, nf, nsa, nsu,
                        true),
                arguments(2020, 6, 1, 2020, 6, 4, nm, nt, w, nth, nf, nsa, nsu,
                        false)
        // TODO this doesn't make sense
        );

    }

    @ParameterizedTest
    @MethodSource("inRangeOnDayProvider")
    void inRange_withDay(int y1s, int m1s, int d1s, int y1e, int m1e, int d1e,
            boolean onM, boolean onT, boolean onW, boolean onTh, boolean onF,
            boolean onSa, boolean onSu, boolean expectedResult) {

        Timestamp s1 = TimeUtil.getDay(y1s, m1s, d1s);
        Timestamp e1 = TimeUtil.getDay(y1e, m1e, d1e);

        boolean result =
                TimeUtil.inRange(s1, e1, onM, onT, onW, onTh, onF, onSa, onSu);
        assertEquals(expectedResult, result);

    }

    static Stream<Arguments> sameDayProvider() {

        return Stream.of(
                arguments(null, null, true),
                arguments(null, now, true),
                arguments(now, null, true),
                arguments(TimeUtil.getDay(2000, 1, 1),
                        TimeUtil.getDay(2000, 1, 1), true),
                arguments(TimeUtil.getDay(2000, 1, 1),
                        TimeUtil.getDay(2000, 1, 2), false),
                arguments(TimeUtil.getDay(2000, 2, 1),
                        TimeUtil.getDay(2000, 1, 1), false),
                arguments(TimeUtil.getDay(2001, 1, 1),
                        TimeUtil.getDay(2000, 1, 1), false));

    }

    @ParameterizedTest
    @MethodSource("sameDayProvider")
    void isSameDay(Timestamp day1, Timestamp day2, boolean expectedResult) {

        assertEquals(expectedResult, TimeUtil.isSameDay(day1, day2),
                "sameDay did not provide the result expected");

    }

    @Test
    void isSameDay_whenGivenTheTruncatedTS_returnsTrue() {

        assertTrue(TimeUtil.isSameDay(now, TimeUtil.getDay(now)),
                "When provided the same timestamp, one truncated, should return true");

    }

    @Test
    void isSameHour_whenPassedNull() {

        assertTrue(TimeUtil.isSameHour(null, null));

    }

    static Stream<Arguments> hourOfDayProvider() {

        return Stream.of(

                arguments(2002, 06, 01, 01, 2002, 06, 01, 01, true),
                arguments(2002, 06, 01, 01, 2002, 06, 01, 02, false),
                arguments(2002, 06, 01, 01, 2001, 06, 01, 01, true),
                arguments(2002, 06, 01, 01, 2002, 07, 01, 01, true),
                arguments(2002, 06, 01, 01, 2002, 06, 02, 01, true));

    }

    @ParameterizedTest
    @MethodSource("hourOfDayProvider")
    void isSameHour(int y1, int m1, int d1, int h1, int y2, int m2, int d2,
            int h2, boolean expectedResult) {

        GregorianCalendar cal1 = new GregorianCalendar(y1, m1 - 1, d1);
        cal1.set(Calendar.HOUR_OF_DAY, h1);
        GregorianCalendar cal2 = new GregorianCalendar(y1, m1 - 1, d1);
        cal2.set(Calendar.HOUR_OF_DAY, h2);

        Timestamp t1 = new Timestamp(cal1.getTimeInMillis());
        Timestamp t2 = new Timestamp(cal2.getTimeInMillis());

        boolean result = TimeUtil.isSameHour(t1, t2);
        assertEquals(expectedResult, result);

    }

    static Stream<Arguments> dateToYearProvider() {

        return Stream.of(

                arguments(1970, 01, 01),
                arguments(2001, 12, 31),
                arguments(2010, 05, 10));

    }

    @ParameterizedTest
    @MethodSource("dateToYearProvider")
    void getYearFromTimestamp(int yr, int m, int d) {

        assertEquals(yr,
                TimeUtil.getYearFromTimestamp(TimeUtil.getDay(yr, m, d)));

    }

    @Test
    void whenPassedNull_getYearFromTimestampThrowsNPE() {

        assertThrows(NullPointerException.class, () -> {
            TimeUtil.getYearFromTimestamp(null);
        });

    }

    @Test
    void whenPassedZero_getYearFromTimestampReturns1970() {

        assertEquals(1970, TimeUtil.getYearFromTimestamp(new Timestamp(0)));

    }

}
