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
    private ArrayList<DatePeriod> missedPeriods;

    public Vacation(){}

    public Vacation(LocalDate startDate, DatePeriod vacationPeriod, double incomeSum, ArrayList<DatePeriod> missedPeriods) {
        this.startDate = startDate;
        this.vacationPeriod = vacationPeriod;
        this.incomeSum = incomeSum;
        this.missedPeriods = missedPeriods;
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

    public ArrayList<DatePeriod> getMissedPeriods() {
        return missedPeriods;
    }

    public void setMissedPeriods(ArrayList<DatePeriod> missedPeriods) {
        this.missedPeriods = missedPeriods;
    }

    @Override
    public String toString() {
        return String.format("Vacation[vacationPeriod: %s; incomeSum: %f]", vacationPeriod, incomeSum);
    }

    @Override
    public int hashCode() {
        return (int) (31 * startDate.hashCode() * vacationPeriod.hashCode() * incomeSum * missedPeriods.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (hashCode() != obj.hashCode()) return false;

        Vacation otherVacation = (Vacation) obj;

        return startDate.equals(otherVacation.getStartDate()) && vacationPeriod.equals(otherVacation.getVacationPeriod()) &&
                incomeSum == otherVacation.getIncomeSum() && missedPeriods.equals(otherVacation.getMissedPeriods());
    }

    public double calculatePay() {
        DatePeriod billingPeriod = getBillingPeriod();

        HashMap<YearMonth, Integer> workedDays = billingPeriod.getAbsentDaysByMonth();

        HashMap<YearMonth, Integer> absentDays = getAbsentDaysByMonths();
        for (Map.Entry<YearMonth, Integer> entry : absentDays.entrySet()) {
            YearMonth key = entry.getKey();
            int num = entry.getValue();
            int newValue = workedDays.get(key) - num;
            workedDays.put(key, newValue);
        }


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
        ArrayList<HashMap<YearMonth, Integer>> list = new ArrayList<>();
        for (DatePeriod missedPeriod : missedPeriods) {
            HashMap<YearMonth, Integer> absentDaysByMonth = missedPeriod.getAbsentDaysByMonth();
            list.add(absentDaysByMonth);
        }
        return unitAbsentDaysByMonths(list);
    }
    public HashMap<YearMonth, Integer> unitAbsentDaysByMonths(ArrayList<HashMap<YearMonth, Integer>> list) {
        HashMap<YearMonth, Integer> result = new HashMap<>();

        for (HashMap<YearMonth, Integer> absentDaysByMonth : list) {
            for(Map.Entry<YearMonth, Integer> entry : absentDaysByMonth.entrySet()) {
                YearMonth yearMonth = entry.getKey();
                int absentDays = entry.getValue();
                if(result.containsKey(yearMonth)) {
                    int newValue = result.get(yearMonth) + absentDays;
                    result.put(yearMonth, newValue);
                } else
                    result.put(yearMonth, absentDays);
            }
        }
        return result;
    }
}
