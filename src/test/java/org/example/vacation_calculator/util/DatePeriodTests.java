package org.example.vacation_calculator.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

public class DatePeriodTests {
    @Test
    public void getAbsentDaysByMonthWithBigMissed() {
        HashMap<Integer, Integer> absentDaysByMonth = new HashMap<>();
        absentDaysByMonth.put(4, 19);
        absentDaysByMonth.put(5, 31);
        absentDaysByMonth.put(6, 13);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 6, 13));

        Assertions.assertEquals(absentDaysByMonth, datePeriod.getAbsentDaysByMonth());
    }
    @Test
    public void getAbsentDaysByMonthWithOneMonth() {
        HashMap<Integer, Integer> absentDaysByMonth = new HashMap<>();
        absentDaysByMonth.put(4, 3);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 4, 14));

        Assertions.assertEquals(absentDaysByMonth, datePeriod.getAbsentDaysByMonth());
    }

//    @Test
//    public void getPaidDaysByMonthsWithBigMissed() {
//        HashMap<Integer, Integer> paidDaysByMonth = new HashMap<>();
//        paidDaysByMonth.put(4, 11);
//        paidDaysByMonth.put(5, 0);
//        paidDaysByMonth.put(6, 17);
//
//        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 6, 13));
//
//        Assertions.assertEquals(paidDaysByMonth, datePeriod.getPaidDaysByMonths());
//    }
//
//    @Test
//    public void getPaidDaysByMonthsWithOneMonth() {
//        HashMap<Integer, Integer> paidDaysByMonth = new HashMap<>();
//        paidDaysByMonth.put(4, 25);
//
//        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 4, 16));
//
//        Assertions.assertEquals(paidDaysByMonth, datePeriod.getPaidDaysByMonths());
//    }
//
//    @Test
//    public void getPaidDaysByMonthsWithTwoMonths() {
//        HashMap<Integer, Integer> paidDaysByMonth = new HashMap<>();
//        paidDaysByMonth.put(4, 11);
//        paidDaysByMonth.put(5, 21);
//
//        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2025, 4, 12), LocalDate.of(2025, 5, 10));
//
//        Assertions.assertEquals(paidDaysByMonth, datePeriod.getPaidDaysByMonths());
//    }
}
