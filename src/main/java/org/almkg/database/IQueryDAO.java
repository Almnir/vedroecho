package org.almkg.database;

import org.almkg.models.Device;
import org.almkg.models.User;

import java.util.List;

/**
 * Created by yarnykh on 02.02.2016.
 */
public interface IQueryDAO {
    public List<User> getAllUsers();
    public User getUserByName(String name);
    public void createUser(String login, String password, String passwordSalt, String name);
    public void deleteUserByName(String name);
    public Device getDeviceById(int id);
    public void updateDeviceById(int id);
}
