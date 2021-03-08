package com.company;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        //Calling ConnectDB
        ConnectDB obj_ConnectDB = new ConnectDB();
        connection = obj_ConnectDB.get_connection();

        String phone;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("Program is running...");
        System.out.println("Please type your phone number");
        phone = sc.nextLine();
        System.out.println("Your password");
        password = sc.nextLine();
        //Asking phone and password of teacher

        try {
            String sql1 = "SELECT password FROM teachers WHERE phone LIKE ? AND password LIKE ?";
            //sql statement for checking teacher with such data
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, phone);
            st1.setString(2, password);
            //replace ? to phone and password
            ResultSet result = st1.executeQuery();
            //execute a sql query
            if (result.next()){
                //if such query works then
                String sql2 = "SELECT * FROM teachers WHERE phone LIKE ?";
                PreparedStatement st2 = connection.prepareStatement(sql2);
                st2.setString(1, phone);
                ResultSet result1 = st2.executeQuery();
                while (result1.next()){
                    String first_name = result1.getString(2);
                    //getting name of the teacher (2nd column)
                    System.out.println("Welcome, " + first_name);
                }
                System.out.println("Choose the operation:");
                System.out.println("1. Show all students..");
                System.out.println("2. Add new student..");
                System.out.println("3. Delete student..");
                System.out.println("4. Put marks");
                System.out.println("5. Find AVG grade of a student");
                //menu with functionality
                int option;
                option = sc.nextInt();
                if(option == 1){
                    String sql3 = "SELECT * FROM students";
                    //option for calling all existing students
                    PreparedStatement st3 = connection.prepareStatement(sql3);
                    ResultSet result2 = st3.executeQuery();

                    while (result2.next()){
                        String id = result2.getString(1);
                        String name = result2.getString(2);
                        String last_name = result2.getString(3);
                        String phone1 = result2.getString(4);
                        String classroom = result2.getString(5);
                        String teacherid = result2.getString(6);

                        //outputting all 6 columns about students
                        System.out.print("ID: " + id);
                        System.out.print("| Name: " + name);
                        System.out.print("| Surname: " + last_name);
                        System.out.print("| Phone: " + phone1);
                        System.out.print("| Group: " + classroom);
                        System.out.print("| Teacher ID: " + teacherid + "\n");
                    }

                }
                if(option == 2){
                    //adding a students
                    String s_first_name;
                    String s_last_name;
                    String s_phone;
                    String s_classroom;
                    int s_teacher_id;

                    System.out.println("Your are adding new student:");
                    System.out.println("Student's first name..");
                    s_first_name = sc.next();
                    //getting first name
                    System.out.println("Student's last name..");
                    s_last_name = sc.next();
                    //getting second name
                    System.out.println("Phone number..");
                    s_phone = sc.next();
                    //getting phone number
                    System.out.println("Group..");
                    s_classroom = sc.next();
                    //getting group
                    System.out.println("Student's teacher's ID");
                    s_teacher_id = sc.nextInt();
                    //getting teacher of that student

                    addStudent addS = new addStudent();
                    addS.insertStudent(s_first_name, s_last_name, s_phone, s_classroom, s_teacher_id);
                    //calling addStudent class and insertStudent method
                    //passing variables
                    System.out.println("Student added");
                }
                if(option == 3){
                    //option for deleting a student
                    System.out.println("Your are deleting student:");
                    System.out.println("Student's first name..");
                    String s_first_name = sc.next();
                    //asking student name
                    System.out.println("Student's second name..");
                    String s_last_name = sc.next();
                    //asking student second name
                    System.out.println("Please type your password again to confirm changes..");
                    //asking confirmation password
                    String c_password = sc.next();
                    if(c_password.equals(password)){
                        DeleteStudent delS = new DeleteStudent();
                        delS.deleteS(s_first_name, s_last_name);
                        //calling deleteS method from DeleteStudent class
                        System.out.println("Deletion has been done!");
                    }else{
                        System.out.println("Incorrect password!");
                    }
                }
                if(option == 4){
                    //option for putting marks
                    System.out.println("Your are putting marks: ");
                    System.out.println("Student's id: ");
                    int s_id = sc.nextInt();
                    //asking student id
                    System.out.println("Put a mark:");
                    int mark = sc.nextInt();
                    //asking mark
                    System.out.println("Please type your password again to confirm changes..");
                    System.out.println(password);
                    //password confirmation
                    String c_password = sc.next();
                    if(c_password.equals(password)){
                        putMarks putmarks = new putMarks();
                        putmarks.putM(s_id, mark);
                        //calling putM method from putMarks class
                        System.out.println("Student got a mark!");
                    }else{
                        System.out.println("Incorrect password!");
                    }
                }
                if(option == 5){
                    //option for finding average grade
                    String s_first_name;
                    String s_last_name;
                    System.out.println("Type first name of a student..");
                    s_first_name = sc.next();
                    //asking name of a student
                    System.out.println("Last name..");
                    s_last_name = sc.next();
                    //last name
                    String sql = "SELECT AVG(grade) FROM exam LEFT JOIN students ON students.student_id = exam.student_id WHERE first_name LIKE ? AND last_name LIKE ?";
                    PreparedStatement st3 = connection.prepareStatement(sql);
                    //SQL statement for AVG grade combining exam and students table
                    st3.setString(1, s_first_name);
                    st3.setString(2, s_last_name);
                    //replacing ?s to first and last name in query
                    ResultSet result3 = st3.executeQuery();
                    while (result3.next()){
                        int avg = result3.getInt(1);
                        System.out.println(s_first_name + " has got on average " + avg + "marks ");
                    }
                }

            }else{
                System.out.println("Incorrect password or phone, please try again..");
                //if user typed wrong data
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


