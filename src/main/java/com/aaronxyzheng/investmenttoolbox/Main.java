package com.aaronxyzheng.investmenttoolbox;

import java.util.Scanner;

public class Main {
    
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        run();
    }
    public static void run() {        

        boolean validInput = false;

        while(!validInput){

            System.out.println("What would you like to do:");
            System.out.println();
            System.out.println("Type 1 to Calculate Growth Rates");
            System.out.print("Input: ");
            
            switch(scanner.nextLine()) {
                case "1":
                    calculateGrowthRates();
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    public static void calculateGrowthRates() {
        System.out.println("This part was successful.");
    }

}
