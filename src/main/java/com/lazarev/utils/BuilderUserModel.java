package com.lazarev.utils;

import com.lazarev.database.model.UserModel;
import lombok.experimental.UtilityClass;

/**
 * Класс для создания тестовго объекта таблицы Users
 */
@UtilityClass
public class BuilderUserModel {

    public static UserModel getRandomUser() {
        return UserModel.builder()
                .nickname(RandomData.getRandomNickname())
                .email(RandomData.getRandomEmail())
                .password(RandomData.getRandomPassword())
                .birtDate(RandomData.getRandomBirthDate())
                .isMale(RandomData.getRandomSex())
                .build();
    }
}
