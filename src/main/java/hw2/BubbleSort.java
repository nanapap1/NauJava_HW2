package hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BubbleSort {
    public static ArrayList<Double> createArray(int n) {
        double max =100000.0;
        double min = 0.0;
        ArrayList<Double> array = new ArrayList<>();
        for (int i = 0; i < n;i++ )
            array.add((Math.random() * (max - min + 1)) + min);
        return array;
    }
    public static void bubbleSort(ArrayList<Double> numbers) {
        if (numbers.size() == 0) return;
        int sum = 0;
        for (int i = 1; i < numbers.size(); i++)
            for (int j = 0; j < numbers.size()-i; j++)
                if (numbers.get(j) > numbers.get(j+1)) {
                    double t = numbers.get(j);
                    numbers.set(j,numbers.get(j+1));
                    numbers.set(j+1,t);
                }
    }
}
