package org.example.user.web.repository;

import org.example.user.web.domain.User;
import org.example.user.web.sql.DBConnectionManager;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-02 17:39
 * @since
 */
public class DatabaseUserRepository implements UserRepository {
    private static Logger LOGGER = Logger.getLogger(DatabaseUserRepository.class.getName());
    private static final String TABLE_NAME = "users";
    private static DBConnectionManager dbConnectionManager = new DBConnectionManager();

    @Override
    public boolean save(User user) {
        try {
            dbConnectionManager.save(user, TABLE_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
        return true;
    }

    @Override
    public boolean deleteById(Long userId) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User getById(Long userId) {
        return null;
    }

    @Override
    public User getByNameAndPassword(String userName, String password) {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }
}
