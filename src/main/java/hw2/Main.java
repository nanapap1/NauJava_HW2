package hw2;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Среднее значение, количество элементов");
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

        System.out.println("Сортировка пузырьком, количество элементов");

        n = in.nextInt();
        ArrayList<Double> arrays = BubbleSort.createArray(n);
        System.out.println(arrays);
        BubbleSort.bubbleSort(arrays);
        System.out.println(arrays);

        n = in.nextInt();
        arrays = BubbleSort.createArray(n);
        System.out.println(arrays);
        BubbleSort.bubbleSort(arrays);
        System.out.println(arrays);


        System.out.println("Сортировка работников");

        Employee one = new Employee("Иван Сергеев",32,"PR",60555.0);
        Employee two = new Employee("Илья Петров",28,"QA",45345.2);
        Employee three = new Employee("Марк Иванов",27,"HR",45345.9);
        Employee four = new Employee("Паша Крупник",25,"QA",58432.7);
        Employee five = new Employee("Егор Федосеев",29,"QA", 67934.57);
        Employee six = new Employee("Сергей Ильин",37,"PR", 77234.57);
        List<Employee> employeesList = new ArrayList<>(List.of(one,two,three,four,five,six));
        Arrays.stream(employeesList.toArray(Employee[]::new)).sorted(Comparator.comparingDouble(Employee::getSalary)).forEach(System.out::println);

        System.out.println("Обращение к HTTP за заголовками");

        HttpHead.getHeaders();

        System.out.println("Синхронизация папок");

        ReleaseTask check = new ReleaseTask("src/main/resources/num1","src/main/resources/num2");

        new Thread(() -> {
            new Scanner(System.in).nextLine();
            check.stop();
        }, "stop").start();
        check.start();
    }
}
