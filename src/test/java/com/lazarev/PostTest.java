package com.lazarev;

import com.lazarev.database.model.PostModel;
import com.lazarev.database.model.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.lazarev.utils.BuilderPostModel.getRandomPost;
import static com.lazarev.utils.BuilderUserModel.getRandomUser;
import static com.lazarev.utils.RandomData.TEST_UPDATE_TITLE;

/**
 * Класс с тестами на таблицу Posts
 */
public class PostTest extends BaseTest {

    private PostModel post;
    private UserModel user;

    @BeforeEach
    public void createUserAndPost() {
        //создание пользователя
        user = getRandomUser();

        //внесение потзователя в БД
        steps.createUser(user);

        //инициализация пользователю поля ID
        user = steps.selectBy(user);

        //создание поста
        post = getRandomPost();

        //инициализация в посте данных ID пользователя, написавшего пост
        post.setUserID(user.getId());

        //внесение поста в БД
        stepsPost.createPost(post);
    }

    @AfterEach
    public void deleteUserAfterTest() {
        //после теста удаление созданного тестового пользователя
        steps.deleteUser(user.getId());
    }

    @Test
    public void testCreatePost() {
        boolean isCreated = false;
        post = stepsPost.selectByPost(post);
        List<PostModel> listAll = stepsPost.selectAllPost();

        for (PostModel element : listAll) {
            if (element.equals(post)) {
                isCreated = true;
                break;
            }
        }
        assertThat(isCreated)
                .withFailMessage("Post don`t created")
                .isEqualTo(true);

        stepsPost.deletePost(post);
    }

    @Test
    public void testReadPost() {
        post = stepsPost.selectByPost(post);
        PostModel postBD = stepsPost.selectByPost(post);

        assertThat(postBD)
                .withFailMessage("Post don`t read")
                .isEqualTo(post);

        stepsPost.deletePost(post);
    }

    @Test
    public void testUpdatePost() {
        post = stepsPost.selectByPost(post);
        post.setTitle(TEST_UPDATE_TITLE);

        stepsPost.updatePost(post);

        PostModel postBD = stepsPost.selectByPost(post);

        assertThat(postBD)
                .withFailMessage("Post don`t update")
                .isEqualTo(post);

        stepsPost.deletePost(post);
    }

    @Test
    public void testDeletePost() {
        boolean isDeleted = true;

        post = stepsPost.selectByPost(post);

        stepsPost.deletePost(post);

        List<PostModel> list = stepsPost.selectAllPost();

        for (PostModel element : list) {
            if (element.equals(post)) {
                isDeleted = false;
                break;
            }
        }
        assertThat(isDeleted)
                .withFailMessage("Post don`t delete")
                .isEqualTo(true);

    }
}