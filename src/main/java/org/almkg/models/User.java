package org.almkg.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yarnykh on 26.01.2016.
 */
@DatabaseTable(tableName = "users")
public class User {

    private static final String LOGIN_FIELD = "login";
    private static final String PASSWORD_FIELD = "passwd";
    private static final String NAME_FIELD = "name";

    @DatabaseField(generatedId = true)
    private int userId;

    @DatabaseField(columnName = LOGIN_FIELD)
    private String login;

    @DatabaseField(columnName = PASSWORD_FIELD)
    private String password;

    @DatabaseField(columnName = NAME_FIELD)
    private String name;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
}
