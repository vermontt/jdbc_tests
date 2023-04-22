package com.lazarev.database.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


/**
 * Класс, содержащий поля таблицы Users
 */
@Data
@Builder
@EqualsAndHashCode(exclude = "id")
public class UserModel {

    private final int id;
    private final String nickname;
    private final String email;
    private String password;
    private final LocalDate birtDate;
    private final boolean isMale;
}