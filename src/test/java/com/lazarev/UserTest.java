package com.lazarev;

import com.lazarev.database.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.lazarev.utils.BuilderUserModel.getRandomUser;
import static com.lazarev.utils.RandomData.*;

/**
 * Класс с тестами на таблицу Users
 */
public class UserTest extends BaseTest {

    private UserModel user;

    @BeforeEach
    public void createUser() {
        //создание пользователя
        user = getRandomUser();

        //внесение пользователя в БД
        steps.createUser(user);
    }

    @Test
    public void testCreateUser() {
        boolean isCreated = false;
        int id = -1;

        List<UserModel> list = steps.selectAll();
        for (UserModel element : list) {
            if (element.getNickname().equals(user.getNickname())) {
                id = element.getId();
                isCreated = true;
                assertThat(element).isEqualTo(user);
            }
        }
        assertThat(isCreated)
                .withFailMessage("User wasn`t create")
                .isEqualTo(true);

        steps.deleteUser(id);
    }

    @Test
    public void testReadUser() {
        UserModel userDataBase = steps.selectBy(user);

        assertThat(userDataBase)
                .withFailMessage("Users don`t read")
                .isEqualTo(user);

        steps.deleteUser(userDataBase.getId());
    }

    @Test
    public void testUpdateUser() {
        user.setPassword(TEST_UPDATE_PASSWORD);

        steps.updateUser(user);

        UserModel userDataBase = steps.selectBy(user);

        assertThat(userDataBase)
                .withFailMessage("Users don`t update")
                .isEqualTo(user);

        steps.deleteUser(userDataBase.getId());
    }

    @Test
    public void testDeleteUser() {
        boolean isDeleted = true;

        user = steps.selectBy(user);

        steps.deleteUser(user.getId());

        List<UserModel> list = steps.selectAll();
        for (UserModel element : list) {
            if (element.getNickname().equals(user.getNickname())) {
                isDeleted = false;
                break;
            }
        }
        assertThat(isDeleted)
                .withFailMessage("User wasn`t delete")
                .isEqualTo(true);
    }
}
