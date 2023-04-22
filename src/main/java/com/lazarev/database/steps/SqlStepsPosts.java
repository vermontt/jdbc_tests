package com.lazarev.database.steps;

import com.lazarev.database.model.PostModel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс с релизацей SQL запросов на таблицу Posts
 */
@AllArgsConstructor
public class SqlStepsPosts {

    //Подключение к БД
    private final Connection connection;

    //Поля таблицы Posts
    private static final String ID_POST = "id";
    private static final String USER_ID = "user_id";
    private static final String TITLE = "title";
    private static final String POST_TEXT = "post_text";
    private static final String POST_DATE = "post_date";

    //Синтаксис запросов
    private static final String INSERT_SQL = "INSERT INTO posts (%s, %s, %s, %s)" +
            "VALUES (%d, '%s', '%s', '%s')";
    private static final String SELECT_BY_SQL = "SELECT * FROM posts WHERE %s = %d AND %s = '%s'";
    private static final String SELECT_ALL_SQL = "SELECT * FROM posts";
    private static final String UPDATE_SQL = "UPDATE posts SET %s = '%s' WHERE %s = %d";
    private static final String DELETE_SQL = "DELETE FROM posts WHERE %s = %d";

    // Запрос INSERT
    @SneakyThrows
    public void createPost(PostModel postmodel) {
        Statement stm = connection.createStatement();
        stm.executeUpdate(String.format(INSERT_SQL,
                USER_ID, TITLE, POST_TEXT, POST_DATE,
                postmodel.getUserID(), postmodel.getTitle(), postmodel.getPostText(), postmodel.getPostDate()));
    }

    //Запрос DELETE
    @SneakyThrows
    public void deletePost(PostModel postModel) {
        Statement stm = connection.createStatement();
        stm.executeUpdate(String.format(DELETE_SQL,
                ID_POST, postModel.getId()));
    }

    //Запрос UPDATE
    @SneakyThrows
    public void updatePost(PostModel postModel) {
        Statement stm = connection.createStatement();
        stm.executeUpdate(String.format(UPDATE_SQL,
                TITLE, postModel.getTitle(),
                ID_POST, postModel.getId()));
    }

    //Запрос SELECT конкретной сущности
    @SneakyThrows
    public PostModel selectByPost(PostModel postModel) {
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery(String.format(SELECT_BY_SQL,
                USER_ID, postModel.getUserID(),
                TITLE, postModel.getTitle()));
        if (result.next()) {
            return PostModel.builder()
                    .id(result.getInt(ID_POST))
                    .userID(result.getInt(USER_ID))
                    .title(result.getString(TITLE))
                    .postText(result.getString(POST_TEXT))
                    .postDate(result.getObject(POST_DATE, LocalDate.class))
                    .build();
        }
        return null;
    }

    //Запрос SELECT всех записей таблицы
    @SneakyThrows
    public List<PostModel> selectAllPost() {
        List<PostModel> listPosts = new ArrayList<>();

        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery(SELECT_ALL_SQL);

        while (result.next()) {
            listPosts.add(PostModel.builder()
                    .id(result.getInt(ID_POST))
                    .userID(result.getInt(USER_ID))
                    .title(result.getString(TITLE))
                    .postText(result.getString(POST_TEXT))
                    .postDate(result.getObject(POST_DATE, LocalDate.class))
                    .build());
        }
        return listPosts;
    }
}