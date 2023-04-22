package com.lazarev;

import com.lazarev.database.SqlConnector;
import com.lazarev.database.steps.SqlStepsPosts;
import com.lazarev.database.steps.SqlStepsUsers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;

/**
 * Базовый тестовый класс
 */
public class BaseTest {

    //Переменные с запросами SQL
    public SqlStepsUsers steps;
    public SqlStepsPosts stepsPost;

    //Экземпляр класс SQL коннектора
    private final SqlConnector connector = new SqlConnector();

    //Переменная с подключением
    protected Connection connection;

    @BeforeEach
    public void setUp() {
        connection = connector.openConnection();

        steps = new SqlStepsUsers(connection);

        stepsPost = new SqlStepsPosts(connection);
    }

    @AfterEach
    public void tearDown() {
        connector.closeConnection(connection);
    }
}
