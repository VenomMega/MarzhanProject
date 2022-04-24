package com.example.demo.Services;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class UserService {
    @EJB
    UserRepository userRepository;

    public List<User> getAll(){ return userRepository.getAll();}

    public User getAirplaneById(Long id) {
        return userRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        userRepository.save(user, session);
        tx1.commit();
        session.close();
    }

//    public void updateUserByName(Long id, String name) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        User user = userRepository.findById(id, session);
//        user.set
//        userRepository.update(user,session);
//        tx1.commit();
//        session.close();
//    }

    public void deleteUserById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = userRepository.findById(id,session);
        userRepository.delete(user, session);
        tx1.commit();
        session.close();
    }

//    public void addAirplaneToFlight(Long airplaneId, Long flightId){
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        userRepository.addToFlight(airplaneId, flightId, session);
//        tx1.commit();
//        session.close();
//    }
}
