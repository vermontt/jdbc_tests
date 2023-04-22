package com.lazarev.utils;


import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

/**
 * Класс с тестовыми данными
 */
@UtilityClass
public class RandomData {

    Faker faker = new Faker();

    //Тестовые данные на таблицу Users
    private static final String EMAIL_REGEXP = "[a-z]{10}\\@[a-z]{5}\\.[a-z]{2}";

    public static final String TEST_UPDATE_PASSWORD = "FREdd012345";

    public static String getRandomNickname() {
        return faker.name().username();
    }

    public static String getRandomEmail() {
        return faker.regexify(EMAIL_REGEXP);
    }

    public static String getRandomPassword() {
        return faker.cat().breed();
    }

    public static LocalDate getRandomBirthDate() {
        int year = faker.number().numberBetween(1930, 2000);
        int month = faker.number().numberBetween(1, 12);
        int day = faker.number().numberBetween(1, 28);
        return LocalDate.of(year, month, day);
    }

    public static boolean getRandomSex() {
        return faker.random().nextBoolean();
    }

    //Тестовые данные на таблицу Posts
    private static final String POST_TEXT_REGEXP = "[a-z]{25} [a-z]{20}";

    public static String getRandomPostText() {
        return faker.regexify(POST_TEXT_REGEXP);
    }

    public static LocalDate getRandomPostDate() {
        int year = faker.number().numberBetween(2021, 2022);
        int month = faker.number().numberBetween(1, 12);
        int day = faker.number().numberBetween(1, 28);
        return LocalDate.of(year, month, day);
    }

    public static String getRandomTitle() {
        return faker.beer().name();
    }

    public static final String TEST_UPDATE_TITLE = "Woooooooo";
}
