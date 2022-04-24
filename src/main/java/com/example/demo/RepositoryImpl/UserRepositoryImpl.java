package com.example.demo.RepositoryImpl;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Shop;
import com.example.demo.Repositories.UserRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    @Override
    public User findById(Long id, Session session) {
        return session.get(User.class, id);
    }

    @Override
    public void save(User user, Session session) {
        session.save(user);
    }

//    @Override
//    public void update(User user, Session session) {
//        session.update(user);
//    }

    @Override
    public void delete(User user, Session session) {
        session.delete(user);
    }

//    @Override
//    public void addToFlight(Long id, Long id2, Session session) {
//        User airplane = session.get(User.class, id);
//        Shop flight = session.get(Shop.class, id2);
//        flight.setAirplane(airplane);
//        session.update(flight);
//        airplane.setFlight(flight);
//        session.update(airplane);
//    }
}
