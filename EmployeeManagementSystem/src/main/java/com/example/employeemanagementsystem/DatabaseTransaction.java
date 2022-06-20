package com.example.employeemanagementsystem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseTransaction {
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public ArrayList<Employee> getEmployee(){

        ArrayList<Employee> output = new ArrayList<>();

        try {
            statement = con.createStatement();
            String getEmployeeStatement = "Select * From employees";
            ResultSet rs = statement.executeQuery(getEmployeeStatement);
            while (rs.next()){
                int id = rs.getInt("Employee_ID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                LocalDate hiringDate = rs.getDate("Hiring_Date").toLocalDate();
                int Salary = rs.getInt("Salary");
                LocalDate birthday = rs.getDate("Birthday").toLocalDate();
                int titlesID = rs.getInt("Titles_ID");
                String gender = rs.getString("Gender");
                int departmentID = rs.getInt("Department_ID");

                output.add(new Employee(id,firstName,lastName,hiringDate,Salary,birthday,titlesID,gender,departmentID));

            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Title> getTitle(){
        ArrayList<Title> output = new ArrayList<>();

        try {
            statement = con.createStatement();
            String getTitleStatement = "Select * From titles";
            ResultSet rs = statement.executeQuery(getTitleStatement);
            while (rs.next()){
                int titleId = rs.getInt("Title_ID");
                String titleName = rs.getString("Title_Name");

                output.add(new Title(titleId,titleName));

            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Department> getDepartment(){
        ArrayList<Department> output = new ArrayList<>();

        try {
            statement = con.createStatement();
            String getDepartmentStatement = "Select * From departments";
            ResultSet rs = statement.executeQuery(getDepartmentStatement);
            while (rs.next()){
                int departmentID = rs.getInt("Department_ID");
                String departmentName = rs.getString("Department_Name");
                int managerID = rs.getInt("Manager_ID");

                output.add(new Department(departmentID,departmentName,managerID));

            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void autoIncreaseReset(String tableName){
        String autoIncreaseReset = "ALTER TABLE " + tableName + " AUTO_INCREMENT =1";
        try {
            preparedStatement = con.prepareStatement(autoIncreaseReset);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void setNull(String tableName,String setParameter,String whereParameter,int whereValue){
        String setNull = "UPDATE " + tableName + " SET " + setParameter + " = Null WHERE " + whereParameter + " = " + whereValue;
        try {
            statement.executeUpdate(setNull);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addEmployee(String fName,String lName,LocalDate hDate,int salary,LocalDate bDate,int titleID, String gender, int departmentId){
        autoIncreaseReset("employees");
        String addEmployeeStatement = "Insert Into employees (First_Name,Last_Name,Hiring_Date,Salary,Birthday,Titles_ID," +
                "Gender, Department_ID) Values(?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(addEmployeeStatement);
            preparedStatement.setString(1,fName);
            preparedStatement.setString(2,lName);
            preparedStatement.setDate(3, Date.valueOf(hDate));
            preparedStatement.setInt(4,salary);
            preparedStatement.setDate(5, Date.valueOf(bDate));
            preparedStatement.setInt(6,titleID);
            preparedStatement.setString(7,gender);
            preparedStatement.setInt(8,departmentId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addDepartment(String dName){
        autoIncreaseReset("departments");
        String addDepartmentStatement = "INSERT INTO departments (Department_Name) Values(?)";
        try {
            preparedStatement = con.prepareStatement(addDepartmentStatement);
            preparedStatement.setString(1,dName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addTitle(String tName){
        autoIncreaseReset("titles");
        String addTitleStatement = "INSERT INTO titles (Title_Name) Values(?)";
        try {
            preparedStatement = con.prepareStatement(addTitleStatement);
            preparedStatement.setString(1,tName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editEmployee(int fId,String fName,String lName,LocalDate hDate,int salary,LocalDate bDate,int titleID, String gender, int departmentId){
        String editEmployeeStatement = "UPDATE employees SET First_Name = ? , Last_Name = ? , Hiring_Date = ? ," +
                "Salary = ? , Birthday = ? , Titles_ID = ? , Gender = ? , Department_ID = ? WHERE Employee_ID = ?";
        try {
            preparedStatement = con.prepareStatement(editEmployeeStatement);

            preparedStatement.setString(1,fName);
            preparedStatement.setString(2,lName);
            preparedStatement.setDate(3, Date.valueOf(hDate));
            preparedStatement.setInt(4,salary);
            preparedStatement.setDate(5, Date.valueOf(bDate));
            preparedStatement.setInt(6,titleID);
            preparedStatement.setString(7,gender);
            preparedStatement.setInt(8,departmentId);
            preparedStatement.setInt(9,fId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editDepartment(int dId,String dName){
        String editDepartmentStatement = "UPDATE departments SET Department_Name = ?  WHERE Department_ID = ?";
        try {
            preparedStatement = con.prepareStatement(editDepartmentStatement);

            preparedStatement.setString(1,dName);
            preparedStatement.setInt(2,dId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editTitle(int tId,String tName){
        String editTitleStatement = "UPDATE titles SET Title_Name = ?  WHERE Title_ID = ?";
        try {
            preparedStatement = con.prepareStatement(editTitleStatement);

            preparedStatement.setString(1,tName);
            preparedStatement.setInt(2,tId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployee(int fID){
        String deleteEmployeeStatement = "DELETE FROM employees WHERE Employee_ID = ?";
        try {
            preparedStatement = con.prepareStatement(deleteEmployeeStatement);
            preparedStatement.setInt(1,fID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteDepartment(int dID){
        assignManager(dID);
        setNull("employees","Department_ID","Department_ID",dID);

        String deleteDepartmentStatement = "DELETE FROM departments WHERE Department_ID = ?";

        try {
            preparedStatement = con.prepareStatement(deleteDepartmentStatement);
            preparedStatement.setInt(1,dID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteTitle(int tID){
        String deleteTitleStatement = "DELETE FROM titles WHERE Title_ID = ?";
        setNull("employees","Titles_ID", "Titles_ID", tID);
        try {
            preparedStatement = con.prepareStatement(deleteTitleStatement);
            preparedStatement.setInt(1,tID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void assignManager(int fId,int dId){
        String assignManagerStatement = "UPDATE departments SET Manager_ID = ?  WHERE Department_ID = ?";
        try {
            preparedStatement = con.prepareStatement(assignManagerStatement);

            preparedStatement.setInt(1,fId);
            preparedStatement.setInt(2,dId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void assignManager(int dId){
        String assignManagerStatement = "UPDATE departments SET Manager_ID = Null  WHERE Department_ID = ?";
        try {
            preparedStatement = con.prepareStatement(assignManagerStatement);
            preparedStatement.setInt(1,dId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean makeLogin(String userName, String password){
        String loginStatement = "Select * From administrator where Admin_UserName = ? And Admin_Password = ?";
        try {
            preparedStatement = con.prepareStatement(loginStatement);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);

            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next())
                return false;
            else
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed login");
            return false;
        }
    }

    public DatabaseTransaction(){
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.dbName + "?useUnicode=true&characterEncoding=utf8";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver bulunamadi...");
        }

        try {
            con = DriverManager.getConnection(url,Database.username,Database.password);
            System.out.println("Baglanti basarili");
        } catch (SQLException e) {
            System.out.println("Baglanti basarisiz");
        }
    }

    public static void main(String[] args) {
        DatabaseTransaction transaction = new DatabaseTransaction();
    }
}
