package com.example.demo.RepositoryImpl;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Airplane;
import com.example.demo.Entity.Airport;
import com.example.demo.Entity.Flight;
import com.example.demo.Repositories.AirplaneRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class AirplaneRepositoryImpl implements AirplaneRepository {
    @Override
    public List<Airplane> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT a FROM Airplane a", Airplane.class).getResultList();
    }

    @Override
    public Airplane findById(Long id, Session session) {
        return session.get(Airplane.class, id);
    }

    @Override
    public void save(Airplane airplane, Session session) {
        session.save(airplane);
    }

    @Override
    public void update(Airplane airplane, Session session) {
        session.update(airplane);
    }

    @Override
    public void delete(Airplane airplane, Session session) {
        session.delete(airplane);
    }

    @Override
    public void addToFlight(Long id, Long id2, Session session) {
        Airplane airplane = session.get(Airplane.class, id);
        Flight flight = session.get(Flight.class, id2);
        flight.setAirplane(airplane);
        session.update(flight);
//        airplane.setFlight(flight);
//        session.update(airplane);
    }
}
