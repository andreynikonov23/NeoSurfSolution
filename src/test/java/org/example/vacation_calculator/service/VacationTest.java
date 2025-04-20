package org.example.vacation_calculator.service;

import org.example.vacation_calculator.util.DatePeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VacationTest {
    @Test
    public void calculatePayTest1() {
        DatePeriod vacationPeriod = new DatePeriod(LocalDate.of(2025, 8, 7), LocalDate.of(2025, 9, 3));
        DatePeriod missingPeriod1 = new DatePeriod(LocalDate.of(2024, 11, 4), LocalDate.of(2024, 12, 18));
        ArrayList<DatePeriod> missingPeriods = new ArrayList<>(List.of(missingPeriod1));
        Vacation vacation = new Vacation(LocalDate.of(2023, 4, 13), vacationPeriod, 683718, missingPeriods);

        Assertions.assertEquals(62112.40, vacation.calculatePay());
    }
    @Test
    public void calculatePayTest2() {
        DatePeriod vacationPeriod = new DatePeriod(LocalDate.of(2025, 7, 9), LocalDate.of(2025, 8, 5));
        ArrayList<DatePeriod> missingPeriods = new ArrayList<>();
        Vacation vacation = new Vacation(LocalDate.of(2025, 1, 9), vacationPeriod, 417286, missingPeriods);

        Assertions.assertEquals(66461.93, vacation.calculatePay());
    }

}
