package com.dao;

import com.pojos.AddressDetails;
import com.pojos.Employee;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

//2. There is need to store employee information [like : employee id, name, salary, join date] and
// employee's address information [like : address id, city, country, employee id].
// Employee and Address information should be stored in separate tables with Foreign key relating them.
// Create a menu driven program which can insert employee and address records and to perform the following tasks.
// First store employee record and then address record.
// Make sure, if the address record is not saved then employee transaction should be rollbacked.
//   a)  Select the employee records with their address.
//   b) Select the address of an employee whose employee id is given.
//  c) Select all the employees who are in the given city.
//  d) Select the employee who gets highest salary.
//  e) Select all the employees who has experience more than 5 years.
public interface IEmployeeDao {

    //insert employee and address records
    void insertEmpRecord(Employee employee) throws SQLException;
    void insertEmpAddress(AddressDetails addressDetails) throws SQLException;

    //Get employee and address record
    List<Employee> getAllEmpRecords() throws SQLException;
    List<AddressDetails> getAllAddressRecords() throws SQLException;

    //a) Select the employee records with their address.
    HashMap<Employee,AddressDetails> getEmpRecordsWithAddress() throws SQLException;

    //b) Select the address of an employee whose employee id is given.
    HashMap<Employee,AddressDetails> getEmpAddDetailsByEmployeeId(int id) throws SQLException;

    //c) Select all the employees who are in the given city
    List<String> getEmpByCity(String city) throws SQLException;

    //d) Select the employee who gets highest salary.
    HashMap<String,Integer> getEmpWithHighestSalary() throws SQLException;

    //e) Select all the employees who has experience more than 5 years.
    List<Employee> get5YearExperiencedEmp() throws SQLException;
}
