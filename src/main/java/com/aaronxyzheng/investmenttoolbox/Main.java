package com.aaronxyzheng.investmenttoolbox;

import java.util.Scanner;
import java.util.HashMap;

public class Main {
    
    public static Scanner scanner = new Scanner(System.in);
    public static Calculations calculations = new Calculations();
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
        // Method Asks user for financial numbers than calculates the 10y, 5y, 3y, and 1y growth rate. (If enough data isn't given results will be adjusted accordingly.);
        System.out.println("Please enter numbers from newest to oldest seperated by a space.");
        System.out.print("Numbers: ");
        
        String dataString = scanner.nextLine().strip();
        String[] dataArray = dataString.split("[^0-9(),.%]+");

        HashMap<Integer, Double> growthMap = calculations.returnAllGrowthRates(dataArray); // HashMap that'll create growth rates and how the period that they apply to.
        
        String returnString = "";

        for(int item : growthMap.keySet()) {
            returnString += (item + "y: " + growthMap.get(item) + "% | ");
        }

        System.out.println();
        System.out.println("Rates: " + returnString.substring(0, returnString.length() - 2)); // Removes a space and comma from growth rate String

    }

}
// 1y: 2.17% | 3y: 16.42% | 5y: 17.24% | 9y: 17.31%