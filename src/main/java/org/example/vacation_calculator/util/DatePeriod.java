package org.example.vacation_calculator.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;

public class DatePeriod {
    private LocalDate dateOn;
    private LocalDate dateTo;

    public DatePeriod(LocalDate dateOn, LocalDate dateTo) {
        this.dateOn = dateOn;
        this.dateTo = dateTo;
    }

    public LocalDate getDateOn() {
        return dateOn;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public HashMap<Integer, Integer> getAbsentDaysByMonth() {
        HashMap<Integer, Integer> absentDaysByMonth = new HashMap<>();
        int month = 0;
        LocalDate date = dateOn;
        int absentDays = 0;
        while (!date.isAfter(dateTo)) {
            if (date.getMonthValue() != month) {
                month = date.getMonthValue();
                absentDays = 0;
            }
            absentDays++;
            absentDaysByMonth.put(month, absentDays);
            date = date.plusDays(1);
        }
        return absentDaysByMonth;

    }

//    public HashMap<Integer, Integer> getPaidDaysByMonths() {
//        HashMap<Integer, Integer> paidDaysByMonth = new HashMap<>();
//        int month = 0;
//        LocalDate date = dateOn.with(TemporalAdjusters.firstDayOfMonth());
//        LocalDate endDate = dateTo.with(TemporalAdjusters.lastDayOfMonth());
//
//        int paidDays = 0;
//        while (!date.isAfter(endDate)) {
//            if (date.getMonthValue() != month) {
//                month = date.getMonthValue();
//                paidDays = 0;
//                paidDaysByMonth.put(month, paidDays);
//            }
//
//            boolean isAbsent = false;
//            if (!date.isBefore(dateOn) && !date.isAfter(dateTo)) {
//                isAbsent = true;
//            }
//
//            if(!isAbsent) {
//                paidDays++;
//                paidDaysByMonth.put(month, paidDays);
//            }
//
//            date = date.plusDays(1);
//        }
//        return paidDaysByMonth;
//    }

    @Override
    public String toString() {
        return String.format("{%s; %s}", dateOn, dateTo);
    }

    @Override
    public int hashCode() {
        return 37 * dateOn.hashCode() * dateTo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (hashCode() != obj.hashCode()) return false;

        DatePeriod otherDatePeriod = (DatePeriod) obj;

        return dateOn.equals(otherDatePeriod.dateOn) && dateTo.equals(otherDatePeriod.dateTo);
    }
}
