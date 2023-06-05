package com;

import com.dao.EmployeeDaoImpl;
import com.pojos.AddressDetails;
import com.pojos.Employee;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

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
public class Main {

    public static void main(String[] args) {
        try(Scanner sc =  new Scanner(System.in)){
            EmployeeDaoImpl dao = new EmployeeDaoImpl();
            boolean exit = false;
            try {
                while (!exit){
                    System.out.println("-------------------------------------------------------");
                    System.out.println("1.Insert employee records.");
                    System.out.println("2.Insert employee's address details.");
                    System.out.println("3.Display employee table.");
                    System.out.println("4.Display employee's Address Details table.");
                    System.out.println("5.Select the employee records with their address.");
                    System.out.println("6.Select the address of an employee whose employee id is given.");
                    System.out.println("7.Select all the employees who are in the given city.");
                    System.out.println("8.Select the employee who gets highest salary.");
                    System.out.println("9.Select all the employees who has experience more than 5 years.");
                    System.out.println("0.Clean up and close.");
                    System.out.println();

                    System.out.print(">>>Enter your choice number from above menu: ");
                    int choice = sc.nextInt();
                    switch (choice){
                        case 1:
                            System.out.println("Enter employee record to be inserted : (name | salary | joinDate(YYYY-MM-DD))");
                            dao.insertEmpRecord(new Employee(sc.next(),sc.nextDouble(), Date.valueOf(sc.next())));
                            break;
                        case 2:
                            System.out.println("Enter employee's address record to be inserted : (addressId | city | country | empId)");
                            dao.insertEmpAddress(new AddressDetails(sc.nextInt(),sc.next(),sc.next(),sc.nextInt()));
                            break;
                        case 3:
                            System.out.println("Latest Employee table:");
                            List<Employee> lst1 = dao.getAllEmpRecords();
                            if(lst1.size()>0){
                                lst1.forEach(System.out::println);
                            }
                            else {
                                System.out.println("Empty Table!");
                            }
                            break;
                        case 4:
                            System.out.println("Latest Employee's address details table:");
                            List<AddressDetails> lst2 = dao.getAllAddressRecords();
                            if(lst2.size()>0){
                                lst2.forEach(System.out::println);
                            }
                            else {
                                System.out.println("Empty table!");
                            }
                            break;
                        case 5:
                            System.out.println("Employee records with their address:");
                            HashMap<Employee,AddressDetails> hm1 = dao.getEmpRecordsWithAddress();
                            for(Map.Entry<Employee,AddressDetails> x : hm1.entrySet()){
                                System.out.println(x.getKey() + " " + x.getValue());
                            }
                            break;
                        case 6:
                            System.out.print("Enter employee Id to get employee's address datails: ");
                            int id = sc.nextInt();
                            System.out.println("Address details of employee whose employee Id is " + id);
                            HashMap<Employee,AddressDetails> hm2 = dao.getEmpAddDetailsByEmployeeId(id);
                            for (Map.Entry<Employee,AddressDetails> x : hm2.entrySet()){
                                System.out.println(x.getKey() + " " + x.getValue());
                            }
                            break;
                        case 7:
                            System.out.print("Enter name of the city: ");
                            String city = sc.next();
                            List<String> lst3 = dao.getEmpByCity(city);
                            if(lst3.size()>0){
                                System.out.println("Employees who are in the " + city + " city");
                                lst3.forEach(System.out::println);
                            }
                            else {
                                System.out.println("Empty Table!");
                            }
                            break;
                        case 8:
                            System.out.println("Employee with highest salary: ");
                            dao.getEmpWithHighestSalary().forEach((name,salary)-> System.out.println("Name:" + name + " Salary:" + salary));
                            break;
                        case 9:
                            List<Employee> lst4 = dao.get5YearExperiencedEmp();
                            if (lst4.size()>0){
                                System.out.println("All employees who has experience more than 5 years: ");
                                lst4.forEach(System.out::println);
                            }
                            else {
                                System.out.println("Empty Table!");
                            }
                            break;
                        case 0:
                            dao.cleanUp();
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice...Please try again!");
                    }
                }
            }catch (NumberFormatException  | InputMismatchException e){
                System.out.println("Please Enter valid choice number from given menu!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
