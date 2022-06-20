package com.example.employeemanagementsystem;

public class Department {
    private int departmentID;
    private String departmentName;
    private int departmentManagerID;

    public Department(int departmentID, String departmentName, int departmentManagerID) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.departmentManagerID = departmentManagerID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentManagerID() {
        return departmentManagerID;
    }

    public void setDepartmentManagerID(int departmentManagerID) {
        this.departmentManagerID = departmentManagerID;
    }
}
