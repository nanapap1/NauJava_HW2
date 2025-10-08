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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = AverageValue.createArray(n);
        for(int i = 0; i < array.length;i++)
            System.out.print(array[i] + " ");
        System.out.println();
        System.out.println(AverageValue.getAverageValue(array));

        n = in.nextInt();
        array = AverageValue.createArray(n);
        for(int i = 0; i < array.length;i++)
            System.out.print(array[i] + " ");
        System.out.println();
        System.out.println(AverageValue.getAverageValue(array));

        n = in.nextInt();
        array = AverageValue.createArray(n);
        for(int i = 0; i < array.length;i++)
            System.out.print(array[i] + " ");
        System.out.println();
        System.out.println(AverageValue.getAverageValue(array));
    }
}
