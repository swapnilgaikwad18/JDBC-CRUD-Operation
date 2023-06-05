package com.dao;

import com.pojo.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.utils.DBUtils.openConnection;

public class StudentDaoImpl implements IStudentDao{

    private Connection cn;
    private PreparedStatement prst1,prst2,prst3;

    public StudentDaoImpl() throws SQLException{
        cn = openConnection();
    }

    @Override
    public int getColumnCount(String tableName) throws SQLException {
        prst1 = cn.prepareStatement("SELECT * FROM " + tableName);
        System.out.println("student Dao created");
        ResultSet rst = prst1.executeQuery();
        ResultSetMetaData rsmd = rst.getMetaData();
        int count = rsmd.getColumnCount();
        return count;
    }

    @Override
    public String insertStudentRecord(Student student) throws SQLException {
        prst2 = cn.prepareStatement("INSERT INTO student VALUES (default,?,?,?,?)");
        // rno | name    | marks | dateOfBirth | City
        prst2.setString(1,student.getName());
        prst2.setInt(2,student.getMarks());
        prst2.setDate(3, (Date) student.getDateOfBirth());
        prst2.setString(4,student.getCity());
        int updatedRecord = prst2.executeUpdate();
        if (updatedRecord == 1){
            return "Student record inserted successfully!";
        }
        else {
            return "Insertion Failed!";
        }
    }

    @Override
    public List<Student> getEntireTable() throws SQLException {
        List<Student> list = new ArrayList<>();

        prst3 = cn.prepareStatement("SELECT * FROM student");

        ResultSet rst = prst3.executeQuery();

        while (rst.next()){
//            int rno = rst.getInt("rno");
//            String name = rst.getString("name");
//            int marks = rst.getInt("marks");
//            String dateOfBirth = rst.getString("dateOfBirth");
//            String city = rst.getString("city");
//            System.out.println(rno + "\t" + name + "\t  " + marks + "\t" + dateOfBirth + "\t  " + city);
            Student student = new Student();
            student.setRno(rst.getInt("rno"));
            student.setName(rst.getString("name"));
            student.setMarks(rst.getInt("marks"));
            student.setDateOfBirth(rst.getDate("dateOfBirth"));
            student.setCity(rst.getString("city"));
            list.add(student);
        }
        return list;
    }

    public void destroy() throws SQLException {
        if(prst1!=null){
            prst1.close();
        }
        if(prst2!=null){
            prst2.close();
        }
        if(prst3!=null){
            prst3.close();
        }
        if (cn!=null){
            cn.close();
        }
        System.out.println("Student dao cleaned up!");
    }
}
