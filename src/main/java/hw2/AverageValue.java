package hw2;

import java.util.Scanner;

public class AverageValue {

    public static int[] createArray(int n) {
        int max =100000;
        int min = 0;
        int[] array = new int[n];
        for (int i = 0; i < n; i++ )
            array[i] =  (int)(Math.random() * (max - min + 1)) + min;
        return array;

    }
    public static double getAverageValue(int[] numbers) {
        if (numbers.length == 0) return 0;
        int sum = 0;
        for(int i = 0; i < numbers.length;i++)
            sum+=numbers[i];

        return (double)sum/numbers.length;
    }
}
