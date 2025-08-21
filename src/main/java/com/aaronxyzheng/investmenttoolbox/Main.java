package com.aaronxyzheng.investmenttoolbox;

import java.util.*;

public class Main {
    
    public static Scanner scanner = new Scanner(System.in);
    public static Calculations calculations = new Calculations();
    public static void main(String[] args) {
        while (true) {
            run();
        }
    }
    public static void run() {        

        boolean validInput = false;

        while(!validInput){

            System.out.println("What would you like to do:");
            System.out.println();
            System.out.println("Type 1 to Calculate Growth Rates");
            System.out.println("Type 2 to Create a Big Four Table for Notion");
            System.out.print("Input: ");
            
            switch(scanner.nextLine()) {
                case "1":
                    calculateGrowthRates();
                    validInput = true;
                    break;
                case "2": 
                    createBigFourNotionTable();
                    validInput = true;
                    break;  
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    public static void calculateGrowthRates() {
        // Method Asks user for financial numbers than calculates the 10y, 5y, 3y, and 1y growth rate. (If enough data isn't given results will be adjusted accordingly.);
        System.out.println("Enter the metric values from newest to oldest, seperated by spaces");
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

    public static void createBigFourNotionTable() {
        
        ArrayList<String> metricList = new ArrayList<>(Arrays.asList("Revenue", "Equity", "Net Income", "Cash from Operations"));
        ArrayList<HashMap<Integer,Double>> listOfRateHashes = new ArrayList<>();
            
        // Gets the Rates of All the 4 Values
        for(String metric : metricList) {
            System.out.println();
            System.out.println("Enter the " + metric + " values from newest to oldest, separated by spaces:");
            System.out.print("Values: ");

            String dataString = scanner.nextLine().strip();
            String[] dataArray = dataString.split("[^0-9(),.%]+");

            listOfRateHashes.add(calculations.returnAllGrowthRates(dataArray));
        }

        boolean sameYearRanges = true; 
        Set<Integer> yearRanges = listOfRateHashes.get(0).keySet();

        for(int i = 1; i<=listOfRateHashes.size(); i++) {
            if(!listOfRateHashes.get(i-1).keySet().equals(yearRanges)) {
                sameYearRanges = false;
                break;
            }
        }

        if(sameYearRanges == false) {
            System.out.println("It looks like you didn't copy the same amount years for each metric.");
        } else {

            System.out.println();
            System.out.println();

            String headerRow = "| Big Four |";
            for(int yearRange : yearRanges) {
                headerRow += (" " + yearRange + " Year |");
            }

            System.out.println(headerRow);

            String secondRow = "|";
            for(int i = 1; i <= yearRanges.size()+1; i++) {
                secondRow += " --- |";
            }
            System.out.println(secondRow);

            for(int i = 1; i <= 4; i++) {
                String row = ("| " + metricList.get(i-1) + " | ");
                for (int j = 1; j <= yearRanges.size(); j++) {
                    List<Integer> listOfYearRanges = new ArrayList<Integer>(yearRanges);
                    if(Double.isNaN(listOfRateHashes.get(i-1).get(listOfYearRanges.get(j-1)))) {
                        row += "Undefined | ";
                    } else {
                        row += (listOfRateHashes.get(i-1).get(listOfYearRanges.get(j-1)) + "% | ");
                    }
                }

                System.out.println(row);
            }

            System.out.println();
            System.out.println();
            System.out.println("Copy Above;");
        }

    }

}
// 1y: 2.17% | 3y: 16.42% | 5y: 17.24% | 9y: 17.31%