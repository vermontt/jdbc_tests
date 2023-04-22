package com.lazarev.database.steps;

import com.lazarev.database.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс с релизацей SQL запросов на таблицу Users
 */
@AllArgsConstructor
public class SqlStepsUsers {

    //Подключение к БД
    private final Connection connection;

    //Поля таблицы Users
    private static final String ID = "id";
    private static final String NICKNAME_FIELD = "nickname";
    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private static final String BIRTH_DATE_FIELD = "birthdate";
    private static final String IS_MALE_FIELD = "is_male";

    //Синтаксис запросов
    private static final String INSERT_SQL_REQUEST = "INSERT INTO users (%s, %s, %s, %s, %s) " +
            "VALUES ('%s', '%s', '%s', '%s', %b)";
    private static final String SELECT_ALL_SQL_REQUEST = "SELECT * FROM users";
    private static final String SELECT_BY_ID_SQL_REQUEST = "SELECT * FROM users WHERE %s = '%s'";
    private static final String UPDATE_SQL_REQUEST = "UPDATE users SET %s = '%s' WHERE %s = '%s'";
    private static final String DELETE_SQL_REQUEST = "DELETE FROM users WHERE %s = %d";

    // Запрос INSERT
    @SneakyThrows
    public void createUser(UserModel userModel) {
        Statement stm = connection.createStatement();
        stm.executeUpdate(String.format(INSERT_SQL_REQUEST,
                NICKNAME_FIELD, EMAIL_FIELD, PASSWORD_FIELD, BIRTH_DATE_FIELD, IS_MALE_FIELD,
                userModel.getNickname(), userModel.getEmail(), userModel.getPassword(),
                userModel.getBirtDate(), userModel.isMale()));
    }

    //Запрос DELETE
    @SneakyThrows
    public void deleteUser(int id) {
        Statement stm = connection.createStatement();
        stm.executeUpdate(String.format(DELETE_SQL_REQUEST,
                ID, id));
    }

    //Запрос UPDATE
    @SneakyThrows
    public void updateUser(UserModel userModel) {
        Statement stm = connection.createStatement();
        stm.executeUpdate(String.format(UPDATE_SQL_REQUEST,
                PASSWORD_FIELD, userModel.getPassword(),
                NICKNAME_FIELD, userModel.getNickname()
        ));
    }

    //Запрос SELECT конкретной сущности
    @SneakyThrows
    public UserModel selectBy(UserModel userModel) {
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery(String.format(SELECT_BY_ID_SQL_REQUEST,
                NICKNAME_FIELD, userModel.getNickname()));
        if (result.next()) {
            return UserModel.builder()
                    .id(result.getInt(ID))
                    .nickname(result.getString(NICKNAME_FIELD))
                    .email(result.getString(EMAIL_FIELD))
                    .password(result.getString(PASSWORD_FIELD))
                    .birtDate(result.getObject(BIRTH_DATE_FIELD, LocalDate.class))
                    .isMale(result.getBoolean(IS_MALE_FIELD))
                    .build();
        }
        return null;
    }

    //Запрос SELECT всех записей таблицы
    @SneakyThrows
    public List<UserModel> selectAll() {
        List<UserModel> listAll = new ArrayList<>();
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery(SELECT_ALL_SQL_REQUEST);
        while (result.next()) {
            listAll.add(UserModel.builder()
                    .id(result.getInt(ID))
                    .nickname(result.getString(NICKNAME_FIELD))
                    .email(result.getString(EMAIL_FIELD))
                    .password(result.getString(PASSWORD_FIELD))
                    .birtDate(result.getObject(BIRTH_DATE_FIELD, LocalDate.class))
                    .isMale(result.getBoolean(IS_MALE_FIELD))
                    .build());
        }
        return listAll;
    }
}
