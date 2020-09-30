package com.example.hivemanager;
import java.sql.*;

public class SQLConnection {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://spike-exercise.cpahsyvhsld2.us-east-2.rds.amazonaws." +
            "com/spike_exercise?autoReconnect=true&useSSL=false";

    private final String USERNAME = "admin";
    private final String PASSWORD = "administrator";

    private Connection conn = null;
    private Statement stmt = null;
    private String sql = null;
    private ResultSet rs = null;
    private String status = null;
    private CallableStatement cstmt = null;


    public SQLConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();

            System.out.println("Database is successfully Connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void closeConnection() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void createAccount(String username, String password) {

        try {
            cstmt = conn
                    .prepareCall("call createAccount(\"" + username + "\", \"" + password
                            + "\",?)");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.executeUpdate();
            status = cstmt.getString(1);

            if (status.equals("Success")) {
                System.out.println("Successfully created account");
            } else {
                System.out.println("The acount already exists");
            }
        } catch (SQLException e) {
            System.out.println("Error in createAccount");
        }

    }

    protected void searchAccount(String username, String password) {
        try {
            cstmt = conn
                    .prepareCall("call searchAccount(\"" + username + "\", \"" + password
                            + "\",?)");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.executeUpdate();
            status = cstmt.getString(1);

            if (status.equals("Username")) {
                System.out.println("The account does not exist");
            } else if (status.equals("Password")) {
                System.out.println("The password is wrong");
            } else {
                System.out.println("Successfully logged in");
            }
        } catch (SQLException e) {
            System.out.println("Error in searchAccount");
        }
    }

    protected void updateProfile(String username, Blob picture, String address, String contact) {

        sql = "call updateProfile(?, ?, ?,?)";
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, username);
            cstmt.setBlob(2, picture);
            cstmt.setString(3, address);
            cstmt.setString(4, contact);

            cstmt.execute();
            System.out.println("Successfully updated profile");
        } catch (SQLException e) {
            System.out.println("Error in updateProfile");
        }
    }

    protected void createHive(String hive, String inspection, String health, String honey,
                              String queen, String equipHive, String equipInven, String loss,
                              String gain) {
        sql = "call createHive(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, hive);
            cstmt.setString(2, inspection);
            cstmt.setString(3, health);
            cstmt.setString(4, honey);
            cstmt.setString(5, queen);
            cstmt.setString(6, equipHive);
            cstmt.setString(7, equipInven);
            cstmt.setString(8, loss);
            cstmt.setString(9, gain);

            cstmt.registerOutParameter(10, Types.VARCHAR);
            cstmt.executeUpdate();
            status = cstmt.getString(10);

            if (status.equals("Exists")) {
                System.out.println("The hive already exists");
            } else {
                System.out.println("The hive is successfully created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void updateHive(String hive, String inspection, String health, String honey,
                              String queen, String equipHive, String equipInven, String loss,
                              String gain) {
        sql = "call updateHive(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, hive);
            cstmt.setString(2, inspection);
            cstmt.setString(3, health);
            cstmt.setString(4, honey);
            cstmt.setString(5, queen);
            cstmt.setString(6, equipHive);
            cstmt.setString(7, equipInven);
            cstmt.setString(8, loss);
            cstmt.setString(9, gain);

            cstmt.executeUpdate();

            System.out.println("Successfully updated Hive");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected void deleteHive(String hive) {
        sql = "call deleteHive(\"" + hive +"\")";
        System.out.println(sql);
        try {
            stmt.executeQuery(sql);
            System.out.println("Successfully deleted the hive");
        } catch (SQLException e) {
            System.out.println("Error in deleteHive");
        }

    }

    protected void hiveList() {
        sql = "Call hiveList()";
        try {
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String hive = rs.getString("Hive");
                String inspection = rs.getString("Result_Of_Inspection");
                String health = rs.getString("Health");
                String honey = rs.getString("Honey_Stores");
                String qp = rs.getString("Queen_Production");
                String ehive = rs.getString("Equipment_on_Hive");
                String einven = rs.getString("Equipment_in_Inventory");
                String loss =rs.getString("loss");
                String gain = rs.getString("gain");
                System.out.println(hive + "\t" + inspection + " \t" + health + " \t" + honey +
                        " \t" + qp + " \t" + ehive + " \t" + einven + " \t" + loss + " \t" + gain);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error in hivelist");
            System.out.println(e.getMessage());
        }


    }

}
