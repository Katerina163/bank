package com.github.Katerina163.bank.calculations;

public class Calculator {
    public static long calcMonthlyRate(int percent, long sum) {
        float rate = (float) percent / 1200;
        var ratio = (rate * Math.pow((1 + rate), 12)) / (Math.pow((1 + rate), 12) - 1);
        float ratioRound = (float) Math.floor(ratio * 1000000) / 1000000;
        return (long) (sum * ratioRound);
    }

    public static long calPercentMonth(long debt, int percent) {
        return debt * percent * 30 / 36500;
    }
}
