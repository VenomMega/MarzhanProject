package com.example.demo.Repositories;

import com.example.demo.Entity.Airport;
import com.example.demo.Entity.Flight;
import org.hibernate.Session;

import java.util.List;

public interface FlightRepository {
    List<Flight> getAll();
    Flight findById(Long id, Session session);
    void save(Flight t, Session session);
    void update(Flight t, Session session);
    void delete(Flight t, Session session);
}
