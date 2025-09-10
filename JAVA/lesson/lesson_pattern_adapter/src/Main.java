import com.sun.jdi.IntegerValue;

import java.util.ArrayList;
import java.util.Map;

// Целевой интерфейс
interface Database {
    void connect();
    void query(String query);
}

// Адаптируемый класс для MySQL
class MySQLDatabase {
    public void connectMySQL() {
        System.out.println("Подключение к MySQL");
    }

    public void executeQuery(String query) {
        System.out.println("Выполнение запроса в MySQL: " + query);
    }
}

// Адаптируемый класс для PostgreSQL
class PostgreSQLDatabase {
    public void connectPostgreSQL() {
        System.out.println("Подключение к PostgreSQL");
    }

    public void executePostgreSQLQuery(String query) {
        System.out.println("Выполнение запроса в PostgreSQL: " + query);
    }
}

// Адаптер для MySQL
class MySQLAdapter implements Database {
    private MySQLDatabase mySQLDatabase;

    public MySQLAdapter(MySQLDatabase mySQLDatabase) {
        this.mySQLDatabase = mySQLDatabase;
    }

    @Override
    public void connect() {
        mySQLDatabase.connectMySQL();
    }

    @Override
    public void query(String query) {
        mySQLDatabase.executeQuery(query);
    }
}

// Адаптер для PostgreSQL
class PostgreSQLAdapter implements Database {
    private PostgreSQLDatabase postgreSQLDatabase;

    public PostgreSQLAdapter(PostgreSQLDatabase postgreSQLDatabase) {
        this.postgreSQLDatabase = postgreSQLDatabase;
    }

    @Override
    public void connect() {
        postgreSQLDatabase.connectPostgreSQL();
    }

    @Override
    public void query(String query) {
        postgreSQLDatabase.executePostgreSQLQuery(query);
    }
}

public class Main {
    public static void main(String[] args) {
        MySQLDatabase mySQLDatabase = new MySQLDatabase();
        PostgreSQLDatabase postgreSQLDatabase = new PostgreSQLDatabase();

        Database mySQLAdapter = new MySQLAdapter(mySQLDatabase);
        Database postgreSQLAdapter = new PostgreSQLAdapter(postgreSQLDatabase);

        mySQLAdapter.connect();
        mySQLAdapter.query("SELECT * FROM users");

        postgreSQLAdapter.connect();
        postgreSQLAdapter.query("SELECT * FROM users");
    }
}