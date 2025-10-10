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

}
