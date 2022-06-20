package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PanelController implements Initializable {

    @FXML
    private TextField panelDepartmentID;

    @FXML
    private TextField panelTitleID;

    @FXML
    private TextField panelTitleName;

    @FXML
    private TextField panelDepartmentName;

    @FXML
    private TableView<Department> panelDepartmentTable;

    @FXML
    private TableColumn<?, ?> panelDepartmentTableID;

    @FXML
    private TableColumn<?, ?> panelDepartmentTableManager;

    @FXML
    private TableColumn<?, ?> panelDepartmentTableName;

    @FXML
    private DatePicker panelEmployeeBDDP;

    @FXML
    private ChoiceBox<Integer> panelEmployeeDeptCB;

    @FXML
    private TextField panelEmployeeFNTF;


    @FXML
    private DatePicker panelEmployeeHDDP;

    @FXML
    private TextField panelEmployeeIDTF;

    @FXML
    private TextField panelEmployeeLNTF;

    @FXML
    private TextField panelEmployeeSalaryTF;

    @FXML
    private TableView<Employee> panelEmployeeTable;

    @FXML
    private TableColumn<?,?> panelEmployeeTableBD;

    @FXML
    private TableColumn<?,?> panelEmployeeTableDepartment;

    @FXML
    private TableColumn<?, ?> panelEmployeeTableFN;

    @FXML
    private TableColumn<?, ?> panelEmployeeTableGender;

    @FXML
    private TableColumn<?, ?> panelEmployeeTableHD;

    @FXML
    private TableColumn<?, ?> panelEmployeeTableID;

    @FXML
    private TableColumn<?, ?> panelEmployeeTableLN;

    @FXML
    private TableColumn<?, ?> panelEmployeeTableSalary;

    @FXML
    private TableColumn<?, ?> panelEmployeeTableTitle;

    @FXML
    private ChoiceBox<Integer> panelEmployeeTitleCB;


    @FXML
    private TableView<Title> panelTitleTable;

    @FXML
    private TableColumn<?, ?> panelTitleTableID;

    @FXML
    private TableColumn<?, ?> panelTitleTableName;

    @FXML
    private ChoiceBox<String> panelEmployeeGenderCB;

    DatabaseTransaction databaseTransaction = new DatabaseTransaction();




    public void showEmployee(){
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList = databaseTransaction.getEmployee();
        ObservableList<Employee> employees = FXCollections.observableList(employeeList);

        if (employees != null ){
            for (Employee employee: employees){
                panelEmployeeTableID.setCellValueFactory(new PropertyValueFactory<>("id"));
                panelEmployeeTableFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                panelEmployeeTableLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                panelEmployeeTableHD.setCellValueFactory(new PropertyValueFactory<>("hiringDate"));
                panelEmployeeTableSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                panelEmployeeTableBD.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
                panelEmployeeTableTitle.setCellValueFactory(new PropertyValueFactory<>("titlesID"));
                panelEmployeeTableGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                panelEmployeeTableDepartment.setCellValueFactory(new PropertyValueFactory<>("departmentID"));
                panelEmployeeTable.setItems(employees);
                panelEmployeeTable.refresh();
            }
            System.out.println("Employee list has been refreshed");
        }
    }
    public void showTitle(){
        ArrayList<Title> titleList = new ArrayList<>();
        titleList = databaseTransaction.getTitle();
        ObservableList<Title> titles = FXCollections.observableList(titleList);
        if (titles != null){
            for (Title title: titles){
                panelTitleTableID.setCellValueFactory(new PropertyValueFactory<>("titleId"));
                panelTitleTableName.setCellValueFactory(new PropertyValueFactory<>("titleName"));
                panelTitleTable.setItems(titles);
                panelTitleTable.refresh();
            }
            System.out.println("Title list has been refreshed");
        }
    }

    public void showDepartment(){
        ArrayList<Department> departmentList = new ArrayList<>();
        departmentList = databaseTransaction.getDepartment();
        ObservableList<Department> departments = FXCollections.observableList(departmentList);
        if (departments != null){
            for (Department department: departments){
                panelDepartmentTableID.setCellValueFactory(new PropertyValueFactory<>("departmentID"));
                panelDepartmentTableName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
                panelDepartmentTableManager.setCellValueFactory(new PropertyValueFactory<>("departmentManagerID"));
                panelDepartmentTable.setItems(departments);
                panelDepartmentTable.refresh();
            }
            System.out.println("Department list has been refreshed");
        }
    }
    public void clearSelect(){
        panelEmployeeIDTF.setText("");
        panelEmployeeFNTF.setText("");
        panelEmployeeLNTF.setText("");
        panelEmployeeHDDP.setValue(null);
        panelEmployeeBDDP.setValue(null);
        panelEmployeeSalaryTF.setText("");
        panelEmployeeTitleCB.setValue(null);
        panelEmployeeDeptCB.setValue(null);
        panelEmployeeGenderCB.setValue(null);
        panelDepartmentID.setText("");
        panelDepartmentName.setText("");
        panelTitleID.setText("");
        panelTitleName.setText("");
    }
    public void listChoiceBox(){
        panelEmployeeTitleCB.getItems().clear();
        panelEmployeeDeptCB.getItems().clear();
        panelEmployeeGenderCB.getItems().clear();
        ArrayList<Title> titles = new ArrayList<>();
        titles = databaseTransaction.getTitle();
        ArrayList<Department> departments = new ArrayList<>();
        departments = databaseTransaction.getDepartment();

        panelEmployeeGenderCB.getItems().addAll("M","F");
        for (Title title: titles){
            panelEmployeeTitleCB.getItems().add(title.getTitleId());
        }
        for (Department department: departments){
            panelEmployeeDeptCB.getItems().add(department.getDepartmentID());
        }
    }
    public void missingFieldAlert(String name){
        System.out.println("Missing adding " + name);
        System.out.println("Failed Adding " + name);
        Alert alertAdd = new Alert(Alert.AlertType.ERROR);
        alertAdd.setContentText("There has been a missing "  + name + " field, please fill in all.");
        alertAdd.setHeaderText("Missing Field");
        alertAdd.show();
    }
    public void missingIDAlert(String name){
        System.out.println("Missing Id " + name);
        System.out.println("Failed Id " + name);
        Alert alertID = new Alert(Alert.AlertType.ERROR);
        alertID.setContentText(name + " ID field is not selected, please select any.");
        alertID.setHeaderText("Missing Field");
        alertID.show();
    }

    public void selectEmployee(Employee employee){
        if (employee != null){
            panelEmployeeIDTF.setText("" + employee.getId());
            panelEmployeeFNTF.setText(employee.getFirstName());
            panelEmployeeLNTF.setText(employee.getLastName());
            panelEmployeeHDDP.setValue(employee.getHiringDate());
            panelEmployeeBDDP.setValue(employee.getBirthDay());
            panelEmployeeSalaryTF.setText(""+ employee.getSalary());
            panelEmployeeTitleCB.setValue(employee.getTitlesID());
            panelEmployeeDeptCB.setValue(employee.getDepartmentID());
            panelEmployeeGenderCB.setValue(employee.getGender());
        } else {
           clearSelect();
        }
    }

    public void selectDepartment(Department department){
        if (department != null){
            panelDepartmentID.setText("" + department.getDepartmentID());
            panelDepartmentName.setText(department.getDepartmentName());
        }
        else {
            clearSelect();
        }
    }

    public void selectTitle(Title title){
        if (title != null){
            panelTitleID.setText("" + title.getTitleId());
            panelTitleName.setText(title.getTitleName());
        }
        else {
            clearSelect();
        }
    }
    public void addEmployeeAction(ActionEvent actionEvent){
        if (panelEmployeeFNTF.getText().matches("") || panelEmployeeLNTF.getText().matches("")|| panelEmployeeHDDP == null ||
                panelEmployeeSalaryTF.getText().matches("") || panelEmployeeBDDP == null || panelEmployeeTitleCB == null ||
                panelEmployeeGenderCB == null || panelEmployeeDeptCB == null) {
            missingFieldAlert("Employee Information");
        }else {
            String fName = panelEmployeeFNTF.getText();
            String lName = panelEmployeeLNTF.getText();
            LocalDate hDate = panelEmployeeHDDP.getValue();
            int salary = Integer.parseInt(panelEmployeeSalaryTF.getText());
            LocalDate bDate = panelEmployeeBDDP.getValue();
            int titleID = panelEmployeeTitleCB.getValue();
            String gender = panelEmployeeGenderCB.getValue();
            int departmentId = panelEmployeeDeptCB.getValue();

            databaseTransaction.addEmployee(fName, lName, hDate, salary, bDate, titleID, gender, departmentId);
            System.out.println("Employee has been added");
            showEmployee();
            clearSelect();
        }
    }
    public void addDepartmentAction(ActionEvent actionEvent){
        if (panelDepartmentName.getText().matches("")){
            missingFieldAlert("Department Information");
        } else {
            String dName = panelDepartmentName.getText();
            databaseTransaction.addDepartment(dName);
            System.out.println("Department has been added");
            showDepartment();
            clearSelect();
            listChoiceBox();
        }

    }
    public void addTitleAction(ActionEvent actionEvent){
        if (panelTitleName.getText().matches("")){
            missingFieldAlert("Title Information");
        } else {
            String tName = panelTitleName.getText();
            databaseTransaction.addTitle(tName);
            System.out.println("Title has been added");
            showTitle();
            clearSelect();
            listChoiceBox();
        }
    }
    public void editEmployeeAction(ActionEvent actionEvent){
        if (panelEmployeeIDTF.getText().matches("")) {
            missingIDAlert("Employee");
        }else {
            int fId = Integer.parseInt(panelEmployeeIDTF.getText());
            String fName = panelEmployeeFNTF.getText();
            String lName = panelEmployeeLNTF.getText();
            LocalDate hDate = panelEmployeeHDDP.getValue();
            int salary = Integer.parseInt(panelEmployeeSalaryTF.getText());
            LocalDate bDate = panelEmployeeBDDP.getValue();
            int titleID = panelEmployeeTitleCB.getValue();
            String gender = panelEmployeeGenderCB.getValue();
            int departmentId = panelEmployeeDeptCB.getValue();

            databaseTransaction.editEmployee(fId, fName, lName, hDate, salary, bDate, titleID, gender, departmentId);
            System.out.println("Employee has been edited");
            showEmployee();
            clearSelect();
        }
    }
    public void editDepartmentAction(ActionEvent actionEvent){
        if (panelDepartmentID.getText().matches("")) {
            missingIDAlert("Department");
        }else {
            int dId = Integer.parseInt(panelDepartmentID.getText());
            String dName = panelDepartmentName.getText();

            databaseTransaction.editDepartment(dId, dName);
            System.out.println("Department has been edited");
            showDepartment();
            clearSelect();
        }
    }
    public void editTitleAction(ActionEvent actionEvent){
        if (panelTitleID.getText().matches("")) {
            missingIDAlert("Title");
        }else {
            int tId = Integer.parseInt(panelTitleID.getText());
            String tName = panelTitleName.getText();

            databaseTransaction.editTitle(tId, tName);
            System.out.println("Title has been edited");
            showTitle();
            clearSelect();
        }
    }
    public void deleteEmployeeAction(ActionEvent actionEvent){
        if (panelEmployeeIDTF.getText().matches("")) {
            missingIDAlert("Employee");
        }else {
            int fId = Integer.parseInt(panelEmployeeIDTF.getText());
            databaseTransaction.deleteEmployee(fId);
            System.out.println("Employee has been deleted");
            showEmployee();
            clearSelect();
        }
    }
    public void deleteDepartmentAction(ActionEvent actionEvent){
        if (panelDepartmentID.getText().matches("")) {
            missingIDAlert("Department");
        }else {
            int dId = Integer.parseInt(panelDepartmentID.getText());
            databaseTransaction.deleteDepartment(dId);
            System.out.println("Department has been deleted");
            showEmployee();
            showDepartment();
            clearSelect();
            listChoiceBox();
        }
    }
    public void deleteTitleAction(ActionEvent actionEvent){
        if (panelTitleID.getText().matches("")) {
            missingIDAlert("Title");
        }else {
            int tId = Integer.parseInt(panelTitleID.getText());
            databaseTransaction.deleteTitle(tId);
            System.out.println("Title has been deleted");
            showEmployee();
            showTitle();
            clearSelect();
            listChoiceBox();
        }
    }
    public void assignManagerAction(ActionEvent actionEvent){
         if (panelDepartmentID.getText().matches("")){
            missingFieldAlert("Department");
        }else if (panelEmployeeIDTF.getText().matches("")){
            int dId = Integer.parseInt(panelDepartmentID.getText());
            databaseTransaction.assignManager(dId);
            System.out.println("Manager has been removed");
            showDepartment();
            clearSelect();
        }else {
            int fId = Integer.parseInt(panelEmployeeIDTF.getText());
            int dId = Integer.parseInt(panelDepartmentID.getText());
            databaseTransaction.assignManager(fId,dId);
            System.out.println("Manager has been assigned");
            showDepartment();
            clearSelect();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmployee();
        showTitle();
        showDepartment();
        panelEmployeeTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectEmployee(newValue)
        );
        panelDepartmentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectDepartment(newValue)
        );
        panelTitleTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectTitle(newValue)
        );
        listChoiceBox();

    }
}
