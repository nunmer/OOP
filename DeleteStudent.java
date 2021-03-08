package com.company;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteStudent extends Main {
    public void deleteS (String s_first_name, String s_last_name){

        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;

        ConnectDB obj_ConnectDB = new ConnectDB();
        connection = obj_ConnectDB.get_connection();

        try {

            String sql = "DELETE FROM students WHERE first_name LIKE ? and last_name LIKE ?";
            PreparedStatement st1 = connection.prepareStatement(sql);
            st1.setString(1, s_first_name);
            st1.setString(2, s_last_name);

            ResultSet result = st1.executeQuery();

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
