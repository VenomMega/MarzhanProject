package com.example.demo.Repositories;

import com.example.demo.Entity.User;
import org.hibernate.Session;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User findById(Long id, Session session);
    void save(User user, Session session);
//    void update(User user, Session session);
    void delete(User user, Session session);
//    void addToFlight(Long id, Long id2, Session session);
}

// Airplane - user