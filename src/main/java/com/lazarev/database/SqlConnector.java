package com.lazarev.database;

import com.lazarev.config.BaseConfig;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Класс в котором подключение к базе данных
 */
public class SqlConnector {

    private static final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    @SneakyThrows
    public Connection openConnection() {

        Class.forName(config.driverDb());

        return DriverManager.getConnection(config.urlDb(), config.userDb(), config.passwordDb());
    }

    @SneakyThrows
    public void closeConnection(Connection connection) {
        connection.close();
    }
}