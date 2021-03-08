package com.company;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class addStudent extends Main {

    public void insertStudent(String s_first_name, String s_last_name, String s_phone, String s_classroom, int s_teacher_id) {

        Scanner sc = new Scanner(System.in);
        //implementing scanner
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;

        ConnectDB obj_ConnectDB = new ConnectDB();
        connection = obj_ConnectDB.get_connection();

        try {
            //SQL statement for insertion of students
            String sql = "INSERT INTO students (first_name, last_name, phone, classroom, teacher_id) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement st1 = connection.prepareStatement(sql);
            st1.setString(1, s_first_name);
            st1.setString(2, s_last_name);
            st1.setString(3, s_phone);
            st1.setString(4, s_classroom);
            st1.setInt(5, s_teacher_id);
            ResultSet result = st1.executeQuery();

            System.out.println("User added successfully!");

            st.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
