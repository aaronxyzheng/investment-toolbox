package com.aaronxyzheng.investmenttoolbox;

import java.util.HashMap;
import java.util.ArrayList;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculations {
    public HashMap<Integer, Double> returnAllGrowthRates(String[] array) {
        HashMap<Integer, Double> growthMap = new HashMap<Integer, Double>();
        
        // Makes the Array a Double Array
        int arrayLength = array.length;

        double[] doubleArray = new double[arrayLength];
        for(int i = 0; i < array.length; i++) {
            doubleArray[i] = Double.parseDouble(array[i].replace(",","")); // Gets rid of the commas
        }
        // Creates the year ranges allowed
        ArrayList<Integer> growthLengthList = createGrowthLengthList(arrayLength);
        //
        for(int growthLength : growthLengthList) {
            growthMap.put(growthLength, calculateGrowthRate(doubleArray, growthLength));
        }
        

        return growthMap;
    }

    private double calculateGrowthRate(double[] valuesArray, int growthLength) { // Growth Length is the rate length. Like 10 for 10 year growth rate.
        double multiplier = valuesArray[0]/valuesArray[growthLength];
        double growthRate = Math.pow(multiplier, (1.0/growthLength));
        double result = (growthRate-1)*100;

        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(2 , RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private ArrayList<Integer> createGrowthLengthList(int arrayLength) {
        ArrayList<Integer> growthLengthList = new ArrayList<>();
        if(arrayLength-1 >= 10) {
            growthLengthList.add(10);
        } else if(arrayLength-1 >= 6) {
            growthLengthList.add(arrayLength-1);
        }

        if(arrayLength-1 >= 5) {
            growthLengthList.add(5);
        } else if(arrayLength-1 >= 4) {
            growthLengthList.add(arrayLength-1);
        }

        if(arrayLength-1 >= 3) {
            growthLengthList.add(3);
        } else if(arrayLength-1 >= 2) {
            growthLengthList.add(arrayLength-1);
        }
    
        growthLengthList.add(1);

        return growthLengthList;
    }
}




