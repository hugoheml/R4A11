package com.example.tp2.utilities;

import java.time.LocalDate;

public class AgeCalculator {
    public static int calculateAge(int birthYear) {
        int currentYear = LocalDate.now().getYear();

        return currentYear - birthYear;
    }
}
