package org.example.vacation_calculator.controller;

import org.example.vacation_calculator.service.Vacation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateController {

    @GetMapping("calculate")
    public double calculate(@RequestBody Vacation vacation) {
        double result = vacation.calculatePay();
        return result;
    }
}
