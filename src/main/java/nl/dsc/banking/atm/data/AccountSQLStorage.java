package nl.dsc.banking.atm.data;

import nl.dsc.banking.atm.domain.Account;
import nl.dsc.banking.atm.domain.AccountStorage;

import java.math.BigDecimal;
import java.sql.*;

public class AccountSQLStorage implements AccountStorage {
    private final String url;
    private final String username;
    private final String password;

    public AccountSQLStorage(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Account load() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            String query = "SELECT checking_balance, saving_balance FROM accounts_table WHERE id = 1";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                BigDecimal checkingBalance = resultSet.getBigDecimal("checking_balance");
                BigDecimal savingBalance = resultSet.getBigDecimal("saving_balance");
                return new Account(checkingBalance, savingBalance);
            }
        } catch (SQLException e) {
            // Handle exceptions, log errors, etc.
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void save(Account account) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE accounts_table SET checking_balance = ?, saving_balance = ? WHERE id = 1")) {

            preparedStatement.setBigDecimal(1, account.getCheckingBalance());
            preparedStatement.setBigDecimal(2, account.getSavingBalance());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                // No rows were updated, so the data may not exist in the database. You can choose to insert it.
                // Or, you can modify the SQL query to insert the data if it doesn't exist and update it if it does.
            }
        } catch (SQLException e) {
            // Handle exceptions, log errors, etc.
            e.printStackTrace();
        }
    }

//
//     try {
//        // Step 1: Register the JDBC driver (not always necessary with modern JDBC drivers)
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        // Step 2: Establish the database connection
//        String url = "jdbc:mysql://localhost:3306/atm_db";
//        String username = "your_username";
//        String password = "your_password";
//        Connection conn = null;
//    } catch (SQLException e) {
//        System.out.println("Database connection error: " + e.getMessage());
//    }
}


