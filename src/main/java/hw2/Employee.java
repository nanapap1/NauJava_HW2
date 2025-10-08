package hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Employee {
    private String fullname;
    private Integer age;
    private String departament;
    private Double salary;

    public Employee(String fullname, Integer age, String departament, Double salary) {
        this.fullname = fullname;
        this.age = age;
        this.departament = departament;
        this.salary = salary;

    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString () {
        return String.format("Имя: %s, Возраст: %d, Отдел: %s, Зарплата: %.1f",this.fullname,this.age,this.departament,this.salary);
    }


    public static void main(String[] args) {
        Employee one = new Employee("Иван Сергеев",32,"PR",60555.0);
        Employee two = new Employee("Илья Петров",28,"QA",45345.2);
        Employee three = new Employee("Марк Иванов",27,"HR",45345.9);
        Employee four = new Employee("Паша Крупник",25,"QA",58432.7);
        Employee five = new Employee("Егор Федосеев",29,"QA", 67934.57);
        Employee six = new Employee("Сергей Ильин",37,"PR", 77234.57);
        List<Employee> employeesList = new ArrayList<>(List.of(one,two,three,four,five,six));
        Arrays.stream(employeesList.toArray(Employee[]::new)).sorted(Comparator.comparingDouble(Employee::getSalary)).forEach(System.out::println);


    }












}
