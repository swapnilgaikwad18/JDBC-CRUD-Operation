package com.dao;

import com.pojos.AddressDetails;
import com.pojos.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.utils.DBUtils.openConnection;

public class EmployeeDaoImpl implements IEmployeeDao{
    private Connection cn;
    private PreparedStatement prst;
    public EmployeeDaoImpl() throws SQLException{
        cn = openConnection();
    }

    public void insertEmpRecord(Employee employee) throws SQLException{
        try {
            cn.setAutoCommit(false);
            // empId | name   | salary | joinDate
            prst = cn.prepareStatement("INSERT INTO employee VALUES (default,?,?,?)");
            prst.setString(1,employee.getName());
            prst.setDouble(2,employee.getSalary());
            prst.setDate(3,employee.getJoinDate());
            prst.executeUpdate();

            cn.commit();
            System.out.println("Employee record inserted successfully!");
        }catch (Exception e){
            cn.rollback();
            System.out.println("Error occured! Transaction rolled back.");
            e.printStackTrace();
        }
    }

    public void insertEmpAddress(AddressDetails addressDetails) throws SQLException{
        try {
            cn.setAutoCommit(false);
            // addressId | city   | country | empId
            prst = cn.prepareStatement("INSERT INTO addressdetails VALUES (?,?,?,?)");
            prst.setInt(1,addressDetails.getAddressId());
            prst.setString(2,addressDetails.getCity());
            prst.setString(3, addressDetails.getCountry());
            prst.setInt(4,addressDetails.getEmpId());
            prst.executeUpdate();

            System.out.println("Employee's address record inserted successfully!");
        }catch (Exception e){
            cn.commit();
            System.out.println("Error occured! Transaction rolled back.");
        }

    }

    @Override
    public List<Employee> getAllEmpRecords() throws SQLException {
        List<Employee> lst = new ArrayList<>();
        prst = cn.prepareStatement("SELECT * FROM employee");
        ResultSet rst = prst.executeQuery();

        while (rst.next()){
            Employee employee = new Employee();
            employee.setEmpId(rst.getInt("empId"));
            employee.setName(rst.getString("name"));
            employee.setSalary(rst.getDouble("salary"));
            employee.setJoinDate(rst.getDate("joinDate"));

            lst.add(employee);
        }
        return lst;
    }

    @Override
    public List<AddressDetails> getAllAddressRecords() throws SQLException {
        List<AddressDetails> lst = new ArrayList<>();

        prst = cn.prepareStatement("SELECT * FROM addressDetails");
        ResultSet rst = prst.executeQuery();
        // addressId | city   | country | empId
        while (rst.next()){
            AddressDetails addressDetails = new AddressDetails();
            addressDetails.setAddressId(rst.getInt("addressId"));
            addressDetails.setCity(rst.getString("city"));
            addressDetails.setCountry(rst.getString("country"));
            addressDetails.setEmpId(rst.getInt("empId"));
            lst.add(addressDetails);
        }
        return lst;
    }

    @Override
    public HashMap<Employee, AddressDetails> getEmpRecordsWithAddress() throws SQLException {
        HashMap<Employee,AddressDetails> hm = new LinkedHashMap<>();
        prst = cn.prepareStatement("SELECT * FROM employee NATURAL JOIN addressDetails");
        ResultSet rst = prst.executeQuery();
        // empId | name   | salary | joinDate   | addressId | city   | country
        while (rst.next()){
            Employee employee = new Employee();
            AddressDetails addressDetails = new AddressDetails();
            employee.setEmpId(rst.getInt("empId"));
            employee.setName(rst.getString("name"));
            employee.setSalary(rst.getDouble("salary"));
            employee.setJoinDate(rst.getDate("joinDate"));
            addressDetails.setAddressId(rst.getInt("addressId"));
            addressDetails.setCity(rst.getString("city"));
            addressDetails.setCountry(rst.getString("country"));

            hm.put(employee,addressDetails);
        }
        return hm;
    }

    @Override
    public HashMap<Employee, AddressDetails> getEmpAddDetailsByEmployeeId(int id) throws SQLException {
        HashMap<Employee,AddressDetails> hm = new LinkedHashMap<>();
        prst = cn.prepareStatement(" SELECT empId,name,city,country FROM employee NATURAL JOIN addressDetails WHERE empId=?");
        prst.setInt(1,id);
        ResultSet rst = prst.executeQuery();
        //empId  name  city  country
        while (rst.next()){
            Employee employee = new Employee();
            employee.setEmpId(rst.getInt(1));
            employee.setName(rst.getString(2));
            AddressDetails addressDetails = new AddressDetails();
            addressDetails.setCity(rst.getString(3));
            addressDetails.setCountry(rst.getString(4));
            hm.put(employee,addressDetails);
        }
        return hm;
    }

    @Override
    public List<String> getEmpByCity(String city) throws SQLException {
        List<String> lst = new ArrayList<>();
        prst = cn.prepareStatement("SELECT name AS 'employees who are in the given city' FROM employee NATURAL JOIN addressDetails WHERE city=?");
        prst.setString(1,city);
        ResultSet rst = prst.executeQuery();
        while (rst.next()){
            lst.add(rst.getString(1));
        }
        return lst;
    }

    @Override
    public HashMap<String,Integer> getEmpWithHighestSalary() throws SQLException {
        HashMap<String,Integer> hm = new LinkedHashMap<>();
        prst = cn.prepareStatement("SELECT name,salary FROM employee WHERE salary = (SELECT MAX(salary) FROM employee);");
        ResultSet rst = prst.executeQuery();
        if (rst.next()){
            hm.put(rst.getString(1),rst.getInt(2));
        }
        return hm;
    }

    @Override
    public List<Employee> get5YearExperiencedEmp() throws SQLException {
        List<Employee> lst = new ArrayList<>();
        prst = cn.prepareStatement(" SELECT * FROM employee WHERE joinDate <= DATE_SUB(CURDATE(), INTERVAL 5 YEAR)");
        ResultSet rst = prst.executeQuery();

        while (rst.next()){
            Employee employee = new Employee();
            employee.setEmpId(rst.getInt(1));
            employee.setName(rst.getString(2));
            employee.setSalary(rst.getDouble(3));
            employee.setJoinDate(rst.getDate(4));
            lst.add(employee);
        }
        return lst;
    }

    public void cleanUp() throws SQLException{
        if(prst!=null){
            prst.close();
        }
        if (cn!=null){
            cn.close();
        }
        System.out.println("Employee Dao Cleaned Up!");
    }
}
