package com.company;

import java.text.DecimalFormat;

/** Mykola Gutsaliuk
 * 11/15/2020
 * Calculates mortgage payoff time and cost depending on variables.
 */
public class Main {
    final static double BALANCE = 138878.69; // loan balance
    final static double IR = 0.0375; // 3.75%
    final static double ESCROW = 421.06;
    final static double MIN_MONTHLY_PAYMENT = 1129.63; // minimum monthly payment
    final static double ADDITIONAL_MONTHLY_PAYMENT = 0.00; // any extra amount on top of min monthly payment
    final static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        double remainingBal = BALANCE;
        double totalPaid = 0.0;
        double totalInterest = 0.0;
        double totalEscrow = 0.0;
        int month = 0;
	while (remainingBal > 0) {
        month++;
	    double interest = calcMonthlyInterest(remainingBal);
	    double principal = MIN_MONTHLY_PAYMENT + ADDITIONAL_MONTHLY_PAYMENT - ESCROW - interest;
	    totalInterest = totalInterest + interest;
	    remainingBal = remainingBal - principal;
	    totalEscrow = totalEscrow + ESCROW;
	    totalPaid = totalPaid + MIN_MONTHLY_PAYMENT + ADDITIONAL_MONTHLY_PAYMENT;
        System.out.println("Month "+ month +": Principal: $" + df.format(principal) + " Interest: $" + df.format(interest) +
                " Remaining balance: $"+ df.format(remainingBal));

    }
	totalPaid = totalPaid + remainingBal; // if last month balance is negative
        double years = month / 12.0;
        System.out.println();
        System.out.println("With monthly payment $" + MIN_MONTHLY_PAYMENT + " + additional monthly payment of $" +
                ADDITIONAL_MONTHLY_PAYMENT);
        System.out.println("It will take " + month + " month or " + df.format(years)+ " years to pay off $" + BALANCE +
                " balance.");
        System.out.println("Total payment will be $" + df.format(totalPaid));
        System.out.println("Total interest paid $" + df.format(totalInterest));
        System.out.println("Total escrow paid $" + df.format(totalEscrow));
    }

    private static Double calcMonthlyInterest(Double balance) {
        return (balance * IR) / 12;
    }
}
