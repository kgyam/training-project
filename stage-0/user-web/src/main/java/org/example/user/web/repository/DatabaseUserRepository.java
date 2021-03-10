package org.example.user.web.repository;

import org.example.user.web.domain.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-02 17:39
 * @since
 */
public class DatabaseUserRepository implements UserRepository {
    private static Logger logger = Logger.getLogger(DatabaseUserRepository.class.getName());

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    @Override
    public boolean save(@Valid User user) {
        try {
            logger.info("save user:" + user);
            entityManager.persist(user);
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
