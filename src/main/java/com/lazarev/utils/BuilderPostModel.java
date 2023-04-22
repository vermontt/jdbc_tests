package com.lazarev.utils;

import com.lazarev.database.model.PostModel;
import lombok.experimental.UtilityClass;

/**
 * Класс для создания тестовго объекта таблицы Posts
 */
@UtilityClass
public class BuilderPostModel {

    public static PostModel getRandomPost() {

        return PostModel.builder()
                .title(RandomData.getRandomTitle())
                .postText(RandomData.getRandomPostText())
                .postDate(RandomData.getRandomPostDate())
                .build();
    }
}