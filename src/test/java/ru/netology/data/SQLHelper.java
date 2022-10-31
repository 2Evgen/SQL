package ru.netology.data;

import com.sun.jdi.connect.spi.Connection;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static DataHelper.VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var result = runner.query((java.sql.Connection) conn, codeSQL, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(result);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(String.valueOf(connection), "DELETE FROM auth_codes");
        runner.execute(String.valueOf(connection), "DELETE FROM card_transactions");
        runner.execute(String.valueOf(connection), "DELETE FROM cards");
        runner.execute(String.valueOf(connection), "DELETE FROM users");
    }

    public static String getCode() {
        String code = null;
        return null;
    }

    public static void clean() {
    }
}