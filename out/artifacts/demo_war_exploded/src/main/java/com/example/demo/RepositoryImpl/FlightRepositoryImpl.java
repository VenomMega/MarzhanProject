package com.example.demo.RepositoryImpl;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Airport;
import com.example.demo.Entity.Flight;
import com.example.demo.Repositories.FlightRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class FlightRepositoryImpl implements FlightRepository {
    @Override
    public List<Flight> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT f FROM Flight f", Flight.class).getResultList();

    }

    @Override
    public Flight findById(Long id, Session session) {
        return session.get(Flight.class, id);
    }

    @Override
    public void save(Flight flight, Session session) {
        session.save(flight);
    }

    @Override
    public void update(Flight flight, Session session) {
        session.update(flight);
    }

    @Override
    public void delete(Flight flight, Session session) {
        session.delete(flight);
    }
}
