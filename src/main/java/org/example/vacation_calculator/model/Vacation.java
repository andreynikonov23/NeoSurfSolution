package org.example.vacation_calculator.model;

import org.example.vacation_calculator.util.DatePeriod;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Vacation {
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
        long monthDiff = ChronoUnit.MONTHS.between(vacationPeriod.getDateOn(), vacationPeriod.getDateOn());
        if (monthDiff > 12) {
            startDate = vacationPeriod.getDateOn().minusYears(1);
        }
        long billingPeriod = ChronoUnit.MONTHS.between(startDate, vacationPeriod.getDateOn());

        ArrayList<HashMap<Integer, Integer>> list = new ArrayList<>();
        for (DatePeriod missedPeriod : missedPeriods) {
            HashMap<Integer, Integer> absentDaysByMonth = missedPeriod.getAbsentDaysByMonth();
            list.add(absentDaysByMonth);
        }
        HashMap<Integer, Integer> absentDaysByMonths = unitAbsentDaysByMonths(list);

        int fullMonthsWorked = 12 - absentDaysByMonths.size();

        for (Map.Entry<Integer, Integer> entry : absentDaysByMonths.entrySet()) {
            int numOfMonth = entry.getKey();
            int absentDays = entry.getValue();


        }
        return 0;
    }

    public HashMap<Integer, Integer> unitAbsentDaysByMonths(ArrayList<HashMap<Integer, Integer>> list) {
        HashMap<Integer, Integer> result = new HashMap<>();

        for (HashMap<Integer, Integer> absentDaysByMonth : list) {
            for(Map.Entry<Integer, Integer> entry : absentDaysByMonth.entrySet()) {
                int numOfMonth = entry.getKey();
                int absentDays = entry.getValue();
                if(result.containsKey(numOfMonth)) {
                    int newValue = result.get(numOfMonth) + absentDays;
                    result.put(numOfMonth, newValue);
                } else
                    result.put(numOfMonth, absentDays);
            }
        }
        return result;
    }
}
