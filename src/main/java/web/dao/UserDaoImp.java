package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    private UserDaoImp (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteUser(long id) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("delete from User where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    public List<User> listUsers() {
        List<User> allUsers = sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
        return allUsers;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public User getUser(long id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }
}
