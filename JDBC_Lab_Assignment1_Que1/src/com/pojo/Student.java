package com.pojo;

import java.util.Date;

public class Student {
    private int rno;
    private String name;
    private int marks;
    private Date dateOfBirth;
    private String city;

    public Student() {
    }

    public Student(String name, int marks, Date dateOfBirth, String city) {
        super();
        this.name = name;
        this.marks = marks;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
    }

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "rno=" + rno +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                ", dateOfBirth=" + dateOfBirth +
                ", city='" + city + '\'' +
                '}';
    }
}
