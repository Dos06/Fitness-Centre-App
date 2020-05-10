package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";;
        // jdbc:mysql://localhost:3306/fussa?useLegacyDatetimeCode=false&amp;serverTimezone=UTC
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {

        String query = "INSERT INTO " + Configs.USERS_TABLE + " (" +
                Configs.USERS_NAME + ", " + Configs.USERS_SURNAME + ", " +
                Configs.USERS_LOGIN + ", " + Configs.USERS_PASSWORD + ", "
                + Configs.USERS_GENDER + ", " + Configs.USERS_AGE + ", "
                + Configs.USERS_ACTIVITY
                + ") VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = getDbConnection().prepareStatement(query);

            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getGender());
            ps.setInt(6, user.getAge());
            ps.setString(7, user.getActivity());

            ps.executeUpdate();         // save
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getUser(User user) {
        ResultSet rs = null;

        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + USERS_LOGIN + " = ? AND "
                + USERS_PASSWORD + " = ?";

        try {
            PreparedStatement ps = getDbConnection().prepareStatement(query);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public void deleteUser(User user) {
        String query = "DELETE FROM " + USERS_TABLE + " WHERE " + USERS_LOGIN + " = '" + user.getLogin() + "'";

        try {
            PreparedStatement ps = getDbConnection().prepareStatement(query);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllUsers() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + USERS_TABLE;
        ResultSet rs = getDbConnection().createStatement().executeQuery(query);

        return rs;
    }

    public ResultSet getActivities() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM activities";
        ResultSet rs = getDbConnection().createStatement().executeQuery(query);

        return rs;
    }

    public void addActivity(String activity) {
        String query = "INSERT INTO activities (name) VALUES ('" + activity + "')";

        try {
            PreparedStatement ps = getDbConnection().prepareStatement(query);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteActivity(String activity) {
        String query = "DELETE FROM activities WHERE name = '" + activity + "'";

        try {
            PreparedStatement ps = getDbConnection().prepareStatement(query);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(String login, String password, String column, String record) {
//        String query = "UPDATE " + USERS_TABLE + " SET '" + column + "' = '" + record + "' WHERE '" + Configs.USERS_LOGIN
//                + "' = '" + login + "', '" + Configs.USERS_PASSWORD + "' = '" + password + "'";

        String query = "UPDATE " + USERS_TABLE + " SET " + column + " = '" + record + "' WHERE " + Configs.USERS_LOGIN
                + " = '" + login + "';";

        try {
            PreparedStatement ps = getDbConnection().prepareStatement(query);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUsersByActivity(String activity) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE activity = '" + activity + "';";
        ResultSet rs = getDbConnection().createStatement().executeQuery(query);

        return rs;
    }


}
