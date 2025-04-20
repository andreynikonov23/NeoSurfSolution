package org.example.vacation_calculator.model;

import org.example.vacation_calculator.util.DatePeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class VacationTest {
    @Test
    public void unitHashMap() {
        HashMap<Integer, Integer> paidDaysByMonths1 = new HashMap<>();
        HashMap<Integer, Integer> paidDaysByMonths2 = new HashMap<>();

        paidDaysByMonths1.put(4, 12);
        paidDaysByMonths1.put(5, 17);

        paidDaysByMonths2.put(5, 12);
        paidDaysByMonths2.put(6, 20);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 27));
        Vacation vacation = new Vacation(LocalDate.of(2024, 4, 8), datePeriod, 780000, new ArrayList<>());


    }
}
