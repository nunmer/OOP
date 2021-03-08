package com.company;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class putMarks {
    public void putM (int s_id, int mark){
        //getting id and mark
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        //connecting with ConnectDB
        ConnectDB obj_ConnectDB = new ConnectDB();
        connection = obj_ConnectDB.get_connection();

        try {
            //SQL statement for insertion
            String sql = "INSERT INTO exam (student_id, grade) VALUES(?, ?)";
            PreparedStatement st1 = connection.prepareStatement(sql);
            st1.setInt(1, s_id);
            st1.setInt(2, mark);
            //replacing ?s to id and mark variables
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
