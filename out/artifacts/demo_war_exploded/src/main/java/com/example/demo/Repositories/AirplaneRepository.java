package com.example.demo.Repositories;

import com.example.demo.Entity.Airplane;
import com.example.demo.Entity.Airport;
import com.example.demo.Entity.Flight;
import org.hibernate.Session;

import java.util.List;

public interface AirplaneRepository {
    List<Airplane> getAll();
    Airplane findById(Long id, Session session);
    void save(Airplane airplane, Session session);
    void update(Airplane airplane, Session session);
    void delete(Airplane airplane, Session session);
    void addToFlight(Long id, Long id2, Session session);
}
