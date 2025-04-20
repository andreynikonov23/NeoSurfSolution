package org.example.vacation_calculator.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DatePeriod {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOn;
    @JsonFormat(pattern = "yyyy-MM-dd")
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

    public long getDayDiff() {
        return ChronoUnit.DAYS.between(dateOn, dateTo) + 1;
    }

    public HashMap<YearMonth, Integer> getAbsentDaysByMonth() {
        HashMap<YearMonth, Integer> absentDaysByMonth = new HashMap<>();
        YearMonth yearMonth = null;
        LocalDate date = dateOn;
        int absentDays = 0;
        while (!date.isAfter(dateTo)) {
            YearMonth currentYearMonth = YearMonth.from(date);
            if (!currentYearMonth.equals(yearMonth)) {
                yearMonth = currentYearMonth;
                absentDays = 0;
            }
            absentDays++;
            absentDaysByMonth.put(yearMonth, absentDays);
            date = date.plusDays(1);
        }

        return absentDaysByMonth;
    }

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
