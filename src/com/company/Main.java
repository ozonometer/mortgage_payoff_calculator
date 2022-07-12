package com.company;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;

/** Mykola Gutsaliuk
 * 11/15/2020
 * Calculates mortgage payoff time and cost depending on variables.
 */
public class Main {
    final static double BALANCE = 138878.69; // loan balance 138878.69
    final static double IR = 0.0375; // 3.75% annual interest rate
    final static double ESCROW = 451.09; // monthly escrow amount
    final static double MIN_MONTHLY_PAYMENT = 1129.63; // minimum monthly payment
    final static double ADDITIONAL_MONTHLY_PAYMENT = 0.00; // any extra amount on top of min monthly payment

    final static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        int currentYear = currentdate.getYear();
        System.out.println("Current balance as on " + currentdate + " is " + BALANCE);

        double remainingBal = BALANCE;
        double totalPaid = 0.0;
        double totalInterest = 0.0;
        double totalEscrow = 0.0;
        int month = 0;
        int years = 0;

	while (remainingBal > 0) {
        month++;
        currentMonth = currentMonth.plus(1);
        double interest = calcMonthlyInterest(remainingBal);
	    double principal = MIN_MONTHLY_PAYMENT + ADDITIONAL_MONTHLY_PAYMENT - ESCROW - interest;
	    remainingBal = remainingBal - principal; // principal toward balance

        totalInterest = totalInterest + interest;
	    totalEscrow = totalEscrow + ESCROW;
	    totalPaid = totalPaid + MIN_MONTHLY_PAYMENT + ADDITIONAL_MONTHLY_PAYMENT;
        System.out.println("Principal: $" + df.format(principal) + " Interest: $" + df.format(interest) +
                " Remaining balance: $"+ df.format(remainingBal) + " " + currentMonth +" " + currentYear + " (Month " + month + ")");

        // calendar year count
        if (currentMonth.getValue() == 12) {
            currentYear = currentYear + 1;
        }
        // year from today count
        if (month % 12 == 0) {
            years++;
            System.out.println("--- Remaining balance after year " +years+ " ("+ currentYear +" " + currentMonth + ") will be $"+df.format(remainingBal));
        }
    }

	totalPaid = totalPaid + remainingBal; // if last month balance is negative
        int remainingMonths = month - (years * 12);
        System.out.println();
        System.out.println("Loan will be paid off in " + currentMonth + " " + currentYear);
        System.out.println("With monthly payment $" + MIN_MONTHLY_PAYMENT + " + additional monthly payment of $" +
                ADDITIONAL_MONTHLY_PAYMENT);
        System.out.println("It will take " + month + " month or " + years + " years and " + remainingMonths +
                " months to pay off $" + BALANCE + " balance.");
        System.out.println("Total payment will be $" + df.format(totalPaid));
        System.out.println("Total interest paid $" + df.format(totalInterest));
        System.out.println("Total escrow paid $" + df.format(totalEscrow));
    }

    /**
     * Monthly interest rate is calculated by formula: loan balance x interest rate / 12 months
     * @param balance outstanding balance
     * @return calculated interest rate
     */
    private static Double calcMonthlyInterest(Double balance) {
        return (balance * IR) / 12;
    }
}
