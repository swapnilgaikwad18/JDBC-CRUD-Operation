package com;
import com.dao.StudentDaoImpl;
import com.pojo.Student;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)){
            String tableName = "Student";
            StudentDaoImpl dao = new StudentDaoImpl();
            int count = dao.getColumnCount(tableName);
            System.out.println("-----------------------------------------------");
            System.out.println("number of columns present in the given table: " + count);
            System.out.println("-----------------------------------------------");

            System.out.println();
            System.out.println("Student table before inserting record: ");
            System.out.println("-----------------------------------------------");
            List<Student> list1 = dao.getEntireTable();
            if(list1.size()>0){
                list1.forEach(System.out::println);
            }
            else {
                System.out.println("Student record doesn't found!");
            }
            dao.getEntireTable();
            System.out.println("-----------------------------------------------");

            System.out.println("Enter Student record to be inserted: (name | marks | dateOfBirth | City)");
            dao.insertStudentRecord(new Student(sc.next(), sc.nextInt(), Date.valueOf(sc.next()), sc.next()));
            System.out.println();

            System.out.println("Student table after inserted record: ");
            System.out.println("-----------------------------------------------");
            List<Student> list2 = dao.getEntireTable();
            if(list2.size()>0){
                list2.forEach(System.out::println);
            }
            else {
                System.out.println("Employee record doesn't found!");
            }
            System.out.println("-----------------------------------------------");

            dao.destroy();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
