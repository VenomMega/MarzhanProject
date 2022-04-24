package com.example.demo.Repositories;

import com.example.demo.Entity.Airport;
import org.hibernate.Session;

import java.util.List;


public interface AirportRepository {
    List<Airport> getAll();
    Airport findById(Long id, Session session);
    void save(Airport t, Session session);
    void update(Airport t, Session session);
    void delete(Airport t, Session session);
}
