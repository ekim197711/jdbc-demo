import java.sql.*;

public class MyJDBCDemo {
    public static void main(String[] args) {
        // driver name and database URL
        String jdbcDriver = "org.postgresql.Driver";
        String databaseURL = "jdbc:postgresql://localhost:5432/mikesdb";

        //  credentials
        final String username = "postgres";
        final String password = "mike";
        Connection connection = null;
        Statement statement = null;
        try {
            //Register driver
            Class.forName(jdbcDriver);

            //Open a connection
            System.out.println("Connect to database");
            connection = DriverManager.getConnection(databaseURL, username, password);

            //Execute a query
            System.out.println("Create statement");
            statement = connection.createStatement();
            String sql = "SELECT id, model, \"maxSpeed\", fuel, captain " +
                    "FROM spaceship";
            ResultSet resultSet = statement.executeQuery(sql);

            //Extract data from result set
            while (resultSet.next()) {
                //Retrieve data by type and column name
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                Double maxSpeed = resultSet.getDouble("maxSpeed");
                Double fuel = resultSet.getDouble("fuel");
                String captain = resultSet.getString("captain");

                //Display values
                String theFieldValues = String.format("Id: %s, model: %s, maxSpeed: %s, fuel: %s, captain: %s",
                        id, model, maxSpeed, fuel, captain);
                System.out.println(theFieldValues);
            }
            //Close recordset, statement and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException se) {
            //Handle errors
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
