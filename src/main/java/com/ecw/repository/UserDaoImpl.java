package com.ecw.repository;

import com.ecw.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
        logger.info("User saved successfully");
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User updated successfully");
    }

    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        for(User user : userList){
            logger.info("User List::"+user);
        }
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        logger.info("User loaded successfully");
        return user;
    }

    @Override
    public void remoteUser(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        if (null != user) {
            session.delete(user);
        }
        logger.info("Person deleted successfully");
    }
}
