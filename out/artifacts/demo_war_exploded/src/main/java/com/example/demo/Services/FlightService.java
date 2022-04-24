package com.example.demo.Services;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Flight;
import com.example.demo.Repositories.FlightRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class FlightService {
    @EJB
    FlightRepository flightRepository;

    public List<Flight> getAll(){ return flightRepository.getAll();}

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewFlight(Flight flight) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        flightRepository.save(flight, session);
        tx1.commit();
        session.close();
    }

    public void updateFlightByName(Long id, String flightName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Flight flight = flightRepository.findById(id, session);
        flight.setFlightName(flightName);
        flightRepository.update(flight,session);
        tx1.commit();
        session.close();
    }

    public void deleteAirplaneById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Flight flight = flightRepository.findById(id,session);
        flightRepository.delete(flight, session);
        tx1.commit();
        session.close();
    }
}
