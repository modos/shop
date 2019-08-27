package com.modos.shop.models;

public class User {
    private String username, password, name, age;
    private int manager;

    // constants for database
    public static final String TABLE_NAME = "user";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String MANAGER = "manager";

    public static final String QUERY_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME + " TEXT, " +
            PASSWORD + " TEXT, " +
            NAME + " TEXT, " +
            AGE + " INTEGER, " +
            MANAGER + " INTEGER)";

    public static final String QUERY_ADMIN = "insert into " + TABLE_NAME + " (" + USERNAME + ", " + PASSWORD + ", "
            + NAME + ", " + AGE + ", " + MANAGER + ") values('admin', 'admin', 'modos', 20, 1);";


    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
