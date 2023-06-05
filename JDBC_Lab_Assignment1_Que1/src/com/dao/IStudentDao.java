package com.dao;
//Make a program that displays number of columns present in the given table.
//b). Create a program to call a stored procedure, created to insert student record [ rno, name, marks, DOB, city].
import java.sql.SQLException;
import java.util.List;

import com.pojo.Student;

public interface IStudentDao {

    //To get number of columns of the table
    int getColumnCount(String tableName) throws SQLException;

    //To insert values inside the table
    // rno | name    | marks | dateOfBirth | City
    String insertStudentRecord(Student student) throws SQLException;

    //display entire table
    List<Student> getEntireTable() throws SQLException;
}
