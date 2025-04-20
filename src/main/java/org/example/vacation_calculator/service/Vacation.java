package org.example.vacation_calculator.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.vacation_calculator.util.DatePeriod;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vacation {
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate startDate;
    private DatePeriod vacationPeriod;
    private double incomeSum;
    private ArrayList<DatePeriod> absentPeriods;

    public Vacation(){}

    public Vacation(LocalDate startDate, DatePeriod vacationPeriod, double incomeSum, ArrayList<DatePeriod> absentPeriods) {
        this.startDate = startDate;
        this.vacationPeriod = vacationPeriod;
        this.incomeSum = incomeSum;
        this.absentPeriods = absentPeriods;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public DatePeriod getVacationPeriod() {
        return vacationPeriod;
    }

    public void setVacationPeriod(DatePeriod vacationPeriod) {
        this.vacationPeriod = vacationPeriod;
    }

    public double getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(double incomeSum) {
        this.incomeSum = incomeSum;
    }

    public ArrayList<DatePeriod> getAbsentPeriods() {
        return absentPeriods;
    }

    public void setAbsentPeriods(ArrayList<DatePeriod> absentPeriods) {
        this.absentPeriods = absentPeriods;
    }

    @Override
    public String toString() {
        return String.format("Vacation[vacationPeriod: %s; incomeSum: %f]", vacationPeriod, incomeSum);
    }

    @Override
    public int hashCode() {
        return (int) (31 * startDate.hashCode() * vacationPeriod.hashCode() * incomeSum * absentPeriods.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (hashCode() != obj.hashCode()) return false;

        Vacation otherVacation = (Vacation) obj;

        return startDate.equals(otherVacation.getStartDate()) && vacationPeriod.equals(otherVacation.getVacationPeriod()) &&
                incomeSum == otherVacation.getIncomeSum() && absentPeriods.equals(otherVacation.getAbsentPeriods());
    }

    public double calculatePay() {
        DatePeriod billingPeriod = getBillingPeriod();

        HashMap<YearMonth, Integer> workedDays = billingPeriod.getAbsentDaysByMonth();

        HashMap<YearMonth, Integer> absentDays = getAbsentDaysByMonths();

        absentDays.forEach((yearMonth, absentDay) -> workedDays.compute(yearMonth, (k, newValue) -> newValue - absentDay));

        double daysWorkedSum = 0;
        for(Map.Entry<YearMonth, Integer> entry : workedDays.entrySet()) {
            YearMonth yearMonth = entry.getKey();
            int workingDaysMonth = entry.getValue();
            int lengthOfMonth = yearMonth.lengthOfMonth();

            daysWorkedSum += (double) workingDaysMonth / lengthOfMonth * 29.3;
        }

        double averageDailyEarning = incomeSum / daysWorkedSum;
        long vacationDays = vacationPeriod.getDayDiff();
        double result = averageDailyEarning * vacationDays;
        return (double) Math.round(result * 100) / 100;
    }

    public DatePeriod getBillingPeriod() {
        long monthDiff = ChronoUnit.MONTHS.between(startDate, vacationPeriod.getDateOn());
        if (monthDiff > 12) {
            startDate = vacationPeriod.getDateOn().minusYears(1);
        }
        return new DatePeriod(startDate, vacationPeriod.getDateOn().minusDays(1));
    }

    public HashMap<YearMonth, Integer> getAbsentDaysByMonths() {
        if (absentPeriods == null || absentPeriods.isEmpty()) {
            return new HashMap<>();
        }

        return absentPeriods.stream().map(DatePeriod::getAbsentDaysByMonth).reduce(new HashMap<>(), (result, absentDaysByMonths) -> {
            absentDaysByMonths.forEach((yearMonth, absentDays) -> result.merge(yearMonth, absentDays, Integer::sum));
            return result;
        });
    }
}
