package com.tdg.ato.schedule;

import com.tdg.ato.database.DbPoolConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by DerikZhang on 2017/6/1.
 */
public class ato2test {
    public static void main(String[] args) {
        // test to connect database
        testdb();
    }

    public static void testdb(){
        try {
            Connection dbcon = new DbPoolConnection().getConnection();
            Statement st = dbcon.createStatement();
            st.execute("select * from test");
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                System.out.println("name:" + rs.getString("name") + ":success");
            }
            st.close();
            dbcon.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect database");
        }
    }
}
