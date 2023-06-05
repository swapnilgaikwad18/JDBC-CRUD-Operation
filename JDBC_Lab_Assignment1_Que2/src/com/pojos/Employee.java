package com.pojos;

import java.util.Date;

public class Employee {
    // empId | name   | salary | joinDate
    private int empId;
    private String name;
    private double salary;
    private Date joinDate;

    public Employee() {

    }

    public Employee(String name, double salary, Date joinDate) {
        super();
        this.name = name;
        this.salary = salary;
        this.joinDate = joinDate;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public java.sql.Date getJoinDate() {
        return (java.sql.Date) joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return  "empId=" + empId +"  name='" + name + '\'' +"  salary=" + salary +"  joinDate=" + joinDate + "  ";
    }
}
