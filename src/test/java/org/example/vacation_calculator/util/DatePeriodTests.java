package org.example.vacation_calculator.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

public class DatePeriodTests {
    @Test
    public void getAbsentDaysByMonthWithBigMissed() {
        HashMap<YearMonth, Integer> absentDaysByMonth = new HashMap<>();
        absentDaysByMonth.put(YearMonth.of(2025, 4), 19);
        absentDaysByMonth.put(YearMonth.of(2025, 5), 31);
        absentDaysByMonth.put(YearMonth.of(2025, 6), 13);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 6, 13));

        Assertions.assertEquals(absentDaysByMonth, datePeriod.getAbsentDaysByMonth());
    }
    @Test
    public void getAbsentDaysByMonthWithTwoMonth() {
        HashMap<YearMonth, Integer> absentDaysByMonth = new HashMap<>();
        absentDaysByMonth.put(YearMonth.of(2024, 11), 27);
        absentDaysByMonth.put(YearMonth.of(2024, 12), 18);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2024, 11, 4), LocalDate.of(2024, 12, 18));

        Assertions.assertEquals(absentDaysByMonth, datePeriod.getAbsentDaysByMonth());
    }
    @Test
    public void getAbsentDaysByMonthWithOneMonth() {
        HashMap<YearMonth, Integer> absentDaysByMonth = new HashMap<>();
        absentDaysByMonth.put(YearMonth.of(2025, 4), 3);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 4, 14));

        Assertions.assertEquals(absentDaysByMonth, datePeriod.getAbsentDaysByMonth());
    }
}
