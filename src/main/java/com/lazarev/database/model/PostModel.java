package com.lazarev.database.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Класс, содержащий поля таблицы Posts
 */
@Data
@Builder
@EqualsAndHashCode(exclude = "id")
public class PostModel {

    private final int id;
    private int userID;
    private String title;
    private final String postText;
    private final LocalDate postDate;
}