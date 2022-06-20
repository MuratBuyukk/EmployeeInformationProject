package com.example.employeemanagementsystem;

import java.time.LocalDate;
import java.util.Date;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate hiringDate;
    private int salary;
    private LocalDate birthDay;
    private int titlesID;
    private String gender;
    private int departmentID;

    public Employee(int id, String firstName, String lastName, LocalDate hiringDate, int salary, LocalDate birthDay, int titlesID, String gender, int departmentID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.birthDay = birthDay;
        this.titlesID = titlesID;
        this.gender = gender;
        this.departmentID = departmentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public int getTitlesID() {
        return titlesID;
    }

    public void setTitlesID(int titlesID) {
        this.titlesID = titlesID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
}
